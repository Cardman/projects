package code.formathtml;

import org.junit.Assert;

import code.formathtml.classes.EnumNumber;
import code.formathtml.classes.Rate;
import code.util.*;
import code.util.StringList;

public final class EquallableExUtil {

    private static final String DIFF = " != ";

    private EquallableExUtil() {
    }

    public static Configuration newConfiguration() {
        Configuration c_ = new Configuration();
        return c_;
    }
    public static String formatFile(String _folder, String _locale, String _relative) {
        return StringList.concat(_folder,"/",_locale,"/",_relative,".properties");
    }

    public static void assertEq(boolean _expected, boolean _result) {
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

    public static void assertEq(double _expected, double _result) {
        Assert.assertTrue(StringList.concat(Double.toString(_expected),DIFF,Double.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }

    public static void assertEq(int _expected, int _result) {
        Assert.assertTrue(StringList.concat(Long.toString(_expected),DIFF,Long.toString(_result)), sameValue(_expected, _result));
    }
    public static void assertEq(char _expected, char _result) {
        Assert.assertEquals(StringList.concat(Character.toString(_expected), DIFF, Character.toString(_result)), _expected, _result);
    }
    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(StringList.concat(_expected.display(),DIFF,_result.display()), _expected.eq(_result));
    }

    public static void assertEq(EnumNumber _expected, EnumNumber _result) {
        Assert.assertSame(_expected, _result);
    }

    private static boolean sameValue(long _expected, long _result) {
        return _expected == _result;
    }

    private static boolean sameValue(double _expected, double _result) {
        return _expected == _result;
    }
}
