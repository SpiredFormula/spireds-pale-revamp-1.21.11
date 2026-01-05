package spred.spiredspalerevamp;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import spred.spiredspalerevamp.entity.ModBlockEntities;
import spred.spiredspalerevamp.render.PaleChestBlockEntityRenderer;

public class SpiredsPaleRevampClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		BlockEntityRendererFactories.register(ModBlockEntities.PALE_CHEST, PaleChestBlockEntityRenderer::new);
	}
}