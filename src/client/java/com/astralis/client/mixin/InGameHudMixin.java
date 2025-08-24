package com.astralis.client.mixin;

import com.astralis.client.AstralisClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            PlayerEntity player = client.player;
            TextRenderer textRenderer = client.textRenderer;

            String x = String.format("X: %.2f", player.getX());
            String y = String.format("Y: %.2f", player.getY());
            String z = String.format("Z: %.2f", player.getZ());

            String direction = "Direction: " + player.getHorizontalFacing().toString();

            BlockPos pos = player.getBlockPos();
            Biome biome = player.getWorld().getBiome(pos).value();
            String biomeName = "Biome: " + biome.toString();

            textRenderer.draw(x, 5, 5, 0xFFFFFF, true, matrices.peek().getPositionMatrix(), client.getBufferBuilders().getOutlineVertexConsumers(), TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
            textRenderer.draw(y, 5, 15, 0xFFFFFF, true, matrices.peek().getPositionMatrix(), client.getBufferBuilders().getOutlineVertexConsumers(), TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
            textRenderer.draw(z, 5, 25, 0xFFFFFF, true, matrices.peek().getPositionMatrix(), client.getBufferBuilders().getOutlineVertexConsumers(), TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
            textRenderer.draw(direction, 5, 35, 0xFFFFFF, true, matrices.peek().getPositionMatrix(), client.getBufferBuilders().getOutlineVertexConsumers(), TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
            textRenderer.draw(biomeName, 5, 45, 0xFFFFFF, true, matrices.peek().getPositionMatrix(), client.getBufferBuilders().getOutlineVertexConsumers(), TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
        }
    }
}
