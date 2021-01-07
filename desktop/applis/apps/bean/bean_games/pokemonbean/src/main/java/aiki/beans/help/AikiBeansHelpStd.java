package aiki.beans.help;
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
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansHelpStd {
    public static final String TYPE_FIGHT_HELP_BEAN = "aiki.beans.help.FightHelpBean";
    public static final String TYPE_GENERAL_HELP_BEAN = "aiki.beans.help.GeneralHelpBean";
    public static final String TYPE_LANGS_BEAN = "aiki.beans.help.LangsBean";
    public static final String TYPE_LANGUAGE_ELEMENT_KEY = "aiki.beans.help.LanguageElementKey";
    public static final String TYPE_LANGUAGE_ELEMENT_STRING_KEY = "aiki.beans.help.LanguageElementStringKey";

    private static final String GET_MAP_WIDTH = "getMapWidth";
    private static final String IS_FIRST_ROW = "isFirstRow";
    private static final String GET_PLACE_NAME = "getPlaceName";
    private static final String GET_MINI_MAP_IMAGE = "getMiniMapImage";
    private static final String GET_IMAGE = "getImage";
    private static final String CLICK_NAME = "clickName";
    private static final String GET_NAME = "getName";
    private static final String GET_GENDER = "getGender";
    private static final String GET_LEVEL = "getLevel";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_ABILITY = "getAbility";
    private static final String FIRST_POKEMON_HAS_ITEM = "firstPokemonHasItem";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String GET_MOVES_AT_LEVEL = "getMovesAtLevel";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_MOVE = "getMove";
    private static final String CLICK_POKEMON = "clickPokemon";
    private static final String GET_TR_POKEMON = "getTrPokemon";
    private static final String CLICK_TM = "clickTm";
    private static final String GET_TR_TM = "getTrTm";
    private static final String GET_TM_PRICE = "getTmPrice";
    private static final String CLICK_HM = "clickHm";
    private static final String GET_TR_HM = "getTrHm";
    private static final String GET_TR_TYPE = "getTrType";
    private static final String GET_IMAGE_TYPE = "getImageType";
    private static final String GET_COLOR_TYPE = "getColorType";
    private static final String GET_TR_LANG = "getTrLang";
    private static final String GET_KEYS_GENDERS = "getKeysGenders";
    private static final String GET_ROW_GENDER = "getRowGender";
    private static final String GET_KEYS_BOOLEANS = "getKeysBooleans";
    private static final String GET_ROW_BOOLEAN = "getRowBoolean";
    private static final String GET_KEYS_ENVIRONMENTS = "getKeysEnvironments";
    private static final String GET_ROW_ENVIRONMENT = "getRowEnvironment";
    private static final String GET_KEYS_STATISTICS = "getKeysStatistics";
    private static final String GET_ROW_STATISTIC = "getRowStatistic";
    private static final String GET_KEYS_TARGETS = "getKeysTargets";
    private static final String GET_ROW_TARGET = "getRowTarget";
    private static final String GET_KEYS_CATEGORIES = "getKeysCategories";
    private static final String GET_ROW_CATEGORY = "getRowCategory";
    private static final String GET_KEYS_TYPES = "getKeysTypes";
    private static final String GET_ROW_TYPE = "getRowType";
    private static final String GET_KEYS_POKEMON = "getKeysPokemon";
    private static final String GET_ROW_POKEMON = "getRowPokemon";
    private static final String GET_KEYS_MOVES = "getKeysMoves";
    private static final String GET_ROW_MOVE = "getRowMove";
    private static final String GET_KEYS_ITEMS = "getKeysItems";
    private static final String GET_ROW_ITEM = "getRowItem";
    private static final String GET_KEYS_ABILITIES = "getKeysAbilities";
    private static final String GET_ROW_ABILITY = "getRowAbility";
    private static final String GET_KEYS_STATUS = "getKeysStatus";
    private static final String GET_ROW_STATUS = "getRowStatus";
    private static final String GET_KEYS_MATH = "getKeysMath";
    private static final String GET_ROW_MATH = "getRowMath";
    private static final String GET_KEYS_DESC = "getKeysDesc";
    private static final String GET_ROW_DESC = "getRowDesc";
    private static final String CLICK_PRIVATING_MOVES = "clickPrivatingMoves";
    private static final String GET_TR_PRIVATING_MOVES = "getTrPrivatingMoves";
    private static final String CLICK_MOVES_HEALING_SUBSTITUTE = "clickMovesHealingSubstitute";
    private static final String GET_TR_MOVES_HEALING_SUBSTITUTE = "getTrMovesHealingSubstitute";
    private static final String CLICK_DEFAULT_MOVE = "clickDefaultMove";
    private static final String GET_TR_DEFAULT_MOVE = "getTrDefaultMove";
    private static final String CLICK_ABILITIES_SENT_BEGIN = "clickAbilitiesSentBegin";
    private static final String GET_TR_ABILITIES_SENT_BEGIN = "getTrAbilitiesSentBegin";
    private static final String CLICK_ITEMS_SENT_BEGIN = "clickItemsSentBegin";
    private static final String GET_TR_ITEMS_SENT_BEGIN = "getTrItemsSentBegin";
    private static final String CLICK_ITEMS_SENT_BEGIN_OTH = "clickItemsSentBeginOth";
    private static final String GET_TR_ITEMS_SENT_BEGIN_OTH = "getTrItemsSentBeginOth";
    private static final String CLICK_CHANGING_TYPES_ABILITIES = "clickChangingTypesAbilities";
    private static final String GET_TR_CHANGING_TYPES_ABILITIES = "getTrChangingTypesAbilities";
    private static final String CLICK_COPY_ABILITIES = "clickCopyAbilities";
    private static final String GET_TR_COPY_ABILITIES = "getTrCopyAbilities";
    private static final String CLICK_ABILITIES_SENT_STATIS = "clickAbilitiesSentStatis";
    private static final String GET_TR_ABILITIES_SENT_STATIS = "getTrAbilitiesSentStatis";
    private static final String CLICK_SUBSTITUTING_MOVES = "clickSubstitutingMoves";
    private static final String GET_TR_SUBSTITUTING_MOVES = "getTrSubstitutingMoves";
    private static final String CLICK_ABILITIES_PRIO = "clickAbilitiesPrio";
    private static final String GET_TR_ABILITIES_PRIO = "getTrAbilitiesPrio";
    private static final String CLICK_SLOW_ABILITIES = "clickSlowAbilities";
    private static final String GET_TR_SLOW_ABILITIES = "getTrSlowAbilities";
    private static final String CLICK_SLOW_ITEMS = "clickSlowItems";
    private static final String GET_TR_SLOW_ITEMS = "getTrSlowItems";
    private static final String CLICK_REVERSE_SPEED_MOVES = "clickReverseSpeedMoves";
    private static final String GET_TR_REVERSE_SPEED_MOVES = "getTrReverseSpeedMoves";
    private static final String CLICK_BERRY_SPEED = "clickBerrySpeed";
    private static final String GET_TR_BERRY_SPEED = "getTrBerrySpeed";
    private static final String CLICK_ITEM_SPEED = "clickItemSpeed";
    private static final String GET_TR_ITEM_SPEED = "getTrItemSpeed";
    private static final String CLICK_ABILITIES_SWITCH = "clickAbilitiesSwitch";
    private static final String GET_TR_ABILITIES_SWITCH = "getTrAbilitiesSwitch";
    private static final String CLICK_DELETED_STATUS_SWITCH = "clickDeletedStatusSwitch";
    private static final String GET_TR_DELETED_STATUS_SWITCH = "getTrDeletedStatusSwitch";
    private static final String CLICK_ENTRY_HAZARD = "clickEntryHazard";
    private static final String GET_TR_ENTRY_HAZARD = "getTrEntryHazard";
    private static final String CLICK_BEGIN_ROUND_STATUS = "clickBeginRoundStatus";
    private static final String GET_TR_BEGIN_ROUND_STATUS = "getTrBeginRoundStatus";
    private static final String CLICK_DELETE_STATUS_MOVE = "clickDeleteStatusMove";
    private static final String GET_TR_DELETE_STATUS_MOVE = "getTrDeleteStatusMove";
    private static final String CLICK_IMMU_STATUS_ABILITY = "clickImmuStatusAbility";
    private static final String GET_TR_IMMU_STATUS_ABILITY = "getTrImmuStatusAbility";
    private static final String HAS_LAW_FOR_ATTACK_ANY = "hasLawForAttackAny";
    private static final String HAS_LAW_FOR_ATTACK = "hasLawForAttack";
    private static final String HAS_LAW_FOR_HEAL_ANY = "hasLawForHealAny";
    private static final String HAS_LAW_FOR_HEAL = "hasLawForHeal";
    private static final String CLICK_AUTO_DAMAGE = "clickAutoDamage";
    private static final String GET_TR_AUTO_DAMAGE = "getTrAutoDamage";
    private static final String GET_FOMULA = "getFomula";
    private static final String CLICK_PREPA_ROUND_MOVES = "clickPrepaRoundMoves";
    private static final String GET_TR_PREPA_ROUND_MOVES = "getTrPrepaRoundMoves";
    private static final String CLICK_SPEED_PREPARING_ITEMS = "clickSpeedPreparingItems";
    private static final String GET_TR_SPEED_PREPARING_ITEMS = "getTrSpeedPreparingItems";
    private static final String IS_DISAPPEARING_USER = "isDisappearingUser";
    private static final String GET_TR_RECHARGE_MOVES = "getTrRechargeMoves";
    private static final String CLICK_IMMU_RECHARGING = "clickImmuRecharging";
    private static final String GET_TR_IMMU_RECHARGING = "getTrImmuRecharging";
    private static final String CLICK_MOVES_INVOKING = "clickMovesInvoking";
    private static final String GET_TR_MOVES_INVOKING = "getTrMovesInvoking";
    private static final String CLICK_COPY_MOVE_TYPES_AB = "clickCopyMoveTypesAb";
    private static final String GET_TR_COPY_MOVE_TYPES_AB = "getTrCopyMoveTypesAb";
    private static final String CLICK_MOVES_THIEVING = "clickMovesThieving";
    private static final String GET_TR_MOVES_THIEVING = "getTrMovesThieving";
    private static final String CLICK_MOVES_SEC_EFF_ITEMS = "clickMovesSecEffItems";
    private static final String GET_TR_MOVES_SEC_EFF_ITEMS = "getTrMovesSecEffItems";
    private static final String CLICK_MOVES_ATTRACTING = "clickMovesAttracting";
    private static final String GET_TR_MOVES_ATTRACTING = "getTrMovesAttracting";
    private static final String CLICK_BEGIN_ROUND_STATUS_FOE = "clickBeginRoundStatusFoe";
    private static final String GET_TR_BEGIN_ROUND_STATUS_FOE = "getTrBeginRoundStatusFoe";
    private static final String CLICK_PRESSURE_ABILITIES = "clickPressureAbilities";
    private static final String GET_TR_PRESSURE_ABILITIES = "getTrPressureAbilities";
    private static final String CLICK_PROTECT_ABILITIES = "clickProtectAbilities";
    private static final String GET_TR_PROTECT_ABILITIES = "getTrProtectAbilities";
    private static final String CLICK_PROTECT_ITEMS = "clickProtectItems";
    private static final String GET_TR_PROTECT_ITEMS = "getTrProtectItems";
    private static final String CLICK_PROTECT_MOVES = "clickProtectMoves";
    private static final String GET_TR_PROTECT_MOVES = "getTrProtectMoves";
    private static final String CLICK_EFF_MOVES = "clickEffMoves";
    private static final String GET_TR_EFF_MOVES = "getTrEffMoves";
    private static final String CLICK_MOVES_MIRROR = "clickMovesMirror";
    private static final String GET_TR_MOVES_MIRROR = "getTrMovesMirror";
    private static final String CLICK_ABILITIES_PART_STATIS = "clickAbilitiesPartStatis";
    private static final String GET_TR_ABILITIES_PART_STATIS = "getTrAbilitiesPartStatis";
    private static final String IMMU_STATIS_TEAM_MOVE_ANY = "immuStatisTeamMoveAny";
    private static final String IMMU_STATIS_TEAM_MOVE = "immuStatisTeamMove";
    private static final String CLICK_MOVES_TEAM = "clickMovesTeam";
    private static final String GET_TR_MOVES_TEAM = "getTrMovesTeam";
    private static final String CLICK_ABILITIES_FIGHTER_STATIS_VAR = "clickAbilitiesFighterStatisVar";
    private static final String GET_TR_ABILITIES_FIGHTER_STATIS_VAR = "getTrAbilitiesFighterStatisVar";
    private static final String CLICK_ABILITIES_RATE_STATIS = "clickAbilitiesRateStatis";
    private static final String GET_TR_ABILITIES_RATE_STATIS = "getTrAbilitiesRateStatis";
    private static final String CLICK_COMBO_EVT_STAT = "clickComboEvtStat";
    private static final String GET_TR_COMBO_EVT_STAT = "getTrComboEvtStat";
    private static final String CLICK_ABILITIES_FIGHTER_STATIS = "clickAbilitiesFighterStatis";
    private static final String GET_TR_ABILITIES_FIGHTER_STATIS = "getTrAbilitiesFighterStatis";
    private static final String CLICK_ITEMS_FIGHTER_STATIS = "clickItemsFighterStatis";
    private static final String GET_TR_ITEMS_FIGHTER_STATIS = "getTrItemsFighterStatis";
    private static final String CLICK_SUCCESSFUL_STATUS = "clickSuccessfulStatus";
    private static final String GET_TR_SUCCESSFUL_STATUS = "getTrSuccessfulStatus";
    private static final String CLICK_GLOBAL_MOVES_STATUS = "clickGlobalMovesStatus";
    private static final String GET_TR_GLOBAL_MOVES_STATUS = "getTrGlobalMovesStatus";
    private static final String CLICK_ABILITIES_PART_STATUS = "clickAbilitiesPartStatus";
    private static final String GET_TR_ABILITIES_PART_STATUS = "getTrAbilitiesPartStatus";
    private static final String IMMU_STATUS_TEAM_MOVE_ANY = "immuStatusTeamMoveAny";
    private static final String IMMU_STATUS_TEAM_MOVE = "immuStatusTeamMove";
    private static final String CLICK_ABILITIES_FIGHTER_STATUS = "clickAbilitiesFighterStatus";
    private static final String GET_TR_ABILITIES_FIGHTER_STATUS = "getTrAbilitiesFighterStatus";
    private static final String CLICK_ITEMS_FIGHTER_STATUS = "clickItemsFighterStatus";
    private static final String GET_TR_ITEMS_FIGHTER_STATUS = "getTrItemsFighterStatus";
    private static final String GET_TR_LAW_RATE = "getTrLawRate";
    private static final String CLICK_MOVES_PROT_AGAINST_KO = "clickMovesProtAgainstKo";
    private static final String GET_TR_MOVES_PROT_AGAINST_KO = "getTrMovesProtAgainstKo";
    private static final String CLICK_ITEMS_PROT_AGAINST_KO = "clickItemsProtAgainstKo";
    private static final String GET_TR_ITEMS_PROT_AGAINST_KO = "getTrItemsProtAgainstKo";
    private static final String CLICK_MOVES_CANNOT_KO = "clickMovesCannotKo";
    private static final String GET_TR_MOVES_CANNOT_KO = "getTrMovesCannotKo";
    private static final String CLICK_ITEMS_ABS = "clickItemsAbs";
    private static final String GET_TR_ITEMS_ABS = "getTrItemsAbs";
    private static final String CLICK_ABILITIES_REV_ABS = "clickAbilitiesRevAbs";
    private static final String GET_TR_ABILITIES_REV_ABS = "getTrAbilitiesRevAbs";
    private static final String CLICK_ABILITIES_DAMAGE_STATIS = "clickAbilitiesDamageStatis";
    private static final String GET_TR_ABILITIES_DAMAGE_STATIS = "getTrAbilitiesDamageStatis";
    private static final String CLICK_ABILITIES_CHANGING_TYPES_DAMAGE = "clickAbilitiesChangingTypesDamage";
    private static final String GET_TR_ABILITIES_CHANGING_TYPES_DAMAGE = "getTrAbilitiesChangingTypesDamage";
    private static final String CLICK_ABILITIES_TAKING_ITEM = "clickAbilitiesTakingItem";
    private static final String GET_TR_ABILITIES_TAKING_ITEM = "getTrAbilitiesTakingItem";
    private static final String CLICK_ABILITIES_STATIS_VAR_USER = "clickAbilitiesStatisVarUser";
    private static final String GET_TR_ABILITIES_STATIS_VAR_USER = "getTrAbilitiesStatisVarUser";
    private static final String CLICK_ABILITIES_STATUS = "clickAbilitiesStatus";
    private static final String GET_TR_ABILITIES_STATUS = "getTrAbilitiesStatus";
    private static final String CLICK_ABILITIES_COPY_AB = "clickAbilitiesCopyAb";
    private static final String GET_TR_ABILITIES_COPY_AB = "getTrAbilitiesCopyAb";
    private static final String CLICK_RECOIL_ITEMS = "clickRecoilItems";
    private static final String GET_TR_RECOIL_ITEMS = "getTrRecoilItems";
    private static final String CLICK_RECOIL_ABILITIES = "clickRecoilAbilities";
    private static final String GET_TR_RECOIL_ABILITIES = "getTrRecoilAbilities";
    private static final String CLICK_ABILITIES_KO_TARGET = "clickAbilitiesKoTarget";
    private static final String GET_TR_ABILITIES_KO_TARGET = "getTrAbilitiesKoTarget";
    private static final String CLICK_MOVES_KO_TARGET = "clickMovesKoTarget";
    private static final String GET_TR_MOVES_KO_TARGET = "getTrMovesKoTarget";
    private static final String CLICK_BERRY_USER = "clickBerryUser";
    private static final String GET_TR_BERRY_USER = "getTrBerryUser";
    private static final String CLICK_BERRY_TARGET = "clickBerryTarget";
    private static final String GET_TR_BERRY_TARGET = "getTrBerryTarget";
    private static final String CLICK_ABILITIES_END_ROUND = "clickAbilitiesEndRound";
    private static final String GET_TR_ABILITIES_END_ROUND = "getTrAbilitiesEndRound";
    private static final String CLICK_BERRY_END_ROUND = "clickBerryEndRound";
    private static final String GET_TR_BERRY_END_ROUND = "getTrBerryEndRound";
    private static final String ATTACK_FIRST = "attackFirst";
    private static final String ATTACK_LAST = "attackLast";
    private static final String CLICK_MOVES_CHANGING_ATT_ORDER = "clickMovesChangingAttOrder";
    private static final String GET_TR_MOVES_CHANGING_ATT_ORDER = "getTrMovesChangingAttOrder";
    private static final String ATTACK_LAST_ANY = "attackLastAny";
    private static final String GET_TR_DIFFICULTY = "getTrDifficulty";
    private static final String WITH_CONST_DAMAGE_ANY = "withConstDamageAny";
    private static final String WITH_CONST_DAMAGE = "withConstDamage";
    private static final String CLICK_DAMAGING_MOVES = "clickDamagingMoves";
    private static final String GET_TR_DAMAGING_MOVES = "getTrDamagingMoves";
    private static final String WITH_RAND_DAMAGE_ANY = "withRandDamageAny";
    private static final String WITH_RAND_DAMAGE = "withRandDamage";
    private static final String WITH_MULT_DAMAGE_ANY = "withMultDamageAny";
    private static final String WITH_MULT_DAMAGE = "withMultDamage";
    private static final String CLICK_ITEMS_USER_POWER = "clickItemsUserPower";
    private static final String GET_TR_ITEMS_USER_POWER = "getTrItemsUserPower";
    private static final String CLICK_MOVES_USER_POWER = "clickMovesUserPower";
    private static final String GET_TR_MOVES_USER_POWER = "getTrMovesUserPower";
    private static final String CLICK_MOVES_TARGET_POWER = "clickMovesTargetPower";
    private static final String GET_TR_MOVES_TARGET_POWER = "getTrMovesTargetPower";
    private static final String CLICK_ABILITIES_USER_POWER = "clickAbilitiesUserPower";
    private static final String GET_TR_ABILITIES_USER_POWER = "getTrAbilitiesUserPower";
    private static final String CLICK_MOVES_USER_ALLYA_DAMAGE = "clickMovesUserAllyaDamage";
    private static final String GET_TR_MOVES_USER_ALLY_DAMAGE = "getTrMovesUserAllyDamage";
    private static final String CLICK_ABILITIES_TARGET_DAMAGE = "clickAbilitiesTargetDamage";
    private static final String GET_TR_ABILITIES_TARGET_DAMAGE = "getTrAbilitiesTargetDamage";
    private static final String CLICK_MOVES_TARGET_TEAM_DAMAGE = "clickMovesTargetTeamDamage";
    private static final String GET_TR_MOVES_TARGET_TEAM_DAMAGE = "getTrMovesTargetTeamDamage";
    private static final String CLICK_ABILITIES_USER_IGN_TARGET_TEAM = "clickAbilitiesUserIgnTargetTeam";
    private static final String GET_TR_ABILITIES_USER_IGN_TARGET_TEAM = "getTrAbilitiesUserIgnTargetTeam";
    private static final String CLICK_ABILITIES_GLOBAL = "clickAbilitiesGlobal";
    private static final String GET_TR_ABILITIES_GLOBAL = "getTrAbilitiesGlobal";
    private static final String CLICK_MOVES_GLOBAL = "clickMovesGlobal";
    private static final String GET_TR_MOVES_GLOBAL = "getTrMovesGlobal";
    private static final String CLICK_ITEMS_USER_DAMAGE = "clickItemsUserDamage";
    private static final String GET_TR_ITEMS_USER_DAMAGE = "getTrItemsUserDamage";
    private static final String CLICK_ABILITIES_USER_DAMAGE = "clickAbilitiesUserDamage";
    private static final String GET_TR_ABILITIES_USER_DAMAGE = "getTrAbilitiesUserDamage";
    private static final String CLICK_MOVES_INVOK_DAMAGE = "clickMovesInvokDamage";
    private static final String GET_TR_MOVES_INVOK_DAMAGE = "getTrMovesInvokDamage";
    private static final String CLICK_ITEMS_TARGET_DAMAGE = "clickItemsTargetDamage";
    private static final String GET_TR_ITEMS_TARGET_DAMAGE = "getTrItemsTargetDamage";
    private static final String CLICK_MOVES_GLOBAL_PREPA_DAMAGE = "clickMovesGlobalPrepaDamage";
    private static final String GET_TR_MOVES_GLOBAL_PREPA_DAMAGE = "getTrMovesGlobalPrepaDamage";
    private static final String CLICK_STATUS_DAMAGE = "clickStatusDamage";
    private static final String GET_TR_STATUS_DAMAGE = "getTrStatusDamage";
    private static final String CLICK_ABILITIES_USER_TARGET_DAMAGE = "clickAbilitiesUserTargetDamage";
    private static final String GET_TR_ABILITIES_USER_TARGET_DAMAGE = "getTrAbilitiesUserTargetDamage";
    private static final String GET_STAB = "getStab";
    private static final String CLICK_ABILITIES_USER_STAB_DAMAGE = "clickAbilitiesUserStabDamage";
    private static final String GET_TR_ABILITIES_USER_STAB_DAMAGE = "getTrAbilitiesUserStabDamage";
    private static final String CLICK_MOVES_TYPES_DEF_ITEM = "clickMovesTypesDefItem";
    private static final String GET_TR_MOVES_TYPES_DEF_ITEM = "getTrMovesTypesDefItem";
    private static final String CLICK_ITEMS_TYPES_DEF = "clickItemsTypesDef";
    private static final String GET_TR_ITEMS_TYPES_DEF = "getTrItemsTypesDef";
    private static final String CLICK_MOVES_TYPES_DEF_WEATHER = "clickMovesTypesDefWeather";
    private static final String GET_TR_MOVES_TYPES_DEF_WEATHER = "getTrMovesTypesDefWeather";
    private static final String CLICK_ABILITIES_TYPE_DEF_MOVES = "clickAbilitiesTypeDefMoves";
    private static final String GET_TR_ABILITIES_TYPE_DEF_MOVES = "getTrAbilitiesTypeDefMoves";
    private static final String CLICK_ABILITIES_CHANGE_TYPE_MOVES = "clickAbilitiesChangeTypeMoves";
    private static final String GET_TR_ABILITIES_CHANGE_TYPE_MOVES = "getTrAbilitiesChangeTypeMoves";
    private static final String CLICK_MOVES_TYPE_DEF_MOVES = "clickMovesTypeDefMoves";
    private static final String GET_TR_MOVES_TYPE_DEF_MOVES = "getTrMovesTypeDefMoves";
    private static final String GET_TR_MOVES_CHANGE_TYPE_MOVES = "getTrMovesChangeTypeMoves";
    private static final String CLICK_MOVES_GLOBAL_BREAK_IMMU = "clickMovesGlobalBreakImmu";
    private static final String GET_TR_MOVES_GLOBAL_BREAK_IMMU = "getTrMovesGlobalBreakImmu";
    private static final String CLICK_MOVES_UNPROTECTING_TYPES = "clickMovesUnprotectingTypes";
    private static final String GET_TR_MOVES_UNPROTECTING_TYPES = "getTrMovesUnprotectingTypes";
    private static final String CLICK_ABILITIES_BREAK_IMMU = "clickAbilitiesBreakImmu";
    private static final String GET_TR_ABILITIES_BREAK_IMMU = "getTrAbilitiesBreakImmu";
    private static final String CLICK_ITEMS_CANCEL_IMMU = "clickItemsCancelImmu";
    private static final String GET_TR_ITEMS_CANCEL_IMMU = "getTrItemsCancelImmu";
    private static final String NEXT_ROW_AFTER = "nextRowAfter";
    private static final String GET_EFFICIENCY = "getEfficiency";
    private static final String CLICK_MOVES_IGN_LOW_ATT = "clickMovesIgnLowAtt";
    private static final String GET_TR_MOVES_IGN_LOW_ATT = "getTrMovesIgnLowAtt";
    private static final String CLICK_MOVES_IGN_INC_DEF = "clickMovesIgnIncDef";
    private static final String GET_TR_MOVES_IGN_INC_DEF = "getTrMovesIgnIncDef";
    private static final String ABILITY_BOOST_NORMAL_ANY = "abilityBoostNormalAny";
    private static final String ABILITY_BOOST_NORMAL = "abilityBoostNormal";
    private static final String CLICK_ABILITIES_BOOSTING_STAT = "clickAbilitiesBoostingStat";
    private static final String GET_TR_ABILITIES_BOOSTING_STAT = "getTrAbilitiesBoostingStat";
    private static final String ITEM_BOOST_NORMAL_ANY = "itemBoostNormalAny";
    private static final String ITEM_BOOST_NORMAL = "itemBoostNormal";
    private static final String CLICK_ITEMS_BOOSTING_STAT = "clickItemsBoostingStat";
    private static final String GET_TR_ITEMS_BOOSTING_STAT = "getTrItemsBoostingStat";
    private static final String ITEM_MULT_NORMAL_ANY = "itemMultNormalAny";
    private static final String ITEM_MULT_NORMAL = "itemMultNormal";
    private static final String CLICK_ITEMS_MULT_STAT = "clickItemsMultStat";
    private static final String GET_TR_ITEMS_MULT_STAT = "getTrItemsMultStat";
    private static final String ABILITY_MULT_NORMAL_ANY = "abilityMultNormalAny";
    private static final String ABILITY_MULT_NORMAL = "abilityMultNormal";
    private static final String CLICK_ABILITIES_MULT_STAT = "clickAbilitiesMultStat";
    private static final String GET_TR_ABILITIES_MULT_STAT = "getTrAbilitiesMultStat";
    private static final String MOVE_GLOBAL_MULT_NORMAL_ANY = "moveGlobalMultNormalAny";
    private static final String MOVE_GLOBAL_MULT_NORMAL = "moveGlobalMultNormal";
    private static final String CLICK_MOVES_GLOBAL_MULT_STAT = "clickMovesGlobalMultStat";
    private static final String GET_TR_MOVES_GLOBAL_MULT_STAT = "getTrMovesGlobalMultStat";
    private static final String MOVE_TEAM_MULT_NORMAL_ANY = "moveTeamMultNormalAny";
    private static final String MOVE_TEAM_MULT_NORMAL = "moveTeamMultNormal";
    private static final String CLICK_MOVES_TEAM_MULT_STAT = "clickMovesTeamMultStat";
    private static final String GET_TR_MOVES_TEAM_MULT_STAT = "getTrMovesTeamMultStat";
    private static final String ABILITY_ALLY_MULT_NORMAL_ANY = "abilityAllyMultNormalAny";
    private static final String ABILITY_ALLY_MULT_NORMAL = "abilityAllyMultNormal";
    private static final String CLICK_ABILITIES_ALLY_MULT_STAT = "clickAbilitiesAllyMultStat";
    private static final String GET_TR_ABILITIES_ALLY_MULT_STAT = "getTrAbilitiesAllyMultStat";
    private static final String MOVE_FOE_TEAM_MULT_NORMAL_ANY = "moveFoeTeamMultNormalAny";
    private static final String MOVE_FOE_TEAM_MULT_NORMAL = "moveFoeTeamMultNormal";
    private static final String CLICK_MOVES_FOE_TEAM_MULT_STAT = "clickMovesFoeTeamMultStat";
    private static final String GET_TR_MOVES_FOE_TEAM_MULT_STAT = "getTrMovesFoeTeamMultStat";
    private static final String STATUS_MULT_NORMAL_ANY = "statusMultNormalAny";
    private static final String STATUS_MULT_NORMAL = "statusMultNormal";
    private static final String CLICK_STATUS_MULT_STAT = "clickStatusMultStat";
    private static final String GET_TR_STATUS_MULT_STAT = "getTrStatusMultStat";
    private static final String ABILITY_IMMU_MULT_NORMAL_ANY = "abilityImmuMultNormalAny";
    private static final String ABILITY_IMMU_MULT_NORMAL = "abilityImmuMultNormal";
    private static final String CLICK_ABILITIES_IMMU_MULT_STAT = "clickAbilitiesImmuMultStat";
    private static final String GET_TR_ABILITIES_IMMU_MULT_STAT = "getTrAbilitiesImmuMultStat";
    private static final String COMBO_MULT_NORMAL_ANY = "comboMultNormalAny";
    private static final String COMBO_MULT_NORMAL = "comboMultNormal";
    private static final String CLICK_COMBO_MULT_STAT = "clickComboMultStat";
    private static final String GET_TR_COMBO_MULT_STAT = "getTrComboMultStat";
    private static final String CLICK_ABILITIES_BREAK_PROTECT_MOVES = "clickAbilitiesBreakProtectMoves";
    private static final String GET_TR_ABILITIES_BREAK_PROTECT_MOVES = "getTrAbilitiesBreakProtectMoves";
    private static final String CLICK_MOVES_IGN_ACC = "clickMovesIgnAcc";
    private static final String GET_TR_MOVES_IGN_ACC = "getTrMovesIgnAcc";
    private static final String CLICK_MOVES_IGN_EVA = "clickMovesIgnEva";
    private static final String GET_TR_MOVES_IGN_EVA = "getTrMovesIgnEva";
    private static final String CLICK_MOVES_GLOBAL_ACC = "clickMovesGlobalAcc";
    private static final String GET_TR_MOVES_GLOBAL_ACC = "getTrMovesGlobalAcc";
    private static final String ABILITY_BOOST_ACCURACY_ANY = "abilityBoostAccuracyAny";
    private static final String ABILITY_BOOST_ACCURACY = "abilityBoostAccuracy";
    private static final String ITEM_BOOST_ACCURACY_ANY = "itemBoostAccuracyAny";
    private static final String ITEM_BOOST_ACCURACY = "itemBoostAccuracy";
    private static final String ITEM_MULT_ACCURACY_ANY = "itemMultAccuracyAny";
    private static final String ITEM_MULT_ACCURACY = "itemMultAccuracy";
    private static final String ABILITY_MULT_ACCURACY_ANY = "abilityMultAccuracyAny";
    private static final String ABILITY_MULT_ACCURACY = "abilityMultAccuracy";
    private static final String MOVE_GLOBAL_MULT_ACCURACY_ANY = "moveGlobalMultAccuracyAny";
    private static final String MOVE_GLOBAL_MULT_ACCURACY = "moveGlobalMultAccuracy";
    private static final String MOVE_TEAM_MULT_ACCURACY_ANY = "moveTeamMultAccuracyAny";
    private static final String MOVE_TEAM_MULT_ACCURACY = "moveTeamMultAccuracy";
    private static final String ABILITY_ALLY_MULT_ACCURACY_ANY = "abilityAllyMultAccuracyAny";
    private static final String ABILITY_ALLY_MULT_ACCURACY = "abilityAllyMultAccuracy";
    private static final String MOVE_FOE_TEAM_MULT_ACCURACY_ANY = "moveFoeTeamMultAccuracyAny";
    private static final String MOVE_FOE_TEAM_MULT_ACCURACY = "moveFoeTeamMultAccuracy";
    private static final String STATUS_MULT_ACCURACY_ANY = "statusMultAccuracyAny";
    private static final String STATUS_MULT_ACCURACY = "statusMultAccuracy";
    private static final String ABILITY_IMMU_MULT_ACCURACY_ANY = "abilityImmuMultAccuracyAny";
    private static final String ABILITY_IMMU_MULT_ACCURACY = "abilityImmuMultAccuracy";
    private static final String COMBO_MULT_ACCURACY_ANY = "comboMultAccuracyAny";
    private static final String COMBO_MULT_ACCURACY = "comboMultAccuracy";
    private static final String ABILITY_BOOST_EVASINESS_ANY = "abilityBoostEvasinessAny";
    private static final String ABILITY_BOOST_EVASINESS = "abilityBoostEvasiness";
    private static final String ITEM_BOOST_EVASINESS_ANY = "itemBoostEvasinessAny";
    private static final String ITEM_BOOST_EVASINESS = "itemBoostEvasiness";
    private static final String ITEM_MULT_EVASINESS_ANY = "itemMultEvasinessAny";
    private static final String ABILITY_MULT_EVASINESS_ANY = "abilityMultEvasinessAny";
    private static final String ABILITY_MULT_EVASINESS = "abilityMultEvasiness";
    private static final String MOVE_GLOBAL_MULT_EVASINESS_ANY = "moveGlobalMultEvasinessAny";
    private static final String MOVE_GLOBAL_MULT_EVASINESS = "moveGlobalMultEvasiness";
    private static final String MOVE_TEAM_MULT_EVASINESS_ANY = "moveTeamMultEvasinessAny";
    private static final String MOVE_TEAM_MULT_EVASINESS = "moveTeamMultEvasiness";
    private static final String ABILITY_ALLY_MULT_EVASINESS_ANY = "abilityAllyMultEvasinessAny";
    private static final String ABILITY_ALLY_MULT_EVASINESS = "abilityAllyMultEvasiness";
    private static final String MOVE_FOE_TEAM_MULT_EVASINESS_ANY = "moveFoeTeamMultEvasinessAny";
    private static final String MOVE_FOE_TEAM_MULT_EVASINESS = "moveFoeTeamMultEvasiness";
    private static final String STATUS_MULT_EVASINESS_ANY = "statusMultEvasinessAny";
    private static final String STATUS_MULT_EVASINESS = "statusMultEvasiness";
    private static final String ABILITY_IMMU_MULT_EVASINESS_ANY = "abilityImmuMultEvasinessAny";
    private static final String ABILITY_IMMU_MULT_EVASINESS = "abilityImmuMultEvasiness";
    private static final String COMBO_MULT_EVASINESS_ANY = "comboMultEvasinessAny";
    private static final String COMBO_MULT_EVASINESS = "comboMultEvasiness";
    private static final String IMMU_CH_TEAM_MOVE_ANY = "immuChTeamMoveAny";
    private static final String IMMU_CH_TEAM_MOVE = "immuChTeamMove";
    private static final String CLICK_ABILITIES_IMMU_CH = "clickAbilitiesImmuCh";
    private static final String GET_TR_ABILITIES_IMMU_CH = "getTrAbilitiesImmuCh";
    private static final String ABILITY_BOOST_CH_ANY = "abilityBoostChAny";
    private static final String ABILITY_BOOST_CH = "abilityBoostCh";
    private static final String ITEM_BOOST_CH_ANY = "itemBoostChAny";
    private static final String ITEM_BOOST_CH = "itemBoostCh";
    private static final String CLICK_MOVES_BOOST_CH = "clickMovesBoostCh";
    private static final String GET_TR_MOVES_BOOST_CH = "getTrMovesBoostCh";
    private static final String CLICK_ABILITIES_MULT_EVT_CH = "clickAbilitiesMultEvtCh";
    private static final String GET_TR_ABILITIES_MULT_EVT_CH = "getTrAbilitiesMultEvtCh";
    private static final String CLICK_ABILITIES_MULT_RATE_CH = "clickAbilitiesMultRateCh";
    private static final String GET_TR_ABILITIES_MULT_RATE_CH = "getTrAbilitiesMultRateCh";
    private static final String ABILITY_BOOST_SPEED_ANY = "abilityBoostSpeedAny";
    private static final String ABILITY_BOOST_SPEED = "abilityBoostSpeed";
    private static final String ITEM_BOOST_SPEED_ANY = "itemBoostSpeedAny";
    private static final String ITEM_BOOST_SPEED = "itemBoostSpeed";
    private static final String ITEM_MULT_SPEED_ANY = "itemMultSpeedAny";
    private static final String ITEM_MULT_SPEED = "itemMultSpeed";
    private static final String ABILITY_MULT_SPEED_ANY = "abilityMultSpeedAny";
    private static final String ABILITY_MULT_SPEED = "abilityMultSpeed";
    private static final String MOVE_GLOBAL_MULT_SPEED_ANY = "moveGlobalMultSpeedAny";
    private static final String MOVE_GLOBAL_MULT_SPEED = "moveGlobalMultSpeed";
    private static final String MOVE_TEAM_MULT_SPEED_ANY = "moveTeamMultSpeedAny";
    private static final String MOVE_TEAM_MULT_SPEED = "moveTeamMultSpeed";
    private static final String ABILITY_ALLY_MULT_SPEED_ANY = "abilityAllyMultSpeedAny";
    private static final String ABILITY_ALLY_MULT_SPEED = "abilityAllyMultSpeed";
    private static final String MOVE_FOE_TEAM_MULT_SPEED_ANY = "moveFoeTeamMultSpeedAny";
    private static final String MOVE_FOE_TEAM_MULT_SPEED = "moveFoeTeamMultSpeed";
    private static final String STATUS_MULT_SPEED_ANY = "statusMultSpeedAny";
    private static final String STATUS_MULT_SPEED = "statusMultSpeed";
    private static final String ABILITY_IMMU_MULT_SPEED_ANY = "abilityImmuMultSpeedAny";
    private static final String ABILITY_IMMU_MULT_SPEED = "abilityImmuMultSpeed";
    private static final String COMBO_MULT_SPEED_ANY = "comboMultSpeedAny";
    private static final String COMBO_MULT_SPEED = "comboMultSpeed";
    private static final String CLICK_MOVES_PROTECTING_TYPES = "clickMovesProtectingTypes";
    private static final String GET_TR_MOVES_PROTECTING_TYPES = "getTrMovesProtectingTypes";
    private static final String CLICK_MOVES_GLOBAL_BREAK_IMMU_AB = "clickMovesGlobalBreakImmuAb";
    private static final String GET_TR_MOVES_GLOBAL_BREAK_IMMU_AB = "getTrMovesGlobalBreakImmuAb";
    private static final String CLICK_ABILITIES_BREAKABLE = "clickAbilitiesBreakable";
    private static final String GET_TR_ABILITIES_BREAKABLE = "getTrAbilitiesBreakable";
    private static final String CLICK_ABILITIES_IMMU_TYPES = "clickAbilitiesImmuTypes";
    private static final String GET_TR_ABILITIES_IMMU_TYPES = "getTrAbilitiesImmuTypes";
    private static final String CLICK_ITEMS_IMMU_TYPES = "clickItemsImmuTypes";
    private static final String GET_TR_ITEMS_IMMU_TYPES = "getTrItemsImmuTypes";
    private static final String CLICK_ABILITIES_IMMU_ALLIES = "clickAbilitiesImmuAllies";
    private static final String GET_TR_ABILITIES_IMMU_ALLIES = "getTrAbilitiesImmuAllies";
    private static final String CLICK_ABILITIES_IMMU_ALLIES_DAM = "clickAbilitiesImmuAlliesDam";
    private static final String GET_TR_ABILITIES_IMMU_ALLIES_DAM = "getTrAbilitiesImmuAlliesDam";
    private static final String CLICK_ABILITIES_IMMU = "clickAbilitiesImmu";
    private static final String GET_TR_ABILITIES_IMMU = "getTrAbilitiesImmu";
    private static final String CLICK_ITEMS_IMMU = "clickItemsImmu";
    private static final String GET_TR_ITEMS_IMMU = "getTrItemsImmu";
    private static final String CLICK_ABILITIES_IMMU_SEC_EFF_OTHER = "clickAbilitiesImmuSecEffOther";
    private static final String GET_TR_ABILITIES_IMMU_SEC_EFF_OTHER = "getTrAbilitiesImmuSecEffOther";
    private static final String CLICK_ABILITIES_IMMU_SEC_EFF_OWNER = "clickAbilitiesImmuSecEffOwner";
    private static final String GET_TR_ABILITIES_IMMU_SEC_EFF_OWNER = "getTrAbilitiesImmuSecEffOwner";
    private static final String CLICK_ABILITIES_ACHIEVE_TARGET = "clickAbilitiesAchieveTarget";
    private static final String GET_TR_ABILITIES_ACHIEVE_TARGET = "getTrAbilitiesAchieveTarget";
    private static final String CLICK_MOVES_PROTECTING = "clickMovesProtecting";
    private static final String GET_TR_MOVES_PROTECTING = "getTrMovesProtecting";
    private static final String GET_TR_STATISTIC = "getTrStatistic";
    private static final String GET_ANIM_STATISTIC = "getAnimStatistic";
    private static final String GET_ANIM_ABSORB = "getAnimAbsorb";
    private static final String MAX_LEVEL = "maxLevel";
    private static final String MAX_EV = "maxEv";
    private static final String MAX_IV = "maxIv";
    private static final String HAPPINESS_MAX = "happinessMax";
    private static final String BEGIN = "begin";
    private static final String MINI_MAP = "miniMap";
    private static final String UNLOCKED_CITY = "unlockedCity";
    private static final String NB_MAX_TEAM = "nbMaxTeam";
    private static final String MIN_LEVEL = "minLevel";
    private static final String NB_MAX_MOVES = "nbMaxMoves";
    private static final String MAX_PP = "maxPp";
    private static final String NB_NEC_STEPS_INCR_HAPPINESS = "nbNecStepsIncrHappiness";
    private static final String NB_MAX_STEPS_SAME_EVO_BASE = "nbMaxStepsSameEvoBase";
    private static final String NB_MAX_STEPS = "nbMaxSteps";
    private static final String POKEMON_DEFAULT_EGG_GROUP = "pokemonDefaultEggGroup";
    private static final String DEFAULT_MONEY = "defaultMoney";
    private static final String TM = "tm";
    private static final String HM = "hm";
    private static final String TYPES = "types";
    private static final String LANGUAGES = "languages";
    private static final String DEFAULT_BOOST_VALUE = "defaultBoostValue";
    private static final String PRIVATING_MOVES = "privatingMoves";
    private static final String MOVES_HEALING_SUBSTITUTE = "movesHealingSubstitute";
    private static final String ABILITIES_SENT_BEGIN_WEATHER = "abilitiesSentBeginWeather";
    private static final String ITEMS_SENT_BEGIN_WEATHER = "itemsSentBeginWeather";
    private static final String ITEMS_SENT_BEGIN_OTHER = "itemsSentBeginOther";
    private static final String CHANGING_TYPES_ABILITIES = "changingTypesAbilities";
    private static final String COPY_ABILITIES = "copyAbilities";
    private static final String ABILITIES_SENT_STATIS = "abilitiesSentStatis";
    private static final String SUBSTITUTING_MOVES = "substitutingMoves";
    private static final String ABILITIES_PRIO = "abilitiesPrio";
    private static final String SLOW_ABILITIES = "slowAbilities";
    private static final String SLOW_ITEMS = "slowItems";
    private static final String REVERSE_SPEED_MOVES = "reverseSpeedMoves";
    private static final String BERRY_SPEED = "berrySpeed";
    private static final String ITEM_SPEED = "itemSpeed";
    private static final String ABILITIES_SWITCH = "abilitiesSwitch";
    private static final String DELETED_STATUS_SWITCH = "deletedStatusSwitch";
    private static final String ENTRY_HAZARD = "entryHazard";
    private static final String BEGIN_ROUND_STATUS = "beginRoundStatus";
    private static final String DELETE_STATUS_MOVE = "deleteStatusMove";
    private static final String IMMU_STATUS_ABILITY = "immuStatusAbility";
    private static final String AUTO_DAMAGE = "autoDamage";
    private static final String MAP_AUTO_DAMAGE = "mapAutoDamage";
    private static final String PREPA_ROUND_MOVES = "prepaRoundMoves";
    private static final String SPEED_PREPARING_ITEMS = "speedPreparingItems";
    private static final String DISAPPEARING_ROUND_MOVES = "disappearingRoundMoves";
    private static final String RECHARGE_MOVES = "rechargeMoves";
    private static final String IMMU_RECHARGING = "immuRecharging";
    private static final String MOVES_INVOKING = "movesInvoking";
    private static final String COPY_MOVE_TYPES_AB = "copyMoveTypesAb";
    private static final String MOVES_THIEVING = "movesThieving";
    private static final String MOVES_SEC_EFF_ITEMS = "movesSecEffItems";
    private static final String MOVES_ATTRACTING = "movesAttracting";
    private static final String BEGIN_ROUND_STATUS_FOE = "beginRoundStatusFoe";
    private static final String PRESSURE_ABILITIES = "pressureAbilities";
    private static final String PROTECT_ABILITIES = "protectAbilities";
    private static final String PROTECT_ITEMS = "protectItems";
    private static final String PROTECT_MOVES = "protectMoves";
    private static final String EFF_MOVES = "effMoves";
    private static final String MOVES_MIRROR = "movesMirror";
    private static final String ABILITIES_PART_STATIS = "abilitiesPartStatis";
    private static final String MOVES_TEAM = "movesTeam";
    private static final String ABILITIES_FIGHTER_STATIS_VAR = "abilitiesFighterStatisVar";
    private static final String ABILITIES_RATE_STATIS = "abilitiesRateStatis";
    private static final String COMBO_EVT_STAT = "comboEvtStat";
    private static final String ABILITIES_FIGHTER_STATIS = "abilitiesFighterStatis";
    private static final String ITEMS_FIGHTER_STATIS = "itemsFighterStatis";
    private static final String SUCCESSFUL_STATUS = "successfulStatus";
    private static final String GLOBAL_MOVES_STATUS = "globalMovesStatus";
    private static final String ABILITIES_PART_STATUS = "abilitiesPartStatus";
    private static final String ABILITIES_FIGHTER_STATUS = "abilitiesFighterStatus";
    private static final String ITEMS_FIGHTER_STATUS = "itemsFighterStatus";
    private static final String LAWS_RATES = "lawsRates";
    private static final String MOVES_PROT_AGAINST_KO = "movesProtAgainstKo";
    private static final String MIN_HP_NOT_KO = "minHpNotKo";
    private static final String ITEMS_PROT_AGAINST_KO = "itemsProtAgainstKo";
    private static final String MOVES_CANNOT_KO = "movesCannotKo";
    private static final String ITEMS_ABS = "itemsAbs";
    private static final String ABILITIES_REV_ABS = "abilitiesRevAbs";
    private static final String ABILITIES_DAMAGE_STATIS = "abilitiesDamageStatis";
    private static final String ABILITIES_CHANGING_TYPES_DAMAGE = "abilitiesChangingTypesDamage";
    private static final String ABILITIES_TAKING_ITEM = "abilitiesTakingItem";
    private static final String ABILITIES_STATIS_VAR_USER = "abilitiesStatisVarUser";
    private static final String ABILITIES_STATUS = "abilitiesStatus";
    private static final String ABILITIES_COPY_AB = "abilitiesCopyAb";
    private static final String RECOIL_ITEMS = "recoilItems";
    private static final String RECOIL_ABILITIES = "recoilAbilities";
    private static final String ABILITIES_KO_TARGET = "abilitiesKoTarget";
    private static final String MOVES_KO_TARGET = "movesKoTarget";
    private static final String BERRY_USER = "berryUser";
    private static final String BERRY_TARGET = "berryTarget";
    private static final String ABILITIES_END_ROUND = "abilitiesEndRound";
    private static final String BERRY_END_ROUND = "berryEndRound";
    private static final String MOVES_CHANGING_ATT_ORDER = "movesChangingAttOrder";
    private static final String RATES = "rates";
    private static final String VAR_RATES = "varRates";
    private static final String WON_HAPPINESS_POINTS_LEVEL = "wonHappinessPointsLevel";
    private static final String HAPPINESS_POINTS = "happinessPoints";
    private static final String DAMGE_FORMULA = "damgeFormula";
    private static final String MAP_VAR = "mapVar";
    private static final String STRONG_MOVE = "strongMove";
    private static final String DAMAGING_MOVES = "damagingMoves";
    private static final String ITEMS_USER_POWER = "itemsUserPower";
    private static final String MOVES_USER_POWER = "movesUserPower";
    private static final String MOVES_TARGET_POWER = "movesTargetPower";
    private static final String ABILITIES_USER_POWER = "abilitiesUserPower";
    private static final String MOVES_USER_ALLY_DAMAGE = "movesUserAllyDamage";
    private static final String ABILITIES_TARGET_DAMAGE = "abilitiesTargetDamage";
    private static final String MOVES_TARGET_TEAM_DAMAGE = "movesTargetTeamDamage";
    private static final String ABILITIES_USER_IGN_TARGET_TEAM = "abilitiesUserIgnTargetTeam";
    private static final String ABILITIES_GLOBAL = "abilitiesGlobal";
    private static final String MOVES_GLOBAL = "movesGlobal";
    private static final String ITEMS_USER_DAMAGE = "itemsUserDamage";
    private static final String ABILITIES_USER_DAMAGE = "abilitiesUserDamage";
    private static final String MOVES_INVOK_DAMAGE = "movesInvokDamage";
    private static final String ITEMS_TARGET_DAMAGE = "itemsTargetDamage";
    private static final String MOVES_GLOBAL_PREPA_DAMAGE = "movesGlobalPrepaDamage";
    private static final String STATUS_DAMAGE = "statusDamage";
    private static final String ABILITIES_USER_TARGET_DAMAGE = "abilitiesUserTargetDamage";
    private static final String ABILITIES_USER_STAB_DAMAGE = "abilitiesUserStabDamage";
    private static final String MOVES_TYPES_DEF_ITEM = "movesTypesDefItem";
    private static final String ITEMS_TYPES_DEF = "itemsTypesDef";
    private static final String MOVES_TYPES_DEF_WEATHER = "movesTypesDefWeather";
    private static final String ABILITIES_TYPE_DEF_MOVES = "abilitiesTypeDefMoves";
    private static final String MOVES_TYPE_DEF_MOVES = "movesTypeDefMoves";
    private static final String MOVES_CHANGE_TYPE_MOVES = "movesChangeTypeMoves";
    private static final String MOVES_GLOBAL_BREAK_IMMU = "movesGlobalBreakImmu";
    private static final String MOVES_UNPROTECTING_TYPES = "movesUnprotectingTypes";
    private static final String ABILITIES_BREAK_IMMU = "abilitiesBreakImmu";
    private static final String ITEMS_CANCEL_IMMU = "itemsCancelImmu";
    private static final String EFFICIENCY = "efficiency";
    private static final String MOVES_IGN_LOW_ATT = "movesIgnLowAtt";
    private static final String MOVES_IGN_INC_DEF = "movesIgnIncDef";
    private static final String ABILITIES_BOOSTING_STAT = "abilitiesBoostingStat";
    private static final String ITEMS_BOOSTING_STAT = "itemsBoostingStat";
    private static final String ITEMS_MULT_STAT = "itemsMultStat";
    private static final String ABILITIES_MULT_STAT = "abilitiesMultStat";
    private static final String MOVES_GLOBAL_MULT_STAT = "movesGlobalMultStat";
    private static final String MOVES_TEAM_MULT_STAT = "movesTeamMultStat";
    private static final String ABILITIES_ALLY_MULT_STAT = "abilitiesAllyMultStat";
    private static final String MOVES_FOE_TEAM_MULT_STAT = "movesFoeTeamMultStat";
    private static final String STATUS_MULT_STAT = "statusMultStat";
    private static final String ABILITIES_IMMU_MULT_STAT = "abilitiesImmuMultStat";
    private static final String COMBO_MULT_STAT = "comboMultStat";
    private static final String ABILITIES_BREAK_PROTECT_MOVES = "abilitiesBreakProtectMoves";
    private static final String MOVES_IGN_ACC = "movesIgnAcc";
    private static final String MOVES_IGN_EVA = "movesIgnEva";
    private static final String MOVES_GLOBAL_ACC = "movesGlobalAcc";
    private static final String ABILITIES_IMMU_CH = "abilitiesImmuCh";
    private static final String MOVES_BOOST_CH = "movesBoostCh";
    private static final String ABILITES_MULT_EVT_CH = "abilitesMultEvtCh";
    private static final String ABILITES_MULT_RATE_CH = "abilitesMultRateCh";
    private static final String RATE_FORMULA = "rateFormula";
    private static final String BOOSTS = "boosts";
    private static final String RATE_FORMULA_CH = "rateFormulaCh";
    private static final String BOOSTS_CH = "boostsCh";
    private static final String MOVES_PROTECTING_TYPES = "movesProtectingTypes";
    private static final String MOVES_GLOBAL_BREAK_IMMU_AB = "movesGlobalBreakImmuAb";
    private static final String ABILITIES_BREAKABLE = "abilitiesBreakable";
    private static final String ABILITIES_IMMU_TYPES = "abilitiesImmuTypes";
    private static final String ITEMS_IMMU_TYPES = "itemsImmuTypes";
    private static final String ABILITIES_IMMU_ALLIES = "abilitiesImmuAllies";
    private static final String ABILITIES_IMMU_ALLIES_DAM = "abilitiesImmuAlliesDam";
    private static final String ABILITIES_IMMU = "abilitiesImmu";
    private static final String ITEMS_IMMU = "itemsImmu";
    private static final String ABILITIES_IMMU_SEC_EFF_OTHER = "abilitiesImmuSecEffOther";
    private static final String ABILITIES_IMMU_SEC_EFF_OWNER = "abilitiesImmuSecEffOwner";
    private static final String ABILITIES_ACHIEVE_TARGET = "abilitiesAchieveTarget";
    private static final String MOVES_PROTECTING = "movesProtecting";
    private static final String CATCHING_FORMULA = "catchingFormula";
    private static final String VAR_CATCHING_FORMULA = "varCatchingFormula";
    private static final String FLEEING_FORMULA = "fleeingFormula";
    private static final String VAR_FLEEING_FORMULA = "varFleeingFormula";
    private static final String STATISTIC_ANIM = "statisticAnim";

    public static void build(PokemonStandards _std) {
        buildFightHelpBean(_std);
        buildGeneralHelpBean(_std);
        buildLangsBean(_std);
        buildLanguageElementKey(_std);
        buildLanguageElementStringKey(_std);
    }
    private static void buildFightHelpBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_FIGHT_HELP_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(DEFAULT_BOOST_VALUE,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(PRIVATING_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_HEALING_SUBSTITUTE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_SENT_BEGIN_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_SENT_BEGIN_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_SENT_BEGIN_OTHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(CHANGING_TYPES_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(COPY_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_SENT_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SUBSTITUTING_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_PRIO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SLOW_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SLOW_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(REVERSE_SPEED_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(BERRY_SPEED, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEM_SPEED, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_SWITCH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(DELETED_STATUS_SWITCH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ENTRY_HAZARD, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(BEGIN_ROUND_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(DELETE_STATUS_MOVE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_STATUS_ABILITY, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(AUTO_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MAP_AUTO_DAMAGE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(PREPA_ROUND_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SPEED_PREPARING_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(DISAPPEARING_ROUND_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(RECHARGE_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(IMMU_RECHARGING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_INVOKING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(COPY_MOVE_TYPES_AB, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_THIEVING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_SEC_EFF_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_ATTRACTING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(BEGIN_ROUND_STATUS_FOE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(PRESSURE_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(PROTECT_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(PROTECT_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(PROTECT_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(EFF_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_MIRROR, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_PART_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_FIGHTER_STATIS_VAR, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_RATE_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(COMBO_EVT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_FIGHTER_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_FIGHTER_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(SUCCESSFUL_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(GLOBAL_MOVES_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_PART_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_FIGHTER_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_FIGHTER_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(LAWS_RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MOVES_PROT_AGAINST_KO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MIN_HP_NOT_KO,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(ITEMS_PROT_AGAINST_KO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_CANNOT_KO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_ABS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_REV_ABS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_DAMAGE_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_CHANGING_TYPES_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_TAKING_ITEM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_STATIS_VAR_USER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_COPY_AB, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(RECOIL_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(RECOIL_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_KO_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_KO_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(BERRY_USER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(BERRY_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(BERRY_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_CHANGING_ATT_ORDER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(VAR_RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(WON_HAPPINESS_POINTS_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(HAPPINESS_POINTS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(DAMGE_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MAP_VAR, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(STRONG_MOVE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(DAMAGING_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_USER_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_USER_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_TARGET_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_USER_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_USER_ALLY_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_TARGET_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_TARGET_TEAM_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_USER_IGN_TARGET_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_GLOBAL, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_GLOBAL, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_USER_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_USER_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_INVOK_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_TARGET_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_GLOBAL_PREPA_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(STATUS_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_USER_TARGET_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_USER_STAB_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_TYPES_DEF_ITEM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_TYPES_DEF, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_TYPES_DEF_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_TYPE_DEF_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_TYPE_DEF_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_CHANGE_TYPE_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_GLOBAL_BREAK_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_UNPROTECTING_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_BREAK_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_CANCEL_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(EFFICIENCY, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MOVES_IGN_LOW_ATT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_IGN_INC_DEF, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_BOOSTING_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_BOOSTING_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_GLOBAL_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_TEAM_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_ALLY_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_FOE_TEAM_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(STATUS_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(COMBO_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_BREAK_PROTECT_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_IGN_ACC, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_IGN_EVA, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_GLOBAL_ACC, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_BOOST_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITES_MULT_EVT_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITES_MULT_RATE_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(RATE_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(BOOSTS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(RATE_FORMULA_CH,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(BOOSTS_CH, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(MOVES_PROTECTING_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_GLOBAL_BREAK_IMMU_AB, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_BREAKABLE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_IMMU_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU_ALLIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU_ALLIES_DAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ITEMS_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU_SEC_EFF_OTHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_IMMU_SEC_EFF_OWNER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(ABILITIES_ACHIEVE_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES_PROTECTING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(CATCHING_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(VAR_CATCHING_FORMULA, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(FLEEING_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(VAR_FLEEING_FORMULA, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(STATISTIC_ANIM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_PRIVATING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_PRIVATING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_HEALING_SUBSTITUTE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_HEALING_SUBSTITUTE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_DEFAULT_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_DEFAULT_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_SENT_BEGIN_OTH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_SENT_BEGIN_OTH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_CHANGING_TYPES_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_CHANGING_TYPES_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_COPY_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_COPY_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_SENT_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_SENT_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_SUBSTITUTING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_SUBSTITUTING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_PRIO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_PRIO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_SLOW_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_SLOW_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_SLOW_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_SLOW_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_REVERSE_SPEED_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_REVERSE_SPEED_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_BERRY_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_BERRY_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEM_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEM_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_DELETED_STATUS_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_DELETED_STATUS_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ENTRY_HAZARD,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ENTRY_HAZARD,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_BEGIN_ROUND_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_BEGIN_ROUND_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_DELETE_STATUS_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_DELETE_STATUS_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_IMMU_STATUS_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(HAS_LAW_FOR_ATTACK_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(HAS_LAW_FOR_ATTACK,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(HAS_LAW_FOR_HEAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(HAS_LAW_FOR_HEAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_AUTO_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_AUTO_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_FOMULA,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_PREPA_ROUND_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_PREPA_ROUND_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_SPEED_PREPARING_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_SPEED_PREPARING_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_DISAPPEARING_USER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_RECHARGE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_IMMU_RECHARGING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_IMMU_RECHARGING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_INVOKING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_INVOKING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_COPY_MOVE_TYPES_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_COPY_MOVE_TYPES_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_THIEVING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_THIEVING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_SEC_EFF_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_SEC_EFF_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_ATTRACTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_ATTRACTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_BEGIN_ROUND_STATUS_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_BEGIN_ROUND_STATUS_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_PRESSURE_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_PRESSURE_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_PROTECT_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_PROTECT_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_PROTECT_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_PROTECT_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_EFF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_EFF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_MIRROR,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_MIRROR,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_PART_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_PART_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IMMU_STATIS_TEAM_MOVE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IMMU_STATIS_TEAM_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_FIGHTER_STATIS_VAR,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_FIGHTER_STATIS_VAR,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_RATE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_RATE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_COMBO_EVT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_COMBO_EVT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_SUCCESSFUL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_SUCCESSFUL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_GLOBAL_MOVES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_GLOBAL_MOVES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_PART_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_PART_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IMMU_STATUS_TEAM_MOVE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IMMU_STATUS_TEAM_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_LAW_RATE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_CANNOT_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_CANNOT_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_REV_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_REV_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_DAMAGE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_DAMAGE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_CHANGING_TYPES_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_CHANGING_TYPES_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_TAKING_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_TAKING_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_STATIS_VAR_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_STATIS_VAR_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_COPY_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_COPY_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_RECOIL_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_RECOIL_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_RECOIL_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_RECOIL_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_BERRY_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_BERRY_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_BERRY_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_BERRY_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_BERRY_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_BERRY_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ATTACK_FIRST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ATTACK_LAST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_CHANGING_ATT_ORDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_CHANGING_ATT_ORDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ATTACK_LAST_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_DIFFICULTY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(WITH_CONST_DAMAGE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(WITH_CONST_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_DAMAGING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_DAMAGING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(WITH_RAND_DAMAGE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(WITH_RAND_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(WITH_MULT_DAMAGE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(WITH_MULT_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_TARGET_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_TARGET_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_USER_ALLYA_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_USER_ALLY_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_TARGET_TEAM_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_TARGET_TEAM_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_IGN_TARGET_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_IGN_TARGET_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_INVOK_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_INVOK_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_PREPA_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_PREPA_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_STATUS_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_STATUS_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_STAB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_STAB_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_STAB_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_TYPES_DEF_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_TYPES_DEF_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_TYPES_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_TYPES_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_TYPES_DEF_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_TYPES_DEF_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_CHANGE_TYPE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_CHANGE_TYPE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_CHANGE_TYPE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_UNPROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_UNPROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_CANCEL_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_CANCEL_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(NEXT_ROW_AFTER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_EFFICIENCY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_IGN_LOW_ATT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_LOW_ATT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_IGN_INC_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_INC_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_BOOST_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_BOOST_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_TEAM_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_ALLY_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_ALLY_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_FOE_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_FOE_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(STATUS_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_STATUS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_STATUS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(COMBO_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_COMBO_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_COMBO_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_BREAK_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_BREAK_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_IGN_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_IGN_EVA,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_EVA,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_BOOST_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_BOOST_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_TEAM_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(STATUS_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(COMBO_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_BOOST_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_BOOST_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_TEAM_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(STATUS_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(COMBO_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IMMU_CH_TEAM_MOVE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IMMU_CH_TEAM_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_CH_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_BOOST_CH,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_CH_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_BOOST_CH,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_BOOST_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_BOOST_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_MULT_EVT_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_MULT_EVT_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_MULT_RATE_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_MULT_RATE_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_BOOST_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_BOOST_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ITEM_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_TEAM_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(STATUS_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(COMBO_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_PROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_PROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_BREAK_IMMU_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_BREAK_IMMU_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_BREAKABLE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_BREAKABLE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_ALLIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_ALLIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_ALLIES_DAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_ALLIES_DAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ITEMS_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ITEMS_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_SEC_EFF_OTHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_SEC_EFF_OTHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_SEC_EFF_OWNER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_SEC_EFF_OWNER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_ABILITIES_ACHIEVE_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITIES_ACHIEVE_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVES_PROTECTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_MOVES_PROTECTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ANIM_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ANIM_ABSORB,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_FIGHT_HELP_BEAN, type_);
    }
    private static void buildGeneralHelpBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_GENERAL_HELP_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(MAX_LEVEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(MAX_EV,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(MAX_IV,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(HAPPINESS_MAX,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(BEGIN,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(MINI_MAP, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(UNLOCKED_CITY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(NB_MAX_TEAM,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(MIN_LEVEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(NB_MAX_MOVES,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(MAX_PP,_std.getAliasPrimLong(),false,false,type_));
        fields_.add(new StandardField(NB_NEC_STEPS_INCR_HAPPINESS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(NB_MAX_STEPS_SAME_EVO_BASE,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(NB_MAX_STEPS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(POKEMON_DEFAULT_EGG_GROUP, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(DEFAULT_MONEY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(TM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(HM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_MAP_WIDTH,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(IS_FIRST_ROW,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_PLACE_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_MINI_MAP_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_GENDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_LEVEL,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(FIRST_POKEMON_HAS_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MOVES_AT_LEVEL,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TM_PRICE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(CLICK_HM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_HM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_IMAGE_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_COLOR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_GENERAL_HELP_BEAN, type_);
    }
    private static void buildLangsBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_LANGS_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(LANGUAGES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_LANG,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_GENDERS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_GENDER,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_BOOLEANS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_BOOLEAN,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_ENVIRONMENTS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_ENVIRONMENT,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_STATISTICS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_STATISTIC,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_TARGETS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_TARGET,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_CATEGORIES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_CATEGORY,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_TYPES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_TYPE,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_POKEMON,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_POKEMON,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_MOVES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_MOVE,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_ITEMS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_ITEM,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_ABILITIES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_ABILITY,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_STATUS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_STATUS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_MATH,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_MATH,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_DESC,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_ROW_DESC,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_LANGS_BEAN, type_);
    }
    private static void buildLanguageElementKey(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_LANGUAGE_ELEMENT_KEY, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_LANGUAGE_ELEMENT_KEY, type_);
    }
    private static void buildLanguageElementStringKey(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_LANGUAGE_ELEMENT_STRING_KEY, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_LANGUAGE_ELEMENT_STRING_KEY, type_);
    }
    public static ResultErrorStd getResultFightHelpBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FightHelpBean instance_ = (FightHelpBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DEFAULT_BOOST_VALUE)) {
            res_.setResult(new IntStruct(instance_.getDefaultBoostValue()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PRIVATING_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getPrivatingMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_HEALING_SUBSTITUTE)) {
            res_.setResult(new DefaultStruct(instance_.getMovesHealingSubstitute(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_SENT_BEGIN_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesSentBeginWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_SENT_BEGIN_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getItemsSentBeginWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_SENT_BEGIN_OTHER)) {
            res_.setResult(new DefaultStruct(instance_.getItemsSentBeginOther(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CHANGING_TYPES_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getChangingTypesAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COPY_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getCopyAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_SENT_STATIS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesSentStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SUBSTITUTING_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getSubstitutingMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_PRIO)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesPrio(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SLOW_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getSlowAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SLOW_ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getSlowItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REVERSE_SPEED_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getReverseSpeedMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BERRY_SPEED)) {
            res_.setResult(new DefaultStruct(instance_.getBerrySpeed(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEM_SPEED)) {
            res_.setResult(new DefaultStruct(instance_.getItemSpeed(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_SWITCH)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesSwitch(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DELETED_STATUS_SWITCH)) {
            res_.setResult(new DefaultStruct(instance_.getDeletedStatusSwitch(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENTRY_HAZARD)) {
            res_.setResult(new DefaultStruct(instance_.getEntryHazard(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BEGIN_ROUND_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getBeginRoundStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DELETE_STATUS_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getDeleteStatusMove(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMMU_STATUS_ABILITY)) {
            res_.setResult(new DefaultStruct(instance_.getImmuStatusAbility(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,AUTO_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getAutoDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_AUTO_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMapAutoDamage(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PREPA_ROUND_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getPrepaRoundMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SPEED_PREPARING_ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getSpeedPreparingItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DISAPPEARING_ROUND_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getDisappearingRoundMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RECHARGE_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getRechargeMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMMU_RECHARGING)) {
            res_.setResult(new DefaultStruct(instance_.getImmuRecharging(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_INVOKING)) {
            res_.setResult(new DefaultStruct(instance_.getMovesInvoking(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COPY_MOVE_TYPES_AB)) {
            res_.setResult(new DefaultStruct(instance_.getCopyMoveTypesAb(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_THIEVING)) {
            res_.setResult(new DefaultStruct(instance_.getMovesThieving(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_SEC_EFF_ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getMovesSecEffItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_ATTRACTING)) {
            res_.setResult(new DefaultStruct(instance_.getMovesAttracting(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BEGIN_ROUND_STATUS_FOE)) {
            res_.setResult(new DefaultStruct(instance_.getBeginRoundStatusFoe(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PRESSURE_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getPressureAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PROTECT_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getProtectAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PROTECT_ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getProtectItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PROTECT_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getProtectMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EFF_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getEffMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_MIRROR)) {
            res_.setResult(new DefaultStruct(instance_.getMovesMirror(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_PART_STATIS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesPartStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TEAM)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_FIGHTER_STATIS_VAR)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesFighterStatisVar(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_RATE_STATIS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesRateStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COMBO_EVT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getComboEvtStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_FIGHTER_STATIS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesFighterStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_FIGHTER_STATIS)) {
            res_.setResult(new DefaultStruct(instance_.getItemsFighterStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SUCCESSFUL_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getSuccessfulStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,GLOBAL_MOVES_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getGlobalMovesStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_PART_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesPartStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_FIGHTER_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesFighterStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_FIGHTER_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getItemsFighterStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAWS_RATES)) {
            res_.setResult(new DefaultStruct(instance_.getLawsRates(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_PROT_AGAINST_KO)) {
            res_.setResult(new DefaultStruct(instance_.getMovesProtAgainstKo(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MIN_HP_NOT_KO)) {
            res_.setResult(new RateStruct(instance_.getMinHpNotKo(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_PROT_AGAINST_KO)) {
            res_.setResult(new DefaultStruct(instance_.getItemsProtAgainstKo(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_CANNOT_KO)) {
            res_.setResult(new DefaultStruct(instance_.getMovesCannotKo(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_ABS)) {
            res_.setResult(new DefaultStruct(instance_.getItemsAbs(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_REV_ABS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesRevAbs(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_DAMAGE_STATIS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesDamageStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_CHANGING_TYPES_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesChangingTypesDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_TAKING_ITEM)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesTakingItem(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_STATIS_VAR_USER)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesStatisVarUser(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_COPY_AB)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesCopyAb(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RECOIL_ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getRecoilItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RECOIL_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getRecoilAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_KO_TARGET)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesKoTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_KO_TARGET)) {
            res_.setResult(new DefaultStruct(instance_.getMovesKoTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BERRY_USER)) {
            res_.setResult(new DefaultStruct(instance_.getBerryUser(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BERRY_TARGET)) {
            res_.setResult(new DefaultStruct(instance_.getBerryTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BERRY_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getBerryEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_CHANGING_ATT_ORDER)) {
            res_.setResult(new DefaultStruct(instance_.getMovesChangingAttOrder(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATES)) {
            res_.setResult(new DefaultStruct(instance_.getRates(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,VAR_RATES)) {
            res_.setResult(new DefaultStruct(instance_.getVarRates(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WON_HAPPINESS_POINTS_LEVEL)) {
            res_.setResult(new RateStruct(instance_.getWonHappinessPointsLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HAPPINESS_POINTS)) {
            res_.setResult(new IntStruct(instance_.getHappinessPoints()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMGE_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getDamgeFormula()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAP_VAR)) {
            res_.setResult(new DefaultStruct(instance_.getMapVar(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STRONG_MOVE)) {
            res_.setResult(new RateStruct(instance_.getStrongMove(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGING_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getDamagingMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_USER_POWER)) {
            res_.setResult(new DefaultStruct(instance_.getItemsUserPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_USER_POWER)) {
            res_.setResult(new DefaultStruct(instance_.getMovesUserPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TARGET_POWER)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTargetPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_USER_POWER)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesUserPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_USER_ALLY_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMovesUserAllyDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_TARGET_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesTargetDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TARGET_TEAM_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTargetTeamDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_USER_IGN_TARGET_TEAM)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesUserIgnTargetTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_GLOBAL)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesGlobal(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_GLOBAL)) {
            res_.setResult(new DefaultStruct(instance_.getMovesGlobal(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_USER_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getItemsUserDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_USER_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesUserDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_INVOK_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMovesInvokDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_TARGET_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getItemsTargetDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_GLOBAL_PREPA_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMovesGlobalPrepaDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATUS_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getStatusDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_USER_TARGET_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesUserTargetDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_USER_STAB_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesUserStabDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TYPES_DEF_ITEM)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTypesDefItem(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_TYPES_DEF)) {
            res_.setResult(new DefaultStruct(instance_.getItemsTypesDef(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TYPES_DEF_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTypesDefWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_TYPE_DEF_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesTypeDefMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TYPE_DEF_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTypeDefMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMovesChangeTypeMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_GLOBAL_BREAK_IMMU)) {
            res_.setResult(new DefaultStruct(instance_.getMovesGlobalBreakImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_UNPROTECTING_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getMovesUnprotectingTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_BREAK_IMMU)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesBreakImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_CANCEL_IMMU)) {
            res_.setResult(new DefaultStruct(instance_.getItemsCancelImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EFFICIENCY)) {
            res_.setResult(new DefaultStruct(instance_.getEfficiency(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_IGN_LOW_ATT)) {
            res_.setResult(new DefaultStruct(instance_.getMovesIgnLowAtt(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_IGN_INC_DEF)) {
            res_.setResult(new DefaultStruct(instance_.getMovesIgnIncDef(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_BOOSTING_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesBoostingStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_BOOSTING_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getItemsBoostingStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getItemsMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_GLOBAL_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getMovesGlobalMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_TEAM_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getMovesTeamMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_ALLY_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesAllyMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_FOE_TEAM_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getMovesFoeTeamMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATUS_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getStatusMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmuMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COMBO_MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getComboMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_BREAK_PROTECT_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesBreakProtectMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_IGN_ACC)) {
            res_.setResult(new DefaultStruct(instance_.getMovesIgnAcc(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_IGN_EVA)) {
            res_.setResult(new DefaultStruct(instance_.getMovesIgnEva(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_GLOBAL_ACC)) {
            res_.setResult(new DefaultStruct(instance_.getMovesGlobalAcc(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU_CH)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmuCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_BOOST_CH)) {
            res_.setResult(new DefaultStruct(instance_.getMovesBoostCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITES_MULT_EVT_CH)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitesMultEvtCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITES_MULT_RATE_CH)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitesMultRateCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getRateFormula()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BOOSTS)) {
            res_.setResult(new DefaultStruct(instance_.getBoosts(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_FORMULA_CH)) {
            res_.setResult(new StringStruct(instance_.getRateFormulaCh()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BOOSTS_CH)) {
            res_.setResult(new DefaultStruct(instance_.getBoostsCh(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_PROTECTING_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getMovesProtectingTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_GLOBAL_BREAK_IMMU_AB)) {
            res_.setResult(new DefaultStruct(instance_.getMovesGlobalBreakImmuAb(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_BREAKABLE)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesBreakable(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmuTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_IMMU_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getItemsImmuTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU_ALLIES)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmuAllies(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU_ALLIES_DAM)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmuAlliesDam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEMS_IMMU)) {
            res_.setResult(new DefaultStruct(instance_.getItemsImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU_SEC_EFF_OTHER)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmuSecEffOther(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_IMMU_SEC_EFF_OWNER)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesImmuSecEffOwner(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITIES_ACHIEVE_TARGET)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesAchieveTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_PROTECTING)) {
            res_.setResult(new DefaultStruct(instance_.getMovesProtecting(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATCHING_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getCatchingFormula()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,VAR_CATCHING_FORMULA)) {
            res_.setResult(new DefaultStruct(instance_.getVarCatchingFormula(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,FLEEING_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getFleeingFormula()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,VAR_FLEEING_FORMULA)) {
            res_.setResult(new DefaultStruct(instance_.getVarFleeingFormula(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATISTIC_ANIM)) {
            res_.setResult(new DefaultStruct(instance_.getStatisticAnim(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultGeneralHelpBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        GeneralHelpBean instance_ = (GeneralHelpBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,MAX_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getMaxLevel()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAX_EV)) {
            res_.setResult(new IntStruct(instance_.getMaxEv()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAX_IV)) {
            res_.setResult(new IntStruct(instance_.getMaxIv()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HAPPINESS_MAX)) {
            res_.setResult(new IntStruct(instance_.getHappinessMax()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BEGIN)) {
            res_.setResult(new StringStruct(instance_.getBegin()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MINI_MAP)) {
            res_.setResult(new DefaultStruct(instance_.getMiniMap(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,UNLOCKED_CITY)) {
            res_.setResult(new StringStruct(instance_.getUnlockedCity()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_MAX_TEAM)) {
            res_.setResult(new IntStruct(instance_.getNbMaxTeam()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MIN_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getMinLevel()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_MAX_MOVES)) {
            res_.setResult(new IntStruct(instance_.getNbMaxMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MAX_PP)) {
            res_.setResult(new LongStruct(instance_.getMaxPp()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_NEC_STEPS_INCR_HAPPINESS)) {
            res_.setResult(new IntStruct(instance_.getNbNecStepsIncrHappiness()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_MAX_STEPS_SAME_EVO_BASE)) {
            res_.setResult(new IntStruct(instance_.getNbMaxStepsSameEvoBase()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_MAX_STEPS)) {
            res_.setResult(new IntStruct(instance_.getNbMaxSteps()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,POKEMON_DEFAULT_EGG_GROUP)) {
            res_.setResult(new DefaultStruct(instance_.getPokemonDefaultEggGroup(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DEFAULT_MONEY)) {
            res_.setResult(new RateStruct(instance_.getDefaultMoney(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TM)) {
            res_.setResult(new DefaultStruct(instance_.getTm(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HM)) {
            res_.setResult(new DefaultStruct(instance_.getHm(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultLangsBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        LangsBean instance_ = (LangsBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,LANGUAGES)) {
            res_.setResult(new DefaultStruct(instance_.getLanguages(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFightHelpBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        FightHelpBean instance_ = (FightHelpBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_PRIVATING_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickPrivatingMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_PRIVATING_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrPrivatingMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_HEALING_SUBSTITUTE)) {
            res_.setResult(new StringStruct(instance_.clickMovesHealingSubstitute((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_HEALING_SUBSTITUTE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesHealingSubstitute((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_DEFAULT_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickDefaultMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_DEFAULT_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrDefaultMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesSentBegin((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesSentBegin((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.clickItemsSentBegin((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.getTrItemsSentBegin((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_SENT_BEGIN_OTH)) {
            res_.setResult(new StringStruct(instance_.clickItemsSentBeginOth((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_SENT_BEGIN_OTH)) {
            res_.setResult(new StringStruct(instance_.getTrItemsSentBeginOth((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_CHANGING_TYPES_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickChangingTypesAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_CHANGING_TYPES_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrChangingTypesAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_COPY_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickCopyAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_COPY_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrCopyAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_SENT_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesSentStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_SENT_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesSentStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_SUBSTITUTING_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickSubstitutingMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_SUBSTITUTING_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrSubstitutingMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_PRIO)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesPrio((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_PRIO)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesPrio((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_SLOW_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickSlowAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_SLOW_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrSlowAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_SLOW_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickSlowItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_SLOW_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrSlowItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_REVERSE_SPEED_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickReverseSpeedMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_REVERSE_SPEED_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrReverseSpeedMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_BERRY_SPEED)) {
            res_.setResult(new StringStruct(instance_.clickBerrySpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_BERRY_SPEED)) {
            res_.setResult(new StringStruct(instance_.getTrBerrySpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM_SPEED)) {
            res_.setResult(new StringStruct(instance_.clickItemSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEM_SPEED)) {
            res_.setResult(new StringStruct(instance_.getTrItemSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_SWITCH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesSwitch((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_SWITCH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesSwitch((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_DELETED_STATUS_SWITCH)) {
            res_.setResult(new StringStruct(instance_.clickDeletedStatusSwitch((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_DELETED_STATUS_SWITCH)) {
            res_.setResult(new StringStruct(instance_.getTrDeletedStatusSwitch((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ENTRY_HAZARD)) {
            res_.setResult(new StringStruct(instance_.clickEntryHazard((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ENTRY_HAZARD)) {
            res_.setResult(new StringStruct(instance_.getTrEntryHazard((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_BEGIN_ROUND_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickBeginRoundStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_BEGIN_ROUND_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrBeginRoundStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_DELETE_STATUS_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickDeleteStatusMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_DELETE_STATUS_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrDeleteStatusMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_IMMU_STATUS_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickImmuStatusAbility((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_IMMU_STATUS_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatusAbility((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,HAS_LAW_FOR_ATTACK_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.hasLawForAttackAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,HAS_LAW_FOR_ATTACK)) {
            res_.setResult(BooleanStruct.of(instance_.hasLawForAttack((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,HAS_LAW_FOR_HEAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.hasLawForHealAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,HAS_LAW_FOR_HEAL)) {
            res_.setResult(BooleanStruct.of(instance_.hasLawForHeal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_AUTO_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAutoDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_AUTO_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAutoDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_FOMULA)) {
            res_.setResult(new StringStruct(instance_.getFomula((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_PREPA_ROUND_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickPrepaRoundMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_PREPA_ROUND_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrPrepaRoundMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_SPEED_PREPARING_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickSpeedPreparingItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_SPEED_PREPARING_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrSpeedPreparingItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_DISAPPEARING_USER)) {
            res_.setResult(BooleanStruct.of(instance_.isDisappearingUser((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_RECHARGE_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrRechargeMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_IMMU_RECHARGING)) {
            res_.setResult(new StringStruct(instance_.clickImmuRecharging((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_IMMU_RECHARGING)) {
            res_.setResult(new StringStruct(instance_.getTrImmuRecharging((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_INVOKING)) {
            res_.setResult(new StringStruct(instance_.clickMovesInvoking((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_INVOKING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesInvoking((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_COPY_MOVE_TYPES_AB)) {
            res_.setResult(new StringStruct(instance_.clickCopyMoveTypesAb((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_COPY_MOVE_TYPES_AB)) {
            res_.setResult(new StringStruct(instance_.getTrCopyMoveTypesAb((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_THIEVING)) {
            res_.setResult(new StringStruct(instance_.clickMovesThieving((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_THIEVING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesThieving((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_SEC_EFF_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickMovesSecEffItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_SEC_EFF_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrMovesSecEffItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_ATTRACTING)) {
            res_.setResult(new StringStruct(instance_.clickMovesAttracting((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_ATTRACTING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesAttracting((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_BEGIN_ROUND_STATUS_FOE)) {
            res_.setResult(new StringStruct(instance_.clickBeginRoundStatusFoe((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_BEGIN_ROUND_STATUS_FOE)) {
            res_.setResult(new StringStruct(instance_.getTrBeginRoundStatusFoe((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_PRESSURE_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickPressureAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_PRESSURE_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrPressureAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_PROTECT_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickProtectAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_PROTECT_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrProtectAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_PROTECT_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickProtectItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_PROTECT_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrProtectItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickProtectMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrProtectMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_EFF_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickEffMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_EFF_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrEffMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_MIRROR)) {
            res_.setResult(new StringStruct(instance_.clickMovesMirror((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_MIRROR)) {
            res_.setResult(new StringStruct(instance_.getTrMovesMirror((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_PART_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesPartStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_PART_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesPartStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IMMU_STATIS_TEAM_MOVE_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.immuStatisTeamMoveAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IMMU_STATIS_TEAM_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.immuStatisTeamMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.clickMovesTeam((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTeam((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_FIGHTER_STATIS_VAR)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesFighterStatisVar((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_FIGHTER_STATIS_VAR)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesFighterStatisVar((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_RATE_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesRateStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_RATE_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesRateStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_COMBO_EVT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickComboEvtStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_COMBO_EVT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrComboEvtStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesFighterStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesFighterStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickItemsFighterStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrItemsFighterStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_SUCCESSFUL_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickSuccessfulStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_SUCCESSFUL_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrSuccessfulStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_GLOBAL_MOVES_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickGlobalMovesStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_GLOBAL_MOVES_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrGlobalMovesStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_PART_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesPartStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_PART_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesPartStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IMMU_STATUS_TEAM_MOVE_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.immuStatusTeamMoveAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IMMU_STATUS_TEAM_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.immuStatusTeamMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesFighterStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesFighterStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickItemsFighterStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrItemsFighterStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_LAW_RATE)) {
            res_.setResult(new StringStruct(instance_.getTrLawRate((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.clickMovesProtAgainstKo((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.getTrMovesProtAgainstKo((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.clickItemsProtAgainstKo((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.getTrItemsProtAgainstKo((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_CANNOT_KO)) {
            res_.setResult(new StringStruct(instance_.clickMovesCannotKo((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_CANNOT_KO)) {
            res_.setResult(new StringStruct(instance_.getTrMovesCannotKo((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_ABS)) {
            res_.setResult(new StringStruct(instance_.clickItemsAbs((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_ABS)) {
            res_.setResult(new StringStruct(instance_.getTrItemsAbs((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_REV_ABS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesRevAbs((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_REV_ABS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesRevAbs((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_DAMAGE_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesDamageStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_DAMAGE_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesDamageStatis((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_CHANGING_TYPES_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesChangingTypesDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_CHANGING_TYPES_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesChangingTypesDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_TAKING_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesTakingItem((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_TAKING_ITEM)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesTakingItem((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_STATIS_VAR_USER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesStatisVarUser((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_STATIS_VAR_USER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesStatisVarUser((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesStatus((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_COPY_AB)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesCopyAb((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_COPY_AB)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesCopyAb((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_RECOIL_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickRecoilItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_RECOIL_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrRecoilItems((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_RECOIL_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickRecoilAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_RECOIL_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrRecoilAbilities((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesKoTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesKoTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickMovesKoTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrMovesKoTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_BERRY_USER)) {
            res_.setResult(new StringStruct(instance_.clickBerryUser((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_BERRY_USER)) {
            res_.setResult(new StringStruct(instance_.getTrBerryUser((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_BERRY_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickBerryTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_BERRY_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrBerryTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesEndRound((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesEndRound((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_BERRY_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.clickBerryEndRound((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_BERRY_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.getTrBerryEndRound((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ATTACK_FIRST)) {
            res_.setResult(BooleanStruct.of(instance_.attackFirst()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ATTACK_LAST)) {
            res_.setResult(BooleanStruct.of(instance_.attackLast((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_CHANGING_ATT_ORDER)) {
            res_.setResult(new StringStruct(instance_.clickMovesChangingAttOrder((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_CHANGING_ATT_ORDER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesChangingAttOrder((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ATTACK_LAST_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.attackLastAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_DIFFICULTY)) {
            res_.setResult(new StringStruct(instance_.getTrDifficulty((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,WITH_CONST_DAMAGE_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.withConstDamageAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,WITH_CONST_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.withConstDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_DAMAGING_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickDamagingMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_DAMAGING_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrDamagingMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,WITH_RAND_DAMAGE_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.withRandDamageAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,WITH_RAND_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.withRandDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,WITH_MULT_DAMAGE_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.withMultDamageAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,WITH_MULT_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.withMultDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.clickItemsUserPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrItemsUserPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.clickMovesUserPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesUserPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_TARGET_POWER)) {
            res_.setResult(new StringStruct(instance_.clickMovesTargetPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_TARGET_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTargetPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserPower((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_USER_ALLYA_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesUserAllyaDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_USER_ALLY_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesUserAllyDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesTargetDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesTargetDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_TARGET_TEAM_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesTargetTeamDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_TARGET_TEAM_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTargetTeamDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_USER_IGN_TARGET_TEAM)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserIgnTargetTeam((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_USER_IGN_TARGET_TEAM)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserIgnTargetTeam((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesGlobal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesGlobal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickItemsUserDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrItemsUserDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_INVOK_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesInvokDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_INVOK_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesInvokDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickItemsTargetDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrItemsTargetDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_GLOBAL_PREPA_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalPrepaDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_GLOBAL_PREPA_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalPrepaDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_STATUS_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickStatusDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_STATUS_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrStatusDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_USER_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserTargetDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_USER_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserTargetDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_STAB)) {
            res_.setResult(new StringStruct(instance_.getStab()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_USER_STAB_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserStabDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_USER_STAB_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserStabDamage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_TYPES_DEF_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickMovesTypesDefItem((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_TYPES_DEF_ITEM)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTypesDefItem((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_TYPES_DEF)) {
            res_.setResult(new StringStruct(instance_.clickItemsTypesDef((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_TYPES_DEF)) {
            res_.setResult(new StringStruct(instance_.getTrItemsTypesDef((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_TYPES_DEF_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickMovesTypesDefWeather((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_TYPES_DEF_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTypesDefWeather((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesTypeDefMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesTypeDefMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesChangeTypeMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesChangeTypeMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickMovesTypeDefMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTypeDefMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesChangeTypeMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_GLOBAL_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalBreakImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_GLOBAL_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalBreakImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_UNPROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickMovesUnprotectingTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_UNPROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesUnprotectingTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBreakImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBreakImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_CANCEL_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickItemsCancelImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_CANCEL_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrItemsCancelImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,NEXT_ROW_AFTER)) {
            res_.setResult(BooleanStruct.of(instance_.nextRowAfter((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_EFFICIENCY)) {
            res_.setResult(new StringStruct(instance_.getEfficiency((Integer)_args[0],(Integer)_args[1])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_IGN_LOW_ATT)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnLowAtt((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_IGN_LOW_ATT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnLowAtt((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_IGN_INC_DEF)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnIncDef((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_IGN_INC_DEF)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnIncDef((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBoostingStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBoostingStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.clickItemsBoostingStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrItemsBoostingStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.itemMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickItemsMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrItemsMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_GLOBAL_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_GLOBAL_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickMovesTeamMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTeamMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_ALLY_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesAllyMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_ALLY_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesAllyMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_FOE_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickMovesFoeTeamMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_FOE_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesFoeTeamMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_STATUS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickStatusMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_STATUS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrStatusMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_NORMAL_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultNormalAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_NORMAL)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultNormal((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_COMBO_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickComboMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_COMBO_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrComboMultStat((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_BREAK_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBreakProtectMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_BREAK_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBreakProtectMoves((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_IGN_ACC)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnAcc((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_IGN_ACC)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnAcc((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_IGN_EVA)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnEva((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_IGN_EVA)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnEva((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_GLOBAL_ACC)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalAcc((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_GLOBAL_ACC)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalAcc((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.itemMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_ACCURACY_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultAccuracyAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_ACCURACY)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultAccuracy((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_EVASINESS_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultEvasinessAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_EVASINESS)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultEvasiness((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IMMU_CH_TEAM_MOVE_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.immuChTeamMoveAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IMMU_CH_TEAM_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.immuChTeamMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU_CH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU_CH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_CH_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostChAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_CH)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_CH_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostChAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_CH)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_BOOST_CH)) {
            res_.setResult(new StringStruct(instance_.clickMovesBoostCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_BOOST_CH)) {
            res_.setResult(new StringStruct(instance_.getTrMovesBoostCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_MULT_EVT_CH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesMultEvtCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_MULT_EVT_CH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesMultEvtCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_MULT_RATE_CH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesMultRateCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_MULT_RATE_CH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesMultRateCh((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_BOOST_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.abilityBoostSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_BOOST_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.itemBoostSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.itemMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ITEM_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.itemMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.abilityMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_GLOBAL_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.moveGlobalMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_TEAM_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.moveTeamMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_ALLY_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.abilityAllyMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,MOVE_FOE_TEAM_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.moveFoeTeamMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,STATUS_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.statusMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABILITY_IMMU_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.abilityImmuMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_SPEED_ANY)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultSpeedAny()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,COMBO_MULT_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.comboMultSpeed((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_PROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickMovesProtectingTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_PROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesProtectingTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_GLOBAL_BREAK_IMMU_AB)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalBreakImmuAb((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_GLOBAL_BREAK_IMMU_AB)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalBreakImmuAb((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_BREAKABLE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBreakable((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_BREAKABLE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBreakable((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickItemsImmuTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrItemsImmuTypes((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU_ALLIES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuAllies((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU_ALLIES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuAllies((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU_ALLIES_DAM)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuAlliesDam((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU_ALLIES_DAM)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuAlliesDam((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickItemsImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ITEMS_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrItemsImmu((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU_SEC_EFF_OTHER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuSecEffOther((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU_SEC_EFF_OTHER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuSecEffOther((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_IMMU_SEC_EFF_OWNER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuSecEffOwner((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_IMMU_SEC_EFF_OWNER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuSecEffOwner((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES_ACHIEVE_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesAchieveTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_ABILITIES_ACHIEVE_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesAchieveTarget((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVES_PROTECTING)) {
            res_.setResult(new StringStruct(instance_.clickMovesProtecting((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_MOVES_PROTECTING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesProtecting((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getTrStatistic((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ANIM_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getAnimStatistic((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ANIM_ABSORB)) {
            res_.setResult(new StringStruct(instance_.getAnimAbsorb()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodGeneralHelpBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        GeneralHelpBean instance_ = (GeneralHelpBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MAP_WIDTH)) {
            res_.setResult(new IntStruct(instance_.getMapWidth()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FIRST_ROW)) {
            res_.setResult(BooleanStruct.of(instance_.isFirstRow((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PLACE_NAME)) {
            res_.setResult(new StringStruct(instance_.getPlaceName((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MINI_MAP_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniMapImage((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getLevel()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,FIRST_POKEMON_HAS_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.firstPokemonHasItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVES_AT_LEVEL)) {
            res_.setResult(new DefaultStruct(instance_.getMovesAtLevel(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_POKEMON)) {
            res_.setResult(new StringStruct(instance_.clickPokemon((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getTrPokemon((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_TM)) {
            res_.setResult(new StringStruct(instance_.clickTm((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_TM)) {
            res_.setResult(new StringStruct(instance_.getTrTm((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TM_PRICE)) {
            res_.setResult(new StringStruct(instance_.getTmPrice((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_HM)) {
            res_.setResult(new StringStruct(instance_.clickHm((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_HM)) {
            res_.setResult(new StringStruct(instance_.getTrHm((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrType((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE_TYPE)) {
            res_.setResult(new StringStruct(instance_.getImageType((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_COLOR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getColorType((Integer)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodLangsBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        LangsBean instance_ = (LangsBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_TR_LANG)) {
            res_.setResult(new StringStruct(instance_.getTrLang((Integer)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_GENDERS)) {
            res_.setResult(new DefaultStruct(instance_.getKeysGenders(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_GENDER)) {
            res_.setResult(new DefaultStruct(instance_.getRowGender((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_BOOLEANS)) {
            res_.setResult(new DefaultStruct(instance_.getKeysBooleans(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_BOOLEAN)) {
            res_.setResult(new DefaultStruct(instance_.getRowBoolean((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_ENVIRONMENTS)) {
            res_.setResult(new DefaultStruct(instance_.getKeysEnvironments(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_ENVIRONMENT)) {
            res_.setResult(new DefaultStruct(instance_.getRowEnvironment((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_STATISTICS)) {
            res_.setResult(new DefaultStruct(instance_.getKeysStatistics(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_STATISTIC)) {
            res_.setResult(new DefaultStruct(instance_.getRowStatistic((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_TARGETS)) {
            res_.setResult(new DefaultStruct(instance_.getKeysTargets(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_TARGET)) {
            res_.setResult(new DefaultStruct(instance_.getRowTarget((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_CATEGORIES)) {
            res_.setResult(new DefaultStruct(instance_.getKeysCategories(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_CATEGORY)) {
            res_.setResult(new DefaultStruct(instance_.getRowCategory((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getKeysTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_TYPE)) {
            res_.setResult(new DefaultStruct(instance_.getRowType((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getKeysPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getRowPokemon((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getKeysMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getRowMove((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getKeysItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_ITEM)) {
            res_.setResult(new DefaultStruct(instance_.getRowItem((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getKeysAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_ABILITY)) {
            res_.setResult(new DefaultStruct(instance_.getRowAbility((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getKeysStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getRowStatus((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_MATH)) {
            res_.setResult(new DefaultStruct(instance_.getKeysMath(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_MATH)) {
            res_.setResult(new DefaultStruct(instance_.getRowMath((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEYS_DESC)) {
            res_.setResult(new DefaultStruct(instance_.getKeysDesc(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ROW_DESC)) {
            res_.setResult(new DefaultStruct(instance_.getRowDesc((Integer)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
}
