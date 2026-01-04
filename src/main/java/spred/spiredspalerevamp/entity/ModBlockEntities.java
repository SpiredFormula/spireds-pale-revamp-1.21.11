package spred.spiredspalerevamp.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import spred.spiredspalerevamp.SpiredsPaleRevamp;
import spred.spiredspalerevamp.block.ModBlocks;
import spred.spiredspalerevamp.entity.custom.PaleChestEntity;

public class ModBlockEntities {

    public static final BlockEntityType<PaleChestEntity> PALE_CHEST = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(SpiredsPaleRevamp.MOD_ID, "pale_chest"), FabricBlockEntityTypeBuilder.create(PaleChestEntity::new, ModBlocks.PALE_CHEST).build());

    public static void initialize(){

    }
}
