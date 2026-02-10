package spired.spiredspalerevamp.entity.custom;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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


    public static void tick(World world, BlockPos blockPos, BlockState blockState, ChestBlockEntity blockEntity) {



        if(world.getEnvironmentAttributes().getAttributeValue(EnvironmentAttributes.CREAKING_ACTIVE_GAMEPLAY, blockPos) && blockState.get(PaleChestBlock.OPENABLE) == false){
           world.setBlockState(blockPos, blockState.with(PaleChestBlock.OPENABLE, true));
           world.playSound(null, blockPos, SoundEvents.BLOCK_CREAKING_HEART_SPAWN, SoundCategory.PLAYERS, 1f, 1f);

        }
        else if(!world.getEnvironmentAttributes().getAttributeValue(EnvironmentAttributes.CREAKING_ACTIVE_GAMEPLAY, blockPos) && blockState.get(PaleChestBlock.OPENABLE) == true){
            world.setBlockState(blockPos, blockState.with(PaleChestBlock.OPENABLE, false));
            world.playSound(null, blockPos, SoundEvents.BLOCK_CREAKING_HEART_FALL, SoundCategory.PLAYERS, 1f, 1f);
        }
    }
}
