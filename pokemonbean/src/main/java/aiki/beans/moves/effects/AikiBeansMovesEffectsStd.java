package aiki.beans.moves.effects;
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
import code.expressionlanguage.structs.NullStruct;
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

public final class AikiBeansMovesEffectsStd {
    public static final String TYPE_EFFECT_ALLY_BEAN = "aiki.beans.moves.effects.EffectAllyBean";
    public static final String TYPE_EFFECT_BATON_PASS_BEAN = "aiki.beans.moves.effects.EffectBatonPassBean";
    public static final String TYPE_EFFECT_BEAN = "aiki.beans.moves.effects.EffectBean";
    public static final String TYPE_EFFECT_CLONE_BEAN = "aiki.beans.moves.effects.EffectCloneBean";
    public static final String TYPE_EFFECT_COMMON_STATISTICS_BEAN = "aiki.beans.moves.effects.EffectCommonStatisticsBean";
    public static final String TYPE_EFFECT_COPY_FIGHTER_BEAN = "aiki.beans.moves.effects.EffectCopyFighterBean";
    public static final String TYPE_EFFECT_COPY_MOVE_BEAN = "aiki.beans.moves.effects.EffectCopyMoveBean";
    public static final String TYPE_EFFECT_COUNTER_ATTACK_BEAN = "aiki.beans.moves.effects.EffectCounterAttackBean";
    public static final String TYPE_EFFECT_DAMAGE_BEAN = "aiki.beans.moves.effects.EffectDamageBean";
    public static final String TYPE_EFFECT_DAMAGE_RATE_BEAN = "aiki.beans.moves.effects.EffectDamageRateBean";
    public static final String TYPE_EFFECT_END_ROUND_MOVE_BEAN = "aiki.beans.moves.effects.EffectEndRoundMoveBean";
    public static final String TYPE_EFFECT_FULL_HP_RATE_BEAN = "aiki.beans.moves.effects.EffectFullHpRateBean";
    public static final String TYPE_EFFECT_GLOBAL_BEAN = "aiki.beans.moves.effects.EffectGlobalBean";
    public static final String TYPE_EFFECT_INVOKE_BEAN = "aiki.beans.moves.effects.EffectInvokeBean";
    public static final String TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER_BEAN = "aiki.beans.moves.effects.EffectMultSufferedMovePowerBean";
    public static final String TYPE_EFFECT_MULT_USED_MOVE_POWER_BEAN = "aiki.beans.moves.effects.EffectMultUsedMovePowerBean";
    public static final String TYPE_EFFECT_ORDER_BEAN = "aiki.beans.moves.effects.EffectOrderBean";
    public static final String TYPE_EFFECT_PROTECT_FROM_TYPES_BEAN = "aiki.beans.moves.effects.EffectProtectFromTypesBean";
    public static final String TYPE_EFFECT_PROTECTION_BEAN = "aiki.beans.moves.effects.EffectProtectionBean";
    public static final String TYPE_EFFECT_REMAINED_HP_RATE_BEAN = "aiki.beans.moves.effects.EffectRemainedHpRateBean";
    public static final String TYPE_EFFECT_RESTRICTION_BEAN = "aiki.beans.moves.effects.EffectRestrictionBean";
    public static final String TYPE_EFFECT_STATISTIC_BEAN = "aiki.beans.moves.effects.EffectStatisticBean";
    public static final String TYPE_EFFECT_STATUS_BEAN = "aiki.beans.moves.effects.EffectStatusBean";
    public static final String TYPE_EFFECT_SWITCH_ABILITIES_BEAN = "aiki.beans.moves.effects.EffectSwitchAbilitiesBean";
    public static final String TYPE_EFFECT_SWITCH_ITEMS_BEAN = "aiki.beans.moves.effects.EffectSwitchItemsBean";
    public static final String TYPE_EFFECT_SWITCH_MOVE_TYPES_BEAN = "aiki.beans.moves.effects.EffectSwitchMoveTypesBean";
    public static final String TYPE_EFFECT_SWITCH_POINT_VIEW_BEAN = "aiki.beans.moves.effects.EffectSwitchPointViewBean";
    public static final String TYPE_EFFECT_SWITCH_TYPES_BEAN = "aiki.beans.moves.effects.EffectSwitchTypesBean";
    public static final String TYPE_EFFECT_TEAM_BEAN = "aiki.beans.moves.effects.EffectTeamBean";
    public static final String TYPE_EFFECT_TEAM_WHILE_SEND_FOE_BEAN = "aiki.beans.moves.effects.EffectTeamWhileSendFoeBean";
    public static final String TYPE_EFFECT_UNPROTECT_FROM_TYPES_BEAN = "aiki.beans.moves.effects.EffectUnprotectFromTypesBean";
    public static final String TYPE_EFFECT_VAR_P_P_BEAN = "aiki.beans.moves.effects.EffectVarPPBean";
    public static final String TYPE_EFFECT_WIN_MONEY_BEAN = "aiki.beans.moves.effects.EffectWinMoneyBean";

    private static final String IS_ADJ_ADV = "isAdjAdv";
    private static final String IS_ADJ_MULT = "isAdjMult";
    private static final String IS_ADJ_UNIQ = "isAdjUniq";
    private static final String IS_ALLIE = "isAllie";
    private static final String IS_ALLIES = "isAllies";
    private static final String IS_ANY_FOE = "isAnyFoe";
    private static final String IS_AUTRE_UNIQ = "isAutreUniq";
    private static final String IS_GLOBALE = "isGlobale";
    private static final String IS_LANCEUR = "isLanceur";
    private static final String IS_PSEUDO_GLOBALE = "isPseudoGlobale";
    private static final String IS_TOUS_ADV = "isTousAdv";
    private static final String IS_UNIQUE_IMPORTE = "isUniqueImporte";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_TR_MOVE = "getTrMove";
    private static final String CLICK_MOVE_END_ROUND = "clickMoveEndRound";
    private static final String GET_TR_MOVES_END_ROUND = "getTrMovesEndRound";
    private static final String CLICK_MOVE_BATON_PASS = "clickMoveBatonPass";
    private static final String GET_TR_MOVES_BATON_PASS = "getTrMovesBatonPass";
    private static final String CLICK_MOVE_SENDING = "clickMoveSending";
    private static final String GET_TR_MOVES_SENDING = "getTrMovesSending";
    private static final String GET_TR_STATISTIC = "getTrStatistic";
    private static final String CLICK_DEFAULT_MOVE = "clickDefaultMove";
    private static final String GET_TR_DEFAULT_MOVE = "getTrDefaultMove";
    private static final String COPY_MOVE_FOR_USER = "copyMoveForUser";
    private static final String CLICK_MOVE_TRANS = "clickMoveTrans";
    private static final String GET_TR_MOVE_TRANS = "getTrMoveTrans";
    private static final String GET_TR_SUFFERING_DAMAGE_TYPES = "getTrSufferingDamageTypes";
    private static final String GET_TR_DROPPED_STAT_DIRECT_MOVE = "getTrDroppedStatDirectMove";
    private static final String GET_MAP_VARS_FAIL_COUNTER = "getMapVarsFailCounter";
    private static final String HAS_LAW_FOR_DAMAGE = "hasLawForDamage";
    private static final String HAS_DETERMINATED_LAW_FOR_DAMAGE = "hasDeterminatedLawForDamage";
    private static final String COUNTER_DAMAGE_CAT = "counterDamageCat";
    private static final String CONST_POWER = "constPower";
    private static final String HAS_CONST_POWER = "hasConstPower";
    private static final String GET_TRANSLATED_STATIS_TARGET = "getTranslatedStatisTarget";
    private static final String GET_TRANSLATED_STATIS_USER = "getTranslatedStatisUser";
    private static final String GET_TRANSLATED_STATIS_KO = "getTranslatedStatisKo";
    private static final String CLICK_PREVENTED_STATUS = "clickPreventedStatus";
    private static final String GET_TR_PREVENTED_STATUS = "getTrPreventedStatus";
    private static final String CLICK_CANCELLED_ABILITY = "clickCancelledAbility";
    private static final String GET_TR_CANCELLED_ABILITY = "getTrCancelledAbility";
    private static final String CLICK_UNUSABLE_MOVE = "clickUnusableMove";
    private static final String GET_TR_UNUSABLE_MOVES = "getTrUnusableMoves";
    private static final String CLICK_CANCELLED_EFFECT = "clickCancelledEffect";
    private static final String GET_TR_CANCELLED_EFFECT = "getTrCancelledEffect";
    private static final String CLICK_MULT_MOVE_POWER = "clickMultMovePower";
    private static final String GET_TR_MULT_MOVE_POWER = "getTrMultMovePower";
    private static final String CLICK_INVOKED_MOVE = "clickInvokedMove";
    private static final String GET_TR_INVOKED_MOVE_TERRAIN = "getTrInvokedMoveTerrain";
    private static final String CLICK_INVOKING_MOVE = "clickInvokingMove";
    private static final String GET_TR_INVOKING_MOVE = "getTrInvokingMove";
    private static final String CLICK_INVOKING_MOVE_TYPES = "clickInvokingMoveTypes";
    private static final String GET_TR_INVOKING_MOVE_TYPES = "getTrInvokingMoveTypes";
    private static final String GET_TR_MULT_STAT_IF_DAMGE_TYPE_FIRST = "getTrMultStatIfDamgeTypeFirst";
    private static final String GET_TR_MULT_STAT_IF_DAMGE_TYPE_SECOND = "getTrMultStatIfDamgeTypeSecond";
    private static final String CLICK_MOVES_TARGET = "clickMovesTarget";
    private static final String GET_TR_MOVES_TARGET = "getTrMovesTarget";
    private static final String GET_TR_ENV = "getTrEnv";
    private static final String CLICK_MOVE_FCT_ENV = "clickMoveFctEnv";
    private static final String GET_TR_MOVE_FCT_ENV = "getTrMoveFctEnv";
    private static final String CLICK_GLOBAL_MOVE_FCT_ENV = "clickGlobalMoveFctEnv";
    private static final String GET_TR_GLOBAL_MOVE_FCT_ENV = "getTrGlobalMoveFctEnv";
    private static final String IS_TYPE = "isType";
    private static final String GET_TR_USER_TYPES = "getTrUserTypes";
    private static final String CLICK_MOVE_USER_TYPES = "clickMoveUserTypes";
    private static final String GET_TR_MOVE_USER_TYPES = "getTrMoveUserTypes";
    private static final String CLICK_MOVE_NOT_INVOK = "clickMoveNotInvok";
    private static final String GET_TR_MOVE_NOT_INVOK = "getTrMoveNotInvok";
    private static final String GET_TR_TYPE = "getTrType";
    private static final String FORBID = "forbid";
    private static final String FORBID_STATUS_MOVE = "forbidStatusMove";
    private static final String FORBID_LAST_MOVE = "forbidLastMove";
    private static final String FORBID_USER_MOVES = "forbidUserMoves";
    private static final String FORBID_USE_MOVE = "forbidUseMove";
    private static final String FORCE_USE_MOVE = "forceUseMove";
    private static final String RANDOM_STATIS = "randomStatis";
    private static final String IS_ALWAYS_ENABLED = "isAlwaysEnabled";
    private static final String NOT_EMPTY_VAR_BOOST = "notEmptyVarBoost";
    private static final String GET_FAIL = "getFail";
    private static final String GET_RATE = "getRate";
    private static final String GET_SWAP_FAIL = "getSwapFail";
    private static final String IS_STATUS = "isStatus";
    private static final String CLICK_LINK = "clickLink";
    private static final String GET_TR_LINK = "getTrLink";
    private static final String CLICK_LINK_DELETED = "clickLinkDeleted";
    private static final String GET_TR_LINK_DELETED = "getTrLinkDeleted";
    private static final String GIVE_TO_TARGET = "giveToTarget";
    private static final String GIVE_TO_USER = "giveToUser";
    private static final String GIVE_CONST = "giveConst";
    private static final String IS_DEF_ABILITY = "isDefAbility";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_TR_ABILITY = "getTrAbility";
    private static final String SWITCH_ABILITIES = "switchAbilities";
    private static final String DELETE_TARGET_BERRY = "deleteTargetBerry";
    private static final String TAKE_ITEM = "takeItem";
    private static final String REMOVE_TARGET_ITEM = "removeTargetItem";
    private static final String SWITCH_ITEMS = "switchItems";
    private static final String RESUSE_LAST_ITEM = "resuseLastItem";
    private static final String GIVE_TARGET_ITEM = "giveTargetItem";
    private static final String USE_ITEM_AS_POSSIBLE = "useItemAsPossible";
    private static final String GET_TR_REPLACING_TYPES = "getTrReplacingTypes";
    private static final String GET_TR_CHANGED_TYPES = "getTrChangedTypes";
    private static final String THIEVE_BONUS = "thieveBonus";
    private static final String MIRROR_AGAINST_USER = "mirrorAgainstUser";
    private static final String ATTRACT_DAMAGE_MOVES = "attractDamageMoves";
    private static final String IS_RES_TYPES = "isResTypes";
    private static final String IS_USER_TYPES = "isUserTypes";
    private static final String IS_CONST_TYPES = "isConstTypes";
    private static final String GET_TR_ADDED_TYPE = "getTrAddedType";
    private static final String SWITCH_TYPES = "switchTypes";
    private static final String GET_TR_CONST_TYPE = "getTrConstType";
    private static final String CLICK_STATUS = "clickStatus";
    private static final String GET_TR_STATUS = "getTrStatus";
    private static final String GET_TR_UNUSABLE_MOVE = "getTrUnusableMove";
    private static final String CLICK_DISABLE_FOE_TEAM_EFFECTS = "clickDisableFoeTeamEffects";
    private static final String GET_TR_DISABLE_FOE_TEAM_EFFECTS = "getTrDisableFoeTeamEffects";
    private static final String CLICK_DISABLE_FOE_TEAM_STATUS = "clickDisableFoeTeamStatus";
    private static final String GET_TR_DISABLE_FOE_TEAM_STATUS = "getTrDisableFoeTeamStatus";
    private static final String GET_TRANSLATED_STATISTIC = "getTranslatedStatistic";
    private static final String GET_TRANSLATED_STATUS = "getTranslatedStatus";
    private static final String GET_TRANSLATED_TYPE = "getTranslatedType";
    private static final String GET_TR_DAMAGE_TYPE = "getTrDamageType";
    private static final String GET_TR_POKEMON_TYPE = "getTrPokemonType";
    private static final String GET_TR_DISABLE_IMMU_TYPE = "getTrDisableImmuType";
    private static final String GET_TR_DISABLE_IMMU_MOVE = "getTrDisableImmuMove";
    private static final String GET_TR_ATTACK_TARGET_TYPE = "getTrAttackTargetType";
    private static final String INDEX = "index";
    private static final String MOVE = "move";
    private static final String REASONS = "reasons";
    private static final String MAP_VARS_FAIL = "mapVarsFail";
    private static final String NEED_SUCCESS_FIRST_EFFECT = "needSuccessFirstEffect";
    private static final String EFFECT_BEAN = "effectBean";
    private static final String MULT_ALLY_DAMAGE = "multAllyDamage";
    private static final String MOVES = "moves";
    private static final String HP_RATE_CLONE = "hpRateClone";
    private static final String MOVES_END_ROUND = "movesEndRound";
    private static final String MOVES_BATON_PASS = "movesBatonPass";
    private static final String MOVES_SENDING = "movesSending";
    private static final String COMMON_VALUE = "commonValue";
    private static final String MAP_VARS_COMMON_STATISTICS = "mapVarsCommonStatistics";
    private static final String PP_FOR_MOVES = "ppForMoves";
    private static final String DISPLAY_NAME = "displayName";
    private static final String COPYING_MOVE_FOR_USER = "copyingMoveForUser";
    private static final String COPYING_MOVE_FOR_USER_DEF = "copyingMoveForUserDef";
    private static final String MOVES_TRANSFORMING = "movesTransforming";
    private static final String MOVES_NOT_TO_BE_COPIED = "movesNotToBeCopied";
    private static final String SUFFERING_DAMAGE_TYPES = "sufferingDamageTypes";
    private static final String DROPPED_STAT_DIRECT_MOVE = "droppedStatDirectMove";
    private static final String SUFFERING_DAMAGE_DIRECT_MOVE = "sufferingDamageDirectMove";
    private static final String REASONS_PROTECT = "reasonsProtect";
    private static final String REASONS_COUNTER = "reasonsCounter";
    private static final String HITS_LAW = "hitsLaw";
    private static final String NB_HITS = "nbHits";
    private static final String CONST_DAMAGE = "constDamage";
    private static final String POWER = "power";
    private static final String DAMAGE_LAW = "damageLaw";
    private static final String MAP_VARS_DAMAGE = "mapVarsDamage";
    private static final String MULT_DAMAGE_AGAINST = "multDamageAgainst";
    private static final String CH_RATE = "chRate";
    private static final String CH_LAW = "chLaw";
    private static final String USER_ATTACK = "userAttack";
    private static final String STATIS_ATT = "statisAtt";
    private static final String TARGET_DEFENSE = "targetDefense";
    private static final String STATIS_DEF = "statisDef";
    private static final String IGN_VAR_STAT_TARGET_POS = "ignVarStatTargetPos";
    private static final String IGN_VAR_STAT_USER_NEG = "ignVarStatUserNeg";
    private static final String RAND_MAX = "randMax";
    private static final String BOOST_STATIS_ONCE_KO_FOE = "boostStatisOnceKoFoe";
    private static final String SUMMING_USER_TEAM_OK_FIGHTER = "summingUserTeamOkFighter";
    private static final String WIN_HP = "winHp";
    private static final String RATE_DAMAGE = "rateDamage";
    private static final String END_ROUND_RANK = "endRoundRank";
    private static final String REASONS_END_ROUND = "reasonsEndRound";
    private static final String MAP_VARS_FAIL_END_ROUND = "mapVarsFailEndRound";
    private static final String LEFT_USER_HP = "leftUserHp";
    private static final String RESTORED_HP = "restoredHp";
    private static final String MAP_VARS_RESTORED = "mapVarsRestored";
    private static final String CLOSEST_FOE_DAMAGE_RATE_HP = "closestFoeDamageRateHp";
    private static final String WEATHER = "weather";
    private static final String CANCELED_IF_USED = "canceledIfUsed";
    private static final String REVERSE_ORDER_OF_SORT_BY_SPEED = "reverseOrderOfSortBySpeed";
    private static final String UNUSABLE_ITEM = "unusableItem";
    private static final String PUTTING_KO = "puttingKo";
    private static final String MULT_ACCURACY = "multAccuracy";
    private static final String DAMAGE_END_ROUND = "damageEndRound";
    private static final String HEALING_END_ROUND_GROUND = "healingEndRoundGround";
    private static final String HEALING_END_ROUND = "healingEndRound";
    private static final String MULT_EFFECT_LOVING_ALLY = "multEffectLovingAlly";
    private static final String PREVENT_STATUS = "preventStatus";
    private static final String IMMUNE_TYPES = "immuneTypes";
    private static final String EFFICIENCY_MOVES = "efficiencyMoves";
    private static final String DISABLE_IMMU_AGAINST_TYPES = "disableImmuAgainstTypes";
    private static final String CANCEL_PROTECTING_ABILITIES = "cancelProtectingAbilities";
    private static final String UNUSABLE_MOVES = "unusableMoves";
    private static final String CANCEL_EFFECTS = "cancelEffects";
    private static final String MULT_POWER_MOVES = "multPowerMoves";
    private static final String MULT_DAMAGE_TYPES_MOVES = "multDamageTypesMoves";
    private static final String CANCEL_CHGT_STAT = "cancelChgtStat";
    private static final String INVOKED_MOVE_TERRAIN = "invokedMoveTerrain";
    private static final String INVOKING_MOVES = "invokingMoves";
    private static final String CHANGED_TYPES_TERRAIN = "changedTypesTerrain";
    private static final String INVOKING_MOVES_CHANGING_TYPES = "invokingMovesChangingTypes";
    private static final String MULT_STAT_IF_CONTAINS_TYPE = "multStatIfContainsType";
    private static final String MULT_DAMAGE_PREPA_ROUND = "multDamagePrepaRound";
    private static final String MOVES_USED_BY_TARGETED_FIGHTERS = "movesUsedByTargetedFighters";
    private static final String INVOKING_MOVE_BUT_USER = "invokingMoveButUser";
    private static final String INVOKING_TARGET_CHOSEN_MOVE = "invokingTargetChosenMove";
    private static final String INVOKING_USER_MOVE_WHILE_SLEEP = "invokingUserMoveWhileSleep";
    private static final String INVOKING_ALLY_MOVE = "invokingAllyMove";
    private static final String INVOKING_TARGET_SUCCESFUL_MOVE = "invokingTargetSuccesfulMove";
    private static final String INVOKING_SUFFERED_MOVE = "invokingSufferedMove";
    private static final String RATE_INVOKATION_MOVE = "rateInvokationMove";
    private static final String MOVE_FCT_ENV = "moveFctEnv";
    private static final String GLOBAL_MOVES = "globalMoves";
    private static final String INVOKING_MOVE_BY_USER_TYPES = "invokingMoveByUserTypes";
    private static final String MOVES_NOT_TO_BE_INVOKED = "movesNotToBeInvoked";
    private static final String MULT_MOVE_POWER_FCT_TYPE = "multMovePowerFctType";
    private static final String TARGET_ATTACKS_LAST = "targetAttacksLast";
    private static final String IMMU_AGAINST_TYPES = "immuAgainstTypes";
    private static final String PROT_SINGLE = "protSingle";
    private static final String PROT_SINGLE_AGAINST_KO = "protSingleAgainstKo";
    private static final String PROT_TEAM_AGAINST_MULT_TARGETS = "protTeamAgainstMultTargets";
    private static final String PROT_TEAM_AGAINST_PRIO = "protTeamAgainstPrio";
    private static final String PROT_TEAM_AGAINST_STATUS_MOVES = "protTeamAgainstStatusMoves";
    private static final String PROT_TEAM_AGAINST_DAMAGE_MOVES = "protTeamAgainstDamageMoves";
    private static final String RATE_HP = "rateHp";
    private static final String FORBID_TARGET_USING_ITEM = "forbidTargetUsingItem";
    private static final String EVT_RATE = "evtRate";
    private static final String EVT_RATE_PER_CENT = "evtRatePerCent";
    private static final String STATIS_VAR_RANK = "statisVarRank";
    private static final String MAP_VARS_STATISTICS = "mapVarsStatistics";
    private static final String SWAP_BOOST_STATIS = "swapBoostStatis";
    private static final String CANCEL_LOW_STAT = "cancelLowStat";
    private static final String DEFAULT_BOOST = "defaultBoost";
    private static final String COPY_BOOST = "copyBoost";
    private static final String LAW_STATUS = "lawStatus";
    private static final String MAP_VARS_STATUS = "mapVarsStatus";
    private static final String DELETED_STATUS = "deletedStatus";
    private static final String KO_USER_HEAL_SUBST = "koUserHealSubst";
    private static final String STATUS_FROM_USER = "statusFromUser";
    private static final String REPLACING_TYPES = "replacingTypes";
    private static final String CHANGE_TYPES = "changeTypes";
    private static final String CHGT_TYPE_BY_ENV = "chgtTypeByEnv";
    private static final String ADDED_TYPES = "addedTypes";
    private static final String CONST_TYPES = "constTypes";
    private static final String FORBIDDING_HEALING = "forbiddingHealing";
    private static final String PROTECT_AGAINST_CH = "protectAgainstCh";
    private static final String FORBIDDEN_BOOST = "forbiddenBoost";
    private static final String CANCEL_CHGT_STAT_FOE_TEAM = "cancelChgtStatFoeTeam";
    private static final String CANCEL_CHGT_STAT_TEAM = "cancelChgtStatTeam";
    private static final String PROTECT_AGAINST_LOW_STAT = "protectAgainstLowStat";
    private static final String PROTECT_AGAINST_STATUS = "protectAgainstStatus";
    private static final String MULT_STATISTIC = "multStatistic";
    private static final String MULT_STATISTIC_FOE = "multStatisticFoe";
    private static final String MULT_DAMAGE = "multDamage";
    private static final String DISABLE_FOE_TEAM_EFFECTS = "disableFoeTeamEffects";
    private static final String DISABLE_FOE_TEAM_STATUS = "disableFoeTeamStatus";
    private static final String DAMAGE_RATE_AGAINST_FOE = "damageRateAgainstFoe";
    private static final String MAP_VARS_DAMAGE_SENT_FOE = "mapVarsDamageSentFoe";
    private static final String STATISTICS = "statistics";
    private static final String STATUS_BY_NB_USES = "statusByNbUses";
    private static final String REASONS_SENDING = "reasonsSending";
    private static final String MAP_VARS_FAIL_SENDING = "mapVarsFailSending";
    private static final String DELETED_BY_FOE_TYPES = "deletedByFoeTypes";
    private static final String TYPES = "types";
    private static final String DISABLE_IMMU_FROM_MOVES = "disableImmuFromMoves";
    private static final String ATTACK_TARGET_WITH_TYPES = "attackTargetWithTypes";
    private static final String DELETE_PP = "deletePp";
    private static final String WINNING_RATE_BY_SUM_TARGET_USER = "winningRateBySumTargetUser";

    public static void build(BeanLgNames _std) {
        buildEffectAllyBean(_std);
        buildEffectBatonPassBean(_std);
        buildEffectBean(_std);
        buildEffectCloneBean(_std);
        buildEffectCommonStatisticsBean(_std);
        buildEffectCopyFighterBean(_std);
        buildEffectCopyMoveBean(_std);
        buildEffectCounterAttackBean(_std);
        buildEffectDamageBean(_std);
        buildEffectDamageRateBean(_std);
        buildEffectEndRoundMoveBean(_std);
        buildEffectFullHpRateBean(_std);
        buildEffectGlobalBean(_std);
        buildEffectInvokeBean(_std);
        buildEffectMultSufferedMovePowerBean(_std);
        buildEffectMultUsedMovePowerBean(_std);
        buildEffectOrderBean(_std);
        buildEffectProtectFromTypesBean(_std);
        buildEffectProtectionBean(_std);
        buildEffectRemainedHpRateBean(_std);
        buildEffectRestrictionBean(_std);
        buildEffectStatisticBean(_std);
        buildEffectStatusBean(_std);
        buildEffectSwitchAbilitiesBean(_std);
        buildEffectSwitchItemsBean(_std);
        buildEffectSwitchMoveTypesBean(_std);
        buildEffectSwitchPointViewBean(_std);
        buildEffectSwitchTypesBean(_std);
        buildEffectTeamBean(_std);
        buildEffectTeamWhileSendFoeBean(_std);
        buildEffectUnprotectFromTypesBean(_std);
        buildEffectVarPPBean(_std);
        buildEffectWinMoneyBean(_std);
    }
    private static void buildEffectAllyBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_ALLY_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(MULT_ALLY_DAMAGE,new StandardField(MULT_ALLY_DAMAGE,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_ALLY_BEAN, type_);
    }
    private static void buildEffectBatonPassBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_BATON_PASS_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(MOVES,new StandardField(MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_BATON_PASS_BEAN, type_);
    }
    private static void buildEffectBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(INDEX,new StandardField(INDEX,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(MOVE,new StandardField(MOVE,_std.getAliasString(),false,false,type_));
        fields_.put(REASONS,new StandardField(REASONS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MAP_VARS_FAIL,new StandardField(MAP_VARS_FAIL, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(NEED_SUCCESS_FIRST_EFFECT,new StandardField(NEED_SUCCESS_FIRST_EFFECT,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(EFFECT_BEAN,new StandardField(EFFECT_BEAN,_std.getAliasString(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(IS_ADJ_ADV,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ADJ_MULT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ADJ_UNIQ,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ALLIE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ALLIES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ANY_FOE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_AUTRE_UNIQ,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_GLOBALE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_LANCEUR,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_PSEUDO_GLOBALE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_TOUS_ADV,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_UNIQUE_IMPORTE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_BEAN, type_);
    }
    private static void buildEffectCloneBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_CLONE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(HP_RATE_CLONE,new StandardField(HP_RATE_CLONE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MOVES_END_ROUND,new StandardField(MOVES_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_BATON_PASS,new StandardField(MOVES_BATON_PASS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_SENDING,new StandardField(MOVES_SENDING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_END_ROUND,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_BATON_PASS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_BATON_PASS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_SENDING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_SENDING,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_CLONE_BEAN, type_);
    }
    private static void buildEffectCommonStatisticsBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_COMMON_STATISTICS_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(COMMON_VALUE,new StandardField(COMMON_VALUE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MAP_VARS_COMMON_STATISTICS,new StandardField(MAP_VARS_COMMON_STATISTICS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_COMMON_STATISTICS_BEAN, type_);
    }
    private static void buildEffectCopyFighterBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_COPY_FIGHTER_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(PP_FOR_MOVES,new StandardField(PP_FOR_MOVES,_std.getAliasPrimShort(),false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_COPY_FIGHTER_BEAN, type_);
    }
    private static void buildEffectCopyMoveBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_COPY_MOVE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(DISPLAY_NAME,new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(COPYING_MOVE_FOR_USER,new StandardField(COPYING_MOVE_FOR_USER,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(COPYING_MOVE_FOR_USER_DEF,new StandardField(COPYING_MOVE_FOR_USER_DEF,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(MOVES_TRANSFORMING,new StandardField(MOVES_TRANSFORMING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_NOT_TO_BE_COPIED,new StandardField(MOVES_NOT_TO_BE_COPIED, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_DEFAULT_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_DEFAULT_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(COPY_MOVE_FOR_USER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_TRANS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVE_TRANS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_COPY_MOVE_BEAN, type_);
    }
    private static void buildEffectCounterAttackBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_COUNTER_ATTACK_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(SUFFERING_DAMAGE_TYPES,new StandardField(SUFFERING_DAMAGE_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(DROPPED_STAT_DIRECT_MOVE,new StandardField(DROPPED_STAT_DIRECT_MOVE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(SUFFERING_DAMAGE_DIRECT_MOVE,new StandardField(SUFFERING_DAMAGE_DIRECT_MOVE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(REASONS_PROTECT,new StandardField(REASONS_PROTECT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(REASONS_COUNTER,new StandardField(REASONS_COUNTER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SUFFERING_DAMAGE_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DROPPED_STAT_DIRECT_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MAP_VARS_FAIL_COUNTER,params_, BeanNatLgNames.TYPE_MAP, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_COUNTER_ATTACK_BEAN, type_);
    }
    private static void buildEffectDamageBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_DAMAGE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(HITS_LAW,new StandardField(HITS_LAW, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(NB_HITS,new StandardField(NB_HITS,_std.getAliasPrimLong(),false,false,type_));
        fields_.put(CONST_DAMAGE,new StandardField(CONST_DAMAGE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(POWER,new StandardField(POWER,_std.getAliasString(),false,false,type_));
        fields_.put(DAMAGE_LAW,new StandardField(DAMAGE_LAW, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MAP_VARS_DAMAGE,new StandardField(MAP_VARS_DAMAGE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_DAMAGE_AGAINST,new StandardField(MULT_DAMAGE_AGAINST, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(CH_RATE,new StandardField(CH_RATE,_std.getAliasPrimByte(),false,false,type_));
        fields_.put(CH_LAW,new StandardField(CH_LAW, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(USER_ATTACK,new StandardField(USER_ATTACK,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(STATIS_ATT,new StandardField(STATIS_ATT,_std.getAliasString(),false,false,type_));
        fields_.put(TARGET_DEFENSE,new StandardField(TARGET_DEFENSE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(STATIS_DEF,new StandardField(STATIS_DEF,_std.getAliasString(),false,false,type_));
        fields_.put(IGN_VAR_STAT_TARGET_POS,new StandardField(IGN_VAR_STAT_TARGET_POS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(IGN_VAR_STAT_USER_NEG,new StandardField(IGN_VAR_STAT_USER_NEG, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(RAND_MAX,new StandardField(RAND_MAX,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(BOOST_STATIS_ONCE_KO_FOE,new StandardField(BOOST_STATIS_ONCE_KO_FOE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(SUMMING_USER_TEAM_OK_FIGHTER,new StandardField(SUMMING_USER_TEAM_OK_FIGHTER,_std.getAliasPrimBoolean(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(HAS_LAW_FOR_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(HAS_DETERMINATED_LAW_FOR_DAMAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(COUNTER_DAMAGE_CAT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CONST_POWER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(HAS_CONST_POWER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TRANSLATED_STATIS_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TRANSLATED_STATIS_USER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TRANSLATED_STATIS_KO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_DAMAGE_BEAN, type_);
    }
    private static void buildEffectDamageRateBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_DAMAGE_RATE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(WIN_HP,new StandardField(WIN_HP,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(RATE_DAMAGE,new StandardField(RATE_DAMAGE,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_DAMAGE_RATE_BEAN, type_);
    }
    private static void buildEffectEndRoundMoveBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_END_ROUND_MOVE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(END_ROUND_RANK,new StandardField(END_ROUND_RANK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(REASONS_END_ROUND,new StandardField(REASONS_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MAP_VARS_FAIL_END_ROUND,new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_END_ROUND_MOVE_BEAN, type_);
    }
    private static void buildEffectFullHpRateBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_FULL_HP_RATE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(LEFT_USER_HP,new StandardField(LEFT_USER_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(RESTORED_HP,new StandardField(RESTORED_HP,_std.getAliasString(),false,false,type_));
        fields_.put(MAP_VARS_RESTORED,new StandardField(MAP_VARS_RESTORED, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(CLOSEST_FOE_DAMAGE_RATE_HP,new StandardField(CLOSEST_FOE_DAMAGE_RATE_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_FULL_HP_RATE_BEAN, type_);
    }
    private static void buildEffectGlobalBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_GLOBAL_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(WEATHER,new StandardField(WEATHER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(CANCELED_IF_USED,new StandardField(CANCELED_IF_USED,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(REVERSE_ORDER_OF_SORT_BY_SPEED,new StandardField(REVERSE_ORDER_OF_SORT_BY_SPEED,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(UNUSABLE_ITEM,new StandardField(UNUSABLE_ITEM,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PUTTING_KO,new StandardField(PUTTING_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(MULT_ACCURACY,new StandardField(MULT_ACCURACY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(DAMAGE_END_ROUND,new StandardField(DAMAGE_END_ROUND,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HEALING_END_ROUND_GROUND,new StandardField(HEALING_END_ROUND_GROUND,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HEALING_END_ROUND,new StandardField(HEALING_END_ROUND,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_EFFECT_LOVING_ALLY,new StandardField(MULT_EFFECT_LOVING_ALLY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(PREVENT_STATUS,new StandardField(PREVENT_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(IMMUNE_TYPES,new StandardField(IMMUNE_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(EFFICIENCY_MOVES,new StandardField(EFFICIENCY_MOVES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(DISABLE_IMMU_AGAINST_TYPES,new StandardField(DISABLE_IMMU_AGAINST_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CANCEL_PROTECTING_ABILITIES,new StandardField(CANCEL_PROTECTING_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(UNUSABLE_MOVES,new StandardField(UNUSABLE_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CANCEL_EFFECTS,new StandardField(CANCEL_EFFECTS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MULT_POWER_MOVES,new StandardField(MULT_POWER_MOVES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_DAMAGE_TYPES_MOVES,new StandardField(MULT_DAMAGE_TYPES_MOVES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(CANCEL_CHGT_STAT,new StandardField(CANCEL_CHGT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(INVOKED_MOVE_TERRAIN,new StandardField(INVOKED_MOVE_TERRAIN,_std.getAliasString(),false,false,type_));
        fields_.put(INVOKING_MOVES,new StandardField(INVOKING_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CHANGED_TYPES_TERRAIN,new StandardField(CHANGED_TYPES_TERRAIN, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(INVOKING_MOVES_CHANGING_TYPES,new StandardField(INVOKING_MOVES_CHANGING_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MULT_STAT_IF_CONTAINS_TYPE,new StandardField(MULT_STAT_IF_CONTAINS_TYPE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_DAMAGE_PREPA_ROUND,new StandardField(MULT_DAMAGE_PREPA_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MOVES_USED_BY_TARGETED_FIGHTERS,new StandardField(MOVES_USED_BY_TARGETED_FIGHTERS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_PREVENTED_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_PREVENTED_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_CANCELLED_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CANCELLED_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_UNUSABLE_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_UNUSABLE_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_CANCELLED_EFFECT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CANCELLED_EFFECT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MULT_MOVE_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_MOVE_POWER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_INVOKED_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_INVOKED_MOVE_TERRAIN,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_INVOKING_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_INVOKING_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_INVOKING_MOVE_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_INVOKING_MOVE_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_DAMGE_TYPE_FIRST,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_IF_DAMGE_TYPE_SECOND,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVES_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVES_TARGET,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_GLOBAL_BEAN, type_);
    }
    private static void buildEffectInvokeBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_INVOKE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(INVOKING_MOVE_BUT_USER,new StandardField(INVOKING_MOVE_BUT_USER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(INVOKING_TARGET_CHOSEN_MOVE,new StandardField(INVOKING_TARGET_CHOSEN_MOVE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(INVOKING_USER_MOVE_WHILE_SLEEP,new StandardField(INVOKING_USER_MOVE_WHILE_SLEEP,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(INVOKING_ALLY_MOVE,new StandardField(INVOKING_ALLY_MOVE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(INVOKING_TARGET_SUCCESFUL_MOVE,new StandardField(INVOKING_TARGET_SUCCESFUL_MOVE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(INVOKING_SUFFERED_MOVE,new StandardField(INVOKING_SUFFERED_MOVE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(RATE_INVOKATION_MOVE,new StandardField(RATE_INVOKATION_MOVE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MOVE_FCT_ENV,new StandardField(MOVE_FCT_ENV, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(GLOBAL_MOVES,new StandardField(GLOBAL_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(INVOKING_MOVE_BY_USER_TYPES,new StandardField(INVOKING_MOVE_BY_USER_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MOVES_NOT_TO_BE_INVOKED,new StandardField(MOVES_NOT_TO_BE_INVOKED, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_FCT_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVE_FCT_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_GLOBAL_MOVE_FCT_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_GLOBAL_MOVE_FCT_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_TYPE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_USER_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_USER_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVE_USER_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_NOT_INVOK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MOVE_NOT_INVOK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_INVOKE_BEAN, type_);
    }
    private static void buildEffectMultSufferedMovePowerBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(MULT_MOVE_POWER_FCT_TYPE,new StandardField(MULT_MOVE_POWER_FCT_TYPE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER_BEAN, type_);
    }
    private static void buildEffectMultUsedMovePowerBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_MULT_USED_MOVE_POWER_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(MULT_MOVE_POWER_FCT_TYPE,new StandardField(MULT_MOVE_POWER_FCT_TYPE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_MULT_USED_MOVE_POWER_BEAN, type_);
    }
    private static void buildEffectOrderBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_ORDER_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(TARGET_ATTACKS_LAST,new StandardField(TARGET_ATTACKS_LAST,_std.getAliasPrimBoolean(),false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_ORDER_BEAN, type_);
    }
    private static void buildEffectProtectFromTypesBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_PROTECT_FROM_TYPES_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(IMMU_AGAINST_TYPES,new StandardField(IMMU_AGAINST_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_PROTECT_FROM_TYPES_BEAN, type_);
    }
    private static void buildEffectProtectionBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_PROTECTION_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(PROT_SINGLE,new StandardField(PROT_SINGLE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PROT_SINGLE_AGAINST_KO,new StandardField(PROT_SINGLE_AGAINST_KO,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(PROT_TEAM_AGAINST_MULT_TARGETS,new StandardField(PROT_TEAM_AGAINST_MULT_TARGETS,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PROT_TEAM_AGAINST_PRIO,new StandardField(PROT_TEAM_AGAINST_PRIO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PROT_TEAM_AGAINST_STATUS_MOVES,new StandardField(PROT_TEAM_AGAINST_STATUS_MOVES,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PROT_TEAM_AGAINST_DAMAGE_MOVES,new StandardField(PROT_TEAM_AGAINST_DAMAGE_MOVES,_std.getAliasPrimBoolean(),false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_PROTECTION_BEAN, type_);
    }
    private static void buildEffectRemainedHpRateBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_REMAINED_HP_RATE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(WIN_HP,new StandardField(WIN_HP,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(RATE_HP,new StandardField(RATE_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_REMAINED_HP_RATE_BEAN, type_);
    }
    private static void buildEffectRestrictionBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_RESTRICTION_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(FORBID_TARGET_USING_ITEM,new StandardField(FORBID_TARGET_USING_ITEM,_std.getAliasPrimBoolean(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(FORBID,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FORBID_STATUS_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FORBID_LAST_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FORBID_USER_MOVES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FORBID_USE_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FORCE_USE_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_RESTRICTION_BEAN, type_);
    }
    private static void buildEffectStatisticBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_STATISTIC_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(EVT_RATE,new StandardField(EVT_RATE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(EVT_RATE_PER_CENT,new StandardField(EVT_RATE_PER_CENT,_std.getAliasString(),false,false,type_));
        fields_.put(STATIS_VAR_RANK,new StandardField(STATIS_VAR_RANK, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MAP_VARS_STATISTICS,new StandardField(MAP_VARS_STATISTICS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(SWAP_BOOST_STATIS,new StandardField(SWAP_BOOST_STATIS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CANCEL_LOW_STAT,new StandardField(CANCEL_LOW_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DEFAULT_BOOST,new StandardField(DEFAULT_BOOST,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(CANCEL_CHGT_STAT,new StandardField(CANCEL_CHGT_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(COPY_BOOST,new StandardField(COPY_BOOST, BeanNatLgNames.TYPE_LIST,false,false,type_));
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
        _std.getStandards().put(TYPE_EFFECT_STATISTIC_BEAN, type_);
    }
    private static void buildEffectStatusBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_STATUS_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(LAW_STATUS,new StandardField(LAW_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MAP_VARS_STATUS,new StandardField(MAP_VARS_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(DELETED_STATUS,new StandardField(DELETED_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(KO_USER_HEAL_SUBST,new StandardField(KO_USER_HEAL_SUBST,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(STATUS_FROM_USER,new StandardField(STATUS_FROM_USER,_std.getAliasPrimBoolean(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_STATUS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_FAIL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LINK_DELETED,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_LINK_DELETED,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_STATUS_BEAN, type_);
    }
    private static void buildEffectSwitchAbilitiesBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_SWITCH_ABILITIES_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(GIVE_TO_TARGET,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GIVE_TO_USER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GIVE_CONST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_DEF_ABILITY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SWITCH_ABILITIES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_SWITCH_ABILITIES_BEAN, type_);
    }
    private static void buildEffectSwitchItemsBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_SWITCH_ITEMS_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(DELETE_TARGET_BERRY,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(TAKE_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(REMOVE_TARGET_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SWITCH_ITEMS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(RESUSE_LAST_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GIVE_TARGET_ITEM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(USE_ITEM_AS_POSSIBLE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_SWITCH_ITEMS_BEAN, type_);
    }
    private static void buildEffectSwitchMoveTypesBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_SWITCH_MOVE_TYPES_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(REPLACING_TYPES,new StandardField(REPLACING_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CHANGE_TYPES,new StandardField(CHANGE_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_REPLACING_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CHANGED_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_SWITCH_MOVE_TYPES_BEAN, type_);
    }
    private static void buildEffectSwitchPointViewBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_SWITCH_POINT_VIEW_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(THIEVE_BONUS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MIRROR_AGAINST_USER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ATTRACT_DAMAGE_MOVES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_SWITCH_POINT_VIEW_BEAN, type_);
    }
    private static void buildEffectSwitchTypesBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_SWITCH_TYPES_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(CHGT_TYPE_BY_ENV,new StandardField(CHGT_TYPE_BY_ENV, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(GLOBAL_MOVES,new StandardField(GLOBAL_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ADDED_TYPES,new StandardField(ADDED_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CONST_TYPES,new StandardField(CONST_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(IS_RES_TYPES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_USER_TYPES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_CONST_TYPES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_GLOBAL_MOVE_FCT_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_GLOBAL_MOVE_FCT_ENV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ADDED_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GIVE_TO_TARGET,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GIVE_TO_USER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SWITCH_TYPES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GIVE_CONST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CONST_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_SWITCH_TYPES_BEAN, type_);
    }
    private static void buildEffectTeamBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_TEAM_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(FORBIDDING_HEALING,new StandardField(FORBIDDING_HEALING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PROTECT_AGAINST_CH,new StandardField(PROTECT_AGAINST_CH,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(FORBIDDEN_BOOST,new StandardField(FORBIDDEN_BOOST, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(CANCEL_CHGT_STAT_FOE_TEAM,new StandardField(CANCEL_CHGT_STAT_FOE_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DEFAULT_BOOST,new StandardField(DEFAULT_BOOST,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(CANCEL_CHGT_STAT_TEAM,new StandardField(CANCEL_CHGT_STAT_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(PROTECT_AGAINST_LOW_STAT,new StandardField(PROTECT_AGAINST_LOW_STAT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(PROTECT_AGAINST_STATUS,new StandardField(PROTECT_AGAINST_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MULT_STATISTIC,new StandardField(MULT_STATISTIC, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_STATISTIC_FOE,new StandardField(MULT_STATISTIC_FOE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_DAMAGE,new StandardField(MULT_DAMAGE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(UNUSABLE_MOVES,new StandardField(UNUSABLE_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DISABLE_FOE_TEAM_EFFECTS,new StandardField(DISABLE_FOE_TEAM_EFFECTS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DISABLE_FOE_TEAM_STATUS,new StandardField(DISABLE_FOE_TEAM_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_UNUSABLE_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_UNUSABLE_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_DISABLE_FOE_TEAM_EFFECTS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DISABLE_FOE_TEAM_EFFECTS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_DISABLE_FOE_TEAM_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DISABLE_FOE_TEAM_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_TEAM_BEAN, type_);
    }
    private static void buildEffectTeamWhileSendFoeBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_TEAM_WHILE_SEND_FOE_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(DAMAGE_RATE_AGAINST_FOE,new StandardField(DAMAGE_RATE_AGAINST_FOE,_std.getAliasString(),false,false,type_));
        fields_.put(MAP_VARS_DAMAGE_SENT_FOE,new StandardField(MAP_VARS_DAMAGE_SENT_FOE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(STATISTICS,new StandardField(STATISTICS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(STATUS_BY_NB_USES,new StandardField(STATUS_BY_NB_USES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(REASONS_SENDING,new StandardField(REASONS_SENDING, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MAP_VARS_FAIL_SENDING,new StandardField(MAP_VARS_FAIL_SENDING, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(DELETED_BY_FOE_TYPES,new StandardField(DELETED_BY_FOE_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TRANSLATED_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TRANSLATED_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TRANSLATED_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_TEAM_WHILE_SEND_FOE_BEAN, type_);
    }
    private static void buildEffectUnprotectFromTypesBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_UNPROTECT_FROM_TYPES_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(TYPES,new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DISABLE_IMMU_AGAINST_TYPES,new StandardField(DISABLE_IMMU_AGAINST_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DISABLE_IMMU_FROM_MOVES,new StandardField(DISABLE_IMMU_FROM_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(ATTACK_TARGET_WITH_TYPES,new StandardField(ATTACK_TARGET_WITH_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DAMAGE_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DISABLE_IMMU_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_DISABLE_IMMU_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ATTACK_TARGET_TYPE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EFFECT_UNPROTECT_FROM_TYPES_BEAN, type_);
    }
    private static void buildEffectVarPPBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_VAR_P_P_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(DELETE_PP,new StandardField(DELETE_PP,_std.getAliasPrimShort(),false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_VAR_P_P_BEAN, type_);
    }
    private static void buildEffectWinMoneyBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EFFECT_WIN_MONEY_BEAN, fields_, constructors_, methods_, AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, MethodModifier.NORMAL);
        fields_.put(WINNING_RATE_BY_SUM_TARGET_USER,new StandardField(WINNING_RATE_BY_SUM_TARGET_USER,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().put(TYPE_EFFECT_WIN_MONEY_BEAN, type_);
    }
    public static ResultErrorStd getResultEffectAllyBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectAllyBean instance_ = (EffectAllyBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MULT_ALLY_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMultAllyDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectBatonPassBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectBatonPassBean instance_ = (EffectBatonPassBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MOVES)) {
            res_.setResult(new StdStruct(instance_.getMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectBean instance_ = (EffectBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            res_.setResult(new IntStruct(instance_.getIndex()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS)) {
            res_.setResult(new StdStruct(instance_.getReasons(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_FAIL)) {
            res_.setResult(new StdStruct(instance_.getMapVarsFail(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NEED_SUCCESS_FIRST_EFFECT)) {
            res_.setResult(BooleanStruct.of(instance_.getNeedSuccessFirstEffect()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EFFECT_BEAN)) {
            res_.setResult(new StringStruct(EffectBean.EFFECT_BEAN));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEffectCloneBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectCloneBean instance_ = (EffectCloneBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,HP_RATE_CLONE)) {
            res_.setResult(new StdStruct(instance_.getHpRateClone(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getMovesEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_BATON_PASS)) {
            res_.setResult(new StdStruct(instance_.getMovesBatonPass(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_SENDING)) {
            res_.setResult(new StdStruct(instance_.getMovesSending(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectCommonStatisticsBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectCommonStatisticsBean instance_ = (EffectCommonStatisticsBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,COMMON_VALUE)) {
            res_.setResult(new StdStruct(instance_.getCommonValue(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_COMMON_STATISTICS)) {
            res_.setResult(new StdStruct(instance_.getMapVarsCommonStatistics(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectCopyFighterBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectCopyFighterBean instance_ = (EffectCopyFighterBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,PP_FOR_MOVES)) {
            res_.setResult(new ShortStruct(instance_.getPpForMoves()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectCopyMoveBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectCopyMoveBean instance_ = (EffectCopyMoveBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPYING_MOVE_FOR_USER)) {
            res_.setResult(new ShortStruct(instance_.getCopyingMoveForUser()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPYING_MOVE_FOR_USER_DEF)) {
            res_.setResult(BooleanStruct.of(instance_.getCopyingMoveForUserDef()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_TRANSFORMING)) {
            res_.setResult(new StdStruct(instance_.getMovesTransforming(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_NOT_TO_BE_COPIED)) {
            res_.setResult(new StdStruct(instance_.getMovesNotToBeCopied(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectCounterAttackBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectCounterAttackBean instance_ = (EffectCounterAttackBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,SUFFERING_DAMAGE_TYPES)) {
            res_.setResult(new StdStruct(instance_.getSufferingDamageTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DROPPED_STAT_DIRECT_MOVE)) {
            res_.setResult(new StdStruct(instance_.getDroppedStatDirectMove(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SUFFERING_DAMAGE_DIRECT_MOVE)) {
            res_.setResult(new StdStruct(instance_.getSufferingDamageDirectMove(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS_PROTECT)) {
            res_.setResult(new StdStruct(instance_.getReasonsProtect(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS_COUNTER)) {
            res_.setResult(new StdStruct(instance_.getReasonsCounter(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectDamageBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectDamageBean instance_ = (EffectDamageBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,HITS_LAW)) {
            res_.setResult(new StdStruct(instance_.getHitsLaw(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_HITS)) {
            res_.setResult(new LongStruct(instance_.getNbHits()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CONST_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.getConstDamage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,POWER)) {
            res_.setResult(new StringStruct(instance_.getPower()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_LAW)) {
            res_.setResult(new StdStruct(instance_.getDamageLaw(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMapVarsDamage(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE_AGAINST)) {
            res_.setResult(new StdStruct(instance_.getMultDamageAgainst(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CH_RATE)) {
            res_.setResult(new ByteStruct(instance_.getChRate()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CH_LAW)) {
            res_.setResult(new StdStruct(instance_.getChLaw(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,USER_ATTACK)) {
            res_.setResult(BooleanStruct.of(instance_.getUserAttack()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATIS_ATT)) {
            res_.setResult(new StringStruct(instance_.getStatisAtt()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TARGET_DEFENSE)) {
            res_.setResult(BooleanStruct.of(instance_.getTargetDefense()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATIS_DEF)) {
            res_.setResult(new StringStruct(instance_.getStatisDef()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IGN_VAR_STAT_TARGET_POS)) {
            res_.setResult(new StdStruct(instance_.getIgnVarStatTargetPos(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IGN_VAR_STAT_USER_NEG)) {
            res_.setResult(new StdStruct(instance_.getIgnVarStatUserNeg(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RAND_MAX)) {
            res_.setResult(BooleanStruct.of(instance_.getRandMax()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOST_STATIS_ONCE_KO_FOE)) {
            res_.setResult(new StdStruct(instance_.getBoostStatisOnceKoFoe(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SUMMING_USER_TEAM_OK_FIGHTER)) {
            res_.setResult(BooleanStruct.of(instance_.getSummingUserTeamOkFighter()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectDamageRateBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectDamageRateBean instance_ = (EffectDamageRateBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,WIN_HP)) {
            res_.setResult(BooleanStruct.of(instance_.getWinHp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getRateDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectEndRoundMoveBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectEndRoundMoveBean instance_ = (EffectEndRoundMoveBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,END_ROUND_RANK)) {
            res_.setResult(new IntStruct(instance_.getEndRoundRank()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getReasonsEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_FAIL_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getMapVarsFailEndRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectFullHpRateBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectFullHpRateBean instance_ = (EffectFullHpRateBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,LEFT_USER_HP)) {
            res_.setResult(new StdStruct(instance_.getLeftUserHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RESTORED_HP)) {
            res_.setResult(new StringStruct(instance_.getRestoredHp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_RESTORED)) {
            res_.setResult(new StdStruct(instance_.getMapVarsRestored(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CLOSEST_FOE_DAMAGE_RATE_HP)) {
            res_.setResult(new StdStruct(instance_.getClosestFoeDamageRateHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectGlobalBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectGlobalBean instance_ = (EffectGlobalBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,WEATHER)) {
            res_.setResult(BooleanStruct.of(instance_.getWeather()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCELED_IF_USED)) {
            res_.setResult(BooleanStruct.of(instance_.getCanceledIfUsed()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REVERSE_ORDER_OF_SORT_BY_SPEED)) {
            res_.setResult(BooleanStruct.of(instance_.getReverseOrderOfSortBySpeed()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,UNUSABLE_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.getUnusableItem()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PUTTING_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getPuttingKo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_ACCURACY)) {
            res_.setResult(new StdStruct(instance_.getMultAccuracy(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getDamageEndRound(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALING_END_ROUND_GROUND)) {
            res_.setResult(new StdStruct(instance_.getHealingEndRoundGround(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALING_END_ROUND)) {
            res_.setResult(new StdStruct(instance_.getHealingEndRound(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_EFFECT_LOVING_ALLY)) {
            res_.setResult(new StdStruct(instance_.getMultEffectLovingAlly(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PREVENT_STATUS)) {
            res_.setResult(new StdStruct(instance_.getPreventStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMUNE_TYPES)) {
            res_.setResult(new StdStruct(instance_.getImmuneTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EFFICIENCY_MOVES)) {
            res_.setResult(new StdStruct(instance_.getEfficiencyMoves(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISABLE_IMMU_AGAINST_TYPES)) {
            res_.setResult(new StdStruct(instance_.getDisableImmuAgainstTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_PROTECTING_ABILITIES)) {
            res_.setResult(new StdStruct(instance_.getCancelProtectingAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,UNUSABLE_MOVES)) {
            res_.setResult(new StdStruct(instance_.getUnusableMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_EFFECTS)) {
            res_.setResult(new StdStruct(instance_.getCancelEffects(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_POWER_MOVES)) {
            res_.setResult(new StdStruct(instance_.getMultPowerMoves(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE_TYPES_MOVES)) {
            res_.setResult(new StdStruct(instance_.getMultDamageTypesMoves(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_CHGT_STAT)) {
            res_.setResult(new StdStruct(instance_.getCancelChgtStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKED_MOVE_TERRAIN)) {
            res_.setResult(new StringStruct(instance_.getInvokedMoveTerrain()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_MOVES)) {
            res_.setResult(new StdStruct(instance_.getInvokingMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHANGED_TYPES_TERRAIN)) {
            res_.setResult(new StdStruct(instance_.getChangedTypesTerrain(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_MOVES_CHANGING_TYPES)) {
            res_.setResult(new StdStruct(instance_.getInvokingMovesChangingTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_IF_CONTAINS_TYPE)) {
            res_.setResult(new StdStruct(instance_.getMultStatIfContainsType(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE_PREPA_ROUND)) {
            res_.setResult(new StdStruct(instance_.getMultDamagePrepaRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_USED_BY_TARGETED_FIGHTERS)) {
            res_.setResult(new StdStruct(instance_.getMovesUsedByTargetedFighters(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectInvokeBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectInvokeBean instance_ = (EffectInvokeBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INVOKING_MOVE_BUT_USER)) {
            res_.setResult(BooleanStruct.of(instance_.getInvokingMoveButUser()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_TARGET_CHOSEN_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.getInvokingTargetChosenMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_USER_MOVE_WHILE_SLEEP)) {
            res_.setResult(BooleanStruct.of(instance_.getInvokingUserMoveWhileSleep()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_ALLY_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.getInvokingAllyMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_TARGET_SUCCESFUL_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.getInvokingTargetSuccesfulMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_SUFFERED_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.getInvokingSufferedMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_INVOKATION_MOVE)) {
            res_.setResult(new StdStruct(instance_.getRateInvokationMove(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVE_FCT_ENV)) {
            res_.setResult(new StdStruct(instance_.getMoveFctEnv(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GLOBAL_MOVES)) {
            res_.setResult(new StdStruct(instance_.getGlobalMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INVOKING_MOVE_BY_USER_TYPES)) {
            res_.setResult(new StdStruct(instance_.getInvokingMoveByUserTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_NOT_TO_BE_INVOKED)) {
            res_.setResult(new StdStruct(instance_.getMovesNotToBeInvoked(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectMultSufferedMovePowerBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectMultSufferedMovePowerBean instance_ = (EffectMultSufferedMovePowerBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MULT_MOVE_POWER_FCT_TYPE)) {
            res_.setResult(new StdStruct(instance_.getMultMovePowerFctType(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectMultUsedMovePowerBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectMultUsedMovePowerBean instance_ = (EffectMultUsedMovePowerBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MULT_MOVE_POWER_FCT_TYPE)) {
            res_.setResult(new StdStruct(instance_.getMultMovePowerFctType(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectOrderBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectOrderBean instance_ = (EffectOrderBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TARGET_ATTACKS_LAST)) {
            res_.setResult(BooleanStruct.of(instance_.getTargetAttacksLast()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectProtectFromTypesBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectProtectFromTypesBean instance_ = (EffectProtectFromTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,IMMU_AGAINST_TYPES)) {
            res_.setResult(new StdStruct(instance_.getImmuAgainstTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectProtectionBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectProtectionBean instance_ = (EffectProtectionBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,PROT_SINGLE)) {
            res_.setResult(BooleanStruct.of(instance_.getProtSingle()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROT_SINGLE_AGAINST_KO)) {
            res_.setResult(new StdStruct(instance_.getProtSingleAgainstKo(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROT_TEAM_AGAINST_MULT_TARGETS)) {
            res_.setResult(BooleanStruct.of(instance_.getProtTeamAgainstMultTargets()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROT_TEAM_AGAINST_PRIO)) {
            res_.setResult(BooleanStruct.of(instance_.getProtTeamAgainstPrio()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROT_TEAM_AGAINST_STATUS_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.getProtTeamAgainstStatusMoves()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROT_TEAM_AGAINST_DAMAGE_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.getProtTeamAgainstDamageMoves()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectRemainedHpRateBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectRemainedHpRateBean instance_ = (EffectRemainedHpRateBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,WIN_HP)) {
            res_.setResult(BooleanStruct.of(instance_.getWinHp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_HP)) {
            res_.setResult(new StdStruct(instance_.getRateHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectRestrictionBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectRestrictionBean instance_ = (EffectRestrictionBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,FORBID_TARGET_USING_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.getForbidTargetUsingItem()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectStatisticBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectStatisticBean instance_ = (EffectStatisticBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,EVT_RATE)) {
            res_.setResult(new StdStruct(instance_.getEvtRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVT_RATE_PER_CENT)) {
            res_.setResult(new StringStruct(instance_.getEvtRatePerCent()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATIS_VAR_RANK)) {
            res_.setResult(new StdStruct(instance_.getStatisVarRank(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_STATISTICS)) {
            res_.setResult(new StdStruct(instance_.getMapVarsStatistics(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SWAP_BOOST_STATIS)) {
            res_.setResult(new StdStruct(instance_.getSwapBoostStatis(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_LOW_STAT)) {
            res_.setResult(new StdStruct(instance_.getCancelLowStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DEFAULT_BOOST)) {
            res_.setResult(new IntStruct(instance_.getDefaultBoost()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_CHGT_STAT)) {
            res_.setResult(new StdStruct(instance_.getCancelChgtStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPY_BOOST)) {
            res_.setResult(new StdStruct(instance_.getCopyBoost(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectStatusBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectStatusBean instance_ = (EffectStatusBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,LAW_STATUS)) {
            res_.setResult(new StdStruct(instance_.getLawStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_STATUS)) {
            res_.setResult(new StdStruct(instance_.getMapVarsStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DELETED_STATUS)) {
            res_.setResult(new StdStruct(instance_.getDeletedStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,KO_USER_HEAL_SUBST)) {
            res_.setResult(BooleanStruct.of(instance_.getKoUserHealSubst()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS_FROM_USER)) {
            res_.setResult(BooleanStruct.of(instance_.getStatusFromUser()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectSwitchMoveTypesBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectSwitchMoveTypesBean instance_ = (EffectSwitchMoveTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,REPLACING_TYPES)) {
            res_.setResult(new StdStruct(instance_.getReplacingTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHANGE_TYPES)) {
            res_.setResult(new StdStruct(instance_.getChangeTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectSwitchTypesBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectSwitchTypesBean instance_ = (EffectSwitchTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,CHGT_TYPE_BY_ENV)) {
            res_.setResult(new StdStruct(instance_.getChgtTypeByEnv(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GLOBAL_MOVES)) {
            res_.setResult(new StdStruct(instance_.getGlobalMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ADDED_TYPES)) {
            res_.setResult(new StdStruct(instance_.getAddedTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CONST_TYPES)) {
            res_.setResult(new StdStruct(instance_.getConstTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectTeamBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectTeamBean instance_ = (EffectTeamBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,FORBIDDING_HEALING)) {
            res_.setResult(BooleanStruct.of(instance_.getForbiddingHealing()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_AGAINST_CH)) {
            res_.setResult(BooleanStruct.of(instance_.getProtectAgainstCh()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FORBIDDEN_BOOST)) {
            res_.setResult(new StdStruct(instance_.getForbiddenBoost(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_CHGT_STAT_FOE_TEAM)) {
            res_.setResult(new StdStruct(instance_.getCancelChgtStatFoeTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DEFAULT_BOOST)) {
            res_.setResult(new IntStruct(instance_.getDefaultBoost()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_CHGT_STAT_TEAM)) {
            res_.setResult(new StdStruct(instance_.getCancelChgtStatTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_AGAINST_LOW_STAT)) {
            res_.setResult(new StdStruct(instance_.getProtectAgainstLowStat(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_AGAINST_STATUS)) {
            res_.setResult(new StdStruct(instance_.getProtectAgainstStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STATISTIC)) {
            res_.setResult(new StdStruct(instance_.getMultStatistic(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STATISTIC_FOE)) {
            res_.setResult(new StdStruct(instance_.getMultStatisticFoe(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getMultDamage(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,UNUSABLE_MOVES)) {
            res_.setResult(new StdStruct(instance_.getUnusableMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISABLE_FOE_TEAM_EFFECTS)) {
            res_.setResult(new StdStruct(instance_.getDisableFoeTeamEffects(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISABLE_FOE_TEAM_STATUS)) {
            res_.setResult(new StdStruct(instance_.getDisableFoeTeamStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectTeamWhileSendFoeBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectTeamWhileSendFoeBean instance_ = (EffectTeamWhileSendFoeBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_AGAINST_FOE)) {
            res_.setResult(new StringStruct(instance_.getDamageRateAgainstFoe()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_DAMAGE_SENT_FOE)) {
            res_.setResult(new StdStruct(instance_.getMapVarsDamageSentFoe(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATISTICS)) {
            res_.setResult(new StdStruct(instance_.getStatistics(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS_BY_NB_USES)) {
            res_.setResult(new StdStruct(instance_.getStatusByNbUses(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS_SENDING)) {
            res_.setResult(new StdStruct(instance_.getReasonsSending(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_FAIL_SENDING)) {
            res_.setResult(new StdStruct(instance_.getMapVarsFailSending(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DELETED_BY_FOE_TYPES)) {
            res_.setResult(new StdStruct(instance_.getDeletedByFoeTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectUnprotectFromTypesBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EffectUnprotectFromTypesBean instance_ = (EffectUnprotectFromTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPES)) {
            res_.setResult(new StdStruct(instance_.getTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISABLE_IMMU_AGAINST_TYPES)) {
            res_.setResult(new StdStruct(instance_.getDisableImmuAgainstTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISABLE_IMMU_FROM_MOVES)) {
            res_.setResult(new StdStruct(instance_.getDisableImmuFromMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ATTACK_TARGET_WITH_TYPES)) {
            res_.setResult(new StdStruct(instance_.getAttackTargetWithTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectVarPPBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectVarPPBean instance_ = (EffectVarPPBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DELETE_PP)) {
            res_.setResult(new ShortStruct(instance_.getDeletePp()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEffectWinMoneyBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectWinMoneyBean instance_ = (EffectWinMoneyBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,WINNING_RATE_BY_SUM_TARGET_USER)) {
            res_.setResult(new StdStruct(instance_.getWinningRateBySumTargetUser(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansMovesEffectsStd.getResultEffectBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd setResultEffectBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EffectBean instance_ = (EffectBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            instance_.setIndex((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVE)) {
            instance_.setMove((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectBatonPassBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectBatonPassBean instance_ = (EffectBatonPassBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrMove((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectBean instance_ = (EffectBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_ADJ_ADV)) {
            res_.setResult(BooleanStruct.of(instance_.isAdjAdv()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ADJ_MULT)) {
            res_.setResult(BooleanStruct.of(instance_.isAdjMult()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ADJ_UNIQ)) {
            res_.setResult(BooleanStruct.of(instance_.isAdjUniq()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ALLIE)) {
            res_.setResult(BooleanStruct.of(instance_.isAllie()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ALLIES)) {
            res_.setResult(BooleanStruct.of(instance_.isAllies()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ANY_FOE)) {
            res_.setResult(BooleanStruct.of(instance_.isAnyFoe()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_AUTRE_UNIQ)) {
            res_.setResult(BooleanStruct.of(instance_.isAutreUniq()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_GLOBALE)) {
            res_.setResult(BooleanStruct.of(instance_.isGlobale()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_LANCEUR)) {
            res_.setResult(BooleanStruct.of(instance_.isLanceur()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_PSEUDO_GLOBALE)) {
            res_.setResult(BooleanStruct.of(instance_.isPseudoGlobale()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_TOUS_ADV)) {
            res_.setResult(BooleanStruct.of(instance_.isTousAdv()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_UNIQUE_IMPORTE)) {
            res_.setResult(BooleanStruct.of(instance_.isUniqueImporte()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEffectCloneBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectCloneBean instance_ = (EffectCloneBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_MOVE_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.clickMoveEndRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_END_ROUND)) {
            res_.setResult(new StringStruct(instance_.getTrMovesEndRound((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE_BATON_PASS)) {
            res_.setResult(new StringStruct(instance_.clickMoveBatonPass((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_BATON_PASS)) {
            res_.setResult(new StringStruct(instance_.getTrMovesBatonPass((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE_SENDING)) {
            res_.setResult(new StringStruct(instance_.clickMoveSending((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_SENDING)) {
            res_.setResult(new StringStruct(instance_.getTrMovesSending((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectCommonStatisticsBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectCommonStatisticsBean instance_ = (EffectCommonStatisticsBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getTrStatistic((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectCopyMoveBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectCopyMoveBean instance_ = (EffectCopyMoveBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_DEFAULT_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickDefaultMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DEFAULT_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrDefaultMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,COPY_MOVE_FOR_USER)) {
            res_.setResult(BooleanStruct.of(instance_.copyMoveForUser()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE_TRANS)) {
            res_.setResult(new StringStruct(instance_.clickMoveTrans((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVE_TRANS)) {
            res_.setResult(new StringStruct(instance_.getTrMoveTrans((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrMove((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectCounterAttackBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        EffectCounterAttackBean instance_ = (EffectCounterAttackBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_SUFFERING_DAMAGE_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrSufferingDamageTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DROPPED_STAT_DIRECT_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrDroppedStatDirectMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MAP_VARS_FAIL_COUNTER)) {
            res_.setResult(new StdStruct(instance_.getMapVarsFailCounter(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectDamageBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectDamageBean instance_ = (EffectDamageBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,HAS_LAW_FOR_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.hasLawForDamage()));
            return res_;
        }
        if (StringList.quickEq(methodName_,HAS_DETERMINATED_LAW_FOR_DAMAGE)) {
            res_.setResult(BooleanStruct.of(instance_.hasDeterminatedLawForDamage()));
            return res_;
        }
        if (StringList.quickEq(methodName_,COUNTER_DAMAGE_CAT)) {
            res_.setResult(BooleanStruct.of(instance_.counterDamageCat()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CONST_POWER)) {
            res_.setResult(BooleanStruct.of(instance_.constPower()));
            return res_;
        }
        if (StringList.quickEq(methodName_,HAS_CONST_POWER)) {
            res_.setResult(BooleanStruct.of(instance_.hasConstPower()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_STATIS_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTranslatedStatisTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_STATIS_USER)) {
            res_.setResult(new StringStruct(instance_.getTranslatedStatisUser((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_STATIS_KO)) {
            res_.setResult(new StringStruct(instance_.getTranslatedStatisKo((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectGlobalBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectGlobalBean instance_ = (EffectGlobalBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_PREVENTED_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickPreventedStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_PREVENTED_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrPreventedStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_CANCELLED_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickCancelledAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CANCELLED_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrCancelledAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_UNUSABLE_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickUnusableMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_UNUSABLE_MOVES)) {
            res_.setResult(new StringStruct(instance_.getTrUnusableMoves((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_CANCELLED_EFFECT)) {
            res_.setResult(new StringStruct(instance_.clickCancelledEffect((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CANCELLED_EFFECT)) {
            res_.setResult(new StringStruct(instance_.getTrCancelledEffect((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MULT_MOVE_POWER)) {
            res_.setResult(new StringStruct(instance_.clickMultMovePower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_MOVE_POWER)) {
            res_.setResult(new StringStruct(instance_.getTrMultMovePower((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_INVOKED_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickInvokedMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_INVOKED_MOVE_TERRAIN)) {
            res_.setResult(new StringStruct(instance_.getTrInvokedMoveTerrain()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_INVOKING_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickInvokingMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_INVOKING_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrInvokingMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_INVOKING_MOVE_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickInvokingMoveTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_INVOKING_MOVE_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrInvokingMoveTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_DAMGE_TYPE_FIRST)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfDamgeTypeFirst((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_IF_DAMGE_TYPE_SECOND)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatIfDamgeTypeSecond((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVES_TARGET)) {
            res_.setResult(new StringStruct(instance_.clickMovesTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVES_TARGET)) {
            res_.setResult(new StringStruct(instance_.getTrMovesTarget((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectInvokeBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectInvokeBean instance_ = (EffectInvokeBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_ENV)) {
            res_.setResult(new StringStruct(instance_.getTrEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE_FCT_ENV)) {
            res_.setResult(new StringStruct(instance_.clickMoveFctEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVE_FCT_ENV)) {
            res_.setResult(new StringStruct(instance_.getTrMoveFctEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_GLOBAL_MOVE_FCT_ENV)) {
            res_.setResult(new StringStruct(instance_.clickGlobalMoveFctEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_GLOBAL_MOVE_FCT_ENV)) {
            res_.setResult(new StringStruct(instance_.getTrGlobalMoveFctEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_TYPE)) {
            res_.setResult(BooleanStruct.of(instance_.isType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_USER_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrUserTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE_USER_TYPES)) {
            res_.setResult(new StringStruct(instance_.clickMoveUserTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVE_USER_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrMoveUserTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE_NOT_INVOK)) {
            res_.setResult(new StringStruct(instance_.clickMoveNotInvok((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MOVE_NOT_INVOK)) {
            res_.setResult(new StringStruct(instance_.getTrMoveNotInvok((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectMultSufferedMovePowerBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectMultSufferedMovePowerBean instance_ = (EffectMultSufferedMovePowerBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrType((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectMultUsedMovePowerBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectMultUsedMovePowerBean instance_ = (EffectMultUsedMovePowerBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrType((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectProtectFromTypesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectProtectFromTypesBean instance_ = (EffectProtectFromTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrType((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectRestrictionBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectRestrictionBean instance_ = (EffectRestrictionBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,FORBID)) {
            res_.setResult(BooleanStruct.of(instance_.forbid()));
            return res_;
        }
        if (StringList.quickEq(methodName_,FORBID_STATUS_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.forbidStatusMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,FORBID_LAST_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.forbidLastMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,FORBID_USER_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.forbidUserMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,FORBID_USE_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.forbidUseMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,FORCE_USE_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.forceUseMove()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectStatisticBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectStatisticBean instance_ = (EffectStatisticBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,RANDOM_STATIS)) {
            res_.setResult(BooleanStruct.of(instance_.randomStatis()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ALWAYS_ENABLED)) {
            res_.setResult(BooleanStruct.of(instance_.isAlwaysEnabled()));
            return res_;
        }
        if (StringList.quickEq(methodName_,NOT_EMPTY_VAR_BOOST)) {
            res_.setResult(BooleanStruct.of(instance_.notEmptyVarBoost()));
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
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectStatusBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectStatusBean instance_ = (EffectStatusBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_STATUS)) {
            res_.setResult(BooleanStruct.of(instance_.isStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LINK)) {
            res_.setResult(new StringStruct(instance_.clickLink((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_LINK)) {
            res_.setResult(new StringStruct(instance_.getTrLink((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_FAIL)) {
            res_.setResult(new StringStruct(instance_.getFail((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LINK_DELETED)) {
            res_.setResult(new StringStruct(instance_.clickLinkDeleted((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_LINK_DELETED)) {
            res_.setResult(new StringStruct(instance_.getTrLinkDeleted((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectSwitchAbilitiesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectSwitchAbilitiesBean instance_ = (EffectSwitchAbilitiesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GIVE_TO_TARGET)) {
            res_.setResult(BooleanStruct.of(instance_.giveToTarget()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GIVE_TO_USER)) {
            res_.setResult(BooleanStruct.of(instance_.giveToUser()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GIVE_CONST)) {
            res_.setResult(BooleanStruct.of(instance_.giveConst()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_DEF_ABILITY)) {
            res_.setResult(BooleanStruct.of(instance_.isDefAbility()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrAbility((Integer)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,SWITCH_ABILITIES)) {
            res_.setResult(BooleanStruct.of(instance_.switchAbilities()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectSwitchItemsBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectSwitchItemsBean instance_ = (EffectSwitchItemsBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,DELETE_TARGET_BERRY)) {
            res_.setResult(BooleanStruct.of(instance_.deleteTargetBerry()));
            return res_;
        }
        if (StringList.quickEq(methodName_,TAKE_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.takeItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,REMOVE_TARGET_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.removeTargetItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SWITCH_ITEMS)) {
            res_.setResult(BooleanStruct.of(instance_.switchItems()));
            return res_;
        }
        if (StringList.quickEq(methodName_,RESUSE_LAST_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.resuseLastItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GIVE_TARGET_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.giveTargetItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,USE_ITEM_AS_POSSIBLE)) {
            res_.setResult(BooleanStruct.of(instance_.useItemAsPossible()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectSwitchMoveTypesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectSwitchMoveTypesBean instance_ = (EffectSwitchMoveTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_REPLACING_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrReplacingTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CHANGED_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrChangedTypes((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectSwitchPointViewBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectSwitchPointViewBean instance_ = (EffectSwitchPointViewBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,THIEVE_BONUS)) {
            res_.setResult(BooleanStruct.of(instance_.thieveBonus()));
            return res_;
        }
        if (StringList.quickEq(methodName_,MIRROR_AGAINST_USER)) {
            res_.setResult(BooleanStruct.of(instance_.mirrorAgainstUser()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ATTRACT_DAMAGE_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.attractDamageMoves()));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectSwitchTypesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectSwitchTypesBean instance_ = (EffectSwitchTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_RES_TYPES)) {
            res_.setResult(BooleanStruct.of(instance_.isResTypes()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_USER_TYPES)) {
            res_.setResult(BooleanStruct.of(instance_.isUserTypes()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_CONST_TYPES)) {
            res_.setResult(BooleanStruct.of(instance_.isConstTypes()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ENV)) {
            res_.setResult(new StringStruct(instance_.getTrEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_GLOBAL_MOVE_FCT_ENV)) {
            res_.setResult(new StringStruct(instance_.clickGlobalMoveFctEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_GLOBAL_MOVE_FCT_ENV)) {
            res_.setResult(new StringStruct(instance_.getTrGlobalMoveFctEnv((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ADDED_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrAddedType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GIVE_TO_TARGET)) {
            res_.setResult(BooleanStruct.of(instance_.giveToTarget()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GIVE_TO_USER)) {
            res_.setResult(BooleanStruct.of(instance_.giveToUser()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SWITCH_TYPES)) {
            res_.setResult(BooleanStruct.of(instance_.switchTypes()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GIVE_CONST)) {
            res_.setResult(BooleanStruct.of(instance_.giveConst()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CONST_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrConstType((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectTeamBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectTeamBean instance_ = (EffectTeamBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_UNUSABLE_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickUnusableMove((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_UNUSABLE_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrUnusableMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DISABLE_FOE_TEAM_EFFECTS)) {
            res_.setResult(new StringStruct(instance_.clickDisableFoeTeamEffects((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DISABLE_FOE_TEAM_EFFECTS)) {
            res_.setResult(new StringStruct(instance_.getTrDisableFoeTeamEffects((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DISABLE_FOE_TEAM_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickDisableFoeTeamStatus((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DISABLE_FOE_TEAM_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrDisableFoeTeamStatus((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectTeamWhileSendFoeBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectTeamWhileSendFoeBean instance_ = (EffectTeamWhileSendFoeBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TRANSLATED_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getTranslatedStatistic((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTranslatedStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTranslatedType((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEffectUnprotectFromTypesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EffectUnprotectFromTypesBean instance_ = (EffectUnprotectFromTypesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_TR_DAMAGE_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrDamageType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DISABLE_IMMU_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrDisableImmuType((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_DISABLE_IMMU_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrDisableImmuMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_ATTACK_TARGET_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTrAttackTargetType((Long)_args[0])));
            return res_;
        }
        return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_cont, _instance, _method, _args);
    }
}
