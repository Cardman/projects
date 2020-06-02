package code.maths;

import org.junit.Assert;

import code.maths.geo.CustPoint;
import code.maths.geo.CustPointThreeDims;
import code.util.*;
import code.util.StringList;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class EquallableMathUtil {

    private static final String DIFF = " != ";

    private EquallableMathUtil() {
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(long _expected, BigDecimal _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,_result.toString()), sameValue(_expected, _result.longValue()));
    }

    public static void assertEq(long _expected, BigInteger _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,_result.toString()), sameValue(_expected, _result.longValue()));
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    public static void assertEq(double _expected, double _result) {
        Assert.assertTrue(StringList.concat(Double.toString(_expected),DIFF,Double.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(long _expected, int _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertTrue(StringList.concat(String.valueOf(_expected),DIFF,String.valueOf(_result)), _expected == _result);
    }

    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }
    
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toNumberString(),DIFF,_result.toNumberString()), _expected.eq(_result));
    }

    public static void assertEq(Complex _expected, Complex _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(BigDec _expected, BigDec _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(CustPoint _expected, CustPoint _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }
    
    public static void assertEq(CustPointThreeDims _expected, CustPointThreeDims _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    private static boolean sameValue(double _expected, double _result) {
        return _expected == _result;
    }
    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }
}
