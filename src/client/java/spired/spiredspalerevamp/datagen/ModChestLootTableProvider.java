package spired.spiredspalerevamp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import spired.spiredspalerevamp.util.ModLootTables;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModChestLootTableProvider extends SimpleFabricLootTableProvider {
    RegistryWrapper.WrapperLookup registries;
    public ModChestLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
        registries = registryLookup.join();
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {

                lootTableBiConsumer.accept(ModLootTables.PALE_RUIN_LOOT, createPaleRuinLootTable());
    }

    LootTable.Builder createPaleRuinLootTable(){
        return LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(2.0f, 6.0f))
                        .with(ItemEntry.builder(Items.EMERALD).weight(2).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f))))
                        .with(ItemEntry.builder(Items.DIAMOND).weight(5).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                        .with(ItemEntry.builder(Items.IRON_INGOT).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
                        .with(ItemEntry.builder(Items.GOLD_INGOT).weight(15).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 7.0F))))
                        .with(
                                ItemEntry.builder(Items.DIAMOND_SWORD)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.DIAMOND_SPEAR)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.DIAMOND_BOOTS)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.DIAMOND_CHESTPLATE)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.DIAMOND_LEGGINGS)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.DIAMOND_HELMET)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.DIAMOND_PICKAXE)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.DIAMOND_SHOVEL)
                                        .weight(1)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(0.0F, 19.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.IRON_SWORD)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 29.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.IRON_BOOTS)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 29.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.IRON_CHESTPLATE)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 29.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.IRON_LEGGINGS)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 29.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.IRON_HELMET)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 29.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.IRON_PICKAXE)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 29.0F)))
                        )
                        .with(
                                ItemEntry.builder(Items.IRON_SHOVEL)
                                        .weight(3)
                                        .apply(EnchantWithLevelsLootFunction.builder(registries, UniformLootNumberProvider.create(10.0F, 29.0F)))
                        )
                );
    }
}
