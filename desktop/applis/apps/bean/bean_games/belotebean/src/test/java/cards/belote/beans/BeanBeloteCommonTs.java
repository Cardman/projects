package cards.belote.beans;

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
