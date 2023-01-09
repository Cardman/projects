package code.expressionlanguage.utilcompo;

import code.expressionlanguage.structs.*;
import code.gui.TextAnswerValue;
import code.gui.initialize.*;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.*;
import code.stream.core.*;
import code.util.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableElIntUtil {
    public static void assertNull(byte[] _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(String _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(StringMap<ContentTime> _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertNull(Node _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(Struct _value) {
        Assert.assertTrue(BooleanStruct.isTrue(_value));
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(Struct _value) {
        Assert.assertTrue(BooleanStruct.isFalse(_value));
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static StringMap<ContentTime> init() {
        return new StringMap<ContentTime>();
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name, String _content) {
        _all.put(_name,new ContentTime(StringUtil.encode(_content),_light.getThreadFactory().millis()));
        return _all;
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name) {
        _all.put(_name,new ContentTime(null,_light.getThreadFactory().millis()));
        return _all;
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
    public static FileSystemParameterizing params() {
        return new FileSystemParameterizing("d",new StringBuilder(),new Ints(),"f",new StringBuilder(),new Ints());
    }
}
