package code.mock;

import code.gui.AbsCustComponent;
import code.gui.AbstractMutableTreeNodeCore;
import code.gui.EnabledMenu;
import code.gui.TextAnswerValue;
import code.gui.images.AbstractImage;
import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.stream.AbsClipStream;
import code.stream.core.ContentTime;
import code.threads.ThState;
import code.util.StringMap;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableMockGuiAdvUtil {
    public static void assertNull(String _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(EnabledMenu _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractImage _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbstractMutableTreeNodeCore<String> _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(AbsClipStream _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNotNull(AbsCustComponent _expected) {
        Assert.assertNotNull(_expected);
    }
//    public static void assertNull(Struct _expected) {
//        Assert.assertNull(_expected);
//    }
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
    public static void assertSame(AbstractMutableTreeNodeCore<String> _expected, AbstractMutableTreeNodeCore<String> _result) {
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
        return SortConstants.wrapByteArray(MockZipFact.wrapInts(_files));
    }

    protected static long[] wrapLongs(long... _files) {
        return _files;
    }
    protected static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }

    protected static MockProgramInfos init() {
        return MockProgramInfos.inst("", "", new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0,new long[0], StringUtil.wrapStringArray("/")));
    }

    protected static MockProgramInfos init2() {
        return MockProgramInfos.inst("", "", new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(0,new long[0], StringUtil.wrapStringArray("/")));
    }

}
