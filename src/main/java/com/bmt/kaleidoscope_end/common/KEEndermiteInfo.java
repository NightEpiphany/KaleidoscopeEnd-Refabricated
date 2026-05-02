package com.bmt.kaleidoscope_end.common;

import com.bmt.kaleidoscope_end.api.IEndermiteExtension;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class KEEndermiteInfo {
    private final Endermite endermite;
    public boolean fed = false;
    public int inLove = 0;
    public int loveTime = 0;
    public int cooldown = 0;

    public KEEndermiteInfo(Endermite endermite) {
        this.endermite = endermite;
    }

    public boolean isInLove() {
        return inLove > 0;
    }

    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putBoolean("fed", fed);
        compoundTag.putInt("inLove", inLove);
        compoundTag.putInt("loveTime", loveTime);
        compoundTag.putInt("cooldown", cooldown);
        return compoundTag;
    }

    public void deserializeNBT(CompoundTag nbt) {
        fed = nbt.getBoolean("fed").orElse(false);
        inLove = nbt.getInt("inLove").orElse(0);
        loveTime = nbt.getInt("loveTime").orElse(0);
        cooldown = nbt.getInt("cooldown").orElse(0);
    }

    public void aiStep() {
        if (!endermite.level().isClientSide()) {
            if (isInLove()) {
                findValidBreedPartner(endermite).ifPresent(target -> {
                    endermite.getNavigation().moveTo(target, 1.0D);
                    endermite.lookAt(target, 30.0F, 30.0F);
                    ++loveTime;
                    if (loveTime >= 60 && endermite.distanceToSqr(target) < 9.0D) {
                        Entity child = endermite.getType().create(endermite.level(), EntitySpawnReason.BREEDING);
                        if (child != null) {
                            child.setPos(endermite.getPosition(1.0F));
                            endermite.level().addFreshEntity(child);
                            KEEndermiteInfo partnerInfo = IEndermiteExtension.getInfo(target);
                            partnerInfo.cooldown = 6000;
                            partnerInfo.inLove = 0;
                            partnerInfo.loveTime = 0;
                            cooldown = 6000;
                            inLove = 0;
                            loveTime = 0;
                        }
                    }
                });
            }
            RandomSource random = endermite.getRandom();
            Level level = endermite.level();
            if (inLove > 0) {
                --inLove;
                if (inLove % 10 == 0) {
                    level.addParticle(ParticleTypes.HEART, endermite.getRandomX(1.0D), endermite.getRandomY() + 0.5D, endermite.getRandomZ(1.0D), random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D);
                }
            }
        }
        cooldown = Math.max(0, cooldown - 1);
    }

    public void setInLove(@Nullable Player player) {
        if (cooldown <= 0) {
            inLove = 600;
            fed = true;
            burstLoveParticle(endermite);
            endermite.setPersistenceRequired();
        }
    }

    private void burstLoveParticle(Endermite endermite) {
        if (!(endermite.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        RandomSource random = endermite.getRandom();
        for (int i = 0; i < 7; ++i) {
            serverLevel.sendParticles(ParticleTypes.HEART, endermite.getRandomX(1.0D), endermite.getRandomY() + 0.5D, endermite.getRandomZ(1.0D), 1, random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D, random.nextGaussian() * 0.02D, 1.0D);
        }
    }

    private Optional<Endermite> findValidBreedPartner(Endermite source) {
        List<Endermite> nearbyEndermites = source.level().getEntitiesOfClass(Endermite.class, source.getBoundingBox().inflate(8.0D), other -> other != source && other.isAlive());
        return nearbyEndermites.stream().filter(other -> IEndermiteExtension.getInfo(other).isInLove()).findFirst();
    }
}
