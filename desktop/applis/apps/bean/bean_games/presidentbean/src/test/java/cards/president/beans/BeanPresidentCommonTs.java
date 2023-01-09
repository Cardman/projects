package cards.president.beans;

import cards.consts.beans.LineDealStruct;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.nat.*;
import code.bean.nat.*;
import code.maths.Rate;
import code.util.Longs;

public abstract class BeanPresidentCommonTs extends EquallablePresidentBeanUtil {
    public static final String EN = "en";
    public static NaSt beanResults(String _language, ResultsPresident _dataBase) {
        PresidentStandardsResults stds_ = new PresidentStandardsResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanResults(_language);
    }

    public static NaSt callPresidentBeanNicknames(NaSt _str, long... _args) {
        return callLongs(new PresidentBeanNicknames(),_str,_args);
    }

    public static NaSt callPresidentBeanLinesDeal(NaSt _str, long... _args) {
        return callLongs(new PresidentBeanLinesDeal(),_str,_args);
    }
    public static NaSt beanRules(String _language, RulesPresident _dataBase) {
        PresidentStandardsRules stds_ = new PresidentStandardsRules();
        stds_.setDataBaseRules(_dataBase);
        return stds_.beanRules(_language);
    }
    public static NaSt callRulesPresidentBeanSameAmount(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanSameAmount(),_str,_args);
    }

    public static NaSt callRulesPresidentBeanNbCardsPerPlayerMin(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbCardsPerPlayerMin(),_str,_args);
    }

    public static NaSt callRulesPresidentBeanNbCardsPerPlayerMax(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbCardsPerPlayerMax(),_str,_args);
    }

    public static NaSt callRulesPresidentBeanLooserStartsFirst(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanLooserStartsFirst(),_str,_args);
    }

    public static NaSt callRulesPresidentBeanSwitchCards(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanSwitchCards(),_str,_args);
    }

    public static NaSt callRulesPresidentBeanLoosingIfFinishByBestCards(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanLoosingIfFinishByBestCards(),_str,_args);
    }

    public static NaSt callRulesPresidentBeanHasToPlay(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanHasToPlay(),_str,_args);
    }

    public static NaSt callRulesPresidentBeanPossibleReversing(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanPossibleReversing(),_str,_args);
    }
    public static NaSt callRulesPresidentCartesBattues(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanCartesBattues(),_str,_args);
    }
    public static NaSt callRulesPresidentBeanNbPlayers(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbPlayers(),_str,_args);
    }
    public static NaSt callRulesPresidentBeanNbStacks(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanNbStacks(),_str,_args);
    }
    public static NaSt callRulesPresidentBeanEqualty(NaSt _str, long... _args) {
        return callLongs(new RulesPresidentBeanEqualty(),_str,_args);
    }
    public static NaSt callLongs(NatCaller _caller, NaSt _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }

    public NaSt displaying(NaSt _b) {
        beforeDisplaying(_b);
        return _b;
    }

    public static void beforeDisplaying(NaSt _bean) {
        ((BeanStruct)_bean).beforeDisplaying();
    }
    public static NaSt[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

    public static void assertSizeEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
    }
    public static void assertNumberEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
    }
    public static void assertEq(long _exp, NaSt _result, int _index, int _second) {
        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
    }

    public static void assertEq(String _exp, NaSt _result) {
        assertEq(_exp,((NaStSt)_result).getInstance());
    }
    public static void assertEq(Rate _exp, NaSt _result) {
        assertTrue(_exp.eq(((RtSt)_result).getInstance()));
    }
    public static void assertEq(long _exp, NaSt _result) {
        assertEq(_exp,(((NaNbSt)_result).longStruct()));
    }
    public static void assertTrue(NaSt _value) {
        assertSame(NaBoSt.of(true),_value);
    }
    public static void assertFalse(NaSt _value) {
        assertSame(NaBoSt.of(false),_value);
    }
    public static void assertSizeEq(int _exp, NaSt _result) {
        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
    }
    public static void assertEq(String _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(Rate _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
    public static void assertEq(long _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
    }
}
