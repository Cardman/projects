package code.expressionlanguage;

import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;
import org.junit.Assert;

public abstract class EquallableElUtil {

    public static void assertNotNull(Object _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(Object _value) {
        Assert.assertNull(_value);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(Object _expected, Object _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(Object _expected, Object _result) {
        Assert.assertNotSame(_expected, _result);
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        int size_ = _expected.size();
        Assert.assertEquals(size_, _result.size());
        for (int i = 0; i < size_; i++) {
            assertEq(_expected.get(i),_result.get(i));
        }
    }

    public static void assertEq(char _expected, char _result) {
        Assert.assertEquals(_expected,_result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(MethodId _expected, MethodId _result) {
        Assert.assertNotNull(_result);
        assertEq(_expected.getName(),_result.getName());
        Assert.assertSame(_expected.getKind(),_result.getKind());
        Assert.assertEquals(_expected.isVararg(),_result.isVararg());
        int size_ = _expected.getParametersTypesLength();
        Assert.assertEquals(size_, _result.getParametersTypesLength());
        for (int i = 0; i < size_; i++) {
            assertEq(_expected.getParametersType(i),_result.getParametersType(i));
        }
    }
    
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }
    
    public static void assertEq(int _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(double _expected, double _result) {
        Assert.assertEquals(Double.toString(_expected),Double.toString(_result));
    }
}
