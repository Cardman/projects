package code.sml;

import code.util.CustList;
import org.junit.Assert;

public abstract class EquallableRowColUtil {

    public static void assertNotNull(CustList<EncodedChar> _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(RowCol _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(DocumentResult _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Document _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Node _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(NamedNodeMap _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(RowCol _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Document _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Node _value) {
        Assert.assertNull(_value);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertSame(Node _expected, Node _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(long _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(RowCol _expected, RowCol _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.display(),_expected.getRow(),_result.getRow());
        Assert.assertEquals(_expected.display(),_expected.getCol(),_result.getCol());
    }

}
