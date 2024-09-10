package aiki.beans.status;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansStatusStd{
    public static final String BEAN_STATUS="status";
    public static final String BEAN_STATUS_SET="status_set";
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
    private AikiBeansStatusStd(){}
    public static void build(PokemonStandards _std) {
        buildStatusBean(_std);
        buildStatusSetBean(_std);
    }
    private static void buildStatusBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING, new StatusBeanDisplayNameGet(),null));
        fields_.add(new StandardField(ANIM_STATUS,BeanNatCommonLgNames.STRING, new StatusBeanAnimStatusGet(),null));
        fields_.add(new StandardField(END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanEndRoundGet(),null));
        fields_.add(new StandardField(END_ROUND_RANK, BeanNatCommonLgNames.PRIM_INTEGER, new StatusBeanEndRoundRankGet(),null));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatCommonLgNames.TYPE_LIST, new StatusBeanReasonsEndRoundGet(),null));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatCommonLgNames.TYPE_MAP, new StatusBeanMapVarsFailEndRoundGet(),null));
        fields_.add(new StandardField(SINGLE_STATUS,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanSingleStatusGet(),null));
        fields_.add(new StandardField(INCREMENTING_DAMAGE_BY_ROUNDS,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanIncrementingDamageByRoundsGet(),null));
        fields_.add(new StandardField(CATCHING_RATE,BeanNatCommonLgNames.TYPE_RATE, new StatusBeanCatchingRateGet(),null));
        fields_.add(new StandardField(DISABLED_EFF_IF_SWITCH,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanDisabledEffIfSwitchGet(),null));
        fields_.add(new StandardField(INCREMENT_END_ROUND, BeanNatCommonLgNames.PRIM_INTEGER, new StatusBeanIncrementEndRoundGet(),null));
        fields_.add(new StandardField(INCREMENTING_END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanIncrementingEndRoundGet(),null));
        fields_.add(new StandardField(MULT_STAT, BeanNatCommonLgNames.TYPE_MAP, new StatusBeanMultStatGet(),null));
        fields_.add(new StandardField(REASONS, BeanNatCommonLgNames.TYPE_LIST, new StatusBeanReasonsGet(),null));
        fields_.add(new StandardField(MAP_VARS_FAIL, BeanNatCommonLgNames.TYPE_MAP, new StatusBeanMapVarsFailGet(),null));
        fields_.add(new StandardField(RATE_FOR_USING_A_MOVE,BeanNatCommonLgNames.TYPE_RATE, new StatusBeanRateForUsingAMoveGet(),null));
        fields_.add(new StandardField(NOT_ATTACK,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanNotAttackGet(),null));
        fields_.add(new StandardField(RATE_FOR_USING_A_MOVE_IF_FOE,BeanNatCommonLgNames.TYPE_RATE, new StatusBeanRateForUsingAMoveIfFoeGet(),null));
        fields_.add(new StandardField(NOT_ATTACK_FOE,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanNotAttackFoeGet(),null));
        fields_.add(new StandardField(RATE_FOR_FULL_HEAL_IF_MOVE,BeanNatCommonLgNames.TYPE_RATE, new StatusBeanRateForFullHealIfMoveGet(),null));
        fields_.add(new StandardField(LAW_FOR_USING_A_MOVE_NB_ROUND, BeanNatCommonLgNames.TYPE_MAP, new StatusBeanLawForUsingAMoveNbRoundGet(),null));
        fields_.add(new StandardField(POWER,BeanNatCommonLgNames.TYPE_RATE, new StatusBeanPowerGet(),null));
        fields_.add(new StandardField(ATTACK,BeanNatCommonLgNames.STRING, new StatusBeanAttackGet(),null));
        fields_.add(new StandardField(DEFENSE,BeanNatCommonLgNames.STRING, new StatusBeanDefenseGet(),null));
        fields_.add(new StandardField(EFFECTS_PARTNER, BeanNatCommonLgNames.TYPE_LIST, new StatusBeanEffectsPartnerGet(),null));
        methods_.add( new SpecNatMethod(CLICK_INDEX,BeanNatCommonLgNames.STRING, new StatusBeanClickIndex()));
        methods_.add( new SpecNatMethod(INCREMENT_END_ROUND_INT,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanIncrementEndRoundInt()));
        methods_.add( new SpecNatMethod(IS_SINGLE,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatusBeanIsSingle()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STAT,BeanNatCommonLgNames.STRING, new StatusBeanGetTrMultStat()));
        methods_.add( new SpecNatMethod(GET_EFFECT_PARTNER,PokemonStandards.TYPE_EFFECT_PARTNER_STATUS, new StatusBeanGetEffectPartner()));
        _std.getStds().addEntry(TYPE_STATUS_BEAN, type_);
    }
    private static void buildStatusSetBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(TYPED_STATUS,BeanNatCommonLgNames.STRING, new StatusSetBeanTypedStatusGet(),new StatusSetBeanTypedStatusSet()));
        fields_.add(new StandardField(SORTED_STATUS, BeanNatCommonLgNames.TYPE_LIST, new StatusSetBeanSortedStatusGet(),null));
        methods_.add( new SpecNatMethod(SEARCH,BeanNatCommonLgNames.STRING, new StatusSetBeanSearch()));
        methods_.add( new SpecNatMethod(CLICK_STATUS,BeanNatCommonLgNames.STRING, new StatusSetBeanClickStatus()));
        methods_.add( new SpecNatMethod(GET_TR_STATUS,BeanNatCommonLgNames.STRING, new StatusSetBeanGetTrStatus()));
        _std.getStds().addEntry(TYPE_STATUS_SET_BEAN, type_);
    }
}
