package cards.president.beans;

import cards.consts.LineDeal;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import code.scripts.pages.cards.MessagesPresidentPage;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class BeanPresidentCommonTs extends EquallablePresidentBeanUtil {
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;
    public static PresidentBean beanResults(String _language, ResultsPresident _dataBase) {
        PresidentBean bean_ = new PresidentBean();
        bean_.setDataBase(_dataBase);
        bean_.setBuilder(builder());
//        bean_.setLanguage(_language);
        return bean_;
    }


    public static StringList callPresidentBeanNicknames(PresidentBean _str, int... _args) {
        return _str.getNicknames();
    }

    public static CustList<LineDeal> callPresidentBeanLinesDeal(PresidentBean _str, int... _args) {
        return _str.getLinesDeal();
    }
    public static RulesPresidentBean beanRules(String _language, RulesPresident _dataBase) {
        RulesPresidentBean bean_ = new RulesPresidentBean();
        bean_.setDataBase(_dataBase);
        bean_.setBuilder(builder());
//        bean_.setLanguage(_language);
        return bean_;
    }
    public static boolean callRulesPresidentBeanSameAmount(RulesPresidentBean _str, int... _args) {
        return _str.sameAmount();
    }

    public static long callRulesPresidentBeanNbCardsPerPlayerMin(RulesPresidentBean _str, int... _args) {
        return _str.getNbCardsPerPlayerMin();
    }

    public static long callRulesPresidentBeanNbCardsPerPlayerMax(RulesPresidentBean _str, int... _args) {
        return _str.getNbCardsPerPlayerMax();
    }

    public static boolean callRulesPresidentBeanLooserStartsFirst(RulesPresidentBean _str, int... _args) {
        return _str.isLooserStartsFirst();
    }

    public static boolean callRulesPresidentBeanSwitchCards(RulesPresidentBean _str, int... _args) {
        return _str.isSwitchCards();
    }

    public static boolean callRulesPresidentBeanLoosingIfFinishByBestCards(RulesPresidentBean _str, int... _args) {
        return _str.isLoosingIfFinishByBestCards();
    }

    public static boolean callRulesPresidentBeanHasToPlay(RulesPresidentBean _str, int... _args) {
        return _str.isHasToPlay();
    }

    public static boolean callRulesPresidentBeanPossibleReversing(RulesPresidentBean _str, int... _args) {
        return _str.isPossibleReversing();
    }
    public static String callRulesPresidentCartesBattues(RulesPresidentBean _str, int... _args) {
        return _str.getCartesBattues();
    }
    public static long callRulesPresidentBeanNbPlayers(RulesPresidentBean _str, int... _args) {
        return _str.getNbPlayers();
    }
    public static long callRulesPresidentBeanNbStacks(RulesPresidentBean _str, int... _args) {
        return _str.getNbStacks();
    }
    public static String callRulesPresidentBeanEqualty(RulesPresidentBean _str, int... _args) {
        return _str.getEqualty();
    }
//    public static RulesPresidentBean callLongs(NatCaller _caller, RulesPresidentBean _str, long... _args) {
//        return _caller.re(_str,getLongArray(_args));
//    }

    public RulesPresidentBean displaying(RulesPresidentBean _b) {
        _b.build();
        return _b;
    }


    public PresidentBean displayingGame(PresidentBean _b) {
        _b.build();
        return _b;
    }

//    public static RulesPresidentBean[] getLongArray(long... _ls){
//        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
//    }

//    public static void assertSizeEq(int _exp, RulesPresidentBean _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
//    }
//    public static void assertNumberEq(int _exp, RulesPresidentBean _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
//    }
//    public static void assertEq(long _exp, RulesPresidentBean _result, int _index, int _second) {
//        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
//    }

//    public static void assertEq(String _exp, RulesPresidentBean _result) {
//        assertEq(_exp,((NaStSt)_result).getInstance());
//    }
//    public static void assertEq(Rate _exp, RulesPresidentBean _result) {
//        assertTrue(_exp.eq(((RtSt)_result).getInstance()));
//    }
//    public static void assertEq(long _exp, RulesPresidentBean _result) {
//        assertEq(_exp,(((NaNbSt)_result).longStruct()));
//    }
//    public static void assertTrue(RulesPresidentBean _value) {
//        assertSame(NaBoSt.of(true),_value);
//    }
//    public static void assertFalse(RulesPresidentBean _value) {
//        assertSame(NaBoSt.of(false),_value);
//    }
//    public static void assertSizeEq(int _exp, RulesPresidentBean _result) {
//        assertEq(_exp,(((NatArrayStruct)_result).getLength()));
//    }
//    public static void assertEq(String _exp, RulesPresidentBean _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(Rate _exp, RulesPresidentBean _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(long _exp, RulesPresidentBean _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }

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