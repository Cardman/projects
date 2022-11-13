package aiki.beans.endround;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansEndroundStd{
    public static final String PAGE_EFF = "web/html/endround/eff.html";
    public static final String PAGE_END_ROUND = "web/html/endround/endround.html";
    public static final String PAGE_FOE = "web/html/endround/foe.html";
    public static final String PAGE_GLOBAL = "web/html/endround/global.html";
    public static final String PAGE_INDIVIDUAL = "web/html/endround/individual.html";
    public static final String PAGE_MULTIRELATION = "web/html/endround/multirelation.html";
    public static final String PAGE_POSITIONRELATION = "web/html/endround/positionrelation.html";
    public static final String PAGE_POSITIONTARGET = "web/html/endround/positiontarget.html";
    public static final String PAGE_SINGLERELATION = "web/html/endround/singlerelation.html";
    public static final String PAGE_STATUS = "web/html/endround/status.html";
    public static final String PAGE_STATUSRELATION = "web/html/endround/statusrelation.html";
    public static final String PAGE_TEAM = "web/html/endround/team.html";
    public static final String BEAN_END="end";
    public static final String BEAN_ENDROUND="endround";
    public static final String BEAN_END_FOE="end_foe";
    public static final String BEAN_END_GLOBAL="end_global";
    public static final String BEAN_END_INDIVIDUAL="end_individual";
    public static final String BEAN_END_MULTIRELATION="end_multirelation";
    public static final String BEAN_END_POSITIONRELATION="end_positionrelation";
    public static final String BEAN_END_POSITIONTARGET="end_positiontarget";
    public static final String BEAN_END_SINGLERELATION="end_singlerelation";
    public static final String BEAN_END_STATUS="end_status";
    public static final String BEAN_END_STATUSRELATION="end_statusrelation";
    public static final String BEAN_END_TEAM="end_team";
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
    private AikiBeansEndroundStd(){}
    public static void build(PokemonStandards _std) {
        buildEffectEndRoundBean(_std);
        buildEffectEndRoundFoeBean(_std);
        buildEffectEndRoundGlobalBean(_std);
        buildEffectEndRoundIndividualBean(_std);
        buildEffectEndRoundMultiRelationBean(_std);
        buildEffectEndRoundPositionRelationBean(_std);
        buildEffectEndRoundPositionTargetBean(_std);
        buildEffectEndRoundSingleRelationBean(_std);
        buildEffectEndRoundStatusBean(_std);
        buildEffectEndRoundStatusRelationBean(_std);
        buildEffectEndRoundTeamBean(_std);
        buildEndRoundBean(_std);
    }
    private static void buildEffectEndRoundBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(MOVE,BeanNatCommonLgNames.STRING,false,false,new EffectEndRoundBeanMoveGet(),null));
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new EffectEndRoundBeanIndexGet(),new EffectEndRoundBeanIndexSet()));
        fields_.add(new StandardField(ABILITY,BeanNatCommonLgNames.STRING,false,false,new EffectEndRoundBeanAbilityGet(),null));
        fields_.add(new StandardField(ITEM,BeanNatCommonLgNames.STRING,false,false,new EffectEndRoundBeanItemGet(),null));
        fields_.add(new StandardField(STATUS,BeanNatCommonLgNames.STRING,false,false,new EffectEndRoundBeanStatusGet(),null));
        fields_.add(new StandardField(MOVES, BeanNatCommonLgNames.TYPE_LIST,false,false,new EffectEndRoundBeanMovesGet(),null));
        fields_.add(new StandardField(END_ROUND_RANK, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new EffectEndRoundBeanEndRoundRankGet(),null));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatCommonLgNames.TYPE_LIST,false,false,new EffectEndRoundBeanReasonsEndRoundGet(),null));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatCommonLgNames.TYPE_MAP,false,false,new EffectEndRoundBeanMapVarsFailEndRoundGet(),null));
        fields_.add(new StandardField(END_ROUND_HTML,BeanNatCommonLgNames.STRING,false,false,new CstNatCaller(PAGE_EFF),null));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundBeanClickMove()));
        methods_.add( new SpecNatMethod(CLICK_ABILITY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundBeanClickAbility()));
        methods_.add( new SpecNatMethod(CLICK_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundBeanClickItem()));
        methods_.add( new SpecNatMethod(CLICK_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundBeanClickStatus()));
        methods_.add( new SpecNatMethod(CLICK_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundBeanClickMoves()));
        methods_.add( new SpecNatMethod(GET_TR_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundBeanGetTrMoves()));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_BEAN, type_);
    }
    private static void buildEffectEndRoundFoeBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_FOE_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(INFLICTED_RATE_HP_TARGET,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundFoeBeanInflictedRateHpTargetGet(),null));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_FOE_BEAN, type_);
    }
    private static void buildEffectEndRoundGlobalBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_GLOBAL_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(DAMAGE_END_ROUND,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundGlobalBeanDamageEndRoundGet(),null));
        fields_.add(new StandardField(HEALING_END_ROUND_GROUND,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundGlobalBeanHealingEndRoundGroundGet(),null));
        fields_.add(new StandardField(HEALING_END_ROUND,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundGlobalBeanHealingEndRoundGet(),null));
        fields_.add(new StandardField(PUTTING_KO,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new EffectEndRoundGlobalBeanPuttingKoGet(),null));
        fields_.add(new StandardField(IMMUNE_TYPES, BeanNatCommonLgNames.TYPE_LIST,false,false,new EffectEndRoundGlobalBeanImmuneTypesGet(),null));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_GLOBAL_BEAN, type_);
    }
    private static void buildEffectEndRoundIndividualBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(DELETE_ALL_STATUS,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundIndividualBeanDeleteAllStatusGet(),null));
        fields_.add(new StandardField(RECOIL_DAMAGE,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundIndividualBeanRecoilDamageGet(),null));
        fields_.add(new StandardField(HEAL_HP,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundIndividualBeanHealHpGet(),null));
        fields_.add(new StandardField(USER_STATUS_END_ROUND,BeanNatCommonLgNames.STRING,false,false,new EffectEndRoundIndividualBeanUserStatusEndRoundGet(),null));
        fields_.add(new StandardField(MULT_DAMAGE_STATUS, BeanNatCommonLgNames.TYPE_MAP,false,false,new EffectEndRoundIndividualBeanMultDamageStatusGet(),null));
        fields_.add(new StandardField(HEAL_HP_BY_OWNER_TYPES, BeanNatCommonLgNames.TYPE_MAP,false,false,new EffectEndRoundIndividualBeanHealHpByOwnerTypesGet(),null));
        methods_.add( new SpecNatMethod(CLICK_USER_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundIndividualBeanClickUserStatus()));
        methods_.add( new SpecNatMethod(GET_TR_USER_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundIndividualBeanGetTrUserStatus()));
        methods_.add( new SpecNatMethod(CLICK_DAMAGE_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundIndividualBeanClickDamageStatus()));
        methods_.add( new SpecNatMethod(GET_TR_DAMAGE_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundIndividualBeanGetTrDamageStatus()));
        methods_.add( new SpecNatMethod(IS_TYPE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new EffectEndRoundIndividualBeanIsType()));
        methods_.add( new SpecNatMethod(GET_TR_TYPE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundIndividualBeanGetTrType()));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN, type_);
    }
    private static void buildEffectEndRoundMultiRelationBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(DAMAGE_BY_STATUS, BeanNatCommonLgNames.TYPE_MAP,false,false,new EffectEndRoundMultiRelationBeanDamageByStatusGet(),null));
        methods_.add( new SpecNatMethod(CLICK_DAMAGE_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundMultiRelationBeanClickDamageStatus()));
        methods_.add( new SpecNatMethod(GET_TR_DAMAGE_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundMultiRelationBeanGetTrDamageStatus()));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN, type_);
    }
    private static void buildEffectEndRoundPositionRelationBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(HEAL_HP,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundPositionRelationBeanHealHpGet(),null));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN, type_);
    }
    private static void buildEffectEndRoundPositionTargetBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        methods_.add( new SpecNatMethod(GET_MOVES_SAME_CATEGORY, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new EffectEndRoundPositionTargetBeanGetMovesSameCategory()));
        methods_.add( new SpecNatMethod(CLICK_TARGET_RELATION_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundPositionTargetBeanClickTargetRelationMove()));
        methods_.add( new SpecNatMethod(GET_TR_TARGET_RELATION_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EffectEndRoundPositionTargetBeanGetTrTargetRelationMove()));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN, type_);
    }
    private static void buildEffectEndRoundSingleRelationBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS, BeanNatCommonLgNames.TYPE_MAP,false,false,new EffectEndRoundSingleRelationBeanRateDamageFunctionOfNbRoundsGet(),null));
        fields_.add(new StandardField(LAW_FOR_ENABLING_EFFECT, BeanNatCommonLgNames.TYPE_MAP,false,false,new EffectEndRoundSingleRelationBeanLawForEnablingEffectGet(),null));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN, type_);
    }

    private static void buildEffectEndRoundStatusBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_STATUS_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(INFLICTED_RATE_HP_TARGET,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundStatusBeanInflictedRateHpTargetGet(),null));
        fields_.add(new StandardField(END_ROUND_STATUS_HTML,BeanNatCommonLgNames.STRING,false,false,new CstNatCaller(EffectEndRoundStatusBean.END_ROUND_STATUS_HTML),null));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_STATUS_BEAN, type_);
    }
    private static void buildEffectEndRoundStatusRelationBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_BEAN);
        fields_.add(new StandardField(THIEVED_HP_RATE_TARGET_TO_USER,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundStatusRelationBeanThievedHpRateTargetToUserGet(),null));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN, type_);
    }
    private static void buildEffectEndRoundTeamBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_END_ROUND_TEAM_BEAN, fields_, methods_, AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
        fields_.add(new StandardField(DELETE_ALL_STATUS,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundTeamBeanDeleteAllStatusGet(),null));
        fields_.add(new StandardField(DELETE_ALL_STATUS_ALLY,BeanNatCommonLgNames.TYPE_RATE,false,false,new EffectEndRoundTeamBeanDeleteAllStatusAllyGet(),null));
        _std.getStds().addEntry(TYPE_EFFECT_END_ROUND_TEAM_BEAN, type_);
    }
    private static void buildEndRoundBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_END_ROUND_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        methods_.add( new SpecNatMethod(GET_EVTS, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new EndRoundBeanGetEvts()));
        methods_.add( new SpecNatMethod(GET_PAGE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new EndRoundBeanGetPage()));
        _std.getStds().addEntry(TYPE_END_ROUND_BEAN, type_);
    }
}
