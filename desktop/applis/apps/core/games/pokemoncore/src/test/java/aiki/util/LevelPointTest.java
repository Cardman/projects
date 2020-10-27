package aiki.util;
import static aiki.db.EquallablePkUtil.assertEq;

import code.util.core.StringUtil;
import org.junit.Test;


public class LevelPointTest {

    @Test
    public void new_LevelPoint_String_1Test() {
        LevelPoint lpt_ = new LevelPoint(StringUtil.concat("2",Character.toString(LevelPoint.SEPARATOR),"3",Character.toString(Point.SEPARATOR),"1"));
        assertEq(2, lpt_.getLevelIndex());
        assertEq(3, lpt_.getPoint().getx());
        assertEq(1, lpt_.getPoint().gety());
    }

    @Test
    public void new_LevelPoint_String_2Test() {
        LevelPoint lpt_ = LevelPoint.newLevelPoint(StringUtil.concat("2",Character.toString(LevelPoint.SEPARATOR),"3",Character.toString(Point.SEPARATOR),"1"));
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
        assertEq(StringUtil.concat("2",Character.toString(LevelPoint.SEPARATOR),"3",Character.toString(Point.SEPARATOR),"1"), lpt_.display());
    }
}
