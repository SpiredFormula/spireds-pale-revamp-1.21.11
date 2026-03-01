package spired.spiredspalerevamp.util;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import spired.spiredspalerevamp.SpiredsPaleRevamp;

public class ModTags {
    public static TagKey<Structure> ON_PALE_EXPLORER_MAPS = TagKey.of(RegistryKeys.STRUCTURE, Identifier.of(SpiredsPaleRevamp.MOD_ID, "on_pale_explorer_maps"));

    public static void initialize(){
    }
}
