package spred.spiredspalerevamp.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import spred.spiredspalerevamp.SpiredsPaleRevamp;


@Mixin(net.minecraft.client.render.fog.FogRenderer.class)
public class FogRendererMixin {
    private float lerpProgress = 0f;
    private float fogTransitionTime = 5f;
    @Inject(
            method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getDevice()Lcom/mojang/blaze3d/systems/GpuDevice;")

    )
    private void paleGardenFog(Camera camera, int viewDistance, RenderTickCounter renderTickCounter, float f, ClientWorld clientWorld, CallbackInfoReturnable<Vector4f> cir, @Local FogData fogData, @Local(ordinal = 1) float h, @Local(ordinal = 2) float i) {
        SpiredsPaleRevamp.LOGGER.info(Float.toString(this.lerpProgress));
        MinecraftClient clientInstance = MinecraftClient.getInstance();
        ClientPlayerEntity player = clientInstance.player;
        if(player == null || clientInstance.world == null){
            return;
        }
        RegistryEntry<Biome> currentBiome = clientInstance.world.getBiome(player.getBlockPos());

        float transitionSpeed = 1.0f/(fogTransitionTime * (fogTransitionTime * 10));
        float startingRenderDistanceStart = fogData.renderDistanceStart;
        float startingRenderDistanceEnd = fogData.renderDistanceEnd;
        float paleRenderDistanceStart = 0f;
        float paleRenderDistanceEnd = 25f;


        if(currentBiome.matchesKey(BiomeKeys.PALE_GARDEN)){

            fogData.renderDistanceStart = MathHelper.lerp(this.lerpProgress, startingRenderDistanceStart, paleRenderDistanceStart );
            fogData.renderDistanceEnd = MathHelper.lerp(this.lerpProgress, startingRenderDistanceEnd, paleRenderDistanceEnd);

            this.lerpProgress = MathHelper.clamp(this.lerpProgress + renderTickCounter.getTickProgress(true) * transitionSpeed, 0f, 1f);
        }
        else{
            fogData.renderDistanceStart = MathHelper.lerp(this.lerpProgress, startingRenderDistanceStart, paleRenderDistanceStart );
            fogData.renderDistanceEnd = MathHelper.lerp(this.lerpProgress, startingRenderDistanceEnd, paleRenderDistanceEnd);
            this.lerpProgress = MathHelper.clamp(this.lerpProgress - renderTickCounter.getTickProgress(true) * transitionSpeed, 0f, 1f);
        }
    }
}
