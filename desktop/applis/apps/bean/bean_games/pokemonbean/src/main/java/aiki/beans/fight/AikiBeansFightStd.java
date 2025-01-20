package aiki.beans.fight;

import aiki.beans.FighterNamePkNameMvStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.fight.KeyHypothesis;
import aiki.beans.facade.fight.MultPowerMoves;
import aiki.beans.facade.fight.StatisticInfo;
import aiki.beans.facade.fight.SufferedDamageCategory;
import aiki.game.fight.*;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.MoveTarget;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.*;
import code.util.core.BoolVal;

public final class AikiBeansFightStd{
    public static final String TYPE_COMMON_FIGHT_BEAN = "aiki.beans.fight.CommonFightBean";
    public static final String TYPE_FIGHT_BEAN = "aiki.beans.fight.FightBean";
    public static final String TYPE_FIGHT_CALCULATION_BEAN = "aiki.beans.fight.FightCalculationBean";
    public static final String TYPE_FIGHTER_BEAN = "aiki.beans.fight.FighterBean";
    public static final String TYPE_TEAM_BEAN = "aiki.beans.fight.TeamBean";
    public static final String TYPE_HYPOTHESIS = "hp";
    private static final String HYPOTHESIS_PK = "pk";
    private static final String HYPOTHESIS_MV = "mv";
    private static final String CLICK_PLAYER = "clickPlayer";
    private static final String CLICK_FOE = "clickFoe";
    private static final String IS_STILL_ENABLED = "isStillEnabled";
    private static final String GET_FIGHTER = "getFighter";
    private static final String GET_FIGHTER_WILD_FIGHT = "getFighterWildFight";
    private static final String IS_FOE_TARGET_CHOICE_TEAM = "isFoeTargetChoiceTeam";
    private static final String IS_BACK_TARGET_CHOICE_TEAM = "isBackTargetChoiceTeam";
    private static final String GET_TARGET_NAME_ALLY_CHOICE_CONDITION = "getTargetNameAllyChoiceCondition";
    private static final String IS_FOE_TARGET_TEAM = "isFoeTargetTeam";
    private static final String IS_BACK_TARGET_TEAM = "isBackTargetTeam";
    private static final String GET_TARGET_NAME_ALLY_CHOICE = "getTargetNameAllyChoice";
    private static final String GET_FOE_FIGHTER_NAME = "getFoeFighterName";
    private static final String IS_CHOSEN_TARGET = "isChosenTarget";
    private static final String IS_FOE_TARGET_CH_TEAM = "isFoeTargetChTeam";
    private static final String GET_TARGET_NAME_FOE_CHOICE = "getTargetNameFoeChoice";
    private static final String IS_BACK_SUBST = "isBackSubst";
    private static final String IS_BACK = "isBack";
    private static final String IS_FOE_STATUS_RELAT_TEAM = "isFoeStatusRelatTeam";
    private static final String GET_STATUS_RELAT_TEAM = "getStatusRelatTeam";
    private static final String IS_ENABLED = "isEnabled";
    private static final String IS_FOE_PRIVATE_MOVES_TEAM = "isFoePrivateMovesTeam";
    private static final String GET_INCR_PRIVATE_MOVES_TEAM = "getIncrPrivateMovesTeam";
    private static final String IS_FOE_TRAPPING_MOVES_TEAM = "isFoeTrappingMovesTeam";
    private static final String GET_INCR_TRAPPING_MOVES_TEAM = "getIncrTrappingMovesTeam";
    private static final String IS_FOE_TRACKING_MOVES_TEAM = "isFoeTrackingMovesTeam";
    private static final String GET_INCR_TRACKING_MOVES_TEAM = "getIncrTrackingMovesTeam";
    private static final String IS_FOE_INCR_USER_ACCURACY_TEAM = "isFoeIncrUserAccuracyTeam";
    private static final String GET_INCR_USER_ACCURACY_TEAM = "getIncrUserAccuracyTeam";
    private static final String GET_MEMBERS = "getMembers";
    private static final String CLICK_FIGHTER = "clickFighter";
    private static final String GET_TR_POKEMON_LINK = "getTrPokemonLink";
    private static final String GET_KEY = "getKey";
    private static final String IS_FOE_MOVES_ANTICIPATION_TEAM = "isFoeMovesAnticipationTeam";
    private static final String IS_PLAYER_MOVES_ANTICIPATION_TEAM = "isPlayerMovesAnticipationTeam";
    private static final String IS_BACK_MOVES_ANTICIPATION_TEAM = "isBackMovesAnticipationTeam";
    private static final String GET_PLAYER_FIGTHER_AGAINST_FOE = "getPlayerFigtherAgainstFoe";
    private static final String GET_FOE_FIGTHER_AGAINST_FOE = "getFoeFigtherAgainstFoe";
    private static final String MULT = "mult";
    private static final String NB_ROUNDS = "nbRounds";
    private static final String NB_FLEE_ATTEMPT = "nbFleeAttempt";
    private static final String WINNING_MONEY = "winningMoney";
    private static final String ENABLED_MOVES = "enabledMoves";
    private static final String SORTED_FIGHTERS = "sortedFighters";
    private static final String SORTED_FIGHTERS_WILD_FIGHT = "sortedFightersWildFight";
    private static final String DAMAGE = "damage";
    private static final String ALLY_CHOICE = "allyChoice";
    private static final String FOE_CHOICES = "foeChoices";
    private static final String NAME = "name";
    private static final String CHANGED = "changed";
    private static final String CURRENT_NAME = "currentName";
    private static final String CURRENT_GENDER = "currentGender";
    private static final String USED_BALL_CATCHING = "usedBallCatching";
    private static final String BELONGING_TO_PLAYER = "belongingToPlayer";
    private static final String GROUND_PLACE_SUBST = "groundPlaceSubst";
    private static final String GROUND_PLACE = "groundPlace";
    private static final String NICKNAME = "nickname";
    private static final String HAPPINESS = "happiness";
    private static final String LEVEL = "level";
    private static final String WON_EXP_SINCE_LAST_LEVEL = "wonExpSinceLastLevel";
    private static final String NECESSARY_POINTS_NEXT_LEVEL = "necessaryPointsNextLevel";
    private static final String GENDER = "gender";
    private static final String WEIGHT = "weight";
    private static final String WEIGHT_STR = "weightStr";
    private static final String HEIGHT = "height";
    private static final String HEIGHT_STR = "heightStr";
    private static final String REMAINING_HP = "remainingHp";
    private static final String MAX_HP = "maxHp";
    private static final String REMAINING_HP_STR = "remainingHpStr";
    private static final String REMAINING_HP_STR_PER_CENT = "remainingHpStrPerCent";
    private static final String CLONE = "clone";
    private static final String CLONE_STR = "cloneStr";
    private static final String ABILITY = "ability";
    private static final String CURRENT_ABILITY = "currentAbility";
    private static final String ACTED = "acted";
    private static final String LAST_USED_ITEM = "lastUsedItem";
    private static final String ITEM = "item";
    private static final String EXP_ITEM = "expItem";
    private static final String USING_ITEM = "usingItem";
    private static final String TYPES = "types";
    private static final String MOVES = "moves";
    private static final String CURRENT_MOVES = "currentMoves";
    private static final String COPIED_MOVES = "copiedMoves";
    private static final String STATISTICS = "statistics";
    private static final String DAMAGE_RATE_BY_TYPE = "damageRateByType";
    private static final String PROTECTED_AGAINST_MOVE_TYPES = "protectedAgainstMoveTypes";
    private static final String DAMAGE_SUFFERED_CATEG = "damageSufferedCateg";
    private static final String ENABLED_MOVES_FOR_ALLY = "enabledMovesForAlly";
    private static final String NB_USES_MOVES = "nbUsesMoves";
    private static final String NB_REPEATING_SUCCESSFUL_MOVES = "nbRepeatingSuccessfulMoves";
    private static final String SUCCESSFUL_MOVE = "successfulMove";
    private static final String LAST_SUCCESSFUL_MOVE = "lastSuccessfulMove";
    private static final String LAST_USED_MOVE = "lastUsedMove";
    private static final String USED_MOVE_LAST_ROUND = "usedMoveLastRound";
    private static final String NB_PREPA_ROUND = "nbPrepaRound";
    private static final String ALREADY_INVOKED_MOVES_ROUND = "alreadyInvokedMovesRound";
    private static final String LAST_SUFFERED_MOVE = "lastSufferedMove";
    private static final String LAST_SUFFERED_MOVE_TYPES = "lastSufferedMoveTypes";
    private static final String DISAPPEARED = "disappeared";
    private static final String NEEDING_TO_RECHARGE = "needingToRecharge";
    private static final String STATUS = "status";
    private static final String STATUS_RELAT = "statusRelat";
    private static final String PRIVATE_MOVES = "privateMoves";
    private static final String TRAPPING_MOVES = "trappingMoves";
    private static final String TRACKING_MOVES = "trackingMoves";
    private static final String INCR_USER_ACCURACY = "incrUserAccuracy";
    private static final String FOE_TEAM = "foeTeam";
    private static final String ENABLED_MOVES_BY_GROUP = "enabledMovesByGroup";
    private static final String ENABLED_MOVES_WHILE_SENDING_FOE_USES = "enabledMovesWhileSendingFoeUses";
    private static final String HEAL_AFTER = "healAfter";
    private static final String MOVES_ANTICIPATION = "movesAnticipation";
    private static final String PLAYER_FIGHTERS_AGAINST_FOE = "playerFightersAgainstFoe";

    private AikiBeansFightStd(){}
    public static void build(PokemonStandards _std) {
        buildCommonFightBean(_std);
        buildFightBean(_std);
        buildFightCalculationBean(_std);
        buildFightHypothesisBean(_std);
        buildFighterBean(_std);
        buildTeamBean(_std);
    }
    private static void buildCommonFightBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        methods_.add( new SpecNatMethod("f", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML)));
        methods_.add( new SpecNatMethod("d", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML)));
        methods_.add( new SpecNatMethod("e", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.WEB_FIGHT_HTML_FIGHTER_HTML)));
        methods_.add( new SpecNatMethod("t", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML)));
        _std.getStds().addEntry(TYPE_COMMON_FIGHT_BEAN, type_);
    }
    private static void buildFightBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(MULT, BeanNatCommonLgNames.PRIM_INTEGER, new FightBeanMultGet(),null));
        fields_.add(new StandardField(NB_ROUNDS,BeanNatCommonLgNames.TYPE_LG_INT, new FightBeanNbRoundsGet(),null));
        fields_.add(new StandardField(NB_FLEE_ATTEMPT, BeanNatCommonLgNames.PRIM_INTEGER, new FightBeanNbFleeAttemptGet(),null));
        fields_.add(new StandardField(WINNING_MONEY,BeanNatCommonLgNames.TYPE_RATE, new FightBeanWinningMoneyGet(),null));
        fields_.add(new StandardField(ENABLED_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FightBeanEnabledMovesGet(),null));
        methods_.add( new SpecNatMethod(CLICK_PLAYER,BeanNatCommonLgNames.STRING, new FightBeanClickPlayer()));
        methods_.add( new SpecNatMethod(CLICK_FOE,BeanNatCommonLgNames.STRING, new FightBeanClickFoe()));
        methods_.add( new SpecNatMethod(IS_STILL_ENABLED,BeanNatCommonLgNames.PRIM_BOOLEAN, new FightBeanIsStillEnabled()));
        _std.getStds().addEntry(TYPE_FIGHT_BEAN, type_);
    }
    private static void buildFightCalculationBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(SORTED_FIGHTERS, BeanNatCommonLgNames.TYPE_LIST, new FightCalculationBeanSortedFightersGet(),null));
        fields_.add(new StandardField(SORTED_FIGHTERS_WILD_FIGHT, BeanNatCommonLgNames.TYPE_MAP, new FightCalculationBeanSortedFightersWildFightGet(),null));
        fields_.add(new StandardField(DAMAGE, BeanNatCommonLgNames.TYPE_LIST, new FightCalculationBeanDamageGet(),null));
        fields_.add(new StandardField(ALLY_CHOICE, BeanNatCommonLgNames.TYPE_MAP, new FightCalculationBeanAllyChoiceGet(),null));
        fields_.add(new StandardField(FOE_CHOICES, BeanNatCommonLgNames.TYPE_MAP, new FightCalculationBeanFoeChoicesGet(),null));
        methods_.add( new SpecNatMethod(GET_FIGHTER,BeanNatCommonLgNames.STRING, new FightCalculationBeanGetFighter()));
        methods_.add( new SpecNatMethod(GET_FIGHTER_WILD_FIGHT,BeanNatCommonLgNames.STRING, new FightCalculationBeanGetFighterWildFight()));
        methods_.add( new SpecNatMethod(IS_FOE_TARGET_CHOICE_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FightCalculationBeanIsFoeTargetChoiceTeam()));
        methods_.add( new SpecNatMethod(IS_BACK_TARGET_CHOICE_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FightCalculationBeanIsBackTargetChoiceTeam()));
        methods_.add( new SpecNatMethod(GET_TARGET_NAME_ALLY_CHOICE_CONDITION,BeanNatCommonLgNames.STRING, new FightCalculationBeanGetTargetNameAllyChoiceCondition()));
        methods_.add( new SpecNatMethod(IS_FOE_TARGET_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FightCalculationBeanIsFoeTargetTeam()));
        methods_.add( new SpecNatMethod(IS_BACK_TARGET_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FightCalculationBeanIsBackTargetTeam()));
        methods_.add( new SpecNatMethod(GET_TARGET_NAME_ALLY_CHOICE,BeanNatCommonLgNames.STRING, new FightCalculationBeanGetTargetNameAllyChoice()));
        methods_.add( new SpecNatMethod(GET_FOE_FIGHTER_NAME,BeanNatCommonLgNames.STRING, new FightCalculationBeanGetFoeFighterName()));
        methods_.add( new SpecNatMethod(IS_CHOSEN_TARGET,BeanNatCommonLgNames.PRIM_BOOLEAN, new FightCalculationBeanIsChosenTarget()));
        methods_.add( new SpecNatMethod(IS_FOE_TARGET_CH_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FightCalculationBeanIsFoeTargetChTeam()));
        methods_.add( new SpecNatMethod(GET_TARGET_NAME_FOE_CHOICE,BeanNatCommonLgNames.STRING, new FightCalculationBeanGetTargetNameFoeChoice()));
        _std.getStds().addEntry(TYPE_FIGHT_CALCULATION_BEAN, type_);
    }
    private static void buildFightHypothesisBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(HYPOTHESIS_PK, BeanNatCommonLgNames.STRING, new FighterNamePkNameMvNamePkGet(),null));
        fields_.add(new StandardField(HYPOTHESIS_MV, BeanNatCommonLgNames.STRING, new FighterNamePkNameMvNameMvGet(),null));
        _std.getStds().addEntry(TYPE_HYPOTHESIS, type_);
    }
    private static void buildFighterBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING, new FighterBeanNameGet(),null));
        fields_.add(new StandardField(CHANGED,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanChangedGet(),null));
        fields_.add(new StandardField(CURRENT_NAME,BeanNatCommonLgNames.STRING, new FighterBeanCurrentNameGet(),null));
        fields_.add(new StandardField(CURRENT_GENDER,BeanNatCommonLgNames.STRING, new FighterBeanCurrentGenderGet(),null));
        fields_.add(new StandardField(USED_BALL_CATCHING,BeanNatCommonLgNames.STRING, new FighterBeanUsedBallCatchingGet(),null));
        fields_.add(new StandardField(BELONGING_TO_PLAYER,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanBelongingToPlayerGet(),null));
        fields_.add(new StandardField(GROUND_PLACE_SUBST, BeanNatCommonLgNames.PRIM_INTEGER, new FighterBeanGroundPlaceSubstGet(),null));
        fields_.add(new StandardField(GROUND_PLACE, BeanNatCommonLgNames.PRIM_INTEGER, new FighterBeanGroundPlaceGet(),null));
        fields_.add(new StandardField(NICKNAME,BeanNatCommonLgNames.STRING, new FighterBeanNicknameGet(),null));
        fields_.add(new StandardField(HAPPINESS, BeanNatCommonLgNames.PRIM_INTEGER, new FighterBeanHappinessGet(),null));
        fields_.add(new StandardField(LEVEL, BeanNatCommonLgNames.PRIM_INTEGER, new FighterBeanLevelGet(),null));
        fields_.add(new StandardField(WON_EXP_SINCE_LAST_LEVEL,BeanNatCommonLgNames.TYPE_RATE, new FighterBeanWonExpSinceLastLevelGet(),null));
        fields_.add(new StandardField(NECESSARY_POINTS_NEXT_LEVEL,BeanNatCommonLgNames.TYPE_RATE, new FighterBeanNecessaryPointsNextLevelGet(),null));
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING, new FighterBeanGenderGet(),null));
        fields_.add(new StandardField(WEIGHT,BeanNatCommonLgNames.TYPE_RATE, new FighterBeanWeightGet(),null));
        fields_.add(new StandardField(WEIGHT_STR,BeanNatCommonLgNames.STRING, new FighterBeanWeightStrGet(),null));
        fields_.add(new StandardField(HEIGHT,BeanNatCommonLgNames.TYPE_RATE, new FighterBeanHeightGet(),null));
        fields_.add(new StandardField(HEIGHT_STR,BeanNatCommonLgNames.STRING, new FighterBeanHeightStrGet(),null));
        fields_.add(new StandardField(REMAINING_HP,BeanNatCommonLgNames.TYPE_RATE, new FighterBeanRemainingHpGet(),null));
        fields_.add(new StandardField(MAX_HP,BeanNatCommonLgNames.TYPE_RATE, new FighterBeanFullHpGet(),null));
        fields_.add(new StandardField(REMAINING_HP_STR,BeanNatCommonLgNames.STRING, new FighterBeanRemainingHpStrGet(),null));
        fields_.add(new StandardField(REMAINING_HP_STR_PER_CENT,BeanNatCommonLgNames.STRING, new FighterBeanRemainingHpStrPerCentGet(),null));
        fields_.add(new StandardField(CLONE,BeanNatCommonLgNames.TYPE_RATE, new FighterBeanCloneGet(),null));
        fields_.add(new StandardField(CLONE_STR,BeanNatCommonLgNames.STRING, new FighterBeanCloneStrGet(),null));
        fields_.add(new StandardField(ABILITY,BeanNatCommonLgNames.STRING, new FighterBeanAbilityGet(),null));
        fields_.add(new StandardField(CURRENT_ABILITY,BeanNatCommonLgNames.STRING, new FighterBeanCurrentAbilityGet(),null));
        fields_.add(new StandardField(ACTED,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanActedGet(),null));
        fields_.add(new StandardField(LAST_USED_ITEM,BeanNatCommonLgNames.STRING, new FighterBeanLastUsedItemGet(),null));
        fields_.add(new StandardField(ITEM,BeanNatCommonLgNames.STRING, new FighterBeanItemGet(),null));
        fields_.add(new StandardField(EXP_ITEM,BeanNatCommonLgNames.STRING, new FighterBeanExpItemGet(),null));
        fields_.add(new StandardField(USING_ITEM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanUsingItemGet(),null));
        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST, new FighterBeanTypesGet(),null));
        fields_.add(new StandardField(MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanMovesGet(),null));
        fields_.add(new StandardField(CURRENT_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanCurrentMovesGet(),null));
        fields_.add(new StandardField(NB_ROUNDS,BeanNatCommonLgNames.TYPE_LG_INT, new FighterBeanNbRoundsGet(),null));
        fields_.add(new StandardField(COPIED_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanCopiedMovesGet(),null));
        fields_.add(new StandardField(STATISTICS, BeanNatCommonLgNames.TYPE_LIST, new FighterBeanStatisticsGet(),null));
        fields_.add(new StandardField(DAMAGE_RATE_BY_TYPE, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanDamageRateByTypeGet(),null));
        fields_.add(new StandardField(PROTECTED_AGAINST_MOVE_TYPES, BeanNatCommonLgNames.TYPE_LIST, new FighterBeanProtectedAgainstMoveTypesGet(),null));
        fields_.add(new StandardField(DAMAGE_SUFFERED_CATEG, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanDamageSufferedCategGet(),null));
        fields_.add(new StandardField(ENABLED_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanEnabledMovesGet(),null));
        fields_.add(new StandardField(ENABLED_MOVES_FOR_ALLY, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanEnabledMovesForAllyGet(),null));
        fields_.add(new StandardField(NB_USES_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanNbUsesMovesGet(),null));
        fields_.add(new StandardField(NB_REPEATING_SUCCESSFUL_MOVES,BeanNatCommonLgNames.TYPE_LG_INT, new FighterBeanNbRepeatingSuccessfulMovesGet(),null));
        fields_.add(new StandardField(SUCCESSFUL_MOVE,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanSuccessfulMoveGet(),null));
        fields_.add(new StandardField(LAST_SUCCESSFUL_MOVE,BeanNatCommonLgNames.STRING, new FighterBeanLastSuccessfulMoveGet(),null));
        fields_.add(new StandardField(LAST_USED_MOVE,BeanNatCommonLgNames.STRING, new FighterBeanLastUsedMoveGet(),null));
        fields_.add(new StandardField(USED_MOVE_LAST_ROUND,BeanNatCommonLgNames.STRING, new FighterBeanUsedMoveLastRoundGet(),null));
        fields_.add(new StandardField(NB_PREPA_ROUND, BeanNatCommonLgNames.PRIM_INTEGER, new FighterBeanNbPrepaRoundGet(),null));
        fields_.add(new StandardField(ALREADY_INVOKED_MOVES_ROUND, BeanNatCommonLgNames.TYPE_LIST, new FighterBeanAlreadyInvokedMovesRoundGet(),null));
        fields_.add(new StandardField(LAST_SUFFERED_MOVE,BeanNatCommonLgNames.STRING, new FighterBeanLastSufferedMoveGet(),null));
        fields_.add(new StandardField(LAST_SUFFERED_MOVE_TYPES, BeanNatCommonLgNames.TYPE_LIST, new FighterBeanLastSufferedMoveTypesGet(),null));
        fields_.add(new StandardField(DISAPPEARED,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanDisappearedGet(),null));
        fields_.add(new StandardField(NEEDING_TO_RECHARGE,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanNeedingToRechargeGet(),null));
        fields_.add(new StandardField(STATUS, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanStatusGet(),null));
        fields_.add(new StandardField(STATUS_RELAT, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanStatusRelatGet(),null));
        fields_.add(new StandardField(PRIVATE_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanPrivateMovesGet(),null));
        fields_.add(new StandardField(TRAPPING_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanTrappingMovesGet(),null));
        fields_.add(new StandardField(TRACKING_MOVES, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanTrackingMovesGet(),null));
        fields_.add(new StandardField(INCR_USER_ACCURACY, BeanNatCommonLgNames.TYPE_MAP, new FighterBeanIncrUserAccuracyGet(),null));
        methods_.add( new SpecNatMethod(IS_BACK_SUBST,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsBackSubst()));
        methods_.add( new SpecNatMethod(IS_BACK,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsBack()));
        methods_.add( new SpecNatMethod(IS_FOE_STATUS_RELAT_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsFoeStatusRelatTeam()));
        methods_.add( new SpecNatMethod(GET_STATUS_RELAT_TEAM,BeanNatCommonLgNames.STRING, new FighterBeanGetStatusRelatTeam()));
        methods_.add( new SpecNatMethod(IS_ENABLED,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsEnabled()));
        methods_.add( new SpecNatMethod(IS_FOE_PRIVATE_MOVES_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsFoePrivateMovesTeam()));
        methods_.add( new SpecNatMethod(GET_INCR_PRIVATE_MOVES_TEAM,BeanNatCommonLgNames.STRING, new FighterBeanGetIncrPrivateMovesTeam()));
        methods_.add( new SpecNatMethod(IS_FOE_TRAPPING_MOVES_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsFoeTrappingMovesTeam()));
        methods_.add( new SpecNatMethod(GET_INCR_TRAPPING_MOVES_TEAM,BeanNatCommonLgNames.STRING, new FighterBeanGetIncrTrappingMovesTeam()));
        methods_.add( new SpecNatMethod(IS_FOE_TRACKING_MOVES_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsFoeTrackingMovesTeam()));
        methods_.add( new SpecNatMethod(GET_INCR_TRACKING_MOVES_TEAM,BeanNatCommonLgNames.STRING, new FighterBeanGetIncrTrackingMovesTeam()));
        methods_.add( new SpecNatMethod(IS_FOE_INCR_USER_ACCURACY_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new FighterBeanIsFoeIncrUserAccuracyTeam()));
        methods_.add( new SpecNatMethod(GET_INCR_USER_ACCURACY_TEAM,BeanNatCommonLgNames.STRING, new FighterBeanGetIncrUserAccuracyTeam()));
        _std.getStds().addEntry(TYPE_FIGHTER_BEAN, type_);
    }
    private static void buildTeamBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(FOE_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new TeamBeanFoeTeamGet(),null));
        fields_.add(new StandardField(ENABLED_MOVES, BeanNatCommonLgNames.TYPE_MAP, new TeamBeanEnabledMovesGet(),null));
        fields_.add(new StandardField(ENABLED_MOVES_BY_GROUP, BeanNatCommonLgNames.TYPE_MAP, new TeamBeanEnabledMovesByGroupGet(),null));
        fields_.add(new StandardField(ENABLED_MOVES_WHILE_SENDING_FOE_USES, BeanNatCommonLgNames.TYPE_MAP, new TeamBeanEnabledMovesWhileSendingFoeUsesGet(),null));
        fields_.add(new StandardField(NB_USES_MOVES, BeanNatCommonLgNames.TYPE_MAP, new TeamBeanNbUsesMovesGet(),null));
        fields_.add(new StandardField(HEAL_AFTER, BeanNatCommonLgNames.TYPE_MAP, new TeamBeanHealAfterGet(),null));
        fields_.add(new StandardField(MOVES_ANTICIPATION, BeanNatCommonLgNames.TYPE_MAP, new TeamBeanMovesAnticipationGet(),null));
        fields_.add(new StandardField(PLAYER_FIGHTERS_AGAINST_FOE, BeanNatCommonLgNames.TYPE_MAP, new TeamBeanPlayerFightersAgainstFoeGet(),null));
        methods_.add( new SpecNatMethod(GET_MEMBERS, BeanNatCommonLgNames.TYPE_LIST, new TeamBeanGetMembers()));
        methods_.add( new SpecNatMethod(CLICK_FIGHTER,BeanNatCommonLgNames.STRING, new TeamBeanClickFighter()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_LINK,BeanNatCommonLgNames.STRING, new TeamBeanGetTrPokemonLink()));
        methods_.add( new SpecNatMethod(GET_KEY,BeanNatCommonLgNames.STRING, new TeamBeanGetKey()));
        methods_.add( new SpecNatMethod(IS_FOE_MOVES_ANTICIPATION_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new TeamBeanIsFoeMovesAnticipationTeam()));
        methods_.add( new SpecNatMethod(IS_PLAYER_MOVES_ANTICIPATION_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new TeamBeanIsPlayerMovesAnticipationTeam()));
        methods_.add( new SpecNatMethod(IS_BACK_MOVES_ANTICIPATION_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new TeamBeanIsBackMovesAnticipationTeam()));
        methods_.add( new SpecNatMethod(GET_PLAYER_FIGTHER_AGAINST_FOE,BeanNatCommonLgNames.STRING, new TeamBeanGetPlayerFigtherAgainstFoe()));
        methods_.add( new SpecNatMethod(GET_FOE_FIGTHER_AGAINST_FOE,BeanNatCommonLgNames.STRING, new TeamBeanGetFoeFigtherAgainstFoe()));
        _std.getStds().addEntry(TYPE_TEAM_BEAN, type_);
    }

    public static NatArrayStruct getMvTars(AbsMap<MoveTarget, MoveTarget> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MoveTarget, MoveTarget> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new MoveTargetStruct(e.getKey()),new MoveTargetStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getMvTar(AbsMap<Integer, MoveTarget> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Integer, MoveTarget> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new MoveTargetStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStaInf(CustList<StatisticInfo> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (StatisticInfo s:_ls) {
            arr_.set(j_,new StatisticInfoStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getKeyHyp(CustList<KeyHypothesis> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (KeyHypothesis s:_ls) {
            arr_.set(j_,new KeyHypothesisStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getBigNatMapLs(CustList<ImgMovesListTeamPositionsList> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (ImgMovesListTeamPositionsList e:_map) {
            NatArrayStruct k_ = new NatArrayStruct(e.getKeyPks().size());
            CustList<FighterImgPkNameMv> ls_ = e.getKeyPks();
            int s_ = ls_.size();
            for (int i = 0; i < s_; i++) {
                k_.set(i, new FighterNamePkNameMvStruct(ls_.get(i)));
            }
            PairStruct p_ = new PairStruct(k_, getTeamPos(e.getTeamPositions()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getTeamPos(CustList<TeamPosition> _map) {
        int len_ = _map.size();
        return PokemonStandards.arrId(len_);
    }

    public static NatArrayStruct getBigByAn(AbsMap<String, IntTreeMap<Anticipation>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, IntTreeMap<Anticipation>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),getByAn(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getBigBySt(AbsMap<String, IntTreeMap<StacksOfUses>> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, IntTreeMap<StacksOfUses>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),getBySt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getByAn(AbsMap<Integer, Anticipation> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Integer, Anticipation> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new AnticipationStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getBySt(AbsMap<Integer, StacksOfUses> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<Integer, StacksOfUses> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaNbSt(e.getKey()),new StacksOfUsesStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcMvTp(AbsMap<MoveTeamPosition, AffectedMove> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, AffectedMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new MoveTeamPositionStruct(e.getKey()),new AffectedMoveStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcMvAm(AbsMap<MoveTeamPosition, ActivityOfMove> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new MoveTeamPositionStruct(e.getKey()),new ActivityOfMoveStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcMvTpNb(AbsMap<MoveTeamPosition, Long> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, Long> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new MoveTeamPositionStruct(e.getKey()),new NaNbSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getWcMvTpBool(AbsMap<MoveTeamPosition, BoolVal> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, BoolVal> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new MoveTeamPositionStruct(e.getKey()), NaBoSt.of(e.getValue() == BoolVal.TRUE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getMvTpStr(AbsMap<MoveTeamPosition, String> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new MoveTeamPositionStruct(e.getKey()),new NaStSt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getCpStr(AbsMap<String, CopiedMove> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, CopiedMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),new CopiedMoveStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getStrAct(AbsMap<StringList, ActivityOfMove> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<StringList, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(BeanNatCommonLgNames.getStringArray(e.getKey()),new ActivityOfMoveStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getSuffCatStr(AbsMap<String, SufferedDamageCategory> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, SufferedDamageCategory> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),new SufferedDamageCategoryStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getActMove(AbsMap<String, ActivityOfMove> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),new ActivityOfMoveStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getMultPowStr(AbsMap<String, MultPowerMoves> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int j_ = 0;
        for (EntryCust<String, MultPowerMoves> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(new NaStSt(e.getKey()),new MultPowerMovesStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
}
