package code.scripts.confs;

import aiki.beans.facade.simulation.enums.SimulationSteps;
import aiki.db.ImageArrayBaseSixtyFour;
import aiki.fight.enums.Statistic;
import aiki.util.Coords;
import code.bean.nat.*;
//import code.formathtml.structs.Message;
import code.util.StringMap;
import org.junit.Assert;

public abstract class EquallablePkBeanUtil {
//    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
//    public static void assertNull(Message _value) {
//        Assert.assertNull(_value);
//    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(NaSt _expected, NaSt _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Statistic _expected, Statistic _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SimulationSteps _expected, SimulationSteps _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(Coords _expected, Coords _result) {
        Assert.assertTrue(_expected.eq(_result));
    }
    public static void assertEq(double _expected, double _result) {
        Assert.assertEquals(Double.toString(_expected),Double.toString(_result));
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(char _expected, char _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int[][] _expected, int[][] _result) {
        Assert.assertNotNull(_result);
        Assert.assertArrayEquals(_expected,_result);
    }
    public static ImageArrayBaseSixtyFour instance(int _c) {
        return instance(one(_c));
    }
    public static int[][] one(int _c) {
        return new int[][]{new int[]{_c}};
    }

    public static ImageArrayBaseSixtyFour instance(int[][] _img) {
        return ImageArrayBaseSixtyFour.instance(_img,"");
    }
}
