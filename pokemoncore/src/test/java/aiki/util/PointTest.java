package aiki.util;
import static aiki.db.EquallablePkUtil.assertEq;

import org.junit.Test;

import code.util.StringList;


public class PointTest {

    @Test
    public void new_Point_String_1Test() {
        Point pt_ = new Point(StringList.concat("3",String.valueOf(Point.SEPARATOR),"1"));
        assertEq(3, pt_.getx());
        assertEq(1, pt_.gety());
    }

    @Test
    public void toString1Test() {
        Point pt_ = new Point();
        pt_.setx((short) 3);
        pt_.sety((short) 1);
        assertEq(StringList.concat("3",String.valueOf(Point.SEPARATOR),"1"), pt_.display());
    }
}
