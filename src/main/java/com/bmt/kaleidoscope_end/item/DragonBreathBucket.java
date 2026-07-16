package com.bmt.kaleidoscope_end.item;

import com.bmt.kaleidoscope_end.common.DragonBreathCloudService;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class DragonBreathBucket extends Item {
    public DragonBreathBucket(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        Vec3 clickLocation = context.getClickLocation();
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(level, clickLocation.x(), clickLocation.y(), clickLocation.z());

        float radius = 3.0F;
        CustomData customData = context.getItemInHand().get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            radius = customData.copyTag().getFloat("radius").orElse(radius);
        }

        areaEffectCloud.setRadius(radius);
        areaEffectCloud.setDuration(600);
        areaEffectCloud.setRadiusPerTick((7.0F - areaEffectCloud.getRadius()) / areaEffectCloud.getDuration());
        areaEffectCloud.addEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1, 1));
        DragonBreathCloudService.markBucketPlaced(areaEffectCloud);

        Player player = context.getPlayer();
        if (player != null) {
            // Owner 必须是已加入世界的实体，否则伤害同步时无法取得有效 Entity ID。
            areaEffectCloud.setOwner(player);
        }
        if (!level.addFreshEntity(areaEffectCloud)) {
            return InteractionResult.FAIL;
        }

        if (player != null && !player.getAbilities().instabuild) {
            var itemInHand = context.getItemInHand();
            itemInHand.shrink(1);
            if (itemInHand.isEmpty()) {
                player.setItemInHand(context.getHand(), Items.BUCKET.getDefaultInstance());
            } else if (!player.getInventory().add(Items.BUCKET.getDefaultInstance())) {
                player.drop(Items.BUCKET.getDefaultInstance(), false);
            }
        }
        level.playSound(null, clickLocation.x(), clickLocation.y(), clickLocation.z(), SoundEvents.BUCKET_EMPTY, SoundSource.NEUTRAL, 1.0F, 1.0F);

        return InteractionResult.SUCCESS_SERVER;
    }
}
