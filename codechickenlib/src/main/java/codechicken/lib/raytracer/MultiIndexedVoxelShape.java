package codechicken.lib.raytracer;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import static net.covers1624.quack.util.SneakyUtils.unsafeCast;

/**
 * A VoxelShape implementation, produces a {@link VoxelShapeBlockHitResult} when ray traced.
 * Whilst similar to {@link IndexedVoxelShape}, will ray trace each sub-component provided, returning the closest.
 * <p>
 * The sub-component will have its outline automatically rendered appropriately.
 * <p>
 * Created by covers1624 on 5/12/20.
 */
public class MultiIndexedVoxelShape extends VoxelShape {

    private final VoxelShape merged;
    private final ImmutableSet<IndexedVoxelShape> shapes;

    /**
     * Construct a MultiIndexedVoxelShape, using the combination of all the sub-components
     * as this VoxelShape.
     *
     * @param shapes The sub-components.
     */
    public MultiIndexedVoxelShape(ImmutableSet<IndexedVoxelShape> shapes) {
        this(VoxelShapeCache.merge(unsafeCast(shapes)), shapes);//Generics die in a hole pls, kthx.
    }

    /**
     * Constructs a MultiIndexedVoxelShape, using the provided VoxelShape as this shape,
     * whilst still RayTracing against all the sub-components.
     *
     * @param merged The base shape.
     * @param shapes The sub-components.
     */
    public MultiIndexedVoxelShape(VoxelShape merged, ImmutableSet<IndexedVoxelShape> shapes) {
        super(merged.shape);
        this.merged = merged;
        this.shapes = shapes;
    }

    @Override
    public DoubleList getCoords(Direction.Axis axis) {
        return merged.getCoords(axis);
    }

    @Nullable
    @Override
    public VoxelShapeBlockHitResult clip(Vec3 start, Vec3 end, BlockPos pos) {
        VoxelShapeBlockHitResult closest = null;
        double dist = Double.MAX_VALUE;
        for (IndexedVoxelShape shape : shapes) {
            VoxelShapeBlockHitResult hit = shape.clip(start, end, pos);
            if (hit != null && dist >= hit.dist) {
                closest = hit;
                dist = hit.dist;
            }
        }

        return closest;
    }
}
