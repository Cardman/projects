package aiki.util;
import static aiki.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.StringList;

@SuppressWarnings("static-method")
public class CoordsTest {

    @Test
    public void isValid1Test() {
        Coords pt_ = new Coords();
        assertTrue(!pt_.isValid());
    }

    @Test
    public void isValid2Test() {
        Coords pt_ = new Coords();
        pt_.setNumberPlace((short) 0);
        pt_.setLevel(new LevelPoint());
        pt_.getLevel().setLevelIndex((byte) 0);
        pt_.getLevel().setPoint(new Point((byte)0,(byte)0));
        assertTrue(pt_.isValid());
    }

    @Test
    public void isInside1Test() {
        Coords pt_ = new Coords();
        pt_.setNumberPlace((short) 0);
        pt_.setLevel(new LevelPoint());
        pt_.getLevel().setLevelIndex((byte) 0);
        pt_.getLevel().setPoint(new Point((byte)0,(byte)0));
        assertTrue(!pt_.isInside());
    }

    @Test
    public void isInside2Test() {
        Coords pt_ = new Coords();
        pt_.setNumberPlace((short) 0);
        pt_.setInsideBuilding(new Point((byte)0,(byte)0));
        pt_.setLevel(new LevelPoint());
        pt_.getLevel().setLevelIndex((byte) 0);
        pt_.getLevel().setPoint(new Point((byte)0,(byte)0));
        assertTrue(pt_.isInside());
    }

    @Test
    public void new_Coords_String_1Test() {
        Coords pt_ = new Coords(Coords.INVALID);
        assertTrue(!pt_.isValid());
        assertNull(pt_.getLevel());
    }

    @Test
    public void new_Coords_String_2Test() {
        Coords lpt_ = new Coords(StringList.concat("4",String.valueOf(Coords.SEPARATOR),"2",String.valueOf(LevelPoint.SEPARATOR),"3",String.valueOf(Point.SEPARATOR),"1"));
        assertTrue(lpt_.isValid());
        assertTrue(!lpt_.isInside());
        assertEq(4, lpt_.getNumberPlace());
        assertEq(2, lpt_.getLevel().getLevelIndex());
        assertEq(3, lpt_.getLevel().getPoint().getx());
        assertEq(1, lpt_.getLevel().getPoint().gety());
    }

    @Test
    public void new_Coords_String_3Test() {
        Coords lpt_ = new Coords(StringList.concat("4",String.valueOf(Coords.SEPARATOR),"5",String.valueOf(Point.SEPARATOR),"6",String.valueOf(Coords.SEPARATOR),"2",String.valueOf(LevelPoint.SEPARATOR),"3",String.valueOf(Point.SEPARATOR),"1"));
        assertTrue(lpt_.isValid());
        assertTrue(lpt_.isInside());
        assertEq(5, lpt_.getInsideBuilding().getx());
        assertEq(6, lpt_.getInsideBuilding().gety());
        assertEq(4, lpt_.getNumberPlace());
        assertEq(2, lpt_.getLevel().getLevelIndex());
        assertEq(3, lpt_.getLevel().getPoint().getx());
        assertEq(1, lpt_.getLevel().getPoint().gety());
    }

    @Test
    public void toString1Test() {
        Coords pt_ = new Coords();
        assertEq(Coords.INVALID, pt_.display());
    }

    @Test
    public void toString2Test() {
        Point pt_ = new Point();
        pt_.setx((short) 3);
        pt_.sety((short) 1);
        LevelPoint lpt_ = new LevelPoint();
        lpt_.setPoint(pt_);
        lpt_.setLevelIndex((byte) 2);
        Coords coords_ = new Coords();
        coords_.setLevel(lpt_);
        coords_.setNumberPlace((short) 4);
        assertEq(StringList.concat("4",String.valueOf(Coords.SEPARATOR),"2",String.valueOf(LevelPoint.SEPARATOR),"3",String.valueOf(Point.SEPARATOR),"1"), coords_.display());
    }

    @Test
    public void toString3Test() {
        Point pt_ = new Point();
        pt_.setx((short) 3);
        pt_.sety((short) 1);
        LevelPoint lpt_ = new LevelPoint();
        lpt_.setPoint(pt_);
        lpt_.setLevelIndex((byte) 2);
        Coords coords_ = new Coords();
        coords_.setLevel(lpt_);
        coords_.setNumberPlace((short) 4);
        coords_.setInsideBuilding(new Point((short)5,(short)6));
        assertEq(StringList.concat("4",String.valueOf(Coords.SEPARATOR),"5",String.valueOf(Point.SEPARATOR),"6",String.valueOf(Coords.SEPARATOR),"2",String.valueOf(LevelPoint.SEPARATOR),"3",String.valueOf(Point.SEPARATOR),"1"), coords_.display());
    }
}
