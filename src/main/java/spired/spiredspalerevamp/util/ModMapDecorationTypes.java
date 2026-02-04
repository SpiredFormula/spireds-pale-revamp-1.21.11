package spired.spiredspalerevamp.util;

import net.minecraft.block.MapColor;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import spired.spiredspalerevamp.SpiredsPaleRevamp;

public class ModMapDecorationTypes {

    public static RegistryEntry<MapDecorationType> PALE_RUIN = register("pale_ruin", "ruin_pale", true, MapColor.DEEPSLATE_GRAY.color, false, true);

    private static RegistryEntry<MapDecorationType> register(
            String id, String assetId, boolean showOnItemFrame, int mapColor, boolean trackCount, boolean explorationMapElement
    ) {
        RegistryKey<MapDecorationType> registryKey = RegistryKey.of(RegistryKeys.MAP_DECORATION_TYPE, Identifier.of(SpiredsPaleRevamp.MOD_ID, id));
        MapDecorationType mapDecorationType = new MapDecorationType(Identifier.of(SpiredsPaleRevamp.MOD_ID, assetId), showOnItemFrame, mapColor, explorationMapElement, trackCount);
        return Registry.registerReference(Registries.MAP_DECORATION_TYPE, registryKey, mapDecorationType);
    }

    public static void initialize(){
    }
}
