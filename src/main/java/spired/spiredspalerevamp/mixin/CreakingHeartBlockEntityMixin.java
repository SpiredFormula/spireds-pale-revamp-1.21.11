package spired.spiredspalerevamp.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.mob.CreakingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.attribute.EnvironmentAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import spired.spiredspalerevamp.SpiredsPaleRevamp;

@Mixin(net.minecraft.block.entity.CreakingHeartBlockEntity.class)
public abstract class CreakingHeartBlockEntityMixin extends BlockEntiyMixin {

    @Inject(method = "onBlockReplaced", at =  @At("RETURN"))
    private void injectPunishment(BlockPos pos, BlockState oldState, CallbackInfo ci){
        if(!this.world.getEnvironmentAttributes().getAttributeValue(EnvironmentAttributes.CREAKING_ACTIVE_GAMEPLAY, pos)){


            PlayerEntity player = this.world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10, true);
            DamageSources damageSources = this.world.getDamageSources();

            if(!this.world.isClient() && player != null){
                player.damage((ServerWorld) this.world, damageSources.generic(), 18f);
            }

        }
    }
}

@Mixin(net.minecraft.block.entity.BlockEntity.class)
abstract class BlockEntiyMixin{
    @Shadow protected World world;
}


