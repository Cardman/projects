package cards.belote.beans;

import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.consts.beans.LineDealStruct;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.EquallableBeloteBeanUtil;
import code.scripts.pages.cards.MessagesBelotePage;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.util.Longs;
import code.util.core.StringUtil;

public abstract class BeanBeloteCommonTs extends EquallableBeloteBeanUtil {
    public static final String EN = StringUtil.EN;
    public static final String FR = StringUtil.FR;

    public static final String RETURNE_LINE = "\n";
    public static final String SEP = ":";


    public static NaSt beanDetailResultsBelote(String _language, ResultsBelote _dataBase) {
        BeloteStandardsDetailResults stds_ = new BeloteStandardsDetailResults();
        stds_.setDataBase(_dataBase);
        DetailsResultsBeloteBean bean_ = new DetailsResultsBeloteBean();
        bean_.setBuilder(builder());
        return stds_.bean(bean_, _language);
    }
    public static NaSt callDetailsResultsBeloteBeanDeclaring(NaSt _str, long... _args) {
        return callLongs(new DetailsResultsBeloteBeanDeclaring(),_str,_args);
    }
    public static NaSt callBeloteSumDeclaringPlayerSum(NaSt _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerSum(),_str,_args);
    }
    public static NaSt callBeloteSumDeclaringPlayerNickname(NaSt _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerNickname(),_str,_args);
    }
    public static NaSt callBeloteSumDeclaringPlayerStatut(NaSt _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerStatut(),_str,_args);
    }
    public static NaSt callBeloteSumDeclaringPlayerDeclaring(NaSt _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerDeclaring(),_str,_args);
    }
    public static NaSt callDeclaringPlayerValueDeclaring(NaSt _str, long... _args) {
        return callLongs(new DeclaringPlayerValueDeclaring(),_str,_args);
    }
    public static NaSt callDeclaringPlayerValueValue(NaSt _str, long... _args) {
        return callLongs(new DeclaringPlayerValueValue(),_str,_args);
    }


    public static NaSt beanResultsBelote(String _language, ResultsBelote _dataBase) {
        BeloteStandardsResults stds_ = new BeloteStandardsResults();
        stds_.setDataBase(_dataBase);
        ResultsBeloteBean bean_ = new ResultsBeloteBean();
        bean_.setBuilder(builder());
        return stds_.bean(bean_, _language);
    }
    public static NaSt callResultsBeloteBeanSuccessfulBid(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanSuccessfulBid(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanMidBid(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanMidBid(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanFailedBid(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanFailedBid(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanWin(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanWin(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanEquality(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanEquality(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanLoose(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanLoose(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanSlam(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanSlam(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanBidString(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanBidString(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanCalledPlayersList(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanCalledPlayersList(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanTakerNickname(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanTakerNickname(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanDifferenceScoreTaker(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanDifferenceScoreTaker(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanAbsoluteDiff(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanAbsoluteDiff(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanPointsAttaqueSansPrime(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsAttaqueSansPrime(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanPointsAttaqueDefinitif(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsAttaqueDefinitif(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanPointsAttaqueTemporaire(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsAttaqueTemporaire(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanPointsDefenseDefinitif(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsDefenseDefinitif(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanPointsDefenseSansPrime(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsDefenseSansPrime(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanPointsDefenseTemporaire(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsDefenseTemporaire(),_str,_args);
    }

    public static NaSt callResultsBeloteBeanLinesDeal(NaSt _str, long... _args) {
        return callLongs(new ResultsBeloteBeanLinesDeal(),_str,_args);
    }


    public static NaSt callBeloteBeanPlayGame(NaSt _str, long... _args) {
        return callLongs(new BeloteBeanPlayGame(),_str,_args);
    }

    public static NaSt callBeloteBeanGetNicknames(NaSt _str, long... _args) {
        return callLongs(new BeloteBeanGetNicknames(),_str,_args);
    }

    public static NaSt callBeloteBeanGetScores(NaSt _str, long... _args) {
        return callLongs(new BeloteBeanGetScores(),_str,_args);
    }
    public static NaSt beanRules(String _language, RulesBelote _dataBase) {
        BeloteStandardsRules stds_ = new BeloteStandardsRules();
        stds_.setDataBaseRules(_dataBase);
        RulesBeloteBean bean_ = new RulesBeloteBean();
        bean_.setBuilder(builder());
        return stds_.bean(bean_, _language);
    }

    public static NaSt callRulesBeloteBeanComptePointsClassique(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanComptePointsClassique(),_str,_args);
    }

    public static NaSt callRulesBeloteBeanRepartition(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanRepartition(),_str,_args);
    }

    public static NaSt callRulesBeloteBeanEncheresAutorisees(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanEncheresAutorisees(),_str,_args);
    }

    public static NaSt callRulesBeloteBeanGestionCoupePartenaire(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanGestionCoupePartenaire(),_str,_args);
    }
    public static NaSt callRulesBeloteBeanCartesBattues(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanCartesBattues(),_str,_args);
    }
    public static NaSt callRulesBeloteBeanAnnoncesAutorisees(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanAnnoncesAutorisees(),_str,_args);
    }
    public static NaSt callRulesBeloteBeanDealAll(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanDealAll(),_str,_args);
    }
    public static NaSt callRulesBeloteBeanSousCoupeAdv(NaSt _str, long... _args) {
        return callLongs(new RulesBeloteBeanSousCoupeAdv(),_str,_args);
    }
    public static NaSt callLongs(NatCaller _caller, NaSt _str, long... _args) {
        return _caller.re(_str,getLongArray(_args));
    }

    public NaSt displaying(NaSt _b) {
        ((RulesBeloteBean)((BeloteBeanStruct)_b).getBean()).build();
        return _b;
    }

    public NaSt displayingGame(NaSt _b) {
        ((ResultsBeloteBean)((BeloteBeanStruct)_b).getBean()).build();
        return _b;
    }

    public NaSt displayingDetail(NaSt _b) {
        ((DetailsResultsBeloteBean)((BeloteBeanStruct)_b).getBean()).build();
        return _b;
    }

    public static NaSt[] getLongArray(long... _ls){
        return BeanNatCommonLgNames.getLongArray(Longs.newList(_ls)).getInstance();
    }

    public static void assertSizeEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getScores().size()));
    }

    public static void assertSizeLongsEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((NatArrayStruct)((NatArrayStruct)_result).get(_index)).getLength()));
    }
    public static void assertNumberEq(int _exp, NaSt _result, int _index) {
        assertEq(_exp,(((LineDealStruct)((NatArrayStruct)_result).get(_index)).getLineDeal().getNumber()));
    }
    public static void assertEq(long _exp, NaSt _result, int _index, int _second) {
        assertEq(_exp,((LineDealStruct)(((NatArrayStruct)_result).get(_index))).getLineDeal().getScores().get(_second));
    }
    public static void assertLongsEq(long _exp, NaSt _result, int _index, int _second) {
        assertEq(_exp,((NatArrayStruct)(((NatArrayStruct)_result).get(_index))).get(_second));
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
    public static NaSt elt(NaSt _arr, int _index) {
        return ((NatArrayStruct)_arr).get(_index);
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
