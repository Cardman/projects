package aiki.util;

import aiki.db.EquallablePkUtil;
import code.util.CustList;
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
    @Test
    public void toString2Test() {
        assertEq(StringUtil.concat("3",Character.toString(Point.SEPARATOR),"1"), new NullablePoint(StringUtil.concat("3",Character.toString(Point.SEPARATOR),"1")).display());
    }
    @Test
    public void toString3Test() {
        assertEq("", new NullablePoint("").display());
        CustList<Point> ls_ = new CustList<Point>();
        NullablePoint.tryAdd(ls_,new NullablePoint());
        assertEq(0,ls_.size());
        Points<int[][]> map_ = new PointsArr();
        NullablePoint.tryAdd(map_,new NullablePoint(),new int[0][]);
        assertEq(0,map_.size());
    }
}
