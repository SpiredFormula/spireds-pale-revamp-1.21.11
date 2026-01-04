package spred.spiredspalerevamp.render;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LidOpenable;
import net.minecraft.block.enums.ChestType;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.block.entity.model.ChestBlockModel;
import net.minecraft.client.render.block.entity.state.ChestBlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import spred.spiredspalerevamp.SpiredsPaleRevamp;

import static net.minecraft.client.render.TexturedRenderLayers.CHEST_ATLAS_TEXTURE;

public class PaleChestBlockEntityRenderer<T extends BlockEntity & LidOpenable> extends ChestBlockEntityRenderer<T> {
    private final SpriteHolder materials;
    private final ChestBlockModel singleChest;
    private final ChestBlockModel doubleChestLeft;
    private final ChestBlockModel doubleChestRight;


    public PaleChestBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(context);

        this.materials = context.spriteHolder();
        this.singleChest = new ChestBlockModel(context.getLayerModelPart(EntityModelLayers.CHEST));
        this.doubleChestLeft = new ChestBlockModel(context.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_LEFT));
        this.doubleChestRight = new ChestBlockModel(context.getLayerModelPart(EntityModelLayers.DOUBLE_CHEST_RIGHT));

    }

    @Override
    public void render(ChestBlockEntityRenderState chestBlockEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {
        matrixStack.push();
        matrixStack.translate(0.5F, 0.5F, 0.5F);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-chestBlockEntityRenderState.yaw));
        matrixStack.translate(-0.5F, -0.5F, -0.5F);
        float f = chestBlockEntityRenderState.lidAnimationProgress;
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        SpriteIdentifier spriteIdentifier = createChestTextureId(chestBlockEntityRenderState.chestType);
        RenderLayer renderLayer = spriteIdentifier.getRenderLayer(RenderLayers::entityCutout);
        Sprite sprite = this.materials.getSprite(spriteIdentifier);
        if (chestBlockEntityRenderState.chestType != ChestType.SINGLE) {
            if (chestBlockEntityRenderState.chestType == ChestType.LEFT) {
                orderedRenderCommandQueue.submitModel(
                        this.doubleChestLeft,
                        f,
                        matrixStack,
                        renderLayer,
                        chestBlockEntityRenderState.lightmapCoordinates,
                        OverlayTexture.DEFAULT_UV,
                        -1,
                        sprite,
                        0,
                        chestBlockEntityRenderState.crumblingOverlay
                );
            } else {
                orderedRenderCommandQueue.submitModel(
                        this.doubleChestRight,
                        f,
                        matrixStack,
                        renderLayer,
                        chestBlockEntityRenderState.lightmapCoordinates,
                        OverlayTexture.DEFAULT_UV,
                        -1,
                        sprite,
                        0,
                        chestBlockEntityRenderState.crumblingOverlay
                );
            }
        } else {
            orderedRenderCommandQueue.submitModel(
                    this.singleChest,
                    f,
                    matrixStack,
                    renderLayer,
                    chestBlockEntityRenderState.lightmapCoordinates,
                    OverlayTexture.DEFAULT_UV,
                    -1,
                    sprite,
                    0,
                    chestBlockEntityRenderState.crumblingOverlay
            );
        }

        matrixStack.pop();
    }


    private static SpriteIdentifier createChestTextureId(ChestType type) {
        switch (type) {
            case LEFT:
                return new SpriteIdentifier(CHEST_ATLAS_TEXTURE, Identifier.of(SpiredsPaleRevamp.MOD_ID,"entity/chest/pale_chest_left"));
            case RIGHT:
                return new SpriteIdentifier(CHEST_ATLAS_TEXTURE, Identifier.of(SpiredsPaleRevamp.MOD_ID,"entity/chest/pale_chest_right"));
            case SINGLE:
            default:
                return new SpriteIdentifier(CHEST_ATLAS_TEXTURE, Identifier.of(SpiredsPaleRevamp.MOD_ID,"entity/chest/pale_chest"));
        }
    }
}
