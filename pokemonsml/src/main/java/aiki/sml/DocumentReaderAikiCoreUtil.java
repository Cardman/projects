package aiki.sml;
import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.db.Resources;
import aiki.facade.FacadeGame;
import aiki.fight.Combos;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.effects.EffectWhileSendingSimple;
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
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.CategoryMult;
import aiki.fight.util.EfficiencyRate;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticPokemon;
import aiki.fight.util.StatisticStatus;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypeDamageBoost;
import aiki.fight.util.TypesDuo;
import aiki.fight.util.WeatherType;
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
import aiki.game.fight.actions.Action;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionHealMove;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ActionSimpleHeal;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.enums.FightType;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.MoveTarget;
import aiki.game.fight.util.MovesAbilities;
import aiki.game.params.Difficulty;
import aiki.game.params.LoadingGame;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Inventory;
import aiki.game.player.Player;
import aiki.game.player.enums.Sex;
import aiki.instances.Instances;
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
import aiki.map.characters.Healer;
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
import aiki.map.places.*;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.Pokemon;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.UsablePokemon;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.util.Dims;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.PlaceInterConnect;
import aiki.map.util.ScreenCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.Coords;
import aiki.util.LawNumber;
import aiki.util.LevelPoint;
import aiki.util.Point;
import code.images.BaseSixtyFourUtil;
import code.images.Image;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloEnum;
import code.maths.montecarlo.MonteCarloNumber;
import code.resources.ResourceFiles;
import code.sml.maths.DocumentReaderMathUtil;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentReaderCoreUtil;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.util.ExtractFromFiles;
import code.util.*;
import code.util.consts.Constants;
import code.util.pagination.SelectedBoolean;

import java.util.concurrent.atomic.AtomicInteger;

public final class DocumentReaderAikiCoreUtil {


    public static final String VAR_PREFIX = "VAR__";

    public static final String EMPTY_STRING = "";
    public static final String MIN_BOOST = "MIN_BOOST";
    public static final String MAX_BOOST = "MAX_BOOST";
    public static final String VALEUR_DEF_STATIS = "VALEUR_DEF_STATIS";
    public static final String NIVEAU_PK_ECLOSION = "NIVEAU_PK_ECLOSION";
    public static final short INVALID_LEVEL = -1;
    public static final String PAS_NECES_INCREMENT_BONHEUR = "PAS_NECES_INCREMENT_BONHEUR";
    public static final String PP_MAX = "PP_MAX";
    public static final String DEF_PKEQ = "DEF_PKEQ";
    public static final String MAX_EV = "MAX_EV";
    public static final String MAX_IV = "MAX_IV";
    public static final String NIVEAU_PK_MAX = "NIVEAU_PK_MAX";
    public static final String GAIN_BONHEUR_NIV = "GAIN_BONHEUR_NIV";
    public static final String EVO_BONHEUR = "EVO_BONHEUR";
    public static final String MAX_BONHEUR = "MAX_BONHEUR";
    public static final String ARGENT = "ARGENT";
    public static final String STRONG_MOVE = "STRONG_MOVE";
    public static final String SEPARATOR_MOVES = ";";
    public static final String SEPARATOR_RAND = ";";
    public static final String SEPARATOR_RGB = ";";
    public static final String SEPARATOR_RAND_EVENTS = " ";
    public static final String DEF_MOVE = "DEF_MOVE";
    public static final String RATE_CATCHING = "RATE_CATCHING";
    public static final String RATE_FLEEING = "RATE_FLEEING";
    public static final String RATE_BOOST = "RATE_BOOST";
    public static final String RATE_BOOST_CRITICAL_HIT = "RATE_BOOST_CRITICAL_HIT";
    public static final String DAMAGE_FORMULA = "DAMAGE_FORMULA";
    public static final String DEFAULT_EGG_GROUP = "DEFAULT_EGG_GROUP";
    public static final String MAX_STEPS_SAME_EVO_BASE = "MAX_STEPS_SAME_EVO_BASE";
    public static final String MAX_STEPS = "MAX_STEPS";
    public static final String MIN_HP = "MIN_HP";
    public static final String BONUS_BOOST = "BONUS_BOOST";

    public static final String SEP_BETWEEN_KEYS = "__";
    public static final String ZIP_FILES_EXT = ".zip";
    public static final String IMG_FILES_RES_EXT = ".png";
    public static final String IMG_FILES_RES_EXT_TXT = ".txt";
    public static final String FILES_RES_EXT = ".xml";
    public static final String DASH_FILE_INFO = "-";
    /**
     * The custom beans can be modified but they must have a common base package
     * Avoid to recompile classes in standard packages like java, javax, and
     * even projects core, gui ...
     */

    public static final int MAX_MULT_FIGHT = 4;
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
    public static final int ONE_POSSIBLE_CHOICE = 1;
    public static final String AUTRE = "AUTRE";
    public static final String WEB_FOLDER = "web";
    public static final String WEB_FIGHT = "web_fight";
    public static final String WEB_GAME = "web_game";
    public static final String WEB_PROG = "web_prog";
    public static final String WEB_PK = "web_pk";
    public static final String BEANS_FOLDER = "java_beans";

    public static final String ANIM_STATIS = "anim_statis";

    public static final String ANIM_STATUS = "anim_status";

    public static final String ANIM_ABSORB = "anim_absorb/absorb.txt";

    private static final int DEFAULT_POWER_INT = 80;

    private static final int DEFAULT_HEAL_RATE_NUM = 1;
    private static final int DEFAULT_HEAL_RATE_DEN = 2;

    private static final int DEFAULT_INFLICTED_RATE_NUM = 1;
    private static final int DEFAULT_INFLICTED_RATE_DEN = 8;

    private static final String POKEDEX_FOLDER = "pokedex";
    private static final String MOVES_FOLDER = "moves";
    private static final String ABILITIES_FOLDER = "abilities";
    private static final String STATUS_FOLDER = "status";

    private static final String ITEMS_FOLDER = "items";
    private static final String SEPARATOR_KEY_HEROS = ";";

    private static final char TAB_CHAR = '\t';
    private static final String TAB = "\t";

    private static final char RETURN_LINE_CHAR = '\n';
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

    private static final String DEF_MAX_ATT = "DEF_MAX_ATT";
    private static final String BALL_DEF = "BALL_DEF";

    private static final String MOVE_FORMULA = "move";
    private static final String CAT_FORMULA = "cat";
    private static final String STATIS_FORMULA = "statis";
    private static final String STATUS_FORMULA = "status";
    private static final String TYPE_FORMULA = "type";

    private static final char UNDERSCORE = '_';

    private static final String ATTR_FIELD = "field";
    private static final String ATTR_VALUE = "value";
    private static final char DOT = '.';
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
    private static final String TYPE_ACTION = "Action";
    private static final String TYPE_ACTION_HEAL_MOVE = "ActionHealMove";
    private static final String TYPE_ACTION_MOVE = "ActionMove";
    private static final String TYPE_ACTION_SIMPLE_HEAL = "ActionSimpleHeal";
    private static final String TYPE_ACTION_SWITCH = "ActionSwitch";
    private static final String TYPE_BALL = "Ball";
    private static final String TYPE_BERRY = "Berry";
    private static final String TYPE_BOOST = "Boost";
    private static final String TYPE_CAVE = "Cave";
    private static final String TYPE_CITY = "City";
    private static final String TYPE_DAMAGING_MOVE_DATA = "DamagingMoveData";
    private static final String TYPE_DEALER_ITEM = "DealerItem";
    private static final String TYPE_EFFECT_ACCURACY = "EffectAccuracy";
    private static final String TYPE_EFFECT_ALLY = "EffectAlly";
    private static final String TYPE_EFFECT_BATON_PASS = "EffectBatonPass";
    private static final String TYPE_EFFECT_CLONE = "EffectClone";
    private static final String TYPE_EFFECT_COMMON_STATISTICS = "EffectCommonStatistics";
    private static final String TYPE_EFFECT_COPY_FIGHTER = "EffectCopyFighter";
    private static final String TYPE_EFFECT_COPY_MOVE = "EffectCopyMove";
    private static final String TYPE_EFFECT_COUNTER_ATTACK = "EffectCounterAttack";
    private static final String TYPE_EFFECT_DAMAGE = "EffectDamage";
    private static final String TYPE_EFFECT_DAMAGE_RATE = "EffectDamageRate";
    private static final String TYPE_EFFECT_END_ROUND_FOE = "EffectEndRoundFoe";
    private static final String TYPE_EFFECT_END_ROUND_GLOBAL = "EffectEndRoundGlobal";
    private static final String TYPE_EFFECT_END_ROUND_INDIVIDUAL = "EffectEndRoundIndividual";
    private static final String TYPE_EFFECT_END_ROUND_MULTI_RELATION = "EffectEndRoundMultiRelation";
    private static final String TYPE_EFFECT_END_ROUND_POSITION_RELATION = "EffectEndRoundPositionRelation";
    private static final String TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION = "EffectEndRoundPositionTargetRelation";
    private static final String TYPE_EFFECT_END_ROUND_SINGLE_RELATION = "EffectEndRoundSingleRelation";
    private static final String TYPE_EFFECT_END_ROUND_SINGLE_STATUS = "EffectEndRoundSingleStatus";
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
    private static final String TYPE_EFFECT_WHILE_SENDING_SIMPLE = "EffectWhileSending";
    private static final String TYPE_EFFECT_WHILE_SENDING_WITH_STATISTIC = "EffectWhileSendingWithStatistic";
    private static final String TYPE_EFFECT_WIN_MONEY = "EffectWinMoney";
    private static final String TYPE_EGG = "Egg";
    private static final String TYPE_EVOLUTION_HAPPINESS = "EvolutionHappiness";
    private static final String TYPE_EVOLUTION_ITEM = "EvolutionItem";
    private static final String TYPE_EVOLUTION_LEVEL_GENDER = "EvolutionLevelGender";
    private static final String TYPE_EVOLUTION_LEVEL_SIMPLE = "EvolutionLevel";
    private static final String TYPE_EVOLUTION_MOVE = "EvolutionMove";
    private static final String TYPE_EVOLUTION_MOVE_TYPE = "EvolutionMoveType";
    private static final String TYPE_EVOLUTION_STONE_GENDER = "EvolutionStoneGender";
    private static final String TYPE_EVOLUTION_STONE_SIMPLE = "EvolutionStone";
    private static final String TYPE_EVOLUTION_TEAM = "EvolutionTeam";
    private static final String TYPE_EVOLVING_ITEM = "EvolvingItem";
    private static final String TYPE_EVOLVING_STONE = "EvolvingStone";
    private static final String TYPE_FOSSIL = "Fossil";
    private static final String TYPE_GERANT_POKEMON = "GerantPokemon";
    private static final String TYPE_GYM = "Gym";
    private static final String TYPE_GYM_LEADER = "GymLeader";
    private static final String TYPE_GYM_TRAINER = "GymTrainer";
    private static final String TYPE_HEALER = "Healer";
    private static final String TYPE_HEALING_HP = "HealingHp";
    private static final String TYPE_HEALING_HP_STATUS = "HealingHpStatus";
    private static final String TYPE_HEALING_PP = "HealingPp";
    private static final String TYPE_HEALING_SIMPLE_ITEM = "HealingItem";
    private static final String TYPE_HEALING_SIMPLE_STATUS = "HealingStatus";
    private static final String TYPE_ITEM_FOR_BATTLE = "ItemForBattle";
    private static final String TYPE_LEAGUE = "League";
    private static final String TYPE_POKEMON_CENTER = "PokemonCenter";
    private static final String TYPE_POKEMON_PLAYER = "PokemonPlayer";
    private static final String TYPE_REPEL = "Repel";
    private static final String TYPE_ROAD = "Road";
    private static final String TYPE_SELLER = "Seller";
    private static final String TYPE_SELLING_ITEM = "SellingItem";
    private static final String TYPE_STATUS_BEGIN_ROUND_AUTO_DAMAGE = "StatusBeginRoundAutoDamage";
    private static final String TYPE_STATUS_BEGIN_ROUND_SIMPLE = "StatusBeginRound";
    private static final String TYPE_STATUS_MOVE_DATA = "StatusMoveData";
    private static final String TYPE_STATUS_SIMPLE = "Status";
    private static final String TYPE_TEMP_TRAINER = "TempTrainer";
    private static final String TYPE_TRAINER_LEAGUE = "TrainerLeague";
    private static final String TYPE_TRAINER_MULTI_FIGHTS = "TrainerMultiFights";

    // Load rom option
    public static void loadRomAndCheck(FacadeGame _f,String _fileName,
                                       StringMap<String> _files) {
        DataBase data_ = loadedRom(_f,_files);
        if (data_ == null) {
            _f.setLoadedData(false);
            return;
        }
        if (!_f.isLoading()) {
            return;
        }
        data_.validate(_f.getAtPerCentLoading(), _f.getLoading());
        if (!_f.isLoading() || data_.isError()) {
            if (data_.isError()) {
                _f.setLoadedData(false);
            }
            return;
        }
        data_.initializeWildPokemon();
        _f.setPerCentLoading(99);
        if (!_f.isLoading()) {
            return;
        }
        _f.setZipName(_fileName);
        if (_f.getData() != null) {
            data_.setMessages(_f.getData());
        }
        _f.setData(data_);
        _f.setLoadedData(true);
    }
    // Load rom first
    private static DataBase loadedRom(FacadeGame _f,StringMap<String> _files) {
        DataBase data_ = new DataBase();
        _f.setLoading(true);
        data_.setLanguage(_f.getLanguage());
        loadRom(data_,_files,_f.getAtPerCentLoading());
        if (data_.isError()) {
            return null;
        }
        if (!data_.getMap().validSavedLink()) {
            data_.setError(true);
            return null;

        }
        data_.getMap().initializeLinks();
        return data_;
    }
    public static void loadResources(FacadeGame _f) {
        DataBase data_ = new DataBase();
        _f.setLoading(true);
        data_.setLanguage(_f.getLanguage());
        loadResources(data_,_f.getAtPerCentLoading(), _f.getLanguage());
        if (_f.getData() != null) {
            data_.setMessages(_f.getData());
        }
        _f.setData(data_);
        _f.setLoadedData(true);
        _f.setZipName(DataBase.EMPTY_STRING);
    }
    public static void initMessages(DataBase _d,String _lg) {
        _d.setMessagesPokemonPlayer(ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, PokemonPlayer.POKEMON_PLAYER));
        _d.setMessagesPlayer(ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Player.PLAYER));
        _d.setMessagesFighter(ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Fighter.FIGHTER));
        _d.setMessagesTeam(ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Team.TEAM));
        _d.setMessagesFight(ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Fight.FIGHT));
        _d.setMessagesGame(ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Game.GAME));
    }
    public static void loadRom(DataBase _d,StringMap<String> _files, AtomicInteger _perCentLoading) {
        if (_files == null) {
            _d.setError(true);
            return;
        }
        _perCentLoading.set(0);
        _d.initializeMembers();
        StringMap<String> files_;
        StringList listRelativePaths_;
        String common_ = EMPTY_STRING;
        files_ = _files;
        listRelativePaths_ = files_.getKeys();
        StringList foldersBase_ = new StringList();
        for (String f : listRelativePaths_) {
            StringBuilder str_ = new StringBuilder();
            for (char c : f.toCharArray()) {
                if (!StringList.isWordChar(c)) {
                    break;
                }
                str_.append(c);
            }
            String strFolder_ = str_.toString();
            foldersBase_.add(strFolder_);
        }
        foldersBase_.removeDuplicates();
        if (foldersBase_.size() == 1) {
            common_ = StringList.concat(foldersBase_.first(), SEPARATOR_FILES);
            listRelativePaths_.removePrefixInStrings(common_);
        }
        StringList listCopy_ = new StringList();
        for (String s : listRelativePaths_) {
            listCopy_.add(DataBase.toUpperCase(s));
        }
        int sizeListCopy_ = listCopy_.size();
        listCopy_.removeDuplicates();
        if (!Numbers.eq(listCopy_.size(), sizeListCopy_)) {
            _d.setError(true);
            return;
        }
        _perCentLoading.set(5);
        StringList filesNames_;
        filesNames_ = new StringList();

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(POKEDEX_FOLDER, SEPARATOR_FILES))) {

            String n_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            if (n_.isEmpty()) {
                continue;
            }
            n_ = removeExtension(n_);
            if (!isCorrectIdentifier(n_)) {
                _d.setError(true);
            }
            filesNames_.add(n_);
            PokemonData f_ = DocumentReaderAikiCoreUtil.getPokemonData(notNull(files_,StringList.concat(common_, f)));
            _d.completeMembers(DataBase.toUpperCase(n_), f_);
        }
        _d.calculateAvgPound();
        filesNames_.clear();

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(MOVES_FOLDER, SEPARATOR_FILES))) {

            String n_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            if (n_.isEmpty()) {
                continue;
            }
            n_ = removeExtension(n_);
            if (!isCorrectIdentifier(n_)) {
                _d.setError(true);
            }
            filesNames_.add(n_);
            MoveData move_ = DocumentReaderAikiCoreUtil.getMoveData(notNull(files_,StringList.concat(common_, f)));
            _d.completeMembers(DataBase.toUpperCase(n_), move_);
        }
        _perCentLoading.set(10);
        String fileHmTm_ = notNull(files_,StringList.concat(common_, CT_CS_FILE));
        StringList tmHm_;
        tmHm_ = StringList.splitChars(
                fileHmTm_,
                RETURN_LINE_CHAR);
        for (String l : tmHm_) {
            if (l.startsWith(CT)) {
                StringList infos_ = StringList.splitChars(l, TAB_CHAR);
                short cle_ = (short) Numbers.parseInt(infos_.first().substring(2));
                _d.getTm().put(cle_, infos_.get(1));
                LgInt price_;
                if (LgInt.isValid(infos_.get(2))) {
                    price_ = new LgInt(infos_.get(2));
                } else {
                    price_ = new LgInt(1000);
                }
                _d.getTmPrice().put(cle_, price_);

            }
            if (l.startsWith(CS)) {
                StringList infos_ = StringList.splitChars(l, TAB_CHAR);
                short cle_ = (short) Numbers.parseInt(infos_.first().substring(2));
                _d.getHm().put(cle_, infos_.get(1));
            }
        }
        filesNames_.clear();

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(ITEMS_FOLDER, SEPARATOR_FILES))) {

            String n_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            if (n_.isEmpty()) {
                continue;
            }
            n_ = removeExtension(n_);
            if (!isCorrectIdentifier(n_)) {
                _d.setError(true);
            }
            filesNames_.add(n_);
            Item o_ = DocumentReaderAikiCoreUtil.getItem(notNull(files_,StringList.concat(common_, f)));
            _d.completeMembers(DataBase.toUpperCase(n_), o_);
        }
        filesNames_.clear();

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(ABILITIES_FOLDER, SEPARATOR_FILES))) {

            String n_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            if (n_.isEmpty()) {
                continue;
            }
            n_ = removeExtension(n_);
            if (!isCorrectIdentifier(n_)) {
                _d.setError(true);
            }
            filesNames_.add(n_);
            AbilityData ab_ = DocumentReaderAikiCoreUtil.getAbilityData(notNull(files_,StringList.concat(common_, f)));
            _d.completeMembers(DataBase.toUpperCase(n_), ab_);
        }
        filesNames_.clear();

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(STATUS_FOLDER, SEPARATOR_FILES))) {

            String n_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            if (n_.isEmpty()) {
                continue;
            }
            n_ = removeExtension(n_);
            if (!isCorrectIdentifier(n_)) {
                _d.setError(true);
            }
            filesNames_.add(n_);
            Status st_ = DocumentReaderAikiCoreUtil.getStatus(notNull(files_,StringList.concat(common_, f)));
            _d.completeMembers(DataBase.toUpperCase(n_), st_);
        }
        _perCentLoading.set(15);
        _d.completeVariables();
        filesNames_.clear();
        _d.setImages(new StringMap<int[][]>());
        _d.setImagesTiles(new StringMap<ObjectMap<ScreenCoords, int[][]>>());
        StringList images_;

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            _d.getImages().put(key_, BaseSixtyFourUtil.getImageByString(notNull(files_,StringList.concat(common_, s))));
        }
        filesNames_.clear();

        _d.setMiniMap(new StringMap<int[][]>());
        StringList miniMap_;

        miniMap_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(MINI_MAP_FOLDER, SEPARATOR_FILES));
        for (String s : miniMap_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            _d.getMiniMap().put(key_, BaseSixtyFourUtil.getImageByString(notNull(files_,StringList.concat(common_, s))));
        }

        filesNames_.clear();
        _d.setLinks(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(LINKS_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            _d.getLinks().put(key_, BaseSixtyFourUtil.getImageByString(notNull(files_,StringList.concat(common_, s))));
        }
        filesNames_.clear();
        _d.setPeople(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(PEOPLE_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            _d.getPeople().put(key_, BaseSixtyFourUtil.getImageByString(notNull(files_,StringList.concat(common_, s))));
        }
        filesNames_.clear();
        _d.setFrontHeros(new ObjectMap<ImageHeroKey, int[][]>());
        String frHeros_ = notNull(files_,StringList.concat(
                common_, HERO_FOLDER, SEPARATOR_FILES, HERO_FRONT));
        for (String l : StringList.splitChars(frHeros_,
                RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringList.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = EnvironmentType.getEnvByName(keyStrings_
                    .first());
            Sex sex_ = Sex.getSexByName(keyStrings_.last());
            _d.getFrontHeros().put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        _d.setBackHeros(new ObjectMap<ImageHeroKey, int[][]>());
        String bkHeros_ = notNull(files_,StringList.concat(
                common_, HERO_FOLDER, SEPARATOR_FILES, HERO_BACK));
        for (String l : StringList.splitChars(bkHeros_,
                RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringList.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = EnvironmentType.getEnvByName(keyStrings_
                    .first());
            Sex sex_ = Sex.getSexByName(keyStrings_.last());
            _d.getBackHeros().put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        _d.setOverWorldHeros(new ObjectMap<ImageHeroKey, int[][]>());
        String ovHeros_ = notNull(files_,StringList.concat(
                common_, HERO_FOLDER, SEPARATOR_FILES, HERO_MINI));
        for (String l : StringList.splitChars(ovHeros_,
                RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringList.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = EnvironmentType.getEnvByName(keyStrings_
                    .first());
            Direction dir_ = Direction.getDirectionByName(keyStrings_
                    .get(CustList.SECOND_INDEX));
            Sex sex_ = Sex.getSexByName(keyStrings_.last());
            _d.getOverWorldHeros().put(new ImageHeroKey(env_, dir_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }

        filesNames_.clear();
        _d.setTrainers(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(TRAINERS_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            _d.getTrainers().put(key_, BaseSixtyFourUtil.getImageByString(notNull(files_,StringList.concat(common_, s))));
        }
        filesNames_.clear();
        _d.setMaxiPkBack(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(BACK_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            _d.getMaxiPkBack().put(DataBase.toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(notNull(files_,StringList.concat(common_,
                            s))));
        }
        filesNames_.clear();
        _d.setMaxiPkFront(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(FRONT_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            _d.getMaxiPkFront().put(DataBase.toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(notNull(files_,StringList.concat(common_,
                            s))));
        }
        filesNames_.clear();
        _d.setMiniPk(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(MINI_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            _d.getMiniPk().put(DataBase.toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(notNull(files_,StringList.concat(common_,
                            s))));
        }
        _perCentLoading.set(25);
        filesNames_.clear();
        _d.setMiniItems(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(OBJECTS_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            if (!s.endsWith(IMG_FILES_RES_EXT_TXT)) {
                continue;
            }

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            _d.getMiniItems().put(DataBase.toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(notNull(files_,StringList.concat(common_,
                            s))));
        }

        filesNames_.clear();
        _d.setTypesImages(new StringMap<int[][]>());

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(TYPES_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            if (!s.endsWith(IMG_FILES_RES_EXT_TXT)) {
                continue;
            }
            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);

            n_ = removeExtension(n_);
            filesNames_.add(n_);
            _d.getTypesImages().put(DataBase.toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(notNull(files_,StringList.concat(common_,
                            s))));
        }

        String imgHmTm_ = notNull(files_,StringList
                .concat(common_, IMAGE_TM_HM_FILES, IMG_FILES_RES_EXT_TXT));
        _d.setImageTmHm(BaseSixtyFourUtil.getImageByString(imgHmTm_));
        String storeImg_ = notNull(files_,StringList
                .concat(common_, IMAGE_STORAGE_FILES, IMG_FILES_RES_EXT_TXT));
        _d.setStorage(BaseSixtyFourUtil.getImageByString(storeImg_));
        String combos_ = notNull(files_,StringList
                .concat(common_, COMBOS));
        _d.setCombos(DocumentReaderAikiCoreUtil.getCombos(combos_));
        _d.completeMembersCombos();
        _d.sortEndRound();
        String mapFile_ = notNull(files_,StringList
                .concat(common_, MAP_FILE));
        _d.setMap(DocumentReaderAikiCoreUtil.getDataMap(mapFile_));
        _d.setConstNum(new StringMap<Rate>());
        String cstNum_ = notNull(files_,StringList.concat(common_, CONST_NUM));
        StringList linesNum_ = StringList.splitChar(
                cstNum_,
                RETURN_LINE_CHAR);
        for (String l : linesNum_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChar(l, TAB_CHAR);
            _d.getConstNum().put(infos_.first(), new Rate(infos_.last()));
        }

        String cstNotNum_ = notNull(files_,StringList.concat(common_, CONST_NOT_NUM));
        StringList linesNotNum_ = StringList.splitChar(
                cstNotNum_,
                RETURN_LINE_CHAR);
        for (String l : linesNotNum_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            if (StringList.quickEq(infos_.first(), DEF_MOVE)) {
                _d.setDefMove(infos_.last());
            } else if (StringList.quickEq(infos_.first(), RATE_BOOST)) {
                _d.setRateBoost(infos_.last());
            } else if (StringList.quickEq(infos_.first(),
                    RATE_BOOST_CRITICAL_HIT)) {
                _d.setRateBoostCriticalHit(infos_.last());
            } else if (StringList.quickEq(infos_.first(), RATE_FLEEING)) {
                _d.setRateFleeing(infos_.last());
            } else if (StringList.quickEq(infos_.first(), RATE_CATCHING)) {
                _d.setRateCatching(infos_.last());
            } else if (StringList.quickEq(infos_.first(), BALL_DEF)) {
                _d.setBallDef(infos_.last());
            } else if (StringList.quickEq(infos_.first(), DEFAULT_EGG_GROUP)) {
                _d.setDefaultEggGoup(infos_.last());
            } else if (StringList.quickEq(infos_.first(), DAMAGE_FORMULA)) {
                _d.setDamageFormula(infos_.last());
            }

        }
        _d.setTableTypes(new ObjectMap<TypesDuo, Rate>());
        String tTable_ = notNull(files_,StringList.concat(common_, TABLE_TYPES));
        StringList linesTableTypes_ = StringList.splitChars(
                tTable_,
                RETURN_LINE_CHAR);
        String head_ = linesTableTypes_.first();
        StringList typesOff_ = StringList.splitChars(head_, TAB_CHAR);
        typesOff_.removeString(EMPTY_STRING);
        StringList typesDef_ = new StringList();
        for (String l : linesTableTypes_.sub(1, linesTableTypes_.size())) {
            typesDef_.add(StringList.getFirstToken(l, TAB_CHAR));
        }
        typesDef_.removeString(EMPTY_STRING);
        for (String pkType_ : typesDef_) {

            String l_ = getElements(linesTableTypes_, pkType_).first();
            StringList infos_ = StringList.splitChars(l_, TAB_CHAR);
            infos_.removeString(pkType_);
            int i_ = 0;
            for (String damageType_ : typesOff_) {
                TypesDuo t_ = new TypesDuo(damageType_, pkType_);
                Rate r_;
                if (Rate.isValid(infos_.get(i_))) {
                    r_ = new Rate(infos_.get(i_));
                } else {
                    r_ = DataBase.defRateProduct();
                }
                _d.getTableTypes().put(t_, r_);

                i_++;
            }
        }
        _d.setLawsDamageRate(new EnumMap<DifficultyModelLaw, LawNumber>());
        String rdLaw_ = notNull(files_,StringList.concat(common_, LOIS_RANDOM));
        StringList laws_ = StringList.splitChars(
                rdLaw_,
                RETURN_LINE_CHAR);
        for (String l : laws_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            MonteCarloNumber law_ = new MonteCarloNumber();

            for (String evt_ : StringList.splitStrings(infos_.get(1),
                    SEPARATOR_RAND)) {
                StringList infosLoc_ = StringList.splitStrings(evt_,
                        SEPARATOR_RAND_EVENTS);
                boolean defaultLaw_ = false;
                if (!Rate.isValid(infosLoc_.first())) {
                    defaultLaw_ = true;
                } else if (!LgInt.isValid(infosLoc_.get(1))) {
                    defaultLaw_ = true;
                }
                if (defaultLaw_) {
                    law_ = new MonteCarloNumber();
                    law_.addEvent(new Rate(1),DataBase.defElementaryEvent());

                    break;
                }

                law_.addEvent(new Rate(infosLoc_.first()),
                        new LgInt(infosLoc_.get(1)));

            }

            if (!law_.checkEvents()) {
                _d.setError(true);
                return;
            }
            _d.getLawsDamageRate().put(
                    DifficultyModelLaw.getModelByName(infos_.first()),
                    new LawNumber(law_, (short) Numbers.parseInt(infos_.last())));
        }
        _d.setExpGrowth(new EnumMap<ExpType, String>());
        String pts_ = notNull(files_,StringList.concat(common_, COURBE_PTS_EXP));
        StringList courbes_ = StringList.splitChars(
                pts_,
                RETURN_LINE_CHAR);
        for (String l : courbes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            _d.getExpGrowth().put(ExpType.getExpTypeByName(infos_.first()),
                    infos_.get(1));
        }
        _d.setRates(new EnumMap<DifficultyWinPointsFight, String>());
        String rWon_ = notNull(files_,StringList.concat(common_, RATE_WON_POINTS));
        StringList rates_ = StringList.splitChars(
                rWon_,
                RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            _d.getRates().put(DifficultyWinPointsFight.getDiffWonPtsByName(infos_
                    .first()), infos_.get(1));
        }
        _d.setTypesColors(new StringMap<String>());
        String imgTypes_ = notNull(files_,StringList
                .concat(common_, TYPES_COLOR_CODE, IMG_FILES_RES_EXT_TXT));
        StringList colorTypes_ = StringList.splitChars(imgTypes_,
                RETURN_LINE_CHAR);
        for (String l : colorTypes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            String colorStr_ = infos_.get(1);
            _d.getTypesColors().put(infos_.first(), colorStr_);
        }
        String endGame_ = notNull(files_,StringList.concat(common_, END_GAME_IMAGE,
                IMG_FILES_RES_EXT_TXT));
        _d.setEndGameImage(BaseSixtyFourUtil.getImageByString(endGame_));
        _d.initTranslations();
        _perCentLoading.set(30);
        for (String l : Constants.getAvailableLanguages()) {
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_GENDERS);
            EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                genders_.put(Gender.getGenderByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedGenders().put(l, genders_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_BOOLEANS);
            EnumMap<SelectedBoolean, String> booleans_ = new EnumMap<SelectedBoolean, String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                booleans_.put(SelectedBoolean.getBoolByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedBooleans().put(l, booleans_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_DIFF_WIN_PTS);
            EnumMap<DifficultyWinPointsFight, String> diffWinPts_ = new EnumMap<DifficultyWinPointsFight, String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                diffWinPts_.put(DifficultyWinPointsFight
                        .getDiffWonPtsByName(infos_.first()), DocumentBuilder
                        .transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffWinPts().put(l, diffWinPts_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList
                    .concat(fileName_, TRANSLATION_DIFF_MODEL_LAW);
            EnumMap<DifficultyModelLaw, String> diffLaw_ = new EnumMap<DifficultyModelLaw, String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                diffLaw_.put(DifficultyModelLaw.getModelByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffModelLaw().put(l, diffLaw_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ENVIRONMENTS);
            EnumMap<EnvironmentType, String> environments_ = new EnumMap<EnvironmentType, String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                environments_.put(EnvironmentType.getEnvByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedEnvironment().put(l, environments_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATISTICS);
            EnumMap<Statistic, String> statistics_ = new EnumMap<Statistic, String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                statistics_.put(Statistic.getStatisticByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatistics().put(l, statistics_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TARGETS);
            EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                targets_.put(
                        TargetChoice.getTargetChoiceByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTargets().put(l, targets_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CATEGORIES);
            StringMap<String> categories_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                categories_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedCategories().put(l, categories_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TYPES);
            StringMap<String> types_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                types_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTypes().put(l, types_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_POKEMON);
            StringMap<String> pokemon_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                pokemon_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedPokemon().put(l, pokemon_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MOVES);
            StringMap<String> moves_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                moves_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedMoves().put(l, moves_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ITEMS);
            StringMap<String> items_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                items_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedItems().put(l, items_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ABILITIES);
            StringMap<String> abilities_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                abilities_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedAbilities().put(l, abilities_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATUS);
            StringMap<String> status_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                status_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatus().put(l, status_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MATH);
            StringMap<String> fctsMath_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                fctsMath_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedFctMath().put(l, fctsMath_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CLASSES);
            StringMap<String> descrClasses_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                descrClasses_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedClassesDescriptions().put(l, descrClasses_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_LITTERAL);
            StringMap<String> litteral_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    notNull(files_,StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                litteral_
                        .put(infos_.first(), DocumentBuilder
                                .transformSpecialChars(infos_.mid(
                                        CustList.SECOND_INDEX, infos_.size())
                                        .join(TAB)));
            }
            _d.getLitterals().put(l, litteral_);
        }
        _perCentLoading.set(35);

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(ANIM_STATIS, SEPARATOR_FILES))) {

            String f_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            f_ = removeExtension(f_);
            if (f_.isEmpty()) {
                continue;
            }
            _d.getAnimStatis().put(DataBase.toUpperCase(f_), BaseSixtyFourUtil
                    .getImageByString(notNull(files_,StringList.concat(common_,
                            f))));
        }

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(ANIM_STATUS, SEPARATOR_FILES))) {

            String f_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            f_ = removeExtension(f_);
            if (f_.isEmpty()) {
                continue;
            }
            _d.getAnimStatus().put(DataBase.toUpperCase(f_), BaseSixtyFourUtil
                    .getImageByString(notNull(files_,StringList.concat(common_,
                            f))));
        }
        String anAbs_ = notNull(files_,StringList.concat(common_, ANIM_ABSORB));
        _d.setAnimAbsorb(BaseSixtyFourUtil.getImageByString(anAbs_));
        _perCentLoading.set(40);
    }


    public static void loadResources(DataBase _d,AtomicInteger _perCentLoading, String _lg) {
        int delta_ = (100 - _perCentLoading.get()) / 6;
        _d.getImagesDimensions().clear();

        _d.initializeMembers();
        String common_ = Resources.ACCESS_TO_DEFAULT_FILES;
        StringList tmHm_ = StringList.splitChars(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, CT_CS_FILE)),
                RETURN_LINE_CHAR);
        for (String l : tmHm_) {
            if (l.startsWith(CT)) {
                StringList infos_ = StringList.splitChars(l, TAB_CHAR);
                short cle_ = (short) Numbers.parseInt(infos_.first().substring(2));
                _d.getTm().put(cle_, infos_.get(1).trim());
                LgInt price_;
                if (LgInt.isValid(infos_.get(2).trim())) {
                    price_ = new LgInt(infos_.get(2).trim());
                } else {
                    price_ = new LgInt(1000);
                }
                _d.getTmPrice().put(cle_, price_);

            }
            if (l.startsWith(CS)) {
                StringList infos_ = StringList.splitChars(l.trim(), TAB_CHAR);
                short cle_ = (short) Numbers.parseInt(infos_.first().substring(2));
                _d.getHm().put(cle_, infos_.get(1));
            }
        }
        _d.setFrontHeros(new ObjectMap<ImageHeroKey, int[][]>());
        for (String l : StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, HERO_FOLDER,
                        SEPARATOR_FILES, HERO_FRONT)), RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringList.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = EnvironmentType.getEnvByName(keyStrings_
                    .first());
            Sex sex_ = Sex.getSexByName(keyStrings_.last());
            _d.getFrontHeros().put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        _d.setBackHeros(new ObjectMap<ImageHeroKey, int[][]>());
        for (String l : StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, HERO_FOLDER,
                        SEPARATOR_FILES, HERO_BACK)), RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringList.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = EnvironmentType.getEnvByName(keyStrings_
                    .first());
            Sex sex_ = Sex.getSexByName(keyStrings_.last());
            _d.getBackHeros().put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        _d.setOverWorldHeros(new ObjectMap<ImageHeroKey, int[][]>());
        for (String l : StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, HERO_FOLDER,
                        SEPARATOR_FILES, HERO_MINI)), RETURN_LINE_CHAR)) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            StringList keyStrings_ = StringList.splitStrings(infos_.first(),
                    SEPARATOR_KEY_HEROS);
            EnvironmentType env_ = EnvironmentType.getEnvByName(keyStrings_
                    .first());
            Direction dir_ = Direction.getDirectionByName(keyStrings_
                    .get(CustList.SECOND_INDEX));
            Sex sex_ = Sex.getSexByName(keyStrings_.last());
            _d.getOverWorldHeros().put(new ImageHeroKey(env_, dir_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        _d.setImageTmHm(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_, IMAGE_TM_HM_FILES,
                        IMG_FILES_RES_EXT_TXT))));
        _d.setStorage(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_,
                        IMAGE_STORAGE_FILES, IMG_FILES_RES_EXT_TXT))));
        _d.setCombos(DocumentReaderAikiCoreUtil.getCombos(ResourceFiles
                .ressourceFichier(StringList.concat(common_, COMBOS))));
        _d.completeMembersCombos();
        _d.setMap(DocumentReaderAikiCoreUtil.getDataMap(ResourceFiles
                .ressourceFichier(StringList.concat(common_, MAP_FILE))));
        _perCentLoading.addAndGet(delta_);
        _d.setConstNum(new StringMap<Rate>());
        StringList lines_ = StringList.splitChars(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, CONST_NUM)),
                RETURN_LINE_CHAR);
        for (String l : lines_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            _d.getConstNum().put(infos_.first(), new Rate(infos_.last()));
        }

        lines_ = StringList.splitChars(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, CONST_NOT_NUM)),
                RETURN_LINE_CHAR);
        for (String l : lines_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            if (StringList.quickEq(infos_.first(), DEF_MOVE)) {
                _d.setDefMove(infos_.last());
            } else if (StringList.quickEq(infos_.first(), RATE_BOOST)) {
                _d.setRateBoost(infos_.last());
            } else if (StringList.quickEq(infos_.first(),
                    RATE_BOOST_CRITICAL_HIT)) {
                _d.setRateBoostCriticalHit(infos_.last());
            } else if (StringList.quickEq(infos_.first(), RATE_FLEEING)) {
                _d.setRateFleeing(infos_.last());
            } else if (StringList.quickEq(infos_.first(), RATE_CATCHING)) {
                _d.setRateCatching(infos_.last());
            } else if (StringList.quickEq(infos_.first(), BALL_DEF)) {
                _d.setBallDef(infos_.last());
            } else if (StringList.quickEq(infos_.first(), DEFAULT_EGG_GROUP)) {
                _d.setDefaultEggGoup(infos_.last());
            } else if (StringList.quickEq(infos_.first(), DAMAGE_FORMULA)) {
                _d.setDamageFormula(infos_.last());
            }

        }
        _d.setTableTypes(new ObjectMap<TypesDuo, Rate>());
        StringList linesTableTypes_ = StringList.splitChars(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, TABLE_TYPES)),
                RETURN_LINE_CHAR);
        String head_ = linesTableTypes_.first();
        StringList typesOff_ = StringList.splitChars(head_, TAB_CHAR);
        typesOff_.removeString(EMPTY_STRING);
        StringList typesDef_ = new StringList();
        for (String l : linesTableTypes_.sub(1, linesTableTypes_.size())) {
            typesDef_.add(StringList.getFirstToken(l, TAB_CHAR));
        }
        typesDef_.removeString(EMPTY_STRING);
        for (String pkType_ : typesDef_) {

            String l_ = getElements(linesTableTypes_, pkType_).first();
            StringList infos_ = StringList.splitChars(l_, TAB_CHAR);
            infos_.removeString(pkType_);
            int i_ = 0;
            for (String damageType_ : typesOff_) {
                TypesDuo t_ = new TypesDuo(damageType_, pkType_);
                Rate r_;
                if (Rate.isValid(infos_.get(i_))) {
                    r_ = new Rate(infos_.get(i_));
                } else {
                    r_ = DataBase.defRateProduct();
                }
                _d.getTableTypes().put(t_, r_);

                i_++;
            }
        }
        _d.initTypesByTable();
        _d.setLawsDamageRate(new EnumMap<DifficultyModelLaw, LawNumber>());
        StringList laws_ = StringList.splitChars(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, LOIS_RANDOM)),
                RETURN_LINE_CHAR);
        for (String l : laws_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            MonteCarloNumber law_ = new MonteCarloNumber();

            for (String evt_ : StringList.splitStrings(infos_.get(1),
                    SEPARATOR_RAND)) {
                StringList infosLoc_ = StringList.splitStrings(evt_,
                        SEPARATOR_RAND_EVENTS);
                boolean defaultLaw_ = false;
                if (!Rate.isValid(infosLoc_.first())) {
                    defaultLaw_ = true;
                } else if (!LgInt.isValid(infosLoc_.get(1))) {
                    defaultLaw_ = true;
                }
                if (defaultLaw_) {
                    law_ = new MonteCarloNumber();

                    law_.addEvent(new Rate(1), DataBase.defElementaryEvent());
                    break;
                }

                law_.addEvent(new Rate(infosLoc_.first()),
                        new LgInt(infosLoc_.get(1)));

            }

            if (!law_.checkEvents()) {
                _d.setError(true);
                return;
            }
            _d.getLawsDamageRate().put(
                    DifficultyModelLaw.getModelByName(infos_.first()),
                    new LawNumber(law_, (short) Numbers.parseInt(infos_.last())));
        }
        _d.setExpGrowth(new EnumMap<ExpType, String>());
        StringList courbes_ = StringList.splitChars(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, COURBE_PTS_EXP)),
                RETURN_LINE_CHAR);
        for (String l : courbes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            _d.getExpGrowth().put(ExpType.getExpTypeByName(infos_.first()),
                    infos_.get(1));
        }
        _d.setRates(new EnumMap<DifficultyWinPointsFight, String>());
        StringList rates_ = StringList.splitChars(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, RATE_WON_POINTS)),
                RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            _d.getRates().put(DifficultyWinPointsFight.getDiffWonPtsByName(infos_
                    .first()), infos_.get(1));
        }
        _d.setTypesColors(new StringMap<String>());
        rates_ = StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, TYPES_COLOR_CODE,
                        IMG_FILES_RES_EXT_TXT)), RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            String colorStr_ = infos_.get(1);
            _d.getTypesColors().put(infos_.first(), colorStr_);
        }
        _d.setEndGameImage(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_, END_GAME_IMAGE,
                        IMG_FILES_RES_EXT_TXT))));
        _d.initTranslations();
        for (String l : Constants.getAvailableLanguages()) {
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_GENDERS);
            EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                genders_.put(Gender.getGenderByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedGenders().put(l, genders_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_BOOLEANS);
            EnumMap<SelectedBoolean, String> booleans_ = new EnumMap<SelectedBoolean, String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                booleans_.put(SelectedBoolean.getBoolByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedBooleans().put(l, booleans_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_DIFF_WIN_PTS);
            EnumMap<DifficultyWinPointsFight, String> diffWinPts_ = new EnumMap<DifficultyWinPointsFight, String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                diffWinPts_.put(DifficultyWinPointsFight
                        .getDiffWonPtsByName(infos_.first()), DocumentBuilder
                        .transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffWinPts().put(l, diffWinPts_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList
                    .concat(fileName_, TRANSLATION_DIFF_MODEL_LAW);
            EnumMap<DifficultyModelLaw, String> diffLaw_ = new EnumMap<DifficultyModelLaw, String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                diffLaw_.put(DifficultyModelLaw.getModelByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedDiffModelLaw().put(l, diffLaw_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ENVIRONMENTS);
            EnumMap<EnvironmentType, String> environments_ = new EnumMap<EnvironmentType, String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                environments_.put(EnvironmentType.getEnvByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedEnvironment().put(l, environments_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATISTICS);
            EnumMap<Statistic, String> statistics_ = new EnumMap<Statistic, String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                statistics_.put(Statistic.getStatisticByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatistics().put(l, statistics_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TARGETS);
            EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                targets_.put(
                        TargetChoice.getTargetChoiceByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTargets().put(l, targets_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CATEGORIES);
            StringMap<String> categories_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                categories_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedCategories().put(l, categories_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TYPES);
            StringMap<String> types_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                types_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedTypes().put(l, types_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_POKEMON);
            StringMap<String> pokemon_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                pokemon_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedPokemon().put(l, pokemon_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MOVES);
            StringMap<String> moves_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                moves_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedMoves().put(l, moves_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ITEMS);
            StringMap<String> items_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                items_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedItems().put(l, items_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ABILITIES);
            StringMap<String> abilities_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                abilities_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedAbilities().put(l, abilities_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATUS);
            StringMap<String> status_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                status_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedStatus().put(l, status_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MATH);
            StringMap<String> fctsMath_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                fctsMath_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedFctMath().put(l, fctsMath_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CLASSES);
            StringMap<String> descrClasses_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                descrClasses_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            _d.getTranslatedClassesDescriptions().put(l, descrClasses_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_LITTERAL);
            StringMap<String> litteral_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(ResourceFiles
                            .ressourceFichier(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                litteral_
                        .put(infos_.first(), DocumentBuilder
                                .transformSpecialChars(infos_.mid(
                                        CustList.SECOND_INDEX, infos_.size())
                                        .join(TAB)));
            }
            _d.getLitterals().put(l, litteral_);
        }
        _perCentLoading.addAndGet(delta_);
        for (Statistic f : _d.getTranslatedStatistics().getVal(_lg)
                .getKeys()) {
            if (!f.isBoost()) {
                continue;
            }
            String f_ = StringList.concat(ANIM_STATIS, SEPARATOR_FILES,
                    f.name(), IMG_FILES_RES_EXT_TXT);
            _d.getAnimStatis().put(f.name(), BaseSixtyFourUtil
                    .getImageByString(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, f_))));
        }
        for (String f : _d.getTranslatedStatus().getVal(_lg)
                .getKeys()) {
            String f_ = StringList.concat(ANIM_STATUS, SEPARATOR_FILES, f,
                    IMG_FILES_RES_EXT_TXT);
            _d.getAnimStatus().put(DataBase.toUpperCase(f), BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, f_))));
        }
        _d.setAnimAbsorb(BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_, ANIM_ABSORB))));
        StringList filesNames_;
        filesNames_ = new StringList();
        for (String f : _d.getTranslatedPokemon().getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(POKEDEX_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(f);
            PokemonData f_ = DocumentReaderAikiCoreUtil
                    .getPokemonData(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, n_)));
            _d.completeMembers(DataBase.toUpperCase(f), f_);
        }
        _d.calculateAvgPound();
        filesNames_.clear();
        for (String f : _d.getTranslatedMoves().getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(MOVES_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            MoveData move_ = DocumentReaderAikiCoreUtil
                    .getMoveData(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, n_)));
            _d.completeMembers(DataBase.toUpperCase(f), move_);
        }
        filesNames_.clear();
        for (String f : _d.getTranslatedItems().getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(ITEMS_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            Item o_ = DocumentReaderAikiCoreUtil.getItem(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_)));
            _d.completeMembers(DataBase.toUpperCase(f), o_);
        }
        filesNames_.clear();
        for (String f : _d.getTranslatedAbilities().getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(ABILITIES_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            AbilityData ab_ = DocumentReaderAikiCoreUtil
                    .getAbilityData(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, n_)));
            _d.completeMembers(DataBase.toUpperCase(f), ab_);
        }
        filesNames_.clear();
        for (String f : _d.getTranslatedStatus().getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(STATUS_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            Status st_ = DocumentReaderAikiCoreUtil.getStatus(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_)));
            _d.completeMembers(DataBase.toUpperCase(f), st_);
        }
        _d.completeVariables();
        filesNames_.clear();
        _d.sortEndRound();
        _perCentLoading.addAndGet(delta_);
        for (PokemonData pk_ : _d.getPokedex().values()) {
            for (short hm_ : pk_.getHiddenMoves()) {
                String move_ = _d.getHm().getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (short hm_ : pk_.getTechnicalMoves()) {
                String move_ = _d.getTm().getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (LevelMove l : pk_.getLevMoves()) {
                pk_.getMoveTutors().add(l.getMove());
            }
            pk_.getMoveTutors().removeDuplicates();
        }
        _d.setMaxiPkBack(new StringMap<int[][]>());
        for (String s : _d.getPokedex().getKeys()) {
            String n_ = StringList.concat(BACK_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMaxiPkBack().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        _d.setMaxiPkFront(new StringMap<int[][]>());
        for (String s : _d.getPokedex().getKeys()) {
            String n_ = StringList.concat(FRONT_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMaxiPkFront().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        _d.setMiniPk(new StringMap<int[][]>());
        for (String s : _d.getPokedex().getKeys()) {
            String n_ = StringList.concat(MINI_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMiniPk().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        _d.setMiniItems(new StringMap<int[][]>());
        for (String s : _d.getItems().getKeys()) {
            String n_ = StringList.concat(OBJECTS_IMAGES_FOLDER,
                    SEPARATOR_FILES, s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getMiniItems().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        _d.setTypesImages(new StringMap<int[][]>());
        for (String s : _d.getTypes()) {
            String n_ = StringList.concat(TYPES_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            _d.getTypesImages().put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        _perCentLoading.addAndGet(delta_);
        filesNames_.clear();
        _d.getMap().initializeLinks();
        _d.getMap().initInteractiveElements();
        _d.getMap().initializeTree();
        _d.getMap().initializeAccessibility();
        _d.setTrainers(new StringMap<int[][]>());
        _d.setPeople(new StringMap<int[][]>());
        _d.setImages(new StringMap<int[][]>());
        _d.setImagesTiles(new StringMap<ObjectMap<ScreenCoords, int[][]>>());
        _d.setLinks(new StringMap<int[][]>());
        _d.setMiniMap(new StringMap<int[][]>());
        for (Place p : _d.getMap().getPlaces().values()) {
            if (p instanceof League) {
                League l_ = (League) p;
                for (Level l : l_.getLevelsList()) {
                    LevelLeague lev_ = (LevelLeague) l;
                    String f_ = lev_.getTrainer().getImageMaxiFileName();
                    String file_ = StringList.concat(TRAINERS_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getTrainers().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    f_ = lev_.getTrainer().getImageMiniFileName();
                    file_ = StringList.concat(PEOPLE_FOLDER, SEPARATOR_FILES,
                            f_);
                    _d.getPeople().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    for (Block b_ : l.getBlocks().values()) {
                        f_ = b_.getTileFileName();
                        file_ = StringList.concat(IMAGES_FOLDER,
                                SEPARATOR_FILES, f_);
                        _d.getImages().put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                    f_ = lev_.getFileName();
                    file_ = StringList
                            .concat(LINKS_FOLDER, SEPARATOR_FILES, f_);
                    _d.getLinks().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                String f_ = l_.getFileName();
                String file_ = StringList.concat(LINKS_FOLDER, SEPARATOR_FILES,
                        f_);
                _d.getLinks().put(f_, BaseSixtyFourUtil.getImageByString(ResourceFiles
                        .ressourceFichier(StringList.concat(common_, file_))));
                continue;
            }
            if (p instanceof City) {
                City c_ = (City) p;
                for (Building b : c_.getBuildings().values()) {
                    if (b instanceof Gym) {
                        Gym g_ = (Gym) b;
                        for (Trainer t : g_.getLevel().getGymTrainers()
                                .values()) {
                            String f_ = t.getImageMaxiFileName();
                            String file_ = StringList.concat(TRAINERS_FOLDER,
                                    SEPARATOR_FILES, f_);
                            _d.getTrainers().put(f_, BaseSixtyFourUtil
                                    .getImageByString(ResourceFiles
                                            .ressourceFichier(StringList
                                                    .concat(common_, file_))));
                            f_ = t.getImageMiniFileName();
                            file_ = StringList.concat(PEOPLE_FOLDER,
                                    SEPARATOR_FILES, f_);
                            _d.getPeople().put(f_, BaseSixtyFourUtil
                                    .getImageByString(ResourceFiles
                                            .ressourceFichier(StringList
                                                    .concat(common_, file_))));
                        }
                        String f_ = g_.getLevel().getGymLeader()
                                .getImageMaxiFileName();
                        String file_ = StringList.concat(TRAINERS_FOLDER,
                                SEPARATOR_FILES, f_);
                        _d.getTrainers().put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                        f_ = g_.getLevel().getGymLeader()
                                .getImageMiniFileName();
                        file_ = StringList.concat(PEOPLE_FOLDER,
                                SEPARATOR_FILES, f_);
                        _d.getPeople().put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                    if (b instanceof PokemonCenter) {
                        PokemonCenter pkCenter_ = (PokemonCenter) b;
                        for (Person g : pkCenter_.getLevel().getGerants()
                                .values()) {
                            String f_ = g.getImageMiniFileName();
                            String file_ = StringList.concat(PEOPLE_FOLDER,
                                    SEPARATOR_FILES, f_);
                            _d.getPeople().put(f_, BaseSixtyFourUtil
                                    .getImageByString(ResourceFiles
                                            .ressourceFichier(StringList
                                                    .concat(common_, file_))));
                        }
                    }
                    for (Block b_ : b.getLevel().getBlocks().values()) {
                        String f_ = b_.getTileFileName();
                        String file_ = StringList.concat(IMAGES_FOLDER,
                                SEPARATOR_FILES, f_);
                        _d.getImages().put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                    String f_ = b.getImageFileName();
                    String file_ = StringList.concat(LINKS_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getLinks().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                for (Block b_ : c_.getLevel().getBlocks().values()) {
                    String f_ = b_.getTileFileName();
                    String file_ = StringList.concat(IMAGES_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getImages().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                for (Link k : c_.getLinksWithCaves().values()) {
                    String f_ = k.getFileName();
                    String file_ = StringList.concat(LINKS_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getLinks().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                continue;
            }
            Campaign c_ = (Campaign) p;
            for (Level l : c_.getLevelsMap().values()) {
                LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                for (CharacterInRoadCave c : level_.getCharacters().values()) {
                    if (c instanceof TrainerMultiFights) {
                        TrainerMultiFights tr_ = (TrainerMultiFights) c;
                        String f_ = tr_.getImageMaxiFileName();
                        String file_ = StringList.concat(TRAINERS_FOLDER,
                                SEPARATOR_FILES, f_);
                        _d.getTrainers().put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                        f_ = tr_.getImageMiniFileName();
                        file_ = StringList.concat(PEOPLE_FOLDER,
                                SEPARATOR_FILES, f_);
                        _d.getPeople().put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                }
                for (DualFight d : level_.getDualFights().values()) {
                    String f_ = d.getFoeTrainer().getImageMaxiFileName();
                    String file_ = StringList.concat(TRAINERS_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getTrainers().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    f_ = d.getFoeTrainer().getImageMiniFileName();
                    file_ = StringList.concat(PEOPLE_FOLDER, SEPARATOR_FILES,
                            f_);
                    _d.getPeople().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    f_ = d.getFoeTrainer().getImageMiniSecondTrainerFileName();
                    file_ = StringList.concat(PEOPLE_FOLDER, SEPARATOR_FILES,
                            f_);
                    _d.getPeople().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                for (Block b_ : l.getBlocks().values()) {
                    String f_ = b_.getTileFileName();
                    String file_ = StringList.concat(IMAGES_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getImages().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
            }
            if (p instanceof InitializedPlace) {
                InitializedPlace p_ = (InitializedPlace) p;
                for (Link k : p_.getLinksWithCaves().values()) {
                    String f_ = k.getFileName();
                    String file_ = StringList.concat(LINKS_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getLinks().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
            }
            if (p instanceof Cave) {
                Cave cave_ = (Cave) p;
                for (Link k : cave_.getLinksWithOtherPlaces().values()) {
                    String f_ = k.getFileName();
                    String file_ = StringList.concat(LINKS_FOLDER,
                            SEPARATOR_FILES, f_);
                    _d.getLinks().put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                for (Level l : cave_.getLevelsMap().values()) {
                    LevelCave lCave_ = (LevelCave) l;
                    for (Link k : lCave_.getLinksOtherLevels().values()) {
                        String f_ = k.getFileName();
                        String file_ = StringList.concat(LINKS_FOLDER,
                                SEPARATOR_FILES, f_);
                        _d.getLinks().put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                }
            }
        }
        for (TileMiniMap t : _d.getMap().getMiniMap().values()) {
            String f_ = t.getFile();
            String file_ = StringList.concat(MINI_MAP_FOLDER, SEPARATOR_FILES,
                    f_);
            _d.getMiniMap().put(f_, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, file_))));
        }
        _d.getMiniMap().put(_d.getMap().getUnlockedCity(), BaseSixtyFourUtil
                .getImageByString(ResourceFiles.ressourceFichier(StringList
                        .concat(common_, MINI_MAP_FOLDER, SEPARATOR_FILES,
                                _d.getMap().getUnlockedCity()))));
        _perCentLoading.addAndGet(delta_);
        _d.initializeWildPokemon();
        _perCentLoading.addAndGet(delta_);

        _d.validateEvolutions();
        for (int[][] i : _d.getMaxiPkBack().values()) {
            if (i.length == 0) {
                _d.setError(true);
                return;
            }
            if (i[0].length > _d.getMaxWidthPk()) {
                _d.setMaxWidthPk(i[0].length);
            }
            if (i.length > _d.getMaxHeightPk()) {
                _d.setMaxHeightPk(i.length);
            }

        }
        for (int[][] i : _d.getMaxiPkFront().values()) {
            if (i.length == 0) {
                _d.setError(true);
                return;
            }
            if (i[0].length > _d.getMaxWidthPk()) {
                _d.setMaxWidthPk(i[0].length);
            }
            if (i.length > _d.getMaxHeightPk()) {
                _d.setMaxHeightPk(i.length);
            }

        }
        int side_ = _d.getMap().getSideLength();
        for (EntryCust<String, int[][]> i : _d.getImages().entryList()) {
            int[][] img_ = i.getValue();
            String name_ = i.getKey();
            Dims d_ = new Dims();
            d_.setWidth((short) (img_[0].length / side_));
            d_.setHeight((short) (img_.length / side_));
            ObjectMap<ScreenCoords, int[][]> tiles_;
            tiles_ = new ObjectMap<ScreenCoords, int[][]>();
            for (short x = 0; x < d_.getWidth(); x++) {
                for (short y = 0; y < d_.getHeight(); y++) {
                    ScreenCoords sc_ = new ScreenCoords(x, y);
                    tiles_.put(sc_, Image.clipSixtyFour(img_, x * side_, y
                            * side_, side_, side_));
                }
            }
            _d.getImagesTiles().put(name_, tiles_);
        }
        _perCentLoading.set(100);
    }

    private static boolean isCorrectIdentifier(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        int len_ = _string.length();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            char curr_ = _string.charAt(i);
            boolean ok_ = false;
            if (curr_ >= 'a' && curr_ <= 'z') {
                ok_ = true;
            }
            if (curr_ >= 'A' && curr_ <= 'Z') {
                ok_ = true;
            }
            if (curr_ >= '0' && curr_ <= '9') {
                ok_ = true;
            }
            if (curr_ == UNDERSCORE) {
                ok_ = true;
            }
            if (!ok_) {
                return false;
            }
            if (curr_ == UNDERSCORE) {
                if (i + 1 == len_) {
                    continue;
                }
                if (_string.charAt(i + 1) == UNDERSCORE) {
                    return false;
                }
            }
        }
        return true;
    }

    public static StringList filterBeginIgnoreCase(StringList _instance,String _regExp) {
        StringList list_ = new StringList();
        String patt_ = DataBase.toUpperCase(_regExp);
        for (String s: _instance) {
            if (!DataBase.toUpperCase(s).startsWith(patt_)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    public static StringList filterStrictBeginIgnoreCase(StringList _instance,String _regExp) {
        StringList list_ = new StringList();
        String patt_ = DataBase.toUpperCase(_regExp);
        for (String s: _instance) {
            if (!DataBase.toUpperCase(s).startsWith(patt_)) {
                continue;
            }
            if (StringList.quickEq(DataBase.toUpperCase(s),patt_)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    private static String notNull(StringMap<String> _m, String _k) {
        String v_ = _m.getVal(_k);
        return notNull(v_);
    }
    private static String notNull(String _s) {
        if (_s == null) {
            return EMPTY_STRING;
        }
        return _s;
    }

    static String removeExtension(String _string) {
        return StringList.replaceExtension(_string);

    }

    private static StringList getElements(StringList _list, String _prefixWord) {
        StringList elts_ = new StringList();
        for (String l : _list) {
            if (!l.startsWith(_prefixWord)) {
                continue;
            }
            if (StringList.quickEq(l, _prefixWord)) {
                elts_.add(l);
                continue;
            }
            char next_ = l.charAt(_prefixWord.length());
            if (!StringList.isWordChar(next_)) {
                elts_.add(l);
            }
        }
        return elts_;
    }

    public static Combos getCombos(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        return getCombos(doc_.getDocumentElement());
    }

    private static Combos getCombos(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Combos object_ = Instances.newCombos();
        for (Element c: childElements_) {
            getCombos(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getCombos(Combos _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_EFFECTS)) {
            _object.setEffects(getMapStringListEffectCombo(_element));
            return;
        }
    }

    public static AbilityData getAbilityData(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newAbilityData();
        }
        return getAbilityData(doc_.getDocumentElement());
    }

    private static AbilityData getAbilityData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AbilityData object_ = Instances.newAbilityData();
        for (Element c: childElements_) {
            getAbilityData(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAbilityData(AbilityData _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BREAK_FOE_IMMUNE)) {
            _object.setBreakFoeImmune(getListTypesDuo(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FORBID_USE_BERRY_AGAINST_FOES)) {
            _object.setForbidUseBerryAgainstFoes(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHGT_TYPE_BY_WEATHER)) {
            _object.setChgtTypeByWeather(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHGT_TYPE_BY_DAMAGE)) {
            _object.setChgtTypeByDamage(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RECOIL_DAMAGE_FOE)) {
            _object.setRecoilDamageFoe(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RECOIL_DAMAGE_FOE_BY_KO_OWNER)) {
            _object.setRecoilDamageFoeByKoOwner(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DECREASE_NEC_STEPS_HATCH)) {
            _object.setDecreaseNecStepsHatch(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DIVIDE_STATUS_ROUND)) {
            _object.setDivideStatusRound(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP_BY_WEATHER)) {
            _object.setHealHpByWeather(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IGN_ABILITY)) {
            _object.setIgnAbility(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IGN_FOE_TEAM_MOVE)) {
            _object.setIgnFoeTeamMove(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IGN_FOE_STATIS_BOOST)) {
            _object.setIgnFoeStatisBoost(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_MOVE)) {
            _object.setImmuMove(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_LOW_STAT)) {
            _object.setImmuLowStat(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_LOW_STAT_IF_STATUS)) {
            _object.setImmuLowStatIfStatus(getListStatisticStatus(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_CH)) {
            _object.setImmuCh(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_WEATHER)) {
            _object.setImmuWeather(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_DAMAGE_TRAPPING_MOVES)) {
            _object.setImmuDamageTrappingMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_DAMAGE_ALLY_MOVES)) {
            _object.setImmuDamageAllyMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_DAMAGE_RECOIL)) {
            _object.setImmuDamageRecoil(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_ABILITY)) {
            _object.setImmuAbility(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_STATUS_BEGIN_ROUND)) {
            _object.setImmuStatusBeginRound(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_RECHARGE_ROUND)) {
            _object.setImmuRechargeRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_STATUS)) {
            _object.setImmuStatus(DocumentReaderCoreUtil.getStringMapStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SLOWING)) {
            _object.setSlowing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_FOE)) {
            _object.setMultDamageFoe(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_CH)) {
            _object.setMultDamageCh(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_ALLY_DAMAGE)) {
            _object.setMultAllyDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_SUFFERED_DAMAGE_SUPER_EFF)) {
            _object.setMultSufferedDamageSuperEff(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_SUFFERED_DAMAGE_LOW_EFF)) {
            _object.setImmuSufferedDamageLowEff(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_EVT_RATE_CH)) {
            _object.setMultEvtRateCh(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_SEC_EFFECT_OTHER)) {
            _object.setCancelSecEffectOther(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_SEC_EFFECT_OWNER)) {
            _object.setCancelSecEffectOwner(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_EVT_RATE_SEC_EFFECT_OWNER)) {
            _object.setMultEvtRateSecEffectOwner(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_POWER)) {
            _object.setMultPower(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE)) {
            _object.setMultDamage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAB)) {
            _object.setMultStab(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BONUS_STAT_RANK)) {
            _object.setBonusStatRank(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOST_STAT_RANK_PROTECTED)) {
            _object.setBoostStatRankProtected(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOST_STAT_RANK_END_ROUND)) {
            _object.setBoostStatRankEndRound(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_ALLY)) {
            _object.setMultStatAlly(getMapStatisticRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_IF_KO_FOE)) {
            _object.setMultStatIfKoFoe(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_IF_LOW_STAT)) {
            _object.setMultStatIfLowStat(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_IF_CAT)) {
            _object.setMultStatIfCat(getMapStatisticCategoryRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_IF_STATUT_RANK)) {
            _object.setMultStatIfStatutRank(getMapStatisticStatusByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_IF_DAMAGE_CAT)) {
            _object.setMultStatIfDamageCat(getMapStatisticCategoryByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_IF_DAMGE_TYPE)) {
            _object.setMultStatIfDamgeType(getMapStatisticTypeByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING)) {
            _object.setInflictingDamageInsteadOfSuffering(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_VAR_BOOST)) {
            _object.setMultVarBoost(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_USED_PP)) {
            _object.setNbUsedPp(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_HITS)) {
            _object.setNbHits(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BREAK_PROTECTION)) {
            _object.setBreakProtection(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLATE)) {
            _object.setPlate(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALED_STATUS_BY_SWITCH)) {
            _object.setHealedStatusBySwitch(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALED_HP_RATE_BY_SWITCH)) {
            _object.setHealedHpRateBySwitch(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREASED_PRIO)) {
            _object.setIncreasedPrio(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREASED_PRIO_TYPES)) {
            _object.setIncreasedPrioTypes(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MAX_STATISTICS_IF_CH)) {
            _object.setMaxStatisticsIfCh(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SINGLE_STATUS)) {
            _object.setSingleStatus(DocumentReaderMathUtil.getMonteCarloString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACHIEVED_DISAPPEARED_PK)) {
            _object.setAchievedDisappearedPk(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FORWARD_STATUS)) {
            _object.setForwardStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FAIL_STATUS)) {
            _object.setFailStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TYPE_FOR_MOVES)) {
            _object.setTypeForMoves(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MAX_HP_FOR_USING_BERRY)) {
            _object.setMaxHpForUsingBerry(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MUMY)) {
            _object.setMumy(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP_BY_TYPE_IF_WEATHER)) {
            _object.setHealHpByTypeIfWeather(getMapWeatherTypeRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_MOVE_TYPES_BY_WEATHER)) {
            _object.setImmuMoveTypesByWeather(DocumentReaderCoreUtil.getStringMapStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRound(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECT_SENDING)) {
            _object.setEffectSending(getListEffectWhileSending(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHANGING_BOOST_TYPES)) {
            _object.setChangingBoostTypes(getStringMapTypeDamageBoost(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_ALLY_FROM_MOVES)) {
            _object.setImmuAllyFromMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_STATUS_TYPES)) {
            _object.setImmuStatusTypes(DocumentReaderCoreUtil.getStringMapStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_LOW_STATIS_TYPES)) {
            _object.setImmuLowStatisTypes(getStringMapListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOW_STAT_FOE_HIT)) {
            _object.setLowStatFoeHit(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_COPY_MOVES_TYPES)) {
            _object.setCopyMovesTypes(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_POWER_MOVES_TYPES_GLOBAL)) {
            _object.setMultPowerMovesTypesGlobal(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL)) {
            _object.setReverseEffectsPowerMovesTypesGlobal(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP_WHILE_USING_BERRY)) {
            _object.setHealHpWhileUsingBerry(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TAKE_ITEM_BY_DAMAGING_MOVE)) {
            _object.setTakeItemByDamagingMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GIVE_ITEM_TO_ALLY_HAVING_USED)) {
            _object.setGiveItemToAllyHavingUsed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static EffectWhileSending getEffectWhileSending(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_EFFECT_WHILE_SENDING_SIMPLE)) {
            EffectWhileSendingSimple object_ = Instances.newEffectWhileSendingSimple();
            for (Element c: childElements_) {
                getEffectWhileSending(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_WHILE_SENDING_WITH_STATISTIC)) {
            EffectWhileSendingWithStatistic object_ = Instances.newEffectWhileSendingWithStatistic();
            for (Element c: childElements_) {
                getEffectWhileSendingWithStatistic(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newEffectWhileSendingSimple();
    }

    private static void getEffectWhileSending(EffectWhileSending _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_DISABLE_WEATHER)) {
            _object.setDisableWeather(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_WEATHER)) {
            _object.setEnabledWeather(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_COPYING_ABILITY)) {
            _object.setCopyingAbility(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLATE)) {
            _object.setPlate(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_WEIGHT)) {
            _object.setMultWeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
    }

    private static void getEffectWhileSendingWithStatistic(EffectWhileSendingWithStatistic _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_EFFECT)) {
            _object.setEffect(getEffectStatistic(_element));
            return;
        }
        getEffectWhileSending(_object, _fieldName, _element);
    }

    private static Statistic getStatistic(Element _elt) {
        for (Statistic e: Statistic.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return Statistic.SPEED;
    }

    private static void getBall(Ball _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CATCHING_RATE)) {
            _object.setCatchingRate(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getItem(_object, _fieldName, _element);
    }

    private static void getBerry(Berry _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP_BY_SUPER_EFF_MOVE)) {
            _object.setHealHpBySuperEffMove(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAW_FOR_ATTACK_FIRST)) {
            _object.setLawForAttackFirst(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_FOES_DAMAGE)) {
            _object.setMultFoesDamage(getStringMapEfficiencyRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticBoostHpRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WITHOUT_FAIL)) {
            _object.setWithoutFail(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_PP)) {
            _object.setHealPp(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP)) {
            _object.setHealHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MAX_HP_HEALING_HP)) {
            _object.setMaxHpHealingHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_STATUS)) {
            _object.setHealStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP_RATE)) {
            _object.setHealHpRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MAX_HP_HEALING_HP_RATE)) {
            _object.setMaxHpHealingHpRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_RATE_RECOIL_FOE)) {
            _object.setDamageRateRecoilFoe(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CATEGORY_BOOSTING)) {
            _object.setCategoryBoosting(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOST_STATIS)) {
            _object.setBoostStatis(getMapStatisticByte(_element));
            return;
        }
        getItem(_object, _fieldName, _element);
    }

    private static void getBoost(Boost _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_WIN_PP)) {
            _object.setWinPp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EVS)) {
            _object.setEvs(getMapStatisticShort(_element));
            return;
        }
        getItem(_object, _fieldName, _element);
    }

    private static void getFossil(Fossil _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getItem(_object, _fieldName, _element);
    }

    private static void getHealingHp(HealingHp _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_HP)) {
            _object.setHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getHealingItem(_object, _fieldName, _element);
    }

    private static void getHealingHpStatus(HealingHpStatus _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_HEALED_HP_RATE)) {
            _object.setHealedHpRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getHealingStatus(_object, _fieldName, _element);
    }

    private static void getHealingItem(HealingItem _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALING_TEAM)) {
            _object.setHealingTeam(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getItem(_object, _fieldName, _element);
    }

    private static void getHealingPp(HealingPp _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_HEALED_MOVE_PP)) {
            _object.setHealedMovePp(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALING_ALL_MOVES_FULLPP)) {
            _object.setHealingAllMovesFullpp(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALING_ALL_MOVES_PP)) {
            _object.setHealingAllMovesPp(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALING_MOVE_FULLPP)) {
            _object.setHealingMoveFullpp(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getHealingItem(_object, _fieldName, _element);
    }

    private static void getHealingStatus(HealingStatus _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALING_KO)) {
            _object.setHealingKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getHealingItem(_object, _fieldName, _element);
    }

    public static Item getItem(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newItemForBattle();
        }
        return getItem(doc_.getDocumentElement());
    }
    private static Item getItem(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_BALL)) {
            Ball object_ = Instances.newBall();
            for (Element c: childElements_) {
                getBall(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_BERRY)) {
            Berry object_ = Instances.newBerry();
            for (Element c: childElements_) {
                getBerry(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_BOOST)) {
            Boost object_ = Instances.newBoost();
            for (Element c: childElements_) {
                getBoost(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLVING_ITEM)) {
            EvolvingItem object_ = Instances.newEvolvingItem();
            for (Element c: childElements_) {
                getItem(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLVING_STONE)) {
            EvolvingStone object_ = Instances.newEvolvingStone();
            for (Element c: childElements_) {
                getItem(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_FOSSIL)) {
            Fossil object_ = Instances.newFossil();
            for (Element c: childElements_) {
                getFossil(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_HEALING_HP)) {
            HealingHp object_ = Instances.newHealingHp();
            for (Element c: childElements_) {
                getHealingHp(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_HEALING_HP_STATUS)) {
            HealingHpStatus object_ = Instances.newHealingHpStatus();
            for (Element c: childElements_) {
                getHealingHpStatus(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_HEALING_PP)) {
            HealingPp object_ = Instances.newHealingPp();
            for (Element c: childElements_) {
                getHealingPp(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_HEALING_SIMPLE_ITEM)) {
            HealingSimpleItem object_ = Instances.newHealingSimpleItem();
            for (Element c: childElements_) {
                getHealingItem(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_HEALING_SIMPLE_STATUS)) {
            HealingSimpleStatus object_ = Instances.newHealingSimpleStatus();
            for (Element c: childElements_) {
                getHealingStatus(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_ITEM_FOR_BATTLE)) {
            ItemForBattle object_ = Instances.newItemForBattle();
            for (Element c: childElements_) {
                getItemForBattle(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_REPEL)) {
            Repel object_ = Instances.newRepel();
            for (Element c: childElements_) {
                getRepel(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_SELLING_ITEM)) {
            SellingItem object_ = Instances.newSellingItem();
            for (Element c: childElements_) {
                getItem(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newItemForBattle();
    }

    private static void getItem(Item _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PRICE)) {
            _object.setPrice(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static void getItemForBattle(ItemForBattle _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TYPES_PK)) {
            _object.setTypesPk(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_IMMU_TYPE)) {
            _object.setCancelImmuType(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_AGAINST_EVO)) {
            _object.setAgainstEvo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ATTACK_LAST)) {
            _object.setAttackLast(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOST_EXP)) {
            _object.setBoostExp(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_STATUS)) {
            _object.setImmuStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_LOW_STATIS)) {
            _object.setImmuLowStatis(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREASING_MAX_NB_ROUND_TRAP)) {
            _object.setIncreasingMaxNbRoundTrap(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ATTACKS_SOON)) {
            _object.setAttacksSoon(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REPELLING_WILD_PK)) {
            _object.setRepellingWildPk(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SYNCHRO_STATUS)) {
            _object.setSynchroStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FAIL_STATUS)) {
            _object.setFailStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROTECT_AGAINST_KO)) {
            _object.setProtectAgainstKo(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROTECT_AGAINST_KO_IF_FULL_HP)) {
            _object.setProtectAgainstKoIfFullHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DRAINED_HP_BY_DAMAGE_RATE)) {
            _object.setDrainedHpByDamageRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WIN_EV_FIGHT)) {
            _object.setWinEvFight(getMapStatisticShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAW_FOR_ATTACK_FIRST)) {
            _object.setLawForAttackFirst(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_TRAPPING_DAMAGE)) {
            _object.setMultTrappingDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_WINNING_MONEY)) {
            _object.setMultWinningMoney(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_WINNING_HAPPINESS)) {
            _object.setMultWinningHappiness(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_WINNING_EV)) {
            _object.setMultWinningEv(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_WINNING_EXP)) {
            _object.setMultWinningExp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_POWER)) {
            _object.setMultPower(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE)) {
            _object.setMultDamage(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DRAINED_HP)) {
            _object.setMultDrainedHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_RECOIL)) {
            _object.setDamageRecoil(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_RANK)) {
            _object.setMultStatRank(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_POKEMON_RANK)) {
            _object.setMultStatPokemonRank(getMapStatisticPokemonByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREASING_MAX_NB_ROUND_GLOBAL_MOVE)) {
            _object.setIncreasingMaxNbRoundGlobalMove(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREASING_MAX_NB_ROUND_TEAM_MOVE)) {
            _object.setIncreasingMaxNbRoundTeamMove(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_MOVES)) {
            _object.setImmuMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HATCHING)) {
            _object.setHatching(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_TYPES)) {
            _object.setImmuTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMU_WEATHER)) {
            _object.setImmuWeather(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOST_STATIS_SUPER_EFF)) {
            _object.setBoostStatisSuperEff(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOST_STATIS_TYPES)) {
            _object.setBoostStatisTypes(getStringMapMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DETRUIT_SI_CONTACT)) {
            _object.setDetruitSiContact(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SWITCH_POSSIBLE_SI_TOUCHE)) {
            _object.setSwitchPossibleSiTouche(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SWITCH_FORCE_ADV_SI_TOUCHE)) {
            _object.setSwitchForceAdvSiTouche(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TRANSFERT_OBJ_SI_CONTACT)) {
            _object.setTransfertObjSiContact(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRound(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECT_SENDING)) {
            _object.setEffectSending(getListEffectWhileSending(_element));
            return;
        }
        getItem(_object, _fieldName, _element);
    }

    private static void getRepel(Repel _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_STEPS)) {
            _object.setSteps(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        getItem(_object, _fieldName, _element);
    }

    private static void getDamagingMoveData(DamagingMoveData _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CATEGORY)) {
            _object.setCategory(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DIRECT)) {
            _object.setDirect(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANNOT_KO)) {
            _object.setCannotKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STOPPABLE_MOVE_KO_SINGLE)) {
            _object.setStoppableMoveKoSingle(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getMoveData(_object, _fieldName, _element);
    }

    public static MoveData getMoveData(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newDamagingMoveData();
        }
        return getMoveData(doc_.getDocumentElement());
    }

    private static MoveData getMoveData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_DAMAGING_MOVE_DATA)) {
            DamagingMoveData object_ = Instances.newDamagingMoveData();
            for (Element c: childElements_) {
                getDamagingMoveData(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_STATUS_MOVE_DATA)) {
            StatusMoveData object_ = Instances.newStatusMoveData();
            for (Element c: childElements_) {
                getStatusMoveData(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newDamagingMoveData();
    }

    private static void getMoveData(MoveData _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NOT_TRANSLATED)) {
            _object.setNotTranslated(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PP)) {
            _object.setPp(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TYPES)) {
            _object.setTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOSTED_TYPES)) {
            _object.setBoostedTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PRIORITY)) {
            _object.setPriority(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACCURACY)) {
            _object.setAccuracy(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECTS)) {
            _object.setEffects(getListEffect(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_PREPA_ROUND)) {
            _object.setNbPrepaRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISAPPEAR_BEFORE_USE)) {
            _object.setDisappearBeforeUse(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REPEAT_ROUND_LAW)) {
            _object.setRepeatRoundLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RANK_INCREMENT_NB_ROUND)) {
            _object.setRankIncrementNbRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RECHARGE_ROUND)) {
            _object.setRechargeRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CONST_USER_CHOICE)) {
            _object.setConstUserChoice(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STOPPABLE_MOVE_SOLO)) {
            _object.setStoppableMoveSolo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STOPPABLE_MOVE_MULTI)) {
            _object.setStoppableMoveMulti(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STOPPABLE_MOVE_PRIO)) {
            _object.setStoppableMovePrio(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SEC_EFFECT_IF_NO_DAMAGE)) {
            _object.setSecEffectIfNoDamage(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SEC_EFFECTS_BY_ITEM)) {
            _object.setSecEffectsByItem(DocumentReaderCoreUtil.getStringMapListInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IGN_VAR_ACCUR_USER_NEG)) {
            _object.setIgnVarAccurUserNeg(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IGN_VAR_EVAS_TARGET_POS)) {
            _object.setIgnVarEvasTargetPos(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BREAK_IMMU_TYPE_ABILITY)) {
            _object.setBreakImmuTypeAbility(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACHIEVE_DISAPPEARED_PK_USING_MOVE)) {
            _object.setAchieveDisappearedPkUsingMove(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SWITCH_TYPE)) {
            _object.setSwitchType(getSwitchType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TYPES_BY_OWNED_ITEM)) {
            _object.setTypesByOwnedItem(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TYPES_BY_WEATHER)) {
            _object.setTypesByWeather(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TARGET_CHOICE)) {
            _object.setTargetChoice(getTargetChoice(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DELETED_STATUS)) {
            _object.setDeletedStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REQUIRED_STATUS)) {
            _object.setRequiredStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
    }

    private static void getStatusMoveData(StatusMoveData _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_THIEVABLE_MOVE)) {
            _object.setThievableMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_COUNTERABLE_MOVE)) {
            _object.setCounterableMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getMoveData(_object, _fieldName, _element);
    }

    private static Effect getEffect(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_EFFECT_ACCURACY)) {
            EffectAccuracy object_ = Instances.newEffectAccuracy();
            for (Element c: childElements_) {
                getEffect(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_ALLY)) {
            EffectAlly object_ = Instances.newEffectAlly();
            for (Element c: childElements_) {
                getEffectAlly(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_BATON_PASS)) {
            EffectBatonPass object_ = Instances.newEffectBatonPass();
            for (Element c: childElements_) {
                getEffect(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_CLONE)) {
            EffectClone object_ = Instances.newEffectClone();
            for (Element c: childElements_) {
                getEffectClone(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_COMMON_STATISTICS)) {
            EffectCommonStatistics object_ = Instances.newEffectCommonStatistics();
            for (Element c: childElements_) {
                getEffectCommonStatistics(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_COPY_FIGHTER)) {
            EffectCopyFighter object_ = Instances.newEffectCopyFighter();
            for (Element c: childElements_) {
                getEffectCopyFighter(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_COPY_MOVE)) {
            EffectCopyMove object_ = Instances.newEffectCopyMove();
            for (Element c: childElements_) {
                getEffectCopyMove(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_COUNTER_ATTACK)) {
            EffectCounterAttack object_ = Instances.newEffectCounterAttack();
            for (Element c: childElements_) {
                getEffectCounterAttack(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_DAMAGE)) {
            EffectDamage object_ = Instances.newEffectDamage();
            for (Element c: childElements_) {
                getEffectDamage(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_DAMAGE_RATE)) {
            EffectDamageRate object_ = Instances.newEffectDamageRate();
            for (Element c: childElements_) {
                getEffectDamageRate(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_FOE)) {
            EffectEndRoundFoe object_ = Instances.newEffectEndRoundFoe();
            for (Element c: childElements_) {
                getEffectEndRoundFoe(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_GLOBAL)) {
            EffectEndRoundGlobal object_ = Instances.newEffectEndRoundGlobal();
            for (Element c: childElements_) {
                getEffectEndRound(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_INDIVIDUAL)) {
            EffectEndRoundIndividual object_ = Instances.newEffectEndRoundIndividual();
            for (Element c: childElements_) {
                getEffectEndRoundIndividual(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_MULTI_RELATION)) {
            EffectEndRoundMultiRelation object_ = Instances.newEffectEndRoundMultiRelation();
            for (Element c: childElements_) {
                getEffectEndRoundMultiRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_POSITION_RELATION)) {
            EffectEndRoundPositionRelation object_ = Instances.newEffectEndRoundPositionRelation();
            for (Element c: childElements_) {
                getEffectEndRoundPositionRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION)) {
            EffectEndRoundPositionTargetRelation object_ = Instances.newEffectEndRoundPositionTargetRelation();
            for (Element c: childElements_) {
                getEffectEndRound(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_SINGLE_RELATION)) {
            EffectEndRoundSingleRelation object_ = Instances.newEffectEndRoundSingleRelation();
            for (Element c: childElements_) {
                getEffectEndRoundSingleRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_SINGLE_STATUS)) {
            EffectEndRoundSingleStatus object_ = Instances.newEffectEndRoundSingleStatus();
            for (Element c: childElements_) {
                getEffectEndRoundSingleStatus(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_STATUS_RELATION)) {
            EffectEndRoundStatusRelation object_ = Instances.newEffectEndRoundStatusRelation();
            for (Element c: childElements_) {
                getEffectEndRoundStatusRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_TEAM)) {
            EffectEndRoundTeam object_ = Instances.newEffectEndRoundTeam();
            for (Element c: childElements_) {
                getEffectEndRoundTeam(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_FULL_HP_RATE)) {
            EffectFullHpRate object_ = Instances.newEffectFullHpRate();
            for (Element c: childElements_) {
                getEffectFullHpRate(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_GLOBAL)) {
            EffectGlobal object_ = Instances.newEffectGlobal();
            for (Element c: childElements_) {
                getEffectGlobal(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_INVOKE)) {
            EffectInvoke object_ = Instances.newEffectInvoke();
            for (Element c: childElements_) {
                getEffectInvoke(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER)) {
            EffectMultSufferedMovePower object_ = Instances.newEffectMultSufferedMovePower();
            for (Element c: childElements_) {
                getEffectMultSufferedMovePower(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_MULT_USED_MOVE_POWER)) {
            EffectMultUsedMovePower object_ = Instances.newEffectMultUsedMovePower();
            for (Element c: childElements_) {
                getEffectMultUsedMovePower(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_ORDER)) {
            EffectOrder object_ = Instances.newEffectOrder();
            for (Element c: childElements_) {
                getEffectOrder(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_PROTECT_FROM_TYPES)) {
            EffectProtectFromTypes object_ = Instances.newEffectProtectFromTypes();
            for (Element c: childElements_) {
                getEffectProtectFromTypes(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_PROTECTION)) {
            EffectProtection object_ = Instances.newEffectProtection();
            for (Element c: childElements_) {
                getEffectProtection(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_REMAINED_HP_RATE)) {
            EffectRemainedHpRate object_ = Instances.newEffectRemainedHpRate();
            for (Element c: childElements_) {
                getEffectRemainedHpRate(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_RESTRICTION)) {
            EffectRestriction object_ = Instances.newEffectRestriction();
            for (Element c: childElements_) {
                getEffectRestriction(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_STATISTIC)) {
            EffectStatistic object_ = Instances.newEffectStatistic();
            for (Element c: childElements_) {
                getEffectStatistic(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_STATUS)) {
            EffectStatus object_ = Instances.newEffectStatus();
            for (Element c: childElements_) {
                getEffectStatus(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_SWITCH_ABILITIES)) {
            EffectSwitchAbilities object_ = Instances.newEffectSwitchAbilities();
            for (Element c: childElements_) {
                getEffectSwitchAbilities(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_SWITCH_ITEMS)) {
            EffectSwitchItems object_ = Instances.newEffectSwitchItems();
            for (Element c: childElements_) {
                getEffectSwitchItems(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_SWITCH_MOVE_TYPES)) {
            EffectSwitchMoveTypes object_ = Instances.newEffectSwitchMoveTypes();
            for (Element c: childElements_) {
                getEffectSwitchMoveTypes(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_SWITCH_POINT_VIEW)) {
            EffectSwitchPointView object_ = Instances.newEffectSwitchPointView();
            for (Element c: childElements_) {
                getEffectSwitchPointView(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_SWITCH_POSITION)) {
            EffectSwitchPosition object_ = Instances.newEffectSwitchPosition();
            for (Element c: childElements_) {
                getEffect(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_SWITCH_TYPES)) {
            EffectSwitchTypes object_ = Instances.newEffectSwitchTypes();
            for (Element c: childElements_) {
                getEffectSwitchTypes(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_TEAM)) {
            EffectTeam object_ = Instances.newEffectTeam();
            for (Element c: childElements_) {
                getEffectTeam(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_TEAM_WHILE_SEND_FOE)) {
            EffectTeamWhileSendFoe object_ = Instances.newEffectTeamWhileSendFoe();
            for (Element c: childElements_) {
                getEffectTeamWhileSendFoe(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_UNPROTECT_FROM_TYPES)) {
            EffectUnprotectFromTypes object_ = Instances.newEffectUnprotectFromTypes();
            for (Element c: childElements_) {
                getEffectUnprotectFromTypes(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_VAR_P_P)) {
            EffectVarPP object_ = Instances.newEffectVarPP();
            for (Element c: childElements_) {
                getEffectVarPP(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_WIN_MONEY)) {
            EffectWinMoney object_ = Instances.newEffectWinMoney();
            for (Element c: childElements_) {
                getEffectWinMoney(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newEffectDamage();
    }

    private static void getEffect(Effect _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TARGET_CHOICE)) {
            _object.setTargetChoice(getTargetChoice(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FAIL)) {
            _object.setFail(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REQUIRED_SUCCESSFUL_EFFECTS)) {
            _object.setRequiredSuccessfulEffects(DocumentReaderCoreUtil.getListInteger(_element));
            return;
        }
    }

    private static void getEffectAlly(EffectAlly _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MULT_ALLY_DAMAGE)) {
            _object.setMultAllyDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectClone(EffectClone _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_HP_RATE_CLONE)) {
            _object.setHpRateClone(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectCombo getEffectCombo(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectCombo object_ = Instances.newEffectCombo();
        for (Element c: childElements_) {
            getEffectCombo(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getEffectCombo(EffectCombo _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MULT_EVT_RATE_SEC_EFF)) {
            _object.setMultEvtRateSecEff(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REPEATED_ROUNDS_LAW)) {
            _object.setRepeatedRoundsLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RANK_INCREMENT_NB_ROUND)) {
            _object.setRankIncrementNbRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRoundFoe(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TEAM_MOVE)) {
            _object.setTeamMove(getListEffectTeam(_element));
            return;
        }
    }

    private static void getEffectCommonStatistics(EffectCommonStatistics _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_COMMON_VALUE)) {
            _object.setCommonValue(getMapStatisticString(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectCopyFighter(EffectCopyFighter _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PP_FOR_MOVES)) {
            _object.setPpForMoves(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectCopyMove(EffectCopyMove _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_COPYING_MOVE_FOR_USER)) {
            _object.setCopyingMoveForUser(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_COPYING_MOVE_FOR_USER_DEF)) {
            _object.setCopyingMoveForUserDef(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES_NOT_TO_BE_COPIED)) {
            _object.setMovesNotToBeCopied(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectCounterAttack(EffectCounterAttack _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_SUFFERING_DAMAGE_TYPES)) {
            _object.setSufferingDamageTypes(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DROPPED_STAT_DIRECT_MOVE)) {
            _object.setDroppedStatDirectMove(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SUFFERING_DAMAGE_DIRECT_MOVE)) {
            _object.setSufferingDamageDirectMove(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROTECT_FAIL)) {
            _object.setProtectFail(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_COUNTER_FAIL)) {
            _object.setCounterFail(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectDamage(EffectDamage _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CH_RATE)) {
            _object.setChRate(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CONST_DAMAGE)) {
            _object.setConstDamage(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_LAW)) {
            _object.setDamageLaw(DocumentReaderMathUtil.getMonteCarloString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_AGAINST)) {
            _object.setMultDamageAgainst(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CH_LAW)) {
            _object.setChLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HITS_LAW)) {
            _object.setHitsLaw(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_POWER)) {
            _object.setPower(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RAND_MAX)) {
            _object.setRandMax(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SUMMING_USER_TEAM_OK_FIGHTER)) {
            _object.setSummingUserTeamOkFighter(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IGN_VAR_STAT_TARGET_POS)) {
            _object.setIgnVarStatTargetPos(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IGN_VAR_STAT_USER_NEG)) {
            _object.setIgnVarStatUserNeg(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USER_ATTACK)) {
            _object.setUserAttack(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATIS_ATT)) {
            _object.setStatisAtt(getStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TARGET_DEFENSE)) {
            _object.setTargetDefense(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATIS_DEF)) {
            _object.setStatisDef(getStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOOST_STATIS_ONCE_KO_FOE)) {
            _object.setBoostStatisOnceKoFoe(getMapStatisticByte(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectDamageRate(EffectDamageRate _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_RATE_DAMAGE)) {
            _object.setRateDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectEndRound getEffectEndRound(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_FOE)) {
            EffectEndRoundFoe object_ = Instances.newEffectEndRoundFoe();
            for (Element c: childElements_) {
                getEffectEndRoundFoe(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_GLOBAL)) {
            EffectEndRoundGlobal object_ = Instances.newEffectEndRoundGlobal();
            for (Element c: childElements_) {
                getEffectEndRound(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_INDIVIDUAL)) {
            EffectEndRoundIndividual object_ = Instances.newEffectEndRoundIndividual();
            for (Element c: childElements_) {
                getEffectEndRoundIndividual(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_MULTI_RELATION)) {
            EffectEndRoundMultiRelation object_ = Instances.newEffectEndRoundMultiRelation();
            for (Element c: childElements_) {
                getEffectEndRoundMultiRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_POSITION_RELATION)) {
            EffectEndRoundPositionRelation object_ = Instances.newEffectEndRoundPositionRelation();
            for (Element c: childElements_) {
                getEffectEndRoundPositionRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION)) {
            EffectEndRoundPositionTargetRelation object_ = Instances.newEffectEndRoundPositionTargetRelation();
            for (Element c: childElements_) {
                getEffectEndRound(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_SINGLE_RELATION)) {
            EffectEndRoundSingleRelation object_ = Instances.newEffectEndRoundSingleRelation();
            for (Element c: childElements_) {
                getEffectEndRoundSingleRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_SINGLE_STATUS)) {
            EffectEndRoundSingleStatus object_ = Instances.newEffectEndRoundSingleStatus();
            for (Element c: childElements_) {
                getEffectEndRoundSingleStatus(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_STATUS_RELATION)) {
            EffectEndRoundStatusRelation object_ = Instances.newEffectEndRoundStatusRelation();
            for (Element c: childElements_) {
                getEffectEndRoundStatusRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_TEAM)) {
            EffectEndRoundTeam object_ = Instances.newEffectEndRoundTeam();
            for (Element c: childElements_) {
                getEffectEndRoundTeam(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newEffectEndRoundIndividual();
    }

    private static void getEffectEndRound(EffectEndRound _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FAIL_END_ROUND)) {
            _object.setFailEndRound(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_END_ROUND_RANK)) {
            _object.setEndRoundRank(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectEndRoundFoe getEffectEndRoundFoe(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectEndRoundFoe object_ = Instances.newEffectEndRoundFoe();
        for (Element c: childElements_) {
            getEffectEndRoundFoe(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getEffectEndRoundFoe(EffectEndRoundFoe _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_INFLICTED_RATE_HP_TARGET)) {
            _object.setInflictedRateHpTarget(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundIndividual(EffectEndRoundIndividual _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_DELETE_ALL_STATUS)) {
            _object.setDeleteAllStatus(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RECOIL_DAMAGE)) {
            _object.setRecoilDamage(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP)) {
            _object.setHealHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP_BY_OWNER_TYPES)) {
            _object.setHealHpByOwnerTypes(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_STATUS)) {
            _object.setMultDamageStatus(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USER_STATUS_END_ROUND)) {
            _object.setUserStatusEndRound(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundMultiRelation(EffectEndRoundMultiRelation _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_BY_STATUS)) {
            _object.setDamageByStatus(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_STATUS)) {
            _object.setMultDamageStatus(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundPositionRelation(EffectEndRoundPositionRelation _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_HEAL_HP)) {
            _object.setHealHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundSingleRelation(EffectEndRoundSingleRelation _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS)) {
            _object.setRateDamageFunctionOfNbRounds(DocumentReaderMathUtil.getMapLongRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAW_FOR_ENABLING_EFFECT)) {
            _object.setLawForEnablingEffect(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundSingleStatus(EffectEndRoundSingleStatus _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_STATUS)) {
            _object.setMultDamageStatus(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREMENTING_DAMAGE_BY_ROUNDS)) {
            _object.setIncrementingDamageByRounds(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffectEndRoundStatus(_object, _fieldName, _element);
    }

    private static EffectEndRoundStatus getEffectEndRoundStatus(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_SINGLE_STATUS)) {
            EffectEndRoundSingleStatus object_ = Instances.newEffectEndRoundSingleStatus();
            for (Element c: childElements_) {
                getEffectEndRoundSingleStatus(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EFFECT_END_ROUND_STATUS_RELATION)) {
            EffectEndRoundStatusRelation object_ = Instances.newEffectEndRoundStatusRelation();
            for (Element c: childElements_) {
                getEffectEndRoundStatusRelation(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newEffectEndRoundSingleStatus();
    }

    private static void getEffectEndRoundStatus(EffectEndRoundStatus _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_INFLICTED_RATE_HP_TARGET)) {
            _object.setInflictedRateHpTarget(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundStatusRelation(EffectEndRoundStatusRelation _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_THIEVED_HP_RATE_TARGET_TO_USER)) {
            _object.setThievedHpRateTargetToUser(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRoundStatus(_object, _fieldName, _element);
    }

    private static void getEffectEndRoundTeam(EffectEndRoundTeam _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_DELETE_ALL_STATUS)) {
            _object.setDeleteAllStatus(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DELETE_ALL_STATUS_ALLY)) {
            _object.setDeleteAllStatusAlly(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffectEndRound(_object, _fieldName, _element);
    }

    private static void getEffectFullHpRate(EffectFullHpRate _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LEFT_USER_HP)) {
            _object.setLeftUserHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RESTORED_HP)) {
            _object.setRestoredHp(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CLOSEST_FOE_DAMAGE_RATE_HP)) {
            _object.setClosestFoeDamageRateHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectGlobal(EffectGlobal _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PRISE_EN_COMPTE_PK_LANCEUR)) {
            _object.setPriseEnComptePkLanceur(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WEATHER)) {
            _object.setWeather(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCELED_IF_USED)) {
            _object.setCanceledIfUsed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REVERSE_ORDER_OF_SORT_BY_SPEED)) {
            _object.setReverseOrderOfSortBySpeed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PUTTING_KO)) {
            _object.setPuttingKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_ACCURACY)) {
            _object.setMultAccuracy(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_UNUSABLE_ITEM)) {
            _object.setUnusableItem(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PREVENT_STATUS)) {
            _object.setPreventStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMMUNE_TYPES)) {
            _object.setImmuneTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_END_ROUND)) {
            _object.setDamageEndRound(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALING_END_ROUND)) {
            _object.setHealingEndRound(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEALING_END_ROUND_GROUND)) {
            _object.setHealingEndRoundGround(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFICIENCY_MOVES)) {
            _object.setEfficiencyMoves(getMapTypesDuoRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISABLE_IMMU_AGAINST_TYPES)) {
            _object.setDisableImmuAgainstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_PROTECTING_ABILITIES)) {
            _object.setCancelProtectingAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_UNUSABLE_MOVES)) {
            _object.setUnusableMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_PREPA_ROUND)) {
            _object.setMultDamagePrepaRound(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES_USED_BY_TARGETED_FIGHTERS)) {
            _object.setMovesUsedByTargetedFighters(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_EFFECT_LOVING_ALLY)) {
            _object.setMultEffectLovingAlly(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_POWER_MOVES)) {
            _object.setMultPowerMoves(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT_IF_CONTAINS_TYPE)) {
            _object.setMultStatIfContainsType(getMapStatisticTypeRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_EFFECTS)) {
            _object.setCancelEffects(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_TYPES_MOVES)) {
            _object.setMultDamageTypesMoves(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_CHGT_STAT)) {
            _object.setCancelChgtStat(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKED_MOVE_TERRAIN)) {
            _object.setInvokedMoveTerrain(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHANGED_TYPES_TERRAIN)) {
            _object.setChangedTypesTerrain(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectInvoke(EffectInvoke _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MOVE_FCT_ENV)) {
            _object.setMoveFctEnv(getMapEnvironmentTypeString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKING_MOVE_BUT_USER)) {
            _object.setInvokingMoveButUser(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKING_TARGET_CHOSEN_MOVE)) {
            _object.setInvokingTargetChosenMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKING_USER_MOVE_WHILE_SLEEP)) {
            _object.setInvokingUserMoveWhileSleep(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKING_ALLY_MOVE)) {
            _object.setInvokingAllyMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKING_TARGET_SUCCESFUL_MOVE)) {
            _object.setInvokingTargetSuccesfulMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKING_SUFFERED_MOVE)) {
            _object.setInvokingSufferedMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVOKING_MOVE_BY_USER_TYPES)) {
            _object.setInvokingMoveByUserTypes(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES_NOT_TO_BE_INVOKED)) {
            _object.setMovesNotToBeInvoked(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RATE_INVOKATION_MOVE)) {
            _object.setRateInvokationMove(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectMultSufferedMovePower(EffectMultSufferedMovePower _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MULT_MOVE_POWER_FCT_TYPE)) {
            _object.setMultMovePowerFctType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectMultUsedMovePower(EffectMultUsedMovePower _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MULT_MOVE_POWER_FCT_TYPE)) {
            _object.setMultMovePowerFctType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectOrder(EffectOrder _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TARGET_ATTACKS_LAST)) {
            _object.setTargetAttacksLast(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectProtectFromTypes(EffectProtectFromTypes _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_IMMU_AGAINST_TYPES)) {
            _object.setImmuAgainstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectProtection(EffectProtection _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PROT_SINGLE)) {
            _object.setProtSingle(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROT_SINGLE_AGAINST_KO)) {
            _object.setProtSingleAgainstKo(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROT_TEAM_AGAINST_MULT_TARGETS)) {
            _object.setProtTeamAgainstMultTargets(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROT_TEAM_AGAINST_PRIO)) {
            _object.setProtTeamAgainstPrio(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROT_TEAM_AGAINST_STATUS_MOVES)) {
            _object.setProtTeamAgainstStatusMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROT_TEAM_AGAINST_DAMAGE_MOVES)) {
            _object.setProtTeamAgainstDamageMoves(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectRemainedHpRate(EffectRemainedHpRate _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_RATE_HP)) {
            _object.setRateHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectRestriction(EffectRestriction _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FORBID_TARGET_USING_ITEM)) {
            _object.setForbidTargetUsingItem(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHOICE_RESTRICTION)) {
            _object.setChoiceRestriction(getMoveChoiceRestrictionType(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectStatistic getEffectStatistic(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectStatistic object_ = Instances.newEffectStatistic();
        for (Element c: childElements_) {
            getEffectStatistic(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getEffectStatistic(EffectStatistic _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_STATIS_VAR_RANK)) {
            _object.setStatisVarRank(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOCAL_FAIL_STATIS)) {
            _object.setLocalFailStatis(getMapStatisticString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EVT_RATE)) {
            _object.setEvtRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_COPY_BOOST)) {
            _object.setCopyBoost(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SWAP_BOOST_STATIS)) {
            _object.setSwapBoostStatis(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOCAL_FAIL_SWAP_BOOST_STATIS)) {
            _object.setLocalFailSwapBoostStatis(getMapStatisticString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAW_BOOST)) {
            _object.setLawBoost(getMonteCarloEnumStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_LOW_STAT)) {
            _object.setCancelLowStat(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_CHGT_STAT)) {
            _object.setCancelChgtStat(getListStatistic(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectStatus(EffectStatus _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LAW_STATUS)) {
            _object.setLawStatus(DocumentReaderMathUtil.getMonteCarloString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DELETED_STATUS)) {
            _object.setDeletedStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOCAL_FAIL_STATUS)) {
            _object.setLocalFailStatus(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_KO_USER_HEAL_SUBST)) {
            _object.setKoUserHealSubst(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATUS_FROM_USER)) {
            _object.setStatusFromUser(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchAbilities(EffectSwitchAbilities _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_EXCHANGE_ABILITY)) {
            _object.setExchangeAbility(getExchangeType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CONST_ABILITY)) {
            _object.setConstAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchItems(EffectSwitchItems _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MOVE_OBJECT)) {
            _object.setMoveObject(getMoveItemType(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchMoveTypes(EffectSwitchMoveTypes _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CHANGE_TYPES)) {
            _object.setChangeTypes(DocumentReaderCoreUtil.getStringMapString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REPLACING_TYPES)) {
            _object.setReplacingTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchPointView(EffectSwitchPointView _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_POINT_VIEW_CHANGEMENT)) {
            _object.setPointViewChangement(getPointViewChangementType(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectSwitchTypes(EffectSwitchTypes _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CHGT_TYPE_BY_ENV)) {
            _object.setChgtTypeByEnv(getMapEnvironmentTypeString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CONST_VALUES_TYPE)) {
            _object.setConstValuesType(getConstValuesType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EXCHANGE_TYPES)) {
            _object.setExchangeTypes(getExchangeType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CONST_TYPES)) {
            _object.setConstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ADDED_TYPES)) {
            _object.setAddedTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static EffectTeam getEffectTeam(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectTeam object_ = Instances.newEffectTeam();
        for (Element c: childElements_) {
            getEffectTeam(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getEffectTeam(EffectTeam _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FORBIDDING_HEALING)) {
            _object.setForbiddingHealing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FORBIDDEN_BOOST)) {
            _object.setForbiddenBoost(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_UNUSABLE_MOVES)) {
            _object.setUnusableMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_CHGT_STAT_FOE_TEAM)) {
            _object.setCancelChgtStatFoeTeam(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CANCEL_CHGT_STAT_TEAM)) {
            _object.setCancelChgtStatTeam(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE)) {
            _object.setMultDamage(getMapCategoryMultRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STATISTIC)) {
            _object.setMultStatistic(getMapStatisticRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STATISTIC_FOE)) {
            _object.setMultStatisticFoe(getMapStatisticRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROTECT_AGAINST_LOW_STAT)) {
            _object.setProtectAgainstLowStat(getListStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROTECT_AGAINST_CH)) {
            _object.setProtectAgainstCh(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROTECT_AGAINST_STATUS)) {
            _object.setProtectAgainstStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISABLE_FOE_TEAM_EFFECTS)) {
            _object.setDisableFoeTeamEffects(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISABLE_FOE_TEAM_STATUS)) {
            _object.setDisableFoeTeamStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectTeamWhileSendFoe(EffectTeamWhileSendFoe _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FAIL_SENDING)) {
            _object.setFailSending(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATUS_BY_NB_USES)) {
            _object.setStatusByNbUses(DocumentReaderCoreUtil.getMapShortString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DELETED_BY_FOE_TYPES)) {
            _object.setDeletedByFoeTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_RATE_AGAINST_FOE)) {
            _object.setDamageRateAgainstFoe(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATISTICS)) {
            _object.setStatistics(getMapStatisticByte(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectUnprotectFromTypes(EffectUnprotectFromTypes _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TYPES)) {
            _object.setTypes(getListTypesDuo(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISABLE_IMMU_AGAINST_TYPES)) {
            _object.setDisableImmuAgainstTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISABLE_IMMU_FROM_MOVES)) {
            _object.setDisableImmuFromMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ATTACK_TARGET_WITH_TYPES)) {
            _object.setAttackTargetWithTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectVarPP(EffectVarPP _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_DELETE_PP)) {
            _object.setDeletePp(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static void getEffectWinMoney(EffectWinMoney _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_WINNING_RATE_BY_SUM_TARGET_USER)) {
            _object.setWinningRateBySumTargetUser(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        getEffect(_object, _fieldName, _element);
    }

    private static ConstValuesType getConstValuesType(Element _elt) {
        for (ConstValuesType e: ConstValuesType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return ConstValuesType.NOTHING;
    }

    private static ExchangeType getExchangeType(Element _elt) {
        for (ExchangeType e: ExchangeType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return ExchangeType.NOTHING;
    }

    private static MoveChoiceRestrictionType getMoveChoiceRestrictionType(Element _elt) {
        for (MoveChoiceRestrictionType e: MoveChoiceRestrictionType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return MoveChoiceRestrictionType.NOTHING;
    }

    private static MoveItemType getMoveItemType(Element _elt) {
        for (MoveItemType e: MoveItemType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return MoveItemType.REUSE_LAST_OBJECT;
    }

    private static PointViewChangementType getPointViewChangementType(Element _elt) {
        for (PointViewChangementType e: PointViewChangementType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return PointViewChangementType.NOTHING;
    }

    private static SwitchType getSwitchType(Element _elt) {
        for (SwitchType e: SwitchType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return SwitchType.NOTHING;
    }

    private static TargetChoice getTargetChoice(Element _elt) {
        for (TargetChoice e: TargetChoice.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return TargetChoice.NOTHING;
    }

    public static PokemonData getPokemonData(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newPokemonData();
        }
        return getPokemonData(doc_.getDocumentElement());
    }

    private static PokemonData getPokemonData(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PokemonData object_ = Instances.newPokemonData();
        for (Element c: childElements_) {
            getPokemonData(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getPokemonData(PokemonData _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NUMBER)) {
            _object.setNumber(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WEIGHT)) {
            _object.setWeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TYPES)) {
            _object.setTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATISTICS)) {
            _object.setStatistics(getMapStatisticStatBaseEv(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEV_MOVES)) {
            _object.setLevMoves(getListLevelMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GENDER_REP)) {
            _object.setGenderRep(getGenderRepartition(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ABILITIES)) {
            _object.setAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVE_TUTORS)) {
            _object.setMoveTutors(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HIDDEN_MOVES)) {
            _object.setHiddenMoves(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TECHNICAL_MOVES)) {
            _object.setTechnicalMoves(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BASE_EVO)) {
            _object.setBaseEvo(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EVOLUTIONS)) {
            _object.setEvolutions(getStringMapEvolution(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CATCHING_RATE)) {
            _object.setCatchingRate(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEIGHT)) {
            _object.setHeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EXP_EVO)) {
            _object.setExpEvo(getExpType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EXP_RATE)) {
            _object.setExpRate(DocumentReaderCoreUtil.getLong(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EGG_GROUPS)) {
            _object.setEggGroups(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HATCHING_STEPS)) {
            _object.setHatchingSteps(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HAPPINESS_HATCH)) {
            _object.setHappinessHatch(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
    }

    private static ExpType getExpType(Element _elt) {
        for (ExpType e: ExpType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return ExpType.M;
    }

    private static GenderRepartition getGenderRepartition(Element _elt) {
        for (GenderRepartition e: GenderRepartition.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return GenderRepartition.NO_GENDER;
    }

    private static Evolution getEvolution(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_HAPPINESS)) {
            EvolutionHappiness object_ = Instances.newEvolutionHappiness();
            for (Element c: childElements_) {
                getEvolution(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_ITEM)) {
            EvolutionItem object_ = Instances.newEvolutionItem();
            for (Element c: childElements_) {
                getEvolutionItem(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_LEVEL_GENDER)) {
            EvolutionLevelGender object_ = Instances.newEvolutionLevelGender();
            for (Element c: childElements_) {
                getEvolutionLevelGender(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_LEVEL_SIMPLE)) {
            EvolutionLevelSimple object_ = Instances.newEvolutionLevelSimple();
            for (Element c: childElements_) {
                getEvolutionLevel(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_MOVE)) {
            EvolutionMove object_ = Instances.newEvolutionMove();
            for (Element c: childElements_) {
                getEvolutionMove(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_MOVE_TYPE)) {
            EvolutionMoveType object_ = Instances.newEvolutionMoveType();
            for (Element c: childElements_) {
                getEvolutionMoveType(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_STONE_GENDER)) {
            EvolutionStoneGender object_ = Instances.newEvolutionStoneGender();
            for (Element c: childElements_) {
                getEvolutionStoneGender(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_STONE_SIMPLE)) {
            EvolutionStoneSimple object_ = Instances.newEvolutionStoneSimple();
            for (Element c: childElements_) {
                getEvolutionStone(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_EVOLUTION_TEAM)) {
            EvolutionTeam object_ = Instances.newEvolutionTeam();
            for (Element c: childElements_) {
                getEvolutionTeam(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newEvolutionHappiness();
    }

    private static void getEvolution(Evolution _object, String _fieldName, Element _element) {
    }

    private static void getEvolutionItem(EvolutionItem _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ITEM)) {
            _object.setItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getEvolutionLevel(EvolutionLevel _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
    }

    private static void getEvolutionLevelGender(EvolutionLevelGender _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        getEvolutionLevel(_object, _fieldName, _element);
    }

    private static void getEvolutionMove(EvolutionMove _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MOVE)) {
            _object.setMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getEvolutionMoveType(EvolutionMoveType _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TYPE)) {
            _object.setType(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getEvolutionStone(EvolutionStone _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_STONE)) {
            _object.setStone(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getEvolutionStoneGender(EvolutionStoneGender _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        getEvolutionStone(_object, _fieldName, _element);
    }

    private static void getEvolutionTeam(EvolutionTeam _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_POKEMON)) {
            _object.setPokemon(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    public static Status getStatus(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newStatusSimple();
        }
        return getStatus(doc_.getDocumentElement());
    }

    private static Status getStatus(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_STATUS_BEGIN_ROUND_AUTO_DAMAGE)) {
            StatusBeginRoundAutoDamage object_ = Instances.newStatusBeginRoundAutoDamage();
            for (Element c: childElements_) {
                getStatusBeginRoundAutoDamage(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_STATUS_BEGIN_ROUND_SIMPLE)) {
            StatusBeginRoundSimple object_ = Instances.newStatusBeginRoundSimple();
            for (Element c: childElements_) {
                getStatusBeginRound(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_STATUS_SIMPLE)) {
            StatusSimple object_ = Instances.newStatusSimple();
            for (Element c: childElements_) {
                getStatus(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newStatusSimple();
    }

    private static void getStatus(Status _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATUS_TYPE)) {
            _object.setStatusType(getStatusType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CATCHING_RATE)) {
            _object.setCatchingRate(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECT_END_ROUND)) {
            _object.setEffectEndRound(getListEffectEndRoundStatus(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EFFECTS_PARTNER)) {
            _object.setEffectsPartner(getListEffectPartnerStatus(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISABLED_EFF_IF_SWITCH)) {
            _object.setDisabledEffIfSwitch(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREMENT_END_ROUND)) {
            _object.setIncrementEndRound(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCREMENTING_END_ROUND)) {
            _object.setIncrementingEndRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_STAT)) {
            _object.setMultStat(getMapStatisticRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FAIL)) {
            _object.setFail(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getStatusBeginRound(StatusBeginRound _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LAW_FOR_USING_A_MOVE)) {
            _object.setLawForUsingAMove(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAW_FOR_USING_A_MOVE_NB_ROUND)) {
            _object.setLawForUsingAMoveNbRound(DocumentReaderMathUtil.getMonteCarloNumber(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAW_FOR_USING_A_MOVE_IF_FOE)) {
            _object.setLawForUsingAMoveIfFoe(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAW_FOR_FULL_HEAL_IF_MOVE)) {
            _object.setLawForFullHealIfMove(DocumentReaderMathUtil.getMonteCarloBoolean(_element));
            return;
        }
        getStatus(_object, _fieldName, _element);
    }

    private static void getStatusBeginRoundAutoDamage(StatusBeginRoundAutoDamage _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_POWER)) {
            _object.setPower(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ATTACK)) {
            _object.setAttack(getStatistic(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DEFENSE)) {
            _object.setDefense(getStatistic(_element));
            return;
        }
        getStatusBeginRound(_object, _fieldName, _element);
    }

    private static StatusType getStatusType(Element _elt) {
        for (StatusType e: StatusType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return StatusType.INDIVIDUEL;
    }

    private static EffectPartnerStatus getEffectPartnerStatus(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        EffectPartnerStatus object_ = Instances.newEffectPartnerStatus();
        for (Element c: childElements_) {
            getEffectPartnerStatus(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getEffectPartnerStatus(EffectPartnerStatus _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MULT_DAMAGE_AGAINST_FOE)) {
            _object.setMultDamageAgainstFoe(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WEDDING_ALLY)) {
            _object.setWeddingAlly(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RESTORED_HP_RATE_LOVED_ALLY)) {
            _object.setRestoredHpRateLovedAlly(DocumentReaderMathUtil.getRate(_element));
            return;
        }
    }

    private static BoostHpRate getBoostHpRate(Element _elt) {
        return BoostHpRate.newBoostHpRate(_elt.getAttribute(ATTR_VALUE));
    }

    private static CategoryMult getCategoryMult(Element _elt) {
        return CategoryMult.newCategoryMult(_elt.getAttribute(ATTR_VALUE));
    }

    private static EfficiencyRate getEfficiencyRate(Element _elt) {
        return EfficiencyRate.newEfficiencyRate(_elt.getAttribute(ATTR_VALUE));
    }

    private static LevelMove getLevelMove(Element _elt) {
        return LevelMove.newLevelMove(_elt.getAttribute(ATTR_VALUE));
    }

    private static StatBaseEv getStatBaseEv(Element _elt) {
        return StatBaseEv.newStatBaseEv(_elt.getAttribute(ATTR_VALUE));
    }

    private static StatisticCategory getStatisticCategory(Element _elt) {
        return StatisticCategory.newStatisticCategory(_elt.getAttribute(ATTR_VALUE));
    }

    private static StatisticPokemon getStatisticPokemon(Element _elt) {
        return StatisticPokemon.newStatisticPokemon(_elt.getAttribute(ATTR_VALUE));
    }

    private static StatisticStatus getStatisticStatus(Element _elt) {
        return StatisticStatus.newStatisticStatus(_elt.getAttribute(ATTR_VALUE));
    }

    private static StatisticType getStatisticType(Element _elt) {
        return StatisticType.newStatisticType(_elt.getAttribute(ATTR_VALUE));
    }

    private static TypeDamageBoost getTypeDamageBoost(Element _elt) {
        return TypeDamageBoost.newTypeDamageBoost(_elt.getAttribute(ATTR_VALUE));
    }

    private static TypesDuo getTypesDuo(Element _elt) {
        return TypesDuo.newTypesDuo(_elt.getAttribute(ATTR_VALUE));
    }

    private static WeatherType getWeatherType(Element _elt) {
        return WeatherType.newWeatherType(_elt.getAttribute(ATTR_VALUE));
    }

    public static Game getGame(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return null;
        }
        String tagName_ = doc_.getDocumentElement().getTagName();
        if (StringList.quickEq(tagName_,"LoadingGame")) {
            return null;
        }
        return getGame(doc_.getDocumentElement());
    }

    private static Game getGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Game object_ = Instances.newGame();
        for (Element c: childElements_) {
            getGame(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getGame(Game _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ZIPPED_ROM)) {
            _object.setZippedRom(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLAYER)) {
            _object.setPlayer(getPlayer(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RANK_LEAGUE)) {
            _object.setRankLeague(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BEAT_GYM_TRAINER)) {
            _object.setBeatGymTrainer(getMapShortListPoint(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BEAT_GYM_LEADER)) {
            _object.setBeatGymLeader(getMapCoordsBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BEAT_TRAINER)) {
            _object.setBeatTrainer(getMapNbFightCoordsBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TAKEN_OBJECTS)) {
            _object.setTakenObjects(getMapCoordsBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TAKEN_POKEMON)) {
            _object.setTakenPokemon(getMapCoordsBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLAYER_COORDS)) {
            _object.setPlayerCoords(getCoords(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLAYER_ORIENTATION)) {
            _object.setPlayerOrientation(getDirection(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HOSTED_PK)) {
            _object.setHostedPk(getMapCoordsHostPokemonDuo(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FIGHT)) {
            _object.setFight(getFight(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DIFFICULTY)) {
            _object.setDifficulty(getDifficulty(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INDEX_PERIOD_FISHING)) {
            _object.setIndexPeriodFishing(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INDEX_PERIOD)) {
            _object.setIndexPeriod(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INDEX_STEP)) {
            _object.setIndexStep(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_VISITED_PLACES)) {
            _object.setVisitedPlaces(getMapCoordsBoolean(_element));
            return;
        }
    }

    private static HostPokemonDuo getHostPokemonDuo(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        HostPokemonDuo object_ = Instances.newHostPokemonDuo();
        for (Element c: childElements_) {
            getHostPokemonDuo(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getHostPokemonDuo(HostPokemonDuo _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FIRST_POKEMON)) {
            _object.setFirstPokemon(getPokemonPlayer(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SECOND_POKEMON)) {
            _object.setSecondPokemon(getPokemonPlayer(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_STEPS)) {
            _object.setNbSteps(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static NbFightCoords getNbFightCoords(Element _elt) {
        return NbFightCoords.newNbFightCoords(_elt.getAttribute(ATTR_VALUE));
    }

    private static UsesOfMove getUsesOfMove(Element _elt) {
        return UsesOfMove.newUsesOfMove(_elt.getAttribute(ATTR_VALUE));
    }

    private static ActivityOfMove getActivityOfMove(Element _elt) {
        return ActivityOfMove.newActivityOfMove(_elt.getAttribute(ATTR_VALUE));
    }

    private static Anticipation getAnticipation(Element _elt) {
        return Anticipation.newAnticipation(_elt.getAttribute(ATTR_VALUE));
    }

    private static ChoiceOfEvolutionAndMoves getChoiceOfEvolutionAndMoves(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        ChoiceOfEvolutionAndMoves object_ = Instances.newChoiceOfEvolutionAndMoves();
        for (Element c: childElements_) {
            getChoiceOfEvolutionAndMoves(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getChoiceOfEvolutionAndMoves(ChoiceOfEvolutionAndMoves _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_KEPT_MOVES)) {
            _object.setKeptMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ABILITY)) {
            _object.setAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static Fight getFight(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Fight object_ = Instances.newFight();
        for (Element c: childElements_) {
            getFight(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getFight(Fight _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FIGHT_TYPE)) {
            _object.setFightType(getFightType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENV_TYPE)) {
            _object.setEnvType(getEnvironmentType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT)) {
            _object.setMult(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLAYER_MAX_NUMBER_FRONT_FIGHTERS)) {
            _object.setPlayerMaxNumberFrontFighters(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES)) {
            _object.setEnabledMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STILL_ENABLED_MOVES)) {
            _object.setStillEnabledMoves(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TEAMS)) {
            _object.setTeams(getMapByteTeam(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_FLEE_ATTEMPT)) {
            _object.setNbFleeAttempt(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_ROUNDS)) {
            _object.setNbRounds(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WINNING_MONEY)) {
            _object.setWinningMoney(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CATCHING_BALL)) {
            _object.setCatchingBall(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CURRENT_USER)) {
            _object.setCurrentUser(getTeamPosition(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATE)) {
            _object.setState(getFightState(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USED_ITEMS_WHILE_ROUND)) {
            _object.setUsedItemsWhileRound(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FIRST_POSIT_PLAYER_FIGHTERS)) {
            _object.setFirstPositPlayerFighters(DocumentReaderCoreUtil.getMapByteByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FIRST_POSIT_FOE_FIGHTERS)) {
            _object.setFirstPositFoeFighters(DocumentReaderCoreUtil.getMapByteByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ALLY_CHOICE)) {
            _object.setAllyChoice(getMapMoveTargetMoveTarget(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOST_OBJECTS)) {
            _object.setLostObjects(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHOICES)) {
            _object.setChoices(getMapByteChoiceOfEvolutionAndMoves(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CAUGHT_EVOLUTIONS)) {
            _object.setCaughtEvolutions(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
    }

    private static Fighter getFighter(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Fighter object_ = Instances.newFighter();
        for (Element c: childElements_) {
            getFighter(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getFighter(Fighter _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NICKNAME)) {
            _object.setNickname(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WEIGHT)) {
            _object.setWeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEIGHT)) {
            _object.setHeight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CURRENT_NAME)) {
            _object.setCurrentName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CURRENT_GENDER)) {
            _object.setCurrentGender(getGender(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAST_USED_ITEM)) {
            _object.setLastUsedItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ITEM)) {
            _object.setItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EXP_ITEM)) {
            _object.setExpItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ABILITY)) {
            _object.setAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CURRENT_ABILITY)) {
            _object.setCurrentAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderCoreUtil.getStringMapShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATUS_RELAT)) {
            _object.setStatusRelat(getMapMoveTeamPositionShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_ROUNDS)) {
            _object.setNbRounds(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TYPES)) {
            _object.setTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES)) {
            _object.setMoves(getStringMapUsesOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CURRENT_MOVES)) {
            _object.setCurrentMoves(getStringMapUsesOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EV)) {
            _object.setEv(getMapStatisticShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATIS_BASE)) {
            _object.setStatisBase(getMapStatisticRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATIS_BOOST)) {
            _object.setStatisBoost(getMapStatisticByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REMAINING_HP)) {
            _object.setRemainingHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CLONE)) {
            _object.setClone(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES)) {
            _object.setEnabledMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_PROT)) {
            _object.setEnabledMovesProt(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PROTECTED_AGAINST_MOVE_TYPES)) {
            _object.setProtectedAgainstMoveTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_UNPROT)) {
            _object.setEnabledMovesUnprot(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_END_ROUND)) {
            _object.setEnabledMovesEndRound(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_CONST_CHOICES)) {
            _object.setEnabledMovesConstChoices(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_CHANGING_TYPES_MOVES)) {
            _object.setEnabledChangingTypesMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_COUNTERING_MOVES)) {
            _object.setEnabledCounteringMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_FOR_ALLY)) {
            _object.setEnabledMovesForAlly(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_RATE_INFLICTED_BY_TYPE)) {
            _object.setDamageRateInflictedByType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_RATE_SUFFERED_BY_TYPE)) {
            _object.setDamageRateSufferedByType(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACTED)) {
            _object.setActed(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GROUND_PLACE)) {
            _object.setGroundPlace(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GROUND_PLACE_SUBST)) {
            _object.setGroundPlaceSubst(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WON_EXP)) {
            _object.setWonExp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WON_EXP_SINCE_LAST_LEVEL)) {
            _object.setWonExpSinceLastLevel(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USED_BALL_CATCHING)) {
            _object.setUsedBallCatching(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INCR_USER_ACCURACY)) {
            _object.setIncrUserAccuracy(getMapMoveTeamPositionBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_USES_MOVES)) {
            _object.setNbUsesMoves(DocumentReaderCoreUtil.getStringMapInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_PREPA_ROUND)) {
            _object.setNbPrepaRound(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DISAPPEARED)) {
            _object.setDisappeared(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NEEDING_TO_RECHARGE)) {
            _object.setNeedingToRecharge(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TRACKING_MOVES)) {
            _object.setTrackingMoves(getMapMoveTeamPositionAffectedMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TRAPPING_MOVES)) {
            _object.setTrappingMoves(getMapMoveTeamPositionActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAST_SUFFERED_MOVE)) {
            _object.setLastSufferedMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAST_SUFFERED_MOVE_TYPES)) {
            _object.setLastSufferedMoveTypes(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_SUFFERED_CATEG)) {
            _object.setDamageSufferedCateg(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_SUFFERED_CATEG_ROUND)) {
            _object.setDamageSufferedCategRound(DocumentReaderMathUtil.getStringMapRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAST_USED_MOVE)) {
            _object.setLastUsedMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USED_MOVE_LAST_ROUND)) {
            _object.setUsedMoveLastRound(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ALREADY_INVOKED_MOVES_ROUND)) {
            _object.setAlreadyInvokedMovesRound(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAST_SUCCESSFUL_MOVE)) {
            _object.setLastSuccessfulMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_COPIED_MOVES)) {
            _object.setCopiedMoves(getStringMapCopiedMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_REPEATING_SUCCESSFUL_MOVES)) {
            _object.setNbRepeatingSuccessfulMoves(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USING_ITEM)) {
            _object.setUsingItem(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SUCCESSFUL_MOVE)) {
            _object.setSuccessfulMove(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHANGED)) {
            _object.setChanged(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PRIVATE_MOVES)) {
            _object.setPrivateMoves(getMapMoveTeamPositionStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BELONGING_TO_PLAYER)) {
            _object.setBelongingToPlayer(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACTION)) {
            _object.setAction(getAbstractAction(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES_TO_BE_LEARNT)) {
            _object.setMovesToBeLearnt(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES_ABILITIES_EVOS)) {
            _object.setMovesAbilitiesEvos(getStringMapMovesAbilities(_element));
            return;
        }
    }

    private static MoveTeamPosition getMoveTeamPosition(Element _elt) {
        return MoveTeamPosition.newMoveTeamPosition(_elt.getAttribute(ATTR_VALUE));
    }

    private static StacksOfUses getStacksOfUses(Element _elt) {
        return StacksOfUses.newStacksOfUses(_elt.getAttribute(ATTR_VALUE));
    }

    private static TargetCoords getTargetCoords(Element _elt) {
        return TargetCoords.newTargetCoords(_elt.getAttribute(ATTR_VALUE));
    }

    private static Team getTeam(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Team object_ = Instances.newTeam();
        for (Element c: childElements_) {
            getTeam(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTeam(Team _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_BY_GROUP)) {
            _object.setEnabledMovesByGroup(getMapStringListActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES)) {
            _object.setEnabledMoves(getStringMapActivityOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_WHILE_SENDING_FOE)) {
            _object.setEnabledMovesWhileSendingFoe(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_MOVES_WHILE_SENDING_FOE_USES)) {
            _object.setEnabledMovesWhileSendingFoeUses(DocumentReaderMathUtil.getStringMapLgInt(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_USES_MOVES)) {
            _object.setNbUsesMoves(DocumentReaderCoreUtil.getStringMapInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_USES_MOVES_ROUND)) {
            _object.setNbUsesMovesRound(DocumentReaderCoreUtil.getStringMapInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEAL_AFTER)) {
            _object.setHealAfter(getStringMapMapByteStacksOfUses(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES_ANTICIPATION)) {
            _object.setMovesAnticipation(getStringMapMapByteAnticipation(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MEMBERS)) {
            _object.setMembers(getMapByteFighter(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLAYER_FIGHTERS_AGAINST_FOE)) {
            _object.setPlayerFightersAgainstFoe(DocumentReaderCoreUtil.getMapByteListByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_KO_ROUND)) {
            _object.setNbKoRound(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_KO_PREVIOUS_ROUND)) {
            _object.setNbKoPreviousRound(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SUCCESSFUL_MOVES_ROUND)) {
            _object.setSuccessfulMovesRound(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
    }

    private static TeamPosition getTeamPosition(Element _elt) {
        return TeamPosition.newTeamPosition(_elt.getAttribute(ATTR_VALUE));
    }

    private static AbstractAction getAbstractAction(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_ACTION)) {
            Action object_ = Instances.newAction();
            for (Element c: childElements_) {
                getAbstractAction(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_ACTION_HEAL_MOVE)) {
            ActionHealMove object_ = Instances.newActionHealMove();
            for (Element c: childElements_) {
                getActionHealMove(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_ACTION_MOVE)) {
            ActionMove object_ = Instances.newActionMove();
            for (Element c: childElements_) {
                getActionMove(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_ACTION_SIMPLE_HEAL)) {
            ActionSimpleHeal object_ = Instances.newActionSimpleHeal();
            for (Element c: childElements_) {
                getActionSimpleHeal(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_ACTION_SWITCH)) {
            ActionSwitch object_ = Instances.newActionSwitch();
            for (Element c: childElements_) {
                getActionSwitch(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newAction();
    }

    private static void getAbstractAction(AbstractAction _object, String _fieldName, Element _element) {
    }

    private static void getActionHeal(ActionHeal _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_CHOSEN_HEALING_ITEM)) {
            _object.setChosenHealingItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TEAM)) {
            _object.setTeam(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static void getActionHealMove(ActionHealMove _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FIRST_CHOSEN_MOVE)) {
            _object.setFirstChosenMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getActionHeal(_object, _fieldName, _element);
    }

    private static void getActionMove(ActionMove _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FIRST_CHOSEN_MOVE)) {
            _object.setFirstChosenMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FINAL_CHOSEN_MOVE)) {
            _object.setFinalChosenMove(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHOSEN_TARGETS)) {
            _object.setChosenTargets(getListTargetCoords(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SUBSTITUTE)) {
            _object.setSubstitute(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    private static void getActionSimpleHeal(ActionSimpleHeal _object, String _fieldName, Element _element) {
        getActionHeal(_object, _fieldName, _element);
    }

    private static void getActionSwitch(ActionSwitch _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_SUBSTITUTE)) {
            _object.setSubstitute(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
    }

    private static FightState getFightState(Element _elt) {
        for (FightState e: FightState.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return FightState.RIEN;
    }

    private static FightType getFightType(Element _elt) {
        for (FightType e: FightType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return FightType.NOTHING;
    }

    private static AffectedMove getAffectedMove(Element _elt) {
        return AffectedMove.newAffectedMove(_elt.getAttribute(ATTR_VALUE));
    }

    private static CopiedMove getCopiedMove(Element _elt) {
        return CopiedMove.newCopiedMove(_elt.getAttribute(ATTR_VALUE));
    }

    private static MoveTarget getMoveTarget(Element _elt) {
        return MoveTarget.newMoveTarget(_elt.getAttribute(ATTR_VALUE));
    }

    private static MovesAbilities getMovesAbilities(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        MovesAbilities object_ = Instances.newMovesAbilities();
        for (Element c: childElements_) {
            getMovesAbilities(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getMovesAbilities(MovesAbilities _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MOVES)) {
            _object.setMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ABILITIES)) {
            _object.setAbilities(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
    }

    private static Difficulty getDifficulty(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Difficulty object_ = Instances.newDifficulty();
        for (Element c: childElements_) {
            getDifficulty(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDifficulty(Difficulty _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ALLOW_CATCHING_KO)) {
            _object.setAllowCatchingKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ALLOWED_SWITCH_PLACES_END_ROUND)) {
            _object.setAllowedSwitchPlacesEndRound(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DIFF_WINNING_EXP_PTS_FIGHT)) {
            _object.setDiffWinningExpPtsFight(getDifficultyWinPointsFight(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RATE_WINNING_EXP_PTS_FIGHT)) {
            _object.setRateWinningExpPtsFight(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WIN_TRAINER_EXP)) {
            _object.setWinTrainerExp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_RATE_PLAYER)) {
            _object.setDamageRatePlayer(getDifficultyModelLaw(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DAMAGE_RATE_LAW_FOE)) {
            _object.setDamageRateLawFoe(getDifficultyModelLaw(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_END_FIGHT_IF_ONE_TEAM_KO)) {
            _object.setEndFightIfOneTeamKo(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RATE_WIN_MONEY_BASE)) {
            _object.setRateWinMoneyBase(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RATE_LOOSE_MONEY_WIN)) {
            _object.setRateLooseMoneyWin(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IV_PLAYER)) {
            _object.setIvPlayer(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IV_FOE)) {
            _object.setIvFoe(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STILL_POSSIBLE_FLEE)) {
            _object.setStillPossibleFlee(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RESTORED_MOVES_END_FIGHT)) {
            _object.setRestoredMovesEndFight(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_CLOSING)) {
            _object.setEnabledClosing(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_RANDOM_WILD_FIGHT)) {
            _object.setRandomWildFight(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL)) {
            _object.setSkipLearningMovesWhileNotGrowingLevel(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    public static LoadingGame getLoadingGame(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        if (doc_ == null) {
            return Instances.newLoadingGame();
        }
        return getLoadingGame(doc_.getDocumentElement());
    }

    private static LoadingGame getLoadingGame(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LoadingGame object_ = Instances.newLoadingGame();
        for (Element c: childElements_) {
            getLoadingGame(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getLoadingGame(LoadingGame _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LAST_ROM)) {
            _object.setLastRom(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LAST_SAVED_GAME)) {
            _object.setLastSavedGame(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SAVE_HOME_FOLDER)) {
            _object.setSaveHomeFolder(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOAD_HOME_FOLDER)) {
            _object.setLoadHomeFolder(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOAD_LAST_ROM)) {
            _object.setLoadLastRom(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LOAD_LAST_GAME)) {
            _object.setLoadLastGame(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SAVE_GAME_AT_EXIT)) {
            _object.setSaveGameAtExit(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLE_ANIMATION)) {
            _object.setEnableAnimation(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLE_MOVING_HEROS_ANIMATION)) {
            _object.setEnableMovingHerosAnimation(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CLICK_BUTTONS_PAD)) {
            _object.setClickButtonsPad(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ENABLED_KEY_PAD)) {
            _object.setEnabledKeyPad(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static DifficultyModelLaw getDifficultyModelLaw(Element _elt) {
        for (DifficultyModelLaw e: DifficultyModelLaw.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return DifficultyModelLaw.UNIFORME;
    }

    private static DifficultyWinPointsFight getDifficultyWinPointsFight(Element _elt) {
        for (DifficultyWinPointsFight e: DifficultyWinPointsFight.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return DifficultyWinPointsFight.TRES_FACILE;
    }

    private static Inventory getInventory(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Inventory object_ = Instances.newInventory();
        for (Element c: childElements_) {
            getInventory(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getInventory(Inventory _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ITEMS)) {
            _object.setItems(DocumentReaderMathUtil.getStringMapLgInt(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TM)) {
            _object.setTm(DocumentReaderCoreUtil.getMapShortBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HM)) {
            _object.setHm(DocumentReaderCoreUtil.getMapShortBoolean(_element));
            return;
        }
    }

    private static Player getPlayer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Player object_ = Instances.newPlayer();
        for (Element c: childElements_) {
            getPlayer(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getPlayer(Player _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NICKNAME)) {
            _object.setNickname(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SEX)) {
            _object.setSex(getSex(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TEAM)) {
            _object.setTeam(getListUsablePokemon(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BOX)) {
            _object.setBox(getListUsablePokemon(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INVENTORY)) {
            _object.setInventory(getInventory(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CAUGHT_PK)) {
            _object.setCaughtPk(DocumentReaderCoreUtil.getStringMapBoolean(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MONEY)) {
            _object.setMoney(DocumentReaderMathUtil.getLgInt(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REMAINING_REPEL_STEPS)) {
            _object.setRemainingRepelSteps(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static Sex getSex(Element _elt) {
        Sex[] values_ = Sex.values();
        for (Sex e: values_) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return values_[0];
    }

    public static DataMap getDataMap(String _string) {
        Document doc_ = DocumentBuilder.parseNoTextDocument(_string);
        return getDataMap(doc_.getDocumentElement());
    }

    private static DataMap getDataMap(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DataMap object_ = Instances.newDataMap();
        for (Element c: childElements_) {
            getDataMap(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDataMap(DataMap _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_PLACES)) {
            _object.setPlaces(getMapShortPlace(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACCESS_CONDITION)) {
            _object.setAccessCondition(getMapCoordsListCoords(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MINI_MAP)) {
            _object.setMiniMap(getMapMiniMapCoordsTileMiniMap(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_UNLOCKED_CITY)) {
            _object.setUnlockedCity(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BEGIN)) {
            _object.setBegin(getCoords(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FIRST_POKEMON)) {
            _object.setFirstPokemon(getWildPk(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SCREEN_WIDTH)) {
            _object.setScreenWidth(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SCREEN_HEIGHT)) {
            _object.setScreenHeight(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SPACE_BETWEEN_LEFT_AND_HEROS)) {
            _object.setSpaceBetweenLeftAndHeros(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SPACE_BETWEEN_TOP_AND_HEROS)) {
            _object.setSpaceBetweenTopAndHeros(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SIDE_LENGTH)) {
            _object.setSideLength(DocumentReaderCoreUtil.getInteger(_element));
            return;
        }
    }

    private static Building getBuilding(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_GYM)) {
            Gym object_ = Instances.newGym();
            for (Element c: childElements_) {
                getGym(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_POKEMON_CENTER)) {
            PokemonCenter object_ = Instances.newPokemonCenter();
            for (Element c: childElements_) {
                getPokemonCenter(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newPokemonCenter();
    }

    private static void getBuilding(Building _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_IMAGE_FILE_NAME)) {
            _object.setImageFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EXIT_CITY)) {
            _object.setExitCity(getPoint(_element));
            return;
        }
    }

    private static void getGym(Gym _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(getLevelIndoorGym(_element));
            return;
        }
        getBuilding(_object, _fieldName, _element);
    }

    private static void getPokemonCenter(PokemonCenter _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(getLevelIndoorPokemonCenter(_element));
            return;
        }
        getBuilding(_object, _fieldName, _element);
    }

    private static Ally getAlly(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Ally object_ = Instances.newAlly();
        for (Element c: childElements_) {
            getAlly(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAlly(Ally _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TEAM)) {
            _object.setTeam(getListPkTrainer(_element));
            return;
        }
    }

    private static CharacterInRoadCave getCharacterInRoadCave(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_DEALER_ITEM)) {
            DealerItem object_ = Instances.newDealerItem();
            for (Element c: childElements_) {
                getDealerItem(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_TRAINER_MULTI_FIGHTS)) {
            TrainerMultiFights object_ = Instances.newTrainerMultiFights();
            for (Element c: childElements_) {
                getTrainerMultiFights(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newDealerItem();
    }

    private static void getDealerItem(DealerItem _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ITEMS)) {
            _object.setItems(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TECHNICAL_MOVES)) {
            _object.setTechnicalMoves(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        getPerson(_object, _fieldName, _element);
    }

    private static DualFight getDualFight(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        DualFight object_ = Instances.newDualFight();
        for (Element c: childElements_) {
            getDualFight(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getDualFight(DualFight _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_ALLY)) {
            _object.setAlly(getAlly(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FOE_TRAINER)) {
            _object.setFoeTrainer(getTempTrainer(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NAMES)) {
            _object.setNames(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PT)) {
            _object.setPt(getPoint(_element));
            return;
        }
    }

    private static void getGerantPokemon(GerantPokemon _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_GERANCE)) {
            _object.setGerance(getGeranceType(_element));
            return;
        }
        getPerson(_object, _fieldName, _element);
    }

    private static GymLeader getGymLeader(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GymLeader object_ = Instances.newGymLeader();
        for (Element c: childElements_) {
            getGymLeader(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getGymLeader(GymLeader _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TM)) {
            _object.setTm(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static GymTrainer getGymTrainer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        GymTrainer object_ = Instances.newGymTrainer();
        for (Element c: childElements_) {
            getGymTrainer(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getGymTrainer(GymTrainer _object, String _fieldName, Element _element) {
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static Person getPerson(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_DEALER_ITEM)) {
            DealerItem object_ = Instances.newDealerItem();
            for (Element c: childElements_) {
                getDealerItem(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_GERANT_POKEMON)) {
            GerantPokemon object_ = Instances.newGerantPokemon();
            for (Element c: childElements_) {
                getGerantPokemon(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_GYM_LEADER)) {
            GymLeader object_ = Instances.newGymLeader();
            for (Element c: childElements_) {
                getGymLeader(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_GYM_TRAINER)) {
            GymTrainer object_ = Instances.newGymTrainer();
            for (Element c: childElements_) {
                getGymTrainer(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_HEALER)) {
            Healer object_ = Instances.newHealer();
            for (Element c: childElements_) {
                getPerson(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_SELLER)) {
            Seller object_ = Instances.newSeller();
            for (Element c: childElements_) {
                getSeller(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_TEMP_TRAINER)) {
            TempTrainer object_ = Instances.newTempTrainer();
            for (Element c: childElements_) {
                getTempTrainer(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_TRAINER_LEAGUE)) {
            TrainerLeague object_ = Instances.newTrainerLeague();
            for (Element c: childElements_) {
                getTrainerLeague(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_TRAINER_MULTI_FIGHTS)) {
            TrainerMultiFights object_ = Instances.newTrainerMultiFights();
            for (Element c: childElements_) {
                getTrainerMultiFights(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newDealerItem();
    }

    private static void getPerson(Person _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_IMAGE_MINI_FILE_NAME)) {
            _object.setImageMiniFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
    }

    private static void getSeller(Seller _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_SELL)) {
            _object.setSell(getSellType(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ITEMS)) {
            _object.setItems(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TM)) {
            _object.setTm(DocumentReaderCoreUtil.getListShort(_element));
            return;
        }
        getPerson(_object, _fieldName, _element);
    }

    private static TempTrainer getTempTrainer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TempTrainer object_ = Instances.newTempTrainer();
        for (Element c: childElements_) {
            getTempTrainer(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTempTrainer(TempTrainer _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_IMAGE_MINI_SECOND_TRAINER_FILE_NAME)) {
            _object.setImageMiniSecondTrainerFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static void getTrainer(Trainer _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_MULTIPLICITY_FIGHT)) {
            _object.setMultiplicityFight(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_IMAGE_MAXI_FILE_NAME)) {
            _object.setImageMaxiFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getPerson(_object, _fieldName, _element);
    }

    private static TrainerLeague getTrainerLeague(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TrainerLeague object_ = Instances.newTrainerLeague();
        for (Element c: childElements_) {
            getTrainerLeague(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTrainerLeague(TrainerLeague _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getTrainerOneFight(_object, _fieldName, _element);
    }

    private static void getTrainerMultiFights(TrainerMultiFights _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TEAMS_REWARDS)) {
            _object.setTeamsRewards(getListPokemonTeam(_element));
            return;
        }
        getTrainer(_object, _fieldName, _element);
    }

    private static void getTrainerOneFight(TrainerOneFight _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_REWARD)) {
            _object.setReward(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TEAM)) {
            _object.setTeam(getListPkTrainer(_element));
            return;
        }
        getTrainer(_object, _fieldName, _element);
    }

    private static GeranceType getGeranceType(Element _elt) {
        for (GeranceType e: GeranceType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return GeranceType.HEAL;
    }

    private static SellType getSellType(Element _elt) {
        for (SellType e: SellType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return SellType.ITEM;
    }

    private static Direction getDirection(Element _elt) {
        for (Direction e: Direction.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return Direction.UP;
    }

    private static AreaApparition getAreaApparition(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        AreaApparition object_ = Instances.newAreaApparition();
        for (Element c: childElements_) {
            getAreaApparition(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getAreaApparition(AreaApparition _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_WILD_POKEMON)) {
            _object.setWildPokemon(getListWildPk(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_AVG_NB_STEPS)) {
            _object.setAvgNbSteps(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MULT_FIGHT)) {
            _object.setMultFight(DocumentReaderCoreUtil.getByte(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WILD_POKEMON_FISHING)) {
            _object.setWildPokemonFishing(getListWildPk(_element));
            return;
        }
    }

    private static Block getBlock(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        Block object_ = Instances.newBlock();
        for (Element c: childElements_) {
            getBlock(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getBlock(Block _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_WIDTH)) {
            _object.setWidth(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEIGHT)) {
            _object.setHeight(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_INDEX_APPARITION)) {
            _object.setIndexApparition(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TILE_FILE_NAME)) {
            _object.setTileFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TYPE)) {
            _object.setType(getEnvironmentType(_element));
            return;
        }
    }

    private static void getLevel(Level _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_BLOCKS)) {
            _object.setBlocks(getMapPointBlock(_element));
            return;
        }
    }

    private static LevelCave getLevelCave(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelCave object_ = Instances.newLevelCave();
        for (Element c: childElements_) {
            getLevelCave(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getLevelCave(LevelCave _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_LINKS_OTHER_LEVELS)) {
            _object.setLinksOtherLevels(getMapPointLink(_element));
            return;
        }
        getLevelWithWildPokemon(_object, _fieldName, _element);
    }

    private static LevelIndoorGym getLevelIndoorGym(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelIndoorGym object_ = Instances.newLevelIndoorGym();
        for (Element c: childElements_) {
            getLevelIndoorGym(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getLevelIndoorGym(LevelIndoorGym _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_GYM_TRAINERS)) {
            _object.setGymTrainers(getMapPointGymTrainer(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GYM_LEADER_COORDS)) {
            _object.setGymLeaderCoords(getPoint(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GYM_LEADER)) {
            _object.setGymLeader(getGymLeader(_element));
            return;
        }
        getLevel(_object, _fieldName, _element);
    }

    private static LevelIndoorPokemonCenter getLevelIndoorPokemonCenter(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelIndoorPokemonCenter object_ = Instances.newLevelIndoorPokemonCenter();
        for (Element c: childElements_) {
            getLevelIndoorPokemonCenter(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getLevelIndoorPokemonCenter(LevelIndoorPokemonCenter _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_GERANTS)) {
            _object.setGerants(getMapPointPerson(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STORAGE_COORDS)) {
            _object.setStorageCoords(getPoint(_element));
            return;
        }
        getLevel(_object, _fieldName, _element);
    }

    private static LevelLeague getLevelLeague(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelLeague object_ = Instances.newLevelLeague();
        for (Element c: childElements_) {
            getLevelLeague(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getLevelLeague(LevelLeague _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TRAINER_COORDS)) {
            _object.setTrainerCoords(getPoint(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TRAINER)) {
            _object.setTrainer(getTrainerLeague(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACCESS_POINT)) {
            _object.setAccessPoint(getPoint(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NEXT_LEVEL_TARGET)) {
            _object.setNextLevelTarget(getPoint(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FILE_NAME)) {
            _object.setFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getLevel(_object, _fieldName, _element);
    }

    private static LevelOutdoor getLevelOutdoor(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelOutdoor object_ = Instances.newLevelOutdoor();
        for (Element c: childElements_) {
            getLevelOutdoor(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getLevelOutdoor(LevelOutdoor _object, String _fieldName, Element _element) {
        getLevel(_object, _fieldName, _element);
    }

    private static LevelRoad getLevelRoad(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        LevelRoad object_ = Instances.newLevelRoad();
        for (Element c: childElements_) {
            getLevelRoad(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getLevelRoad(LevelRoad _object, String _fieldName, Element _element) {
        getLevelWithWildPokemon(_object, _fieldName, _element);
    }

    private static void getLevelWithWildPokemon(LevelWithWildPokemon _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_WILD_POKEMON_AREAS)) {
            _object.setWildPokemonAreas(getListAreaApparition(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_CHARACTERS)) {
            _object.setCharacters(getMapPointCharacterInRoadCave(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_DUAL_FIGHTS)) {
            _object.setDualFights(getMapPointDualFight(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEGENDARY_PKS)) {
            _object.setLegendaryPks(getMapPointWildPk(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ITEMS)) {
            _object.setItems(getMapPointString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_TM)) {
            _object.setTm(getMapPointShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HM)) {
            _object.setHm(getMapPointShort(_element));
            return;
        }
        getLevel(_object, _fieldName, _element);
    }

    private static Link getLink(Element _elt) {
        return Link.newLink(_elt.getAttribute(ATTR_VALUE));
    }

    private static EnvironmentType getEnvironmentType(Element _elt) {
        for (EnvironmentType e: EnvironmentType.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return EnvironmentType.NOTHING;
    }

    private static void getCave(Cave _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVELS)) {
            _object.setLevels(getMapByteLevelCave(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LINKS_WITH_OTHER_PLACES)) {
            _object.setLinksWithOtherPlaces(getMapLevelPointLink(_element));
            return;
        }
    }

    private static void getCity(City _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_BUILDINGS)) {
            _object.setBuildings(getMapPointBuilding(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(getLevelOutdoor(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SAVEDLINKS)) {
            _object.setSavedlinks(getMapPlaceInterConnectCoords(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LINKS_WITH_CAVES)) {
            _object.setLinksWithCaves(getMapPointLink(_element));
            return;
        }
    }

    private static void getLeague(League _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ROOMS)) {
            _object.setRooms(getListLevelLeague(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ACCESS_COORDS)) {
            _object.setAccessCoords(getCoords(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_FILE_NAME)) {
            _object.setFileName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_BEGIN)) {
            _object.setBegin(getPoint(_element));
            return;
        }
    }

    private static Place getPlace(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_CAVE)) {
            Cave object_ = Instances.newCave();
            for (Element c: childElements_) {
                getCave(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_CITY)) {
            City object_ = Instances.newCity();
            for (Element c: childElements_) {
                getCity(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_LEAGUE)) {
            League object_ = Instances.newLeague();
            for (Element c: childElements_) {
                getLeague(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_ROAD)) {
            Road object_ = Instances.newRoad();
            for (Element c: childElements_) {
                getRoad(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newRoad();
    }

    private static void getRoad(Road _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(getLevelRoad(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LINKS_WITH_CAVES)) {
            _object.setLinksWithCaves(getMapPointLink(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_SAVEDLINKS)) {
            _object.setSavedlinks(getMapPlaceInterConnectCoords(_element));
            return;
        }
    }

    private static Egg getEgg(Element _elt) {
        return Egg.newEgg(_elt.getAttribute(ATTR_VALUE));
    }

    private static PkTrainer getPkTrainer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PkTrainer object_ = Instances.newPkTrainer();
        for (Element c: childElements_) {
            getPkTrainer(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getPkTrainer(PkTrainer _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ABILITY)) {
            _object.setAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ITEM)) {
            _object.setItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES)) {
            _object.setMoves(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        getPokemon(_object, _fieldName, _element);
    }

    private static void getPokemon(Pokemon _object, String _fieldName, Element _element) {
    }

    public static PokemonPlayer getPokemonPlayer(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PokemonPlayer object_ = Instances.newPokemonPlayer();
        for (Element c: childElements_) {
            getPokemonPlayer(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getPokemonPlayer(PokemonPlayer _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ABILITY)) {
            _object.setAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ITEM)) {
            _object.setItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REMAINING_HP)) {
            _object.setRemainingHp(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_STATUS)) {
            _object.setStatus(DocumentReaderCoreUtil.getStringList(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NICKNAME)) {
            _object.setNickname(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_MOVES)) {
            _object.setMoves(getStringMapUsesOfMove(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_EV)) {
            _object.setEv(getMapStatisticShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_WON_EXP_SINCE_LAST_LEVEL)) {
            _object.setWonExpSinceLastLevel(DocumentReaderMathUtil.getRate(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HAPPINESS)) {
            _object.setHappiness(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_USED_BALL_CATCHING)) {
            _object.setUsedBallCatching(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_NB_STEPS_TEAM_LEAD)) {
            _object.setNbStepsTeamLead(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        getPokemon(_object, _fieldName, _element);
    }

    private static PokemonTeam getPokemonTeam(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        PokemonTeam object_ = Instances.newPokemonTeam();
        for (Element c: childElements_) {
            getPokemonTeam(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getPokemonTeam(PokemonTeam _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_TEAM)) {
            _object.setTeam(getListPkTrainer(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_REWARD)) {
            _object.setReward(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
    }

    private static UsablePokemon getUsablePokemon(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        String tagName_ = _element.getTagName();
        tagName_ = tagName_.substring(tagName_.lastIndexOf(DOT)+1);
        if (StringList.quickEq(tagName_,TYPE_EGG)) {
            Egg object_ = getEgg(_element);
            return object_;
        }
        if (StringList.quickEq(tagName_,TYPE_POKEMON_PLAYER)) {
            PokemonPlayer object_ = Instances.newPokemonPlayer();
            for (Element c: childElements_) {
                getPokemonPlayer(object_,c.getAttribute(ATTR_FIELD),c);
            }
            return object_;
        }
        return Instances.newPokemonPlayer();
    }

    private static WildPk getWildPk(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        WildPk object_ = Instances.newWildPk();
        for (Element c: childElements_) {
            getWildPk(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getWildPk(WildPk _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_NAME)) {
            _object.setName(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_LEVEL)) {
            _object.setLevel(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_GENDER)) {
            _object.setGender(getGender(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ABILITY)) {
            _object.setAbility(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_ITEM)) {
            _object.setItem(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        getPokemon(_object, _fieldName, _element);
    }

    private static Gender getGender(Element _elt) {
        for (Gender e: Gender.values()) {
            if (StringList.quickEq(e.name(),_elt.getAttribute(ATTR_VALUE))) {
                return e;
            }
        }
        return Gender.NO_GENDER;
    }

    private static MiniMapCoords getMiniMapCoords(Element _elt) {
        return MiniMapCoords.newMiniMapCoords(_elt.getAttribute(ATTR_VALUE));
    }

    private static PlaceInterConnect getPlaceInterConnect(Element _elt) {
        return PlaceInterConnect.newPlaceInterConnect(_elt.getAttribute(ATTR_VALUE));
    }

    private static TileMiniMap getTileMiniMap(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        TileMiniMap object_ = Instances.newTileMiniMap();
        for (Element c: childElements_) {
            getTileMiniMap(object_,c.getAttribute(ATTR_FIELD),c);
        }
        return object_;
    }

    private static void getTileMiniMap(TileMiniMap _object, String _fieldName, Element _element) {
        if (StringList.quickEq(_fieldName, FIELD_FILE)) {
            _object.setFile(DocumentReaderCoreUtil.getString(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_PLACE)) {
            _object.setPlace(DocumentReaderCoreUtil.getShort(_element));
            return;
        }
        if (StringList.quickEq(_fieldName, FIELD_HEROS)) {
            _object.setHeros(DocumentReaderCoreUtil.getBoolean(_element));
            return;
        }
    }

    private static Coords getCoords(Element _elt) {
        return Coords.newCoords(_elt.getAttribute(ATTR_VALUE));
    }

    private static LevelPoint getLevelPoint(Element _elt) {
        return LevelPoint.newLevelPoint(_elt.getAttribute(ATTR_VALUE));
    }

    private static Point getPoint(Element _elt) {
        return Point.newPoint(_elt.getAttribute(ATTR_VALUE));
    }

    private static MonteCarloEnum<Statistic> getMonteCarloEnumStatistic(Element _element) {
        ElementList childElements_ = _element.getChildElements();
        MonteCarloEnum<Statistic> object_ = new MonteCarloEnum<Statistic>();
        for (Element c: childElements_) {
            object_.setLaw(getMapStatisticLgInt(c));
        }
        return object_;
    }

    private static EnumMap<Statistic,LgInt> getMapStatisticLgInt(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Statistic,LgInt> map_ = new EnumMap<Statistic,LgInt>(cap_);
        EnumList<Statistic> keys_ = new EnumList<Statistic>(cap_);
        EqList<LgInt> values_ = new EqList<LgInt>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderMathUtil.getLgInt(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static CustList<EffectWhileSending> getListEffectWhileSending(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectWhileSending> list_ = new CustList<EffectWhileSending>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectWhileSending(c));
        }
        return list_;
    }

    private static CustList<Effect> getListEffect(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<Effect> list_ = new CustList<Effect>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffect(c));
        }
        return list_;
    }

    private static CustList<EffectEndRound> getListEffectEndRound(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectEndRound> list_ = new CustList<EffectEndRound>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectEndRound(c));
        }
        return list_;
    }

    private static CustList<EffectEndRoundFoe> getListEffectEndRoundFoe(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectEndRoundFoe> list_ = new CustList<EffectEndRoundFoe>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectEndRoundFoe(c));
        }
        return list_;
    }

    private static CustList<EffectEndRoundStatus> getListEffectEndRoundStatus(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectEndRoundStatus> list_ = new CustList<EffectEndRoundStatus>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectEndRoundStatus(c));
        }
        return list_;
    }

    private static CustList<EffectTeam> getListEffectTeam(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectTeam> list_ = new CustList<EffectTeam>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectTeam(c));
        }
        return list_;
    }

    private static CustList<EffectPartnerStatus> getListEffectPartnerStatus(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<EffectPartnerStatus> list_ = new CustList<EffectPartnerStatus>(cap_);
        for (Element c: childElements_) {
            list_.add(getEffectPartnerStatus(c));
        }
        return list_;
    }

    private static CustList<AreaApparition> getListAreaApparition(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<AreaApparition> list_ = new CustList<AreaApparition>(cap_);
        for (Element c: childElements_) {
            list_.add(getAreaApparition(c));
        }
        return list_;
    }

    private static CustList<LevelLeague> getListLevelLeague(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<LevelLeague> list_ = new CustList<LevelLeague>(cap_);
        for (Element c: childElements_) {
            list_.add(getLevelLeague(c));
        }
        return list_;
    }

    private static CustList<PkTrainer> getListPkTrainer(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<PkTrainer> list_ = new CustList<PkTrainer>(cap_);
        for (Element c: childElements_) {
            list_.add(getPkTrainer(c));
        }
        return list_;
    }

    private static CustList<PokemonTeam> getListPokemonTeam(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<PokemonTeam> list_ = new CustList<PokemonTeam>(cap_);
        for (Element c: childElements_) {
            list_.add(getPokemonTeam(c));
        }
        return list_;
    }

    public static CustList<UsablePokemon> getListUsablePokemon(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        CustList<UsablePokemon> list_ = new CustList<UsablePokemon>(cap_);
        for (Element c: childElements_) {
            list_.add(getUsablePokemon(c));
        }
        return list_;
    }

    private static EnumList<Statistic> getListStatistic(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EnumList<Statistic> list_ = new EnumList<Statistic>(cap_);
        for (Element c: childElements_) {
            list_.add(getStatistic(c));
        }
        return list_;
    }

    private static EnumMap<Statistic,BoostHpRate> getMapStatisticBoostHpRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Statistic,BoostHpRate> map_ = new EnumMap<Statistic,BoostHpRate>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<BoostHpRate> values_ = new CustList<BoostHpRate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(getBoostHpRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EnumMap<Statistic,StatBaseEv> getMapStatisticStatBaseEv(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Statistic,StatBaseEv> map_ = new EnumMap<Statistic,StatBaseEv>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<StatBaseEv> values_ = new CustList<StatBaseEv>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(getStatBaseEv(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EnumMap<Statistic,Rate> getMapStatisticRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Statistic,Rate> map_ = new EnumMap<Statistic,Rate>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EnumMap<Statistic,Byte> getMapStatisticByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Statistic,Byte> map_ = new EnumMap<Statistic,Byte>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EnumMap<Statistic,Short> getMapStatisticShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Statistic,Short> map_ = new EnumMap<Statistic,Short>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<Short> values_ = new CustList<Short>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getShort(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EnumMap<Statistic,String> getMapStatisticString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<Statistic,String> map_ = new EnumMap<Statistic,String>(cap_);
        CustList<Statistic> keys_ = new CustList<Statistic>(cap_);
        CustList<String> values_ = new CustList<String>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatistic(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EnumMap<EnvironmentType,String> getMapEnvironmentTypeString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        EnumMap<EnvironmentType,String> map_ = new EnumMap<EnvironmentType,String>(cap_);
        CustList<EnvironmentType> keys_ = new CustList<EnvironmentType>(cap_);
        CustList<String> values_ = new CustList<String>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getEnvironmentType(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static EqList<LevelMove> getListLevelMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<LevelMove> list_ = new EqList<LevelMove>(cap_);
        for (Element c: childElements_) {
            list_.add(getLevelMove(c));
        }
        return list_;
    }

    private static EqList<StatisticStatus> getListStatisticStatus(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<StatisticStatus> list_ = new EqList<StatisticStatus>(cap_);
        for (Element c: childElements_) {
            list_.add(getStatisticStatus(c));
        }
        return list_;
    }

    private static EqList<TypesDuo> getListTypesDuo(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<TypesDuo> list_ = new EqList<TypesDuo>(cap_);
        for (Element c: childElements_) {
            list_.add(getTypesDuo(c));
        }
        return list_;
    }

    private static EqList<TargetCoords> getListTargetCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<TargetCoords> list_ = new EqList<TargetCoords>(cap_);
        for (Element c: childElements_) {
            list_.add(getTargetCoords(c));
        }
        return list_;
    }

    private static EqList<WildPk> getListWildPk(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<WildPk> list_ = new EqList<WildPk>(cap_);
        for (Element c: childElements_) {
            list_.add(getWildPk(c));
        }
        return list_;
    }

    private static EqList<Coords> getListCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<Coords> list_ = new EqList<Coords>(cap_);
        for (Element c: childElements_) {
            list_.add(getCoords(c));
        }
        return list_;
    }

    private static EqList<Point> getListPoint(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_);
        EqList<Point> list_ = new EqList<Point>(cap_);
        for (Element c: childElements_) {
            list_.add(getPoint(c));
        }
        return list_;
    }

    public static NatTreeMap<Byte,PokemonPlayer> getMapBytePokemonPlayer(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NatTreeMap<Byte,PokemonPlayer> map_ = new NatTreeMap<Byte,PokemonPlayer>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<PokemonPlayer> values_ = new CustList<PokemonPlayer>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getPokemonPlayer(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Byte,Anticipation> getMapByteAnticipation(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,Anticipation> map_ = new NumberMap<Byte,Anticipation>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<Anticipation> values_ = new CustList<Anticipation>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getAnticipation(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Byte,ChoiceOfEvolutionAndMoves> getMapByteChoiceOfEvolutionAndMoves(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,ChoiceOfEvolutionAndMoves> map_ = new NumberMap<Byte,ChoiceOfEvolutionAndMoves>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<ChoiceOfEvolutionAndMoves> values_ = new CustList<ChoiceOfEvolutionAndMoves>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getChoiceOfEvolutionAndMoves(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Byte,Fighter> getMapByteFighter(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,Fighter> map_ = new NumberMap<Byte,Fighter>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<Fighter> values_ = new CustList<Fighter>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getFighter(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Byte,StacksOfUses> getMapByteStacksOfUses(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,StacksOfUses> map_ = new NumberMap<Byte,StacksOfUses>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<StacksOfUses> values_ = new CustList<StacksOfUses>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getStacksOfUses(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Byte,Team> getMapByteTeam(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,Team> map_ = new NumberMap<Byte,Team>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<Team> values_ = new CustList<Team>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getTeam(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Byte,LevelCave> getMapByteLevelCave(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Byte,LevelCave> map_ = new NumberMap<Byte,LevelCave>(cap_);
        CustList<Byte> keys_ = new CustList<Byte>(cap_);
        CustList<LevelCave> values_ = new CustList<LevelCave>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getByte(c));
            } else {
                values_.add(getLevelCave(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Short,Place> getMapShortPlace(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Short,Place> map_ = new NumberMap<Short,Place>(cap_);
        CustList<Short> keys_ = new CustList<Short>(cap_);
        CustList<Place> values_ = new CustList<Place>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getShort(c));
            } else {
                values_.add(getPlace(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static NumberMap<Short,EqList<Point>> getMapShortListPoint(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        NumberMap<Short,EqList<Point>> map_ = new NumberMap<Short,EqList<Point>>(cap_);
        CustList<Short> keys_ = new CustList<Short>(cap_);
        CustList<EqList<Point>> values_ = new CustList<EqList<Point>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getShort(c));
            } else {
                values_.add(getListPoint(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<CategoryMult,Rate> getMapCategoryMultRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<CategoryMult,Rate> map_ = new ObjectMap<CategoryMult,Rate>(cap_);
        CustList<CategoryMult> keys_ = new CustList<CategoryMult>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCategoryMult(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StatisticCategory,Rate> getMapStatisticCategoryRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StatisticCategory,Rate> map_ = new ObjectMap<StatisticCategory,Rate>(cap_);
        CustList<StatisticCategory> keys_ = new CustList<StatisticCategory>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticCategory(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StatisticCategory,Byte> getMapStatisticCategoryByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StatisticCategory,Byte> map_ = new ObjectMap<StatisticCategory,Byte>(cap_);
        CustList<StatisticCategory> keys_ = new CustList<StatisticCategory>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticCategory(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StatisticPokemon,Byte> getMapStatisticPokemonByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StatisticPokemon,Byte> map_ = new ObjectMap<StatisticPokemon,Byte>(cap_);
        CustList<StatisticPokemon> keys_ = new CustList<StatisticPokemon>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticPokemon(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StatisticStatus,Byte> getMapStatisticStatusByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StatisticStatus,Byte> map_ = new ObjectMap<StatisticStatus,Byte>(cap_);
        CustList<StatisticStatus> keys_ = new CustList<StatisticStatus>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticStatus(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StatisticType,Rate> getMapStatisticTypeRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StatisticType,Rate> map_ = new ObjectMap<StatisticType,Rate>(cap_);
        CustList<StatisticType> keys_ = new CustList<StatisticType>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticType(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StatisticType,Byte> getMapStatisticTypeByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StatisticType,Byte> map_ = new ObjectMap<StatisticType,Byte>(cap_);
        CustList<StatisticType> keys_ = new CustList<StatisticType>(cap_);
        CustList<Byte> values_ = new CustList<Byte>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getStatisticType(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<TypesDuo,Rate> getMapTypesDuoRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<TypesDuo,Rate> map_ = new ObjectMap<TypesDuo,Rate>(cap_);
        CustList<TypesDuo> keys_ = new CustList<TypesDuo>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getTypesDuo(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<WeatherType,Rate> getMapWeatherTypeRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<WeatherType,Rate> map_ = new ObjectMap<WeatherType,Rate>(cap_);
        CustList<WeatherType> keys_ = new CustList<WeatherType>(cap_);
        CustList<Rate> values_ = new CustList<Rate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getWeatherType(c));
            } else {
                values_.add(DocumentReaderMathUtil.getRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<NbFightCoords,Boolean> getMapNbFightCoordsBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<NbFightCoords,Boolean> map_ = new ObjectMap<NbFightCoords,Boolean>(cap_);
        CustList<NbFightCoords> keys_ = new CustList<NbFightCoords>(cap_);
        CustList<Boolean> values_ = new CustList<Boolean>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getNbFightCoords(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<MoveTeamPosition,ActivityOfMove> getMapMoveTeamPositionActivityOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<MoveTeamPosition,ActivityOfMove> map_ = new ObjectMap<MoveTeamPosition,ActivityOfMove>(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<ActivityOfMove> values_ = new CustList<ActivityOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(getActivityOfMove(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<MoveTeamPosition,AffectedMove> getMapMoveTeamPositionAffectedMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<MoveTeamPosition,AffectedMove> map_ = new ObjectMap<MoveTeamPosition,AffectedMove>(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<AffectedMove> values_ = new CustList<AffectedMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(getAffectedMove(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<MoveTeamPosition,StringList> getMapMoveTeamPositionStringList(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<MoveTeamPosition,StringList> map_ = new ObjectMap<MoveTeamPosition,StringList>(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<StringList> values_ = new CustList<StringList>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getStringList(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<MoveTeamPosition,Boolean> getMapMoveTeamPositionBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<MoveTeamPosition,Boolean> map_ = new ObjectMap<MoveTeamPosition,Boolean>(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<Boolean> values_ = new CustList<Boolean>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<MoveTeamPosition,Short> getMapMoveTeamPositionShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<MoveTeamPosition,Short> map_ = new ObjectMap<MoveTeamPosition,Short>(cap_);
        CustList<MoveTeamPosition> keys_ = new CustList<MoveTeamPosition>(cap_);
        CustList<Short> values_ = new CustList<Short>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTeamPosition(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getShort(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<MoveTarget,MoveTarget> getMapMoveTargetMoveTarget(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<MoveTarget,MoveTarget> map_ = new ObjectMap<MoveTarget,MoveTarget>(cap_);
        CustList<MoveTarget> keys_ = new CustList<MoveTarget>(cap_);
        CustList<MoveTarget> values_ = new CustList<MoveTarget>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMoveTarget(c));
            } else {
                values_.add(getMoveTarget(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<MiniMapCoords,TileMiniMap> getMapMiniMapCoordsTileMiniMap(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<MiniMapCoords,TileMiniMap> map_ = new ObjectMap<MiniMapCoords,TileMiniMap>(cap_);
        CustList<MiniMapCoords> keys_ = new CustList<MiniMapCoords>(cap_);
        CustList<TileMiniMap> values_ = new CustList<TileMiniMap>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getMiniMapCoords(c));
            } else {
                values_.add(getTileMiniMap(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<PlaceInterConnect,Coords> getMapPlaceInterConnectCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<PlaceInterConnect,Coords> map_ = new ObjectMap<PlaceInterConnect,Coords>(cap_);
        CustList<PlaceInterConnect> keys_ = new CustList<PlaceInterConnect>(cap_);
        CustList<Coords> values_ = new CustList<Coords>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPlaceInterConnect(c));
            } else {
                values_.add(getCoords(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Coords,HostPokemonDuo> getMapCoordsHostPokemonDuo(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Coords,HostPokemonDuo> map_ = new ObjectMap<Coords,HostPokemonDuo>(cap_);
        CustList<Coords> keys_ = new CustList<Coords>(cap_);
        CustList<HostPokemonDuo> values_ = new CustList<HostPokemonDuo>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCoords(c));
            } else {
                values_.add(getHostPokemonDuo(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Coords,EqList<Coords>> getMapCoordsListCoords(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Coords,EqList<Coords>> map_ = new ObjectMap<Coords,EqList<Coords>>(cap_);
        CustList<Coords> keys_ = new CustList<Coords>(cap_);
        CustList<EqList<Coords>> values_ = new CustList<EqList<Coords>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCoords(c));
            } else {
                values_.add(getListCoords(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Coords,Boolean> getMapCoordsBoolean(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Coords,Boolean> map_ = new ObjectMap<Coords,Boolean>(cap_);
        CustList<Coords> keys_ = new CustList<Coords>(cap_);
        CustList<Boolean> values_ = new CustList<Boolean>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getCoords(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getBoolean(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<LevelPoint,Link> getMapLevelPointLink(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<LevelPoint,Link> map_ = new ObjectMap<LevelPoint,Link>(cap_);
        CustList<LevelPoint> keys_ = new CustList<LevelPoint>(cap_);
        CustList<Link> values_ = new CustList<Link>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getLevelPoint(c));
            } else {
                values_.add(getLink(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,Building> getMapPointBuilding(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,Building> map_ = new ObjectMap<Point,Building>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Building> values_ = new CustList<Building>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getBuilding(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,CharacterInRoadCave> getMapPointCharacterInRoadCave(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,CharacterInRoadCave> map_ = new ObjectMap<Point,CharacterInRoadCave>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<CharacterInRoadCave> values_ = new CustList<CharacterInRoadCave>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getCharacterInRoadCave(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,DualFight> getMapPointDualFight(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,DualFight> map_ = new ObjectMap<Point,DualFight>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<DualFight> values_ = new CustList<DualFight>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getDualFight(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,GymTrainer> getMapPointGymTrainer(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,GymTrainer> map_ = new ObjectMap<Point,GymTrainer>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<GymTrainer> values_ = new CustList<GymTrainer>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getGymTrainer(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,Person> getMapPointPerson(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,Person> map_ = new ObjectMap<Point,Person>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Person> values_ = new CustList<Person>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getPerson(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,Block> getMapPointBlock(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,Block> map_ = new ObjectMap<Point,Block>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Block> values_ = new CustList<Block>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getBlock(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,Link> getMapPointLink(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,Link> map_ = new ObjectMap<Point,Link>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Link> values_ = new CustList<Link>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getLink(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,WildPk> getMapPointWildPk(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,WildPk> map_ = new ObjectMap<Point,WildPk>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<WildPk> values_ = new CustList<WildPk>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(getWildPk(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,Short> getMapPointShort(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,Short> map_ = new ObjectMap<Point,Short>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<Short> values_ = new CustList<Short>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getShort(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<Point,String> getMapPointString(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<Point,String> map_ = new ObjectMap<Point,String>(cap_);
        CustList<Point> keys_ = new CustList<Point>(cap_);
        CustList<String> values_ = new CustList<String>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(getPoint(c));
            } else {
                values_.add(DocumentReaderCoreUtil.getString(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StringList,EffectCombo> getMapStringListEffectCombo(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StringList,EffectCombo> map_ = new ObjectMap<StringList,EffectCombo>(cap_);
        CustList<StringList> keys_ = new CustList<StringList>(cap_);
        CustList<EffectCombo> values_ = new CustList<EffectCombo>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getStringList(c));
            } else {
                values_.add(getEffectCombo(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static ObjectMap<StringList,ActivityOfMove> getMapStringListActivityOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        ObjectMap<StringList,ActivityOfMove> map_ = new ObjectMap<StringList,ActivityOfMove>(cap_);
        CustList<StringList> keys_ = new CustList<StringList>(cap_);
        CustList<ActivityOfMove> values_ = new CustList<ActivityOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getStringList(c));
            } else {
                values_.add(getActivityOfMove(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    public static StringMap<GenderRepartition> getStringMapGenderRepartition(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<GenderRepartition> map_ = new StringMap<GenderRepartition>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<GenderRepartition> values_ = new CustList<GenderRepartition>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getGenderRepartition(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<Evolution> getStringMapEvolution(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<Evolution> map_ = new StringMap<Evolution>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<Evolution> values_ = new CustList<Evolution>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getEvolution(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<EfficiencyRate> getStringMapEfficiencyRate(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<EfficiencyRate> map_ = new StringMap<EfficiencyRate>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<EfficiencyRate> values_ = new CustList<EfficiencyRate>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getEfficiencyRate(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<TypeDamageBoost> getStringMapTypeDamageBoost(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<TypeDamageBoost> map_ = new StringMap<TypeDamageBoost>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<TypeDamageBoost> values_ = new CustList<TypeDamageBoost>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getTypeDamageBoost(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<UsesOfMove> getStringMapUsesOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<UsesOfMove> map_ = new StringMap<UsesOfMove>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<UsesOfMove> values_ = new CustList<UsesOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getUsesOfMove(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<ActivityOfMove> getStringMapActivityOfMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<ActivityOfMove> map_ = new StringMap<ActivityOfMove>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<ActivityOfMove> values_ = new CustList<ActivityOfMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getActivityOfMove(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<CopiedMove> getStringMapCopiedMove(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<CopiedMove> map_ = new StringMap<CopiedMove>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<CopiedMove> values_ = new CustList<CopiedMove>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getCopiedMove(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<MovesAbilities> getStringMapMovesAbilities(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<MovesAbilities> map_ = new StringMap<MovesAbilities>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<MovesAbilities> values_ = new CustList<MovesAbilities>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMovesAbilities(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<EnumList<Statistic>> getStringMapListStatistic(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<EnumList<Statistic>> map_ = new StringMap<EnumList<Statistic>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<EnumList<Statistic>> values_ = new CustList<EnumList<Statistic>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getListStatistic(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<EnumMap<Statistic,Byte>> getStringMapMapStatisticByte(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<EnumMap<Statistic,Byte>> map_ = new StringMap<EnumMap<Statistic,Byte>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<EnumMap<Statistic,Byte>> values_ = new CustList<EnumMap<Statistic,Byte>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMapStatisticByte(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<NumberMap<Byte,Anticipation>> getStringMapMapByteAnticipation(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<NumberMap<Byte,Anticipation>> map_ = new StringMap<NumberMap<Byte,Anticipation>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<NumberMap<Byte,Anticipation>> values_ = new CustList<NumberMap<Byte,Anticipation>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMapByteAnticipation(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
    private static StringMap<NumberMap<Byte,StacksOfUses>> getStringMapMapByteStacksOfUses(Element _elt) {
        ElementList childElements_ = _elt.getChildElements();
        int len_ = childElements_.getLength();
        CollCapacity cap_ = new CollCapacity(len_/2);
        StringMap<NumberMap<Byte,StacksOfUses>> map_ = new StringMap<NumberMap<Byte,StacksOfUses>>(cap_);
        StringList keys_ = new StringList(cap_);
        CustList<NumberMap<Byte,StacksOfUses>> values_ = new CustList<NumberMap<Byte,StacksOfUses>>(cap_);
        for (Element c: childElements_) {
            if (DocumentReaderCoreUtil.hasKey(c)) {
                keys_.add(DocumentReaderCoreUtil.getString(c));
            } else {
                values_.add(getMapByteStacksOfUses(c));
            }
        }
        int min_ = Math.min(keys_.size(), values_.size());
        for (int i = CustList.FIRST_INDEX; i < min_; i++) {
            map_.put(keys_.get(i), values_.get(i));
        }
        return map_;
    }
}
