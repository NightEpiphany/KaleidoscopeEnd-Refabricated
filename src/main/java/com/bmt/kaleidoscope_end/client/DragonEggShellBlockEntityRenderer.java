package com.bmt.kaleidoscope_end.client;

import com.bmt.kaleidoscope_end.block.entity.DragonEggShellBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.jspecify.annotations.NonNull;

public final class DragonEggShellBlockEntityRenderer implements BlockEntityRenderer<DragonEggShellBlockEntity, DragonEggShellRenderState> {
    private final ItemModelResolver itemModelResolver;

    public DragonEggShellBlockEntityRenderer(Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public @NonNull DragonEggShellRenderState createRenderState() {
        return new DragonEggShellRenderState();
    }

    @Override
    public void extractRenderState(
            @NonNull DragonEggShellBlockEntity shell,
            @NonNull DragonEggShellRenderState state,
            float partialTick,
            @NonNull Vec3 cameraPosition,
            CrumblingOverlay crumblingOverlay
    ) {
        BlockEntityRenderer.super.extractRenderState(shell, state, partialTick, cameraPosition, crumblingOverlay);
        state.facing = shell.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
        Level level = shell.getLevel();
        int seed = (int) shell.getBlockPos().asLong();
        for (int i = 0; i < state.items.length; i++) {
            itemModelResolver.updateForTopItem(
                    state.items[i],
                    shell.getItem(i),
                    ItemDisplayContext.FIXED,
                    level,
                    null,
                    seed + i
            );
        }
    }

    @Override
    public void submit(
            DragonEggShellRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState camera
    ) {
        int rotation = state.facing.get2DDataValue() * 90;
        poseStack.pushPose();
        poseStack.translate(0.5D, 0.0D, 0.5D);
        poseStack.mulPose(Axis.YN.rotationDegrees(rotation).get(new Matrix4f()));
        poseStack.translate(-0.5D, 0.0D, -0.5D);

        for (int i = 0; i < state.items.length; i++) {
            ItemStackRenderState item = state.items[i];
            if (item.isEmpty()) {
                continue;
            }

            poseStack.pushPose();
            poseStack.translate(0.1D + (i % 4) * 0.15D, 0.3D, 0.35D + (i / 4) * 0.32D);
            poseStack.mulPose(Axis.YN.rotationDegrees(90.0F).get(new Matrix4f()));
            poseStack.mulPose(Axis.XN.rotationDegrees(-30.0F).get(new Matrix4f()));
            poseStack.scale(0.375F, 0.375F, 0.375F);
            item.submit(poseStack, collector, state.lightCoords, 0, 0);
            poseStack.popPose();
        }

        poseStack.popPose();
    }
}
