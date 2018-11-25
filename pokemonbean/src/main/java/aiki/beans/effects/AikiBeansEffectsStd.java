package aiki.beans.effects;
import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import aiki.fight.effects.EffectWhileSending;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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

    public static void build(BeanLgNames _std) {
        buildComboDto(_std);
        buildCombosBean(_std);
        buildEffectComboBean(_std);
        buildEffectWhileSendingBean(_std);
    }
    private static void buildComboDto(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMBO_DTO, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMBO_DTO, type_);
    }
    private static void buildCombosBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMBOS_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(COMBO,new StandardField(COMBO,_std.getAliasString(),false,false,type_));
        fields_.put(COMBOS,new StandardField(COMBOS,AikiBeansEffectsStd.TYPE_COMBO_DTO,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_COMBOS_KEY,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_COMBOS_BEAN, type_);
    }
    private static void buildEffectComboBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_COMBO_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(MOVES,new StandardField(MOVES,_std.getCustList(),false,false,type_));
        fields_.put(INDEX,new StandardField(INDEX,_std.getAliasPrimLong(),false,false,type_));
        fields_.put(END_ROUND,new StandardField(END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(END_ROUND_RANK,new StandardField(END_ROUND_RANK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(REASONS_END_ROUND,new StandardField(REASONS_END_ROUND,_std.getCustList(),false,false,type_));
        fields_.put(MAP_VARS_FAIL_END_ROUND,new StandardField(MAP_VARS_FAIL_END_ROUND,_std.getCustMap(),false,false,type_));
        fields_.put(MULT_EVT_RATE_SEC_EFF,new StandardField(MULT_EVT_RATE_SEC_EFF,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_STATISTIC_FOE,new StandardField(MULT_STATISTIC_FOE,_std.getCustMap(),false,false,type_));
        fields_.put(RANK_INCREMENT_NB_ROUND,new StandardField(RANK_INCREMENT_NB_ROUND,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(REPEATED_ROUNDS_LAW,new StandardField(REPEATED_ROUNDS_LAW,_std.getCustMap(),false,false,type_));
        fields_.put(COMBOS,new StandardField(COMBOS,AikiBeansEffectsStd.TYPE_COMBO_DTO,false,false,type_));
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_COMBO_BEAN, type_);
    }
    private static void buildEffectWhileSendingBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_WHILE_SENDING_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(EFFECT,new StandardField(EFFECT,PokemonStandards.TYPE_EFFECT_WHILE_SENDING,false,false,type_));
        fields_.put(DISABLE_WEATHER,new StandardField(DISABLE_WEATHER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ENABLED_WEATHER,new StandardField(ENABLED_WEATHER,_std.getAliasString(),false,false,type_));
        fields_.put(COPYING_ABILITY,new StandardField(COPYING_ABILITY,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PLATE,new StandardField(PLATE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(MULT_WEIGHT,new StandardField(MULT_WEIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(STATISTIC,new StandardField(STATISTIC,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(REASONS,new StandardField(REASONS,_std.getCustList(),false,false,type_));
        fields_.put(MAP_VARS_FAIL,new StandardField(MAP_VARS_FAIL,_std.getCustMap(),false,false,type_));
        fields_.put(EVT_RATE,new StandardField(EVT_RATE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(EVT_RATE_PER_CENT,new StandardField(EVT_RATE_PER_CENT,_std.getAliasString(),false,false,type_));
        fields_.put(STATIS_VAR_RANK,new StandardField(STATIS_VAR_RANK,_std.getCustMap(),false,false,type_));
        fields_.put(MAP_VARS_STATISTICS,new StandardField(MAP_VARS_STATISTICS,_std.getCustMap(),false,false,type_));
        fields_.put(SWAP_BOOST_STATIS,new StandardField(SWAP_BOOST_STATIS,_std.getCustList(),false,false,type_));
        fields_.put(CANCEL_LOW_STAT,new StandardField(CANCEL_LOW_STAT,_std.getCustList(),false,false,type_));
        fields_.put(DEFAULT_BOOST,new StandardField(DEFAULT_BOOST,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(CANCEL_CHGT_STAT,new StandardField(CANCEL_CHGT_STAT,_std.getCustList(),false,false,type_));
        fields_.put(COPY_BOOST,new StandardField(COPY_BOOST,_std.getCustList(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(RANDOM_STATIS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ALWAYS_ENABLED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(NOT_EMPTY_VAR_BOOST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_FAIL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_RATE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_SWAP_FAIL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_WHILE_SENDING_BEAN, type_);
    }
    public static ResultErrorStd getResultCombosBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        CombosBean instance_ = (CombosBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,COMBO)) {
            res_.setResult(new StringStruct(instance_.getCombo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COMBOS)) {
            res_.setResult(StdStruct.newInstance(instance_.getCombos(),AikiBeansEffectsStd.TYPE_COMBO_DTO));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEffectComboBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectComboBean instance_ = (EffectComboBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MOVES)) {
            res_.setResult(new StdStruct(instance_.getMoves(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INDEX)) {
            res_.setResult(new LongStruct(instance_.getIndex()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_ROUND)) {
            res_.setResult(new BooleanStruct(instance_.getEndRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_ROUND_RANK)) {
            res_.setResult(new IntStruct(instance_.getEndRoundRank()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getReasonsEndRound(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_FAIL_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getMapVarsFailEndRound(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_EVT_RATE_SEC_EFF)) {
            res_.setResult(new StdStruct(instance_.getMultEvtRateSecEff(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STATISTIC_FOE)) {
            res_.setResult(new StdStruct(instance_.getMultStatisticFoe(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RANK_INCREMENT_NB_ROUND)) {
            res_.setResult(new ShortStruct(instance_.getRankIncrementNbRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REPEATED_ROUNDS_LAW)) {
            res_.setResult(new StdStruct(instance_.getRepeatedRoundsLaw(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEffectWhileSendingBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectWhileSendingBean instance_ = (EffectWhileSendingBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DISABLE_WEATHER)) {
            res_.setResult(new BooleanStruct(instance_.getDisableWeather()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getEnabledWeather()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPYING_ABILITY)) {
            res_.setResult(new BooleanStruct(instance_.getCopyingAbility()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLATE)) {
            res_.setResult(new BooleanStruct(instance_.getPlate()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_WEIGHT)) {
            res_.setResult(new StdStruct(instance_.getMultWeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATISTIC)) {
            res_.setResult(new BooleanStruct(instance_.getStatistic()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS)) {
            res_.setResult(new StdStruct(instance_.getReasons(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_FAIL)) {
            res_.setResult(new StdStruct(instance_.getMapVarsFail(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVT_RATE)) {
            res_.setResult(new StdStruct(instance_.getEvtRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVT_RATE_PER_CENT)) {
            res_.setResult(new StringStruct(instance_.getEvtRatePerCent()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATIS_VAR_RANK)) {
            res_.setResult(new StdStruct(instance_.getStatisVarRank(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_STATISTICS)) {
            res_.setResult(new StdStruct(instance_.getMapVarsStatistics(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SWAP_BOOST_STATIS)) {
            res_.setResult(new StdStruct(instance_.getSwapBoostStatis(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_LOW_STAT)) {
            res_.setResult(new StdStruct(instance_.getCancelLowStat(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DEFAULT_BOOST)) {
            res_.setResult(new IntStruct(instance_.getDefaultBoost()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_CHGT_STAT)) {
            res_.setResult(new StdStruct(instance_.getCancelChgtStat(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPY_BOOST)) {
            res_.setResult(new StdStruct(instance_.getCopyBoost(),std_.getCustList()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultEffectComboBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectComboBean instance_ = (EffectComboBean) _instance.getInstance();
        Object value_ = _value.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            instance_.setIndex((Long) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,COMBOS)) {
            instance_.setCombos((ComboDto) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultEffectWhileSendingBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectWhileSendingBean instance_ = (EffectWhileSendingBean) _instance.getInstance();
        Object value_ = _value.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,EFFECT)) {
            instance_.setEffect((EffectWhileSending) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodCombosBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        CombosBean instance_ = (CombosBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_COMBOS_KEY)) {
            res_.setResult(new StdStruct(instance_.getCombosKey(),std_.getCustList()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectComboBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectComboBean instance_ = (EffectComboBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getTrStatistic((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectWhileSendingBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectWhileSendingBean instance_ = (EffectWhileSendingBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickWeather()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrWeather()));
            return res_;
        }
        if (StringList.quickEq(methodName_,RANDOM_STATIS)) {
            res_.setResult(new BooleanStruct(instance_.randomStatis()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ALWAYS_ENABLED)) {
            res_.setResult(new BooleanStruct(instance_.isAlwaysEnabled()));
            return res_;
        }
        if (StringList.quickEq(methodName_,NOT_EMPTY_VAR_BOOST)) {
            res_.setResult(new BooleanStruct(instance_.notEmptyVarBoost()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_FAIL)) {
            res_.setResult(new StringStruct(instance_.getFail((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_RATE)) {
            res_.setResult(new StdStruct(instance_.getRate((Long)_args[0]),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_SWAP_FAIL)) {
            res_.setResult(new StringStruct(instance_.getSwapFail((Long)_args[0])));
            return res_;
        }
        return res_;
    }
}
