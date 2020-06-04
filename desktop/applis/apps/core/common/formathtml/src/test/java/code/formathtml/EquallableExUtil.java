package code.formathtml;

import org.junit.Assert;

import code.util.StringList;

public final class EquallableExUtil {

    private EquallableExUtil() {
    }

    public static Configuration newConfiguration() {
        return new Configuration();
    }
    public static String formatFile(String _folder, String _locale, String _relative) {
        return StringList.concat(_folder,"/",_locale,"/",_relative,".properties");
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(double _expected, double _result) {
        Assert.assertEquals(Double.toString(_expected),Double.toString(_result));
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(char _expected, char _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        int size_ = _expected.size();
        Assert.assertEquals(size_, _result.size());
        for (int i = 0; i < size_; i++) {
            assertEq(_expected.get(i),_result.get(i));
        }
    }
}
