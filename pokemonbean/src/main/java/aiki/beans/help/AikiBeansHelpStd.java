package aiki.beans.help;
import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
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
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.formathtml.structs.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.structs.StdStruct;
import code.formathtml.util.BeanNatLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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

    public static void build(BeanLgNames _std) {
        buildFightHelpBean(_std);
        buildGeneralHelpBean(_std);
        buildLangsBean(_std);
        buildLanguageElementKey(_std);
        buildLanguageElementStringKey(_std);
    }
    private static void buildFightHelpBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_FIGHT_HELP_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(DEFAULT_BOOST_VALUE,new StandardField(DEFAULT_BOOST_VALUE,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(PRIVATING_MOVES,new StandardField(PRIVATING_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_HEALING_SUBSTITUTE,new StandardField(MOVES_HEALING_SUBSTITUTE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_SENT_BEGIN_WEATHER,new StandardField(ABILITIES_SENT_BEGIN_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_SENT_BEGIN_WEATHER,new StandardField(ITEMS_SENT_BEGIN_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_SENT_BEGIN_OTHER,new StandardField(ITEMS_SENT_BEGIN_OTHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CHANGING_TYPES_ABILITIES,new StandardField(CHANGING_TYPES_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(COPY_ABILITIES,new StandardField(COPY_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_SENT_STATIS,new StandardField(ABILITIES_SENT_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SUBSTITUTING_MOVES,new StandardField(SUBSTITUTING_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_PRIO,new StandardField(ABILITIES_PRIO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SLOW_ABILITIES,new StandardField(SLOW_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SLOW_ITEMS,new StandardField(SLOW_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(REVERSE_SPEED_MOVES,new StandardField(REVERSE_SPEED_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(BERRY_SPEED,new StandardField(BERRY_SPEED, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEM_SPEED,new StandardField(ITEM_SPEED, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_SWITCH,new StandardField(ABILITIES_SWITCH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DELETED_STATUS_SWITCH,new StandardField(DELETED_STATUS_SWITCH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ENTRY_HAZARD,new StandardField(ENTRY_HAZARD, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(BEGIN_ROUND_STATUS,new StandardField(BEGIN_ROUND_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DELETE_STATUS_MOVE,new StandardField(DELETE_STATUS_MOVE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(IMMU_STATUS_ABILITY,new StandardField(IMMU_STATUS_ABILITY, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(AUTO_DAMAGE,new StandardField(AUTO_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MAP_AUTO_DAMAGE,new StandardField(MAP_AUTO_DAMAGE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(PREPA_ROUND_MOVES,new StandardField(PREPA_ROUND_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SPEED_PREPARING_ITEMS,new StandardField(SPEED_PREPARING_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DISAPPEARING_ROUND_MOVES,new StandardField(DISAPPEARING_ROUND_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(RECHARGE_MOVES,new StandardField(RECHARGE_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(IMMU_RECHARGING,new StandardField(IMMU_RECHARGING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_INVOKING,new StandardField(MOVES_INVOKING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(COPY_MOVE_TYPES_AB,new StandardField(COPY_MOVE_TYPES_AB, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_THIEVING,new StandardField(MOVES_THIEVING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_SEC_EFF_ITEMS,new StandardField(MOVES_SEC_EFF_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_ATTRACTING,new StandardField(MOVES_ATTRACTING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(BEGIN_ROUND_STATUS_FOE,new StandardField(BEGIN_ROUND_STATUS_FOE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(PRESSURE_ABILITIES,new StandardField(PRESSURE_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(PROTECT_ABILITIES,new StandardField(PROTECT_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(PROTECT_ITEMS,new StandardField(PROTECT_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(PROTECT_MOVES,new StandardField(PROTECT_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(EFF_MOVES,new StandardField(EFF_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_MIRROR,new StandardField(MOVES_MIRROR, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_PART_STATIS,new StandardField(ABILITIES_PART_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_TEAM,new StandardField(MOVES_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_FIGHTER_STATIS_VAR,new StandardField(ABILITIES_FIGHTER_STATIS_VAR, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_RATE_STATIS,new StandardField(ABILITIES_RATE_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(COMBO_EVT_STAT,new StandardField(COMBO_EVT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_FIGHTER_STATIS,new StandardField(ABILITIES_FIGHTER_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_FIGHTER_STATIS,new StandardField(ITEMS_FIGHTER_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SUCCESSFUL_STATUS,new StandardField(SUCCESSFUL_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(GLOBAL_MOVES_STATUS,new StandardField(GLOBAL_MOVES_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_PART_STATUS,new StandardField(ABILITIES_PART_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_FIGHTER_STATUS,new StandardField(ABILITIES_FIGHTER_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_FIGHTER_STATUS,new StandardField(ITEMS_FIGHTER_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(LAWS_RATES,new StandardField(LAWS_RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MOVES_PROT_AGAINST_KO,new StandardField(MOVES_PROT_AGAINST_KO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MIN_HP_NOT_KO,new StandardField(MIN_HP_NOT_KO,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(ITEMS_PROT_AGAINST_KO,new StandardField(ITEMS_PROT_AGAINST_KO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_CANNOT_KO,new StandardField(MOVES_CANNOT_KO, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_ABS,new StandardField(ITEMS_ABS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_REV_ABS,new StandardField(ABILITIES_REV_ABS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_DAMAGE_STATIS,new StandardField(ABILITIES_DAMAGE_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_CHANGING_TYPES_DAMAGE,new StandardField(ABILITIES_CHANGING_TYPES_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_TAKING_ITEM,new StandardField(ABILITIES_TAKING_ITEM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_STATIS_VAR_USER,new StandardField(ABILITIES_STATIS_VAR_USER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_STATUS,new StandardField(ABILITIES_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_COPY_AB,new StandardField(ABILITIES_COPY_AB, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(RECOIL_ITEMS,new StandardField(RECOIL_ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(RECOIL_ABILITIES,new StandardField(RECOIL_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_KO_TARGET,new StandardField(ABILITIES_KO_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_KO_TARGET,new StandardField(MOVES_KO_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(BERRY_USER,new StandardField(BERRY_USER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(BERRY_TARGET,new StandardField(BERRY_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_END_ROUND,new StandardField(ABILITIES_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(BERRY_END_ROUND,new StandardField(BERRY_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_CHANGING_ATT_ORDER,new StandardField(MOVES_CHANGING_ATT_ORDER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(RATES,new StandardField(RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(VAR_RATES,new StandardField(VAR_RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(WON_HAPPINESS_POINTS_LEVEL,new StandardField(WON_HAPPINESS_POINTS_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HAPPINESS_POINTS,new StandardField(HAPPINESS_POINTS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(DAMGE_FORMULA,new StandardField(DAMGE_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.put(MAP_VAR,new StandardField(MAP_VAR, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(STRONG_MOVE,new StandardField(STRONG_MOVE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(DAMAGING_MOVES,new StandardField(DAMAGING_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_USER_POWER,new StandardField(ITEMS_USER_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_USER_POWER,new StandardField(MOVES_USER_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_TARGET_POWER,new StandardField(MOVES_TARGET_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_USER_POWER,new StandardField(ABILITIES_USER_POWER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_USER_ALLY_DAMAGE,new StandardField(MOVES_USER_ALLY_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_TARGET_DAMAGE,new StandardField(ABILITIES_TARGET_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_TARGET_TEAM_DAMAGE,new StandardField(MOVES_TARGET_TEAM_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_USER_IGN_TARGET_TEAM,new StandardField(ABILITIES_USER_IGN_TARGET_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_GLOBAL,new StandardField(ABILITIES_GLOBAL, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_GLOBAL,new StandardField(MOVES_GLOBAL, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_USER_DAMAGE,new StandardField(ITEMS_USER_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_USER_DAMAGE,new StandardField(ABILITIES_USER_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_INVOK_DAMAGE,new StandardField(MOVES_INVOK_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_TARGET_DAMAGE,new StandardField(ITEMS_TARGET_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_GLOBAL_PREPA_DAMAGE,new StandardField(MOVES_GLOBAL_PREPA_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(STATUS_DAMAGE,new StandardField(STATUS_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_USER_TARGET_DAMAGE,new StandardField(ABILITIES_USER_TARGET_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_USER_STAB_DAMAGE,new StandardField(ABILITIES_USER_STAB_DAMAGE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_TYPES_DEF_ITEM,new StandardField(MOVES_TYPES_DEF_ITEM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_TYPES_DEF,new StandardField(ITEMS_TYPES_DEF, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_TYPES_DEF_WEATHER,new StandardField(MOVES_TYPES_DEF_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_TYPE_DEF_MOVES,new StandardField(ABILITIES_TYPE_DEF_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_TYPE_DEF_MOVES,new StandardField(MOVES_TYPE_DEF_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_CHANGE_TYPE_MOVES,new StandardField(MOVES_CHANGE_TYPE_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_GLOBAL_BREAK_IMMU,new StandardField(MOVES_GLOBAL_BREAK_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_UNPROTECTING_TYPES,new StandardField(MOVES_UNPROTECTING_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_BREAK_IMMU,new StandardField(ABILITIES_BREAK_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_CANCEL_IMMU,new StandardField(ITEMS_CANCEL_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(TYPES,new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(EFFICIENCY,new StandardField(EFFICIENCY, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MOVES_IGN_LOW_ATT,new StandardField(MOVES_IGN_LOW_ATT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_IGN_INC_DEF,new StandardField(MOVES_IGN_INC_DEF, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_BOOSTING_STAT,new StandardField(ABILITIES_BOOSTING_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_BOOSTING_STAT,new StandardField(ITEMS_BOOSTING_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_MULT_STAT,new StandardField(ITEMS_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_MULT_STAT,new StandardField(ABILITIES_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_GLOBAL_MULT_STAT,new StandardField(MOVES_GLOBAL_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_TEAM_MULT_STAT,new StandardField(MOVES_TEAM_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_ALLY_MULT_STAT,new StandardField(ABILITIES_ALLY_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_FOE_TEAM_MULT_STAT,new StandardField(MOVES_FOE_TEAM_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(STATUS_MULT_STAT,new StandardField(STATUS_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU_MULT_STAT,new StandardField(ABILITIES_IMMU_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(COMBO_MULT_STAT,new StandardField(COMBO_MULT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_BREAK_PROTECT_MOVES,new StandardField(ABILITIES_BREAK_PROTECT_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_IGN_ACC,new StandardField(MOVES_IGN_ACC, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_IGN_EVA,new StandardField(MOVES_IGN_EVA, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_GLOBAL_ACC,new StandardField(MOVES_GLOBAL_ACC, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU_CH,new StandardField(ABILITIES_IMMU_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_BOOST_CH,new StandardField(MOVES_BOOST_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITES_MULT_EVT_CH,new StandardField(ABILITES_MULT_EVT_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITES_MULT_RATE_CH,new StandardField(ABILITES_MULT_RATE_CH, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(RATE_FORMULA,new StandardField(RATE_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.put(BOOSTS,new StandardField(BOOSTS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(RATE_FORMULA_CH,new StandardField(RATE_FORMULA_CH,_std.getAliasString(),false,false,type_));
        fields_.put(BOOSTS_CH,new StandardField(BOOSTS_CH, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MOVES_PROTECTING_TYPES,new StandardField(MOVES_PROTECTING_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_GLOBAL_BREAK_IMMU_AB,new StandardField(MOVES_GLOBAL_BREAK_IMMU_AB, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_BREAKABLE,new StandardField(ABILITIES_BREAKABLE, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU_TYPES,new StandardField(ABILITIES_IMMU_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_IMMU_TYPES,new StandardField(ITEMS_IMMU_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU_ALLIES,new StandardField(ABILITIES_IMMU_ALLIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU_ALLIES_DAM,new StandardField(ABILITIES_IMMU_ALLIES_DAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU,new StandardField(ABILITIES_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ITEMS_IMMU,new StandardField(ITEMS_IMMU, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU_SEC_EFF_OTHER,new StandardField(ABILITIES_IMMU_SEC_EFF_OTHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_IMMU_SEC_EFF_OWNER,new StandardField(ABILITIES_IMMU_SEC_EFF_OWNER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ABILITIES_ACHIEVE_TARGET,new StandardField(ABILITIES_ACHIEVE_TARGET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_PROTECTING,new StandardField(MOVES_PROTECTING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CATCHING_FORMULA,new StandardField(CATCHING_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.put(VAR_CATCHING_FORMULA,new StandardField(VAR_CATCHING_FORMULA, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(FLEEING_FORMULA,new StandardField(FLEEING_FORMULA,_std.getAliasString(),false,false,type_));
        fields_.put(VAR_FLEEING_FORMULA,new StandardField(VAR_FLEEING_FORMULA, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(STATISTIC_ANIM,new StandardField(STATISTIC_ANIM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_PRIVATING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_PRIVATING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_HEALING_SUBSTITUTE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_HEALING_SUBSTITUTE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_DEFAULT_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_DEFAULT_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_SENT_BEGIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_SENT_BEGIN_OTH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_SENT_BEGIN_OTH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_CHANGING_TYPES_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CHANGING_TYPES_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_COPY_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_COPY_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_SENT_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_SENT_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_SUBSTITUTING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SUBSTITUTING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_PRIO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_PRIO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_SLOW_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SLOW_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_SLOW_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SLOW_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_REVERSE_SPEED_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_REVERSE_SPEED_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_BERRY_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BERRY_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEM_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEM_SPEED,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_DELETED_STATUS_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DELETED_STATUS_SWITCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ENTRY_HAZARD,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ENTRY_HAZARD,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_BEGIN_ROUND_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BEGIN_ROUND_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_DELETE_STATUS_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DELETE_STATUS_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_STATUS_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(HAS_LAW_FOR_ATTACK_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(HAS_LAW_FOR_ATTACK,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(HAS_LAW_FOR_HEAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(HAS_LAW_FOR_HEAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_AUTO_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_AUTO_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_FOMULA,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_PREPA_ROUND_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_PREPA_ROUND_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_SPEED_PREPARING_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SPEED_PREPARING_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_DISAPPEARING_USER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_RECHARGE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_RECHARGING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_RECHARGING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_INVOKING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_INVOKING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_COPY_MOVE_TYPES_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_COPY_MOVE_TYPES_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_THIEVING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_THIEVING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_SEC_EFF_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_SEC_EFF_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_ATTRACTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_ATTRACTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_BEGIN_ROUND_STATUS_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BEGIN_ROUND_STATUS_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_PRESSURE_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_PRESSURE_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_PROTECT_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_PROTECT_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_PROTECT_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_PROTECT_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_EFF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_EFF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_MIRROR,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_MIRROR,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_PART_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_PART_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IMMU_STATIS_TEAM_MOVE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IMMU_STATIS_TEAM_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_FIGHTER_STATIS_VAR,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_FIGHTER_STATIS_VAR,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_RATE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_RATE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_COMBO_EVT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_COMBO_EVT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_FIGHTER_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_SUCCESSFUL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SUCCESSFUL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_GLOBAL_MOVES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_GLOBAL_MOVES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_PART_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_PART_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IMMU_STATUS_TEAM_MOVE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IMMU_STATUS_TEAM_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_FIGHTER_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_LAW_RATE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_PROT_AGAINST_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_CANNOT_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_CANNOT_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_REV_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_REV_ABS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_DAMAGE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_DAMAGE_STATIS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_CHANGING_TYPES_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_CHANGING_TYPES_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_TAKING_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_TAKING_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_STATIS_VAR_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_STATIS_VAR_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_COPY_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_COPY_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_RECOIL_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_RECOIL_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_RECOIL_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_RECOIL_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_KO_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_BERRY_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BERRY_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_BERRY_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BERRY_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_BERRY_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BERRY_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ATTACK_FIRST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ATTACK_LAST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_CHANGING_ATT_ORDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_CHANGING_ATT_ORDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ATTACK_LAST_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DIFFICULTY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(WITH_CONST_DAMAGE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(WITH_CONST_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_DAMAGING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DAMAGING_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(WITH_RAND_DAMAGE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(WITH_RAND_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(WITH_MULT_DAMAGE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(WITH_MULT_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TARGET_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TARGET_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_USER_ALLYA_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_USER_ALLY_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TARGET_TEAM_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TARGET_TEAM_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_IGN_TARGET_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_IGN_TARGET_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_INVOK_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_INVOK_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_PREPA_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_PREPA_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_STATUS_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATUS_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_TARGET_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_STAB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_USER_STAB_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_USER_STAB_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TYPES_DEF_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TYPES_DEF_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_TYPES_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_TYPES_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TYPES_DEF_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TYPES_DEF_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_CHANGE_TYPE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_CHANGE_TYPE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TYPE_DEF_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_CHANGE_TYPE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_UNPROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_UNPROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_BREAK_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_CANCEL_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_CANCEL_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(NEXT_ROW_AFTER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_EFFICIENCY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_IGN_LOW_ATT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_LOW_ATT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_IGN_INC_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_INC_DEF,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_BOOST_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_BOOST_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_BOOSTING_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_TEAM_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_ALLY_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_ALLY_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_FOE_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_FOE_TEAM_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(STATUS_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_STATUS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATUS_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_NORMAL_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(COMBO_MULT_NORMAL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_COMBO_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_COMBO_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_BREAK_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_BREAK_PROTECT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_IGN_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_IGN_EVA,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_IGN_EVA,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_ACC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_BOOST_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_BOOST_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_TEAM_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(STATUS_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_ACCURACY_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(COMBO_MULT_ACCURACY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_BOOST_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_BOOST_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_TEAM_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(STATUS_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_EVASINESS_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(COMBO_MULT_EVASINESS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IMMU_CH_TEAM_MOVE_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IMMU_CH_TEAM_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_CH_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_BOOST_CH,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_CH_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_BOOST_CH,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_BOOST_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_BOOST_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_MULT_EVT_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_MULT_EVT_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_MULT_RATE_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_MULT_RATE_CH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_BOOST_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_BOOST_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_BOOST_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_BOOST_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ITEM_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ITEM_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_GLOBAL_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_TEAM_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_TEAM_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_ALLY_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_ALLY_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(MOVE_FOE_TEAM_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(STATUS_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(STATUS_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABILITY_IMMU_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(ABILITY_IMMU_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(COMBO_MULT_SPEED_ANY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(COMBO_MULT_SPEED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_PROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_PROTECTING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_GLOBAL_BREAK_IMMU_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_GLOBAL_BREAK_IMMU_AB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_BREAKABLE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_BREAKABLE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_ALLIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_ALLIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_ALLIES_DAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_ALLIES_DAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEMS_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ITEMS_IMMU,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_SEC_EFF_OTHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_SEC_EFF_OTHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_IMMU_SEC_EFF_OWNER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_IMMU_SEC_EFF_OWNER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITIES_ACHIEVE_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITIES_ACHIEVE_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_PROTECTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_PROTECTING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ANIM_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ANIM_ABSORB,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_FIGHT_HELP_BEAN, type_);
    }
    private static void buildGeneralHelpBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_GENERAL_HELP_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(MAX_LEVEL,new StandardField(MAX_LEVEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(MAX_EV,new StandardField(MAX_EV,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(MAX_IV,new StandardField(MAX_IV,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(HAPPINESS_MAX,new StandardField(HAPPINESS_MAX,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(BEGIN,new StandardField(BEGIN,_std.getAliasString(),false,false,type_));
        fields_.put(MINI_MAP,new StandardField(MINI_MAP, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(UNLOCKED_CITY,new StandardField(UNLOCKED_CITY,_std.getAliasString(),false,false,type_));
        fields_.put(NB_MAX_TEAM,new StandardField(NB_MAX_TEAM,_std.getAliasPrimByte(),false,false,type_));
        fields_.put(MIN_LEVEL,new StandardField(MIN_LEVEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(NB_MAX_MOVES,new StandardField(NB_MAX_MOVES,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(MAX_PP,new StandardField(MAX_PP,_std.getAliasPrimLong(),false,false,type_));
        fields_.put(NB_NEC_STEPS_INCR_HAPPINESS,new StandardField(NB_NEC_STEPS_INCR_HAPPINESS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(NB_MAX_STEPS_SAME_EVO_BASE,new StandardField(NB_MAX_STEPS_SAME_EVO_BASE,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(NB_MAX_STEPS,new StandardField(NB_MAX_STEPS,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(POKEMON_DEFAULT_EGG_GROUP,new StandardField(POKEMON_DEFAULT_EGG_GROUP, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DEFAULT_MONEY,new StandardField(DEFAULT_MONEY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(TM,new StandardField(TM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(HM,new StandardField(HM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(TYPES,new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_MAP_WIDTH,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FIRST_ROW,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_PLACE_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MINI_MAP_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_GENDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_LEVEL,params_,_std.getAliasPrimShort(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FIRST_POKEMON_HAS_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MOVES_AT_LEVEL,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TM_PRICE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_HM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_HM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_COLOR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_GENERAL_HELP_BEAN, type_);
    }
    private static void buildLangsBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_LANGS_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(LANGUAGES,new StandardField(LANGUAGES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_LANG,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_GENDERS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_GENDER,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_BOOLEANS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_BOOLEAN,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_ENVIRONMENTS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_ENVIRONMENT,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_STATISTICS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_STATISTIC,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_TARGETS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_TARGET,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_CATEGORIES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_CATEGORY,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_TYPES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_TYPE,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_POKEMON,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_POKEMON,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_MOVES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_MOVE,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_ITEMS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_ITEM,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_ABILITIES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_ABILITY,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_STATUS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_STATUS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_MATH,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_MATH,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEYS_DESC,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ROW_DESC,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_LANGS_BEAN, type_);
    }
    private static void buildLanguageElementKey(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_LANGUAGE_ELEMENT_KEY, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_LANGUAGE_ELEMENT_KEY, type_);
    }
    private static void buildLanguageElementStringKey(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_LANGUAGE_ELEMENT_STRING_KEY, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_LANGUAGE_ELEMENT_STRING_KEY, type_);
    }
    public static ResultErrorStd getResultFightHelpBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FightHelpBean instance_ = (FightHelpBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DEFAULT_BOOST_VALUE)) {
            res_.setResult(new IntStruct(instance_.getDefaultBoostValue()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PRIVATING_MOVES)) {
            res_.setResult(new StdStruct(instance_.getPrivatingMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_HEALING_SUBSTITUTE)) {
            res_.setResult(new StdStruct(instance_.getMovesHealingSubstitute(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_SENT_BEGIN_WEATHER)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesSentBeginWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_SENT_BEGIN_WEATHER)) {
            res_.setResult(new StdStruct(instance_.getItemsSentBeginWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_SENT_BEGIN_OTHER)) {
            res_.setResult(new StdStruct(instance_.getItemsSentBeginOther(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHANGING_TYPES_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getChangingTypesAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPY_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getCopyAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_SENT_STATIS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesSentStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SUBSTITUTING_MOVES)) {
            res_.setResult(new StdStruct(instance_.getSubstitutingMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_PRIO)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesPrio(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SLOW_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getSlowAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SLOW_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getSlowItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REVERSE_SPEED_MOVES)) {
            res_.setResult(new StdStruct(instance_.getReverseSpeedMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BERRY_SPEED)) {
            res_.setResult(new StdStruct(instance_.getBerrySpeed(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEM_SPEED)) {
            res_.setResult(new StdStruct(instance_.getItemSpeed(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_SWITCH)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesSwitch(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DELETED_STATUS_SWITCH)) {
            res_.setResult(new StdStruct(instance_.getDeletedStatusSwitch(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENTRY_HAZARD)) {
            res_.setResult(new StdStruct(instance_.getEntryHazard(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BEGIN_ROUND_STATUS)) {
            res_.setResult(new StdStruct(instance_.getBeginRoundStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DELETE_STATUS_MOVE)) {
            res_.setResult(new StdStruct(instance_.getDeleteStatusMove(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_STATUS_ABILITY)) {
            res_.setResult(new StdStruct(instance_.getImmuStatusAbility(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,AUTO_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getAutoDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_AUTO_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMapAutoDamage(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PREPA_ROUND_MOVES)) {
            res_.setResult(new StdStruct(instance_.getPrepaRoundMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SPEED_PREPARING_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getSpeedPreparingItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISAPPEARING_ROUND_MOVES)) {
            res_.setResult(new StdStruct(instance_.getDisappearingRoundMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RECHARGE_MOVES)) {
            res_.setResult(new StdStruct(instance_.getRechargeMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_RECHARGING)) {
            res_.setResult(new StdStruct(instance_.getImmuRecharging(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_INVOKING)) {
            res_.setResult(new StdStruct(instance_.getMovesInvoking(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPY_MOVE_TYPES_AB)) {
            res_.setResult(new StdStruct(instance_.getCopyMoveTypesAb(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_THIEVING)) {
            res_.setResult(new StdStruct(instance_.getMovesThieving(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_SEC_EFF_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getMovesSecEffItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_ATTRACTING)) {
            res_.setResult(new StdStruct(instance_.getMovesAttracting(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BEGIN_ROUND_STATUS_FOE)) {
            res_.setResult(new StdStruct(instance_.getBeginRoundStatusFoe(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PRESSURE_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getPressureAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getProtectAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getProtectItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_MOVES)) {
            res_.setResult(new StdStruct(instance_.getProtectMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EFF_MOVES)) {
            res_.setResult(new StdStruct(instance_.getEffMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_MIRROR)) {
            res_.setResult(new StdStruct(instance_.getMovesMirror(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_PART_STATIS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesPartStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TEAM)) {
            res_.setResult(new StdStruct(instance_.getMovesTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_FIGHTER_STATIS_VAR)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesFighterStatisVar(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_RATE_STATIS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesRateStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COMBO_EVT_STAT)) {
            res_.setResult(new StdStruct(instance_.getComboEvtStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_FIGHTER_STATIS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesFighterStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_FIGHTER_STATIS)) {
            res_.setResult(new StdStruct(instance_.getItemsFighterStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SUCCESSFUL_STATUS)) {
            res_.setResult(new StdStruct(instance_.getSuccessfulStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GLOBAL_MOVES_STATUS)) {
            res_.setResult(new StdStruct(instance_.getGlobalMovesStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_PART_STATUS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesPartStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_FIGHTER_STATUS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesFighterStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_FIGHTER_STATUS)) {
            res_.setResult(new StdStruct(instance_.getItemsFighterStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LAWS_RATES)) {
            res_.setResult(new StdStruct(instance_.getLawsRates(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_PROT_AGAINST_KO)) {
            res_.setResult(new StdStruct(instance_.getMovesProtAgainstKo(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MIN_HP_NOT_KO)) {
            res_.setResult(new StdStruct(instance_.getMinHpNotKo(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_PROT_AGAINST_KO)) {
            res_.setResult(new StdStruct(instance_.getItemsProtAgainstKo(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_CANNOT_KO)) {
            res_.setResult(new StdStruct(instance_.getMovesCannotKo(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_ABS)) {
            res_.setResult(new StdStruct(instance_.getItemsAbs(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_REV_ABS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesRevAbs(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_DAMAGE_STATIS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesDamageStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_CHANGING_TYPES_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesChangingTypesDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_TAKING_ITEM)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesTakingItem(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_STATIS_VAR_USER)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesStatisVarUser(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_STATUS)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_COPY_AB)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesCopyAb(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RECOIL_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getRecoilItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RECOIL_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getRecoilAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_KO_TARGET)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesKoTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_KO_TARGET)) {
            res_.setResult(new StdStruct(instance_.getMovesKoTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BERRY_USER)) {
            res_.setResult(new StdStruct(instance_.getBerryUser(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BERRY_TARGET)) {
            res_.setResult(new StdStruct(instance_.getBerryTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BERRY_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getBerryEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_CHANGING_ATT_ORDER)) {
            res_.setResult(new StdStruct(instance_.getMovesChangingAttOrder(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATES)) {
            res_.setResult(new StdStruct(instance_.getRates(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,VAR_RATES)) {
            res_.setResult(new StdStruct(instance_.getVarRates(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WON_HAPPINESS_POINTS_LEVEL)) {
            res_.setResult(new StdStruct(instance_.getWonHappinessPointsLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS_POINTS)) {
            res_.setResult(new IntStruct(instance_.getHappinessPoints()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMGE_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getDamgeFormula()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VAR)) {
            res_.setResult(new StdStruct(instance_.getMapVar(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STRONG_MOVE)) {
            res_.setResult(new StdStruct(instance_.getStrongMove(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGING_MOVES)) {
            res_.setResult(new StdStruct(instance_.getDamagingMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_USER_POWER)) {
            res_.setResult(new StdStruct(instance_.getItemsUserPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_USER_POWER)) {
            res_.setResult(new StdStruct(instance_.getMovesUserPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TARGET_POWER)) {
            res_.setResult(new StdStruct(instance_.getMovesTargetPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_USER_POWER)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesUserPower(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_USER_ALLY_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMovesUserAllyDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_TARGET_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesTargetDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TARGET_TEAM_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMovesTargetTeamDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_USER_IGN_TARGET_TEAM)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesUserIgnTargetTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_GLOBAL)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesGlobal(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_GLOBAL)) {
            res_.setResult(new StdStruct(instance_.getMovesGlobal(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_USER_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getItemsUserDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_USER_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesUserDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_INVOK_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMovesInvokDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_TARGET_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getItemsTargetDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_GLOBAL_PREPA_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMovesGlobalPrepaDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getStatusDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_USER_TARGET_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesUserTargetDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_USER_STAB_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesUserStabDamage(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TYPES_DEF_ITEM)) {
            res_.setResult(new StdStruct(instance_.getMovesTypesDefItem(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_TYPES_DEF)) {
            res_.setResult(new StdStruct(instance_.getItemsTypesDef(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TYPES_DEF_WEATHER)) {
            res_.setResult(new StdStruct(instance_.getMovesTypesDefWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_TYPE_DEF_MOVES)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesTypeDefMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TYPE_DEF_MOVES)) {
            res_.setResult(new StdStruct(instance_.getMovesTypeDefMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new StdStruct(instance_.getMovesChangeTypeMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_GLOBAL_BREAK_IMMU)) {
            res_.setResult(new StdStruct(instance_.getMovesGlobalBreakImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_UNPROTECTING_TYPES)) {
            res_.setResult(new StdStruct(instance_.getMovesUnprotectingTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_BREAK_IMMU)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesBreakImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_CANCEL_IMMU)) {
            res_.setResult(new StdStruct(instance_.getItemsCancelImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPES)) {
            res_.setResult(new StdStruct(instance_.getTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EFFICIENCY)) {
            res_.setResult(new StdStruct(instance_.getEfficiency(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_IGN_LOW_ATT)) {
            res_.setResult(new StdStruct(instance_.getMovesIgnLowAtt(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_IGN_INC_DEF)) {
            res_.setResult(new StdStruct(instance_.getMovesIgnIncDef(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_BOOSTING_STAT)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesBoostingStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_BOOSTING_STAT)) {
            res_.setResult(new StdStruct(instance_.getItemsBoostingStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getItemsMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_GLOBAL_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getMovesGlobalMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TEAM_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getMovesTeamMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_ALLY_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesAllyMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_FOE_TEAM_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getMovesFoeTeamMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getStatusMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmuMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COMBO_MULT_STAT)) {
            res_.setResult(new StdStruct(instance_.getComboMultStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_BREAK_PROTECT_MOVES)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesBreakProtectMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_IGN_ACC)) {
            res_.setResult(new StdStruct(instance_.getMovesIgnAcc(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_IGN_EVA)) {
            res_.setResult(new StdStruct(instance_.getMovesIgnEva(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_GLOBAL_ACC)) {
            res_.setResult(new StdStruct(instance_.getMovesGlobalAcc(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU_CH)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmuCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_BOOST_CH)) {
            res_.setResult(new StdStruct(instance_.getMovesBoostCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITES_MULT_EVT_CH)) {
            res_.setResult(new StdStruct(instance_.getAbilitesMultEvtCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITES_MULT_RATE_CH)) {
            res_.setResult(new StdStruct(instance_.getAbilitesMultRateCh(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getRateFormula()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOSTS)) {
            res_.setResult(new StdStruct(instance_.getBoosts(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_FORMULA_CH)) {
            res_.setResult(new StringStruct(instance_.getRateFormulaCh()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOSTS_CH)) {
            res_.setResult(new StdStruct(instance_.getBoostsCh(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_PROTECTING_TYPES)) {
            res_.setResult(new StdStruct(instance_.getMovesProtectingTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_GLOBAL_BREAK_IMMU_AB)) {
            res_.setResult(new StdStruct(instance_.getMovesGlobalBreakImmuAb(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_BREAKABLE)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesBreakable(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU_TYPES)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmuTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_IMMU_TYPES)) {
            res_.setResult(new StdStruct(instance_.getItemsImmuTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU_ALLIES)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmuAllies(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU_ALLIES_DAM)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmuAlliesDam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS_IMMU)) {
            res_.setResult(new StdStruct(instance_.getItemsImmu(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU_SEC_EFF_OTHER)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmuSecEffOther(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_IMMU_SEC_EFF_OWNER)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesImmuSecEffOwner(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_ACHIEVE_TARGET)) {
            res_.setResult(new StdStruct(instance_.getAbilitiesAchieveTarget(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_PROTECTING)) {
            res_.setResult(new StdStruct(instance_.getMovesProtecting(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CATCHING_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getCatchingFormula()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,VAR_CATCHING_FORMULA)) {
            res_.setResult(new StdStruct(instance_.getVarCatchingFormula(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FLEEING_FORMULA)) {
            res_.setResult(new StringStruct(instance_.getFleeingFormula()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,VAR_FLEEING_FORMULA)) {
            res_.setResult(new StdStruct(instance_.getVarFleeingFormula(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATISTIC_ANIM)) {
            res_.setResult(new StdStruct(instance_.getStatisticAnim(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultGeneralHelpBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        GeneralHelpBean instance_ = (GeneralHelpBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MAX_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getMaxLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_EV)) {
            res_.setResult(new IntStruct(instance_.getMaxEv()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_IV)) {
            res_.setResult(new IntStruct(instance_.getMaxIv()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS_MAX)) {
            res_.setResult(new IntStruct(instance_.getHappinessMax()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BEGIN)) {
            res_.setResult(new StringStruct(instance_.getBegin()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MINI_MAP)) {
            res_.setResult(new StdStruct(instance_.getMiniMap(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,UNLOCKED_CITY)) {
            res_.setResult(new StringStruct(instance_.getUnlockedCity()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_MAX_TEAM)) {
            res_.setResult(new ByteStruct(instance_.getNbMaxTeam()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MIN_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getMinLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_MAX_MOVES)) {
            res_.setResult(new IntStruct(instance_.getNbMaxMoves()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_PP)) {
            res_.setResult(new LongStruct(instance_.getMaxPp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_NEC_STEPS_INCR_HAPPINESS)) {
            res_.setResult(new IntStruct(instance_.getNbNecStepsIncrHappiness()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_MAX_STEPS_SAME_EVO_BASE)) {
            res_.setResult(new ShortStruct(instance_.getNbMaxStepsSameEvoBase()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_MAX_STEPS)) {
            res_.setResult(new ShortStruct(instance_.getNbMaxSteps()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,POKEMON_DEFAULT_EGG_GROUP)) {
            res_.setResult(new StdStruct(instance_.getPokemonDefaultEggGroup(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DEFAULT_MONEY)) {
            res_.setResult(new StdStruct(instance_.getDefaultMoney(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TM)) {
            res_.setResult(new StdStruct(instance_.getTm(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HM)) {
            res_.setResult(new StdStruct(instance_.getHm(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPES)) {
            res_.setResult(new StdStruct(instance_.getTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultLangsBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        LangsBean instance_ = (LangsBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,LANGUAGES)) {
            res_.setResult(new StdStruct(instance_.getLanguages(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFightHelpBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        FightHelpBean instance_ = (FightHelpBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_PRIVATING_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickPrivatingMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_PRIVATING_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrPrivatingMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_HEALING_SUBSTITUTE)) {
            res_.setResult(new StringStruct(instance_.clickMovesHealingSubstitute((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_HEALING_SUBSTITUTE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesHealingSubstitute((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DEFAULT_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickDefaultMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DEFAULT_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrDefaultMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesSentBegin((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesSentBegin((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.clickItemsSentBegin((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_SENT_BEGIN)) {
            res_.setResult(new StringStruct(instance_.getTrItemsSentBegin((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_SENT_BEGIN_OTH)) {
            res_.setResult(new StringStruct(instance_.clickItemsSentBeginOth((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_SENT_BEGIN_OTH)) {
            res_.setResult(new StringStruct(instance_.getTrItemsSentBeginOth((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_CHANGING_TYPES_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickChangingTypesAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CHANGING_TYPES_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrChangingTypesAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_COPY_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickCopyAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_COPY_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrCopyAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_SENT_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesSentStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_SENT_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesSentStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SUBSTITUTING_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickSubstitutingMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_SUBSTITUTING_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrSubstitutingMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_PRIO)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesPrio((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_PRIO)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesPrio((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SLOW_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickSlowAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_SLOW_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrSlowAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SLOW_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickSlowItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_SLOW_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrSlowItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_REVERSE_SPEED_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickReverseSpeedMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_REVERSE_SPEED_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrReverseSpeedMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_BERRY_SPEED)) {
            res_.setResult(new StringStruct(instance_.clickBerrySpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BERRY_SPEED)) {
            res_.setResult(new StringStruct(instance_.getTrBerrySpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEM_SPEED)) {
            res_.setResult(new StringStruct(instance_.clickItemSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEM_SPEED)) {
            res_.setResult(new StringStruct(instance_.getTrItemSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_SWITCH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesSwitch((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_SWITCH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesSwitch((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DELETED_STATUS_SWITCH)) {
            res_.setResult(new StringStruct(instance_.clickDeletedStatusSwitch((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DELETED_STATUS_SWITCH)) {
            res_.setResult(new StringStruct(instance_.getTrDeletedStatusSwitch((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ENTRY_HAZARD)) {
            res_.setResult(new StringStruct(instance_.clickEntryHazard((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ENTRY_HAZARD)) {
            res_.setResult(new StringStruct(instance_.getTrEntryHazard((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_BEGIN_ROUND_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickBeginRoundStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BEGIN_ROUND_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrBeginRoundStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DELETE_STATUS_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickDeleteStatusMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DELETE_STATUS_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrDeleteStatusMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_STATUS_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickImmuStatusAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_STATUS_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatusAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,HAS_LAW_FOR_ATTACK_ANY)) {
            res_.setResult(new BooleanStruct(instance_.hasLawForAttackAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,HAS_LAW_FOR_ATTACK)) {
            res_.setResult(new BooleanStruct(instance_.hasLawForAttack((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,HAS_LAW_FOR_HEAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.hasLawForHealAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,HAS_LAW_FOR_HEAL)) {
            res_.setResult(new BooleanStruct(instance_.hasLawForHeal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_AUTO_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAutoDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_AUTO_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAutoDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_FOMULA)) {
            res_.setResult(new StringStruct(instance_.getFomula((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_PREPA_ROUND_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickPrepaRoundMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_PREPA_ROUND_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrPrepaRoundMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SPEED_PREPARING_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickSpeedPreparingItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_SPEED_PREPARING_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrSpeedPreparingItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_DISAPPEARING_USER)) {
            res_.setResult(new BooleanStruct(instance_.isDisappearingUser((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_RECHARGE_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrRechargeMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_RECHARGING)) {
            res_.setResult(new StringStruct(instance_.clickImmuRecharging((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_RECHARGING)) {
            res_.setResult(new StringStruct(instance_.getTrImmuRecharging((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_INVOKING)) {
            res_.setResult(new StringStruct(instance_.clickMovesInvoking((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_INVOKING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesInvoking((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_COPY_MOVE_TYPES_AB)) {
            res_.setResult(new StringStruct(instance_.clickCopyMoveTypesAb((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_COPY_MOVE_TYPES_AB)) {
            res_.setResult(new StringStruct(instance_.getTrCopyMoveTypesAb((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_THIEVING)) {
            res_.setResult(new StringStruct(instance_.clickMovesThieving((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_THIEVING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesThieving((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_SEC_EFF_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickMovesSecEffItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_SEC_EFF_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrMovesSecEffItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_ATTRACTING)) {
            res_.setResult(new StringStruct(instance_.clickMovesAttracting((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_ATTRACTING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesAttracting((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_BEGIN_ROUND_STATUS_FOE)) {
            res_.setResult(new StringStruct(instance_.clickBeginRoundStatusFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BEGIN_ROUND_STATUS_FOE)) {
            res_.setResult(new StringStruct(instance_.getTrBeginRoundStatusFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_PRESSURE_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickPressureAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_PRESSURE_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrPressureAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_PROTECT_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickProtectAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_PROTECT_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrProtectAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_PROTECT_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickProtectItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_PROTECT_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrProtectItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickProtectMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrProtectMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_EFF_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickEffMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_EFF_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrEffMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_MIRROR)) {
            res_.setResult(new StringStruct(instance_.clickMovesMirror((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_MIRROR)) {
            res_.setResult(new StringStruct(instance_.getTrMovesMirror((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_PART_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesPartStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_PART_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesPartStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IMMU_STATIS_TEAM_MOVE_ANY)) {
            res_.setResult(new BooleanStruct(instance_.immuStatisTeamMoveAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IMMU_STATIS_TEAM_MOVE)) {
            res_.setResult(new BooleanStruct(instance_.immuStatisTeamMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.clickMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_FIGHTER_STATIS_VAR)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesFighterStatisVar((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_FIGHTER_STATIS_VAR)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesFighterStatisVar((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_RATE_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesRateStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_RATE_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesRateStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_COMBO_EVT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickComboEvtStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_COMBO_EVT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrComboEvtStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesFighterStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesFighterStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickItemsFighterStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_FIGHTER_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrItemsFighterStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SUCCESSFUL_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickSuccessfulStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_SUCCESSFUL_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrSuccessfulStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_GLOBAL_MOVES_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickGlobalMovesStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_GLOBAL_MOVES_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrGlobalMovesStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_PART_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesPartStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_PART_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesPartStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IMMU_STATUS_TEAM_MOVE_ANY)) {
            res_.setResult(new BooleanStruct(instance_.immuStatusTeamMoveAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IMMU_STATUS_TEAM_MOVE)) {
            res_.setResult(new BooleanStruct(instance_.immuStatusTeamMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesFighterStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesFighterStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickItemsFighterStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_FIGHTER_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrItemsFighterStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_LAW_RATE)) {
            res_.setResult(new StringStruct(instance_.getTrLawRate((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.clickMovesProtAgainstKo((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.getTrMovesProtAgainstKo((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.clickItemsProtAgainstKo((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_PROT_AGAINST_KO)) {
            res_.setResult(new StringStruct(instance_.getTrItemsProtAgainstKo((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_CANNOT_KO)) {
            res_.setResult(new StringStruct(instance_.clickMovesCannotKo((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_CANNOT_KO)) {
            res_.setResult(new StringStruct(instance_.getTrMovesCannotKo((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_ABS)) {
            res_.setResult(new StringStruct(instance_.clickItemsAbs((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_ABS)) {
            res_.setResult(new StringStruct(instance_.getTrItemsAbs((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_REV_ABS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesRevAbs((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_REV_ABS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesRevAbs((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_DAMAGE_STATIS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesDamageStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_DAMAGE_STATIS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesDamageStatis((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_CHANGING_TYPES_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesChangingTypesDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_CHANGING_TYPES_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesChangingTypesDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_TAKING_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesTakingItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_TAKING_ITEM)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesTakingItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_STATIS_VAR_USER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesStatisVarUser((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_STATIS_VAR_USER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesStatisVarUser((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_COPY_AB)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesCopyAb((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_COPY_AB)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesCopyAb((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_RECOIL_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickRecoilItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_RECOIL_ITEMS)) {
            res_.setResult(new StringStruct(instance_.getTrRecoilItems((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_RECOIL_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickRecoilAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_RECOIL_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.getTrRecoilAbilities((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesKoTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesKoTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickMovesKoTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_KO_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrMovesKoTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_BERRY_USER)) {
            res_.setResult(new StringStruct(instance_.clickBerryUser((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BERRY_USER)) {
            res_.setResult(new StringStruct(instance_.getTrBerryUser((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_BERRY_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickBerryTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BERRY_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrBerryTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesEndRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesEndRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_BERRY_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.clickBerryEndRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BERRY_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.getTrBerryEndRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ATTACK_FIRST)) {
            res_.setResult(new BooleanStruct(instance_.attackFirst()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ATTACK_LAST)) {
            res_.setResult(new BooleanStruct(instance_.attackLast((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_CHANGING_ATT_ORDER)) {
            res_.setResult(new StringStruct(instance_.clickMovesChangingAttOrder((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_CHANGING_ATT_ORDER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesChangingAttOrder((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ATTACK_LAST_ANY)) {
            res_.setResult(new BooleanStruct(instance_.attackLastAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DIFFICULTY)) {
            res_.setResult(new StringStruct(instance_.getTrDifficulty((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,WITH_CONST_DAMAGE_ANY)) {
            res_.setResult(new BooleanStruct(instance_.withConstDamageAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,WITH_CONST_DAMAGE)) {
            res_.setResult(new BooleanStruct(instance_.withConstDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DAMAGING_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickDamagingMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DAMAGING_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrDamagingMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,WITH_RAND_DAMAGE_ANY)) {
            res_.setResult(new BooleanStruct(instance_.withRandDamageAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,WITH_RAND_DAMAGE)) {
            res_.setResult(new BooleanStruct(instance_.withRandDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,WITH_MULT_DAMAGE_ANY)) {
            res_.setResult(new BooleanStruct(instance_.withMultDamageAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,WITH_MULT_DAMAGE)) {
            res_.setResult(new BooleanStruct(instance_.withMultDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.clickItemsUserPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrItemsUserPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.clickMovesUserPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesUserPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TARGET_POWER)) {
            res_.setResult(new StringStruct(instance_.clickMovesTargetPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TARGET_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTargetPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_USER_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserPower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_USER_ALLYA_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesUserAllyaDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_USER_ALLY_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesUserAllyDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesTargetDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesTargetDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TARGET_TEAM_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesTargetTeamDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TARGET_TEAM_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTargetTeamDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_USER_IGN_TARGET_TEAM)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserIgnTargetTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_USER_IGN_TARGET_TEAM)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserIgnTargetTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesGlobal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesGlobal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_GLOBAL)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickItemsUserDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrItemsUserDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_USER_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_INVOK_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesInvokDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_INVOK_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesInvokDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickItemsTargetDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrItemsTargetDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_GLOBAL_PREPA_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalPrepaDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_GLOBAL_PREPA_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalPrepaDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_STATUS_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickStatusDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_STATUS_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrStatusDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_USER_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserTargetDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_USER_TARGET_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserTargetDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_STAB)) {
            res_.setResult(new StringStruct(instance_.getStab()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_USER_STAB_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesUserStabDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_USER_STAB_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesUserStabDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TYPES_DEF_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickMovesTypesDefItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TYPES_DEF_ITEM)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTypesDefItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_TYPES_DEF)) {
            res_.setResult(new StringStruct(instance_.clickItemsTypesDef((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_TYPES_DEF)) {
            res_.setResult(new StringStruct(instance_.getTrItemsTypesDef((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TYPES_DEF_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickMovesTypesDefWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TYPES_DEF_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTypesDefWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesTypeDefMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesTypeDefMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesChangeTypeMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesChangeTypeMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickMovesTypeDefMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TYPE_DEF_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTypeDefMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_CHANGE_TYPE_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesChangeTypeMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_GLOBAL_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalBreakImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_GLOBAL_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalBreakImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_UNPROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickMovesUnprotectingTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_UNPROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesUnprotectingTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBreakImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_BREAK_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBreakImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_CANCEL_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickItemsCancelImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_CANCEL_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrItemsCancelImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,NEXT_ROW_AFTER)) {
            res_.setResult(new BooleanStruct(instance_.nextRowAfter((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_EFFICIENCY)) {
            res_.setResult(new StringStruct(instance_.getEfficiency((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_IGN_LOW_ATT)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnLowAtt((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_IGN_LOW_ATT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnLowAtt((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_IGN_INC_DEF)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnIncDef((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_IGN_INC_DEF)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnIncDef((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBoostingStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBoostingStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.clickItemsBoostingStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_BOOSTING_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrItemsBoostingStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.itemMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickItemsMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrItemsMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_GLOBAL_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_GLOBAL_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickMovesTeamMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTeamMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_ALLY_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesAllyMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_ALLY_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesAllyMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_FOE_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickMovesFoeTeamMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_FOE_TEAM_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMovesFoeTeamMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.statusMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.statusMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_STATUS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickStatusMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_STATUS_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrStatusMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_NORMAL_ANY)) {
            res_.setResult(new BooleanStruct(instance_.comboMultNormalAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_NORMAL)) {
            res_.setResult(new BooleanStruct(instance_.comboMultNormal((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_COMBO_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.clickComboMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_COMBO_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrComboMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_BREAK_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBreakProtectMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_BREAK_PROTECT_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBreakProtectMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_IGN_ACC)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnAcc((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_IGN_ACC)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnAcc((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_IGN_EVA)) {
            res_.setResult(new StringStruct(instance_.clickMovesIgnEva((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_IGN_EVA)) {
            res_.setResult(new StringStruct(instance_.getTrMovesIgnEva((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_GLOBAL_ACC)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalAcc((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_GLOBAL_ACC)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalAcc((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.itemMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.statusMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.statusMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_ACCURACY_ANY)) {
            res_.setResult(new BooleanStruct(instance_.comboMultAccuracyAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_ACCURACY)) {
            res_.setResult(new BooleanStruct(instance_.comboMultAccuracy((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.statusMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.statusMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_EVASINESS_ANY)) {
            res_.setResult(new BooleanStruct(instance_.comboMultEvasinessAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_EVASINESS)) {
            res_.setResult(new BooleanStruct(instance_.comboMultEvasiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IMMU_CH_TEAM_MOVE_ANY)) {
            res_.setResult(new BooleanStruct(instance_.immuChTeamMoveAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IMMU_CH_TEAM_MOVE)) {
            res_.setResult(new BooleanStruct(instance_.immuChTeamMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU_CH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU_CH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_CH_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostChAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_CH)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_CH_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostChAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_CH)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_BOOST_CH)) {
            res_.setResult(new StringStruct(instance_.clickMovesBoostCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_BOOST_CH)) {
            res_.setResult(new StringStruct(instance_.getTrMovesBoostCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_MULT_EVT_CH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesMultEvtCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_MULT_EVT_CH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesMultEvtCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_MULT_RATE_CH)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesMultRateCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_MULT_RATE_CH)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesMultRateCh((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_BOOST_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.abilityBoostSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_BOOST_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.itemBoostSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.itemMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ITEM_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.itemMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.abilityMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_GLOBAL_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.moveGlobalMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_TEAM_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.moveTeamMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_ALLY_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.abilityAllyMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MOVE_FOE_TEAM_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.moveFoeTeamMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.statusMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,STATUS_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.statusMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ABILITY_IMMU_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.abilityImmuMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_SPEED_ANY)) {
            res_.setResult(new BooleanStruct(instance_.comboMultSpeedAny()));
            return res_;
        }
        if (StringList.quickEq(methodName_,COMBO_MULT_SPEED)) {
            res_.setResult(new BooleanStruct(instance_.comboMultSpeed((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_PROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickMovesProtectingTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_PROTECTING_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrMovesProtectingTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_GLOBAL_BREAK_IMMU_AB)) {
            res_.setResult(new StringStruct(instance_.clickMovesGlobalBreakImmuAb((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_GLOBAL_BREAK_IMMU_AB)) {
            res_.setResult(new StringStruct(instance_.getTrMovesGlobalBreakImmuAb((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_BREAKABLE)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesBreakable((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_BREAKABLE)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesBreakable((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickItemsImmuTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrItemsImmuTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU_ALLIES)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuAllies((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU_ALLIES)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuAllies((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU_ALLIES_DAM)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuAlliesDam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU_ALLIES_DAM)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuAlliesDam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS_IMMU)) {
            res_.setResult(new StringStruct(instance_.clickItemsImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ITEMS_IMMU)) {
            res_.setResult(new StringStruct(instance_.getTrItemsImmu((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU_SEC_EFF_OTHER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuSecEffOther((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU_SEC_EFF_OTHER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuSecEffOther((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_IMMU_SEC_EFF_OWNER)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesImmuSecEffOwner((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_IMMU_SEC_EFF_OWNER)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesImmuSecEffOwner((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES_ACHIEVE_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickAbilitiesAchieveTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITIES_ACHIEVE_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrAbilitiesAchieveTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_PROTECTING)) {
            res_.setResult(new StringStruct(instance_.clickMovesProtecting((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_PROTECTING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesProtecting((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getTrStatistic((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ANIM_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getAnimStatistic((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ANIM_ABSORB)) {
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
        if (StringList.quickEq(methodName_,GET_MAP_WIDTH)) {
            res_.setResult(new IntStruct(instance_.getMapWidth()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FIRST_ROW)) {
            res_.setResult(new BooleanStruct(instance_.isFirstRow((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_PLACE_NAME)) {
            res_.setResult(new StringStruct(instance_.getPlaceName((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MINI_MAP_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniMapImage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringList.quickEq(methodName_,FIRST_POKEMON_HAS_ITEM)) {
            res_.setResult(new BooleanStruct(instance_.firstPokemonHasItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MOVES_AT_LEVEL)) {
            res_.setResult(new StdStruct(instance_.getMovesAtLevel(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove((Long)_args[0])));
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
        if (StringList.quickEq(methodName_,CLICK_TM)) {
            res_.setResult(new StringStruct(instance_.clickTm((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_TM)) {
            res_.setResult(new StringStruct(instance_.getTrTm((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TM_PRICE)) {
            res_.setResult(new StringStruct(instance_.getTmPrice((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_HM)) {
            res_.setResult(new StringStruct(instance_.clickHm((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_HM)) {
            res_.setResult(new StringStruct(instance_.getTrHm((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_TYPE)) {
            res_.setResult(new StringStruct(instance_.getImageType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_COLOR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getColorType((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodLangsBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        LangsBean instance_ = (LangsBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_LANG)) {
            res_.setResult(new StringStruct(instance_.getTrLang((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_GENDERS)) {
            res_.setResult(new StdStruct(instance_.getKeysGenders(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_GENDER)) {
            res_.setResult(new StdStruct(instance_.getRowGender((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_BOOLEANS)) {
            res_.setResult(new StdStruct(instance_.getKeysBooleans(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_BOOLEAN)) {
            res_.setResult(new StdStruct(instance_.getRowBoolean((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_ENVIRONMENTS)) {
            res_.setResult(new StdStruct(instance_.getKeysEnvironments(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_ENVIRONMENT)) {
            res_.setResult(new StdStruct(instance_.getRowEnvironment((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_STATISTICS)) {
            res_.setResult(new StdStruct(instance_.getKeysStatistics(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_STATISTIC)) {
            res_.setResult(new StdStruct(instance_.getRowStatistic((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_TARGETS)) {
            res_.setResult(new StdStruct(instance_.getKeysTargets(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_TARGET)) {
            res_.setResult(new StdStruct(instance_.getRowTarget((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_CATEGORIES)) {
            res_.setResult(new StdStruct(instance_.getKeysCategories(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_CATEGORY)) {
            res_.setResult(new StdStruct(instance_.getRowCategory((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_TYPES)) {
            res_.setResult(new StdStruct(instance_.getKeysTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_TYPE)) {
            res_.setResult(new StdStruct(instance_.getRowType((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_POKEMON)) {
            res_.setResult(new StdStruct(instance_.getKeysPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_POKEMON)) {
            res_.setResult(new StdStruct(instance_.getRowPokemon((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_MOVES)) {
            res_.setResult(new StdStruct(instance_.getKeysMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_MOVE)) {
            res_.setResult(new StdStruct(instance_.getRowMove((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_ITEMS)) {
            res_.setResult(new StdStruct(instance_.getKeysItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_ITEM)) {
            res_.setResult(new StdStruct(instance_.getRowItem((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getKeysAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_ABILITY)) {
            res_.setResult(new StdStruct(instance_.getRowAbility((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_STATUS)) {
            res_.setResult(new StdStruct(instance_.getKeysStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_STATUS)) {
            res_.setResult(new StdStruct(instance_.getRowStatus((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_MATH)) {
            res_.setResult(new StdStruct(instance_.getKeysMath(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_MATH)) {
            res_.setResult(new StdStruct(instance_.getRowMath((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEYS_DESC)) {
            res_.setResult(new StdStruct(instance_.getKeysDesc(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ROW_DESC)) {
            res_.setResult(new StdStruct(instance_.getRowDesc((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
}
