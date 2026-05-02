package com.bmt.kaleidoscope_end.item;

import com.bmt.kaleidoscope_end.config.MainConfig;
import com.bmt.kaleidoscope_end.util.tier.DragonToothTier;
import com.github.ysbbbbbb.kaleidoscopecookery.item.KitchenKnifeItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class DragonToothKnifeItem extends KitchenKnifeItem {
    private static final Set<Identifier> END_MOBS_CACHE = new HashSet<>();
    private static final Identifier THE_END_DIMENSION = Identifier.fromNamespaceAndPath("minecraft", "the_end");

    public DragonToothKnifeItem(Item.Properties properties) {
        super(properties.durability(3542).fireResistant(), DragonToothTier.INSTANCE, 3.0F, -2.4F);
    }

    @Override
    public void hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);
        if (isInEndDimension(attacker.level()) || isEndMob(target)) {
            target.hurt(target.damageSources().mobAttack(attacker), getAttackDamage() * 3.0F);
        }
    }

    private float getAttackDamage() {
        return DragonToothTier.INSTANCE.attackDamageBonus() + 3.0F;
    }

    private boolean isInEndDimension(Level level) {
        ResourceKey<Level> dimension = level.dimension();
        return dimension.identifier().equals(THE_END_DIMENSION);
    }

    private boolean isEndMob(LivingEntity entity) {
        Identifier entityId = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
        if (END_MOBS_CACHE.isEmpty()) {
            loadEndMobsFromConfig();
        }
        return END_MOBS_CACHE.contains(entityId);
    }

    private static void loadEndMobsFromConfig() {
        END_MOBS_CACHE.clear();
        for (String mobId : MainConfig.DRAGON_TOOTH_KNIFE_EXTRA_END_MOBS.get()) {
            try {
                END_MOBS_CACHE.add(Identifier.parse(mobId));
            } catch (Exception ignored) {
            }
        }
    }
}
