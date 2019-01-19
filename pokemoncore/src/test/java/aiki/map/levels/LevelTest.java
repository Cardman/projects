package aiki.map.levels;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.util.Point;
import code.util.ObjectMap;


public class LevelTest {

    @Test
    public void translateLineData1Test() {
        ObjectMap<Point,Short> elements_ = new ObjectMap<Point,Short>();
        elements_.put(new Point((short)2,(short)1), (short) 5);
        Short eltOne_ = elements_.getVal(new Point((short)2,(short)1));
        Level.translateShortLineData(elements_,(short) 2,(short) 1);
        Short eltTwo_ = elements_.getVal(new Point((short)2,(short)1));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)2,(short)1)));
        assertEq(5, elements_.getVal(new Point((short)2,(short)1)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateLineData2Test() {
        ObjectMap<Point,Short> elements_ = new ObjectMap<Point,Short>();
        elements_.put(new Point((short)2,(short)2), (short) 7);
        Short eltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.translateShortLineData(elements_,(short) 2,(short) 1);
        Short eltTwo_ = elements_.getVal(new Point((short)2,(short)3));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)2,(short)3)));
        assertEq(7, elements_.getVal(new Point((short)2,(short)3)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateLineData3Test() {
        ObjectMap<Point,Short> elements_ = new ObjectMap<Point,Short>();
        elements_.put(new Point((short)2,(short)1), (short) 5);
        elements_.put(new Point((short)2,(short)2), (short) 7);
        Short firstEltOne_ = elements_.getVal(new Point((short)2,(short)1));
        Short secondEltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.translateShortLineData(elements_,(short) 2,(short) 1);
        Short firstEltTwo_ = elements_.getVal(new Point((short)2,(short)1));
        Short secondEltTwo_ = elements_.getVal(new Point((short)2,(short)3));
        assertEq(2, elements_.size());
        assertTrue(elements_.contains(new Point((short)2,(short)1)));
        assertTrue(elements_.contains(new Point((short)2,(short)3)));
        assertEq(5, elements_.getVal(new Point((short)2,(short)1)).intValue());
        assertEq(7, elements_.getVal(new Point((short)2,(short)3)).intValue());
        assertSame(firstEltOne_, firstEltTwo_);
        assertSame(secondEltOne_, secondEltTwo_);
    }

    @Test
    public void translateColumnData1Test() {
        ObjectMap<Point,Short> elements_ = new ObjectMap<Point,Short>();
        elements_.put(new Point((short)2,(short)1), (short) 5);
        Short eltOne_ = elements_.getVal(new Point((short)2,(short)1));
        Level.translateShortColumnData(elements_,(short) 2,(short) 3);
        Short eltTwo_ = elements_.getVal(new Point((short)5,(short)1));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)5,(short)1)));
        assertEq(5, elements_.getVal(new Point((short)5,(short)1)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateColumnData2Test() {
        ObjectMap<Point,Short> elements_ = new ObjectMap<Point,Short>();
        elements_.put(new Point((short)2,(short)2),(short) 7);
        Short eltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.translateShortColumnData(elements_,(short) 2,(short) -1);
        Short eltTwo_ = elements_.getVal(new Point((short)1,(short)2));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)1,(short)2)));
        assertEq(7, elements_.getVal(new Point((short)1,(short)2)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateColumnData3Test() {
        ObjectMap<Point,Short> elements_ = new ObjectMap<Point,Short>();
        elements_.put(new Point((short)1,(short)2),(short) 5);
        elements_.put(new Point((short)2,(short)2),(short) 7);
        Short firstEltOne_ = elements_.getVal(new Point((short)1,(short)2));
        Short secondEltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.translateShortColumnData(elements_,(short) 2,(short) 1);
        Short firstEltTwo_ = elements_.getVal(new Point((short)1,(short)2));
        Short secondEltTwo_ = elements_.getVal(new Point((short)3,(short)2));
        assertEq(2, elements_.size());
        assertTrue(elements_.contains(new Point((short)1,(short)2)));
        assertTrue(elements_.contains(new Point((short)3,(short)2)));
        assertEq(5, elements_.getVal(new Point((short)1,(short)2)).intValue());
        assertEq(7, elements_.getVal(new Point((short)3,(short)2)).intValue());
        assertSame(firstEltOne_, firstEltTwo_);
        assertSame(secondEltOne_, secondEltTwo_);
    }
}
