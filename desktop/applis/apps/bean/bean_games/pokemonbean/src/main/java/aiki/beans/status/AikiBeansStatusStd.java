package aiki.beans.status;
import aiki.beans.*;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansStatusStd {
    public static final String TYPE_STATUS_BEAN = "aiki.beans.status.StatusBean";
    public static final String TYPE_STATUS_SET_BEAN = "aiki.beans.status.StatusSetBean";

    private static final String CLICK_INDEX = "clickIndex";
    private static final String INCREMENT_END_ROUND_INT = "incrementEndRoundInt";
    private static final String IS_SINGLE = "isSingle";
    private static final String GET_TR_MULT_STAT = "getTrMultStat";
    private static final String GET_EFFECT_PARTNER = "getEffectPartner";
    private static final String SEARCH = "search";
    private static final String CLICK_STATUS = "clickStatus";
    private static final String GET_TR_STATUS = "getTrStatus";
    private static final String DISPLAY_NAME = "displayName";
    private static final String ANIM_STATUS = "animStatus";
    private static final String END_ROUND = "endRound";
    private static final String END_ROUND_RANK = "endRoundRank";
    private static final String REASONS_END_ROUND = "reasonsEndRound";
    private static final String MAP_VARS_FAIL_END_ROUND = "mapVarsFailEndRound";
    private static final String SINGLE_STATUS = "singleStatus";
    private static final String INCREMENTING_DAMAGE_BY_ROUNDS = "incrementingDamageByRounds";
    private static final String CATCHING_RATE = "catchingRate";
    private static final String DISABLED_EFF_IF_SWITCH = "disabledEffIfSwitch";
    private static final String INCREMENT_END_ROUND = "incrementEndRound";
    private static final String INCREMENTING_END_ROUND = "incrementingEndRound";
    private static final String MULT_STAT = "multStat";
    private static final String REASONS = "reasons";
    private static final String MAP_VARS_FAIL = "mapVarsFail";
    private static final String RATE_FOR_USING_A_MOVE = "rateForUsingAMove";
    private static final String NOT_ATTACK = "notAttack";
    private static final String RATE_FOR_USING_A_MOVE_IF_FOE = "rateForUsingAMoveIfFoe";
    private static final String NOT_ATTACK_FOE = "notAttackFoe";
    private static final String RATE_FOR_FULL_HEAL_IF_MOVE = "rateForFullHealIfMove";
    private static final String LAW_FOR_USING_A_MOVE_NB_ROUND = "lawForUsingAMoveNbRound";
    private static final String POWER = "power";
    private static final String ATTACK = "attack";
    private static final String DEFENSE = "defense";
    private static final String EFFECTS_PARTNER = "effectsPartner";
    private static final String TYPED_STATUS = "typedStatus";
    private static final String SORTED_STATUS = "sortedStatus";

    public static void build(PokemonStandards _std) {
        buildStatusBean(_std);
        buildStatusSetBean(_std);
    }
    private static void buildStatusBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_STATUS_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(ANIM_STATUS,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(END_ROUND_RANK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(SINGLE_STATUS,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(INCREMENTING_DAMAGE_BY_ROUNDS,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(CATCHING_RATE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(DISABLED_EFF_IF_SWITCH,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(INCREMENT_END_ROUND,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(INCREMENTING_END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(MULT_STAT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(REASONS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MAP_VARS_FAIL, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(RATE_FOR_USING_A_MOVE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(NOT_ATTACK,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(RATE_FOR_USING_A_MOVE_IF_FOE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(NOT_ATTACK_FOE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(RATE_FOR_FULL_HEAL_IF_MOVE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(LAW_FOR_USING_A_MOVE_NB_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(POWER,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(ATTACK,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(DEFENSE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(EFFECTS_PARTNER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_INDEX,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(INCREMENT_END_ROUND_INT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_SINGLE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_EFFECT_PARTNER,params_,PokemonStandards.TYPE_EFFECT_PARTNER_STATUS, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_STATUS_BEAN, type_);
    }
    private static void buildStatusSetBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_STATUS_SET_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(TYPED_STATUS,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(SORTED_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_STATUS_SET_BEAN, type_);
    }
    public static ResultErrorStd getResultStatusBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        StatusBean instance_ = (StatusBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ANIM_STATUS)) {
            res_.setResult(new StringStruct(instance_.getAnimStatus()));
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
        if (StringUtil.quickEq(fieldName_,SINGLE_STATUS)) {
            res_.setResult(BooleanStruct.of(instance_.getSingleStatus()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,INCREMENTING_DAMAGE_BY_ROUNDS)) {
            res_.setResult(BooleanStruct.of(instance_.getIncrementingDamageByRounds()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATCHING_RATE)) {
            res_.setResult(new RateStruct(instance_.getCatchingRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DISABLED_EFF_IF_SWITCH)) {
            res_.setResult(BooleanStruct.of(instance_.getDisabledEffIfSwitch()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,INCREMENT_END_ROUND)) {
            res_.setResult(new IntStruct(instance_.getIncrementEndRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,INCREMENTING_END_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getIncrementingEndRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MULT_STAT)) {
            res_.setResult(PokemonStandards.getStaRate(_cont,instance_.getMultStat()));
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
        if (StringUtil.quickEq(fieldName_,RATE_FOR_USING_A_MOVE)) {
            res_.setResult(new RateStruct(instance_.getRateForUsingAMove(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NOT_ATTACK)) {
            res_.setResult(BooleanStruct.of(instance_.getNotAttack()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_FOR_USING_A_MOVE_IF_FOE)) {
            res_.setResult(new RateStruct(instance_.getRateForUsingAMoveIfFoe(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NOT_ATTACK_FOE)) {
            res_.setResult(BooleanStruct.of(instance_.getNotAttackFoe()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_FOR_FULL_HEAL_IF_MOVE)) {
            res_.setResult(new RateStruct(instance_.getRateForFullHealIfMove(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAW_FOR_USING_A_MOVE_NB_ROUND)) {
            res_.setResult(PokemonStandards.getLgIntRate(_cont,instance_.getLawForUsingAMoveNbRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,POWER)) {
            res_.setResult(new RateStruct(instance_.getPower(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ATTACK)) {
            res_.setResult(new StringStruct(instance_.getAttack()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DEFENSE)) {
            res_.setResult(new StringStruct(instance_.getDefense()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EFFECTS_PARTNER)) {
            res_.setResult(PokemonStandards.getEffPartStat(instance_.getEffectsPartner()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultStatusSetBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        StatusSetBean instance_ = (StatusSetBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TYPED_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTypedStatus()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SORTED_STATUS)) {
            res_.setResult(std_.getStringArray(instance_.getSortedStatus()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultStatusSetBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        StatusSetBean instance_ = (StatusSetBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,TYPED_STATUS)) {
            instance_.setTypedStatus(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStatusBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        StatusBean instance_ = (StatusBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_INDEX)) {
            res_.setResult(new StringStruct(instance_.clickIndex()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,INCREMENT_END_ROUND_INT)) {
            res_.setResult(BooleanStruct.of(instance_.incrementEndRoundInt()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_SINGLE)) {
            res_.setResult(BooleanStruct.of(instance_.isSingle()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMultStat(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_EFFECT_PARTNER)) {
            res_.setResult(new EffectPartnerStatusStruct(instance_.getEffectPartner(),PokemonStandards.TYPE_EFFECT_PARTNER_STATUS));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStatusSetBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        StatusSetBean instance_ = (StatusSetBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,SEARCH)) {
            res_.setResult(new StringStruct(instance_.search()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrStatus(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
}
