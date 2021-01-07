package aiki.beans.endround;
import aiki.beans.AikiBeansStd;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.RateStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansEndroundStd {
    public static final String TYPE_EFFECT_END_ROUND_BEAN = "aiki.beans.endround.EffectEndRoundBean";
    public static final String TYPE_EFFECT_END_ROUND_FOE_BEAN = "aiki.beans.endround.EffectEndRoundFoeBean";
    public static final String TYPE_EFFECT_END_ROUND_GLOBAL_BEAN = "aiki.beans.endround.EffectEndRoundGlobalBean";
    public static final String TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN = "aiki.beans.endround.EffectEndRoundIndividualBean";
    public static final String TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN = "aiki.beans.endround.EffectEndRoundMultiRelationBean";
    public static final String TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN = "aiki.beans.endround.EffectEndRoundPositionRelationBean";
    public static final String TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN = "aiki.beans.endround.EffectEndRoundPositionTargetBean";
    public static final String TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN = "aiki.beans.endround.EffectEndRoundSingleRelationBean";
    public static final String TYPE_EFFECT_END_ROUND_SINGLE_STATUS_BEAN = "aiki.beans.endround.EffectEndRoundSingleStatusBean";
    public static final String TYPE_EFFECT_END_ROUND_STATUS_BEAN = "aiki.beans.endround.EffectEndRoundStatusBean";
    public static final String TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN = "aiki.beans.endround.EffectEndRoundStatusRelationBean";
    public static final String TYPE_EFFECT_END_ROUND_TEAM_BEAN = "aiki.beans.endround.EffectEndRoundTeamBean";
    public static final String TYPE_END_ROUND_BEAN = "aiki.beans.endround.EndRoundBean";

    private static final String CLICK_MOVE = "clickMove";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String CLICK_STATUS = "clickStatus";
    private static final String CLICK_MOVES = "clickMoves";
    private static final String GET_TR_MOVES = "getTrMoves";
    private static final String GET_EVTS = "getEvts";
    private static final String GET_PAGE = "getPage";
    private static final String CLICK_USER_STATUS = "clickUserStatus";
    private static final String GET_TR_USER_STATUS = "getTrUserStatus";
    private static final String CLICK_DAMAGE_STATUS = "clickDamageStatus";
    private static final String GET_TR_DAMAGE_STATUS = "getTrDamageStatus";
    private static final String IS_TYPE = "isType";
    private static final String GET_TR_TYPE = "getTrType";
    private static final String GET_MOVES_SAME_CATEGORY = "getMovesSameCategory";
    private static final String CLICK_TARGET_RELATION_MOVE = "clickTargetRelationMove";
    private static final String GET_TR_TARGET_RELATION_MOVE = "getTrTargetRelationMove";
    private static final String MOVE = "move";
    private static final String INDEX = "index";
    private static final String ABILITY = "ability";
    private static final String ITEM = "item";
    private static final String STATUS = "status";
    private static final String MOVES = "moves";
    private static final String END_ROUND_RANK = "endRoundRank";
    private static final String REASONS_END_ROUND = "reasonsEndRound";
    private static final String MAP_VARS_FAIL_END_ROUND = "mapVarsFailEndRound";
    private static final String END_ROUND_HTML = "endRoundHtml";
    private static final String INFLICTED_RATE_HP_TARGET = "inflictedRateHpTarget";
    private static final String DAMAGE_END_ROUND = "damageEndRound";
    private static final String HEALING_END_ROUND_GROUND = "healingEndRoundGround";
    private static final String HEALING_END_ROUND = "healingEndRound";
    private static final String PUTTING_KO = "puttingKo";
    private static final String IMMUNE_TYPES = "immuneTypes";
    private static final String DELETE_ALL_STATUS = "deleteAllStatus";
    private static final String RECOIL_DAMAGE = "recoilDamage";
    private static final String HEAL_HP = "healHp";
    private static final String USER_STATUS_END_ROUND = "userStatusEndRound";
    private static final String MULT_DAMAGE_STATUS = "multDamageStatus";
    private static final String HEAL_HP_BY_OWNER_TYPES = "healHpByOwnerTypes";
    private static final String DAMAGE_BY_STATUS = "damageByStatus";
    private static final String RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS = "rateDamageFunctionOfNbRounds";
    private static final String LAW_FOR_ENABLING_EFFECT = "lawForEnablingEffect";
    private static final String END_ROUND_STATUS_HTML = "endRoundStatusHtml";
    private static final String THIEVED_HP_RATE_TARGET_TO_USER = "thievedHpRateTargetToUser";
    private static final String DELETE_ALL_STATUS_ALLY = "deleteAllStatusAlly";

    public static void build(PokemonStandards _std) {
        buildEffectEndRoundBean(_std);
        buildEffectEndRoundFoeBean(_std);
        buildEffectEndRoundGlobalBean(_std);
        buildEffectEndRoundIndividualBean(_std);
        buildEffectEndRoundMultiRelationBean(_std);
        buildEffectEndRoundPositionRelationBean(_std);
        buildEffectEndRoundPositionTargetBean(_std);
        buildEffectEndRoundSingleRelationBean(_std);
        buildEffectEndRoundSingleStatusBean(_std);
        buildEffectEndRoundStatusBean(_std);
        buildEffectEndRoundStatusRelationBean(_std);
        buildEffectEndRoundTeamBean(_std);
        buildEndRoundBean(_std);
    }
    private static void buildEffectEndRoundBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(MOVE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(ABILITY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(ITEM,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(STATUS,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(END_ROUND_RANK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(END_ROUND_HTML,_std.getAliasString(),false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_BEAN, type_);
    }
    private static void buildEffectEndRoundFoeBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_FOE_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(INFLICTED_RATE_HP_TARGET,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_FOE_BEAN, type_);
    }
    private static void buildEffectEndRoundGlobalBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_GLOBAL_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DAMAGE_END_ROUND,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(HEALING_END_ROUND_GROUND,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(HEALING_END_ROUND,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(PUTTING_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IMMUNE_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_GLOBAL_BEAN, type_);
    }
    private static void buildEffectEndRoundIndividualBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DELETE_ALL_STATUS,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(RECOIL_DAMAGE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(HEAL_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(USER_STATUS_END_ROUND,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MULT_DAMAGE_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(HEAL_HP_BY_OWNER_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_USER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_USER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_DAMAGE_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_DAMAGE_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_TYPE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN, type_);
    }
    private static void buildEffectEndRoundMultiRelationBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DAMAGE_BY_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_DAMAGE_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_DAMAGE_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN, type_);
    }
    private static void buildEffectEndRoundPositionRelationBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(HEAL_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN, type_);
    }
    private static void buildEffectEndRoundPositionTargetBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MOVES_SAME_CATEGORY,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_TARGET_RELATION_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_TARGET_RELATION_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN, type_);
    }
    private static void buildEffectEndRoundSingleRelationBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(LAW_FOR_ENABLING_EFFECT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN, type_);
    }
    private static void buildEffectEndRoundSingleStatusBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_SINGLE_STATUS_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_BEAN, MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_SINGLE_STATUS_BEAN, type_);
    }
    private static void buildEffectEndRoundStatusBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_STATUS_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(INFLICTED_RATE_HP_TARGET,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(END_ROUND_STATUS_HTML,_std.getAliasString(),false,false,type_));
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_STATUS_BEAN, type_);
    }
    private static void buildEffectEndRoundStatusRelationBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(THIEVED_HP_RATE_TARGET_TO_USER,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN, type_);
    }
    private static void buildEffectEndRoundTeamBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_TEAM_BEAN, fields_, constructors_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DELETE_ALL_STATUS,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(DELETE_ALL_STATUS_ALLY,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().addEntry(TYPE_EFFECT_END_ROUND_TEAM_BEAN, type_);
    }
    private static void buildEndRoundBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_END_ROUND_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_EVTS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_PAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_END_ROUND_BEAN, type_);
    }
    public static ResultErrorStd getResultEffectEndRoundBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundBean instance_ = (EffectEndRoundBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(instance_.getIndex()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATUS)) {
            res_.setResult(new StringStruct(instance_.getStatus()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_ROUND_RANK)) {
            res_.setResult(new IntStruct(instance_.getEndRoundRank()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REASONS_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getReasonsEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_VARS_FAIL_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getMapVarsFailEndRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_ROUND_HTML)) {
            res_.setResult(new StringStruct(EffectEndRoundBean.END_ROUND_HTML));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEffectEndRoundFoeBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundFoeBean instance_ = (EffectEndRoundFoeBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INFLICTED_RATE_HP_TARGET)) {
            res_.setResult(new RateStruct(instance_.getInflictedRateHpTarget(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundGlobalBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundGlobalBean instance_ = (EffectEndRoundGlobalBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DAMAGE_END_ROUND)) {
            res_.setResult(new RateStruct(instance_.getDamageEndRound(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEALING_END_ROUND_GROUND)) {
            res_.setResult(new RateStruct(instance_.getHealingEndRoundGround(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEALING_END_ROUND)) {
            res_.setResult(new RateStruct(instance_.getHealingEndRound(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PUTTING_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getPuttingKo()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMMUNE_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getImmuneTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundIndividualBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundIndividualBean instance_ = (EffectEndRoundIndividualBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DELETE_ALL_STATUS)) {
            res_.setResult(new RateStruct(instance_.getDeleteAllStatus(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RECOIL_DAMAGE)) {
            res_.setResult(new RateStruct(instance_.getRecoilDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEAL_HP)) {
            res_.setResult(new RateStruct(instance_.getHealHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,USER_STATUS_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.getUserStatusEndRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MULT_DAMAGE_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getMultDamageStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEAL_HP_BY_OWNER_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getHealHpByOwnerTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundMultiRelationBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundMultiRelationBean instance_ = (EffectEndRoundMultiRelationBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DAMAGE_BY_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getDamageByStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundPositionRelationBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundPositionRelationBean instance_ = (EffectEndRoundPositionRelationBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,HEAL_HP)) {
            res_.setResult(new RateStruct(instance_.getHealHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundSingleRelationBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundSingleRelationBean instance_ = (EffectEndRoundSingleRelationBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS)) {
            res_.setResult(new DefaultStruct(instance_.getRateDamageFunctionOfNbRounds(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAW_FOR_ENABLING_EFFECT)) {
            res_.setResult(new DefaultStruct(instance_.getLawForEnablingEffect(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundStatusBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundStatusBean instance_ = (EffectEndRoundStatusBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INFLICTED_RATE_HP_TARGET)) {
            res_.setResult(new RateStruct(instance_.getInflictedRateHpTarget(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_ROUND_STATUS_HTML)) {
            res_.setResult(new StringStruct(EffectEndRoundStatusBean.END_ROUND_STATUS_HTML));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundStatusRelationBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundStatusRelationBean instance_ = (EffectEndRoundStatusRelationBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,THIEVED_HP_RATE_TARGET_TO_USER)) {
            res_.setResult(new RateStruct(instance_.getThievedHpRateTargetToUser(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundStatusBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundTeamBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundTeamBean instance_ = (EffectEndRoundTeamBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DELETE_ALL_STATUS)) {
            res_.setResult(new RateStruct(instance_.getDeleteAllStatus(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DELETE_ALL_STATUS_ALLY)) {
            res_.setResult(new RateStruct(instance_.getDeleteAllStatusAlly(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansEndroundStd.getResultEffectEndRoundBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd setResultEffectEndRoundBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundBean instance_ = (EffectEndRoundBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,INDEX)) {
            instance_.setIndex((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectEndRoundBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectEndRoundBean instance_ = (EffectEndRoundBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickMoves((Integer)_args[0],(Integer)_args[1])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrMoves((Integer)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectEndRoundIndividualBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectEndRoundIndividualBean instance_ = (EffectEndRoundIndividualBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_USER_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickUserStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_USER_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrUserStatus()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_DAMAGE_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickDamageStatus((Integer)_args[0],(Integer)_args[1])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_DAMAGE_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrDamageStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_TYPE)) {
            res_.setResult(BooleanStruct.of(instance_.isType((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrType((Integer)_args[0])));
            return res_;
        }
        return AikiBeansEndroundStd.invokeMethodEffectEndRoundBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectEndRoundMultiRelationBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectEndRoundMultiRelationBean instance_ = (EffectEndRoundMultiRelationBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_DAMAGE_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickDamageStatus((Integer)_args[0],(Integer)_args[1])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_DAMAGE_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrDamageStatus((Integer)_args[0])));
            return res_;
        }
        return AikiBeansEndroundStd.invokeMethodEffectEndRoundBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectEndRoundPositionTargetBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        EffectEndRoundPositionTargetBean instance_ = (EffectEndRoundPositionTargetBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MOVES_SAME_CATEGORY)) {
            res_.setResult(new DefaultStruct(instance_.getMovesSameCategory(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_TARGET_RELATION_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickTargetRelationMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_TARGET_RELATION_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrTargetRelationMove((Integer)_args[0])));
            return res_;
        }
        return AikiBeansEndroundStd.invokeMethodEffectEndRoundBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEndRoundBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        EndRoundBean instance_ = (EndRoundBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_EVTS)) {
            res_.setResult(new DefaultStruct(instance_.getEvts(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PAGE)) {
            res_.setResult(new StringStruct(instance_.getPage((Integer)_args[0])));
            return res_;
        }
        return res_;
    }
}
