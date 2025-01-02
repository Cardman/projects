package aiki.map.levels;

import aiki.db.EquallablePkUtil;
import org.junit.Test;


public class BlockBoundsTest extends EquallablePkUtil {

    @Test
    public void invalidate1Test() {
        BlockBounds blockBounds_ = new BlockBounds();
        blockBounds_.setxLeftTop(0);
        blockBounds_.setyLeftTop(0);
        blockBounds_.setxRightTop(3);
        blockBounds_.setyRightTop(0);
        blockBounds_.setxLeftBottom(0);
        blockBounds_.setyLeftBottom(5);
        blockBounds_.setxRightBottom(3);
        blockBounds_.setyRightBottom(5);
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
        blockBounds_.setxLeftTop(4);
        blockBounds_.setyLeftTop(0);
        blockBounds_.setxRightTop(3);
        blockBounds_.setyRightTop(0);
        blockBounds_.setxLeftBottom(4);
        blockBounds_.setyLeftBottom(5);
        blockBounds_.setxRightBottom(3);
        blockBounds_.setyRightBottom(5);
        blockBounds_.invalidate();
        assertTrue(!blockBounds_.isValid());
    }

    @Test
    public void invalidate4Test() {
        BlockBounds blockBounds_ = new BlockBounds();
        blockBounds_.setxLeftTop(0);
        blockBounds_.setyLeftTop(6);
        blockBounds_.setxRightTop(3);
        blockBounds_.setyRightTop(6);
        blockBounds_.setxLeftBottom(0);
        blockBounds_.setyLeftBottom(5);
        blockBounds_.setxRightBottom(3);
        blockBounds_.setyRightBottom(5);
        blockBounds_.invalidate();
        assertTrue(!blockBounds_.isValid());
    }
}
