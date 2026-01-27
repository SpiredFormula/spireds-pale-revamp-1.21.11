package spired.spiredspalerevamp.datagen;


import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.util.Identifier;
import spired.spiredspalerevamp.block.ModBlocks;

public class ModModelProvider extends FabricModelProvider {


    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerChest(ModBlocks.PALE_CHEST, Blocks.PALE_OAK_PLANKS, Identifier.of("spireds-pale-revamp", "pale_chest"), false);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}