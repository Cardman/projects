package code.maths;

import code.util.core.StringUtil;
import org.junit.Assert;

import code.maths.geo.CustPoint;
import code.maths.geo.CustPointThreeDims;
import code.util.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class EquallableMathUtil {

    private static final String DIFF = " != ";

    private EquallableMathUtil() {
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, BigDecimal _result) {
        Assert.assertEquals(_expected, _result.longValue());
    }

    public static void assertEq(long _expected, BigInteger _result) {
        Assert.assertEquals(_expected, _result.longValue());
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(double _expected, double _result) {
        Assert.assertEquals(Double.toString(_expected),Double.toString(_result));
    }

    public static void assertEq(long _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        assertEq(_expected.getNumerator(),_result.getNumerator());
        assertEq(_expected.getDenominator(),_result.getDenominator());
    }
    
    public static void assertEq(LgInt _expected, LgInt _result) {
        Assert.assertNotNull(_result);
        Longs expDigits_ = _expected.getGrDigits();
        Longs resDigits_ = _result.getGrDigits();
        int expSize_ = expDigits_.size();
        Assert.assertEquals(_expected.getSignum(), _result.getSignum());
        Assert.assertEquals(expSize_, resDigits_.size());
        for (int i = 0; i < expSize_; i++) {
            Assert.assertEquals(expDigits_.get(i),resDigits_.get(i));
        }
    }

    public static void assertEq(Complex _expected, Complex _result) {
        Assert.assertNotNull(_result);
        assertEq(_expected.getImag(),_result.getImag());
        assertEq(_expected.getReal(),_result.getReal());
    }

    public static void assertEq(BigDec _expected, BigDec _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringUtil.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(CustPoint _expected, CustPoint _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getXcoords(),_result.getXcoords());
        Assert.assertEquals(_expected.getYcoords(),_result.getYcoords());
    }
    
    public static void assertEq(CustPointThreeDims _expected, CustPointThreeDims _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getXcoords(),_result.getXcoords());
        Assert.assertEquals(_expected.getYcoords(),_result.getYcoords());
        Assert.assertEquals(_expected.getZcoords(),_result.getZcoords());
    }

}
