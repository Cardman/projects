package aiki.map.levels;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class BlockBoundsTest {

    @Test
    public void invalidate1Test() {
        BlockBounds blockBounds_ = new BlockBounds();
        blockBounds_.setxLeftTop((short) 0);
        blockBounds_.setyLeftTop((short) 0);
        blockBounds_.setxRightTop((short) 3);
        blockBounds_.setyRightTop((short) 0);
        blockBounds_.setxLeftBottom((short) 0);
        blockBounds_.setyLeftBottom((short) 5);
        blockBounds_.setxRightBottom((short) 3);
        blockBounds_.setyRightBottom((short) 5);
        blockBounds_.invalidate();
        assertTrue(blockBounds_.isValid());
    }

    @Test
    public void invalidate2Test() {
        BlockBounds blockBounds_ = new BlockBounds();
        blockBounds_.invalidate();
        assertTrue(blockBounds_.isValid());
    }

    @Test
    public void invalidate3Test() {
        BlockBounds blockBounds_ = new BlockBounds();
        blockBounds_.setxLeftTop((short) 4);
        blockBounds_.setyLeftTop((short) 0);
        blockBounds_.setxRightTop((short) 3);
        blockBounds_.setyRightTop((short) 0);
        blockBounds_.setxLeftBottom((short) 4);
        blockBounds_.setyLeftBottom((short) 5);
        blockBounds_.setxRightBottom((short) 3);
        blockBounds_.setyRightBottom((short) 5);
        blockBounds_.invalidate();
        assertTrue(!blockBounds_.isValid());
    }

    @Test
    public void invalidate4Test() {
        BlockBounds blockBounds_ = new BlockBounds();
        blockBounds_.setxLeftTop((short) 0);
        blockBounds_.setyLeftTop((short) 6);
        blockBounds_.setxRightTop((short) 3);
        blockBounds_.setyRightTop((short) 6);
        blockBounds_.setxLeftBottom((short) 0);
        blockBounds_.setyLeftBottom((short) 5);
        blockBounds_.setxRightBottom((short) 3);
        blockBounds_.setyRightBottom((short) 5);
        blockBounds_.invalidate();
        assertTrue(!blockBounds_.isValid());
    }
}
