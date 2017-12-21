package code.expressionlanguage;

import org.junit.Assert;

import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public final class EquallableElUtil {

    private static final String DIFF = " != ";

    private EquallableElUtil() {
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.eq(_result));
    }

    public static void assertEq(Character _expected, Character _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected.charValue() == _result.charValue());
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, _expected == _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, StringList.quickEq(_expected, _result));
    }

    public static void assertEq(MethodId _expected, MethodId _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.getSignature()+DIFF+_result.getSignature(), _expected.eq(_result));
    }

    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected+DIFF+_result, sameValue(_expected, _result));
    }

    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }
}
