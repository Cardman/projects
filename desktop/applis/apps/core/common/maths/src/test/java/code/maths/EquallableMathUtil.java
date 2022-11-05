package code.maths;

import code.maths.geo.*;
import code.maths.litteral.MathType;
import code.maths.litteral.MbArgument;
import code.maths.litteraladv.MaNumParsers;
import code.maths.litteraladv.MaRateStruct;
import code.maths.litteraladv.MaStruct;
import code.maths.matrix.Diagonal;
import code.maths.montecarlo.CustomSeedGene;
import code.util.core.BoolVal;
import org.junit.Assert;

import code.util.*;

public abstract class EquallableMathUtil {

    public static void assertNotNull(MbArgument _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(CustomSeedGene _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(LgInt _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(RatePointThreeDims _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(CustList<EdgeThreeDimensions> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNull(MaStruct _value) {
        Assert.assertNull(_value);
    }

    public static void assertNull(RatePointThreeDims _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertEqRate(MaRateStruct _o, MaRateStruct _t) {
        Assert.assertTrue(MaNumParsers.eqNb(_o,_t));
    }

    public static void assertNotEq(MaRateStruct _o, MaRateStruct _t) {
        Assert.assertFalse(MaNumParsers.eqNb(_o,_t));
    }
    public static void assertSame(RatePointThreeDims _expected, RatePointThreeDims _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(RatePoint _expected, RatePoint _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Diagonal _expected, Diagonal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SitePointThreeDims _expected, SitePointThreeDims _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MathType _expected, MathType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Rate _expected, Rate _result) {
        Assert.assertNotSame(_expected, _result);
    }
    public static void assertNotSame(LgInt _expected, LgInt _result) {
        Assert.assertNotSame(_expected, _result);
    }
    public static void assertNotSame(Longs _expected, Longs _result) {
        Assert.assertNotSame(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
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
        Assert.assertEquals(_expected.isSignum(), _result.isSignum());
        assertEqLongs(expDigits_,resDigits_);
    }

    protected static void assertEqLongs(Longs _expected, Longs _result) {
        Assert.assertEquals(_expected.getList(),_result.getList());
    }
    public static void assertEq(Complex _expected, Complex _result) {
        Assert.assertNotNull(_result);
        assertEq(_expected.getImag(),_result.getImag());
        assertEq(_expected.getReal(),_result.getReal());
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

    public static void assertEq(RatePoint _expected, RatePoint _result) {
        Assert.assertNotNull(_result);
        assertEq(_expected.getXcoords(),_result.getXcoords());
        assertEq(_expected.getYcoords(),_result.getYcoords());
    }

    public static void assertEq(RatePointThreeDims _expected, RatePointThreeDims _result) {
        Assert.assertNotNull(_result);
        assertEq(_expected.getXcoords(),_result.getXcoords());
        assertEq(_expected.getYcoords(),_result.getYcoords());
        assertEq(_expected.getZcoords(),_result.getZcoords());
    }

}
