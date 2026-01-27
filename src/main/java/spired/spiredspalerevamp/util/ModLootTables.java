package spired.spiredspalerevamp.util;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import spired.spiredspalerevamp.SpiredsPaleRevamp;

public class ModLootTables {
    public static RegistryKey<LootTable> PALE_RUIN_LOOT = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(SpiredsPaleRevamp.MOD_ID, "chests/pale_ruin_loot"));

    public static void initialize(){
    }
}
