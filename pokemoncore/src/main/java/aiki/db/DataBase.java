package aiki.db;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import aiki.comparators.ComparatorEndRoundMainElements;
import aiki.fight.Combos;
import aiki.fight.EndRoundMainElements;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.EndTurnType;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.Fossil;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingStatus;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectAccuracy;
import aiki.fight.moves.effects.EffectAlly;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectCommonStatistics;
import aiki.fight.moves.effects.EffectCopyMove;
import aiki.fight.moves.effects.EffectCounterAttack;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectDamageRate;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundFoe;
import aiki.fight.moves.effects.EffectEndRoundIndividual;
import aiki.fight.moves.effects.EffectEndRoundMultiRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionRelation;
import aiki.fight.moves.effects.EffectEndRoundPositionTargetRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.moves.effects.EffectEndRoundSingleStatus;
import aiki.fight.moves.effects.EffectEndRoundStatus;
import aiki.fight.moves.effects.EffectFullHpRate;
import aiki.fight.moves.effects.EffectGlobal;
import aiki.fight.moves.effects.EffectInvoke;
import aiki.fight.moves.effects.EffectMultSufferedMovePower;
import aiki.fight.moves.effects.EffectMultUsedMovePower;
import aiki.fight.moves.effects.EffectProtectFromTypes;
import aiki.fight.moves.effects.EffectProtection;
import aiki.fight.moves.effects.EffectRestriction;
import aiki.fight.moves.effects.EffectStatistic;
import aiki.fight.moves.effects.EffectStatus;
import aiki.fight.moves.effects.EffectSwitchAbilities;
import aiki.fight.moves.effects.EffectSwitchMoveTypes;
import aiki.fight.moves.effects.EffectSwitchTypes;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import aiki.fight.moves.effects.EffectUnprotectFromTypes;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.PokemonFamily;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionItem;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.pokemon.evolution.EvolutionStone;
import aiki.fight.status.Status;
import aiki.fight.status.StatusType;
import aiki.fight.util.CategoryMult;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatisticCategory;
import aiki.fight.util.StatisticType;
import aiki.fight.util.TypesDuo;
import aiki.fight.util.WeatherType;
import aiki.game.Game;
import aiki.game.fight.CheckNumericStringsFight;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.Team;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
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
import aiki.map.characters.GymTrainer;
import aiki.map.characters.Person;
import aiki.map.characters.Seller;
import aiki.map.characters.TempTrainer;
import aiki.map.characters.Trainer;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.enums.Direction;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Block;
import aiki.map.levels.Level;
import aiki.map.levels.LevelCave;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.LevelIndoorPokemonCenter;
import aiki.map.levels.LevelLeague;
import aiki.map.levels.LevelWithWildPokemon;
import aiki.map.levels.Link;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Campaign;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.InitializedPlace;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.PokemonTeam;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.util.Dims;
import aiki.map.util.ScreenCoords;
import aiki.map.util.TileMiniMap;
import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.DocumentWriterAikiCoreUtil;
import aiki.util.Coords;
import aiki.util.LawNumber;
import aiki.util.LevelPoint;
import aiki.util.Point;
import aiki.util.TypeStatistic;
import code.images.BaseSixtyFourUtil;
import code.images.ConverterBufferedImage;
import code.images.Image;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteral.EvolvedMathFactory;
import code.maths.montecarlo.MonteCarloNumber;
import code.maths.montecarlo.MonteCarloString;
import code.resources.ResourceFiles;
import code.sml.DocumentBuilder;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NatStringTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.MathFactory;
import code.util.ints.NumericableString;
import code.util.ints.WithMathFactory;
import code.util.pagination.SelectedBoolean;

public class DataBase implements WithMathFactory {

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

    private StringMap<PokemonData> pokedex = new StringMap<PokemonData>();

    private Rate avgWeight = Rate.zero();

    private StringMap<MoveData> moves = new StringMap<MoveData>();

    private NumberMap<Short, String> tm = new NumberMap<Short, String>();

    private NumberMap<Short, LgInt> tmPrice = new NumberMap<Short, LgInt>();

    private NumberMap<Short, String> hm = new NumberMap<Short, String>();

    private StringMap<Item> items = new StringMap<Item>();

    private StringMap<AbilityData> abilities = new StringMap<AbilityData>();

    private StringMap<Status> status = new StringMap<Status>();

    private StringMap<int[][]> miniPk = new StringMap<int[][]>();

    private StringMap<int[][]> maxiPkBack = new StringMap<int[][]>();

    private StringMap<int[][]> maxiPkFront = new StringMap<int[][]>();

    private int maxWidthPk;

    private int maxHeightPk;

    private StringMap<int[][]> miniItems = new StringMap<int[][]>();

    private StringMap<int[][]> trainers = new StringMap<int[][]>();

    private StringMap<int[][]> people = new StringMap<int[][]>();

    private StringMap<int[][]> typesImages = new StringMap<int[][]>();

    private StringMap<String> typesColors = new StringMap<String>();

    private ObjectMap<ImageHeroKey, int[][]> frontHeros = new ObjectMap<ImageHeroKey, int[][]>();

    private ObjectMap<ImageHeroKey, int[][]> backHeros = new ObjectMap<ImageHeroKey, int[][]>();

    private ObjectMap<ImageHeroKey, int[][]> overWorldHeros = new ObjectMap<ImageHeroKey, int[][]>();

    private StringMap<int[][]> links = new StringMap<int[][]>();

    private StringMap<int[][]> images = new StringMap<int[][]>();

    private StringMap<ObjectMap<ScreenCoords, int[][]>> imagesTiles = new StringMap<ObjectMap<ScreenCoords, int[][]>>();

    private StringMap<int[][]> miniMap = new StringMap<int[][]>();

    private StringMap<Dims> imagesDimensions = new StringMap<Dims>();

    private int[][] imageTmHm = new int[0][0];

    private int[][] storage = new int[0][0];

    private DataMap map = new DataMap();

    private Combos combos = new Combos();

    private StringMap<Rate> constNum = new StringMap<Rate>();

    private String rateBoostCriticalHit;

    private String rateFleeing;

    private String defMove;

    private String rateBoost;

    private String damageFormula;

    private String ballDef;

    private String defaultEggGoup;

    private String rateCatching;

    private EnumMap<ExpType, String> expGrowth = new EnumMap<ExpType, String>();

    private EnumMap<DifficultyWinPointsFight, String> rates = new EnumMap<DifficultyWinPointsFight, String>();

    private ObjectMap<TypesDuo, Rate> tableTypes = new ObjectMap<TypesDuo, Rate>();
    private StringList types = new StringList();

    private EnumMap<DifficultyModelLaw, LawNumber> lawsDamageRate = new EnumMap<DifficultyModelLaw, LawNumber>();

    private StringList movesProtAgainstStatusMoves;
    private StringList movesProtAgainstDamageMoves;
    private StringList movesProtAgainstPrio;
    private StringList movesProtAgainstMultiTarget;
    private StringList movesCountering;
    private StringList movesProtSingleTarget;
    private StringList movesProtSingleTargetAgainstKo;
    private StringList movesCopyingTemp;
    private StringList trappingMoves;
    private StringList movesAccuracy;
    private StringList movesActingMoveUses;
    private StringList movesForbidding;
    private StringList movesEffectIndiv;
    private StringList movesEffectProt;
    private StringList movesEffectUnprot;
    private StringList movesEffectIndivIncr;
    private StringList movesEffEndRoundIndiv;
    private StringList movesEffEndRoundIndivIncr;
    private StringList movesEffectTeam;
    private StringList movesEffectWhileSending;
    private StringList movesEffectGlobal;
    private StringList movesEffectGlobalWeather;
    private StringList movesEffectAlly;
    private StringList movesHealingAfter;
    private StringList movesFullHeal;
    private StringList movesAnticipation;
    private StringList movesConstChoices;
    private StringList movesInvoking;
    private StringList movesChangingTypes;
    private StringList allCategories;
    private StringList categories;

    private StringList variables;

    private StringMap<StringList> varParamsMove;

    private CustList<EndRoundMainElements> evtEndRound;

    private int[][] endGameImage = new int[0][0];

    private StringList filesWithSameNameDifferentCase;

    private StringMap<StringMap<String>> translatedCategories = new StringMap<StringMap<String>>();

    private StringMap<EnumMap<EnvironmentType, String>> translatedEnvironment = new StringMap<EnumMap<EnvironmentType, String>>();

    private StringMap<EnumMap<SelectedBoolean, String>> translatedBooleans = new StringMap<EnumMap<SelectedBoolean, String>>();

    private StringMap<EnumMap<DifficultyWinPointsFight, String>> translatedDiffWinPts = new StringMap<EnumMap<DifficultyWinPointsFight, String>>();

    private StringMap<EnumMap<DifficultyModelLaw, String>> translatedDiffModelLaw = new StringMap<EnumMap<DifficultyModelLaw, String>>();

    private StringMap<EnumMap<Gender, String>> translatedGenders = new StringMap<EnumMap<Gender, String>>();

    private StringMap<EnumMap<Statistic, String>> translatedStatistics = new StringMap<EnumMap<Statistic, String>>();

    private StringMap<EnumMap<TargetChoice, String>> translatedTargets = new StringMap<EnumMap<TargetChoice, String>>();

    private StringMap<StringMap<String>> translatedTypes = new StringMap<StringMap<String>>();

    private StringMap<StringMap<String>> translatedPokemon = new StringMap<StringMap<String>>();

    private StringMap<StringMap<String>> translatedMoves = new StringMap<StringMap<String>>();

    private StringMap<StringMap<String>> translatedItems = new StringMap<StringMap<String>>();

    private StringMap<StringMap<String>> translatedAbilities = new StringMap<StringMap<String>>();

    private StringMap<StringMap<String>> translatedStatus = new StringMap<StringMap<String>>();

    private StringMap<StringMap<String>> translatedClassesDescriptions = new StringMap<StringMap<String>>();

    private StringMap<int[][]> animStatis = new StringMap<int[][]>();

    private StringMap<int[][]> animStatus = new StringMap<int[][]>();

    private int[][] animAbsorb = new int[0][0];

    private StringMap<StringMap<String>> litterals = new StringMap<StringMap<String>>();

    private StringMap<StringMap<String>> translatedFctMath = new StringMap<StringMap<String>>();

    private StringMap<PokemonFamily> families = new StringMap<PokemonFamily>();

    private boolean checkTranslation;

    private EvolvedMathFactory standardMathFactory = new EvolvedMathFactory();
    private boolean error;
    private StringMap<String> messagesPokemonPlayer = new StringMap<String>();
    private StringMap<String> messagesPlayer = new StringMap<String>();
    private StringMap<String> messagesFighter = new StringMap<String>();
    private StringMap<String> messagesTeam = new StringMap<String>();
    private StringMap<String> messagesFight = new StringMap<String>();
    private StringMap<String> messagesGame = new StringMap<String>();
    private String language = "";

    @Override
    public MathFactory getMathFactory() {
        return standardMathFactory;
    }

    public LgInt getMaxRd() {
        return standardMathFactory.getMaxRandomNb();
    }
    public Rate evaluateDirectlyRate(String _numExp) {
        return standardMathFactory.evaluateDirectlyRate(_numExp);
    }

    public Boolean evaluateDirectlyBoolean(String _booleanExp) {
        return standardMathFactory.evaluateDirectlyBoolean(_booleanExp);
    }

    public NumericableString<Rate> createNumericableString(
            String _chaineNumerique, StringMap<String> _vars) {
        return standardMathFactory.createNumericableString(_chaineNumerique,
                _vars);
    }

    public NumericableString<Boolean> createBooleanString(
            String _chaineBooleenne, StringMap<String> _vars) {
        return standardMathFactory.createBooleanString(_chaineBooleenne, _vars);
    }

    public String getTrueString() {
        return standardMathFactory.getTrueString();
    }

    public String getFalseString() {
        return standardMathFactory.getFalseString();
    }

    public char getSepartorSetChar() {
        return standardMathFactory.getSepartorSetChar();
    }

    public Rate evaluateNumericable(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return standardMathFactory.evaluateNumericable(_numericString,
                _variables, _default);
    }

    public Rate evaluatePositiveOrZeroExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return standardMathFactory.evaluatePositiveOrZeroExp(_numericString,
                _variables, _default);
    }

    public Rate evaluatePositiveExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return standardMathFactory.evaluatePositiveExp(_numericString,
                _variables, _default);
    }

    public boolean evaluateBoolean(String _booleanString,
            StringMap<String> _variables, Boolean _default) {
        return standardMathFactory.evaluateBoolean(_booleanString, _variables,
                _default);
    }

    public ObjectMap<Point, int[][]> getLevelImage(short _pl, byte _level) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        return getLevelImage(coords_);
    }

    public ObjectMap<Point, int[][]> getLevelImage(short _pl, byte _level,
            Point _inside) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        coords_.setInsideBuilding(_inside);
        return getLevelImage(coords_);
    }

    public ObjectMap<Point, int[][]> getLevelImage(Coords _coords) {
        ObjectMap<Point, int[][]> tiles_ = Level.getLevelBackgroundImage(this,
                _coords);
        ObjectMap<Point, int[][]> frontTiles_ = Level.getLevelForegroundImage(
                this, _coords);
        for (Point p : frontTiles_.getKeys()) {
            tiles_.put(p, stackImages(tiles_, frontTiles_, p));
        }
        return tiles_;
    }

    public static int[][] stackImages(ObjectMap<Point, int[][]> _tiles,
            ObjectMap<Point, int[][]> _frontTiles, Point _pt) {
        int[][] img_ = _frontTiles.getVal(_pt);
        if (img_.length == 0) {
            return _tiles.getVal(_pt);
        }
        int[][] imgBack_ = _tiles.getVal(_pt);
        return ConverterBufferedImage.stackImages(imgBack_, img_);
    }

    public void addPerson(String _fileName, int[][] _img) {
        people.put(_fileName, _img);
    }

    public void addBackHero(EnvironmentType _env, Sex _sex, int[][] _img) {
        backHeros.put(new ImageHeroKey(_env, _sex), _img);
    }

    public void addFrontHero(EnvironmentType _env, Sex _sex, int[][] _img) {
        frontHeros.put(new ImageHeroKey(_env, _sex), _img);
    }

    public void addOverworldHero(EnvironmentType _env, Direction _dir,
            Sex _sex, int[][] _img) {
        overWorldHeros.put(new ImageHeroKey(_env, _dir, _sex), _img);
    }

    public void addImage(String _fileName, int[][] _img) {
        images.put(_fileName, _img);
    }

    public void addLink(String _fileName, int[][] _img) {
        links.put(_fileName, _img);
    }

    public void addTrainerImage(String _fileName, int[][] _img) {
        trainers.put(_fileName, _img);
    }

    public void addFrontImagePk(String _fileName, int[][] _img) {
        maxiPkFront.put(_fileName, _img);
    }

    public void addBackImagePk(String _fileName, int[][] _img) {
        maxiPkBack.put(_fileName, _img);
    }

    public void addMiniImagePk(String _fileName, int[][] _img) {
        miniPk.put(_fileName, _img);
    }

    public void addImageObjects(String _fileName, int[][] _img) {
        miniItems.put(_fileName, _img);
    }

    public void validate(AtomicInteger _perCentLoading, AtomicBoolean _loading) {
        imagesDimensions.clear();
        for (LawNumber v : lawsDamageRate.values()) {
            if (v.getLaw().events().isEmpty()) {
                setError(true);
                return;
            }
        }

        for (PokemonData pk_ : pokedex.values()) {
            for (short hm_ : pk_.getHiddenMoves()) {
                String move_ = hm.getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (short hm_ : pk_.getTechnicalMoves()) {
                String move_ = tm.getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (LevelMove l : pk_.getLevMoves()) {
                pk_.getMoveTutors().add(l.getMove());
            }
            pk_.getMoveTutors().removeDuplicates();
        }
        validateCore(_perCentLoading);
        if (!_loading.get()) {
            return;
        }
        _perCentLoading.set(60);
        validateConstants();
        setCheckTranslation(true);
        CheckNumericStringsFight.validateNumericBooleanStrings(this, false);
        if (!_loading.get()) {
            return;
        }
        _perCentLoading.set(70);
        Rate power_ = getStrongMovePower();
        if (Rate.strLower(power_, new Rate(90))) {
            setError(true);
            return;
        }
        ObjectNotNullMap<TypeStatistic, Boolean> strongMovesTypeStat_ = strongMoves(power_);
        for (EntryCust<TypeStatistic, Boolean> e : strongMovesTypeStat_
                .entryList()) {
            if (e.getValue()) {
                continue;
            }
            setError(true);
            return;
        }

        if (!_loading.get()) {
            return;
        }
        map.validate(this);
        if (map.isError()) {
            setError(true);
            return;
        }
        _perCentLoading.set(85);
        if (!_loading.get()) {
            return;
        }
        validateImages();
        if (!_loading.get()) {
            return;
        }
        validateTranslations();
        _perCentLoading.set(95);

    }

    private ObjectNotNullMap<TypeStatistic, Boolean> strongMoves(Rate _power) {
        ObjectNotNullMap<TypeStatistic, Boolean> existDamageMoveWithTypeStatAttack_;
        existDamageMoveWithTypeStatAttack_ = new ObjectNotNullMap<TypeStatistic, Boolean>();
        for (String t : getTypes()) {
            existDamageMoveWithTypeStatAttack_.put(new TypeStatistic(t,
                    Statistic.ATTACK), false);
            existDamageMoveWithTypeStatAttack_.put(new TypeStatistic(t,
                    Statistic.SPECIAL_ATTACK), false);
        }
        for (EntryCust<String, MoveData> m : getMoves().entryList()) {
            MoveData move_ = m.getValue();
            int primaryEffect_ = move_.indexOfPrimaryEffect();
            if (!(move_ instanceof DamagingMoveData)) {
                continue;
            }
            if (move_.getPriority() < 0) {
                continue;
            }
            if (move_.getNbPrepaRound() > 0) {
                continue;
            }
            if (move_.getRechargeRound()) {
                continue;
            }
            if (move_.getTypes().size() > DataBase.ONE_POSSIBLE_CHOICE) {
                continue;
            }
            if (move_.getConstUserChoice()) {
                continue;
            }
            if (move_.getTargetChoice() == TargetChoice.ADJ_MULT) {
                continue;
            }
            if (move_.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE) {
                continue;
            }
            if (move_.getEffects().size() > DataBase.ONE_POSSIBLE_CHOICE) {
                boolean next_ = nextIteration(move_, primaryEffect_);
                if (next_) {
                    continue;
                }
            }
            String accStr_ = move_.getAccuracy();
            if (!Rate.isValid(accStr_)) {
                continue;
            }
            Rate acc_ = new Rate(accStr_);
            if (Rate.strLower(acc_, new Rate(1))) {
                continue;
            }

            DamagingMoveData damageMove_ = (DamagingMoveData) move_;
            for (Effect e : damageMove_.getEffects()) {
                if (!(e instanceof EffectDamage)) {
                    continue;
                }
                EffectDamage effect_ = (EffectDamage) e;
                if (!effect_.getFail().isEmpty()) {
                    continue;
                }
                TypeStatistic pair_;
                pair_ = new TypeStatistic(move_.getTypes().first(),
                        effect_.getStatisAtt());
                if (existDamageMoveWithTypeStatAttack_.getVal(pair_)) {
                    continue;
                }
                String powStr_ = effect_.getPower();
                if (!Rate.isValid(powStr_)) {
                    break;
                }
                if (Rate.strLower(new Rate(powStr_), _power)) {
                    break;
                }

                existDamageMoveWithTypeStatAttack_.put(pair_, true);
                break;
            }
        }
        return existDamageMoveWithTypeStatAttack_;
    }

    private static boolean nextIteration(MoveData _move, int _primaryEffect) {
        boolean next_ = false;
        int len_ = _move.getEffects().size();
        for (Effect sec_ : _move.getEffects().mid(_primaryEffect + 1, len_)) {
            if (sec_ instanceof EffectDamageRate) {
                if (!((EffectDamageRate) sec_).getRateDamage().isZeroOrGt()) {
                    next_ = true;
                    break;
                }
            }
            if (sec_ instanceof EffectStatistic) {
                boolean toBeTreated_ = false;
                if (sec_.getTargetChoice() == TargetChoice.LANCEUR) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.ALLIE) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.ALLIES) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.ADJ_MULT) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.GLOBALE) {
                    toBeTreated_ = true;
                }
                if (toBeTreated_) {
                    EffectStatistic effect_ = (EffectStatistic) sec_;
                    if (effect_.getStatisVarRank().contains(Statistic.SPEED)) {
                        if (effect_.getStatisVarRank().getVal(Statistic.SPEED) < 0) {
                            next_ = true;
                            break;
                        }
                    }
                    if (effect_.getStatisVarRank().contains(Statistic.ATTACK)) {
                        if (effect_.getStatisVarRank().getVal(Statistic.ATTACK) < 0) {
                            next_ = true;
                            break;
                        }
                    }
                    if (effect_.getStatisVarRank().contains(
                            Statistic.SPECIAL_ATTACK)) {
                        if (effect_.getStatisVarRank().getVal(
                                Statistic.SPECIAL_ATTACK) < 0) {
                            next_ = true;
                            break;
                        }
                    }
                    if (effect_.getStatisVarRank().contains(Statistic.ACCURACY)) {
                        if (effect_.getStatisVarRank().getVal(
                                Statistic.ACCURACY) < 0) {
                            next_ = true;
                            break;
                        }
                    }
                }
                if (sec_.getTargetChoice() == TargetChoice.ADJ_ADV) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.ADJ_MULT) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.ADJ_UNIQ) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.ANY_FOE) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.AUTRE_UNIQ) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.GLOBALE) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.TOUS_ADV) {
                    toBeTreated_ = true;
                }
                if (toBeTreated_) {
                    EffectStatistic effect_ = (EffectStatistic) sec_;
                    if (effect_.getStatisVarRank()
                            .contains(Statistic.EVASINESS)) {
                        if (effect_.getStatisVarRank().getVal(
                                Statistic.EVASINESS) < 0) {
                            next_ = true;
                            break;
                        }
                    }
                }
            }
            if (sec_ instanceof EffectStatus) {
                boolean toBeTreated_ = false;
                if (sec_.getTargetChoice() == TargetChoice.LANCEUR) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.ALLIE) {
                    toBeTreated_ = true;
                } else if (sec_.getTargetChoice() == TargetChoice.ALLIES) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.ADJ_MULT) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE) {
                    toBeTreated_ = true;
                } else if (_move.getTargetChoice() == TargetChoice.GLOBALE) {
                    toBeTreated_ = true;
                }
                if (toBeTreated_) {
                    EffectStatus effect_ = (EffectStatus) sec_;
                    if (!effect_.getLawStatus().events().isEmpty()) {
                        next_ = true;
                        break;
                    }
                }
            }
        }
        return next_;
    }

    public void validateCore(AtomicInteger _perCentLoading) {
        initTypesByTable();
        _perCentLoading.set(55);
        for (String t1_ : types) {
            for (String t2_ : types) {
                if (!tableTypes.contains(new TypesDuo(t1_, t2_))) {
                    setError(true);
                    return;
                }
                if (!tableTypes.getVal(new TypesDuo(t1_, t2_)).isZeroOrGt()) {
                    setError(true);
                    return;
                }
            }
        }
        if (getCategories().containsObj(AUTRE)) {
            setError(true);
            return;
        }
        for (String s : getCategories()) {

            if (!isCorrectIdentifier(s)) {
                setError(true);
                return;
            }
        }
        for (EntryCust<String, PokemonData> e : getPokedex().entryList()) {
            e.getValue().validate(this);
        }
        for (EntryCust<String, MoveData> e : getMoves().entryList()) {
            e.getValue().validate(this);
        }
        for (String m : movesFullHeal) {
            MoveData move_ = getMove(m);
            boolean foundAfter_ = false;
            for (Effect e : move_.getEffects()) {
                if (foundAfter_) {
                    setError(true);
                    return;
                }
                if (!(e instanceof EffectStatus)) {
                    continue;
                }
                EffectStatus eff_ = (EffectStatus) e;
                if (!eff_.getKoUserHealSubst()) {
                    continue;
                }
                if (e.getTargetChoice() != TargetChoice.LANCEUR) {
                    setError(true);
                    return;
                }
                foundAfter_ = true;
            }
        }
        combos.validate(this);
        for (EntryCust<String, Item> e : getItems().entryList()) {
            e.getValue().validate(this);
        }
        for (EntryCust<String, Status> e : getStatus().entryList()) {
            e.getValue().validate(this);
        }
        for (EntryCust<String, AbilityData> e : getAbilities().entryList()) {
            e.getValue().validate(this);
        }
        for (PokemonData d : getPokedex().values()) {
            StringList moves_ = new StringList(d.getMoveTutors());
            for (LevelMove p2_ : d.getLevMoves()) {
                moves_.add(p2_.getMove());
            }
            for (Evolution e : d.getEvolutions().values()) {
                if (!(e instanceof EvolutionMove)) {
                    continue;
                }
                if (!moves_.containsObj(((EvolutionMove) e).getMove())) {
                    setError(true);
                    return;
                }
            }
        }
        validateEvolutions();

        if (hasDuplicates(tm.values())) {
            setError(true);
            return;
        }
        if (hasDuplicates(hm.values())) {
            setError(true);
            return;
        }

        for (String m : hm.values()) {
            if (!getMoves().contains(m)) {
                setError(true);
                return;
            }
        }
        for (EntryCust<Short, LgInt> tmPrice_ : tmPrice.entryList()) {
            if (!tm.contains(tmPrice_.getKey())) {
                setError(true);
                return;
            }
            if (!tmPrice_.getValue().isZeroOrGt()) {
                setError(true);
                return;
            }
        }
        for (String m : tm.values()) {
            if (StringList.quickEq(m, getDefaultMove())) {
                setError(true);
                return;
            }
            if (!getMoves().contains(m)) {
                setError(true);
                return;
            }
        }
        Numbers<Short> incrementNbRound_ = new Numbers<Short>();
        Numbers<Short> nonIncrementNbRound_ = new Numbers<Short>();
        for (EndRoundMainElements e : getEvtEndRound()) {
            if (e.isIncrementNumberOfRounds()) {
                incrementNbRound_.add(e.getNumberIncrement());
                continue;
            }
            nonIncrementNbRound_.add(e.getNumberIncrement());
        }
        int nb_ = nonIncrementNbRound_.size();
        nonIncrementNbRound_.removeDuplicates();
        if (nb_ != nonIncrementNbRound_.size()) {
            setError(true);
            return;
        }
        for (short e : incrementNbRound_) {
            if (nonIncrementNbRound_.contains(e)) {
                setError(true);
                return;
            }
        }
    }

    private void validateEvolutions() {
        families = new StringMap<PokemonFamily>();
        StringList listEvoBases_ = new StringList();
        for (PokemonData d : getPokedex().values()) {
            listEvoBases_.add(d.getBaseEvo());
        }
        listEvoBases_.removeDuplicates();
        for (String p : listEvoBases_) {
            families.put(p, new PokemonFamily(this, p));
        }
        EqList<StringList> lists_ = new EqList<StringList>();
        for (PokemonFamily f : families.values()) {
            lists_.add(f.getAllPokemon());
        }
        if (!StringList.disjoints(lists_)) {
            setError(true);
            return;
        }
        StringList allPokemon_ = new StringList();
        for (StringList l : lists_) {
            allPokemon_.addAllElts(l);
        }
        if (!StringList.equalsSet(allPokemon_, pokedex.getKeys())) {
            setError(true);
            return;
        }
    }

    public void validateConstants() {
        if (getDefaultMoney().isZero()) {
            setError(true);
            return;
        }
        if (!getDefaultMoney().isZeroOrGt()) {
            setError(true);
            return;
        }
        if (!getMinHp().isZeroOrGt()) {
            setError(true);
            return;
        }
        if (!getStab().greaterThanOne()) {
            setError(true);
            return;
        }
        if (getNbMaxTeam() < 2) {
            setError(true);
            return;
        }
        if (getNbMaxTeam() > 8) {
            setError(true);
            return;
        }
        if (getMaxPp() <= 0) {
            setError(true);
            return;
        }
        if (getMaxPp() > 255) {
            setError(true);
            return;
        }
        if (getWonHappinessByGrowLevel().isZeroOrLt()) {
            setError(true);
            return;
        }
        if (getMaxLevel() < 0) {
            setError(true);
            return;
        }
        if (getMinLevel() < 1) {
            setError(true);
            return;
        }
        if (getMaxLevel() > 1023) {
            setError(true);
            return;
        }
        if (getMinLevel() > getMaxLevel()) {
            setError(true);
            return;
        }
        if (getNbMaxSteps() > 2048) {
            setError(true);
            return;
        }
        if (getNbMaxSteps() <= 0) {
            setError(true);
            return;
        }
        if (getNbMaxStepsSameEvoBase() >= getNbMaxSteps()) {
            setError(true);
            return;
        }
        if (getNbMaxStepsSameEvoBase() <= 0) {
            setError(true);
            return;
        }
        if (getMaxEv() < 0) {
            setError(true);
            return;
        }
        if (getMaxIv() < 31) {
            setError(true);
            return;
        }
        if (getNbNecStepsIncrHappiness() <= 0) {
            setError(true);
            return;
        }
        if (getMaxEv() > 255) {
            setError(true);
            return;
        }
        if (getMaxIv() > 255) {
            setError(true);
            return;
        }
        if (getNbNecStepsIncrHappiness() > 255) {
            setError(true);
            return;
        }
        if (getHappinessMax() < 0) {
            setError(true);
            return;
        }
        if (getHappinessMax() > 255) {
            setError(true);
            return;
        }
        if (getHappinessMax() < getHappinessEvo()) {
            setError(true);
            return;
        }
        if (getMaxBoost() < getDefaultBoost()) {
            setError(true);
            return;
        }
        if (getDefaultBoost() < getMinBoost()) {
            setError(true);
            return;
        }
        if (getDefaultEggGroup().isEmpty()) {
            setError(true);
            return;
        }
        if (!(items.getVal(getDefaultBall()) instanceof Ball)) {
            setError(true);
            return;
        }
        if (!moves.contains(getDefaultMove())) {
            setError(true);
            return;
        }
    }

    public void validateImages() {
        if (!animStatus.containsAllAsKeys(status.getKeys())) {
            setError(true);
            return;
        }
        StringList statisNames_ = new StringList();
        for (Statistic s : Statistic.getStatisticsWithBoost()) {
            statisNames_.add(s.name());
        }
        if (!animStatis.containsAllAsKeys(statisNames_)) {
            setError(true);
            return;
        }
        if (!StringList.equalsSet(types, typesColors.getKeys())) {
            setError(true);
            return;
        }
        if (!StringList.equalsSet(types, typesImages.getKeys())) {
            setError(true);
            return;
        }
        for (String v : typesColors.values()) {
            if (ConverterBufferedImage.getIntColor(v, SEPARATOR_RGB) == -1) {
                setError(true);
                return;
            }
        }
        for (Place p : map.getPlaces().values()) {
            if (!p.hasValidImage(this)) {
                setError(true);
                return;
            }
        }
        for (int[][] i : links.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i.length > map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length > map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : people.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i.length > map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length > map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : trainers.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }

        }
        for (Direction d : Direction.values()) {
            for (Sex s : Sex.values()) {
                ImageHeroKey key_;
                key_ = new ImageHeroKey(EnvironmentType.ROAD, d, s);
                if (!overWorldHeros.contains(key_)) {
                    setError(true);
                    return;
                }
            }
        }
        for (int[][] i : overWorldHeros.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i.length > map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length > map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (Sex s : Sex.values()) {
            ImageHeroKey key_;
            key_ = new ImageHeroKey(EnvironmentType.ROAD, s);
            if (!frontHeros.contains(key_)) {
                setError(true);
                return;
            }
        }
        for (Sex s : Sex.values()) {
            ImageHeroKey key_;
            key_ = new ImageHeroKey(EnvironmentType.ROAD, s);
            if (!backHeros.contains(key_)) {
                setError(true);
                return;
            }
        }
        for (int[][] i : frontHeros.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }

        }
        for (int[][] i : backHeros.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }

        }
        for (int[][] i : maxiPkBack.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i[0].length > maxWidthPk) {
                maxWidthPk = i[0].length;
            }
            if (i.length > maxHeightPk) {
                maxHeightPk = i.length;
            }

        }
        for (int[][] i : maxiPkFront.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i[0].length > maxWidthPk) {
                maxWidthPk = i[0].length;
            }
            if (i.length > maxHeightPk) {
                maxHeightPk = i.length;
            }

        }
        for (int[][] i : typesImages.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }

        }
        for (int[][] i : miniItems.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i.length > map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length > map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : miniMap.values()) {
            if (i.length != map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length != map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : animStatis.values()) {
            if (i.length != map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length != map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : animStatus.values()) {
            if (i.length != map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length != map.getSideLength()) {
                setError(true);
                return;
            }
        }
        if (animAbsorb.length != map.getSideLength()) {
            setError(true);
            return;
        }
        if (animAbsorb[0].length != map.getSideLength()) {
            setError(true);
            return;
        }
        for (int[][] i : miniPk.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i.length > map.getSideLength()) {
                setError(true);
                return;
            }
            if (i[0].length > map.getSideLength()) {
                setError(true);
                return;
            }
        }
        if (imageTmHm.length == 0) {
            setError(true);
            return;
        }
        if (imageTmHm.length > map.getSideLength()) {
            setError(true);
            return;
        }
        if (imageTmHm[0].length > map.getSideLength()) {
            setError(true);
            return;
        }
        if (storage.length == 0) {
            setError(true);
            return;
        }
        if (storage.length > map.getSideLength()) {
            setError(true);
            return;
        }
        if (storage[0].length > map.getSideLength()) {
            setError(true);
            return;
        }
        if (!miniPk.containsAllAsKeys(pokedex.getKeys())) {
            setError(true);
            return;
        }
        if (!miniItems.containsAllAsKeys(items.getKeys())) {
            setError(true);
            return;
        }
        if (!maxiPkBack.containsAllAsKeys(pokedex.getKeys())) {
            setError(true);
            return;
        }
        if (!maxiPkFront.containsAllAsKeys(pokedex.getKeys())) {
            setError(true);
            return;
        }
        if (endGameImage.length == 0) {
            setError(true);
            return;
        }
        for (TileMiniMap t : map.getMiniMap().values()) {
            if (!miniMap.contains(t.getFile())) {
                setError(true);
                return;
            }
        }
        int side_ = map.getSideLength();
        for (EntryCust<String, int[][]> i : images.entryList()) {
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
            imagesTiles.put(name_, tiles_);
        }
    }

    public void validateTranslations() {
        StringList allCustKeys_ = new StringList();
        StringList allStandardKeys_ = new StringList();
        if (!StringList.equalsSet(translatedGenders.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        StringList homonyms_ = new StringList();
        StringList distinct_ = new StringList();
        for (EnumMap<Gender, String> v : translatedGenders.values()) {
            for (Gender g : v.getKeys()) {
                allStandardKeys_.add(g.name());
            }
            if (!Gender.equalsSet(v.getKeys(),
                    new EnumList<Gender>(Gender.values()))) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (Gender g : Gender.values()) {
            if (!distinct_.containsObj(g.name())) {
                distinct_.add(g.name());
            } else {
                homonyms_.add(g.name());
            }
        }
        if (!StringList.equalsSet(translatedBooleans.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        for (EnumMap<SelectedBoolean, String> v : translatedBooleans.values()) {
            for (SelectedBoolean g : v.getKeys()) {
                allStandardKeys_.add(g.name());
            }
            if (!SelectedBoolean.equalsSet(v.getKeys(),
                    new EnumList<SelectedBoolean>(SelectedBoolean.values()))) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        if (!StringList.equalsSet(translatedDiffWinPts.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        for (EnumMap<DifficultyWinPointsFight, String> v : translatedDiffWinPts
                .values()) {
            for (DifficultyWinPointsFight g : v.getKeys()) {
                allStandardKeys_.add(g.name());
            }
            if (!DifficultyWinPointsFight.equalsSet(v.getKeys(),
                    new EnumList<DifficultyWinPointsFight>(
                            DifficultyWinPointsFight.values()))) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        if (!StringList.equalsSet(translatedDiffModelLaw.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        for (EnumMap<DifficultyModelLaw, String> v : translatedDiffModelLaw
                .values()) {
            for (DifficultyModelLaw g : v.getKeys()) {
                allStandardKeys_.add(g.name());
            }
            if (!DifficultyModelLaw.equalsSet(
                    v.getKeys(),
                    new EnumList<DifficultyModelLaw>(DifficultyModelLaw
                            .values()))) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        if (!StringList.equalsSet(translatedEnvironment.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        for (EnumMap<EnvironmentType, String> v : translatedEnvironment
                .values()) {
            for (EnvironmentType g : v.getKeys()) {
                allStandardKeys_.add(g.name());
            }
            if (!EnvironmentType.equalsSet(v.getKeys(),
                    new EnumList<EnvironmentType>(EnvironmentType.values()))) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (EnvironmentType g : EnvironmentType.values()) {
            if (!distinct_.containsObj(g.name())) {
                distinct_.add(g.name());
            } else {
                homonyms_.add(g.name());
            }
        }
        if (!StringList.equalsSet(translatedStatistics.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        for (EnumMap<Statistic, String> v : translatedStatistics.values()) {
            for (Statistic g : v.getKeys()) {
                allStandardKeys_.add(g.name());
            }
            if (!Statistic.equalsSet(v.getKeys(), new EnumList<Statistic>(
                    Statistic.values()))) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (Statistic g : Statistic.values()) {
            if (!distinct_.containsObj(g.name())) {
                distinct_.add(g.name());
            } else {
                homonyms_.add(g.name());
            }
        }
        if (!StringList.equalsSet(translatedTypes.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        allCustKeys_.addAllElts(types);
        for (StringMap<String> v : translatedTypes.values()) {
            if (!StringList.equalsSet(v.getKeys(), types)) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String g : types) {
            if (!distinct_.containsObj(g)) {
                distinct_.add(g);
            } else {
                homonyms_.add(g);
            }
        }
        if (!StringList.equalsSet(translatedCategories.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        allCustKeys_.addAllElts(allCategories);
        for (StringMap<String> v : translatedCategories.values()) {
            if (!StringList.equalsSet(v.getKeys(), allCategories)) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String g : allCategories) {
            if (!distinct_.containsObj(g)) {
                distinct_.add(g);
            } else {
                homonyms_.add(g);
            }
        }
        if (!StringList.equalsSet(translatedPokemon.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        allCustKeys_.addAllElts(pokedex.getKeys());
        for (StringMap<String> v : translatedPokemon.values()) {
            if (!StringList.equalsSet(v.getKeys(), pokedex.getKeys())) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String g : pokedex.getKeys()) {
            if (!distinct_.containsObj(g)) {
                distinct_.add(g);
            } else {
                homonyms_.add(g);
            }
        }
        if (!StringList.equalsSet(translatedItems.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        allCustKeys_.addAllElts(items.getKeys());
        for (StringMap<String> v : translatedItems.values()) {
            if (!StringList.equalsSet(v.getKeys(), items.getKeys())) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String g : items.getKeys()) {
            if (!distinct_.containsObj(g)) {
                distinct_.add(g);
            } else {
                homonyms_.add(g);
            }
        }
        if (!StringList.equalsSet(translatedAbilities.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        allCustKeys_.addAllElts(abilities.getKeys());
        for (StringMap<String> v : translatedAbilities.values()) {
            if (!StringList.equalsSet(v.getKeys(), abilities.getKeys())) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String g : abilities.getKeys()) {
            if (!distinct_.containsObj(g)) {
                distinct_.add(g);
            } else {
                homonyms_.add(g);
            }
        }
        if (!StringList.equalsSet(translatedMoves.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        allCustKeys_.addAllElts(moves.getKeys());
        for (StringMap<String> v : translatedMoves.values()) {
            if (!StringList.equalsSet(v.getKeys(), moves.getKeys())) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String g : moves.getKeys()) {
            if (!distinct_.containsObj(g)) {
                distinct_.add(g);
            } else {
                homonyms_.add(g);
            }
        }
        if (!StringList.equalsSet(translatedStatus.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        allCustKeys_.addAllElts(status.getKeys());
        for (StringMap<String> v : translatedStatus.values()) {
            if (!StringList.equalsSet(v.getKeys(), status.getKeys())) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String g : status.getKeys()) {
            if (!distinct_.containsObj(g)) {
                distinct_.add(g);
            } else {
                homonyms_.add(g);
            }
        }
        homonyms_.removeDuplicates();
        if (!StringList.equalsSet(translatedFctMath.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        for (StringMap<String> v : translatedFctMath.values()) {
            if (!v.containsAllAsKeys(EvolvedMathFactory.getFunctions())) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        for (String l : Constants.getAvailableLanguages()) {
            for (String s : homonyms_) {
                StringList tr_ = new StringList();
                if (translatedMoves.getVal(l).contains(s)) {
                    tr_.add(translatedMoves.getVal(l).getVal(s));
                }
                if (translatedTypes.getVal(l).contains(s)) {
                    tr_.add(translatedTypes.getVal(l).getVal(s));
                }
                if (translatedAbilities.getVal(l).contains(s)) {
                    tr_.add(translatedAbilities.getVal(l).getVal(s));
                }
                if (translatedPokemon.getVal(l).contains(s)) {
                    tr_.add(translatedPokemon.getVal(l).getVal(s));
                }
                if (translatedItems.getVal(l).contains(s)) {
                    tr_.add(translatedItems.getVal(l).getVal(s));
                }
                if (translatedStatus.getVal(l).contains(s)) {
                    tr_.add(translatedStatus.getVal(l).getVal(s));
                }
                if (translatedCategories.getVal(l).contains(s)) {
                    tr_.add(translatedCategories.getVal(l).getVal(s));
                }
                for (Statistic s_ : Statistic.values()) {
                    if (StringList.quickEq(s, s_.name())) {
                        if (translatedStatistics.getVal(l).contains(s_)) {
                            tr_.add(translatedStatistics.getVal(l).getVal(s_));
                            break;
                        }
                    }
                }
                for (Gender g : Gender.values()) {
                    if (StringList.quickEq(s, g.name())) {
                        if (translatedGenders.getVal(l).contains(g)) {
                            tr_.add(translatedGenders.getVal(l).getVal(g));
                            break;
                        }
                    }
                }
                tr_.removeDuplicates();
                if (tr_.size() > DataBase.ONE_POSSIBLE_CHOICE) {
                    setError(true);
                    return;
                }
            }
        }

        if (!StringList.equalsSet(translatedClassesDescriptions.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        StringList classesItems_;
        classesItems_ = new StringList();
        for (Item i : items.values()) {
            classesItems_.add(i.getItemType());
        }
        classesItems_.removeDuplicates();
        for (StringMap<String> v : translatedClassesDescriptions.values()) {
            if (!v.containsAllAsKeys(classesItems_)) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        if (!StringList.equalsSet(translatedTargets.getKeys(),
                Constants.getAvailableLanguages())) {
            setError(true);
            return;
        }
        for (EnumMap<TargetChoice, String> v : translatedTargets.values()) {
            for (TargetChoice g : v.getKeys()) {
                allStandardKeys_.add(g.name());
            }
            if (!TargetChoice.equalsSet(v.getKeys(),
                    new EnumList<TargetChoice>(TargetChoice.values()))) {
                setError(true);
                return;
            }
            if (hasDuplicates(v.values())) {
                setError(true);
                return;
            }
        }
        int nbCustKeys_ = allCustKeys_.size();
        allCustKeys_.removeDuplicates();
        if (nbCustKeys_ != allCustKeys_.size()) {
            setError(true);
            return;
        }
        for (String n : allStandardKeys_) {
            if (allCustKeys_.containsObj(n)) {
                setError(true);
                return;
            }
        }
    }

    private static boolean hasDuplicates(Listable<String> _list) {
        StringList l_ = new StringList(_list);
        int size_ = l_.size();
        l_.removeDuplicates();
        return l_.size() != size_;
    }

    public void initTypesByTable() {
        StringList moveTypes_ = new StringList();
        for (TypesDuo t : tableTypes.getKeys()) {

            if (!isCorrectIdentifier(t.getDamageType())) {
                setError(true);
            }
            moveTypes_.add(t.getDamageType());
        }
        moveTypes_.removeDuplicates();
        StringList pkTypes_ = new StringList();
        for (TypesDuo t : tableTypes.getKeys()) {

            if (!isCorrectIdentifier(t.getPokemonType())) {
                setError(true);
            }
            pkTypes_.add(t.getPokemonType());
        }
        pkTypes_.removeDuplicates();
        if (!StringList.equalsSet(moveTypes_, pkTypes_)) {
            setError(true);
        }
        types = moveTypes_;
    }

    public void validateForEditing() {
        imagesDimensions.clear();
        initTypesByTableForEditing();
        for (String t1_ : types) {
            for (String t2_ : types) {
                if (!tableTypes.contains(new TypesDuo(t1_, t2_))) {
                    tableTypes.put(new TypesDuo(t1_, t2_), Rate.one());
                }
            }
        }
        for (EntryCust<String, PokemonData> e : getPokedex().entryList()) {
            e.getValue().validateForEditing();
        }
        for (EntryCust<String, Status> e : getStatus().entryList()) {
            e.getValue().validateForEditing();
        }
        map.validateForEditing(this);
        for (int[][] i : links.values()) {
            if (i.length != map.getSideLength()
                    || i[0].length != map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : people.values()) {
            if (i.length != map.getSideLength()
                    || i[0].length != map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : trainers.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
        }
        for (int[][] i : maxiPkBack.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
        }
        for (int[][] i : maxiPkFront.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
        }
        for (int[][] i : miniItems.values()) {
            if (i.length != map.getSideLength()
                    || i[0].length != map.getSideLength()) {
                setError(true);
                return;
            }
        }
        for (int[][] i : miniPk.values()) {
            if (i.length != map.getSideLength()
                    || i[0].length != map.getSideLength()) {
                setError(true);
                return;
            }
        }
        if (imageTmHm.length != map.getSideLength()
                || imageTmHm[0].length != map.getSideLength()) {
            setError(true);
            return;
        }
        if (storage.length != map.getSideLength()
                || storage[0].length != map.getSideLength()) {
            setError(true);
            return;
        }
        if (!StringList.equalsSet(miniPk.getKeys(), pokedex.getKeys())) {
            setError(true);
            return;
        }
        if (!StringList.equalsSet(miniItems.getKeys(), items.getKeys())) {
            setError(true);
            return;
        }
        if (endGameImage.length == 0) {
            setError(true);
            return;
        }
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

    public void initTypesByTableForEditing() {
        StringList moveTypes_ = new StringList();
        for (TypesDuo t : tableTypes.getKeys()) {
            moveTypes_.add(t.getDamageType());
        }
        moveTypes_.removeDuplicates();
        StringList pkTypes_ = new StringList();
        for (TypesDuo t : tableTypes.getKeys()) {
            pkTypes_.add(t.getPokemonType());
        }
        pkTypes_.removeDuplicates();
        if (!StringList.equalsSet(moveTypes_, pkTypes_)) {
            setError(true);
            return;
        }
        types = moveTypes_;
    }

    public void loadRom(StringMap<String> _files, AtomicInteger _perCentLoading) {

        _perCentLoading.set(0);
        initializeMembers();
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
            listCopy_.add(toUpperCase(s));
        }
        int sizeListCopy_ = listCopy_.size();
        listCopy_.removeDuplicates();
        if (!Numbers.eq(listCopy_.size(), sizeListCopy_)) {
            setError(true);
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
                setError(true);
            }
            filesNames_.add(n_);
            PokemonData f_ = DocumentReaderAikiCoreUtil.getPokemonData(files_
                    .getVal(StringList.concat(common_, f)));
            completeMembers(toUpperCase(n_), f_);
        }
        calculateAvgPound();
        filesNames_.clear();

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(MOVES_FOLDER, SEPARATOR_FILES))) {

            String n_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            if (n_.isEmpty()) {
                continue;
            }
            n_ = removeExtension(n_);
            if (!isCorrectIdentifier(n_)) {
                setError(true);
            }
            filesNames_.add(n_);
            MoveData move_ = DocumentReaderAikiCoreUtil.getMoveData(files_
                    .getVal(StringList.concat(common_, f)));
            completeMembers(toUpperCase(n_), move_);
        }
        _perCentLoading.set(10);
        StringList tmHm_ = StringList.splitChars(
                files_.getVal(StringList.concat(common_, CT_CS_FILE)),
                RETURN_LINE_CHAR);
        for (String l : tmHm_) {
            if (l.startsWith(CT)) {
                StringList infos_ = StringList.splitChars(l, TAB_CHAR);
                short cle_ = Short.parseShort(infos_.first().substring(2));
                tm.put(cle_, infos_.get(1));
                LgInt price_;
                if (LgInt.isValid(infos_.get(2))) {
                    price_ = new LgInt(infos_.get(2));
                } else {
                    price_ = new LgInt(1000);
                }
                tmPrice.put(cle_, price_);

            }
            if (l.startsWith(CS)) {
                StringList infos_ = StringList.splitChars(l, TAB_CHAR);
                short cle_ = Short.parseShort(infos_.first().substring(2));
                hm.put(cle_, infos_.get(1));
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
                setError(true);
            }
            filesNames_.add(n_);
            Item o_ = DocumentReaderAikiCoreUtil.getItem(files_
                    .getVal(StringList.concat(common_, f)));
            completeMembers(toUpperCase(n_), o_);
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
                setError(true);
            }
            filesNames_.add(n_);
            AbilityData ab_ = DocumentReaderAikiCoreUtil.getAbilityData(files_
                    .getVal(StringList.concat(common_, f)));
            completeMembers(toUpperCase(n_), ab_);
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
                setError(true);
            }
            filesNames_.add(n_);
            Status st_ = DocumentReaderAikiCoreUtil.getStatus(files_
                    .getVal(StringList.concat(common_, f)));
            completeMembers(toUpperCase(n_), st_);
        }
        _perCentLoading.set(15);
        completeVariables();
        filesNames_.clear();
        images = new StringMap<int[][]>();
        imagesTiles = new StringMap<ObjectMap<ScreenCoords, int[][]>>();
        StringList images_;

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            images.put(key_, BaseSixtyFourUtil.getImageByString(files_
                    .getVal(StringList.concat(common_, s))));
        }
        filesNames_.clear();

        miniMap = new StringMap<int[][]>();
        StringList miniMap_;

        miniMap_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(MINI_MAP_FOLDER, SEPARATOR_FILES));
        for (String s : miniMap_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            miniMap.put(key_, BaseSixtyFourUtil.getImageByString(files_
                    .getVal(StringList.concat(common_, s))));
        }

        filesNames_.clear();
        links = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(LINKS_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            links.put(key_, BaseSixtyFourUtil.getImageByString(files_
                    .getVal(StringList.concat(common_, s))));
        }
        filesNames_.clear();
        people = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(PEOPLE_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            people.put(key_, BaseSixtyFourUtil.getImageByString(files_
                    .getVal(StringList.concat(common_, s))));
        }
        filesNames_.clear();
        frontHeros = new ObjectMap<ImageHeroKey, int[][]>();
        for (String l : StringList.splitChars(files_.getVal(StringList.concat(
                common_, HERO_FOLDER, SEPARATOR_FILES, HERO_FRONT)),
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
            frontHeros.put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        backHeros = new ObjectMap<ImageHeroKey, int[][]>();
        for (String l : StringList.splitChars(files_.getVal(StringList.concat(
                common_, HERO_FOLDER, SEPARATOR_FILES, HERO_BACK)),
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
            backHeros.put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        overWorldHeros = new ObjectMap<ImageHeroKey, int[][]>();
        for (String l : StringList.splitChars(files_.getVal(StringList.concat(
                common_, HERO_FOLDER, SEPARATOR_FILES, HERO_MINI)),
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
            overWorldHeros.put(new ImageHeroKey(env_, dir_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(HERO_FOLDER, SEPARATOR_FILES));
        filesNames_.clear();
        trainers = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(TRAINERS_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            filesNames_.add(s);

            String key_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            trainers.put(key_, BaseSixtyFourUtil.getImageByString(files_
                    .getVal(StringList.concat(common_, s))));
        }
        filesNames_.clear();
        maxiPkBack = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(BACK_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            maxiPkBack.put(toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(files_.getVal(StringList.concat(common_,
                            s))));
        }
        filesNames_.clear();
        maxiPkFront = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(FRONT_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            maxiPkFront.put(toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(files_.getVal(StringList.concat(common_,
                            s))));
        }
        filesNames_.clear();
        miniPk = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(MINI_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            miniPk.put(toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(files_.getVal(StringList.concat(common_,
                            s))));
        }
        _perCentLoading.set(25);
        filesNames_.clear();
        miniItems = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(OBJECTS_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            if (!s.endsWith(IMG_FILES_RES_EXT_TXT)) {
                continue;
            }

            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);
            n_ = removeExtension(n_);
            filesNames_.add(n_);
            miniItems.put(toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(files_.getVal(StringList.concat(common_,
                            s))));
        }

        filesNames_.clear();
        typesImages = new StringMap<int[][]>();

        images_ = filterStrictBeginIgnoreCase(listRelativePaths_,StringList
                .concat(TYPES_IMAGES_FOLDER, SEPARATOR_FILES));
        for (String s : images_) {
            if (!s.endsWith(IMG_FILES_RES_EXT_TXT)) {
                continue;
            }
            String n_ = StringList.skipStringUntil(s, SEPARATOR_FILES);

            n_ = removeExtension(n_);
            filesNames_.add(n_);
            typesImages.put(toUpperCase(n_), BaseSixtyFourUtil
                    .getImageByString(files_.getVal(StringList.concat(common_,
                            s))));
        }

        imageTmHm = BaseSixtyFourUtil.getImageByString(files_.getVal(StringList
                .concat(common_, IMAGE_TM_HM_FILES, IMG_FILES_RES_EXT_TXT)));
        storage = BaseSixtyFourUtil.getImageByString(files_.getVal(StringList
                .concat(common_, IMAGE_STORAGE_FILES, IMG_FILES_RES_EXT_TXT)));
        combos = DocumentReaderAikiCoreUtil.getCombos(files_.getVal(StringList
                .concat(common_, COMBOS)));
        completeMembersCombos();
        sortEndRound();
        map = DocumentReaderAikiCoreUtil.getDataMap(files_.getVal(StringList
                .concat(common_, MAP_FILE)));
        constNum = new StringMap<Rate>();
        StringList linesNum_ = StringList.splitChars(
                files_.getVal(StringList.concat(common_, CONST_NUM)),
                RETURN_LINE_CHAR);
        for (String l : linesNum_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            constNum.put(infos_.first(), new Rate(infos_.last()));
        }

        StringList linesNotNum_ = StringList.splitChars(
                files_.getVal(StringList.concat(common_, CONST_NOT_NUM)),
                RETURN_LINE_CHAR);
        for (String l : linesNotNum_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            if (StringList.quickEq(infos_.first(), DEF_MOVE)) {
                defMove = infos_.last();
            } else if (StringList.quickEq(infos_.first(), RATE_BOOST)) {
                rateBoost = infos_.last();
            } else if (StringList.quickEq(infos_.first(),
                    RATE_BOOST_CRITICAL_HIT)) {
                rateBoostCriticalHit = infos_.last();
            } else if (StringList.quickEq(infos_.first(), RATE_FLEEING)) {
                rateFleeing = infos_.last();
            } else if (StringList.quickEq(infos_.first(), RATE_CATCHING)) {
                rateCatching = infos_.last();
            } else if (StringList.quickEq(infos_.first(), BALL_DEF)) {
                ballDef = infos_.last();
            } else if (StringList.quickEq(infos_.first(), DEFAULT_EGG_GROUP)) {
                defaultEggGoup = infos_.last();
            } else if (StringList.quickEq(infos_.first(), DAMAGE_FORMULA)) {
                damageFormula = infos_.last();
            }

        }
        tableTypes = new ObjectMap<TypesDuo, Rate>();
        StringList linesTableTypes_ = StringList.splitChars(
                files_.getVal(StringList.concat(common_, TABLE_TYPES)),
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
                    r_ = defRateProduct();
                }
                tableTypes.put(t_, r_);

                i_++;
            }
        }
        lawsDamageRate = new EnumMap<DifficultyModelLaw, LawNumber>();
        StringList laws_ = StringList.splitChars(
                files_.getVal(StringList.concat(common_, LOIS_RANDOM)),
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
                    law_.addEvent(new Rate(1), defElementaryEvent());

                    break;
                }

                law_.addEvent(new Rate(infosLoc_.first()),
                        new LgInt(infosLoc_.get(1)));

            }

            if (!law_.checkEvents()) {
                setError(true);
                return;
            }
            lawsDamageRate.put(
                    DifficultyModelLaw.getModelByName(infos_.first()),
                    new LawNumber(law_, Short.parseShort(infos_.last())));
        }
        expGrowth = new EnumMap<ExpType, String>();
        StringList courbes_ = StringList.splitChars(
                files_.getVal(StringList.concat(common_, COURBE_PTS_EXP)),
                RETURN_LINE_CHAR);
        for (String l : courbes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            expGrowth.put(ExpType.getExpTypeByName(infos_.first()),
                    infos_.get(1));
        }
        rates = new EnumMap<DifficultyWinPointsFight, String>();
        StringList rates_ = StringList.splitChars(
                files_.getVal(StringList.concat(common_, RATE_WON_POINTS)),
                RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            rates.put(DifficultyWinPointsFight.getDiffWonPtsByName(infos_
                    .first()), infos_.get(1));
        }
        typesColors = new StringMap<String>();
        StringList colorTypes_ = StringList.splitChars(files_.getVal(StringList
                .concat(common_, TYPES_COLOR_CODE, IMG_FILES_RES_EXT_TXT)),
                RETURN_LINE_CHAR);
        for (String l : colorTypes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            String colorStr_ = infos_.get(1);
            typesColors.put(infos_.first(), colorStr_);
        }
        endGameImage = BaseSixtyFourUtil.getImageByString(files_
                .getVal(StringList.concat(common_, END_GAME_IMAGE,
                        IMG_FILES_RES_EXT_TXT)));
        translatedBooleans = new StringMap<EnumMap<SelectedBoolean, String>>();
        translatedDiffWinPts = new StringMap<EnumMap<DifficultyWinPointsFight, String>>();
        translatedDiffModelLaw = new StringMap<EnumMap<DifficultyModelLaw, String>>();
        translatedGenders = new StringMap<EnumMap<Gender, String>>();
        translatedEnvironment = new StringMap<EnumMap<EnvironmentType, String>>();
        translatedStatistics = new StringMap<EnumMap<Statistic, String>>();
        translatedPokemon = new StringMap<StringMap<String>>();
        translatedMoves = new StringMap<StringMap<String>>();
        translatedItems = new StringMap<StringMap<String>>();
        translatedStatus = new StringMap<StringMap<String>>();
        translatedAbilities = new StringMap<StringMap<String>>();
        translatedCategories = new StringMap<StringMap<String>>();
        translatedTypes = new StringMap<StringMap<String>>();
        translatedFctMath = new StringMap<StringMap<String>>();
        translatedTargets = new StringMap<EnumMap<TargetChoice, String>>();
        translatedClassesDescriptions = new StringMap<StringMap<String>>();
        litterals = new StringMap<StringMap<String>>();
        _perCentLoading.set(30);
        for (String l : Constants.getAvailableLanguages()) {
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_GENDERS);
            EnumMap<Gender, String> genders_ = new EnumMap<Gender, String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                genders_.put(Gender.getGenderByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedGenders.put(l, genders_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_BOOLEANS);
            EnumMap<SelectedBoolean, String> booleans_ = new EnumMap<SelectedBoolean, String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                booleans_.put(SelectedBoolean.getBoolByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedBooleans.put(l, booleans_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_DIFF_WIN_PTS);
            EnumMap<DifficultyWinPointsFight, String> diffWinPts_ = new EnumMap<DifficultyWinPointsFight, String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                diffWinPts_.put(DifficultyWinPointsFight
                        .getDiffWonPtsByName(infos_.first()), DocumentBuilder
                        .transformSpecialChars(infos_.last()));
            }
            translatedDiffWinPts.put(l, diffWinPts_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList
                    .concat(fileName_, TRANSLATION_DIFF_MODEL_LAW);
            EnumMap<DifficultyModelLaw, String> diffLaw_ = new EnumMap<DifficultyModelLaw, String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                diffLaw_.put(DifficultyModelLaw.getModelByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedDiffModelLaw.put(l, diffLaw_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ENVIRONMENTS);
            EnumMap<EnvironmentType, String> environments_ = new EnumMap<EnvironmentType, String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                environments_.put(EnvironmentType.getEnvByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedEnvironment.put(l, environments_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATISTICS);
            EnumMap<Statistic, String> statistics_ = new EnumMap<Statistic, String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                statistics_.put(Statistic.getStatisticByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedStatistics.put(l, statistics_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TARGETS);
            EnumMap<TargetChoice, String> targets_ = new EnumMap<TargetChoice, String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                targets_.put(
                        TargetChoice.getTargetChoiceByName(infos_.first()),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedTargets.put(l, targets_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CATEGORIES);
            StringMap<String> categories_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                categories_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedCategories.put(l, categories_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TYPES);
            StringMap<String> types_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                types_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedTypes.put(l, types_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_POKEMON);
            StringMap<String> pokemon_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                pokemon_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedPokemon.put(l, pokemon_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MOVES);
            StringMap<String> moves_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                moves_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedMoves.put(l, moves_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ITEMS);
            StringMap<String> items_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                items_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedItems.put(l, items_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ABILITIES);
            StringMap<String> abilities_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                abilities_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedAbilities.put(l, abilities_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATUS);
            StringMap<String> status_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                status_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedStatus.put(l, status_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MATH);
            StringMap<String> fctsMath_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                fctsMath_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedFctMath.put(l, fctsMath_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CLASSES);
            StringMap<String> descrClasses_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
                    RETURN_LINE_CHAR)) {
                if (l2_.isEmpty()) {
                    continue;
                }
                StringList infos_ = StringList.splitChars(l2_, TAB_CHAR);
                descrClasses_.put(infos_.first(),
                        DocumentBuilder.transformSpecialChars(infos_.last()));
            }
            translatedClassesDescriptions.put(l, descrClasses_);
            fileName_ = StringList.concat(TRANSLATION_FOLDER, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_LITTERAL);
            StringMap<String> litteral_ = new StringMap<String>();
            for (String l2_ : StringList.splitChars(
                    files_.getVal(StringList.concat(common_, fileName_)),
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
            litterals.put(l, litteral_);
        }
        _perCentLoading.set(35);

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(ANIM_STATIS, SEPARATOR_FILES))) {

            String f_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            f_ = removeExtension(f_);
            if (f_.isEmpty()) {
                continue;
            }
            animStatis.put(toUpperCase(f_), BaseSixtyFourUtil
                    .getImageByString(files_.getVal(StringList.concat(common_,
                            f))));
        }

        for (String f : filterBeginIgnoreCase(listRelativePaths_,StringList
                .concat(ANIM_STATUS, SEPARATOR_FILES))) {

            String f_ = StringList.skipStringUntil(f, SEPARATOR_FILES);
            f_ = removeExtension(f_);
            if (f_.isEmpty()) {
                continue;
            }
            animStatus.put(toUpperCase(f_), BaseSixtyFourUtil
                    .getImageByString(files_.getVal(StringList.concat(common_,
                            f))));
        }
        animAbsorb = BaseSixtyFourUtil.getImageByString(files_
                .getVal(StringList.concat(common_, ANIM_ABSORB)));
        _perCentLoading.set(40);
    }

    public void setLanguage(String _language) {
        language = _language;
    }

    public void loadResources(AtomicInteger _perCentLoading, String _lg) {
        int delta_ = (100 - _perCentLoading.get()) / 6;
        imagesDimensions.clear();

        initializeMembers();
        String common_ = Resources.ACCESS_TO_DEFAULT_FILES;
        StringList tmHm_ = StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, CT_CS_FILE)),
                RETURN_LINE_CHAR);
        for (String l : tmHm_) {
            if (l.startsWith(CT)) {
                StringList infos_ = StringList.splitChars(l, TAB_CHAR);
                short cle_ = Short.parseShort(infos_.first().substring(2));
                tm.put(cle_, infos_.get(1));
                LgInt price_;
                if (LgInt.isValid(infos_.get(2))) {
                    price_ = new LgInt(infos_.get(2));
                } else {
                    price_ = new LgInt(1000);
                }
                tmPrice.put(cle_, price_);

            }
            if (l.startsWith(CS)) {
                StringList infos_ = StringList.splitChars(l, TAB_CHAR);
                short cle_ = Short.parseShort(infos_.first().substring(2));
                hm.put(cle_, infos_.get(1));
            }
        }
        frontHeros = new ObjectMap<ImageHeroKey, int[][]>();
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
            frontHeros.put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        backHeros = new ObjectMap<ImageHeroKey, int[][]>();
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
            backHeros.put(new ImageHeroKey(env_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        overWorldHeros = new ObjectMap<ImageHeroKey, int[][]>();
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
            overWorldHeros.put(new ImageHeroKey(env_, dir_, sex_),
                    BaseSixtyFourUtil.getImageByString(infos_.last()));
        }
        imageTmHm = BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_, IMAGE_TM_HM_FILES,
                        IMG_FILES_RES_EXT_TXT)));
        storage = BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_,
                        IMAGE_STORAGE_FILES, IMG_FILES_RES_EXT_TXT)));
        combos = DocumentReaderAikiCoreUtil.getCombos(ResourceFiles
                .ressourceFichier(StringList.concat(common_, COMBOS)));
        completeMembersCombos();
        map = DocumentReaderAikiCoreUtil.getDataMap(ResourceFiles
                .ressourceFichier(StringList.concat(common_, MAP_FILE)));
        _perCentLoading.addAndGet(delta_);
        constNum = new StringMap<Rate>();
        StringList lines_ = StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, CONST_NUM)),
                RETURN_LINE_CHAR);
        for (String l : lines_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            constNum.put(infos_.first(), new Rate(infos_.last()));
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
                defMove = infos_.last();
            } else if (StringList.quickEq(infos_.first(), RATE_BOOST)) {
                rateBoost = infos_.last();
            } else if (StringList.quickEq(infos_.first(),
                    RATE_BOOST_CRITICAL_HIT)) {
                rateBoostCriticalHit = infos_.last();
            } else if (StringList.quickEq(infos_.first(), RATE_FLEEING)) {
                rateFleeing = infos_.last();
            } else if (StringList.quickEq(infos_.first(), RATE_CATCHING)) {
                rateCatching = infos_.last();
            } else if (StringList.quickEq(infos_.first(), BALL_DEF)) {
                ballDef = infos_.last();
            } else if (StringList.quickEq(infos_.first(), DEFAULT_EGG_GROUP)) {
                defaultEggGoup = infos_.last();
            } else if (StringList.quickEq(infos_.first(), DAMAGE_FORMULA)) {
                damageFormula = infos_.last();
            }

        }
        tableTypes = new ObjectMap<TypesDuo, Rate>();
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
                    r_ = defRateProduct();
                }
                tableTypes.put(t_, r_);

                i_++;
            }
        }
        initTypesByTable();
        lawsDamageRate = new EnumMap<DifficultyModelLaw, LawNumber>();
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

                    law_.addEvent(new Rate(1), defElementaryEvent());
                    break;
                }

                law_.addEvent(new Rate(infosLoc_.first()),
                        new LgInt(infosLoc_.get(1)));

            }

            if (!law_.checkEvents()) {
                setError(true);
                return;
            }
            lawsDamageRate.put(
                    DifficultyModelLaw.getModelByName(infos_.first()),
                    new LawNumber(law_, Short.parseShort(infos_.last())));
        }
        expGrowth = new EnumMap<ExpType, String>();
        StringList courbes_ = StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, COURBE_PTS_EXP)),
                RETURN_LINE_CHAR);
        for (String l : courbes_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            expGrowth.put(ExpType.getExpTypeByName(infos_.first()),
                    infos_.get(1));
        }
        rates = new EnumMap<DifficultyWinPointsFight, String>();
        StringList rates_ = StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, RATE_WON_POINTS)),
                RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            rates.put(DifficultyWinPointsFight.getDiffWonPtsByName(infos_
                    .first()), infos_.get(1));
        }
        typesColors = new StringMap<String>();
        rates_ = StringList.splitChars(ResourceFiles
                .ressourceFichier(StringList.concat(common_, TYPES_COLOR_CODE,
                        IMG_FILES_RES_EXT_TXT)), RETURN_LINE_CHAR);
        for (String l : rates_) {
            if (l.isEmpty()) {
                continue;
            }
            StringList infos_ = StringList.splitChars(l, TAB_CHAR);
            String colorStr_ = infos_.get(1);
            typesColors.put(infos_.first(), colorStr_);
        }
        endGameImage = BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_, END_GAME_IMAGE,
                        IMG_FILES_RES_EXT_TXT)));
        translatedBooleans = new StringMap<EnumMap<SelectedBoolean, String>>();
        translatedDiffWinPts = new StringMap<EnumMap<DifficultyWinPointsFight, String>>();
        translatedDiffModelLaw = new StringMap<EnumMap<DifficultyModelLaw, String>>();
        translatedGenders = new StringMap<EnumMap<Gender, String>>();
        translatedEnvironment = new StringMap<EnumMap<EnvironmentType, String>>();
        translatedStatistics = new StringMap<EnumMap<Statistic, String>>();
        translatedPokemon = new StringMap<StringMap<String>>();
        translatedMoves = new StringMap<StringMap<String>>();
        translatedItems = new StringMap<StringMap<String>>();
        translatedStatus = new StringMap<StringMap<String>>();
        translatedAbilities = new StringMap<StringMap<String>>();
        translatedCategories = new StringMap<StringMap<String>>();
        translatedTypes = new StringMap<StringMap<String>>();
        translatedFctMath = new StringMap<StringMap<String>>();
        translatedTargets = new StringMap<EnumMap<TargetChoice, String>>();
        translatedClassesDescriptions = new StringMap<StringMap<String>>();
        litterals = new StringMap<StringMap<String>>();
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
            translatedGenders.put(l, genders_);
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
            translatedBooleans.put(l, booleans_);
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
            translatedDiffWinPts.put(l, diffWinPts_);
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
            translatedDiffModelLaw.put(l, diffLaw_);
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
            translatedEnvironment.put(l, environments_);
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
            translatedStatistics.put(l, statistics_);
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
            translatedTargets.put(l, targets_);
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
            translatedCategories.put(l, categories_);
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
            translatedTypes.put(l, types_);
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
            translatedPokemon.put(l, pokemon_);
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
            translatedMoves.put(l, moves_);
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
            translatedItems.put(l, items_);
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
            translatedAbilities.put(l, abilities_);
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
            translatedStatus.put(l, status_);
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
            translatedFctMath.put(l, fctsMath_);
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
            translatedClassesDescriptions.put(l, descrClasses_);
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
            litterals.put(l, litteral_);
        }
        _perCentLoading.addAndGet(delta_);
        for (Statistic f : translatedStatistics.getVal(_lg)
                .getKeys()) {
            if (!f.isBoost()) {
                continue;
            }
            String f_ = StringList.concat(ANIM_STATIS, SEPARATOR_FILES,
                    f.name(), IMG_FILES_RES_EXT_TXT);
            animStatis.put(f.name(), BaseSixtyFourUtil
                    .getImageByString(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, f_))));
        }
        for (String f : translatedStatus.getVal(_lg)
                .getKeys()) {
            String f_ = StringList.concat(ANIM_STATUS, SEPARATOR_FILES, f,
                    IMG_FILES_RES_EXT_TXT);
            animStatus.put(toUpperCase(f), BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, f_))));
        }
        animAbsorb = BaseSixtyFourUtil.getImageByString(ResourceFiles
                .ressourceFichier(StringList.concat(common_, ANIM_ABSORB)));
        StringList filesNames_;
        filesNames_ = new StringList();
        for (String f : translatedPokemon.getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(POKEDEX_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(f);
            PokemonData f_ = DocumentReaderAikiCoreUtil
                    .getPokemonData(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, n_)));
            completeMembers(toUpperCase(f), f_);
        }
        calculateAvgPound();
        filesNames_.clear();
        for (String f : translatedMoves.getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(MOVES_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            MoveData move_ = DocumentReaderAikiCoreUtil
                    .getMoveData(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, n_)));
            completeMembers(toUpperCase(f), move_);
        }
        filesNames_.clear();
        for (String f : translatedItems.getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(ITEMS_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            Item o_ = DocumentReaderAikiCoreUtil.getItem(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_)));
            completeMembers(toUpperCase(f), o_);
        }
        filesNames_.clear();
        for (String f : translatedAbilities.getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(ABILITIES_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            AbilityData ab_ = DocumentReaderAikiCoreUtil
                    .getAbilityData(ResourceFiles.ressourceFichier(StringList
                            .concat(common_, n_)));
            completeMembers(toUpperCase(f), ab_);
        }
        filesNames_.clear();
        for (String f : translatedStatus.getVal(_lg)
                .getKeys()) {
            String n_ = StringList.concat(STATUS_FOLDER, SEPARATOR_FILES, f,
                    FILES_RES_EXT);
            filesNames_.add(n_);
            Status st_ = DocumentReaderAikiCoreUtil.getStatus(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_)));
            completeMembers(toUpperCase(f), st_);
        }
        completeVariables();
        filesNames_.clear();
        sortEndRound();
        _perCentLoading.addAndGet(delta_);
        for (PokemonData pk_ : pokedex.values()) {
            for (short hm_ : pk_.getHiddenMoves()) {
                String move_ = hm.getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (short hm_ : pk_.getTechnicalMoves()) {
                String move_ = tm.getVal(hm_);
                pk_.getMoveTutors().add(move_);
            }
            for (LevelMove l : pk_.getLevMoves()) {
                pk_.getMoveTutors().add(l.getMove());
            }
            pk_.getMoveTutors().removeDuplicates();
        }
        maxiPkBack = new StringMap<int[][]>();
        for (String s : pokedex.getKeys()) {
            String n_ = StringList.concat(BACK_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            maxiPkBack.put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        maxiPkFront = new StringMap<int[][]>();
        for (String s : pokedex.getKeys()) {
            String n_ = StringList.concat(FRONT_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            maxiPkFront.put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        miniPk = new StringMap<int[][]>();
        for (String s : pokedex.getKeys()) {
            String n_ = StringList.concat(MINI_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            miniPk.put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        miniItems = new StringMap<int[][]>();
        for (String s : items.getKeys()) {
            String n_ = StringList.concat(OBJECTS_IMAGES_FOLDER,
                    SEPARATOR_FILES, s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            miniItems.put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        filesNames_.clear();
        typesImages = new StringMap<int[][]>();
        for (String s : types) {
            String n_ = StringList.concat(TYPES_IMAGES_FOLDER, SEPARATOR_FILES,
                    s, IMG_FILES_RES_EXT_TXT);
            filesNames_.add(n_);
            typesImages.put(s, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, n_))));
        }
        _perCentLoading.addAndGet(delta_);
        filesNames_.clear();
        map.initializeLinks();
        map.initInteractiveElements();
        map.initializeTree();
        map.initializeAccessibility();
        trainers = new StringMap<int[][]>();
        people = new StringMap<int[][]>();
        images = new StringMap<int[][]>();
        imagesTiles = new StringMap<ObjectMap<ScreenCoords, int[][]>>();
        links = new StringMap<int[][]>();
        miniMap = new StringMap<int[][]>();
        for (Place p : map.getPlaces().values()) {
            if (p instanceof League) {
                League l_ = (League) p;
                for (Level l : l_.getLevelsList()) {
                    LevelLeague lev_ = (LevelLeague) l;
                    String f_ = lev_.getTrainer().getImageMaxiFileName();
                    String file_ = StringList.concat(TRAINERS_FOLDER,
                            SEPARATOR_FILES, f_);
                    trainers.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    f_ = lev_.getTrainer().getImageMiniFileName();
                    file_ = StringList.concat(PEOPLE_FOLDER, SEPARATOR_FILES,
                            f_);
                    people.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    for (Block b_ : l.getBlocks().values()) {
                        f_ = b_.getTileFileName();
                        file_ = StringList.concat(IMAGES_FOLDER,
                                SEPARATOR_FILES, f_);
                        images.put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                    f_ = lev_.getFileName();
                    file_ = StringList
                            .concat(LINKS_FOLDER, SEPARATOR_FILES, f_);
                    links.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                String f_ = l_.getFileName();
                String file_ = StringList.concat(LINKS_FOLDER, SEPARATOR_FILES,
                        f_);
                links.put(f_, BaseSixtyFourUtil.getImageByString(ResourceFiles
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
                            trainers.put(f_, BaseSixtyFourUtil
                                    .getImageByString(ResourceFiles
                                            .ressourceFichier(StringList
                                                    .concat(common_, file_))));
                            f_ = t.getImageMiniFileName();
                            file_ = StringList.concat(PEOPLE_FOLDER,
                                    SEPARATOR_FILES, f_);
                            people.put(f_, BaseSixtyFourUtil
                                    .getImageByString(ResourceFiles
                                            .ressourceFichier(StringList
                                                    .concat(common_, file_))));
                        }
                        String f_ = g_.getLevel().getGymLeader()
                                .getImageMaxiFileName();
                        String file_ = StringList.concat(TRAINERS_FOLDER,
                                SEPARATOR_FILES, f_);
                        trainers.put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                        f_ = g_.getLevel().getGymLeader()
                                .getImageMiniFileName();
                        file_ = StringList.concat(PEOPLE_FOLDER,
                                SEPARATOR_FILES, f_);
                        people.put(f_, BaseSixtyFourUtil
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
                            people.put(f_, BaseSixtyFourUtil
                                    .getImageByString(ResourceFiles
                                            .ressourceFichier(StringList
                                                    .concat(common_, file_))));
                        }
                    }
                    for (Block b_ : b.getLevel().getBlocks().values()) {
                        String f_ = b_.getTileFileName();
                        String file_ = StringList.concat(IMAGES_FOLDER,
                                SEPARATOR_FILES, f_);
                        images.put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                    String f_ = b.getImageFileName();
                    String file_ = StringList.concat(LINKS_FOLDER,
                            SEPARATOR_FILES, f_);
                    links.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                for (Block b_ : c_.getLevel().getBlocks().values()) {
                    String f_ = b_.getTileFileName();
                    String file_ = StringList.concat(IMAGES_FOLDER,
                            SEPARATOR_FILES, f_);
                    images.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                for (Link k : c_.getLinksWithCaves().values()) {
                    String f_ = k.getFileName();
                    String file_ = StringList.concat(LINKS_FOLDER,
                            SEPARATOR_FILES, f_);
                    links.put(f_, BaseSixtyFourUtil
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
                        trainers.put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                        f_ = tr_.getImageMiniFileName();
                        file_ = StringList.concat(PEOPLE_FOLDER,
                                SEPARATOR_FILES, f_);
                        people.put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                }
                for (DualFight d : level_.getDualFights().values()) {
                    String f_ = d.getFoeTrainer().getImageMaxiFileName();
                    String file_ = StringList.concat(TRAINERS_FOLDER,
                            SEPARATOR_FILES, f_);
                    trainers.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    f_ = d.getFoeTrainer().getImageMiniFileName();
                    file_ = StringList.concat(PEOPLE_FOLDER, SEPARATOR_FILES,
                            f_);
                    people.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                    f_ = d.getFoeTrainer().getImageMiniSecondTrainerFileName();
                    file_ = StringList.concat(PEOPLE_FOLDER, SEPARATOR_FILES,
                            f_);
                    people.put(f_, BaseSixtyFourUtil
                            .getImageByString(ResourceFiles
                                    .ressourceFichier(StringList.concat(
                                            common_, file_))));
                }
                for (Block b_ : l.getBlocks().values()) {
                    String f_ = b_.getTileFileName();
                    String file_ = StringList.concat(IMAGES_FOLDER,
                            SEPARATOR_FILES, f_);
                    images.put(f_, BaseSixtyFourUtil
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
                    links.put(f_, BaseSixtyFourUtil
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
                    links.put(f_, BaseSixtyFourUtil
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
                        links.put(f_, BaseSixtyFourUtil
                                .getImageByString(ResourceFiles
                                        .ressourceFichier(StringList.concat(
                                                common_, file_))));
                    }
                }
            }
        }
        for (TileMiniMap t : map.getMiniMap().values()) {
            String f_ = t.getFile();
            String file_ = StringList.concat(MINI_MAP_FOLDER, SEPARATOR_FILES,
                    f_);
            miniMap.put(f_, BaseSixtyFourUtil.getImageByString(ResourceFiles
                    .ressourceFichier(StringList.concat(common_, file_))));
        }
        miniMap.put(map.getUnlockedCity(), BaseSixtyFourUtil
                .getImageByString(ResourceFiles.ressourceFichier(StringList
                        .concat(common_, MINI_MAP_FOLDER, SEPARATOR_FILES,
                                map.getUnlockedCity()))));
        _perCentLoading.addAndGet(delta_);
        initializeWildPokemon();
        _perCentLoading.addAndGet(delta_);

        validateEvolutions();
        for (int[][] i : maxiPkBack.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i[0].length > maxWidthPk) {
                maxWidthPk = i[0].length;
            }
            if (i.length > maxHeightPk) {
                maxHeightPk = i.length;
            }

        }
        for (int[][] i : maxiPkFront.values()) {
            if (i.length == 0) {
                setError(true);
                return;
            }
            if (i[0].length > maxWidthPk) {
                maxWidthPk = i[0].length;
            }
            if (i.length > maxHeightPk) {
                maxHeightPk = i.length;
            }

        }
        int side_ = map.getSideLength();
        for (EntryCust<String, int[][]> i : images.entryList()) {
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
            imagesTiles.put(name_, tiles_);
        }
        _perCentLoading.set(100);
    }

    public static StringList filterBeginIgnoreCase(StringList _instance,String _regExp) {
        StringList list_ = new StringList();
        String patt_ = toUpperCase(_regExp);
        for (String s: _instance) {
            if (!toUpperCase(s).startsWith(patt_)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    public static StringList filterStrictBeginIgnoreCase(StringList _instance,String _regExp) {
        StringList list_ = new StringList();
        String patt_ = toUpperCase(_regExp);
        for (String s: _instance) {
            if (!toUpperCase(s).startsWith(patt_)) {
                continue;
            }
            if (StringList.quickEq(toUpperCase(s),patt_)) {
                continue;
            }
            list_.add(s);
        }
        return list_;
    }

    public static String toUpperCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = 0; i < len_; i++) {
            char curr_ = _string.charAt(i);
            if (curr_ >= 'a' && curr_ <= 'z') {
                int char_ = curr_ - 'a' + 'A';
                str_.append((char)char_);
                continue;
            }
            str_.append(curr_);
        }
        return str_.toString();
    }
    public void initMessages(String _lg) {
        messagesPokemonPlayer = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER,_lg, PokemonPlayer.POKEMON_PLAYER);
        messagesPlayer = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Player.PLAYER);
        messagesFighter = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Fighter.FIGHTER);
        messagesTeam = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Team.TEAM);
        messagesFight = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Fight.FIGHT);
        messagesGame = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, Game.GAME);
    }
    public void setMessages(DataBase _other) {
        messagesPokemonPlayer = _other.messagesPokemonPlayer;
        messagesPlayer = _other.messagesPlayer;
        messagesFighter = _other.messagesFighter;
        messagesTeam = _other.messagesTeam;
        messagesFight = _other.messagesFight;
        messagesGame = _other.messagesGame;
    }
    public StringMap<String> getMessagesPokemonPlayer() {
        return messagesPokemonPlayer;
    }
    public StringMap<String> getMessagesPlayer() {
        return messagesPlayer;
    }
    public StringMap<String> getMessagesFighter() {
        return messagesFighter;
    }
    public StringMap<String> getMessagesTeam() {
        return messagesTeam;
    }
    public StringMap<String> getMessagesFight() {
        return messagesFight;
    }
    public StringMap<String> getMessagesGame() {
        return messagesGame;
    }
    public void setupPseudoImages() {
        int side_ = map.getSideLength();
        for (EntryCust<String, int[][]> i : images.entryList()) {
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
            imagesTiles.put(name_, tiles_);
        }

    }

    public static void deleteLineReturn(StringMap<String> _map) {
        for (EntryCust<String, String> e : _map.entryList()) {
            String value_ = e.getValue();
            e.setValue(StringList.removeStrings(value_, RETURN_LINE));
        }
    }

    public static void deleteLineReturn(
            ListableEntries<ImageHeroKey, String> _map) {
        for (EntryCust<ImageHeroKey, String> e : _map.entryList()) {
            String value_ = e.getValue();
            e.setValue(StringList.removeStrings(value_, RETURN_LINE));
        }
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

    public void checkCaseOfFiles(String _folderName, StringList _files) {
        StringList filesNamesWithSameCase_;
        filesNamesWithSameCase_ = new StringList();
        for (String s : _files) {
            String upperCase_ = toUpperCase(s);
            if (filesNamesWithSameCase_.containsObj(upperCase_)) {
                String name_ = StringList.concat(_folderName, SEPARATOR_FILES,
                        upperCase_);
                if (!filesWithSameNameDifferentCase.containsObj(name_)) {
                    filesWithSameNameDifferentCase.add(name_);
                }
            }
            filesNamesWithSameCase_.add(upperCase_);
        }
    }

    public void initializeWildPokemon() {
        map.initializeWildPokemon();
    }

    public void initializeMembers() {
        pokedex = new StringMap<PokemonData>();
        moves = new StringMap<MoveData>();
        items = new StringMap<Item>();
        status = new StringMap<Status>();
        tm = new NumberMap<Short, String>();
        tmPrice = new NumberMap<Short, LgInt>();
        hm = new NumberMap<Short, String>();
        abilities = new StringMap<AbilityData>();
        avgWeight = Rate.zero();
        movesProtAgainstPrio = new StringList();
        movesProtAgainstMultiTarget = new StringList();
        movesProtSingleTarget = new StringList();
        movesProtSingleTargetAgainstKo = new StringList();
        movesCopyingTemp = new StringList();
        trappingMoves = new StringList();
        movesAccuracy = new StringList();
        movesActingMoveUses = new StringList();
        movesForbidding = new StringList();
        movesEffectIndiv = new StringList();
        movesEffectUnprot = new StringList();
        movesEffectProt = new StringList();
        movesEffectIndivIncr = new StringList();
        movesEffEndRoundIndiv = new StringList();
        movesEffEndRoundIndivIncr = new StringList();
        movesEffectTeam = new StringList();
        movesEffectWhileSending = new StringList();
        movesEffectGlobal = new StringList();
        movesEffectGlobalWeather = new StringList();
        movesEffectAlly = new StringList();
        movesHealingAfter = new StringList();
        movesFullHeal = new StringList();
        movesAnticipation = new StringList();
        movesConstChoices = new StringList();
        movesInvoking = new StringList();
        movesChangingTypes = new StringList();
        movesCountering = new StringList();
        movesProtAgainstDamageMoves = new StringList();
        movesProtAgainstStatusMoves = new StringList();
        allCategories = new StringList();
        categories = new StringList();
        evtEndRound = new CustList<EndRoundMainElements>();
        variables = new StringList();
        filesWithSameNameDifferentCase = new StringList();
        animStatis = new StringMap<int[][]>();
        animStatus = new StringMap<int[][]>();

    }

    public void calculateAvgPound() {
        if (!pokedex.isEmpty()) {
            avgWeight.divideBy(new Rate(pokedex.size()));
        }
    }

    public void completeMembers(String _pokemonName, PokemonData _pokemon) {
        avgWeight.addNb(_pokemon.getWeight());
        pokedex.put(_pokemonName, _pokemon);
    }

    public void removeMoveFromLists(String _moveName, MoveData _move) {
        categories.removeObj(_move.getCategory());
        allCategories.removeObj(_move.getCategory());
        movesCopyingTemp.removeObj(_moveName);
        movesProtAgainstPrio.removeObj(_moveName);
        movesProtAgainstMultiTarget.removeObj(_moveName);
        movesProtSingleTarget.removeObj(_moveName);
        movesProtSingleTargetAgainstKo.removeObj(_moveName);
        movesAccuracy.removeObj(_moveName);
        movesEffectAlly.removeObj(_moveName);
        trappingMoves.removeObj(_moveName);
        movesEffEndRoundIndiv.removeObj(_moveName);
        movesEffEndRoundIndivIncr.removeObj(_moveName);
        movesAnticipation.removeObj(_moveName);
        movesHealingAfter.removeObj(_moveName);
        movesEffectUnprot.removeObj(_moveName);
        movesEffectProt.removeObj(_moveName);
        movesEffectIndivIncr.removeObj(_moveName);
        movesActingMoveUses.removeObj(_moveName);
        movesForbidding.removeObj(_moveName);
        movesEffectIndiv.removeObj(_moveName);
        movesEffectIndivIncr.removeObj(_moveName);
        movesEffectTeam.removeObj(_moveName);
        movesEffectGlobalWeather.removeObj(_moveName);
        movesEffectGlobal.removeObj(_moveName);
        movesFullHeal.removeObj(_moveName);
        movesEffectWhileSending.removeObj(_moveName);
    }

    public void completeMembers(String _moveName, MoveData _move) {
        if (_move instanceof DamagingMoveData) {
            categories.add(_move.getCategory());
        }
        allCategories.add(_move.getCategory());

        variables.addAllElts(getVariableWords(_move.getAccuracy()));

        EndRoundMainElements endTurn_;
        if (_move.getRankIncrementNbRound() > 0) {
            endTurn_ = new EndRoundMainElements();
            endTurn_.setNumberIncrement(_move.getRankIncrementNbRound());
            endTurn_.setIncrementNumberOfRounds(true);
            endTurn_.setEndRoundType(EndTurnType.ATTAQUE);
            endTurn_.setElement(_moveName);
            endTurn_.setRelation(RelationType.INDIVIDUEL);
            evtEndRound.add(endTurn_);
        }
        for (Effect e : _move.getEffects()) {

            variables.addAllElts(getVariableWords(e.getFail()));

            if (e instanceof EffectCopyMove) {
                if (((EffectCopyMove) e).getCopyingMoveForUser() > 0) {
                    movesCopyingTemp.add(_moveName);
                    movesCopyingTemp.removeDuplicates();
                }
            }
            if (e instanceof EffectCounterAttack) {
                EffectCounterAttack effectCounterAttack_;
                effectCounterAttack_ = (EffectCounterAttack) e;

                variables.addAllElts(getVariableWords(effectCounterAttack_
                        .getCounterFail()));
                variables.addAllElts(getVariableWords(effectCounterAttack_
                        .getProtectFail()));
                movesCountering.add(_moveName);
                movesCountering.removeDuplicates();
            }
            if (e instanceof EffectProtection) {
                EffectProtection effetProtection_ = (EffectProtection) e;
                if (effetProtection_.isProtTeamAgainstDamageMoves()) {
                    movesProtAgainstDamageMoves.add(_moveName);
                    movesProtAgainstDamageMoves.removeDuplicates();
                }
                if (effetProtection_.isProtTeamAgainstStatusMoves()) {
                    movesProtAgainstStatusMoves.add(_moveName);
                    movesProtAgainstStatusMoves.removeDuplicates();
                }
                if (effetProtection_.getProtTeamAgainstPrio()) {
                    movesProtAgainstPrio.add(_moveName);
                    movesProtAgainstPrio.removeDuplicates();
                }
                if (effetProtection_.getProtTeamAgainstMultTargets()) {
                    movesProtAgainstMultiTarget.add(_moveName);
                    movesProtAgainstMultiTarget.removeDuplicates();
                }
                if (effetProtection_.getProtSingle()) {
                    movesProtSingleTarget.add(_moveName);
                    movesProtSingleTarget.removeDuplicates();
                }
                if (!effetProtection_.getProtSingleAgainstKo().isZero()) {
                    movesProtSingleTargetAgainstKo.add(_moveName);
                    movesProtSingleTargetAgainstKo.removeDuplicates();
                }
            }
            if (e instanceof EffectAccuracy) {
                movesAccuracy.add(_moveName);
                movesAccuracy.removeDuplicates();
            }
            if (e instanceof EffectAlly) {
                movesEffectAlly.add(_moveName);
                movesEffectAlly.removeDuplicates();
            }
            if (e instanceof EffectDamage) {

                variables.addAllElts(getVariableWords(((EffectDamage) e)
                        .getPower()));

                for (String event_ : ((EffectDamage) e).getDamageLaw().events()) {

                    variables.addAllElts(getVariableWords(event_));

                }
            }
            if (e instanceof EffectEndRound) {
                EffectEndRound e_ = (EffectEndRound) e;
                endTurn_ = new EndRoundMainElements();
                endTurn_.setNumberIncrement((short) e_.getEndRoundRank());
                endTurn_.setIncrementNumberOfRounds(false);
                endTurn_.setEndRoundType(EndTurnType.ATTAQUE);
                endTurn_.setElement(_moveName);
                endTurn_.setRelation(e_.getRelation());
                evtEndRound.add(endTurn_);
                if (e_ instanceof EffectEndRoundSingleRelation) {
                    trappingMoves.add(_moveName);
                }
                if (e_ instanceof EffectEndRoundIndividual) {
                    movesEffEndRoundIndiv.add(_moveName);
                    if (_move.getRepeatRoundLaw().events().size() > 0) {
                        movesEffEndRoundIndivIncr.add(_moveName);
                    }
                }
                if (e_ instanceof EffectEndRoundPositionTargetRelation) {
                    movesAnticipation.add(_moveName);
                }
                if (e_ instanceof EffectEndRoundPositionRelation) {
                    movesHealingAfter.add(_moveName);
                }

                variables.addAllElts(getVariableWords(e_.getFailEndRound()));
            }
            if (e instanceof EffectUnprotectFromTypes
                    || e instanceof EffectProtectFromTypes) {
                if (e instanceof EffectUnprotectFromTypes) {
                    movesEffectUnprot.add(_moveName);
                }
                if (e instanceof EffectProtectFromTypes) {
                    movesEffectProt.add(_moveName);
                }
                if (_move.getRepeatRoundLaw().events().size() > 0) {
                    movesEffectIndivIncr.add(_moveName);
                }
            }
            if (e instanceof EffectSwitchMoveTypes) {
                movesChangingTypes.add(_moveName);
            }
            if (e instanceof EffectRestriction) {
                EffectRestriction effetAntiChoix_ = (EffectRestriction) e;
                if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.FORCE) {
                    movesActingMoveUses.add(_moveName);
                } else if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.FORBIDDEN) {
                    movesActingMoveUses.add(_moveName);
                } else if (effetAntiChoix_.getChoiceRestriction() == MoveChoiceRestrictionType.LANCEUR_ATTAQUES) {
                    movesForbidding.add(_moveName);
                } else {
                    movesEffectIndiv.add(_moveName);
                    if (_move.getRepeatRoundLaw().events().size() > 0) {
                        movesEffectIndivIncr.add(_moveName);
                    }
                }
            }
            if (e instanceof EffectTeam) {
                movesEffectTeam.add(_moveName);
            }
            if (e instanceof EffectGlobal) {
                EffectGlobal effetGlobal_ = (EffectGlobal) e;
                if (effetGlobal_.getWeather()) {
                    movesEffectGlobalWeather.add(_moveName);
                }
                movesEffectGlobal.add(_moveName);
            }
            if (e instanceof EffectStatistic) {
                for (String r : ((EffectStatistic) e).getLocalFailStatis()
                        .values()) {

                    variables.addAllElts(getVariableWords(r));
                }
                for (String r : ((EffectStatistic) e)
                        .getLocalFailSwapBoostStatis().values()) {

                    variables.addAllElts(getVariableWords(r));
                }
            }
            if (e instanceof EffectStatus) {
                for (String r : ((EffectStatus) e).getLocalFailStatus()
                        .values()) {

                    variables.addAllElts(getVariableWords(r));
                }
                if (((EffectStatus) e).getKoUserHealSubst()) {
                    movesFullHeal.add(_moveName);
                }
            }
            if (e instanceof EffectCommonStatistics) {
                for (String r : ((EffectCommonStatistics) e).getCommonValue()
                        .values()) {

                    variables.addAllElts(getVariableWords(r));
                }
            }
            if (e instanceof EffectFullHpRate) {

                variables.addAllElts(getVariableWords(((EffectFullHpRate) e)
                        .getRestoredHp()));
            }
            if (e instanceof EffectTeamWhileSendFoe) {
                movesEffectWhileSending.add(_moveName);

                variables
                        .addAllElts(getVariableWords(((EffectTeamWhileSendFoe) e)
                                .getDamageRateAgainstFoe()));
                variables
                        .addAllElts(getVariableWords(((EffectTeamWhileSendFoe) e)
                                .getFailSending()));
            }
            if (e instanceof EffectInvoke) {
                movesInvoking.add(_moveName);
            }
        }
        if (!movesEffectIndiv.containsObj(_moveName)) {
            if (!movesEffEndRoundIndiv.containsObj(_moveName)) {
                if (_move.getRepeatRoundLaw().events().size() > 0) {
                    if (_move.getConstUserChoice()) {
                        movesConstChoices.add(_moveName);
                    }
                }
            }
        }
        moves.put(_moveName, _move);
    }

    public void completeMembers(String _objectName, Item _object) {
        if (_object instanceof ItemForBattle) {
            ItemForBattle obj_ = (ItemForBattle) _object;
            if (!obj_.getEffectEndRound().isEmpty()) {
                EndRoundMainElements endTurn_ = new EndRoundMainElements();
                endTurn_.setNumberIncrement((short) obj_.getEffectEndRound()
                        .first().getEndRoundRank());
                endTurn_.setIncrementNumberOfRounds(false);
                endTurn_.setEndRoundType(EndTurnType.OBJET);
                endTurn_.setElement(_objectName);
                endTurn_.setRelation(obj_.getEffectEndRound().first()
                        .getRelation());
                evtEndRound.add(endTurn_);
            }

            variables.addAllElts(getVariableWords(new StringList(obj_
                    .getMultStat().values()).join(EMPTY_STRING)));
            variables.addAllElts(getVariableWords(obj_.getMultDamage()));
            variables.addAllElts(getVariableWords(obj_.getMultPower()));
            variables.addAllElts(getVariableWords(new StringList(obj_
                    .getFailStatus().values()).join(EMPTY_STRING)));
        }
        items.put(_objectName, _object);
    }

    public void completeMembers(String _abilityName, AbilityData _ability) {
        if (!_ability.getEffectEndRound().isEmpty()) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements();
            endTurn_.setNumberIncrement((short) _ability.getEffectEndRound()
                    .first().getEndRoundRank());
            endTurn_.setIncrementNumberOfRounds(false);
            endTurn_.setEndRoundType(EndTurnType.CAPACITE);
            endTurn_.setElement(_abilityName);
            endTurn_.setRelation(_ability.getEffectEndRound().first()
                    .getRelation());
            evtEndRound.add(endTurn_);
        }

        variables.addAllElts(getVariableWords(new StringList(_ability
                .getMultStat().values()).join(EMPTY_STRING)));
        variables.addAllElts(getVariableWords(_ability.getMultDamage()));
        variables.addAllElts(getVariableWords(_ability.getMultPower()));
        variables.addAllElts(getVariableWords(new StringList(_ability
                .getFailStatus().values()).join(EMPTY_STRING)));
        abilities.put(_abilityName, _ability);
    }

    public void completeMembers(String _statusName, Status _status) {
        if (!_status.getEffectEndRound().isEmpty()) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements();
            endTurn_.setNumberIncrement((short) _status.getEffectEndRound()
                    .first().getEndRoundRank());
            endTurn_.setIncrementNumberOfRounds(false);
            endTurn_.setEndRoundType(EndTurnType.STATUT);
            endTurn_.setElement(_statusName);
            endTurn_.setRelation(_status.getEffectEndRound().first()
                    .getRelation());
            evtEndRound.add(endTurn_);
        }
        if (_status.getIncrementingEndRound()) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements();
            endTurn_.setNumberIncrement((short) _status.getIncrementEndRound());
            endTurn_.setIncrementNumberOfRounds(false);
            endTurn_.setEndRoundType(EndTurnType.STATUT);
            endTurn_.setElement(_statusName);
            if (_status.getStatusType() == StatusType.INDIVIDUEL) {
                endTurn_.setRelation(RelationType.STATUT);
            } else {
                endTurn_.setRelation(RelationType.STATUT_RELATION);
            }
            evtEndRound.add(endTurn_);
        }
        status.put(_statusName, _status);
    }

    public void initCombosTest() {
        combos = new Combos();
        combos.setEffects(new ObjectMap<StringList, EffectCombo>());
    }

    public void completeMembersCombos() {
        for (StringList k : combos.getEffects().getKeys()) {
            EffectCombo ef_ = combos.getEffects().getVal(k);
            completeMembers(k, ef_);
        }
    }

    public void completeMembers(StringList _moves, EffectCombo _effect) {
        if (_effect.getRankIncrementNbRound() > 0) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements();
            endTurn_.setNumberIncrement(_effect.getRankIncrementNbRound());
            endTurn_.setIncrementNumberOfRounds(true);
            endTurn_.setEndRoundType(EndTurnType.ATTAQUE_COMBI);
            endTurn_.setElement(_moves.join(SEPARATOR_MOVES));
            endTurn_.setRelation(RelationType.EQUIPE);
            evtEndRound.add(endTurn_);
        }
        if (_effect.getEffectEndRound().isEmpty()) {
            return;
        }
        EndRoundMainElements endTurn_ = new EndRoundMainElements();
        endTurn_.setNumberIncrement((short) _effect.getEffectEndRound().first()
                .getEndRoundRank());
        endTurn_.setIncrementNumberOfRounds(false);
        endTurn_.setEndRoundType(EndTurnType.ATTAQUE_COMBI);
        endTurn_.setElement(_moves.join(SEPARATOR_MOVES));
        endTurn_.setRelation(_effect.getEffectEndRound().first().getRelation());
        evtEndRound.add(endTurn_);
    }

    public void completeVariables() {
        removeDuplicatesCategoriesMoves();
        varParamsMove = new StringMap<StringList>();
        for (String e : variables) {
            StringList infos_ = StringList.splitStrings(e, SEP_BETWEEN_KEYS);
            String key_ = infos_.get(CustList.SECOND_INDEX);
            String element_ = new StringList(infos_.sub(
                    CustList.SECOND_INDEX + 1, infos_.size()))
                    .join(SEP_BETWEEN_KEYS);
            if (varParamsMove.contains(key_)) {
                StringList ref_ = varParamsMove.getVal(key_);
                ref_.add(element_);
            } else {
                StringList list_ = new StringList();
                list_.add(element_);
                varParamsMove.put(key_, list_);
            }
        }
        for (StringList l : varParamsMove.values()) {
            l.removeDuplicates();
        }
    }

    void removeDuplicatesCategoriesMoves() {
        allCategories.removeDuplicates();
        categories.removeDuplicates();
    }

    public void sortEndRound() {
        evtEndRound.sortElts(new ComparatorEndRoundMainElements());
    }

    public void renamePokemon(String _oldName, String _newName, boolean _homonym) {
        if (pokedex.contains(_newName)) {
            return;
        }
        StringList types_ = getTypes();
        StringList categories_ = getCategories();
        if (_homonym) {
            if (items.contains(_newName)) {
                return;
            }
            if (abilities.contains(_newName)) {
                return;
            }
            if (moves.contains(_newName)) {
                return;
            }
            if (status.contains(_newName)) {
                return;
            }
            if (types_.containsObj(_newName)) {
                return;
            }
            if (categories_.containsObj(_newName)) {
                return;
            }
            changeNameInNumericExpressions(_oldName, _newName);
        }
        for (Item o : items.values()) {
            if (o instanceof Fossil) {
                Fossil f_ = (Fossil) o;
                if (StringList.quickEq(f_.getPokemon(), _oldName)) {
                    f_.setPokemon(_newName);
                }
            }
        }
        for (PokemonData p : pokedex.values()) {
            if (StringList.quickEq(p.getBaseEvo(), _oldName)) {
                p.setBaseEvo(_newName);
            }
            p.getEvolutions().move(_oldName, _newName);
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (AreaApparition a : level_.getWildPokemonAreas()) {
                        for (WildPk p2_ : a.getWildPokemon()) {
                            if (StringList.quickEq(p2_.getName(), _oldName)) {
                                p2_.setName(_newName);
                            }
                        }
                        for (WildPk p2_ : a.getWildPokemonFishing()) {
                            if (StringList.quickEq(p2_.getName(), _oldName)) {
                                p2_.setName(_newName);
                            }
                        }
                    }
                    for (WildPk p2_ : level_.getLegendaryPks().values()) {
                        if (StringList.quickEq(p2_.getName(), _oldName)) {
                            p2_.setName(_newName);
                        }
                    }
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    if (StringList.quickEq(p2_.getName(),
                                            _oldName)) {
                                        p2_.setName(_newName);
                                    }
                                }
                            }
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            if (StringList.quickEq(p2_.getName(), _oldName)) {
                                p2_.setName(_newName);
                            }
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            if (StringList.quickEq(p2_.getName(), _oldName)) {
                                p2_.setName(_newName);
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        if (StringList.quickEq(p2_.getName(), _oldName)) {
                            p2_.setName(_newName);
                        }
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            if (StringList.quickEq(p2_.getName(), _oldName)) {
                                p2_.setName(_newName);
                            }
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        if (StringList.quickEq(p2_.getName(), _oldName)) {
                            p2_.setName(_newName);
                        }
                    }
                }
            }
        }
        pokedex.move(_oldName, _newName);
    }

    public void renameMove(String _oldName, String _newName, boolean _homonym) {
        if (moves.contains(_newName)) {
            return;
        }
        StringList types_ = getTypes();
        StringList categories_ = getCategories();
        if (_homonym) {
            if (items.contains(_newName)) {
                return;
            }
            if (abilities.contains(_newName)) {
                return;
            }
            if (pokedex.contains(_newName)) {
                return;
            }
            if (status.contains(_newName)) {
                return;
            }
            if (types_.containsObj(_newName)) {
                return;
            }
            if (categories_.containsObj(_newName)) {
                return;
            }
            changeNameInNumericExpressions(_oldName, _newName);
        }
        for (PokemonData p : pokedex.values()) {
            for (LevelMove p2_ : p.getLevMoves()) {
                if (StringList.quickEq(p2_.getMove(), _oldName)) {
                    p2_.setMove(_newName);
                }
            }
            p.getMoveTutors().replace(_oldName, _newName);
            for (Evolution e : p.getEvolutions().values()) {
                if (e instanceof EvolutionMove) {
                    EvolutionMove evo_ = (EvolutionMove) e;
                    if (StringList.quickEq(evo_.getMove(), _oldName)) {
                        evo_.setMove(_newName);
                    }
                }
            }
        }
        for (AbilityData a : abilities.values()) {
            a.getImmuMove().replace(_oldName, _newName);
            a.getIgnFoeTeamMove().replace(_oldName, _newName);
            a.getImmuWeather().replace(_oldName, _newName);
            a.getImmuMoveTypesByWeather().move(_oldName, _newName);
            a.getImmuStatus().move(_oldName, _newName);
            a.getChgtTypeByWeather().move(_oldName, _newName);
            a.getHealHpByWeather().move(_oldName, _newName);
            if (!a.getEffectSending().isEmpty()) {
                if (StringList.quickEq(a.getEffectSending().first()
                        .getEnabledWeather(), _oldName)) {
                    a.getEffectSending().first().setEnabledWeather(_newName);
                }
            }
            ObjectMap<WeatherType, Rate> map_ = new ObjectMap<WeatherType, Rate>();
            for (WeatherType p : a.getHealHpByTypeIfWeather().getKeys()) {
                if (StringList.quickEq(p.getWeather(), _oldName)) {
                    map_.put(new WeatherType(_newName, p.getType()), a
                            .getHealHpByTypeIfWeather().getVal(p));
                } else {
                    map_.put(p, a.getHealHpByTypeIfWeather().getVal(p));
                }
            }
            a.setHealHpByTypeIfWeather(map_);
        }
        for (Item o : items.values()) {
            if (o instanceof ItemForBattle) {
                ItemForBattle obj_ = (ItemForBattle) o;
                obj_.getIncreasingMaxNbRoundTrap().move(_oldName, _newName);
                obj_.getIncreasingMaxNbRoundGlobalMove().move(_oldName,
                        _newName);
                obj_.getIncreasingMaxNbRoundTeamMove().move(_oldName, _newName);
                if (!obj_.getEffectSending().isEmpty()) {
                    if (StringList.quickEq(obj_.getEffectSending().first()
                            .getEnabledWeather(), _oldName)) {
                        obj_.getEffectSending().first()
                                .setEnabledWeather(_newName);
                    }
                }
            }
        }
        ObjectMap<StringList, EffectCombo> effects_ = new ObjectMap<StringList, EffectCombo>();
        for (StringList l : combos.getEffects().getKeys()) {
            EffectCombo eff_ = combos.getEffects().getVal(l);
            if (eff_.estActifEquipe()) {
                EffectTeam eff2_ = eff_.getTeamMove().first();
                eff2_.getUnusableMoves().replace(_oldName, _newName);
                eff2_.getDisableFoeTeamEffects().replace(_oldName, _newName);
            }
            if (l.containsObj(_oldName)) {
                StringList l_ = new StringList(l);
                l_.replace(_oldName, _newName);
                effects_.put(l_, eff_);
            } else {
                effects_.put(l, eff_);
            }
        }
        combos.setEffects(effects_);
        for (MoveData m : moves.values()) {
            m.getAchieveDisappearedPkUsingMove().replace(_oldName, _newName);
            for (Effect e : m.getEffects()) {
                if (e instanceof EffectUnprotectFromTypes) {
                    EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) e;
                    eff_.getDisableImmuFromMoves().replace(_oldName, _newName);
                }
                if (e instanceof EffectCopyMove) {
                    EffectCopyMove eff_ = (EffectCopyMove) e;
                    eff_.getMovesNotToBeCopied().replace(_oldName, _newName);
                }
                if (e instanceof EffectTeam) {
                    EffectTeam eff_ = (EffectTeam) e;
                    eff_.getUnusableMoves().replace(_oldName, _newName);
                    eff_.getDisableFoeTeamEffects().replace(_oldName, _newName);
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    eff_.getCancelEffects().replace(_oldName, _newName);
                    eff_.getMultPowerMoves().move(_oldName, _newName);
                    eff_.getUnusableMoves().replace(_oldName, _newName);
                    eff_.getMovesUsedByTargetedFighters().replace(_oldName,
                            _newName);
                }
                if (e instanceof EffectInvoke) {
                    EffectInvoke eff_ = (EffectInvoke) e;
                    eff_.getMovesNotToBeInvoked().replace(_oldName, _newName);
                    replace(eff_.getMoveFctEnv(), _oldName, _newName);
                    replaceStr(eff_.getInvokingMoveByUserTypes(), _oldName,
                            _newName);
                }
            }
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    p2_.getMoves().replace(_oldName, _newName);
                                }
                            }
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            p2_.getMoves().replace(_oldName, _newName);
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            p2_.getMoves().replace(_oldName, _newName);
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        p2_.getMoves().replace(_oldName, _newName);
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            p2_.getMoves().replace(_oldName, _newName);
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        p2_.getMoves().replace(_oldName, _newName);
                    }
                }
            }
        }
        movesCopyingTemp.replace(_oldName, _newName);
        movesProtAgainstPrio.replace(_oldName, _newName);
        movesProtAgainstMultiTarget.replace(_oldName, _newName);
        movesProtSingleTarget.replace(_oldName, _newName);
        movesProtSingleTargetAgainstKo.replace(_oldName, _newName);
        movesAccuracy.replace(_oldName, _newName);
        movesEffectAlly.replace(_oldName, _newName);
        trappingMoves.replace(_oldName, _newName);
        movesEffEndRoundIndiv.replace(_oldName, _newName);
        movesEffEndRoundIndivIncr.replace(_oldName, _newName);
        movesAnticipation.replace(_oldName, _newName);
        movesHealingAfter.replace(_oldName, _newName);
        movesEffectUnprot.replace(_oldName, _newName);
        movesEffectProt.replace(_oldName, _newName);
        movesEffectIndivIncr.replace(_oldName, _newName);
        movesActingMoveUses.replace(_oldName, _newName);
        movesForbidding.replace(_oldName, _newName);
        movesEffectIndiv.replace(_oldName, _newName);
        movesEffectIndivIncr.replace(_oldName, _newName);
        movesEffectTeam.replace(_oldName, _newName);
        movesEffectGlobalWeather.replace(_oldName, _newName);
        movesEffectGlobal.replace(_oldName, _newName);
        movesFullHeal.replace(_oldName, _newName);
        movesEffectWhileSending.replace(_oldName, _newName);
        replaceShort(hm, _oldName, _newName);
        replaceShort(tm, _oldName, _newName);
        moves.move(_oldName, _newName);
    }

    public void renameObject(String _oldName, String _newName, boolean _homonym) {
        if (items.contains(_newName)) {
            return;
        }
        StringList types_ = getTypes();
        StringList categories_ = getCategories();
        if (_homonym) {
            if (moves.contains(_newName)) {
                return;
            }
            if (abilities.contains(_newName)) {
                return;
            }
            if (pokedex.contains(_newName)) {
                return;
            }
            if (status.contains(_newName)) {
                return;
            }
            if (types_.containsObj(_newName)) {
                return;
            }
            if (categories_.containsObj(_newName)) {
                return;
            }
            changeNameInNumericExpressions(_oldName, _newName);
        }
        for (MoveData m : moves.values()) {
            m.getTypesByOwnedItem().move(_oldName, _newName);
            m.getSecEffectsByItem().move(_oldName, _newName);
        }
        for (PokemonData p : pokedex.values()) {
            for (Evolution e : p.getEvolutions().values()) {
                if (e instanceof EvolutionStone) {
                    EvolutionStone evo_ = (EvolutionStone) e;
                    if (StringList.quickEq(evo_.getStone(), _oldName)) {
                        evo_.setStone(_newName);
                    }
                }
                if (e instanceof EvolutionItem) {
                    EvolutionItem evo_ = (EvolutionItem) e;
                    if (StringList.quickEq(evo_.getItem(), _oldName)) {
                        evo_.setItem(_newName);
                    }
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof HealingItem) {
                HealingItem h_ = (HealingItem) o;
                h_.getHappiness().move(_oldName, _newName);
            }
            if (o instanceof Boost) {
                Boost b_ = (Boost) o;
                b_.getHappiness().move(_oldName, _newName);
            }
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (AreaApparition a : level_.getWildPokemonAreas()) {
                        for (WildPk p2_ : a.getWildPokemon()) {
                            if (StringList.quickEq(p2_.getItem(), _oldName)) {
                                p2_.setItem(_newName);
                            }
                        }
                        for (WildPk p2_ : a.getWildPokemonFishing()) {
                            if (StringList.quickEq(p2_.getItem(), _oldName)) {
                                p2_.setItem(_newName);
                            }
                        }
                    }
                    for (WildPk p2_ : level_.getLegendaryPks().values()) {
                        if (StringList.quickEq(p2_.getItem(), _oldName)) {
                            p2_.setItem(_newName);
                        }
                    }
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    if (StringList.quickEq(p2_.getItem(),
                                            _oldName)) {
                                        p2_.setItem(_newName);
                                    }
                                }
                            }
                        }
                        if (t instanceof DealerItem) {
                            DealerItem d_ = (DealerItem) t;
                            d_.getItems().replace(_oldName, _newName);
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            if (StringList.quickEq(p2_.getItem(), _oldName)) {
                                p2_.setItem(_newName);
                            }
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            if (StringList.quickEq(p2_.getItem(), _oldName)) {
                                p2_.setItem(_newName);
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        if (StringList.quickEq(p2_.getItem(), _oldName)) {
                            p2_.setItem(_newName);
                        }
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            if (StringList.quickEq(p2_.getItem(), _oldName)) {
                                p2_.setItem(_newName);
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorPokemonCenter) {
                    LevelIndoorPokemonCenter level_ = (LevelIndoorPokemonCenter) l;
                    for (Person g : level_.getGerants().values()) {
                        if (g instanceof Seller) {
                            Seller s_ = (Seller) g;
                            s_.getItems().replace(_oldName, _newName);
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        if (StringList.quickEq(p2_.getItem(), _oldName)) {
                            p2_.setItem(_newName);
                        }
                    }
                }
            }
        }
        items.move(_oldName, _newName);
    }

    public void renameAbility(String _oldName, String _newName, boolean _homonym) {
        if (abilities.contains(_newName)) {
            return;
        }
        StringList types_ = getTypes();
        StringList categories_ = getCategories();
        if (_homonym) {
            if (moves.contains(_newName)) {
                return;
            }
            if (items.contains(_newName)) {
                return;
            }
            if (pokedex.contains(_newName)) {
                return;
            }
            if (status.contains(_newName)) {
                return;
            }
            if (types_.containsObj(_newName)) {
                return;
            }
            if (categories_.containsObj(_newName)) {
                return;
            }
            changeNameInNumericExpressions(_oldName, _newName);
        }
        for (MoveData m : moves.values()) {
            for (Effect e : m.getEffects()) {
                if (e instanceof EffectSwitchAbilities) {
                    EffectSwitchAbilities eff_ = (EffectSwitchAbilities) e;
                    if (StringList.quickEq(eff_.getConstAbility(), _oldName)) {
                        eff_.setConstAbility(_newName);
                    }
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    eff_.getCancelProtectingAbilities().replace(_oldName,
                            _newName);
                }
            }
        }
        /*
         * for (Item o: items.values()) { if (o instanceof ItemForBattle) {
         * ItemForBattle i_ = (ItemForBattle) o;
         * i_.getSansEffetCapacite().replace(_oldName, _newName); } }
         */
        for (PokemonData p : pokedex.values()) {
            p.getAbilities().replace(_oldName, _newName);
        }
        for (AbilityData a : abilities.values()) {
            a.getImmuAbility().replace(_oldName, _newName);
            a.getIgnAbility().replace(_oldName, _newName);
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (AreaApparition a : level_.getWildPokemonAreas()) {
                        for (WildPk p2_ : a.getWildPokemon()) {
                            if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                                p2_.setAbility(_newName);
                            }
                        }
                        for (WildPk p2_ : a.getWildPokemonFishing()) {
                            if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                                p2_.setAbility(_newName);
                            }
                        }
                    }
                    for (WildPk p2_ : level_.getLegendaryPks().values()) {
                        if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                            p2_.setAbility(_newName);
                        }
                    }
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    if (StringList.quickEq(p2_.getAbility(),
                                            _oldName)) {
                                        p2_.setAbility(_newName);
                                    }
                                }
                            }
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                                p2_.setAbility(_newName);
                            }
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                                p2_.setAbility(_newName);
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                            p2_.setAbility(_newName);
                        }
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                                p2_.setAbility(_newName);
                            }
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        if (StringList.quickEq(p2_.getAbility(), _oldName)) {
                            p2_.setAbility(_newName);
                        }
                    }
                }
            }
        }
        abilities.move(_oldName, _newName);
    }

    public void renameStatus(String _oldName, String _newName, boolean _homonym) {
        if (abilities.contains(_newName)) {
            return;
        }
        StringList types_ = getTypes();
        StringList categories_ = getCategories();
        if (_homonym) {
            if (moves.contains(_newName)) {
                return;
            }
            if (items.contains(_newName)) {
                return;
            }
            if (pokedex.contains(_newName)) {
                return;
            }
            if (status.contains(_newName)) {
                return;
            }
            if (types_.containsObj(_newName)) {
                return;
            }
            if (categories_.containsObj(_newName)) {
                return;
            }
            changeNameInNumericExpressions(_oldName, _newName);
        }
        for (MoveData m : moves.values()) {
            m.getRequiredStatus().replace(_oldName, _newName);
            m.getDeletedStatus().replace(_oldName, _newName);
            for (Effect e : m.getEffects()) {
                if (e instanceof EffectEndRound) {
                    renameStatusEffectEndRound((EffectEndRound) e, _oldName,
                            _newName);
                }
                if (e instanceof EffectTeam) {
                    EffectTeam eff_ = (EffectTeam) e;
                    eff_.getProtectAgainstStatus().replace(_oldName, _newName);
                }
                if (e instanceof EffectTeamWhileSendFoe) {
                    EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                    replaceShort(eff_.getStatusByNbUses(), _oldName, _newName);
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    eff_.getPreventStatus().replace(_oldName, _newName);
                }
                if (e instanceof EffectStatus) {
                    EffectStatus eff_ = (EffectStatus) e;
                    eff_.getLocalFailStatus().move(_oldName, _newName);
                    MonteCarloString newLaw_ = new MonteCarloString();
                    for (String s : eff_.getLawStatus().events()) {
                        if (StringList.quickEq(s, _oldName)) {
                            newLaw_.addEvent(_newName, eff_.getLawStatus()
                                    .rate(s));
                        } else {
                            newLaw_.addEvent(s, eff_.getLawStatus().rate(s));
                        }
                    }
                    newLaw_.checkEvents();
                    eff_.setLawStatus(newLaw_);
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof Berry) {
                Berry b_ = (Berry) o;
                b_.getHealStatus().replace(_oldName, _newName);
            }
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                i_.getFailStatus().move(_oldName, _newName);
                i_.getImmuStatus().replace(_oldName, _newName);
                i_.getSynchroStatus().replace(_oldName, _newName);
                if (!i_.getEffectEndRound().isEmpty()) {
                    renameStatusEffectEndRound(i_.getEffectEndRound().first(),
                            _oldName, _newName);
                }
            }
            if (o instanceof HealingStatus) {
                HealingStatus s_ = (HealingStatus) o;
                s_.getStatus().replace(_oldName, _newName);
            }
        }
        for (AbilityData a : abilities.values()) {
            a.getFailStatus().move(_oldName, _newName);
            MonteCarloString law_ = new MonteCarloString();
            for (String e : a.getSingleStatus().events()) {
                if (StringList.quickEq(e, _oldName)) {
                    law_.addEvent(_newName, a.getSingleStatus().rate(e));
                } else {
                    law_.addEvent(e, a.getSingleStatus().rate(e));
                }
            }
            law_.checkEvents();
            a.setSingleStatus(law_);
            a.getForwardStatus().move(_oldName, _newName);
            replaceStr(a.getForwardStatus(), _oldName, _newName);
            if (!a.getEffectEndRound().isEmpty()) {
                renameStatusEffectEndRound(a.getEffectEndRound().first(),
                        _oldName, _newName);
            }
        }
        for (Status s : status.values()) {
            for (EffectEndRoundStatus e : s.getEffectEndRound()) {
                renameStatusEffectEndRound(e, _oldName, _newName);
            }
        }
        for (EffectCombo e : combos.getEffects().values()) {
            for (EffectTeam e2_ : e.getTeamMove()) {
                e2_.getProtectAgainstStatus().replace(_oldName, _newName);
            }
        }
        status.move(_oldName, _newName);
    }

    static void renameStatusEffectEndRound(EffectEndRound _effect,
            String _oldName, String _newName) {
        if (_effect instanceof EffectEndRoundIndividual) {
            EffectEndRoundIndividual eff_ = (EffectEndRoundIndividual) _effect;
            eff_.getMultDamageStatus().move(_oldName, _newName);
            if (StringList.quickEq(eff_.getUserStatusEndRound(), _oldName)) {
                eff_.setUserStatusEndRound(_newName);
            }
        }
        if (_effect instanceof EffectEndRoundMultiRelation) {
            EffectEndRoundMultiRelation eff_ = (EffectEndRoundMultiRelation) _effect;
            eff_.getMultDamageStatus().move(_oldName, _newName);
            eff_.getDamageByStatus().move(_oldName, _newName);
        }
        if (_effect instanceof EffectEndRoundSingleStatus) {
            EffectEndRoundSingleStatus eff_ = (EffectEndRoundSingleStatus) _effect;
            eff_.getMultDamageStatus().move(_oldName, _newName);
        }
    }

    public void renameType(String _oldName, String _newName, boolean _homonym) {
        StringList types_ = getTypes();
        if (types_.containsObj(_newName)) {
            return;
        }
        StringList categories_ = getCategories();
        if (_homonym) {
            if (moves.contains(_newName)) {
                return;
            }
            if (items.contains(_newName)) {
                return;
            }
            if (pokedex.contains(_newName)) {
                return;
            }
            if (status.contains(_newName)) {
                return;
            }
            if (abilities.contains(_newName)) {
                return;
            }
            if (categories_.containsObj(_newName)) {
                return;
            }
            changeNameInNumericExpressions(_oldName, _newName);
        }
        for (PokemonData p : pokedex.values()) {
            p.getTypes().replace(_oldName, _newName);
        }
        for (MoveData p : moves.values()) {
            p.getTypes().replace(_oldName, _newName);
            p.getBoostedTypes().replace(_oldName, _newName);
            replaceStr(p.getTypesByWeather(), _oldName, _newName);
            replaceStr(p.getTypesByOwnedItem(), _oldName, _newName);
            for (Effect e : p.getEffects()) {
                if (e instanceof EffectUnprotectFromTypes) {
                    EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) e;
                    eff_.getAttackTargetWithTypes().replace(_oldName, _newName);
                    eff_.getDisableImmuAgainstTypes().replace(_oldName,
                            _newName);
                    EqList<TypesDuo> newList_ = new EqList<TypesDuo>();
                    for (TypesDuo t : eff_.getTypes()) {
                        TypesDuo pair_ = new TypesDuo();
                        if (StringList.quickEq(t.getDamageType(), _oldName)) {
                            pair_.setDamageType(_newName);
                        } else {
                            pair_.setDamageType(t.getDamageType());
                        }
                        if (StringList.quickEq(t.getPokemonType(), _oldName)) {
                            pair_.setPokemonType(_newName);
                        } else {
                            pair_.setPokemonType(t.getPokemonType());
                        }
                        newList_.add(pair_);
                    }
                    eff_.setTypes(newList_);
                }
                if (e instanceof EffectSwitchTypes) {
                    EffectSwitchTypes eff_ = (EffectSwitchTypes) e;
                    eff_.getConstTypes().replace(_oldName, _newName);
                    replace(eff_.getChgtTypeByEnv(), _oldName, _newName);
                }
                if (e instanceof EffectTeamWhileSendFoe) {
                    EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                    eff_.getDeletedByFoeTypes().replace(_oldName, _newName);
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    eff_.getImmuneTypes().replace(_oldName, _newName);
                    eff_.getMultDamagePrepaRound().move(_oldName, _newName);
                    eff_.getMultDamageTypesMoves().move(_oldName, _newName);
                    eff_.getDisableImmuAgainstTypes().replace(_oldName,
                            _newName);
                    ObjectMap<TypesDuo, Rate> table_ = new ObjectMap<TypesDuo, Rate>();
                    for (TypesDuo t : eff_.getEfficiencyMoves().getKeys()) {
                        Rate value_ = eff_.getEfficiencyMoves().getVal(t);
                        TypesDuo pair_ = new TypesDuo();
                        if (StringList.quickEq(t.getDamageType(), _oldName)) {
                            pair_.setDamageType(_newName);
                        } else {
                            pair_.setDamageType(t.getDamageType());
                        }
                        if (StringList.quickEq(t.getPokemonType(), _oldName)) {
                            pair_.setPokemonType(_newName);
                        } else {
                            pair_.setPokemonType(t.getPokemonType());
                        }
                        table_.put(pair_, value_);
                    }
                    eff_.setEfficiencyMoves(table_);
                    ObjectMap<StatisticType, Rate> mult_ = new ObjectMap<StatisticType, Rate>();
                    for (StatisticType t : eff_.getMultStatIfContainsType()
                            .getKeys()) {
                        Rate value_ = eff_.getMultStatIfContainsType()
                                .getVal(t);
                        StatisticType pair_ = new StatisticType();
                        pair_.setStatistic(t.getStatistic());
                        if (StringList.quickEq(t.getType(), _oldName)) {
                            pair_.setType(_newName);
                        } else {
                            pair_.setType(t.getType());
                        }
                        mult_.put(pair_, value_);
                    }
                    eff_.setMultStatIfContainsType(mult_);
                }
                if (e instanceof EffectProtectFromTypes) {
                    EffectProtectFromTypes eff_ = (EffectProtectFromTypes) e;
                    eff_.getImmuAgainstTypes().replace(_oldName, _newName);
                }
                if (e instanceof EffectMultUsedMovePower) {
                    EffectMultUsedMovePower eff_ = (EffectMultUsedMovePower) e;
                    eff_.getMultMovePowerFctType().move(_oldName, _newName);
                }
                if (e instanceof EffectMultSufferedMovePower) {
                    EffectMultSufferedMovePower eff_ = (EffectMultSufferedMovePower) e;
                    eff_.getMultMovePowerFctType().move(_oldName, _newName);
                }
                if (e instanceof EffectEndRound) {
                    renameTypeEffectEndRound((EffectEndRound) e, _oldName,
                            _newName);
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof Berry) {
                Berry b_ = (Berry) o;
                b_.getMultFoesDamage().move(_oldName, _newName);
            }
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                i_.getTypesPk().replace(_oldName, _newName);
                i_.getImmuTypes().replace(_oldName, _newName);
                if (!i_.getEffectEndRound().isEmpty()) {
                    renameTypeEffectEndRound(i_.getEffectEndRound().first(),
                            _oldName, _newName);
                }
            }
        }
        for (AbilityData a : abilities.values()) {
            ObjectMap<StatisticType, Byte> mult_ = new ObjectMap<StatisticType, Byte>();
            for (StatisticType t : a.getMultStatIfDamgeType().getKeys()) {
                byte value_ = a.getMultStatIfDamgeType().getVal(t);
                StatisticType pair_ = new StatisticType();
                pair_.setStatistic(t.getStatistic());
                if (StringList.quickEq(t.getType(), _oldName)) {
                    pair_.setType(_newName);
                } else {
                    pair_.setType(t.getType());
                }
                mult_.put(pair_, value_);
            }
            a.setMultStatIfDamgeType(mult_);
            EqList<TypesDuo> newList_ = new EqList<TypesDuo>();
            for (TypesDuo t : a.getBreakFoeImmune()) {
                TypesDuo pair_ = new TypesDuo();
                if (StringList.quickEq(t.getDamageType(), _oldName)) {
                    pair_.setDamageType(_newName);
                } else {
                    pair_.setDamageType(t.getDamageType());
                }
                if (StringList.quickEq(t.getPokemonType(), _oldName)) {
                    pair_.setPokemonType(_newName);
                } else {
                    pair_.setPokemonType(t.getPokemonType());
                }
                newList_.add(pair_);
            }
            a.setBreakFoeImmune(newList_);

            ObjectMap<WeatherType, Rate> restore_ = new ObjectMap<WeatherType, Rate>();
            for (WeatherType t : a.getHealHpByTypeIfWeather().getKeys()) {
                Rate value_ = a.getHealHpByTypeIfWeather().getVal(t);
                WeatherType pair_ = new WeatherType();
                pair_.setWeather(t.getWeather());
                if (StringList.quickEq(t.getType(), _oldName)) {
                    pair_.setType(_newName);
                } else {
                    pair_.setType(t.getType());
                }
                restore_.put(pair_, value_);
            }
            a.setHealHpByTypeIfWeather(restore_);
            replaceStr(a.getChgtTypeByWeather(), _oldName, _newName);
            for (StringList l : a.getImmuMoveTypesByWeather().values()) {
                l.replace(_oldName, _newName);
            }
            a.getMultDamageFoe().move(_oldName, _newName);
            if (StringList.quickEq(a.getTypeForMoves(), _oldName)) {
                a.setTypeForMoves(_newName);
            }
            if (!a.getEffectEndRound().isEmpty()) {
                renameTypeEffectEndRound(a.getEffectEndRound().first(),
                        _oldName, _newName);
            }
        }
        ObjectMap<TypesDuo, Rate> table_ = new ObjectMap<TypesDuo, Rate>();
        for (TypesDuo p : tableTypes.getKeys()) {
            Rate value_ = tableTypes.getVal(p);
            TypesDuo pair_ = new TypesDuo();
            if (StringList.quickEq(p.getDamageType(), _oldName)) {
                pair_.setDamageType(_newName);
            } else {
                pair_.setDamageType(p.getDamageType());
            }
            if (StringList.quickEq(p.getPokemonType(), _oldName)) {
                pair_.setPokemonType(_newName);
            } else {
                pair_.setPokemonType(p.getPokemonType());
            }
            table_.put(pair_, value_);
        }
        tableTypes = table_;
        types.replace(_oldName, _newName);
    }

    private static void replace(ListableEntries<EnvironmentType, String> _map,
            String _old, String _new) {
        for (EntryCust<EnvironmentType, String> e : _map.entryList()) {
            if (StringList.quickEq(e.getValue(), _old)) {
                e.setValue(_new);
            }
        }
    }

    private static void replaceStr(ListableEntries<String, String> _map,
            String _old, String _new) {
        for (EntryCust<String, String> e : _map.entryList()) {
            if (StringList.quickEq(e.getValue(), _old)) {
                e.setValue(_new);
            }
        }
    }

    private static void replaceShort(ListableEntries<Short, String> _map,
            String _old, String _new) {
        for (EntryCust<Short, String> e : _map.entryList()) {
            if (StringList.quickEq(e.getValue(), _old)) {
                e.setValue(_new);
            }
        }
    }

    static void renameTypeEffectEndRound(EffectEndRound _effect,
            String _oldName, String _newName) {
        if (_effect instanceof EffectEndRoundIndividual) {
            EffectEndRoundIndividual eff_ = (EffectEndRoundIndividual) _effect;
            eff_.getHealHpByOwnerTypes().move(_oldName, _newName);
        }
    }

    public void renameCategory(String _oldName, String _newName,
            boolean _homonym) {
        if (StringList.quickEq(_newName, AUTRE)) {
            return;
        }
        StringList categories_ = getCategories();
        if (categories_.containsObj(_newName)) {
            return;
        }
        StringList types_ = getTypes();
        if (_homonym) {
            if (moves.contains(_newName)) {
                return;
            }
            if (items.contains(_newName)) {
                return;
            }
            if (pokedex.contains(_newName)) {
                return;
            }
            if (status.contains(_newName)) {
                return;
            }
            if (abilities.contains(_newName)) {
                return;
            }
            if (types_.containsObj(_newName)) {
                return;
            }
            changeNameInNumericExpressions(_oldName, _newName);
        }
        for (AbilityData a : abilities.values()) {
            ObjectMap<StatisticCategory, Byte> mult_ = new ObjectMap<StatisticCategory, Byte>();
            for (StatisticCategory t : a.getMultStatIfDamageCat().getKeys()) {
                byte value_ = a.getMultStatIfDamageCat().getVal(t);
                StatisticCategory pair_ = new StatisticCategory();
                pair_.setStatistic(t.getStatistic());
                if (StringList.quickEq(t.getCategory(), _oldName)) {
                    pair_.setCategory(_newName);
                } else {
                    pair_.setCategory(t.getCategory());
                }
                mult_.put(pair_, value_);
            }
            a.setMultStatIfDamageCat(mult_);
            a.getIncreasedPrio().move(_oldName, _newName);
            ObjectMap<StatisticCategory, Rate> mult2_ = new ObjectMap<StatisticCategory, Rate>();
            for (StatisticCategory t : a.getMultStatIfCat().getKeys()) {
                Rate value_ = a.getMultStatIfCat().getVal(t);
                StatisticCategory pair_ = new StatisticCategory();
                pair_.setStatistic(t.getStatistic());
                if (StringList.quickEq(t.getCategory(), _oldName)) {
                    pair_.setCategory(_newName);
                } else {
                    pair_.setCategory(t.getCategory());
                }
                mult2_.put(pair_, value_);
            }
            a.setMultStatIfCat(mult2_);
        }
        for (MoveData m : moves.values()) {
            if (m instanceof DamagingMoveData) {
                DamagingMoveData m_ = (DamagingMoveData) m;
                if (StringList.quickEq(m_.getCategory(), _oldName)) {
                    m_.setCategory(_newName);
                }
            }
            for (Effect e : m.getEffects()) {
                if (e instanceof EffectTeam) {
                    EffectTeam eff_ = (EffectTeam) e;
                    ObjectMap<CategoryMult, Rate> mult_ = new ObjectMap<CategoryMult, Rate>();
                    for (CategoryMult t : eff_.getMultDamage().getKeys()) {
                        Rate value_ = eff_.getMultDamage().getVal(t);
                        CategoryMult pair_ = new CategoryMult();
                        pair_.setMult(t.getMult());
                        if (StringList.quickEq(t.getCategory(), _oldName)) {
                            pair_.setCategory(_newName);
                        } else {
                            pair_.setCategory(t.getCategory());
                        }
                        mult_.put(pair_, value_);
                    }
                    eff_.setMultDamage(mult_);
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof Berry) {
                Berry b_ = (Berry) o;
                b_.getDamageRateRecoilFoe().move(_oldName, _newName);
            }
        }
        getAllCategories().replace(_oldName, _newName);
        getCategories().replace(_oldName, _newName);
    }

    void changeCategory(String _oldName, String _newName) {
        if (StringList.quickEq(_newName, AUTRE)) {
            return;
        }
        getCategories().removeObj(_oldName);
        getAllCategories().removeObj(_oldName);
        getCategories().add(_newName);
        getAllCategories().add(_newName);
        getCategories().removeDuplicates();
        getAllCategories().removeDuplicates();
    }

    void changeNameInNumericExpressions(String _oldName, String _newName) {
        StringMap<String> replace_ = new StringMap<String>();
        replace_.put(_oldName, _newName);
        for (Item o : items.values()) {
            if (o instanceof Ball) {
                Ball b_ = (Ball) o;
                b_.setCatchingRate(StringList.replaceWordsJoin(
                        b_.getCatchingRate(), replace_));
            }
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                i_.setMultPower(StringList.replaceWordsJoin(i_.getMultPower(),
                        replace_));
                i_.setMultDamage(StringList.replaceWordsJoin(
                        i_.getMultDamage(), replace_));
                for (EntryCust<String, String> s : i_.getFailStatus()
                        .entryList()) {
                    s.setValue(StringList.replaceWordsJoin(s.getValue(),
                            replace_));
                }
                for (EntryCust<Statistic, String> s : i_.getMultStat()
                        .entryList()) {
                    s.setValue(StringList.replaceWordsJoin(s.getValue(),
                            replace_));
                }
                if (!i_.getEffectSending().isEmpty()) {
                    if (i_.getEffectSending().first() instanceof EffectWhileSendingWithStatistic) {
                        EffectWhileSendingWithStatistic e_ = (EffectWhileSendingWithStatistic) i_
                                .getEffectSending().first();
                        EffectStatistic eff_ = e_.getEffect();
                        eff_.setFail(StringList.replaceWordsJoin(
                                eff_.getFail(), replace_));
                        for (EntryCust<Statistic, String> s : eff_
                                .getLocalFailStatis().entryList()) {
                            s.setValue(StringList.replaceWordsJoin(
                                    s.getValue(), replace_));
                        }
                        for (EntryCust<Statistic, String> s : eff_
                                .getLocalFailSwapBoostStatis().entryList()) {
                            s.setValue(StringList.replaceWordsJoin(
                                    s.getValue(), replace_));
                        }
                    }
                }
                if (!i_.getEffectEndRound().isEmpty()) {
                    EffectEndRound e_ = i_.getEffectEndRound().first();
                    e_.setFail(StringList.replaceWordsJoin(e_.getFail(),
                            replace_));
                    e_.setFailEndRound(StringList.replaceWordsJoin(
                            e_.getFailEndRound(), replace_));
                }
            }
        }
        for (AbilityData a : abilities.values()) {
            a.setMultPower(StringList.replaceWordsJoin(a.getMultPower(),
                    replace_));
            a.setMultDamage(StringList.replaceWordsJoin(a.getMultDamage(),
                    replace_));
            for (EntryCust<Statistic, String> s : a.getMultStat().entryList()) {
                s.setValue(StringList.replaceWordsJoin(s.getValue(), replace_));
            }
            for (EntryCust<String, String> s : a.getFailStatus().entryList()) {
                s.setValue(StringList.replaceWordsJoin(s.getValue(), replace_));
            }
            if (!a.getEffectSending().isEmpty()) {
                if (a.getEffectSending().first() instanceof EffectWhileSendingWithStatistic) {
                    EffectWhileSendingWithStatistic e_ = (EffectWhileSendingWithStatistic) a
                            .getEffectSending().first();
                    EffectStatistic eff_ = e_.getEffect();
                    eff_.setFail(StringList.replaceWordsJoin(eff_.getFail(),
                            replace_));
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailStatis().entryList()) {
                        s.setValue(StringList.replaceWordsJoin(s.getValue(),
                                replace_));
                    }
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailSwapBoostStatis().entryList()) {
                        s.setValue(StringList.replaceWordsJoin(s.getValue(),
                                replace_));
                    }
                }
            }
            if (!a.getEffectEndRound().isEmpty()) {
                EffectEndRound e_ = a.getEffectEndRound().first();
                e_.setFail(StringList.replaceWordsJoin(e_.getFail(), replace_));
                e_.setFailEndRound(StringList.replaceWordsJoin(
                        e_.getFailEndRound(), replace_));
            }
        }
        for (MoveData m : moves.values()) {
            m.setAccuracy(StringList.replaceWordsJoin(m.getAccuracy(), replace_));
            for (Effect e : m.getEffects()) {
                e.setFail(StringList.replaceWordsJoin(e.getFail(), replace_));
                if (e instanceof EffectDamage) {
                    EffectDamage eff_ = (EffectDamage) e;
                    eff_.setPower(StringList.replaceWordsJoin(eff_.getPower(),
                            replace_));
                    MonteCarloString newLaw_ = new MonteCarloString();
                    for (String s : eff_.getDamageLaw().events()) {
                        newLaw_.addEvent(StringList.replaceWordsJoin(s,
                                replace_), eff_.getDamageLaw().rate(s));
                    }
                    newLaw_.checkEvents();
                    eff_.setDamageLaw(newLaw_);
                }
                if (e instanceof EffectTeamWhileSendFoe) {
                    EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                    eff_.setDamageRateAgainstFoe(StringList.replaceWordsJoin(
                            eff_.getDamageRateAgainstFoe(), replace_));
                    eff_.setFailSending(StringList.replaceWordsJoin(
                            eff_.getFailSending(), replace_));
                }
                if (e instanceof EffectCommonStatistics) {
                    EffectCommonStatistics eff_ = (EffectCommonStatistics) e;
                    for (EntryCust<Statistic, String> s : eff_.getCommonValue()
                            .entryList()) {
                        s.setValue(StringList.replaceWordsJoin(s.getValue(),
                                replace_));
                    }
                }
                if (e instanceof EffectStatistic) {
                    EffectStatistic eff_ = (EffectStatistic) e;
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailStatis().entryList()) {
                        s.setValue(StringList.replaceWordsJoin(s.getValue(),
                                replace_));
                    }
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailSwapBoostStatis().entryList()) {
                        s.setValue(StringList.replaceWordsJoin(s.getValue(),
                                replace_));
                    }
                }
                if (e instanceof EffectStatus) {
                    EffectStatus eff_ = (EffectStatus) e;
                    for (EntryCust<String, String> s : eff_
                            .getLocalFailStatus().entryList()) {
                        s.setValue(StringList.replaceWordsJoin(s.getValue(),
                                replace_));
                    }
                }
                if (e instanceof EffectFullHpRate) {
                    EffectFullHpRate eff_ = (EffectFullHpRate) e;
                    eff_.setRestoredHp(StringList.replaceWordsJoin(
                            eff_.getRestoredHp(), replace_));
                }
                if (e instanceof EffectEndRound) {
                    EffectEndRound eff_ = (EffectEndRound) e;
                    eff_.setFailEndRound(StringList.replaceWordsJoin(
                            eff_.getFailEndRound(), replace_));
                }
            }
        }
        for (Status s : status.values()) {
            for (EffectEndRoundStatus e : s.getEffectEndRound()) {
                e.setFail(StringList.replaceWordsJoin(e.getFail(), replace_));
                e.setFailEndRound(StringList.replaceWordsJoin(
                        e.getFailEndRound(), replace_));
            }
        }
        for (EffectCombo e : combos.getEffects().values()) {
            for (EffectEndRoundFoe e2_ : e.getEffectEndRound()) {
                e2_.setFail(StringList.replaceWordsJoin(e2_.getFail(), replace_));
                e2_.setFailEndRound(StringList.replaceWordsJoin(
                        e2_.getFailEndRound(), replace_));
            }
        }
    }

    public void editMove(String _name, MoveData _modifiedMove) {
        MoveData move_ = moves.getVal(_name);
        removeMoveFromLists(_name, move_);
        completeMembers(_name, _modifiedMove);
        removeDuplicatesCategoriesMoves();
    }

    public void deletePokemon(String _name) {
        if (used(_name)) {
            return;
        }
        for (Item o : items.values()) {
            if (o instanceof Fossil) {
                Fossil f_ = (Fossil) o;
                if (StringList.quickEq(f_.getPokemon(), _name)) {
                    return;
                }
            }
        }
        for (EntryCust<String, PokemonData> e : pokedex.entryList()) {
            if (StringList.quickEq(e.getKey(), _name)) {
                continue;
            }
            if (StringList.quickEq(e.getValue().getBaseEvo(), _name)) {
                return;
            }
            if (e.getValue().getEvolutions().contains(_name)) {
                return;
            }
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (AreaApparition a : level_.getWildPokemonAreas()) {
                        for (WildPk p2_ : a.getWildPokemon()) {
                            if (StringList.quickEq(p2_.getName(), _name)) {
                                return;
                            }
                        }
                        for (WildPk p2_ : a.getWildPokemonFishing()) {
                            if (StringList.quickEq(p2_.getName(), _name)) {
                                return;
                            }
                        }
                    }
                    for (WildPk p2_ : level_.getLegendaryPks().values()) {
                        if (StringList.quickEq(p2_.getName(), _name)) {
                            return;
                        }
                    }
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    if (StringList
                                            .quickEq(p2_.getName(), _name)) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            if (StringList.quickEq(p2_.getName(), _name)) {
                                return;
                            }
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            if (StringList.quickEq(p2_.getName(), _name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        if (StringList.quickEq(p2_.getName(), _name)) {
                            return;
                        }
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            if (StringList.quickEq(p2_.getName(), _name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        if (StringList.quickEq(p2_.getName(), _name)) {
                            return;
                        }
                    }
                }
            }
        }
        pokedex.removeKey(_name);
    }

    public void deleteMove(String _name) {
        if (used(_name)) {
            return;
        }

        if (!getHmByMove(_name).isEmpty()) {
            return;
        }
        if (!getTmByMove(_name).isEmpty()) {
            return;
        }

        for (PokemonData p : pokedex.values()) {
            for (LevelMove p2_ : p.getLevMoves()) {
                if (StringList.quickEq(p2_.getMove(), _name)) {
                    return;
                }
            }
            if (p.getMoveTutors().containsObj(_name)) {
                return;
            }
            for (Evolution e : p.getEvolutions().values()) {
                if (e instanceof EvolutionMove) {
                    EvolutionMove evo_ = (EvolutionMove) e;
                    if (StringList.quickEq(evo_.getMove(), _name)) {
                        return;
                    }
                }
            }
        }
        for (AbilityData a : abilities.values()) {
            if (a.getImmuMove().containsObj(_name)) {
                return;
            }
            if (a.getIgnFoeTeamMove().containsObj(_name)) {
                return;
            }
            if (a.getImmuWeather().containsObj(_name)) {
                return;
            }
            if (a.getImmuMoveTypesByWeather().contains(_name)) {
                return;
            }
            if (a.getImmuStatus().contains(_name)) {
                return;
            }
            if (a.getChgtTypeByWeather().contains(_name)) {
                return;
            }
            if (a.getHealHpByWeather().contains(_name)) {
                return;
            }
            if (!a.getEffectSending().isEmpty()) {
                if (StringList.quickEq(a.getEffectSending().first()
                        .getEnabledWeather(), _name)) {
                    return;
                }
            }
            for (WeatherType p : a.getHealHpByTypeIfWeather().getKeys()) {
                if (StringList.quickEq(p.getWeather(), _name)) {
                    return;
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof ItemForBattle) {
                ItemForBattle obj_ = (ItemForBattle) o;
                if (obj_.getIncreasingMaxNbRoundTrap().contains(_name)) {
                    return;
                }
                if (obj_.getIncreasingMaxNbRoundGlobalMove().contains(_name)) {
                    return;
                }
                if (obj_.getIncreasingMaxNbRoundTeamMove().contains(_name)) {
                    return;
                }
                if (!obj_.getEffectSending().isEmpty()) {
                    if (StringList.quickEq(obj_.getEffectSending().first()
                            .getEnabledWeather(), _name)) {
                        return;
                    }
                }
            }
        }
        for (StringList l : combos.getEffects().getKeys()) {
            EffectCombo eff_ = combos.getEffects().getVal(l);
            if (eff_.estActifEquipe()) {
                EffectTeam eff2_ = eff_.getTeamMove().first();
                if (eff2_.getUnusableMoves().containsObj(_name)) {
                    return;
                }
                if (eff2_.getDisableFoeTeamEffects().containsObj(_name)) {
                    return;
                }
            }
            if (l.containsObj(_name)) {
                return;
            }
        }
        for (EntryCust<String, MoveData> m : moves.entryList()) {
            if (StringList.quickEq(m.getKey(), _name)) {
                continue;
            }
            if (m.getValue().getAchieveDisappearedPkUsingMove()
                    .containsObj(_name)) {
                return;
            }
            for (Effect e : m.getValue().getEffects()) {
                if (e instanceof EffectUnprotectFromTypes) {
                    EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) e;
                    if (eff_.getDisableImmuFromMoves().containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectCopyMove) {
                    EffectCopyMove eff_ = (EffectCopyMove) e;
                    if (eff_.getMovesNotToBeCopied().containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectTeam) {
                    EffectTeam eff_ = (EffectTeam) e;
                    if (eff_.getUnusableMoves().containsObj(_name)) {
                        return;
                    }
                    if (eff_.getDisableFoeTeamEffects().containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    if (eff_.getCancelEffects().containsObj(_name)) {
                        return;
                    }
                    if (eff_.getMultPowerMoves().contains(_name)) {
                        return;
                    }
                    if (eff_.getUnusableMoves().containsObj(_name)) {
                        return;
                    }
                    if (eff_.getMovesUsedByTargetedFighters()
                            .containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectInvoke) {
                    EffectInvoke eff_ = (EffectInvoke) e;
                    if (eff_.getMovesNotToBeInvoked().containsObj(_name)) {
                        return;
                    }
                    if (containsStr(eff_.getMoveFctEnv().values(), _name)) {
                        return;
                    }
                    if (containsStr(eff_.getInvokingMoveByUserTypes().values(),
                            _name)) {
                        return;
                    }
                }
            }
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    if (p2_.getMoves().containsObj(_name)) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            if (p2_.getMoves().containsObj(_name)) {
                                return;
                            }
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            if (p2_.getMoves().containsObj(_name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        if (p2_.getMoves().containsObj(_name)) {
                            return;
                        }
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            if (p2_.getMoves().containsObj(_name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        if (p2_.getMoves().containsObj(_name)) {
                            return;
                        }
                    }
                }
            }
        }
        removeMoveFromLists(_name, moves.getVal(_name));
        moves.removeKey(_name);
    }

    public void deleteObject(String _name) {
        if (used(_name)) {
            return;
        }
        for (MoveData m : moves.values()) {
            if (m.getTypesByOwnedItem().contains(_name)) {
                return;
            }
            if (m.getSecEffectsByItem().contains(_name)) {
                return;
            }
        }
        for (PokemonData p : pokedex.values()) {
            for (Evolution e : p.getEvolutions().values()) {
                if (e instanceof EvolutionStone) {
                    EvolutionStone evo_ = (EvolutionStone) e;
                    if (StringList.quickEq(evo_.getStone(), _name)) {
                        return;
                    }
                }
                if (e instanceof EvolutionItem) {
                    EvolutionItem evo_ = (EvolutionItem) e;
                    if (StringList.quickEq(evo_.getItem(), _name)) {
                        return;
                    }
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof HealingItem) {
                HealingItem h_ = (HealingItem) o;
                if (h_.getHappiness().contains(_name)) {
                    return;
                }
            }
            if (o instanceof Boost) {
                Boost b_ = (Boost) o;
                if (b_.getHappiness().contains(_name)) {
                    return;
                }
            }
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (AreaApparition a : level_.getWildPokemonAreas()) {
                        for (WildPk p2_ : a.getWildPokemon()) {
                            if (StringList.quickEq(p2_.getItem(), _name)) {
                                return;
                            }
                        }
                        for (WildPk p2_ : a.getWildPokemonFishing()) {
                            if (StringList.quickEq(p2_.getItem(), _name)) {
                                return;
                            }
                        }
                    }
                    for (WildPk p2_ : level_.getLegendaryPks().values()) {
                        if (StringList.quickEq(p2_.getItem(), _name)) {
                            return;
                        }
                    }
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    if (StringList
                                            .quickEq(p2_.getItem(), _name)) {
                                        return;
                                    }
                                }
                            }
                        }
                        if (t instanceof DealerItem) {
                            DealerItem d_ = (DealerItem) t;
                            if (d_.getItems().containsObj(_name)) {
                                return;
                            }
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            if (StringList.quickEq(p2_.getItem(), _name)) {
                                return;
                            }
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            if (StringList.quickEq(p2_.getItem(), _name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        if (StringList.quickEq(p2_.getItem(), _name)) {
                            return;
                        }
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            if (StringList.quickEq(p2_.getItem(), _name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorPokemonCenter) {
                    LevelIndoorPokemonCenter level_ = (LevelIndoorPokemonCenter) l;
                    for (Person g : level_.getGerants().values()) {
                        if (g instanceof Seller) {
                            Seller s_ = (Seller) g;
                            if (s_.getItems().containsObj(_name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        if (StringList.quickEq(p2_.getItem(), _name)) {
                            return;
                        }
                    }
                }
            }
        }
        items.removeKey(_name);
    }

    public void deleteAbility(String _name) {
        if (used(_name)) {
            return;
        }
        for (MoveData m : moves.values()) {
            for (Effect e : m.getEffects()) {
                if (e instanceof EffectSwitchAbilities) {
                    EffectSwitchAbilities eff_ = (EffectSwitchAbilities) e;
                    if (StringList.quickEq(eff_.getConstAbility(), _name)) {
                        return;
                    }
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    if (eff_.getCancelProtectingAbilities().containsObj(_name)) {
                        return;
                    }
                }
            }
        }
        /*
         * for (Item o: items.values()) { if (o instanceof ItemForBattle) {
         * ItemForBattle i_ = (ItemForBattle) o; if
         * (i_.getSansEffetCapacite().containsObj(_name)) { return; } } }
         */
        for (PokemonData p : pokedex.values()) {
            if (p.getAbilities().containsObj(_name)) {
                return;
            }
        }
        for (AbilityData a : abilities.values()) {
            if (a.getImmuAbility().containsObj(_name)) {
                return;
            }
            if (a.getIgnAbility().containsObj(_name)) {
                return;
            }
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    for (AreaApparition a : level_.getWildPokemonAreas()) {
                        for (WildPk p2_ : a.getWildPokemon()) {
                            if (StringList.quickEq(p2_.getAbility(), _name)) {
                                return;
                            }
                        }
                        for (WildPk p2_ : a.getWildPokemonFishing()) {
                            if (StringList.quickEq(p2_.getAbility(), _name)) {
                                return;
                            }
                        }
                    }
                    for (WildPk p2_ : level_.getLegendaryPks().values()) {
                        if (StringList.quickEq(p2_.getAbility(), _name)) {
                            return;
                        }
                    }
                    for (CharacterInRoadCave t : level_.getCharacters()
                            .values()) {
                        if (t instanceof TrainerMultiFights) {
                            TrainerMultiFights t_ = (TrainerMultiFights) t;
                            for (PokemonTeam t2_ : t_.getTeamsRewards()) {
                                for (PkTrainer p2_ : t2_.getTeam()) {
                                    if (StringList.quickEq(p2_.getAbility(),
                                            _name)) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                    for (DualFight d : level_.getDualFights().values()) {
                        Ally a_ = d.getAlly();
                        for (PkTrainer p2_ : a_.getTeam()) {
                            if (StringList.quickEq(p2_.getAbility(), _name)) {
                                return;
                            }
                        }
                        TempTrainer tmp_ = d.getFoeTrainer();
                        for (PkTrainer p2_ : tmp_.getTeam()) {
                            if (StringList.quickEq(p2_.getAbility(), _name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelIndoorGym) {
                    LevelIndoorGym level_ = (LevelIndoorGym) l;
                    for (PkTrainer p2_ : level_.getGymLeader().getTeam()) {
                        if (StringList.quickEq(p2_.getAbility(), _name)) {
                            return;
                        }
                    }
                    for (GymTrainer t : level_.getGymTrainers().values()) {
                        for (PkTrainer p2_ : t.getTeam()) {
                            if (StringList.quickEq(p2_.getAbility(), _name)) {
                                return;
                            }
                        }
                    }
                }
                if (l instanceof LevelLeague) {
                    LevelLeague level_ = (LevelLeague) l;
                    for (PkTrainer p2_ : level_.getTrainer().getTeam()) {
                        if (StringList.quickEq(p2_.getAbility(), _name)) {
                            return;
                        }
                    }
                }
            }
        }
        abilities.removeKey(_name);
    }

    public void deleteStatus(String _name) {
        if (used(_name)) {
            return;
        }
        for (MoveData m : moves.values()) {
            if (m.getDeletedStatus().containsObj(_name)) {
                return;
            }
            if (m.getRequiredStatus().containsObj(_name)) {
                return;
            }
            for (Effect e : m.getEffects()) {
                if (e instanceof EffectEndRound) {
                    if (usedStatusEffectEndRound((EffectEndRound) e, _name)) {
                        return;
                    }
                }
                if (e instanceof EffectTeam) {
                    EffectTeam eff_ = (EffectTeam) e;
                    if (eff_.getProtectAgainstStatus().containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectTeamWhileSendFoe) {
                    EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                    if (containsStr(eff_.getStatusByNbUses().values(), _name)) {
                        return;
                    }
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    if (eff_.getPreventStatus().containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectStatus) {
                    EffectStatus eff_ = (EffectStatus) e;
                    if (eff_.getLocalFailStatus().contains(_name)) {
                        return;
                    }
                    for (String s : eff_.getLawStatus().events()) {
                        if (StringList.quickEq(s, _name)) {
                            return;
                        }
                    }
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof Berry) {
                Berry b_ = (Berry) o;
                if (b_.getHealStatus().containsObj(_name)) {
                    return;
                }
            }
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                if (i_.getFailStatus().contains(_name)) {
                    return;
                }
                if (i_.getImmuStatus().containsObj(_name)) {
                    return;
                }
                if (i_.getSynchroStatus().containsObj(_name)) {
                    return;
                }
                if (!i_.getEffectEndRound().isEmpty()) {
                    if (usedStatusEffectEndRound(
                            i_.getEffectEndRound().first(), _name)) {
                        return;
                    }
                }
            }
            if (o instanceof HealingStatus) {
                HealingStatus s_ = (HealingStatus) o;
                if (s_.getStatus().containsObj(_name)) {
                    return;
                }
            }
        }
        for (AbilityData a : abilities.values()) {
            if (a.getFailStatus().contains(_name)) {
                return;
            }
            if (!a.getEffectEndRound().isEmpty()) {
                if (usedStatusEffectEndRound(a.getEffectEndRound().first(),
                        _name)) {
                    return;
                }
            }
        }
        status.removeKey(_name);
    }

    static boolean usedStatusEffectEndRound(EffectEndRound _effect, String _name) {
        if (_effect instanceof EffectEndRoundIndividual) {
            EffectEndRoundIndividual eff_ = (EffectEndRoundIndividual) _effect;
            if (eff_.getMultDamageStatus().contains(_name)) {
                return true;
            }
            if (StringList.quickEq(eff_.getUserStatusEndRound(), _name)) {
                return true;
            }
        }
        if (_effect instanceof EffectEndRoundMultiRelation) {
            EffectEndRoundMultiRelation eff_ = (EffectEndRoundMultiRelation) _effect;
            if (eff_.getMultDamageStatus().contains(_name)) {
                return true;
            }
            if (eff_.getDamageByStatus().contains(_name)) {
                return true;
            }
        }
        if (_effect instanceof EffectEndRoundSingleStatus) {
            EffectEndRoundSingleStatus eff_ = (EffectEndRoundSingleStatus) _effect;
            if (eff_.getMultDamageStatus().contains(_name)) {
                return true;
            }
        }
        return false;
    }

    public void deleteType(String _name) {
        if (used(_name)) {
            return;
        }
        for (PokemonData p : pokedex.values()) {
            if (p.getTypes().containsObj(_name)) {
                return;
            }
        }
        for (MoveData p : moves.values()) {
            if (p.getTypes().containsObj(_name)) {
                return;
            }
            if (p.getBoostedTypes().containsObj(_name)) {
                return;
            }
            if (containsStr(p.getTypesByWeather().values(), _name)) {
                return;
            }
            if (containsStr(p.getTypesByOwnedItem().values(), _name)) {
                return;
            }
            for (Effect e : p.getEffects()) {
                if (e instanceof EffectUnprotectFromTypes) {
                    EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) e;
                    if (eff_.getAttackTargetWithTypes().containsObj(_name)) {
                        return;
                    }
                    if (eff_.getDisableImmuAgainstTypes().containsObj(_name)) {
                        return;
                    }
                    for (TypesDuo t : eff_.getTypes()) {
                        if (StringList.quickEq(t.getDamageType(), _name)) {
                            return;
                        }
                        if (StringList.quickEq(t.getPokemonType(), _name)) {
                            return;
                        }
                    }
                }
                if (e instanceof EffectSwitchTypes) {
                    EffectSwitchTypes eff_ = (EffectSwitchTypes) e;
                    if (eff_.getConstTypes().containsObj(_name)) {
                        return;
                    }
                    if (containsStr(eff_.getChgtTypeByEnv().values(), _name)) {
                        return;
                    }
                }
                if (e instanceof EffectTeamWhileSendFoe) {
                    EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                    if (eff_.getDeletedByFoeTypes().containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    if (eff_.getImmuneTypes().containsObj(_name)) {
                        return;
                    }
                    if (eff_.getMultDamagePrepaRound().contains(_name)) {
                        return;
                    }
                    if (eff_.getMultDamageTypesMoves().contains(_name)) {
                        return;
                    }
                    if (eff_.getDisableImmuAgainstTypes().containsObj(_name)) {
                        return;
                    }
                    for (TypesDuo t : eff_.getEfficiencyMoves().getKeys()) {
                        if (StringList.quickEq(t.getDamageType(), _name)) {
                            return;
                        }
                        if (StringList.quickEq(t.getPokemonType(), _name)) {
                            return;
                        }
                    }
                    for (StatisticType t : eff_.getMultStatIfContainsType()
                            .getKeys()) {
                        if (StringList.quickEq(t.getType(), _name)) {
                            return;
                        }
                    }
                }
                if (e instanceof EffectProtectFromTypes) {
                    EffectProtectFromTypes eff_ = (EffectProtectFromTypes) e;
                    if (eff_.getImmuAgainstTypes().containsObj(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectMultUsedMovePower) {
                    EffectMultUsedMovePower eff_ = (EffectMultUsedMovePower) e;
                    if (eff_.getMultMovePowerFctType().contains(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectMultSufferedMovePower) {
                    EffectMultSufferedMovePower eff_ = (EffectMultSufferedMovePower) e;
                    if (eff_.getMultMovePowerFctType().contains(_name)) {
                        return;
                    }
                }
                if (e instanceof EffectEndRound) {
                    if (usedTypeEffectEndRound((EffectEndRound) e, _name)) {
                        return;
                    }
                }
            }
        }
        for (Item o : items.values()) {
            if (o instanceof Berry) {
                Berry b_ = (Berry) o;
                if (b_.getMultFoesDamage().contains(_name)) {
                    return;
                }
            }
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                if (i_.getTypesPk().containsObj(_name)) {
                    return;
                }
                if (i_.getImmuTypes().containsObj(_name)) {
                    return;
                }
                if (!i_.getEffectEndRound().isEmpty()) {
                    if (usedTypeEffectEndRound(i_.getEffectEndRound().first(),
                            _name)) {
                        return;
                    }
                }
            }
        }
        for (AbilityData a : abilities.values()) {
            for (StatisticType t : a.getMultStatIfDamgeType().getKeys()) {
                if (StringList.quickEq(t.getType(), _name)) {
                    return;
                }
            }
            for (TypesDuo t : a.getBreakFoeImmune()) {
                if (StringList.quickEq(t.getDamageType(), _name)) {
                    return;
                }
                if (StringList.quickEq(t.getPokemonType(), _name)) {
                    return;
                }
            }
            for (WeatherType t : a.getHealHpByTypeIfWeather().getKeys()) {
                if (StringList.quickEq(t.getType(), _name)) {
                    return;
                }
            }
            if (containsStr(a.getChgtTypeByWeather().values(), _name)) {
                return;
            }
            for (StringList l : a.getImmuMoveTypesByWeather().values()) {
                if (l.containsObj(_name)) {
                    return;
                }
            }
            if (a.getMultDamageFoe().contains(_name)) {
                return;
            }
            if (StringList.quickEq(a.getTypeForMoves(), _name)) {
                return;
            }
            if (!a.getEffectEndRound().isEmpty()) {
                if (usedTypeEffectEndRound(a.getEffectEndRound().first(), _name)) {
                    return;
                }
            }
        }
        ObjectMap<TypesDuo, Rate> table_ = new ObjectMap<TypesDuo, Rate>();
        for (TypesDuo p : tableTypes.getKeys()) {
            if (StringList.quickEq(p.getDamageType(), _name)) {
                continue;
            }
            if (StringList.quickEq(p.getPokemonType(), _name)) {
                continue;
            }
            Rate value_ = tableTypes.getVal(p);
            table_.put(p, value_);
        }
        tableTypes = table_;
        types.removeObj(_name);
    }

    static boolean containsStr(Listable<String> _l, String _s) {
        for (String s : _l) {
            if (StringList.quickEq(s, _s)) {
                return true;
            }
        }
        return false;
    }

    static boolean usedTypeEffectEndRound(EffectEndRound _effect, String _name) {
        if (_effect instanceof EffectEndRoundIndividual) {
            EffectEndRoundIndividual eff_ = (EffectEndRoundIndividual) _effect;
            if (eff_.getHealHpByOwnerTypes().contains(_name)) {
                return true;
            }
        }
        return false;
    }

    public void deleteTm(short _tm) {
        for (PokemonData p : pokedex.values()) {
            p.getTechnicalMoves().removeObj(_tm);
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    EqList<Point> keys_ = new EqList<Point>();
                    for (Point p2_ : level_.getTm().getKeys()) {
                        if (!Numbers.eq(level_.getTm(p2_), _tm)) {
                            continue;
                        }
                        keys_.add(p2_);
                    }
                    for (Point p2_ : keys_) {
                        level_.getTm().removeKey(p2_);
                    }
                }
                if (l instanceof LevelIndoorPokemonCenter) {
                    LevelIndoorPokemonCenter level_ = (LevelIndoorPokemonCenter) l;
                    for (Person p2_ : level_.getGerants().values()) {
                        if (p2_ instanceof Seller) {
                            Seller s_ = (Seller) p2_;
                            s_.getTm().removeObj(_tm);
                        }
                        if (p2_ instanceof DealerItem) {
                            DealerItem s_ = (DealerItem) p2_;
                            s_.getTechnicalMoves().removeObj(_tm);
                        }
                    }
                }
            }
        }
        tm.removeKey(_tm);
    }

    public void deleteHm(short _tm) {
        for (PokemonData p : pokedex.values()) {
            p.getHiddenMoves().removeObj(_tm);
        }
        for (Place p : map.getPlaces().values()) {
            for (Level l : p.getLevelsMap().values()) {
                if (l instanceof LevelWithWildPokemon) {
                    LevelWithWildPokemon level_ = (LevelWithWildPokemon) l;
                    EqList<Point> keys_ = new EqList<Point>();
                    for (Point p2_ : level_.getHm().getKeys()) {
                        if (!Numbers.eq(level_.getHm(p2_), _tm)) {
                            continue;
                        }
                        keys_.add(p2_);
                    }
                    for (Point p2_ : keys_) {
                        level_.getHm().removeKey(p2_);
                    }
                }
            }
        }
        hm.removeKey(_tm);
    }

    boolean used(String _name) {

        for (Item o : items.values()) {
            if (o instanceof Ball) {
                Ball b_ = (Ball) o;
                if (containsWord(b_.getCatchingRate(), _name)) {
                    return true;
                }
            }
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                if (containsWord(i_.getMultPower(), _name)) {
                    return true;
                }
                if (containsWord(i_.getMultDamage(), _name)) {
                    return true;
                }
                for (EntryCust<Statistic, String> s : i_.getMultStat()
                        .entryList()) {
                    if (containsWord(s.getValue(), _name)) {
                        return true;
                    }
                }
                if (!i_.getEffectSending().isEmpty()) {
                    if (i_.getEffectSending().first() instanceof EffectWhileSendingWithStatistic) {
                        EffectWhileSendingWithStatistic e_ = (EffectWhileSendingWithStatistic) i_
                                .getEffectSending().first();
                        EffectStatistic eff_ = e_.getEffect();
                        if (containsWord(eff_.getFail(), _name)) {
                            return true;
                        }
                        for (EntryCust<Statistic, String> s : eff_
                                .getLocalFailStatis().entryList()) {
                            if (containsWord(s.getValue(), _name)) {
                                return true;
                            }
                        }
                        for (EntryCust<Statistic, String> s : eff_
                                .getLocalFailSwapBoostStatis().entryList()) {
                            if (containsWord(s.getValue(), _name)) {
                                return true;
                            }
                        }
                    }
                }
                if (!i_.getEffectEndRound().isEmpty()) {
                    EffectEndRound e_ = i_.getEffectEndRound().first();
                    if (containsWord(e_.getFail(), _name)) {
                        return true;
                    }
                    if (containsWord(e_.getFailEndRound(), _name)) {
                        return true;
                    }
                }
            }
        }
        for (AbilityData a : abilities.values()) {
            if (containsWord(a.getMultPower(), _name)) {
                return true;
            }
            if (containsWord(a.getMultDamage(), _name)) {
                return true;
            }
            for (EntryCust<Statistic, String> s : a.getMultStat().entryList()) {
                if (containsWord(s.getValue(), _name)) {
                    return true;
                }
            }
            for (EntryCust<String, String> s : a.getFailStatus().entryList()) {
                if (containsWord(s.getValue(), _name)) {
                    return true;
                }
            }
            if (!a.getEffectSending().isEmpty()) {
                if (a.getEffectSending().first() instanceof EffectWhileSendingWithStatistic) {
                    EffectWhileSendingWithStatistic e_ = (EffectWhileSendingWithStatistic) a
                            .getEffectSending().first();
                    EffectStatistic eff_ = e_.getEffect();
                    if (containsWord(eff_.getFail(), _name)) {
                        return true;
                    }
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailStatis().entryList()) {
                        if (containsWord(s.getValue(), _name)) {
                            return true;
                        }
                    }
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailSwapBoostStatis().entryList()) {
                        if (containsWord(s.getValue(), _name)) {
                            return true;
                        }
                    }
                }
            }
            if (!a.getEffectEndRound().isEmpty()) {
                EffectEndRound e_ = a.getEffectEndRound().first();
                if (containsWord(e_.getFail(), _name)) {
                    return true;
                }
                if (containsWord(e_.getFailEndRound(), _name)) {
                    return true;
                }
            }
        }
        for (MoveData m : moves.values()) {
            if (containsWord(m.getAccuracy(), _name)) {
                return true;
            }
            for (Effect e : m.getEffects()) {
                if (containsWord(e.getFail(), _name)) {
                    return true;
                }
                if (e instanceof EffectDamage) {
                    EffectDamage eff_ = (EffectDamage) e;
                    if (containsWord(eff_.getPower(), _name)) {
                        return true;
                    }
                    MonteCarloString newLaw_ = new MonteCarloString();
                    for (String s : eff_.getDamageLaw().events()) {
                        if (containsWord(s, _name)) {
                            return true;
                        }
                    }
                    newLaw_.checkEvents();
                    eff_.setDamageLaw(newLaw_);
                }
                if (e instanceof EffectTeamWhileSendFoe) {
                    EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                    if (containsWord(eff_.getDamageRateAgainstFoe(), _name)) {
                        return true;
                    }
                    if (containsWord(eff_.getFailSending(), _name)) {
                        return true;
                    }
                }
                if (e instanceof EffectCommonStatistics) {
                    EffectCommonStatistics eff_ = (EffectCommonStatistics) e;
                    for (EntryCust<Statistic, String> s : eff_.getCommonValue()
                            .entryList()) {
                        if (containsWord(s.getValue(), _name)) {
                            return true;
                        }
                    }
                }
                if (e instanceof EffectStatistic) {
                    EffectStatistic eff_ = (EffectStatistic) e;
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailStatis().entryList()) {
                        if (containsWord(s.getValue(), _name)) {
                            return true;
                        }
                    }
                    for (EntryCust<Statistic, String> s : eff_
                            .getLocalFailSwapBoostStatis().entryList()) {
                        if (containsWord(s.getValue(), _name)) {
                            return true;
                        }
                    }
                }
                if (e instanceof EffectStatus) {
                    EffectStatus eff_ = (EffectStatus) e;
                    for (EntryCust<String, String> s : eff_
                            .getLocalFailStatus().entryList()) {
                        if (containsWord(s.getValue(), _name)) {
                            return true;
                        }
                    }
                }
                if (e instanceof EffectFullHpRate) {
                    EffectFullHpRate eff_ = (EffectFullHpRate) e;
                    if (containsWord(eff_.getRestoredHp(), _name)) {
                        return true;
                    }
                }
                if (e instanceof EffectEndRound) {
                    EffectEndRound eff_ = (EffectEndRound) e;
                    if (containsWord(eff_.getFailEndRound(), _name)) {
                        return true;
                    }
                }
            }
        }
        for (Status s : status.values()) {
            for (EffectEndRoundStatus e : s.getEffectEndRound()) {
                if (containsWord(e.getFail(), _name)) {
                    return true;
                }
                if (containsWord(e.getFailEndRound(), _name)) {
                    return true;
                }
            }
        }
        for (EffectCombo e : combos.getEffects().values()) {
            for (EffectEndRoundFoe e2_ : e.getEffectEndRound()) {
                if (containsWord(e2_.getFail(), _name)) {
                    return true;
                }
                if (containsWord(e2_.getFailEndRound(), _name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean containsWord(String _string, String _word) {
        StringList tokens_ = StringList.getWordsSeparators(_string);
        return tokens_.containsObj(_word);
    }

    public StringMap<String> getTextFiles() {
        return getTextFiles(true);
    }

    public StringMap<String> getTextFiles(boolean _addImages) {
        StringMap<String> files_ = new StringMap<String>();
        for (String n : pokedex.getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setPokemonData(pokedex
                    .getVal(n));
            files_.put(StringList.concat(POKEDEX_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : moves.getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setMoveData(moves
                    .getVal(n));
            files_.put(StringList.concat(MOVES_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : items.getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setItem(items.getVal(n));
            files_.put(StringList.concat(ITEMS_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : abilities.getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setAbilityData(abilities
                    .getVal(n));
            files_.put(StringList.concat(ABILITIES_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        for (String n : status.getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setStatus(status
                    .getVal(n));
            files_.put(StringList.concat(STATUS_FOLDER, SEPARATOR_FILES, n,
                    FILES_RES_EXT), file_);
        }
        String file_ = DocumentWriterAikiCoreUtil.setCombos(combos);
        files_.put(COMBOS, file_);
        file_ = DocumentWriterAikiCoreUtil.setDataMap(map);
        files_.put(MAP_FILE, file_);
        StringList lines_ = new StringList();
        for (String s : constNum.getKeys()) {
            lines_.add(StringList.concat(s, TAB, constNum.getVal(s)
                    .toNumberString()));
        }
        files_.put(CONST_NUM, lines_.join(RETURN_LINE));
        lines_ = new StringList();
        for (String s : typesColors.getKeys()) {
            lines_.add(StringList.concat(s, TAB, typesColors.getVal(s)));
        }
        files_.put(StringList.concat(TYPES_COLOR_CODE, IMG_FILES_RES_EXT_TXT),
                lines_.join(RETURN_LINE));
        lines_ = new StringList();
        lines_.add(StringList.concat(DEF_MOVE, TAB, defMove));
        lines_.add(StringList.concat(RATE_BOOST, TAB, rateBoost));
        lines_.add(StringList.concat(RATE_BOOST_CRITICAL_HIT, TAB,
                rateBoostCriticalHit));
        lines_.add(StringList.concat(RATE_FLEEING, TAB, rateFleeing));
        lines_.add(StringList.concat(RATE_CATCHING, TAB, rateCatching));
        lines_.add(StringList.concat(BALL_DEF, TAB, ballDef));
        lines_.add(StringList.concat(DEFAULT_EGG_GROUP, TAB, defaultEggGoup));
        lines_.add(StringList.concat(DAMAGE_FORMULA, TAB, damageFormula));

        files_.put(CONST_NOT_NUM, lines_.join(RETURN_LINE));
        StringList types_ = new StringList();
        for (TypesDuo p : tableTypes.getKeys()) {
            types_.add(p.getDamageType());
        }
        types_.removeDuplicates();
        String output_ = StringList.concat(TAB, types_.join(TAB));
        for (String pkType_ : types_) {
            output_ = StringList.concat(output_, RETURN_LINE, pkType_);
            for (String damageType_ : types_) {
                output_ = StringList.concat(output_, TAB,
                        tableTypes.getVal(new TypesDuo(damageType_, pkType_))
                                .toNumberString());
            }
        }
        files_.put(TABLE_TYPES, output_);
        StringList linesCourbes_ = new StringList();
        for (ExpType c : expGrowth.getKeys()) {
            linesCourbes_.add(StringList.concat(c.name(), TAB,
                    expGrowth.getVal(c)));
        }
        files_.put(COURBE_PTS_EXP, linesCourbes_.join(RETURN_LINE));
        StringList rates_ = new StringList();
        for (DifficultyWinPointsFight c : rates.getKeys()) {
            rates_.add(StringList.concat(c.name(), TAB, rates.getVal(c)));
        }
        files_.put(RATE_WON_POINTS, rates_.join(RETURN_LINE));
        StringList linesLaws_ = new StringList();
        for (DifficultyModelLaw k : lawsDamageRate.getKeys()) {
            LawNumber value_ = lawsDamageRate.getVal(k);
            StringList lawValues_ = new StringList();
            for (Rate event_ : value_.getLaw().events()) {
                lawValues_.add(StringList.concat(event_.toNumberString(),
                        SEPARATOR_RAND_EVENTS, value_.getLaw().rate(event_)
                                .toNumberString()));
            }
            linesLaws_.add(StringList.concat(k.name(), TAB,
                    lawValues_.join(SEPARATOR_RAND), TAB,
                    Long.toString(value_.getNumber())));
        }
        files_.put(LOIS_RANDOM, linesLaws_.join(RETURN_LINE));
        StringList linesTmHm_ = new StringList();
        for (short k : hm.getKeys()) {
            linesTmHm_.add(StringList.concat(CS, Long.toString(k), TAB,
                    hm.getVal(k)));
        }
        for (short k : tm.getKeys()) {
            linesTmHm_.add(StringList.concat(CT, Long.toString(k), TAB,
                    tm.getVal(k), TAB, tmPrice.getVal(k).toNumberString()));
        }
        files_.put(CT_CS_FILE, linesTmHm_.join(RETURN_LINE));
        for (String l : translatedCategories.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> genders_ = translatedCategories.getVal(l);
            for (String g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CATEGORIES);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedGenders.getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<Gender, String> genders_ = translatedGenders.getVal(l);
            for (Gender g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_GENDERS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedBooleans.getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<SelectedBoolean, String> genders_ = translatedBooleans
                    .getVal(l);
            for (SelectedBoolean g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_BOOLEANS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedDiffWinPts.getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<DifficultyWinPointsFight, String> genders_ = translatedDiffWinPts
                    .getVal(l);
            for (DifficultyWinPointsFight g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_DIFF_WIN_PTS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedDiffModelLaw.getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<DifficultyModelLaw, String> genders_ = translatedDiffModelLaw
                    .getVal(l);
            for (DifficultyModelLaw g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList
                    .concat(fileName_, TRANSLATION_DIFF_MODEL_LAW);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedEnvironment.getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<EnvironmentType, String> statistics_ = translatedEnvironment
                    .getVal(l);
            for (EnvironmentType g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ENVIRONMENTS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedStatistics.getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<Statistic, String> statistics_ = translatedStatistics
                    .getVal(l);
            for (Statistic g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATISTICS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedTargets.getKeys()) {
            StringList linesGenders_ = new StringList();
            EnumMap<TargetChoice, String> statistics_ = translatedTargets
                    .getVal(l);
            for (TargetChoice g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.name());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TARGETS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedTypes.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> statistics_ = translatedTypes.getVal(l);
            for (String g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_TYPES);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedPokemon.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> pokemon_ = translatedPokemon.getVal(l);
            for (String g : pokemon_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(pokemon_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_POKEMON);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedMoves.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> moves_ = translatedMoves.getVal(l);
            for (String g : moves_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(moves_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MOVES);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedItems.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> items_ = translatedItems.getVal(l);
            for (String g : items_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(items_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ITEMS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedAbilities.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> abilities_ = translatedAbilities.getVal(l);
            for (String g : abilities_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(abilities_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_ABILITIES);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedStatus.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = translatedStatus.getVal(l);
            for (String g : status_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_STATUS);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedFctMath.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = translatedFctMath.getVal(l);
            for (String g : status_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_MATH);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : translatedClassesDescriptions.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = translatedClassesDescriptions.getVal(l);
            for (String g : status_.getKeys()) {

                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_CLASSES);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }
        for (String l : litterals.getKeys()) {
            StringList linesGenders_ = new StringList();
            StringMap<String> status_ = litterals.getVal(l);
            for (String g : status_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g);
                words_.add(DocumentBuilder.encodeToHtml(status_.getVal(g)));
                linesGenders_.add(words_.join(TAB));
            }
            String fileName_ = StringList.concat(TRANSLATION_FOLDER,
                    SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, l, SEPARATOR_FILES);
            fileName_ = StringList.concat(fileName_, TRANSLATION_LITTERAL);
            files_.put(fileName_, linesGenders_.join(RETURN_LINE));
        }

        if (_addImages) {
            for (String n : animStatis.getKeys()) {
                files_.put(StringList.concat(ANIM_STATIS, SEPARATOR_FILES, n,
                        IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                        .getStringByImage(animStatis.getVal(n)));
            }
            for (String n : animStatus.getKeys()) {
                files_.put(StringList.concat(ANIM_STATUS, SEPARATOR_FILES, n,
                        IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                        .getStringByImage(animStatus.getVal(n)));
            }
            files_.put(ANIM_ABSORB,
                    BaseSixtyFourUtil.getStringByImage(animAbsorb));
            for (String n : images.getKeys()) {
                files_.put(
                        StringList.concat(IMAGES_FOLDER, SEPARATOR_FILES, n),
                        BaseSixtyFourUtil.getStringByImage(images.getVal(n)));
            }
            for (String n : miniMap.getKeys()) {
                files_.put(
                        StringList.concat(MINI_MAP_FOLDER, SEPARATOR_FILES, n),
                        BaseSixtyFourUtil.getStringByImage(miniMap.getVal(n)));
            }
            for (String n : links.getKeys()) {
                files_.put(StringList.concat(LINKS_FOLDER, SEPARATOR_FILES, n),
                        BaseSixtyFourUtil.getStringByImage(links.getVal(n)));
            }
            for (String n : people.getKeys()) {
                files_.put(
                        StringList.concat(PEOPLE_FOLDER, SEPARATOR_FILES, n),
                        BaseSixtyFourUtil.getStringByImage(people.getVal(n)));
            }
            StringList linesHeros_;
            linesHeros_ = new StringList();
            for (ImageHeroKey k : frontHeros.getKeys()) {
                String image_ = BaseSixtyFourUtil.getStringByImage(frontHeros
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
                    StringList.concat(HERO_FOLDER, SEPARATOR_FILES, HERO_FRONT),
                    linesHeros_.join(RETURN_LINE));
            linesHeros_.clear();
            for (ImageHeroKey k : backHeros.getKeys()) {
                String image_ = BaseSixtyFourUtil.getStringByImage(backHeros
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
                    StringList.concat(HERO_FOLDER, SEPARATOR_FILES, HERO_BACK),
                    linesHeros_.join(RETURN_LINE));
            linesHeros_.clear();
            for (ImageHeroKey k : overWorldHeros.getKeys()) {
                String image_ = BaseSixtyFourUtil
                        .getStringByImage(overWorldHeros.getVal(k));
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
                    StringList.concat(HERO_FOLDER, SEPARATOR_FILES, HERO_MINI),
                    linesHeros_.join(RETURN_LINE));
            for (String n : trainers.getKeys()) {
                files_.put(
                        StringList.concat(TRAINERS_FOLDER, SEPARATOR_FILES, n),
                        BaseSixtyFourUtil.getStringByImage(trainers.getVal(n)));
            }
            for (String n : maxiPkFront.getKeys()) {
                files_.put(StringList.concat(FRONT_IMAGES_FOLDER,
                        SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                        BaseSixtyFourUtil.getStringByImage(maxiPkFront
                                .getVal(n)));
            }
            for (String n : maxiPkBack.getKeys()) {
                files_.put(
                        StringList.concat(BACK_IMAGES_FOLDER, SEPARATOR_FILES,
                                n, IMG_FILES_RES_EXT_TXT),
                        BaseSixtyFourUtil.getStringByImage(maxiPkBack.getVal(n)));
            }
            for (String n : miniPk.getKeys()) {
                files_.put(StringList.concat(MINI_IMAGES_FOLDER,
                        SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                        BaseSixtyFourUtil.getStringByImage(miniPk.getVal(n)));
            }
            for (String n : miniItems.getKeys()) {
                files_.put(StringList.concat(OBJECTS_IMAGES_FOLDER,
                        SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                        BaseSixtyFourUtil.getStringByImage(miniItems.getVal(n)));
            }
            for (String n : typesImages.getKeys()) {
                files_.put(StringList.concat(TYPES_IMAGES_FOLDER,
                        SEPARATOR_FILES, n, IMG_FILES_RES_EXT_TXT),
                        BaseSixtyFourUtil.getStringByImage(typesImages
                                .getVal(n)));
            }
            files_.put(
                    StringList.concat(IMAGE_TM_HM_FILES, IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(imageTmHm));
            files_.put(StringList.concat(IMAGE_STORAGE_FILES,
                    IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                    .getStringByImage(storage));
            files_.put(
                    StringList.concat(END_GAME_IMAGE, IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(endGameImage));
        }
        return files_;
    }

    public String getExpGrowth(ExpType _exp) {
        return expGrowth.getVal(_exp);
    }

    public String getFormula(String _litt, String _language) {
        StringMap<String> litt_ = litterals.getVal(_language);
        
        StringBuilder str_ = new StringBuilder();
        int len_ = _litt.length();
        int i_ = 0;
        boolean br_ = false;
        StringList list_ = new StringList();
        while (i_ < len_) {
            char cur_ = _litt.charAt(i_);
            if (br_) {
                boolean dig_ = cur_ >= '0' && cur_ <= '9';
                int j_ = i_;
                int delta_ = 0;
                if (!StringList.isWordChar(cur_)) {
                    j_++;
                    cur_ = _litt.charAt(j_);
                    delta_++;
                }
                while (StringList.isWordChar(cur_)) {
                    j_++;
                    cur_ = _litt.charAt(j_);
                }
                String word_ = _litt.substring(i_+delta_, j_);
                if (dig_) {
                    list_.add(word_);
                } else if (!word_.startsWith(VAR_PREFIX)) {
                    list_.add(translate(word_, _language));
                } else {
                    String tok_ = word_.substring(VAR_PREFIX.length());
                    StringList elts_ = StringList.splitStrings(tok_, SEP_BETWEEN_KEYS);
                    String line_ = litt_.getVal(elts_.first());
                    StringList infos_ = StringList.splitStrings(line_, TAB);
                    StringList objDisplay_ = getVars(word_, _language);
                    String pattern_ = infos_.get(1);

                    String format_ = StringList.simpleStringsFormat(pattern_,
                            objDisplay_.toArray());
                    list_.add(format_);
                }
                if (cur_ == '}') {
                    list_.sort();
                    str_.append(list_.join(getSepartorSetChar()));
                    list_.clear();
                    br_ = false;
                }
                i_ = j_;
                continue;
            }
            if (cur_ == '{') {
                str_.append(cur_);
                i_++;
                if (_litt.charAt(i_) != '}') {
                    br_ = true;
                }
                continue;
            }
            if (StringList.isWordChar(cur_)) {
                boolean dig_ = cur_ >= '0' && cur_ <= '9';
                int j_ = i_;
                while (StringList.isWordChar(cur_)) {
                    j_++;
                    cur_ = _litt.charAt(j_);
                }
                String word_ = _litt.substring(i_, j_);
                if (dig_) {
                    str_.append(word_);
                    i_ = j_;
                    continue;
                }
                if (cur_ == '(' || StringList.quickEq(getTrueString(), word_)|| StringList.quickEq(getFalseString(), word_)) {
                    str_.append(translatedFctMath.getVal(_language).getVal(word_));
                    i_ = j_;
                    continue;
                }
                if (!word_.startsWith(VAR_PREFIX)) {
                    str_.append(translate(word_, _language));
                    i_ = j_;
                    continue;
                }
                String tok_ = word_.substring(VAR_PREFIX.length());
                StringList elts_ = StringList.splitStrings(tok_, SEP_BETWEEN_KEYS);
                String line_ = litt_.getVal(elts_.first());
                StringList infos_ = StringList.splitStrings(line_, TAB);
                StringList objDisplay_ = getVars(word_, _language);
                String pattern_ = infos_.get(1);

                String format_ = StringList.simpleStringsFormat(pattern_,
                        objDisplay_.toArray());
                str_.append(format_);
                i_ = j_;
                continue;
            }
            str_.append(cur_);
            i_++;
        }
        return str_.toString();
    }

    private static StringList getVariableWords(String _str) {
        StringList list_ = StringList.getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int i_ = CustList.FIRST_INDEX;
        for (String t : list_) {
            if (i_ % 2 == 0) {
                i_++;
                continue;
            }
            if (isVariable(t)) {
                newList_.add(t);
                i_++;
                continue;
            }
            i_++;
        }
        return newList_;
    }

    private static boolean isVariable(String _string) {
        if (!_string.startsWith(VAR_PREFIX)) {
            return false;
        }
        return _string.length() > VAR_PREFIX.length();
    }

    public NatStringTreeMap< String> getDescriptions(String _litt,
            String _language) {
        StringMap<String> litt_ = litterals.getVal(_language);

        StringList tokens_ = StringList.getWordsSeparatorsPrefix(_litt,
                VAR_PREFIX);
        NatStringTreeMap< String> desc_ = new NatStringTreeMap< String>();
        int len_ = tokens_.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (i % 2 == 0) {
                continue;
            }
            String tok_ = tokens_.get(i).substring(VAR_PREFIX.length());
            StringList elts_ = StringList.splitStrings(tok_, SEP_BETWEEN_KEYS);
            String line_ = litt_.getVal(elts_.first());
            StringList infos_ = StringList.splitStrings(line_, TAB);
            String key_ = infos_.get(1);
            StringList objDisplay_ = getVars(tokens_.get(i), _language);

            String formatKey_ = StringList.simpleStringsFormat(key_,
                    objDisplay_.toArray());
            String pattern_ = infos_.get(2);

            String format_ = StringList.simpleStringsFormat(pattern_,
                    objDisplay_.toArray());
            desc_.put(formatKey_, format_);
        }
        return desc_;
    }

    StringList getVars(String _token, String _language) {
        StringMap<String> litt_ = litterals.getVal(_language);
        String tok_ = _token.substring(VAR_PREFIX.length());
        StringList elts_ = StringList.splitStrings(tok_, SEP_BETWEEN_KEYS);
        String line_ = litt_.getVal(elts_.first());
        StringList infos_ = StringList.splitStrings(line_, TAB);
        String type_ = infos_.get(0);
        StringList types_ = StringList.splitChars(type_, getSepartorSetChar());
        StringList objDisplay_ = new StringList();
        int len_ = elts_.size();
        for (int j = CustList.SECOND_INDEX; j < len_; j++) {
            if (StringList.quickEq(types_.get(j - 1), MOVE_FORMULA)) {
                objDisplay_.add(translatedMoves.getVal(_language).getVal(
                        elts_.get(j)));
            }
            if (StringList.quickEq(types_.get(j - 1), CAT_FORMULA)) {
                objDisplay_.add(translatedCategories.getVal(_language).getVal(
                        elts_.get(j)));
            }
            if (StringList.quickEq(types_.get(j - 1), STATIS_FORMULA)) {
                objDisplay_.add(translatedStatistics.getVal(_language).getVal(
                        Statistic.getStatisticByName(elts_.get(j))));
            }
            if (StringList.quickEq(types_.get(j - 1), STATUS_FORMULA)) {
                objDisplay_.add(translatedStatus.getVal(_language).getVal(
                        elts_.get(j)));
            }
            if (StringList.quickEq(types_.get(j - 1), TYPE_FORMULA)) {
                objDisplay_.add(translatedTypes.getVal(_language).getVal(
                        elts_.get(j)));
            }
        }
        return objDisplay_;
    }

    public boolean isTranslatable(String _key) {
        for (String l : Constants.getAvailableLanguages()) {
            if (translate(_key, l).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String translate(String _key, String _language) {
        if (translatedMoves.getVal(_language).contains(_key)) {
            return translatedMoves.getVal(_language).getVal(_key);
        }
        if (translatedCategories.getVal(_language).contains(_key)) {
            return translatedCategories.getVal(_language).getVal(_key);
        }
        if (translatedStatus.getVal(_language).contains(_key)) {
            return translatedStatus.getVal(_language).getVal(_key);
        }
        if (translatedTypes.getVal(_language).contains(_key)) {
            return translatedTypes.getVal(_language).getVal(_key);
        }
        if (translatedItems.getVal(_language).contains(_key)) {
            return translatedItems.getVal(_language).getVal(_key);
        }
        if (translatedPokemon.getVal(_language).contains(_key)) {
            return translatedPokemon.getVal(_language).getVal(_key);
        }
        if (translatedAbilities.getVal(_language).contains(_key)) {
            return translatedAbilities.getVal(_language).getVal(_key);
        }
        for (EnvironmentType s : EnvironmentType.values()) {
            if (StringList.quickEq(_key, s.name())) {
                if (translatedEnvironment.getVal(_language).contains(s)) {
                    return translatedEnvironment.getVal(_language).getVal(s);
                }
            }
        }
        for (Statistic s : Statistic.values()) {
            if (StringList.quickEq(_key, s.name())) {
                if (translatedStatistics.getVal(_language).contains(s)) {
                    return translatedStatistics.getVal(_language).getVal(s);
                }
            }
        }
        for (Gender g : Gender.values()) {
            if (StringList.quickEq(_key, g.name())) {
                if (translatedGenders.getVal(_language).contains(g)) {
                    return translatedGenders.getVal(_language).getVal(g);
                }
            }
        }
        return EMPTY_STRING;
    }

    public short ppCopiedMove(String _move) {
        MoveData fAtt_ = getMove(_move);
        int nbEffets_ = fAtt_.nbEffets();
        short pp_ = 0;
        for (int i = CustList.FIRST_INDEX; i < nbEffets_; i++) {
            Effect effet_ = fAtt_.getEffet(i);
            if (!(effet_ instanceof EffectCopyMove)) {
                continue;
            }
            EffectCopyMove effetCopieAtt_ = (EffectCopyMove) effet_;
            if (effetCopieAtt_.getCopyingMoveForUser() > 0) {
                pp_ = effetCopieAtt_.getCopyingMoveForUser();
                break;
            }
        }
        return pp_;
    }

    public boolean isBatonPassMove(String _move) {
        if (!moves.contains(_move)) {
            return false;
        }
        MoveData fAtt_ = getMove(_move);
        boolean relais_ = false;
        int nbEffets_ = fAtt_.nbEffets();
        for (int i = CustList.FIRST_INDEX; i < nbEffets_; i++) {
            Effect effet_ = fAtt_.getEffet(i);
            if (!(effet_ instanceof EffectBatonPass)) {
                continue;
            }
            relais_ = true;
            break;
        }
        return relais_;
    }

    public boolean isObjectUsedForExp(String _obj) {
        if (!items.contains(_obj)) {
            return false;
        }
        Item obj_ = items.getVal(_obj);
        if (!(obj_ instanceof ItemForBattle)) {
            return false;
        }
        ItemForBattle f_ = (ItemForBattle) obj_;
        return f_.isUsedForExp();
    }

    public StringList getTypes() {
        return types;
    }

    public static Rate determinatedRate() {
        return Rate.one();
    }

    public static Rate defRateProduct() {
        return Rate.one();
    }

    public static LgInt defElementaryEvent() {
        return LgInt.one();
    }

    public static String removeFinalFile(String _string) {
        return StringList.replaceFinalFile(_string);

    }

    static String removeExtension(String _string) {
        return StringList.replaceExtension(_string);

    }

    public PokemonData getPokemon(String _name) {
        return pokedex.getVal(_name);
    }

    public MoveData getMove(String _name) {
        return moves.getVal(_name);
    }

    public Item getItem(String _name) {
        return items.getVal(_name);
    }

    public AbilityData getAbility(String _name) {
        return abilities.getVal(_name);
    }

    public Status getStatus(String _name) {
        return status.getVal(_name);
    }

    public StringList getVariables() {
        return variables;
    }

    public StringMap<PokemonData> getPokedex() {
        return pokedex;
    }

    public StringMap<MoveData> getMoves() {
        return moves;
    }

    public NumberMap<Short, String> getTm() {
        return tm;
    }

    public Numbers<Short> getTmByMove(String _move) {
        Numbers<Short> tms_ = new Numbers<Short>();
        for (EntryCust<Short, String> e : tm.entryList()) {
            if (StringList.quickEq(e.getValue(), _move)) {
                tms_.add(e.getKey());
            }
        }
        return tms_;
    }

    public NumberMap<Short, LgInt> getTmPrice() {
        return tmPrice;
    }

    public NumberMap<Short, String> getHm() {
        return hm;
    }

    public Numbers<Short> getHmByMove(String _move) {
        Numbers<Short> tms_ = new Numbers<Short>();
        for (EntryCust<Short, String> e : hm.entryList()) {
            if (StringList.quickEq(e.getValue(), _move)) {
                tms_.add(e.getKey());
            }
        }
        return tms_;
    }

    public StringMap<Item> getItems() {
        return items;
    }

    public StringMap<AbilityData> getAbilities() {
        return abilities;
    }

    public StringMap<Status> getStatus() {
        return status;
    }

    public EnumMap<ExpType, String> getExpGrowth() {
        return expGrowth;
    }

    public EnumMap<DifficultyWinPointsFight, String> getRates() {
        return rates;
    }

    public EnumMap<DifficultyModelLaw, LawNumber> getLawsDamageRate() {
        return lawsDamageRate;
    }

    public StringMap<int[][]> getMiniPk() {
        return miniPk;
    }

    public StringMap<int[][]> getMaxiPkBack() {
        return maxiPkBack;
    }

    public StringMap<int[][]> getMaxiPkFront() {
        return maxiPkFront;
    }

    public int getMaxHeightPk() {
        return maxHeightPk;
    }

    public int getMaxWidthPk() {
        return maxWidthPk;
    }

    public StringMap<int[][]> getMiniItems() {
        return miniItems;
    }

    public int[][] getTrainer(String _name) {
        return getValueCaseInsensitive(trainers, _name);

    }

    public StringMap<int[][]> getTrainers() {
        return trainers;
    }

    public int[][] getPerson(String _name) {
        return getValueCaseInsensitive(people, _name);

    }

    public StringMap<int[][]> getPeople() {
        return people;
    }

    public ObjectMap<ImageHeroKey, int[][]> getFrontHeros() {
        return frontHeros;
    }

    public ObjectMap<ImageHeroKey, int[][]> getBackHeros() {
        return backHeros;
    }

    public ObjectMap<ImageHeroKey, int[][]> getOverWorldHeros() {
        return overWorldHeros;
    }

    public int[][] getLink(String _name) {
        return getValueCaseInsensitive(links, _name);

    }

    public StringMap<int[][]> getLinks() {
        return links;
    }

    public int[][] getImage(String _name) {
        return getValueCaseInsensitive(images, _name);

    }

    public StringMap<int[][]> getImages() {
        return images;
    }

    public int[][] getImageTile(String _name, ScreenCoords _coords) {
        for (EntryCust<String, ObjectMap<ScreenCoords, int[][]>> e : imagesTiles
                .entryList()) {
            if (!StringList.quickEq(e.getKey(),_name)) {
                continue;
            }
            return e.getValue().getVal(_coords);
        }
        return new int[0][0];
    }

    public int[][] getMiniMap(String _name) {
        return getValueCaseInsensitive(miniMap, _name);

    }

    public static int[][] getValueCaseInsensitive(StringMap<int[][]> _map,
            String _name) {
        for (EntryCust<String, int[][]> e : _map.entryList()) {
            if (StringList.quickEq(e.getKey(),_name)) {
                return e.getValue();
            }
        }
        return new int[0][0];
    }

    public StringMap<int[][]> getMiniMap() {
        return miniMap;
    }

    public StringMap<Dims> getImagesDimensions() {
        return imagesDimensions;
    }

    public int[][] getImageTmHm() {
        return imageTmHm;
    }

    public void setImageTmHm(int[][] _imageTmHm) {
        imageTmHm = _imageTmHm;
    }

    public int[][] getStorage() {
        return storage;
    }

    public void setStorage(int[][] _storage) {
        storage = _storage;
    }

    public DataMap getMap() {
        return map;
    }

    public void setMap(DataMap _map) {
        map = _map;
    }

    public Combos getCombos() {
        return combos;
    }

    public CustList<EndRoundMainElements> getEvtEndRound() {
        return evtEndRound;
    }

    private Rate constNum(String _key) {
        return constNum.getVal(_key);
    }

    public void addConstNumTest(String _key, Rate _value) {
        constNum.put(_key, _value);
    }

    public void initDefaultConsts(String _ballDef, String _rateCatching,
            String _rateFleeing, String _rateBoost,
            String _rateBoostCriticalHit, String _damageFormula,
            String _defMove, String _defaultEggGoup) {
        ballDef = _ballDef;
        rateCatching = _rateCatching;
        rateFleeing = _rateFleeing;
        rateBoost = _rateBoost;
        rateBoostCriticalHit = _rateBoostCriticalHit;
        damageFormula = _damageFormula;
        defMove = _defMove;
        defaultEggGoup = _defaultEggGoup;
    }

    /** USED */
    public Rate getStrongMovePower() {
        return constNum(STRONG_MOVE);
    }

    /** General data - Begin game HTML */
    public Rate getDefaultMoney() {
        return constNum(ARGENT);
    }

    /** USED */
    public Rate getWonHappinessByGrowLevel() {
        return constNum(GAIN_BONHEUR_NIV);
    }

    /** General data HTML - walking */
    public short getNbNecStepsIncrHappiness() {
        return (short) constNum(PAS_NECES_INCREMENT_BONHEUR).ll();
    }

    /** General data HTML - host */
    public short getNbMaxStepsSameEvoBase() {
        return (short) constNum(MAX_STEPS_SAME_EVO_BASE).ll();
    }

    /** General data HTML - host */
    public short getNbMaxSteps() {
        return (short) constNum(MAX_STEPS).ll();
    }

    /** General data HTML */
    public long getMaxPp() {
        return constNum(PP_MAX).ll();
    }

    /** USED */
    public int getHappinessEvo() {
        return (int) constNum(EVO_BONHEUR).ll();
    }

    /** General data HTML */
    public int getHappinessMax() {
        return (int) constNum(MAX_BONHEUR).ll();
    }

    /** General data HTML */
    public int getMaxEv() {
        return (int) constNum(MAX_EV).ll();
    }

    /** General data HTML */
    public int getMaxIv() {
        return (int) constNum(MAX_IV).ll();
    }

    /** General data HTML */
    public byte getNbMaxTeam() {
        return (byte) constNum(DEF_PKEQ).ll();
    }

    /** USED in a table */
    public int getMinBoost() {
        return (int) constNum(MIN_BOOST).ll();
    }

    /** USED */
    public int getDefaultBoost() {
        return (int) constNum(VALEUR_DEF_STATIS).ll();
    }

    /** USED in a table */
    public int getMaxBoost() {
        return (int) constNum(MAX_BOOST).ll();
    }

    /** General data HTML */
    public int getNbMaxMoves() {
        return (int) constNum(DEF_MAX_ATT).ll();
    }

    /** USED */
    public Rate getMinHp() {
        return constNum(DataBase.MIN_HP);
    }

    /** General data HTML */
    public int getMinLevel() {
        return (int) constNum(DataBase.NIVEAU_PK_ECLOSION).ll();
    }

    /** General data HTML */
    public int getMaxLevel() {
        return (int) constNum(DataBase.NIVEAU_PK_MAX).ll();
    }

    /** USED */
    public Rate getStab() {
        return constNum(DataBase.BONUS_BOOST);
    }

    /** USED */
    public String getCatchingFormula() {

        return rateCatching;
    }

    /** USED */
    public String getFleeingFormula() {

        return rateFleeing;
    }

    /** USED */
    public String getDamageFormula() {

        return damageFormula;
    }

    /** USED */
    public String getRateBoost() {

        return rateBoost;
    }

    /** USED */
    public String getRateBoostCriticalHit() {

        return rateBoostCriticalHit;
    }

    /** General data - show all pokemon belonging to this group */
    public String getDefaultEggGroup() {

        return defaultEggGoup;
    }

    public String getDefaultBall() {

        return ballDef;
    }

    /** USED */
    public String getDefaultMove() {

        return defMove;
    }

    public StringList getVarParamsMove(String _var) {
        StringList elements_ = new StringList();
        if (varParamsMove.contains(_var)) {
            elements_.addAllElts(varParamsMove.getVal(_var));
        }
        return elements_;
    }

    public StringMap<StringList> getVarParamsMove() {
        return varParamsMove;
    }

    public ObjectMap<TypesDuo, Rate> getTableTypes() {
        return tableTypes;
    }

    public StringList getMovesProtAgainstDamageMoves() {
        return movesProtAgainstDamageMoves;
    }

    public StringList getMovesProtAgainstStatusMoves() {
        return movesProtAgainstStatusMoves;
    }

    public StringList getMovesProtAgainstPrio() {
        return movesProtAgainstPrio;
    }

    public StringList getMovesProtAgainstMultiTarget() {
        return movesProtAgainstMultiTarget;
    }

    public StringList getMovesProtSingleTarget() {
        return movesProtSingleTarget;
    }

    public StringList getMovesProtSingleTargetAgainstKo() {
        return movesProtSingleTargetAgainstKo;
    }

    public StringList getMovesCopyingTemp() {
        return movesCopyingTemp;
    }

    public StringList getTrappingMoves() {
        return trappingMoves;
    }

    public StringList getMovesAccuracy() {
        return movesAccuracy;
    }

    public StringList getMovesActingMoveUses() {
        return movesActingMoveUses;
    }

    public StringList getMovesForbidding() {
        return movesForbidding;
    }

    public StringList getMovesEffectIndiv() {
        return movesEffectIndiv;
    }

    public StringList getMovesEffectProt() {
        return movesEffectProt;
    }

    public StringList getMovesEffectUnprot() {
        return movesEffectUnprot;
    }

    public StringList getMovesEffectIndivIncr() {
        return movesEffectIndivIncr;
    }

    public StringList getMovesEffEndRoundIndiv() {
        return movesEffEndRoundIndiv;
    }

    public StringList getMovesEffEndRoundIndivIncr() {
        return movesEffEndRoundIndivIncr;
    }

    public StringList getMovesEffectTeam() {
        return movesEffectTeam;
    }

    public StringList getMovesEffectWhileSending() {
        return movesEffectWhileSending;
    }

    public StringList getMovesEffectGlobal() {
        return movesEffectGlobal;
    }

    public StringList getMovesEffectGlobalWeather() {
        return movesEffectGlobalWeather;
    }

    public StringList getMovesEffectAlly() {
        return movesEffectAlly;
    }

    public StringList getMovesHealingAfter() {
        return movesHealingAfter;
    }

    public StringList getMovesFullHeal() {
        return movesFullHeal;
    }

    public StringList getMovesAnticipation() {
        return movesAnticipation;
    }

    public StringList getMovesConstChoices() {
        return movesConstChoices;
    }

    public StringList getMovesInvoking() {
        return movesInvoking;
    }

    public StringList getMovesChangingTypes() {
        return movesChangingTypes;
    }

    public StringList getMovesCountering() {
        return movesCountering;
    }

    public StringList getAllCategories() {
        return allCategories;
    }

    public StringList getCategories() {
        return categories;
    }

    public Rate getAvgWeight() {
        return avgWeight;
    }

    public int[][] getEndGameImage() {
        return endGameImage;
    }

    public void setEndGameImage(int[][] _endGameImage) {
        endGameImage = _endGameImage;
    }

    public StringList getFilesWithSameNameDifferentCase() {
        return filesWithSameNameDifferentCase;
    }

    public StringMap<StringMap<String>> getTranslatedCategories() {
        return translatedCategories;
    }

    public EnumMap<Gender, String> getTranslatedGendersCurLanguage(String _lg) {
        return translatedGenders.getVal(_lg);
    }

    public StringMap<EnumMap<Gender, String>> getTranslatedGenders() {
        return translatedGenders;
    }

    public StringMap<String> getTranslatedStatusCurLanguage(String _lg) {
        return translatedStatus.getVal(_lg);
    }

    public StringMap<String> getTranslatedItemsCurLanguage(String _lg) {
        return translatedItems.getVal(_lg);
    }

    public StringMap<String> getTranslatedMovesCurLanguage(String _lg) {
        return translatedMoves.getVal(_lg);
    }

    public StringMap<String> getTranslatedPokemonCurLanguage(String _lg) {
        return translatedPokemon.getVal(_lg);
    }

    public StringMap<String> getTranslatedAbilitiesCurLanguage(String _lg) {
        return translatedAbilities.getVal(_lg);
    }

    public EnumMap<SelectedBoolean, String> getTranslatedBooleansCurLanguage(String _lg) {
        return translatedBooleans.getVal(_lg);
    }

    public StringMap<EnumMap<SelectedBoolean, String>> getTranslatedBooleans() {
        return translatedBooleans;
    }

    public StringMap<EnumMap<DifficultyModelLaw, String>> getTranslatedDiffModelLaw() {
        return translatedDiffModelLaw;
    }

    public StringMap<EnumMap<DifficultyWinPointsFight, String>> getTranslatedDiffWinPts() {
        return translatedDiffWinPts;
    }

    public StringMap<EnumMap<EnvironmentType, String>> getTranslatedEnvironment() {
        return translatedEnvironment;
    }

    public StringMap<EnumMap<Statistic, String>> getTranslatedStatistics() {
        return translatedStatistics;
    }

    public StringMap<EnumMap<TargetChoice, String>> getTranslatedTargets() {
        return translatedTargets;
    }

    public StringMap<StringMap<String>> getTranslatedTypes() {
        return translatedTypes;
    }

    public StringMap<StringMap<String>> getTranslatedAbilities() {
        return translatedAbilities;
    }

    public StringMap<StringMap<String>> getTranslatedItems() {
        return translatedItems;
    }

    public StringMap<StringMap<String>> getTranslatedMoves() {
        return translatedMoves;
    }

    public StringMap<StringMap<String>> getTranslatedPokemon() {
        return translatedPokemon;
    }

    public StringMap<StringMap<String>> getTranslatedFctMath() {
        return translatedFctMath;
    }

    public String translatePokemon(String _pokemon) {
        return translatedPokemon.getVal(language).getVal(
                _pokemon);
    }

    public String translateMove(String _move) {
        return translatedMoves.getVal(language).getVal(_move);
    }

    public String translateItem(String _item) {
        return translatedItems.getVal(language).getVal(_item);
    }

    public String translateAbility(String _ability) {
        return translatedAbilities.getVal(language).getVal(
                _ability);
    }

    public String translateStatus(String _status) {
        return translatedStatus.getVal(language).getVal(_status);
    }

    public String translateType(String _type) {
        return translatedTypes.getVal(language).getVal(_type);
    }

    public String translateStatistics(Statistic _statistic) {
        return translatedStatistics.getVal(language).getVal(
                _statistic);
    }

    public String translatedTargets(TargetChoice _target) {
        return translatedTargets.getVal(language)
                .getVal(_target);
    }

    public String translateGenders(Gender _gender) {
        return translatedGenders.getVal(language)
                .getVal(_gender);
    }

    public StringMap<StringMap<String>> getTranslatedStatus() {
        return translatedStatus;
    }

    public StringMap<StringMap<String>> getTranslatedClassesDescriptions() {
        return translatedClassesDescriptions;
    }

    public StringMap<String> getTypesColors() {
        return typesColors;
    }

    public StringMap<int[][]> getTypesImages() {
        return typesImages;
    }

    public StringMap<int[][]> getAnimStatis() {
        return animStatis;
    }

    public StringMap<int[][]> getAnimStatus() {
        return animStatus;
    }

    public int[][] getAnimAbsorb() {
        return animAbsorb;
    }

    public StringMap<PokemonFamily> getFamilies() {
        return families;
    }

    public static Rate getDefaultPower() {
        return new Rate(DEFAULT_POWER_INT);
    }

    public static Rate getDefaultHealRate() {
        return new Rate(DEFAULT_HEAL_RATE_NUM, DEFAULT_HEAL_RATE_DEN);
    }

    public static Rate getDefaultInflictedRate() {
        return new Rate(DEFAULT_INFLICTED_RATE_NUM, DEFAULT_INFLICTED_RATE_DEN);
    }

    public boolean isCheckTranslation() {
        return checkTranslation;
    }

    public void setCheckTranslation(boolean _checkTranslation) {
        checkTranslation = _checkTranslation;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean _error) {
        error = _error;
    }

    public String getLanguage() {
        return language;
    }
}
