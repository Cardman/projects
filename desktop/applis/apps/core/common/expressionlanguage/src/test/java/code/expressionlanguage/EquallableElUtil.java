package code.expressionlanguage;

import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;
import org.junit.Assert;

public final class EquallableElUtil {

    private EquallableElUtil() {
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
        int size_ = _expected.getParametersTypes().size();
        Assert.assertEquals(size_, _result.getParametersTypes().size());
        for (int i = 0; i < size_; i++) {
            assertEq(_expected.getParametersTypes().get(i),_result.getParametersTypes().get(i));
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
