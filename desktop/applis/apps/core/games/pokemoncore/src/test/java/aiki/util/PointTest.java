package aiki.util;

import aiki.db.EquallablePkUtil;
import code.util.core.StringUtil;
import org.junit.Test;


public class PointTest extends EquallablePkUtil {

    @Test
    public void new_Point_String_1Test() {
        Point pt_ = new Point(StringUtil.concat("3",Character.toString(Point.SEPARATOR),"1"));
        assertEq(3, pt_.getx());
        assertEq(1, pt_.gety());
    }

    @Test
    public void new_Point_String_2Test() {
        Point pt_ = Point.newPoint(StringUtil.concat("3",Character.toString(Point.SEPARATOR),"1"));
        assertEq(3, pt_.getx());
        assertEq(1, pt_.gety());
    }

    @Test
    public void toString1Test() {
        Point pt_ = new Point();
        pt_.setx((short) 3);
        pt_.sety((short) 1);
        assertEq(StringUtil.concat("3",Character.toString(Point.SEPARATOR),"1"), pt_.display());
    }
}
