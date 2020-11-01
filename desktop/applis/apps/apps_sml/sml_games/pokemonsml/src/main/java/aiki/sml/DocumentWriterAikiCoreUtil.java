package aiki.sml;
import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.fight.Combos;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.EvolvingItem;
import aiki.fight.items.EvolvingStone;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingHp;
import aiki.fight.items.HealingHpStatus;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.items.HealingSimpleItem;
import aiki.fight.items.HealingSimpleStatus;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.items.Repel;
import aiki.fight.items.SellingItem;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectAccuracy;
import aiki.fight.moves.effects.EffectAlly;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectClone;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectCommonStatistics;
import aiki.fight.moves.effects.EffectCopyFighter;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import aiki.fight.moves.effects.EffectEndRoundGlobal;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleStatus;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import aiki.fight.moves.effects.EffectEndRoundStatusRelation;
import aiki.fight.moves.effects.EffectEndRoundTeam;
import aiki.fight.moves.effects.EffectFullHpRate;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import aiki.fight.moves.effects.EffectMultUsedMovePower;
import aiki.fight.moves.effects.EffectOrder;
import aiki.fight.moves.effects.EffectProtectFromTypes;
import aiki.fight.moves.effects.EffectProtection;
import aiki.fight.moves.effects.EffectRemainedHpRate;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectSwitchAbilities;
import aiki.fight.moves.effects.EffectSwitchItems;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.EffectSwitchPosition;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.effects.EffectVarPP;
import aiki.fight.moves.effects.EffectWinMoney;
import aiki.fight.moves.effects.enums.ConstValuesType;
import aiki.fight.moves.effects.enums.ExchangeType;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.MoveItemType;
import aiki.fight.moves.effects.enums.PointViewChangementType;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionHappiness;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionLevel;
import aiki.fight.pokemon.evolution.EvolutionLevelGender;
import aiki.fight.pokemon.evolution.EvolutionLevelSimple;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.pokemon.evolution.EvolutionMoveType;
import aiki.fight.pokemon.evolution.EvolutionStone;
import aiki.fight.pokemon.evolution.EvolutionStoneGender;
import aiki.fight.pokemon.evolution.EvolutionStoneSimple;
import aiki.fight.pokemon.evolution.EvolutionTeam;
import aiki.fight.status.Status;
import aiki.fight.status.StatusBeginRound;
import aiki.fight.status.StatusBeginRoundAutoDamage;
import aiki.fight.status.StatusBeginRoundSimple;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.*;
import aiki.game.Game;
import aiki.game.HostPokemonDuo;
import aiki.game.NbFightCoords;
import aiki.game.UsesOfMove;
import aiki.game.fight.ActivityOfMove;
import aiki.game.fight.Anticipation;
import aiki.game.fight.ChoiceOfEvolutionAndMoves;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.MoveTeamPosition;
import aiki.game.fight.StacksOfUses;
import aiki.game.fight.TargetCoords;
import aiki.game.fight.Team;
import aiki.game.fight.TeamPosition;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionHealMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSimpleHeal;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.util.*;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Inventory;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.buildings.PokemonCenter;
import aiki.map.characters.Ally;
import aiki.map.characters.CharacterInRoadCave;
import aiki.map.characters.DealerItem;
import aiki.map.characters.DualFight;
import aiki.map.characters.GerantPokemon;
import aiki.map.characters.GymLeader;
import aiki.map.characters.GymTrainer;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.characters.TempTrainer;
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerLeague;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.TrainerOneFight;
import aiki.map.characters.enums.GeranceType;
import aiki.map.characters.enums.SellType;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.Level;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelOutdoor;
import aiki.map.levels.LevelRoad;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.places.Road;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.LawNumber;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.images.BaseSixtyFourUtil;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.sml.maths.DocumentWriterMathUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.core.DocumentWriterCoreUtil;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.StringUtil;

public final class DocumentWriterAikiCoreUtil {

    public static final String SEPARATOR_RAND = ";";
    public static final String SEPARATOR_RAND_EVENTS = " ";
    public static final String DEF_MOVE = "DEF_MOVE";
    public static final String RATE_CATCHING = "RATE_CATCHING";
    public static final String RATE_FLEEING = "RATE_FLEEING";
    public static final String RATE_BOOST = "RATE_BOOST";
    public static final String RATE_BOOST_CRITICAL_HIT = "RATE_BOOST_CRITICAL_HIT";
    public static final String DAMAGE_FORMULA = "DAMAGE_FORMULA";
    public static final String DEFAULT_EGG_GROUP = "DEFAULT_EGG_GROUP";
    public static final String IMG_FILES_RES_EXT_TXT = ".txt";
    public static final String FILES_RES_EXT = ".xml";
    /**
     * The custom beans can be modified but they must have a common base package
     * Avoid to recompile classes in standard packages like java, javax, and
     * even projects core, gui ...
     */

    public static final String IMAGES_FOLDER = "images";
    public static final String LINKS_FOLDER = "links";
    public static final String PEOPLE_FOLDER = "people";
    public static final String TRAINERS_FOLDER = "trainers";
    public static final String HERO_FOLDER = "heros";
    public static final String MINI_MAP_FOLDER = "mini_map";
    public static final String HERO_FRONT = "heros_front.txt";
    public static final String HERO_BACK = "heros_back.txt";
    public static final String HERO_MINI = "heros_mini.txt";
    public static final String FRONT_IMAGES_FOLDER = "front";
    public static final String BACK_IMAGES_FOLDER = "back";
    public static final String MINI_IMAGES_FOLDER = "mini";

    public static final String OBJECTS_IMAGES_FOLDER = "items_images";
    public static final String TYPES_IMAGES_FOLDER = "types_images";
    public static final String IMAGE_TM_HM_FILES = "hm_tm";
    public static final String IMAGE_STORAGE_FILES = "storage";
    public static final String TYPES_COLOR_CODE = "types_color";
    public static final String SEPARATOR_FILES = "/";
    public static final String END_GAME_IMAGE = "end_game";

    public static final String ANIM_STATIS = "anim_statis";

    public static final String ANIM_STATUS = "anim_status";

    public static final String ANIM_ABSORB = "anim_absorb/absorb.txt";

    private static final String POKEDEX_FOLDER = "pokedex";
    private static final String MOVES_FOLDER = "moves";
    private static final String ABILITIES_FOLDER = "abilities";
    private static final String STATUS_FOLDER = "status";

    private static final String ITEMS_FOLDER = "items";
    private static final String SEPARATOR_KEY_HEROS = ";";

    private static final String TAB = "\t";

    private static final String RETURN_LINE = "\n";
    private static final String CS = "CS";
    private static final String CT = "CT";
    private static final String CT_CS_FILE = "ct_cs.txt";
    private static final String CONST_NUM = "const_num.txt";
    private static final String CONST_NOT_NUM = "constantes_non_num.txt";
    private static final String TABLE_TYPES = "table_types.txt";
    private static final String LOIS_RANDOM = "lois_random.txt";
    private static final String COURBE_PTS_EXP = "courbe_pts_exp.txt";
    private static final String RATE_WON_POINTS = "rate_won_points.txt";
    private static final String COMBOS = "combos.xml";
    private static final String MAP_FILE = "map.xml";
    private static final String TRANSLATION_FOLDER = "translations";
    private static final String TRANSLATION_CATEGORIES = "categories.txt";
    private static final String TRANSLATION_GENDERS = "genders.txt";
    private static final String TRANSLATION_ENVIRONMENTS = "environments.txt";
    private static final String TRANSLATION_BOOLEANS = "booleans.txt";
    private static final String TRANSLATION_DIFF_WIN_PTS = "winpts.txt";
    private static final String TRANSLATION_DIFF_MODEL_LAW = "modellaw.txt";
    private static final String TRANSLATION_STATISTICS = "statistics.txt";
    private static final String TRANSLATION_TARGETS = "targets.txt";
    private static final String TRANSLATION_TYPES = "types.txt";
    private static final String TRANSLATION_POKEMON = "pokemon.txt";
    private static final String TRANSLATION_MOVES = "moves.txt";
    private static final String TRANSLATION_ITEMS = "items.txt";
    private static final String TRANSLATION_ABILITIES = "abilities.txt";
    private static final String TRANSLATION_STATUS = "status.txt";
    private static final String TRANSLATION_MATH = "math.txt";
    private static final String TRANSLATION_CLASSES = "classes.txt";
    private static final String TRANSLATION_LITTERAL = "litteral.txt";

    private static final String BALL_DEF = "BALL_DEF";

    private static final String ATTR_VALUE = "value";
    private static final String EMPTY_STRING = "";
    private static final String FIELD_ABILITIES = "abilities";
    private static final String FIELD_ABILITY = "ability";
    private static final String FIELD_ACCESS_CONDITION = "accessCondition";
    private static final String FIELD_ACCESS_COORDS = "accessCoords";
    private static final String FIELD_ACCESS_POINT = "accessPoint";
    private static final String FIELD_ACCURACY = "accuracy";
    private static final String FIELD_ACHIEVE_DISAPPEARED_PK_USING_MOVE = "achieveDisappearedPkUsingMove";
    private static final String FIELD_ACHIEVED_DISAPPEARED_PK = "achievedDisappearedPk";
    private static final String FIELD_ACTED = "acted";
    private static final String FIELD_ACTION = "action";
    private static final String FIELD_ADDED_TYPES = "addedTypes";
    private static final String FIELD_AGAINST_EVO = "againstEvo";
    private static final String FIELD_ALLOW_CATCHING_KO = "allowCatchingKo";
    private static final String FIELD_ALLOWED_SWITCH_PLACES_END_ROUND = "allowedSwitchPlacesEndRound";
    private static final String FIELD_ALLY = "ally";
    private static final String FIELD_ALLY_CHOICE = "allyChoice";
    private static final String FIELD_ALREADY_INVOKED_MOVES_ROUND = "alreadyInvokedMovesRound";
    private static final String FIELD_ATTACK = "attack";
    private static final String FIELD_ATTACK_LAST = "attackLast";
    private static final String FIELD_ATTACK_TARGET_WITH_TYPES = "attackTargetWithTypes";
    private static final String FIELD_ATTACKS_SOON = "attacksSoon";
    private static final String FIELD_AVG_NB_STEPS = "avgNbSteps";
    private static final String FIELD_BASE_EVO = "baseEvo";
    private static final String FIELD_BEAT_GYM_LEADER = "beatGymLeader";
    private static final String FIELD_BEAT_GYM_TRAINER = "beatGymTrainer";
    private static final String FIELD_BEAT_TRAINER = "beatTrainer";
    private static final String FIELD_BEGIN = "begin";
    private static final String FIELD_BEGIN_ROUND = "beginRound";
    private static final String FIELD_BELONGING_TO_PLAYER = "belongingToPlayer";
    private static final String FIELD_BLOCKS = "blocks";
    private static final String FIELD_BONUS_STAT_RANK = "bonusStatRank";
    private static final String FIELD_BOOST_EXP = "boostExp";
    private static final String FIELD_BOOST_STAT_RANK_END_ROUND = "boostStatRankEndRound";
    private static final String FIELD_BOOST_STAT_RANK_PROTECTED = "boostStatRankProtected";
    private static final String FIELD_BOOST_STATIS = "boostStatis";
    private static final String FIELD_BOOST_STATIS_ONCE_KO_FOE = "boostStatisOnceKoFoe";
    private static final String FIELD_BOOST_STATIS_SUPER_EFF = "boostStatisSuperEff";
    private static final String FIELD_BOOST_STATIS_TYPES = "boostStatisTypes";
    private static final String FIELD_BOOSTED_TYPES = "boostedTypes";
    private static final String FIELD_BOX = "box";
    private static final String FIELD_BREAK_FOE_IMMUNE = "breakFoeImmune";
    private static final String FIELD_BREAK_IMMU_TYPE_ABILITY = "breakImmuTypeAbility";
    private static final String FIELD_BREAK_PROTECTION = "breakProtection";
    private static final String FIELD_BUILDINGS = "buildings";
    private static final String FIELD_CANCEL_CHGT_STAT = "cancelChgtStat";
    private static final String FIELD_CANCEL_CHGT_STAT_FOE_TEAM = "cancelChgtStatFoeTeam";
    private static final String FIELD_CANCEL_CHGT_STAT_TEAM = "cancelChgtStatTeam";
    private static final String FIELD_CANCEL_EFFECTS = "cancelEffects";
    private static final String FIELD_CANCEL_IMMU_TYPE = "cancelImmuType";
    private static final String FIELD_CANCEL_LOW_STAT = "cancelLowStat";
    private static final String FIELD_CANCEL_PROTECTING_ABILITIES = "cancelProtectingAbilities";
    private static final String FIELD_CANCEL_SEC_EFFECT_OTHER = "cancelSecEffectOther";
    private static final String FIELD_CANCEL_SEC_EFFECT_OWNER = "cancelSecEffectOwner";
    private static final String FIELD_CANCELED_IF_USED = "canceledIfUsed";
    private static final String FIELD_CANNOT_KO = "cannotKo";
    private static final String FIELD_CATCHING_BALL = "catchingBall";
    private static final String FIELD_CATCHING_RATE = "catchingRate";
    private static final String FIELD_CATEGORY = "category";
    private static final String FIELD_CATEGORY_BOOSTING = "categoryBoosting";
    private static final String FIELD_CAUGHT_EVOLUTIONS = "caughtEvolutions";
    private static final String FIELD_CAUGHT_PK = "caughtPk";
    private static final String FIELD_CH_LAW = "chLaw";
    private static final String FIELD_CH_RATE = "chRate";
    private static final String FIELD_CHANGE_TYPES = "changeTypes";
    private static final String FIELD_CHANGED = "changed";
    private static final String FIELD_CHANGED_TYPES_TERRAIN = "changedTypesTerrain";
    private static final String FIELD_CHANGING_BOOST_TYPES = "changingBoostTypes";
    private static final String FIELD_CHARACTERS = "characters";
    private static final String FIELD_CHGT_TYPE_BY_DAMAGE = "chgtTypeByDamage";
    private static final String FIELD_CHGT_TYPE_BY_ENV = "chgtTypeByEnv";
    private static final String FIELD_CHGT_TYPE_BY_WEATHER = "chgtTypeByWeather";
    private static final String FIELD_CHOICE_RESTRICTION = "choiceRestriction";
    private static final String FIELD_CHOICES = "choices";
    private static final String FIELD_CHOSEN_HEALING_ITEM = "chosenHealingItem";
    private static final String FIELD_CHOSEN_TARGETS = "chosenTargets";
    private static final String FIELD_CLICK_BUTTONS_PAD = "clickButtonsPad";
    private static final String FIELD_CLONE = "clone";
    private static final String FIELD_CLOSEST_FOE_DAMAGE_RATE_HP = "closestFoeDamageRateHp";
    private static final String FIELD_COMMON_VALUE = "commonValue";
    private static final String FIELD_CONST_ABILITY = "constAbility";
    private static final String FIELD_CONST_DAMAGE = "constDamage";
    private static final String FIELD_CONST_TYPES = "constTypes";
    private static final String FIELD_CONST_USER_CHOICE = "constUserChoice";
    private static final String FIELD_CONST_VALUES_TYPE = "constValuesType";
    private static final String FIELD_COPIED_MOVES = "copiedMoves";
    private static final String FIELD_COPY_BOOST = "copyBoost";
    private static final String FIELD_COPY_MOVES_TYPES = "copyMovesTypes";
    private static final String FIELD_COPYING_ABILITY = "copyingAbility";
    private static final String FIELD_COPYING_MOVE_FOR_USER = "copyingMoveForUser";
    private static final String FIELD_COPYING_MOVE_FOR_USER_DEF = "copyingMoveForUserDef";
    private static final String FIELD_COUNTER_FAIL = "counterFail";
    private static final String FIELD_COUNTERABLE_MOVE = "counterableMove";
    private static final String FIELD_CURRENT_ABILITY = "currentAbility";
    private static final String FIELD_CURRENT_GENDER = "currentGender";
    private static final String FIELD_CURRENT_MOVES = "currentMoves";
    private static final String FIELD_CURRENT_NAME = "currentName";
    private static final String FIELD_CURRENT_USER = "currentUser";
    private static final String FIELD_DAMAGE_BY_STATUS = "damageByStatus";
    private static final String FIELD_DAMAGE_END_ROUND = "damageEndRound";
    private static final String FIELD_DAMAGE_LAW = "damageLaw";
    private static final String FIELD_DAMAGE_RATE_AGAINST_FOE = "damageRateAgainstFoe";
    private static final String FIELD_DAMAGE_RATE_INFLICTED_BY_TYPE = "damageRateInflictedByType";
    private static final String FIELD_DAMAGE_RATE_LAW_FOE = "damageRateLawFoe";
    private static final String FIELD_DAMAGE_RATE_PLAYER = "damageRatePlayer";
    private static final String FIELD_DAMAGE_RATE_RECOIL_FOE = "damageRateRecoilFoe";
    private static final String FIELD_DAMAGE_RATE_SUFFERED_BY_TYPE = "damageRateSufferedByType";
    private static final String FIELD_DAMAGE_RECOIL = "damageRecoil";
    private static final String FIELD_DAMAGE_SUFFERED_CATEG = "damageSufferedCateg";
    private static final String FIELD_DAMAGE_SUFFERED_CATEG_ROUND = "damageSufferedCategRound";
    private static final String FIELD_DECREASE_NEC_STEPS_HATCH = "decreaseNecStepsHatch";
    private static final String FIELD_DEFENSE = "defense";
    private static final String FIELD_DELETE_ALL_STATUS = "deleteAllStatus";
    private static final String FIELD_DELETE_ALL_STATUS_ALLY = "deleteAllStatusAlly";
    private static final String FIELD_DELETE_PP = "deletePp";
    private static final String FIELD_DELETED_BY_FOE_TYPES = "deletedByFoeTypes";
    private static final String FIELD_DELETED_STATUS = "deletedStatus";
    private static final String FIELD_DETRUIT_SI_CONTACT = "detruitSiContact";
    private static final String FIELD_DIFF_WINNING_EXP_PTS_FIGHT = "diffWinningExpPtsFight";
    private static final String FIELD_DIFFICULTY = "difficulty";
    private static final String FIELD_DIRECT = "direct";
    private static final String FIELD_DISABLE_FOE_TEAM_EFFECTS = "disableFoeTeamEffects";
    private static final String FIELD_DISABLE_FOE_TEAM_STATUS = "disableFoeTeamStatus";
    private static final String FIELD_DISABLE_IMMU_AGAINST_TYPES = "disableImmuAgainstTypes";
    private static final String FIELD_DISABLE_IMMU_FROM_MOVES = "disableImmuFromMoves";
    private static final String FIELD_DISABLE_WEATHER = "disableWeather";
    private static final String FIELD_DISABLED_EFF_IF_SWITCH = "disabledEffIfSwitch";
    private static final String FIELD_DISAPPEAR_BEFORE_USE = "disappearBeforeUse";
    private static final String FIELD_DISAPPEARED = "disappeared";
    private static final String FIELD_DIVIDE_STATUS_ROUND = "divideStatusRound";
    private static final String FIELD_DRAINED_HP_BY_DAMAGE_RATE = "drainedHpByDamageRate";
    private static final String FIELD_DROPPED_STAT_DIRECT_MOVE = "droppedStatDirectMove";
    private static final String FIELD_DUAL_FIGHTS = "dualFights";
    private static final String FIELD_EFFECT = "effect";
    private static final String FIELD_EFFECT_END_ROUND = "effectEndRound";
    private static final String FIELD_EFFECT_SENDING = "effectSending";
    private static final String FIELD_EFFECTS = "effects";
    private static final String FIELD_EFFECTS_PARTNER = "effectsPartner";
    private static final String FIELD_EFFICIENCY_MOVES = "efficiencyMoves";
    private static final String FIELD_EGG_GROUPS = "eggGroups";
    private static final String FIELD_ENABLE_ANIMATION = "enableAnimation";
    private static final String FIELD_ENABLE_MOVING_HEROS_ANIMATION = "enableMovingHerosAnimation";
    private static final String FIELD_ENABLED_CHANGING_TYPES_MOVES = "enabledChangingTypesMoves";
    private static final String FIELD_ENABLED_CLOSING = "enabledClosing";
    private static final String FIELD_ENABLED_COUNTERING_MOVES = "enabledCounteringMoves";
    private static final String FIELD_ENABLED_KEY_PAD = "enabledKeyPad";
    private static final String FIELD_ENABLED_MOVES = "enabledMoves";
    private static final String FIELD_ENABLED_MOVES_BY_GROUP = "enabledMovesByGroup";
    private static final String FIELD_ENABLED_MOVES_CONST_CHOICES = "enabledMovesConstChoices";
    private static final String FIELD_ENABLED_MOVES_END_ROUND = "enabledMovesEndRound";
    private static final String FIELD_ENABLED_MOVES_FOR_ALLY = "enabledMovesForAlly";
    private static final String FIELD_ENABLED_MOVES_PROT = "enabledMovesProt";
    private static final String FIELD_ENABLED_MOVES_UNPROT = "enabledMovesUnprot";
    private static final String FIELD_ENABLED_MOVES_WHILE_SENDING_FOE = "enabledMovesWhileSendingFoe";
    private static final String FIELD_ENABLED_MOVES_WHILE_SENDING_FOE_USES = "enabledMovesWhileSendingFoeUses";
    private static final String FIELD_ENABLED_WEATHER = "enabledWeather";
    private static final String FIELD_END_FIGHT_IF_ONE_TEAM_KO = "endFightIfOneTeamKo";
    private static final String FIELD_END_ROUND_RANK = "endRoundRank";
    private static final String FIELD_ENV_TYPE = "envType";
    private static final String FIELD_EV = "ev";
    private static final String FIELD_EVOLUTIONS = "evolutions";
    private static final String FIELD_EVS = "evs";
    private static final String FIELD_EVT_RATE = "evtRate";
    private static final String FIELD_EXCHANGE_ABILITY = "exchangeAbility";
    private static final String FIELD_EXCHANGE_TYPES = "exchangeTypes";
    private static final String FIELD_EXIT_CITY = "exitCity";
    private static final String FIELD_EXP_EVO = "expEvo";
    private static final String FIELD_EXP_ITEM = "expItem";
    private static final String FIELD_EXP_RATE = "expRate";
    private static final String FIELD_FAIL = "fail";
    private static final String FIELD_FAIL_END_ROUND = "failEndRound";
    private static final String FIELD_FAIL_SENDING = "failSending";
    private static final String FIELD_FAIL_STATUS = "failStatus";
    private static final String FIELD_FIGHT = "fight";
    private static final String FIELD_FIGHT_TYPE = "fightType";
    private static final String FIELD_FILE = "file";
    private static final String FIELD_FILE_NAME = "fileName";
    private static final String FIELD_FINAL_CHOSEN_MOVE = "finalChosenMove";
    private static final String FIELD_FIRST_CHOSEN_MOVE = "firstChosenMove";
    private static final String FIELD_FIRST_POKEMON = "firstPokemon";
    private static final String FIELD_FIRST_POSIT_FOE_FIGHTERS = "firstPositFoeFighters";
    private static final String FIELD_FIRST_POSIT_PLAYER_FIGHTERS = "firstPositPlayerFighters";
    private static final String FIELD_FOE_TRAINER = "foeTrainer";
    private static final String FIELD_FORBID_TARGET_USING_ITEM = "forbidTargetUsingItem";
    private static final String FIELD_FORBID_USE_BERRY_AGAINST_FOES = "forbidUseBerryAgainstFoes";
    private static final String FIELD_FORBIDDEN_BOOST = "forbiddenBoost";
    private static final String FIELD_FORBIDDING_HEALING = "forbiddingHealing";
    private static final String FIELD_FORWARD_STATUS = "forwardStatus";
    private static final String FIELD_GENDER = "gender";
    private static final String FIELD_GENDER_REP = "genderRep";
    private static final String FIELD_GERANCE = "gerance";
    private static final String FIELD_GERANTS = "gerants";
    private static final String FIELD_GIVE_ITEM_TO_ALLY_HAVING_USED = "giveItemToAllyHavingUsed";
    private static final String FIELD_GROUND_PLACE = "groundPlace";
    private static final String FIELD_GROUND_PLACE_SUBST = "groundPlaceSubst";
    private static final String FIELD_GYM_LEADER = "gymLeader";
    private static final String FIELD_GYM_LEADER_COORDS = "gymLeaderCoords";
    private static final String FIELD_GYM_TRAINERS = "gymTrainers";
    private static final String FIELD_HAPPINESS = "happiness";
    private static final String FIELD_HAPPINESS_HATCH = "happinessHatch";
    private static final String FIELD_HATCHING = "hatching";
    private static final String FIELD_HATCHING_STEPS = "hatchingSteps";
    private static final String FIELD_HEAL_AFTER = "healAfter";
    private static final String FIELD_HEAL_HP = "healHp";
    private static final String FIELD_HEAL_HP_BY_OWNER_TYPES = "healHpByOwnerTypes";
    private static final String FIELD_HEAL_HP_BY_SUPER_EFF_MOVE = "healHpBySuperEffMove";
    private static final String FIELD_HEAL_HP_BY_TYPE_IF_WEATHER = "healHpByTypeIfWeather";
    private static final String FIELD_HEAL_HP_BY_WEATHER = "healHpByWeather";
    private static final String FIELD_HEAL_HP_RATE = "healHpRate";
    private static final String FIELD_HEAL_HP_WHILE_USING_BERRY = "healHpWhileUsingBerry";
    private static final String FIELD_HEAL_PP = "healPp";
    private static final String FIELD_HEAL_STATUS = "healStatus";
    private static final String FIELD_HEALED_HP_RATE = "healedHpRate";
    private static final String FIELD_HEALED_HP_RATE_BY_SWITCH = "healedHpRateBySwitch";
    private static final String FIELD_HEALED_MOVE_PP = "healedMovePp";
    private static final String FIELD_HEALED_STATUS_BY_SWITCH = "healedStatusBySwitch";
    private static final String FIELD_HEALING_ALL_MOVES_FULLPP = "healingAllMovesFullpp";
    private static final String FIELD_HEALING_ALL_MOVES_PP = "healingAllMovesPp";
    private static final String FIELD_HEALING_END_ROUND = "healingEndRound";
    private static final String FIELD_HEALING_END_ROUND_GROUND = "healingEndRoundGround";
    private static final String FIELD_HEALING_KO = "healingKo";
    private static final String FIELD_HEALING_MOVE_FULLPP = "healingMoveFullpp";
    private static final String FIELD_HEALING_TEAM = "healingTeam";
    private static final String FIELD_HEIGHT = "height";
    private static final String FIELD_HEROS = "heros";
    private static final String FIELD_HIDDEN_MOVES = "hiddenMoves";
    private static final String FIELD_HITS_LAW = "hitsLaw";
    private static final String FIELD_HM = "hm";
    private static final String FIELD_HOSTED_PK = "hostedPk";
    private static final String FIELD_HP = "hp";
    private static final String FIELD_HP_RATE_CLONE = "hpRateClone";
    private static final String FIELD_IGN_ABILITY = "ignAbility";
    private static final String FIELD_IGN_FOE_STATIS_BOOST = "ignFoeStatisBoost";
    private static final String FIELD_IGN_FOE_TEAM_MOVE = "ignFoeTeamMove";
    private static final String FIELD_IGN_VAR_ACCUR_USER_NEG = "ignVarAccurUserNeg";
    private static final String FIELD_IGN_VAR_EVAS_TARGET_POS = "ignVarEvasTargetPos";
    private static final String FIELD_IGN_VAR_STAT_TARGET_POS = "ignVarStatTargetPos";
    private static final String FIELD_IGN_VAR_STAT_USER_NEG = "ignVarStatUserNeg";
    private static final String FIELD_IMAGE_FILE_NAME = "imageFileName";
    private static final String FIELD_IMAGE_MAXI_FILE_NAME = "imageMaxiFileName";
    private static final String FIELD_IMAGE_MINI_FILE_NAME = "imageMiniFileName";
    private static final String FIELD_IMAGE_MINI_SECOND_TRAINER_FILE_NAME = "imageMiniSecondTrainerFileName";
    private static final String FIELD_IMMU_ABILITY = "immuAbility";
    private static final String FIELD_IMMU_AGAINST_TYPES = "immuAgainstTypes";
    private static final String FIELD_IMMU_ALLY_FROM_MOVES = "immuAllyFromMoves";
    private static final String FIELD_IMMU_CH = "immuCh";
    private static final String FIELD_IMMU_DAMAGE_ALLY_MOVES = "immuDamageAllyMoves";
    private static final String FIELD_IMMU_DAMAGE_RECOIL = "immuDamageRecoil";
    private static final String FIELD_IMMU_DAMAGE_TRAPPING_MOVES = "immuDamageTrappingMoves";
    private static final String FIELD_IMMU_LOW_STAT = "immuLowStat";
    private static final String FIELD_IMMU_LOW_STAT_IF_STATUS = "immuLowStatIfStatus";
    private static final String FIELD_IMMU_LOW_STATIS = "immuLowStatis";
    private static final String FIELD_IMMU_LOW_STATIS_TYPES = "immuLowStatisTypes";
    private static final String FIELD_IMMU_MOVE = "immuMove";
    private static final String FIELD_IMMU_MOVE_TYPES_BY_WEATHER = "immuMoveTypesByWeather";
    private static final String FIELD_IMMU_MOVES = "immuMoves";
    private static final String FIELD_IMMU_RECHARGE_ROUND = "immuRechargeRound";
    private static final String FIELD_IMMU_STATUS = "immuStatus";
    private static final String FIELD_IMMU_STATUS_BEGIN_ROUND = "immuStatusBeginRound";
    private static final String FIELD_IMMU_STATUS_TYPES = "immuStatusTypes";
    private static final String FIELD_IMMU_SUFFERED_DAMAGE_LOW_EFF = "immuSufferedDamageLowEff";
    private static final String FIELD_IMMU_TYPES = "immuTypes";
    private static final String FIELD_IMMU_WEATHER = "immuWeather";
    private static final String FIELD_IMMUNE_TYPES = "immuneTypes";
    private static final String FIELD_INCR_USER_ACCURACY = "incrUserAccuracy";
    private static final String FIELD_INCREASED_PRIO = "increasedPrio";
    private static final String FIELD_INCREASED_PRIO_TYPES = "increasedPrioTypes";
    private static final String FIELD_INCREASING_MAX_NB_ROUND_GLOBAL_MOVE = "increasingMaxNbRoundGlobalMove";
    private static final String FIELD_INCREASING_MAX_NB_ROUND_TEAM_MOVE = "increasingMaxNbRoundTeamMove";
    private static final String FIELD_INCREASING_MAX_NB_ROUND_TRAP = "increasingMaxNbRoundTrap";
    private static final String FIELD_INCREMENT_END_ROUND = "incrementEndRound";
    private static final String FIELD_INCREMENTING_DAMAGE_BY_ROUNDS = "incrementingDamageByRounds";
    private static final String FIELD_INCREMENTING_END_ROUND = "incrementingEndRound";
    private static final String FIELD_INDEX_APPARITION = "indexApparition";
    private static final String FIELD_INDEX_PERIOD = "indexPeriod";
    private static final String FIELD_INDEX_PERIOD_FISHING = "indexPeriodFishing";
    private static final String FIELD_INDEX_STEP = "indexStep";
    private static final String FIELD_INFLICTED_RATE_HP_TARGET = "inflictedRateHpTarget";
    private static final String FIELD_INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING = "inflictingDamageInsteadOfSuffering";
    private static final String FIELD_INVENTORY = "inventory";
    private static final String FIELD_INVOKED_MOVE_TERRAIN = "invokedMoveTerrain";
    private static final String FIELD_INVOKING_ALLY_MOVE = "invokingAllyMove";
    private static final String FIELD_INVOKING_MOVE_BUT_USER = "invokingMoveButUser";
    private static final String FIELD_INVOKING_MOVE_BY_USER_TYPES = "invokingMoveByUserTypes";
    private static final String FIELD_INVOKING_SUFFERED_MOVE = "invokingSufferedMove";
    private static final String FIELD_INVOKING_TARGET_CHOSEN_MOVE = "invokingTargetChosenMove";
    private static final String FIELD_INVOKING_TARGET_SUCCESFUL_MOVE = "invokingTargetSuccesfulMove";
    private static final String FIELD_INVOKING_USER_MOVE_WHILE_SLEEP = "invokingUserMoveWhileSleep";
    private static final String FIELD_ITEM = "item";
    private static final String FIELD_ITEMS = "items";
    private static final String FIELD_IV_FOE = "ivFoe";
    private static final String FIELD_IV_PLAYER = "ivPlayer";
    private static final String FIELD_KEPT_MOVES = "keptMoves";
    private static final String FIELD_KO_USER_HEAL_SUBST = "koUserHealSubst";
    private static final String FIELD_LAST_ROM = "lastRom";
    private static final String FIELD_LAST_SAVED_GAME = "lastSavedGame";
    private static final String FIELD_LAST_SUCCESSFUL_MOVE = "lastSuccessfulMove";
    private static final String FIELD_LAST_SUFFERED_MOVE = "lastSufferedMove";
    private static final String FIELD_LAST_SUFFERED_MOVE_TYPES = "lastSufferedMoveTypes";
    private static final String FIELD_LAST_USED_ITEM = "lastUsedItem";
    private static final String FIELD_LAST_USED_MOVE = "lastUsedMove";
    private static final String FIELD_LAW = "law";
    private static final String FIELD_LAW_BOOST = "lawBoost";
    private static final String FIELD_LAW_FOR_ATTACK_FIRST = "lawForAttackFirst";
    private static final String FIELD_LAW_FOR_ENABLING_EFFECT = "lawForEnablingEffect";
    private static final String FIELD_LAW_FOR_FULL_HEAL_IF_MOVE = "lawForFullHealIfMove";
    private static final String FIELD_LAW_FOR_USING_A_MOVE = "lawForUsingAMove";
    private static final String FIELD_LAW_FOR_USING_A_MOVE_IF_FOE = "lawForUsingAMoveIfFoe";
    private static final String FIELD_LAW_FOR_USING_A_MOVE_NB_ROUND = "lawForUsingAMoveNbRound";
    private static final String FIELD_LAW_STATUS = "lawStatus";
    private static final String FIELD_LEFT_USER_HP = "leftUserHp";
    private static final String FIELD_LEGENDARY_PKS = "legendaryPks";
    private static final String FIELD_LEV_MOVES = "levMoves";
    private static final String FIELD_LEVEL = "level";
    private static final String FIELD_LEVELS = "levels";
    private static final String FIELD_LINKS_OTHER_LEVELS = "linksOtherLevels";
    private static final String FIELD_LINKS_WITH_CAVES = "linksWithCaves";
    private static final String FIELD_LINKS_WITH_OTHER_PLACES = "linksWithOtherPlaces";
    private static final String FIELD_LOAD_HOME_FOLDER = "loadHomeFolder";
    private static final String FIELD_LOAD_LAST_GAME = "loadLastGame";
    private static final String FIELD_LOAD_LAST_ROM = "loadLastRom";
    private static final String FIELD_LOCAL_FAIL_STATIS = "localFailStatis";
    private static final String FIELD_LOCAL_FAIL_STATUS = "localFailStatus";
    private static final String FIELD_LOCAL_FAIL_SWAP_BOOST_STATIS = "localFailSwapBoostStatis";
    private static final String FIELD_LOST_OBJECTS = "lostObjects";
    private static final String FIELD_LOW_STAT_FOE_HIT = "lowStatFoeHit";
    private static final String FIELD_MAX_HP_FOR_USING_BERRY = "maxHpForUsingBerry";
    private static final String FIELD_MAX_HP_HEALING_HP = "maxHpHealingHp";
    private static final String FIELD_MAX_HP_HEALING_HP_RATE = "maxHpHealingHpRate";
    private static final String FIELD_MAX_STATISTICS_IF_CH = "maxStatisticsIfCh";
    private static final String FIELD_MEMBERS = "members";
    private static final String FIELD_MINI_MAP = "miniMap";
    private static final String FIELD_MONEY = "money";
    private static final String FIELD_MOVE = "move";
    private static final String FIELD_MOVE_FCT_ENV = "moveFctEnv";
    private static final String FIELD_MOVE_OBJECT = "moveObject";
    private static final String FIELD_MOVE_TUTORS = "moveTutors";
    private static final String FIELD_MOVES = "moves";
    private static final String FIELD_MOVES_ABILITIES_EVOS = "movesAbilitiesEvos";
    private static final String FIELD_MOVES_ANTICIPATION = "movesAnticipation";
    private static final String FIELD_MOVES_NOT_TO_BE_COPIED = "movesNotToBeCopied";
    private static final String FIELD_MOVES_NOT_TO_BE_INVOKED = "movesNotToBeInvoked";
    private static final String FIELD_MOVES_TO_BE_LEARNT = "movesToBeLearnt";
    private static final String FIELD_MOVES_USED_BY_TARGETED_FIGHTERS = "movesUsedByTargetedFighters";
    private static final String FIELD_MULT = "mult";
    private static final String FIELD_MULT_ACCURACY = "multAccuracy";
    private static final String FIELD_MULT_ALLY_DAMAGE = "multAllyDamage";
    private static final String FIELD_MULT_DAMAGE = "multDamage";
    private static final String FIELD_MULT_DAMAGE_AGAINST = "multDamageAgainst";
    private static final String FIELD_MULT_DAMAGE_AGAINST_FOE = "multDamageAgainstFoe";
    private static final String FIELD_MULT_DAMAGE_CH = "multDamageCh";
    private static final String FIELD_MULT_DAMAGE_FOE = "multDamageFoe";
    private static final String FIELD_MULT_DAMAGE_PREPA_ROUND = "multDamagePrepaRound";
    private static final String FIELD_MULT_DAMAGE_STATUS = "multDamageStatus";
    private static final String FIELD_MULT_DAMAGE_TYPES_MOVES = "multDamageTypesMoves";
    private static final String FIELD_MULT_DRAINED_HP = "multDrainedHp";
    private static final String FIELD_MULT_EFFECT_LOVING_ALLY = "multEffectLovingAlly";
    private static final String FIELD_MULT_EVT_RATE_CH = "multEvtRateCh";
    private static final String FIELD_MULT_EVT_RATE_SEC_EFF = "multEvtRateSecEff";
    private static final String FIELD_MULT_EVT_RATE_SEC_EFFECT_OWNER = "multEvtRateSecEffectOwner";
    private static final String FIELD_MULT_FIGHT = "multFight";
    private static final String FIELD_MULT_FOES_DAMAGE = "multFoesDamage";
    private static final String FIELD_MULT_MOVE_POWER_FCT_TYPE = "multMovePowerFctType";
    private static final String FIELD_MULT_POWER = "multPower";
    private static final String FIELD_MULT_POWER_MOVES = "multPowerMoves";
    private static final String FIELD_MULT_POWER_MOVES_TYPES_GLOBAL = "multPowerMovesTypesGlobal";
    private static final String FIELD_MULT_STAB = "multStab";
    private static final String FIELD_MULT_STAT = "multStat";
    private static final String FIELD_MULT_STAT_ALLY = "multStatAlly";
    private static final String FIELD_MULT_STAT_IF_CAT = "multStatIfCat";
    private static final String FIELD_MULT_STAT_IF_CONTAINS_TYPE = "multStatIfContainsType";
    private static final String FIELD_MULT_STAT_IF_DAMAGE_CAT = "multStatIfDamageCat";
    private static final String FIELD_MULT_STAT_IF_DAMGE_TYPE = "multStatIfDamgeType";
    private static final String FIELD_MULT_STAT_IF_KO_FOE = "multStatIfKoFoe";
    private static final String FIELD_MULT_STAT_IF_LOW_STAT = "multStatIfLowStat";
    private static final String FIELD_MULT_STAT_IF_STATUT_RANK = "multStatIfStatutRank";
    private static final String FIELD_MULT_STAT_POKEMON_RANK = "multStatPokemonRank";
    private static final String FIELD_MULT_STAT_RANK = "multStatRank";
    private static final String FIELD_MULT_STATISTIC = "multStatistic";
    private static final String FIELD_MULT_STATISTIC_FOE = "multStatisticFoe";
    private static final String FIELD_MULT_SUFFERED_DAMAGE_SUPER_EFF = "multSufferedDamageSuperEff";
    private static final String FIELD_MULT_TRAPPING_DAMAGE = "multTrappingDamage";
    private static final String FIELD_MULT_VAR_BOOST = "multVarBoost";
    private static final String FIELD_MULT_WEIGHT = "multWeight";
    private static final String FIELD_MULT_WINNING_EV = "multWinningEv";
    private static final String FIELD_MULT_WINNING_EXP = "multWinningExp";
    private static final String FIELD_MULT_WINNING_HAPPINESS = "multWinningHappiness";
    private static final String FIELD_MULT_WINNING_MONEY = "multWinningMoney";
    private static final String FIELD_MULTIPLICITY_FIGHT = "multiplicityFight";
    private static final String FIELD_MUMY = "mumy";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_NAMES = "names";
    private static final String FIELD_NB_FLEE_ATTEMPT = "nbFleeAttempt";
    private static final String FIELD_NB_HITS = "nbHits";
    private static final String FIELD_NB_KO_PREVIOUS_ROUND = "nbKoPreviousRound";
    private static final String FIELD_NB_KO_ROUND = "nbKoRound";
    private static final String FIELD_NB_PREPA_ROUND = "nbPrepaRound";
    private static final String FIELD_NB_REPEATING_SUCCESSFUL_MOVES = "nbRepeatingSuccessfulMoves";
    private static final String FIELD_NB_ROUNDS = "nbRounds";
    private static final String FIELD_NB_STEPS = "nbSteps";
    private static final String FIELD_NB_STEPS_TEAM_LEAD = "nbStepsTeamLead";
    private static final String FIELD_NB_USED_PP = "nbUsedPp";
    private static final String FIELD_NB_USES_MOVES = "nbUsesMoves";
    private static final String FIELD_NB_USES_MOVES_ROUND = "nbUsesMovesRound";
    private static final String FIELD_NEEDING_TO_RECHARGE = "needingToRecharge";
    private static final String FIELD_NEXT_LEVEL_TARGET = "nextLevelTarget";
    private static final String FIELD_NICKNAME = "nickname";
    private static final String FIELD_NOT_TRANSLATED = "notTranslated";
    private static final String FIELD_NUMBER = "number";
    private static final String FIELD_PLACE = "place";
    private static final String FIELD_PLACES = "places";
    private static final String FIELD_PLATE = "plate";
    private static final String FIELD_PLAYER = "player";
    private static final String FIELD_PLAYER_COORDS = "playerCoords";
    private static final String FIELD_PLAYER_FIGHTERS_AGAINST_FOE = "playerFightersAgainstFoe";
    private static final String FIELD_PLAYER_MAX_NUMBER_FRONT_FIGHTERS = "playerMaxNumberFrontFighters";
    private static final String FIELD_PLAYER_ORIENTATION = "playerOrientation";
    private static final String FIELD_POINT_VIEW_CHANGEMENT = "pointViewChangement";
    private static final String FIELD_POKEMON = "pokemon";
    private static final String FIELD_POWER = "power";
    private static final String FIELD_PP = "pp";
    private static final String FIELD_PP_FOR_MOVES = "ppForMoves";
    private static final String FIELD_PREVENT_STATUS = "preventStatus";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_PRIORITY = "priority";
    private static final String FIELD_PRISE_EN_COMPTE_PK_LANCEUR = "priseEnComptePkLanceur";
    private static final String FIELD_PRIVATE_MOVES = "privateMoves";
    private static final String FIELD_PROT_SINGLE = "protSingle";
    private static final String FIELD_PROT_SINGLE_AGAINST_KO = "protSingleAgainstKo";
    private static final String FIELD_PROT_TEAM_AGAINST_DAMAGE_MOVES = "protTeamAgainstDamageMoves";
    private static final String FIELD_PROT_TEAM_AGAINST_MULT_TARGETS = "protTeamAgainstMultTargets";
    private static final String FIELD_PROT_TEAM_AGAINST_PRIO = "protTeamAgainstPrio";
    private static final String FIELD_PROT_TEAM_AGAINST_STATUS_MOVES = "protTeamAgainstStatusMoves";
    private static final String FIELD_PROTECT_AGAINST_CH = "protectAgainstCh";
    private static final String FIELD_PROTECT_AGAINST_KO = "protectAgainstKo";
    private static final String FIELD_PROTECT_AGAINST_KO_IF_FULL_HP = "protectAgainstKoIfFullHp";
    private static final String FIELD_PROTECT_AGAINST_LOW_STAT = "protectAgainstLowStat";
    private static final String FIELD_PROTECT_AGAINST_STATUS = "protectAgainstStatus";
    private static final String FIELD_PROTECT_FAIL = "protectFail";
    private static final String FIELD_PROTECTED_AGAINST_MOVE_TYPES = "protectedAgainstMoveTypes";
    private static final String FIELD_PT = "pt";
    private static final String FIELD_PUTTING_KO = "puttingKo";
    private static final String FIELD_RAND_MAX = "randMax";
    private static final String FIELD_RANDOM_WILD_FIGHT = "randomWildFight";
    private static final String FIELD_RANK_INCREMENT_NB_ROUND = "rankIncrementNbRound";
    private static final String FIELD_RANK_LEAGUE = "rankLeague";
    private static final String FIELD_RATE_DAMAGE = "rateDamage";
    private static final String FIELD_RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS = "rateDamageFunctionOfNbRounds";
    private static final String FIELD_RATE_HP = "rateHp";
    private static final String FIELD_RATE_INVOKATION_MOVE = "rateInvokationMove";
    private static final String FIELD_RATE_LOOSE_MONEY_WIN = "rateLooseMoneyWin";
    private static final String FIELD_RATE_WIN_MONEY_BASE = "rateWinMoneyBase";
    private static final String FIELD_RATE_WINNING_EXP_PTS_FIGHT = "rateWinningExpPtsFight";
    private static final String FIELD_RECHARGE_ROUND = "rechargeRound";
    private static final String FIELD_RECOIL_DAMAGE = "recoilDamage";
    private static final String FIELD_RECOIL_DAMAGE_FOE = "recoilDamageFoe";
    private static final String FIELD_RECOIL_DAMAGE_FOE_BY_KO_OWNER = "recoilDamageFoeByKoOwner";
    private static final String FIELD_REMAINING_HP = "remainingHp";
    private static final String FIELD_REMAINING_REPEL_STEPS = "remainingRepelSteps";
    private static final String FIELD_REPEAT_ROUND_LAW = "repeatRoundLaw";
    private static final String FIELD_REPEATED_ROUNDS_LAW = "repeatedRoundsLaw";
    private static final String FIELD_REPELLING_WILD_PK = "repellingWildPk";
    private static final String FIELD_REPLACING_TYPES = "replacingTypes";
    private static final String FIELD_REQUIRED_STATUS = "requiredStatus";
    private static final String FIELD_REQUIRED_SUCCESSFUL_EFFECTS = "requiredSuccessfulEffects";
    private static final String FIELD_RESTORED_HP = "restoredHp";
    private static final String FIELD_RESTORED_HP_RATE_LOVED_ALLY = "restoredHpRateLovedAlly";
    private static final String FIELD_RESTORED_MOVES_END_FIGHT = "restoredMovesEndFight";
    private static final String FIELD_REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL = "reverseEffectsPowerMovesTypesGlobal";
    private static final String FIELD_REVERSE_ORDER_OF_SORT_BY_SPEED = "reverseOrderOfSortBySpeed";
    private static final String FIELD_REWARD = "reward";
    private static final String FIELD_ROOMS = "rooms";
    private static final String FIELD_SAVE_GAME_AT_EXIT = "saveGameAtExit";
    private static final String FIELD_SAVE_HOME_FOLDER = "saveHomeFolder";
    private static final String FIELD_SAVEDLINKS = "savedlinks";
    private static final String FIELD_SCREEN_HEIGHT = "screenHeight";
    private static final String FIELD_SCREEN_WIDTH = "screenWidth";
    private static final String FIELD_SEC_EFFECT_IF_NO_DAMAGE = "secEffectIfNoDamage";
    private static final String FIELD_SEC_EFFECTS_BY_ITEM = "secEffectsByItem";
    private static final String FIELD_SECOND_POKEMON = "secondPokemon";
    private static final String FIELD_SELL = "sell";
    private static final String FIELD_SEX = "sex";
    private static final String FIELD_SIDE_LENGTH = "sideLength";
    private static final String FIELD_SINGLE_STATUS = "singleStatus";
    private static final String FIELD_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL = "skipLearningMovesWhileNotGrowingLevel";
    private static final String FIELD_SLOWING = "slowing";
    private static final String FIELD_SPACE_BETWEEN_LEFT_AND_HEROS = "spaceBetweenLeftAndHeros";
    private static final String FIELD_SPACE_BETWEEN_TOP_AND_HEROS = "spaceBetweenTopAndHeros";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_STATIS_ATT = "statisAtt";
    private static final String FIELD_STATIS_BASE = "statisBase";
    private static final String FIELD_STATIS_BOOST = "statisBoost";
    private static final String FIELD_STATIS_DEF = "statisDef";
    private static final String FIELD_STATIS_VAR_RANK = "statisVarRank";
    private static final String FIELD_STATISTICS = "statistics";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_STATUS_BY_NB_USES = "statusByNbUses";
    private static final String FIELD_STATUS_FROM_USER = "statusFromUser";
    private static final String FIELD_STATUS_RELAT = "statusRelat";
    private static final String FIELD_STATUS_TYPE = "statusType";
    private static final String FIELD_STEPS = "steps";
    private static final String FIELD_STILL_ENABLED_MOVES = "stillEnabledMoves";
    private static final String FIELD_STILL_POSSIBLE_FLEE = "stillPossibleFlee";
    private static final String FIELD_STONE = "stone";
    private static final String FIELD_STOPPABLE_MOVE_KO_SINGLE = "stoppableMoveKoSingle";
    private static final String FIELD_STOPPABLE_MOVE_MULTI = "stoppableMoveMulti";
    private static final String FIELD_STOPPABLE_MOVE_PRIO = "stoppableMovePrio";
    private static final String FIELD_STOPPABLE_MOVE_SOLO = "stoppableMoveSolo";
    private static final String FIELD_STORAGE_COORDS = "storageCoords";
    private static final String FIELD_SUBSTITUTE = "substitute";
    private static final String FIELD_SUCCESSFUL_MOVE = "successfulMove";
    private static final String FIELD_SUCCESSFUL_MOVES_ROUND = "successfulMovesRound";
    private static final String FIELD_SUFFERING_DAMAGE_DIRECT_MOVE = "sufferingDamageDirectMove";
    private static final String FIELD_SUFFERING_DAMAGE_TYPES = "sufferingDamageTypes";
    private static final String FIELD_SUMMING_USER_TEAM_OK_FIGHTER = "summingUserTeamOkFighter";
    private static final String FIELD_SWAP_BOOST_STATIS = "swapBoostStatis";
    private static final String FIELD_SWITCH_FORCE_ADV_SI_TOUCHE = "switchForceAdvSiTouche";
    private static final String FIELD_SWITCH_POSSIBLE_SI_TOUCHE = "switchPossibleSiTouche";
    private static final String FIELD_SWITCH_TYPE = "switchType";
    private static final String FIELD_SYNCHRO_STATUS = "synchroStatus";
    private static final String FIELD_TAKE_ITEM_BY_DAMAGING_MOVE = "takeItemByDamagingMove";
    private static final String FIELD_TAKEN_OBJECTS = "takenObjects";
    private static final String FIELD_TAKEN_POKEMON = "takenPokemon";
    private static final String FIELD_TARGET_ATTACKS_LAST = "targetAttacksLast";
    private static final String FIELD_TARGET_CHOICE = "targetChoice";
    private static final String FIELD_TARGET_DEFENSE = "targetDefense";
    private static final String FIELD_TEAM = "team";
    private static final String FIELD_TEAM_MOVE = "teamMove";
    private static final String FIELD_TEAMS = "teams";
    private static final String FIELD_TEAMS_REWARDS = "teamsRewards";
    private static final String FIELD_TECHNICAL_MOVES = "technicalMoves";
    private static final String FIELD_THIEVABLE_MOVE = "thievableMove";
    private static final String FIELD_THIEVED_HP_RATE_TARGET_TO_USER = "thievedHpRateTargetToUser";
    private static final String FIELD_TILE_FILE_NAME = "tileFileName";
    private static final String FIELD_TM = "tm";
    private static final String FIELD_TRACKING_MOVES = "trackingMoves";
    private static final String FIELD_TRAINER = "trainer";
    private static final String FIELD_TRAINER_COORDS = "trainerCoords";
    private static final String FIELD_TRANSFERT_OBJ_SI_CONTACT = "transfertObjSiContact";
    private static final String FIELD_TRAPPING_MOVES = "trappingMoves";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_TYPE_FOR_MOVES = "typeForMoves";
    private static final String FIELD_TYPES = "types";
    private static final String FIELD_TYPES_BY_OWNED_ITEM = "typesByOwnedItem";
    private static final String FIELD_TYPES_BY_WEATHER = "typesByWeather";
    private static final String FIELD_TYPES_PK = "typesPk";
    private static final String FIELD_UNLOCKED_CITY = "unlockedCity";
    private static final String FIELD_UNUSABLE_ITEM = "unusableItem";
    private static final String FIELD_UNUSABLE_MOVES = "unusableMoves";
    private static final String FIELD_USED_BALL_CATCHING = "usedBallCatching";
    private static final String FIELD_USED_ITEMS_WHILE_ROUND = "usedItemsWhileRound";
    private static final String FIELD_USED_MOVE_LAST_ROUND = "usedMoveLastRound";
    private static final String FIELD_USER_ATTACK = "userAttack";
    private static final String FIELD_USER_STATUS_END_ROUND = "userStatusEndRound";
    private static final String FIELD_USING_ITEM = "usingItem";
    private static final String FIELD_VISITED_PLACES = "visitedPlaces";
    private static final String FIELD_WEATHER = "weather";
    private static final String FIELD_WEDDING_ALLY = "weddingAlly";
    private static final String FIELD_WEIGHT = "weight";
    private static final String FIELD_WIDTH = "width";
    private static final String FIELD_WILD_POKEMON = "wildPokemon";
    private static final String FIELD_WILD_POKEMON_AREAS = "wildPokemonAreas";
    private static final String FIELD_WILD_POKEMON_FISHING = "wildPokemonFishing";
    private static final String FIELD_WIN_EV_FIGHT = "winEvFight";
    private static final String FIELD_WIN_PP = "winPp";
    private static final String FIELD_WIN_TRAINER_EXP = "winTrainerExp";
    private static final String FIELD_WINNING_MONEY = "winningMoney";
    private static final String FIELD_WINNING_RATE_BY_SUM_TARGET_USER = "winningRateBySumTargetUser";
    private static final String FIELD_WITHOUT_FAIL = "withoutFail";
    private static final String FIELD_WON_EXP = "wonExp";
    private static final String FIELD_WON_EXP_SINCE_LAST_LEVEL = "wonExpSinceLastLevel";
    private static final String FIELD_ZIPPED_ROM = "zippedRom";
    private static final String TYPE_COMBOS = "Combos";
    private static final String TYPE_ABILITY_DATA = "AbilityData";
    private static final String TYPE_EFFECT_WHILE_SENDING = "EffectWhileSending";
    private static final String TYPE_EFFECT_WHILE_SENDING_SIMPLE = "EffectWhileSending";
    private static final String TYPE_EFFECT_WHILE_SENDING_WITH_STATISTIC = "EffectWhileSendingWithStatistic";
    private static final String TYPE_STATISTIC = "Statistic";
    private static final String TYPE_BALL = "Ball";
    private static final String TYPE_BERRY = "Berry";
    private static final String TYPE_BOOST = "Boost";
    private static final String TYPE_EVOLVING_ITEM = "EvolvingItem";
    private static final String TYPE_EVOLVING_STONE = "EvolvingStone";
    private static final String TYPE_FOSSIL = "Fossil";
    private static final String TYPE_HEALING_HP = "HealingHp";
    private static final String TYPE_HEALING_HP_STATUS = "HealingHpStatus";
    private static final String TYPE_HEALING_PP = "HealingPp";
    private static final String TYPE_HEALING_SIMPLE_ITEM = "HealingItem";
    private static final String TYPE_HEALING_SIMPLE_STATUS = "HealingStatus";
    private static final String TYPE_ITEM = "Item";
    private static final String TYPE_ITEM_FOR_BATTLE = "ItemForBattle";
    private static final String TYPE_REPEL = "Repel";
    private static final String TYPE_SELLING_ITEM = "SellingItem";
    private static final String TYPE_DAMAGING_MOVE_DATA = "DamagingMoveData";
    private static final String TYPE_MOVE_DATA = "MoveData";
    private static final String TYPE_STATUS_MOVE_DATA = "StatusMoveData";
    private static final String TYPE_EFFECT = "Effect";
    private static final String TYPE_EFFECT_ACCURACY = "EffectAccuracy";
    private static final String TYPE_EFFECT_ALLY = "EffectAlly";
    private static final String TYPE_EFFECT_BATON_PASS = "EffectBatonPass";
    private static final String TYPE_EFFECT_CLONE = "EffectClone";
    private static final String TYPE_EFFECT_COMBO = "EffectCombo";
    private static final String TYPE_EFFECT_COMMON_STATISTICS = "EffectCommonStatistics";
    private static final String TYPE_EFFECT_COPY_FIGHTER = "EffectCopyFighter";
    private static final String TYPE_EFFECT_COPY_MOVE = "EffectCopyMove";
    private static final String TYPE_EFFECT_COUNTER_ATTACK = "EffectCounterAttack";
    private static final String TYPE_EFFECT_DAMAGE = "EffectDamage";
    private static final String TYPE_EFFECT_DAMAGE_RATE = "EffectDamageRate";
    private static final String TYPE_EFFECT_END_ROUND = "EffectEndRound";
    private static final String TYPE_EFFECT_END_ROUND_FOE = "EffectEndRoundFoe";
    private static final String TYPE_EFFECT_END_ROUND_GLOBAL = "EffectEndRoundGlobal";
    private static final String TYPE_EFFECT_END_ROUND_INDIVIDUAL = "EffectEndRoundIndividual";
    private static final String TYPE_EFFECT_END_ROUND_MULTI_RELATION = "EffectEndRoundMultiRelation";
    private static final String TYPE_EFFECT_END_ROUND_POSITION_RELATION = "EffectEndRoundPositionRelation";
    private static final String TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION = "EffectEndRoundPositionTargetRelation";
    private static final String TYPE_EFFECT_END_ROUND_SINGLE_RELATION = "EffectEndRoundSingleRelation";
    private static final String TYPE_EFFECT_END_ROUND_SINGLE_STATUS = "EffectEndRoundSingleStatus";
    private static final String TYPE_EFFECT_END_ROUND_STATUS = "EffectEndRoundStatus";
    private static final String TYPE_EFFECT_END_ROUND_STATUS_RELATION = "EffectEndRoundStatusRelation";
    private static final String TYPE_EFFECT_END_ROUND_TEAM = "EffectEndRoundTeam";
    private static final String TYPE_EFFECT_FULL_HP_RATE = "EffectFullHpRate";
    private static final String TYPE_EFFECT_GLOBAL = "EffectGlobal";
    private static final String TYPE_EFFECT_INVOKE = "EffectInvoke";
    private static final String TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER = "EffectMultSufferedMovePower";
    private static final String TYPE_EFFECT_MULT_USED_MOVE_POWER = "EffectMultUsedMovePower";
    private static final String TYPE_EFFECT_ORDER = "EffectOrder";
    private static final String TYPE_EFFECT_PROTECT_FROM_TYPES = "EffectProtectFromTypes";
    private static final String TYPE_EFFECT_PROTECTION = "EffectProtection";
    private static final String TYPE_EFFECT_REMAINED_HP_RATE = "EffectRemainedHpRate";
    private static final String TYPE_EFFECT_RESTRICTION = "EffectRestriction";
    private static final String TYPE_EFFECT_STATISTIC = "EffectStatistic";
    private static final String TYPE_EFFECT_STATUS = "EffectStatus";
    private static final String TYPE_EFFECT_SWITCH_ABILITIES = "EffectSwitchAbilities";
    private static final String TYPE_EFFECT_SWITCH_ITEMS = "EffectSwitchItems";
    private static final String TYPE_EFFECT_SWITCH_MOVE_TYPES = "EffectSwitchMoveTypes";
    private static final String TYPE_EFFECT_SWITCH_POINT_VIEW = "EffectSwitchPointView";
    private static final String TYPE_EFFECT_SWITCH_POSITION = "EffectSwitchPosition";
    private static final String TYPE_EFFECT_SWITCH_TYPES = "EffectSwitchTypes";
    private static final String TYPE_EFFECT_TEAM = "EffectTeam";
    private static final String TYPE_EFFECT_TEAM_WHILE_SEND_FOE = "EffectTeamWhileSendFoe";
    private static final String TYPE_EFFECT_UNPROTECT_FROM_TYPES = "EffectUnprotectFromTypes";
    private static final String TYPE_EFFECT_VAR_P_P = "EffectVarPP";
    private static final String TYPE_EFFECT_WIN_MONEY = "EffectWinMoney";
    private static final String TYPE_CONST_VALUES_TYPE = "ConstValuesType";
    private static final String TYPE_EXCHANGE_TYPE = "ExchangeType";
    private static final String TYPE_MOVE_CHOICE_RESTRICTION_TYPE = "MoveChoiceRestrictionType";
    private static final String TYPE_MOVE_ITEM_TYPE = "MoveItemType";
    private static final String TYPE_POINT_VIEW_CHANGEMENT_TYPE = "PointViewChangementType";
    private static final String TYPE_SWITCH_TYPE = "SwitchType";
    private static final String TYPE_TARGET_CHOICE = "TargetChoice";
    private static final String TYPE_POKEMON_DATA = "PokemonData";
    private static final String TYPE_EXP_TYPE = "ExpType";
    private static final String TYPE_GENDER_REPARTITION = "GenderRepartition";
    private static final String TYPE_EVOLUTION = "Evolution";
    private static final String TYPE_EVOLUTION_HAPPINESS = "EvolutionHappiness";
    private static final String TYPE_EVOLUTION_ITEM = "EvolutionItem";
    private static final String TYPE_EVOLUTION_LEVEL_GENDER = "EvolutionLevelGender";
    private static final String TYPE_EVOLUTION_LEVEL_SIMPLE = "EvolutionLevel";
    private static final String TYPE_EVOLUTION_MOVE = "EvolutionMove";
    private static final String TYPE_EVOLUTION_MOVE_TYPE = "EvolutionMoveType";
    private static final String TYPE_EVOLUTION_STONE_GENDER = "EvolutionStoneGender";
    private static final String TYPE_EVOLUTION_STONE_SIMPLE = "EvolutionStone";
    private static final String TYPE_EVOLUTION_TEAM = "EvolutionTeam";
    private static final String TYPE_STATUS = "Status";
    private static final String TYPE_STATUS_BEGIN_ROUND_AUTO_DAMAGE = "StatusBeginRoundAutoDamage";
    private static final String TYPE_STATUS_BEGIN_ROUND_SIMPLE = "StatusBeginRound";
    private static final String TYPE_STATUS_SIMPLE = "Status";
    private static final String TYPE_STATUS_TYPE = "StatusType";
    private static final String TYPE_EFFECT_PARTNER_STATUS = "EffectPartnerStatus";
    private static final String TYPE_BOOST_HP_RATE = "BoostHpRate";
    private static final String TYPE_CATEGORY_MULT = "CategoryMult";
    private static final String TYPE_EFFICIENCY_RATE = "EfficiencyRate";
    private static final String TYPE_LEVEL_MOVE = "LevelMove";
    private static final String TYPE_STAT_BASE_EV = "StatBaseEv";
    private static final String TYPE_STATISTIC_CATEGORY = "StatisticCategory";
    private static final String TYPE_STATISTIC_POKEMON = "StatisticPokemon";
    private static final String TYPE_STATISTIC_STATUS = "StatisticStatus";
    private static final String TYPE_STATISTIC_TYPE = "StatisticType";
    private static final String TYPE_TYPE_DAMAGE_BOOST = "TypeDamageBoost";
    private static final String TYPE_TYPES_DUO = "TypesDuo";
    private static final String TYPE_WEATHER_TYPE = "WeatherType";
    private static final String TYPE_GAME = "Game";
    private static final String TYPE_HOST_POKEMON_DUO = "HostPokemonDuo";
    private static final String TYPE_NB_FIGHT_COORDS = "NbFightCoords";
    private static final String TYPE_USES_OF_MOVE = "UsesOfMove";
    private static final String TYPE_ACTIVITY_OF_MOVE = "ActivityOfMove";
    private static final String TYPE_ANTICIPATION = "Anticipation";
    private static final String TYPE_CHOICE_OF_EVOLUTION_AND_MOVES = "ChoiceOfEvolutionAndMoves";
    private static final String TYPE_FIGHT = "Fight";
    private static final String TYPE_FIGHTER = "Fighter";
    private static final String TYPE_MOVE_TEAM_POSITION = "MoveTeamPosition";
    private static final String TYPE_STACKS_OF_USES = "StacksOfUses";
    private static final String TYPE_TARGET_COORDS = "TargetCoords";
    private static final String TYPE_TEAM = "Team";
    private static final String TYPE_TEAM_POSITION = "TeamPosition";
    private static final String TYPE_ABSTRACT_ACTION = "AbstractAction";
    private static final String TYPE_ACTION = "Action";
    private static final String TYPE_ACTION_HEAL_MOVE = "ActionHealMove";
    private static final String TYPE_ACTION_MOVE = "ActionMove";
    private static final String TYPE_ACTION_SIMPLE_HEAL = "ActionSimpleHeal";
    private static final String TYPE_ACTION_SWITCH = "ActionSwitch";
    private static final String TYPE_FIGHT_STATE = "FightState";
    private static final String TYPE_FIGHT_TYPE = "FightType";
    private static final String TYPE_AFFECTED_MOVE = "AffectedMove";
    private static final String TYPE_COPIED_MOVE = "CopiedMove";
    private static final String TYPE_MOVE_TARGET = "MoveTarget";
    private static final String TYPE_MOVES_ABILITIES = "MovesAbilities";
    private static final String TYPE_DIFFICULTY = "Difficulty";
    private static final String TYPE_LOADING_GAME = "LoadingGame";
    private static final String TYPE_DIFFICULTY_MODEL_LAW = "DifficultyModelLaw";
    private static final String TYPE_DIFFICULTY_WIN_POINTS_FIGHT = "DifficultyWinPointsFight";
    private static final String TYPE_INVENTORY = "Inventory";
    private static final String TYPE_PLAYER = "Player";
    private static final String TYPE_SEX = "Sex";
    private static final String TYPE_DATA_MAP = "DataMap";
    private static final String TYPE_BUILDING = "Building";
    private static final String TYPE_GYM = "Gym";
    private static final String TYPE_POKEMON_CENTER = "PokemonCenter";
    private static final String TYPE_ALLY = "Ally";
    private static final String TYPE_CHARACTER_IN_ROAD_CAVE = "CharacterInRoadCave";
    private static final String TYPE_DEALER_ITEM = "DealerItem";
    private static final String TYPE_DUAL_FIGHT = "DualFight";
    private static final String TYPE_GERANT_POKEMON = "GerantPokemon";
    private static final String TYPE_GYM_LEADER = "GymLeader";
    private static final String TYPE_GYM_TRAINER = "GymTrainer";
    private static final String TYPE_HEALER = "Healer";
    private static final String TYPE_PERSON = "Person";
    private static final String TYPE_SELLER = "Seller";
    private static final String TYPE_TEMP_TRAINER = "TempTrainer";
    private static final String TYPE_TRAINER_LEAGUE = "TrainerLeague";
    private static final String TYPE_TRAINER_MULTI_FIGHTS = "TrainerMultiFights";
    private static final String TYPE_GERANCE_TYPE = "GeranceType";
    private static final String TYPE_SELL_TYPE = "SellType";
    private static final String TYPE_DIRECTION = "Direction";
    private static final String TYPE_AREA_APPARITION = "AreaApparition";
    private static final String TYPE_BLOCK = "Block";
    private static final String TYPE_LEVEL_CAVE = "LevelCave";
    private static final String TYPE_LEVEL_INDOOR_GYM = "LevelIndoorGym";
    private static final String TYPE_LEVEL_INDOOR_POKEMON_CENTER = "LevelIndoorPokemonCenter";
    private static final String TYPE_LEVEL_LEAGUE = "LevelLeague";
    private static final String TYPE_LEVEL_OUTDOOR = "LevelOutdoor";
    private static final String TYPE_LEVEL_ROAD = "LevelRoad";
    private static final String TYPE_LINK = "Link";
    private static final String TYPE_ENVIRONMENT_TYPE = "EnvironmentType";
    private static final String TYPE_CAVE = "Cave";
    private static final String TYPE_CITY = "City";
    private static final String TYPE_LEAGUE = "League";
    private static final String TYPE_PLACE = "Place";
    private static final String TYPE_ROAD = "Road";
    private static final String TYPE_EGG = "Egg";
    private static final String TYPE_PK_TRAINER = "PkTrainer";
    private static final String TYPE_POKEMON_PLAYER = "PokemonPlayer";
    private static final String TYPE_POKEMON_TEAM = "PokemonTeam";
    private static final String TYPE_USABLE_POKEMON = "UsablePokemon";
    private static final String TYPE_WILD_PK = "WildPk";
    private static final String TYPE_GENDER = "Gender";
    private static final String TYPE_MINI_MAP_COORDS = "MiniMapCoords";
    private static final String TYPE_PLACE_INTER_CONNECT = "PlaceInterConnect";
    private static final String TYPE_TILE_MINI_MAP = "TileMiniMap";
    private static final String TYPE_COORDS = "Coords";
    private static final String TYPE_LEVEL_POINT = "LevelPoint";
    private static final String TYPE_POINT = "Point";

    private static final String TYPE_MAP = "m";
    private static final String TYPE_LIST = "l";
    private static final String TYPE_MONTE_CARLO = "c";


    public StringMap<String> getTextFiles(DataBase _d) {
        StringMap<String> files_ = new StringMap<String>();
        for (String n : _d.getPokedex().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setPokemonData(_d.getPokedex()
                    .getVal(n));
            files_.put(StringUtil.concat(POKEDEX_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : _d.getMoves().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setMoveData(_d.getMoves()
                    .getVal(n));
            files_.put(StringUtil.concat(MOVES_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : _d.getItems().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setItem(_d.getItems().getVal(n));
            files_.put(StringUtil.concat(ITEMS_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : _d.getAbilities().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setAbilityData(_d.getAbilities()
                    .getVal(n));
            files_.put(StringUtil.concat(ABILITIES_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : _d.getStatus().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setStatus(_d.getStatus()
                    .getVal(n));
            files_.put(StringUtil.concat(STATUS_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        String file_ = DocumentWriterAikiCoreUtil.setCombos(_d.getCombos());
        files_.put(COMBOS, file_);
        file_ = DocumentWriterAikiCoreUtil.setDataMap(_d.getMap());
        files_.put(MAP_FILE, file_);
        StringList lines_ = new StringList();
        for (String s : _d.getConstNum().getKeys()) {
            lines_.add(StringUtil.concat(s, TAB, _d.getConstNum().getVal(s)
                    .toNumberString()));
        }
        files_.put(CONST_NUM, StringUtil.join(lines_, RETURN_LINE));
        lines_ = new StringList();
        for (String s : _d.getTypesColors().getKeys()) {
            lines_.add(StringUtil.concat(s, TAB, _d.getTypesColors().getVal(s)));
        }
        files_.put(StringUtil.concat(TYPES_COLOR_CODE, IMG_FILES_RES_EXT_TXT),
                StringUtil.join(lines_, RETURN_LINE));
        lines_ = new StringList();
        lines_.add(StringUtil.concat(DEF_MOVE, TAB, _d.getDefMove()));
        lines_.add(StringUtil.concat(RATE_BOOST, TAB, _d.getRateBoost()));
        lines_.add(StringUtil.concat(RATE_BOOST_CRITICAL_HIT, TAB,
                _d.getRateBoostCriticalHit()));
        lines_.add(StringUtil.concat(RATE_FLEEING, TAB, _d.getRateFleeing()));
        lines_.add(StringUtil.concat(RATE_CATCHING, TAB, _d.getRateCatching()));
        lines_.add(StringUtil.concat(BALL_DEF, TAB, _d.getBallDef()));
        lines_.add(StringUtil.concat(DEFAULT_EGG_GROUP, TAB, _d.getDefaultEggGroup()));
        lines_.add(StringUtil.concat(DAMAGE_FORMULA, TAB, _d.getDamageFormula()));

        files_.put(CONST_NOT_NUM, StringUtil.join(lines_, RETURN_LINE));
        StringList types_ = new StringList();
        for (TypesDuo p : _d.getTableTypes().getKeys()) {
            types_.add(p.getDamageType());
        }
        types_.removeDuplicates();
        String output_ = StringUtil.concat(TAB, StringUtil.join(types_, TAB));
        for (String pkType_ : types_) {
            output_ = StringUtil.concat(output_, RETURN_LINE, pkType_);
            for (String damageType_ : types_) {
                output_ = StringUtil.concat(output_, TAB,
                        _d.getTableTypes().getVal(new TypesDuo(damageType_, pkType_))
                                .toNumberString());
            }
        }
        files_.put(TABLE_TYPES, output_);
        StringList linesCourbes_ = new StringList();
        for (ExpType c : _d.getExpGrowth().getKeys()) {
            linesCourbes_.add(StringUtil.concat(c.name(), TAB,
                    _d.getExpGrowth().getVal(c)));
        }
        files_.put(COURBE_PTS_EXP, StringUtil.join(linesCourbes_, RETURN_LINE));
        StringList rates_ = new StringList();
        for (DifficultyWinPointsFight c : _d.getRates().getKeys()) {
            rates_.add(StringUtil.concat(c.name(), TAB, _d.getRates().getVal(c)));
        }
        files_.put(RATE_WON_POINTS, StringUtil.join(rates_, RETURN_LINE));
        StringList linesLaws_ = new StringList();
        for (DifficultyModelLaw k : _d.getLawsDamageRate().getKeys()) {
            LawNumber value_ = _d.getLawsDamageRate().getVal(k);
            StringList lawValues_ = new StringList();
            for (Rate event_ : value_.getLaw().events()) {
                lawValues_.add(StringUtil.concat(event_.toNumberString(),
                        SEPARATOR_RAND_EVENTS, value_.getLaw().rate(event_)
                                .toNumberString()));
            }
            linesLaws_.add(StringUtil.concat(k.name(), TAB,
                    StringUtil.join(lawValues_, SEPARATOR_RAND), TAB,
                    Long.toString(value_.getNumber())));
        }
        files_.put(LOIS_RANDOM, StringUtil.join(linesLaws_, RETURN_LINE));
        StringList linesTmHm_ = new StringList();
        for (short k : _d.getHm().getKeys()) {
            linesTmHm_.add(StringUtil.concat(CS, Long.toString(k), TAB,
                    _d.getHm().getVal(k)));
        }
        for (short k : _d.getTm().getKeys()) {
            linesTmHm_.add(StringUtil.concat(CT, Long.toString(k), TAB,
                    _d.getTm().getVal(k), TAB, _d.getTmPrice().getVal(k).toNumberString()));
        }
        files_.put(CT_CS_FILE, StringUtil.join(linesTmHm_, RETURN_LINE));
        for (String l : _d.getTranslatedCategories().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> genders_ = _d.getTranslatedCategories().getVal(l);
            for (String g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_CATEGORIES);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedGenders().getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<Gender, String> genders_ = _d.getTranslatedGenders().getVal(l);
            for (Gender g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_GENDERS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedBooleans().getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<SelectedBoolean, String> genders_ = _d.getTranslatedBooleans()
                    .getVal(l);
            for (SelectedBoolean g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_BOOLEANS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedDiffWinPts().getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<DifficultyWinPointsFight, String> genders_ = _d.getTranslatedDiffWinPts()
                    .getVal(l);
            for (DifficultyWinPointsFight g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_DIFF_WIN_PTS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedDiffModelLaw().getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<DifficultyModelLaw, String> genders_ = _d.getTranslatedDiffModelLaw()
                    .getVal(l);
            for (DifficultyModelLaw g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil
                    .concat(fileName_, TRANSLATION_DIFF_MODEL_LAW);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedEnvironment().getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<EnvironmentType, String> statistics_ = _d.getTranslatedEnvironment()
                    .getVal(l);
            for (EnvironmentType g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_ENVIRONMENTS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedStatistics().getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<Statistic, String> statistics_ = _d.getTranslatedStatistics()
                    .getVal(l);
            for (Statistic g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_STATISTICS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedTargets().getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<TargetChoice, String> statistics_ = _d.getTranslatedTargets()
                    .getVal(l);
            for (TargetChoice g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_TARGETS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedTypes().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> statistics_ = _d.getTranslatedTypes().getVal(l);
            for (String g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_TYPES);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedPokemon().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> pokemon_ = _d.getTranslatedPokemon().getVal(l);
            for (String g : pokemon_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(pokemon_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_POKEMON);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedMoves().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> moves_ = _d.getTranslatedMoves().getVal(l);
            for (String g : moves_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(moves_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_MOVES);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedItems().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> items_ = _d.getTranslatedItems().getVal(l);
            for (String g : items_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(items_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_ITEMS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedAbilities().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> abilities_ = _d.getTranslatedAbilities().getVal(l);
            for (String g : abilities_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(abilities_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_ABILITIES);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedStatus().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = _d.getTranslatedStatus().getVal(l);
            for (String g : status_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_STATUS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedFctMath().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = _d.getTranslatedFctMath().getVal(l);
            for (String g : status_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_MATH);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedClassesDescriptions().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = _d.getTranslatedClassesDescriptions().getVal(l);
            for (String g : status_.getKeys()) {

                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_CLASSES);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getLitterals().getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = _d.getLitterals().getVal(l);
            for (String g : status_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, TRANSLATION_LITTERAL);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String n : _d.getAnimStatis().getKeys()) {
            files_.put(StringUtil.concat(ANIM_STATIS, SEPARATOR_FILES, n,
                    IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                    .getStringByImage(_d.getAnimStatis().getVal(n)));
        }
        for (String n : _d.getAnimStatus().getKeys()) {
            files_.put(StringUtil.concat(ANIM_STATUS, SEPARATOR_FILES, n,
                    IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                    .getStringByImage(_d.getAnimStatus().getVal(n)));
        }
        files_.put(ANIM_ABSORB,
                BaseSixtyFourUtil.getStringByImage(_d.getAnimAbsorb()));
        for (String n : _d.getImages().getKeys()) {
            files_.put(
                    StringUtil.concat(IMAGES_FOLDER, SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getImages().getVal(n)));
        }
        for (String n : _d.getMiniMap().getKeys()) {
            files_.put(
                    StringUtil.concat(MINI_MAP_FOLDER, SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getMiniMap().getVal(n)));
        }
        for (String n : _d.getLinks().getKeys()) {
            files_.put(StringUtil.concat(LINKS_FOLDER, SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getLinks().getVal(n)));
        }
        for (String n : _d.getPeople().getKeys()) {
            files_.put(
                    StringUtil.concat(PEOPLE_FOLDER, SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getPeople().getVal(n)));
        }
        StringList linesHeros_;
        linesHeros_ = new StringList();
        for (ImageHeroKey k : _d.getFrontHeros().getKeys()) {
            String image_ = BaseSixtyFourUtil.getStringByImage(_d.getFrontHeros()
                    .getVal(k));
            StringBuilder str_ = new StringBuilder();
            str_.append(k.getType().name());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getSex().name());
            str_.append(TAB);
            str_.append(image_);
            linesHeros_.add(str_.toString());
        }
        files_.put(
                StringUtil.concat(HERO_FOLDER, SEPARATOR_FILES, HERO_FRONT),
                StringUtil.join(linesHeros_, RETURN_LINE));
        linesHeros_.clear();
        for (ImageHeroKey k : _d.getBackHeros().getKeys()) {
            String image_ = BaseSixtyFourUtil.getStringByImage(_d.getBackHeros()
                    .getVal(k));
            StringBuilder str_ = new StringBuilder();
            str_.append(k.getType().name());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getSex().name());
            str_.append(TAB);
            str_.append(image_);
            linesHeros_.add(str_.toString());
        }
        files_.put(
                StringUtil.concat(HERO_FOLDER, SEPARATOR_FILES, HERO_BACK),
                StringUtil.join(linesHeros_, RETURN_LINE));
        linesHeros_.clear();
        for (ImageHeroKey k : _d.getOverWorldHeros().getKeys()) {
            String image_ = BaseSixtyFourUtil
                    .getStringByImage(_d.getOverWorldHeros().getVal(k));
            StringBuilder str_ = new StringBuilder();
            str_.append(k.getType().name());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getDirection().name());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getSex().name());
            str_.append(TAB);
            str_.append(image_);
            linesHeros_.add(str_.toString());
        }
        files_.put(
                StringUtil.concat(HERO_FOLDER, SEPARATOR_FILES, HERO_MINI),
                StringUtil.join(linesHeros_, RETURN_LINE));
        for (String n : _d.getTrainers().getKeys()) {
            files_.put(
                    StringUtil.concat(TRAINERS_FOLDER, SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getTrainers().getVal(n)));
        }
        for (String n : _d.getMaxiPkFront().getKeys()) {
            files_.put(StringUtil.concat(FRONT_IMAGES_FOLDER,
                    SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMaxiPkFront()
                            .getVal(n)));
        }
        for (String n : _d.getMaxiPkBack().getKeys()) {
            files_.put(
                    StringUtil.concat(BACK_IMAGES_FOLDER, SEPARATOR_FILES,
                            n, IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMaxiPkBack().getVal(n)));
        }
        for (String n : _d.getMiniPk().getKeys()) {
            files_.put(StringUtil.concat(MINI_IMAGES_FOLDER,
                    SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMiniPk().getVal(n)));
        }
        for (String n : _d.getMiniItems().getKeys()) {
            files_.put(StringUtil.concat(OBJECTS_IMAGES_FOLDER,
                    SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMiniItems().getVal(n)));
        }
        for (String n : _d.getTypesImages().getKeys()) {
            files_.put(StringUtil.concat(TYPES_IMAGES_FOLDER,
                    SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getTypesImages()
                            .getVal(n)));
        }
        files_.put(
                StringUtil.concat(IMAGE_TM_HM_FILES, IMG_FILES_RES_EXT_TXT),
                BaseSixtyFourUtil.getStringByImage(_d.getImageTmHm()));
        files_.put(StringUtil.concat(IMAGE_STORAGE_FILES,
                IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                .getStringByImage(_d.getStorage()));
        files_.put(
                StringUtil.concat(END_GAME_IMAGE, IMG_FILES_RES_EXT_TXT),
                BaseSixtyFourUtil.getStringByImage(_d.getEndGameImage()));
        return files_;
    }

    public static String setCombos(Combos _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setCombos(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setCombos(Combos _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_COMBOS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setCombos(_object,element_,_document);
        return element_;
    }

    private static void setCombos(Combos _object, Element _element, Document _document) {
        _element.appendChild(setMapStringListEffectCombo(_object.getEffects(),FIELD_EFFECTS,_document));
    }

    private static Element setAbilityData(AbilityData _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ABILITY_DATA);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAbilityData(_object,element_,_document);
        return element_;
    }

    public static String setAbilityData(AbilityData _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setAbilityData(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static void setAbilityData(AbilityData _object, Element _element, Document _document) {
        _element.appendChild(setListTypesDuo(_object.getBreakFoeImmune(),FIELD_BREAK_FOE_IMMUNE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isForbidUseBerryAgainstFoes(),FIELD_FORBID_USE_BERRY_AGAINST_FOES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getChgtTypeByWeather(),FIELD_CHGT_TYPE_BY_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isChgtTypeByDamage(),FIELD_CHGT_TYPE_BY_DAMAGE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRecoilDamageFoe(),FIELD_RECOIL_DAMAGE_FOE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getDecreaseNecStepsHatch(),FIELD_DECREASE_NEC_STEPS_HATCH,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getDivideStatusRound(),FIELD_DIVIDE_STATUS_ROUND,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getHealHpByWeather(),FIELD_HEAL_HP_BY_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getIgnAbility(),FIELD_IGN_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getIgnFoeTeamMove(),FIELD_IGN_FOE_TEAM_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isIgnFoeStatisBoost(),FIELD_IGN_FOE_STATIS_BOOST,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuMove(),FIELD_IMMU_MOVE,_document));
        _element.appendChild(setListStatistic(_object.getImmuLowStat(),FIELD_IMMU_LOW_STAT,_document));
        _element.appendChild(setListStatisticStatus(_object.getImmuLowStatIfStatus(),FIELD_IMMU_LOW_STAT_IF_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isImmuCh(),FIELD_IMMU_CH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuWeather(),FIELD_IMMU_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isImmuDamageTrappingMoves(),FIELD_IMMU_DAMAGE_TRAPPING_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isImmuDamageAllyMoves(),FIELD_IMMU_DAMAGE_ALLY_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isImmuDamageRecoil(),FIELD_IMMU_DAMAGE_RECOIL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuAbility(),FIELD_IMMU_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuStatusBeginRound(),FIELD_IMMU_STATUS_BEGIN_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isImmuRechargeRound(),FIELD_IMMU_RECHARGE_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapStringList(_object.getImmuStatus(),FIELD_IMMU_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSlowing(),FIELD_SLOWING,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultDamageFoe(),FIELD_MULT_DAMAGE_FOE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultDamageCh(),FIELD_MULT_DAMAGE_CH,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultAllyDamage(),FIELD_MULT_ALLY_DAMAGE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultSufferedDamageSuperEff(),FIELD_MULT_SUFFERED_DAMAGE_SUPER_EFF,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isImmuSufferedDamageLowEff(),FIELD_IMMU_SUFFERED_DAMAGE_LOW_EFF,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultEvtRateCh(),FIELD_MULT_EVT_RATE_CH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCancelSecEffectOther(),FIELD_CANCEL_SEC_EFFECT_OTHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCancelSecEffectOwner(),FIELD_CANCEL_SEC_EFFECT_OWNER,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultEvtRateSecEffectOwner(),FIELD_MULT_EVT_RATE_SEC_EFFECT_OWNER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getMultPower(),FIELD_MULT_POWER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getMultDamage(),FIELD_MULT_DAMAGE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultStab(),FIELD_MULT_STAB,_document));
        _element.appendChild(setMapStatisticByte(_object.getBonusStatRank(),FIELD_BONUS_STAT_RANK,_document));
        _element.appendChild(setMapStatisticByte(_object.getBoostStatRankProtected(),FIELD_BOOST_STAT_RANK_PROTECTED,_document));
        _element.appendChild(setMapStatisticByte(_object.getBoostStatRankEndRound(),FIELD_BOOST_STAT_RANK_END_ROUND,_document));
        _element.appendChild(setMapStatisticRate(_object.getMultStatAlly(),FIELD_MULT_STAT_ALLY,_document));
        _element.appendChild(setMapStatisticByte(_object.getMultStatIfKoFoe(),FIELD_MULT_STAT_IF_KO_FOE,_document));
        _element.appendChild(setMapStatisticByte(_object.getMultStatIfLowStat(),FIELD_MULT_STAT_IF_LOW_STAT,_document));
        _element.appendChild(setMapStatisticCategoryRate(_object.getMultStatIfCat(),FIELD_MULT_STAT_IF_CAT,_document));
        _element.appendChild(setMapStatisticStatusByte(_object.getMultStatIfStatutRank(),FIELD_MULT_STAT_IF_STATUT_RANK,_document));
        _element.appendChild(setMapStatisticCategoryByte(_object.getMultStatIfDamageCat(),FIELD_MULT_STAT_IF_DAMAGE_CAT,_document));
        _element.appendChild(setMapStatisticTypeByte(_object.getMultStatIfDamgeType(),FIELD_MULT_STAT_IF_DAMGE_TYPE,_document));
        _element.appendChild(setMapStatisticString(_object.getMultStat(),FIELD_MULT_STAT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isInflictingDamageInsteadOfSuffering(),FIELD_INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultVarBoost(),FIELD_MULT_VAR_BOOST,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbUsedPp(),FIELD_NB_USED_PP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isNbHits(),FIELD_NB_HITS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isBreakProtection(),FIELD_BREAK_PROTECTION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isPlate(),FIELD_PLATE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isHealedStatusBySwitch(),FIELD_HEALED_STATUS_BY_SWITCH,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealedHpRateBySwitch(),FIELD_HEALED_HP_RATE_BY_SWITCH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getIncreasedPrio(),FIELD_INCREASED_PRIO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getIncreasedPrioTypes(),FIELD_INCREASED_PRIO_TYPES,_document));
        _element.appendChild(setListStatistic(_object.getMaxStatisticsIfCh(),FIELD_MAX_STATISTICS_IF_CH,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloString(_object.getSingleStatus(),FIELD_SINGLE_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isAchievedDisappearedPk(),FIELD_ACHIEVED_DISAPPEARED_PK,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getForwardStatus(),FIELD_FORWARD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getFailStatus(),FIELD_FAIL_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getTypeForMoves(),FIELD_TYPE_FOR_MOVES,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMaxHpForUsingBerry(),FIELD_MAX_HP_FOR_USING_BERRY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isMumy(),FIELD_MUMY,_document));
        _element.appendChild(setMapWeatherTypeRate(_object.getHealHpByTypeIfWeather(),FIELD_HEAL_HP_BY_TYPE_IF_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapStringList(_object.getImmuMoveTypesByWeather(),FIELD_IMMU_MOVE_TYPES_BY_WEATHER,_document));
        _element.appendChild(setListEffectEndRound(_object.getEffectEndRound(),FIELD_EFFECT_END_ROUND,_document));
        _element.appendChild(setListEffectWhileSending(_object.getEffectSending(),FIELD_EFFECT_SENDING,_document));
        _element.appendChild(setStringMapTypeDamageBoost(_object.getChangingBoostTypes(),FIELD_CHANGING_BOOST_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuAllyFromMoves(),FIELD_IMMU_ALLY_FROM_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapStringList(_object.getImmuStatusTypes(),FIELD_IMMU_STATUS_TYPES,_document));
        _element.appendChild(setStringMapListStatistic(_object.getImmuLowStatisTypes(),FIELD_IMMU_LOW_STATIS_TYPES,_document));
        _element.appendChild(setMapStatisticByte(_object.getLowStatFoeHit(),FIELD_LOW_STAT_FOE_HIT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isCopyMovesTypes(),FIELD_COPY_MOVES_TYPES,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultPowerMovesTypesGlobal(),FIELD_MULT_POWER_MOVES_TYPES_GLOBAL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isReverseEffectsPowerMovesTypesGlobal(),FIELD_REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealHpWhileUsingBerry(),FIELD_HEAL_HP_WHILE_USING_BERRY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isTakeItemByDamagingMove(),FIELD_TAKE_ITEM_BY_DAMAGING_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isGiveItemToAllyHavingUsed(),FIELD_GIVE_ITEM_TO_ALLY_HAVING_USED,_document));
    }

    private static Element setEffectWhileSending(EffectWhileSendingWithStatistic _object, String _fieldName, Document _document) {
        if (_object instanceof EffectWhileSendingWithStatistic) {
            Element element_ = _document.createElement(TYPE_EFFECT_WHILE_SENDING_WITH_STATISTIC);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectWhileSendingWithStatistic(_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_EFFECT_WHILE_SENDING);
    }

    private static void setEffectWhileSending(EffectWhileSendingWithStatistic _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisableWeather(),FIELD_DISABLE_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getEnabledWeather(),FIELD_ENABLED_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getCopyingAbility(),FIELD_COPYING_ABILITY,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultWeight(),FIELD_MULT_WEIGHT,_document));
    }

    private static void setEffectWhileSendingWithStatistic(EffectWhileSendingWithStatistic _object, Element _element, Document _document) {
        if (_object.getEffect() != null) {
            _element.appendChild(setEffectStatistic(_object.getEffect(), FIELD_EFFECT, _document));
        }
        setEffectWhileSending(_object, _element, _document);
    }

    private static Element setStatistic(Statistic _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STATISTIC);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static void setBall(Ball _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getCatchingRate(),FIELD_CATCHING_RATE,_document));
        setItem(_object, _element, _document);
    }

    private static void setBerry(Berry _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealHpBySuperEffMove(),FIELD_HEAL_HP_BY_SUPER_EFF_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getLawForAttackFirst(),FIELD_LAW_FOR_ATTACK_FIRST,_document));
        _element.appendChild(setStringMapEfficiencyRate(_object.getMultFoesDamage(),FIELD_MULT_FOES_DAMAGE,_document));
        _element.appendChild(setMapStatisticBoostHpRate(_object.getMultStat(),FIELD_MULT_STAT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getWithoutFail(),FIELD_WITHOUT_FAIL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getHealPp(),FIELD_HEAL_PP,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealHp(),FIELD_HEAL_HP,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMaxHpHealingHp(),FIELD_MAX_HP_HEALING_HP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getHealStatus(),FIELD_HEAL_STATUS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealHpRate(),FIELD_HEAL_HP_RATE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMaxHpHealingHpRate(),FIELD_MAX_HP_HEALING_HP_RATE,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getDamageRateRecoilFoe(),FIELD_DAMAGE_RATE_RECOIL_FOE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getCategoryBoosting(),FIELD_CATEGORY_BOOSTING,_document));
        _element.appendChild(setMapStatisticByte(_object.getBoostStatis(),FIELD_BOOST_STATIS,_document));
        setItem(_object, _element, _document);
    }

    private static void setBoost(Boost _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWinPp(),FIELD_WIN_PP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getHappiness(),FIELD_HAPPINESS,_document));
        _element.appendChild(setMapStatisticShort(_object.getEvs(),FIELD_EVS,_document));
        setItem(_object, _element, _document);
    }

    private static void setFossil(Fossil _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPokemon(),FIELD_POKEMON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getLevel(),FIELD_LEVEL,_document));
        setItem(_object, _element, _document);
    }

    private static void setHealingHp(HealingHp _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHp(),FIELD_HP,_document));
        setHealingItem(_object, _element, _document);
    }

    private static void setHealingHpStatus(HealingHpStatus _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealedHpRate(),FIELD_HEALED_HP_RATE,_document));
        setHealingStatus(_object, _element, _document);
    }

    private static void setHealingItem(HealingItem _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getHappiness(),FIELD_HAPPINESS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getHealingTeam(),FIELD_HEALING_TEAM,_document));
        setItem(_object, _element, _document);
    }

    private static void setHealingPp(HealingPp _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getHealedMovePp(),FIELD_HEALED_MOVE_PP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getHealingAllMovesFullpp(),FIELD_HEALING_ALL_MOVES_FULLPP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isHealingAllMovesPp(),FIELD_HEALING_ALL_MOVES_PP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getHealingMoveFullpp(),FIELD_HEALING_MOVE_FULLPP,_document));
        setHealingItem(_object, _element, _document);
    }

    private static void setHealingStatus(HealingStatus _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getHealingKo(),FIELD_HEALING_KO,_document));
        setHealingItem(_object, _element, _document);
    }

    public static String setItem(Item _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setItem(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setItem(Item _object, String _fieldName, Document _document) {
        if (_object instanceof Ball) {
            Element element_ = _document.createElement(TYPE_BALL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setBall((Ball)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Berry) {
            Element element_ = _document.createElement(TYPE_BERRY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setBerry((Berry)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Boost) {
            Element element_ = _document.createElement(TYPE_BOOST);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setBoost((Boost)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolvingItem) {
            Element element_ = _document.createElement(TYPE_EVOLVING_ITEM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setItem(_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolvingStone) {
            Element element_ = _document.createElement(TYPE_EVOLVING_STONE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setItem(_object,element_,_document);
            return element_;
        }
        if (_object instanceof Fossil) {
            Element element_ = _document.createElement(TYPE_FOSSIL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setFossil((Fossil)_object,element_,_document);
            return element_;
        }
        if (_object instanceof HealingHp) {
            Element element_ = _document.createElement(TYPE_HEALING_HP);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setHealingHp((HealingHp)_object,element_,_document);
            return element_;
        }
        if (_object instanceof HealingHpStatus) {
            Element element_ = _document.createElement(TYPE_HEALING_HP_STATUS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setHealingHpStatus((HealingHpStatus)_object,element_,_document);
            return element_;
        }
        if (_object instanceof HealingPp) {
            Element element_ = _document.createElement(TYPE_HEALING_PP);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setHealingPp((HealingPp)_object,element_,_document);
            return element_;
        }
        if (_object instanceof HealingSimpleItem) {
            Element element_ = _document.createElement(TYPE_HEALING_SIMPLE_ITEM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setHealingItem((HealingSimpleItem)_object,element_,_document);
            return element_;
        }
        if (_object instanceof HealingSimpleStatus) {
            Element element_ = _document.createElement(TYPE_HEALING_SIMPLE_STATUS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setHealingStatus((HealingSimpleStatus)_object,element_,_document);
            return element_;
        }
        if (_object instanceof ItemForBattle) {
            Element element_ = _document.createElement(TYPE_ITEM_FOR_BATTLE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setItemForBattle((ItemForBattle)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Repel) {
            Element element_ = _document.createElement(TYPE_REPEL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRepel((Repel)_object,element_,_document);
            return element_;
        }
        if (_object instanceof SellingItem) {
            Element element_ = _document.createElement(TYPE_SELLING_ITEM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setItem(_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_ITEM);
    }

    private static void setItem(Item _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getPrice(),FIELD_PRICE,_document));
    }

    private static void setItemForBattle(ItemForBattle _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getTypesPk(),FIELD_TYPES_PK,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getCancelImmuType(),FIELD_CANCEL_IMMU_TYPE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getAgainstEvo(),FIELD_AGAINST_EVO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getAttackLast(),FIELD_ATTACK_LAST,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getBoostExp(),FIELD_BOOST_EXP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuStatus(),FIELD_IMMU_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getImmuLowStatis(),FIELD_IMMU_LOW_STATIS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getIncreasingMaxNbRoundTrap(),FIELD_INCREASING_MAX_NB_ROUND_TRAP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getAttacksSoon(),FIELD_ATTACKS_SOON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getSynchroStatus(),FIELD_SYNCHRO_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getFailStatus(),FIELD_FAIL_STATUS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getProtectAgainstKo(),FIELD_PROTECT_AGAINST_KO,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getProtectAgainstKoIfFullHp(),FIELD_PROTECT_AGAINST_KO_IF_FULL_HP,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getDrainedHpByDamageRate(),FIELD_DRAINED_HP_BY_DAMAGE_RATE,_document));
        _element.appendChild(setMapStatisticShort(_object.getWinEvFight(),FIELD_WIN_EV_FIGHT,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloBoolean(_object.getLawForAttackFirst(),FIELD_LAW_FOR_ATTACK_FIRST,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultTrappingDamage(),FIELD_MULT_TRAPPING_DAMAGE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultWinningHappiness(),FIELD_MULT_WINNING_HAPPINESS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultWinningEv(),FIELD_MULT_WINNING_EV,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultWinningExp(),FIELD_MULT_WINNING_EXP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getMultPower(),FIELD_MULT_POWER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getMultDamage(),FIELD_MULT_DAMAGE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultDrainedHp(),FIELD_MULT_DRAINED_HP,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getDamageRecoil(),FIELD_DAMAGE_RECOIL,_document));
        _element.appendChild(setMapStatisticByte(_object.getMultStatRank(),FIELD_MULT_STAT_RANK,_document));
        _element.appendChild(setMapStatisticPokemonByte(_object.getMultStatPokemonRank(),FIELD_MULT_STAT_POKEMON_RANK,_document));
        _element.appendChild(setMapStatisticString(_object.getMultStat(),FIELD_MULT_STAT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getIncreasingMaxNbRoundGlobalMove(),FIELD_INCREASING_MAX_NB_ROUND_GLOBAL_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getIncreasingMaxNbRoundTeamMove(),FIELD_INCREASING_MAX_NB_ROUND_TEAM_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuMoves(),FIELD_IMMU_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getHatching(),FIELD_HATCHING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuTypes(),FIELD_IMMU_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuWeather(),FIELD_IMMU_WEATHER,_document));
        _element.appendChild(setMapStatisticByte(_object.getBoostStatisSuperEff(),FIELD_BOOST_STATIS_SUPER_EFF,_document));
        _element.appendChild(setStringMapMapStatisticByte(_object.getBoostStatisTypes(),FIELD_BOOST_STATIS_TYPES,_document));
        _element.appendChild(setListEffectEndRound(_object.getEffectEndRound(),FIELD_EFFECT_END_ROUND,_document));
        _element.appendChild(setListEffectWhileSending(_object.getEffectSending(),FIELD_EFFECT_SENDING,_document));
        setItem(_object, _element, _document);
    }

    private static void setRepel(Repel _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getSteps(),FIELD_STEPS,_document));
        setItem(_object, _element, _document);
    }

    private static void setDamagingMoveData(DamagingMoveData _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getCategory(),FIELD_CATEGORY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDirect(),FIELD_DIRECT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getCannotKo(),FIELD_CANNOT_KO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getStoppableMoveKoSingle(),FIELD_STOPPABLE_MOVE_KO_SINGLE,_document));
        setMoveData(_object, _element, _document);
    }

    public static String setMoveData(MoveData _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setMoveData(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setMoveData(MoveData _object, String _fieldName, Document _document) {
        if (_object instanceof DamagingMoveData) {
            Element element_ = _document.createElement(TYPE_DAMAGING_MOVE_DATA);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setDamagingMoveData((DamagingMoveData)_object,element_,_document);
            return element_;
        }
        if (_object instanceof StatusMoveData) {
            Element element_ = _document.createElement(TYPE_STATUS_MOVE_DATA);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setStatusMoveData((StatusMoveData)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_MOVE_DATA);
    }

    private static void setMoveData(MoveData _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getPp(),FIELD_PP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getTypes(),FIELD_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getBoostedTypes(),FIELD_BOOSTED_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPriority(),FIELD_PRIORITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getAccuracy(),FIELD_ACCURACY,_document));
        _element.appendChild(setListEffect(_object.getEffects(),FIELD_EFFECTS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getNbPrepaRound(),FIELD_NB_PREPA_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisappearBeforeUse(),FIELD_DISAPPEAR_BEFORE_USE,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloNumber(_object.getRepeatRoundLaw(),FIELD_REPEAT_ROUND_LAW,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getRankIncrementNbRound(),FIELD_RANK_INCREMENT_NB_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getRechargeRound(),FIELD_RECHARGE_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getConstUserChoice(),FIELD_CONST_USER_CHOICE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getStoppableMoveSolo(),FIELD_STOPPABLE_MOVE_SOLO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getStoppableMoveMulti(),FIELD_STOPPABLE_MOVE_MULTI,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getStoppableMovePrio(),FIELD_STOPPABLE_MOVE_PRIO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getSecEffectIfNoDamage(),FIELD_SEC_EFFECT_IF_NO_DAMAGE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapListInteger(_object.getSecEffectsByItem(),FIELD_SEC_EFFECTS_BY_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getIgnVarAccurUserNeg(),FIELD_IGN_VAR_ACCUR_USER_NEG,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getIgnVarEvasTargetPos(),FIELD_IGN_VAR_EVAS_TARGET_POS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAchieveDisappearedPkUsingMove(),FIELD_ACHIEVE_DISAPPEARED_PK_USING_MOVE,_document));
        _element.appendChild(setSwitchType(_object.getSwitchType(),FIELD_SWITCH_TYPE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getTypesByOwnedItem(),FIELD_TYPES_BY_OWNED_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getTypesByWeather(),FIELD_TYPES_BY_WEATHER,_document));
        _element.appendChild(setTargetChoice(_object.getTargetChoice(),FIELD_TARGET_CHOICE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDeletedStatus(),FIELD_DELETED_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getRequiredStatus(),FIELD_REQUIRED_STATUS,_document));
    }

    private static void setStatusMoveData(StatusMoveData _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getThievableMove(),FIELD_THIEVABLE_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getCounterableMove(),FIELD_COUNTERABLE_MOVE,_document));
        setMoveData(_object, _element, _document);
    }

    private static Element setEffect(Effect _object, String _fieldName, Document _document) {
        if (_object instanceof EffectAccuracy) {
            Element element_ = _document.createElement(TYPE_EFFECT_ACCURACY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffect(_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectAlly) {
            Element element_ = _document.createElement(TYPE_EFFECT_ALLY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectAlly((EffectAlly)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectBatonPass) {
            Element element_ = _document.createElement(TYPE_EFFECT_BATON_PASS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffect(_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectClone) {
            Element element_ = _document.createElement(TYPE_EFFECT_CLONE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectClone((EffectClone)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectCommonStatistics) {
            Element element_ = _document.createElement(TYPE_EFFECT_COMMON_STATISTICS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectCommonStatistics((EffectCommonStatistics)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectCopyFighter) {
            Element element_ = _document.createElement(TYPE_EFFECT_COPY_FIGHTER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectCopyFighter((EffectCopyFighter)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectCopyMove) {
            Element element_ = _document.createElement(TYPE_EFFECT_COPY_MOVE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectCopyMove((EffectCopyMove)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectCounterAttack) {
            Element element_ = _document.createElement(TYPE_EFFECT_COUNTER_ATTACK);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectCounterAttack((EffectCounterAttack)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectDamage) {
            Element element_ = _document.createElement(TYPE_EFFECT_DAMAGE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectDamage((EffectDamage)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectDamageRate) {
            Element element_ = _document.createElement(TYPE_EFFECT_DAMAGE_RATE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectDamageRate((EffectDamageRate)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundFoe) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_FOE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundFoe((EffectEndRoundFoe)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundGlobal) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_GLOBAL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRound((EffectEndRoundGlobal)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundIndividual) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_INDIVIDUAL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundIndividual((EffectEndRoundIndividual)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundMultiRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_MULTI_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundMultiRelation((EffectEndRoundMultiRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundPositionRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_POSITION_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundPositionRelation((EffectEndRoundPositionRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundPositionTargetRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRound((EffectEndRoundPositionTargetRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundSingleRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_SINGLE_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundSingleRelation((EffectEndRoundSingleRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundSingleStatus) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_SINGLE_STATUS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundSingleStatus((EffectEndRoundSingleStatus)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundStatusRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_STATUS_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundStatusRelation((EffectEndRoundStatusRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundTeam) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_TEAM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundTeam((EffectEndRoundTeam)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectFullHpRate) {
            Element element_ = _document.createElement(TYPE_EFFECT_FULL_HP_RATE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectFullHpRate((EffectFullHpRate)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectGlobal) {
            Element element_ = _document.createElement(TYPE_EFFECT_GLOBAL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectGlobal((EffectGlobal)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectInvoke) {
            Element element_ = _document.createElement(TYPE_EFFECT_INVOKE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectInvoke((EffectInvoke)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectMultSufferedMovePower) {
            Element element_ = _document.createElement(TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectMultSufferedMovePower((EffectMultSufferedMovePower)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectMultUsedMovePower) {
            Element element_ = _document.createElement(TYPE_EFFECT_MULT_USED_MOVE_POWER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectMultUsedMovePower((EffectMultUsedMovePower)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectOrder) {
            Element element_ = _document.createElement(TYPE_EFFECT_ORDER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectOrder((EffectOrder)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectProtectFromTypes) {
            Element element_ = _document.createElement(TYPE_EFFECT_PROTECT_FROM_TYPES);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectProtectFromTypes((EffectProtectFromTypes)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectProtection) {
            Element element_ = _document.createElement(TYPE_EFFECT_PROTECTION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectProtection((EffectProtection)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectRemainedHpRate) {
            Element element_ = _document.createElement(TYPE_EFFECT_REMAINED_HP_RATE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectRemainedHpRate((EffectRemainedHpRate)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectRestriction) {
            Element element_ = _document.createElement(TYPE_EFFECT_RESTRICTION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectRestriction((EffectRestriction)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectStatistic) {
            Element element_ = _document.createElement(TYPE_EFFECT_STATISTIC);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectStatistic((EffectStatistic)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectStatus) {
            Element element_ = _document.createElement(TYPE_EFFECT_STATUS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectStatus((EffectStatus)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectSwitchAbilities) {
            Element element_ = _document.createElement(TYPE_EFFECT_SWITCH_ABILITIES);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectSwitchAbilities((EffectSwitchAbilities)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectSwitchItems) {
            Element element_ = _document.createElement(TYPE_EFFECT_SWITCH_ITEMS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectSwitchItems((EffectSwitchItems)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectSwitchMoveTypes) {
            Element element_ = _document.createElement(TYPE_EFFECT_SWITCH_MOVE_TYPES);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectSwitchMoveTypes((EffectSwitchMoveTypes)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectSwitchPointView) {
            Element element_ = _document.createElement(TYPE_EFFECT_SWITCH_POINT_VIEW);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectSwitchPointView((EffectSwitchPointView)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectSwitchPosition) {
            Element element_ = _document.createElement(TYPE_EFFECT_SWITCH_POSITION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffect(_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectSwitchTypes) {
            Element element_ = _document.createElement(TYPE_EFFECT_SWITCH_TYPES);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectSwitchTypes((EffectSwitchTypes)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectTeam) {
            Element element_ = _document.createElement(TYPE_EFFECT_TEAM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectTeam((EffectTeam)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectTeamWhileSendFoe) {
            Element element_ = _document.createElement(TYPE_EFFECT_TEAM_WHILE_SEND_FOE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectTeamWhileSendFoe((EffectTeamWhileSendFoe)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectUnprotectFromTypes) {
            Element element_ = _document.createElement(TYPE_EFFECT_UNPROTECT_FROM_TYPES);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectUnprotectFromTypes((EffectUnprotectFromTypes)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectVarPP) {
            Element element_ = _document.createElement(TYPE_EFFECT_VAR_P_P);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectVarPP((EffectVarPP)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectWinMoney) {
            Element element_ = _document.createElement(TYPE_EFFECT_WIN_MONEY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectWinMoney((EffectWinMoney)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_EFFECT);
    }

    private static void setEffect(Effect _object, Element _element, Document _document) {
        _element.appendChild(setTargetChoice(_object.getTargetChoice(),FIELD_TARGET_CHOICE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFail(),FIELD_FAIL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListInteger(_object.getRequiredSuccessfulEffects(),FIELD_REQUIRED_SUCCESSFUL_EFFECTS,_document));
    }

    private static void setEffectAlly(EffectAlly _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultAllyDamage(),FIELD_MULT_ALLY_DAMAGE,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectClone(EffectClone _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHpRateClone(),FIELD_HP_RATE_CLONE,_document));
        setEffect(_object, _element, _document);
    }

    private static Element setEffectCombo(EffectCombo _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_EFFECT_COMBO);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectCombo(_object,element_,_document);
        return element_;
    }

    private static void setEffectCombo(EffectCombo _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultEvtRateSecEff(),FIELD_MULT_EVT_RATE_SEC_EFF,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloNumber(_object.getRepeatedRoundsLaw(),FIELD_REPEATED_ROUNDS_LAW,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getRankIncrementNbRound(),FIELD_RANK_INCREMENT_NB_ROUND,_document));
        _element.appendChild(setListEffectEndRoundFoe(_object.getEffectEndRound(),FIELD_EFFECT_END_ROUND,_document));
        _element.appendChild(setListEffectTeam(_object.getTeamMove(),FIELD_TEAM_MOVE,_document));
    }

    private static void setEffectCommonStatistics(EffectCommonStatistics _object, Element _element, Document _document) {
        _element.appendChild(setMapStatisticString(_object.getCommonValue(),FIELD_COMMON_VALUE,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectCopyFighter(EffectCopyFighter _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getPpForMoves(),FIELD_PP_FOR_MOVES,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectCopyMove(EffectCopyMove _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getCopyingMoveForUser(),FIELD_COPYING_MOVE_FOR_USER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getCopyingMoveForUserDef(),FIELD_COPYING_MOVE_FOR_USER_DEF,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMovesNotToBeCopied(),FIELD_MOVES_NOT_TO_BE_COPIED,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectCounterAttack(EffectCounterAttack _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getSufferingDamageTypes(),FIELD_SUFFERING_DAMAGE_TYPES,_document));
        _element.appendChild(setMapStatisticByte(_object.getDroppedStatDirectMove(),FIELD_DROPPED_STAT_DIRECT_MOVE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getSufferingDamageDirectMove(),FIELD_SUFFERING_DAMAGE_DIRECT_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getProtectFail(),FIELD_PROTECT_FAIL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getCounterFail(),FIELD_COUNTER_FAIL,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectDamage(EffectDamage _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getChRate(),FIELD_CH_RATE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getConstDamage(),FIELD_CONST_DAMAGE,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloString(_object.getDamageLaw(),FIELD_DAMAGE_LAW,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultDamageAgainst(),FIELD_MULT_DAMAGE_AGAINST,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloNumber(_object.getChLaw(),FIELD_CH_LAW,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloNumber(_object.getHitsLaw(),FIELD_HITS_LAW,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPower(),FIELD_POWER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getRandMax(),FIELD_RAND_MAX,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getSummingUserTeamOkFighter(),FIELD_SUMMING_USER_TEAM_OK_FIGHTER,_document));
        _element.appendChild(setListStatistic(_object.getIgnVarStatTargetPos(),FIELD_IGN_VAR_STAT_TARGET_POS,_document));
        _element.appendChild(setListStatistic(_object.getIgnVarStatUserNeg(),FIELD_IGN_VAR_STAT_USER_NEG,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isUserAttack(),FIELD_USER_ATTACK,_document));
        _element.appendChild(setStatistic(_object.getStatisAtt(),FIELD_STATIS_ATT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isTargetDefense(),FIELD_TARGET_DEFENSE,_document));
        _element.appendChild(setStatistic(_object.getStatisDef(),FIELD_STATIS_DEF,_document));
        _element.appendChild(setMapStatisticByte(_object.getBoostStatisOnceKoFoe(),FIELD_BOOST_STATIS_ONCE_KO_FOE,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectDamageRate(EffectDamageRate _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRateDamage(),FIELD_RATE_DAMAGE,_document));
        setEffect(_object, _element, _document);
    }

    private static Element setEffectEndRound(EffectEndRound _object, String _fieldName, Document _document) {
        if (_object instanceof EffectEndRoundFoe) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_FOE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundFoe((EffectEndRoundFoe)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundGlobal) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_GLOBAL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRound(_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundIndividual) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_INDIVIDUAL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundIndividual((EffectEndRoundIndividual)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundMultiRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_MULTI_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundMultiRelation((EffectEndRoundMultiRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundPositionRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_POSITION_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundPositionRelation((EffectEndRoundPositionRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundPositionTargetRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRound(_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundSingleRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_SINGLE_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundSingleRelation((EffectEndRoundSingleRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundSingleStatus) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_SINGLE_STATUS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundSingleStatus((EffectEndRoundSingleStatus)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundStatusRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_STATUS_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundStatusRelation((EffectEndRoundStatusRelation)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundTeam) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_TEAM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundTeam((EffectEndRoundTeam)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_EFFECT_END_ROUND);
    }

    private static void setEffectEndRound(EffectEndRound _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFailEndRound(),FIELD_FAIL_END_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getEndRoundRank(),FIELD_END_ROUND_RANK,_document));
        setEffect(_object, _element, _document);
    }

    private static Element setEffectEndRoundFoe(EffectEndRoundFoe _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_FOE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectEndRoundFoe(_object,element_,_document);
        return element_;
    }

    private static void setEffectEndRoundFoe(EffectEndRoundFoe _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getInflictedRateHpTarget(),FIELD_INFLICTED_RATE_HP_TARGET,_document));
        setEffectEndRound(_object, _element, _document);
    }

    private static void setEffectEndRoundIndividual(EffectEndRoundIndividual _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getDeleteAllStatus(),FIELD_DELETE_ALL_STATUS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRecoilDamage(),FIELD_RECOIL_DAMAGE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealHp(),FIELD_HEAL_HP,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getHealHpByOwnerTypes(),FIELD_HEAL_HP_BY_OWNER_TYPES,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultDamageStatus(),FIELD_MULT_DAMAGE_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getUserStatusEndRound(),FIELD_USER_STATUS_END_ROUND,_document));
        setEffectEndRound(_object, _element, _document);
    }

    private static void setEffectEndRoundMultiRelation(EffectEndRoundMultiRelation _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getDamageByStatus(),FIELD_DAMAGE_BY_STATUS,_document));
        setEffectEndRound(_object, _element, _document);
    }

    private static void setEffectEndRoundPositionRelation(EffectEndRoundPositionRelation _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealHp(),FIELD_HEAL_HP,_document));
        setEffectEndRound(_object, _element, _document);
    }

    private static void setEffectEndRoundSingleRelation(EffectEndRoundSingleRelation _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setMapLongRate(_object.getRateDamageFunctionOfNbRounds(),FIELD_RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloNumber(_object.getLawForEnablingEffect(),FIELD_LAW_FOR_ENABLING_EFFECT,_document));
        setEffectEndRound(_object, _element, _document);
    }

    private static void setEffectEndRoundSingleStatus(EffectEndRoundSingleStatus _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isIncrementingDamageByRounds(),FIELD_INCREMENTING_DAMAGE_BY_ROUNDS,_document));
        setEffectEndRoundStatus(_object, _element, _document);
    }

    private static Element setEffectEndRoundStatus(EffectEndRoundStatus _object, String _fieldName, Document _document) {
        if (_object instanceof EffectEndRoundSingleStatus) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_SINGLE_STATUS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundSingleStatus((EffectEndRoundSingleStatus)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EffectEndRoundStatusRelation) {
            Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_STATUS_RELATION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectEndRoundStatusRelation((EffectEndRoundStatusRelation)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_EFFECT_END_ROUND_STATUS);
    }

    private static void setEffectEndRoundStatus(EffectEndRoundStatus _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getInflictedRateHpTarget(),FIELD_INFLICTED_RATE_HP_TARGET,_document));
        setEffectEndRound(_object, _element, _document);
    }

    private static void setEffectEndRoundStatusRelation(EffectEndRoundStatusRelation _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getThievedHpRateTargetToUser(),FIELD_THIEVED_HP_RATE_TARGET_TO_USER,_document));
        setEffectEndRoundStatus(_object, _element, _document);
    }

    private static void setEffectEndRoundTeam(EffectEndRoundTeam _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getDeleteAllStatus(),FIELD_DELETE_ALL_STATUS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getDeleteAllStatusAlly(),FIELD_DELETE_ALL_STATUS_ALLY,_document));
        setEffectEndRound(_object, _element, _document);
    }

    private static void setEffectFullHpRate(EffectFullHpRate _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getLeftUserHp(),FIELD_LEFT_USER_HP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getRestoredHp(),FIELD_RESTORED_HP,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getClosestFoeDamageRateHp(),FIELD_CLOSEST_FOE_DAMAGE_RATE_HP,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectGlobal(EffectGlobal _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getWeather(),FIELD_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getCanceledIfUsed(),FIELD_CANCELED_IF_USED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getReverseOrderOfSortBySpeed(),FIELD_REVERSE_ORDER_OF_SORT_BY_SPEED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getPuttingKo(),FIELD_PUTTING_KO,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultAccuracy(),FIELD_MULT_ACCURACY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getUnusableItem(),FIELD_UNUSABLE_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getPreventStatus(),FIELD_PREVENT_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuneTypes(),FIELD_IMMUNE_TYPES,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getDamageEndRound(),FIELD_DAMAGE_END_ROUND,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealingEndRound(),FIELD_HEALING_END_ROUND,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHealingEndRoundGround(),FIELD_HEALING_END_ROUND_GROUND,_document));
        _element.appendChild(setMapTypesDuoRate(_object.getEfficiencyMoves(),FIELD_EFFICIENCY_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDisableImmuAgainstTypes(),FIELD_DISABLE_IMMU_AGAINST_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getCancelProtectingAbilities(),FIELD_CANCEL_PROTECTING_ABILITIES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getUnusableMoves(),FIELD_UNUSABLE_MOVES,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultDamagePrepaRound(),FIELD_MULT_DAMAGE_PREPA_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMovesUsedByTargetedFighters(),FIELD_MOVES_USED_BY_TARGETED_FIGHTERS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultEffectLovingAlly(),FIELD_MULT_EFFECT_LOVING_ALLY,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultPowerMoves(),FIELD_MULT_POWER_MOVES,_document));
        _element.appendChild(setMapStatisticTypeRate(_object.getMultStatIfContainsType(),FIELD_MULT_STAT_IF_CONTAINS_TYPE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getCancelEffects(),FIELD_CANCEL_EFFECTS,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultDamageTypesMoves(),FIELD_MULT_DAMAGE_TYPES_MOVES,_document));
        _element.appendChild(setListStatistic(_object.getCancelChgtStat(),FIELD_CANCEL_CHGT_STAT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getInvokedMoveTerrain(),FIELD_INVOKED_MOVE_TERRAIN,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getChangedTypesTerrain(),FIELD_CHANGED_TYPES_TERRAIN,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectInvoke(EffectInvoke _object, Element _element, Document _document) {
        _element.appendChild(setMapEnvironmentTypeString(_object.getMoveFctEnv(),FIELD_MOVE_FCT_ENV,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getInvokingMoveButUser(),FIELD_INVOKING_MOVE_BUT_USER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getInvokingTargetChosenMove(),FIELD_INVOKING_TARGET_CHOSEN_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getInvokingUserMoveWhileSleep(),FIELD_INVOKING_USER_MOVE_WHILE_SLEEP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getInvokingAllyMove(),FIELD_INVOKING_ALLY_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getInvokingTargetSuccesfulMove(),FIELD_INVOKING_TARGET_SUCCESFUL_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getInvokingSufferedMove(),FIELD_INVOKING_SUFFERED_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getInvokingMoveByUserTypes(),FIELD_INVOKING_MOVE_BY_USER_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMovesNotToBeInvoked(),FIELD_MOVES_NOT_TO_BE_INVOKED,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRateInvokationMove(),FIELD_RATE_INVOKATION_MOVE,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectMultSufferedMovePower(EffectMultSufferedMovePower _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultMovePowerFctType(),FIELD_MULT_MOVE_POWER_FCT_TYPE,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectMultUsedMovePower(EffectMultUsedMovePower _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getMultMovePowerFctType(),FIELD_MULT_MOVE_POWER_FCT_TYPE,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectOrder(EffectOrder _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getTargetAttacksLast(),FIELD_TARGET_ATTACKS_LAST,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectProtectFromTypes(EffectProtectFromTypes _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getImmuAgainstTypes(),FIELD_IMMU_AGAINST_TYPES,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectProtection(EffectProtection _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getProtSingle(),FIELD_PROT_SINGLE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getProtSingleAgainstKo(),FIELD_PROT_SINGLE_AGAINST_KO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getProtTeamAgainstMultTargets(),FIELD_PROT_TEAM_AGAINST_MULT_TARGETS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getProtTeamAgainstPrio(),FIELD_PROT_TEAM_AGAINST_PRIO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isProtTeamAgainstStatusMoves(),FIELD_PROT_TEAM_AGAINST_STATUS_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isProtTeamAgainstDamageMoves(),FIELD_PROT_TEAM_AGAINST_DAMAGE_MOVES,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectRemainedHpRate(EffectRemainedHpRate _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRateHp(),FIELD_RATE_HP,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectRestriction(EffectRestriction _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getForbidTargetUsingItem(),FIELD_FORBID_TARGET_USING_ITEM,_document));
        _element.appendChild(setMoveChoiceRestrictionType(_object.getChoiceRestriction(),FIELD_CHOICE_RESTRICTION,_document));
        setEffect(_object, _element, _document);
    }

    private static Element setEffectStatistic(EffectStatistic _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_EFFECT_STATISTIC);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectStatistic(_object,element_,_document);
        return element_;
    }

    private static void setEffectStatistic(EffectStatistic _object, Element _element, Document _document) {
        _element.appendChild(setMapStatisticByte(_object.getStatisVarRank(),FIELD_STATIS_VAR_RANK,_document));
        _element.appendChild(setMapStatisticString(_object.getLocalFailStatis(),FIELD_LOCAL_FAIL_STATIS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getEvtRate(),FIELD_EVT_RATE,_document));
        _element.appendChild(setListStatistic(_object.getCopyBoost(),FIELD_COPY_BOOST,_document));
        _element.appendChild(setListStatistic(_object.getSwapBoostStatis(),FIELD_SWAP_BOOST_STATIS,_document));
        _element.appendChild(setMapStatisticString(_object.getLocalFailSwapBoostStatis(),FIELD_LOCAL_FAIL_SWAP_BOOST_STATIS,_document));
        _element.appendChild(setMonteCarloEnumStatistic(_object.getLawBoost(),FIELD_LAW_BOOST,_document));
        _element.appendChild(setListStatistic(_object.getCancelLowStat(),FIELD_CANCEL_LOW_STAT,_document));
        _element.appendChild(setListStatistic(_object.getCancelChgtStat(),FIELD_CANCEL_CHGT_STAT,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectStatus(EffectStatus _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloString(_object.getLawStatus(),FIELD_LAW_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDeletedStatus(),FIELD_DELETED_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getLocalFailStatus(),FIELD_LOCAL_FAIL_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getKoUserHealSubst(),FIELD_KO_USER_HEAL_SUBST,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getStatusFromUser(),FIELD_STATUS_FROM_USER,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectSwitchAbilities(EffectSwitchAbilities _object, Element _element, Document _document) {
        _element.appendChild(setExchangeType(_object.getExchangeAbility(),FIELD_EXCHANGE_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getConstAbility(),FIELD_CONST_ABILITY,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectSwitchItems(EffectSwitchItems _object, Element _element, Document _document) {
        _element.appendChild(setMoveItemType(_object.getMoveObject(),FIELD_MOVE_OBJECT,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectSwitchMoveTypes(EffectSwitchMoveTypes _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringMapString(_object.getChangeTypes(),FIELD_CHANGE_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getReplacingTypes(),FIELD_REPLACING_TYPES,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectSwitchPointView(EffectSwitchPointView _object, Element _element, Document _document) {
        _element.appendChild(setPointViewChangementType(_object.getPointViewChangement(),FIELD_POINT_VIEW_CHANGEMENT,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectSwitchTypes(EffectSwitchTypes _object, Element _element, Document _document) {
        _element.appendChild(setMapEnvironmentTypeString(_object.getChgtTypeByEnv(),FIELD_CHGT_TYPE_BY_ENV,_document));
        _element.appendChild(setConstValuesType(_object.getConstValuesType(),FIELD_CONST_VALUES_TYPE,_document));
        _element.appendChild(setExchangeType(_object.getExchangeTypes(),FIELD_EXCHANGE_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getConstTypes(),FIELD_CONST_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAddedTypes(),FIELD_ADDED_TYPES,_document));
        setEffect(_object, _element, _document);
    }

    private static Element setEffectTeam(EffectTeam _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_EFFECT_TEAM);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectTeam(_object,element_,_document);
        return element_;
    }

    private static void setEffectTeam(EffectTeam _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getForbiddingHealing(),FIELD_FORBIDDING_HEALING,_document));
        _element.appendChild(setListStatistic(_object.getForbiddenBoost(),FIELD_FORBIDDEN_BOOST,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getUnusableMoves(),FIELD_UNUSABLE_MOVES,_document));
        _element.appendChild(setListStatistic(_object.getCancelChgtStatFoeTeam(),FIELD_CANCEL_CHGT_STAT_FOE_TEAM,_document));
        _element.appendChild(setListStatistic(_object.getCancelChgtStatTeam(),FIELD_CANCEL_CHGT_STAT_TEAM,_document));
        _element.appendChild(setMapCategoryMultRate(_object.getMultDamage(),FIELD_MULT_DAMAGE,_document));
        _element.appendChild(setMapStatisticRate(_object.getMultStatistic(),FIELD_MULT_STATISTIC,_document));
        _element.appendChild(setMapStatisticRate(_object.getMultStatisticFoe(),FIELD_MULT_STATISTIC_FOE,_document));
        _element.appendChild(setListStatistic(_object.getProtectAgainstLowStat(),FIELD_PROTECT_AGAINST_LOW_STAT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getProtectAgainstCh(),FIELD_PROTECT_AGAINST_CH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getProtectAgainstStatus(),FIELD_PROTECT_AGAINST_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDisableFoeTeamEffects(),FIELD_DISABLE_FOE_TEAM_EFFECTS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDisableFoeTeamStatus(),FIELD_DISABLE_FOE_TEAM_STATUS,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectTeamWhileSendFoe(EffectTeamWhileSendFoe _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFailSending(),FIELD_FAIL_SENDING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapShortString(_object.getStatusByNbUses(),FIELD_STATUS_BY_NB_USES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDeletedByFoeTypes(),FIELD_DELETED_BY_FOE_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getDamageRateAgainstFoe(),FIELD_DAMAGE_RATE_AGAINST_FOE,_document));
        _element.appendChild(setMapStatisticByte(_object.getStatistics(),FIELD_STATISTICS,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectUnprotectFromTypes(EffectUnprotectFromTypes _object, Element _element, Document _document) {
        _element.appendChild(setListTypesDuo(_object.getTypes(),FIELD_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDisableImmuAgainstTypes(),FIELD_DISABLE_IMMU_AGAINST_TYPES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getDisableImmuFromMoves(),FIELD_DISABLE_IMMU_FROM_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAttackTargetWithTypes(),FIELD_ATTACK_TARGET_WITH_TYPES,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectVarPP(EffectVarPP _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getDeletePp(),FIELD_DELETE_PP,_document));
        setEffect(_object, _element, _document);
    }

    private static void setEffectWinMoney(EffectWinMoney _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWinningRateBySumTargetUser(),FIELD_WINNING_RATE_BY_SUM_TARGET_USER,_document));
        setEffect(_object, _element, _document);
    }

    private static Element setConstValuesType(ConstValuesType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_CONST_VALUES_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setExchangeType(ExchangeType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_EXCHANGE_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setMoveChoiceRestrictionType(MoveChoiceRestrictionType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MOVE_CHOICE_RESTRICTION_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setMoveItemType(MoveItemType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MOVE_ITEM_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setPointViewChangementType(PointViewChangementType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_POINT_VIEW_CHANGEMENT_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setSwitchType(SwitchType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_SWITCH_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setTargetChoice(TargetChoice _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_TARGET_CHOICE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static String setPokemonData(PokemonData _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPokemonData(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setPokemonData(PokemonData _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_POKEMON_DATA);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPokemonData(_object,element_,_document);
        return element_;
    }

    private static void setPokemonData(PokemonData _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWeight(),FIELD_WEIGHT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getTypes(),FIELD_TYPES,_document));
        _element.appendChild(setMapStatisticStatBaseEv(_object.getStatistics(),FIELD_STATISTICS,_document));
        _element.appendChild(setListLevelMove(_object.getLevMoves(),FIELD_LEV_MOVES,_document));
        _element.appendChild(setGenderRepartition(_object.getGenderRep(),FIELD_GENDER_REP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAbilities(),FIELD_ABILITIES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMoveTutors(),FIELD_MOVE_TUTORS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getHiddenMoves(),FIELD_HIDDEN_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getTechnicalMoves(),FIELD_TECHNICAL_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getBaseEvo(),FIELD_BASE_EVO,_document));
        _element.appendChild(setStringMapEvolution(_object.getEvolutions(),FIELD_EVOLUTIONS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getCatchingRate(),FIELD_CATCHING_RATE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHeight(),FIELD_HEIGHT,_document));
        _element.appendChild(setExpType(_object.getExpEvo(),FIELD_EXP_EVO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setLong(_object.getExpRate(),FIELD_EXP_RATE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getEggGroups(),FIELD_EGG_GROUPS,_document));
        _element.appendChild(DocumentWriterMathUtil.setLgInt(_object.getHatchingSteps(),FIELD_HATCHING_STEPS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getHappiness(),FIELD_HAPPINESS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getHappinessHatch(),FIELD_HAPPINESS_HATCH,_document));
    }

    private static Element setExpType(ExpType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_EXP_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setGenderRepartition(GenderRepartition _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_GENDER_REPARTITION);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setEvolution(Evolution _object, String _fieldName, Document _document) {
        if (_object instanceof EvolutionHappiness) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_HAPPINESS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolution(_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionItem) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_ITEM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionItem((EvolutionItem)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionLevelGender) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_LEVEL_GENDER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionLevelGender((EvolutionLevelGender)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionLevelSimple) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_LEVEL_SIMPLE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionLevel((EvolutionLevelSimple)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionMove) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_MOVE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionMove((EvolutionMove)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionMoveType) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_MOVE_TYPE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionMoveType((EvolutionMoveType)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionStoneGender) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_STONE_GENDER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionStoneGender((EvolutionStoneGender)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionStoneSimple) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_STONE_SIMPLE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionStone((EvolutionStoneSimple)_object,element_,_document);
            return element_;
        }
        if (_object instanceof EvolutionTeam) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_TEAM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEvolutionTeam((EvolutionTeam)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_EVOLUTION);
    }

    private static void setEvolution(Evolution _object, Element _element, Document _document) {
    }

    private static void setEvolutionItem(EvolutionItem _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getItem(),FIELD_ITEM,_document));
    }

    private static void setEvolutionLevel(EvolutionLevel _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getLevel(),FIELD_LEVEL,_document));
    }

    private static void setEvolutionLevelGender(EvolutionLevelGender _object, Element _element, Document _document) {
        _element.appendChild(setGender(_object.getGender(),FIELD_GENDER,_document));
        setEvolutionLevel(_object, _element, _document);
    }

    private static void setEvolutionMove(EvolutionMove _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getMove(),FIELD_MOVE,_document));
    }

    private static void setEvolutionMoveType(EvolutionMoveType _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getType(),FIELD_TYPE,_document));
    }

    private static void setEvolutionStone(EvolutionStone _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getStone(),FIELD_STONE,_document));
    }

    private static void setEvolutionStoneGender(EvolutionStoneGender _object, Element _element, Document _document) {
        _element.appendChild(setGender(_object.getGender(),FIELD_GENDER,_document));
        setEvolutionStone(_object, _element, _document);
    }

    private static void setEvolutionTeam(EvolutionTeam _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getPokemon(),FIELD_POKEMON,_document));
    }

    public static String setStatus(Status _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setStatus(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setStatus(Status _object, String _fieldName, Document _document) {
        if (_object instanceof StatusBeginRoundAutoDamage) {
            Element element_ = _document.createElement(TYPE_STATUS_BEGIN_ROUND_AUTO_DAMAGE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setStatusBeginRoundAutoDamage((StatusBeginRoundAutoDamage)_object,element_,_document);
            return element_;
        }
        if (_object instanceof StatusBeginRoundSimple) {
            Element element_ = _document.createElement(TYPE_STATUS_BEGIN_ROUND_SIMPLE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setStatusBeginRound((StatusBeginRoundSimple)_object,element_,_document);
            return element_;
        }
        if (_object instanceof StatusSimple) {
            Element element_ = _document.createElement(TYPE_STATUS_SIMPLE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setStatus(_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_STATUS);
    }

    private static void setStatus(Status _object, Element _element, Document _document) {
        _element.appendChild(setStatusType(_object.getStatusType(),FIELD_STATUS_TYPE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getCatchingRate(),FIELD_CATCHING_RATE,_document));
        _element.appendChild(setListEffectEndRoundStatus(_object.getEffectEndRound(),FIELD_EFFECT_END_ROUND,_document));
        _element.appendChild(setListEffectPartnerStatus(_object.getEffectsPartner(),FIELD_EFFECTS_PARTNER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisabledEffIfSwitch(),FIELD_DISABLED_EFF_IF_SWITCH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIncrementEndRound(),FIELD_INCREMENT_END_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getIncrementingEndRound(),FIELD_INCREMENTING_END_ROUND,_document));
        _element.appendChild(setMapStatisticRate(_object.getMultStat(),FIELD_MULT_STAT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFail(),FIELD_FAIL,_document));
    }

    private static void setStatusBeginRound(StatusBeginRound _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloBoolean(_object.getLawForUsingAMove(),FIELD_LAW_FOR_USING_A_MOVE,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloNumber(_object.getLawForUsingAMoveNbRound(),FIELD_LAW_FOR_USING_A_MOVE_NB_ROUND,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloBoolean(_object.getLawForUsingAMoveIfFoe(),FIELD_LAW_FOR_USING_A_MOVE_IF_FOE,_document));
        _element.appendChild(DocumentWriterMathUtil.setMonteCarloBoolean(_object.getLawForFullHealIfMove(),FIELD_LAW_FOR_FULL_HEAL_IF_MOVE,_document));
        setStatus(_object, _element, _document);
    }

    private static void setStatusBeginRoundAutoDamage(StatusBeginRoundAutoDamage _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getPower(),FIELD_POWER,_document));
        _element.appendChild(setStatistic(_object.getAttack(),FIELD_ATTACK,_document));
        _element.appendChild(setStatistic(_object.getDefense(),FIELD_DEFENSE,_document));
        setStatusBeginRound(_object, _element, _document);
    }

    private static Element setStatusType(StatusType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STATUS_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setEffectPartnerStatus(EffectPartnerStatus _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_EFFECT_PARTNER_STATUS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectPartnerStatus(_object,element_,_document);
        return element_;
    }

    private static void setEffectPartnerStatus(EffectPartnerStatus _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultDamageAgainstFoe(),FIELD_MULT_DAMAGE_AGAINST_FOE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getWeddingAlly(),FIELD_WEDDING_ALLY,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRestoredHpRateLovedAlly(),FIELD_RESTORED_HP_RATE_LOVED_ALLY,_document));
    }

    private static Element setBoostHpRate(BoostHpRate _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_BOOST_HP_RATE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setCategoryMult(CategoryMult _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_CATEGORY_MULT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setEfficiencyRate(EfficiencyRate _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_EFFICIENCY_RATE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setLevelMove(LevelMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LEVEL_MOVE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setStatBaseEv(StatBaseEv _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STAT_BASE_EV);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticCategory(StatisticCategory _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STATISTIC_CATEGORY);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticPokemon(StatisticPokemon _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STATISTIC_POKEMON);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticStatus(StatisticStatus _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STATISTIC_STATUS);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticType(StatisticType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STATISTIC_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setTypeDamageBoost(TypeDamageBoost _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_TYPE_DAMAGE_BOOST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setTypesDuo(TypesDuo _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_TYPES_DUO);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setWeatherType(WeatherType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_WEATHER_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    public static String setGame(Game _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setGame(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setGame(Game _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_GAME);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGame(_object,element_,_document);
        return element_;
    }

    private static void setGame(Game _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getZippedRom(),FIELD_ZIPPED_ROM,_document));
        _element.appendChild(setPlayer(_object.getPlayer(),FIELD_PLAYER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getRankLeague(),FIELD_RANK_LEAGUE,_document));
        _element.appendChild(setMapShortListPoint(_object.getBeatGymTrainer(),FIELD_BEAT_GYM_TRAINER,_document));
        _element.appendChild(setMapCoordsBoolean(_object.getBeatGymLeader(),FIELD_BEAT_GYM_LEADER,_document));
        _element.appendChild(setMapNbFightCoordsBoolean(_object.getBeatTrainer(),FIELD_BEAT_TRAINER,_document));
        _element.appendChild(setMapCoordsBoolean(_object.getTakenObjects(),FIELD_TAKEN_OBJECTS,_document));
        _element.appendChild(setMapCoordsBoolean(_object.getTakenPokemon(),FIELD_TAKEN_POKEMON,_document));
        _element.appendChild(setCoords(_object.getPlayerCoords(),FIELD_PLAYER_COORDS,_document));
        _element.appendChild(setDirection(_object.getPlayerOrientation(),FIELD_PLAYER_ORIENTATION,_document));
        _element.appendChild(setMapCoordsHostPokemonDuo(_object.getHostedPk(),FIELD_HOSTED_PK,_document));
        _element.appendChild(setFight(_object.getFight(),FIELD_FIGHT,_document));
        _element.appendChild(setDifficulty(_object.getDifficulty(),FIELD_DIFFICULTY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndexPeriodFishing(),FIELD_INDEX_PERIOD_FISHING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndexPeriod(),FIELD_INDEX_PERIOD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getIndexStep(),FIELD_INDEX_STEP,_document));
        _element.appendChild(setMapCoordsBoolean(_object.getVisitedPlaces(),FIELD_VISITED_PLACES,_document));
    }

    private static Element setHostPokemonDuo(HostPokemonDuo _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_HOST_POKEMON_DUO);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setHostPokemonDuo(_object,element_,_document);
        return element_;
    }

    private static void setHostPokemonDuo(HostPokemonDuo _object, Element _element, Document _document) {
        _element.appendChild(setPokemonPlayer(_object.getFirstPokemon(),FIELD_FIRST_POKEMON,_document));
        _element.appendChild(setPokemonPlayer(_object.getSecondPokemon(),FIELD_SECOND_POKEMON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getNbSteps(),FIELD_NB_STEPS,_document));
    }

    private static Element setNbFightCoords(NbFightCoords _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_NB_FIGHT_COORDS);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setUsesOfMove(UsesOfMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_USES_OF_MOVE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setActivityOfMove(ActivityOfMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_ACTIVITY_OF_MOVE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setAnticipation(Anticipation _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_ANTICIPATION);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setChoiceOfEvolutionAndMoves(ChoiceOfEvolutionAndMoves _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_CHOICE_OF_EVOLUTION_AND_MOVES);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setChoiceOfEvolutionAndMoves(_object,element_,_document);
        return element_;
    }

    private static void setChoiceOfEvolutionAndMoves(ChoiceOfEvolutionAndMoves _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getKeptMoves(),FIELD_KEPT_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getAbility(),FIELD_ABILITY,_document));
    }

    private static Element setFight(Fight _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_FIGHT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setFight(_object,element_,_document);
        return element_;
    }

    private static void setFight(Fight _object, Element _element, Document _document) {
        _element.appendChild(setFightType(_object.getFightType(),FIELD_FIGHT_TYPE,_document));
        _element.appendChild(setEnvironmentType(_object.getEnvType(),FIELD_ENV_TYPE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getMult(),FIELD_MULT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getPlayerMaxNumberFrontFighters(),FIELD_PLAYER_MAX_NUMBER_FRONT_FIGHTERS,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledMoves(),FIELD_ENABLED_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapBoolean(_object.getStillEnabledMoves(),FIELD_STILL_ENABLED_MOVES,_document));
        _element.appendChild(setMapByteTeam(_object.getTeams(),FIELD_TEAMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getNbFleeAttempt(),FIELD_NB_FLEE_ATTEMPT,_document));
        _element.appendChild(DocumentWriterMathUtil.setLgInt(_object.getNbRounds(),FIELD_NB_ROUNDS,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWinningMoney(),FIELD_WINNING_MONEY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getCatchingBall(),FIELD_CATCHING_BALL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getBeginRound(),FIELD_BEGIN_ROUND,_document));
        _element.appendChild(setTeamPosition(_object.getCurrentUser(),FIELD_CURRENT_USER,_document));
        _element.appendChild(setFightState(_object.getState(),FIELD_STATE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getUsedItemsWhileRound(),FIELD_USED_ITEMS_WHILE_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapByteByte(_object.getFirstPositPlayerFighters(),FIELD_FIRST_POSIT_PLAYER_FIGHTERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapByteByte(_object.getFirstPositFoeFighters(),FIELD_FIRST_POSIT_FOE_FIGHTERS,_document));
        _element.appendChild(setMapMoveTargetMoveTarget(_object.getAllyChoice(),FIELD_ALLY_CHOICE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getLostObjects(),FIELD_LOST_OBJECTS,_document));
        _element.appendChild(setMapByteChoiceOfEvolutionAndMoves(_object.getChoices(),FIELD_CHOICES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getCaughtEvolutions(),FIELD_CAUGHT_EVOLUTIONS,_document));
    }

    private static Element setFighter(Fighter _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_FIGHTER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setFighter(_object,element_,_document);
        return element_;
    }

    private static void setFighter(Fighter _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getNickname(),FIELD_NICKNAME,_document));
        _element.appendChild(setGender(_object.getGender(),FIELD_GENDER,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWeight(),FIELD_WEIGHT,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getHeight(),FIELD_HEIGHT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getCurrentName(),FIELD_CURRENT_NAME,_document));
        _element.appendChild(setGender(_object.getCurrentGender(),FIELD_CURRENT_GENDER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLastUsedItem(),FIELD_LAST_USED_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getItem(),FIELD_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getExpItem(),FIELD_EXP_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getAbility(),FIELD_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getCurrentAbility(),FIELD_CURRENT_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapShort(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(setMapMoveTeamPositionShort(_object.getStatusRelat(),FIELD_STATUS_RELAT,_document));
        _element.appendChild(DocumentWriterMathUtil.setLgInt(_object.getNbRounds(),FIELD_NB_ROUNDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getTypes(),FIELD_TYPES,_document));
        _element.appendChild(setStringMapUsesOfMove(_object.getMoves(),FIELD_MOVES,_document));
        _element.appendChild(setStringMapUsesOfMove(_object.getCurrentMoves(),FIELD_CURRENT_MOVES,_document));
        _element.appendChild(setMapStatisticShort(_object.getEv(),FIELD_EV,_document));
        _element.appendChild(setMapStatisticRate(_object.getStatisBase(),FIELD_STATIS_BASE,_document));
        _element.appendChild(setMapStatisticByte(_object.getStatisBoost(),FIELD_STATIS_BOOST,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRemainingHp(),FIELD_REMAINING_HP,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getClone(),FIELD_CLONE,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledMoves(),FIELD_ENABLED_MOVES,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledMovesProt(),FIELD_ENABLED_MOVES_PROT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getProtectedAgainstMoveTypes(),FIELD_PROTECTED_AGAINST_MOVE_TYPES,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledMovesUnprot(),FIELD_ENABLED_MOVES_UNPROT,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledMovesEndRound(),FIELD_ENABLED_MOVES_END_ROUND,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledMovesConstChoices(),FIELD_ENABLED_MOVES_CONST_CHOICES,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledChangingTypesMoves(),FIELD_ENABLED_CHANGING_TYPES_MOVES,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledCounteringMoves(),FIELD_ENABLED_COUNTERING_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapBoolean(_object.getEnabledMovesForAlly(),FIELD_ENABLED_MOVES_FOR_ALLY,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getDamageRateInflictedByType(),FIELD_DAMAGE_RATE_INFLICTED_BY_TYPE,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getDamageRateSufferedByType(),FIELD_DAMAGE_RATE_SUFFERED_BY_TYPE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isActed(),FIELD_ACTED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getGroundPlace(),FIELD_GROUND_PLACE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getGroundPlaceSubst(),FIELD_GROUND_PLACE_SUBST,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWonExp(),FIELD_WON_EXP,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWonExpSinceLastLevel(),FIELD_WON_EXP_SINCE_LAST_LEVEL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getLevel(),FIELD_LEVEL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getHappiness(),FIELD_HAPPINESS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getUsedBallCatching(),FIELD_USED_BALL_CATCHING,_document));
        _element.appendChild(setMapMoveTeamPositionBoolean(_object.getIncrUserAccuracy(),FIELD_INCR_USER_ACCURACY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapInteger(_object.getNbUsesMoves(),FIELD_NB_USES_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getNbPrepaRound(),FIELD_NB_PREPA_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isDisappeared(),FIELD_DISAPPEARED,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isNeedingToRecharge(),FIELD_NEEDING_TO_RECHARGE,_document));
        _element.appendChild(setMapMoveTeamPositionAffectedMove(_object.getTrackingMoves(),FIELD_TRACKING_MOVES,_document));
        _element.appendChild(setMapMoveTeamPositionActivityOfMove(_object.getTrappingMoves(),FIELD_TRAPPING_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLastSufferedMove(),FIELD_LAST_SUFFERED_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getLastSufferedMoveTypes(),FIELD_LAST_SUFFERED_MOVE_TYPES,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getDamageSufferedCateg(),FIELD_DAMAGE_SUFFERED_CATEG,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapRate(_object.getDamageSufferedCategRound(),FIELD_DAMAGE_SUFFERED_CATEG_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLastUsedMove(),FIELD_LAST_USED_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getUsedMoveLastRound(),FIELD_USED_MOVE_LAST_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAlreadyInvokedMovesRound(),FIELD_ALREADY_INVOKED_MOVES_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLastSuccessfulMove(),FIELD_LAST_SUCCESSFUL_MOVE,_document));
        _element.appendChild(setStringMapCopiedMove(_object.getCopiedMoves(),FIELD_COPIED_MOVES,_document));
        _element.appendChild(DocumentWriterMathUtil.setLgInt(_object.getNbRepeatingSuccessfulMoves(),FIELD_NB_REPEATING_SUCCESSFUL_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isUsingItem(),FIELD_USING_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSuccessfulMove(),FIELD_SUCCESSFUL_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isChanged(),FIELD_CHANGED,_document));
        _element.appendChild(setMapMoveTeamPositionStringList(_object.getPrivateMoves(),FIELD_PRIVATE_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isBelongingToPlayer(),FIELD_BELONGING_TO_PLAYER,_document));
        _element.appendChild(setAbstractAction(_object.getAction(),FIELD_ACTION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMovesToBeLearnt(),FIELD_MOVES_TO_BE_LEARNT,_document));
        _element.appendChild(setStringMapMovesAbilities(_object.getMovesAbilitiesEvos(),FIELD_MOVES_ABILITIES_EVOS,_document));
    }

    private static Element setMoveTeamPosition(MoveTeamPosition _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MOVE_TEAM_POSITION);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setStacksOfUses(StacksOfUses _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_STACKS_OF_USES);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setTargetCoords(TargetCoords _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_TARGET_COORDS);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setTeam(Team _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TEAM);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTeam(_object,element_,_document);
        return element_;
    }

    private static void setTeam(Team _object, Element _element, Document _document) {
        _element.appendChild(setMapStringListActivityOfMove(_object.getEnabledMovesByGroup(),FIELD_ENABLED_MOVES_BY_GROUP,_document));
        _element.appendChild(setStringMapActivityOfMove(_object.getEnabledMoves(),FIELD_ENABLED_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapBoolean(_object.getEnabledMovesWhileSendingFoe(),FIELD_ENABLED_MOVES_WHILE_SENDING_FOE,_document));
        _element.appendChild(DocumentWriterMathUtil.setStringMapLgInt(_object.getEnabledMovesWhileSendingFoeUses(),FIELD_ENABLED_MOVES_WHILE_SENDING_FOE_USES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapInteger(_object.getNbUsesMoves(),FIELD_NB_USES_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapInteger(_object.getNbUsesMovesRound(),FIELD_NB_USES_MOVES_ROUND,_document));
        _element.appendChild(setStringMapMapByteStacksOfUses(_object.getHealAfter(),FIELD_HEAL_AFTER,_document));
        _element.appendChild(setStringMapMapByteAnticipation(_object.getMovesAnticipation(),FIELD_MOVES_ANTICIPATION,_document));
        _element.appendChild(setMapByteFighter(_object.getMembers(),FIELD_MEMBERS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapByteListByte(_object.getPlayerFightersAgainstFoe(),FIELD_PLAYER_FIGHTERS_AGAINST_FOE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getNbKoRound(),FIELD_NB_KO_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getNbKoPreviousRound(),FIELD_NB_KO_PREVIOUS_ROUND,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getSuccessfulMovesRound(),FIELD_SUCCESSFUL_MOVES_ROUND,_document));
    }

    private static Element setTeamPosition(TeamPosition _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_TEAM_POSITION);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setAbstractAction(AbstractAction _object, String _fieldName, Document _document) {
        if (_object == null) {
            Element element_ = _document.createElement(TYPE_ACTION);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setAbstractAction(_object,element_,_document);
            return element_;
        }
        if (_object instanceof ActionHealMove) {
            Element element_ = _document.createElement(TYPE_ACTION_HEAL_MOVE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setActionHealMove((ActionHealMove)_object,element_,_document);
            return element_;
        }
        if (_object instanceof ActionMove) {
            Element element_ = _document.createElement(TYPE_ACTION_MOVE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setActionMove((ActionMove)_object,element_,_document);
            return element_;
        }
        if (_object instanceof ActionSimpleHeal) {
            Element element_ = _document.createElement(TYPE_ACTION_SIMPLE_HEAL);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setActionSimpleHeal((ActionSimpleHeal)_object,element_,_document);
            return element_;
        }
        if (_object instanceof ActionSwitch) {
            Element element_ = _document.createElement(TYPE_ACTION_SWITCH);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setActionSwitch((ActionSwitch)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_ABSTRACT_ACTION);
    }

    private static void setAbstractAction(AbstractAction _object, Element _element, Document _document) {
    }

    private static void setActionHeal(ActionHeal _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getChosenHealingItem(),FIELD_CHOSEN_HEALING_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isTeam(),FIELD_TEAM,_document));
    }

    private static void setActionHealMove(ActionHealMove _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFirstChosenMove(),FIELD_FIRST_CHOSEN_MOVE,_document));
        setActionHeal(_object, _element, _document);
    }

    private static void setActionMove(ActionMove _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFirstChosenMove(),FIELD_FIRST_CHOSEN_MOVE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFinalChosenMove(),FIELD_FINAL_CHOSEN_MOVE,_document));
        _element.appendChild(setListTargetCoords(_object.getChosenTargets(),FIELD_CHOSEN_TARGETS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getSubstitute(),FIELD_SUBSTITUTE,_document));
    }

    private static void setActionSimpleHeal(ActionSimpleHeal _object, Element _element, Document _document) {
        setActionHeal(_object, _element, _document);
    }

    private static void setActionSwitch(ActionSwitch _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getSubstitute(),FIELD_SUBSTITUTE,_document));
    }

    private static Element setFightState(FightState _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_FIGHT_STATE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setFightType(FightType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_FIGHT_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setAffectedMove(AffectedMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_AFFECTED_MOVE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setCopiedMove(CopiedMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_COPIED_MOVE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setMoveTarget(MoveTarget _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MOVE_TARGET);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setMovesAbilities(MovesAbilities _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_MOVES_ABILITIES);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setMovesAbilities(_object,element_,_document);
        return element_;
    }

    private static void setMovesAbilities(MovesAbilities _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMoves(),FIELD_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAbilities(),FIELD_ABILITIES,_document));
    }

    private static Element setDifficulty(Difficulty _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DIFFICULTY);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDifficulty(_object,element_,_document);
        return element_;
    }

    private static void setDifficulty(Difficulty _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getAllowCatchingKo(),FIELD_ALLOW_CATCHING_KO,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getAllowedSwitchPlacesEndRound(),FIELD_ALLOWED_SWITCH_PLACES_END_ROUND,_document));
        _element.appendChild(setDifficultyWinPointsFight(_object.getDiffWinningExpPtsFight(),FIELD_DIFF_WINNING_EXP_PTS_FIGHT,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRateWinningExpPtsFight(),FIELD_RATE_WINNING_EXP_PTS_FIGHT,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWinTrainerExp(),FIELD_WIN_TRAINER_EXP,_document));
        _element.appendChild(setDifficultyModelLaw(_object.getDamageRatePlayer(),FIELD_DAMAGE_RATE_PLAYER,_document));
        _element.appendChild(setDifficultyModelLaw(_object.getDamageRateLawFoe(),FIELD_DAMAGE_RATE_LAW_FOE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getEndFightIfOneTeamKo(),FIELD_END_FIGHT_IF_ONE_TEAM_KO,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRateWinMoneyBase(),FIELD_RATE_WIN_MONEY_BASE,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRateLooseMoneyWin(),FIELD_RATE_LOOSE_MONEY_WIN,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getIvPlayer(),FIELD_IV_PLAYER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getIvFoe(),FIELD_IV_FOE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getStillPossibleFlee(),FIELD_STILL_POSSIBLE_FLEE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getRestoredMovesEndFight(),FIELD_RESTORED_MOVES_END_FIGHT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getEnabledClosing(),FIELD_ENABLED_CLOSING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getRandomWildFight(),FIELD_RANDOM_WILD_FIGHT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSkipLearningMovesWhileNotGrowingLevel(),FIELD_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,_document));
    }

    public static String setLoadingGame(LoadingGame _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setLoadingGame(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setLoadingGame(LoadingGame _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_LOADING_GAME);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLoadingGame(_object,element_,_document);
        return element_;
    }

    private static void setLoadingGame(LoadingGame _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLastRom(),FIELD_LAST_ROM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getLastSavedGame(),FIELD_LAST_SAVED_GAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSaveHomeFolder(),FIELD_SAVE_HOME_FOLDER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isLoadHomeFolder(),FIELD_LOAD_HOME_FOLDER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isLoadLastRom(),FIELD_LOAD_LAST_ROM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isLoadLastGame(),FIELD_LOAD_LAST_GAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isSaveGameAtExit(),FIELD_SAVE_GAME_AT_EXIT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isEnableAnimation(),FIELD_ENABLE_ANIMATION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isEnableMovingHerosAnimation(),FIELD_ENABLE_MOVING_HEROS_ANIMATION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isClickButtonsPad(),FIELD_CLICK_BUTTONS_PAD,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isEnabledKeyPad(),FIELD_ENABLED_KEY_PAD,_document));
    }

    private static Element setDifficultyModelLaw(DifficultyModelLaw _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_DIFFICULTY_MODEL_LAW);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setDifficultyWinPointsFight(DifficultyWinPointsFight _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_DIFFICULTY_WIN_POINTS_FIGHT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setInventory(Inventory _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_INVENTORY);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setInventory(_object,element_,_document);
        return element_;
    }

    private static void setInventory(Inventory _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterMathUtil.setStringMapLgInt(_object.getItems(),FIELD_ITEMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapShortBoolean(_object.getTm(),FIELD_TM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setMapShortBoolean(_object.getHm(),FIELD_HM,_document));
    }

    private static Element setPlayer(Player _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_PLAYER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPlayer(_object,element_,_document);
        return element_;
    }

    private static void setPlayer(Player _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getNickname(),FIELD_NICKNAME,_document));
        _element.appendChild(setSex(_object.getSex(),FIELD_SEX,_document));
        _element.appendChild(setListUsablePokemon(_object.getTeam(),FIELD_TEAM,_document));
        _element.appendChild(setListUsablePokemon(_object.getBox(),FIELD_BOX,_document));
        _element.appendChild(setInventory(_object.getInventory(),FIELD_INVENTORY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringMapBoolean(_object.getCaughtPk(),FIELD_CAUGHT_PK,_document));
        _element.appendChild(DocumentWriterMathUtil.setLgInt(_object.getMoney(),FIELD_MONEY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getRemainingRepelSteps(),FIELD_REMAINING_REPEL_STEPS,_document));
    }

    private static Element setSex(Sex _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_SEX);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    public static String setDataMap(DataMap _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDataMap(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setDataMap(DataMap _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DATA_MAP);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDataMap(_object,element_,_document);
        return element_;
    }

    private static void setDataMap(DataMap _object, Element _element, Document _document) {
        _element.appendChild(setMapShortPlace(_object.getPlaces(),FIELD_PLACES,_document));
        _element.appendChild(setMapCoordsListCoords(_object.getAccessCondition(),FIELD_ACCESS_CONDITION,_document));
        _element.appendChild(setMapMiniMapCoordsTileMiniMap(_object.getMiniMap(),FIELD_MINI_MAP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getUnlockedCity(),FIELD_UNLOCKED_CITY,_document));
        _element.appendChild(setCoords(_object.getBegin(),FIELD_BEGIN,_document));
        _element.appendChild(setWildPk(_object.getFirstPokemon(),FIELD_FIRST_POKEMON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getScreenWidth(),FIELD_SCREEN_WIDTH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getScreenHeight(),FIELD_SCREEN_HEIGHT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getSpaceBetweenLeftAndHeros(),FIELD_SPACE_BETWEEN_LEFT_AND_HEROS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getSpaceBetweenTopAndHeros(),FIELD_SPACE_BETWEEN_TOP_AND_HEROS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setInteger(_object.getSideLength(),FIELD_SIDE_LENGTH,_document));
    }

    private static Element setBuilding(Building _object, String _fieldName, Document _document) {
        if (_object instanceof Gym) {
            Element element_ = _document.createElement(TYPE_GYM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setGym((Gym)_object,element_,_document);
            return element_;
        }
        if (_object instanceof PokemonCenter) {
            Element element_ = _document.createElement(TYPE_POKEMON_CENTER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPokemonCenter((PokemonCenter)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_BUILDING);
    }

    private static void setBuilding(Building _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getImageFileName(),FIELD_IMAGE_FILE_NAME,_document));
        _element.appendChild(setPoint(_object.getExitCity(),FIELD_EXIT_CITY,_document));
    }

    private static void setGym(Gym _object, Element _element, Document _document) {
        _element.appendChild(setLevelIndoorGym(_object.getIndoor(),FIELD_LEVEL,_document));
        setBuilding(_object, _element, _document);
    }

    private static void setPokemonCenter(PokemonCenter _object, Element _element, Document _document) {
        _element.appendChild(setLevelIndoorPokemonCenter(_object.getIndoor(),FIELD_LEVEL,_document));
        setBuilding(_object, _element, _document);
    }

    private static Element setAlly(Ally _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_ALLY);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAlly(_object,element_,_document);
        return element_;
    }

    private static void setAlly(Ally _object, Element _element, Document _document) {
        _element.appendChild(setListPkTrainer(_object.getTeam(),FIELD_TEAM,_document));
    }

    private static Element setCharacterInRoadCave(CharacterInRoadCave _object, String _fieldName, Document _document) {
        if (_object instanceof DealerItem) {
            Element element_ = _document.createElement(TYPE_DEALER_ITEM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setDealerItem((DealerItem)_object,element_,_document);
            return element_;
        }
        if (_object instanceof TrainerMultiFights) {
            Element element_ = _document.createElement(TYPE_TRAINER_MULTI_FIGHTS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setTrainerMultiFights((TrainerMultiFights)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_CHARACTER_IN_ROAD_CAVE);
    }

    private static void setDealerItem(DealerItem _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getItems(),FIELD_ITEMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getTechnicalMoves(),FIELD_TECHNICAL_MOVES,_document));
        setPerson(_object, _element, _document);
    }

    private static Element setDualFight(DualFight _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_DUAL_FIGHT);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setDualFight(_object,element_,_document);
        return element_;
    }

    private static void setDualFight(DualFight _object, Element _element, Document _document) {
        _element.appendChild(setAlly(_object.getAlly(),FIELD_ALLY,_document));
        _element.appendChild(setTempTrainer(_object.getFoeTrainer(),FIELD_FOE_TRAINER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getNames(),FIELD_NAMES,_document));
        _element.appendChild(setPoint(_object.getPt(),FIELD_PT,_document));
    }

    private static void setGerantPokemon(GerantPokemon _object, Element _element, Document _document) {
        _element.appendChild(setGeranceType(_object.getGerance(),FIELD_GERANCE,_document));
        setPerson(_object, _element, _document);
    }

    private static Element setGymLeader(GymLeader _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_GYM_LEADER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGymLeader(_object,element_,_document);
        return element_;
    }

    private static void setGymLeader(GymLeader _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getTm(),FIELD_TM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        setTrainerOneFight(_object, _element, _document);
    }

    private static Element setGymTrainer(GymTrainer _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_GYM_TRAINER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setGymTrainer(_object,element_,_document);
        return element_;
    }

    private static void setGymTrainer(GymTrainer _object, Element _element, Document _document) {
        setTrainerOneFight(_object, _element, _document);
    }

    private static Element setPerson(Person _object, String _fieldName, Document _document) {
        if (_object instanceof DealerItem) {
            Element element_ = _document.createElement(TYPE_DEALER_ITEM);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setDealerItem((DealerItem)_object,element_,_document);
            return element_;
        }
        if (_object instanceof GerantPokemon) {
            Element element_ = _document.createElement(TYPE_GERANT_POKEMON);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setGerantPokemon((GerantPokemon)_object,element_,_document);
            return element_;
        }
        if (_object instanceof GymLeader) {
            Element element_ = _document.createElement(TYPE_GYM_LEADER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setGymLeader((GymLeader)_object,element_,_document);
            return element_;
        }
        if (_object instanceof GymTrainer) {
            Element element_ = _document.createElement(TYPE_GYM_TRAINER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setGymTrainer((GymTrainer)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Seller) {
            Element element_ = _document.createElement(TYPE_SELLER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setSeller((Seller)_object,element_,_document);
            return element_;
        }
        if (_object instanceof TempTrainer) {
            Element element_ = _document.createElement(TYPE_TEMP_TRAINER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setTempTrainer((TempTrainer)_object,element_,_document);
            return element_;
        }
        if (_object instanceof TrainerLeague) {
            Element element_ = _document.createElement(TYPE_TRAINER_LEAGUE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setTrainerLeague((TrainerLeague)_object,element_,_document);
            return element_;
        }
        if (_object instanceof TrainerMultiFights) {
            Element element_ = _document.createElement(TYPE_TRAINER_MULTI_FIGHTS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setTrainerMultiFights((TrainerMultiFights)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PERSON);
    }

    private static void setPerson(Person _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getImageMiniFileName(),FIELD_IMAGE_MINI_FILE_NAME,_document));
    }

    private static void setSeller(Seller _object, Element _element, Document _document) {
        _element.appendChild(setSellType(_object.getSell(),FIELD_SELL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getItems(),FIELD_ITEMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getTm(),FIELD_TM,_document));
        setPerson(_object, _element, _document);
    }

    private static Element setTempTrainer(TempTrainer _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TEMP_TRAINER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTempTrainer(_object,element_,_document);
        return element_;
    }

    private static void setTempTrainer(TempTrainer _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getImageMiniSecondTrainerFileName(),FIELD_IMAGE_MINI_SECOND_TRAINER_FILE_NAME,_document));
        setTrainerOneFight(_object, _element, _document);
    }

    private static void setTrainer(Trainer _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getMultiplicityFight(),FIELD_MULTIPLICITY_FIGHT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getImageMaxiFileName(),FIELD_IMAGE_MAXI_FILE_NAME,_document));
        setPerson(_object, _element, _document);
    }

    private static Element setTrainerLeague(TrainerLeague _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TRAINER_LEAGUE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrainerLeague(_object,element_,_document);
        return element_;
    }

    private static void setTrainerLeague(TrainerLeague _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        setTrainerOneFight(_object, _element, _document);
    }

    private static void setTrainerMultiFights(TrainerMultiFights _object, Element _element, Document _document) {
        _element.appendChild(setListPokemonTeam(_object.getTeamsRewards(),FIELD_TEAMS_REWARDS,_document));
        setTrainer(_object, _element, _document);
    }

    private static void setTrainerOneFight(TrainerOneFight _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getReward(),FIELD_REWARD,_document));
        _element.appendChild(setListPkTrainer(_object.getTeam(),FIELD_TEAM,_document));
        setTrainer(_object, _element, _document);
    }

    private static Element setGeranceType(GeranceType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_GERANCE_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setSellType(SellType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_SELL_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setDirection(Direction _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_DIRECTION);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setAreaApparition(AreaApparition _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_AREA_APPARITION);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setAreaApparition(_object,element_,_document);
        return element_;
    }

    private static void setAreaApparition(AreaApparition _object, Element _element, Document _document) {
        _element.appendChild(setListWildPk(_object.getWildPokemon(),FIELD_WILD_POKEMON,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getAvgNbSteps(),FIELD_AVG_NB_STEPS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setByte(_object.getMultFight(),FIELD_MULT_FIGHT,_document));
        _element.appendChild(setListWildPk(_object.getWildPokemonFishing(),FIELD_WILD_POKEMON_FISHING,_document));
    }

    private static Element setBlock(Block _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_BLOCK);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setBlock(_object,element_,_document);
        return element_;
    }

    private static void setBlock(Block _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getWidth(),FIELD_WIDTH,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getHeight(),FIELD_HEIGHT,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getIndexApparition(),FIELD_INDEX_APPARITION,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getTileFileName(),FIELD_TILE_FILE_NAME,_document));
        _element.appendChild(setEnvironmentType(_object.getType(),FIELD_TYPE,_document));
    }

    private static void setLevel(Level _object, Element _element, Document _document) {
        _element.appendChild(setMapPointBlock(_object.getBlocks(),FIELD_BLOCKS,_document));
    }

    private static Element setLevelCave(LevelCave _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_LEVEL_CAVE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelCave(_object,element_,_document);
        return element_;
    }

    private static void setLevelCave(LevelCave _object, Element _element, Document _document) {
        _element.appendChild(setMapPointLink(_object.getLinksOtherLevels(),FIELD_LINKS_OTHER_LEVELS,_document));
        setLevelWithWildPokemon(_object, _element, _document);
    }

    private static Element setLevelIndoorGym(LevelIndoorGym _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_LEVEL_INDOOR_GYM);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelIndoorGym(_object,element_,_document);
        return element_;
    }

    private static void setLevelIndoorGym(LevelIndoorGym _object, Element _element, Document _document) {
        _element.appendChild(setMapPointGymTrainer(_object.getGymTrainers(),FIELD_GYM_TRAINERS,_document));
        _element.appendChild(setPoint(_object.getGymLeaderCoords(),FIELD_GYM_LEADER_COORDS,_document));
        _element.appendChild(setGymLeader(_object.getGymLeader(),FIELD_GYM_LEADER,_document));
        setLevel(_object, _element, _document);
    }

    private static Element setLevelIndoorPokemonCenter(LevelIndoorPokemonCenter _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_LEVEL_INDOOR_POKEMON_CENTER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelIndoorPokemonCenter(_object,element_,_document);
        return element_;
    }

    private static void setLevelIndoorPokemonCenter(LevelIndoorPokemonCenter _object, Element _element, Document _document) {
        _element.appendChild(setMapPointPerson(_object.getGerants(),FIELD_GERANTS,_document));
        _element.appendChild(setPoint(_object.getStorageCoords(),FIELD_STORAGE_COORDS,_document));
        setLevel(_object, _element, _document);
    }

    private static Element setLevelLeague(LevelLeague _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_LEVEL_LEAGUE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelLeague(_object,element_,_document);
        return element_;
    }

    private static void setLevelLeague(LevelLeague _object, Element _element, Document _document) {
        _element.appendChild(setPoint(_object.getTrainerCoords(),FIELD_TRAINER_COORDS,_document));
        _element.appendChild(setTrainerLeague(_object.getTrainer(),FIELD_TRAINER,_document));
        _element.appendChild(setPoint(_object.getAccessPoint(),FIELD_ACCESS_POINT,_document));
        _element.appendChild(setPoint(_object.getNextLevelTarget(),FIELD_NEXT_LEVEL_TARGET,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFileName(),FIELD_FILE_NAME,_document));
        setLevel(_object, _element, _document);
    }

    private static Element setLevelOutdoor(LevelOutdoor _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_LEVEL_OUTDOOR);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelOutdoor(_object,element_,_document);
        return element_;
    }

    private static void setLevelOutdoor(LevelOutdoor _object, Element _element, Document _document) {
        setLevel(_object, _element, _document);
    }

    private static Element setLevelRoad(LevelRoad _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_LEVEL_ROAD);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelRoad(_object,element_,_document);
        return element_;
    }

    private static void setLevelRoad(LevelRoad _object, Element _element, Document _document) {
        setLevelWithWildPokemon(_object, _element, _document);
    }

    private static void setLevelWithWildPokemon(LevelWithWildPokemon _object, Element _element, Document _document) {
        _element.appendChild(setListAreaApparition(_object.getWildPokemonAreas(),FIELD_WILD_POKEMON_AREAS,_document));
        _element.appendChild(setMapPointCharacterInRoadCave(_object.getCharacters(),FIELD_CHARACTERS,_document));
        _element.appendChild(setMapPointDualFight(_object.getDualFights(),FIELD_DUAL_FIGHTS,_document));
        _element.appendChild(setMapPointWildPk(_object.getLegendaryPks(),FIELD_LEGENDARY_PKS,_document));
        _element.appendChild(setMapPointString(_object.getItems(),FIELD_ITEMS,_document));
        _element.appendChild(setMapPointShort(_object.getTm(),FIELD_TM,_document));
        _element.appendChild(setMapPointShort(_object.getHm(),FIELD_HM,_document));
        setLevel(_object, _element, _document);
    }

    private static Element setLink(Link _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LINK);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setEnvironmentType(EnvironmentType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_ENVIRONMENT_TYPE);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static void setCave(Cave _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(setMapByteLevelCave(_object.getLevels(),FIELD_LEVELS,_document));
        _element.appendChild(setMapLevelPointLink(_object.getLinksWithOtherPlaces(),FIELD_LINKS_WITH_OTHER_PLACES,_document));
    }

    private static void setCity(City _object, Element _element, Document _document) {
        _element.appendChild(setMapPointBuilding(_object.getBuildings(),FIELD_BUILDINGS,_document));
        _element.appendChild(setLevelOutdoor(_object.getLevelOutdoor(),FIELD_LEVEL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(setMapPlaceInterConnectCoords(_object.getSavedlinks(),FIELD_SAVEDLINKS,_document));
        _element.appendChild(setMapPointLink(_object.getLinksWithCaves(),FIELD_LINKS_WITH_CAVES,_document));
    }

    private static void setLeague(League _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(setListLevelLeague(_object.getRooms(),FIELD_ROOMS,_document));
        _element.appendChild(setCoords(_object.getAccessCoords(),FIELD_ACCESS_COORDS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFileName(),FIELD_FILE_NAME,_document));
        _element.appendChild(setPoint(_object.getBegin(),FIELD_BEGIN,_document));
    }

    private static Element setPlace(Place _object, String _fieldName, Document _document) {
        if (_object instanceof Cave) {
            Element element_ = _document.createElement(TYPE_CAVE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setCave((Cave)_object,element_,_document);
            return element_;
        }
        if (_object instanceof City) {
            Element element_ = _document.createElement(TYPE_CITY);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setCity((City)_object,element_,_document);
            return element_;
        }
        if (_object instanceof League) {
            Element element_ = _document.createElement(TYPE_LEAGUE);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setLeague((League)_object,element_,_document);
            return element_;
        }
        if (_object instanceof Road) {
            Element element_ = _document.createElement(TYPE_ROAD);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setRoad((Road)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_PLACE);
    }

    private static void setRoad(Road _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(setLevelRoad(_object.getLevelRoad(),FIELD_LEVEL,_document));
        _element.appendChild(setMapPointLink(_object.getLinksWithCaves(),FIELD_LINKS_WITH_CAVES,_document));
        _element.appendChild(setMapPlaceInterConnectCoords(_object.getSavedlinks(),FIELD_SAVEDLINKS,_document));
    }

    private static Element setEgg(Egg _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_EGG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setPkTrainer(PkTrainer _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_PK_TRAINER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPkTrainer(_object,element_,_document);
        return element_;
    }

    private static void setPkTrainer(PkTrainer _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getLevel(),FIELD_LEVEL,_document));
        _element.appendChild(setGender(_object.getGender(),FIELD_GENDER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getAbility(),FIELD_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getItem(),FIELD_ITEM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMoves(),FIELD_MOVES,_document));
        setPokemon(_object, _element, _document);
    }

    private static void setPokemon(Pokemon _object, Element _element, Document _document) {
    }

    public static Element setPokemonPlayer(PokemonPlayer _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_POKEMON_PLAYER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPokemonPlayer(_object,element_,_document);
        return element_;
    }

    private static void setPokemonPlayer(PokemonPlayer _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getLevel(),FIELD_LEVEL,_document));
        _element.appendChild(setGender(_object.getGender(),FIELD_GENDER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getAbility(),FIELD_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getItem(),FIELD_ITEM,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getRemainingHp(),FIELD_REMAINING_HP,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getStatus(),FIELD_STATUS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getNickname(),FIELD_NICKNAME,_document));
        _element.appendChild(setStringMapUsesOfMove(_object.getMoves(),FIELD_MOVES,_document));
        _element.appendChild(setMapStatisticShort(_object.getEv(),FIELD_EV,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getWonExpSinceLastLevel(),FIELD_WON_EXP_SINCE_LAST_LEVEL,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getHappiness(),FIELD_HAPPINESS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getUsedBallCatching(),FIELD_USED_BALL_CATCHING,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getNbStepsTeamLead(),FIELD_NB_STEPS_TEAM_LEAD,_document));
        setPokemon(_object, _element, _document);
    }

    private static Element setPokemonTeam(PokemonTeam _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_POKEMON_TEAM);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPokemonTeam(_object,element_,_document);
        return element_;
    }

    private static void setPokemonTeam(PokemonTeam _object, Element _element, Document _document) {
        _element.appendChild(setListPkTrainer(_object.getTeam(),FIELD_TEAM,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getReward(),FIELD_REWARD,_document));
    }

    private static Element setUsablePokemon(UsablePokemon _object, String _fieldName, Document _document) {
        if (_object instanceof Egg) {
            return setEgg((Egg)_object,_fieldName,_document);
        }
        if (_object instanceof PokemonPlayer) {
            Element element_ = _document.createElement(TYPE_POKEMON_PLAYER);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setPokemonPlayer((PokemonPlayer)_object,element_,_document);
            return element_;
        }
        return _document.createElement(TYPE_USABLE_POKEMON);
    }

    private static Element setWildPk(WildPk _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_WILD_PK);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setWildPk(_object,element_,_document);
        return element_;
    }

    private static void setWildPk(WildPk _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getName(),FIELD_NAME,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getLevel(),FIELD_LEVEL,_document));
        _element.appendChild(setGender(_object.getGender(),FIELD_GENDER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getAbility(),FIELD_ABILITY,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getItem(),FIELD_ITEM,_document));
        setPokemon(_object, _element, _document);
    }

    private static Element setGender(Gender _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_GENDER);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE,_object.name());
        return elt_;
    }

    private static Element setMiniMapCoords(MiniMapCoords _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MINI_MAP_COORDS);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setPlaceInterConnect(PlaceInterConnect _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_PLACE_INTER_CONNECT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setTileMiniMap(TileMiniMap _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_TILE_MINI_MAP);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTileMiniMap(_object,element_,_document);
        return element_;
    }

    private static void setTileMiniMap(TileMiniMap _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getFile(),FIELD_FILE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setShort(_object.getPlace(),FIELD_PLACE,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.isHeros(),FIELD_HEROS,_document));
    }

    private static Element setCoords(Coords _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_COORDS);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setLevelPoint(LevelPoint _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LEVEL_POINT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setPoint(Point _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_POINT);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(ATTR_VALUE, _object.display());
        return elt_;
    }

    private static Element setMonteCarloEnumStatistic(MonteCarloEnum<Statistic> _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(TYPE_MONTE_CARLO);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        element_.appendChild(setMapStatisticLgInt(_object.getLaw(),FIELD_LAW,_document));
        return element_;
    }

    private static Element setMapStatisticLgInt(AbsMap<Statistic,LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Statistic, LgInt> s: _object.entryList()) {
            Element sub_ = setStatistic(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setLgInt(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setListEffectWhileSending(CustList<EffectWhileSendingWithStatistic> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectWhileSendingWithStatistic s: _object) {
            elt_.appendChild(setEffectWhileSending(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffect(CustList<Effect> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Effect s: _object) {
            elt_.appendChild(setEffect(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectEndRound(CustList<EffectEndRound> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectEndRound s: _object) {
            elt_.appendChild(setEffectEndRound(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectEndRoundFoe(CustList<EffectEndRoundFoe> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectEndRoundFoe s: _object) {
            elt_.appendChild(setEffectEndRoundFoe(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectEndRoundStatus(CustList<EffectEndRoundStatus> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectEndRoundStatus s: _object) {
            elt_.appendChild(setEffectEndRoundStatus(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectTeam(CustList<EffectTeam> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectTeam s: _object) {
            elt_.appendChild(setEffectTeam(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectPartnerStatus(CustList<EffectPartnerStatus> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectPartnerStatus s: _object) {
            elt_.appendChild(setEffectPartnerStatus(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListAreaApparition(CustList<AreaApparition> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (AreaApparition s: _object) {
            elt_.appendChild(setAreaApparition(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListLevelLeague(CustList<LevelLeague> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LevelLeague s: _object) {
            elt_.appendChild(setLevelLeague(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListPkTrainer(CustList<PkTrainer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (PkTrainer s: _object) {
            elt_.appendChild(setPkTrainer(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListPokemonTeam(CustList<PokemonTeam> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (PokemonTeam s: _object) {
            elt_.appendChild(setPokemonTeam(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListUsablePokemon(CustList<UsablePokemon> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (UsablePokemon s: _object) {
            elt_.appendChild(setUsablePokemon(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListStatistic(EnumList<Statistic> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Statistic s: _object) {
            elt_.appendChild(setStatistic(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setMapStatisticBoostHpRate(EnumMap<Statistic,BoostHpRate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Statistic, BoostHpRate> s: _object.entryList()) {
            Element sub_ = setStatistic(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBoostHpRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticStatBaseEv(EnumMap<Statistic,StatBaseEv> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Statistic, StatBaseEv> s: _object.entryList()) {
            Element sub_ = setStatistic(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setStatBaseEv(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticRate(EnumMap<Statistic,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Statistic, Rate> s: _object.entryList()) {
            Element sub_ = setStatistic(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticByte(EnumMap<Statistic,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Statistic, Byte> s: _object.entryList()) {
            Element sub_ = setStatistic(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticShort(EnumMap<Statistic,Short> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Statistic, Short> s: _object.entryList()) {
            Element sub_ = setStatistic(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setShort(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticString(EnumMap<Statistic,String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Statistic, String> s: _object.entryList()) {
            Element sub_ = setStatistic(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setString(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapEnvironmentTypeString(EnumMap<EnvironmentType,String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<EnvironmentType, String> s: _object.entryList()) {
            Element sub_ = setEnvironmentType(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setString(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setListLevelMove(CustList<LevelMove> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LevelMove s: _object) {
            elt_.appendChild(setLevelMove(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListStatisticStatus(EqList<StatisticStatus> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticStatus s: _object) {
            elt_.appendChild(setStatisticStatus(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListTypesDuo(EqList<TypesDuo> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TypesDuo s: _object) {
            elt_.appendChild(setTypesDuo(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListTargetCoords(EqList<TargetCoords> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TargetCoords s: _object) {
            elt_.appendChild(setTargetCoords(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListWildPk(CustList<WildPk> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (WildPk s: _object) {
            elt_.appendChild(setWildPk(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCoords(EqList<Coords> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Coords s: _object) {
            elt_.appendChild(setCoords(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListPoint(EqList<Point> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_LIST);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Point s: _object) {
            elt_.appendChild(setPoint(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setMapBytePokemonPlayer(ByteTreeMap<PokemonPlayer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, PokemonPlayer> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setPokemonPlayer(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapByteAnticipation(ByteMap<Anticipation> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, Anticipation> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setAnticipation(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapByteChoiceOfEvolutionAndMoves(ByteMap<ChoiceOfEvolutionAndMoves> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, ChoiceOfEvolutionAndMoves> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setChoiceOfEvolutionAndMoves(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapByteFighter(ByteMap<Fighter> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, Fighter> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setFighter(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapByteStacksOfUses(ByteMap<StacksOfUses> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, StacksOfUses> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setStacksOfUses(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapByteTeam(ByteMap<Team> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Byte, Team> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setByte(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setTeam(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapByteLevelCave(CustList<LevelCave> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LevelCave s: _object) {
            Element sub_ = setLevelCave(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapShortPlace(CustList<Place> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Place s: _object) {
            Element sub_ = setPlace(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapShortListPoint(ShortMap<EqList<Point>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Short, EqList<Point>> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setShort(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListPoint(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCategoryMultRate(ObjectMap<CategoryMult,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<CategoryMult, Rate> s: _object.entryList()) {
            Element sub_ = setCategoryMult(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticCategoryRate(ObjectMap<StatisticCategory,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<StatisticCategory, Rate> s: _object.entryList()) {
            Element sub_ = setStatisticCategory(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticCategoryByte(ObjectMap<StatisticCategory,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<StatisticCategory, Byte> s: _object.entryList()) {
            Element sub_ = setStatisticCategory(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticPokemonByte(ObjectMap<StatisticPokemon,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<StatisticPokemon, Byte> s: _object.entryList()) {
            Element sub_ = setStatisticPokemon(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticStatusByte(ObjectMap<StatisticStatus,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<StatisticStatus, Byte> s: _object.entryList()) {
            Element sub_ = setStatisticStatus(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticTypeRate(ObjectMap<StatisticType,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<StatisticType, Rate> s: _object.entryList()) {
            Element sub_ = setStatisticType(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticTypeByte(ObjectMap<StatisticType,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<StatisticType, Byte> s: _object.entryList()) {
            Element sub_ = setStatisticType(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapTypesDuoRate(ObjectMap<TypesDuo,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<TypesDuo, Rate> s: _object.entryList()) {
            Element sub_ = setTypesDuo(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapWeatherTypeRate(ObjectMap<WeatherType,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<WeatherType, Rate> s: _object.entryList()) {
            Element sub_ = setWeatherType(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapNbFightCoordsBoolean(ObjectMap<NbFightCoords,Boolean> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<NbFightCoords, Boolean> s: _object.entryList()) {
            Element sub_ = setNbFightCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionActivityOfMove(ObjectMap<MoveTeamPosition,ActivityOfMove> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<MoveTeamPosition, ActivityOfMove> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setActivityOfMove(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionAffectedMove(ObjectMap<MoveTeamPosition,AffectedMove> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<MoveTeamPosition, AffectedMove> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setAffectedMove(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionStringList(ObjectMap<MoveTeamPosition,StringList> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<MoveTeamPosition, StringList> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setStringList(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionBoolean(ObjectMap<MoveTeamPosition,Boolean> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<MoveTeamPosition, Boolean> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionShort(ObjectMap<MoveTeamPosition,Short> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<MoveTeamPosition, Short> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setShort(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTargetMoveTarget(ObjectMap<MoveTarget,MoveTarget> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<MoveTarget, MoveTarget> s: _object.entryList()) {
            Element sub_ = setMoveTarget(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setMoveTarget(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMiniMapCoordsTileMiniMap(ObjectMap<MiniMapCoords,TileMiniMap> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<MiniMapCoords, TileMiniMap> s: _object.entryList()) {
            Element sub_ = setMiniMapCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setTileMiniMap(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPlaceInterConnectCoords(ObjectMap<PlaceInterConnect,Coords> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<PlaceInterConnect, Coords> s: _object.entryList()) {
            Element sub_ = setPlaceInterConnect(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setCoords(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCoordsHostPokemonDuo(ObjectMap<Coords,HostPokemonDuo> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Coords, HostPokemonDuo> s: _object.entryList()) {
            Element sub_ = setCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setHostPokemonDuo(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCoordsListCoords(ObjectMap<Coords,EqList<Coords>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Coords, EqList<Coords>> s: _object.entryList()) {
            Element sub_ = setCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListCoords(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCoordsBoolean(ObjectMap<Coords,Boolean> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Coords, Boolean> s: _object.entryList()) {
            Element sub_ = setCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapLevelPointLink(ObjectMap<LevelPoint,Link> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<LevelPoint, Link> s: _object.entryList()) {
            Element sub_ = setLevelPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLink(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointBuilding(ObjectMap<Point,Building> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, Building> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBuilding(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointCharacterInRoadCave(ObjectMap<Point,CharacterInRoadCave> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, CharacterInRoadCave> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setCharacterInRoadCave(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointDualFight(ObjectMap<Point,DualFight> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, DualFight> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setDualFight(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointGymTrainer(ObjectMap<Point,GymTrainer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, GymTrainer> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setGymTrainer(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointPerson(ObjectMap<Point,Person> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, Person> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setPerson(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointBlock(ObjectMap<Point,Block> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, Block> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBlock(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointLink(ObjectMap<Point,Link> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, Link> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLink(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointWildPk(ObjectMap<Point,WildPk> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, WildPk> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setWildPk(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointShort(ObjectMap<Point,Short> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, Short> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setShort(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointString(ObjectMap<Point,String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Point, String> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setString(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStringListEffectCombo(CustList<ListEffectCombo> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (ListEffectCombo s: _object) {
            Element sub_ = DocumentWriterCoreUtil.setStringList(s.getList(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setEffectCombo(s.getCombo(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStringListActivityOfMove(CustList<ListActivityOfMove> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (ListActivityOfMove s: _object) {
            Element sub_ = DocumentWriterCoreUtil.setStringList(s.getList(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setActivityOfMove(s.getCombo(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    public static Element setStringMapGenderRepartition(StringMap<GenderRepartition> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, GenderRepartition> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setGenderRepartition(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapEvolution(StringMap<Evolution> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, Evolution> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setEvolution(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapEfficiencyRate(StringMap<EfficiencyRate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, EfficiencyRate> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setEfficiencyRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapTypeDamageBoost(StringMap<TypeDamageBoost> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, TypeDamageBoost> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setTypeDamageBoost(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapUsesOfMove(StringMap<UsesOfMove> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, UsesOfMove> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setUsesOfMove(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapActivityOfMove(StringMap<ActivityOfMove> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, ActivityOfMove> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setActivityOfMove(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapCopiedMove(StringMap<CopiedMove> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, CopiedMove> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setCopiedMove(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapMovesAbilities(StringMap<MovesAbilities> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, MovesAbilities> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setMovesAbilities(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapListStatistic(StringMap<EnumList<Statistic>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, EnumList<Statistic>> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListStatistic(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapMapStatisticByte(StringMap<EnumMap<Statistic,Byte>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, EnumMap<Statistic,Byte>> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setMapStatisticByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapMapByteAnticipation(StringMap<ByteMap<Anticipation>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, ByteMap<Anticipation>> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setMapByteAnticipation(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapMapByteStacksOfUses(StringMap<ByteMap<StacksOfUses>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(TYPE_MAP);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, ByteMap<StacksOfUses>> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setMapByteStacksOfUses(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

}
