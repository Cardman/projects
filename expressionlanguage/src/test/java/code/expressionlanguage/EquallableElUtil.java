package code.expressionlanguage;

import org.junit.Assert;

import code.expressionlanguage.opers.util.MethodId;
import code.util.Numbers;
import code.util.StringList;

public final class EquallableElUtil {

    private static final String DIFF = " != ";

    private EquallableElUtil() {
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(char _expected, Character _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(StringList.concat(Character.toString(_expected),DIFF,_result.toString()),_expected,_result.charValue());
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(StringList.concat(Boolean.toString(_expected),DIFF,Boolean.toString(_result)),_expected,_result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(MethodId _expected, MethodId _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.getSignature(),DIFF,_result.getSignature()), _expected.eq(_result));
    }
    
    public static void assertEq(long _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), sameValue(_expected, _result));
    }
    
    public static void assertEq(int _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(double _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), sameValue(_expected, _result));
    }
    
    private static boolean sameValue(long _expected, Number _result) {
        return _expected == _result.longValue();
    }

    private static boolean sameValue(double _expected, Number _result) {
        return _expected == _result.doubleValue();
    }
}
