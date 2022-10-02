package cards.belote.beans;

import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.consts.beans.LineDealStruct;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.BeanStruct;
import code.bean.nat.NatCaller;
import code.bean.nat.RateStruct;
import code.expressionlanguage.structs.*;
import code.maths.Rate;
import code.scripts.confs.EquallableBeloteBeanUtil;
import code.util.Longs;

public abstract class BeanBeloteCommonTs extends EquallableBeloteBeanUtil {
    public static final String EN = "en";

    public static final String RETURNE_LINE = "\n";
    public static final String SEP = ":";


    public static Struct beanDetailResultsBelote(String _language, ResultsBelote _dataBase) {
        BeloteStandardsDetailResults stds_ = new BeloteStandardsDetailResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanDetailResults(_language);
    }
    public static Struct callDetailsResultsBeloteBeanDeclaring(Struct _str, long... _args) {
        return callLongs(new DetailsResultsBeloteBeanDeclaring(),_str,_args);
    }
    public static Struct callBeloteSumDeclaringPlayerSum(Struct _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerSum(),_str,_args);
    }
    public static Struct callBeloteSumDeclaringPlayerNickname(Struct _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerNickname(),_str,_args);
    }
    public static Struct callBeloteSumDeclaringPlayerStatut(Struct _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerStatut(),_str,_args);
    }
    public static Struct callBeloteSumDeclaringPlayerDeclaring(Struct _str, long... _args) {
        return callLongs(new BeloteSumDeclaringPlayerDeclaring(),_str,_args);
    }
    public static Struct callDeclaringPlayerValueDeclaring(Struct _str, long... _args) {
        return callLongs(new DeclaringPlayerValueDeclaring(),_str,_args);
    }
    public static Struct callDeclaringPlayerValueValue(Struct _str, long... _args) {
        return callLongs(new DeclaringPlayerValueValue(),_str,_args);
    }


    public static Struct beanResultsBelote(String _language, ResultsBelote _dataBase) {
        BeloteStandardsResults stds_ = new BeloteStandardsResults();
        stds_.setDataBase(_dataBase);
        return stds_.beanResults(_language);
    }
    public static Struct callResultsBeloteBeanSuccessfulBid(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanSuccessfulBid(),_str,_args);
    }

    public static Struct callResultsBeloteBeanMidBid(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanMidBid(),_str,_args);
    }

    public static Struct callResultsBeloteBeanFailedBid(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanFailedBid(),_str,_args);
    }

    public static Struct callResultsBeloteBeanWin(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanWin(),_str,_args);
    }

    public static Struct callResultsBeloteBeanEquality(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanEquality(),_str,_args);
    }

    public static Struct callResultsBeloteBeanLoose(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanLoose(),_str,_args);
    }

    public static Struct callResultsBeloteBeanSlam(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanSlam(),_str,_args);
    }

    public static Struct callResultsBeloteBeanBidString(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanBidString(),_str,_args);
    }

    public static Struct callResultsBeloteBeanCalledPlayersList(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanCalledPlayersList(),_str,_args);
    }

    public static Struct callResultsBeloteBeanTakerNickname(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanTakerNickname(),_str,_args);
    }

    public static Struct callResultsBeloteBeanDifferenceScoreTaker(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanDifferenceScoreTaker(),_str,_args);
    }

    public static Struct callResultsBeloteBeanAbsoluteDiff(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanAbsoluteDiff(),_str,_args);
    }

    public static Struct callResultsBeloteBeanPointsAttaqueSansPrime(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsAttaqueSansPrime(),_str,_args);
    }

    public static Struct callResultsBeloteBeanPointsAttaqueDefinitif(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsAttaqueDefinitif(),_str,_args);
    }

    public static Struct callResultsBeloteBeanPointsAttaqueTemporaire(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsAttaqueTemporaire(),_str,_args);
    }

    public static Struct callResultsBeloteBeanPointsDefenseDefinitif(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsDefenseDefinitif(),_str,_args);
    }

    public static Struct callResultsBeloteBeanPointsDefenseSansPrime(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsDefenseSansPrime(),_str,_args);
    }

    public static Struct callResultsBeloteBeanPointsDefenseTemporaire(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanPointsDefenseTemporaire(),_str,_args);
    }

    public static Struct callResultsBeloteBeanLinesDeal(Struct _str, long... _args) {
        return callLongs(new ResultsBeloteBeanLinesDeal(),_str,_args);
    }


    public static Struct callBeloteBeanPlayGame(Struct _str, long... _args) {
        return callLongs(new BeloteBeanPlayGame(),_str,_args);
    }

    public static Struct callBeloteBeanGetNicknames(Struct _str, long... _args) {
        return callLongs(new BeloteBeanGetNicknames(),_str,_args);
    }

    public static Struct callBeloteBeanGetScores(Struct _str, long... _args) {
        return callLongs(new BeloteBeanGetScores(),_str,_args);
    }
    public static Struct beanRules(String _language, RulesBelote _dataBase) {
        BeloteStandardsRules stds_ = new BeloteStandardsRules();
        stds_.setDataBaseRules(_dataBase);
        return stds_.beanRules(_language);
    }

    public static Struct callRulesBeloteBeanComptePointsClassique(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanComptePointsClassique(),_str,_args);
    }

    public static Struct callRulesBeloteBeanRepartition(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanRepartition(),_str,_args);
    }

    public static Struct callRulesBeloteBeanEncheresAutorisees(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanEncheresAutorisees(),_str,_args);
    }

    public static Struct callRulesBeloteBeanGestionCoupePartenaire(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanGestionCoupePartenaire(),_str,_args);
    }
    public static Struct callRulesBeloteBeanCartesBattues(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanCartesBattues(),_str,_args);
    }
    public static Struct callRulesBeloteBeanAnnoncesAutorisees(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanAnnoncesAutorisees(),_str,_args);
    }
    public static Struct callRulesBeloteBeanDealAll(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanDealAll(),_str,_args);
    }
    public static Struct callRulesBeloteBeanSousCoupeAdv(Struct _str, long... _args) {
        return callLongs(new RulesBeloteBeanSousCoupeAdv(),_str,_args);
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
    public static Struct elt(Struct _arr, int _index) {
        return ((ArrayStruct)_arr).get(_index);
    }
}
