package code.scripts.confs;

//import code.formathtml.Configuration;
import code.sml.Document;
import code.sml.FullDocument;
import code.sml.util.TranslationsAppli;
import code.util.StringMap;
import org.junit.Assert;

public abstract class EquallableScriptsBeanUtil {

    public static void assertNotNull(TranslationsAppli _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNullDoc(StringMap<Document> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNullFullDoc(FullDocument _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNullStrConf(StringMap<String> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNullStrNat(StringMap<StringMap<String>> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(BeanHelpCardsSample _value) {
        Assert.assertNotNull(_value);
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
