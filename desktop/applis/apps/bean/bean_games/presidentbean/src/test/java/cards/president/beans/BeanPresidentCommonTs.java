package cards.president.beans;

import cards.consts.beans.LineDealStruct;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.pages.cards.MessagesPresidentPage;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.util.Longs;
import code.util.core.StringUtil;

public abstract class BeanPresidentCommonTs extends EquallablePresidentBeanUtil {
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;
    public static NaSt beanResults(String _language, ResultsPresident _dataBase) {
        PresidentStandardsResults stds_ = new PresidentStandardsResults();
        stds_.setDataBase(_dataBase);
        PresidentBean bean_ = new PresidentBean();
        PresidentBeanStruct str_ = PresidentStandards.bean(bean_);
        bean_.setDataBase(stds_.getDataBase());
        bean_.setBuilder(builder());
        bean_.setLanguage(_language);
        return str_;
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
        RulesPresidentBean bean_ = new RulesPresidentBean();
        PresidentBeanStruct b_ = PresidentStandards.bean(bean_);
        bean_.setDataBase(stds_.getDataBaseRules());
        bean_.setBuilder(builder());
        bean_.setLanguage(_language);
        return b_;
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
        ((RulesPresidentBean)((PresidentBeanStruct)_b).getBean()).build();
        return _b;
    }


    public NaSt displayingGame(NaSt _b) {
        ((PresidentBean)((PresidentBeanStruct)_b).getBean()).build();
        return _b;
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

    private static IntBeanBuilderHelperPresidentImpl builder() {
        IntBeanBuilderHelperPresidentImpl builder_ = new IntBeanBuilderHelperPresidentImpl();
        Translations ts_ = new Translations();
        ts_.getMapping().addEntry(EN,new TranslationsLg());
        ts_.getMapping().addEntry(FR,new TranslationsLg());
        ts_.getMapping().getVal(EN).getMapping().addEntry(MessagesPresidentPage.APP_BEAN,new TranslationsAppli());
        ts_.getMapping().getVal(FR).getMapping().addEntry(MessagesPresidentPage.APP_BEAN,new TranslationsAppli());
        ts_.getMapping().getVal(EN).getMapping().getVal(MessagesPresidentPage.APP_BEAN).getMapping().addEntry("",MessagesPresidentPage.en());
        ts_.getMapping().getVal(FR).getMapping().getVal(MessagesPresidentPage.APP_BEAN).getMapping().addEntry("",MessagesPresidentPage.fr());
        builder_.setTranslations(ts_);
        return builder_;
    }
}
