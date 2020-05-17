package aiki.beans.simulation;
import aiki.beans.AikiBeansStd;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.simulation.enums.AikiBeansFacadeSimulationEnumsStd;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.enums.Gender;
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
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.structs.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.BeanNatLgNames;
import code.maths.Rate;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import aiki.facade.enums.SelectedBoolean;

public final class AikiBeansSimulationStd {
    public static final String TYPE_ADD_POKEMON_BEAN = "aiki.beans.simulation.AddPokemonBean";
    public static final String TYPE_EDIT_POKEMON_BEAN = "aiki.beans.simulation.EditPokemonBean";
    public static final String TYPE_EDIT_POKEMON_MOVES_BEAN = "aiki.beans.simulation.EditPokemonMovesBean";
    public static final String TYPE_EDIT_TRAINER_POKEMON_BEAN = "aiki.beans.simulation.EditTrainerPokemonBean";
    public static final String TYPE_SELECT_ABILITY_BEAN = "aiki.beans.simulation.SelectAbilityBean";
    public static final String TYPE_SELECT_ITEM_BEAN = "aiki.beans.simulation.SelectItemBean";
    public static final String TYPE_SELECT_POKEMON_BEAN = "aiki.beans.simulation.SelectPokemonBean";
    public static final String TYPE_SIMULATION_BEAN = "aiki.beans.simulation.SimulationBean";
    public static final String TYPE_SIMULATION_LEVEL_BEAN = "aiki.beans.simulation.SimulationLevelBean";

    private static final String CANCEL = "cancel";
    private static final String ADD = "add";
    private static final String SEARCH = "search";
    private static final String GET_MINI_IMAGE = "getMiniImage";
    private static final String CLICK_LINK = "clickLink";
    private static final String TRANSLATE_NAME = "translateName";
    private static final String TRANSLATE_ITEM = "translateItem";
    private static final String CHOOSE_ITEM = "chooseItem";
    private static final String ADD_MOVES = "addMoves";
    private static final String DELETE_MOVES = "deleteMoves";
    private static final String EDIT = "edit";
    private static final String GET_TRANSLATED_STATISTIC = "getTranslatedStatistic";
    private static final String CHOOSE_NAME = "chooseName";
    private static final String CHOOSE_ABILITY = "chooseAbility";
    private static final String VALIDATE_TRAINER_PK = "validateTrainerPk";
    private static final String GET_TRANSLATED_NAME = "getTranslatedName";
    private static final String GET_TRANSLATED_ABILITY = "getTranslatedAbility";
    private static final String GET_TRANSLATED_ITEM = "getTranslatedItem";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_TR_ABILITY = "getTrAbility";
    private static final String CANCEL_ITEM = "cancelItem";
    private static final String GET_REAL_STEP_NUMBER = "getRealStepNumber";
    private static final String QUIT = "quit";
    private static final String IS_DIFF_STATE = "isDiffState";
    private static final String VALIDATE_DIFF_CHOICE = "validateDiffChoice";
    private static final String IS_FOE_STATE = "isFoeState";
    private static final String ADD_PK_TRAINER = "addPkTrainer";
    private static final String SELECT_FOE_PK = "selectFoePk";
    private static final String GET_IMAGE_FOE = "getImageFoe";
    private static final String GET_NAME_FOE = "getNameFoe";
    private static final String GET_LEVEL_FOE = "getLevelFoe";
    private static final String GET_ABILITY_FOE = "getAbilityFoe";
    private static final String GET_GENDER_FOE = "getGenderFoe";
    private static final String GET_ITEM_FOE = "getItemFoe";
    private static final String GET_MOVES_FOE = "getMovesFoe";
    private static final String SELECT_ALLY_PK = "selectAllyPk";
    private static final String GET_IMAGE_ALLY = "getImageAlly";
    private static final String GET_NAME_ALLY = "getNameAlly";
    private static final String GET_LEVEL_ALLY = "getLevelAlly";
    private static final String GET_ABILITY_ALLY = "getAbilityAlly";
    private static final String GET_GENDER_ALLY = "getGenderAlly";
    private static final String GET_ITEM_ALLY = "getItemAlly";
    private static final String GET_MOVES_ALLY = "getMovesAlly";
    private static final String IS_MULTI_LAYER = "isMultiLayer";
    private static final String LAYERS = "layers";
    private static final String CLICK_LEVEL = "clickLevel";
    private static final String GET_TRAINER_NAME = "getTrainerName";
    private static final String CANCEL_DIFF_CHOICE = "cancelDiffChoice";
    private static final String VALIDATE_FOE_CHOICE_FREE = "validateFoeChoiceFree";
    private static final String VALIDATE_FOE_CHOICE = "validateFoeChoice";
    private static final String IS_TEAM_STATE = "isTeamState";
    private static final String SELECT_PK = "selectPk";
    private static final String GET_IMAGE = "getImage";
    private static final String GET_NAME = "getName";
    private static final String GET_LEVEL = "getLevel";
    private static final String GET_ABILITY = "getAbility";
    private static final String GET_GENDER = "getGender";
    private static final String GET_ITEM = "getItem";
    private static final String GET_MOVES = "getMoves";
    private static final String CANCEL_TEAM = "cancelTeam";
    private static final String VALIDATE_TEAM = "validateTeam";
    private static final String VALIDATE_FOE_CHOICE_SKIP_EVOLUTIONS = "validateFoeChoiceSkipEvolutions";
    private static final String IS_EVOLUTIONS_STATE = "isEvolutionsState";
    private static final String DISPLAY_EVOLUTIONS = "displayEvolutions";
    private static final String SELECTED_INDEX = "selectedIndex";
    private static final String VALIDATE_EVO = "validateEvo";
    private static final String CANCEL_EVO = "cancelEvo";
    private static final String CANCEL_EVOLUTIONS = "cancelEvolutions";
    private static final String VALIDATE_EVOLUTIONS = "validateEvolutions";
    private static final String IS_FRONT_STATE = "isFrontState";
    private static final String VALIDATE_FRONT_FIGHTER = "validateFrontFighter";
    private static final String CANCEL_FRONT_FIGHTERS = "cancelFrontFighters";
    private static final String VALIDATE_FRONT_FIGHTERS = "validateFrontFighters";
    private static final String IS_MOVES_STATE = "isMovesState";
    private static final String SELECTED_INDEX_FOR_MOVES = "selectedIndexForMoves";
    private static final String IS_AVAILABLE_MOVES = "isAvailableMoves";
    private static final String VALIDATE_MOVES = "validateMoves";
    private static final String IS_AVAILABLE_ABILITIES = "isAvailableAbilities";
    private static final String CANCEL_MOVES = "cancelMoves";
    private static final String CANCEL_MOVES_SETS = "cancelMovesSets";
    private static final String VALIDATE_MOVES_SETS = "validateMovesSets";
    private static final String IS_MOVES_FIGHT_STATE = "isMovesFightState";
    private static final String VALIDATE_MOVES_CHOICE = "validateMovesChoice";
    private static final String CANCEL_MOVES_EVOS = "cancelMovesEvos";
    private static final String SIMULATE_FIGHT = "simulateFight";
    private static final String IS_SIMULATION_STATE = "isSimulationState";
    private static final String IS_ISSUE = "isIssue";
    private static final String GET_KO_PLAYERS = "getKoPlayers";
    private static final String GET_NOT_KO_FRONT_FOES = "getNotKoFrontFoes";
    private static final String GET_KO_FOES = "getKoFoes";
    private static final String IS_RULES_ISSUE = "isRulesIssue";
    private static final String IS_RULES_MOVES_ISSUE = "isRulesMovesIssue";
    private static final String IS_RULES_LEARN_ISSUE = "isRulesLearnIssue";
    private static final String IS_RULES_SWITCH_ISSUE = "isRulesSwitchIssue";
    private static final String IS_SENDING_ISSUE = "isSendingIssue";
    private static final String IS_RANDOM_ISSUE = "isRandomIssue";
    private static final String IS_USING_ISSUE = "isUsingIssue";
    private static final String IS_HARD_SIMULATION_ISSUE = "isHardSimulationIssue";
    private static final String IS_ISSUE_AFTER_FIGHT = "isIssueAfterFight";
    private static final String GET_IMAGE_AFTER_FIGHT = "getImageAfterFight";
    private static final String GET_NAME_AFTER_FIGHT = "getNameAfterFight";
    private static final String GET_LEVEL_AFTER_FIGHT = "getLevelAfterFight";
    private static final String GET_ABILITY_AFTER_FIGHT = "getAbilityAfterFight";
    private static final String GET_GENDER_AFTER_FIGHT = "getGenderAfterFight";
    private static final String GET_ITEM_AFTER_FIGHT = "getItemAfterFight";
    private static final String GET_MOVES_AFTER_FIGHT = "getMovesAfterFight";
    private static final String GET_REMAINING_LIFE_RATE = "getRemainingLifeRate";
    private static final String NUMBER_NECESSARY_POINTS_FOR_GROWING_LEVEL = "numberNecessaryPointsForGrowingLevel";
    private static final String CHANGE_FIGHT = "changeFight";
    private static final String IS_FIGHT_AFTER = "isFightAfter";
    private static final String NEXT_FIGHT = "nextFight";
    private static final String DISPLAY_COMMENTS = "displayComments";
    private static final String HIDE_COMMENTS = "hideComments";
    private static final String IS_EVOLUTION_AFTER_FIGHT_STATE = "isEvolutionAfterFightState";
    private static final String SELECT_PK_AFTER_FIGHT = "selectPkAfterFight";
    private static final String VALIDATE_EVOLUTION_AFTER_FIGHT = "validateEvolutionAfterFight";
    private static final String CANCEL_EVOLUTIONS_AFTER_FIGHT = "cancelEvolutionsAfterFight";
    private static final String VALIDATE_MOVES_ABILITY_AFTER_FIGHT = "validateMovesAbilityAfterFight";
    private static final String CHANGE_FIGHT_WHILE_END = "changeFightWhileEnd";
    private static final String VALIDATE_MOVES_AFTER_FIGHT = "validateMovesAfterFight";
    private static final String GET_MAP_WIDTH = "getMapWidth";
    private static final String IS_FIRST_ROW = "isFirstRow";
    private static final String CLICK_TILE = "clickTile";
    private static final String NAME_PK = "namePk";
    private static final String ABILITIES = "abilities";
    private static final String ABILITY = "ability";
    private static final String GENDERS = "genders";
    private static final String GENDER = "gender";
    private static final String LEVEL = "level";
    private static final String TYPED_NAME = "typedName";
    private static final String TYPED_TYPE = "typedType";
    private static final String WHOLE_WORD = "wholeWord";
    private static final String BOOLEANS = "booleans";
    private static final String HAS_EVO = "hasEvo";
    private static final String IS_EVO = "isEvo";
    private static final String IS_LEG = "isLeg";
    private static final String POKEDEX = "pokedex";
    private static final String MOVES = "moves";
    private static final String EXPERIENCE = "experience";
    private static final String BALLS = "balls";
    private static final String BALL = "ball";
    private static final String HAPPINESS = "happiness";
    private static final String REMAINING_HP = "remainingHp";
    private static final String HEAL = "heal";
    private static final String EV = "ev";
    private static final String CATEGORIES = "categories";
    private static final String CATEGORY = "category";
    private static final String PLAYER = "player";
    private static final String AVAILABLE_MOVES_ONLY = "availableMovesOnly";
    private static final String ALLY_PK = "allyPk";
    private static final String TYPED_ABILITY = "typedAbility";
    private static final String SORTED_ABILITIES = "sortedAbilities";
    private static final String TYPED_PRICE = "typedPrice";
    private static final String TYPED_CLASS = "typedClass";
    private static final String ITEMS = "items";
    private static final String WIN_POINTS_FIGHT = "winPointsFight";
    private static final String DIFF_WINNING_EXP_PTS_FIGHT = "diffWinningExpPtsFight";
    private static final String ALLOW_CATCHING_KO = "allowCatchingKo";
    private static final String ALLOWED_SWITCH_PLACES_END_ROUND = "allowedSwitchPlacesEndRound";
    private static final String WIN_TRAINER_EXP = "winTrainerExp";
    private static final String RATE_WINNING_EXP_PTS_FIGHT = "rateWinningExpPtsFight";
    private static final String END_FIGHT_IF_ONE_TEAM_KO = "endFightIfOneTeamKo";
    private static final String IV_PLAYER = "ivPlayer";
    private static final String IV_FOE = "ivFoe";
    private static final String RATE_WIN_MONEY_BASE = "rateWinMoneyBase";
    private static final String RATE_LOOSE_MONEY_WIN = "rateLooseMoneyWin";
    private static final String RESTORED_MOVES_END_FIGHT = "restoredMovesEndFight";
    private static final String ENABLED_CLOSING = "enabledClosing";
    private static final String RANDOM_WILD_FIGHT = "randomWildFight";
    private static final String STILL_POSSIBLE_FLEE = "stillPossibleFlee";
    private static final String SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL = "skipLearningMovesWhileNotGrowingLevel";
    private static final String DAMAGE_RATES = "damageRates";
    private static final String DAMAGE_RATE_PLAYER = "damageRatePlayer";
    private static final String DAMAGE_RATE_PLAYER_TABLE = "damageRatePlayerTable";
    private static final String DAMAGE_RATE_LAW_FOE = "damageRateLawFoe";
    private static final String DAMAGE_RATE_FOE_TABLE = "damageRateFoeTable";
    private static final String FREE_TEAMS = "freeTeams";
    private static final String MULTIPLICITY = "multiplicity";
    private static final String ENVIRONMENTS = "environments";
    private static final String ENVIRONMENT = "environment";
    private static final String FOE_TEAM = "foeTeam";
    private static final String SELECTED_FOE_PK = "selectedFoePk";
    private static final String SELECTED_FOE_ACTION = "selectedFoeAction";
    private static final String ALLY_TEAM = "allyTeam";
    private static final String SELECTED_ALLY_PK = "selectedAllyPk";
    private static final String SELECTED_ALLY_ACTION = "selectedAllyAction";
    private static final String PLACES = "places";
    private static final String OK = "ok";
    private static final String TEAM = "team";
    private static final String SELECTED_PK = "selectedPk";
    private static final String SELECTED_ACTION = "selectedAction";
    private static final String AVAILABLE_EVOS = "availableEvos";
    private static final String CHOSEN_EVO = "chosenEvo";
    private static final String LEVEL_EVO = "levelEvo";
    private static final String ROUND = "round";
    private static final String SELECTED_ROUND = "selectedRound";
    private static final String PLACES_FIGHT = "placesFight";
    private static final String PLACE_FIGHT = "placeFight";
    private static final String DISPLAY_IF_ERROR = "displayIfError";
    private static final String CURRENT_ABILITY = "currentAbility";
    private static final String KEPT_MOVES = "keptMoves";
    private static final String MOVES_SET = "movesSet";
    private static final String SELECTED_MOVE = "selectedMove";
    private static final String ALLY_CHOICE = "allyChoice";
    private static final String TARGET_FIGHT = "targetFight";
    private static final String TARGET = "target";
    private static final String COMMENTS = "comments";
    private static final String TEAM_AFTER_FIGHT = "teamAfterFight";
    private static final String EVOLUTIONS_AFTER_FIGHT = "evolutionsAfterFight";
    private static final String EVOLUTION_AFTER_FIGHT = "evolutionAfterFight";
    private static final String ABILITIES_AFTER_FIGHT = "abilitiesAfterFight";
    private static final String ABILITY_AFTER_FIGHT = "abilityAfterFight";
    private static final String KEPT_MOVES_AFTER_FIGHT = "keptMovesAfterFight";
    private static final String POSSIBLE_MULTI_LAYER = "possibleMultiLayer";
    private static final String PLACE_NAME = "placeName";
    private static final String LEVEL_INDEX = "levelIndex";
    private static final String OUTSIDE = "outside";
    private static final String ROAD = "road";
    private static final String GYM = "gym";
    private static final String POKEMON_CENTER = "pokemonCenter";
    private static final String NO_FIGHT = "noFight";
    private static final String TILES = "tiles";

    public static void build(BeanLgNames _std) {
        buildAddPokemonBean(_std);
        buildEditPokemonBean(_std);
        buildEditPokemonMovesBean(_std);
        buildEditTrainerPokemonBean(_std);
        buildSelectAbilityBean(_std);
        buildSelectItemBean(_std);
        buildSelectPokemonBean(_std);
        buildSimulationBean(_std);
        buildSimulationLevelBean(_std);
    }
    private static void buildAddPokemonBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_ADD_POKEMON_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(NAME_PK,new StandardField(NAME_PK,_std.getAliasString(),false,false,type_));
        fields_.put(ABILITIES,new StandardField(ABILITIES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(ABILITY,new StandardField(ABILITY,_std.getAliasString(),false,false,type_));
        fields_.put(GENDERS,new StandardField(GENDERS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(GENDER,new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        fields_.put(LEVEL,new StandardField(LEVEL,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(TYPED_NAME,new StandardField(TYPED_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(TYPED_TYPE,new StandardField(TYPED_TYPE,_std.getAliasString(),false,false,type_));
        fields_.put(WHOLE_WORD,new StandardField(WHOLE_WORD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(BOOLEANS,new StandardField(BOOLEANS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(HAS_EVO,new StandardField(HAS_EVO,_std.getAliasString(),false,false,type_));
        fields_.put(IS_EVO,new StandardField(IS_EVO,_std.getAliasString(),false,false,type_));
        fields_.put(IS_LEG,new StandardField(IS_LEG,_std.getAliasString(),false,false,type_));
        fields_.put(POKEDEX,new StandardField(POKEDEX, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ADD,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MINI_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LINK,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_ADD_POKEMON_BEAN, type_);
    }
    private static void buildEditPokemonBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EDIT_POKEMON_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(LEVEL,new StandardField(LEVEL,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(NAME_PK,new StandardField(NAME_PK,_std.getAliasString(),false,false,type_));
        fields_.put(MOVES,new StandardField(MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(EXPERIENCE,new StandardField(EXPERIENCE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(BALLS,new StandardField(BALLS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(BALL,new StandardField(BALL,_std.getAliasString(),false,false,type_));
        fields_.put(HAPPINESS,new StandardField(HAPPINESS,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(REMAINING_HP,new StandardField(REMAINING_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HEAL,new StandardField(HEAL,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(EV,new StandardField(EV, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(TRANSLATE_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(TRANSLATE_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CHOOSE_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ADD_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(DELETE_MOVES,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(EDIT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TRANSLATED_STATISTIC,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EDIT_POKEMON_BEAN, type_);
    }
    private static void buildEditPokemonMovesBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EDIT_POKEMON_MOVES_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(TYPED_NAME,new StandardField(TYPED_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(CATEGORIES,new StandardField(CATEGORIES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(CATEGORY,new StandardField(CATEGORY,_std.getAliasString(),false,false,type_));
        fields_.put(TYPED_TYPE,new StandardField(TYPED_TYPE,_std.getAliasString(),false,false,type_));
        fields_.put(WHOLE_WORD,new StandardField(WHOLE_WORD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PLAYER,new StandardField(PLAYER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(AVAILABLE_MOVES_ONLY,new StandardField(AVAILABLE_MOVES_ONLY,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(MOVES,new StandardField(MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ADD_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EDIT_POKEMON_MOVES_BEAN, type_);
    }
    private static void buildEditTrainerPokemonBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EDIT_TRAINER_POKEMON_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(MOVES,new StandardField(MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(GENDERS,new StandardField(GENDERS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(GENDER,new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        fields_.put(LEVEL,new StandardField(LEVEL,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(ADD,new StandardField(ADD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ALLY_PK,new StandardField(ALLY_PK,_std.getAliasPrimBoolean(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CHOOSE_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CHOOSE_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CHOOSE_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ADD_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(DELETE_MOVES,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_TRAINER_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TRANSLATED_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TRANSLATED_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TRANSLATED_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EDIT_TRAINER_POKEMON_BEAN, type_);
    }
    private static void buildSelectAbilityBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SELECT_ABILITY_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(TYPED_ABILITY,new StandardField(TYPED_ABILITY,_std.getAliasString(),false,false,type_));
        fields_.put(SORTED_ABILITIES,new StandardField(SORTED_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_SELECT_ABILITY_BEAN, type_);
    }
    private static void buildSelectItemBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SELECT_ITEM_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(TYPED_NAME,new StandardField(TYPED_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(TYPED_PRICE,new StandardField(TYPED_PRICE,_std.getAliasString(),false,false,type_));
        fields_.put(TYPED_CLASS,new StandardField(TYPED_CLASS,_std.getAliasString(),false,false,type_));
        fields_.put(ITEMS,new StandardField(ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MINI_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_SELECT_ITEM_BEAN, type_);
    }
    private static void buildSelectPokemonBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SELECT_POKEMON_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(TYPED_NAME,new StandardField(TYPED_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(TYPED_TYPE,new StandardField(TYPED_TYPE,_std.getAliasString(),false,false,type_));
        fields_.put(WHOLE_WORD,new StandardField(WHOLE_WORD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(BOOLEANS,new StandardField(BOOLEANS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(HAS_EVO,new StandardField(HAS_EVO,_std.getAliasString(),false,false,type_));
        fields_.put(IS_EVO,new StandardField(IS_EVO,_std.getAliasString(),false,false,type_));
        fields_.put(IS_LEG,new StandardField(IS_LEG,_std.getAliasString(),false,false,type_));
        fields_.put(POKEDEX,new StandardField(POKEDEX, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MINI_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_SELECT_POKEMON_BEAN, type_);
    }
    private static void buildSimulationBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SIMULATION_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(WIN_POINTS_FIGHT,new StandardField(WIN_POINTS_FIGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(DIFF_WINNING_EXP_PTS_FIGHT,new StandardField(DIFF_WINNING_EXP_PTS_FIGHT,_std.getAliasString(),false,false,type_));
        fields_.put(ALLOW_CATCHING_KO,new StandardField(ALLOW_CATCHING_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ALLOWED_SWITCH_PLACES_END_ROUND,new StandardField(ALLOWED_SWITCH_PLACES_END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(WIN_TRAINER_EXP,new StandardField(WIN_TRAINER_EXP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(RATE_WINNING_EXP_PTS_FIGHT,new StandardField(RATE_WINNING_EXP_PTS_FIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(END_FIGHT_IF_ONE_TEAM_KO,new StandardField(END_FIGHT_IF_ONE_TEAM_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(IV_PLAYER,new StandardField(IV_PLAYER,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(IV_FOE,new StandardField(IV_FOE,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(RATE_WIN_MONEY_BASE,new StandardField(RATE_WIN_MONEY_BASE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(RATE_LOOSE_MONEY_WIN,new StandardField(RATE_LOOSE_MONEY_WIN,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(RESTORED_MOVES_END_FIGHT,new StandardField(RESTORED_MOVES_END_FIGHT,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ENABLED_CLOSING,new StandardField(ENABLED_CLOSING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(RANDOM_WILD_FIGHT,new StandardField(RANDOM_WILD_FIGHT,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(STILL_POSSIBLE_FLEE,new StandardField(STILL_POSSIBLE_FLEE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,new StandardField(SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(DAMAGE_RATES,new StandardField(DAMAGE_RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(DAMAGE_RATE_PLAYER,new StandardField(DAMAGE_RATE_PLAYER,_std.getAliasString(),false,false,type_));
        fields_.put(DAMAGE_RATE_PLAYER_TABLE,new StandardField(DAMAGE_RATE_PLAYER_TABLE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(DAMAGE_RATE_LAW_FOE,new StandardField(DAMAGE_RATE_LAW_FOE,_std.getAliasString(),false,false,type_));
        fields_.put(DAMAGE_RATE_FOE_TABLE,new StandardField(DAMAGE_RATE_FOE_TABLE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(FREE_TEAMS,new StandardField(FREE_TEAMS,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(MULTIPLICITY,new StandardField(MULTIPLICITY,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(ENVIRONMENTS,new StandardField(ENVIRONMENTS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(ENVIRONMENT,new StandardField(ENVIRONMENT,_std.getAliasString(),false,false,type_));
        fields_.put(FOE_TEAM,new StandardField(FOE_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SELECTED_FOE_PK,new StandardField(SELECTED_FOE_PK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(SELECTED_FOE_ACTION,new StandardField(SELECTED_FOE_ACTION,_std.getAliasString(),false,false,type_));
        fields_.put(ALLY_TEAM,new StandardField(ALLY_TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SELECTED_ALLY_PK,new StandardField(SELECTED_ALLY_PK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(SELECTED_ALLY_ACTION,new StandardField(SELECTED_ALLY_ACTION,_std.getAliasString(),false,false,type_));
        fields_.put(PLACES,new StandardField(PLACES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(OK,new StandardField(OK,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(TEAM,new StandardField(TEAM, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SELECTED_PK,new StandardField(SELECTED_PK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(SELECTED_ACTION,new StandardField(SELECTED_ACTION,_std.getAliasString(),false,false,type_));
        fields_.put(AVAILABLE_EVOS,new StandardField(AVAILABLE_EVOS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(CHOSEN_EVO,new StandardField(CHOSEN_EVO,_std.getAliasString(),false,false,type_));
        fields_.put(LEVEL_EVO,new StandardField(LEVEL_EVO,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(ROUND,new StandardField(ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(SELECTED_ROUND,new StandardField(SELECTED_ROUND,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(PLACES_FIGHT,new StandardField(PLACES_FIGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(PLACE_FIGHT,new StandardField(PLACE_FIGHT,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(DISPLAY_IF_ERROR,new StandardField(DISPLAY_IF_ERROR,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ABILITIES,new StandardField(ABILITIES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(CURRENT_ABILITY,new StandardField(CURRENT_ABILITY,_std.getAliasString(),false,false,type_));
        fields_.put(KEPT_MOVES,new StandardField(KEPT_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MOVES_SET,new StandardField(MOVES_SET, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SELECTED_MOVE,new StandardField(SELECTED_MOVE,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(ALLY_CHOICE,new StandardField(ALLY_CHOICE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(TARGET_FIGHT,new StandardField(TARGET_FIGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(TARGET,new StandardField(TARGET,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(COMMENTS,new StandardField(COMMENTS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(TEAM_AFTER_FIGHT,new StandardField(TEAM_AFTER_FIGHT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(EVOLUTIONS_AFTER_FIGHT,new StandardField(EVOLUTIONS_AFTER_FIGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(EVOLUTION_AFTER_FIGHT,new StandardField(EVOLUTION_AFTER_FIGHT,_std.getAliasString(),false,false,type_));
        fields_.put(ABILITIES_AFTER_FIGHT,new StandardField(ABILITIES_AFTER_FIGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(ABILITY_AFTER_FIGHT,new StandardField(ABILITY_AFTER_FIGHT,_std.getAliasString(),false,false,type_));
        fields_.put(KEPT_MOVES_AFTER_FIGHT,new StandardField(KEPT_MOVES_AFTER_FIGHT, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_REAL_STEP_NUMBER,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(QUIT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_DIFF_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_DIFF_CHOICE,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_FOE_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ADD_PK_TRAINER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SELECT_FOE_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_NAME_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_LEVEL_FOE,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ABILITY_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_GENDER_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM_FOE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVES_FOE,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SELECT_ALLY_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_ALLY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_NAME_ALLY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_LEVEL_ALLY,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ABILITY_ALLY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_GENDER_ALLY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM_ALLY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVES_ALLY,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_MULTI_LAYER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(LAYERS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LEVEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TRAINER_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_DIFF_CHOICE,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_FOE_CHOICE_FREE,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_FOE_CHOICE,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_TEAM_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ADD,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SELECT_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_LEVEL,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_GENDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_TEAM,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_TEAM,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_FOE_CHOICE_SKIP_EVOLUTIONS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_EVOLUTIONS_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(DISPLAY_EVOLUTIONS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SELECTED_INDEX,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_EVO,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_EVO,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_EVOLUTIONS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_EVOLUTIONS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_FRONT_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_FRONT_FIGHTER,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_FRONT_FIGHTERS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_FRONT_FIGHTERS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_MOVES_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SELECTED_INDEX_FOR_MOVES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_AVAILABLE_MOVES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_MOVES,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_AVAILABLE_ABILITIES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_MOVES,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_MOVES_SETS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_MOVES_SETS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_MOVES_FIGHT_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_MOVES_CHOICE,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_MOVES_EVOS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SIMULATE_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_SIMULATION_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KO_PLAYERS,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NOT_KO_FRONT_FOES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KO_FOES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_RULES_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_RULES_MOVES_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_RULES_LEARN_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_RULES_SWITCH_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_SENDING_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_RANDOM_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_USING_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_HARD_SIMULATION_ISSUE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_ISSUE_AFTER_FIGHT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_AFTER_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_NAME_AFTER_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_LEVEL_AFTER_FIGHT,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ABILITY_AFTER_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_GENDER_AFTER_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM_AFTER_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVES_AFTER_FIGHT,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_REMAINING_LIFE_RATE,params_,PokemonStandards.TYPE_LG_INT, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(NUMBER_NECESSARY_POINTS_FOR_GROWING_LEVEL,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CHANGE_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_FIGHT_AFTER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(NEXT_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(DISPLAY_COMMENTS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(HIDE_COMMENTS,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_EVOLUTION_AFTER_FIGHT_STATE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SELECT_PK_AFTER_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_EVOLUTION_AFTER_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL_EVOLUTIONS_AFTER_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_MOVES_ABILITY_AFTER_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CHANGE_FIGHT_WHILE_END,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(VALIDATE_MOVES_AFTER_FIGHT,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_SIMULATION_BEAN, type_);
    }
    private static void buildSimulationLevelBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SIMULATION_LEVEL_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(POSSIBLE_MULTI_LAYER,new StandardField(POSSIBLE_MULTI_LAYER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PLACE_NAME,new StandardField(PLACE_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(LEVEL_INDEX,new StandardField(LEVEL_INDEX,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(OUTSIDE,new StandardField(OUTSIDE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ROAD,new StandardField(ROAD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(GYM,new StandardField(GYM,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(POKEMON_CENTER,new StandardField(POKEMON_CENTER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(NO_FIGHT,new StandardField(NO_FIGHT,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(TILES,new StandardField(TILES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CANCEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MAP_WIDTH,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FIRST_ROW,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TILE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_SIMULATION_LEVEL_BEAN, type_);
    }
    public static ResultErrorStd getResultAddPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        AddPokemonBean instance_ = (AddPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,NAME_PK)) {
            res_.setResult(new StringStruct(instance_.getNamePk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getAbilities(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDERS)) {
            res_.setResult(new DefaultStruct(instance_.getGenders(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDER)) {
            res_.setResult(std_.wrapStd(instance_.getGender(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTypedName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTypedType()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WHOLE_WORD)) {
            res_.setResult(BooleanStruct.of(instance_.getWholeWord()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOLEANS)) {
            res_.setResult(new DefaultStruct(instance_.getBooleans(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAS_EVO)) {
            res_.setResult(std_.wrapStd(instance_.getHasEvo(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_EVO)) {
            res_.setResult(std_.wrapStd(instance_.getIsEvo(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_LEG)) {
            res_.setResult(std_.wrapStd(instance_.getIsLeg(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,POKEDEX)) {
            res_.setResult(new DefaultStruct(instance_.getPokedex(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEditPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EditPokemonBean instance_ = (EditPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NAME_PK)) {
            res_.setResult(new StringStruct(instance_.getNamePk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EXPERIENCE)) {
            res_.setResult(new DefaultStruct(instance_.getExperience(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BALLS)) {
            res_.setResult(new DefaultStruct(instance_.getBalls(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BALL)) {
            res_.setResult(new StringStruct(instance_.getBall()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS)) {
            res_.setResult(new ShortStruct(instance_.getHappiness()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_HP)) {
            res_.setResult(new DefaultStruct(instance_.getRemainingHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL)) {
            res_.setResult(BooleanStruct.of(instance_.getHeal()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EV)) {
            res_.setResult(new DefaultStruct(instance_.getEv(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEditPokemonMovesBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EditPokemonMovesBean instance_ = (EditPokemonMovesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTypedName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CATEGORIES)) {
            res_.setResult(new DefaultStruct(instance_.getCategories(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CATEGORY)) {
            res_.setResult(new StringStruct(instance_.getCategory()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTypedType()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WHOLE_WORD)) {
            res_.setResult(BooleanStruct.of(instance_.getWholeWord()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLAYER)) {
            res_.setResult(BooleanStruct.of(instance_.getPlayer()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,AVAILABLE_MOVES_ONLY)) {
            res_.setResult(BooleanStruct.of(instance_.getAvailableMovesOnly()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEditTrainerPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EditTrainerPokemonBean instance_ = (EditTrainerPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDERS)) {
            res_.setResult(new DefaultStruct(instance_.getGenders(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDER)) {
            res_.setResult(std_.wrapStd(instance_.getGender(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ADD)) {
            res_.setResult(BooleanStruct.of(instance_.getAdd()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLY_PK)) {
            res_.setResult(BooleanStruct.of(instance_.getAllyPk()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultSelectAbilityBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        SelectAbilityBean instance_ = (SelectAbilityBean) ((RealInstanceStruct)_instance).getInstance();
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
    public static ResultErrorStd getResultSelectItemBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        SelectItemBean instance_ = (SelectItemBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTypedName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_PRICE)) {
            res_.setResult(new StringStruct(instance_.getTypedPrice()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_CLASS)) {
            res_.setResult(new StringStruct(instance_.getTypedClass()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultSelectPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        SelectPokemonBean instance_ = (SelectPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTypedName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_TYPE)) {
            res_.setResult(new StringStruct(instance_.getTypedType()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WHOLE_WORD)) {
            res_.setResult(BooleanStruct.of(instance_.getWholeWord()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOLEANS)) {
            res_.setResult(new DefaultStruct(instance_.getBooleans(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAS_EVO)) {
            res_.setResult(std_.wrapStd(instance_.getHasEvo(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_EVO)) {
            res_.setResult(std_.wrapStd(instance_.getIsEvo(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_LEG)) {
            res_.setResult(std_.wrapStd(instance_.getIsLeg(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,POKEDEX)) {
            res_.setResult(new DefaultStruct(instance_.getPokedex(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultSimulationBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        SimulationBean instance_ = (SimulationBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,WIN_POINTS_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getWinPointsFight(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DIFF_WINNING_EXP_PTS_FIGHT)) {
            res_.setResult(std_.wrapStd(instance_.getDiffWinningExpPtsFight(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOW_CATCHING_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getAllowCatchingKo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOWED_SWITCH_PLACES_END_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getAllowedSwitchPlacesEndRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WIN_TRAINER_EXP)) {
            res_.setResult(new DefaultStruct(instance_.getWinTrainerExp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WINNING_EXP_PTS_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getRateWinningExpPtsFight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_FIGHT_IF_ONE_TEAM_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getEndFightIfOneTeamKo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_PLAYER)) {
            res_.setResult(new ShortStruct(instance_.getIvPlayer()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_FOE)) {
            res_.setResult(new ShortStruct(instance_.getIvFoe()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WIN_MONEY_BASE)) {
            res_.setResult(new DefaultStruct(instance_.getRateWinMoneyBase(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_LOOSE_MONEY_WIN)) {
            res_.setResult(new DefaultStruct(instance_.getRateLooseMoneyWin(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RESTORED_MOVES_END_FIGHT)) {
            res_.setResult(BooleanStruct.of(instance_.getRestoredMovesEndFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_CLOSING)) {
            res_.setResult(BooleanStruct.of(instance_.getEnabledClosing()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RANDOM_WILD_FIGHT)) {
            res_.setResult(BooleanStruct.of(instance_.getRandomWildFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STILL_POSSIBLE_FLEE)) {
            res_.setResult(BooleanStruct.of(instance_.getStillPossibleFlee()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL)) {
            res_.setResult(BooleanStruct.of(instance_.getSkipLearningMovesWhileNotGrowingLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATES)) {
            res_.setResult(new DefaultStruct(instance_.getDamageRates(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_PLAYER)) {
            res_.setResult(std_.wrapStd(instance_.getDamageRatePlayer(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_PLAYER_TABLE)) {
            res_.setResult(new DefaultStruct(instance_.getDamageRatePlayerTable(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_LAW_FOE)) {
            res_.setResult(std_.wrapStd(instance_.getDamageRateLawFoe(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_FOE_TABLE)) {
            res_.setResult(new DefaultStruct(instance_.getDamageRateFoeTable(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FREE_TEAMS)) {
            res_.setResult(BooleanStruct.of(instance_.getFreeTeams()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULTIPLICITY)) {
            res_.setResult(new IntStruct(instance_.getMultiplicity()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENVIRONMENTS)) {
            res_.setResult(new DefaultStruct(instance_.getEnvironments(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENVIRONMENT)) {
            res_.setResult(std_.wrapStd(instance_.getEnvironment(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FOE_TEAM)) {
            res_.setResult(new DefaultStruct(instance_.getFoeTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_FOE_PK)) {
            res_.setResult(new IntStruct(instance_.getSelectedFoePk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_FOE_ACTION)) {
            res_.setResult(std_.wrapStd(instance_.getSelectedFoeAction(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLY_TEAM)) {
            res_.setResult(new DefaultStruct(instance_.getAllyTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ALLY_PK)) {
            res_.setResult(new IntStruct(instance_.getSelectedAllyPk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ALLY_ACTION)) {
            res_.setResult(std_.wrapStd(instance_.getSelectedAllyAction(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLACES)) {
            res_.setResult(new DefaultStruct(instance_.getPlaces(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,OK)) {
            res_.setResult(BooleanStruct.of(instance_.getOk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TEAM)) {
            res_.setResult(new DefaultStruct(instance_.getTeam(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_PK)) {
            res_.setResult(new IntStruct(instance_.getSelectedPk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ACTION)) {
            res_.setResult(std_.wrapStd(instance_.getSelectedAction(),_cont));
            return res_;
        }
        if (StringList.quickEq(fieldName_,AVAILABLE_EVOS)) {
            res_.setResult(new DefaultStruct(instance_.getAvailableEvos(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHOSEN_EVO)) {
            res_.setResult(new StringStruct(instance_.getChosenEvo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL_EVO)) {
            res_.setResult(new ShortStruct(instance_.getLevelEvo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ROUND)) {
            res_.setResult(new IntStruct(instance_.getSelectedRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLACES_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getPlacesFight(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLACE_FIGHT)) {
            res_.setResult(new IntStruct(instance_.getPlaceFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISPLAY_IF_ERROR)) {
            res_.setResult(BooleanStruct.of(instance_.getDisplayIfError()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getAbilities(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CURRENT_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getCurrentAbility()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,KEPT_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getKeptMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES_SET)) {
            res_.setResult(new DefaultStruct(instance_.getMovesSet(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_MOVE)) {
            res_.setResult(new IntStruct(instance_.getSelectedMove()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLY_CHOICE)) {
            res_.setResult(BooleanStruct.of(instance_.getAllyChoice()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TARGET_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getTargetFight(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TARGET)) {
            res_.setResult(new IntStruct(instance_.getTarget()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,COMMENTS)) {
            res_.setResult(new DefaultStruct(instance_.getComments(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TEAM_AFTER_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getTeamAfterFight(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVOLUTIONS_AFTER_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getEvolutionsAfterFight(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVOLUTION_AFTER_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getEvolutionAfterFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITIES_AFTER_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getAbilitiesAfterFight(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITY_AFTER_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getAbilityAfterFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,KEPT_MOVES_AFTER_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getKeptMovesAfterFight(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultSimulationLevelBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        SimulationLevelBean instance_ = (SimulationLevelBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,POSSIBLE_MULTI_LAYER)) {
            res_.setResult(BooleanStruct.of(instance_.getPossibleMultiLayer()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLACE_NAME)) {
            res_.setResult(new StringStruct(instance_.getPlaceName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL_INDEX)) {
            res_.setResult(new IntStruct(instance_.getLevelIndex()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,OUTSIDE)) {
            res_.setResult(BooleanStruct.of(instance_.getOutside()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ROAD)) {
            res_.setResult(BooleanStruct.of(instance_.getRoad()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GYM)) {
            res_.setResult(BooleanStruct.of(instance_.getGym()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,POKEMON_CENTER)) {
            res_.setResult(BooleanStruct.of(instance_.getPokemonCenter()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NO_FIGHT)) {
            res_.setResult(new IntStruct(instance_.getNoFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TILES)) {
            res_.setResult(new DefaultStruct(instance_.getTiles(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultAddPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        AddPokemonBean instance_ = (AddPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,ABILITY)) {
            instance_.setAbility((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDER)) {
            instance_.setGender((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL)) {
            instance_.setLevel((Short) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            instance_.setTypedName((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_TYPE)) {
            instance_.setTypedType((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,WHOLE_WORD)) {
            instance_.setWholeWord((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAS_EVO)) {
            instance_.setHasEvo((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_EVO)) {
            instance_.setIsEvo((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_LEG)) {
            instance_.setIsLeg((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultEditPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EditPokemonBean instance_ = (EditPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,EXPERIENCE)) {
            instance_.setExperience((Rate) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,BALL)) {
            instance_.setBall((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS)) {
            instance_.setHappiness((Short) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_HP)) {
            instance_.setRemainingHp((Rate) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL)) {
            instance_.setHeal((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultEditPokemonMovesBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EditPokemonMovesBean instance_ = (EditPokemonMovesBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            instance_.setTypedName((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,CATEGORY)) {
            instance_.setCategory((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_TYPE)) {
            instance_.setTypedType((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,WHOLE_WORD)) {
            instance_.setWholeWord((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,AVAILABLE_MOVES_ONLY)) {
            instance_.setAvailableMovesOnly((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultEditTrainerPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EditTrainerPokemonBean instance_ = (EditTrainerPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,GENDER)) {
            instance_.setGender((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL)) {
            instance_.setLevel((Short) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLY_PK)) {
            instance_.setAllyPk((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultSelectAbilityBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        SelectAbilityBean instance_ = (SelectAbilityBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_ABILITY)) {
            instance_.setTypedAbility((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultSelectItemBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        SelectItemBean instance_ = (SelectItemBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            instance_.setTypedName((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_PRICE)) {
            instance_.setTypedPrice((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_CLASS)) {
            instance_.setTypedClass((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultSelectPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        SelectPokemonBean instance_ = (SelectPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            instance_.setTypedName((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_TYPE)) {
            instance_.setTypedType((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,WHOLE_WORD)) {
            instance_.setWholeWord((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAS_EVO)) {
            instance_.setHasEvo((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_EVO)) {
            instance_.setIsEvo((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IS_LEG)) {
            instance_.setIsLeg((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultSimulationBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        SimulationBean instance_ = (SimulationBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DIFF_WINNING_EXP_PTS_FIGHT)) {
            instance_.setDiffWinningExpPtsFight((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOW_CATCHING_KO)) {
            instance_.setAllowCatchingKo((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOWED_SWITCH_PLACES_END_ROUND)) {
            instance_.setAllowedSwitchPlacesEndRound((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,WIN_TRAINER_EXP)) {
            instance_.setWinTrainerExp((Rate) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WINNING_EXP_PTS_FIGHT)) {
            instance_.setRateWinningExpPtsFight((Rate) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_FIGHT_IF_ONE_TEAM_KO)) {
            instance_.setEndFightIfOneTeamKo((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_PLAYER)) {
            instance_.setIvPlayer((Short) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_FOE)) {
            instance_.setIvFoe((Short) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WIN_MONEY_BASE)) {
            instance_.setRateWinMoneyBase((Rate) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_LOOSE_MONEY_WIN)) {
            instance_.setRateLooseMoneyWin((Rate) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RESTORED_MOVES_END_FIGHT)) {
            instance_.setRestoredMovesEndFight((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_CLOSING)) {
            instance_.setEnabledClosing((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RANDOM_WILD_FIGHT)) {
            instance_.setRandomWildFight((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,STILL_POSSIBLE_FLEE)) {
            instance_.setStillPossibleFlee((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL)) {
            instance_.setSkipLearningMovesWhileNotGrowingLevel((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_PLAYER)) {
            instance_.setDamageRatePlayer((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_LAW_FOE)) {
            instance_.setDamageRateLawFoe((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,FREE_TEAMS)) {
            instance_.setFreeTeams((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULTIPLICITY)) {
            instance_.setMultiplicity((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENVIRONMENT)) {
            instance_.setEnvironment((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_FOE_PK)) {
            instance_.setSelectedFoePk((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_FOE_ACTION)) {
            instance_.setSelectedFoeAction((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ALLY_PK)) {
            instance_.setSelectedAllyPk((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ALLY_ACTION)) {
            instance_.setSelectedAllyAction((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_PK)) {
            instance_.setSelectedPk((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ACTION)) {
            instance_.setSelectedAction((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,CHOSEN_EVO)) {
            instance_.setChosenEvo((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL_EVO)) {
            instance_.setLevelEvo((Short) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_ROUND)) {
            instance_.setSelectedRound((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLACE_FIGHT)) {
            instance_.setPlaceFight((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,CURRENT_ABILITY)) {
            instance_.setCurrentAbility((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SELECTED_MOVE)) {
            instance_.setSelectedMove((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLY_CHOICE)) {
            instance_.setAllyChoice((Boolean) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TARGET)) {
            instance_.setTarget((Integer) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVOLUTION_AFTER_FIGHT)) {
            instance_.setEvolutionAfterFight((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITY_AFTER_FIGHT)) {
            instance_.setAbilityAfterFight((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAddPokemonBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        AddPokemonBean instance_ = (AddPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(AddPokemonBean.cancel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ADD)) {
            res_.setResult(new StringStruct(instance_.add()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEARCH)) {
            instance_.search();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MINI_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniImage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LINK)) {
            instance_.clickLink((Long)_args[0]);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEditPokemonBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EditPokemonBean instance_ = (EditPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(EditPokemonBean.cancel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,TRANSLATE_NAME)) {
            res_.setResult(new StringStruct(instance_.translateName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,TRANSLATE_ITEM)) {
            res_.setResult(new StringStruct(instance_.translateItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CHOOSE_ITEM)) {
            res_.setResult(new StringStruct(instance_.chooseItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ADD_MOVES)) {
            res_.setResult(new StringStruct(instance_.addMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,DELETE_MOVES)) {
            instance_.deleteMoves();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,EDIT)) {
            res_.setResult(new StringStruct(instance_.edit()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_STATISTIC)) {
            res_.setResult(new StringStruct(instance_.getTranslatedStatistic((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEditPokemonMovesBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EditPokemonMovesBean instance_ = (EditPokemonMovesBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(instance_.cancel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEARCH)) {
            instance_.search();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,ADD_MOVES)) {
            res_.setResult(new StringStruct(instance_.addMoves()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEditTrainerPokemonBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EditTrainerPokemonBean instance_ = (EditTrainerPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(instance_.cancel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CHOOSE_NAME)) {
            res_.setResult(new StringStruct(instance_.chooseName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CHOOSE_ITEM)) {
            res_.setResult(new StringStruct(instance_.chooseItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CHOOSE_ABILITY)) {
            res_.setResult(new StringStruct(instance_.chooseAbility()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ADD_MOVES)) {
            res_.setResult(new StringStruct(instance_.addMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,DELETE_MOVES)) {
            instance_.deleteMoves();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_TRAINER_PK)) {
            res_.setResult(new StringStruct(instance_.validateTrainerPk()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTranslatedName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTranslatedAbility()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRANSLATED_ITEM)) {
            res_.setResult(new StringStruct(instance_.getTranslatedItem()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSelectAbilityBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        SelectAbilityBean instance_ = (SelectAbilityBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(SelectAbilityBean.cancel()));
            return res_;
        }
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
    public static ResultErrorStd invokeMethodSelectItemBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        SelectItemBean instance_ = (SelectItemBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(instance_.cancel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_ITEM)) {
            res_.setResult(new StringStruct(instance_.cancelItem()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEARCH)) {
            res_.setResult(new StringStruct(instance_.search()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MINI_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniImage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LINK)) {
            res_.setResult(new StringStruct(instance_.clickLink((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSelectPokemonBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        SelectPokemonBean instance_ = (SelectPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(SelectPokemonBean.cancel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEARCH)) {
            res_.setResult(new StringStruct(instance_.search()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MINI_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniImage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LINK)) {
            res_.setResult(new StringStruct(instance_.clickLink((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSimulationBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        SimulationBean instance_ = (SimulationBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_REAL_STEP_NUMBER)) {
            res_.setResult(new IntStruct(instance_.getRealStepNumber()));
            return res_;
        }
        if (StringList.quickEq(methodName_,QUIT)) {
            res_.setResult(new StringStruct(instance_.quit()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_DIFF_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isDiffState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_DIFF_CHOICE)) {
            instance_.validateDiffChoice();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOE_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isFoeState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ADD_PK_TRAINER)) {
            res_.setResult(new StringStruct(instance_.addPkTrainer()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SELECT_FOE_PK)) {
            res_.setResult(new StringStruct(instance_.selectFoePk()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_FOE)) {
            res_.setResult(new StringStruct(instance_.getImageFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAME_FOE)) {
            res_.setResult(new StringStruct(instance_.getNameFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_LEVEL_FOE)) {
            res_.setResult(new IntStruct(instance_.getLevelFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ABILITY_FOE)) {
            res_.setResult(new StringStruct(instance_.getAbilityFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_GENDER_FOE)) {
            res_.setResult(new StringStruct(instance_.getGenderFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM_FOE)) {
            res_.setResult(new StringStruct(instance_.getItemFoe((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MOVES_FOE)) {
            res_.setResult(new DefaultStruct(instance_.getMovesFoe((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,SELECT_ALLY_PK)) {
            res_.setResult(new StringStruct(instance_.selectAllyPk()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_ALLY)) {
            res_.setResult(new StringStruct(instance_.getImageAlly((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAME_ALLY)) {
            res_.setResult(new StringStruct(instance_.getNameAlly((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_LEVEL_ALLY)) {
            res_.setResult(new IntStruct(instance_.getLevelAlly((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ABILITY_ALLY)) {
            res_.setResult(new StringStruct(instance_.getAbilityAlly((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_GENDER_ALLY)) {
            res_.setResult(new StringStruct(instance_.getGenderAlly((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM_ALLY)) {
            res_.setResult(new StringStruct(instance_.getItemAlly((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MOVES_ALLY)) {
            res_.setResult(new DefaultStruct(instance_.getMovesAlly((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_MULTI_LAYER)) {
            res_.setResult(BooleanStruct.of(instance_.isMultiLayer((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,LAYERS)) {
            res_.setResult(new DefaultStruct(instance_.layers((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LEVEL)) {
            res_.setResult(new StringStruct(instance_.clickLevel((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TRAINER_NAME)) {
            res_.setResult(new StringStruct(instance_.getTrainerName()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_DIFF_CHOICE)) {
            instance_.cancelDiffChoice();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_FOE_CHOICE_FREE)) {
            instance_.validateFoeChoiceFree();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_FOE_CHOICE)) {
            instance_.validateFoeChoice();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_TEAM_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isTeamState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,ADD)) {
            res_.setResult(new StringStruct(instance_.add()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SELECT_PK)) {
            res_.setResult(new StringStruct(instance_.selectPk()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getLevel((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getMoves((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_TEAM)) {
            instance_.cancelTeam();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_TEAM)) {
            instance_.validateTeam();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_FOE_CHOICE_SKIP_EVOLUTIONS)) {
            instance_.validateFoeChoiceSkipEvolutions();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_EVOLUTIONS_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isEvolutionsState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,DISPLAY_EVOLUTIONS)) {
            instance_.displayEvolutions();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,SELECTED_INDEX)) {
            res_.setResult(BooleanStruct.of(instance_.selectedIndex()));
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_EVO)) {
            instance_.validateEvo();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_EVO)) {
            instance_.cancelEvo();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_EVOLUTIONS)) {
            instance_.cancelEvolutions();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_EVOLUTIONS)) {
            instance_.validateEvolutions();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FRONT_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isFrontState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_FRONT_FIGHTER)) {
            instance_.validateFrontFighter();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_FRONT_FIGHTERS)) {
            instance_.cancelFrontFighters();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_FRONT_FIGHTERS)) {
            instance_.validateFrontFighters();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_MOVES_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isMovesState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SELECTED_INDEX_FOR_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.selectedIndexForMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_AVAILABLE_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.isAvailableMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_MOVES)) {
            instance_.validateMoves();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_AVAILABLE_ABILITIES)) {
            res_.setResult(BooleanStruct.of(instance_.isAvailableAbilities()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_MOVES)) {
            instance_.cancelMoves();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_MOVES_SETS)) {
            instance_.cancelMovesSets();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_MOVES_SETS)) {
            instance_.validateMovesSets();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_MOVES_FIGHT_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isMovesFightState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_MOVES_CHOICE)) {
            instance_.validateMovesChoice();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_MOVES_EVOS)) {
            instance_.cancelMovesEvos();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,SIMULATE_FIGHT)) {
            instance_.simulateFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_SIMULATION_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isSimulationState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KO_PLAYERS)) {
            res_.setResult(new DefaultStruct(instance_.getKoPlayers(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NOT_KO_FRONT_FOES)) {
            res_.setResult(new DefaultStruct(instance_.getNotKoFrontFoes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KO_FOES)) {
            res_.setResult(new DefaultStruct(instance_.getKoFoes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_RULES_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isRulesIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_RULES_MOVES_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isRulesMovesIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_RULES_LEARN_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isRulesLearnIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_RULES_SWITCH_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isRulesSwitchIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_SENDING_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isSendingIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_RANDOM_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isRandomIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_USING_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isUsingIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_HARD_SIMULATION_ISSUE)) {
            res_.setResult(BooleanStruct.of(instance_.isHardSimulationIssue()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ISSUE_AFTER_FIGHT)) {
            res_.setResult(BooleanStruct.of(instance_.isIssueAfterFight()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_AFTER_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getImageAfterFight((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_NAME_AFTER_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getNameAfterFight((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_LEVEL_AFTER_FIGHT)) {
            res_.setResult(new IntStruct(instance_.getLevelAfterFight((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ABILITY_AFTER_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getAbilityAfterFight((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_GENDER_AFTER_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getGenderAfterFight((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_ITEM_AFTER_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getItemAfterFight((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MOVES_AFTER_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getMovesAfterFight((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_REMAINING_LIFE_RATE)) {
            res_.setResult(new DefaultStruct(instance_.getRemainingLifeRate((Long)_args[0]),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringList.quickEq(methodName_,NUMBER_NECESSARY_POINTS_FOR_GROWING_LEVEL)) {
            res_.setResult(new DefaultStruct(instance_.numberNecessaryPointsForGrowingLevel((Long)_args[0]),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(methodName_,CHANGE_FIGHT)) {
            instance_.changeFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FIGHT_AFTER)) {
            res_.setResult(BooleanStruct.of(instance_.isFightAfter()));
            return res_;
        }
        if (StringList.quickEq(methodName_,NEXT_FIGHT)) {
            instance_.nextFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,DISPLAY_COMMENTS)) {
            instance_.displayComments();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,HIDE_COMMENTS)) {
            instance_.hideComments();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_EVOLUTION_AFTER_FIGHT_STATE)) {
            res_.setResult(BooleanStruct.of(instance_.isEvolutionAfterFightState()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SELECT_PK_AFTER_FIGHT)) {
            instance_.selectPkAfterFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_EVOLUTION_AFTER_FIGHT)) {
            instance_.validateEvolutionAfterFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,CANCEL_EVOLUTIONS_AFTER_FIGHT)) {
            instance_.cancelEvolutionsAfterFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_MOVES_ABILITY_AFTER_FIGHT)) {
            instance_.validateMovesAbilityAfterFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,CHANGE_FIGHT_WHILE_END)) {
            instance_.changeFightWhileEnd();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(methodName_,VALIDATE_MOVES_AFTER_FIGHT)) {
            instance_.validateMovesAfterFight();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSimulationLevelBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        SimulationLevelBean instance_ = (SimulationLevelBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CANCEL)) {
            res_.setResult(new StringStruct(SimulationLevelBean.cancel()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MAP_WIDTH)) {
            res_.setResult(new IntStruct(instance_.getMapWidth()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FIRST_ROW)) {
            res_.setResult(BooleanStruct.of(instance_.isFirstRow((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TILE)) {
            res_.setResult(new StringStruct(instance_.clickTile((Long)_args[0])));
            return res_;
        }
        return res_;
    }
}
