package code.formathtml;

import org.junit.Assert;

import code.formathtml.classes.EnumNumber;
import code.formathtml.classes.Rate;
import code.util.Numbers;
import code.util.StringList;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static String formatFile(String _folder, String _locale, String _relative) {
        return StringList.concat(_folder,"/",_locale,"/",_relative,".properties");
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Boolean.toString(_expected),DIFF,Boolean.toString(_result)), _expected == _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected,DIFF,_result), StringList.quickEq(_expected, _result));
    }

    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(Number _expected, Number _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(Numbers.toString(_expected),DIFF,Numbers.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(Character _expected, Character _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.toString(),DIFF,_result.toString()), _expected.charValue() == _result.charValue());
    }
    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(EnumNumber _expected, EnumNumber _result) {
        Assert.assertSame(_expected, _result);
    }
    private static boolean sameValue(Number _expected, Number _result) {
        if (_expected instanceof Double || _expected instanceof Float) {
            return _expected.doubleValue() == _result.doubleValue();
        }
        return _expected.longValue() == _result.longValue();
    }
}
