package com.bmt.kaleidoscope_end.mixins.kaleidoscope_end;

import com.bmt.kaleidoscope_end.init.KEBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void isValid(BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        BlockEntityType<?> blockEntityType = (BlockEntityType<?>) (Object) this;
        if (blockEntityType == BlockEntityType.BRUSHABLE_BLOCK) {
            if (blockState.getBlock() == KEBlocks.SUSPICIOUS_END_STONE
                    || blockState.getBlock() == KEBlocks.SUSPICIOUS_DRAGON_EGG) {
                cir.setReturnValue(true);
            }
        }
    }

}
