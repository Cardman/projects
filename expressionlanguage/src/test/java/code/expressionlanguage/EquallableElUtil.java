package code.expressionlanguage;

import code.expressionlanguage.opers.util.MethodId;
import code.util.*;
import code.util.StringList;
import org.junit.Assert;

public final class EquallableElUtil {

    private static final String DIFF = " != ";

    private EquallableElUtil() {
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(char _expected, char _result) {
        Assert.assertEquals(StringList.concat(Character.toString(_expected),DIFF,Character.toString(_result)),_expected,_result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(MethodId _expected, MethodId _result, ContextEl _an) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.getSignature(_an),DIFF,_result.getSignature(_an)), _expected.eq(_result));
    }
    
    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    
    public static void assertEq(int _expected, int _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(double _expected, double _result) {
        Assert.assertTrue(StringList.concat(Double.toString(_expected),DIFF,Double.toString(_result)), sameValue(_expected, _result));
    }
    
    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }

    private static boolean sameValue(double _expected, double _result) {
        return _expected == _result;
    }
}
