package aiki.beans.facade.fight;

import aiki.beans.PokemonStandards;
import code.bean.nat.RateStruct;
import code.bean.nat.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansFacadeFightStd {
    public static final String TYPE_KEY_HYPOTHESIS = "aiki.beans.facade.fight.KeyHypothesis";
    public static final String TYPE_MULT_POWER_MOVES = "aiki.beans.facade.fight.MultPowerMoves";
    public static final String TYPE_STATISTIC_INFO = "aiki.beans.facade.fight.StatisticInfo";
    public static final String TYPE_SUFFERED_DAMAGE_CATEGORY = "aiki.beans.facade.fight.SufferedDamageCategory";

    private static final String GET_PLAYER_POKEMON = "getPlayerPokemon";
    private static final String GET_MOVE = "getMove";
    private static final String GET_TARGET_POKEMON = "getTargetPokemon";
    private static final String IS_BELONGS_TO_USER = "isBelongsToUser";
    private static final String GET_NUMBER_TARGET = "getNumberTarget";
    private static final String GET_DAMAGE = "getDamage";
    private static final String GET_DISPLAY_STATISTIC = "getDisplayStatistic";
    private static final String IS_BASE = "isBase";
    private static final String GET_STATIS_BASE = "getStatisBase";
    private static final String GET_EV = "getEv";
    private static final String GET_IV = "getIv";
    private static final String IS_BOOST = "isBoost";
    private static final String GET_STATIS_BOOST = "getStatisBoost";
    private static final String GET_MULT_INFLICTED = "getMultInflicted";
    private static final String GET_MULT_SUFFERING = "getMultSuffering";
    private static final String GET_ROUND = "getRound";
    private static final String GET_USING = "getUsing";

    public static void build(PokemonStandards _std) {
        buildKeyHypothesis(_std);
        buildMultPowerMoves(_std);
        buildStatisticInfo(_std);
        buildSufferedDamageCategory(_std);
    }
    private static void buildKeyHypothesis(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_KEY_HYPOTHESIS, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(BeanNatLgNames.TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_PLAYER_POKEMON,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TARGET_POKEMON,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_BELONGS_TO_USER,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NUMBER_TARGET,params_, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_DAMAGE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_KEY_HYPOTHESIS, type_);
    }
    private static void buildMultPowerMoves(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_MULT_POWER_MOVES, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MULT_INFLICTED,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MULT_SUFFERING,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_MULT_POWER_MOVES, type_);
    }
    private static void buildStatisticInfo(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_STATISTIC_INFO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_DISPLAY_STATISTIC,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_BASE,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_STATIS_BASE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_EV,params_, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_IV,params_, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_BOOST,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_STATIS_BOOST,params_, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_STATISTIC_INFO, type_);
    }
    private static void buildSufferedDamageCategory(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_SUFFERED_DAMAGE_CATEGORY, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ROUND,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_USING,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_SUFFERED_DAMAGE_CATEGORY, type_);
    }
    public static ResultErrorStd invokeMethodKeyHypothesis(ClassMethodId _method, KeyHypothesis _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_PLAYER_POKEMON)) {
            res_.setResult(new StringStruct(_inst.getPlayerPokemon()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(_inst.getMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TARGET_POKEMON)) {
            res_.setResult(new StringStruct(_inst.getTargetPokemon()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BELONGS_TO_USER)) {
            res_.setResult(BooleanStruct.of(_inst.isBelongsToUser()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NUMBER_TARGET)) {
            res_.setResult(new IntStruct(_inst.getNumberTarget()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_DAMAGE)) {
            res_.setResult(new RateStruct(_inst.getDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMultPowerMoves(ClassMethodId _method, MultPowerMoves _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MULT_INFLICTED)) {
            res_.setResult(new RateStruct(_inst.getMultInflicted(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MULT_SUFFERING)) {
            res_.setResult(new RateStruct(_inst.getMultSuffering(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStatisticInfo(ClassMethodId _method, StatisticInfo _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_DISPLAY_STATISTIC)) {
            res_.setResult(new StringStruct(_inst.getDisplayStatistic()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BASE)) {
            res_.setResult(BooleanStruct.of(_inst.isBase()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_STATIS_BASE)) {
            res_.setResult(new RateStruct(_inst.getStatisBase(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_EV)) {
            res_.setResult(new IntStruct(_inst.getEv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IV)) {
            res_.setResult(new IntStruct(_inst.getIv()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BOOST)) {
            res_.setResult(BooleanStruct.of(_inst.isBoost()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_STATIS_BOOST)) {
            res_.setResult(new IntStruct(_inst.getStatisBoost()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSufferedDamageCategory(ClassMethodId _method, SufferedDamageCategory _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_ROUND)) {
            res_.setResult(new RateStruct(_inst.getRound(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_USING)) {
            res_.setResult(new RateStruct(_inst.getUsing(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
}
