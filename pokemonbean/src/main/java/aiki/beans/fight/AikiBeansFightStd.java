package aiki.beans.fight;
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
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansFightStd {
    public static final String TYPE_COMMON_FIGHT_BEAN = "aiki.beans.fight.CommonFightBean";
    public static final String TYPE_FIGHT_BEAN = "aiki.beans.fight.FightBean";
    public static final String TYPE_FIGHT_CALCULATION_BEAN = "aiki.beans.fight.FightCalculationBean";
    public static final String TYPE_FIGHTER_BEAN = "aiki.beans.fight.FighterBean";
    public static final String TYPE_TEAM_BEAN = "aiki.beans.fight.TeamBean";

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

    public static void build(BeanLgNames _std) {
        buildCommonFightBean(_std);
        buildFightBean(_std);
        buildFightCalculationBean(_std);
        buildFighterBean(_std);
        buildTeamBean(_std);
    }
    private static void buildCommonFightBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMMON_FIGHT_BEAN, fields_, constructors_, methods_, _std.getBean(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMMON_FIGHT_BEAN, type_);
    }
    private static void buildFightBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_FIGHT_BEAN, fields_, constructors_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN, MethodModifier.NORMAL);
        fields_.put(MULT,new StandardField(MULT,_std.getAliasPrimByte(),false,false,type_));
        fields_.put(NB_ROUNDS,new StandardField(NB_ROUNDS,PokemonStandards.TYPE_LG_INT,false,false,type_));
        fields_.put(NB_FLEE_ATTEMPT,new StandardField(NB_FLEE_ATTEMPT,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(WINNING_MONEY,new StandardField(WINNING_MONEY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(ENABLED_MOVES,new StandardField(ENABLED_MOVES,_std.getCustMap(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_PLAYER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_STILL_ENABLED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_FIGHT_BEAN, type_);
    }
    private static void buildFightCalculationBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_FIGHT_CALCULATION_BEAN, fields_, constructors_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN, MethodModifier.NORMAL);
        fields_.put(SORTED_FIGHTERS,new StandardField(SORTED_FIGHTERS,_std.getCustList(),false,false,type_));
        fields_.put(SORTED_FIGHTERS_WILD_FIGHT,new StandardField(SORTED_FIGHTERS_WILD_FIGHT,_std.getCustMap(),false,false,type_));
        fields_.put(DAMAGE,new StandardField(DAMAGE,_std.getCustList(),false,false,type_));
        fields_.put(ALLY_CHOICE,new StandardField(ALLY_CHOICE,_std.getCustMap(),false,false,type_));
        fields_.put(FOE_CHOICES,new StandardField(FOE_CHOICES,_std.getCustMap(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_FIGHTER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_FIGHTER_WILD_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_TARGET_CHOICE_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_BACK_TARGET_CHOICE_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TARGET_NAME_ALLY_CHOICE_CONDITION,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_TARGET_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_BACK_TARGET_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TARGET_NAME_ALLY_CHOICE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_FOE_FIGHTER_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_CHOSEN_TARGET,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_TARGET_CH_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TARGET_NAME_FOE_CHOICE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_FIGHT_CALCULATION_BEAN, type_);
    }
    private static void buildFighterBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_FIGHTER_BEAN, fields_, constructors_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN, MethodModifier.NORMAL);
        fields_.put(NAME,new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.put(CHANGED,new StandardField(CHANGED,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(CURRENT_NAME,new StandardField(CURRENT_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(CURRENT_GENDER,new StandardField(CURRENT_GENDER,_std.getAliasString(),false,false,type_));
        fields_.put(USED_BALL_CATCHING,new StandardField(USED_BALL_CATCHING,_std.getAliasString(),false,false,type_));
        fields_.put(BELONGING_TO_PLAYER,new StandardField(BELONGING_TO_PLAYER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(GROUND_PLACE_SUBST,new StandardField(GROUND_PLACE_SUBST,_std.getAliasPrimByte(),false,false,type_));
        fields_.put(GROUND_PLACE,new StandardField(GROUND_PLACE,_std.getAliasPrimByte(),false,false,type_));
        fields_.put(NICKNAME,new StandardField(NICKNAME,_std.getAliasString(),false,false,type_));
        fields_.put(HAPPINESS,new StandardField(HAPPINESS,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(LEVEL,new StandardField(LEVEL,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(WON_EXP_SINCE_LAST_LEVEL,new StandardField(WON_EXP_SINCE_LAST_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(NECESSARY_POINTS_NEXT_LEVEL,new StandardField(NECESSARY_POINTS_NEXT_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(GENDER,new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        fields_.put(WEIGHT,new StandardField(WEIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(WEIGHT_STR,new StandardField(WEIGHT_STR,_std.getAliasString(),false,false,type_));
        fields_.put(HEIGHT,new StandardField(HEIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HEIGHT_STR,new StandardField(HEIGHT_STR,_std.getAliasString(),false,false,type_));
        fields_.put(REMAINING_HP,new StandardField(REMAINING_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(REMAINING_HP_STR,new StandardField(REMAINING_HP_STR,_std.getAliasString(),false,false,type_));
        fields_.put(REMAINING_HP_STR_PER_CENT,new StandardField(REMAINING_HP_STR_PER_CENT,_std.getAliasString(),false,false,type_));
        fields_.put(CLONE,new StandardField(CLONE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(CLONE_STR,new StandardField(CLONE_STR,_std.getAliasString(),false,false,type_));
        fields_.put(ABILITY,new StandardField(ABILITY,_std.getAliasString(),false,false,type_));
        fields_.put(CURRENT_ABILITY,new StandardField(CURRENT_ABILITY,_std.getAliasString(),false,false,type_));
        fields_.put(ACTED,new StandardField(ACTED,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(LAST_USED_ITEM,new StandardField(LAST_USED_ITEM,_std.getAliasString(),false,false,type_));
        fields_.put(ITEM,new StandardField(ITEM,_std.getAliasString(),false,false,type_));
        fields_.put(EXP_ITEM,new StandardField(EXP_ITEM,_std.getAliasString(),false,false,type_));
        fields_.put(USING_ITEM,new StandardField(USING_ITEM,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(TYPES,new StandardField(TYPES,_std.getCustList(),false,false,type_));
        fields_.put(MOVES,new StandardField(MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(CURRENT_MOVES,new StandardField(CURRENT_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(NB_ROUNDS,new StandardField(NB_ROUNDS,PokemonStandards.TYPE_LG_INT,false,false,type_));
        fields_.put(COPIED_MOVES,new StandardField(COPIED_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(STATISTICS,new StandardField(STATISTICS,_std.getCustList(),false,false,type_));
        fields_.put(DAMAGE_RATE_BY_TYPE,new StandardField(DAMAGE_RATE_BY_TYPE,_std.getCustMap(),false,false,type_));
        fields_.put(PROTECTED_AGAINST_MOVE_TYPES,new StandardField(PROTECTED_AGAINST_MOVE_TYPES,_std.getCustList(),false,false,type_));
        fields_.put(DAMAGE_SUFFERED_CATEG,new StandardField(DAMAGE_SUFFERED_CATEG,_std.getCustMap(),false,false,type_));
        fields_.put(ENABLED_MOVES,new StandardField(ENABLED_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(ENABLED_MOVES_FOR_ALLY,new StandardField(ENABLED_MOVES_FOR_ALLY,_std.getCustMap(),false,false,type_));
        fields_.put(NB_USES_MOVES,new StandardField(NB_USES_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(NB_REPEATING_SUCCESSFUL_MOVES,new StandardField(NB_REPEATING_SUCCESSFUL_MOVES,PokemonStandards.TYPE_LG_INT,false,false,type_));
        fields_.put(SUCCESSFUL_MOVE,new StandardField(SUCCESSFUL_MOVE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(LAST_SUCCESSFUL_MOVE,new StandardField(LAST_SUCCESSFUL_MOVE,_std.getAliasString(),false,false,type_));
        fields_.put(LAST_USED_MOVE,new StandardField(LAST_USED_MOVE,_std.getAliasString(),false,false,type_));
        fields_.put(USED_MOVE_LAST_ROUND,new StandardField(USED_MOVE_LAST_ROUND,_std.getAliasString(),false,false,type_));
        fields_.put(NB_PREPA_ROUND,new StandardField(NB_PREPA_ROUND,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(ALREADY_INVOKED_MOVES_ROUND,new StandardField(ALREADY_INVOKED_MOVES_ROUND,_std.getCustList(),false,false,type_));
        fields_.put(LAST_SUFFERED_MOVE,new StandardField(LAST_SUFFERED_MOVE,_std.getAliasString(),false,false,type_));
        fields_.put(LAST_SUFFERED_MOVE_TYPES,new StandardField(LAST_SUFFERED_MOVE_TYPES,_std.getCustList(),false,false,type_));
        fields_.put(DISAPPEARED,new StandardField(DISAPPEARED,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(NEEDING_TO_RECHARGE,new StandardField(NEEDING_TO_RECHARGE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(STATUS,new StandardField(STATUS,_std.getCustMap(),false,false,type_));
        fields_.put(STATUS_RELAT,new StandardField(STATUS_RELAT,_std.getCustMap(),false,false,type_));
        fields_.put(PRIVATE_MOVES,new StandardField(PRIVATE_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(TRAPPING_MOVES,new StandardField(TRAPPING_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(TRACKING_MOVES,new StandardField(TRACKING_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(INCR_USER_ACCURACY,new StandardField(INCR_USER_ACCURACY,_std.getCustMap(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(IS_BACK_SUBST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_BACK,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_STATUS_RELAT_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_STATUS_RELAT_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_ENABLED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_PRIVATE_MOVES_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_INCR_PRIVATE_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_TRAPPING_MOVES_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_INCR_TRAPPING_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_TRACKING_MOVES_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_INCR_TRACKING_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_INCR_USER_ACCURACY_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_INCR_USER_ACCURACY_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_FIGHTER_BEAN, type_);
    }
    private static void buildTeamBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_TEAM_BEAN, fields_, constructors_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN, MethodModifier.NORMAL);
        fields_.put(FOE_TEAM,new StandardField(FOE_TEAM,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ENABLED_MOVES,new StandardField(ENABLED_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(ENABLED_MOVES_BY_GROUP,new StandardField(ENABLED_MOVES_BY_GROUP,_std.getCustMap(),false,false,type_));
        fields_.put(ENABLED_MOVES_WHILE_SENDING_FOE_USES,new StandardField(ENABLED_MOVES_WHILE_SENDING_FOE_USES,_std.getCustMap(),false,false,type_));
        fields_.put(NB_USES_MOVES,new StandardField(NB_USES_MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(HEAL_AFTER,new StandardField(HEAL_AFTER,_std.getCustMap(),false,false,type_));
        fields_.put(MOVES_ANTICIPATION,new StandardField(MOVES_ANTICIPATION,_std.getCustMap(),false,false,type_));
        fields_.put(PLAYER_FIGHTERS_AGAINST_FOE,new StandardField(PLAYER_FIGHTERS_AGAINST_FOE,_std.getCustMap(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_MEMBERS,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_FIGHTER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(IS_FOE_MOVES_ANTICIPATION_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(IS_BACK_MOVES_ANTICIPATION_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_PLAYER_FIGTHER_AGAINST_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_FOE_FIGTHER_AGAINST_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_TEAM_BEAN, type_);
    }
    public static ResultErrorStd getResultFightBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FightBean instance_ = (FightBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MULT)) {
            res_.setResult(new ByteStruct(instance_.getMult()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_ROUNDS)) {
            res_.setResult(new StdStruct(instance_.getNbRounds(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_FLEE_ATTEMPT)) {
            res_.setResult(new ShortStruct(instance_.getNbFleeAttempt()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WINNING_MONEY)) {
            res_.setResult(new StdStruct(instance_.getWinningMoney(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_MOVES)) {
            res_.setResult(new StdStruct(instance_.getEnabledMoves(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultFightCalculationBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FightCalculationBean instance_ = (FightCalculationBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,SORTED_FIGHTERS)) {
            res_.setResult(new StdStruct(instance_.getSortedFighters(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SORTED_FIGHTERS_WILD_FIGHT)) {
            res_.setResult(new StdStruct(instance_.getSortedFightersWildFight(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE)) {
            res_.setResult(new StdStruct(instance_.getDamage(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLY_CHOICE)) {
            res_.setResult(new StdStruct(instance_.getAllyChoice(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FOE_CHOICES)) {
            res_.setResult(new StdStruct(instance_.getFoeChoices(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultFighterBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FighterBean instance_ = (FighterBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHANGED)) {
            res_.setResult(new BooleanStruct(instance_.getChanged()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CURRENT_NAME)) {
            res_.setResult(new StringStruct(instance_.getCurrentName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CURRENT_GENDER)) {
            res_.setResult(new StringStruct(instance_.getCurrentGender()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,USED_BALL_CATCHING)) {
            res_.setResult(new StringStruct(instance_.getUsedBallCatching()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BELONGING_TO_PLAYER)) {
            res_.setResult(new BooleanStruct(instance_.getBelongingToPlayer()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GROUND_PLACE_SUBST)) {
            res_.setResult(new ByteStruct(instance_.getGroundPlaceSubst()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GROUND_PLACE)) {
            res_.setResult(new ByteStruct(instance_.getGroundPlace()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NICKNAME)) {
            res_.setResult(new StringStruct(instance_.getNickname()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS)) {
            res_.setResult(new ShortStruct(instance_.getHappiness()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WON_EXP_SINCE_LAST_LEVEL)) {
            res_.setResult(new StdStruct(instance_.getWonExpSinceLastLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NECESSARY_POINTS_NEXT_LEVEL)) {
            res_.setResult(new StdStruct(instance_.getNecessaryPointsNextLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WEIGHT)) {
            res_.setResult(new StdStruct(instance_.getWeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WEIGHT_STR)) {
            res_.setResult(new StringStruct(instance_.getWeightStr()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEIGHT)) {
            res_.setResult(new StdStruct(instance_.getHeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEIGHT_STR)) {
            res_.setResult(new StringStruct(instance_.getHeightStr()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_HP)) {
            res_.setResult(new StdStruct(instance_.getRemainingHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_HP_STR)) {
            res_.setResult(new StringStruct(instance_.getRemainingHpStr()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_HP_STR_PER_CENT)) {
            res_.setResult(new StringStruct(instance_.getRemainingHpStrPerCent()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CLONE)) {
            res_.setResult(new StdStruct(instance_.getClone(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CLONE_STR)) {
            res_.setResult(new StringStruct(instance_.getCloneStr()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CURRENT_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getCurrentAbility()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ACTED)) {
            res_.setResult(new BooleanStruct(instance_.getActed()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LAST_USED_ITEM)) {
            res_.setResult(new StringStruct(instance_.getLastUsedItem()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EXP_ITEM)) {
            res_.setResult(new StringStruct(instance_.getExpItem()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,USING_ITEM)) {
            res_.setResult(new BooleanStruct(instance_.getUsingItem()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPES)) {
            res_.setResult(new StdStruct(instance_.getTypes(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES)) {
            res_.setResult(new StdStruct(instance_.getMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CURRENT_MOVES)) {
            res_.setResult(new StdStruct(instance_.getCurrentMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_ROUNDS)) {
            res_.setResult(new StdStruct(instance_.getNbRounds(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COPIED_MOVES)) {
            res_.setResult(new StdStruct(instance_.getCopiedMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATISTICS)) {
            res_.setResult(new StdStruct(instance_.getStatistics(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_BY_TYPE)) {
            res_.setResult(new StdStruct(instance_.getDamageRateByType(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECTED_AGAINST_MOVE_TYPES)) {
            res_.setResult(new StdStruct(instance_.getProtectedAgainstMoveTypes(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_SUFFERED_CATEG)) {
            res_.setResult(new StdStruct(instance_.getDamageSufferedCateg(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_MOVES)) {
            res_.setResult(new StdStruct(instance_.getEnabledMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_MOVES_FOR_ALLY)) {
            res_.setResult(new StdStruct(instance_.getEnabledMovesForAlly(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_USES_MOVES)) {
            res_.setResult(new StdStruct(instance_.getNbUsesMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_REPEATING_SUCCESSFUL_MOVES)) {
            res_.setResult(new StdStruct(instance_.getNbRepeatingSuccessfulMoves(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SUCCESSFUL_MOVE)) {
            res_.setResult(new BooleanStruct(instance_.getSuccessfulMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LAST_SUCCESSFUL_MOVE)) {
            res_.setResult(new StringStruct(instance_.getLastSuccessfulMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LAST_USED_MOVE)) {
            res_.setResult(new StringStruct(instance_.getLastUsedMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,USED_MOVE_LAST_ROUND)) {
            res_.setResult(new StringStruct(instance_.getUsedMoveLastRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_PREPA_ROUND)) {
            res_.setResult(new ShortStruct(instance_.getNbPrepaRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALREADY_INVOKED_MOVES_ROUND)) {
            res_.setResult(new StdStruct(instance_.getAlreadyInvokedMovesRound(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LAST_SUFFERED_MOVE)) {
            res_.setResult(new StringStruct(instance_.getLastSufferedMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LAST_SUFFERED_MOVE_TYPES)) {
            res_.setResult(new StdStruct(instance_.getLastSufferedMoveTypes(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISAPPEARED)) {
            res_.setResult(new BooleanStruct(instance_.getDisappeared()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NEEDING_TO_RECHARGE)) {
            res_.setResult(new BooleanStruct(instance_.getNeedingToRecharge()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS)) {
            res_.setResult(new StdStruct(instance_.getStatus(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS_RELAT)) {
            res_.setResult(new StdStruct(instance_.getStatusRelat(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PRIVATE_MOVES)) {
            res_.setResult(new StdStruct(instance_.getPrivateMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TRAPPING_MOVES)) {
            res_.setResult(new StdStruct(instance_.getTrappingMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TRACKING_MOVES)) {
            res_.setResult(new StdStruct(instance_.getTrackingMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INCR_USER_ACCURACY)) {
            res_.setResult(new StdStruct(instance_.getIncrUserAccuracy(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultTeamBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        TeamBean instance_ = (TeamBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,FOE_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.getFoeTeam()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_MOVES)) {
            res_.setResult(new StdStruct(instance_.getEnabledMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_MOVES_BY_GROUP)) {
            res_.setResult(new StdStruct(instance_.getEnabledMovesByGroup(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_MOVES_WHILE_SENDING_FOE_USES)) {
            res_.setResult(new StdStruct(instance_.getEnabledMovesWhileSendingFoeUses(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_USES_MOVES)) {
            res_.setResult(new StdStruct(instance_.getNbUsesMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_AFTER)) {
            res_.setResult(new StdStruct(instance_.getHealAfter(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_ANTICIPATION)) {
            res_.setResult(new StdStruct(instance_.getMovesAnticipation(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLAYER_FIGHTERS_AGAINST_FOE)) {
            res_.setResult(new StdStruct(instance_.getPlayerFightersAgainstFoe(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFightBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        FightBean instance_ = (FightBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_PLAYER)) {
            res_.setResult(new StringStruct(instance_.clickPlayer()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_FOE)) {
            res_.setResult(new StringStruct(instance_.clickFoe()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_STILL_ENABLED)) {
            res_.setResult(new BooleanStruct(instance_.isStillEnabled((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFightCalculationBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        FightCalculationBean instance_ = (FightCalculationBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_FIGHTER)) {
            res_.setResult(new StringStruct(instance_.getFighter((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_FIGHTER_WILD_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getFighterWildFight((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_TARGET_CHOICE_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeTargetChoiceTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_BACK_TARGET_CHOICE_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isBackTargetChoiceTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TARGET_NAME_ALLY_CHOICE_CONDITION)) {
            res_.setResult(new StringStruct(instance_.getTargetNameAllyChoiceCondition((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_TARGET_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeTargetTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_BACK_TARGET_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isBackTargetTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TARGET_NAME_ALLY_CHOICE)) {
            res_.setResult(new StringStruct(instance_.getTargetNameAllyChoice((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_FOE_FIGHTER_NAME)) {
            res_.setResult(new StringStruct(instance_.getFoeFighterName((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_CHOSEN_TARGET)) {
            res_.setResult(new BooleanStruct(instance_.isChosenTarget((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_TARGET_CH_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeTargetChTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TARGET_NAME_FOE_CHOICE)) {
            res_.setResult(new StringStruct(instance_.getTargetNameFoeChoice((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFighterBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        FighterBean instance_ = (FighterBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_BACK_SUBST)) {
            res_.setResult(new BooleanStruct(instance_.isBackSubst()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_BACK)) {
            res_.setResult(new BooleanStruct(instance_.isBack()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_STATUS_RELAT_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeStatusRelatTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_STATUS_RELAT_TEAM)) {
            res_.setResult(new StringStruct(instance_.getStatusRelatTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ENABLED)) {
            res_.setResult(new BooleanStruct(instance_.isEnabled((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_PRIVATE_MOVES_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoePrivateMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_INCR_PRIVATE_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrPrivateMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_TRAPPING_MOVES_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeTrappingMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_INCR_TRAPPING_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrTrappingMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_TRACKING_MOVES_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeTrackingMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_INCR_TRACKING_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrTrackingMovesTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_INCR_USER_ACCURACY_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeIncrUserAccuracyTeam((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_INCR_USER_ACCURACY_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrUserAccuracyTeam((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTeamBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        TeamBean instance_ = (TeamBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_MEMBERS)) {
            res_.setResult(StdStruct.newListByte(instance_.getMembers(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_FIGHTER)) {
            res_.setResult(new StringStruct(instance_.clickFighter((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON_LINK)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonLink((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEY)) {
            res_.setResult(new StringStruct(instance_.getKey((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_MOVES_ANTICIPATION_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isFoeMovesAnticipationTeam((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_BACK_MOVES_ANTICIPATION_TEAM)) {
            res_.setResult(new BooleanStruct(instance_.isBackMovesAnticipationTeam((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_PLAYER_FIGTHER_AGAINST_FOE)) {
            res_.setResult(new StringStruct(instance_.getPlayerFigtherAgainstFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_FOE_FIGTHER_AGAINST_FOE)) {
            res_.setResult(new StringStruct(instance_.getFoeFigtherAgainstFoe((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        return res_;
    }
}
