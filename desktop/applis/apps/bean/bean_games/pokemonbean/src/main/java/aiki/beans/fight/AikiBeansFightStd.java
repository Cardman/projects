package aiki.beans.fight;

import aiki.beans.LgIntStruct;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.RateStruct;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

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

    public static void build(PokemonStandards _std) {
        buildCommonFightBean(_std);
        buildFightBean(_std);
        buildFightCalculationBean(_std);
        buildFighterBean(_std);
        buildTeamBean(_std);
    }
    private static void buildCommonFightBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMMON_FIGHT_BEAN, fields_, methods_, BeanNatLgNames.TYPE_BEAN);
        _std.getStds().addEntry(TYPE_COMMON_FIGHT_BEAN, type_);
    }
    private static void buildFightBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_FIGHT_BEAN, fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(MULT,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(NB_ROUNDS,PokemonStandards.TYPE_LG_INT,false,false));
        fields_.add(new StandardField(NB_FLEE_ATTEMPT,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(WINNING_MONEY,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(ENABLED_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_PLAYER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_STILL_ENABLED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_FIGHT_BEAN, type_);
    }
    private static void buildFightCalculationBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_FIGHT_CALCULATION_BEAN, fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(SORTED_FIGHTERS, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(SORTED_FIGHTERS_WILD_FIGHT, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(DAMAGE, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(ALLY_CHOICE, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(FOE_CHOICES, BeanNatLgNames.TYPE_MAP,false,false));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_FIGHTER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_FIGHTER_WILD_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_TARGET_CHOICE_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_BACK_TARGET_CHOICE_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_TARGET_NAME_ALLY_CHOICE_CONDITION,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_TARGET_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_BACK_TARGET_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_TARGET_NAME_ALLY_CHOICE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_FOE_FIGHTER_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_CHOSEN_TARGET,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_TARGET_CH_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_TARGET_NAME_FOE_CHOICE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_FIGHT_CALCULATION_BEAN, type_);
    }
    private static void buildFighterBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_FIGHTER_BEAN, fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(NAME,_std.getAliasString(),false,false));
        fields_.add(new StandardField(CHANGED,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(CURRENT_NAME,_std.getAliasString(),false,false));
        fields_.add(new StandardField(CURRENT_GENDER,_std.getAliasString(),false,false));
        fields_.add(new StandardField(USED_BALL_CATCHING,_std.getAliasString(),false,false));
        fields_.add(new StandardField(BELONGING_TO_PLAYER,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(GROUND_PLACE_SUBST,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(GROUND_PLACE,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(NICKNAME,_std.getAliasString(),false,false));
        fields_.add(new StandardField(HAPPINESS,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(LEVEL,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(WON_EXP_SINCE_LAST_LEVEL,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(NECESSARY_POINTS_NEXT_LEVEL,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(GENDER,_std.getAliasString(),false,false));
        fields_.add(new StandardField(WEIGHT,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(WEIGHT_STR,_std.getAliasString(),false,false));
        fields_.add(new StandardField(HEIGHT,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(HEIGHT_STR,_std.getAliasString(),false,false));
        fields_.add(new StandardField(REMAINING_HP,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(REMAINING_HP_STR,_std.getAliasString(),false,false));
        fields_.add(new StandardField(REMAINING_HP_STR_PER_CENT,_std.getAliasString(),false,false));
        fields_.add(new StandardField(CLONE,PokemonStandards.TYPE_RATE,false,false));
        fields_.add(new StandardField(CLONE_STR,_std.getAliasString(),false,false));
        fields_.add(new StandardField(ABILITY,_std.getAliasString(),false,false));
        fields_.add(new StandardField(CURRENT_ABILITY,_std.getAliasString(),false,false));
        fields_.add(new StandardField(ACTED,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(LAST_USED_ITEM,_std.getAliasString(),false,false));
        fields_.add(new StandardField(ITEM,_std.getAliasString(),false,false));
        fields_.add(new StandardField(EXP_ITEM,_std.getAliasString(),false,false));
        fields_.add(new StandardField(USING_ITEM,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(CURRENT_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(NB_ROUNDS,PokemonStandards.TYPE_LG_INT,false,false));
        fields_.add(new StandardField(COPIED_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(STATISTICS, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(DAMAGE_RATE_BY_TYPE, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(PROTECTED_AGAINST_MOVE_TYPES, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(DAMAGE_SUFFERED_CATEG, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(ENABLED_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(ENABLED_MOVES_FOR_ALLY, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(NB_USES_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(NB_REPEATING_SUCCESSFUL_MOVES,PokemonStandards.TYPE_LG_INT,false,false));
        fields_.add(new StandardField(SUCCESSFUL_MOVE,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(LAST_SUCCESSFUL_MOVE,_std.getAliasString(),false,false));
        fields_.add(new StandardField(LAST_USED_MOVE,_std.getAliasString(),false,false));
        fields_.add(new StandardField(USED_MOVE_LAST_ROUND,_std.getAliasString(),false,false));
        fields_.add(new StandardField(NB_PREPA_ROUND,_std.getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(ALREADY_INVOKED_MOVES_ROUND, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(LAST_SUFFERED_MOVE,_std.getAliasString(),false,false));
        fields_.add(new StandardField(LAST_SUFFERED_MOVE_TYPES, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(DISAPPEARED,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(NEEDING_TO_RECHARGE,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(STATUS, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(STATUS_RELAT, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(PRIVATE_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(TRAPPING_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(TRACKING_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(INCR_USER_ACCURACY, BeanNatLgNames.TYPE_MAP,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_BACK_SUBST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_BACK,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_STATUS_RELAT_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_STATUS_RELAT_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_ENABLED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_PRIVATE_MOVES_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_INCR_PRIVATE_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_TRAPPING_MOVES_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_INCR_TRAPPING_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_TRACKING_MOVES_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_INCR_TRACKING_MOVES_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_INCR_USER_ACCURACY_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_INCR_USER_ACCURACY_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_FIGHTER_BEAN, type_);
    }
    private static void buildTeamBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TEAM_BEAN, fields_, methods_, AikiBeansFightStd.TYPE_COMMON_FIGHT_BEAN);
        fields_.add(new StandardField(FOE_TEAM,_std.getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(ENABLED_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(ENABLED_MOVES_BY_GROUP, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(ENABLED_MOVES_WHILE_SENDING_FOE_USES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(NB_USES_MOVES, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(HEAL_AFTER, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(MOVES_ANTICIPATION, BeanNatLgNames.TYPE_MAP,false,false));
        fields_.add(new StandardField(PLAYER_FIGHTERS_AGAINST_FOE, BeanNatLgNames.TYPE_MAP,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MEMBERS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(CLICK_FIGHTER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_TR_POKEMON_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_KEY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_FOE_MOVES_ANTICIPATION_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(IS_BACK_MOVES_ANTICIPATION_TEAM,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_PLAYER_FIGTHER_AGAINST_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_FOE_FIGTHER_AGAINST_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_TEAM_BEAN, type_);
    }
    public static ResultErrorStd getResultFightBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FightBean instance_ = (FightBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,MULT)) {
            res_.setResult(new IntStruct(instance_.getMult()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_ROUNDS)) {
            res_.setResult(new LgIntStruct(instance_.getNbRounds(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_FLEE_ATTEMPT)) {
            res_.setResult(new IntStruct(instance_.getNbFleeAttempt()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WINNING_MONEY)) {
            res_.setResult(new RateStruct(instance_.getWinningMoney(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_MOVES)) {
            res_.setResult(PokemonStandards.getActMove(_cont,instance_.getEnabledMoves()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultFightCalculationBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FightCalculationBean instance_ = (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,SORTED_FIGHTERS)) {
            res_.setResult(PokemonStandards.getTeamPos(_cont,instance_.getSortedFighters()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SORTED_FIGHTERS_WILD_FIGHT)) {
            res_.setResult(PokemonStandards.getBigNatMapLs(_cont,instance_.getSortedFightersWildFight()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE)) {
            res_.setResult(PokemonStandards.getKeyHyp(instance_.getDamage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ALLY_CHOICE)) {
            res_.setResult(PokemonStandards.getMvTars(_cont,instance_.getAllyChoice()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,FOE_CHOICES)) {
            res_.setResult(PokemonStandards.getMvTar(_cont,instance_.getFoeChoices()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultFighterBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        FighterBean instance_ = (FighterBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CHANGED)) {
            res_.setResult(BooleanStruct.of(instance_.getChanged()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CURRENT_NAME)) {
            res_.setResult(new StringStruct(instance_.getCurrentName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CURRENT_GENDER)) {
            res_.setResult(new StringStruct(instance_.getCurrentGender()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,USED_BALL_CATCHING)) {
            res_.setResult(new StringStruct(instance_.getUsedBallCatching()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BELONGING_TO_PLAYER)) {
            res_.setResult(BooleanStruct.of(instance_.getBelongingToPlayer()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,GROUND_PLACE_SUBST)) {
            res_.setResult(new IntStruct(instance_.getGroundPlaceSubst()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,GROUND_PLACE)) {
            res_.setResult(new IntStruct(instance_.getGroundPlace()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NICKNAME)) {
            res_.setResult(new StringStruct(instance_.getNickname()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HAPPINESS)) {
            res_.setResult(new IntStruct(instance_.getHappiness()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new IntStruct(instance_.getLevel()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WON_EXP_SINCE_LAST_LEVEL)) {
            res_.setResult(new RateStruct(instance_.getWonExpSinceLastLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NECESSARY_POINTS_NEXT_LEVEL)) {
            res_.setResult(new RateStruct(instance_.getNecessaryPointsNextLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WEIGHT)) {
            res_.setResult(new RateStruct(instance_.getWeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WEIGHT_STR)) {
            res_.setResult(new StringStruct(instance_.getWeightStr()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEIGHT)) {
            res_.setResult(new RateStruct(instance_.getHeight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEIGHT_STR)) {
            res_.setResult(new StringStruct(instance_.getHeightStr()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REMAINING_HP)) {
            res_.setResult(new RateStruct(instance_.getRemainingHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REMAINING_HP_STR)) {
            res_.setResult(new StringStruct(instance_.getRemainingHpStr()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REMAINING_HP_STR_PER_CENT)) {
            res_.setResult(new StringStruct(instance_.getRemainingHpStrPerCent()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CLONE)) {
            res_.setResult(new RateStruct(instance_.getClone(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CLONE_STR)) {
            res_.setResult(new StringStruct(instance_.getCloneStr()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CURRENT_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getCurrentAbility()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ACTED)) {
            res_.setResult(BooleanStruct.of(instance_.getActed()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAST_USED_ITEM)) {
            res_.setResult(new StringStruct(instance_.getLastUsedItem()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EXP_ITEM)) {
            res_.setResult(new StringStruct(instance_.getExpItem()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,USING_ITEM)) {
            res_.setResult(BooleanStruct.of(instance_.getUsingItem()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(std_.getStringArray(instance_.getTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES)) {
            res_.setResult(PokemonStandards.getUsesStr(_cont,instance_.getMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CURRENT_MOVES)) {
            res_.setResult(PokemonStandards.getUsesStr(_cont,instance_.getCurrentMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_ROUNDS)) {
            res_.setResult(new LgIntStruct(instance_.getNbRounds(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,COPIED_MOVES)) {
            res_.setResult(PokemonStandards.getCpStr(_cont,instance_.getCopiedMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATISTICS)) {
            res_.setResult(PokemonStandards.getStaInf(instance_.getStatistics()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATE_BY_TYPE)) {
            res_.setResult(PokemonStandards.getMultPowStr(_cont,instance_.getDamageRateByType()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PROTECTED_AGAINST_MOVE_TYPES)) {
            res_.setResult(std_.getStringArray(instance_.getProtectedAgainstMoveTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_SUFFERED_CATEG)) {
            res_.setResult(PokemonStandards.getSuffCatStr(_cont,instance_.getDamageSufferedCateg()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_MOVES)) {
            res_.setResult(PokemonStandards.getActMove(_cont,instance_.getEnabledMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_MOVES_FOR_ALLY)) {
            res_.setResult(PokemonStandards.getStrBool(_cont,instance_.getEnabledMovesForAlly()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_USES_MOVES)) {
            res_.setResult(PokemonStandards.getStrInteger(_cont,instance_.getNbUsesMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_REPEATING_SUCCESSFUL_MOVES)) {
            res_.setResult(new LgIntStruct(instance_.getNbRepeatingSuccessfulMoves(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SUCCESSFUL_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.getSuccessfulMove()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAST_SUCCESSFUL_MOVE)) {
            res_.setResult(new StringStruct(instance_.getLastSuccessfulMove()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAST_USED_MOVE)) {
            res_.setResult(new StringStruct(instance_.getLastUsedMove()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,USED_MOVE_LAST_ROUND)) {
            res_.setResult(new StringStruct(instance_.getUsedMoveLastRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_PREPA_ROUND)) {
            res_.setResult(new IntStruct(instance_.getNbPrepaRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ALREADY_INVOKED_MOVES_ROUND)) {
            res_.setResult(std_.getStringArray(instance_.getAlreadyInvokedMovesRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAST_SUFFERED_MOVE)) {
            res_.setResult(new StringStruct(instance_.getLastSufferedMove()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LAST_SUFFERED_MOVE_TYPES)) {
            res_.setResult(std_.getStringArray(instance_.getLastSufferedMoveTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DISAPPEARED)) {
            res_.setResult(BooleanStruct.of(instance_.getDisappeared()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NEEDING_TO_RECHARGE)) {
            res_.setResult(BooleanStruct.of(instance_.getNeedingToRecharge()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATUS)) {
            res_.setResult(PokemonStandards.getStrShort(_cont,instance_.getStatus()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATUS_RELAT)) {
            res_.setResult(PokemonStandards.getWcMvTpNb(_cont,instance_.getStatusRelat()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PRIVATE_MOVES)) {
            res_.setResult(PokemonStandards.getMvTpStr(_cont,instance_.getPrivateMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TRAPPING_MOVES)) {
            res_.setResult(PokemonStandards.getWcMvAm(_cont,instance_.getTrappingMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TRACKING_MOVES)) {
            res_.setResult(PokemonStandards.getWcMvTp(_cont,instance_.getTrackingMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,INCR_USER_ACCURACY)) {
            res_.setResult(PokemonStandards.getWcMvTpBool(_cont,instance_.getIncrUserAccuracy()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultTeamBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        TeamBean instance_ = (TeamBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,FOE_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.getFoeTeam()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_MOVES)) {
            res_.setResult(PokemonStandards.getActMove(_cont,instance_.getEnabledMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_MOVES_BY_GROUP)) {
            res_.setResult(PokemonStandards.getStrAct(_cont,instance_.getEnabledMovesByGroup()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_MOVES_WHILE_SENDING_FOE_USES)) {
            res_.setResult(PokemonStandards.getStrLgInt(_cont,instance_.getEnabledMovesWhileSendingFoeUses()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_USES_MOVES)) {
            res_.setResult(PokemonStandards.getStrInteger(_cont,instance_.getNbUsesMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HEAL_AFTER)) {
            res_.setResult(PokemonStandards.getBigBySt(_cont,instance_.getHealAfter()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES_ANTICIPATION)) {
            res_.setResult(PokemonStandards.getBigByAn(_cont,instance_.getMovesAnticipation()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PLAYER_FIGHTERS_AGAINST_FOE)) {
            res_.setResult(PokemonStandards.getByteBytes(_cont,instance_.getPlayerFightersAgainstFoe()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFightBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        FightBean instance_ = (FightBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_PLAYER)) {
            res_.setResult(new StringStruct(instance_.clickPlayer()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_FOE)) {
            res_.setResult(new StringStruct(instance_.clickFoe()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_STILL_ENABLED)) {
            res_.setResult(BooleanStruct.of(instance_.isStillEnabled(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFightCalculationBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        FightCalculationBean instance_ = (FightCalculationBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_FIGHTER)) {
            res_.setResult(new StringStruct(instance_.getFighter(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_FIGHTER_WILD_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getFighterWildFight(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_TARGET_CHOICE_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeTargetChoiceTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BACK_TARGET_CHOICE_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isBackTargetChoiceTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TARGET_NAME_ALLY_CHOICE_CONDITION)) {
            res_.setResult(new StringStruct(instance_.getTargetNameAllyChoiceCondition(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_TARGET_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeTargetTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BACK_TARGET_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isBackTargetTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TARGET_NAME_ALLY_CHOICE)) {
            res_.setResult(new StringStruct(instance_.getTargetNameAllyChoice(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_FOE_FIGHTER_NAME)) {
            res_.setResult(new StringStruct(instance_.getFoeFighterName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_CHOSEN_TARGET)) {
            res_.setResult(BooleanStruct.of(instance_.isChosenTarget(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_TARGET_CH_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeTargetChTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TARGET_NAME_FOE_CHOICE)) {
            res_.setResult(new StringStruct(instance_.getTargetNameFoeChoice(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodFighterBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        FighterBean instance_ = (FighterBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,IS_BACK_SUBST)) {
            res_.setResult(BooleanStruct.of(instance_.isBackSubst()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BACK)) {
            res_.setResult(BooleanStruct.of(instance_.isBack()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_STATUS_RELAT_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeStatusRelatTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_STATUS_RELAT_TEAM)) {
            res_.setResult(new StringStruct(instance_.getStatusRelatTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ENABLED)) {
            res_.setResult(BooleanStruct.of(instance_.isEnabled(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_PRIVATE_MOVES_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoePrivateMovesTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_INCR_PRIVATE_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrPrivateMovesTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_TRAPPING_MOVES_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeTrappingMovesTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_INCR_TRAPPING_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrTrappingMovesTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_TRACKING_MOVES_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeTrackingMovesTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_INCR_TRACKING_MOVES_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrTrackingMovesTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_INCR_USER_ACCURACY_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeIncrUserAccuracyTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_INCR_USER_ACCURACY_TEAM)) {
            res_.setResult(new StringStruct(instance_.getIncrUserAccuracyTeam(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTeamBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        TeamBean instance_ = (TeamBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MEMBERS)) {
            res_.setResult(((PokemonStandards)std_).getByteArray(instance_.getMembers()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_FIGHTER)) {
            res_.setResult(new StringStruct(instance_.clickFighter(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_LINK)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonLink(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEY)) {
            res_.setResult(new StringStruct(instance_.getKey(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FOE_MOVES_ANTICIPATION_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeMovesAnticipationTeam(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_BACK_MOVES_ANTICIPATION_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.isBackMovesAnticipationTeam(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PLAYER_FIGTHER_AGAINST_FOE)) {
            res_.setResult(new StringStruct(instance_.getPlayerFigtherAgainstFoe(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_FOE_FIGTHER_AGAINST_FOE)) {
            res_.setResult(new StringStruct(instance_.getFoeFigtherAgainstFoe(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        return res_;
    }
}
