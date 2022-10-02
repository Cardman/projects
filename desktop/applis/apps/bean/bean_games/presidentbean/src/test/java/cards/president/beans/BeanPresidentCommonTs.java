package cards.president.beans;

import cards.consts.beans.LineDealStruct;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.*;
import code.maths.Rate;
import code.util.Longs;

public abstract class BeanPresidentCommonTs extends EquallablePresidentBeanUtil {
    public static final String EN = "en";
    public static Struct beanResults(String _language, ResultsPresident _dataBase) {
        PresidentStandardsResults stds_ = new PresidentStandardsResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanResults(_language);
    }

    public static Struct callPresidentBeanNicknames(Struct _str, long... _args) {
        return callLongs(new PresidentBeanNicknames(),_str,_args);
    }

    public static Struct callPresidentBeanLinesDeal(Struct _str, long... _args) {
        return callLongs(new PresidentBeanLinesDeal(),_str,_args);
    }
    public static Struct beanRules(String _language, RulesPresident _dataBase) {
        PresidentStandardsRules stds_ = new PresidentStandardsRules();
        stds_.setDataBaseRules(_dataBase);
        return stds_.beanRules(_language);
    }
    public static Struct callRulesPresidentBeanSameAmount(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanSameAmount(),_str,_args);
    }

    public static Struct callRulesPresidentBeanNbCardsPerPlayerMin(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbCardsPerPlayerMin(),_str,_args);
    }

    public static Struct callRulesPresidentBeanNbCardsPerPlayerMax(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbCardsPerPlayerMax(),_str,_args);
    }

    public static Struct callRulesPresidentBeanLooserStartsFirst(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanLooserStartsFirst(),_str,_args);
    }

    public static Struct callRulesPresidentBeanSwitchCards(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanSwitchCards(),_str,_args);
    }

    public static Struct callRulesPresidentBeanLoosingIfFinishByBestCards(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanLoosingIfFinishByBestCards(),_str,_args);
    }

    public static Struct callRulesPresidentBeanHasToPlay(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanHasToPlay(),_str,_args);
    }

    public static Struct callRulesPresidentBeanPossibleReversing(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanPossibleReversing(),_str,_args);
    }
    public static Struct callRulesPresidentCartesBattues(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanCartesBattues(),_str,_args);
    }
    public static Struct callRulesPresidentBeanNbPlayers(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbPlayers(),_str,_args);
    }
    public static Struct callRulesPresidentBeanNbStacks(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbStacks(),_str,_args);
    }
    public static Struct callRulesPresidentBeanEqualty(Struct _str, long... _args) {
        return callLongs(new RulesPresidentBeanEqualty(),_str,_args);
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
    public static void assertNumberEq(int _exp, Struct _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((ArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
    }
    public static void assertEq(long _exp, Struct _result, int _index, int _second) {
        assertEq(_exp,((LineDealStruct)(((ArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
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
}
