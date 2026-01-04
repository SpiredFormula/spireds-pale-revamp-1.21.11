// TODO make it so the chest cannot be opened during the daytime
package spred.spiredspalerevamp.entity.custom;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jspecify.annotations.Nullable;
import spred.spiredspalerevamp.entity.ModBlockEntities;

public class PaleChestEntity extends ChestBlockEntity implements SidedInventory {
    private static final Text CONTAINER_NAME_TEXT = Text.translatable("spiredsPaleRevampContainer.paleChest");
    public PaleChestEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PALE_CHEST ,pos, state);
    }

    @Override
    protected Text getContainerName() {
        return CONTAINER_NAME_TEXT;
    }
    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState) {
        // Stop items from dropping when the chest is broken
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
}
