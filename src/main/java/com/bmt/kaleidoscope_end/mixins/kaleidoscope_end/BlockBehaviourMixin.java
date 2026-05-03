package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.bmt.kaleidoscope_end.init.KEEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockBehaviourMixin {

    @Inject(method = "getCollisionShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;",
            at = @At("RETURN"),
            cancellable = true)
    private void kaleidoscope_end$voidWalker(BlockGetter level, BlockPos pos, CollisionContext context,
                                             CallbackInfoReturnable<VoxelShape> cir) {
        if (!(context instanceof EntityCollisionContext entityContext)) {
            return;
        }

        if (!(entityContext.getEntity() instanceof LivingEntity living)) {
            return;
        }

        if (living.isShiftKeyDown()) {
            return;
        }

        ItemStack boots = living.getItemBySlot(EquipmentSlot.FEET);
        if (boots.isEmpty()) {
            return;
        }

        Holder<Enchantment> voidWalkerHolder = living.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(KEEnchantments.VOID_WALKER);
        int voidWalkerLevel = EnchantmentHelper.getItemEnchantmentLevel(voidWalkerHolder, boots);

        if (voidWalkerLevel <= 0) {
            return;
        }

        BlockPos entityPos = BlockPos.containing(living.position().add(0, -0.3, 0));

        if (!pos.equals(entityPos)) {
            return;
        }

        if (level.getBlockState(pos).isAir()) {
            cir.setReturnValue(Shapes.block());
        }
    }
}