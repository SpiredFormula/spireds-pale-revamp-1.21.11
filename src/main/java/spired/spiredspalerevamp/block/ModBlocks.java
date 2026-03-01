package spired.spiredspalerevamp.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import spired.spiredspalerevamp.SpiredsPaleRevamp;
import spired.spiredspalerevamp.block.custom.PaleChestBlock;
import spired.spiredspalerevamp.entity.ModBlockEntities;
import java.util.function.Function;

public class ModBlocks {
    public static Block PALE_CHEST = registerBlock("pale_chest", settings -> new PaleChestBlock(() -> ModBlockEntities.PALE_CHEST , SoundEvents.BLOCK_CHEST_OPEN, SoundEvents.BLOCK_CHEST_CLOSE, settings), AbstractBlock.Settings.copy(Blocks.CHEST), true);


    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem){

        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(SpiredsPaleRevamp.MOD_ID,name ));

        Block block = blockFactory.apply(settings.registryKey(blockKey));
        if(shouldRegisterItem){
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SpiredsPaleRevamp.MOD_ID,name ));
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static void initialize(){

    }
}
