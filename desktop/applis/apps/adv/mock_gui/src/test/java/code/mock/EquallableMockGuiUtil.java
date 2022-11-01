package code.mock;

import code.expressionlanguage.structs.Struct;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.stream.AbsClipStream;
import code.stream.core.ContentTime;
import code.threads.ThState;
import code.util.StringMap;
import code.util.core.NumberUtil;
import org.junit.Assert;

public abstract class EquallableMockGuiUtil {
    public static void assertNull(String _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(EnabledMenu _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractImage _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractMutableTreeNodeCore _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbsClipStream _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNotNull(AbsCustComponent _expected) {
        Assert.assertNotNull(_expected);
    }
    public static void assertNull(Struct _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(String[] _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(byte[] _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(StringMap<ContentTime> _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertSame(AbstractMutableTreeNodeCore _expected, AbstractMutableTreeNodeCore _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AbsCustComponent _expected, AbsCustComponent _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MockLayout _expected, MockLayout _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MockPosition _expected, MockPosition _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ThState _expected, ThState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }

    protected static double[] dbs(double... _args) {
        return _args;
    }
    protected static TextAnswerValue[] pairs(TextAnswerValue... _args) {
        return _args;
    }

    protected static byte[] wrapInts(int... _files) {
        return NumberUtil.wrapByteArray(MockZipFact.wrapInts(_files));
    }

    protected static long[] wrapLongs(long... _files) {
        return _files;
    }
    protected static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }

    protected static MockProgramInfosSample init() {
        return new MockProgramInfosSample("", "", new double[0], 0, new long[0], true, "/");
    }

    protected static MockProgramInfosSample initDef() {
        return new MockProgramInfosSample("", "", new double[0], 0, new long[0], false, "/");
    }
}
