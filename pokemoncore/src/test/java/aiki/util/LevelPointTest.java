package aiki.util;
import static aiki.EquallablePkUtil.assertEq;

import org.junit.Test;

import aiki.util.LevelPoint;
import aiki.util.Point;

@SuppressWarnings("static-method")
public class LevelPointTest {

    @Test
    public void new_LevelPoint_String_1Test() {
        LevelPoint lpt_ = new LevelPoint("2"+LevelPoint.SEPARATOR+"3"+Point.SEPARATOR+"1");
        assertEq(2, lpt_.getLevelIndex());
        assertEq(3, lpt_.getPoint().getx());
        assertEq(1, lpt_.getPoint().gety());
    }

    @Test
    public void toString1Test() {
        Point pt_ = new Point();
        pt_.setx((short) 3);
        pt_.sety((short) 1);
        LevelPoint lpt_ = new LevelPoint();
        lpt_.setPoint(pt_);
        lpt_.setLevelIndex((byte) 2);
        assertEq("2"+LevelPoint.SEPARATOR+"3"+Point.SEPARATOR+"1", lpt_.display());
    }
}
