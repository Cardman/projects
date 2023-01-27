package code.formathtml.render;

import code.gui.*;
import code.gui.document.RenderedPage;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.Node;
import code.util.StringList;
import code.util.core.NumberUtil;
import org.junit.Assert;

public abstract class EquallableGuiDocUtil {
    public static void assertNotNull(Node _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(IntComponent _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(Node _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(IntComponent _value) {
        Assert.assertNull(_value);
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(MetaPointForm _expected, MetaPointForm _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(MetaLayout _expected, MetaLayout _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BorderEnum _expected, BorderEnum _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(IntComponent _expected, IntComponent _result) {
        Assert.assertSame(_expected, _result);
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
        Assert.assertEquals(_expected.getList(), _result.getList());
    }
    public static RenderedPage newRenderedPage(MockProgramInfos _pr) {
        return new RenderedPage(_pr.getCompoFactory().newAbsScrollPane(), _pr,new MockCharacterCaseConverter());
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return new MockProgramInfos("", "", new MockEventListIncr(_s,new int[0],new String[0],new TextAnswerValue[0]), _set);
    }
    public static MockProgramInfos newMockProgramInfos(MockEventListIncr _s, MockFileSet _set) {
        return new MockProgramInfos("", "", _s, _set);
    }
    public static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
    public static long[] lgs(long... _args) {
        return _args;
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
    public static byte[] wrapInts(int... _files) {
        return NumberUtil.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    public static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }
}
