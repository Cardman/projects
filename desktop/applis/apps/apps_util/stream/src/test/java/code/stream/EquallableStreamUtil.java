package code.stream;

import code.maths.montecarlo.CustomSeedGene;
import code.mock.*;
import code.sml.*;
import code.stream.core.ContentTime;
import code.stream.core.OutputType;
import code.util.StringMap;
import code.util.core.SortConstants;
import org.junit.Assert;

public abstract class EquallableStreamUtil {
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
    public static void assertSame(OutputType _expected, OutputType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertNull(Node _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return newMockProgramInfos(_s,_set,"");
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set, String _tmp) {
        return MockProgramInfos.inst("", _tmp, _s, _set);
    }
    public static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
    public static byte[] wrapInts(int... _files) {
        return SortConstants.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    public static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }
}
