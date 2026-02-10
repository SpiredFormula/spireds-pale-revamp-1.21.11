package spired.spiredspalerevamp.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class ModVillagerTrades {
    public static void initialize() {

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 4, factories -> {
            factories.add((world, entity, random) -> {
                BlockPos structurePos = world.locateStructure(ModTags.ON_PALE_EXPLORER_MAPS, entity.getBlockPos(),  100, true);
                int price = 18;
                int maxUses = 12;
                int experience = 30;
                if(structurePos != null){
                    ItemStack map = FilledMapItem.createMap(world, structurePos.getX(), structurePos.getZ(), (byte)2, true, true);
                    FilledMapItem.fillExplorationMap(world, map);
                    MapState.addDecorationsNbt(map, structurePos, "+", ModMapDecorationTypes.PALE_RUIN);
                    map.set(DataComponentTypes.ITEM_NAME, Text.translatable("filled_map.custom"));
                    return new TradeOffer(new TradedItem(Items.EMERALD, price), Optional.of(new TradedItem(Items.COMPASS)), map, maxUses, experience, 0.2F);
                }
                else{
                    return null;
                }
            });

        });
    }
}
