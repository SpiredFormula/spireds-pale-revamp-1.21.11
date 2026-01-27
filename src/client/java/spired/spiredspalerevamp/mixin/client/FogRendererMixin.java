package spired.spiredspalerevamp.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.AtmosphericFogModifier;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogModifier;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import spired.spiredspalerevamp.config.ModConfig;

import java.util.List;


@Mixin(net.minecraft.client.render.fog.FogRenderer.class)
public class FogRendererMixin {

    @Shadow @Final private static List<FogModifier> FOG_MODIFIERS;
    private float lerpProgress = 0f;
    private float fogTransitionTime = 5f;
    private ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


    @Inject(
            method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getDevice()Lcom/mojang/blaze3d/systems/GpuDevice;")

    )
    private void paleGardenFog(Camera camera, int viewDistance, RenderTickCounter renderTickCounter, float f, ClientWorld clientWorld, CallbackInfoReturnable<Vector4f> cir,
                               @Local FogData fogData, @Local(ordinal = 1) float h, @Local(ordinal = 2) float i, @Local CameraSubmersionType cameraSubmersionType, @Local Entity entity) {
        if(!config.PaleGardenFog) return;

        MinecraftClient clientInstance = MinecraftClient.getInstance();
        ClientPlayerEntity player = clientInstance.player;

        if(player == null || clientInstance.world == null){
            return;
        }

        BlockPos playerBlockPosition = player.getBlockPos();
        RegistryEntry<Biome> currentBiome = clientInstance.world.getBiome(playerBlockPosition);


        if(!currentBiome.matchesKey(BiomeKeys.PALE_GARDEN) && lerpProgress <= 0) {
            return;
        }

        for(FogModifier fogModifier: FOG_MODIFIERS){
            if(fogModifier.shouldApply(cameraSubmersionType, entity) && !(fogModifier instanceof AtmosphericFogModifier) ) return;
        }

        float transitionSpeed = 1.0f/(fogTransitionTime * (fogTransitionTime * 10));
        float startingEnvironmentStart = fogData.environmentalStart;
        float startingEnvironmentEnd = fogData.environmentalEnd;
        float paleEnvironmentStart = 0f;
        float paleEnvironmentEnd = 25f;


        if(currentBiome.matchesKey(BiomeKeys.PALE_GARDEN) && playerBlockPosition.getY() > 50){

            fogData.environmentalStart = MathHelper.lerp(this.lerpProgress, startingEnvironmentStart, paleEnvironmentStart);
            fogData.environmentalEnd = MathHelper.lerp(this.lerpProgress, startingEnvironmentEnd, paleEnvironmentEnd);

            this.lerpProgress = MathHelper.clamp(this.lerpProgress + renderTickCounter.getTickProgress(true) * transitionSpeed, 0f, 1f);
        }
        else{
            fogData.environmentalStart = MathHelper.lerp(this.lerpProgress, startingEnvironmentStart, paleEnvironmentStart);
            fogData.environmentalEnd = MathHelper.lerp(this.lerpProgress, startingEnvironmentEnd, paleEnvironmentEnd);

            this.lerpProgress = MathHelper.clamp(this.lerpProgress - renderTickCounter.getTickProgress(true) * transitionSpeed, 0f, 1f);
        }


    }
}
