package cards.belote.beans;

import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.consts.LineDeal;
import code.scripts.confs.EquallableBeloteBeanUtil;
import code.scripts.pages.cards.MessagesBelotePage;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class BeanBeloteCommonTs extends EquallableBeloteBeanUtil {
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;

    public static final String RETURNE_LINE = "\n";
    public static final String SEP = ":";


    public static DetailsResultsBeloteBean beanDetailResultsBelote(String _language, ResultsBelote _dataBase) {
        DetailsResultsBeloteBean bean_ = new DetailsResultsBeloteBean();
        bean_.setBuilder(builder());
        bean_.setDataBase(_dataBase,null);
//        bean_.setLanguage(_language);
        return bean_;
    }
    public static CustList<BeloteSumDeclaringPlayer> callDetailsResultsBeloteBeanDeclaring(DetailsResultsBeloteBean _str, int... _args) {
        return _str.getDeclaring();
    }
    public static long callBeloteSumDeclaringPlayerSum(BeloteSumDeclaringPlayer _str, int... _args) {
        return _str.getSum();
    }
    public static String callBeloteSumDeclaringPlayerNickname(BeloteSumDeclaringPlayer _str, int... _args) {
        return _str.getNickname();
    }
    public static String callBeloteSumDeclaringPlayerStatut(BeloteSumDeclaringPlayer _str, int... _args) {
        return _str.getStatut();
    }
    public static CustList<DeclaringPlayerValue> callBeloteSumDeclaringPlayerDeclaring(BeloteSumDeclaringPlayer _str, int... _args) {
        return _str.getDeclaring();
    }
    public static String callDeclaringPlayerValueDeclaring(DeclaringPlayerValue _str, int... _args) {
        return _str.getDeclaring();
    }
    public static long callDeclaringPlayerValueValue(DeclaringPlayerValue _str, int... _args) {
        return _str.getValue();
    }
    protected static BeloteSumDeclaringPlayer eltSum(CustList<BeloteSumDeclaringPlayer> _ls, int _i) {
        return _ls.get(_i);
    }


    public static ResultsBeloteBean beanResultsBelote(String _language, ResultsBelote _dataBase) {
        ResultsBeloteBean bean_ = new ResultsBeloteBean();
        bean_.setBuilder(builder());
        bean_.setDataBase(_dataBase,null);
//        bean_.setLanguage(_language);
        return bean_;
    }
    public static boolean callResultsBeloteBeanSuccessfulBid(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().successfulBid();
    }

    public static boolean callResultsBeloteBeanMidBid(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().midBid();
    }

    public static boolean callResultsBeloteBeanFailedBid(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().failedBid();
    }

    public static boolean callResultsBeloteBeanWin(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().win();
    }

    public static boolean callResultsBeloteBeanEquality(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().equality();
    }

    public static boolean callResultsBeloteBeanLoose(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().loose();
    }

    public static boolean callResultsBeloteBeanSlam(ResultsBeloteBean _str, int... _args) {
        return _str.slam();
    }

    public static String callResultsBeloteBeanBidString(ResultsBeloteBean _str, int... _args) {
        return StringUtil.nullToEmpty(_str.getBidString());
    }

    public static StringList callResultsBeloteBeanCalledPlayersList(ResultsBeloteBean _str, int... _args) {
        return _str.getCalledPlayersList();
    }

    public static String callResultsBeloteBeanTakerNickname(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerNickname();
    }

    public static long callResultsBeloteBeanDifferenceScoreTaker(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().getDifferenceScoreTaker();
    }

    public static long callResultsBeloteBeanAbsoluteDiff(ResultsBeloteBean _str, int... _args) {
        return _str.getTakerResult().absoluteDiff();
    }

    public static long callResultsBeloteBeanPointsAttaqueSansPrime(ResultsBeloteBean _str, int... _args) {
        return _str.getPointsAttaqueSansPrime();
    }

    public static long callResultsBeloteBeanPointsAttaqueDefinitif(ResultsBeloteBean _str, int... _args) {
        return _str.getPointsAttaqueDefinitif();
    }

    public static long callResultsBeloteBeanPointsAttaqueTemporaire(ResultsBeloteBean _str, int... _args) {
        return _str.getPointsAttaqueTemporaire();
    }

    public static long callResultsBeloteBeanPointsDefenseDefinitif(ResultsBeloteBean _str, int... _args) {
        return _str.getPointsDefenseDefinitif();
    }

    public static long callResultsBeloteBeanPointsDefenseSansPrime(ResultsBeloteBean _str, int... _args) {
        return _str.getPointsDefenseSansPrime();
    }

    public static long callResultsBeloteBeanPointsDefenseTemporaire(ResultsBeloteBean _str, int... _args) {
        return _str.getPointsDefenseTemporaire();
    }

    public static CustList<LineDeal> callResultsBeloteBeanLinesDeal(ResultsBeloteBean _str, int... _args) {
        return _str.getLinesDeal();
    }


    public static boolean callBeloteBeanPlayGame(BeloteBean _str, int... _args) {
        return _str.playGame();
    }

    public static StringList callBeloteBeanGetNicknames(BeloteBean _str, int... _args) {
        return _str.getNicknames();
    }

    public static CustList<LineDeal> callBeloteBeanGetScores(BeloteBean _str, int... _args) {
        return _str.getHistory();
    }

    public static RulesBeloteBean beanRules(String _language, RulesBelote _dataBase) {
        RulesBeloteBean bean_ = new RulesBeloteBean();
        bean_.setBuilder(builder());
        bean_.setDataBase(null,_dataBase);
//        bean_.setLanguage(_language);
        return bean_;
    }

    public static boolean callRulesBeloteBeanComptePointsClassique(RulesBeloteBean _str, int... _args) {
        return _str.isComptePointsClassique();
    }

    public static String callRulesBeloteBeanRepartition(RulesBeloteBean _str, int... _args) {
        return _str.getRepartition();
    }

    public static StringList callRulesBeloteBeanEncheresAutorisees(RulesBeloteBean _str, int... _args) {
        return _str.getEncheresAutorisees();
    }

    public static String callRulesBeloteBeanGestionCoupePartenaire(RulesBeloteBean _str, int... _args) {
        return _str.getGestionCoupePartenaire();
    }
    public static String callRulesBeloteBeanCartesBattues(RulesBeloteBean _str, int... _args) {
        return _str.getCartesBattues();
    }
    public static StringList callRulesBeloteBeanAnnoncesAutorisees(RulesBeloteBean _str, int... _args) {
        return _str.getAnnoncesAutorisees();
    }
    public static boolean callRulesBeloteBeanDealAll(RulesBeloteBean _str, int... _args) {
        return _str.isDealAll();
    }
    public static boolean callRulesBeloteBeanSousCoupeAdv(RulesBeloteBean _str, int... _args) {
        return _str.isSousCoupeAdv();
    }
//    public static NaSt callLongs(NatCaller _caller, NaSt _str, long... _args) {
//        return _caller.re(_str,getLongArray(_args));
//    }

    public RulesBeloteBean displaying(RulesBeloteBean _b) {
        _b.build();
        return _b;
    }

    public ResultsBeloteBean displayingGame(ResultsBeloteBean _b) {
        _b.build();
        return _b;
    }

    public DetailsResultsBeloteBean displayingDetail(DetailsResultsBeloteBean _b) {
        _b.build();
        return _b;
    }

//    public static NaSt[] getLongArray(long... _ls){
//        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
//    }

//    public static void assertSizeEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
//    }
//
//    public static void assertSizeLongsEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
//    }
//    public static void assertNumberEq(int _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
//    }
//    public static void assertEq(long _exp, NaSt _result, int _index, int _second) {
//        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
//    }
//    public static void assertLongsEq(long _exp, NaSt _result, int _index, int _second) {
//        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
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
//    public static void assertEq(String _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(Rate _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
//    public static void assertEq(long _exp, NaSt _result, int _index) {
//        assertEq(_exp,(((NatArrayStruct)_result).get(_index)));
//    }
    public static DeclaringPlayerValue elt(CustList<DeclaringPlayerValue> _arr, int _index) {
        return _arr.get(_index);
    }

    private static IntBeanBuilderHelperBeloteImpl builder() {
        IntBeanBuilderHelperBeloteImpl builder_ = new IntBeanBuilderHelperBeloteImpl();
        Translations ts_ = new Translations();
        ts_.getMapping().addEntry(EN,new TranslationsLg());
        ts_.getMapping().addEntry(FR,new TranslationsLg());
        ts_.getMapping().getVal(EN).getMapping().addEntry(MessagesBelotePage.APP_BEAN,new TranslationsAppli());
        ts_.getMapping().getVal(FR).getMapping().addEntry(MessagesBelotePage.APP_BEAN,new TranslationsAppli());
        ts_.getMapping().getVal(EN).getMapping().getVal(MessagesBelotePage.APP_BEAN).getMapping().addEntry("",MessagesBelotePage.en());
        ts_.getMapping().getVal(FR).getMapping().getVal(MessagesBelotePage.APP_BEAN).getMapping().addEntry("",MessagesBelotePage.fr());
        builder_.setTranslations(ts_);
        return builder_;
    }
}