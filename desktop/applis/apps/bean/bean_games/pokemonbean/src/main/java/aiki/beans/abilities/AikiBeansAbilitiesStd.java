package aiki.beans.abilities;
import aiki.beans.AikiBeansStd;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
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
import code.expressionlanguage.structs.NullStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;

public final class AikiBeansAbilitiesStd {
    public static final String TYPE_ABILITIES_BEAN = "aiki.beans.abilities.AbilitiesBean";
    public static final String TYPE_ABILITY_BEAN = "aiki.beans.abilities.AbilityBean";

    private static final String SEARCH = "search";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_TR_ABILITY = "getTrAbility";
    private static final String CLICK_INDEX = "clickIndex";
    private static final String GET_EFFECT_SENDING = "getEffectSending";
    private static final String CLICK_BREAK_PROTECTION_MOVES = "clickBreakProtectionMoves";
    private static final String GET_TR_BREAK_PROTECTION_MOVES = "getTrBreakProtectionMoves";
    private static final String IS_CHGT_TYPE_BY_WEATHER = "isChgtTypeByWeather";
    private static final String CLICK_CHGT_TYPE_BY_WEATHER_KEY = "clickChgtTypeByWeatherKey";
    private static final String GET_TR_CHGT_TYPE_BY_WEATHER_KEY = "getTrChgtTypeByWeatherKey";
    private static final String GET_TR_CHGT_TYPE_BY_WEATHER_VALUE = "getTrChgtTypeByWeatherValue";
    private static final String CLICK_IMMU_RECHARGE_ROUND_MOVES = "clickImmuRechargeRoundMoves";
    private static final String GET_TR_IMMU_RECHARGE_ROUND_MOVES = "getTrImmuRechargeRoundMoves";
    private static final String CLICK_REVERSE_POWER_TYPES_ABILITIES = "clickReversePowerTypesAbilities";
    private static final String GET_TR_REVERSE_POWER_TYPES_ABILITIES = "getTrReversePowerTypesAbilities";
    private static final String DECREASE_NEC_STEPS_HATCH_INT = "decreaseNecStepsHatchInt";
    private static final String NB_USED_PP_INT = "nbUsedPpInt";
    private static final String CLICK_IMMU_MOVE = "clickImmuMove";
    private static final String GET_TR_IMMU_MOVE = "getTrImmuMove";
    private static final String CLICK_IMMU_ALLY_FROM_MOVES = "clickImmuAllyFromMoves";
    private static final String GET_TR_IMMU_ALLY_FROM_MOVES = "getTrImmuAllyFromMoves";
    private static final String CLICK_WEATHER = "clickWeather";
    private static final String GET_TR_WEATHER = "getTrWeather";
    private static final String CLICK_IGN_ABILITY = "clickIgnAbility";
    private static final String GET_TR_IGN_ABILITY = "getTrIgnAbility";
    private static final String CLICK_IGN_FOE_TEAM_MOVE = "clickIgnFoeTeamMove";
    private static final String GET_TR_IGN_FOE_TEAM_MOVE = "getTrIgnFoeTeamMove";
    private static final String CLICK_IMMU_ABILITY = "clickImmuAbility";
    private static final String GET_TR_IMMU_ABILITY = "getTrImmuAbility";
    private static final String CLICK_IMMU_STATUS_BEGIN_ROUND = "clickImmuStatusBeginRound";
    private static final String GET_TR_IMMU_STATUS_BEGIN_ROUND = "getTrImmuStatusBeginRound";
    private static final String GET_TR_CHANGING_BOOST_TYPES_OLD = "getTrChangingBoostTypesOld";
    private static final String GET_TR_CHANGING_BOOST_TYPES_NEW = "getTrChangingBoostTypesNew";
    private static final String IS_HEAL_HP_BY_WEATHER = "isHealHpByWeather";
    private static final String CLICK_HEAL_HP_BY_WEATHER_KEY = "clickHealHpByWeatherKey";
    private static final String GET_TR_HEAL_HP_BY_WEATHER_KEY = "getTrHealHpByWeatherKey";
    private static final String IS_HEAL_HP_BY_TYPE_IF_WEATHER = "isHealHpByTypeIfWeather";
    private static final String CLICK_HEAL_HP_BY_TYPE_IF_WEATHER_KEY = "clickHealHpByTypeIfWeatherKey";
    private static final String GET_TR_HEAL_HP_BY_TYPE_IF_WEATHER_KEY = "getTrHealHpByTypeIfWeatherKey";
    private static final String GET_TR_HEAL_HP_BY_TYPE_IF_WEATHER_KEY_SEC = "getTrHealHpByTypeIfWeatherKeySec";
    private static final String GET_TR_IMMU_LOW_STAT = "getTrImmuLowStat";
    private static final String CLICK_IMMU_LOW_STAT_IF_STATUS_KEY = "clickImmuLowStatIfStatusKey";
    private static final String GET_TR_IMMU_LOW_STAT_IF_STATUS_KEY = "getTrImmuLowStatIfStatusKey";
    private static final String GET_TR_IMMU_LOW_STAT_IF_STATUS_VALUE = "getTrImmuLowStatIfStatusValue";
    private static final String GET_TR_IMMU_LOW_STATIS_TYPES = "getTrImmuLowStatisTypes";
    private static final String GET_TR_IMMU_LOW_STATIS_VALUE = "getTrImmuLowStatisValue";
    private static final String GET_TR_MAX_STATISTICS_IF_CH = "getTrMaxStatisticsIfCh";
    private static final String IS_STATUS = "isStatus";
    private static final String CLICK_SINGLE_STATUS = "clickSingleStatus";
    private static final String GET_TR_SINGLE_STATUS = "getTrSingleStatus";
    private static final String IS_MOVE_BY_WEATHER = "isMoveByWeather";
    private static final String CLICK_IMMU_MOVE_BY_WEATHER = "clickImmuMoveByWeather";
    private static final String GET_TR_IMMU_MOVE_BY_WEATHER = "getTrImmuMoveByWeather";
    private static final String GET_TR_IMMU_TYPE_BY_WEATHER = "getTrImmuTypeByWeather";
    private static final String IS_MOVE_BY_STATUS = "isMoveByStatus";
    private static final String CLICK_IMMU_STATUS_WEATHER = "clickImmuStatusWeather";
    private static final String GET_TR_IMMU_STATUS_WEATHER = "getTrImmuStatusWeather";
    private static final String CLICK_IMMU_STATUS = "clickImmuStatus";
    private static final String GET_TR_IMMU_STATUS = "getTrImmuStatus";
    private static final String GET_TR_IMMU_STATUS_TYPES = "getTrImmuStatusTypes";
    private static final String CLICK_IMMU_STATUS_TYPES = "clickImmuStatusTypes";
    private static final String GET_TR_IMMU_STATUS_VALUE = "getTrImmuStatusValue";
    private static final String CLICK_DIVIDE_STATUS_ROUND_KEY = "clickDivideStatusRoundKey";
    private static final String GET_TR_DIVIDE_STATUS_ROUND_KEY = "getTrDivideStatusRoundKey";
    private static final String CLICK_FORWARD_STATUS_KEY = "clickForwardStatusKey";
    private static final String GET_TR_FORWARD_STATUS_KEY = "getTrForwardStatusKey";
    private static final String CLICK_FORWARD_STATUS_VALUE = "clickForwardStatusValue";
    private static final String GET_TR_FORWARD_STATUS_VALUE = "getTrForwardStatusValue";
    private static final String GET_TR_BREAK_FOE_IMMUNE_KEY = "getTrBreakFoeImmuneKey";
    private static final String GET_TR_BREAK_FOE_IMMUNE_VALUE = "getTrBreakFoeImmuneValue";
    private static final String GET_TR_MULT_STAT = "getTrMultStat";
    private static final String GET_TR_MULT_STAT_IF_DAMAGE_CAT_KEY = "getTrMultStatIfDamageCatKey";
    private static final String GET_TR_MULT_STAT_IF_DAMAGE_CAT_KEY_SEC = "getTrMultStatIfDamageCatKeySec";
    private static final String GET_TR_MULT_STAT_IF_DAMGE_TYPE = "getTrMultStatIfDamgeType";
    private static final String GET_TR_MULT_STAT_IF_DAMGE_TYPE_SEC = "getTrMultStatIfDamgeTypeSec";
    private static final String GET_TR_MULT_STAT_IF_CAT_KEY = "getTrMultStatIfCatKey";
    private static final String GET_TR_MULT_STAT_IF_CAT_KEY_SEC = "getTrMultStatIfCatKeySec";
    private static final String GET_TR_MULT_STAT_IF_STATUT_RANK = "getTrMultStatIfStatutRank";
    private static final String CLICK_MULT_STAT_IF_STATUT_RANK_SEC = "clickMultStatIfStatutRankSec";
    private static final String GET_TR_MULT_STAT_IF_STATUT_RANK_SEC = "getTrMultStatIfStatutRankSec";
    private static final String GET_TR_BONUS_STAT_RANK = "getTrBonusStatRank";
    private static final String GET_TR_BOOST_STAT_RANK_END_ROUND = "getTrBoostStatRankEndRound";
    private static final String GET_TR_BOOST_STAT_RANK_PROTECTED = "getTrBoostStatRankProtected";
    private static final String GET_TR_LOW_STAT_FOE_HIT = "getTrLowStatFoeHit";
    private static final String GET_TR_MULT_STAT_IF_KO_FOE = "getTrMultStatIfKoFoe";
    private static final String GET_TR_MULT_STAT_IF_LOW_STAT = "getTrMultStatIfLowStat";
    private static final String GET_TR_MULT_STAT_ALLY = "getTrMultStatAlly";
    private static final String GET_TR_INCREASED_PRIO = "getTrIncreasedPrio";
    private static final String GET_TR_INCREASED_PRIO_TYPES = "getTrIncreasedPrioTypes";
    private static final String GET_TR_MULT_DAMAGE_FOE = "getTrMultDamageFoe";
    private static final String GET_TR_MULT_POWER_MOVES_TYPES_GLOBAL_KEY = "getTrMultPowerMovesTypesGlobalKey";
    private static final String CLICK_FAIL_STATUS = "clickFailStatus";
    private static final String GET_TR_FAIL_STATUS = "getTrFailStatus";
    private static final String CLICK_POKEMON = "clickPokemon";
    private static final String GET_TR_POKEMON = "getTrPokemon";
    private static final String TYPED_ABILITY = "typedAbility";
    private static final String SORTED_ABILITIES = "sortedAbilities";
    private static final String DISPLAY_NAME = "displayName";
    private static final String END_ROUND = "endRound";
    private static final String END_ROUND_RANK = "endRoundRank";
    private static final String REASONS_END_ROUND = "reasonsEndRound";
    private static final String MAP_VARS_FAIL_END_ROUND = "mapVarsFailEndRound";
    private static final String SENDING = "sending";
    private static final String EFFECT_SEND_BEAN = "effectSendBean";
    private static final String ACHIEVED_DISAPPEARED_PK = "achievedDisappearedPk";
    private static final String BREAK_PROTECTION = "breakProtection";
    private static final String BREAK_PROTECTION_MOVES = "breakProtectionMoves";
    private static final String CANCEL_SEC_EFFECT_OTHER = "cancelSecEffectOther";
    private static final String CANCEL_SEC_EFFECT_OWNER = "cancelSecEffectOwner";
    private static final String CHGT_TYPE_BY_DAMAGE = "chgtTypeByDamage";
    private static final String CHGT_TYPE_BY_WEATHER = "chgtTypeByWeather";
    private static final String COPY_MOVES_TYPES = "copyMovesTypes";
    private static final String FORBID_USE_BERRY_AGAINST_FOES = "forbidUseBerryAgainstFoes";
    private static final String GIVE_ITEM_TO_ALLY_HAVING_USED = "giveItemToAllyHavingUsed";
    private static final String HEALED_STATUS_BY_SWITCH = "healedStatusBySwitch";
    private static final String IGN_FOE_STATIS_BOOST = "ignFoeStatisBoost";
    private static final String IMMU_CH = "immuCh";
    private static final String IMMU_DAMAGE_ALLY_MOVES = "immuDamageAllyMoves";
    private static final String IMMU_DAMAGE_RECOIL = "immuDamageRecoil";
    private static final String IMMU_DAMAGE_TRAPPING_MOVES = "immuDamageTrappingMoves";
    private static final String IMMU_RECHARGE_ROUND = "immuRechargeRound";
    private static final String IMMU_RECHARGE_ROUND_MOVES = "immuRechargeRoundMoves";
    private static final String IMMU_SUFFERED_DAMAGE_LOW_EFF = "immuSufferedDamageLowEff";
    private static final String INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING = "inflictingDamageInsteadOfSuffering";
    private static final String MUMY = "mumy";
    private static final String NB_HITS = "nbHits";
    private static final String PLATE = "plate";
    private static final String REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL = "reverseEffectsPowerMovesTypesGlobal";
    private static final String REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL_ABILITIES = "reverseEffectsPowerMovesTypesGlobalAbilities";
    private static final String SLOWING = "slowing";
    private static final String TAKE_ITEM_BY_DAMAGING_MOVE = "takeItemByDamagingMove";
    private static final String HEAL_HP_WHILE_USING_BERRY = "healHpWhileUsingBerry";
    private static final String MAX_HP_FOR_USING_BERRY = "maxHpForUsingBerry";
    private static final String MULT_ALLY_DAMAGE = "multAllyDamage";
    private static final String MULT_DAMAGE_CH = "multDamageCh";
    private static final String MULT_EVT_RATE_CH = "multEvtRateCh";
    private static final String MULT_EVT_RATE_SEC_EFFECT_OWNER = "multEvtRateSecEffectOwner";
    private static final String MULT_STAB = "multStab";
    private static final String MULT_SUFFERED_DAMAGE_SUPER_EFF = "multSufferedDamageSuperEff";
    private static final String MULT_VAR_BOOST = "multVarBoost";
    private static final String RECOIL_DAMAGE_FOE = "recoilDamageFoe";
    private static final String RECOIL_DAMAGE_FOE_BY_KO_OWNER = "recoilDamageFoeByKoOwner";
    private static final String DECREASE_NEC_STEPS_HATCH = "decreaseNecStepsHatch";
    private static final String NB_USED_PP = "nbUsedPp";
    private static final String IMMU_MOVE = "immuMove";
    private static final String IMMU_ALLY_FROM_MOVES = "immuAllyFromMoves";
    private static final String IMMU_WEATHER = "immuWeather";
    private static final String IGN_ABILITY = "ignAbility";
    private static final String IGN_FOE_TEAM_MOVE = "ignFoeTeamMove";
    private static final String IMMU_ABILITY = "immuAbility";
    private static final String IMMU_STATUS_BEGIN_ROUND = "immuStatusBeginRound";
    private static final String TYPE_FOR_MOVES = "typeForMoves";
    private static final String CHANGING_BOOST_TYPES = "changingBoostTypes";
    private static final String MULT_POWER = "multPower";
    private static final String MULT_DAMAGE = "multDamage";
    private static final String HEAL_HP_BY_WEATHER = "healHpByWeather";
    private static final String HEAL_HP_BY_TYPE_IF_WEATHER = "healHpByTypeIfWeather";
    private static final String IMMU_LOW_STAT = "immuLowStat";
    private static final String IMMU_LOW_STAT_IF_STATUS = "immuLowStatIfStatus";
    private static final String IMMU_LOW_STATIS_TYPES = "immuLowStatisTypes";
    private static final String MAX_STATISTICS_IF_CH = "maxStatisticsIfCh";
    private static final String SINGLE_STATUS = "singleStatus";
    private static final String IMMU_MOVE_TYPES_BY_WEATHER = "immuMoveTypesByWeather";
    private static final String IMMU_STATUS = "immuStatus";
    private static final String IMMU_STATUS_TYPES = "immuStatusTypes";
    private static final String DIVIDE_STATUS_ROUND = "divideStatusRound";
    private static final String FORWARD_STATUS = "forwardStatus";
    private static final String BREAK_FOE_IMMUNE = "breakFoeImmune";
    private static final String DEF_EFF = "defEff";
    private static final String MULT_STAT = "multStat";
    private static final String MULT_STAT_IF_DAMAGE_CAT = "multStatIfDamageCat";
    private static final String MULT_STAT_IF_DAMGE_TYPE = "multStatIfDamgeType";
    private static final String MULT_STAT_IF_CAT = "multStatIfCat";
    private static final String MULT_STAT_IF_STATUT_RANK = "multStatIfStatutRank";
    private static final String BONUS_STAT_RANK = "bonusStatRank";
    private static final String BOOST_STAT_RANK_END_ROUND = "boostStatRankEndRound";
    private static final String BOOST_STAT_RANK_PROTECTED = "boostStatRankProtected";
    private static final String LOW_STAT_FOE_HIT = "lowStatFoeHit";
    private static final String MULT_STAT_IF_KO_FOE = "multStatIfKoFoe";
    private static final String MULT_STAT_IF_LOW_STAT = "multStatIfLowStat";
    private static final String MULT_STAT_ALLY = "multStatAlly";
    private static final String INCREASED_PRIO = "increasedPrio";
    private static final String INCREASED_PRIO_TYPES = "increasedPrioTypes";
    private static final String MULT_DAMAGE_FOE = "multDamageFoe";
    private static final String MULT_POWER_MOVES_TYPES_GLOBAL = "multPowerMovesTypesGlobal";
    private static final String FAIL_STATUS = "failStatus";
    private static final String MAP_VARS = "mapVars";
    private static final String HEALED_HP_RATE_BY_SWITCH = "healedHpRateBySwitch";
    private static final String POKEMON = "pokemon";

    public static void build(PokemonStandards _std) {
        buildAbilitiesBean(_std);
        buildAbilityBean(_std);
    }
    private static void buildAbilitiesBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_ABILITIES_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(TYPED_ABILITY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(SORTED_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_ABILITIES_BEAN, type_);
    }
    private static void buildAbilityBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_ABILITY_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(END_ROUND_RANK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(SENDING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(EFFECT_SEND_BEAN,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(ACHIEVED_DISAPPEARED_PK,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(BREAK_PROTECTION,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(BREAK_PROTECTION_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(CANCEL_SEC_EFFECT_OTHER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(CANCEL_SEC_EFFECT_OWNER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(CHGT_TYPE_BY_DAMAGE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(CHGT_TYPE_BY_WEATHER, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(COPY_MOVES_TYPES,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(FORBID_USE_BERRY_AGAINST_FOES,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(GIVE_ITEM_TO_ALLY_HAVING_USED,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(HEALED_STATUS_BY_SWITCH,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IGN_FOE_STATIS_BOOST,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IMMU_CH,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IMMU_DAMAGE_ALLY_MOVES,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IMMU_DAMAGE_RECOIL,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IMMU_DAMAGE_TRAPPING_MOVES,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IMMU_RECHARGE_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IMMU_RECHARGE_ROUND_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_SUFFERED_DAMAGE_LOW_EFF,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(MUMY,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(NB_HITS,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(PLATE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SLOWING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(TAKE_ITEM_BY_DAMAGING_MOVE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(HEAL_HP_WHILE_USING_BERRY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MAX_HP_FOR_USING_BERRY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_ALLY_DAMAGE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_DAMAGE_CH,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_EVT_RATE_CH,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_EVT_RATE_SEC_EFFECT_OWNER,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_STAB,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_SUFFERED_DAMAGE_SUPER_EFF,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_VAR_BOOST,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(RECOIL_DAMAGE_FOE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(RECOIL_DAMAGE_FOE_BY_KO_OWNER,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(DECREASE_NEC_STEPS_HATCH,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(NB_USED_PP,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(IMMU_MOVE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_ALLY_FROM_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IGN_ABILITY, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IGN_FOE_TEAM_MOVE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_ABILITY, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_STATUS_BEGIN_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(TYPE_FOR_MOVES,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(CHANGING_BOOST_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_POWER,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MULT_DAMAGE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(HEAL_HP_BY_WEATHER, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(HEAL_HP_BY_TYPE_IF_WEATHER, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(IMMU_LOW_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_LOW_STAT_IF_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_LOW_STATIS_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MAX_STATISTICS_IF_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SINGLE_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(IMMU_MOVE_TYPES_BY_WEATHER, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(IMMU_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(IMMU_STATUS_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(DIVIDE_STATUS_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(FORWARD_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(BREAK_FOE_IMMUNE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(DEF_EFF,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(MULT_STAT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_STAT_IF_DAMAGE_CAT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_STAT_IF_DAMGE_TYPE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_STAT_IF_CAT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_STAT_IF_STATUT_RANK, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(BONUS_STAT_RANK, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(BOOST_STAT_RANK_END_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(BOOST_STAT_RANK_PROTECTED, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(LOW_STAT_FOE_HIT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_STAT_IF_KO_FOE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_STAT_IF_LOW_STAT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_STAT_ALLY, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(INCREASED_PRIO, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(INCREASED_PRIO_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_DAMAGE_FOE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MULT_POWER_MOVES_TYPES_GLOBAL, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(FAIL_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MAP_VARS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(HEALED_HP_RATE_BY_SWITCH,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(POKEMON, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_INDEX,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_EFFECT_SENDING,params_,PokemonStandards.TYPE_EFFECT_WHILE_SENDING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_BREAK_PROTECTION_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BREAK_PROTECTION_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_CHGT_TYPE_BY_WEATHER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_CHGT_TYPE_BY_WEATHER_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CHGT_TYPE_BY_WEATHER_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CHGT_TYPE_BY_WEATHER_VALUE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_RECHARGE_ROUND_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_RECHARGE_ROUND_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_REVERSE_POWER_TYPES_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_REVERSE_POWER_TYPES_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(DECREASE_NEC_STEPS_HATCH_INT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(NB_USED_PP_INT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_ALLY_FROM_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_ALLY_FROM_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IGN_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IGN_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IGN_FOE_TEAM_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IGN_FOE_TEAM_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_STATUS_BEGIN_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS_BEGIN_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CHANGING_BOOST_TYPES_OLD,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CHANGING_BOOST_TYPES_NEW,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_HEAL_HP_BY_WEATHER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_HEAL_HP_BY_WEATHER_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_HEAL_HP_BY_WEATHER_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_HEAL_HP_BY_TYPE_IF_WEATHER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_HEAL_HP_BY_TYPE_IF_WEATHER_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_HEAL_HP_BY_TYPE_IF_WEATHER_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_HEAL_HP_BY_TYPE_IF_WEATHER_KEY_SEC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_LOW_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_LOW_STAT_IF_STATUS_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_LOW_STAT_IF_STATUS_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_LOW_STAT_IF_STATUS_VALUE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_LOW_STATIS_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_LOW_STATIS_VALUE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MAX_STATISTICS_IF_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_STATUS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_SINGLE_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SINGLE_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_MOVE_BY_WEATHER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_MOVE_BY_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_MOVE_BY_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_TYPE_BY_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_MOVE_BY_STATUS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_STATUS_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_STATUS_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS_VALUE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_DIVIDE_STATUS_ROUND_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DIVIDE_STATUS_ROUND_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_FORWARD_STATUS_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_FORWARD_STATUS_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_FORWARD_STATUS_VALUE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_FORWARD_STATUS_VALUE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BREAK_FOE_IMMUNE_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BREAK_FOE_IMMUNE_VALUE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_DAMAGE_CAT_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_DAMAGE_CAT_KEY_SEC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_DAMGE_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_DAMGE_TYPE_SEC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_CAT_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_CAT_KEY_SEC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_STATUT_RANK,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MULT_STAT_IF_STATUT_RANK_SEC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_STATUT_RANK_SEC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BONUS_STAT_RANK,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BOOST_STAT_RANK_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BOOST_STAT_RANK_PROTECTED,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_LOW_STAT_FOE_HIT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_KO_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_LOW_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_ALLY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_INCREASED_PRIO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_INCREASED_PRIO_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_DAMAGE_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_POWER_MOVES_TYPES_GLOBAL_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_FAIL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_FAIL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_ABILITY_BEAN, type_);
    }
    public static ResultErrorStd getResultAbilitiesBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        AbilitiesBean instance_ = (AbilitiesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTypedAbility()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SORTED_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getSortedAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultAbilityBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        AbilityBean instance_ = (AbilityBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getEndRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_ROUND_RANK)) {
            res_.setResult(new IntStruct(instance_.getEndRoundRank()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getReasonsEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_FAIL_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getMapVarsFailEndRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SENDING)) {
            res_.setResult(BooleanStruct.of(instance_.getSending()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EFFECT_SEND_BEAN)) {
            res_.setResult(new StringStruct(AbilityBean.EFFECT_SEND_BEAN));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ACHIEVED_DISAPPEARED_PK)) {
            res_.setResult(BooleanStruct.of(instance_.getAchievedDisappearedPk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BREAK_PROTECTION)) {
            res_.setResult(BooleanStruct.of(instance_.getBreakProtection()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BREAK_PROTECTION_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getBreakProtectionMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_SEC_EFFECT_OTHER)) {
            res_.setResult(BooleanStruct.of(instance_.getCancelSecEffectOther()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_SEC_EFFECT_OWNER)) {
            res_.setResult(BooleanStruct.of(instance_.getCancelSecEffectOwner()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHGT_TYPE_BY_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.getChgtTypeByDamage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHGT_TYPE_BY_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getChgtTypeByWeather(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPY_MOVES_TYPES)) {
            res_.setResult(BooleanStruct.of(instance_.getCopyMovesTypes()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FORBID_USE_BERRY_AGAINST_FOES)) {
            res_.setResult(BooleanStruct.of(instance_.getForbidUseBerryAgainstFoes()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GIVE_ITEM_TO_ALLY_HAVING_USED)) {
            res_.setResult(BooleanStruct.of(instance_.getGiveItemToAllyHavingUsed()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALED_STATUS_BY_SWITCH)) {
            res_.setResult(BooleanStruct.of(instance_.getHealedStatusBySwitch()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IGN_FOE_STATIS_BOOST)) {
            res_.setResult(BooleanStruct.of(instance_.getIgnFoeStatisBoost()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_CH)) {
            res_.setResult(BooleanStruct.of(instance_.getImmuCh()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_DAMAGE_ALLY_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.getImmuDamageAllyMoves()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_DAMAGE_RECOIL)) {
            res_.setResult(BooleanStruct.of(instance_.getImmuDamageRecoil()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_DAMAGE_TRAPPING_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.getImmuDamageTrappingMoves()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_RECHARGE_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getImmuRechargeRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_RECHARGE_ROUND_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getImmuRechargeRoundMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_SUFFERED_DAMAGE_LOW_EFF)) {
            res_.setResult(BooleanStruct.of(instance_.getImmuSufferedDamageLowEff()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING)) {
            res_.setResult(BooleanStruct.of(instance_.getInflictingDamageInsteadOfSuffering()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MUMY)) {
            res_.setResult(BooleanStruct.of(instance_.getMumy()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_HITS)) {
            res_.setResult(BooleanStruct.of(instance_.getNbHits()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLATE)) {
            res_.setResult(BooleanStruct.of(instance_.getPlate()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL)) {
            res_.setResult(BooleanStruct.of(instance_.getReverseEffectsPowerMovesTypesGlobal()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getReverseEffectsPowerMovesTypesGlobalAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SLOWING)) {
            res_.setResult(BooleanStruct.of(instance_.getSlowing()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TAKE_ITEM_BY_DAMAGING_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.getTakeItemByDamagingMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_HP_WHILE_USING_BERRY)) {
            res_.setResult(new DefaultStruct(instance_.getHealHpWhileUsingBerry(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_HP_FOR_USING_BERRY)) {
            res_.setResult(new DefaultStruct(instance_.getMaxHpForUsingBerry(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_ALLY_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMultAllyDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE_CH)) {
            res_.setResult(new DefaultStruct(instance_.getMultDamageCh(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_EVT_RATE_CH)) {
            res_.setResult(new DefaultStruct(instance_.getMultEvtRateCh(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_EVT_RATE_SEC_EFFECT_OWNER)) {
            res_.setResult(new DefaultStruct(instance_.getMultEvtRateSecEffectOwner(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAB)) {
            res_.setResult(new DefaultStruct(instance_.getMultStab(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_SUFFERED_DAMAGE_SUPER_EFF)) {
            res_.setResult(new DefaultStruct(instance_.getMultSufferedDamageSuperEff(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_VAR_BOOST)) {
            res_.setResult(new DefaultStruct(instance_.getMultVarBoost(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RECOIL_DAMAGE_FOE)) {
            res_.setResult(new DefaultStruct(instance_.getRecoilDamageFoe(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RECOIL_DAMAGE_FOE_BY_KO_OWNER)) {
            res_.setResult(new DefaultStruct(instance_.getRecoilDamageFoeByKoOwner(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DECREASE_NEC_STEPS_HATCH)) {
            res_.setResult(new IntStruct(instance_.getDecreaseNecStepsHatch()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_USED_PP)) {
            res_.setResult(new IntStruct(instance_.getNbUsedPp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getImmuMove(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_ALLY_FROM_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getImmuAllyFromMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getImmuWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IGN_ABILITY)) {
            res_.setResult(new DefaultStruct(instance_.getIgnAbility(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IGN_FOE_TEAM_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getIgnFoeTeamMove(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_ABILITY)) {
            res_.setResult(new DefaultStruct(instance_.getImmuAbility(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_STATUS_BEGIN_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getImmuStatusBeginRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPE_FOR_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTypeForMoves()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHANGING_BOOST_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getChangingBoostTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_POWER)) {
            res_.setResult(new StringStruct(instance_.getMultPower()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getMultDamage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_HP_BY_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getHealHpByWeather(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_HP_BY_TYPE_IF_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getHealHpByTypeIfWeather(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_LOW_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getImmuLowStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_LOW_STAT_IF_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getImmuLowStatIfStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_LOW_STATIS_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getImmuLowStatisTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_STATISTICS_IF_CH)) {
            res_.setResult(new DefaultStruct(instance_.getMaxStatisticsIfCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SINGLE_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getSingleStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_MOVE_TYPES_BY_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getImmuMoveTypesByWeather(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getImmuStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_STATUS_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getImmuStatusTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DIVIDE_STATUS_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getDivideStatusRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FORWARD_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getForwardStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BREAK_FOE_IMMUNE)) {
            res_.setResult(new DefaultStruct(instance_.getBreakFoeImmune(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DEF_EFF)) {
            res_.setResult(new DefaultStruct(instance_.getDefEff(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getMultStat(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_IF_DAMAGE_CAT)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatIfDamageCat(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_IF_DAMGE_TYPE)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatIfDamgeType(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_IF_CAT)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatIfCat(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_IF_STATUT_RANK)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatIfStatutRank(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BONUS_STAT_RANK)) {
            res_.setResult(new DefaultStruct(instance_.getBonusStatRank(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOST_STAT_RANK_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getBoostStatRankEndRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOST_STAT_RANK_PROTECTED)) {
            res_.setResult(new DefaultStruct(instance_.getBoostStatRankProtected(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LOW_STAT_FOE_HIT)) {
            res_.setResult(new DefaultStruct(instance_.getLowStatFoeHit(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_IF_KO_FOE)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatIfKoFoe(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_IF_LOW_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatIfLowStat(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_ALLY)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatAlly(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INCREASED_PRIO)) {
            res_.setResult(new DefaultStruct(instance_.getIncreasedPrio(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INCREASED_PRIO_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getIncreasedPrioTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE_FOE)) {
            res_.setResult(new DefaultStruct(instance_.getMultDamageFoe(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_POWER_MOVES_TYPES_GLOBAL)) {
            res_.setResult(new DefaultStruct(instance_.getMultPowerMovesTypesGlobal(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FAIL_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getFailStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS)) {
            res_.setResult(new DefaultStruct(instance_.getMapVars(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALED_HP_RATE_BY_SWITCH)) {
            res_.setResult(new DefaultStruct(instance_.getHealedHpRateBySwitch(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultAbilitiesBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        AbilitiesBean instance_ = (AbilitiesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_ABILITY)) {
            instance_.setTypedAbility((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAbilitiesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        AbilitiesBean instance_ = (AbilitiesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,SEARCH)) {
            res_.setResult(new StringStruct(instance_.search()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrAbility((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAbilityBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        AbilityBean instance_ = (AbilityBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_INDEX)) {
            res_.setResult(new StringStruct(instance_.clickIndex()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_EFFECT_SENDING)) {
            res_.setResult(DefaultStruct.newInstance(instance_.getEffectSending(),PokemonStandards.TYPE_EFFECT_WHILE_SENDING));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_BREAK_PROTECTION_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickBreakProtectionMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BREAK_PROTECTION_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrBreakProtectionMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_CHGT_TYPE_BY_WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.isChgtTypeByWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_CHGT_TYPE_BY_WEATHER_KEY)) {
            res_.setResult(new StringStruct(instance_.clickChgtTypeByWeatherKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CHGT_TYPE_BY_WEATHER_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrChgtTypeByWeatherKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CHGT_TYPE_BY_WEATHER_VALUE)) {
            res_.setResult(new StringStruct(instance_.getTrChgtTypeByWeatherValue((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_RECHARGE_ROUND_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickImmuRechargeRoundMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_RECHARGE_ROUND_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrImmuRechargeRoundMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_REVERSE_POWER_TYPES_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickReversePowerTypesAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_REVERSE_POWER_TYPES_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrReversePowerTypesAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,DECREASE_NEC_STEPS_HATCH_INT)) {
            res_.setResult(BooleanStruct.of(instance_.decreaseNecStepsHatchInt()));
            return res_;
        }
        if (StringList.quickEq(methodName_,NB_USED_PP_INT)) {
            res_.setResult(BooleanStruct.of(instance_.nbUsedPpInt()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickImmuMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrImmuMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_ALLY_FROM_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickImmuAllyFromMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_ALLY_FROM_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrImmuAllyFromMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IGN_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickIgnAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IGN_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrIgnAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IGN_FOE_TEAM_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickIgnFoeTeamMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IGN_FOE_TEAM_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrIgnFoeTeamMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickImmuAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrImmuAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_STATUS_BEGIN_ROUND)) {
            res_.setResult(new StringStruct(instance_.clickImmuStatusBeginRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_STATUS_BEGIN_ROUND)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatusBeginRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CHANGING_BOOST_TYPES_OLD)) {
            res_.setResult(new StringStruct(instance_.getTrChangingBoostTypesOld((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CHANGING_BOOST_TYPES_NEW)) {
            res_.setResult(new StringStruct(instance_.getTrChangingBoostTypesNew((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_HEAL_HP_BY_WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.isHealHpByWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_HEAL_HP_BY_WEATHER_KEY)) {
            res_.setResult(new StringStruct(instance_.clickHealHpByWeatherKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_HEAL_HP_BY_WEATHER_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrHealHpByWeatherKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_HEAL_HP_BY_TYPE_IF_WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.isHealHpByTypeIfWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_HEAL_HP_BY_TYPE_IF_WEATHER_KEY)) {
            res_.setResult(new StringStruct(instance_.clickHealHpByTypeIfWeatherKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_HEAL_HP_BY_TYPE_IF_WEATHER_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrHealHpByTypeIfWeatherKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_HEAL_HP_BY_TYPE_IF_WEATHER_KEY_SEC)) {
            res_.setResult(new StringStruct(instance_.getTrHealHpByTypeIfWeatherKeySec((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_LOW_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrImmuLowStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_LOW_STAT_IF_STATUS_KEY)) {
            res_.setResult(new StringStruct(instance_.clickImmuLowStatIfStatusKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_LOW_STAT_IF_STATUS_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrImmuLowStatIfStatusKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_LOW_STAT_IF_STATUS_VALUE)) {
            res_.setResult(new StringStruct(instance_.getTrImmuLowStatIfStatusValue((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_LOW_STATIS_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrImmuLowStatisTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_LOW_STATIS_VALUE)) {
            res_.setResult(new StringStruct(instance_.getTrImmuLowStatisValue((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MAX_STATISTICS_IF_CH)) {
            res_.setResult(new StringStruct(instance_.getTrMaxStatisticsIfCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_STATUS)) {
            res_.setResult(BooleanStruct.of(instance_.isStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SINGLE_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickSingleStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_SINGLE_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrSingleStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_MOVE_BY_WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.isMoveByWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_MOVE_BY_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickImmuMoveByWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_MOVE_BY_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrImmuMoveByWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_TYPE_BY_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrImmuTypeByWeather((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_MOVE_BY_STATUS)) {
            res_.setResult(BooleanStruct.of(instance_.isMoveByStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_STATUS_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickImmuStatusWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_STATUS_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatusWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickImmuStatus((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatus((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_STATUS_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatusTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_STATUS_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickImmuStatusTypes((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_STATUS_VALUE)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatusValue((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DIVIDE_STATUS_ROUND_KEY)) {
            res_.setResult(new StringStruct(instance_.clickDivideStatusRoundKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DIVIDE_STATUS_ROUND_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrDivideStatusRoundKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_FORWARD_STATUS_KEY)) {
            res_.setResult(new StringStruct(instance_.clickForwardStatusKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_FORWARD_STATUS_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrForwardStatusKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_FORWARD_STATUS_VALUE)) {
            res_.setResult(new StringStruct(instance_.clickForwardStatusValue((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_FORWARD_STATUS_VALUE)) {
            res_.setResult(new StringStruct(instance_.getTrForwardStatusValue((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BREAK_FOE_IMMUNE_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrBreakFoeImmuneKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BREAK_FOE_IMMUNE_VALUE)) {
            res_.setResult(new StringStruct(instance_.getTrBreakFoeImmuneValue((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_DAMAGE_CAT_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfDamageCatKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_DAMAGE_CAT_KEY_SEC)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfDamageCatKeySec((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_DAMGE_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfDamgeType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_DAMGE_TYPE_SEC)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfDamgeTypeSec((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_CAT_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfCatKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_CAT_KEY_SEC)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfCatKeySec((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_STATUT_RANK)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfStatutRank((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MULT_STAT_IF_STATUT_RANK_SEC)) {
            res_.setResult(new StringStruct(instance_.clickMultStatIfStatutRankSec((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_STATUT_RANK_SEC)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfStatutRankSec((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BONUS_STAT_RANK)) {
            res_.setResult(new StringStruct(instance_.getTrBonusStatRank((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BOOST_STAT_RANK_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.getTrBoostStatRankEndRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BOOST_STAT_RANK_PROTECTED)) {
            res_.setResult(new StringStruct(instance_.getTrBoostStatRankProtected((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_LOW_STAT_FOE_HIT)) {
            res_.setResult(new StringStruct(instance_.getTrLowStatFoeHit((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_KO_FOE)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfKoFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_LOW_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfLowStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_ALLY)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatAlly((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_INCREASED_PRIO)) {
            res_.setResult(new StringStruct(instance_.getTrIncreasedPrio((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_INCREASED_PRIO_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrIncreasedPrioTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_DAMAGE_FOE)) {
            res_.setResult(new StringStruct(instance_.getTrMultDamageFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_POWER_MOVES_TYPES_GLOBAL_KEY)) {
            res_.setResult(new StringStruct(instance_.getTrMultPowerMovesTypesGlobalKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_FAIL_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickFailStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_FAIL_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrFailStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_POKEMON)) {
            res_.setResult(new StringStruct(instance_.clickPokemon((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getTrPokemon((Long)_args[0])));
            return res_;
        }
        return res_;
    }
}
