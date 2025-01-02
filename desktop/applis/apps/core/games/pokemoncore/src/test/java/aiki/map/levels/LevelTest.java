package aiki.map.levels;

import aiki.db.EquallablePkUtil;
import aiki.instances.Instances;
import aiki.util.Point;
import aiki.util.PointsBlock;
import org.junit.Test;



public class LevelTest extends EquallablePkUtil {

    @Test
    public void translateLineData1Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl_ = Instances.newBlock();
        bl_.setWidth(5);
        bl_.setHeight(7);
        elements_.put(newPoint(2,1), bl_);
        Block eltOne_ = elements_.getVal(newPoint(2,1));
        elements_.translateLineData(2,1);
        Block eltTwo_ = elements_.getVal(newPoint(2,1));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(newPoint(2,1)));
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateLineData2Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl_ = Instances.newBlock();
        bl_.setWidth(7);
        bl_.setHeight(5);
        elements_.put(newPoint(2,2), bl_);
        Block eltOne_ = elements_.getVal(newPoint(2,2));
        elements_.translateLineData(2,1);
        Block eltTwo_ = elements_.getVal(newPoint(2,3));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(newPoint(2,3)));
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateLineData3Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl1_ = Instances.newBlock();
        bl1_.setWidth(5);
        bl1_.setHeight(7);
        Block bl2_ = Instances.newBlock();
        bl2_.setWidth(7);
        bl2_.setHeight(5);
        elements_.put(newPoint(2,1), bl1_);
        elements_.put(newPoint(2,2), bl2_);
        Block firstEltOne_ = elements_.getVal(newPoint(2,1));
        Block secondEltOne_ = elements_.getVal(newPoint(2,2));
        elements_.translateLineData(2, 1);
        Block firstEltTwo_ = elements_.getVal(newPoint(2,1));
        Block secondEltTwo_ = elements_.getVal(newPoint(2,3));
        assertEq(2, elements_.size());
        assertTrue(elements_.contains(newPoint(2,1)));
        assertTrue(elements_.contains(newPoint(2,3)));
        assertSame(firstEltOne_, firstEltTwo_);
        assertSame(secondEltOne_, secondEltTwo_);
    }

    @Test
    public void translateLineData4Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl1_ = Instances.newBlock();
        bl1_.setWidth(5);
        bl1_.setHeight(7);
        Block bl2_ = Instances.newBlock();
        bl2_.setWidth(7);
        bl2_.setHeight(5);
        elements_.put(newPoint(2,1), bl1_);
        elements_.put(newPoint(2,2), bl2_);
        elements_.translateLineData(2, -1);
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(newPoint(2,1)));
    }

    @Test
    public void translateColumnData1Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl_ = Instances.newBlock();
        bl_.setWidth(5);
        bl_.setHeight(7);
        elements_.put(newPoint(2,1), bl_);
        Block eltOne_ = elements_.getVal(newPoint(2,1));
        elements_.translateColumnData(2, 3);
        Block eltTwo_ = elements_.getVal(newPoint(5,1));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(newPoint(5,1)));
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateColumnData2Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl_ = Instances.newBlock();
        bl_.setWidth(7);
        bl_.setHeight(5);
        elements_.put(newPoint(2,2),bl_);
        Block eltOne_ = elements_.getVal(newPoint(2,2));
        elements_.translateColumnData(2, -1);
        Block eltTwo_ = elements_.getVal(newPoint(1,2));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(newPoint(1,2)));
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateColumnData3Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl1_ = Instances.newBlock();
        bl1_.setWidth(5);
        bl1_.setHeight(7);
        Block bl2_ = Instances.newBlock();
        bl2_.setWidth(7);
        bl2_.setHeight(5);
        elements_.put(newPoint(1,2),bl1_);
        elements_.put(newPoint(2,2),bl2_);
        Block firstEltOne_ = elements_.getVal(newPoint(1,2));
        Block secondEltOne_ = elements_.getVal(newPoint(2,2));
        elements_.translateColumnData(2, 1);
        Block firstEltTwo_ = elements_.getVal(newPoint(1,2));
        Block secondEltTwo_ = elements_.getVal(newPoint(3,2));
        assertEq(2, elements_.size());
        assertTrue(elements_.contains(newPoint(1,2)));
        assertTrue(elements_.contains(newPoint(3,2)));
        assertSame(firstEltOne_, firstEltTwo_);
        assertSame(secondEltOne_, secondEltTwo_);
    }

    @Test
    public void translateColumnData4Test() {
        PointsBlock elements_ = new PointsBlock();
        Block bl1_ = Instances.newBlock();
        bl1_.setWidth(5);
        bl1_.setHeight(7);
        Block bl2_ = Instances.newBlock();
        bl2_.setWidth(7);
        bl2_.setHeight(5);
        elements_.put(newPoint(1,2),bl1_);
        elements_.put(newPoint(2,2),bl2_);
        elements_.translateColumnData(2, -1);
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(newPoint(1,2)));
    }
}
