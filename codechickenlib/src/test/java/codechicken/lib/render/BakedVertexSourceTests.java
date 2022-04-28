package codechicken.lib.render;

import codechicken.lib.model.CachedFormat;
import codechicken.lib.model.Quad;
import net.minecraft.client.renderer.block.model.BakedQuad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by covers1624 on 1/1/22.
 */
public class BakedVertexSourceTests {

    static {
        // Init vertex attributes.
        CCRenderState.instance();
    }

    @Test
    public void testExpand() {
        BakedVertexSource source = new BakedVertexSource();
        assertEquals(0, source.availableVertices());
        assertEquals(24, source.getVertices().length);
        source.ensureSpace(32);
        assertEquals(32, source.getVertices().length);
        source.ensureSpace(2048);
        assertEquals(2048, source.getVertices().length);
        source.ensureSpace(2048);
        assertEquals(2048, source.getVertices().length);
    }

    @Test
    public void testExpandWithQuad() {
        Quad unbaked = new Quad();
        unbaked.reset(CachedFormat.BLOCK);
        BakedQuad quad = unbaked.bake();

        BakedVertexSource source = new BakedVertexSource();
        source.reset(CachedFormat.BLOCK);
        assertEquals(0, source.availableVertices());
        assertEquals(24, source.getVertices().length);

        quad.pipe(source);
        assertEquals(4, source.availableVertices());
        assertEquals(24, source.getVertices().length);

        quad.pipe(source);
        assertEquals(8, source.availableVertices());
        assertEquals(24, source.getVertices().length);

        quad.pipe(source);
        assertEquals(12, source.availableVertices());
        assertEquals(24, source.getVertices().length);

        quad.pipe(source);
        assertEquals(16, source.availableVertices());
        assertEquals(24, source.getVertices().length);

        quad.pipe(source);
        assertEquals(20, source.availableVertices());
        assertEquals(24, source.getVertices().length);

        quad.pipe(source);
        assertEquals(24, source.availableVertices());
        assertEquals(24, source.getVertices().length);

        quad.pipe(source);
        assertEquals(28, source.availableVertices());
        assertEquals(28, source.getVertices().length);

        source.reset(CachedFormat.BLOCK);
        assertEquals(0, source.availableVertices());
        assertEquals(28, source.getVertices().length);
    }
}
