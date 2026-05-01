package com.bmt.kaleidoscope_end.event;

import com.bmt.kaleidoscope_end.api.IEndermiteExtension;
import com.bmt.kaleidoscope_end.api.event.EnderManAngerEvent;
import com.bmt.kaleidoscope_end.common.KEEndermiteInfo;
import com.bmt.kaleidoscope_end.init.KEBlocks;
import com.bmt.kaleidoscope_end.init.KEEffects;
import com.bmt.kaleidoscope_end.init.KEEvents;
import com.bmt.kaleidoscope_end.init.KEItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.List;

public final class KEPlayerEvents {
    private static final ThreadLocal<Boolean> REDIRECTING_DAMAGE = ThreadLocal.withInitial(() -> false);

    private KEPlayerEvents() {
    }

    public static void register() {
        UseBlockCallback.EVENT.register(KEPlayerEvents::onUseBlock);
        UseItemCallback.EVENT.register(KEPlayerEvents::onUseItem);
        UseEntityCallback.EVENT.register(KEPlayerEvents::onUseEntity);
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(KEPlayerEvents::onAllowDamage);
        ServerLivingEntityEvents.AFTER_DEATH.register(KEPlayerEvents::onAfterDeath);
        KEEvents.STARE_ENDERMAN.register(KEPlayerEvents::onStareEnderman);
    }

    private static void onStareEnderman(EnderManAngerEvent event) {
        if (event.getPlayer().hasEffect(KEEffects.MINT)) {
            event.setCanceled(true);
        }
    }

    private static InteractionResult onUseBlock(Player player, Level level, net.minecraft.world.InteractionHand hand, BlockHitResult hitResult) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (!heldStack.is(KEItem.DRAGON_TOOTH)) {
            return InteractionResult.PASS;
        }

        var state = level.getBlockState(hitResult.getBlockPos());
        if (state.is(KEBlocks.SUSPICIOUS_DRAGON_EGG) || state.is(Blocks.DRAGON_EGG)) {
            return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }

    private static InteractionResultHolder<ItemStack> onUseItem(Player player, Level level, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.is(Items.BUCKET)) {
            return InteractionResultHolder.pass(stack);
        }

        AABB area = player.getBoundingBox().inflate(2.0D);
        List<AreaEffectCloud> clouds = level.getEntitiesOfClass(AreaEffectCloud.class, area, cloud ->
                cloud.isAlive() && cloud.getOwner() instanceof EnderDragon);
        if (clouds.isEmpty()) {
            return InteractionResultHolder.pass(stack);
        }

        AreaEffectCloud cloud = clouds.getFirst();
        float radius = cloud.getRadius();
        cloud.discard();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BUCKET_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
        level.gameEvent(player, GameEvent.FLUID_PICKUP, player.position());

        ItemStack dragonBreathBucket = KEItem.DRAGON_BREATH_BUCKET_ITEM.getDefaultInstance();
        CompoundTag tag = new CompoundTag();
        tag.putFloat("radius", radius);
        dragonBreathBucket.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));

        stack.shrink(1);
        if (stack.isEmpty()) {
            player.setItemInHand(hand, dragonBreathBucket);
        } else if (!player.getInventory().add(dragonBreathBucket)) {
            player.drop(dragonBreathBucket, false);
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    private static InteractionResult onUseEntity(Player player, Level level, InteractionHand hand, Entity entity, EntityHitResult hitResult) {
        if (!(entity instanceof Endermite endermite) || !player.getItemInHand(hand).is(Items.AMETHYST_SHARD) || level.isClientSide()) {
            return InteractionResult.PASS;
        }

        KEEndermiteInfo info = IEndermiteExtension.getInfo(endermite);
        if (info.inLove > 0) {
            return InteractionResult.PASS;
        }

        info.setInLove(player);
        if (!player.getAbilities().instabuild) {
            player.getItemInHand(hand).shrink(1);
            endermite.setTarget(null);
        }
        return InteractionResult.SUCCESS;
    }

    private static boolean onAllowDamage(LivingEntity entity, DamageSource source, float amount) {
        if (REDIRECTING_DAMAGE.get()) {
            return true;
        }
        if (entity.level().isClientSide()) {
            return true;
        }
        if (entity.hasEffect(KEEffects.DREAM) && source.is(DamageTypes.FALL)) {
            return false;
        }
        if (entity.hasEffect(KEEffects.VOID_EROSION) && entity.getHealth() <= amount) {
            entity.setHealth(1.0F);
            return false;
        }
        if (entity.hasEffect(KEEffects.MINT) && amount > 0.0F) {
            REDIRECTING_DAMAGE.set(true);
            try {
                entity.hurt(source, amount * 0.2F);
            } finally {
                REDIRECTING_DAMAGE.set(false);
            }
            return false;
        }
        return true;
    }

    private static void onAfterDeath(LivingEntity entity, DamageSource source) {
        if (entity.getType() != EntityType.ENDER_DRAGON || !(source.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack stack = player.getInventory().items.get(i);
            if (stack.is(Items.DRAGON_EGG)) {
                player.getInventory().items.set(i, new ItemStack(KEItem.SUSPICIOUS_DRAGON_EGG_ITEM, stack.getCount()));
            }
        }
    }
}
