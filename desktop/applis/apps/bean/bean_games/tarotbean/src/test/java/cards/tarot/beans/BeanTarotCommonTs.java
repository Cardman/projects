package cards.tarot.beans;

import cards.consts.beans.LineDealStruct;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import code.bean.nat.*;
import code.expressionlanguage.structs.*;
import code.maths.Rate;
import code.scripts.confs.EquallableTarotBeanUtil;
import code.util.Longs;

public abstract class BeanTarotCommonTs extends EquallableTarotBeanUtil {
    public static final String EN = "en";

    public static final String RETURNE_LINE = "\n";
    public static final String SEP = ":";


    public static Struct callResultsTarotBeanLoose(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanLoose(),_str,_args);
    }

    public static Struct callResultsTarotBeanWin(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanWin(),_str,_args);
    }


    public static Struct callResultsTarotBeanEquality(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanEquality(),_str,_args);
    }

    public static Struct callResultsTarotBeanFailedBid(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanFailedBid(),_str,_args);
    }

    public static Struct callResultsTarotBeanSuccessfulBid(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanSuccessfulBid(),_str,_args);
    }


    public static Struct callResultsTarotBeanMidBid(Struct _str, long... _args) {
        return callLongs(new ResultsTarotBeanMidBid(),_str,_args);
    }

    public static Struct beanResultsTarot(String _language, ResultsTarot _dataBase) {
        TarotStandardsResults stds_ = new TarotStandardsResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanResults(_language);
    }
    public static Struct callRulesTarotBeanRepartition(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanRepartition(),_str,_args);
    }


    public static Struct callRulesTarotBeanCartesBattues(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanCartesBattues(),_str,_args);
    }

    public static Struct callRulesTarotBeanMiseres(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanMiseres(),_str,_args);
    }


    public static Struct callRulesTarotBeanContrats(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanContrats(),_str,_args);
    }

    public static Struct callRulesTarotBeanMode(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanMode(),_str,_args);
    }

    public static Struct callRulesTarotBeanPoigneesAutorisees(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanPoigneesAutorisees(),_str,_args);
    }


    public static Struct callRulesTarotBeanFinPartieTarot(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanFinPartieTarot(),_str,_args);
    }

    public static Struct callRulesTarotBeanAllowPlayCalledSuit(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanAllowPlayCalledSuit(),_str,_args);
    }

    public static Struct callRulesTarotBeanDiscardAfterCall(Struct _str, long... _args) {
        return callLongs(new RulesTarotBeanDiscardAfterCall(),_str,_args);
    }




    public static Struct beanRules(String _language, RulesTarot _dataBase) {
        TarotStandardsRules stds_ = new TarotStandardsRules();
        stds_.setDataBaseRules(_dataBase);
        return stds_.beanRules(_language);
    }
    public static Struct callLongs(NatCaller _caller, Struct _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }

    public Struct displaying(Struct _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(Struct _bean) {
        ((BeanStruct)_bean).beforeDisplaying();
    }
    public static Struct[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

    public static void assertSizeEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((ArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
    }

    public static void assertSizeLongsEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)((ArrayStruct)_result).get(_index)).getLength()));
    }
    public static void assertNumberEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((ArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
    }
    public static void assertEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((LineDealStruct)(((ArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
    }
    public static void assertLongsEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((ArrayStruct)(((ArrayStruct)_result).get(_index))).get(_second));
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
        assertEq(_exp,(((ArrayStruct)_result).getLength()));
    }
    public static void assertEq(String _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)_result).get(_index)));
    }
    public static void assertEq(Rate _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, Struct _result, int _index) {
        assertEq(_exp,(((ArrayStruct)_result).get(_index)));
    }
    public static void assertFirstEq(String _exp, Struct _result) {
        assertEq(_exp,(((PairStruct)_result).getFirst()));
    }
    public static void assertSecondEq(long _exp, Struct _result) {
        assertEq(_exp,(((PairStruct)_result).getSecond()));
    }
    public static Struct elt(Struct _arr, int _index) {
        return ((ArrayStruct)_arr).get(_index);
    }
}
