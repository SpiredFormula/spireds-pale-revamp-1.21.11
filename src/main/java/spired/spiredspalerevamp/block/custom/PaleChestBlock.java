package spired.spiredspalerevamp.block.custom;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.tick.ScheduledTickView;
import spired.spiredspalerevamp.SpiredsPaleRevamp;
import spired.spiredspalerevamp.entity.custom.PaleChestEntity;
import java.util.function.Supplier;


public class PaleChestBlock extends ChestBlock {

    public static final BooleanProperty OPENABLE = BooleanProperty.of("openable");
    public PaleChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityTypeSupplier, SoundEvent openSound, SoundEvent closeSound, Settings settings) {
        super(blockEntityTypeSupplier, openSound, closeSound, settings);

        setDefaultState(getDefaultState().with(OPENABLE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, CHEST_TYPE, WATERLOGGED, OPENABLE);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PaleChestEntity(pos, state);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {

        return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        if(!world.isClient()){
            if(world.getEnvironmentAttributes().getAttributeValue(EnvironmentAttributes.CREAKING_ACTIVE_GAMEPLAY, pos)){

                state.with(OPENABLE, true);
            }
            else{
                state.with(OPENABLE, false);
            }
        }


    }
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        world.scheduleBlockTick(pos, this, 1);

        // Prevents the Chest from being open during the daytime
//        if(!world.isClient() && !state.get(OPENABLE)){
//            player.sendMessage(Text.translatable("spireds-pale-revamp.pale_chest_message"), true);
//            return ActionResult.SUCCESS;
//        }

        if(!world.isClient() && !world.getEnvironmentAttributes().getAttributeValue(EnvironmentAttributes.CREAKING_ACTIVE_GAMEPLAY, pos)){
            player.sendMessage(Text.translatable("spireds-pale-revamp.pale_chest_message"), true);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hit);
    }
}
