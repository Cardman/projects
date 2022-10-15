package cards.consts.beans;

import cards.consts.LineDeal;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.NatArrayStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.*;
import code.maths.Rate;
import code.util.CustList;
import code.util.Longs;
import org.junit.Assert;

public abstract class EquallableCardConstBeanUtil {

    public static Struct lineDeal(int _nb, long... _scores) {
        LineDeal ls_ = base(_nb, _scores);
        return new LineDealStruct(ls_);
    }
    public static CustList<LineDeal> two(LineDeal _one, LineDeal _two) {
        CustList<LineDeal> ls_ = new CustList<LineDeal>();
        ls_.add(_one);
        ls_.add(_two);
        return ls_;
    }

    public static LineDeal base(int _nb, long... _scores) {
        LineDeal ls_ = new LineDeal();
        ls_.setNumber(_nb);
        ls_.setScores(Longs.newList(_scores));
        return ls_;
    }

    public static Struct callLineDealNumber(Struct _str, long... _args) {
        return callLongs(new LineDealNumber(),_str,_args);
    }

    public static Struct callLineDealScores(Struct _str, long... _args) {
        return callLongs(new LineDealScores(),_str,_args);
    }
    public static Struct callLongs(NatCaller _caller, Struct _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }
    public static Struct[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }
    public static void assertEq(String _exp, Struct _result) {
        assertEq(_exp,((StringStruct)_result).getInstance());
    }
    public static void assertEq(Rate _exp, Struct _result) {
        assertTrue(_exp.eq(((RateStruct)_result).getInstance()));
    }
    public static void assertEq(long _exp, Struct _result) {
        assertEq(_exp,(((NumberStruct)_result).longStruct()));
    }
    public static void assertTrue(Struct _value) {
        assertSame(BooleanStruct.of(true),_value);
    }
    public static void assertFalse(Struct _value) {
        assertSame(BooleanStruct.of(false),_value);
    }
    public static void assertSizeEq(int _exp, Struct _result) {
        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
    }
    public static void assertSizeEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
    }
     public static void assertNumberEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
    }
    public static void assertEq(String _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(Rate _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, Struct _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
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
    public static void assertSame(Struct _expected, Struct _result) {
        Assert.assertSame(_expected, _result);
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
