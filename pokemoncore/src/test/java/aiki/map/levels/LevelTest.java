package aiki.map.levels;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.ObjectMap;
import aiki.map.levels.Level;
import aiki.util.Point;

@SuppressWarnings("static-method")
public class LevelTest {

    @Test
    public void translateLineData1Test() {
        ObjectMap<Point,Integer> elements_ = new ObjectMap<Point,Integer>();
        elements_.put(new Point((short)2,(short)1), 5);
        Integer eltOne_ = elements_.getVal(new Point((short)2,(short)1));
        Level.<Integer>translateLineData(elements_,(short) 2,(short) 1);
        Integer eltTwo_ = elements_.getVal(new Point((short)2,(short)1));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)2,(short)1)));
        assertEq(5, elements_.getVal(new Point((short)2,(short)1)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateLineData2Test() {
        ObjectMap<Point,Integer> elements_ = new ObjectMap<Point,Integer>();
        elements_.put(new Point((short)2,(short)2), 7);
        Integer eltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.<Integer>translateLineData(elements_,(short) 2,(short) 1);
        Integer eltTwo_ = elements_.getVal(new Point((short)2,(short)3));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)2,(short)3)));
        assertEq(7, elements_.getVal(new Point((short)2,(short)3)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateLineData3Test() {
        ObjectMap<Point,Integer> elements_ = new ObjectMap<Point,Integer>();
        elements_.put(new Point((short)2,(short)1), 5);
        elements_.put(new Point((short)2,(short)2), 7);
        Integer firstEltOne_ = elements_.getVal(new Point((short)2,(short)1));
        Integer secondEltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.<Integer>translateLineData(elements_,(short) 2,(short) 1);
        Integer firstEltTwo_ = elements_.getVal(new Point((short)2,(short)1));
        Integer secondEltTwo_ = elements_.getVal(new Point((short)2,(short)3));
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
        ObjectMap<Point,Integer> elements_ = new ObjectMap<Point,Integer>();
        elements_.put(new Point((short)2,(short)1), 5);
        Integer eltOne_ = elements_.getVal(new Point((short)2,(short)1));
        Level.<Integer>translateColumnData(elements_,(short) 2,(short) 3);
        Integer eltTwo_ = elements_.getVal(new Point((short)5,(short)1));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)5,(short)1)));
        assertEq(5, elements_.getVal(new Point((short)5,(short)1)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateColumnData2Test() {
        ObjectMap<Point,Integer> elements_ = new ObjectMap<Point,Integer>();
        elements_.put(new Point((short)2,(short)2), 7);
        Integer eltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.<Integer>translateColumnData(elements_,(short) 2,(short) -1);
        Integer eltTwo_ = elements_.getVal(new Point((short)1,(short)2));
        assertEq(1, elements_.size());
        assertTrue(elements_.contains(new Point((short)1,(short)2)));
        assertEq(7, elements_.getVal(new Point((short)1,(short)2)).intValue());
        assertSame(eltOne_, eltTwo_);
    }

    @Test
    public void translateColumnData3Test() {
        ObjectMap<Point,Integer> elements_ = new ObjectMap<Point,Integer>();
        elements_.put(new Point((short)1,(short)2), 5);
        elements_.put(new Point((short)2,(short)2), 7);
        Integer firstEltOne_ = elements_.getVal(new Point((short)1,(short)2));
        Integer secondEltOne_ = elements_.getVal(new Point((short)2,(short)2));
        Level.<Integer>translateColumnData(elements_,(short) 2,(short) 1);
        Integer firstEltTwo_ = elements_.getVal(new Point((short)1,(short)2));
        Integer secondEltTwo_ = elements_.getVal(new Point((short)3,(short)2));
        assertEq(2, elements_.size());
        assertTrue(elements_.contains(new Point((short)1,(short)2)));
        assertTrue(elements_.contains(new Point((short)3,(short)2)));
        assertEq(5, elements_.getVal(new Point((short)1,(short)2)).intValue());
        assertEq(7, elements_.getVal(new Point((short)3,(short)2)).intValue());
        assertSame(firstEltOne_, firstEltTwo_);
        assertSame(secondEltOne_, secondEltTwo_);
    }
}
