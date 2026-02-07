package spired.spiredspalerevamp.entity.custom;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.attribute.EnvironmentAttributes;
import org.jspecify.annotations.Nullable;
import spired.spiredspalerevamp.SpiredsPaleRevamp;
import spired.spiredspalerevamp.block.custom.PaleChestBlock;
import spired.spiredspalerevamp.entity.ModBlockEntities;

public class PaleChestEntity extends ChestBlockEntity implements SidedInventory {
    private static final Text CONTAINER_NAME_TEXT = Text.translatable("spireds-pale-revamp.pale_chest_container_name");
    public PaleChestEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PALE_CHEST ,pos, state);
    }

    @Override
    protected Text getContainerName() {
        return CONTAINER_NAME_TEXT;
    }
    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState) {
        // TODO don't make this hard codded and implement BlockStates ((https://docs.fabricmc.net/develop/blocks/blockstates))
        // Stop items from dropping when the chest is locked during tha day time

        if(world != null){

            if(!oldState.get(PaleChestBlock.OPENABLE)){
                return;
            }
        }
        super.onBlockReplaced(pos, oldState);


    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }


    public static void tick(World world, BlockPos blockPos, BlockState blockState, ChestBlockEntity chestBlockEntity) {



        if(world.getEnvironmentAttributes().getAttributeValue(EnvironmentAttributes.CREAKING_ACTIVE_GAMEPLAY, blockPos)){
           world.setBlockState(blockPos, blockState.with(PaleChestBlock.OPENABLE, true));
        }
        else{
            world.setBlockState(blockPos, blockState.with(PaleChestBlock.OPENABLE, false));
        }
    }
}
