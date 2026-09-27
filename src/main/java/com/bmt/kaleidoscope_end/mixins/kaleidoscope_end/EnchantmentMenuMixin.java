package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.bmt.kaleidoscope_end.init.KEItem;
import com.bmt.kaleidoscope_end.init.KETags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin {
    @Shadow
    protected abstract List<EnchantmentInstance> getEnchantmentList(RegistryAccess access, ItemStack itemStack, int slot, int enchantmentCost);

    @Shadow
    @Final
    private RandomSource random;

    @Shadow
    @Final
    private Container enchantSlots;

    @Shadow
    @Final
    public int[] costs;

    @Unique
    private boolean kaleidoscope$calling = false;

    @Inject(method = "getEnchantmentList", at = @At("RETURN"), cancellable = true)
    private void modifyEnchantments(RegistryAccess access, ItemStack itemStack, int slot, int enchantmentCost, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {

        if (kaleidoscope$calling) return;

        ItemStack item = this.enchantSlots.getItem(1);
        if (!item.is(KEItem.VOID_CONCH)) {
            return;
        }

        kaleidoscope$calling = true;

        List<EnchantmentInstance> olds = new ArrayList<>(cir.getReturnValue());
        List<EnchantmentInstance> news = getEnchantmentList(access, itemStack, slot + 1, enchantmentCost);
        List<EnchantmentInstance> toAdd = new ArrayList<>();
        
        for (int i = 0; i < news.size(); i++) {
            EnchantmentInstance e1 = news.get(i);
            boolean found = false;
            
            for (int j = 0; j < olds.size(); j++) {
                EnchantmentInstance e2 = olds.get(j);
                if (e2.enchantment().equals(e1.enchantment())) {
                    found = true;
                    if (e1.level() > e2.level()) {
                        olds.set(j, e1);
                    } else if (e1.level() == e2.level()) {
                        olds.set(j, new EnchantmentInstance(e1.enchantment(), e1.level() + 1));
                    }
                    break;
                }
            }

            if (!found && i == news.size() - 1) {
                boolean compatible = true;
                for (EnchantmentInstance old : olds) {
                    if (!Enchantment.areCompatible(e1.enchantment(), old.enchantment())) {
                        compatible = false;
                        break;
                    }
                }
                if (compatible) {
                    toAdd.add(e1);
                }
            }
        }

        olds.addAll(toAdd);

        if (random.nextFloat() <= 0.6F) {
            var enchantmentRegistry = access.lookupOrThrow(Registries.ENCHANTMENT);
            enchantmentRegistry.get(KETags.Enchantments.KE_ENCHANTMENTS).ifPresent(holders -> {
                List<Holder<Enchantment>> holderList = holders.stream().toList();
                if (!holderList.isEmpty()) {
                    Holder<Enchantment> holder = holderList.get(random.nextInt(holderList.size()));

                    int add = 1;
                    int maxLevel = holder.value().getMaxLevel();
                    for (int i = 1; i <= maxLevel; i++) {
                        if (holder.value().getMinCost(i) > enchantmentCost) {
                            maxLevel = maxLevel - 1;
                            break;
                        }
                    }
                    int total = 0;
                    for (int i = 1; i <= maxLevel; i++) {
                        total = total + i + add;
                    }
                    int nextInt = 1 + random.nextInt(total);
                    for (int i = 1; i <= maxLevel; i++) {
                        nextInt = nextInt - (i + add);
                        if (nextInt <= 0) {
                            olds.add(new EnchantmentInstance(holder, i));
                            break;
                        }
                    }
                }
            });
        }

        kaleidoscope$calling = false;

        cir.setReturnValue(olds);
    }
    @Unique
    private static final TagKey<Item> EXTRA_FUEL = KETags.Items.ENCHANTING_FUELS;

    @WrapOperation( method = "quickMoveStack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"
            ))
    private boolean kaleidoscope$shiftMove(ItemStack instance, Object o, Operation<Boolean> original) {
        if (instance.is((Item) o)) return true;
        return instance.is(EXTRA_FUEL);
    }
}
