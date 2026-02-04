package spired.spiredspalerevamp;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spired.spiredspalerevamp.block.ModBlocks;
import spired.spiredspalerevamp.entity.ModBlockEntities;
import spired.spiredspalerevamp.item.ModItems;
import spired.spiredspalerevamp.util.ModMapDecorationTypes;
import spired.spiredspalerevamp.util.ModTags;

import java.util.Optional;

public class SpiredsPaleRevamp implements ModInitializer {
	public static final String MOD_ID = "spireds-pale-revamp";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModBlockEntities.initialize();
		ModTags.initialize();
		ModBlocks.initialize();
		ModItems.initialize();
		ModMapDecorationTypes.initialize();




		// TODO moved to separate class
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, factories -> {
			factories.add((world, entity, random) -> {
				BlockPos structurePos = world.locateStructure(ModTags.ON_PALE_EXPLORER_MAPS, entity.getBlockPos(),  100, true);
				int price = 18;
				int maxUses = 12;
				int experience = 30;
				if(structurePos != null){
					ItemStack map = FilledMapItem.createMap(world, structurePos.getX(), structurePos.getZ(), (byte)2, true, true);
					FilledMapItem.fillExplorationMap(world, map);
					MapState.addDecorationsNbt(map, structurePos, "+", ModMapDecorationTypes.PALE_RUIN);
					map.set(DataComponentTypes.ITEM_NAME, Text.translatable("filled_map.custom"));
					return new TradeOffer(new TradedItem(Items.EMERALD, price), Optional.of(new TradedItem(Items.COMPASS)), map, maxUses, experience, 0.2F);
				}
				else{
					return null;
				}
			});

		});
		LOGGER.info("Hello Fabric world!");


	}
}