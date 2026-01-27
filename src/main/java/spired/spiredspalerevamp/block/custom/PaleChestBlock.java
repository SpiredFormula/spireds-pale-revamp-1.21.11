package spired.spiredspalerevamp.block.custom;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spired.spiredspalerevamp.SpiredsPaleRevamp;
import spired.spiredspalerevamp.entity.custom.PaleChestEntity;
import java.util.function.Supplier;


public class PaleChestBlock extends ChestBlock {


    public PaleChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, SoundEvent openSound, SoundEvent closeSound, Settings settings) {
        super(blockEntityTypeSupplier, openSound, closeSound, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PaleChestEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        // TODO don't make this hard codded and implement BlockStates (https://docs.fabricmc.net/develop/blocks/blockstates)

        // Prevents the Chest from being open during the daytime
        if(!world.isClient() && world.getTimeOfDay() <= 13000){
            SpiredsPaleRevamp.LOGGER.info(Long.toString(world.getTimeOfDay()));
            player.sendMessage(Text.translatable("spireds-pale-revamp.pale_chest_message"), true);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hit);
    }
}
