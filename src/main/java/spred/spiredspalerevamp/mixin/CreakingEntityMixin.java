package spred.spiredspalerevamp.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.entity.mob.CreakingEntity.class)
public abstract class CreakingEntityMixin {

    // Injects new attributes
    @Inject(at = @At("RETURN"), method = "createCreakingAttributes", cancellable = true)
    private static void injectAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir){
        cir.setReturnValue(HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 1.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.6F)
                .add(EntityAttributes.ATTACK_DAMAGE, 30)
                .add(EntityAttributes.FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.STEP_HEIGHT, 1.0625));
    }
    // Suppress the step sounds
    @Inject(at = @At("HEAD"), method = "playStepSound", cancellable = true)
    private void removeStepSound(BlockPos pos, BlockState state, CallbackInfo ci){
        ci.cancel();
    }
}
