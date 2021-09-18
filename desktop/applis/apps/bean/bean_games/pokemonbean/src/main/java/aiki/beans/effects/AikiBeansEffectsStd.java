package aiki.beans.effects;

import aiki.beans.*;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansEffectsStd {
    public static final String TYPE_COMBO_DTO = "aiki.beans.effects.ComboDto";
    public static final String TYPE_COMBOS_BEAN = "aiki.beans.effects.CombosBean";
    public static final String TYPE_EFFECT_COMBO_BEAN = "aiki.beans.effects.EffectComboBean";
    public static final String TYPE_EFFECT_WHILE_SENDING_BEAN = "aiki.beans.effects.EffectWhileSendingBean";

    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_TR_STATISTIC = "getTrStatistic";
    private static final String GET_COMBOS_KEY = "getCombosKey";
    private static final String CLICK_WEATHER = "clickWeather";
    private static final String GET_TR_WEATHER = "getTrWeather";
    private static final String RANDOM_STATIS = "randomStatis";
    private static final String IS_ALWAYS_ENABLED = "isAlwaysEnabled";
    private static final String NOT_EMPTY_VAR_BOOST = "notEmptyVarBoost";
    private static final String GET_FAIL = "getFail";
    private static final String GET_RATE = "getRate";
    private static final String GET_SWAP_FAIL = "getSwapFail";
    private static final String EFFECT = "effect";
    private static final String MOVES = "moves";
    private static final String INDEX = "index";
    private static final String END_ROUND = "endRound";
    private static final String END_ROUND_RANK = "endRoundRank";
    private static final String REASONS_END_ROUND = "reasonsEndRound";
    private static final String MAP_VARS_FAIL_END_ROUND = "mapVarsFailEndRound";
    private static final String MULT_EVT_RATE_SEC_EFF = "multEvtRateSecEff";
    private static final String MULT_STATISTIC_FOE = "multStatisticFoe";
    private static final String RANK_INCREMENT_NB_ROUND = "rankIncrementNbRound";
    private static final String REPEATED_ROUNDS_LAW = "repeatedRoundsLaw";
    private static final String COMBO = "combo";
    private static final String COMBOS = "combos";
    private static final String DISABLE_WEATHER = "disableWeather";
    private static final String ENABLED_WEATHER = "enabledWeather";
    private static final String COPYING_ABILITY = "copyingAbility";
    private static final String PLATE = "plate";
    private static final String MULT_WEIGHT = "multWeight";
    private static final String STATISTIC = "statistic";
    private static final String REASONS = "reasons";
    private static final String MAP_VARS_FAIL = "mapVarsFail";
    private static final String EVT_RATE = "evtRate";
    private static final String EVT_RATE_PER_CENT = "evtRatePerCent";
    private static final String STATIS_VAR_RANK = "statisVarRank";
    private static final String MAP_VARS_STATISTICS = "mapVarsStatistics";
    private static final String SWAP_BOOST_STATIS = "swapBoostStatis";
    private static final String CANCEL_LOW_STAT = "cancelLowStat";
    private static final String DEFAULT_BOOST = "defaultBoost";
    private static final String CANCEL_CHGT_STAT = "cancelChgtStat";
    private static final String COPY_BOOST = "copyBoost";

    public static void build(PokemonStandards _std) {
        buildComboDto(_std);
        buildCombosBean(_std);
        buildEffectComboBean(_std);
        buildEffectWhileSendingBean(_std);
    }
    private static void buildComboDto(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMBO_DTO, fields_, methods_, _std.getAliasObject());
        _std.getStds().addEntry(TYPE_COMBO_DTO, type_);
    }
    private static void buildCombosBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMBOS_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(COMBO,_std.getAliasString(),false,false));
        fields_.add(new StandardField(COMBOS,AikiBeansEffectsStd.TYPE_COMBO_DTO,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBOS_KEY,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_COMBOS_BEAN, type_);
    }
    private static void buildEffectComboBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EFFECT_COMBO_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(MOVES, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(INDEX,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(END_ROUND,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(END_ROUND_RANK,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(MULT_EVT_RATE_SEC_EFF,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(MULT_STATISTIC_FOE, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(RANK_INCREMENT_NB_ROUND,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(REPEATED_ROUNDS_LAW, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(COMBOS,AikiBeansEffectsStd.TYPE_COMBO_DTO,false,false));
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_TR_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_EFFECT_COMBO_BEAN, type_);
    }
    private static void buildEffectWhileSendingBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EFFECT_WHILE_SENDING_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(EFFECT,PokemonStandards.TYPE_EFFECT_WHILE_SENDING,false,false));
        fields_.add(new StandardField(DISABLE_WEATHER,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(ENABLED_WEATHER,_std.getAliasString(),false,false));
        fields_.add(new StandardField(COPYING_ABILITY,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(PLATE,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(MULT_WEIGHT,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(STATISTIC,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(REASONS, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(MAP_VARS_FAIL, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(EVT_RATE,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(EVT_RATE_PER_CENT,_std.getAliasString(),false,false));
        fields_.add(new StandardField(STATIS_VAR_RANK, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(MAP_VARS_STATISTICS, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(SWAP_BOOST_STATIS, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(CANCEL_LOW_STAT, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(DEFAULT_BOOST,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(CANCEL_CHGT_STAT, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(COPY_BOOST, BeanNatLgNames.TYPE_LIST,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TR_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(RANDOM_STATIS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_ALWAYS_ENABLED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(NOT_EMPTY_VAR_BOOST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_FAIL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_RATE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_SWAP_FAIL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_EFFECT_WHILE_SENDING_BEAN, type_);
    }
    public static ResultErrorStd getResultCombosBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        CombosBean instance_ = (CombosBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,COMBO)) {
            res_.setResult(new StringStruct(CombosBean.COMBO));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COMBOS)) {
            res_.setResult(new ComboDtoStruct(instance_.getCombos(),AikiBeansEffectsStd.TYPE_COMBO_DTO));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEffectComboBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectComboBean instance_ = (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,MOVES)) {
            res_.setResult(std_.getStringArray(instance_.getMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(instance_.getIndex()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getEndRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_ROUND_RANK)) {
            res_.setResult(new IntStruct(instance_.getEndRoundRank()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REASONS_END_ROUND)) {
            res_.setResult(std_.getStringArray(instance_.getReasonsEndRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_VARS_FAIL_END_ROUND)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getMapVarsFailEndRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MULT_EVT_RATE_SEC_EFF)) {
            res_.setResult(new RateStruct(instance_.getMultEvtRateSecEff(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MULT_STATISTIC_FOE)) {
            res_.setResult(PokemonStandards.getStaRate(_cont,instance_.getMultStatisticFoe()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RANK_INCREMENT_NB_ROUND)) {
            res_.setResult(new IntStruct(instance_.getRankIncrementNbRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REPEATED_ROUNDS_LAW)) {
            res_.setResult(PokemonStandards.getLgIntRate(_cont,instance_.getRepeatedRoundsLaw()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEffectWhileSendingBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectWhileSendingBean instance_ = (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISABLE_WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.getDisableWeather()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getEnabledWeather()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COPYING_ABILITY)) {
            res_.setResult(BooleanStruct.of(instance_.getCopyingAbility()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PLATE)) {
            res_.setResult(BooleanStruct.of(instance_.getPlate()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MULT_WEIGHT)) {
            res_.setResult(new RateStruct(instance_.getMultWeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATISTIC)) {
            res_.setResult(BooleanStruct.of(instance_.getStatistic()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REASONS)) {
            res_.setResult(std_.getStringArray(instance_.getReasons()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_VARS_FAIL)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getMapVarsFail()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EVT_RATE)) {
            res_.setResult(new RateStruct(instance_.getEvtRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EVT_RATE_PER_CENT)) {
            res_.setResult(new StringStruct(instance_.getEvtRatePerCent()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATIS_VAR_RANK)) {
            res_.setResult(PokemonStandards.getStrByte(_cont,instance_.getStatisVarRank()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_VARS_STATISTICS)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getMapVarsStatistics()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SWAP_BOOST_STATIS)) {
            res_.setResult(std_.getStringArray(instance_.getSwapBoostStatis()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CANCEL_LOW_STAT)) {
            res_.setResult(std_.getStringArray(instance_.getCancelLowStat()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DEFAULT_BOOST)) {
            res_.setResult(new IntStruct(instance_.getDefaultBoost()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CANCEL_CHGT_STAT)) {
            res_.setResult(std_.getStringArray(instance_.getCancelChgtStat()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COPY_BOOST)) {
            res_.setResult(std_.getStringArray(instance_.getCopyBoost()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultEffectComboBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectComboBean instance_ = (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            instance_.setIndex(NumParsers.convertToNumber(_val).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COMBOS)) {
            instance_.setCombos(((ComboDtoStruct)_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultEffectWhileSendingBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectWhileSendingBean instance_ = (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,EFFECT)) {
            instance_.setEffect(((EffectWhileSendingWithStatisticStruct)_val).getEffectPartnerStatus());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodCombosBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        CombosBean instance_ = (CombosBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_COMBOS_KEY)) {
            res_.setResult(PokemonStandards.getStrList(_cont,instance_.getCombosKey()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectComboBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        EffectComboBean instance_ = (EffectComboBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getTrStatistic(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectWhileSendingBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        EffectWhileSendingBean instance_ = (EffectWhileSendingBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickWeather()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrWeather()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,RANDOM_STATIS)) {
            res_.setResult(BooleanStruct.of(instance_.randomStatis()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ALWAYS_ENABLED)) {
            res_.setResult(BooleanStruct.of(instance_.isAlwaysEnabled()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,NOT_EMPTY_VAR_BOOST)) {
            res_.setResult(BooleanStruct.of(instance_.notEmptyVarBoost()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_FAIL)) {
            res_.setResult(new StringStruct(instance_.getFail(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_RATE)) {
            res_.setResult(new RateStruct(instance_.getRate(NumParsers.convertToNumber(_args[0]).intStruct()),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_SWAP_FAIL)) {
            res_.setResult(new StringStruct(instance_.getSwapFail(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
}
