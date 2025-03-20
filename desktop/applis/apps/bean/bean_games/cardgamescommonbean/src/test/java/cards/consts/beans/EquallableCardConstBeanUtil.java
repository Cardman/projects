package cards.consts.beans;

import cards.consts.LineDeal;
import code.util.CustList;
import code.util.Longs;
import org.junit.Assert;

public abstract class EquallableCardConstBeanUtil {

//    public static NaSt lineDeal(int _nb, long... _scores) {
//        LineDeal ls_ = base(_nb, _scores);
//        return new LineDealStruct(ls_);
//    }
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
//
//    public static NaSt callLineDealNumber(NaSt _str, long... _args) {
//        return callLongs(new LineDealNumber(),_str,_args);
//    }

//    public static NaSt callLineDealScores(NaSt _str, long... _args) {
//        return callLongs(new LineDealScores(),_str,_args);
//    }
//    public static NaSt callLongs(NatCaller _caller, NaSt _str, long... _args) {
//        return _caller.re(_str,getLongArray(_args));
//    }
//    public static NaSt[] getLongArray(long... _ls){
//        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
//    }
//    public static void assertEq(String _exp, NaSt _result) {
//        assertEq(_exp,((NaStSt)_result).getInstance());
//    }
//    public static void assertEq(Rate _exp, NaSt _result) {
//        assertTrue(_exp.eq(((RtSt)_result).getInstance()));
//    }
//    public static void assertEq(long _exp, NaSt _result) {
//        assertEq(_exp,(((NaNbSt)_result).longStruct()));
//    }
//    public static void assertTrue(NaSt _value) {
//        assertSame(NaBoSt.of(true),_value);
//    }
//    public static void assertFalse(NaSt _value) {
//        assertSame(NaBoSt.of(false),_value);
//    }
//    public static void assertSizeEq(int _exp, NaSt _result) {
//        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
//    }
//    public static void assertSizeEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
//    }
//     public static void assertNumberEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
//    }
//    public static void assertEq(String _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(Rate _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(long _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(long _exp, NaSt _result, int _index, int _second) {
//        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
//    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
//    public static void assertSame(NaSt _expected, NaSt _result) {
//        Assert.assertSame(_expected, _result);
//    }
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
