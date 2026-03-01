package spired.spiredspalerevamp;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import spired.spiredspalerevamp.config.ModConfig;
import spired.spiredspalerevamp.entity.ModBlockEntities;
import spired.spiredspalerevamp.render.PaleChestBlockEntityRenderer;



public class SpiredsPaleRevampClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		BlockEntityRendererFactories.register(ModBlockEntities.PALE_CHEST, PaleChestBlockEntityRenderer::new);
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);


	}
}