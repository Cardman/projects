package aiki.db;

import aiki.comparators.ComparatorEndRoundMainElements;
import aiki.facade.SexListInt;
import aiki.fight.Combos;
import aiki.fight.EndRoundMainElements;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.EndTurnType;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.PokemonFamily;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.pokemon.evolution.Evolution;
import aiki.fight.pokemon.evolution.EvolutionMove;
import aiki.fight.status.Status;
import aiki.fight.status.StatusType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.ListEffectCombos;
import aiki.fight.util.TypesDuos;
import aiki.fight.util.TypesDuo;
import aiki.game.fight.CheckNumericStringsFight;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.enums.Sex;
import aiki.map.DataMap;
import aiki.map.enums.Direction;
import aiki.map.levels.Block;
import aiki.map.levels.Level;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Place;
import aiki.map.pokemon.enums.Gender;
import aiki.map.tree.util.Dims;
import aiki.map.util.ScreenCoords;
import aiki.map.util.TileMiniMap;
import aiki.util.*;
import code.images.BaseSixtyFourUtil;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteral.EvolvedBooleanString;
import code.maths.litteral.EvolvedMathFactory;
import code.maths.litteral.EvolvedNumString;
import code.maths.litteralcom.MathExpUtil;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.NatStringTreeMap;
import code.util.*;

import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import code.util.ints.Listable;
import aiki.facade.enums.SelectedBoolean;

public class DataBase {

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
    public static final String DEF_CAT = "DEF_CAT";
    public static final String RATE_BOOST_CRITICAL_HIT = "RATE_BOOST_CRITICAL_HIT";
    public static final String DAMAGE_FORMULA = "DAMAGE_FORMULA";
    public static final String DEFAULT_EGG_GROUP = "DEFAULT_EGG_GROUP";
    public static final String MAX_STEPS_SAME_EVO_BASE = "MAX_STEPS_SAME_EVO_BASE";
    public static final String MAX_STEPS = "MAX_STEPS";
    public static final String MIN_HP = "MIN_HP";
    public static final String DEF_MAX_ATT = "DEF_MAX_ATT";
    public static final String BONUS_BOOST = "BONUS_BOOST";
    public static final String DEF_BASE_MOVE = "DEF_BASE_MOVE";
    public static final String BALL_DEF = "BALL_DEF";

    public static final String SEP_BETWEEN_KEYS = "__";
    public static final String IMG_FILES_RES_EXT = ".png";
    public static final String IMG_FILES_RES_EXT_TXT = ".txt";
    public static final String FILES_RES_EXT = ".xml";
    public static final String TRANSLATION_CATEGORIES = "categories.txt";
    public static final String TRANSLATION_GENDERS = "genders.txt";
    public static final String TRANSLATION_ENVIRONMENTS = "environments.txt";
    public static final String TRANSLATION_BOOLEANS = "booleans.txt";
    public static final String TRANSLATION_DIFF_WIN_PTS = "winpts.txt";
    public static final String TRANSLATION_DIFF_MODEL_LAW = "modellaw.txt";
    public static final String TRANSLATION_STATISTICS = "statistics.txt";
    public static final String TRANSLATION_TARGETS = "targets.txt";
    public static final String TRANSLATION_TYPES = "types.txt";
    public static final String TRANSLATION_POKEMON = "pokemon.txt";
    public static final String TRANSLATION_MOVES = "moves.txt";
    public static final String TRANSLATION_ITEMS = "items.txt";
    public static final String TRANSLATION_ABILITIES = "abilities.txt";
    public static final String TRANSLATION_STATUS = "status.txt";
    public static final String TRANSLATION_MATH = "math.txt";
    public static final String TRANSLATION_CLASSES = "classes.txt";
    public static final String TRANSLATION_LITTERAL = "litteral.txt";

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

    public static final String ANIM_STATIS = "anim_statis";

    public static final String ANIM_STATUS = "anim_status";

    public static final String ANIM_ABSORB = "anim_absorb/absorb.txt";

    private static final int DEFAULT_POWER_INT = 80;

    private static final int DEFAULT_HEAL_RATE_NUM = 1;
    private static final int DEFAULT_HEAL_RATE_DEN = 2;

    private static final int DEFAULT_INFLICTED_RATE_NUM = 1;
    private static final int DEFAULT_INFLICTED_RATE_DEN = 8;

    private static final String TAB = "\t";

    private static final String MOVE_FORMULA = "move";
    private static final String CAT_FORMULA = "cat";
    private static final String STATIS_FORMULA = "statis";
    private static final String STATUS_FORMULA = "status";
    private static final String TYPE_FORMULA = "type";

    private static final char UNDERSCORE = '_';

    private StringMap<PokemonData> pokedex = new StringMap<PokemonData>();

    private Rate avgWeight = Rate.zero();

    private StringMap<MoveData> moves = new StringMap<MoveData>();

    private ShortMap< String> tm = new ShortMap< String>();

    private ShortMap< LgInt> tmPrice = new ShortMap< LgInt>();

    private ShortMap< String> hm = new ShortMap< String>();

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

    private ImageHeroKeys frontHeros = new ImageHeroKeys();

    private ImageHeroKeys backHeros = new ImageHeroKeys();

    private ImageHeroKeys overWorldHeros = new ImageHeroKeys();

    private StringMap<int[][]> links = new StringMap<int[][]>();

    private StringMap<int[][]> images = new StringMap<int[][]>();

    private StringMap<ScreenCoordssInt> imagesTiles = new StringMap<ScreenCoordssInt>();

    private StringMap<int[][]> miniMap = new StringMap<int[][]>();

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
    private String defCategory;

    private String ballDef;

    private String defaultEggGroup;

    private String rateCatching;

    private IdMap<ExpType, String> expGrowth = new IdMap<ExpType, String>();

    private IdMap<DifficultyWinPointsFight, String> rates = new IdMap<DifficultyWinPointsFight, String>();

    private TypesDuos tableTypes = new TypesDuos();
    private StringList types = new StringList();

    private IdMap<DifficultyModelLaw, LawNumber> lawsDamageRate = new IdMap<DifficultyModelLaw, LawNumber>();

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

    private StringMap<IdMap<EnvironmentType, String>> translatedEnvironment = new StringMap<IdMap<EnvironmentType, String>>();

    private StringMap<IdMap<SelectedBoolean, String>> translatedBooleans = new StringMap<IdMap<SelectedBoolean, String>>();

    private StringMap<IdMap<DifficultyWinPointsFight, String>> translatedDiffWinPts = new StringMap<IdMap<DifficultyWinPointsFight, String>>();

    private StringMap<IdMap<DifficultyModelLaw, String>> translatedDiffModelLaw = new StringMap<IdMap<DifficultyModelLaw, String>>();

    private StringMap<IdMap<Gender, String>> translatedGenders = new StringMap<IdMap<Gender, String>>();

    private StringMap<IdMap<Statistic, String>> translatedStatistics = new StringMap<IdMap<Statistic, String>>();

    private StringMap<IdMap<TargetChoice, String>> translatedTargets = new StringMap<IdMap<TargetChoice, String>>();

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

    private final EvolvedMathFactory standardMathFactory = new EvolvedMathFactory();
    private boolean error;
    private StringMap<String> messagesPokemonPlayer = new StringMap<String>();
    private StringMap<String> messagesPlayer = new StringMap<String>();
    private StringMap<String> messagesFighter = new StringMap<String>();
    private StringMap<String> messagesTeam = new StringMap<String>();
    private StringMap<String> messagesFight = new StringMap<String>();
    private StringMap<String> messagesGame = new StringMap<String>();
    private StringList languages = new StringList();
    private StringList legPks = new StringList();
    private StringMap<String> displayLanguages = new StringMap<String>();
    private String language = "";
    private final AbstractGenerator generator;

    public DataBase(AbstractGenerator _generator) {
        this.generator = _generator;
        defMove="";
        rateBoost="";
        rateBoostCriticalHit="";
        rateCatching="";
        rateFleeing="";
        damageFormula="";
        defaultEggGroup="";
        defCategory="";
        ballDef="";
    }

    public static StringMap<String> basicTranslationItemsType(DataBase _data) {
        StringMap<String> words_;
        words_ = new StringMap<String>();
        for (Item p: _data.getItems().values()) {
            words_.put(p.getItemType(), p.getItemType());
        }
        return words_;
    }

    public static StringMap<String> basicTranslation(CustList<String> _keys) {
        StringMap<String> words_;
        words_ = new StringMap<String>();
        for (String p: _keys) {
            words_.addEntry(p, p);
        }
        return words_;
    }

    public static int countValues(CustList<BoolVal> _map, BoolVal _taken) {
        int n_ = IndexConstants.FIRST_INDEX;
        for (BoolVal e: _map) {
            if (e == _taken) {
                n_++;
            }
        }
        return n_;
    }

    public static StringList keysWithValue(StringMap<BoolVal> _map, BoolVal _learn) {
        StringList moves_;
        moves_ = new StringList();
        for (EntryCust<String,BoolVal> e: _map.entryList()) {
            if (e.getValue() == _learn) {
                moves_.add(e.getKey());
            }
        }
        return moves_;
    }

    public EnvironmentType envType(Coords _coords) {
        DataMap d_ = getMap();
        EnvironmentType e_;
        if (_coords.isValid()) {
            Block bl_ = d_.currentBlock(_coords);
            if (bl_.isValid()) {
                e_ = bl_.getType();
            } else {
                e_ = EnvironmentType.ROAD;
            }
        } else {
            e_ = EnvironmentType.ROAD;
        }
        return e_;
    }

    public LgInt getMaxRd() {
        return standardMathFactory.getMaxRandomNb();
    }

    public EvolvedNumString createNumericableString(
            String _chaineNumerique, StringMap<String> _vars) {
        return EvolvedMathFactory.createNumericableString(_chaineNumerique,
                _vars);
    }

    public EvolvedBooleanString createBooleanString(
            String _chaineBooleenne, StringMap<String> _vars) {
        return EvolvedMathFactory.createBooleanString(_chaineBooleenne, _vars);
    }

    public String getTrueString() {
        return EvolvedMathFactory.getTrueString();
    }

    public String getFalseString() {
        return EvolvedMathFactory.getFalseString();
    }

    public char getSepartorSetChar() {
        return EvolvedMathFactory.getSepartorSetChar();
    }

    public Rate evaluateNumericable(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return EvolvedMathFactory.evaluateNumericable(_numericString,
                _variables, _default);
    }

    public Rate evaluatePositiveOrZeroExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return EvolvedMathFactory.evaluatePositiveOrZeroExp(_numericString,
                _variables, _default);
    }

    public Rate evaluatePositiveExp(String _numericString,
            StringMap<String> _variables, Rate _default) {
        return EvolvedMathFactory.evaluatePositiveExp(_numericString,
                _variables, _default);
    }

    public boolean evaluateBoolean(String _booleanString,
            StringMap<String> _variables, boolean _default) {
        return EvolvedMathFactory.evaluateBoolean(_booleanString, _variables,
                _default);
    }

    public Points< int[][]> getWhiteLevelImage(short _pl, byte _level) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        return getWhiteLevelImage(coords_);
    }

    public Points< int[][]> getWhiteLevelImage(short _pl, byte _level,
                                          Point _inside) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        coords_.setInsideBuilding(_inside);
        return getWhiteLevelImage(coords_);
    }

    public Points< int[][]> getLevelImage(short _pl, byte _level) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        return getLevelImage(coords_);
    }

    public Points< int[][]> getLevelImage(short _pl, byte _level,
                                          Point _inside) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        coords_.setInsideBuilding(_inside);
        return getLevelImage(coords_);
    }

    public Points< int[][]> getWhiteLevelImage(Coords _coords) {
        Points< int[][]> tiles_ = Level.getWhiteLevelBackgroundImage(this,
                _coords);
        Points< int[][]> frontTiles_ = Level.getLevelForegroundImage(
                this, _coords);
        for (Point p : frontTiles_.getKeys()) {
            tiles_.put(p, stackImages(tiles_, frontTiles_, p));
        }
        return tiles_;
    }

    public Points< int[][]> getLevelImage(Coords _coords) {
        Points< int[][]> tiles_ = Level.getLevelBackgroundImage(this,
                _coords);
        Points< int[][]> frontTiles_ = Level.getLevelForegroundImage(
                this, _coords);
        for (Point p : frontTiles_.getKeys()) {
            tiles_.put(p, stackImages(tiles_, frontTiles_, p));
        }
        return tiles_;
    }

    public static int[][] stackImages(Points< int[][]> _tiles,
            Points< int[][]> _frontTiles, Point _pt) {
        int[][] img_ = _frontTiles.getVal(_pt);
        int[][] imgBack_ = _tiles.getVal(_pt);
        return ConverterBufferedImage.stackImages(imgBack_, img_);
    }

    public void addPerson(String _fileName, int[][] _img) {
        people.put(_fileName, _img);
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

    public void validate(PerCent _perCentLoading, LoadFlag _loading,SexListInt _sexListInt) {
        for (LawNumber v : lawsDamageRate.values()) {
            if (v.getLaw().events().isEmpty()) {
                setError(true);
            }
        }
        completeMoveTutors();
        validateCore(_perCentLoading);
        if (!_loading.get()) {
            return;
        }
        _perCentLoading.setPercent(60);
        setCheckTranslation(true);
        if (!getPokedex().isEmpty() && !getAbilities().isEmpty() && moves.getVal(defMove) != null) {
            CheckNumericStringsFight.validateNumericBooleanStrings(this);
        } else {
            setError(true);
        }
        if (!_loading.get()) {
            return;
        }
        _perCentLoading.setPercent(70);
        Rate power_ = getStrongMovePower();
        if (Rate.strLower(power_, new Rate(90))) {
            setError(true);
        }
        TypeStatistics strongMovesTypeStat_ = strongMoves(power_);
        for (CommonParam<TypeStatistic, BoolVal> e : strongMovesTypeStat_
                .entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                continue;
            }
            setError(true);
        }

        if (!_loading.get()) {
            return;
        }
        map.validate(this);
        _perCentLoading.setPercent(85);
        if (!_loading.get()) {
            return;
        }
        validateImages(_sexListInt);
        _perCentLoading.setPercent(90);
        if (!_loading.get()) {
            return;
        }
        validateTranslations();
        _perCentLoading.setPercent(95);

    }

    public void completeMoveTutors() {
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
    }

    TypeStatistics strongMoves(Rate _power) {
        TypeStatistics existDamageMoveWithTypeStatAttack_;
        existDamageMoveWithTypeStatAttack_ = new TypeStatistics();
        for (String t : getTypes()) {
            existDamageMoveWithTypeStatAttack_.put(new TypeStatistic(t,
                    Statistic.ATTACK), BoolVal.FALSE);
            existDamageMoveWithTypeStatAttack_.put(new TypeStatistic(t,
                    Statistic.SPECIAL_ATTACK), BoolVal.FALSE);
        }
        for (EntryCust<String, MoveData> m : getMoves().entryList()) {
            DamagingMoveData resMove_ = tryGetDamagingMoveData(m);
            if (resMove_ == null) {
                continue;
            }
            for (Effect e : resMove_.getEffects()) {
                TypeStatistic res_ = tryGetTypeStatistic(resMove_, e, existDamageMoveWithTypeStatAttack_);
                if (res_ != null) {
                    String powStr_ = ((EffectDamage) e).getPower();
                    if (Rate.isValid(powStr_) && !Rate.strLower(new Rate(powStr_), _power)) {

                        existDamageMoveWithTypeStatAttack_.put(res_, BoolVal.TRUE);
                    }
                    break;
                }
            }
        }
        return existDamageMoveWithTypeStatAttack_;
    }
    private DamagingMoveData tryGetDamagingMoveData(EntryCust<String, MoveData> _m) {
        if (StringUtil.quickEq(_m.getKey(), getDefMove())||StringUtil.contains(getMovesFullHeal(),_m.getKey())||StringUtil.contains(getMovesAnticipation(),_m.getKey())) {
            return null;
        }
        MoveData move_ = _m.getValue();
        int primaryEffect_ = move_.indexOfPrimaryEffect();
        if (!(move_ instanceof DamagingMoveData) || move_.getPriority() < 0 || move_.getNbPrepaRound() > 0 || move_.getRechargeRound() || move_.getTypes().size() != DataBase.ONE_POSSIBLE_CHOICE || move_.getConstUserChoice() || move_.getTargetChoice() == TargetChoice.ADJ_MULT || move_.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE || move_.getEffects().size() > DataBase.ONE_POSSIBLE_CHOICE && nextIteration(move_, primaryEffect_) || invalidAcc(move_)) {
            return null;
        }

        return (DamagingMoveData) move_;
    }
    private static TypeStatistic tryGetTypeStatistic(MoveData _move,Effect _e,TypeStatistics _existDamageMoveWithTypeStatAttack) {
        if (!(_e instanceof EffectDamage) || !_e.getFail().isEmpty()) {
            return null;
        }
        TypeStatistic pair_ = new TypeStatistic(_move.getTypes().first(),
                ((EffectDamage) _e).getStatisAtt());
        if (_existDamageMoveWithTypeStatAttack.contains(pair_) && _existDamageMoveWithTypeStatAttack.getVal(pair_) == BoolVal.TRUE) {
            return null;
        }
        return pair_;
    }

    private boolean invalidAcc(MoveData _move) {
        String accStr_ = _move.getAccuracy();
        return !Rate.isValid(accStr_) || Rate.strLower(new Rate(accStr_), new Rate(1));
    }

    static boolean nextIteration(MoveData _move, int _primaryEffect) {
        boolean next_ = false;
        for (Effect sec_ : _move.getEffects().mid(_primaryEffect + 1)) {
            if (nextIterationEffect(_move,sec_)) {
                next_ = true;
                break;
            }
//            if (sec_ instanceof EffectDamageRate && !((EffectDamageRate) sec_).getRateDamage().isZeroOrGt()) {
//                next_ = true;
//                break;
//            }
//            if (sec_ instanceof EffectStatistic) {
//                boolean toBeTreated_ = procEffectMove(_move, sec_);
//                if (toBeTreated_ && nextIt((EffectStatistic) sec_)) {
//                    next_ = true;
//                    break;
//                }
//                if (sec_.getTargetChoice() == TargetChoice.ADJ_ADV || sec_.getTargetChoice() == TargetChoice.ADJ_MULT || sec_.getTargetChoice() == TargetChoice.ADJ_UNIQ || sec_.getTargetChoice() == TargetChoice.ANY_FOE || sec_.getTargetChoice() == TargetChoice.AUTRE_UNIQ || sec_.getTargetChoice() == TargetChoice.GLOBALE || _move.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE || _move.getTargetChoice() == TargetChoice.TOUS_ADV) {
//                    toBeTreated_ = true;
//                }
//                if (toBeTreated_) {
//                    EffectStatistic effect_ = (EffectStatistic) sec_;
//                    if (negativeStat(effect_,Statistic.EVASINESS)) {
//                        next_ = true;
//                        break;
//                    }
//                }
//            }
//            if (sec_ instanceof EffectStatus) {
//                boolean toBeTreated_ = procEffectMove(_move, sec_);
//                if (toBeTreated_) {
//                    EffectStatus effect_ = (EffectStatus) sec_;
//                    if (!effect_.getLawStatus().events().isEmpty()) {
//                        next_ = true;
//                        break;
//                    }
//                }
//            }
        }
        return next_;
    }
    private static boolean nextIterationEffect(MoveData _move, Effect _sec) {
        if (_sec instanceof EffectDamageRate && !((EffectDamageRate) _sec).getRateDamage().isZeroOrGt()) {
            return true;
        }
        if (_sec instanceof EffectStatistic) {
            boolean toBeTreated_ = procEffectMove(_move, _sec);
            if (toBeTreated_ && nextIt((EffectStatistic) _sec)) {
                return true;
            }
            if (_sec.getTargetChoice() == TargetChoice.ADJ_ADV || _sec.getTargetChoice() == TargetChoice.ADJ_MULT || _sec.getTargetChoice() == TargetChoice.ADJ_UNIQ || _sec.getTargetChoice() == TargetChoice.ANY_FOE || _sec.getTargetChoice() == TargetChoice.AUTRE_UNIQ || _sec.getTargetChoice() == TargetChoice.GLOBALE || _move.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE || _move.getTargetChoice() == TargetChoice.TOUS_ADV) {
                toBeTreated_ = true;
            }
            if (toBeTreated_ && negativeStat(((EffectStatistic) _sec), Statistic.EVASINESS)) {
                return true;
            }
        }
        if (_sec instanceof EffectStatus) {
            boolean toBeTreated_ = procEffectMove(_move, _sec);
            if (toBeTreated_) {
                EffectStatus effect_ = (EffectStatus) _sec;
                return !effect_.getLawStatus().events().isEmpty();
            }
        }
        return false;
    }

    private static boolean nextIt(EffectStatistic _effect) {
        IdList<Statistic> stats_ = new IdList<Statistic>();
        stats_.add(Statistic.SPEED);
        stats_.add(Statistic.SPECIAL_ATTACK);
        stats_.add(Statistic.ATTACK);
        stats_.add(Statistic.ACCURACY);
        boolean nextIt_ = false;
        for (Statistic s: stats_) {
            if (negativeStat(_effect,s)) {
                nextIt_ = true;
                break;
            }
        }
        return nextIt_;
    }

    private static boolean negativeStat(EffectStatistic _eff, Statistic _s) {
        return _eff.getStatisVarRank().contains(_s) && _eff.getStatisVarRank().getVal(_s) < 0;
    }
    private static boolean procEffectMove(MoveData _move, Effect _sec) {
        return _sec.getTargetChoice() == TargetChoice.LANCEUR || _sec.getTargetChoice() == TargetChoice.ALLIE || _sec.getTargetChoice() == TargetChoice.ALLIES || _move.getTargetChoice() == TargetChoice.ADJ_MULT || _move.getTargetChoice() == TargetChoice.PSEUDO_GLOBALE || _move.getTargetChoice() == TargetChoice.GLOBALE;
    }

    public void validateCore(PerCent _perCentLoading) {
        initTypesByTable();
        _perCentLoading.setPercent(55);
        validateConstants();
        checkTypesWithTable();
        if (StringUtil.contains(getCategories(), getDefCategory())) {
            setError(true);
        }
        for (String s : getCategories()) {

            if (!isCorrectIdentifier(s)) {
                setError(true);
            }
        }
        validateEntities();
        checkEvolutionMove();
        validateEvolutions();

        checkHmTm();
        checkNbRounds();
    }

    private void checkTypesWithTable() {
        for (String t1_ : types) {
            for (String t2_ : types) {
                if (!tableTypes.contains(new TypesDuo(t1_, t2_))) {
                    setError(true);
                    continue;
                }
                if (!tableTypes.getVal(new TypesDuo(t1_, t2_)).isZeroOrGt()) {
                    setError(true);
                }
            }
        }
    }

    private void checkNbRounds() {
        Shorts incrementNbRound_ = new Shorts();
        Shorts nonIncrementNbRound_ = new Shorts();
        for (EndRoundMainElements e : getEvtEndRound()) {
            if (e.isIncrementNumberOfRounds()) {
                incrementNbRound_.add(e.getNumberIncrement());
                continue;
            }
            nonIncrementNbRound_.add(e.getNumberIncrement());
        }
        if (nonIncrementNbRound_.hasDuplicates()) {
            setError(true);
        }
        for (short e : incrementNbRound_) {
            if (nonIncrementNbRound_.contains(e)) {
                setError(true);
            }
        }
    }

    private void checkHmTm() {
        if (hasDuplicates(tm.values())) {
            setError(true);
        }
        if (hasDuplicates(hm.values())) {
            setError(true);
        }

        DataInfoChecker.checkStringListContains(getMoves().getKeys(),hm.values(),this);
        DataInfoChecker.checkShortsContains(tm.getKeys(),tmPrice.getKeys(),this);
        for (EntryCust<Short, LgInt> tmPrice_ : tmPrice.entryList()) {
            if (!tmPrice_.getValue().isZeroOrGt()) {
                setError(true);
            }
        }
        checkDefMove(hm.values());
        checkDefMove(tm.values());
        DataInfoChecker.checkStringListContains(getMoves().getKeys(),tm.values(),this);
    }

    private void checkDefMove(CustList<String> _ls) {
        for (String m : _ls) {
            if (StringUtil.quickEq(m, getDefMove())) {
                setError(true);
            }
        }
    }

    private void validateEntities() {
        for (EntryCust<String, PokemonData> e : getPokedex().entryList()) {
            e.getValue().validate(this);
        }
        for (EntryCust<String, MoveData> e : getMoves().entryList()) {
            e.getValue().validate(this);
        }
        for (String m : movesFullHeal) {
            MoveData move_ = getMove(m);
            checkKoUserHealSubst(move_);
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
    }

    private void checkEvolutionMove() {
        for (PokemonData d : getPokedex().values()) {
            StringList moves_ = new StringList(d.getMoveTutors());
            for (LevelMove p2_ : d.getLevMoves()) {
                moves_.add(p2_.getMove());
            }
            StringList movesEvo_ = new StringList(d.getMoveTutors());
            for (Evolution e : d.getEvolutions().values()) {
                if (!(e instanceof EvolutionMove)) {
                    continue;
                }
                movesEvo_.add(((EvolutionMove) e).getMove());
            }
            DataInfoChecker.checkStringListContains(moves_,movesEvo_,this);
        }
    }

    private void checkKoUserHealSubst(MoveData _move) {
        boolean foundAfter_ = false;
        for (Effect e : _move.getEffects()) {
            if (foundAfter_) {
                setError(true);
            }
            if (!(e instanceof EffectStatus)) {
                continue;
            }
            EffectStatus eff_ = (EffectStatus) e;
            if (eff_.getKoUserHealSubst()) {
                if (e.getTargetChoice() != TargetChoice.LANCEUR) {
                    setError(true);
                }
                foundAfter_ = true;
            }
        }
    }

    public void validateEvolutions() {
        for (EntryCust<String,PokemonData> e: pokedex.entryList()) {
            if (!StringUtil.contains(legPks,e.getKey())) {
                continue;
            }
            if (!e.getValue().getEvolutions().isEmpty()) {
                setError(true);
            }
            if (!StringUtil.quickEq(e.getKey(),e.getValue().getBaseEvo())) {
                setError(true);
            }
        }
        initFamilies();
        CustList<StringList> lists_ = new CustList<StringList>();
        for (PokemonFamily f : families.values()) {
            lists_.add(f.getAllPokemon());
        }
        if (!StringUtil.disjoints(lists_)) {
            setError(true);
        }
        StringList allPokemon_ = new StringList();
        for (StringList l : lists_) {
            allPokemon_.addAllElts(l);
        }
        if (!StringUtil.equalsSet(allPokemon_, pokedex.getKeys())) {
            setError(true);
        }
    }

    public void initFamilies() {
        families = new StringMap<PokemonFamily>();
        StringList listEvoBases_ = new StringList();
        for (PokemonData d : getPokedex().values()) {
            listEvoBases_.add(d.getBaseEvo());
        }
        listEvoBases_.removeDuplicates();
        for (String p : listEvoBases_) {
            families.put(p, new PokemonFamily(this, p));
        }
    }

    public void initValue(String _key, String _value) {
        if (StringUtil.quickEq(_key, DEF_MOVE)) {
            setDefMove(_value);
        } else if (StringUtil.quickEq(_key, RATE_BOOST)) {
            setRateBoost(_value);
        } else if (StringUtil.quickEq(_key,
                RATE_BOOST_CRITICAL_HIT)) {
            setRateBoostCriticalHit(_value);
        } else if (StringUtil.quickEq(_key, RATE_FLEEING)) {
            setRateFleeing(_value);
        } else if (StringUtil.quickEq(_key, RATE_CATCHING)) {
            setRateCatching(_value);
        } else if (StringUtil.quickEq(_key, BALL_DEF)) {
            setBallDef(_value);
        } else if (StringUtil.quickEq(_key, DEFAULT_EGG_GROUP)) {
            setDefaultEggGroup(_value);
        } else if (StringUtil.quickEq(_key, DAMAGE_FORMULA)) {
            setDamageFormula(_value);
        } else if (StringUtil.quickEq(_key, DEF_CAT)) {
            setDefCategory(_value);
        }
    }
    public void validateConstants() {
        if (getDefaultMoney().isZero()) {
            setError(true);
        }
        if (!getDefaultMoney().isZeroOrGt()) {
            setError(true);
        }
        if (getMinHp().isZeroOrLt()) {
            setError(true);
        }
        if (!getStab().greaterThanOne()) {
            setError(true);
        }
        if (getDefBaseMove().isZeroOrLt()) {
            getConstNum().put(DataBase.DEF_BASE_MOVE,getDefaultPower());
        }
        nbPkTeam();
        maxPp();
        if (getWonHappinessByGrowLevel().isZeroOrLt()) {
            setError(true);
        }
        levelBounds();
        stepBounds();
        evIvHappinessBounds();
        boostBounds();
        if (getDefaultEggGroup().isEmpty()) {
            setError(true);
        }
        if (getDefCategory().isEmpty()) {
            setError(true);
        }
        if (!(items.getVal(getBallDef()) instanceof Ball)) {
            setError(true);
        }
        if (!moves.contains(getDefMove())) {
            setError(true);
        }
    }

    private void evIvHappinessBounds() {
        if (getMaxEv() < 0) {
            setError(true);
        }
        if (getMaxIv() < 31) {
            setError(true);
        }
        if (getNbNecStepsIncrHappiness() <= 0) {
            setError(true);
        }
        if (getMaxEv() > 255) {
            setError(true);
        }
        if (getMaxIv() > 255) {
            setError(true);
        }
        if (getNbNecStepsIncrHappiness() > 255) {
            setError(true);
        }
        if (getHappinessMax() < 0) {
            setError(true);
        }
        if (getHappinessMax() > 255) {
            setError(true);
        }
        if (getHappinessMax() < getHappinessEvo()) {
            setError(true);
        }
    }

    private void boostBounds() {
        if (getMaxBoost() < getDefaultBoost()) {
            setError(true);
        }
        if (getDefaultBoost() < getMinBoost()) {
            setError(true);
        }
    }

    private void stepBounds() {
        if (getNbMaxSteps() > 2048) {
            setError(true);
        }
        if (getNbMaxSteps() <= 0) {
            setError(true);
        }
        if (getNbMaxStepsSameEvoBase() >= getNbMaxSteps()) {
            setError(true);
        }
        if (getNbMaxStepsSameEvoBase() <= 0) {
            setError(true);
        }
    }

    private void levelBounds() {
        if (getMaxLevel() < 0) {
            setError(true);
        }
        if (getMinLevel() < 1) {
            setError(true);
        }
        if (getMaxLevel() > 1023) {
            setError(true);
        }
        if (getMinLevel() > getMaxLevel()) {
            setError(true);
        }
    }

    private void maxPp() {
        if (getMaxPp() <= 0) {
            setError(true);
        }
        if (getMaxPp() > 255) {
            setError(true);
        }
    }

    private void nbPkTeam() {
        if (getNbMaxTeam() < 2) {
            setError(true);
        }
        if (getNbMaxTeam() > 8) {
            setError(true);
        }
    }

    public void validateImages(SexListInt _sexListInt) {
        DataInfoChecker.checkStringListContains(animStatus.getKeys(),status.getKeys(),this);
        StringList statisNames_ = statisNames();
        DataInfoChecker.checkStringListContains(animStatis.getKeys(),statisNames_,this);
        if (!StringUtil.equalsSet(types, typesColors.getKeys())) {
            setError(true);
        }
        if (!StringUtil.equalsSet(types, typesImages.getKeys())) {
            setError(true);
        }
        for (String v : typesColors.values()) {
            if (ConverterBufferedImage.getIntColor(v, SEPARATOR_RGB) == -1) {
                setError(true);
            }
        }
        checkPlacesImages();
        checkInners(links.values());
        checkInners(people.values());
        notEmptyImages(trainers.values());
        for (Direction d : Direction.all()) {
            for (Sex s : _sexListInt.all()) {
                ImageHeroKey key_;
                key_ = new ImageHeroKey(EnvironmentType.ROAD, d, s);
                if (!overWorldHeros.contains(key_)) {
                    setError(true);
                }
            }
        }
        checkInners(overWorldHeros.values());
        checkHeros(frontHeros,_sexListInt);
        checkHeros(backHeros,_sexListInt);
        notEmptyImages(frontHeros.values());
        notEmptyImages(backHeros.values());
        boundsPk();
        notEmptyImages(typesImages.values());
        checkInners(miniItems.values());
        checkExact(miniMap.values());
        checkExact(animStatis.values());
        checkExact(animStatus.values());
        checkExact(animAbsorb);
        checkInners(miniPk.values());
        checkInners(imageTmHm);
        checkInners(storage);
        notEmptyImages(maxiPkBack.values());
        notEmptyImages(maxiPkFront.values());
        DataInfoChecker.checkStringListContains(miniPk.getKeys(),pokedex.getKeys(),this);
        DataInfoChecker.checkStringListContains(miniItems.getKeys(),items.getKeys(),this);
        DataInfoChecker.checkStringListContains(maxiPkBack.getKeys(),pokedex.getKeys(),this);
        DataInfoChecker.checkStringListContains(maxiPkFront.getKeys(),pokedex.getKeys(),this);
        notEmptyImage(endGameImage);
        for (TileMiniMap t : map.getMiniMap().values()) {
            if (!miniMap.contains(t.getFile())) {
                setError(true);
            }
        }
        if (isError()) {
            return;
        }
        setupPseudoImages();
    }

    private void checkPlacesImages() {
        for (Place p : map.getPlaces()) {
            if (!p.hasValidImage(this)) {
                setError(true);
            }
        }
    }

    public void boundsPk() {
        updateDims(maxiPkBack.values());
        updateDims(maxiPkFront.values());
    }

    private void updateDims(CustList<int[][]> _imgs) {
        for (int[][] i : _imgs) {
            updateDims(i);
        }
    }

    private void updateDims(int[][] _i) {
        if (_i.length != 0) {
            maxWidthPk = NumberUtil.max(maxWidthPk, _i[0].length);
            maxHeightPk = NumberUtil.max(maxHeightPk, _i.length);
        }
    }

    private void checkHeros(ImageHeroKeys _images, SexListInt _sexListInt) {
        for (Sex s : _sexListInt.all()) {
            ImageHeroKey key_;
            key_ = new ImageHeroKey(EnvironmentType.ROAD, s);
            if (!_images.contains(key_)) {
                setError(true);
            }
        }
    }

    private void checkExact(CustList<int[][]> _imgs) {
        for (int[][] i : _imgs) {
            checkExact(i);
        }
    }

    private void checkExact(int[][] _i) {
        if (_i.length != map.getSideLength()) {
            setError(true);
        }
        if (_i.length != 0 && _i[0].length != map.getSideLength()) {
            setError(true);
        }
    }

    private void notEmptyImages(CustList<int[][]> _imgs) {
        for (int[][] i : _imgs) {
            notEmptyImage(i);

        }
    }

    private void notEmptyImage(int[][] _i) {
        if (_i.length == 0) {
            setError(true);
        }
    }

    private void checkInners(CustList<int[][]> _imgs) {
        for (int[][] i : _imgs) {
            checkInners(i);
        }
    }

    private void checkInners(int[][] _i) {
        if (_i.length > map.getSideLength()) {
            setError(true);
        }
        if (_i.length == 0 || _i[0].length > map.getSideLength()) {
            setError(true);
        }
    }

    public static StringList statisNames() {
        return st(Statistic.getStatisticsWithBoost());
    }

    public static StringList st(CustList<Statistic> _ls) {
        StringList statisNames_ = new StringList();
        for (Statistic s : _ls) {
            statisNames_.add(s.getStatName());
        }
        return statisNames_;
    }

    public void validateTranslations() {
        StringList allCustKeys_ = new StringList();
        StringList allStandardKeys_ = new StringList();
        StringList homonyms_ = new StringList();
        StringList distinct_ = new StringList();
        standardKeysGenders(allStandardKeys_, homonyms_, distinct_);
        standardKeysBooleans(allStandardKeys_);
        standardKeysDiffWinPts(allStandardKeys_);
        standardKeysDiffModelLaw(allStandardKeys_);
        standardKeysEnvironment(allStandardKeys_, homonyms_, distinct_);
        standardKeysStatistic(allStandardKeys_, homonyms_, distinct_);
        custKeys(allCustKeys_, homonyms_, distinct_, translatedTypes, types);
        custKeys(allCustKeys_, homonyms_, distinct_, translatedCategories, allCategories);
        custKeys(allCustKeys_, homonyms_, distinct_, translatedPokemon, pokedex.getKeys());
        custKeys(allCustKeys_, homonyms_, distinct_, translatedItems, items.getKeys());
        custKeys(allCustKeys_, homonyms_, distinct_, translatedAbilities, abilities.getKeys());
        custKeys(allCustKeys_, homonyms_, distinct_, translatedMoves, moves.getKeys());
        custKeys(allCustKeys_, homonyms_, distinct_, translatedStatus, status.getKeys());
        homonyms_.removeDuplicates();
        basicCheckFctMath();
        checkHomonym(homonyms_);

        checkClassesDescriptions();
        standardKeysTargets(allStandardKeys_);
        if (allCustKeys_.hasDuplicates()) {
            setError(true);
        }
        for (String n : allStandardKeys_) {
            if (StringUtil.contains(allCustKeys_, n)) {
                setError(true);
            }
        }
        if (!StringUtil.equalsSet(litterals.getKeys(),
                languages)) {
            setError(true);
        }
        for (String v: variables) {
            for (EntryCust<String,StringMap<String>> m: litterals.entryList()) {
                checkLittVar(v, m);
            }
        }
    }

    private void checkLittVar(String _v, EntryCust<String, StringMap<String>> _m) {
        boolean f_ = false;
        String line_ = EMPTY_STRING;
        StringList varParts_ = StringUtil.splitStrings(_v, SEP_BETWEEN_KEYS);
        String var_ = StringUtil.join(varParts_.left( 2), SEP_BETWEEN_KEYS);
        for (EntryCust<String,String> e: _m.getValue().entryList()) {
            if (StringUtil.quickEq(var_, StringUtil.concat(VAR_PREFIX ,e.getKey()))) {
                f_ = true;
                line_ = e.getValue();
                break;
            }
        }
        if (!f_) {
            setError(true);
        } else {
            StringList infos_ = StringUtil.splitStrings(line_, TAB);
            if (infos_.size() < 3) {
                setError(true);
            } else {
                CustList<String> infosVar_ = varParts_.mid(2);
                if (!infosVar_.isEmpty()) {
                    StringList kinds_ = StringUtil.splitChar(infos_.first(), getSepartorSetChar());
                    checkVars(infosVar_, kinds_);
                }
            }
        }
    }

    private void checkClassesDescriptions() {
        if (!StringUtil.equalsSet(translatedClassesDescriptions.getKeys(),
                languages)) {
            setError(true);
        }
        StringList classesItems_;
        classesItems_ = new StringList();
        for (Item i : items.values()) {
            classesItems_.add(i.getItemType());
        }
        for (StringMap<String> v : translatedClassesDescriptions.values()) {
            if (!v.containsAllAsKeys(classesItems_)) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void checkHomonym(StringList _homonyms) {
        for (String l : languages) {
            for (String s : _homonyms) {
                StringList tr_ = new StringList();
                valuesTr(l, s, tr_, translatedMoves);
                valuesTr(l, s, tr_, translatedTypes);
                valuesTr(l, s, tr_, translatedAbilities);
                valuesTr(l, s, tr_, translatedPokemon);
                valuesTr(l, s, tr_, translatedItems);
                valuesTr(l, s, tr_, translatedStatus);
                valuesTr(l, s, tr_, translatedCategories);
                valuesTrStatistic(l, s, tr_);
                valuesTrGenders(l, s, tr_);
                if (!tr_.onlyOneElt()) {
                    setError(true);
                }
            }
        }
    }

    private void basicCheckFctMath() {
        if (!StringUtil.equalsSet(translatedFctMath.getKeys(),
                languages)) {
            setError(true);
        }
        for (StringMap<String> v : translatedFctMath.values()) {
            if (!v.containsAllAsKeys(EvolvedMathFactory.getFunctions())) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysStatistic(StringList _allStandardKeys, StringList _homonyms, StringList _distinct) {
        if (!StringUtil.equalsSet(translatedStatistics.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<Statistic, String> v : translatedStatistics.values()) {
            for (Statistic g : v.getKeys()) {
                _allStandardKeys.add(g.getStatName());
            }
            if (!new IdList<Statistic>(v.getKeys()).containsAllObj(new IdList<Statistic>(Statistic.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
        for (Statistic g : Statistic.all()) {
            String name_ = g.getStatName();
            gearHomonyms(_homonyms, _distinct, name_);
        }
    }

    private void standardKeysEnvironment(StringList _allStandardKeys, StringList _homonyms, StringList _distinct) {
        if (!StringUtil.equalsSet(translatedEnvironment.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<EnvironmentType, String> v : translatedEnvironment
                .values()) {
            for (EnvironmentType g : v.getKeys()) {
                _allStandardKeys.add(g.getEnvName());
            }
            if (!new IdList<EnvironmentType>(v.getKeys()).containsAllObj(new IdList<EnvironmentType>(EnvironmentType.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
        for (EnvironmentType g : EnvironmentType.all()) {
            String name_ = g.getEnvName();
            gearHomonyms(_homonyms, _distinct, name_);
        }
    }

    private void standardKeysDiffModelLaw(StringList _allStandardKeys) {
        if (!StringUtil.equalsSet(translatedDiffModelLaw.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<DifficultyModelLaw, String> v : translatedDiffModelLaw
                .values()) {
            for (DifficultyModelLaw g : v.getKeys()) {
                _allStandardKeys.add(g.getModelName());
            }
            if (!new IdList<DifficultyModelLaw>(v.getKeys()).containsAllObj(new IdList<DifficultyModelLaw>(DifficultyModelLaw.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysDiffWinPts(StringList _allStandardKeys) {
        if (!StringUtil.equalsSet(translatedDiffWinPts.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<DifficultyWinPointsFight, String> v : translatedDiffWinPts
                .values()) {
            for (DifficultyWinPointsFight g : v.getKeys()) {
                _allStandardKeys.add(g.getWinName());
            }
            if (!new IdList<DifficultyWinPointsFight>(v.getKeys()).containsAllObj(new IdList<DifficultyWinPointsFight>(DifficultyWinPointsFight.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysBooleans(StringList _allStandardKeys) {
        if (!StringUtil.equalsSet(translatedBooleans.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<SelectedBoolean, String> v : translatedBooleans.values()) {
            for (SelectedBoolean g : v.getKeys()) {
                _allStandardKeys.add(g.getBoolName());
            }
            if (!new IdList<SelectedBoolean>(v.getKeys()).containsAllObj(new IdList<SelectedBoolean>(SelectedBoolean.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysGenders(StringList _allStandardKeys, StringList _homonyms, StringList _distinct) {
        if (!StringUtil.equalsSet(translatedGenders.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<Gender, String> v : translatedGenders.values()) {
            for (Gender g : v.getKeys()) {
                _allStandardKeys.add(g.getGenderName());
            }
            if (!new IdList<Gender>(v.getKeys()).containsAllObj(new IdList<Gender>(Gender.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
        for (Gender g : Gender.all()) {
            String name_ = g.getGenderName();
            gearHomonyms(_homonyms, _distinct, name_);
        }
    }

    private void standardKeysTargets(StringList _allStandardKeys) {
        if (!StringUtil.equalsSet(translatedTargets.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<TargetChoice, String> v : translatedTargets.values()) {
            for (TargetChoice g : v.getKeys()) {
                _allStandardKeys.add(g.getTargetName());
            }
            if (!new IdList<TargetChoice>(v.getKeys()).containsAllObj(new IdList<TargetChoice>(TargetChoice.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void checkVars(CustList<String> _infosVar, StringList _kinds) {
        int len_ = _infosVar.size();
        if (len_ != _kinds.size()) {
            setError(true);
            return;
        }
        for (int i = 0; i < len_; i++) {
            checkVar(_infosVar, _kinds, i);
        }
    }

    private void checkVar(CustList<String> _infosVar, StringList _kinds, int _i) {
        String k_ = _kinds.get(_i);
        if (StringUtil.quickEq(k_, MOVE_FORMULA) && !moves.contains(_infosVar.get(_i))) {
            setError(true);
        }
        if (StringUtil.quickEq(k_, CAT_FORMULA) && !StringUtil.contains(categories, _infosVar.get(_i))) {
            setError(true);
        }
        if (StringUtil.quickEq(k_, TYPE_FORMULA) && !StringUtil.contains(types, _infosVar.get(_i))) {
            setError(true);
        }
        if (StringUtil.quickEq(k_, STATUS_FORMULA) && !status.contains(_infosVar.get(_i))) {
            setError(true);
        }
        if (StringUtil.quickEq(k_, STATIS_FORMULA) && !notIn(_infosVar.get(_i))) {
            setError(true);
        }
    }

    private boolean notIn(String _value) {
        boolean ok_ = false;
        for (Statistic s: Statistic.all()) {
            if (StringUtil.quickEq(s.getStatName(), _value)) {
                ok_ = true;
                break;
            }
        }
        return ok_;
    }

    private void valuesTrGenders(String _l, String _s, StringList _tr) {
        for (Gender g : Gender.all()) {
            if (!StringUtil.quickEq(_s, g.getGenderName())) {
                continue;
            }
            for (EntryCust<String,IdMap<Gender,String>> e: translatedGenders.entryList()) {
                if (!StringUtil.quickEq(e.getKey(), _l)) {
                    continue;
                }
                for (EntryCust<Gender,String> f: e.getValue().entryList()) {
                    if (f.getKey() != g) {
                        continue;
                    }
                    _tr.add(f.getValue());
                }
            }
        }
    }

    private void valuesTrStatistic(String _l, String _s, StringList _tr) {
        for (Statistic s_ : Statistic.all()) {
            if (!StringUtil.quickEq(_s, s_.getStatName())) {
                continue;
            }
            for (EntryCust<String,IdMap<Statistic,String>> e: translatedStatistics.entryList()) {
                if (!StringUtil.quickEq(e.getKey(), _l)) {
                    continue;
                }
                for (EntryCust<Statistic,String> f: e.getValue().entryList()) {
                    if (f.getKey() != s_) {
                        continue;
                    }
                    _tr.add(f.getValue());
                }
            }
        }
    }

    private void valuesTr(String _l, String _s, StringList _tr, StringMap<StringMap<String>> _translated) {
        for (EntryCust<String,StringMap<String>> e: _translated.entryList()) {
            if (!StringUtil.quickEq(e.getKey(), _l)) {
                continue;
            }
            for (EntryCust<String,String> f: e.getValue().entryList()) {
                if (!StringUtil.quickEq(f.getKey(), _s)) {
                    continue;
                }
                _tr.add(f.getValue());
            }
        }
    }

    private void custKeys(StringList _allCustKeys, StringList _homonyms, StringList _distinct, StringMap<StringMap<String>> _translated, CustList<String> _keys) {
        if (!StringUtil.equalsSet(_translated.getKeys(),
                languages)) {
            setError(true);
        }
        _allCustKeys.addAllElts(_keys);
        for (StringMap<String> v : _translated.values()) {
            if (!StringUtil.equalsSet(v.getKeys(), _keys)) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
        for (String g : _keys) {
            gearHomonyms(_homonyms, _distinct, g);
        }
    }

    private static void gearHomonyms(Listable<String> _homonyms, CustList<String> _distinct, String _cst) {
        if (!StringUtil.contains(_distinct, _cst)) {
            _distinct.add(_cst);
        } else {
            _homonyms.add(_cst);
        }
    }

    private static boolean hasDuplicates(Listable<String> _list) {
        StringList l_ = new StringList(_list);
        return l_.hasDuplicates();
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
        if (!StringUtil.equalsSet(moveTypes_, pkTypes_)) {
            setError(true);
        }
        types = moveTypes_;
    }

    public static boolean isCorrectIdentifier(String _string) {
        if (_string.trim().isEmpty()) {
            return false;
        }
        int len_ = _string.length();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            char curr_ = _string.charAt(i);
            boolean ok_ = okChar(curr_);
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

    private static boolean okChar(char _curr) {
        boolean ok_ = isLowerLetter(_curr);
        if (_curr >= 'A' && _curr <= 'Z') {
            ok_ = true;
        }
        if (_curr >= '0' && _curr <= '9') {
            ok_ = true;
        }
        if (_curr == UNDERSCORE) {
            ok_ = true;
        }
        return ok_;
    }

    public void setMiniPk(StringMap<int[][]> _miniPk) {
        miniPk = _miniPk;
    }

    public void setMaxiPkBack(StringMap<int[][]> _maxiPkBack) {
        maxiPkBack = _maxiPkBack;
    }

    public void setMaxiPkFront(StringMap<int[][]> _maxiPkFront) {
        maxiPkFront = _maxiPkFront;
    }

    public void setMiniItems(StringMap<int[][]> _miniItems) {
        miniItems = _miniItems;
    }

    public void setTrainers(StringMap<int[][]> _trainers) {
        trainers = _trainers;
    }

    public void setPeople(StringMap<int[][]> _people) {
        people = _people;
    }

    public void setTypesImages(StringMap<int[][]> _typesImages) {
        typesImages = _typesImages;
    }

    public void setTypesColors(StringMap<String> _typesColors) {
        typesColors = _typesColors;
    }

    public void setFrontHeros(ImageHeroKeys _frontHeros) {
        frontHeros = _frontHeros;
    }

    public void setBackHeros(ImageHeroKeys _backHeros) {
        backHeros = _backHeros;
    }

    public void setOverWorldHeros(ImageHeroKeys _overWorldHeros) {
        overWorldHeros = _overWorldHeros;
    }

    public void setLinks(StringMap<int[][]> _links) {
        links = _links;
    }

    public void setImages(StringMap<int[][]> _images) {
        images = _images;
    }

    public StringMap<ScreenCoordssInt> getImagesTiles() {
        return imagesTiles;
    }

    public void setImagesTiles(StringMap<ScreenCoordssInt> _imagesTiles) {
        imagesTiles = _imagesTiles;
    }

    public void setMiniMap(StringMap<int[][]> _miniMap) {
        miniMap = _miniMap;
    }

    public void setCombos(Combos _combos) {
        combos = _combos;
    }

    public StringMap<Rate> getConstNum() {
        return constNum;
    }

    public void setConstNum(StringMap<Rate> _constNum) {
        constNum = _constNum;
    }

    public void setRateBoostCriticalHit(String _rateBoostCriticalHit) {
        rateBoostCriticalHit = _rateBoostCriticalHit;
    }

    public String getRateFleeing() {
        return rateFleeing;
    }

    public void setRateFleeing(String _rateFleeing) {
        rateFleeing = _rateFleeing;
    }

    public String getDefMove() {
        return defMove;
    }

    public void setDefMove(String _defMove) {
        defMove = _defMove;
    }

    public void setRateBoost(String _rateBoost) {
        rateBoost = _rateBoost;
    }

    public void setDamageFormula(String _damageFormula) {
        damageFormula = _damageFormula;
    }

    public String getDefCategory() {
        return defCategory;
    }

    public void setDefCategory(String _d) {
        this.defCategory = _d;
    }

    public String getBallDef() {
        return ballDef;
    }

    public void setBallDef(String _ballDef) {
        ballDef = _ballDef;
    }

    public void setDefaultEggGroup(String _defaultEggGoup) {
        defaultEggGroup = _defaultEggGoup;
    }

    public String getRateCatching() {
        return rateCatching;
    }

    public void setRateCatching(String _rateCatching) {
        rateCatching = _rateCatching;
    }

    public void setExpGrowth(IdMap<ExpType, String> _expGrowth) {
        expGrowth = _expGrowth;
    }

    public void setRates(IdMap<DifficultyWinPointsFight, String> _rates) {
        rates = _rates;
    }

    public void setTableTypes(TypesDuos _tableTypes) {
        tableTypes = _tableTypes;
    }

    public void setTypes(StringList _types) {
        types = _types;
    }

    public void setLawsDamageRate(IdMap<DifficultyModelLaw, LawNumber> _lawsDamageRate) {
        lawsDamageRate = _lawsDamageRate;
    }

    public void setAnimAbsorb(int[][] _a) {
        animAbsorb = _a;
    }

    public void initTranslations() {
        translatedBooleans = new StringMap<IdMap<SelectedBoolean, String>>();
        translatedDiffWinPts = new StringMap<IdMap<DifficultyWinPointsFight, String>>();
        translatedDiffModelLaw = new StringMap<IdMap<DifficultyModelLaw, String>>();
        translatedGenders = new StringMap<IdMap<Gender, String>>();
        translatedEnvironment = new StringMap<IdMap<EnvironmentType, String>>();
        translatedStatistics = new StringMap<IdMap<Statistic, String>>();
        translatedPokemon = new StringMap<StringMap<String>>();
        translatedMoves = new StringMap<StringMap<String>>();
        translatedItems = new StringMap<StringMap<String>>();
        translatedStatus = new StringMap<StringMap<String>>();
        translatedAbilities = new StringMap<StringMap<String>>();
        translatedCategories = new StringMap<StringMap<String>>();
        translatedTypes = new StringMap<StringMap<String>>();
        translatedFctMath = new StringMap<StringMap<String>>();
        translatedTargets = new StringMap<IdMap<TargetChoice, String>>();
        translatedClassesDescriptions = new StringMap<StringMap<String>>();
        litterals = new StringMap<StringMap<String>>();
    }

    public void setLanguage(String _language) {
        language = _language;
    }

    public static String toUpperCase(String _string) {
        int len_ = _string.length();
        StringBuilder str_ = new StringBuilder(len_);
        for (int i = 0; i < len_; i++) {
            char curr_ = _string.charAt(i);
            if (isLowerLetter(curr_)) {
                int char_ = curr_ - 'a' + 'A';
                str_.append((char)char_);
                continue;
            }
            str_.append(curr_);
        }
        return str_.toString();
    }

    private static boolean isLowerLetter(char _curr) {
        return _curr >= 'a' && _curr <= 'z';
    }

    public void setMessages(DataBase _other) {
        messagesPokemonPlayer = _other.messagesPokemonPlayer;
        messagesPlayer = _other.messagesPlayer;
        messagesFighter = _other.messagesFighter;
        messagesTeam = _other.messagesTeam;
        messagesFight = _other.messagesFight;
        messagesGame = _other.messagesGame;
    }

    public void setMessagesGame(StringMap<String> _messagesGame) {
        messagesGame = _messagesGame;
    }

    public void setMessagesPokemonPlayer(StringMap<String> _messagesPokemonPlayer) {
        messagesPokemonPlayer = _messagesPokemonPlayer;
    }

    public StringMap<String> getMessagesPokemonPlayer() {
        return messagesPokemonPlayer;
    }

    public void setMessagesFight(StringMap<String> _messagesFight) {
        messagesFight = _messagesFight;
    }

    public StringMap<String> getMessagesPlayer() {
        return messagesPlayer;
    }

    public void setMessagesPlayer(StringMap<String> _messagesPlayer) {
        messagesPlayer = _messagesPlayer;
    }

    public StringMap<String> getMessagesFighter() {
        return messagesFighter;
    }

    public void setMessagesFighter(StringMap<String> _messagesFighter) {
        messagesFighter = _messagesFighter;
    }

    public StringMap<String> getMessagesTeam() {
        return messagesTeam;
    }

    public void setMessagesTeam(StringMap<String> _messagesTeam) {
        messagesTeam = _messagesTeam;
    }

    public String getFighterName(boolean _foe, String _foeStr, String _allyStr, String _name) {
        StringMap<String> messages_ = getMessagesFight();
        String value_;
        if (_foe) {
            value_ = messages_.getVal(_foeStr);
        } else {
            value_ = messages_.getVal(_allyStr);
        }
        if (value_ == null) {
            return DataBase.EMPTY_STRING;
        }
        return StringUtil.simpleStringsFormat(value_, _name);
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
            ScreenCoordssInt tiles_;
            tiles_ = new ScreenCoordssInt();
            for (short x = 0; x < d_.getWidth(); x++) {
                for (short y = 0; y < d_.getHeight(); y++) {
                    ScreenCoords sc_ = new ScreenCoords(x, y);
                    tiles_.addEntry(sc_, BaseSixtyFourUtil.clipSixtyFour(img_, x * side_, y
                            * side_, side_, side_));
                }
            }
            imagesTiles.addEntry(name_, tiles_);
        }

    }

    void checkCaseOfFiles(String _folderName, StringList _files) {
        StringList filesNamesWithSameCase_;
        filesNamesWithSameCase_ = new StringList();
        for (String s : _files) {
            String upperCase_ = toUpperCase(s);
            if (StringUtil.contains(filesNamesWithSameCase_, upperCase_)) {
                String name_ = StringUtil.concat(_folderName, SEPARATOR_FILES,
                        upperCase_);
                if (!StringUtil.contains(filesWithSameNameDifferentCase, name_)) {
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
        tm = new ShortMap< String>();
        tmPrice = new ShortMap< LgInt>();
        hm = new ShortMap< String>();
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
        legPks = new StringList();
    }

    public void calculateAvgPound() {
        if (!pokedex.isEmpty()) {
            avgWeight.divideBy(new Rate(pokedex.size()));
        }
    }

    public void completeMembers(String _pokemonName, PokemonData _pokemon) {
        updateInfo(_pokemonName,_pokemon);
        pokedex.put(_pokemonName, _pokemon);
    }

    public void completeQuickMembers(String _pokemonName, PokemonData _pokemon) {
        updateInfo(_pokemonName,_pokemon);
        pokedex.addEntry(_pokemonName, _pokemon);
    }

    private void updateInfo(String _pokemonName, PokemonData _pokemon) {
        avgWeight.addNb(_pokemon.getWeight());
        if (_pokemon.getGenderRep() == GenderRepartition.LEGENDARY) {
            legPks.add(_pokemonName);
        }
    }
    public void completeQuickMembers(String _moveName, MoveData _move) {
        updateInfo(_moveName, _move);
        moves.addEntry(_moveName, _move);
    }
    public void completeMembers(String _moveName, MoveData _move) {
        updateInfo(_moveName, _move);
        moves.put(_moveName, _move);
    }
    public String getCategory(MoveData _move) {
        if (_move instanceof DamagingMoveData) {
            return ((DamagingMoveData)_move).getCategory();
        }
        return defCategory;
    }

    private void updateInfo(String _moveName, MoveData _move) {
        if (_move instanceof DamagingMoveData) {
            categories.add(((DamagingMoveData)_move).getCategory());
        }
        allCategories.add(getCategory(_move));

        variables.addAllElts(getVariableWords(_move.getAccuracy()));

        EndRoundMainElements endTurn_;
        if (_move.getRankIncrementNbRound() > 0) {
            endTurn_ = new EndRoundMainElements(null);
            endTurn_.setNumberIncrement(_move.getRankIncrementNbRound());
            endTurn_.setIncrementNumberOfRounds(true);
            endTurn_.setEndRoundType(EndTurnType.ATTAQUE);
            endTurn_.setElement(_moveName);
            endTurn_.setRelation(RelationType.INDIVIDUEL);
            evtEndRound.add(endTurn_);
        }
        for (Effect e : _move.getEffects()) {

            updateInfoEffect(_moveName, _move, e);
        }
        if (!StringUtil.contains(movesEffectIndiv, _moveName) && !StringUtil.contains(movesEffEndRoundIndiv, _moveName) && _move.getRepeatRoundLaw().events().size() > 0 && _move.getConstUserChoice()) {
            movesConstChoices.add(_moveName);
        }
    }

    private void updateInfoEffect(String _moveName, MoveData _move, Effect _e) {
        variables.addAllElts(getVariableWords(_e.getFail()));

        if (_e instanceof EffectCopyMove && ((EffectCopyMove) _e).getCopyingMoveForUser() > 0) {
            movesCopyingTemp.add(_moveName);
            movesCopyingTemp.removeDuplicates();
        }
        if (_e instanceof EffectCounterAttack) {
            EffectCounterAttack effectCounterAttack_;
            effectCounterAttack_ = (EffectCounterAttack) _e;

            variables.addAllElts(getVariableWords(effectCounterAttack_
                    .getCounterFail()));
            variables.addAllElts(getVariableWords(effectCounterAttack_
                    .getProtectFail()));
            movesCountering.add(_moveName);
            movesCountering.removeDuplicates();
        }
        if (_e instanceof EffectProtection) {
            EffectProtection effetProtection_ = (EffectProtection) _e;
            updateInfoEffectProtection(_moveName, effetProtection_);
        }
        if (_e instanceof EffectAccuracy) {
            movesAccuracy.add(_moveName);
            movesAccuracy.removeDuplicates();
        }
        if (_e instanceof EffectAlly) {
            movesEffectAlly.add(_moveName);
            movesEffectAlly.removeDuplicates();
        }
        if (_e instanceof EffectDamage) {

            updateInfoEffectDamage((EffectDamage) _e);
        }
        updateInfoEffectDef(_moveName, _move, _e);
    }

    private void updateInfoEffectDef(String _moveName, MoveData _move, Effect _e) {
        if (_e instanceof EffectEndRound) {
            EffectEndRound e_ = (EffectEndRound) _e;
            updateInfoEffectEndRound(_moveName, _move, e_);
        }
        if (_e instanceof EffectUnprotectFromTypes
                || _e instanceof EffectProtectFromTypes) {
            updateInfoEffectChgtProtect(_moveName, _move, _e);
        }
        if (_e instanceof EffectSwitchMoveTypes) {
            movesChangingTypes.add(_moveName);
        }
        if (_e instanceof EffectRestriction) {
            EffectRestriction effetAntiChoix_ = (EffectRestriction) _e;
            updateInfoEffectRestriction(_moveName, _move, effetAntiChoix_);
        }
        if (_e instanceof EffectTeam) {
            movesEffectTeam.add(_moveName);
        }
        if (_e instanceof EffectGlobal) {
            EffectGlobal effetGlobal_ = (EffectGlobal) _e;
            updateInfoEffectGlobal(_moveName, effetGlobal_);
        }
        if (_e instanceof EffectStatistic) {
            updateInfoEffectStatistic((EffectStatistic) _e);
        }
        if (_e instanceof EffectStatus) {
            updateInfoEffectStatus(_moveName, (EffectStatus) _e);
        }
        if (_e instanceof EffectCommonStatistics) {
            updateInfoEffectCommonStatistic((EffectCommonStatistics) _e);
        }
        if (_e instanceof EffectFullHpRate) {

            variables.addAllElts(getVariableWords(((EffectFullHpRate) _e)
                    .getRestoredHp()));
        }
        if (_e instanceof EffectTeamWhileSendFoe) {
            movesEffectWhileSending.add(_moveName);

            variables
                    .addAllElts(getVariableWords(((EffectTeamWhileSendFoe) _e)
                            .getDamageRateAgainstFoe()));
            variables
                    .addAllElts(getVariableWords(((EffectTeamWhileSendFoe) _e)
                            .getFailSending()));
        }
        if (_e instanceof EffectInvoke) {
            movesInvoking.add(_moveName);
        }
    }

    private void updateInfoEffectCommonStatistic(EffectCommonStatistics _e) {
        for (String r : _e.getCommonValue()
                .values()) {

            variables.addAllElts(getVariableWords(r));
        }
    }

    private void updateInfoEffectStatus(String _moveName, EffectStatus _e) {
        for (String r : _e.getLocalFailStatus()
                .values()) {

            variables.addAllElts(getVariableWords(r));
        }
        if (_e.getKoUserHealSubst()) {
            movesFullHeal.add(_moveName);
        }
    }

    private void updateInfoEffectStatistic(EffectStatistic _e) {
        for (String r : _e.getLocalFailStatis()
                .values()) {

            variables.addAllElts(getVariableWords(r));
        }
        for (String r : _e
                .getLocalFailSwapBoostStatis().values()) {

            variables.addAllElts(getVariableWords(r));
        }
    }

    private void updateInfoEffectGlobal(String _moveName, EffectGlobal _effetGlobal) {
        if (_effetGlobal.getWeather()) {
            movesEffectGlobalWeather.add(_moveName);
        }
        movesEffectGlobal.add(_moveName);
    }

    private void updateInfoEffectRestriction(String _moveName, MoveData _move, EffectRestriction _effetAntiChoix) {
        if (_effetAntiChoix.getChoiceRestriction() == MoveChoiceRestrictionType.FORCE) {
            movesActingMoveUses.add(_moveName);
        } else if (_effetAntiChoix.getChoiceRestriction() == MoveChoiceRestrictionType.FORBIDDEN) {
            movesActingMoveUses.add(_moveName);
        } else if (_effetAntiChoix.getChoiceRestriction() == MoveChoiceRestrictionType.LANCEUR_ATTAQUES) {
            movesForbidding.add(_moveName);
        } else {
            movesEffectIndiv.add(_moveName);
            if (_move.getRepeatRoundLaw().events().size() > 0) {
                movesEffectIndivIncr.add(_moveName);
            }
        }
    }

    private void updateInfoEffectChgtProtect(String _moveName, MoveData _move, Effect _e) {
        if (_e instanceof EffectUnprotectFromTypes) {
            movesEffectUnprot.add(_moveName);
        }
        if (_e instanceof EffectProtectFromTypes) {
            movesEffectProt.add(_moveName);
        }
        if (_move.getRepeatRoundLaw().events().size() > 0) {
            movesEffectIndivIncr.add(_moveName);
        }
    }

    private void updateInfoEffectEndRound(String _moveName, MoveData _move, EffectEndRound _e) {
        EndRoundMainElements endTurn_;
        endTurn_ = new EndRoundMainElements(_e);
        endTurn_.setNumberIncrement((short) _e.getEndRoundRank());
        endTurn_.setIncrementNumberOfRounds(false);
        endTurn_.setEndRoundType(EndTurnType.ATTAQUE);
        endTurn_.setElement(_moveName);
        endTurn_.setRelation(_e.getRelation());
        evtEndRound.add(endTurn_);
        if (_e instanceof EffectEndRoundSingleRelation) {
            trappingMoves.add(_moveName);
        }
        if (_e instanceof EffectEndRoundIndividual) {
            movesEffEndRoundIndiv.add(_moveName);
            if (_move.getRepeatRoundLaw().events().size() > 0) {
                movesEffEndRoundIndivIncr.add(_moveName);
            }
        }
        if (_e instanceof EffectEndRoundPositionTargetRelation) {
            movesAnticipation.add(_moveName);
        }
        if (_e instanceof EffectEndRoundPositionRelation) {
            movesHealingAfter.add(_moveName);
        }

        variables.addAllElts(getVariableWords(_e.getFailEndRound()));
    }

    private void updateInfoEffectDamage(EffectDamage _e) {
        variables.addAllElts(getVariableWords(_e
                .getPower()));

        for (String event_ : _e.getDamageLaw().events()) {

            variables.addAllElts(getVariableWords(event_));

        }
    }

    private void updateInfoEffectProtection(String _moveName, EffectProtection _effetProtection) {
        if (_effetProtection.isProtTeamAgainstDamageMoves()) {
            movesProtAgainstDamageMoves.add(_moveName);
            movesProtAgainstDamageMoves.removeDuplicates();
        }
        if (_effetProtection.isProtTeamAgainstStatusMoves()) {
            movesProtAgainstStatusMoves.add(_moveName);
            movesProtAgainstStatusMoves.removeDuplicates();
        }
        if (_effetProtection.getProtTeamAgainstPrio()) {
            movesProtAgainstPrio.add(_moveName);
            movesProtAgainstPrio.removeDuplicates();
        }
        if (_effetProtection.getProtTeamAgainstMultTargets()) {
            movesProtAgainstMultiTarget.add(_moveName);
            movesProtAgainstMultiTarget.removeDuplicates();
        }
        if (_effetProtection.getProtSingle()) {
            movesProtSingleTarget.add(_moveName);
            movesProtSingleTarget.removeDuplicates();
        }
        if (!_effetProtection.getProtSingleAgainstKo().isZero()) {
            movesProtSingleTargetAgainstKo.add(_moveName);
            movesProtSingleTargetAgainstKo.removeDuplicates();
        }
    }

    public void completeMembers(String _objectName, Item _object) {
        updateInfo(_objectName, _object);
        items.put(_objectName, _object);
    }

    public void completeQuickMembers(String _objectName, Item _object) {
        updateInfo(_objectName, _object);
        items.addEntry(_objectName, _object);
    }

    private void updateInfo(String _objectName, Item _object) {
        if (_object instanceof ItemForBattle) {
            ItemForBattle obj_ = (ItemForBattle) _object;
            if (!obj_.getEffectEndRound().isEmpty()) {
                EndRoundMainElements endTurn_ = new EndRoundMainElements(obj_.getEffectEndRound()
                        .first());
                endTurn_.setNumberIncrement((short) obj_.getEffectEndRound()
                        .first().getEndRoundRank());
                endTurn_.setIncrementNumberOfRounds(false);
                endTurn_.setEndRoundType(EndTurnType.OBJET);
                endTurn_.setElement(_objectName);
                endTurn_.setRelation(obj_.getEffectEndRound().first()
                        .getRelation());
                evtEndRound.add(endTurn_);
            }

            variables.addAllElts(getVariableWords(StringUtil.join(new StringList(obj_
                    .getMultStat().values()), EMPTY_STRING)));
            variables.addAllElts(getVariableWords(obj_.getMultDamage()));
            variables.addAllElts(getVariableWords(obj_.getMultPower()));
            variables.addAllElts(getVariableWords(StringUtil.join(new StringList(obj_
                    .getFailStatus().values()), EMPTY_STRING)));
        }
    }

    public void completeMembers(String _abilityName, AbilityData _ability) {
        updateInfo(_abilityName, _ability);
        abilities.put(_abilityName, _ability);
    }

    public void completeQuickMembers(String _abilityName, AbilityData _ability) {
        updateInfo(_abilityName, _ability);
        abilities.addEntry(_abilityName, _ability);
    }

    private void updateInfo(String _abilityName, AbilityData _ability) {
        if (!_ability.getEffectEndRound().isEmpty()) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements(_ability.getEffectEndRound()
                    .first());
            endTurn_.setNumberIncrement((short) _ability.getEffectEndRound()
                    .first().getEndRoundRank());
            endTurn_.setIncrementNumberOfRounds(false);
            endTurn_.setEndRoundType(EndTurnType.CAPACITE);
            endTurn_.setElement(_abilityName);
            endTurn_.setRelation(_ability.getEffectEndRound().first()
                    .getRelation());
            evtEndRound.add(endTurn_);
        }

        variables.addAllElts(getVariableWords(StringUtil.join(new StringList(_ability
                .getMultStat().values()), EMPTY_STRING)));
        variables.addAllElts(getVariableWords(_ability.getMultDamage()));
        variables.addAllElts(getVariableWords(_ability.getMultPower()));
        variables.addAllElts(getVariableWords(StringUtil.join(new StringList(_ability
                .getFailStatus().values()), EMPTY_STRING)));
    }

    public void completeMembers(String _statusName, Status _status) {
        updateInfo(_statusName, _status);
        status.put(_statusName, _status);
    }

    public void completeQuickMembers(String _statusName, Status _status) {
        updateInfo(_statusName, _status);
        status.addEntry(_statusName, _status);
    }

    private void updateInfo(String _statusName, Status _status) {
        EffectEndRoundStatus e_ = endRound(_status);
        if (e_ != null) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements(e_);
            endTurn_.setNumberIncrement((short) e_.getEndRoundRank());
            endTurn_.setIncrementNumberOfRounds(false);
            endTurn_.setEndRoundType(EndTurnType.STATUT);
            endTurn_.setElement(_statusName);
            endTurn_.setRelation(e_
                    .getRelation());
            evtEndRound.add(endTurn_);
        }
        if (_status.getIncrementingEndRound()) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements(null);
            endTurn_.setNumberIncrement((short) _status.getIncrementEndRound());
            endTurn_.setIncrementNumberOfRounds(true);
            endTurn_.setEndRoundType(EndTurnType.STATUT);
            endTurn_.setElement(_statusName);
            if (_status.getStatusType() == StatusType.INDIVIDUEL) {
                endTurn_.setRelation(RelationType.STATUT);
            } else {
                endTurn_.setRelation(RelationType.STATUT_RELATION);
            }
            evtEndRound.add(endTurn_);
        }
    }

    public static EffectEndRoundStatus endRound(Status _status) {
        EffectEndRoundStatus e_;
        if (!_status.getEffectEndRound().isEmpty()) {
            e_ = _status.getEffectEndRound()
                    .first();
        } else {
            e_ = null;
        }
        return e_;
    }

    public void initCombosTest() {
        combos = new Combos();
        combos.setEffects(new ListEffectCombos());
    }

    public void completeMembersCombos() {
        for (StringList k : combos.getEffects().getKeys()) {
            EffectCombo ef_ = combos.getEffects().getVal(k);
            completeMembers(k, ef_);
        }
    }

    public void completeMembers(StringList _moves, EffectCombo _effect) {
        if (_effect.getRankIncrementNbRound() > 0) {
            EndRoundMainElements endTurn_ = new EndRoundMainElements(null);
            endTurn_.setNumberIncrement(_effect.getRankIncrementNbRound());
            endTurn_.setIncrementNumberOfRounds(true);
            endTurn_.setEndRoundType(EndTurnType.ATTAQUE_COMBI);
            endTurn_.setElement(StringUtil.join(_moves, SEPARATOR_MOVES));
            endTurn_.setRelation(RelationType.EQUIPE);
            evtEndRound.add(endTurn_);
        }
        if (_effect.getEffectEndRound().isEmpty()) {
            return;
        }
        EndRoundMainElements endTurn_ = new EndRoundMainElements(_effect.getEffectEndRound().first());
        endTurn_.setNumberIncrement((short) _effect.getEffectEndRound().first()
                .getEndRoundRank());
        endTurn_.setIncrementNumberOfRounds(false);
        endTurn_.setEndRoundType(EndTurnType.ATTAQUE_COMBI);
        endTurn_.setElement(StringUtil.join(_moves, SEPARATOR_MOVES));
        endTurn_.setRelation(_effect.getEffectEndRound().first().getRelation());
        evtEndRound.add(endTurn_);
    }

    public void completeVariables() {
        removeDuplicatesCategoriesMoves();
        varParamsMove = new StringMap<StringList>();
        for (String e : variables) {
            StringList infos_ = StringUtil.splitStrings(e, SEP_BETWEEN_KEYS);
            String key_ = infos_.get(IndexConstants.SECOND_INDEX);
            int nbInfos_ = infos_.size();
            String element_ = StringUtil
                    .join(new StringList(infos_.leftMinusOne(nbInfos_).leftMinusOne(nbInfos_)), SEP_BETWEEN_KEYS);
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

    private void removeDuplicatesCategoriesMoves() {
        allCategories.removeDuplicates();
        categories.removeDuplicates();
    }

    public void sortEndRound() {
        evtEndRound.sortElts(new ComparatorEndRoundMainElements());
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
                boolean dig_ = MathExpUtil.isDigit(cur_);
                int j_ = possibleIncr(i_, cur_);
                int delta_ = delta(cur_);
                j_ = incr(_litt,j_);
                cur_ = _litt.charAt(j_);
//                while (MathExpUtil.isWordChar(cur_)) {
//                    j_++;
//                    cur_ = _litt.charAt(j_);
//                }
                String word_ = _litt.substring(i_+delta_, j_);
                formulaWord(_language, litt_, list_, dig_, word_);
                if (cur_ == '}') {
                    list_.sort();
                    str_.append(StringUtil.join(list_, getSepartorSetChar()));
                    list_.clear();
                    br_ = false;
                }
                i_ = j_;
            } else if (cur_ == '{') {
                str_.append(cur_);
                i_++;
                if (_litt.charAt(i_) != '}') {
                    br_ = true;
                }
            } else if (MathExpUtil.isWordChar(cur_)) {
                boolean dig_ = MathExpUtil.isDigit(cur_);
//                int j_ = i_;
//                while (MathExpUtil.isWordChar(cur_)) {
//                    j_++;
//                    if (j_ >= _litt.length()) {
//                        break;
//                    }
//                    cur_ = _litt.charAt(j_);
//                }
                int j_ = incrAfterWord(_litt,i_);
                String word_ = _litt.substring(i_, j_);
                char used_ = used(_litt, j_);
                trWordPart(_language, litt_, str_, used_, dig_, word_);
                i_ = j_;
            } else {
                str_.append(cur_);
                i_++;
            }
        }
        return str_.toString();
    }

    private int delta(char _cur) {
        int delta_ = 0;
        if (!MathExpUtil.isWordChar(_cur)) {
//                    cur_ = _litt.charAt(j_);
            delta_++;
        }
        return delta_;
    }

    private int possibleIncr(int _i, char _cur) {
        int j_ = _i;
        if (!MathExpUtil.isWordChar(_cur)) {
            j_++;
        }
        return j_;
    }

    private int incr(String _litt, int _j) {
        int j_ = _j;
        char cur_ = _litt.charAt(j_);
        while (MathExpUtil.isWordChar(cur_)) {
            j_++;
            cur_ = _litt.charAt(j_);
        }
        return j_;
    }
    private char used(String _litt, int _j) {
        char used_;
        if (_j < _litt.length()) {
            used_ = _litt.charAt(_j);
        } else {
            used_ = _litt.charAt(_litt.length()-1);
        }
        return used_;
    }

    private int incrAfterWord(String _litt, int _i) {
        char cur_ = _litt.charAt(_i);
        int j_ = _i;
        while (MathExpUtil.isWordChar(cur_)) {
            j_++;
            if (j_ >= _litt.length()) {
                break;
            }
            cur_ = _litt.charAt(j_);
        }
        return j_;
    }

    private void trWordPart(String _language, StringMap<String> _litt, StringBuilder _str, char _cur, boolean _dig, String _word) {
        if (_dig) {
            _str.append(_word);
        } else {
            trWord(_language, _litt, _str, _cur, _word);
        }
    }

    private void trWord(String _language, StringMap<String> _litt, StringBuilder _str, char _cur, String _word) {
        if (_cur == '(' || StringUtil.quickEq(getTrueString(), _word)|| StringUtil.quickEq(getFalseString(), _word)) {
            _str.append(StringUtil.nullToEmpty(translatedFctMath.getVal(_language).getVal(_word)));
        } else if (!_word.startsWith(VAR_PREFIX)) {
            _str.append(translateSafe(_word, _language));
        } else {
            String format_ = formatVar(_language, _litt, _word);
            _str.append(format_);
        }
    }

    private void formulaWord(String _language, StringMap<String> _litt, StringList _list, boolean _dig, String _word) {
        if (_dig) {
            _list.add(_word);
        } else if (!_word.startsWith(VAR_PREFIX)) {
            _list.add(translateSafe(_word, _language));
        } else {
            String format_ = formatVar(_language, _litt, _word);
            _list.add(format_);
        }
    }

    private String formatVar(String _language, StringMap<String> _litt, String _word) {
        String tok_ = _word.substring(DataBase.VAR_PREFIX.length());
        StringList elts_ = StringUtil.splitStrings(tok_, DataBase.SEP_BETWEEN_KEYS);
        String line_ = StringUtil.nullToEmpty(_litt.getVal(elts_.first()));
        StringList infos_ = StringUtil.splitStrings(line_, DataBase.TAB);
        StringList objDisplay_ = getVars(_word, _language);
        if (infos_.size() < 2) {
            return StringUtil.join(objDisplay_,SEPARATOR_MOVES);
        }
        String pattern_ = infos_.get(1);

        return StringUtil.simpleStringsFormat(pattern_,
                objDisplay_);
    }

    private static StringList getVariableWords(String _str) {
        StringList list_ = MathExpUtil.getWordsSeparators(_str);
        StringList newList_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        for (String t : list_) {
            if (i_ % 2 == 0) {
                i_++;
                continue;
            }
            if (isVariable(t)) {
                newList_.add(t);
            }
            i_++;
        }
        return newList_;
    }

    static boolean isVariable(String _string) {
        if (!_string.startsWith(VAR_PREFIX)) {
            return false;
        }
        return _string.length() > VAR_PREFIX.length();
    }

    public NatStringTreeMap< String> getDescriptions(String _litt,
            String _language) {
        StringMap<String> litt_ = litterals.getVal(_language);

        StringList tokens_ = MathExpUtil.getWordsSeparatorsPrefix(_litt,
                VAR_PREFIX);
        NatStringTreeMap< String> desc_ = new NatStringTreeMap< String>();
        int len_ = tokens_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (i % 2 == 0) {
                continue;
            }
            String tok_ = tokens_.get(i).substring(VAR_PREFIX.length());
            StringList elts_ = StringUtil.splitStrings(tok_, SEP_BETWEEN_KEYS);
            String line_ = StringUtil.nullToEmpty(litt_.getVal(elts_.first()));
            StringList infos_ = StringUtil.splitStrings(line_, TAB);
            if (infos_.size() >= 3) {
                String key_ = infos_.get(1);
                StringList objDisplay_ = getVars(tokens_.get(i), _language);

                String formatKey_ = StringUtil.simpleStringsFormat(key_,
                        objDisplay_);
                String pattern_ = infos_.get(2);

                String format_ = StringUtil.simpleStringsFormat(pattern_,
                        objDisplay_);
                desc_.put(formatKey_, format_);
            }
        }
        return desc_;
    }

    private StringList getVars(String _token, String _language) {
        StringMap<String> litt_ = litterals.getVal(_language);
        String tok_ = _token.substring(VAR_PREFIX.length());
        StringList elts_ = StringUtil.splitStrings(tok_, SEP_BETWEEN_KEYS);
        String line_ = StringUtil.nullToEmpty(litt_.getVal(elts_.first()));
        StringList infos_ = StringUtil.splitStrings(line_, TAB);
        String type_ = infos_.get(0);
        StringList types_ = StringUtil.splitChars(type_, getSepartorSetChar());
        StringList objDisplay_ = new StringList();
        int len_ = elts_.size();
        for (int j = IndexConstants.SECOND_INDEX; j < len_; j++) {
            if (StringUtil.quickEq(types_.get(j - 1), MOVE_FORMULA)) {
                objDisplay_.add(translatedMoves.getVal(_language).getVal(
                        elts_.get(j)));
            }
            if (StringUtil.quickEq(types_.get(j - 1), CAT_FORMULA)) {
                objDisplay_.add(translatedCategories.getVal(_language).getVal(
                        elts_.get(j)));
            }
            if (StringUtil.quickEq(types_.get(j - 1), STATIS_FORMULA)) {
                objDisplay_.add(translatedStatistics.getVal(_language).getVal(
                        Statistic.getStatisticByName(elts_.get(j))));
            }
            if (StringUtil.quickEq(types_.get(j - 1), STATUS_FORMULA)) {
                objDisplay_.add(translatedStatus.getVal(_language).getVal(
                        elts_.get(j)));
            }
            if (StringUtil.quickEq(types_.get(j - 1), TYPE_FORMULA)) {
                objDisplay_.add(translatedTypes.getVal(_language).getVal(
                        elts_.get(j)));
            }
        }
        return objDisplay_;
    }

    public void checkTranslations(String _string) {
        if (!isCheckTranslation()) {
            return;
        }
        String emptySet_ = StringUtil.concat(DataBase.EMPTY_STRING,
                Character.toString(StringUtil.LEFT_BRACE),
                Character.toString(StringUtil.RIGHT_BRACE));
        StringList sets_ = StringUtil.getTokensSets(StringUtil.removeStrings(
                _string, emptySet_));
        for (String s : sets_) {
            if (!s.startsWith(StringUtil.concat(DataBase.EMPTY_STRING,
                    Character.toString(StringUtil.LEFT_BRACE)))) {
                continue;
            }
            checkTranslationsInsideSet(s);
        }
    }

    private void checkTranslationsInsideSet(String _s) {
        String insideSet_ = _s.substring(IndexConstants.SECOND_INDEX,
                _s.length() - 1);
        StringList words_ = MathExpUtil.getWordsSeparators(insideSet_);
        for (String w : words_) {
            if (!MathExpUtil.isWord(w)) {
                if (!w.isEmpty() && !StringUtil.quickEq(w,
                        Character.toString(getSepartorSetChar()))) {
                            setError(true);
                }
                continue;
            }
            if (!isTranslatable(w)) {
                setError(true);
            }
        }
    }

    private boolean isTranslatable(String _key) {
        for (String l : languages) {
            if (translateSafe(_key, l).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String translateSafe(String _key, String _language) {
        return StringUtil.nullToEmpty(translate(_key, _language));
    }
    private String translate(String _key, String _language) {
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
        return translateEnum(_key, _language);
    }

    private String translateEnum(String _key, String _language) {
        for (EnvironmentType s : EnvironmentType.all()) {
            if (StringUtil.quickEq(_key, s.getEnvName()) && translatedEnvironment.getVal(_language).contains(s)) {
                return translatedEnvironment.getVal(_language).getVal(s);
            }
        }
        for (Statistic s : Statistic.all()) {
            if (StringUtil.quickEq(_key, s.getStatName()) && translatedStatistics.getVal(_language).contains(s)) {
                return translatedStatistics.getVal(_language).getVal(s);
            }
        }
        for (Gender g : Gender.all()) {
            if (StringUtil.quickEq(_key, g.getGenderName()) && translatedGenders.getVal(_language).contains(g)) {
                return translatedGenders.getVal(_language).getVal(g);
            }
        }
        return EMPTY_STRING;
    }

    public short ppCopiedMove(String _move) {
        MoveData fAtt_ = getMove(_move);
        int nbEffets_ = fAtt_.nbEffets();
        short pp_ = 0;
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffets_; i++) {
            Effect effet_ = fAtt_.getEffet(i);
            if (effet_ instanceof EffectCopyMove && ((EffectCopyMove) effet_).getCopyingMoveForUser() > 0) {
                pp_ = ((EffectCopyMove) effet_).getCopyingMoveForUser();
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
        for (int i = IndexConstants.FIRST_INDEX; i < nbEffets_; i++) {
            Effect effet_ = fAtt_.getEffet(i);
            if (effet_ instanceof EffectBatonPass) {
                relais_ = true;
                break;
            }
        }
        return relais_;
    }

    public ItemForBattle usedObjectUsedForExp(String _obj) {
        if (!items.contains(_obj)) {
            return null;
        }
        Item obj_ = items.getVal(_obj);
        if (!(obj_ instanceof ItemForBattle)) {
            return null;
        }
        ItemForBattle f_ = (ItemForBattle) obj_;
        if (f_.isUsedForExp()) {
            return f_;
        }
        return null;
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

    public StringMap<PokemonData> getPokedex() {
        return pokedex;
    }

    public StringMap<MoveData> getMoves() {
        return moves;
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

    StringList getVariables() {
        return variables;
    }

    public ShortMap< String> getTm() {
        return tm;
    }

    public Shorts getTmByMove(String _move) {
        Shorts tms_ = new Shorts();
        for (EntryCust<Short, String> e : tm.entryList()) {
            if (StringUtil.quickEq(e.getValue(), _move)) {
                tms_.add(e.getKey());
            }
        }
        return tms_;
    }

    public ShortMap< LgInt> getTmPrice() {
        return tmPrice;
    }

    public ShortMap< String> getHm() {
        return hm;
    }

    public Shorts getHmByMove(String _move) {
        Shorts tms_ = new Shorts();
        for (EntryCust<Short, String> e : hm.entryList()) {
            if (StringUtil.quickEq(e.getValue(), _move)) {
                tms_.add(e.getKey());
            }
        }
        return tms_;
    }

    public IdMap<ExpType, String> getExpGrowth() {
        return expGrowth;
    }

    public IdMap<DifficultyWinPointsFight, String> getRates() {
        return rates;
    }

    public IdMap<DifficultyModelLaw, LawNumber> getLawsDamageRate() {
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

    public void setMaxHeightPk(int _maxHeightPk) {
        maxHeightPk = _maxHeightPk;
    }

    public void setMaxWidthPk(int _maxWidthPk) {
        maxWidthPk = _maxWidthPk;
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

    public ImageHeroKeys getFrontHeros() {
        return frontHeros;
    }

    public ImageHeroKeys getBackHeros() {
        return backHeros;
    }

    public ImageHeroKeys getOverWorldHeros() {
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
        for (EntryCust<String, ScreenCoordssInt> e : imagesTiles
                .entryList()) {
            if (!StringUtil.quickEq(e.getKey(),_name)) {
                continue;
            }
            return e.getValue().getVal(_coords);
        }
        return new int[0][0];
    }

    public int[][] getMiniMap(String _name) {
        return getValueCaseInsensitive(miniMap, _name);

    }

    private static int[][] getValueCaseInsensitive(StringMap<int[][]> _map,
                                                   String _name) {
        for (EntryCust<String, int[][]> e : _map.entryList()) {
            if (StringUtil.quickEq(e.getKey(),_name)) {
                return e.getValue();
            }
        }
        return new int[0][0];
    }

    public StringMap<int[][]> getMiniMap() {
        return miniMap;
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
        Rate rate_ = constNum.getVal(_key);
        if (rate_ == null) {
            return Rate.zero();
        }
        return rate_;
    }

    public void addConstNumTest(String _key, Rate _value) {
        constNum.put(_key, _value);
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
    public Rate getDefBaseMove() {
        return constNum(DataBase.DEF_BASE_MOVE);
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

        return defaultEggGroup;
    }

    public StringList getVarParamsMove(String _var) {
        StringList elements_ = new StringList();
        if (varParamsMove.contains(_var)) {
            elements_.addAllElts(varParamsMove.getVal(_var));
        }
        return elements_;
    }

    StringMap<StringList> getVarParamsMove() {
        return varParamsMove;
    }

    public TypesDuos getTableTypes() {
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

    StringList getFilesWithSameNameDifferentCase() {
        return filesWithSameNameDifferentCase;
    }

    public StringMap<StringMap<String>> getTranslatedCategories() {
        return translatedCategories;
    }

    public IdMap<Gender, String> getTranslatedGendersCurLanguage(String _lg) {
        return translatedGenders.getVal(_lg);
    }

    public StringMap<IdMap<Gender, String>> getTranslatedGenders() {
        return translatedGenders;
    }

    public StringMap<String> getTranslatedPokemonCurLanguage(String _lg) {
        return translatedPokemon.getVal(_lg);
    }

    public StringMap<String> getTranslatedAbilitiesCurLanguage(String _lg) {
        return translatedAbilities.getVal(_lg);
    }

    public IdMap<SelectedBoolean, String> getTranslatedBooleansCurLanguage(String _lg) {
        return translatedBooleans.getVal(_lg);
    }

    public StringMap<IdMap<SelectedBoolean, String>> getTranslatedBooleans() {
        return translatedBooleans;
    }

    public StringMap<IdMap<DifficultyModelLaw, String>> getTranslatedDiffModelLaw() {
        return translatedDiffModelLaw;
    }

    public StringMap<IdMap<DifficultyWinPointsFight, String>> getTranslatedDiffWinPts() {
        return translatedDiffWinPts;
    }

    public StringMap<IdMap<EnvironmentType, String>> getTranslatedEnvironment() {
        return translatedEnvironment;
    }

    public StringMap<IdMap<Statistic, String>> getTranslatedStatistics() {
        return translatedStatistics;
    }

    public StringMap<IdMap<TargetChoice, String>> getTranslatedTargets() {
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
        return StringUtil.nullToEmpty(translatedPokemon.getVal(language).getVal(
                _pokemon));
    }

    public String translateMove(String _move) {
        return StringUtil.nullToEmpty(translatedMoves.getVal(language).getVal(_move));
    }

    public String translateItem(String _item) {
        return StringUtil.nullToEmpty(translatedItems.getVal(language).getVal(_item));
    }

    public String translateAbility(String _ability) {
        return StringUtil.nullToEmpty(translatedAbilities.getVal(language).getVal(
                _ability));
    }

    public String translateStatus(String _status) {
        return StringUtil.nullToEmpty(translatedStatus.getVal(language).getVal(_status));
    }

    public String translateType(String _type) {
        return StringUtil.nullToEmpty(translatedTypes.getVal(language).getVal(_type));
    }

    public String translateStatistics(Statistic _statistic) {
        return StringUtil.nullToEmpty(translatedStatistics.getVal(language).getVal(
                _statistic));
    }

    public String translatedTargets(TargetChoice _target) {
        return StringUtil.nullToEmpty(translatedTargets.getVal(language)
                .getVal(_target));
    }

    public String translateGenders(Gender _gender) {
        return StringUtil.nullToEmpty(translatedGenders.getVal(language)
                .getVal(_gender));
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

    private boolean isCheckTranslation() {
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

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }

    public StringMap<String> getDisplayLanguages() {
        return displayLanguages;
    }

    public void setDisplayLanguages(StringMap<String> _displayLanguages) {
        displayLanguages = _displayLanguages;
    }

    public String getLanguage() {
        return language;
    }
    public StringMap<StringMap<String>> getLitterals() {
        return litterals;
    }

    public StringList getLegPks() {
        return legPks;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

}
