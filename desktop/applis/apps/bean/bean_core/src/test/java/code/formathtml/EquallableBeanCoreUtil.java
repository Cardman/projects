package code.formathtml;

import code.expressionlanguage.structs.Struct;
import code.scripts.confs.BeanPageCardsSample;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableBeanCoreUtil {

    public static void assertNotNull(BeanPageCardsSample _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(Struct _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }

    public static Configuration newConfiguration() {
        return new Configuration();
    }
    public static String formatFile(String _folder, String _locale, String _relative) {
        return StringUtil.concat(_folder,"/",_locale,"/",_relative,".properties");
    }

    public static void assertEq(boolean _expected, boolean _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
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
}
