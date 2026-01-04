package spred.spiredspalerevamp.block.custom;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import spred.spiredspalerevamp.entity.custom.PaleChestEntity;
import java.util.function.Supplier;


public class PaleChestBlock extends ChestBlock {


    public PaleChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, SoundEvent openSound, SoundEvent closeSound, Settings settings) {
        super(blockEntityTypeSupplier, openSound, closeSound, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PaleChestEntity(pos, state);
    }
}
