package spired.spiredspalerevamp.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import spired.spiredspalerevamp.SpiredsPaleRevamp;
import spired.spiredspalerevamp.block.ModBlocks;
import spired.spiredspalerevamp.entity.custom.PaleChestEntity;

public class ModBlockEntities {

    public static final BlockEntityType<PaleChestEntity> PALE_CHEST = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(SpiredsPaleRevamp.MOD_ID, "pale_chest"), FabricBlockEntityTypeBuilder.create(PaleChestEntity::new, ModBlocks.PALE_CHEST).build());

    public static void initialize(){

    }
}
