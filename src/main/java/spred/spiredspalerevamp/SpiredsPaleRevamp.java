package spred.spiredspalerevamp;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spred.spiredspalerevamp.block.ModBlocks;
import spred.spiredspalerevamp.entity.ModBlockEntities;
import spred.spiredspalerevamp.item.ModItems;
import spred.spiredspalerevamp.util.ModTags;

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
		LOGGER.info("Hello Fabric world!");

	}
}