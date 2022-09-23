package code.expressionlanguage;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.files.SegmentStringType;
import code.expressionlanguage.analyze.files.SegmentType;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.common.MatchingEnum;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.Struct;
import code.util.Replacement;
import code.util.StringList;
import code.util.core.BoolVal;
import code.util.ints.Countable;
import org.junit.Assert;

public abstract class EquallableElUtil {

    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ContextEl _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(ArgumentsPair _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(Struct _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(OperationNode _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNotNull(CallingState _value) {
        Assert.assertNotNull(_value);
    }
    public static void assertNull(AbsBk _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Struct _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(CallingState _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(ReadWrite _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(AbstractWrapper _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Argument _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(ArgumentWrapper _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(String _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Replacement _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(ExecOperationNode _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(Countable _value) {
        Assert.assertNull(_value);
    }
    public static void assertNull(AbstractStask _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertFalse(String _mess,boolean _value) {
        Assert.assertFalse(_mess,_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(MatchingEnum _expected, MatchingEnum _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Struct _expected, Struct _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ConstType _expected, ConstType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AccessEnum _expected, AccessEnum _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(AbsBk _expected, AbsBk _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(InitClassState _expected, InitClassState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SegmentStringType _expected, SegmentStringType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(SegmentType _expected, SegmentType _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertNotSame(ErrorType _expected, ErrorType _result) {
        Assert.assertNotSame(_expected, _result);
    }

    public static void assertEq(StringList _expected, StringList _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getList(),_result.getList());
    }

    public static void assertEq(char _expected, char _result) {
        Assert.assertEquals(_expected,_result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(MethodId _expected, MethodId _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(int _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(double _expected, double _result) {
        Assert.assertEquals(Double.toString(_expected),Double.toString(_result));
    }
}
