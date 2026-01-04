package spred.spiredspalerevamp.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import spred.spiredspalerevamp.SpiredsPaleRevamp;
import spred.spiredspalerevamp.block.ModBlocks;

import java.util.function.Function;

public class ModItems {


    // Create creative tab
    public static RegistryKey<ItemGroup> SPIREDS_PALE_REVAMP_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(SpiredsPaleRevamp.MOD_ID, "spireds_pale_revamp_group"));
    public static final ItemGroup SPIREDS_PALE_REVAMP_GROUP = FabricItemGroup.builder().icon(() -> new ItemStack(Items.CREAKING_HEART)).displayName(Text.translatable("spiredsPaleRevampGroup.spireds-pale-revamp")).build();

    private static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Creates the Resource Key
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SpiredsPaleRevamp.MOD_ID, name));
        // Create the item instance
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        // Registers the item
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize( ){
        Registry.register(Registries.ITEM_GROUP, SPIREDS_PALE_REVAMP_GROUP_KEY, SPIREDS_PALE_REVAMP_GROUP);
        ItemGroupEvents.modifyEntriesEvent(SPIREDS_PALE_REVAMP_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModBlocks.PALE_CHEST.asItem());
        });

    }
}
