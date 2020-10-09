package aiki.util;
import static aiki.db.EquallablePkUtil.assertEq;

import code.util.core.StringUtil;
import org.junit.Test;


public class PointTest {

    @Test
    public void new_Point_String_1Test() {
        Point pt_ = new Point(StringUtil.concat("3",String.valueOf(Point.SEPARATOR),"1"));
        assertEq(3, pt_.getx());
        assertEq(1, pt_.gety());
    }

    @Test
    public void new_Point_String_2Test() {
        Point pt_ = Point.newPoint(StringUtil.concat("3",String.valueOf(Point.SEPARATOR),"1"));
        assertEq(3, pt_.getx());
        assertEq(1, pt_.gety());
    }

    @Test
    public void toString1Test() {
        Point pt_ = new Point();
        pt_.setx((short) 3);
        pt_.sety((short) 1);
        assertEq(StringUtil.concat("3",String.valueOf(Point.SEPARATOR),"1"), pt_.display());
    }
}
