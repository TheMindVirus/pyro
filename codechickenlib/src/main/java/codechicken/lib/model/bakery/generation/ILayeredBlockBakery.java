package codechicken.lib.model.bakery.generation;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by covers1624 on 28/10/2016.
 */
public interface ILayeredBlockBakery extends IBlockBakery {

    /**
     * Used to actually generate quads for your block based on the face and layer being requested.
     * Use {@link Block#canRenderInLayer(IBlockState, BlockRenderLayer)} to cull layers from this.
     * You will ONLY be requested for quads if canRenderInLayer returns true for the specific layer.
     * <p>
     * Face may be null!!
     * Treat a null face as "general" quads, Quads that will NOT be culled by neighboring blocks.
     * <p>
     * Each layer you agree to with canRenderInLayer will be requested for "general" AND face quads.
     *
     * @param face  The face quads are requested for.
     * @param layer The layer quads are requested for.
     * @param state The IExtendedBlockState of your block. {@link IBlockBakery#handleState(IExtendedBlockState, IBlockAccess, BlockPos)} has already been called.
     * @return The quads for the layer, May be an empty list. Never null.
     */
    @Nonnull
    @OnlyIn (Dist.CLIENT)
    List<BakedQuad> bakeLayerFace(@Nullable Direction face, RenderType layer, BlockState state, IModelData data);

}
