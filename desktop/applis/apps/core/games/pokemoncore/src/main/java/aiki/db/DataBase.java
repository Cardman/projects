package aiki.db;

import aiki.comparators.ComparatorEndRoundMainElements;
import aiki.facade.SexListInt;
import aiki.fight.*;
import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.fight.status.*;
import aiki.fight.util.*;
import aiki.game.fight.CheckNumericStringsFight;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.enums.Sex;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.buildings.Building;
import aiki.map.buildings.Gym;
import aiki.map.characters.*;
import aiki.map.enums.Direction;
import aiki.map.levels.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.*;
import aiki.map.pokemon.*;
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
import code.maths.montecarlo.MonteCarloString;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;
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

    public static final String PREFIX_KEY = "0";
    public static final String KEY_NIVEAU = "1";
    public static final String KEY_LEVEL_LOOSER = "2";
    public static final String KEY_LEVEL_WINNER = "3";
    public static final String KEY_FIGHTER_NIVEAU = "4";
    public static final String KEY_CIBLE_NIVEAU = "5";
    public static final String KEY_LANCEUR_NIVEAU = "6";
    public static final String KEY_PK_SAUVAGE_NIVEAU = "7";
    public static final String KEY_PK_UT_NIVEAU = "8";
    public static final String KEY_BOOST = "9";
    public static final String KEY_POWER = "10";
    public static final String KEY_ATTACK = "11";
    public static final String KEY_DEFENSE = "12";
    public static final String KEY_BASE_CAPT_PK = "13";
    public static final String KEY_RATE_BALL_STATUS = "14";
    public static final String KEY_FOE_PK_MAX_HP = "15";
    public static final String KEY_FOE_PK_REMOTE_HP = "16";
    public static final String KEY_CIBLE_NB_UTILISATION = "17";
    public static final String KEY_LANCEUR_NB_UTILISATION = "18";
    public static final String KEY_FIGHTER_NB_UTILISATION = "19";
    public static final String KEY_EQUIPE_NB_UTILISATION = "20";
    public static final String KEY_EQUIPE_ADV_NB_UTILISATION = "21";
    public static final String KEY_NB_UTILI_ATT_EQ_TOUR = "22";
    public static final String KEY_LANCEUR_PV_RESTANTS = "23";
    public static final String KEY_CIBLE_PV_RESTANTS = "24";
    public static final String KEY_LANCEUR_PV_MAX = "25";
    public static final String KEY_CIBLE_PV_MAX = "26";
    public static final String KEY_LANCEUR_STATIS = "27";
    public static final String KEY_CIBLE_STATIS = "28";
    public static final String KEY_LANCEUR_BOOST = "29";
    public static final String KEY_CIBLE_BOOST = "30";
    public static final String KEY_SOMME_BOOST_POS_CIBLE ="31";
    public static final String KEY_CIBLE_ATTAQUES ="32";
    public static final String KEY_CIBLE_ATTAQUE_CHOISIE ="33";
    public static final String KEY_CIBLE_ATTAQUES_TYPES ="34";
    public static final String KEY_CIBLE_CLONE ="35";
    public static final String KEY_CIBLE_DEGATS_RECUS ="36";
    public static final String KEY_CIBLE_DEGATS_RECUS_TOTAL ="37";
    public static final String KEY_CIBLE_DEGATS_RECUS_TOUR ="38";
    public static final String KEY_CIBLE_DEGATS_RECUS_TOTAL_TOUR ="39";
    public static final String KEY_CIBLE_DISPARAIT ="40";
    public static final String KEY_CIBLE_DER_JOUE ="41";
    public static final String KEY_CIBLE_JOUE ="42";
    public static final String KEY_CIBLE_MASSE ="43";
    public static final String KEY_CIBLE_TAILLE ="44";
    public static final String KEY_CIBLE_CAPACITE ="45";
    public static final String KEY_CIBLE_OBJET ="46";
    public static final String KEY_CIBLE_STATUTS ="47";
    public static final String KEY_CIBLE_TYPES ="48";
    public static final String KEY_CIBLE_NOM ="49";
    public static final String KEY_CIBLE_PP ="50";
    public static final String KEY_NB_KO_EQUIPE_CIBLE ="51";
    public static final String KEY_NB_KO_EQUIPE_ADV_CIBLE ="52";
    public static final String KEY_COEFF_EFF_BASE_TYPES_CIBLE ="53";
    public static final String KEY_NB_TOUR ="54";
    public static final String KEY_SOMME_BOOST_POS_LANCEUR ="55";
    public static final String KEY_NB_TOUR_GLOBAL ="56";
    public static final String KEY_LANCEUR_ATTAQUES ="57";
    public static final String KEY_LANCEUR_ATTAQUE_CHOISIE ="58";
    public static final String KEY_LANCEUR_ATTAQUES_TYPES ="59";
    public static final String KEY_LANCEUR_CLONE ="60";
    public static final String KEY_LANCEUR_DEGATS_RECUS ="61";
    public static final String KEY_LANCEUR_DEGATS_RECUS_TOTAL ="62";
    public static final String KEY_LANCEUR_DEGATS_RECUS_TOUR ="63";
    public static final String KEY_LANCEUR_DEGATS_RECUS_TOTAL_TOUR ="64";
    public static final String KEY_LANCEUR_DISPARAIT ="65";
    public static final String KEY_LANCEUR_DER_JOUE ="66";
    public static final String KEY_LANCEUR_JOUE ="67";
    public static final String KEY_LANCEUR_MASSE ="68";
    public static final String KEY_LANCEUR_TAILLE ="69";
    public static final String KEY_LANCEUR_CAPACITE ="70";
    public static final String KEY_LANCEUR_OBJET ="71";
    public static final String KEY_LANCEUR_STATUTS ="72";
    public static final String KEY_LANCEUR_TYPES ="73";
    public static final String KEY_LANCEUR_NOM ="74";
    public static final String KEY_COEFF_EFF_BASE_TYPES_LANCEUR ="75";
    public static final String KEY_LANCEUR_PP ="76";
    public static final String KEY_CIBLE_GENRE = "77";
    public static final String KEY_LANCEUR_GENRE = "78";
    public static final String KEY_COEFF_EFF_BASE_TYPES_FIGHTER ="79";
    public static final String KEY_FIGHTER_BONHEUR ="80";
    public static final String KEY_FIGHTER_PP ="81";
    public static final String KEY_FIGHTER_GENRE = "82";
    public static final String KEY_FIGHTER_PV_RESTANTS = "83";
    public static final String KEY_FIGHTER_PV_MAX = "84";
    public static final String KEY_FIGHTER_STATIS = "85";
    public static final String KEY_FIGHTER_BOOST = "86";
    public static final String KEY_SOMME_BOOST_POS_FIGHTER ="87";
    public static final String KEY_FIGHTER_ATTAQUES ="88";
    public static final String KEY_FIGHTER_ATTAQUE_CHOISIE ="89";
    public static final String KEY_FIGHTER_ATTAQUES_TYPES ="90";
    public static final String KEY_FIGHTER_CLONE ="91";
    public static final String KEY_FIGHTER_DEGATS_RECUS ="92";
    public static final String KEY_FIGHTER_DEGATS_RECUS_TOTAL ="93";
    public static final String KEY_FIGHTER_DEGATS_RECUS_TOUR ="94";
    public static final String KEY_FIGHTER_DEGATS_RECUS_TOTAL_TOUR ="95";
    public static final String KEY_FIGHTER_DISPARAIT ="96";
    public static final String KEY_FIGHTER_DER_JOUE ="97";
    public static final String KEY_FIGHTER_JOUE ="98";
    public static final String KEY_FIGHTER_MASSE ="99";
    public static final String KEY_FIGHTER_TAILLE ="100";
    public static final String KEY_FIGHTER_CAPACITE ="101";
    public static final String KEY_FIGHTER_OBJET ="102";
    public static final String KEY_FIGHTER_STATUTS ="103";
    public static final String KEY_FIGHTER_TYPES ="104";
    public static final String KEY_FIGHTER_NOM ="105";
    public static final String KEY_ATTAQUE_CATEGORIE ="106";
    public static final String KEY_ATTAQUE_TYPES ="107";
    public static final String KEY_ATTAQUE_NOM ="108";
    public static final String KEY_PUISSANCE_BASE ="109";
    public static final String KEY_COEFF_EFF ="110";
    public static final String KEY_NB_UTILISATION_CONSECUTIF ="111";
    public static final String KEY_CLIMATS ="112";
    public static final String KEY_LIEU_COMBAT ="113";
    public static final String KEY_NB_FLEES ="114";
    public static final String KEY_CIBLE_BONHEUR ="115";
    public static final String KEY_LANCEUR_BONHEUR ="116";
    public static final String KEY_TEMPS_TOUR ="117";
    public static final String KEY_RATE_EFF_MOVE_AGAINST_TARGET ="118";
    public static final String KEY_NB_COMBATTANTS_TERRAIN ="119";
    public static final String KEY_NB_KO_EQUIPE_LANCEUR ="120";
    public static final String KEY_NB_KO_EQUIPE_ADV_LANCEUR ="121";
    public static final String KEY_NB_KO_EQUIPE_FIGHTER ="122";
    public static final String KEY_NB_KO_EQUIPE_ADV_FIGHTER ="123";
    public static final String KEY_CIBLE_POSSEDE_STATUT_RELATION ="124";
    public static final String KEY_IMMU_TYPE_ATT_COMBATTANT_ENTRANT ="125";
    public static final String KEY_CIBLE_EFFET ="126";
    public static final String KEY_PAS_PP_ATTAQUE_CIBLE ="127";
    public static final String KEY_PAS_UTILIS_ATTAQUE_CIBLE ="128";
    public static final String KEY_IMMU_TYPE_ATT_CIBLE ="129";
    public static final String KEY_AUCUN_BOOST_POSSIBLE ="130";
    public static final String KEY_LANCEUR_EFFET ="131";
    public static final String KEY_TYPES_ATTAQUES_RES_VIDE ="132";
    public static final String KEY_PAS_ATTAQUE_INVOC ="133";
    public static final String KEY_PAS_ATTAQUES_COPIABLES ="134";
    public static final String KEY_PAS_PARTENAIRE ="135";
    public static final String KEY_PAS_PARTENAIRE_ARRIERE ="136";
    public static final String KEY_PAS_PARTENAIRE_TERRAIN ="137";
    public static final String KEY_PAS_TOUR_TERRAIN ="138";
    public static final String KEY_EXISTE_GENRE_ASSEXUE ="139";
    public static final String KEY_GENRES_EGAUX ="140";
    public static final String KEY_DEJA_CAPTURE ="141";
    public static final String KEY_MASSE_MOYENNE_PK ="142";
    public static final String KEY_PK_UT_GENRE ="143";
    public static final String KEY_PK_UT_MASSE ="144";
    public static final String KEY_PK_UT_VITESSE ="145";
    public static final String KEY_PK_UT_TYPES_BASE ="146";
    public static final String KEY_PK_UT_PIERRES_EVOS ="147";
    public static final String KEY_PK_SAUVAGE_GENRE ="148";
    public static final String KEY_PK_SAUVAGE_MASSE ="149";
    public static final String KEY_PK_SAUVAGE_VITESSE ="150";
    public static final String KEY_PK_SAUVAGE_TYPES_BASE ="151";
    public static final String KEY_PK_SAUVAGE_PIERRES_EVOS ="152";
    public static final String KEY_COMBATTANT_ENTRANT_CLONE ="153";
    public static final String KEY_COMBATTANT_ENTRANT_TYPES ="154";
    public static final String KEY_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT ="155";
    public static final String KEY_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION ="156";

    public static final String EMPTY_STRING = "";
    public static final String MIN_BOOST = "6";
    public static final String MAX_BOOST = "2";
    public static final String VALEUR_DEF_STATIS = "4";
    public static final String NIVEAU_PK_ECLOSION = "8";
    public static final short INVALID_LEVEL = -1;
    public static final String PAS_NECES_INCREMENT_BONHEUR = "9";
    public static final String PP_MAX = "16";
    public static final String DEF_PKEQ = "18";
    public static final String MAX_EV = "10";
    public static final String MAX_IV = "19";
    public static final String NIVEAU_PK_MAX = "15";
    public static final String GAIN_BONHEUR_NIV = "3";
    public static final String EVO_BONHEUR = "1";
    public static final String MAX_BONHEUR = "12";
    public static final String ARGENT = "0";
    public static final String STRONG_MOVE = "17";
    public static final String SEPARATOR_MOVES = ";";
    public static final String SEPARATOR_RAND = ";";
    public static final String SEPARATOR_RGB = ";";
    public static final String SEPARATOR_RAND_EVENTS = " ";
    public static final String DEF_MOVE = "_3";
    public static final String RATE_CATCHING = "_9";
    public static final String RATE_FLEEING = "_6";
    public static final String RATE_BOOST = "_7";
    public static final String DEF_CAT = "_1";
    public static final String RATE_BOOST_CRITICAL_HIT = "_5";
    public static final String DAMAGE_FORMULA = "_8";
    public static final String DEFAULT_EGG_GROUP = "_4";
    public static final String MAX_STEPS_SAME_EVO_BASE = "11";
    public static final String MAX_STEPS = "5";
    public static final String MIN_HP = "14";
    public static final String DEF_MAX_ATT = "7";
    public static final String BONUS_BOOST = "13";
    public static final String DEF_BASE_MOVE = "20";
    public static final String BALL_DEF = "_2";

    public static final String SEP_BETWEEN_KEYS = "__";

    public static final String DEF_STAT_HP= "0";
    public static final String DEF_STAT_ATTACK= "1";
    public static final String DEF_STAT_DEFENSE= "2";
    public static final String DEF_STAT_SPECIAL_ATTACK= "3";
    public static final String DEF_STAT_SPECIAL_DEFENSE= "4";
    public static final String DEF_STAT_SPEED= "5";
    public static final String DEF_STAT_ACCURACY= "6";
    public static final String DEF_STAT_EVASINESS= "7";
    public static final String DEF_STAT_CRITICAL_HIT= "8";
    public static final String DEF_STAT_PV_RESTANTS= "9";

    public static final String DEF_GENDER_FEMALE= "0";
    public static final String DEF_GENDER_MALE= "1";
    public static final String DEF_GENDER_NO_GENDER= "2";

    public static final String DEF_GENDER_REP_FEMALE= "0";
    public static final String DEF_GENDER_REP_MALE= "1";
    public static final String DEF_GENDER_REP_MIXED= "2";
    public static final String DEF_GENDER_REP_NO_GENDER= "3";
    public static final String DEF_GENDER_REP_LEGENDARY= "4";

    public static final String DEF_EXCHANGE_TYPE_GIVE_TO_TARGET = "0";
    public static final String DEF_EXCHANGE_TYPE_GIVE_TO_THROWER = "1";
    public static final String DEF_EXCHANGE_TYPE_EXCHANGE = "2";
    public static final String DEF_EXCHANGE_TYPE_GIVE_CONST = "3";
    public static final String DEF_EXCHANGE_TYPE_NOTHING = "4";

    public static final String DEF_MOVE_ITEM_TYPE_GIVE_OBJECT_TARGET = "0";
    public static final String DEF_MOVE_ITEM_TYPE_EXCHANGE_OBJECTS = "1";
    public static final String DEF_MOVE_ITEM_TYPE_TAKE_OBJET = "2";
    public static final String DEF_MOVE_ITEM_TYPE_REMOVE_TARGET_OBJECT = "3";
    public static final String DEF_MOVE_ITEM_TYPE_DELETE_DEF_TARGET_BERRY = "4";
    public static final String DEF_MOVE_ITEM_TYPE_USE_OBJECT_AS_POSSIBLE = "5";
    public static final String DEF_MOVE_ITEM_TYPE_REUSE_LAST_OBJECT = "6";

    public static final String DEF_POINT_VIEW_CHANGEMENT_TYPE_THIEF_BONUSES = "0";
    public static final String DEF_POINT_VIEW_CHANGEMENT_TYPE_MIRROR_AGAINST_THROWER = "1";
    public static final String DEF_POINT_VIEW_CHANGEMENT_TYPE_ATTRACT_DAMAGES_MOVES = "2";
    public static final String DEF_POINT_VIEW_CHANGEMENT_TYPE_NOTHING = "3";

    public static final String DEF_CONST_VALUES_TYPE_LANCEUR_ATTAQUES_TYPES = "0";
    public static final String DEF_CONST_VALUES_TYPE_TYPES_ATTAQUES_RES = "1";
    public static final String DEF_CONST_VALUES_TYPE_NOTHING = "2";

    public static final String DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORCE = "0";
    public static final String DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORBIDDEN = "1";
    public static final String DEF_MOVE_CHOICE_RESTRICTION_TYPE_LANCEUR_ATTAQUES = "2";
    public static final String DEF_MOVE_CHOICE_RESTRICTION_TYPE_DER = "3";
    public static final String DEF_MOVE_CHOICE_RESTRICTION_TYPE_CATEGORIE_AUTRE = "4";
    public static final String DEF_MOVE_CHOICE_RESTRICTION_TYPE_NOTHING = "5";

    public static final String DEF_SEL_BOOL_NO= "0";
    public static final String DEF_SEL_BOOL_YES= "1";
    public static final String DEF_SEL_BOOL_YES_AND_NO= "2";

    public static final String DEF_TARGET_ADJ_ADV="0";
    public static final String DEF_TARGET_ADJ_MULT="1";
    public static final String DEF_TARGET_ADJ_UNIQ="2";
    public static final String DEF_TARGET_ALLIE="3";
    public static final String DEF_TARGET_ALLIES="4";
    public static final String DEF_TARGET_ANY_FOE="5";
    public static final String DEF_TARGET_AUTRE_UNIQ="6";
    public static final String DEF_TARGET_GLOBALE="7";
    public static final String DEF_TARGET_LANCEUR="8";
    public static final String DEF_TARGET_PSEUDO_GLOBALE="9";
    public static final String DEF_TARGET_TOUS_ADV="10";
    public static final String DEF_TARGET_UNIQUE_IMPORTE="11";
    public static final String DEF_TARGET_NOTHING="12";

    public static final String DEF_SEX_GIRL= "0";
    public static final String DEF_SEX_BOY= "1";
    public static final String DEF_SEX_NO= "2";

    public static final String DEF_ENV_BUILDING="0";
    public static final String DEF_ENV_DESERT="1";
    public static final String DEF_ENV_WATER="2";
    public static final String DEF_ENV_ICE="3";
    public static final String DEF_ENV_GRASS="4";
    public static final String DEF_ENV_SNOW="5";
    public static final String DEF_ENV_ROCK="6";
    public static final String DEF_ENV_ROAD="7";
    public static final String DEF_ENV_NOTHING="8";

    public static final String DEF_EXP_E="0";
    public static final String DEF_EXP_F="1";
    public static final String DEF_EXP_L="2";
    public static final String DEF_EXP_M="3";
    public static final String DEF_EXP_P="4";
    public static final String DEF_EXP_R="5";

    public static final String DEF_DIR_UP="0";
    public static final String DEF_DIR_DOWN="1";
    public static final String DEF_DIR_LEFT="2";
    public static final String DEF_DIR_RIGHT="3";

    public static final String DEF_CONSTANT_MIN="0";
    public static final String DEF_CROISSANT="1";
    public static final String DEF_UNIFORME="2";
    public static final String DEF_DECROISSANT="3";
    public static final String DEF_CONSTANT_MAX="4";

    public static final String DEF_TRES_FACILE="0";
    public static final String DEF_FACILE="1";
    public static final String DEF_DIFFICILE="2";
    public static final String DEF_TRES_DIFFICILE="3";

    public static final int KIND_AB = 0;
    public static final int KIND_IT = 1;
    public static final int KIND_MV = 2;
    public static final int KIND_PK = 3;
    public static final int KIND_ST = 4;
    public static final int KIND_CA = 5;
    public static final int KIND_TY = 6;
    /**
     * The custom beans can be modified but they must have a common base package
     * Avoid to recompile classes in standard packages like java, javax, and
     * even projects core, gui ...
     */

    public static final int MAX_MULT_FIGHT = 4;

    public static final String SEPARATOR_FILES = "/";
    public static final int ONE_POSSIBLE_CHOICE = 1;

    public static final String MOVE_FORMULA = "0";
    public static final String CAT_FORMULA = "1";
    public static final String STATIS_FORMULA = "2";
    public static final String STATUS_FORMULA = "3";
    public static final String TYPE_FORMULA = "4";
    public static final String ITEM_FORMULA = "5";

    private static final int DEFAULT_POWER_INT = 80;

    private static final int DEFAULT_HEAL_RATE_NUM = 1;
    private static final int DEFAULT_HEAL_RATE_DEN = 2;

    private static final int DEFAULT_INFLICTED_RATE_NUM = 1;
    private static final int DEFAULT_INFLICTED_RATE_DEN = 8;

    private static final String TAB = "\t";

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

    private StringMap<ImageArrayBaseSixtyFour> miniPk = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ImageArrayBaseSixtyFour> maxiPkBack = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ImageArrayBaseSixtyFour> maxiPkFront = new StringMap<ImageArrayBaseSixtyFour>();

    private int maxWidthPk;

    private int maxHeightPk;

    private StringMap<ImageArrayBaseSixtyFour> miniItems = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ImageArrayBaseSixtyFour> trainers = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ImageArrayBaseSixtyFour> people = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ImageArrayBaseSixtyFour> typesImages = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<String> typesColors = new StringMap<String>();

    private ImageHeroKeys frontHeros = new ImageHeroKeys();

    private ImageHeroKeys backHeros = new ImageHeroKeys();

    private ImageHeroKeys overWorldHeros = new ImageHeroKeys();

    private StringMap<ImageArrayBaseSixtyFour> links = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ImageArrayBaseSixtyFour> images = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ScreenCoordssInt> imagesTiles = new StringMap<ScreenCoordssInt>();

    private StringMap<ImageArrayBaseSixtyFour> miniMap = new StringMap<ImageArrayBaseSixtyFour>();

    private ImageArrayBaseSixtyFour imageTmHm = ImageArrayBaseSixtyFour.instance();

    private ImageArrayBaseSixtyFour storage = ImageArrayBaseSixtyFour.instance();

    private DataMap map = new DataMap();

    private Combos combos = new Combos();

    private StringMap<Rate> constNum = new StringMap<Rate>();

    private final DataBaseConstants constNonNum = new DataBaseConstants();
//    private String prefixVar;

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

    private ImageArrayBaseSixtyFour endGameImage = ImageArrayBaseSixtyFour.instance();

//    private StringList filesWithSameNameDifferentCase;

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

    private StringMap<ImageArrayBaseSixtyFour> animStatis = new StringMap<ImageArrayBaseSixtyFour>();

    private StringMap<ImageArrayBaseSixtyFour> animStatus = new StringMap<ImageArrayBaseSixtyFour>();

    private ImageArrayBaseSixtyFour animAbsorb = ImageArrayBaseSixtyFour.instance();

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
        coords_.affectInside(_inside);
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
        coords_.affectInside(_inside);
        return getLevelImage(coords_);
    }

    public Points< int[][]> getBackLevelImage(short _pl, byte _level) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        return Level.getLevelBackgroundImage(this,coords_);
    }

    public Points< int[][]> getBackLevelImage(short _pl, byte _level,
                                          Point _inside) {
        Coords coords_ = new Coords();
        coords_.setNumberPlace(_pl);
        coords_.setLevel(new LevelPoint());
        coords_.getLevel().setLevelIndex(_level);
        coords_.affectInside(_inside);
        return Level.getLevelBackgroundImage(this,coords_);
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
    public static void updateBorders(Points< int[][]> _tiles, int _side) {
        if (_tiles.isEmpty()) {
            return;
        }
        Shorts x_ = new Shorts();
        Shorts y_ = new Shorts();
        for (Point p : _tiles.getKeys()) {
            x_.add(p.getx());
            y_.add(p.gety());
        }
        short minx_ = (short) x_.getMinimum(0);
        short miny_ = (short) y_.getMinimum(0);
        short maxx_ = (short) x_.getMaximum(0);
        short maxy_ = (short) y_.getMaximum(0);
        _tiles.addEntry(new Point((short)(minx_-1),(short)(miny_-1)),Level.whiteCell(_side));
        _tiles.addEntry(new Point((short)(minx_-1),(short)(maxy_+1)),Level.whiteCell(_side));
        _tiles.addEntry(new Point((short)(maxx_+1),(short)(maxy_+1)),Level.whiteCell(_side));
        _tiles.addEntry(new Point((short)(maxx_+1),(short)(miny_-1)),Level.whiteCell(_side));
        for (short i = minx_; i <= maxx_; i++) {
            _tiles.addEntry(new Point(i,(short)(miny_-1)),Level.whiteCell(_side));
            _tiles.addEntry(new Point(i,(short)(maxy_+1)),Level.whiteCell(_side));
        }
        for (short i = miny_; i <= maxy_; i++) {
            _tiles.addEntry(new Point((short)(minx_-1),i),Level.whiteCell(_side));
            _tiles.addEntry(new Point((short)(maxx_+1),i),Level.whiteCell(_side));
        }
    }

    public static int[][] stackImages(Points< int[][]> _tiles,
            Points< int[][]> _frontTiles, Point _pt) {
        int[][] img_ = _frontTiles.getVal(_pt);
        int[][] imgBack_ = _tiles.getVal(_pt);
        return ConverterBufferedImage.stackImages(imgBack_, img_);
    }

    public void addPerson(String _fileName, ImageArrayBaseSixtyFour _img) {
        people.put(_fileName, _img);
    }

    public void addImage(String _fileName, ImageArrayBaseSixtyFour _img) {
        images.put(_fileName, _img);
    }

    public void addLink(String _fileName, ImageArrayBaseSixtyFour _img) {
        links.put(_fileName, _img);
    }

    public void addTrainerImage(String _fileName, ImageArrayBaseSixtyFour _img) {
        trainers.put(_fileName, _img);
    }

    public void validate(AbstractAtomicIntegerCoreAdd _perCentLoading, AbstractAtomicBooleanCore _loading, SexListInt _sexListInt) {
        validateOne(_perCentLoading);
        validateTwo(_loading,_perCentLoading);
        validateThree(_loading,_perCentLoading);
        validateFour(_loading,_perCentLoading);
        validateFive(_loading,_perCentLoading,_sexListInt);
        validateSix(_loading,_perCentLoading);

    }
    public void validateOne(AbstractAtomicIntegerCoreAdd _perCentLoading) {
        for (LawNumber v : lawsDamageRate.values()) {
            if (v.getLaw().events().isEmpty()) {
                setError(true);
            }
        }
        completeMoveTutors();
        validateCore(_perCentLoading);
    }
    public void validateTwo(AbstractAtomicBooleanCore _loading, AbstractAtomicIntegerCoreAdd _perCentLoading) {
        if (!_loading.get()) {
            return;
        }
        _perCentLoading.set(60);
        setCheckTranslation(true);
        if (!getPokedex().isEmpty() && !getAbilities().isEmpty() && moves.getVal(defMove) != null) {
            CheckNumericStringsFight.validateNumericBooleanStrings(this);
        } else {
            setError(true);
        }
    }
    public void validateThree(AbstractAtomicBooleanCore _loading, AbstractAtomicIntegerCoreAdd _perCentLoading) {
        if (!_loading.get()) {
            return;
        }
        _perCentLoading.set(70);
        Rate power_ = getStrongMovePower();
        if (Rate.strLower(power_, new Rate(90))) {
            setError(true);
        }
        TypeStatistics strongMovesTypeStat_ = strongMoves(power_);
        for (EntryCust<TypeStatistic, BoolVal> e : strongMovesTypeStat_
                .entryList()) {
            if (e.getValue() == BoolVal.TRUE) {
                continue;
            }
            setError(true);
        }
    }
    public void validateFour(AbstractAtomicBooleanCore _loading, AbstractAtomicIntegerCoreAdd _perCentLoading) {
        if (!_loading.get()) {
            return;
        }
        map.validate(this);
        _perCentLoading.set(85);
    }
    public void validateFive(AbstractAtomicBooleanCore _loading, AbstractAtomicIntegerCoreAdd _perCentLoading, SexListInt _sexListInt) {
        if (!_loading.get()) {
            return;
        }
        validateImages(_sexListInt);
        _perCentLoading.set(90);
    }
    public void validateSix(AbstractAtomicBooleanCore _loading, AbstractAtomicIntegerCoreAdd _perCentLoading) {
        if (!_loading.get()) {
            return;
        }
        validateTranslations();
        _perCentLoading.set(95);
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

    public void validateCore(AbstractAtomicIntegerCoreAdd _perCentLoading) {
        initTypesByTable();
        _perCentLoading.set(55);
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
        StringList attaques_ = new StringList();
        attaques_.addAllElts(getVarParamsMove(equipeNbUtilisation()));
        attaques_.addAllElts(getVarParamsMove(equipeAdvNbUtilisation()));
        attaques_.addAllElts(getVarParamsMove(nbUtiliAttEqTour()));
        attaques_.addAllElts(getVarParamsMove(cibleNbUtilisation()));
        attaques_.addAllElts(getVarParamsMove(lanceurNbUtilisation()));
        DataInfoChecker.checkStringListContains(getMoves().getKeys(),attaques_,this);
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
    public void patchPartialEvos() {
        for (EntryCust<String, PokemonData> p: getPokedex().entryList()) {
            for (EntryCust<String,Evolution> e: p.getValue().getEvolutions().entryList()) {
                if (e.getValue() instanceof EvolutionStoneGender) {
                    EvolutionStoneSimple n_ = new EvolutionStoneSimple();
                    n_.setStone(((EvolutionStoneGender)e.getValue()).getStone());
                    e.setValue(n_);
                }
                if (e.getValue() instanceof EvolutionLevelGender) {
                    EvolutionLevelSimple n_ = new EvolutionLevelSimple();
                    n_.setLevel(((EvolutionLevelGender)e.getValue()).getLevel());
                    e.setValue(n_);
                }
            }
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
        initValueOther(_key, _value);
    }

    public void initValueOther(String _key, String _value) {
        if (StringUtil.quickEq(_key, PREFIX_KEY)) {
            prefixVar(_value);
        }
        initValueOther1(_key, _value);
        initValueOther2(_key, _value);
        initValueOther3(_key, _value);
        initValueOtherCible(_key, _value);
        initValueOtherLanceur(_key, _value);
        initValueOtherFighter(_key, _value);
        initValueOther4(_key, _value);
    }

    private void initValueOther1(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_NIVEAU)) {
            niveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LEVEL_LOOSER)) {
            levelLooser(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LEVEL_WINNER)) {
            levelWinner(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_NIVEAU)) {
            fighterNiveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_NIVEAU)) {
            cibleNiveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_NIVEAU)) {
            lanceurNiveau(_value);
        }
        initValueUtSauvage(_key, _value);
        if (StringUtil.quickEq(_key, KEY_BOOST)) {
            boost(_value);
        }
    }

    private void initValueUtSauvage(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_PK_SAUVAGE_NIVEAU)) {
            pkSauvageNiveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_UT_NIVEAU)) {
            pkUtNiveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_UT_GENRE)){
            pkUtGenre(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_UT_MASSE)){
            pkUtMasse(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_UT_VITESSE)){
            pkUtVitesse(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_UT_TYPES_BASE)){
            pkUtTypesBase(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_UT_PIERRES_EVOS)){
            pkUtPierresEvos(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_SAUVAGE_GENRE)){
            pkSauvageGenre(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_SAUVAGE_MASSE)){
            pkSauvageMasse(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_SAUVAGE_VITESSE)){
            pkSauvageVitesse(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_SAUVAGE_TYPES_BASE)){
            pkSauvageTypesBase(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PK_SAUVAGE_PIERRES_EVOS)){
            pkSauvagePierresEvos(_value);
        }
    }

    private void initValueOther2(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_POWER)) {
            power(_value);
        }
        if (StringUtil.quickEq(_key, KEY_ATTACK)) {
            attack(_value);
        }
        if (StringUtil.quickEq(_key, KEY_DEFENSE)) {
            defense(_value);
        }
    }

    private void initValueOther3(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_BASE_CAPT_PK)) {
            baseCaptPk(_value);
        }
        if (StringUtil.quickEq(_key, KEY_RATE_BALL_STATUS)) {
            rateBallStatus(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FOE_PK_MAX_HP)) {
            foePkMaxHp(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FOE_PK_REMOTE_HP)) {
            foePkRemoteHp(_value);
        }
    }
    private void initValueOtherCible(String _key, String _value) {
        initValueOtherCible1(_key, _value);
        initValueOtherCible2(_key, _value);
        if (StringUtil.quickEq(_key, KEY_CIBLE_TAILLE)){
            cibleTaille(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_CAPACITE)){
            cibleCapacite(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_OBJET)){
            cibleObjet(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_STATUTS)){
            cibleStatuts(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_TYPES)){
            cibleTypes(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_PP)){
            ciblePp(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_NIVEAU)){
            cibleNiveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_BONHEUR)){
            cibleBonheur(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_KO_EQUIPE_CIBLE)){
            nbKoEquipeCible(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_KO_EQUIPE_ADV_CIBLE)){
            nbKoEquipeAdvCible(_value);
        }
        if (StringUtil.quickEq(_key, KEY_COEFF_EFF_BASE_TYPES_CIBLE)){
            coeffEffBaseTypesCible(_value);
        }
    }

    private void initValueOtherCible2(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_CIBLE_CLONE)){
            cibleClone(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_DEGATS_RECUS)){
            cibleDegatsRecus(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_DEGATS_RECUS_TOTAL)){
            cibleDegatsRecusTotal(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_DEGATS_RECUS_TOUR)){
            cibleDegatsRecusTour(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_DEGATS_RECUS_TOTAL_TOUR)){
            cibleDegatsRecusTotalTour(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_DISPARAIT)){
            cibleDisparait(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_JOUE)){
            cibleJoue(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_DER_JOUE)){
            cibleDerJoue(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_NOM)){
            cibleNom(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_MASSE)){
            cibleMasse(_value);
        }
    }

    private void initValueOtherCible1(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_CIBLE_POSSEDE_STATUT_RELATION)){
            ciblePossedeStatutRelation(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_EFFET)){
            cibleEffet(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_PP_ATTAQUE_CIBLE)){
            pasPpAttaqueCible(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_UTILIS_ATTAQUE_CIBLE)){
            pasUtilisAttaqueCible(_value);
        }
        if (StringUtil.quickEq(_key, KEY_IMMU_TYPE_ATT_CIBLE)){
            immuTypeAttCible(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_GENRE)){
            cibleGenre(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_PV_RESTANTS)){
            ciblePvRestants(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_PV_MAX)){
            ciblePvMax(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_NB_UTILISATION)){
            cibleNbUtilisation(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_STATIS)){
            cibleStatis(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_BOOST)){
            cibleBoost(_value);
        }
        if (StringUtil.quickEq(_key, KEY_SOMME_BOOST_POS_CIBLE)){
            sommeBoostPosCible(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_ATTAQUE_CHOISIE)){
            cibleAttaqueChoisie(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_ATTAQUES)){
            cibleAttaques(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CIBLE_ATTAQUES_TYPES)){
            cibleAttaquesTypes(_value);
        }
    }

    private void initValueOtherLanceur(String _key, String _value) {
        initValueOtherLanceur1(_key, _value);
        initValueOtherLanceur2(_key, _value);
        if (StringUtil.quickEq(_key, KEY_LANCEUR_STATUTS)){
            lanceurStatuts(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_TYPES)){
            lanceurTypes(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_PP)){
            lanceurPp(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_NIVEAU)){
            lanceurNiveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_BONHEUR)){
            lanceurBonheur(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_KO_EQUIPE_LANCEUR)){
            nbKoEquipeLanceur(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_KO_EQUIPE_ADV_LANCEUR)){
            nbKoEquipeAdvLanceur(_value);
        }
        if (StringUtil.quickEq(_key, KEY_COEFF_EFF_BASE_TYPES_LANCEUR)){
            coeffEffBaseTypesLanceur(_value);
        }
    }

    private void initValueOtherLanceur2(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_LANCEUR_DEGATS_RECUS_TOTAL_TOUR)){
            lanceurDegatsRecusTotalTour(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_DISPARAIT)){
            lanceurDisparait(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_JOUE)){
            lanceurJoue(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_DER_JOUE)){
            lanceurDerJoue(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_NOM)){
            lanceurNom(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_MASSE)){
            lanceurMasse(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_TAILLE)){
            lanceurTaille(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_CAPACITE)){
            lanceurCapacite(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_OBJET)){
            lanceurObjet(_value);
        }
    }

    private void initValueOtherLanceur1(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_LANCEUR_EFFET)){
            lanceurEffet(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_GENRE)){
            lanceurGenre(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_PV_RESTANTS)){
            lanceurPvRestants(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_PV_MAX)){
            lanceurPvMax(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_NB_UTILISATION)){
            lanceurNbUtilisation(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_STATIS)){
            lanceurStatis(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_BOOST)){
            lanceurBoost(_value);
        }
        if (StringUtil.quickEq(_key, KEY_SOMME_BOOST_POS_LANCEUR)){
            sommeBoostPosLanceur(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_ATTAQUE_CHOISIE)){
            lanceurAttaqueChoisie(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_ATTAQUES)){
            lanceurAttaques(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_ATTAQUES_TYPES)){
            lanceurAttaquesTypes(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_CLONE)){
            lanceurClone(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_DEGATS_RECUS)){
            lanceurDegatsRecus(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_DEGATS_RECUS_TOTAL)){
            lanceurDegatsRecusTotal(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LANCEUR_DEGATS_RECUS_TOUR)){
            lanceurDegatsRecusTour(_value);
        }
    }

    private void initValueOtherFighter(String _key, String _value) {
        initValueOtherFighter1(_key, _value);
        initValueOtherFighter2(_key, _value);
        if (StringUtil.quickEq(_key, KEY_FIGHTER_PP)){
            fighterPp(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_NIVEAU)){
            fighterNiveau(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_BONHEUR)){
            fighterBonheur(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_KO_EQUIPE_FIGHTER)){
            nbKoEquipeFighter(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_KO_EQUIPE_ADV_FIGHTER)){
            nbKoEquipeAdvFighter(_value);
        }
        if (StringUtil.quickEq(_key, KEY_COEFF_EFF_BASE_TYPES_FIGHTER)){
            coeffEffBaseTypesFighter(_value);
        }
    }

    private void initValueOtherFighter2(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_FIGHTER_DISPARAIT)){
            fighterDisparait(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_JOUE)){
            fighterJoue(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_DER_JOUE)){
            fighterDerJoue(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_NOM)){
            fighterNom(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_MASSE)){
            fighterMasse(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_TAILLE)){
            fighterTaille(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_CAPACITE)){
            fighterCapacite(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_OBJET)){
            fighterObjet(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_STATUTS)){
            fighterStatuts(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_TYPES)){
            fighterTypes(_value);
        }
    }

    private void initValueOtherFighter1(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_FIGHTER_GENRE)){
            fighterGenre(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_PV_RESTANTS)){
            fighterPvRestants(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_PV_MAX)){
            fighterPvMax(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_NB_UTILISATION)){
            fighterNbUtilisation(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_STATIS)){
            fighterStatis(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_BOOST)){
            fighterBoost(_value);
        }
        if (StringUtil.quickEq(_key, KEY_SOMME_BOOST_POS_FIGHTER)){
            sommeBoostPosFighter(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_ATTAQUE_CHOISIE)){
            fighterAttaqueChoisie(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_ATTAQUES)){
            fighterAttaques(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_ATTAQUES_TYPES)){
            fighterAttaquesTypes(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_CLONE)){
            fighterClone(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_DEGATS_RECUS)){
            fighterDegatsRecus(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_DEGATS_RECUS_TOTAL)){
            fighterDegatsRecusTotal(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_DEGATS_RECUS_TOUR)){
            fighterDegatsRecusTour(_value);
        }
        if (StringUtil.quickEq(_key, KEY_FIGHTER_DEGATS_RECUS_TOTAL_TOUR)){
            fighterDegatsRecusTotalTour(_value);
        }
    }

    private void initValueOther4(String _key, String _value) {
        initValueOther5(_key, _value);
        initValueOther6(_key, _value);
        if (StringUtil.quickEq(_key, KEY_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT)){
            coeffEffBaseTypesCombattantEntrant(_value);
        }
        if (StringUtil.quickEq(_key, KEY_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION)){
            equipeAdvCombattantEntrantNbUtilisation(_value);
        }
        if (StringUtil.quickEq(_key, KEY_ATTAQUE_CATEGORIE)){
            attaqueCategorie(_value);
        }
        if (StringUtil.quickEq(_key, KEY_ATTAQUE_TYPES)){
            attaqueTypes(_value);
        }
        if (StringUtil.quickEq(_key, KEY_ATTAQUE_NOM)){
            attaqueNom(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PUISSANCE_BASE)){
            puissanceBase(_value);
        }
        if (StringUtil.quickEq(_key, KEY_COEFF_EFF)){
            coeffEff(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_UTILISATION_CONSECUTIF)){
            nbUtilisationConsecutif(_value);
        }
        if (StringUtil.quickEq(_key, KEY_EQUIPE_NB_UTILISATION)){
            equipeNbUtilisation(_value);
        }
        if (StringUtil.quickEq(_key, KEY_EQUIPE_ADV_NB_UTILISATION)){
            equipeAdvNbUtilisation(_value);
        }
    }

    private void initValueOther6(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_TEMPS_TOUR)){
            tempsTour(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_TOUR)){
            nbTour(_value);
        }
        if (StringUtil.quickEq(_key, KEY_RATE_EFF_MOVE_AGAINST_TARGET)){
            rateEffMoveAgainstTarget(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_COMBATTANTS_TERRAIN)){
            nbCombattantsTerrain(_value);
        }
        if (StringUtil.quickEq(_key, KEY_LIEU_COMBAT)){
            lieuCombat(_value);
        }
        if (StringUtil.quickEq(_key, KEY_CLIMATS)){
            climats(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_TOUR_GLOBAL)){
            nbTourGlobal(_value);
        }
        if (StringUtil.quickEq(_key, KEY_COMBATTANT_ENTRANT_CLONE)){
            combattantEntrantClone(_value);
        }
        if (StringUtil.quickEq(_key, KEY_COMBATTANT_ENTRANT_TYPES)){
            combattantEntrantTypes(_value);
        }
    }

    private void initValueOther5(String _key, String _value) {
        if (StringUtil.quickEq(_key, KEY_DEJA_CAPTURE)){
            dejaCapture(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_FLEES)){
            nbFlees(_value);
        }
        if (StringUtil.quickEq(_key, KEY_MASSE_MOYENNE_PK)){
            masseMoyennePk(_value);
        }
        if (StringUtil.quickEq(_key, KEY_IMMU_TYPE_ATT_COMBATTANT_ENTRANT)){
            immuTypeAttCombattantEntrant(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_ATTAQUE_INVOC)){
            pasAttaqueInvoc(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_ATTAQUES_COPIABLES)){
            pasAttaquesCopiables(_value);
        }
        if (StringUtil.quickEq(_key, KEY_AUCUN_BOOST_POSSIBLE)){
            aucunBoostPossible(_value);
        }
        if (StringUtil.quickEq(_key, KEY_TYPES_ATTAQUES_RES_VIDE)){
            typesAttaquesResVide(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_PARTENAIRE)){
            pasPartenaire(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_PARTENAIRE_ARRIERE)){
            pasPartenaireArriere(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_PARTENAIRE_TERRAIN)){
            pasPartenaireTerrain(_value);
        }
        if (StringUtil.quickEq(_key, KEY_PAS_TOUR_TERRAIN)){
            pasTourTerrain(_value);
        }
        if (StringUtil.quickEq(_key, KEY_EXISTE_GENRE_ASSEXUE)){
            existeGenreAssexue(_value);
        }
        if (StringUtil.quickEq(_key, KEY_GENRES_EGAUX)){
            genresEgaux(_value);
        }
        if (StringUtil.quickEq(_key, KEY_NB_UTILI_ATT_EQ_TOUR)){
            nbUtiliAttEqTour(_value);
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
        validateOtherConstants();
    }
    public void validateOtherConstants() {
        if (incorrectPrefix(prefixVar())) {
            prefixVar(MessagesDataBaseConstants.VAR_DEF);
        }
        checkGroups();
        checkKeys();
    }

    private void checkGroups() {
        if (gr1().hasDuplicates()) {
            levelLooser(EMPTY_STRING);
            levelWinner(EMPTY_STRING);
        }
        if (gr2().hasDuplicates()) {
            cibleNiveau(EMPTY_STRING);
            lanceurNiveau(EMPTY_STRING);
        }
        if (gr3().hasDuplicates()) {
            pkSauvageNiveau(EMPTY_STRING);
            pkUtNiveau(EMPTY_STRING);
            dejaCapture(EMPTY_STRING);
            nbFlees(EMPTY_STRING);
            lieuCombat(EMPTY_STRING);
            tempsTour(EMPTY_STRING);
            masseMoyennePk(EMPTY_STRING);
            pkUtGenre(EMPTY_STRING);
            pkUtMasse(EMPTY_STRING);
            pkUtVitesse(EMPTY_STRING);
            pkSauvageGenre(EMPTY_STRING);
            pkSauvageMasse(EMPTY_STRING);
            pkSauvageVitesse(EMPTY_STRING);
            pkSauvageTypesBase(EMPTY_STRING);
            pkSauvagePierresEvos(EMPTY_STRING);
            pkUtTypesBase(EMPTY_STRING);
            pkUtPierresEvos(EMPTY_STRING);
        }
        if (gr4().hasDuplicates()) {
            lanceurNiveau(EMPTY_STRING);
            attack(EMPTY_STRING);
            defense(EMPTY_STRING);
            power(EMPTY_STRING);
        }
        if (gr5().hasDuplicates()) {
            baseCaptPk(EMPTY_STRING);
            rateBallStatus(EMPTY_STRING);
            foePkMaxHp(EMPTY_STRING);
            foePkRemoteHp(EMPTY_STRING);
        }
        if (gr6().hasDuplicates()) {
            immuTypeAttCombattantEntrant(EMPTY_STRING);
            pasAttaqueInvoc(EMPTY_STRING);
            pasAttaquesCopiables(EMPTY_STRING);
            ciblePossedeStatutRelation(EMPTY_STRING);
            cibleEffet(EMPTY_STRING);
            pasPpAttaqueCible(EMPTY_STRING);
            pasUtilisAttaqueCible(EMPTY_STRING);
            immuTypeAttCible(EMPTY_STRING);
            aucunBoostPossible(EMPTY_STRING);
            lanceurEffet(EMPTY_STRING);
            typesAttaquesResVide(EMPTY_STRING);
            pasPartenaire(EMPTY_STRING);
            pasPartenaireArriere(EMPTY_STRING);
            pasPartenaireTerrain(EMPTY_STRING);
            pasTourTerrain(EMPTY_STRING);
            existeGenreAssexue(EMPTY_STRING);
            genresEgaux(EMPTY_STRING);
            cibleGenre(EMPTY_STRING);
            ciblePvRestants(EMPTY_STRING);
            ciblePvMax(EMPTY_STRING);
            cibleNbUtilisation(EMPTY_STRING);
            cibleStatis(EMPTY_STRING);
            cibleBoost(EMPTY_STRING);
            sommeBoostPosCible(EMPTY_STRING);
            cibleAttaqueChoisie(EMPTY_STRING);
            cibleAttaques(EMPTY_STRING);
            cibleAttaquesTypes(EMPTY_STRING);
            cibleClone(EMPTY_STRING);
            cibleDegatsRecus(EMPTY_STRING);
            cibleDegatsRecusTotal(EMPTY_STRING);
            cibleDegatsRecusTour(EMPTY_STRING);
            cibleDegatsRecusTotalTour(EMPTY_STRING);
            cibleDisparait(EMPTY_STRING);
            cibleJoue(EMPTY_STRING);
            cibleDerJoue(EMPTY_STRING);
            cibleNom(EMPTY_STRING);
            cibleMasse(EMPTY_STRING);
            cibleTaille(EMPTY_STRING);
            cibleCapacite(EMPTY_STRING);
            cibleObjet(EMPTY_STRING);
            cibleStatuts(EMPTY_STRING);
            cibleTypes(EMPTY_STRING);
            ciblePp(EMPTY_STRING);
            cibleNiveau(EMPTY_STRING);
            cibleBonheur(EMPTY_STRING);
            nbKoEquipeCible(EMPTY_STRING);
            nbKoEquipeAdvCible(EMPTY_STRING);
            coeffEffBaseTypesCible(EMPTY_STRING);
            lanceurGenre(EMPTY_STRING);
            nbUtiliAttEqTour(EMPTY_STRING);
            lanceurPvRestants(EMPTY_STRING);
            lanceurPvMax(EMPTY_STRING);
            lanceurNbUtilisation(EMPTY_STRING);
            lanceurStatis(EMPTY_STRING);
            lanceurBoost(EMPTY_STRING);
            sommeBoostPosLanceur(EMPTY_STRING);
            lanceurAttaqueChoisie(EMPTY_STRING);
            lanceurAttaques(EMPTY_STRING);
            lanceurAttaquesTypes(EMPTY_STRING);
            lanceurClone(EMPTY_STRING);
            lanceurDegatsRecus(EMPTY_STRING);
            lanceurDegatsRecusTotal(EMPTY_STRING);
            lanceurDegatsRecusTour(EMPTY_STRING);
            lanceurDegatsRecusTotalTour(EMPTY_STRING);
            lanceurDisparait(EMPTY_STRING);
            lanceurJoue(EMPTY_STRING);
            lanceurDerJoue(EMPTY_STRING);
            lanceurNom(EMPTY_STRING);
            lanceurMasse(EMPTY_STRING);
            lanceurTaille(EMPTY_STRING);
            lanceurCapacite(EMPTY_STRING);
            lanceurObjet(EMPTY_STRING);
            lanceurStatuts(EMPTY_STRING);
            lanceurTypes(EMPTY_STRING);
            lanceurPp(EMPTY_STRING);
            lanceurNiveau(EMPTY_STRING);
            lanceurBonheur(EMPTY_STRING);
            nbKoEquipeLanceur(EMPTY_STRING);
            nbKoEquipeAdvLanceur(EMPTY_STRING);
            coeffEffBaseTypesLanceur(EMPTY_STRING);
            tempsTour(EMPTY_STRING);
            nbTour(EMPTY_STRING);
            rateEffMoveAgainstTarget(EMPTY_STRING);
            nbCombattantsTerrain(EMPTY_STRING);
            lieuCombat(EMPTY_STRING);
            climats(EMPTY_STRING);
            nbTourGlobal(EMPTY_STRING);
            combattantEntrantClone(EMPTY_STRING);
            combattantEntrantTypes(EMPTY_STRING);
            coeffEffBaseTypesCombattantEntrant(EMPTY_STRING);
            equipeAdvCombattantEntrantNbUtilisation(EMPTY_STRING);
            attaqueCategorie(EMPTY_STRING);
            attaqueTypes(EMPTY_STRING);
            attaqueNom(EMPTY_STRING);
            puissanceBase(EMPTY_STRING);
            fighterGenre(EMPTY_STRING);
            fighterPvRestants(EMPTY_STRING);
            fighterPvMax(EMPTY_STRING);
            fighterNbUtilisation(EMPTY_STRING);
            fighterStatis(EMPTY_STRING);
            fighterBoost(EMPTY_STRING);
            sommeBoostPosFighter(EMPTY_STRING);
            fighterAttaqueChoisie(EMPTY_STRING);
            fighterAttaques(EMPTY_STRING);
            fighterAttaquesTypes(EMPTY_STRING);
            fighterClone(EMPTY_STRING);
            fighterDegatsRecus(EMPTY_STRING);
            fighterDegatsRecusTotal(EMPTY_STRING);
            fighterDegatsRecusTour(EMPTY_STRING);
            fighterDegatsRecusTotalTour(EMPTY_STRING);
            fighterDisparait(EMPTY_STRING);
            fighterJoue(EMPTY_STRING);
            fighterDerJoue(EMPTY_STRING);
            fighterNom(EMPTY_STRING);
            fighterMasse(EMPTY_STRING);
            fighterTaille(EMPTY_STRING);
            fighterCapacite(EMPTY_STRING);
            fighterObjet(EMPTY_STRING);
            fighterStatuts(EMPTY_STRING);
            fighterTypes(EMPTY_STRING);
            fighterPp(EMPTY_STRING);
            fighterNiveau(EMPTY_STRING);
            fighterBonheur(EMPTY_STRING);
            nbKoEquipeFighter(EMPTY_STRING);
            nbKoEquipeAdvFighter(EMPTY_STRING);
            coeffEffBaseTypesFighter(EMPTY_STRING);
            coeffEff(EMPTY_STRING);
            nbUtilisationConsecutif(EMPTY_STRING);
            equipeNbUtilisation(EMPTY_STRING);
            equipeAdvNbUtilisation(EMPTY_STRING);
        }
    }

    private void checkKeys() {
        if (incorrectKey(niveau())) {
            niveau(MessagesDataBaseConstants.DEF_NIVEAU);
        }
        if (incorrectKey(levelLooser())) {
            levelLooser(MessagesDataBaseConstants.DEF_LEVEL_LOOSER);
        }
        if (incorrectKey(levelWinner())) {
            levelWinner(MessagesDataBaseConstants.DEF_LEVEL_WINNER);
        }
        if (incorrectKey(fighterNiveau())) {
            fighterNiveau(MessagesDataBaseConstants.DEF_FIGHTER_NIVEAU);
        }
        if (incorrectKey(cibleNiveau())) {
            cibleNiveau(MessagesDataBaseConstants.DEF_CIBLE_NIVEAU);
        }
        if (incorrectKey(lanceurNiveau())) {
            lanceurNiveau(MessagesDataBaseConstants.DEF_LANCEUR_NIVEAU);
        }
        catching();
        if (incorrectKey(boost())) {
            boost(MessagesDataBaseConstants.DEF_BOOST);
        }
        if (incorrectKey(power())) {
            power(MessagesDataBaseConstants.DEF_POWER);
        }
        if (incorrectKey(attack())) {
            attack(MessagesDataBaseConstants.DEF_ATTACK);
        }
        if (incorrectKey(defense())) {
            defense(MessagesDataBaseConstants.DEF_DEFENSE);
        }
        capt();
        cible();
        lanceur();
        fighter();
        other();
    }

    private void catching() {
        if (incorrectKey(dejaCapture())) {
            dejaCapture(MessagesDataBaseConstants.DEF_DEJA_CAPTURE);
        }
        if (incorrectKey(nbFlees())) {
            nbFlees(MessagesDataBaseConstants.DEF_NB_FLEES);
        }
        if (incorrectKey(pkSauvageNiveau())) {
            pkSauvageNiveau(MessagesDataBaseConstants.DEF_PK_SAUVAGE_NIVEAU);
        }
        if (incorrectKey(pkUtNiveau())) {
            pkUtNiveau(MessagesDataBaseConstants.DEF_PK_UT_NIVEAU);
        }
        if (incorrectKey(masseMoyennePk())) {
            masseMoyennePk(MessagesDataBaseConstants.DEF_MASSE_MOYENNE_PK);
        }
        if (incorrectKey(pkUtGenre())) {
            pkUtGenre(MessagesDataBaseConstants.DEF_PK_UT_GENRE);
        }
        if (incorrectKey(pkUtMasse())) {
            pkUtMasse(MessagesDataBaseConstants.DEF_PK_UT_MASSE);
        }
        if (incorrectKey(pkUtVitesse())) {
            pkUtVitesse(MessagesDataBaseConstants.DEF_PK_UT_VITESSE);
        }
        if (incorrectKey(pkUtTypesBase())) {
            pkUtTypesBase(MessagesDataBaseConstants.DEF_PK_UT_TYPES_BASE);
        }
        if (incorrectKey(pkUtPierresEvos())) {
            pkUtPierresEvos(MessagesDataBaseConstants.DEF_PK_UT_PIERRES_EVOS);
        }
        if (incorrectKey(pkSauvageGenre())) {
            pkSauvageGenre(MessagesDataBaseConstants.DEF_PK_SAUVAGE_GENRE);
        }
        if (incorrectKey(pkSauvageMasse())) {
            pkSauvageMasse(MessagesDataBaseConstants.DEF_PK_SAUVAGE_MASSE);
        }
        if (incorrectKey(pkSauvageVitesse())) {
            pkSauvageVitesse(MessagesDataBaseConstants.DEF_PK_SAUVAGE_VITESSE);
        }
        if (incorrectKey(pkSauvageTypesBase())) {
            pkSauvageTypesBase(MessagesDataBaseConstants.DEF_PK_SAUVAGE_TYPES_BASE);
        }
        if (incorrectKey(pkSauvagePierresEvos())) {
            pkSauvagePierresEvos(MessagesDataBaseConstants.DEF_PK_SAUVAGE_PIERRES_EVOS);
        }
    }

    private void capt() {
        if (incorrectKey(baseCaptPk())) {
            baseCaptPk(MessagesDataBaseConstants.DEF_BASE_CAPT_PK);
        }
        if (incorrectKey(rateBallStatus())) {
            rateBallStatus(MessagesDataBaseConstants.DEF_RATE_BALL_STATUS);
        }
        if (incorrectKey(foePkMaxHp())) {
            foePkMaxHp(MessagesDataBaseConstants.DEF_FOE_PK_MAX_HP);
        }
        if (incorrectKey(foePkRemoteHp())) {
            foePkRemoteHp(MessagesDataBaseConstants.DEF_FOE_PK_REMOTE_HP);
        }
    }
    private void cible() {
        cible1();
        cible2();
        if (incorrectKey(cibleMasse())) {
            cibleMasse(MessagesDataBaseConstants.DEF_CIBLE_MASSE);
        }
        if (incorrectKey(cibleTaille())) {
            cibleTaille(MessagesDataBaseConstants.DEF_CIBLE_TAILLE);
        }
        if (incorrectKey(cibleCapacite())) {
            cibleCapacite(MessagesDataBaseConstants.DEF_CIBLE_CAPACITE);
        }
        if (incorrectKey(cibleObjet())) {
            cibleObjet(MessagesDataBaseConstants.DEF_CIBLE_OBJET);
        }
        if (incorrectKey(cibleStatuts())) {
            cibleStatuts(MessagesDataBaseConstants.DEF_CIBLE_STATUTS);
        }
        if (incorrectKey(cibleTypes())) {
            cibleTypes(MessagesDataBaseConstants.DEF_CIBLE_TYPES);
        }
        if (incorrectKey(ciblePp())) {
            ciblePp(MessagesDataBaseConstants.DEF_CIBLE_PP);
        }
        if (incorrectKey(cibleBonheur())) {
            cibleBonheur(MessagesDataBaseConstants.DEF_CIBLE_BONHEUR);
        }
        if (incorrectKey(nbKoEquipeCible())) {
            nbKoEquipeCible(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_CIBLE);
        }
        if (incorrectKey(nbKoEquipeAdvCible())) {
            nbKoEquipeAdvCible(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_ADV_CIBLE);
        }
        if (incorrectKey(coeffEffBaseTypesCible())) {
            coeffEffBaseTypesCible(MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_CIBLE);
        }
    }

    private void cible2() {
        if (incorrectKey(cibleClone())) {
            cibleClone(MessagesDataBaseConstants.DEF_CIBLE_CLONE);
        }
        if (incorrectKey(cibleDegatsRecus())) {
            cibleDegatsRecus(MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS);
        }
        if (incorrectKey(cibleDegatsRecusTotal())) {
            cibleDegatsRecusTotal(MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL);
        }
        if (incorrectKey(cibleDegatsRecusTour())) {
            cibleDegatsRecusTour(MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOUR);
        }
        if (incorrectKey(cibleDegatsRecusTotalTour())) {
            cibleDegatsRecusTotalTour(MessagesDataBaseConstants.DEF_CIBLE_DEGATS_RECUS_TOTAL_TOUR);
        }
        if (incorrectKey(cibleDisparait())) {
            cibleDisparait(MessagesDataBaseConstants.DEF_CIBLE_DISPARAIT);
        }
        if (incorrectKey(cibleJoue())) {
            cibleJoue(MessagesDataBaseConstants.DEF_CIBLE_JOUE);
        }
        if (incorrectKey(cibleDerJoue())) {
            cibleDerJoue(MessagesDataBaseConstants.DEF_CIBLE_DER_JOUE);
        }
        if (incorrectKey(cibleNom())) {
            cibleNom(MessagesDataBaseConstants.DEF_CIBLE_NOM);
        }
    }

    private void cible1() {
        if (incorrectKey(ciblePossedeStatutRelation())) {
            ciblePossedeStatutRelation(MessagesDataBaseConstants.DEF_CIBLE_POSSEDE_STATUT_RELATION);
        }
        if (incorrectKey(cibleEffet())) {
            cibleEffet(MessagesDataBaseConstants.DEF_CIBLE_EFFET);
        }
        if (incorrectKey(pasPpAttaqueCible())) {
            pasPpAttaqueCible(MessagesDataBaseConstants.DEF_PAS_PP_ATTAQUE_CIBLE);
        }
        if (incorrectKey(pasUtilisAttaqueCible())) {
            pasUtilisAttaqueCible(MessagesDataBaseConstants.DEF_PAS_UTILIS_ATTAQUE_CIBLE);
        }
        if (incorrectKey(immuTypeAttCible())) {
            immuTypeAttCible(MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_CIBLE);
        }
        if (incorrectKey(cibleGenre())) {
            cibleGenre(MessagesDataBaseConstants.DEF_CIBLE_GENRE);
        }
        if (incorrectKey(ciblePvRestants())) {
            ciblePvRestants(MessagesDataBaseConstants.DEF_CIBLE_PV_RESTANTS);
        }
        if (incorrectKey(ciblePvMax())) {
            ciblePvMax(MessagesDataBaseConstants.DEF_CIBLE_PV_MAX);
        }
        if (incorrectKey(cibleNbUtilisation())) {
            cibleNbUtilisation(MessagesDataBaseConstants.DEF_CIBLE_NB_UTILISATION);
        }
        if (incorrectKey(cibleStatis())) {
            cibleStatis(MessagesDataBaseConstants.DEF_CIBLE_STATIS);
        }
        if (incorrectKey(cibleBoost())) {
            cibleBoost(MessagesDataBaseConstants.DEF_CIBLE_BOOST);
        }
        if (incorrectKey(sommeBoostPosCible())) {
            sommeBoostPosCible(MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_CIBLE);
        }
        if (incorrectKey(cibleAttaqueChoisie())) {
            cibleAttaqueChoisie(MessagesDataBaseConstants.DEF_CIBLE_ATTAQUE_CHOISIE);
        }
        if (incorrectKey(cibleAttaques())) {
            cibleAttaques(MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES);
        }
        if (incorrectKey(cibleAttaquesTypes())) {
            cibleAttaquesTypes(MessagesDataBaseConstants.DEF_CIBLE_ATTAQUES_TYPES);
        }
    }

    private void lanceur() {
        lanceur1();
        lanceur2();
        if (incorrectKey(lanceurPp())) {
            lanceurPp(MessagesDataBaseConstants.DEF_LANCEUR_PP);
        }
        if (incorrectKey(lanceurBonheur())) {
            lanceurBonheur(MessagesDataBaseConstants.DEF_LANCEUR_BONHEUR);
        }
        if (incorrectKey(nbKoEquipeLanceur())) {
            nbKoEquipeLanceur(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_LANCEUR);
        }
        if (incorrectKey(nbKoEquipeAdvLanceur())) {
            nbKoEquipeAdvLanceur(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_ADV_LANCEUR);
        }
        if (incorrectKey(coeffEffBaseTypesLanceur())) {
            coeffEffBaseTypesLanceur(MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_LANCEUR);
        }
    }

    private void lanceur2() {
        if (incorrectKey(lanceurDegatsRecusTotalTour())) {
            lanceurDegatsRecusTotalTour(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL_TOUR);
        }
        if (incorrectKey(lanceurDisparait())) {
            lanceurDisparait(MessagesDataBaseConstants.DEF_LANCEUR_DISPARAIT);
        }
        if (incorrectKey(lanceurJoue())) {
            lanceurJoue(MessagesDataBaseConstants.DEF_LANCEUR_JOUE);
        }
        if (incorrectKey(lanceurDerJoue())) {
            lanceurDerJoue(MessagesDataBaseConstants.DEF_LANCEUR_DER_JOUE);
        }
        if (incorrectKey(lanceurNom())) {
            lanceurNom(MessagesDataBaseConstants.DEF_LANCEUR_NOM);
        }
        if (incorrectKey(lanceurMasse())) {
            lanceurMasse(MessagesDataBaseConstants.DEF_LANCEUR_MASSE);
        }
        if (incorrectKey(lanceurTaille())) {
            lanceurTaille(MessagesDataBaseConstants.DEF_LANCEUR_TAILLE);
        }
        if (incorrectKey(lanceurCapacite())) {
            lanceurCapacite(MessagesDataBaseConstants.DEF_LANCEUR_CAPACITE);
        }
        if (incorrectKey(lanceurObjet())) {
            lanceurObjet(MessagesDataBaseConstants.DEF_LANCEUR_OBJET);
        }
        if (incorrectKey(lanceurStatuts())) {
            lanceurStatuts(MessagesDataBaseConstants.DEF_LANCEUR_STATUTS);
        }
        if (incorrectKey(lanceurTypes())) {
            lanceurTypes(MessagesDataBaseConstants.DEF_LANCEUR_TYPES);
        }
    }

    private void lanceur1() {
        if (incorrectKey(lanceurEffet())) {
            lanceurEffet(MessagesDataBaseConstants.DEF_LANCEUR_EFFET);
        }
        if (incorrectKey(lanceurGenre())) {
            lanceurGenre(MessagesDataBaseConstants.DEF_LANCEUR_GENRE);
        }
        if (incorrectKey(lanceurPvRestants())) {
            lanceurPvRestants(MessagesDataBaseConstants.DEF_LANCEUR_PV_RESTANTS);
        }
        if (incorrectKey(lanceurPvMax())) {
            lanceurPvMax(MessagesDataBaseConstants.DEF_LANCEUR_PV_MAX);
        }
        if (incorrectKey(lanceurNbUtilisation())) {
            lanceurNbUtilisation(MessagesDataBaseConstants.DEF_LANCEUR_NB_UTILISATION);
        }
        if (incorrectKey(lanceurStatis())) {
            lanceurStatis(MessagesDataBaseConstants.DEF_LANCEUR_STATIS);
        }
        if (incorrectKey(lanceurBoost())) {
            lanceurBoost(MessagesDataBaseConstants.DEF_LANCEUR_BOOST);
        }
        if (incorrectKey(sommeBoostPosLanceur())) {
            sommeBoostPosLanceur(MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_LANCEUR);
        }
        if (incorrectKey(lanceurAttaqueChoisie())) {
            lanceurAttaqueChoisie(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUE_CHOISIE);
        }
        if (incorrectKey(lanceurAttaques())) {
            lanceurAttaques(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES);
        }
        if (incorrectKey(lanceurAttaquesTypes())) {
            lanceurAttaquesTypes(MessagesDataBaseConstants.DEF_LANCEUR_ATTAQUES_TYPES);
        }
        if (incorrectKey(lanceurClone())) {
            lanceurClone(MessagesDataBaseConstants.DEF_LANCEUR_CLONE);
        }
        if (incorrectKey(lanceurDegatsRecus())) {
            lanceurDegatsRecus(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS);
        }
        if (incorrectKey(lanceurDegatsRecusTotal())) {
            lanceurDegatsRecusTotal(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOTAL);
        }
        if (incorrectKey(lanceurDegatsRecusTour())) {
            lanceurDegatsRecusTour(MessagesDataBaseConstants.DEF_LANCEUR_DEGATS_RECUS_TOUR);
        }
    }

    private void fighter() {
        fighter1();
        fighter2();
        if (incorrectKey(fighterTypes())) {
            fighterTypes(MessagesDataBaseConstants.DEF_FIGHTER_TYPES);
        }
        if (incorrectKey(fighterPp())) {
            fighterPp(MessagesDataBaseConstants.DEF_FIGHTER_PP);
        }
        if (incorrectKey(fighterBonheur())) {
            fighterBonheur(MessagesDataBaseConstants.DEF_FIGHTER_BONHEUR);
        }
        if (incorrectKey(nbKoEquipeFighter())) {
            nbKoEquipeFighter(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_FIGHTER);
        }
        if (incorrectKey(nbKoEquipeAdvFighter())) {
            nbKoEquipeAdvFighter(MessagesDataBaseConstants.DEF_NB_KO_EQUIPE_ADV_FIGHTER);
        }
        if (incorrectKey(coeffEffBaseTypesFighter())) {
            coeffEffBaseTypesFighter(MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_FIGHTER);
        }
    }

    private void fighter2() {
        if (incorrectKey(fighterDisparait())) {
            fighterDisparait(MessagesDataBaseConstants.DEF_FIGHTER_DISPARAIT);
        }
        if (incorrectKey(fighterJoue())) {
            fighterJoue(MessagesDataBaseConstants.DEF_FIGHTER_JOUE);
        }
        if (incorrectKey(fighterDerJoue())) {
            fighterDerJoue(MessagesDataBaseConstants.DEF_FIGHTER_DER_JOUE);
        }
        if (incorrectKey(fighterNom())) {
            fighterNom(MessagesDataBaseConstants.DEF_FIGHTER_NOM);
        }
        if (incorrectKey(fighterMasse())) {
            fighterMasse(MessagesDataBaseConstants.DEF_FIGHTER_MASSE);
        }
        if (incorrectKey(fighterTaille())) {
            fighterTaille(MessagesDataBaseConstants.DEF_FIGHTER_TAILLE);
        }
        if (incorrectKey(fighterCapacite())) {
            fighterCapacite(MessagesDataBaseConstants.DEF_FIGHTER_CAPACITE);
        }
        if (incorrectKey(fighterObjet())) {
            fighterObjet(MessagesDataBaseConstants.DEF_FIGHTER_OBJET);
        }
        if (incorrectKey(fighterStatuts())) {
            fighterStatuts(MessagesDataBaseConstants.DEF_FIGHTER_STATUTS);
        }
    }

    private void fighter1() {
        if (incorrectKey(fighterGenre())) {
            fighterGenre(MessagesDataBaseConstants.DEF_FIGHTER_GENRE);
        }
        if (incorrectKey(fighterPvRestants())) {
            fighterPvRestants(MessagesDataBaseConstants.DEF_FIGHTER_PV_RESTANTS);
        }
        if (incorrectKey(fighterPvMax())) {
            fighterPvMax(MessagesDataBaseConstants.DEF_FIGHTER_PV_MAX);
        }
        if (incorrectKey(fighterNbUtilisation())) {
            fighterNbUtilisation(MessagesDataBaseConstants.DEF_FIGHTER_NB_UTILISATION);
        }
        if (incorrectKey(fighterStatis())) {
            fighterStatis(MessagesDataBaseConstants.DEF_FIGHTER_STATIS);
        }
        if (incorrectKey(fighterBoost())) {
            fighterBoost(MessagesDataBaseConstants.DEF_FIGHTER_BOOST);
        }
        if (incorrectKey(sommeBoostPosFighter())) {
            sommeBoostPosFighter(MessagesDataBaseConstants.DEF_SOMME_BOOST_POS_FIGHTER);
        }
        if (incorrectKey(fighterAttaqueChoisie())) {
            fighterAttaqueChoisie(MessagesDataBaseConstants.DEF_FIGHTER_ATTAQUE_CHOISIE);
        }
        if (incorrectKey(fighterAttaques())) {
            fighterAttaques(MessagesDataBaseConstants.DEF_FIGHTER_ATTAQUES);
        }
        if (incorrectKey(fighterAttaquesTypes())) {
            fighterAttaquesTypes(MessagesDataBaseConstants.DEF_FIGHTER_ATTAQUES_TYPES);
        }
        if (incorrectKey(fighterClone())) {
            fighterClone(MessagesDataBaseConstants.DEF_FIGHTER_CLONE);
        }
        if (incorrectKey(fighterDegatsRecus())) {
            fighterDegatsRecus(MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS);
        }
        if (incorrectKey(fighterDegatsRecusTotal())) {
            fighterDegatsRecusTotal(MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS_TOTAL);
        }
        if (incorrectKey(fighterDegatsRecusTour())) {
            fighterDegatsRecusTour(MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS_TOUR);
        }
        if (incorrectKey(fighterDegatsRecusTotalTour())) {
            fighterDegatsRecusTotalTour(MessagesDataBaseConstants.DEF_FIGHTER_DEGATS_RECUS_TOTAL_TOUR);
        }
    }

    private void other() {
        other1();
        other2();
        if (incorrectKey(puissanceBase())) {
            puissanceBase(MessagesDataBaseConstants.DEF_PUISSANCE_BASE);
        }
        if (incorrectKey(coeffEff())) {
            coeffEff(MessagesDataBaseConstants.DEF_COEFF_EFF);
        }
        if (incorrectKey(nbUtilisationConsecutif())) {
            nbUtilisationConsecutif(MessagesDataBaseConstants.DEF_NB_UTILISATION_CONSECUTIF);
        }
        if (incorrectKey(equipeNbUtilisation())) {
            equipeNbUtilisation(MessagesDataBaseConstants.DEF_EQUIPE_NB_UTILISATION);
        }
        if (incorrectKey(equipeAdvNbUtilisation())) {
            equipeAdvNbUtilisation(MessagesDataBaseConstants.DEF_EQUIPE_ADV_NB_UTILISATION);
        }
    }

    private void other2() {
        if (incorrectKey(nbCombattantsTerrain())) {
            nbCombattantsTerrain(MessagesDataBaseConstants.DEF_NB_COMBATTANTS_TERRAIN);
        }
        if (incorrectKey(lieuCombat())) {
            lieuCombat(MessagesDataBaseConstants.DEF_LIEU_COMBAT);
        }
        if (incorrectKey(climats())) {
            climats(MessagesDataBaseConstants.DEF_CLIMATS);
        }
        if (incorrectKey(nbTourGlobal())) {
            nbTourGlobal(MessagesDataBaseConstants.DEF_NB_TOUR_GLOBAL);
        }
        if (incorrectKey(combattantEntrantClone())) {
            combattantEntrantClone(MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_CLONE);
        }
        if (incorrectKey(combattantEntrantTypes())) {
            combattantEntrantTypes(MessagesDataBaseConstants.DEF_COMBATTANT_ENTRANT_TYPES);
        }
        if (incorrectKey(coeffEffBaseTypesCombattantEntrant())) {
            coeffEffBaseTypesCombattantEntrant(MessagesDataBaseConstants.DEF_COEFF_EFF_BASE_TYPES_COMBATTANT_ENTRANT);
        }
        if (incorrectKey(equipeAdvCombattantEntrantNbUtilisation())) {
            equipeAdvCombattantEntrantNbUtilisation(MessagesDataBaseConstants.DEF_EQUIPE_ADV_COMBATTANT_ENTRANT_NB_UTILISATION);
        }
        if (incorrectKey(attaqueCategorie())) {
            attaqueCategorie(MessagesDataBaseConstants.DEF_ATTAQUE_CATEGORIE);
        }
        if (incorrectKey(attaqueTypes())) {
            attaqueTypes(MessagesDataBaseConstants.DEF_ATTAQUE_TYPES);
        }
        if (incorrectKey(attaqueNom())) {
            attaqueNom(MessagesDataBaseConstants.DEF_ATTAQUE_NOM);
        }
    }

    private void other1() {
        if (incorrectKey(immuTypeAttCombattantEntrant())) {
            immuTypeAttCombattantEntrant(MessagesDataBaseConstants.DEF_IMMU_TYPE_ATT_COMBATTANT_ENTRANT);
        }
        if (incorrectKey(pasAttaqueInvoc())) {
            pasAttaqueInvoc(MessagesDataBaseConstants.DEF_PAS_ATTAQUE_INVOC);
        }
        if (incorrectKey(pasAttaquesCopiables())) {
            pasAttaquesCopiables(MessagesDataBaseConstants.DEF_PAS_ATTAQUES_COPIABLES);
        }
        if (incorrectKey(aucunBoostPossible())) {
            aucunBoostPossible(MessagesDataBaseConstants.DEF_AUCUN_BOOST_POSSIBLE);
        }
        if (incorrectKey(typesAttaquesResVide())) {
            typesAttaquesResVide(MessagesDataBaseConstants.DEF_TYPES_ATTAQUES_RES_VIDE);
        }
        if (incorrectKey(pasPartenaire())) {
            pasPartenaire(MessagesDataBaseConstants.DEF_PAS_PARTENAIRE);
        }
        if (incorrectKey(pasPartenaireArriere())) {
            pasPartenaireArriere(MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_ARRIERE);
        }
        if (incorrectKey(pasPartenaireTerrain())) {
            pasPartenaireTerrain(MessagesDataBaseConstants.DEF_PAS_PARTENAIRE_TERRAIN);
        }
        if (incorrectKey(pasTourTerrain())) {
            pasTourTerrain(MessagesDataBaseConstants.DEF_PAS_TOUR_TERRAIN);
        }
        if (incorrectKey(existeGenreAssexue())) {
            existeGenreAssexue(MessagesDataBaseConstants.DEF_EXISTE_GENRE_ASSEXUE);
        }
        if (incorrectKey(genresEgaux())) {
            genresEgaux(MessagesDataBaseConstants.DEF_GENRES_EGAUX);
        }
        if (incorrectKey(nbUtiliAttEqTour())) {
            nbUtiliAttEqTour(MessagesDataBaseConstants.DEF_NB_UTILI_ATT_EQ_TOUR);
        }
        if (incorrectKey(tempsTour())) {
            tempsTour(MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        }
        if (incorrectKey(nbTour())) {
            nbTour(MessagesDataBaseConstants.DEF_NB_TOUR);
        }
        if (incorrectKey(rateEffMoveAgainstTarget())) {
            rateEffMoveAgainstTarget(MessagesDataBaseConstants.DEF_RATE_EFF_MOVE_AGAINST_TARGET);
        }
    }

    private StringList gr1() {
        StringList str_ = new StringList();
        str_.add(levelLooser());
        str_.add(levelWinner());
        return str_;
    }

    private StringList gr2() {
        StringList str_ = new StringList();
        str_.add(cibleNiveau());
        str_.add(lanceurNiveau());
        return str_;
    }

    private StringList gr3() {
        StringList str_ = new StringList();
        str_.add(pkSauvageNiveau());
        str_.add(pkUtNiveau());
        str_.add(dejaCapture());
        str_.add(nbFlees());
        str_.add(lieuCombat());
        str_.add(tempsTour());
        str_.add(masseMoyennePk());
        str_.add(pkUtGenre());
        str_.add(pkUtMasse());
        str_.add(pkUtVitesse());
        str_.add(pkSauvageGenre());
        str_.add(pkSauvageMasse());
        str_.add(pkSauvageVitesse());
        str_.add(pkSauvageTypesBase());
        str_.add(pkSauvagePierresEvos());
        str_.add(pkUtTypesBase());
        str_.add(pkUtPierresEvos());
        return str_;
    }

    private StringList gr4() {
        StringList str_ = new StringList();
        str_.add(lanceurNiveau());
        stat(str_);
        return str_;
    }

    private StringList gr5() {
        StringList str_ = new StringList();
        str_.add(baseCaptPk());
        str_.add(rateBallStatus());
        str_.add(foePkMaxHp());
        str_.add(foePkRemoteHp());
        return str_;
    }

    private StringList gr6() {
        StringList str_ = new StringList();
        str_.add(immuTypeAttCombattantEntrant());
        str_.add(pasAttaqueInvoc());
        str_.add(pasAttaquesCopiables());
        feedExpBool(str_);
        str_.add(cibleGenre());
        str_.add(ciblePvRestants());
        str_.add(ciblePvMax());
        str_.add(cibleNbUtilisation());
        str_.add(cibleStatis());
        str_.add(cibleBoost());
        str_.add(sommeBoostPosCible());
        str_.add(cibleAttaqueChoisie());
        str_.add(cibleAttaques());
        str_.add(cibleAttaquesTypes());
        str_.add(cibleClone());
        str_.add(cibleDegatsRecus());
        str_.add(cibleDegatsRecusTotal());
        str_.add(cibleDegatsRecusTour());
        str_.add(cibleDegatsRecusTotalTour());
        str_.add(cibleDisparait());
        str_.add(cibleJoue());
        str_.add(cibleDerJoue());
        str_.add(cibleNom());
        str_.add(cibleMasse());
        str_.add(cibleTaille());
        str_.add(cibleCapacite());
        str_.add(cibleObjet());
        str_.add(cibleStatuts());
        str_.add(cibleTypes());
        str_.add(ciblePp());
        str_.add(cibleNiveau());
        str_.add(cibleBonheur());
        str_.add(nbKoEquipeCible());
        str_.add(nbKoEquipeAdvCible());
        str_.add(coeffEffBaseTypesCible());
        str_.add(lanceurGenre());
        str_.add(nbUtiliAttEqTour());
        str_.add(lanceurPvRestants());
        str_.add(lanceurPvMax());
        str_.add(lanceurNbUtilisation());
        str_.add(lanceurStatis());
        str_.add(lanceurBoost());
        str_.add(sommeBoostPosLanceur());
        str_.add(lanceurAttaqueChoisie());
        str_.add(lanceurAttaques());
        str_.add(lanceurAttaquesTypes());
        str_.add(lanceurClone());
        str_.add(lanceurDegatsRecus());
        str_.add(lanceurDegatsRecusTotal());
        str_.add(lanceurDegatsRecusTour());
        str_.add(lanceurDegatsRecusTotalTour());
        str_.add(lanceurDisparait());
        str_.add(lanceurJoue());
        str_.add(lanceurDerJoue());
        str_.add(lanceurNom());
        str_.add(lanceurMasse());
        str_.add(lanceurTaille());
        str_.add(lanceurCapacite());
        str_.add(lanceurObjet());
        str_.add(lanceurStatuts());
        str_.add(lanceurTypes());
        str_.add(lanceurPp());
        str_.add(lanceurNiveau());
        str_.add(lanceurBonheur());
        str_.add(nbKoEquipeLanceur());
        str_.add(nbKoEquipeAdvLanceur());
        str_.add(coeffEffBaseTypesLanceur());
        str_.add(tempsTour());
        str_.add(nbTour());
        str_.add(rateEffMoveAgainstTarget());
        str_.add(nbCombattantsTerrain());
        str_.add(lieuCombat());
        str_.add(climats());
        str_.add(nbTourGlobal());
        str_.add(combattantEntrantClone());
        str_.add(combattantEntrantTypes());
        str_.add(coeffEffBaseTypesCombattantEntrant());
        str_.add(equipeAdvCombattantEntrantNbUtilisation());
        str_.add(attaqueCategorie());
        str_.add(attaqueTypes());
        str_.add(attaqueNom());
        str_.add(puissanceBase());
        str_.add(fighterGenre());
        str_.add(fighterPvRestants());
        str_.add(fighterPvMax());
        str_.add(fighterNbUtilisation());
        str_.add(fighterStatis());
        str_.add(fighterBoost());
        str_.add(sommeBoostPosFighter());
        str_.add(fighterAttaqueChoisie());
        str_.add(fighterAttaques());
        str_.add(fighterAttaquesTypes());
        str_.add(fighterClone());
        str_.add(fighterDegatsRecus());
        str_.add(fighterDegatsRecusTotal());
        str_.add(fighterDegatsRecusTour());
        str_.add(fighterDegatsRecusTotalTour());
        str_.add(fighterDisparait());
        str_.add(fighterJoue());
        str_.add(fighterDerJoue());
        str_.add(fighterNom());
        str_.add(fighterMasse());
        str_.add(fighterTaille());
        str_.add(fighterCapacite());
        str_.add(fighterObjet());
        str_.add(fighterStatuts());
        str_.add(fighterTypes());
        str_.add(fighterPp());
        str_.add(fighterNiveau());
        str_.add(fighterBonheur());
        str_.add(nbKoEquipeFighter());
        str_.add(nbKoEquipeAdvFighter());
        str_.add(coeffEffBaseTypesFighter());
        str_.add(coeffEff());
        str_.add(nbUtilisationConsecutif());
        str_.add(equipeNbUtilisation());
        str_.add(equipeAdvNbUtilisation());
        stat(str_);
        return str_;
    }

    private void stat(StringList _str) {
        _str.add(attack());
        _str.add(defense());
        _str.add(power());
    }

    private void feedExpBool(StringList _str) {
        _str.add(ciblePossedeStatutRelation());
        _str.add(cibleEffet());
        _str.add(pasPpAttaqueCible());
        _str.add(pasUtilisAttaqueCible());
        _str.add(immuTypeAttCible());
        _str.add(aucunBoostPossible());
        _str.add(lanceurEffet());
        _str.add(typesAttaquesResVide());
        _str.add(pasPartenaire());
        _str.add(pasPartenaireArriere());
        _str.add(pasPartenaireTerrain());
        _str.add(pasTourTerrain());
        _str.add(existeGenreAssexue());
        _str.add(genresEgaux());
    }
    private static boolean incorrectPrefix(String _pref) {
        return !_pref.isEmpty() && MathExpUtil.isDigit(_pref.charAt(0)) || incorrectCommonPart(_pref);
    }

    private static boolean incorrectKey(String _pref) {
        return _pref.startsWith("_") || incorrectCommonPart(_pref);
    }

    private static boolean incorrectCommonPart(String _pref) {
        return _pref.endsWith("_") || !isCorrectIdentifier(_pref);
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
        checkExact(animAbsorb.getImage());
        checkInners(miniPk.values());
        checkInners(imageTmHm.getImage());
        checkInners(storage.getImage());
        notEmptyImages(maxiPkBack.values());
        notEmptyImages(maxiPkFront.values());
        DataInfoChecker.checkStringListContains(miniPk.getKeys(),pokedex.getKeys(),this);
        DataInfoChecker.checkStringListContains(miniItems.getKeys(),items.getKeys(),this);
        DataInfoChecker.checkStringListContains(maxiPkBack.getKeys(),pokedex.getKeys(),this);
        DataInfoChecker.checkStringListContains(maxiPkFront.getKeys(),pokedex.getKeys(),this);
        notEmptyImage(endGameImage.getImage());
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

    private void updateDims(CustList<ImageArrayBaseSixtyFour> _imgs) {
        for (ImageArrayBaseSixtyFour i : _imgs) {
            updateDims(i.getImage());
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

    private void checkExact(CustList<ImageArrayBaseSixtyFour> _imgs) {
        for (ImageArrayBaseSixtyFour i : _imgs) {
            checkExact(i.getImage());
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

    private void notEmptyImages(CustList<ImageArrayBaseSixtyFour> _imgs) {
        for (ImageArrayBaseSixtyFour i : _imgs) {
            notEmptyImage(i.getImage());

        }
    }

    private void notEmptyImage(int[][] _i) {
        if (_i.length == 0) {
            setError(true);
        }
    }

    private void checkInners(CustList<ImageArrayBaseSixtyFour> _imgs) {
        for (ImageArrayBaseSixtyFour i : _imgs) {
            checkInners(i.getImage());
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
        StringList homonyms_ = new StringList();
        StringList distinct_ = new StringList();
        standardKeysGenders();
        standardKeysBooleans();
        standardKeysDiffWinPts();
        standardKeysDiffModelLaw();
        standardKeysEnvironment();
        standardKeysStatistic();
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
        standardKeysTargets();
        if (allCustKeys_.hasDuplicates()) {
            setError(true);
        }
        if (!StringUtil.equalsSet(litterals.getKeys(),
                languages)) {
            setError(true);
        }
        for (String v: variables) {
            checkLittVar(v);
        }
        checkLittVar(prefixBoost());
    }

    private void checkLittVar(String _v) {
        for (EntryCust<String,StringMap<String>> m: litterals.entryList()) {
            checkLittVar(_v, m);
        }
    }

    private void checkLittVar(String _v, EntryCust<String, StringMap<String>> _m) {
        boolean f_ = false;
        String line_ = EMPTY_STRING;
        StringList varParts_ = StringUtil.splitStrings(_v, SEP_BETWEEN_KEYS);
        String var_ = StringUtil.join(varParts_.left( 2), SEP_BETWEEN_KEYS);
        String varPref_ = StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS);
        for (EntryCust<String,String> e: _m.getValue().entryList()) {
            if (StringUtil.quickEq(var_, StringUtil.concat(varPref_ ,e.getKey()))) {
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

    private void standardKeysStatistic() {
        if (!StringUtil.equalsSet(translatedStatistics.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<Statistic, String> v : translatedStatistics.values()) {
            if (!new IdList<Statistic>(v.getKeys()).containsAllObj(new IdList<Statistic>(Statistic.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysEnvironment() {
        if (!StringUtil.equalsSet(translatedEnvironment.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<EnvironmentType, String> v : translatedEnvironment
                .values()) {
            if (!new IdList<EnvironmentType>(v.getKeys()).containsAllObj(new IdList<EnvironmentType>(EnvironmentType.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysDiffModelLaw() {
        if (!StringUtil.equalsSet(translatedDiffModelLaw.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<DifficultyModelLaw, String> v : translatedDiffModelLaw
                .values()) {
            if (!new IdList<DifficultyModelLaw>(v.getKeys()).containsAllObj(new IdList<DifficultyModelLaw>(DifficultyModelLaw.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysDiffWinPts() {
        if (!StringUtil.equalsSet(translatedDiffWinPts.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<DifficultyWinPointsFight, String> v : translatedDiffWinPts
                .values()) {
            if (!new IdList<DifficultyWinPointsFight>(v.getKeys()).containsAllObj(new IdList<DifficultyWinPointsFight>(DifficultyWinPointsFight.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysBooleans() {
        if (!StringUtil.equalsSet(translatedBooleans.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<SelectedBoolean, String> v : translatedBooleans.values()) {
            if (!new IdList<SelectedBoolean>(v.getKeys()).containsAllObj(new IdList<SelectedBoolean>(SelectedBoolean.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysGenders() {
        if (!StringUtil.equalsSet(translatedGenders.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<Gender, String> v : translatedGenders.values()) {
            if (!new IdList<Gender>(v.getKeys()).containsAllObj(new IdList<Gender>(Gender.all()))) {
                setError(true);
            }
            if (hasDuplicates(v.values())) {
                setError(true);
            }
        }
    }

    private void standardKeysTargets() {
        if (!StringUtil.equalsSet(translatedTargets.getKeys(),
                languages)) {
            setError(true);
        }
        for (IdMap<TargetChoice, String> v : translatedTargets.values()) {
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
        return MathExpUtil.isWordChar(_curr);
    }

    public void setMiniPk(StringMap<ImageArrayBaseSixtyFour> _miniPk) {
        miniPk = _miniPk;
    }

    public void setMaxiPkBack(StringMap<ImageArrayBaseSixtyFour> _maxiPkBack) {
        maxiPkBack = _maxiPkBack;
    }

    public void setMaxiPkFront(StringMap<ImageArrayBaseSixtyFour> _maxiPkFront) {
        maxiPkFront = _maxiPkFront;
    }

    public void setMiniItems(StringMap<ImageArrayBaseSixtyFour> _miniItems) {
        miniItems = _miniItems;
    }

    public void setTrainers(StringMap<ImageArrayBaseSixtyFour> _trainers) {
        trainers = _trainers;
    }

    public void setPeople(StringMap<ImageArrayBaseSixtyFour> _people) {
        people = _people;
    }

    public void setTypesImages(StringMap<ImageArrayBaseSixtyFour> _typesImages) {
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

    public void setLinks(StringMap<ImageArrayBaseSixtyFour> _links) {
        links = _links;
    }

    public void setImages(StringMap<ImageArrayBaseSixtyFour> _images) {
        images = _images;
    }

    public StringMap<ScreenCoordssInt> getImagesTiles() {
        return imagesTiles;
    }

    public void setImagesTiles(StringMap<ScreenCoordssInt> _imagesTiles) {
        imagesTiles = _imagesTiles;
    }

    public void setMiniMap(StringMap<ImageArrayBaseSixtyFour> _miniMap) {
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

    public void defValues() {
        MessagesDataBaseConstants.init(this);
    }
    public String prefixBoost() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,boost());
    }
    public String prefixAttack() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,attack());
    }
    public String prefixDefense() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,defense());
    }
    public String prefixPower() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,power());
    }
    public String prefixNiveau() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,niveau());
    }
    public String prefixLevelLooser() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,levelLooser());
    }
    public String prefixLevelWinner() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,levelWinner());
    }
    public String prefixImmuTypeAttCombattantEntrant(String _e) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,immuTypeAttCombattantEntrant(),SEP_BETWEEN_KEYS,_e);
    }
    public String prefixPasAttaqueInvoc() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasAttaqueInvoc());
    }
    public String prefixPasAttaquesCopiables() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasAttaquesCopiables());
    }
    public String prefixBaseCaptPk() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,baseCaptPk());
    }
    public String prefixRateBallStatus() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,rateBallStatus());
    }
    public String prefixFoePkMaxHp() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,foePkMaxHp());
    }
    public String prefixFoePkRemoteHp() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,foePkRemoteHp());
    }
    public String prefixAttaqueCategorie() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,attaqueCategorie());
    }
    public String prefixAttaqueNom() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,attaqueNom());
    }
    public String prefixAttaqueTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,attaqueTypes());
    }
    public String prefixPuissanceBase() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,puissanceBase());
    }
    public String prefixCoeffEffBaseTypesCible(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,coeffEffBaseTypesCible(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCoeffEffBaseTypesCombattantEntrant(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,coeffEffBaseTypesCombattantEntrant(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCoeffEffBaseTypesFighter(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,coeffEffBaseTypesFighter(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCoeffEffBaseTypesLanceur(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,coeffEffBaseTypesLanceur(),SEP_BETWEEN_KEYS,_c);
    }

    public String prefixLanceurNom() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurNom());
    }

    public String prefixLanceurNiveau() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurNiveau());
    }

    public String prefixLanceurGenre() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurGenre());
    }
    public String prefixLanceurAttaques() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurAttaques());
    }
    public String prefixLanceurAttaqueChoisie() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurAttaqueChoisie());
    }
    public String prefixLanceurAttaquesTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurAttaquesTypes());
    }
    public String prefixLanceurTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurTypes());
    }
    public String prefixLanceurDegatsRecusTotal() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurDegatsRecusTotal());
    }
    public String prefixLanceurDegatsRecusTotalTour() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurDegatsRecusTotalTour());
    }
    public String prefixLanceurClone() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurClone());
    }
    public String prefixLanceurDisparait() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurDisparait());
    }
    public String prefixLanceurMasse() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurMasse());
    }
    public String prefixLanceurTaille() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurTaille());
    }
    public String prefixLanceurObjet() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurObjet());
    }
    public String prefixLanceurCapacite() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurCapacite());
    }
    public String prefixLanceurBonheur() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurBonheur());
    }
    public String prefixLanceurNbUtilisation(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurNbUtilisation(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixLanceurDegatsRecus(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurDegatsRecus(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixLanceurDegatsRecusTour(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurDegatsRecusTour(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixLanceurPp(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurPp(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixLanceurPvRestants() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurPvRestants());
    }
    public String prefixLanceurPvMax() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurPvMax());
    }
    public String prefixSommeBoostPosLanceur() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,sommeBoostPosLanceur());
    }
    public String prefixNbKoEquipeAdvLanceur() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbKoEquipeAdvLanceur());
    }
    public String prefixNbKoEquipeLanceur() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbKoEquipeLanceur());
    }
    public String prefixLanceurDerJoue() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurDerJoue());
    }
    public String prefixLanceurJoue() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurJoue());
    }
    public String prefixLanceurStatuts() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurStatuts());
    }
    public String prefixLanceurBoost(Statistic _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurBoost(),SEP_BETWEEN_KEYS,_c.getStatName());
    }
    public String prefixLanceurStatis(Statistic _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurStatis(),SEP_BETWEEN_KEYS,_c.getStatName());
    }
    public String prefixLanceurEffet(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lanceurEffet(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixTypesAttaquesResVide() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,typesAttaquesResVide());
    }
    public String prefixPasTourTerrain() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasTourTerrain());
    }
    public String prefixPasPartenaire() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasPartenaire());
    }
    public String prefixPasPartenaireArriere() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasPartenaireArriere());
    }
    public String prefixPasPartenaireTerrain() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasPartenaireTerrain());
    }
    public String prefixCibleNom() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleNom());
    }
    public String prefixCibleNiveau() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleNiveau());
    }

    public String prefixCibleGenre() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleGenre());
    }
    public String prefixCibleAttaques() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleAttaques());
    }
    public String prefixCibleAttaqueChoisie() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleAttaqueChoisie());
    }
    public String prefixCibleAttaquesTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleAttaquesTypes());
    }
    public String prefixCibleTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleTypes());
    }
    public String prefixCibleDegatsRecusTotal() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleDegatsRecusTotal());
    }
    public String prefixCibleDegatsRecusTotalTour() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleDegatsRecusTotalTour());
    }
    public String prefixCibleClone() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleClone());
    }
    public String prefixCibleDisparait() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleDisparait());
    }
    public String prefixCibleMasse() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleMasse());
    }
    public String prefixCibleTaille() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleTaille());
    }
    public String prefixCibleObjet() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleObjet());
    }
    public String prefixCibleCapacite() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleCapacite());
    }
    public String prefixCibleBonheur() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleBonheur());
    }
    public String prefixCibleNbUtilisation(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleNbUtilisation(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCibleDegatsRecus(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleDegatsRecus(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCibleDegatsRecusTour(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleDegatsRecusTour(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCiblePp(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,ciblePp(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCiblePvRestants() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,ciblePvRestants());
    }
    public String prefixCiblePvMax() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,ciblePvMax());
    }
    public String prefixSommeBoostPosCible() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,sommeBoostPosCible());
    }
    public String prefixNbKoEquipeAdvCible() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbKoEquipeAdvCible());
    }
    public String prefixNbKoEquipeCible() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbKoEquipeCible());
    }
    public String prefixCibleDerJoue() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleDerJoue());
    }
    public String prefixCibleJoue() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleJoue());
    }
    public String prefixCibleStatuts() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleStatuts());
    }
    public String prefixCibleBoost(Statistic _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleBoost(),SEP_BETWEEN_KEYS,_c.getStatName());
    }
    public String prefixCibleStatis(Statistic _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleStatis(),SEP_BETWEEN_KEYS,_c.getStatName());
    }
    public String prefixCibleEffet(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,cibleEffet(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCiblePossedeStatutRelation(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,ciblePossedeStatutRelation(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixImmuTypeAttCible(String _e) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,immuTypeAttCible(),SEP_BETWEEN_KEYS,_e);
    }
    public String prefixPasPpAttaqueCible() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasPpAttaqueCible());
    }
    public String prefixPasUtilisAttaqueCible() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pasUtilisAttaqueCible());
    }
    public String prefixRateEffMoveAgainstTarget() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,rateEffMoveAgainstTarget());
    }
    public String prefixAucunBoostPossible() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,aucunBoostPossible());
    }
    public String prefixFighterNom() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterNom());
    }
    public String prefixFighterNiveau() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterNiveau());
    }

    public String prefixFighterGenre() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterGenre());
    }
    public String prefixFighterAttaques() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterAttaques());
    }
    public String prefixFighterAttaqueChoisie() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterAttaqueChoisie());
    }
    public String prefixFighterAttaquesTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterAttaquesTypes());
    }
    public String prefixFighterTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterTypes());
    }
    public String prefixFighterDegatsRecusTotal() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterDegatsRecusTotal());
    }
    public String prefixFighterDegatsRecusTotalTour() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterDegatsRecusTotalTour());
    }
    public String prefixFighterClone() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterClone());
    }
    public String prefixFighterDisparait() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterDisparait());
    }
    public String prefixFighterMasse() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterMasse());
    }
    public String prefixFighterTaille() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterTaille());
    }
    public String prefixFighterObjet() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterObjet());
    }
    public String prefixFighterCapacite() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterCapacite());
    }
    public String prefixFighterBonheur() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterBonheur());
    }
    public String prefixFighterNbUtilisation(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterNbUtilisation(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixFighterDegatsRecus(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterDegatsRecus(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixFighterDegatsRecusTour(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterDegatsRecusTour(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixFighterPp(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterPp(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixFighterPvRestants() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterPvRestants());
    }
    public String prefixFighterPvMax() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterPvMax());
    }
    public String prefixSommeBoostPosFighter() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,sommeBoostPosFighter());
    }
    public String prefixNbKoEquipeAdvFighter() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbKoEquipeAdvFighter());
    }
    public String prefixNbKoEquipeFighter() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbKoEquipeFighter());
    }
    public String prefixFighterDerJoue() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterDerJoue());
    }
    public String prefixFighterJoue() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterJoue());
    }
    public String prefixFighterStatuts() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterStatuts());
    }
    public String prefixFighterBoost(Statistic _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterBoost(),SEP_BETWEEN_KEYS,_c.getStatName());
    }
    public String prefixFighterStatis(Statistic _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,fighterStatis(),SEP_BETWEEN_KEYS,_c.getStatName());
    }
    public String prefixNbTour(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbTour(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixNbTourGlobal(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbTourGlobal(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixEquipeNbUtilisation(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,equipeNbUtilisation(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixEquipeAdvNbUtilisation(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,equipeAdvNbUtilisation(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixEquipeAdvCombattantEntrantNbUtilisation(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,equipeAdvCombattantEntrantNbUtilisation(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixCombattantEntrantClone() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,combattantEntrantClone());
    }
    public String prefixCombattantEntrantTypes() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,combattantEntrantTypes());
    }
    public String prefixNbUtiliAttEqTour(String _c) {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbUtiliAttEqTour(),SEP_BETWEEN_KEYS,_c);
    }
    public String prefixClimats() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,climats());
    }
    public String prefixLieuCombat() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,lieuCombat());
    }
    public String prefixTempsTour() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,tempsTour());
    }
    public String prefixNbCombattantsTerrain() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbCombattantsTerrain());
    }
    public String prefixCoeffEff() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,coeffEff());
    }
    public String prefixNbUtilisationConsecutif() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbUtilisationConsecutif());
    }
    public String prefixExisteGenreAssexue() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,existeGenreAssexue());
    }
    public String prefixGenresEgaux() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,genresEgaux());
    }
    public String prefixDejaCapture() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,dejaCapture());
    }
    public String prefixNbFlees() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,nbFlees());
    }
    public String prefixPkUtNiveau() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkUtNiveau());
    }
    public String prefixPkUtGenre() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkUtGenre());
    }
    public String prefixPkUtVitesse() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkUtVitesse());
    }
    public String prefixPkUtTypesBase() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkUtTypesBase());
    }
    public String prefixPkUtPierresEvos() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkUtPierresEvos());
    }
    public String prefixPkUtMasse() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkUtMasse());
    }
    public String prefixPkSauvageNiveau() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkSauvageNiveau());
    }
    public String prefixPkSauvageGenre() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkSauvageGenre());
    }
    public String prefixPkSauvageVitesse() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkSauvageVitesse());
    }
    public String prefixPkSauvageTypesBase() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkSauvageTypesBase());
    }
    public String prefixPkSauvagePierresEvos() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkSauvagePierresEvos());
    }
    public String prefixPkSauvageMasse() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,pkSauvageMasse());
    }
    public String prefixMasseMoyennePk() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS,masseMoyennePk());
    }
    public String prefixVar() {
        return getConstNonNum().getPrefixVar();
    }

    public void prefixVar(String _p) {
        this.getConstNonNum().setPrefixVar(_p);
    }

    public String niveau() {
        return getConstNonNum().getNiveau();
    }

    public void niveau(String _p) {
        this.getConstNonNum().setNiveau(_p);
    }

    public String levelLooser() {
        return getConstNonNum().getLevelLooser();
    }

    public void levelLooser(String _p) {
        this.getConstNonNum().setLevelLooser(_p);
    }

    public String levelWinner() {
        return getConstNonNum().getLevelWinner();
    }

    public void levelWinner(String _p) {
        this.getConstNonNum().setLevelWinner(_p);
    }

    public String fighterNiveau() {
        return getConstNonNum().getFighterNiveau();
    }

    public void fighterNiveau(String _p) {
        this.getConstNonNum().setFighterNiveau(_p);
    }

    public String cibleNiveau() {
        return getConstNonNum().getCibleNiveau();
    }

    public void cibleNiveau(String _p) {
        this.getConstNonNum().setCibleNiveau(_p);
    }

    public String lanceurNiveau() {
        return getConstNonNum().getLanceurNiveau();
    }

    public void lanceurNiveau(String _p) {
        this.getConstNonNum().setLanceurNiveau(_p);
    }

    public String pkSauvageNiveau() {
        return getConstNonNum().getPkSauvageNiveau();
    }

    public void pkSauvageNiveau(String _p) {
        this.getConstNonNum().setPkSauvageNiveau(_p);
    }

    public String pkUtNiveau() {
        return getConstNonNum().getPkUtNiveau();
    }

    public void pkUtNiveau(String _p) {
        this.getConstNonNum().setPkUtNiveau(_p);
    }

    public String boost() {
        return getConstNonNum().getBoost();
    }

    public void boost(String _p) {
        this.getConstNonNum().setBoost(_p);
    }

    public String power() {
        return getConstNonNum().getPower();
    }

    public void power(String _p) {
        this.getConstNonNum().setPower(_p);
    }

    public String attack() {
        return getConstNonNum().getAttack();
    }

    public void attack(String _p) {
        this.getConstNonNum().setAttack(_p);
    }

    public String defense() {
        return getConstNonNum().getDefense();
    }

    public void defense(String _p) {
        this.getConstNonNum().setDefense(_p);
    }

    public String baseCaptPk() {
        return getConstNonNum().getBaseCaptPk();
    }

    public void baseCaptPk(String _p) {
        this.getConstNonNum().setBaseCaptPk(_p);
    }

    public String rateBallStatus() {
        return getConstNonNum().getRateBallStatus();
    }

    public void rateBallStatus(String _p) {
        this.getConstNonNum().setRateBallStatus(_p);
    }

    public String foePkMaxHp() {
        return getConstNonNum().getFoePkMaxHp();
    }

    public void foePkMaxHp(String _p) {
        this.getConstNonNum().setFoePkMaxHp(_p);
    }

    public String foePkRemoteHp() {
        return getConstNonNum().getFoePkRemoteHp();
    }

    public void foePkRemoteHp(String _p) {
        this.getConstNonNum().setFoePkRemoteHp(_p);
    }

    public String cibleNbUtilisation() {
        return getConstNonNum().getCibleNbUtilisation();
    }

    public void cibleNbUtilisation(String _p) {
        this.getConstNonNum().setCibleNbUtilisation(_p);
    }

    public String fighterNbUtilisation() {
        return getConstNonNum().getFighterNbUtilisation();
    }

    public void fighterNbUtilisation(String _p) {
        this.getConstNonNum().setFighterNbUtilisation(_p);
    }

    public String lanceurNbUtilisation() {
        return getConstNonNum().getLanceurNbUtilisation();
    }

    public void lanceurNbUtilisation(String _p) {
        this.getConstNonNum().setLanceurNbUtilisation(_p);
    }

    public String ciblePp() {
        return getConstNonNum().getCiblePp();
    }

    public void ciblePp(String _p) {
        this.getConstNonNum().setCiblePp(_p);
    }

    public String fighterPp() {
        return getConstNonNum().getFighterPp();
    }

    public void fighterPp(String _p) {
        this.getConstNonNum().setFighterPp(_p);
    }

    public String lanceurPp() {
        return getConstNonNum().getLanceurPp();
    }

    public void lanceurPp(String _p) {
        this.getConstNonNum().setLanceurPp(_p);
    }

    public String ciblePvRestants() {
        return getConstNonNum().getCiblePvRestants();
    }

    public void ciblePvRestants(String _p) {
        this.getConstNonNum().setCiblePvRestants(_p);
    }

    public String fighterPvRestants() {
        return getConstNonNum().getFighterPvRestants();
    }

    public void fighterPvRestants(String _p) {
        this.getConstNonNum().setFighterPvRestants(_p);
    }

    public String lanceurPvRestants() {
        return getConstNonNum().getLanceurPvRestants();
    }

    public void lanceurPvRestants(String _p) {
        this.getConstNonNum().setLanceurPvRestants(_p);
    }

    public String ciblePvMax() {
        return getConstNonNum().getCiblePvMax();
    }

    public void ciblePvMax(String _p) {
        this.getConstNonNum().setCiblePvMax(_p);
    }

    public String fighterPvMax() {
        return getConstNonNum().getFighterPvMax();
    }

    public void fighterPvMax(String _p) {
        this.getConstNonNum().setFighterPvMax(_p);
    }

    public String lanceurPvMax() {
        return getConstNonNum().getLanceurPvMax();
    }

    public void lanceurPvMax(String _p) {
        this.getConstNonNum().setLanceurPvMax(_p);
    }

    public String cibleStatis() {
        return getConstNonNum().getCibleStatis();
    }

    public void cibleStatis(String _p) {
        this.getConstNonNum().setCibleStatis(_p);
    }

    public String fighterStatis() {
        return getConstNonNum().getFighterStatis();
    }

    public void fighterStatis(String _p) {
        this.getConstNonNum().setFighterStatis(_p);
    }

    public String lanceurStatis() {
        return getConstNonNum().getLanceurStatis();
    }

    public void lanceurStatis(String _p) {
        this.getConstNonNum().setLanceurStatis(_p);
    }

    public String cibleBoost() {
        return getConstNonNum().getCibleBoost();
    }

    public void cibleBoost(String _p) {
        this.getConstNonNum().setCibleBoost(_p);
    }

    public String fighterBoost() {
        return getConstNonNum().getFighterBoost();
    }

    public void fighterBoost(String _p) {
        this.getConstNonNum().setFighterBoost(_p);
    }

    public String lanceurBoost() {
        return getConstNonNum().getLanceurBoost();
    }

    public void lanceurBoost(String _p) {
        this.getConstNonNum().setLanceurBoost(_p);
    }

    public String cibleEffet() {
        return getConstNonNum().getCibleEffet();
    }

    public void cibleEffet(String _p) {
        this.getConstNonNum().setCibleEffet(_p);
    }

    public String lanceurEffet() {
        return getConstNonNum().getLanceurEffet();
    }

    public void lanceurEffet(String _p) {
        this.getConstNonNum().setLanceurEffet(_p);
    }

    public String equipeNbUtilisation() {
        return getConstNonNum().getEquipeNbUtilisation();
    }

    public void equipeNbUtilisation(String _p) {
        this.getConstNonNum().setEquipeNbUtilisation(_p);
    }

    public String equipeAdvNbUtilisation() {
        return getConstNonNum().getEquipeAdvNbUtilisation();
    }

    public void equipeAdvNbUtilisation(String _p) {
        this.getConstNonNum().setEquipeAdvNbUtilisation(_p);
    }

    public String coeffEffBaseTypesCombattantEntrant() {
        return getConstNonNum().getCoeffEffBaseTypesCombattantEntrant();
    }

    public void coeffEffBaseTypesCombattantEntrant(String _p) {
        this.getConstNonNum().setCoeffEffBaseTypesCombattantEntrant(_p);
    }

    public String coeffEffBaseTypesCible() {
        return getConstNonNum().getCoeffEffBaseTypesCible();
    }

    public void coeffEffBaseTypesCible(String _p) {
        this.getConstNonNum().setCoeffEffBaseTypesCible(_p);
    }

    public String coeffEffBaseTypesFighter() {
        return getConstNonNum().getCoeffEffBaseTypesFighter();
    }

    public void coeffEffBaseTypesFighter(String _p) {
        this.getConstNonNum().setCoeffEffBaseTypesFighter(_p);
    }

    public String coeffEffBaseTypesLanceur() {
        return getConstNonNum().getCoeffEffBaseTypesLanceur();
    }

    public void coeffEffBaseTypesLanceur(String _p) {
        this.getConstNonNum().setCoeffEffBaseTypesLanceur(_p);
    }

    public String sommeBoostPosCible() {
        return getConstNonNum().getSommeBoostPosCible();
    }

    public void sommeBoostPosCible(String _p) {
        this.getConstNonNum().setSommeBoostPosCible(_p);
    }

    public String sommeBoostPosFighter() {
        return getConstNonNum().getSommeBoostPosFighter();
    }

    public void sommeBoostPosFighter(String _p) {
        this.getConstNonNum().setSommeBoostPosFighter(_p);
    }

    public String sommeBoostPosLanceur() {
        return getConstNonNum().getSommeBoostPosLanceur();
    }

    public void sommeBoostPosLanceur(String _p) {
        this.getConstNonNum().setSommeBoostPosLanceur(_p);
    }

    public String immuTypeAttCombattantEntrant() {
        return getConstNonNum().getImmuTypeAttCombattantEntrant();
    }

    public void immuTypeAttCombattantEntrant(String _p) {
        this.getConstNonNum().setImmuTypeAttCombattantEntrant(_p);
    }

    public String immuTypeAttCible() {
        return getConstNonNum().getImmuTypeAttCible();
    }

    public void immuTypeAttCible(String _p) {
        this.getConstNonNum().setImmuTypeAttCible(_p);
    }

    public String equipeAdvCombattantEntrantNbUtilisation() {
        return getConstNonNum().getEquipeAdvCombattantEntrantNbUtilisation();
    }

    public void equipeAdvCombattantEntrantNbUtilisation(String _p) {
        this.getConstNonNum().setEquipeAdvCombattantEntrantNbUtilisation(_p);
    }

    public String nbTourGlobal() {
        return getConstNonNum().getNbTourGlobal();
    }

    public void nbTourGlobal(String _p) {
        this.getConstNonNum().setNbTourGlobal(_p);
    }

    public String nbUtiliAttEqTour() {
        return getConstNonNum().getNbUtiliAttEqTour();
    }

    public void nbUtiliAttEqTour(String _p) {
        this.getConstNonNum().setNbUtiliAttEqTour(_p);
    }
    public String cibleAttaques(){
        return getConstNonNum().getCibleAttaques();
    }
    public void cibleAttaques(String _p){
        getConstNonNum().setCibleAttaques(_p);
    }
    public String cibleAttaqueChoisie(){
        return getConstNonNum().getCibleAttaqueChoisie();
    }
    public void cibleAttaqueChoisie(String _p){
        getConstNonNum().setCibleAttaqueChoisie(_p);
    }
    public String cibleAttaquesTypes(){
        return getConstNonNum().getCibleAttaquesTypes();
    }
    public void cibleAttaquesTypes(String _p){
        getConstNonNum().setCibleAttaquesTypes(_p);
    }
    public String cibleClone(){
        return getConstNonNum().getCibleClone();
    }
    public void cibleClone(String _p){
        getConstNonNum().setCibleClone(_p);
    }
    public String cibleDegatsRecus(){
        return getConstNonNum().getCibleDegatsRecus();
    }
    public void cibleDegatsRecus(String _p){
        getConstNonNum().setCibleDegatsRecus(_p);
    }
    public String cibleDegatsRecusTotal(){
        return getConstNonNum().getCibleDegatsRecusTotal();
    }
    public void cibleDegatsRecusTotal(String _p){
        getConstNonNum().setCibleDegatsRecusTotal(_p);
    }
    public String cibleDegatsRecusTour(){
        return getConstNonNum().getCibleDegatsRecusTour();
    }
    public void cibleDegatsRecusTour(String _p){
        getConstNonNum().setCibleDegatsRecusTour(_p);
    }
    public String cibleDegatsRecusTotalTour(){
        return getConstNonNum().getCibleDegatsRecusTotalTour();
    }
    public void cibleDegatsRecusTotalTour(String _p){
        getConstNonNum().setCibleDegatsRecusTotalTour(_p);
    }
    public String cibleDisparait(){
        return getConstNonNum().getCibleDisparait();
    }
    public void cibleDisparait(String _p){
        getConstNonNum().setCibleDisparait(_p);
    }
    public String cibleJoue(){
        return getConstNonNum().getCibleJoue();
    }
    public void cibleJoue(String _p){
        getConstNonNum().setCibleJoue(_p);
    }
    public String cibleMasse(){
        return getConstNonNum().getCibleMasse();
    }
    public void cibleMasse(String _p){
        getConstNonNum().setCibleMasse(_p);
    }
    public String cibleTaille(){
        return getConstNonNum().getCibleTaille();
    }
    public void cibleTaille(String _p){
        getConstNonNum().setCibleTaille(_p);
    }
    public String cibleCapacite(){
        return getConstNonNum().getCibleCapacite();
    }
    public void cibleCapacite(String _p){
        getConstNonNum().setCibleCapacite(_p);
    }
    public String cibleObjet(){
        return getConstNonNum().getCibleObjet();
    }
    public void cibleObjet(String _p){
        getConstNonNum().setCibleObjet(_p);
    }
    public String cibleStatuts(){
        return getConstNonNum().getCibleStatuts();
    }
    public void cibleStatuts(String _p){
        getConstNonNum().setCibleStatuts(_p);
    }
    public String cibleTypes(){
        return getConstNonNum().getCibleTypes();
    }
    public void cibleTypes(String _p){
        getConstNonNum().setCibleTypes(_p);
    }
    public String cibleGenre(){
        return getConstNonNum().getCibleGenre();
    }
    public void cibleGenre(String _p){
        getConstNonNum().setCibleGenre(_p);
    }
    public String cibleBonheur(){
        return getConstNonNum().getCibleBonheur();
    }
    public void cibleBonheur(String _p){
        getConstNonNum().setCibleBonheur(_p);
    }
    public String cibleNom(){
        return getConstNonNum().getCibleNom();
    }
    public void cibleNom(String _p){
        getConstNonNum().setCibleNom(_p);
    }
    public String cibleDerJoue(){
        return getConstNonNum().getCibleDerJoue();
    }
    public void cibleDerJoue(String _p){
        getConstNonNum().setCibleDerJoue(_p);
    }
    public String ciblePossedeStatutRelation(){
        return getConstNonNum().getCiblePossedeStatutRelation();
    }
    public void ciblePossedeStatutRelation(String _p){
        getConstNonNum().setCiblePossedeStatutRelation(_p);
    }
    public String nbKoEquipeCible(){
        return getConstNonNum().getNbKoEquipeCible();
    }
    public void nbKoEquipeCible(String _p){
        getConstNonNum().setNbKoEquipeCible(_p);
    }
    public String nbKoEquipeAdvCible(){
        return getConstNonNum().getNbKoEquipeAdvCible();
    }
    public void nbKoEquipeAdvCible(String _p){
        getConstNonNum().setNbKoEquipeAdvCible(_p);
    }
    public String pasPpAttaqueCible(){
        return getConstNonNum().getPasPpAttaqueCible();
    }
    public void pasPpAttaqueCible(String _p){
        getConstNonNum().setPasPpAttaqueCible(_p);
    }
    public String pasUtilisAttaqueCible(){
        return getConstNonNum().getPasUtilisAttaqueCible();
    }
    public void pasUtilisAttaqueCible(String _p){
        getConstNonNum().setPasUtilisAttaqueCible(_p);
    }
    public String lanceurAttaques(){
        return getConstNonNum().getLanceurAttaques();
    }
    public void lanceurAttaques(String _p){
        getConstNonNum().setLanceurAttaques(_p);
    }
    public String lanceurAttaqueChoisie(){
        return getConstNonNum().getLanceurAttaqueChoisie();
    }
    public void lanceurAttaqueChoisie(String _p){
        getConstNonNum().setLanceurAttaqueChoisie(_p);
    }
    public String lanceurAttaquesTypes(){
        return getConstNonNum().getLanceurAttaquesTypes();
    }
    public void lanceurAttaquesTypes(String _p){
        getConstNonNum().setLanceurAttaquesTypes(_p);
    }
    public String lanceurClone(){
        return getConstNonNum().getLanceurClone();
    }
    public void lanceurClone(String _p){
        getConstNonNum().setLanceurClone(_p);
    }
    public String lanceurDegatsRecus(){
        return getConstNonNum().getLanceurDegatsRecus();
    }
    public void lanceurDegatsRecus(String _p){
        getConstNonNum().setLanceurDegatsRecus(_p);
    }
    public String lanceurDegatsRecusTotal(){
        return getConstNonNum().getLanceurDegatsRecusTotal();
    }
    public void lanceurDegatsRecusTotal(String _p){
        getConstNonNum().setLanceurDegatsRecusTotal(_p);
    }
    public String lanceurDegatsRecusTour(){
        return getConstNonNum().getLanceurDegatsRecusTour();
    }
    public void lanceurDegatsRecusTour(String _p){
        getConstNonNum().setLanceurDegatsRecusTour(_p);
    }
    public String lanceurDegatsRecusTotalTour(){
        return getConstNonNum().getLanceurDegatsRecusTotalTour();
    }
    public void lanceurDegatsRecusTotalTour(String _p){
        getConstNonNum().setLanceurDegatsRecusTotalTour(_p);
    }
    public String lanceurDisparait(){
        return getConstNonNum().getLanceurDisparait();
    }
    public void lanceurDisparait(String _p){
        getConstNonNum().setLanceurDisparait(_p);
    }
    public String lanceurJoue(){
        return getConstNonNum().getLanceurJoue();
    }
    public void lanceurJoue(String _p){
        getConstNonNum().setLanceurJoue(_p);
    }
    public String lanceurMasse(){
        return getConstNonNum().getLanceurMasse();
    }
    public void lanceurMasse(String _p){
        getConstNonNum().setLanceurMasse(_p);
    }
    public String lanceurTaille(){
        return getConstNonNum().getLanceurTaille();
    }
    public void lanceurTaille(String _p){
        getConstNonNum().setLanceurTaille(_p);
    }
    public String lanceurCapacite(){
        return getConstNonNum().getLanceurCapacite();
    }
    public void lanceurCapacite(String _p){
        getConstNonNum().setLanceurCapacite(_p);
    }
    public String lanceurObjet(){
        return getConstNonNum().getLanceurObjet();
    }
    public void lanceurObjet(String _p){
        getConstNonNum().setLanceurObjet(_p);
    }
    public String lanceurStatuts(){
        return getConstNonNum().getLanceurStatuts();
    }
    public void lanceurStatuts(String _p){
        getConstNonNum().setLanceurStatuts(_p);
    }
    public String lanceurTypes(){
        return getConstNonNum().getLanceurTypes();
    }
    public void lanceurTypes(String _p){
        getConstNonNum().setLanceurTypes(_p);
    }
    public String lanceurGenre(){
        return getConstNonNum().getLanceurGenre();
    }
    public void lanceurGenre(String _p){
        getConstNonNum().setLanceurGenre(_p);
    }
    public String lanceurBonheur(){
        return getConstNonNum().getLanceurBonheur();
    }
    public void lanceurBonheur(String _p){
        getConstNonNum().setLanceurBonheur(_p);
    }
    public String lanceurNom(){
        return getConstNonNum().getLanceurNom();
    }
    public void lanceurNom(String _p){
        getConstNonNum().setLanceurNom(_p);
    }
    public String lanceurDerJoue(){
        return getConstNonNum().getLanceurDerJoue();
    }
    public void lanceurDerJoue(String _p){
        getConstNonNum().setLanceurDerJoue(_p);
    }
    public String nbKoEquipeLanceur(){
        return getConstNonNum().getNbKoEquipeLanceur();
    }
    public void nbKoEquipeLanceur(String _p){
        getConstNonNum().setNbKoEquipeLanceur(_p);
    }
    public String nbKoEquipeAdvLanceur(){
        return getConstNonNum().getNbKoEquipeAdvLanceur();
    }
    public void nbKoEquipeAdvLanceur(String _p){
        getConstNonNum().setNbKoEquipeAdvLanceur(_p);
    }
    public String fighterAttaques(){
        return getConstNonNum().getFighterAttaques();
    }
    public void fighterAttaques(String _p){
        getConstNonNum().setFighterAttaques(_p);
    }
    public String fighterAttaqueChoisie(){
        return getConstNonNum().getFighterAttaqueChoisie();
    }
    public void fighterAttaqueChoisie(String _p){
        getConstNonNum().setFighterAttaqueChoisie(_p);
    }
    public String fighterAttaquesTypes(){
        return getConstNonNum().getFighterAttaquesTypes();
    }
    public void fighterAttaquesTypes(String _p){
        getConstNonNum().setFighterAttaquesTypes(_p);
    }
    public String fighterClone(){
        return getConstNonNum().getFighterClone();
    }
    public void fighterClone(String _p){
        getConstNonNum().setFighterClone(_p);
    }
    public String fighterDegatsRecus(){
        return getConstNonNum().getFighterDegatsRecus();
    }
    public void fighterDegatsRecus(String _p){
        getConstNonNum().setFighterDegatsRecus(_p);
    }
    public String fighterDegatsRecusTotal(){
        return getConstNonNum().getFighterDegatsRecusTotal();
    }
    public void fighterDegatsRecusTotal(String _p){
        getConstNonNum().setFighterDegatsRecusTotal(_p);
    }
    public String fighterDegatsRecusTour(){
        return getConstNonNum().getFighterDegatsRecusTour();
    }
    public void fighterDegatsRecusTour(String _p){
        getConstNonNum().setFighterDegatsRecusTour(_p);
    }
    public String fighterDegatsRecusTotalTour(){
        return getConstNonNum().getFighterDegatsRecusTotalTour();
    }
    public void fighterDegatsRecusTotalTour(String _p){
        getConstNonNum().setFighterDegatsRecusTotalTour(_p);
    }
    public String fighterDisparait(){
        return getConstNonNum().getFighterDisparait();
    }
    public void fighterDisparait(String _p){
        getConstNonNum().setFighterDisparait(_p);
    }
    public String fighterJoue(){
        return getConstNonNum().getFighterJoue();
    }
    public void fighterJoue(String _p){
        getConstNonNum().setFighterJoue(_p);
    }
    public String fighterMasse(){
        return getConstNonNum().getFighterMasse();
    }
    public void fighterMasse(String _p){
        getConstNonNum().setFighterMasse(_p);
    }
    public String fighterTaille(){
        return getConstNonNum().getFighterTaille();
    }
    public void fighterTaille(String _p){
        getConstNonNum().setFighterTaille(_p);
    }
    public String fighterCapacite(){
        return getConstNonNum().getFighterCapacite();
    }
    public void fighterCapacite(String _p){
        getConstNonNum().setFighterCapacite(_p);
    }
    public String fighterObjet(){
        return getConstNonNum().getFighterObjet();
    }
    public void fighterObjet(String _p){
        getConstNonNum().setFighterObjet(_p);
    }
    public String fighterStatuts(){
        return getConstNonNum().getFighterStatuts();
    }
    public void fighterStatuts(String _p){
        getConstNonNum().setFighterStatuts(_p);
    }
    public String fighterTypes(){
        return getConstNonNum().getFighterTypes();
    }
    public void fighterTypes(String _p){
        getConstNonNum().setFighterTypes(_p);
    }
    public String fighterGenre(){
        return getConstNonNum().getFighterGenre();
    }
    public void fighterGenre(String _p){
        getConstNonNum().setFighterGenre(_p);
    }
    public String fighterBonheur(){
        return getConstNonNum().getFighterBonheur();
    }
    public void fighterBonheur(String _p){
        getConstNonNum().setFighterBonheur(_p);
    }
    public String fighterNom(){
        return getConstNonNum().getFighterNom();
    }
    public void fighterNom(String _p){
        getConstNonNum().setFighterNom(_p);
    }
    public String fighterDerJoue(){
        return getConstNonNum().getFighterDerJoue();
    }
    public void fighterDerJoue(String _p){
        getConstNonNum().setFighterDerJoue(_p);
    }
    public String nbKoEquipeFighter(){
        return getConstNonNum().getNbKoEquipeFighter();
    }
    public void nbKoEquipeFighter(String _p){
        getConstNonNum().setNbKoEquipeFighter(_p);
    }
    public String nbKoEquipeAdvFighter(){
        return getConstNonNum().getNbKoEquipeAdvFighter();
    }
    public void nbKoEquipeAdvFighter(String _p){
        getConstNonNum().setNbKoEquipeAdvFighter(_p);
    }

    public String pkSauvageGenre() {
        return getConstNonNum().getPkSauvageGenre();
    }

    public void pkSauvageGenre(String _p) {
        this.getConstNonNum().setPkSauvageGenre(_p);
    }

    public String pkSauvageMasse() {
        return getConstNonNum().getPkSauvageMasse();
    }

    public void pkSauvageMasse(String _p) {
        this.getConstNonNum().setPkSauvageMasse(_p);
    }

    public String pkSauvageVitesse() {
        return getConstNonNum().getPkSauvageVitesse();
    }

    public void pkSauvageVitesse(String _p) {
        this.getConstNonNum().setPkSauvageVitesse(_p);
    }

    public String pkSauvageTypesBase() {
        return getConstNonNum().getPkSauvageTypesBase();
    }

    public void pkSauvageTypesBase(String _p) {
        this.getConstNonNum().setPkSauvageTypesBase(_p);
    }

    public String pkSauvagePierresEvos() {
        return getConstNonNum().getPkSauvagePierresEvos();
    }

    public void pkSauvagePierresEvos(String _p) {
        this.getConstNonNum().setPkSauvagePierresEvos(_p);
    }

    public String pkUtGenre() {
        return getConstNonNum().getPkUtGenre();
    }

    public void pkUtGenre(String _p) {
        this.getConstNonNum().setPkUtGenre(_p);
    }

    public String pkUtMasse() {
        return getConstNonNum().getPkUtMasse();
    }

    public void pkUtMasse(String _p) {
        this.getConstNonNum().setPkUtMasse(_p);
    }

    public String pkUtVitesse() {
        return getConstNonNum().getPkUtVitesse();
    }

    public void pkUtVitesse(String _p) {
        this.getConstNonNum().setPkUtVitesse(_p);
    }

    public String pkUtTypesBase() {
        return getConstNonNum().getPkUtTypesBase();
    }

    public void pkUtTypesBase(String _p) {
        this.getConstNonNum().setPkUtTypesBase(_p);
    }

    public String pkUtPierresEvos() {
        return getConstNonNum().getPkUtPierresEvos();
    }

    public void pkUtPierresEvos(String _p) {
        this.getConstNonNum().setPkUtPierresEvos(_p);
    }

    public String combattantEntrantClone() {
        return getConstNonNum().getCombattantEntrantClone();
    }

    public void combattantEntrantClone(String _p) {
        this.getConstNonNum().setCombattantEntrantClone(_p);
    }

    public String combattantEntrantTypes() {
        return getConstNonNum().getCombattantEntrantTypes();
    }

    public void combattantEntrantTypes(String _p) {
        this.getConstNonNum().setCombattantEntrantTypes(_p);
    }

    public String aucunBoostPossible() {
        return getConstNonNum().getAucunBoostPossible();
    }

    public void aucunBoostPossible(String _p) {
        this.getConstNonNum().setAucunBoostPossible(_p);
    }

    public String typesAttaquesResVide() {
        return getConstNonNum().getTypesAttaquesResVide();
    }

    public void typesAttaquesResVide(String _p) {
        this.getConstNonNum().setTypesAttaquesResVide(_p);
    }

    public String pasPartenaire() {
        return getConstNonNum().getPasPartenaire();
    }

    public void pasPartenaire(String _p) {
        this.getConstNonNum().setPasPartenaire(_p);
    }

    public String pasPartenaireArriere() {
        return getConstNonNum().getPasPartenaireArriere();
    }

    public void pasPartenaireArriere(String _p) {
        this.getConstNonNum().setPasPartenaireArriere(_p);
    }

    public String pasPartenaireTerrain() {
        return getConstNonNum().getPasPartenaireTerrain();
    }

    public void pasPartenaireTerrain(String _p) {
        this.getConstNonNum().setPasPartenaireTerrain(_p);
    }

    public String pasTourTerrain() {
        return getConstNonNum().getPasTourTerrain();
    }

    public void pasTourTerrain(String _p) {
        this.getConstNonNum().setPasTourTerrain(_p);
    }

    public String existeGenreAssexue() {
        return getConstNonNum().getExisteGenreAssexue();
    }

    public void existeGenreAssexue(String _p) {
        this.getConstNonNum().setExisteGenreAssexue(_p);
    }

    public String genresEgaux() {
        return getConstNonNum().getGenresEgaux();
    }

    public void genresEgaux(String _p) {
        this.getConstNonNum().setGenresEgaux(_p);
    }

    public String rateEffMoveAgainstTarget() {
        return getConstNonNum().getRateEffMoveAgainstTarget();
    }

    public void rateEffMoveAgainstTarget(String _p) {
        this.getConstNonNum().setRateEffMoveAgainstTarget(_p);
    }

    public String coeffEff() {
        return getConstNonNum().getCoeffEff();
    }

    public void coeffEff(String _p) {
        this.getConstNonNum().setCoeffEff(_p);
    }

    public String nbUtilisationConsecutif() {
        return getConstNonNum().getNbUtilisationConsecutif();
    }

    public void nbUtilisationConsecutif(String _p) {
        this.getConstNonNum().setNbUtilisationConsecutif(_p);
    }

    public String attaqueCategorie() {
        return getConstNonNum().getAttaqueCategorie();
    }

    public void attaqueCategorie(String _p) {
        this.getConstNonNum().setAttaqueCategorie(_p);
    }

    public String attaqueTypes() {
        return getConstNonNum().getAttaqueTypes();
    }

    public void attaqueTypes(String _p) {
        this.getConstNonNum().setAttaqueTypes(_p);
    }

    public String attaqueNom() {
        return getConstNonNum().getAttaqueNom();
    }

    public void attaqueNom(String _p) {
        this.getConstNonNum().setAttaqueNom(_p);
    }

    public String puissanceBase() {
        return getConstNonNum().getPuissanceBase();
    }

    public void puissanceBase(String _p) {
        this.getConstNonNum().setPuissanceBase(_p);
    }

    public String pasAttaqueInvoc() {
        return getConstNonNum().getPasAttaqueInvoc();
    }

    public void pasAttaqueInvoc(String _p) {
        this.getConstNonNum().setPasAttaqueInvoc(_p);
    }

    public String pasAttaquesCopiables() {
        return getConstNonNum().getPasAttaquesCopiables();
    }

    public void pasAttaquesCopiables(String _p) {
        this.getConstNonNum().setPasAttaquesCopiables(_p);
    }

    public String nbTour() {
        return getConstNonNum().getNbTour();
    }

    public void nbTour(String _p) {
        this.getConstNonNum().setNbTour(_p);
    }

    public String dejaCapture() {
        return getConstNonNum().getDejaCapture();
    }

    public void dejaCapture(String _p) {
        this.getConstNonNum().setDejaCapture(_p);
    }

    public String nbFlees() {
        return getConstNonNum().getNbFlees();
    }

    public void nbFlees(String _p) {
        this.getConstNonNum().setNbFlees(_p);
    }

    public String masseMoyennePk() {
        return getConstNonNum().getMasseMoyennePk();
    }

    public void masseMoyennePk(String _p) {
        this.getConstNonNum().setMasseMoyennePk(_p);
    }

    public String climats() {
        return getConstNonNum().getClimats();
    }

    public void climats(String _p) {
        this.getConstNonNum().setClimats(_p);
    }

    public String nbCombattantsTerrain() {
        return getConstNonNum().getNbCombattantsTerrain();
    }

    public void nbCombattantsTerrain(String _p) {
        this.getConstNonNum().setNbCombattantsTerrain(_p);
    }

    public String lieuCombat() {
        return getConstNonNum().getLieuCombat();
    }

    public void lieuCombat(String _p) {
        this.getConstNonNum().setLieuCombat(_p);
    }

    public String tempsTour() {
        return getConstNonNum().getTempsTour();
    }

    public void tempsTour(String _p) {
        this.getConstNonNum().setTempsTour(_p);
    }

    public String getRateBoostCriticalHit() {
        return rateBoostCriticalHit;
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

    public String getRateBoost() {
        return rateBoost;
    }

    public void setRateBoost(String _rateBoost) {
        rateBoost = _rateBoost;
    }

    public String getDamageFormula() {
        return damageFormula;
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

    public void setAnimAbsorb(ImageArrayBaseSixtyFour _a) {
        animAbsorb = _a;
    }

    public DataBaseConstants getConstNonNum() {
        return constNonNum;
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

//    public static String toUpperCase(String _string) {
//        int len_ = _string.length();
//        StringBuilder str_ = new StringBuilder(len_);
//        for (int i = 0; i < len_; i++) {
//            char curr_ = _string.charAt(i);
//            if (isLowerLetter(curr_)) {
//                int char_ = curr_ - NumberUtil.MIN_LOW + NumberUtil.MIN_UPP;
//                str_.append((char)char_);
//                continue;
//            }
//            str_.append(curr_);
//        }
//        return str_.toString();
//    }
//
//    private static boolean isLowerLetter(char _curr) {
//        return _curr >= NumberUtil.MIN_LOW && _curr <= NumberUtil.MIN_LOW + 25;
//    }

    public void setMessages(DataBase _other) {
        messagesPokemonPlayer = _other.messagesPokemonPlayer;
        messagesPlayer = _other.messagesPlayer;
        messagesFighter = _other.messagesFighter;
        messagesTeam = _other.messagesTeam;
        messagesFight = _other.messagesFight;
        messagesGame = _other.messagesGame;
    }

    public StringMap<String> getMessagesGame() {
        return messagesGame;
    }

    public void setMessagesGame(StringMap<String> _m) {
        messagesGame = _m;
    }

    public StringMap<String> getMessagesPokemonPlayer() {
        return messagesPokemonPlayer;
    }

    public void setMessagesPokemonPlayer(StringMap<String> _m) {
        messagesPokemonPlayer = _m;
    }
    public StringMap<String> getMessagesFight() {
        return messagesFight;
    }

    public void setMessagesFight(StringMap<String> _m) {
        messagesFight = _m;
    }

    public StringMap<String> getMessagesPlayer() {
        return messagesPlayer;
    }

    public void setMessagesPlayer(StringMap<String> _m) {
        messagesPlayer = _m;
    }

    public StringMap<String> getMessagesFighter() {
        return messagesFighter;
    }

    public void setMessagesFighter(StringMap<String> _m) {
        messagesFighter = _m;
    }

    public StringMap<String> getMessagesTeam() {
        return messagesTeam;
    }

    public void setMessagesTeam(StringMap<String> _m) {
        messagesTeam = _m;
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
    public void setupPseudoImages() {
        int side_ = map.getSideLength();
        for (EntryCust<String, ImageArrayBaseSixtyFour> i : images.entryList()) {
            int[][] img_ = i.getValue().getImage();
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

//    void checkCaseOfFiles(String _folderName, StringList _files) {
//        StringList filesNamesWithSameCase_;
//        filesNamesWithSameCase_ = new StringList();
//        for (String s : _files) {
//            String upperCase_ = toUpperCase(s);
//            if (StringUtil.contains(filesNamesWithSameCase_, upperCase_)) {
//                String name_ = StringUtil.concat(_folderName, SEPARATOR_FILES,
//                        upperCase_);
//                if (!StringUtil.contains(filesWithSameNameDifferentCase, name_)) {
//                    filesWithSameNameDifferentCase.add(name_);
//                }
//            }
//            filesNamesWithSameCase_.add(upperCase_);
//        }
//    }

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
//        filesWithSameNameDifferentCase = new StringList();
        setAnimStatis(new StringMap<ImageArrayBaseSixtyFour>());
        setAnimStatus(new StringMap<ImageArrayBaseSixtyFour>());
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
        addCat(_move, categories, allCategories, defCategory);

        variables.addAllElts(getVariableWords(_move.getAccuracy(), prefixedVar()));

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

    private static void addCat(MoveData _move, StringList _categories, StringList _allCategories, String _defCategory) {
        String category_;
        if (_move instanceof DamagingMoveData) {
            category_ = ((DamagingMoveData) _move).getCategory();
            _categories.add(category_);
        } else {
            category_ = _defCategory;
        }
        _allCategories.add(category_);
    }

    private void updateInfoEffect(String _moveName, MoveData _move, Effect _e) {
        variables.addAllElts(getVariableWords(_e.getFail(), prefixedVar()));

        if (_e instanceof EffectCopyMove && ((EffectCopyMove) _e).getCopyingMoveForUser() > 0) {
            movesCopyingTemp.add(_moveName);
            movesCopyingTemp.removeDuplicates();
        }
        if (_e instanceof EffectCounterAttack) {
            EffectCounterAttack effectCounterAttack_;
            effectCounterAttack_ = (EffectCounterAttack) _e;

            variables.addAllElts(getVariableWords(effectCounterAttack_
                    .getCounterFail(), prefixedVar()));
            variables.addAllElts(getVariableWords(effectCounterAttack_
                    .getProtectFail(), prefixedVar()));
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
                    .getRestoredHp(), prefixedVar()));
        }
        if (_e instanceof EffectTeamWhileSendFoe) {
            movesEffectWhileSending.add(_moveName);

            variables
                    .addAllElts(getVariableWords(((EffectTeamWhileSendFoe) _e)
                            .getDamageRateAgainstFoe(), prefixedVar()));
            variables
                    .addAllElts(getVariableWords(((EffectTeamWhileSendFoe) _e)
                            .getFailSending(), prefixedVar()));
        }
        if (_e instanceof EffectInvoke) {
            movesInvoking.add(_moveName);
        }
    }

    private void updateInfoEffectCommonStatistic(EffectCommonStatistics _e) {
        for (String r : _e.getCommonValue()
                .values()) {

            variables.addAllElts(getVariableWords(r, prefixedVar()));
        }
    }

    private void updateInfoEffectStatus(String _moveName, EffectStatus _e) {
        for (String r : _e.getLocalFailStatus()
                .values()) {

            variables.addAllElts(getVariableWords(r, prefixedVar()));
        }
        if (_e.getKoUserHealSubst()) {
            movesFullHeal.add(_moveName);
        }
    }

    private void updateInfoEffectStatistic(EffectStatistic _e) {
        for (String r : _e.getLocalFailStatis()
                .values()) {

            variables.addAllElts(getVariableWords(r, prefixedVar()));
        }
        for (String r : _e
                .getLocalFailSwapBoostStatis().values()) {

            variables.addAllElts(getVariableWords(r, prefixedVar()));
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

        variables.addAllElts(getVariableWords(_e.getFailEndRound(), prefixedVar()));
    }

    private void updateInfoEffectDamage(EffectDamage _e) {
        variables.addAllElts(getVariableWords(_e
                .getPower(), prefixedVar()));

        for (String event_ : _e.getDamageLaw().events()) {

            variables.addAllElts(getVariableWords(event_, prefixedVar()));

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
                    .getMultStat().values()), EMPTY_STRING), prefixedVar()));
            variables.addAllElts(getVariableWords(obj_.getMultDamage(), prefixedVar()));
            variables.addAllElts(getVariableWords(obj_.getMultPower(), prefixedVar()));
            variables.addAllElts(getVariableWords(StringUtil.join(new StringList(obj_
                    .getFailStatus().values()), EMPTY_STRING), prefixedVar()));
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
                .getMultStat().values()), EMPTY_STRING), prefixedVar()));
        variables.addAllElts(getVariableWords(_ability.getMultDamage(), prefixedVar()));
        variables.addAllElts(getVariableWords(_ability.getMultPower(), prefixedVar()));
        variables.addAllElts(getVariableWords(StringUtil.join(new StringList(_ability
                .getFailStatus().values()), EMPTY_STRING), prefixedVar()));
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


    public String prefixedVar() {
        return StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS);
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
                int j_ = possibleIncr(i_, cur_);
                int delta_ = delta(cur_);
                j_ = incr(_litt,j_);
                cur_ = _litt.charAt(j_);
//                while (MathExpUtil.isWordChar(cur_)) {
//                    j_++;
//                    cur_ = _litt.charAt(j_);
//                }
                String word_ = _litt.substring(i_+delta_, j_);
                formulaWord(_language, litt_, list_, word_);
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
            } else if (MathExpUtil.isDollarWordChar(cur_)|| _litt.startsWith(getTrueString(), i_)|| _litt.startsWith(getFalseString(), i_)) {
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
        if (!MathExpUtil.isDollarWordChar(_cur)) {
//                    cur_ = _litt.charAt(j_);
            delta_++;
        }
        return delta_;
    }

    private int possibleIncr(int _i, char _cur) {
        int j_ = _i;
        if (!MathExpUtil.isDollarWordChar(_cur)) {
            j_++;
        }
        return j_;
    }

    private int incr(String _litt, int _j) {
        int j_ = _j;
        char cur_ = _litt.charAt(j_);
        while (MathExpUtil.isDollarWordChar(cur_)) {
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
        if (_litt.startsWith(getTrueString(), _i))  {
            return _i+getTrueString().length();
        }
        if (_litt.startsWith(getFalseString(), _i))  {
            return _i+getFalseString().length();
        }
        char cur_ = _litt.charAt(_i);
        int j_ = _i;
        while (MathExpUtil.isDollarWordChar(cur_)) {
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
        String varPref_ = StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS);
        if (_cur == '(' || StringUtil.quickEq(getTrueString(), _word)|| StringUtil.quickEq(getFalseString(), _word)) {
            _str.append(StringUtil.nullToEmpty(translatedFctMath.getVal(_language).getVal(_word)));
        } else if (!_word.startsWith(varPref_)) {
            _str.append(translateSafe(_word, _language));
        } else {
            String format_ = formatVar(_language, _litt, _word);
            _str.append(format_);
        }
    }

    private void formulaWord(String _language, StringMap<String> _litt, StringList _list, String _word) {
        String varPref_ = StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS);
        if (!_word.startsWith(varPref_)) {
            _list.add(translateSafe(_word, _language));
        } else {
            String format_ = formatVar(_language, _litt, _word);
            _list.add(format_);
        }
    }

    private String formatVar(String _language, StringMap<String> _litt, String _word) {
        String varPref_ = StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS);
        String tok_ = _word.substring(varPref_.length());
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

    private static StringList getVariableWords(String _str, String _pref) {
        StringList list_ = MathExpUtil.getDollarWordSeparators(_str);
        StringList newList_ = new StringList();
        int i_ = IndexConstants.FIRST_INDEX;
        for (String t : list_) {
            if (i_ % 2 == 0) {
                i_++;
                continue;
            }
            if (isVariable(t, _pref)) {
                newList_.add(t);
            }
            i_++;
        }
        return newList_;
    }

    static boolean isVariable(String _string, String _pref) {
        if (!_string.startsWith(_pref)) {
            return false;
        }
        return _string.length() > _pref.length();
    }

    public NatStringTreeMap< String> getDescriptions(String _litt,
            String _language) {
        String varPref_ = StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS);
        StringMap<String> litt_ = litterals.getVal(_language);

        StringList tokens_ = MathExpUtil.getWordsSeparatorsPrefix(_litt,
                varPref_);
        NatStringTreeMap< String> desc_ = new NatStringTreeMap< String>();
        int len_ = tokens_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            if (i % 2 == 0) {
                continue;
            }
            String tok_ = tokens_.get(i).substring(varPref_.length());
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
        String varPref_ = StringUtil.concat(prefixVar(),SEP_BETWEEN_KEYS);
        StringMap<String> litt_ = litterals.getVal(_language);
        String tok_ = _token.substring(varPref_.length());
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
        StringList words_ = MathExpUtil.getDollarWordSeparators(insideSet_);
        for (String w : words_) {
            if (!MathExpUtil.isDollarWord(w)) {
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

    public AbilityData getSafeAbility(String _name) {
        AbilityData ab_ = getAbility(_name);
        if (ab_ == null) {
            return Instances.newAbilityData();
        }
        return ab_;
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

    public StringMap<ImageArrayBaseSixtyFour> getMiniPk() {
        return miniPk;
    }

    public StringMap<ImageArrayBaseSixtyFour> getMaxiPkBack() {
        return maxiPkBack;
    }

    public StringMap<ImageArrayBaseSixtyFour> getMaxiPkFront() {
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

    public StringMap<ImageArrayBaseSixtyFour> getMiniItems() {
        return miniItems;
    }

    public int[][] getTrainer(String _name) {
        return getValueCaseInsensitive(trainers, _name);

    }

    public StringMap<ImageArrayBaseSixtyFour> getTrainers() {
        return trainers;
    }

    public int[][] getPerson(String _name) {
        return getValueCaseInsensitive(people, _name);

    }

    public StringMap<ImageArrayBaseSixtyFour> getPeople() {
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

    public StringMap<ImageArrayBaseSixtyFour> getLinks() {
        return links;
    }

    public int[][] getImage(String _name) {
        return getValueCaseInsensitive(images, _name);

    }

    public StringMap<ImageArrayBaseSixtyFour> getImages() {
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

    private static int[][] getValueCaseInsensitive(StringMap<ImageArrayBaseSixtyFour> _map,
                                                   String _name) {
        for (EntryCust<String, ImageArrayBaseSixtyFour> e : _map.entryList()) {
            if (StringUtil.quickEq(e.getKey(),_name)) {
                return e.getValue().getImage();
            }
        }
        return new int[0][0];
    }

    public StringMap<ImageArrayBaseSixtyFour> getMiniMap() {
        return miniMap;
    }

    public ImageArrayBaseSixtyFour getImageTmHm() {
        return imageTmHm;
    }

    public void setImageTmHm(ImageArrayBaseSixtyFour _imageTmHm) {
        imageTmHm = _imageTmHm;
    }

    public ImageArrayBaseSixtyFour getStorage() {
        return storage;
    }

    public void setStorage(ImageArrayBaseSixtyFour _storage) {
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

    /** General data - show all pokemon belonging to this group */
    public String getDefaultEggGroup() {

        return defaultEggGroup;
    }
    public void removeMoveFromLists(String _moveName) {
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
    public void renamePokemon(String _oldName, String _newName) {
        if (isUsed(_newName)) {
            return;
        }
        changeNameDefInExp(_oldName, _newName);
        for (Item o: items.values()) {
            if (o instanceof Fossil) {
                Fossil f_ = (Fossil) o;
                changeValue(new ChangeStringFieldFossil(f_),_oldName,_newName);
            }
        }
        for (PokemonData p: pokedex.values()) {
            changeValue(new ChangeStringFieldPokemonDataBaseEvo(p),_oldName,_newName);
            p.getEvolutions().move(_oldName, _newName);
        }
        for (Place p: map.getPlaces()) {
            for (Level l: p.getLevelsList()) {
                new ChangeStringFieldLevelWildName().change(l,_oldName,_newName);
            }
        }
        pokedex.move(_oldName, _newName);
        for (StringMap<String> t: getTranslatedPokemon().values()) {
            t.move(_oldName, _newName);
        }
        miniPk.move(_oldName, _newName);
        maxiPkBack.move(_oldName, _newName);
        maxiPkFront.move(_oldName, _newName);
        legPks.replace(_oldName, _newName);
    }
    public void deletePokemon(String _oldName) {
        if (usedPkInExp(_oldName)) {
            return;
        }
        ChangeStringKeyUtil ls_ = new ChangeStringKeyUtil();
        for (Item o: items.values()) {
            if (o instanceof Fossil) {
                Fossil f_ = (Fossil) o;
                ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldFossil(f_)));
            }
        }
        for (EntryCust<String, PokemonData> p: pokedex.entryList()) {
            if (!StringUtil.quickEq(p.getKey(),_oldName)) {
                ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldPokemonDataBaseEvo(p.getValue())));
                ls_.add(new ChangeStringFieldMatchMapContains<Evolution>(p.getValue().getEvolutions()));
            }
        }
        for (Place p: map.getPlaces()) {
            for (Level l: p.getLevelsList()) {
                ls_.addAllElts(new ChangeStringFieldLevelWildName().change(l));
            }
        }
        if (ls_.contains(_oldName)) {
            return;
        }
        pokedex.removeKey(_oldName);
        for (StringMap<String> t: getTranslatedPokemon().values()) {
            t.removeKey(_oldName);
        }
        miniPk.removeKey(_oldName);
        maxiPkBack.removeKey(_oldName);
        maxiPkFront.removeKey(_oldName);
        legPks.removeObj(_oldName);
    }
    public void renameTm(short _oldName, short _newName) {
        if (tm.contains(_newName)) {
            return;
        }
        for (PokemonData p: pokedex.values()) {
            Shorts ls_ = p.getTechnicalMoves();
            replace(_oldName, _newName, ls_);
        }
        for (Place p: map.getPlaces()) {
            levelTm(_oldName, _newName, p);
        }
        tm.move(_oldName,_newName);
    }

    private void levelTm(short _oldName, short _newName, Place _p) {
        for (Level l: _p.getLevelsList()) {
            if (l instanceof LevelWithWildPokemon) {
                levelTm(_oldName, _newName, (LevelWithWildPokemon) l);
            }
        }
        if (_p instanceof City) {
            for (Building b: ((City) _p).getBuildings().values()) {
                if (b instanceof Gym) {
                    updateTm(_oldName, _newName, (Gym) b);
                }
            }
        }
    }

    private void levelTm(short _oldName, short _newName, LevelWithWildPokemon _l) {
        new ChangeShortValueUtil<Point>(_l.getTm()).replace(_oldName, _newName);
        for (CharacterInRoadCave c: _l.getCharacters().values()) {
            if (c instanceof DealerItem) {
                replace(_oldName, _newName,((DealerItem)c).getTechnicalMoves());
            }
        }
    }

    private void updateTm(short _oldName, short _newName, Gym _b) {
        ChangeShortFieldGymLeader ch_ = new ChangeShortFieldGymLeader(_b.getIndoor().getGymLeader());
        if (ch_.value() == _oldName) {
            ch_.value(_newName);
        }
    }

    public void renameHm(short _oldName, short _newName) {
        if (hm.contains(_newName)) {
            return;
        }
        for (PokemonData p: pokedex.values()) {
            Shorts ls_ = p.getHiddenMoves();
            replace(_oldName, _newName, ls_);
        }
        for (Place p: map.getPlaces()) {
            levelHm(_oldName, _newName, p);
        }
        hm.move(_oldName,_newName);
    }

    private void levelHm(short _oldName, short _newName, Place _p) {
        for (Level l: _p.getLevelsList()) {
            if (l instanceof LevelWithWildPokemon) {
                levelHm(_oldName, _newName, (LevelWithWildPokemon) l);
            }
        }
    }

    private void levelHm(short _oldName, short _newName, LevelWithWildPokemon _l) {
        new ChangeShortValueUtil<Point>(_l.getHm()).replace(_oldName, _newName);
    }
    private static void replace(short _oldName, short _newName, Shorts _ls) {
        int len_ = _ls.size();
        for (int i = 0; i < len_; i++) {
            if (_ls.get(i) == _oldName) {
                _ls.set(i, _newName);
            }
        }
    }

    public void deleteTm(short _oldName) {
        ChangeShortKeyUtil ch_ = new ChangeShortKeyUtil();
        for (PokemonData p: pokedex.values()) {
            Shorts ls_ = p.getTechnicalMoves();
            ch_.add(new ChangeStringFieldMatchShortsContains(ls_));
        }
        for (Place p: map.getPlaces()) {
            ch_.addAllElts(levelTm(p));
        }
        if (ch_.contains(_oldName)) {
            return;
        }
        tm.removeKey(_oldName);
    }

    private CustList<ChangeShortFieldMatch> levelTm(Place _p) {
        CustList<ChangeShortFieldMatch> ls_ = new CustList<ChangeShortFieldMatch>();
        for (Level l: _p.getLevelsList()) {
            if (l instanceof LevelWithWildPokemon) {
                ls_.addAllElts(levelTm((LevelWithWildPokemon) l));
            }
        }
        if (_p instanceof City) {
            for (Building b: ((City) _p).getBuildings().values()) {
                if (b instanceof Gym) {
                    ls_.add(updateTm((Gym) b));
                }
            }
        }
        return ls_;
    }

    private ChangeShortFieldMatchDef updateTm(Gym _b) {
        return new ChangeShortFieldMatchDef(new ChangeShortFieldGymLeader(_b.getIndoor().getGymLeader()));
    }
    private CustList<ChangeShortFieldMatch> levelTm(LevelWithWildPokemon _l) {
        CustList<ChangeShortFieldMatch> ls_ = new CustList<ChangeShortFieldMatch>();
        ls_.add(new ChangeShortValueUtil<Point>(_l.getTm()));
        for (CharacterInRoadCave c: _l.getCharacters().values()) {
            if (c instanceof DealerItem) {
                ls_.add(new ChangeStringFieldMatchShortsContains(((DealerItem)c).getTechnicalMoves()));
            }
        }
        return ls_;
    }
    public void deleteHm(short _oldName) {
        ChangeShortKeyUtil ch_ = new ChangeShortKeyUtil();
        for (PokemonData p: pokedex.values()) {
            Shorts ls_ = p.getHiddenMoves();
            ch_.add(new ChangeStringFieldMatchShortsContains(ls_));
        }
        for (Place p: map.getPlaces()) {
            ch_.addAllElts(levelHm(p));
        }
        if (ch_.contains(_oldName)) {
            return;
        }
        hm.removeKey(_oldName);
    }

    private CustList<ChangeShortFieldMatch> levelHm(Place _p) {
        CustList<ChangeShortFieldMatch> ls_ = new CustList<ChangeShortFieldMatch>();
        for (Level l: _p.getLevelsList()) {
            if (l instanceof LevelWithWildPokemon) {
                ls_.add(levelHm((LevelWithWildPokemon) l));
            }
        }
        return ls_;
    }

    private ChangeShortFieldMatch levelHm(LevelWithWildPokemon _l) {
        return new ChangeShortValueUtil<Point>(_l.getHm());
    }
    public void renameMove(String _oldName, String _newName) {
        if (isUsed(_newName)) {
            return;
        }
        changeNameMoveInExp(_oldName, _newName);
        for (PokemonData p: pokedex.values()) {
            pokemonMove(_oldName, _newName, p);
        }
        for (AbilityData a: abilities.values()) {
            abilityMove(_oldName, _newName, a);
        }
        for (Item o: items.values()) {
            if (o instanceof ItemForBattle) {
                ItemForBattle obj_ = (ItemForBattle) o;
                obj_.getIncreasingMaxNbRoundTrap().move(_oldName, _newName);
                obj_.getIncreasingMaxNbRoundGlobalMove().move(_oldName, _newName);
                obj_.getIncreasingMaxNbRoundTeamMove().move(_oldName, _newName);
                changeEnabledWeatherList(_oldName, _newName, obj_.getEffectSending());
            }
        }
        ListEffectCombos effects_ = new ListEffectCombos();
        for (StringList l: combos.getEffects().getKeys()) {
            EffectCombo eff_ = combos.getEffects().getVal(l);
            if (eff_.estActifEquipe()) {
                EffectTeam eff2_ = eff_.getTeamMove().first();
                eff2_.getUnusableMoves().replace(_oldName, _newName);
                eff2_.getDisableFoeTeamEffects().replace(_oldName, _newName);
            }
            StringList l_ = new StringList(l);
            l_.replace(_oldName, _newName);
            effects_.add(new ListEffectCombo(l_, eff_));
        }
        combos.setEffects(effects_);
        for (MoveData m: moves.values()) {
            move(_oldName, _newName, m);
        }
        for (Place p: map.getPlaces()) {
            for (Level l: p.getLevelsList()) {
                new ChangeStringFieldLevelMoves().change(l,_oldName,_newName);
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
        new ChangeStringValueUtil<Short>(hm).replace(_oldName, _newName);
        new ChangeStringValueUtil<Short>(tm).replace(_oldName, _newName);
        moves.move(_oldName, _newName);
        for (StringMap<String> t: getTranslatedMoves().values()) {
            t.move(_oldName, _newName);
        }
        changeParams(_oldName, _newName, movesPart());
    }

    private void move(String _oldName, String _newName, MoveData _m) {
        _m.getAchieveDisappearedPkUsingMove().replace(_oldName, _newName);
        for (Effect e: _m.getEffects()) {
            moveEffect(_oldName, _newName, e);
        }
    }
    public void editMove(String _name, MoveData _modifiedMove) {
        removeMoveFromLists(_name);
        completeMembers(_name, _modifiedMove);
        StringList nextCat_ = new StringList();
        StringList nextAllCat_ = new StringList();
        for (EntryCust<String, MoveData> m: moves.entryList()) {
            addCat(m.getValue(), nextCat_, nextAllCat_, defCategory);
        }
        categories.clear();
        allCategories.clear();
        categories.addAllElts(nextCat_);
        allCategories.addAllElts(nextAllCat_);
        removeDuplicatesCategoriesMoves();
    }
    public void deleteMove(String _oldName) {
        if (usedMoveInExp(_oldName)) {
            return;
        }
        ChangeStringKeyUtil ls_ = new ChangeStringKeyUtil();
        for (PokemonData p: pokedex.values()) {
            ls_.addAllElts(pokemonMove(p));
        }
        for (AbilityData a: abilities.values()) {
            ls_.addAllElts(abilityMove(a));
        }
        for (Item o: items.values()) {
            if (o instanceof ItemForBattle) {
                ItemForBattle obj_ = (ItemForBattle) o;
                ls_.add(new ChangeStringFieldMatchMapContains<Short>(obj_.getIncreasingMaxNbRoundTrap()));
                ls_.add(new ChangeStringFieldMatchMapContains<Short>(obj_.getIncreasingMaxNbRoundGlobalMove()));
                ls_.add(new ChangeStringFieldMatchMapContains<Short>(obj_.getIncreasingMaxNbRoundTeamMove()));
                ls_.addAllElts(changeEnabledWeatherList(obj_.getEffectSending()));
            }
        }
        for (StringList l: combos.getEffects().getKeys()) {
            EffectCombo eff_ = combos.getEffects().getVal(l);
            if (eff_.estActifEquipe()) {
                EffectTeam eff2_ = eff_.getTeamMove().first();
                ls_.add(new ChangeStringFieldMatchStringListContains(eff2_.getUnusableMoves()));
                ls_.add(new ChangeStringFieldMatchStringListContains(eff2_.getDisableFoeTeamEffects()));
            }
            ls_.add(new ChangeStringFieldMatchStringListContains(l));
        }
        StringList nextCat_ = new StringList();
        StringList nextAllCat_ = new StringList();
        for (EntryCust<String, MoveData> m: moves.entryList()) {
            move(_oldName, ls_, m.getKey(), m.getValue(), nextCat_, nextAllCat_);
        }
        for (Place p: map.getPlaces()) {
            for (Level l: p.getLevelsList()) {
                ls_.addAllElts(new ChangeStringFieldLevelMoves().change(l));
            }
        }
        ls_.add(new ChangeStringValueUtil<Short>(hm));
        ls_.add(new ChangeStringValueUtil<Short>(tm));
        if (ls_.contains(_oldName)) {
            return;
        }
        categories.clear();
        allCategories.clear();
        categories.addAllElts(nextCat_);
        allCategories.addAllElts(nextAllCat_);
        removeDuplicatesCategoriesMoves();
        removeMoveFromLists(_oldName);
        moves.removeKey(_oldName);
        for (StringMap<String> t: getTranslatedMoves().values()) {
            t.removeKey(_oldName);
        }
        removeParams(_oldName, movesPart());
    }

    private void move(String _oldName, ChangeStringKeyUtil _ls, String _key, MoveData _value, StringList _nextCat, StringList _nextAllCat) {
        if (!StringUtil.quickEq(_key, _oldName)) {
            _ls.add(new ChangeStringFieldMatchStringListContains(_value.getAchieveDisappearedPkUsingMove()));
            for (Effect e: _value.getEffects()) {
                _ls.addAllElts(moveEffect(e));
            }
            addCat(_value, _nextCat, _nextAllCat, defCategory);
        }
    }

    private void abilityMove(String _oldName, String _newName, AbilityData _a) {
        _a.getImmuMove().replace(_oldName, _newName);
        _a.getIgnFoeTeamMove().replace(_oldName, _newName);
        _a.getImmuWeather().replace(_oldName, _newName);
        _a.getImmuMoveTypesByWeather().move(_oldName, _newName);
        _a.getImmuStatus().move(_oldName, _newName);
        _a.getChgtTypeByWeather().move(_oldName, _newName);
        _a.getHealHpByWeather().move(_oldName, _newName);
        changeEnabledWeatherList(_oldName, _newName, _a.getEffectSending());
        WeatherTypes map_ = new WeatherTypes();
        for (WeatherType p: _a.getHealHpByTypeIfWeather().getKeys()) {
            WeatherType w_ = new WeatherType(p.getWeather(),p.getType());
            changeValue(new ChangeStringFieldWeatherTypeMove(w_), _oldName, _newName);
            map_.addEntry(w_, _a.getHealHpByTypeIfWeather().getVal(p));
        }
        _a.setHealHpByTypeIfWeather(map_);
    }

    private CustList<ChangeStringFieldMatch> abilityMove(AbilityData _a) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        ls_.add(new ChangeStringFieldMatchStringListContains(_a.getImmuMove()));
        ls_.add(new ChangeStringFieldMatchStringListContains(_a.getIgnFoeTeamMove()));
        ls_.add(new ChangeStringFieldMatchStringListContains(_a.getImmuWeather()));
        ls_.add(new ChangeStringFieldMatchMapContains<StringList>(_a.getImmuMoveTypesByWeather()));
        ls_.add(new ChangeStringFieldMatchMapContains<StringList>(_a.getImmuStatus()));
        ls_.add(new ChangeStringFieldMatchMapContains<String>(_a.getChgtTypeByWeather()));
        ls_.add(new ChangeStringFieldMatchMapContains<Rate>(_a.getHealHpByWeather()));
        ls_.addAllElts(changeEnabledWeatherList(_a.getEffectSending()));
        for (WeatherType p: _a.getHealHpByTypeIfWeather().getKeys()) {
            ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldWeatherTypeMove(p)));
        }
        return ls_;
    }

    private void pokemonMove(String _oldName, String _newName, PokemonData _p) {
        for (LevelMove p2_: _p.getLevMoves()) {
            changeValue(new ChangeStringFieldLevelMove(p2_), _oldName, _newName);
        }
        _p.getMoveTutors().replace(_oldName, _newName);
        for (Evolution e: _p.getEvolutions().values()) {
            if (e instanceof EvolutionMove) {
                EvolutionMove evo_ = (EvolutionMove) e;
                changeValue(new ChangeStringFieldEvolutionMove(evo_), _oldName, _newName);
            }
        }
    }

    private CustList<ChangeStringFieldMatch> pokemonMove(PokemonData _p) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        for (LevelMove p2_: _p.getLevMoves()) {
            ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldLevelMove(p2_)));
        }
        ls_.add(new ChangeStringFieldMatchStringListContains(_p.getMoveTutors()));
        for (Evolution e: _p.getEvolutions().values()) {
            if (e instanceof EvolutionMove) {
                EvolutionMove evo_ = (EvolutionMove) e;
                ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldEvolutionMove(evo_)));
            }
        }
        return ls_;
    }

    private void moveEffect(String _oldName, String _newName, Effect _e) {
        if (_e instanceof EffectUnprotectFromTypes) {
            EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) _e;
            eff_.getDisableImmuFromMoves().replace(_oldName, _newName);
        }
        if (_e instanceof EffectCopyMove) {
            EffectCopyMove eff_ = (EffectCopyMove) _e;
            eff_.getMovesNotToBeCopied().replace(_oldName, _newName);
        }
        if (_e instanceof EffectTeam) {
            EffectTeam eff_ = (EffectTeam) _e;
            eff_.getUnusableMoves().replace(_oldName, _newName);
            eff_.getDisableFoeTeamEffects().replace(_oldName, _newName);
        }
        if (_e instanceof EffectGlobal) {
            EffectGlobal eff_ = (EffectGlobal) _e;
            eff_.getCancelEffects().replace(_oldName, _newName);
            eff_.getMultPowerMoves().move(_oldName, _newName);
            eff_.getUnusableMoves().replace(_oldName, _newName);
            eff_.getMovesUsedByTargetedFighters().replace(_oldName, _newName);
        }
        if (_e instanceof EffectInvoke) {
            EffectInvoke eff_ = (EffectInvoke) _e;
            eff_.getMovesNotToBeInvoked().replace(_oldName, _newName);
            new ChangeStringValueUtil<EnvironmentType>(eff_.getMoveFctEnv()).replace(_oldName, _newName);
            new ChangeStringValueUtil<String>(eff_.getInvokingMoveByUserTypes()).replace(_oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> moveEffect(Effect _e) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_e instanceof EffectUnprotectFromTypes) {
            EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) _e;
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getDisableImmuFromMoves()));
        }
        if (_e instanceof EffectCopyMove) {
            EffectCopyMove eff_ = (EffectCopyMove) _e;
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getMovesNotToBeCopied()));
        }
        if (_e instanceof EffectTeam) {
            EffectTeam eff_ = (EffectTeam) _e;
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getUnusableMoves()));
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getDisableFoeTeamEffects()));
        }
        if (_e instanceof EffectGlobal) {
            EffectGlobal eff_ = (EffectGlobal) _e;
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getCancelEffects()));
            ls_.add(new ChangeStringFieldMatchMapContains<Rate>(eff_.getMultPowerMoves()));
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getUnusableMoves()));
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getMovesUsedByTargetedFighters()));
        }
        if (_e instanceof EffectInvoke) {
            EffectInvoke eff_ = (EffectInvoke) _e;
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getMovesNotToBeInvoked()));
            ls_.add(new ChangeStringValueUtil<EnvironmentType>(eff_.getMoveFctEnv()));
            ls_.add(new ChangeStringValueUtil<String>(eff_.getInvokingMoveByUserTypes()));
        }
        return ls_;
    }

    private void changeEnabledWeatherList(String _oldName, String _newName, CustList<EffectWhileSendingWithStatistic> _ls) {
        if (!_ls.isEmpty()) {
            changeValue(new ChangeStringFieldEnabledWeather(_ls.first()), _oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> changeEnabledWeatherList(CustList<EffectWhileSendingWithStatistic> _ls) {
        if (!_ls.isEmpty()) {
            return new CustList<ChangeStringFieldMatch>(new ChangeStringFieldMatchDef(new ChangeStringFieldEnabledWeather(_ls.first())));
        }
        return new CustList<ChangeStringFieldMatch>();
    }

    public static void changeMoves(String _oldName, String _newName, CustList<PkTrainer> _ls) {
        for (PkTrainer p2_: _ls) {
            p2_.getMoves().replace(_oldName, _newName);
        }
    }

    public static CustList<ChangeStringFieldMatch> changeMoves(CustList<PkTrainer> _ls) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        for (PkTrainer p2_: _ls) {
            ls_.add(new ChangeStringFieldMatchStringListContains(p2_.getMoves()));
        }
        return ls_;
    }

    public void renameItem(String _oldName, String _newName) {
        if (isUsed(_newName)) {
            return;
        }
        changeNameDefInExp(_oldName, _newName);
        for (MoveData m: moves.values()) {
            m.getTypesByOwnedItem().move(_oldName, _newName);
            m.getSecEffectsByItem().move(_oldName, _newName);
        }
        for (PokemonData p: pokedex.values()) {
            for (Evolution e: p.getEvolutions().values()) {
                evoItem(_oldName, _newName, e);
            }
        }
        for (Item o: items.values()) {
            otherItem(_oldName, _newName, o);
        }
        for (Place p: map.getPlaces()) {
            place(_oldName, _newName, p);
        }
        items.move(_oldName, _newName);
        for (StringMap<String> t: getTranslatedItems().values()) {
            t.move(_oldName, _newName);
        }
        miniItems.move(_oldName, _newName);
    }

    private void place(String _oldName, String _newName, Place _p) {
        if (_p instanceof City) {
            for (Building b: ((City) _p).getBuildings().values()) {
                new ChangeStringFieldLevelWildItem().change(b.getLevel(), _oldName, _newName);
                charItem(_oldName, _newName, b.getLevel());
            }
        }
        for (Level l: _p.getLevelsList()) {
            new ChangeStringFieldLevelWildItem().change(l, _oldName, _newName);
            charItem(_oldName, _newName, l);
        }
    }

    public void deleteItem(String _oldName) {
        if (usedItInExp(_oldName)) {
            return;
        }
        ChangeStringKeyUtil matches_ = new ChangeStringKeyUtil();
        for (MoveData m: moves.values()) {
            matches_.add(new ChangeStringFieldMatchMapContains<String>(m.getTypesByOwnedItem()));
            matches_.add(new ChangeStringFieldMatchMapContains<Ints>(m.getSecEffectsByItem()));
        }
        for (PokemonData p: pokedex.values()) {
            for (Evolution e: p.getEvolutions().values()) {
                matches_.addAllElts(evoItem(e));
            }
        }
        for (EntryCust<String, Item> o: items.entryList()) {
            if (!StringUtil.quickEq(o.getKey(),_oldName)) {
                matches_.addAllElts(otherItem(o.getValue()));
            }
        }
        for (Place p: map.getPlaces()) {
            matches_.addAllElts(place(p));
        }
        if (matches_.contains(_oldName)) {
            return;
        }
        items.removeKey(_oldName);
        for (StringMap<String> t: getTranslatedItems().values()) {
            t.removeKey(_oldName);
        }
        miniItems.removeKey(_oldName);
    }
    private CustList<ChangeStringFieldMatch> place(Place _p) {
        CustList<ChangeStringFieldMatch> matches_ = new CustList<ChangeStringFieldMatch>();
        if (_p instanceof City) {
            for (Building b: ((City)_p).getBuildings().values()) {
                matches_.addAllElts(new ChangeStringFieldLevelWildItem().change(b.getLevel()));
                matches_.addAllElts(charItem(b.getLevel()));
            }
        }
        for (Level l: _p.getLevelsList()) {
            matches_.addAllElts(new ChangeStringFieldLevelWildItem().change(l));
            matches_.addAllElts(charItem(l));
        }
        return matches_;
    }

    private void charItem(String _oldName, String _newName, Level _l) {
        if (_l instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon level_ = (LevelWithWildPokemon) _l;
            for (CharacterInRoadCave t: level_.getCharacters().values()) {
                if (t instanceof DealerItem) {
                    DealerItem d_ = (DealerItem) t;
                    d_.getItems().replace(_oldName, _newName);
                }
            }
        }
        if (_l instanceof LevelIndoorPokemonCenter) {
            LevelIndoorPokemonCenter level_ = (LevelIndoorPokemonCenter) _l;
            for (Person g: level_.getGerants().values()) {
                if (g instanceof Seller) {
                    Seller s_ = (Seller) g;
                    s_.getItems().replace(_oldName, _newName);
                }
            }
        }
    }

    private CustList<ChangeStringFieldMatch> charItem(Level _l) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_l instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon level_ = (LevelWithWildPokemon) _l;
            for (CharacterInRoadCave t: level_.getCharacters().values()) {
                if (t instanceof DealerItem) {
                    DealerItem d_ = (DealerItem) t;
                    ls_.add(new ChangeStringFieldMatchStringListContains(d_.getItems()));
                }
            }
        }
        if (_l instanceof LevelIndoorPokemonCenter) {
            LevelIndoorPokemonCenter level_ = (LevelIndoorPokemonCenter) _l;
            for (Person g: level_.getGerants().values()) {
                if (g instanceof Seller) {
                    Seller s_ = (Seller) g;
                    ls_.add(new ChangeStringFieldMatchStringListContains(s_.getItems()));
                }
            }
        }
        return ls_;
    }

    private void otherItem(String _oldName, String _newName, Item _o) {
        if (_o instanceof HealingItem) {
            HealingItem h_ = (HealingItem) _o;
            h_.getHappiness().move(_oldName, _newName);
        }
        if (_o instanceof Boost) {
            Boost b_ = (Boost) _o;
            b_.getHappiness().move(_oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> otherItem(Item _o) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_o instanceof HealingItem) {
            HealingItem h_ = (HealingItem) _o;
            ls_.add(new ChangeStringFieldMatchMapContains<Short>(h_.getHappiness()));
        }
        if (_o instanceof Boost) {
            Boost b_ = (Boost) _o;
            ls_.add(new ChangeStringFieldMatchMapContains<Short>(b_.getHappiness()));
        }
        return ls_;
    }

    private void evoItem(String _oldName, String _newName, Evolution _e) {
        if (_e instanceof EvolutionStone) {
            EvolutionStone evo_ = (EvolutionStone) _e;
            changeValue(new ChangeStringFieldEvolutionStone(evo_), _oldName, _newName);
        }
        if (_e instanceof EvolutionItem) {
            EvolutionItem evo_ = (EvolutionItem) _e;
            changeValue(new ChangeStringFieldEvolutionItem(evo_), _oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> evoItem(Evolution _e) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_e instanceof EvolutionStone) {
            EvolutionStone evo_ = (EvolutionStone) _e;
            ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldEvolutionStone(evo_)));
        }
        if (_e instanceof EvolutionItem) {
            EvolutionItem evo_ = (EvolutionItem) _e;
            ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldEvolutionItem(evo_)));
        }
        return ls_;
    }

    public void renameAbility(String _oldName, String _newName) {
        if (isUsed(_newName)) {
            return;
        }
        changeNameDefInExp(_oldName, _newName);
        movesMatches(_oldName, _newName);
        for (PokemonData p: pokedex.values()) {
            p.getAbilities().replace(_oldName, _newName);
        }
        for (AbilityData a: abilities.values()) {
            a.getImmuAbility().replace(_oldName, _newName);
            a.getIgnAbility().replace(_oldName, _newName);
        }
        for (Place p: map.getPlaces()) {
            for (Level l: p.getLevelsList()) {
                new ChangeStringFieldLevelWildAbility().change(l,_oldName,_newName);
            }
        }
        abilities.move(_oldName, _newName);
        for (StringMap<String> t: getTranslatedAbilities().values()) {
            t.move(_oldName, _newName);
        }
    }

    private void movesMatches(String _oldName, String _newName) {
        for (MoveData m: moves.values()) {
            for (Effect e: m.getEffects()) {
                if (e instanceof EffectSwitchAbilities) {
                    EffectSwitchAbilities eff_ = (EffectSwitchAbilities) e;
                    changeValue(new ChangeStringFieldConstAbility(eff_), _oldName, _newName);
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    eff_.getCancelProtectingAbilities().replace(_oldName, _newName);
                }
            }
        }
    }

    public void deleteAbility(String _oldName) {
        if (usedAbInExp(_oldName)) {
            return;
        }
        ChangeStringKeyUtil matches_ = movesMatches();
        for (PokemonData p: pokedex.values()) {
            matches_.add(new ChangeStringFieldMatchStringListContains(p.getAbilities()));
        }
        for (EntryCust<String, AbilityData> a: abilities.entryList()) {
            if (!StringUtil.quickEq(a.getKey(),_oldName)) {
                matches_.add(new ChangeStringFieldMatchStringListContains(a.getValue().getImmuAbility()));
                matches_.add(new ChangeStringFieldMatchStringListContains(a.getValue().getIgnAbility()));
            }
        }
        for (Place p: map.getPlaces()) {
            for (Level l: p.getLevelsList()) {
                matches_.addAllElts(new ChangeStringFieldLevelWildAbility().change(l));
            }
        }
        if (matches_.contains(_oldName)) {
            return;
        }
        abilities.removeKey(_oldName);
        for (StringMap<String> t: getTranslatedAbilities().values()) {
            t.removeKey(_oldName);
        }
    }

    private ChangeStringKeyUtil movesMatches() {
        ChangeStringKeyUtil matches_ = new ChangeStringKeyUtil();
        for (MoveData m: moves.values()) {
            for (Effect e: m.getEffects()) {
                if (e instanceof EffectSwitchAbilities) {
                    EffectSwitchAbilities eff_ = (EffectSwitchAbilities) e;
                    matches_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldConstAbility(eff_)));
                }
                if (e instanceof EffectGlobal) {
                    EffectGlobal eff_ = (EffectGlobal) e;
                    matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getCancelProtectingAbilities()));
                }
            }
        }
        return matches_;
    }

    public void renameStatus(String _oldName, String _newName) {
        if (isUsed(_newName)) {
            return;
        }
        changeNameStatusInExp(_oldName, _newName);
        for (MoveData m: moves.values()) {
            m.getRequiredStatus().replace(_oldName, _newName);
            m.getDeletedStatus().replace(_oldName, _newName);
            for (Effect e: m.getEffects()) {
                statusEffect(_oldName, _newName, e);
            }
        }
        for (Item o: items.values()) {
            statusItem(_oldName, _newName, o);
        }
        for (AbilityData a: abilities.values()) {
            a.getFailStatus().move(_oldName, _newName);
            MonteCarloString law_ = renameLawKeys(_oldName, _newName, a.getSingleStatus());
            a.setSingleStatus(law_);
            a.getForwardStatus().move(_oldName, _newName);
            new ChangeStringValueUtil<String>(a.getForwardStatus()).replace(_oldName, _newName);
            renameStatusEffectEndRoundList(_oldName, _newName, a.getEffectEndRound());
        }
//        for (Status s: status.values()) {
//            for (EffectEndRoundStatus e: s.getEffectEndRound()) {
//                renameStatusEffectEndRound(e, _oldName, _newName);
//            }
//        }
        for (EffectCombo e: combos.getEffects().values()) {
            for (EffectTeam e2_: e.getTeamMove()) {
                e2_.getProtectAgainstStatus().replace(_oldName, _newName);
            }
        }
        status.move(_oldName, _newName);
        for (StringMap<String> t: getTranslatedStatus().values()) {
            t.move(_oldName, _newName);
        }
        animStatus.move(_oldName,_newName);
        changeParams(_oldName, _newName, statusPart());
    }

    public void deleteStatus(String _oldName) {
        if (usedStatusInExp(_oldName)) {
            return;
        }
        ChangeStringKeyUtil matches_ = statusMatches();
        for (Item o: items.values()) {
            matches_.addAllElts(statusItem(o));
        }
        for (AbilityData a: abilities.values()) {
            matches_.add(new ChangeStringFieldMatchMapContains<String>(a.getFailStatus()));
            matches_.add(new ChangeStringFieldMatchMapContains<LgInt>(a.getSingleStatus().getLaw()));
            matches_.add(new ChangeStringFieldMatchMapContains<String>(a.getForwardStatus()));
            matches_.add(new ChangeStringValueUtil<String>(a.getForwardStatus()));
            matches_.addAllElts(renameStatusEffectEndRoundList(a.getEffectEndRound()));
        }
//        for (EntryCust<String, Status> s: status.entryList()) {
//            if (!StringUtil.quickEq(s.getKey(),_oldName)) {
//                for (EffectEndRoundStatus e: s.getValue().getEffectEndRound()) {
//                    matches_.addAllElts(renameStatusEffectEndRound(e));
//                }
//            }
//        }
        for (EffectCombo e: combos.getEffects().values()) {
            for (EffectTeam e2_: e.getTeamMove()) {
                matches_.add(new ChangeStringFieldMatchStringListContains(e2_.getProtectAgainstStatus()));
            }
        }
        if (matches_.contains(_oldName)) {
            return;
        }
        status.removeKey(_oldName);
        for (StringMap<String> t: getTranslatedStatus().values()) {
            t.removeKey(_oldName);
        }
        animStatus.removeKey(_oldName);
        removeParams(_oldName, statusPart());
    }

    private ChangeStringKeyUtil statusMatches() {
        ChangeStringKeyUtil matches_ = new ChangeStringKeyUtil();
        for (MoveData m: moves.values()) {
            matches_.add(new ChangeStringFieldMatchStringListContains(m.getRequiredStatus()));
            matches_.add(new ChangeStringFieldMatchStringListContains(m.getDeletedStatus()));
            for (Effect e: m.getEffects()) {
                matches_.addAllElts(statusEffect(e));
            }
        }
        return matches_;
    }

    private void statusItem(String _oldName, String _newName, Item _o) {
        if (_o instanceof Berry) {
            Berry b_ = (Berry) _o;
            b_.getHealStatus().replace(_oldName, _newName);
        }
        if (_o instanceof ItemForBattle) {
            ItemForBattle i_ = (ItemForBattle) _o;
            i_.getFailStatus().move(_oldName, _newName);
            i_.getImmuStatus().replace(_oldName, _newName);
            i_.getSynchroStatus().replace(_oldName, _newName);
            renameStatusEffectEndRoundList(_oldName, _newName, i_.getEffectEndRound());
        }
        if (_o instanceof HealingStatus) {
            HealingStatus s_ = (HealingStatus) _o;
            s_.getStatus().replace(_oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> statusItem(Item _o) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_o instanceof Berry) {
            Berry b_ = (Berry) _o;
            ls_.add(new ChangeStringFieldMatchStringListContains(b_.getHealStatus()));
        }
        if (_o instanceof ItemForBattle) {
            ItemForBattle i_ = (ItemForBattle) _o;
            ls_.add(new ChangeStringFieldMatchMapContains<String>(i_.getFailStatus()));
            ls_.add(new ChangeStringFieldMatchStringListContains(i_.getImmuStatus()));
            ls_.add(new ChangeStringFieldMatchStringListContains(i_.getSynchroStatus()));
            ls_.addAllElts(renameStatusEffectEndRoundList(i_.getEffectEndRound()));
        }
        if (_o instanceof HealingStatus) {
            HealingStatus s_ = (HealingStatus) _o;
            ls_.add(new ChangeStringFieldMatchStringListContains(s_.getStatus()));
        }
        return ls_;
    }

    private void statusEffect(String _oldName, String _newName, Effect _e) {
        if (_e instanceof EffectEndRound) {
            renameStatusEffectEndRound((EffectEndRound) _e, _oldName, _newName);
        }
        if (_e instanceof EffectTeam) {
            EffectTeam eff_ = (EffectTeam) _e;
            eff_.getProtectAgainstStatus().replace(_oldName, _newName);
        }
        if (_e instanceof EffectTeamWhileSendFoe) {
            EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) _e;
            new ChangeStringValueUtil<Short>(eff_.getStatusByNbUses()).replace(_oldName, _newName);
        }
        if (_e instanceof EffectGlobal) {
            EffectGlobal eff_ = (EffectGlobal) _e;
            eff_.getPreventStatus().replace(_oldName, _newName);
        }
        if (_e instanceof EffectStatus) {
            EffectStatus eff_ = (EffectStatus) _e;
            eff_.getLocalFailStatus().move(_oldName, _newName);
            MonteCarloString newLaw_ = renameLawKeys(_oldName, _newName, eff_.getLawStatus());
            eff_.setLawStatus(newLaw_);
        }
    }

    private CustList<ChangeStringFieldMatch> statusEffect(Effect _e) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_e instanceof EffectEndRound) {
            ls_.addAllElts(renameStatusEffectEndRound((EffectEndRound) _e));
        }
        if (_e instanceof EffectTeam) {
            EffectTeam eff_ = (EffectTeam) _e;
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getProtectAgainstStatus()));
        }
        if (_e instanceof EffectTeamWhileSendFoe) {
            EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) _e;
            ls_.add(new ChangeStringValueUtil<Short>(eff_.getStatusByNbUses()));
        }
        if (_e instanceof EffectGlobal) {
            EffectGlobal eff_ = (EffectGlobal) _e;
            ls_.add(new ChangeStringFieldMatchStringListContains(eff_.getPreventStatus()));
        }
        if (_e instanceof EffectStatus) {
            EffectStatus eff_ = (EffectStatus) _e;
            ls_.add(new ChangeStringFieldMatchMapContains<String>(eff_.getLocalFailStatus()));
            ls_.add(new ChangeStringFieldMatchMapContains<LgInt>(eff_.getLawStatus().getLaw()));
        }
        return ls_;
    }

    private MonteCarloString renameLawKeys(String _oldName, String _newName, MonteCarloString _law) {
        MonteCarloString newLaw_ = new MonteCarloString();
        for (String s: _law.events()) {
            if (StringUtil.quickEq(s, _oldName)) {
                newLaw_.addEvent(_newName, _law.rate(s));
            } else {
                newLaw_.addEvent(s, _law.rate(s));
            }
        }
        newLaw_.deleteZeroEvents();
        return newLaw_;
    }

    private void renameStatusEffectEndRoundList(String _oldName, String _newName, CustList<EffectEndRound> _ls) {
        if (!_ls.isEmpty()) {
            renameStatusEffectEndRound(_ls.first(), _oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> renameStatusEffectEndRoundList(CustList<EffectEndRound> _ls) {
        if (!_ls.isEmpty()) {
            return renameStatusEffectEndRound(_ls.first());
        }
        return new CustList<ChangeStringFieldMatch>();
    }

    static void renameStatusEffectEndRound(EffectEndRound _effect, String _oldName, String _newName) {
        if (_effect instanceof EffectEndRoundIndividual) {
            EffectEndRoundIndividual eff_ = (EffectEndRoundIndividual) _effect;
            eff_.getMultDamageStatus().move(_oldName, _newName);
            changeValue(new ChangeStringFieldUserStatusEndRound(eff_),_oldName,_newName);
        }
        if (_effect instanceof EffectEndRoundMultiRelation) {
            EffectEndRoundMultiRelation eff_ = (EffectEndRoundMultiRelation) _effect;
//            eff_.getMultDamageStatus().move(_oldName, _newName);
            eff_.getDamageByStatus().move(_oldName, _newName);
        }
//        if (_effect instanceof EffectEndRoundSingleStatus) {
//            EffectEndRoundSingleStatus eff_ = (EffectEndRoundSingleStatus) _effect;
//            eff_.getMultDamageStatus().move(_oldName, _newName);
//        }
    }

    static CustList<ChangeStringFieldMatch> renameStatusEffectEndRound(EffectEndRound _effect) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_effect instanceof EffectEndRoundIndividual) {
            EffectEndRoundIndividual eff_ = (EffectEndRoundIndividual) _effect;
            ls_.add(new ChangeStringFieldMatchMapContains<Rate>(eff_.getMultDamageStatus()));
            ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldUserStatusEndRound(eff_)));
        }
        if (_effect instanceof EffectEndRoundMultiRelation) {
            EffectEndRoundMultiRelation eff_ = (EffectEndRoundMultiRelation) _effect;
//            eff_.getMultDamageStatus().move(_oldName, _newName);
            ls_.add(new ChangeStringFieldMatchMapContains<Rate>(eff_.getDamageByStatus()));
        }
//        if (_effect instanceof EffectEndRoundSingleStatus) {
//            EffectEndRoundSingleStatus eff_ = (EffectEndRoundSingleStatus) _effect;
//            eff_.getMultDamageStatus().move(_oldName, _newName);
//        }
        return ls_;
    }

    public void renameType(String _oldName, String _newName) {
        if (usedInTr(_newName, getTranslatedTypes())) {
            return;
        }
        changeNameTypeInExp(_oldName, _newName);
        for (PokemonData p: pokedex.values()) {
            p.getTypes().replace(_oldName, _newName);
        }
        for (MoveData p: moves.values()) {
            p.getTypes().replace(_oldName, _newName);
            p.getBoostedTypes().replace(_oldName, _newName);
            new ChangeStringValueUtil<String>(p.getTypesByWeather()).replace(_oldName, _newName);
            new ChangeStringValueUtil<String>(p.getTypesByOwnedItem()).replace(_oldName, _newName);
            for (Effect e: p.getEffects()) {
                typeEffect(_oldName, _newName, e);
            }
        }
        for (Item o: items.values()) {
            multFoesDamage(_oldName, _newName, o);
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                i_.getTypesPk().replace(_oldName, _newName);
                i_.getImmuTypes().replace(_oldName, _newName);
                renameTypeEffectEndRoundList(_oldName, _newName, i_.getEffectEndRound());
            }
        }
        for (AbilityData a: abilities.values()) {
            typeAbility(_oldName, _newName, a);
        }
        TypesDuos table_ = new TypesDuos();
        for (TypesDuo p: tableTypes.getKeys()) {
            Rate value_ = tableTypes.getVal(p);
            TypesDuo pair_ = convertDuo(p, _oldName, _newName);
            table_.addEntry(pair_, value_);
        }
        tableTypes = table_;
        types.replace(_oldName, _newName);
        for (StringMap<String> t: getTranslatedTypes().values()) {
            t.move(_oldName, _newName);
        }
        typesImages.move(_oldName, _newName);
        typesColors.move(_oldName, _newName);
        changeParams(_oldName, _newName, typesPart());
    }
    private boolean usedInTr(String _name, StringMap<StringMap<String>> _trs) {
        int nb_ = 0;
        for (StringMap<String> t: _trs.values()) {
            if (t.contains(_name)) {
                nb_++;
            }
        }
        return nb_>0;
    }

    public void deleteType(String _oldName) {
        if (usedType(_oldName)) {
            return;
        }
        TypesDuos table_ = new TypesDuos();
        for (TypesDuo p: tableTypes.getKeys()) {
            if (!StringUtil.quickEq(p.getDamageType(),_oldName)&&!StringUtil.quickEq(p.getPokemonType(),_oldName)) {
                Rate value_ = tableTypes.getVal(p);
                table_.addEntry(p, value_);
            }
        }
        tableTypes = table_;
        types.removeObj(_oldName);
        for (StringMap<String> t: getTranslatedTypes().values()) {
            t.removeKey(_oldName);
        }
        typesImages.removeKey(_oldName);
        typesColors.removeKey(_oldName);
        removeParams(_oldName, typesPart());
    }
    public boolean usedType(String _oldName) {
        if (usedTypeInExp(_oldName)) {
            return true;
        }
        ChangeStringKeyUtil matches_ = new ChangeStringKeyUtil();
        for (PokemonData p: pokedex.values()) {
            matches_.add(new ChangeStringFieldMatchStringListContains(p.getTypes()));
        }
        for (MoveData p: moves.values()) {
            matches_.add(new ChangeStringFieldMatchStringListContains(p.getTypes()));
            matches_.add(new ChangeStringFieldMatchStringListContains(p.getBoostedTypes()));
            matches_.add(new ChangeStringValueUtil<String>(p.getTypesByWeather()));
            matches_.add(new ChangeStringValueUtil<String>(p.getTypesByOwnedItem()));
            for (Effect e: p.getEffects()) {
                matches_.addAllElts(typeEffect(e));
            }
        }
        for (Item o: items.values()) {
            matches_.addAllElts(multFoesDamage(o));
            if (o instanceof ItemForBattle) {
                ItemForBattle i_ = (ItemForBattle) o;
                matches_.add(new ChangeStringFieldMatchStringListContains(i_.getTypesPk()));
                matches_.add(new ChangeStringFieldMatchStringListContains(i_.getImmuTypes()));
                matches_.addAllElts(renameTypeEffectEndRoundList(i_.getEffectEndRound()));
            }
        }
        for (AbilityData a: abilities.values()) {
            matches_.addAllElts(typeAbility(a));
        }
        return matches_.contains(_oldName);
    }
    private void typeAbility(String _oldName, String _newName, AbilityData _a) {
        StatisticTypeByte mult_ = new StatisticTypeByte();
        for (StatisticType t: _a.getMultStatIfDamgeType().getKeys()) {
            byte value_ = _a.getMultStatIfDamgeType().getVal(t);
            StatisticType pair_ = convertStatisticType(t, _oldName, _newName);
            mult_.addEntry(pair_, value_);
        }
        _a.setMultStatIfDamgeType(mult_);
        CustList<TypesDuo> newList_ = new CustList<TypesDuo>();
        for (TypesDuo t: _a.getBreakFoeImmune()) {
            TypesDuo pair_ = convertDuo(t, _oldName, _newName);
            newList_.add(pair_);
        }
        _a.setBreakFoeImmune(newList_);

        WeatherTypes restore_ = new WeatherTypes();
        for (WeatherType t: _a.getHealHpByTypeIfWeather().getKeys()) {
            Rate value_ = _a.getHealHpByTypeIfWeather().getVal(t);
            WeatherType pair_ = new WeatherType(t.getWeather(),t.getType());
            changeValue(new ChangeStringFieldWeatherType(pair_), _oldName, _newName);
            restore_.addEntry(pair_, value_);
        }
        _a.setHealHpByTypeIfWeather(restore_);
        new ChangeStringValueUtil<String>(_a.getChgtTypeByWeather()).replace(_oldName, _newName);
        for (StringList l: _a.getImmuMoveTypesByWeather().values()) {
            l.replace(_oldName, _newName);
        }
        _a.getMultDamageFoe().move(_oldName, _newName);
        changeValue(new ChangeStringFieldTypeForMoves(_a), _oldName, _newName);
        renameTypeEffectEndRoundList(_oldName, _newName, _a.getEffectEndRound());
    }
    private CustList<ChangeStringFieldMatch> typeAbility(AbilityData _a) {
        CustList<ChangeStringFieldMatch> matches_ = new CustList<ChangeStringFieldMatch>();
        for (StatisticType t: _a.getMultStatIfDamgeType().getKeys()) {
            matches_.addAllElts(convertStatisticType(t));
        }
        for (TypesDuo t: _a.getBreakFoeImmune()) {
            matches_.addAllElts(convertDuo(t));
        }
        for (WeatherType t: _a.getHealHpByTypeIfWeather().getKeys()) {
            matches_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldWeatherType(t)));
        }
        matches_.add(new ChangeStringValueUtil<String>(_a.getChgtTypeByWeather()));
        for (StringList l: _a.getImmuMoveTypesByWeather().values()) {
            matches_.add(new ChangeStringFieldMatchStringListContains(l));
        }
        matches_.add(new ChangeStringFieldMatchMapContains<Rate>(_a.getMultDamageFoe()));
        matches_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldTypeForMoves(_a)));
        matches_.addAllElts(renameTypeEffectEndRoundList(_a.getEffectEndRound()));
        return matches_;
    }

    private void typeEffect(String _oldName, String _newName, Effect _e) {
        if (_e instanceof EffectUnprotectFromTypes) {
            EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) _e;
            eff_.getAttackTargetWithTypes().replace(_oldName, _newName);
            eff_.getDisableImmuAgainstTypes().replace(_oldName, _newName);
            CustList<TypesDuo> newList_ = new CustList<TypesDuo>();
            for (TypesDuo t: eff_.getTypes()) {
                TypesDuo pair_ = convertDuo(t, _oldName, _newName);
                newList_.add(pair_);
            }
            eff_.setTypes(newList_);
        }
        if (_e instanceof EffectSwitchTypes) {
            EffectSwitchTypes eff_ = (EffectSwitchTypes) _e;
            eff_.getConstTypes().replace(_oldName, _newName);
            new ChangeStringValueUtil<EnvironmentType>(eff_.getChgtTypeByEnv()).replace(_oldName, _newName);
        }
        if (_e instanceof EffectTeamWhileSendFoe) {
            EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) _e;
            eff_.getDeletedByFoeTypes().replace(_oldName, _newName);
        }
        if (_e instanceof EffectGlobal) {
            EffectGlobal eff_ = (EffectGlobal) _e;
            eff_.getImmuneTypes().replace(_oldName, _newName);
            eff_.getMultDamagePrepaRound().move(_oldName, _newName);
            eff_.getMultDamageTypesMoves().move(_oldName, _newName);
            eff_.getDisableImmuAgainstTypes().replace(_oldName, _newName);
            TypesDuos table_ = new TypesDuos();
            for (TypesDuo t: eff_.getEfficiencyMoves().getKeys()) {
                Rate value_ = eff_.getEfficiencyMoves().getVal(t);
                TypesDuo pair_ = convertDuo(t, _oldName, _newName);
                table_.addEntry(pair_, value_);
            }
            eff_.setEfficiencyMoves(table_);
            StatisticTypeRate mult_ = new StatisticTypeRate();
            for (StatisticType t: eff_.getMultStatIfContainsType().getKeys()) {
                Rate value_ = eff_.getMultStatIfContainsType().getVal(t);
                StatisticType pair_ = convertStatisticType(t, _oldName, _newName);
                mult_.addEntry(pair_, value_);
            }
            eff_.setMultStatIfContainsType(mult_);
        }
        if (_e instanceof EffectProtectFromTypes) {
            EffectProtectFromTypes eff_ = (EffectProtectFromTypes) _e;
            eff_.getImmuAgainstTypes().replace(_oldName, _newName);
        }
        if (_e instanceof EffectMultUsedMovePower) {
            EffectMultUsedMovePower eff_ = (EffectMultUsedMovePower) _e;
            eff_.getMultMovePowerFctType().move(_oldName, _newName);
        }
        if (_e instanceof EffectMultSufferedMovePower) {
            EffectMultSufferedMovePower eff_ = (EffectMultSufferedMovePower) _e;
            eff_.getMultMovePowerFctType().move(_oldName, _newName);
        }
        if (_e instanceof EffectEndRound) {
            renameTypeEffectEndRound((EffectEndRound) _e, _oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> typeEffect(Effect _e) {
        CustList<ChangeStringFieldMatch> matches_ = new CustList<ChangeStringFieldMatch>();
        if (_e instanceof EffectUnprotectFromTypes) {
            EffectUnprotectFromTypes eff_ = (EffectUnprotectFromTypes) _e;
            matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getAttackTargetWithTypes()));
            matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getDisableImmuAgainstTypes()));
            for (TypesDuo t: eff_.getTypes()) {
                matches_.addAllElts(convertDuo(t));
            }
        }
        if (_e instanceof EffectSwitchTypes) {
            EffectSwitchTypes eff_ = (EffectSwitchTypes) _e;
            matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getConstTypes()));
            matches_.add(new ChangeStringValueUtil<EnvironmentType>(eff_.getChgtTypeByEnv()));
        }
        if (_e instanceof EffectTeamWhileSendFoe) {
            EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) _e;
            matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getDeletedByFoeTypes()));
        }
        if (_e instanceof EffectGlobal) {
            EffectGlobal eff_ = (EffectGlobal) _e;
            matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getImmuneTypes()));
            matches_.add(new ChangeStringFieldMatchMapContains<Rate>(eff_.getMultDamagePrepaRound()));
            matches_.add(new ChangeStringFieldMatchMapContains<Rate>(eff_.getMultDamageTypesMoves()));
            matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getDisableImmuAgainstTypes()));
            for (TypesDuo t: eff_.getEfficiencyMoves().getKeys()) {
                matches_.addAllElts(convertDuo(t));
            }
            for (StatisticType t: eff_.getMultStatIfContainsType().getKeys()) {
                matches_.addAllElts(convertStatisticType(t));
            }
        }
        if (_e instanceof EffectProtectFromTypes) {
            EffectProtectFromTypes eff_ = (EffectProtectFromTypes) _e;
            matches_.add(new ChangeStringFieldMatchStringListContains(eff_.getImmuAgainstTypes()));
        }
        if (_e instanceof EffectMultUsedMovePower) {
            EffectMultUsedMovePower eff_ = (EffectMultUsedMovePower) _e;
            matches_.add(new ChangeStringFieldMatchMapContains<Rate>(eff_.getMultMovePowerFctType()));
        }
        if (_e instanceof EffectMultSufferedMovePower) {
            EffectMultSufferedMovePower eff_ = (EffectMultSufferedMovePower) _e;
            matches_.add(new ChangeStringFieldMatchMapContains<Rate>(eff_.getMultMovePowerFctType()));
        }
        if (_e instanceof EffectEndRound) {
            matches_.addAllElts(renameTypeEffectEndRound((EffectEndRound) _e));
        }
        return matches_;
    }
    private void multFoesDamage(String _oldName, String _newName, Item _o) {
        if (_o instanceof Berry) {
            Berry b_ = (Berry) _o;
            b_.getMultFoesDamage().move(_oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> multFoesDamage(Item _o) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_o instanceof Berry) {
            Berry b_ = (Berry) _o;
            ls_.add(new ChangeStringFieldMatchMapContains<EfficiencyRate>(b_.getMultFoesDamage()));
        }
        return ls_;
    }

    private StatisticType convertStatisticType(StatisticType _t, String _oldName, String _newName) {
        StatisticType pair_ = new StatisticType(_t.getStatistic(),_t.getType());
        changeValue(new ChangeStringFieldStatisticType(pair_),_oldName,_newName);
        return pair_;
    }

    private CustList<ChangeStringFieldMatch> convertStatisticType(StatisticType _t) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldStatisticType(_t)));
        return ls_;
    }

    private void renameTypeEffectEndRoundList(String _oldName, String _newName, CustList<EffectEndRound> _ls) {
        if (!_ls.isEmpty()) {
            renameTypeEffectEndRound(_ls.first(), _oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> renameTypeEffectEndRoundList(CustList<EffectEndRound> _ls) {
        if (!_ls.isEmpty()) {
            return renameTypeEffectEndRound(_ls.first());
        }
        return new CustList<ChangeStringFieldMatch>();
    }

    private TypesDuo convertDuo(TypesDuo _t, String _oldName, String _newName) {
        TypesDuo pair_ = new TypesDuo(_t.getDamageType(),_t.getPokemonType());
        changeValue(new ChangeStringFieldDamageType(pair_),_oldName,_newName);
        changeValue(new ChangeStringFieldPokemonType(pair_),_oldName,_newName);
        return pair_;
    }

    private CustList<ChangeStringFieldMatch> convertDuo(TypesDuo _t) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldDamageType(_t)));
        ls_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldPokemonType(_t)));
        return ls_;
    }

    static void renameTypeEffectEndRound(EffectEndRound _effect, String _oldName, String _newName) {
        if (_effect instanceof EffectEndRoundIndividual) {
            EffectEndRoundIndividual eff_ = (EffectEndRoundIndividual) _effect;
            eff_.getHealHpByOwnerTypes().move(_oldName,_newName);
        }
    }

    static CustList<ChangeStringFieldMatch> renameTypeEffectEndRound(EffectEndRound _effect) {
        CustList<ChangeStringFieldMatch> ls_ = new CustList<ChangeStringFieldMatch>();
        if (_effect instanceof EffectEndRoundIndividual) {
            ls_.add(new ChangeStringFieldMatchMapContains<Rate>(((EffectEndRoundIndividual) _effect).getHealHpByOwnerTypes()));
        }
        return ls_;
    }

    public void renameCategory(String _oldName, String _newName) {
        if (usedInTr(_newName,getTranslatedCategories())) {
            return;
        }
        changeNameCategoryInExp(_oldName, _newName);
        for (AbilityData a: abilities.values()) {
            StatisticCategoryByte mult_ = new StatisticCategoryByte();
            for (StatisticCategory t: a.getMultStatIfDamageCat().getKeys()) {
                byte value_ = a.getMultStatIfDamageCat().getVal(t);
                StatisticCategory pair_ = convertStatisticCategory(t, _oldName, _newName);
                mult_.addEntry(pair_, value_);
            }
            a.setMultStatIfDamageCat(mult_);
            a.getIncreasedPrio().move(_oldName, _newName);
            StatisticCategoryRate mult2_ = new StatisticCategoryRate();
            for (StatisticCategory t: a.getMultStatIfCat().getKeys()) {
                Rate value_ = a.getMultStatIfCat().getVal(t);
                StatisticCategory pair_ = convertStatisticCategory(t, _oldName, _newName);
                mult2_.addEntry(pair_, value_);
            }
            a.setMultStatIfCat(mult2_);
        }
        for (MoveData m: moves.values()) {
            moveCategory(_oldName, _newName, m);
        }
        for (Item o: items.values()) {
            damageRateRecoilFoe(_oldName, _newName, o);
        }
        changeCategory(_oldName,_newName);
        for (StringMap<String> t: getTranslatedCategories().values()) {
            t.move(_oldName, _newName);
        }
        changeParams(_oldName, _newName, categoriesPart());
//        getAllCategories().replace(_oldName, _newName);
//        getCategories().replace(_oldName, _newName);
    }

    void changeCategory(String _oldName, String _newName) {
        if (StringUtil.quickEq(_oldName, getDefCategory()) || StringUtil.quickEq(_newName, getDefCategory())) {
            return;
        }
        getAllCategories().replace(_oldName, _newName);
        getCategories().replace(_oldName, _newName);
    }

    public void deleteCategory(String _oldName) {
        if (usedCategory(_oldName)) {
            return;
        }
        getCategories().removeObj(_oldName);
        getAllCategories().removeObj(_oldName);
        for (StringMap<String> t: getTranslatedCategories().values()) {
            t.removeKey(_oldName);
        }
        removeParams(_oldName, categoriesPart());
//        getAllCategories().replace(_oldName, _newName);
//        getCategories().replace(_oldName, _newName);
    }

    public boolean usedCategory(String _oldName) {
        if (usedCategoryInExp(_oldName) || StringUtil.quickEq(_oldName,getDefCategory())) {
            return true;
        }
        ChangeStringKeyUtil ls_ = new ChangeStringKeyUtil();
        for (AbilityData a: abilities.values()) {
            ls_.addAllElts(useCategoryAbility(a));
        }
        for (MoveData m: moves.values()) {
            ls_.addAllElts(moveCategory(m));
        }
        for (Item o: items.values()) {
            ls_.addAllElts(damageRateRecoilFoe(o));
        }
        return ls_.contains(_oldName);
    }
    private CustList<ChangeStringFieldMatch> useCategoryAbility(AbilityData _a) {
        CustList<StatisticCategory> all_ = new CustList<StatisticCategory>();
        all_.addAllElts(_a.getMultStatIfDamageCat().getKeys());
        all_.addAllElts(_a.getMultStatIfCat().getKeys());
        CustList<ChangeStringFieldMatch> chg_ = new CustList<ChangeStringFieldMatch>();
        for (StatisticCategory t: all_) {
            chg_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldStatisticCategory(t)));
        }
        chg_.add(new ChangeStringFieldMatchMapContains<Short>(_a.getIncreasedPrio()));
        return chg_;
    }

    private void moveCategory(String _oldName, String _newName, MoveData _m) {
        ChangeStringField chg_ = tryChgDamagingMoveData(_m);
        changeValue(chg_, _oldName, _newName);
        for (Effect e: _m.getEffects()) {
            if (e instanceof EffectTeam) {
                EffectTeam eff_ = (EffectTeam) e;
                CategoryMults mult_ = new CategoryMults();
                for (CategoryMult t: eff_.getMultDamage().getKeys()) {
                    Rate value_ = eff_.getMultDamage().getVal(t);
                    CategoryMult pair_ = new CategoryMult(t.getCategory(),t.getMult());
                    changeValue(new ChangeStringFieldCategoryMult(pair_), _oldName, _newName);
                    mult_.addEntry(pair_, value_);
                }
                eff_.setMultDamage(mult_);
            }
        }
    }

    private CustList<ChangeStringFieldMatch> moveCategory(MoveData _m) {
        ChangeStringField chg_ = tryChgDamagingMoveData(_m);
        CustList<ChangeStringFieldMatch> chgUtil_ = new CustList<ChangeStringFieldMatch>();
        chgUtil_.add(new ChangeStringFieldMatchDef(chg_));
        for (Effect e: _m.getEffects()) {
            if (e instanceof EffectTeam) {
                EffectTeam eff_ = (EffectTeam) e;
                for (CategoryMult t: eff_.getMultDamage().getKeys()) {
                    chgUtil_.add(new ChangeStringFieldMatchDef(new ChangeStringFieldCategoryMult(t)));
                }
            }
        }
        return chgUtil_;
    }

    private void damageRateRecoilFoe(String _oldName, String _newName, Item _o) {
        if (_o instanceof Berry) {
            Berry b_ = (Berry) _o;
            b_.getDamageRateRecoilFoe().move(_oldName, _newName);
        }
    }

    private CustList<ChangeStringFieldMatch> damageRateRecoilFoe(Item _o) {
        if (_o instanceof Berry) {
            return new CustList<ChangeStringFieldMatch>(new ChangeStringFieldMatchMapContains<Rate>(((Berry) _o).getDamageRateRecoilFoe()));
        }
        return new CustList<ChangeStringFieldMatch>();
    }

    private ChangeStringField tryChgDamagingMoveData(MoveData _m) {
        ChangeStringField chg_;
        if (_m instanceof DamagingMoveData) {
            DamagingMoveData m_ = (DamagingMoveData) _m;
            chg_ = new ChangeStringFieldMoveCategory(m_);
        } else {
            chg_ = null;
        }
        return chg_;
    }

    private StatisticCategory convertStatisticCategory(StatisticCategory _t, String _oldName, String _newName) {
        StatisticCategory pair_ = new StatisticCategory(_t.getStatistic(),_t.getCategory());
        changeValue(new ChangeStringFieldStatisticCategory(pair_),_oldName,_newName);
        return pair_;
    }

    private void changeParams(String _oldName, String _newName, StringList _parts) {
        for (EntryCust<String,StringList> e: getVarParamsMove().entryList()) {
            if (_parts.containsObj(StringUtil.concat(e.getKey(),SEP_BETWEEN_KEYS))) {
                e.getValue().replace(_oldName,_newName);
            }
        }
    }

    private void removeParams(String _oldName, StringList _parts) {
        for (EntryCust<String,StringList> e: getVarParamsMove().entryList()) {
            if (_parts.containsObj(StringUtil.concat(e.getKey(),SEP_BETWEEN_KEYS))) {
                e.getValue().removeObj(_oldName);
            }
        }
    }

    public static void changeValue(ChangeStringField _i, String _oldName, String _newName) {
        if (_i == null) {
            return;
        }
        if (StringUtil.quickEq(_i.value(), _oldName)) {
            _i.value(_newName);
        }
    }

    public void changeNameDefInExp(String _oldName, String _newName) {
        changeNameInNumericExpressions(new StringList(),_oldName, _newName);
    }
    public void changeNameTypeInExp(String _oldName, String _newName) {
        changeNameInNumericExpressions(typesPart(),_oldName, _newName);
    }

    public void changeNameMoveInExp(String _oldName, String _newName) {
        changeNameInNumericExpressions(movesPart(),_oldName, _newName);
    }
    public void changeNameCategoryInExp(String _oldName, String _newName) {
        changeNameInNumericExpressions(categoriesPart(),_oldName, _newName);
    }
    public void changeNameStatusInExp(String _oldName, String _newName) {
        changeNameInNumericExpressions(statusPart(),_oldName, _newName);
    }
    void changeNameInNumericExpressions(StringList _mids, String _oldName, String _newName) {
        for (Item o: items.values()) {
            renameExpItem(_mids, _oldName, _newName, o);
        }
        for (AbilityData a: abilities.values()) {
            renameExpAbility(_mids, _oldName, _newName, a);
        }
        for (MoveData m: moves.values()) {
            m.setAccuracy(rename(m.getAccuracy(), _mids, _oldName, _newName));
            for (Effect e: m.getEffects()) {
                renameExpEffect(_mids, _oldName, _newName, e);
            }
        }
        for (Status s: status.values()) {
            for (EffectEndRoundStatus e: s.getEffectEndRound()) {
                e.setFail(rename(e.getFail(), _mids, _oldName, _newName));
                e.setFailEndRound(rename(e.getFailEndRound(), _mids, _oldName, _newName));
            }
        }
        for (EffectCombo e: combos.getEffects().values()) {
            for (EffectEndRoundFoe e2_: e.getEffectEndRound()) {
                e2_.setFail(rename(e2_.getFail(), _mids, _oldName, _newName));
                e2_.setFailEndRound(rename(e2_.getFailEndRound(), _mids, _oldName, _newName));
            }
        }
    }

    private void renameExpAbility(StringList _mids, String _oldName, String _newName, AbilityData _a) {
        _a.setMultPower(rename(_a.getMultPower(), _mids, _oldName, _newName));
        _a.setMultDamage(rename(_a.getMultDamage(), _mids, _oldName, _newName));
        new ChangeStringValueUtil<Statistic>(_a.getMultStat()).replaceExp(this, _mids, _oldName, _newName);
        new ChangeStringValueUtil<String>(_a.getFailStatus()).replaceExp(this, _mids, _oldName, _newName);
        renameExpSend(_mids, _oldName, _newName, _a.getEffectSending());
        endRound(_mids, _oldName, _newName, _a.getEffectEndRound());
    }

    private void renameExpEffect(StringList _mids, String _oldName, String _newName, Effect _e) {
        _e.setFail(rename(_e.getFail(), _mids, _oldName, _newName));
        if (_e instanceof EffectDamage) {
            EffectDamage eff_ = (EffectDamage) _e;
            eff_.setPower(rename(eff_.getPower(), _mids, _oldName, _newName));
            MonteCarloString newLaw_ = patchLaw(_mids, _oldName, _newName, eff_.getDamageLaw());
            eff_.setDamageLaw(newLaw_);
        }
        if (_e instanceof EffectTeamWhileSendFoe) {
            EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) _e;
            eff_.setDamageRateAgainstFoe(rename(eff_.getDamageRateAgainstFoe(), _mids, _oldName, _newName));
            eff_.setFailSending(rename(eff_.getFailSending(), _mids, _oldName, _newName));
        }
        if (_e instanceof EffectCommonStatistics) {
            EffectCommonStatistics eff_ = (EffectCommonStatistics) _e;
            new ChangeStringValueUtil<Statistic>(eff_.getCommonValue()).replaceExp(this, _mids, _oldName, _newName);
        }
        if (_e instanceof EffectStatistic) {
            EffectStatistic eff_ = (EffectStatistic) _e;
            new ChangeStringValueUtil<Statistic>(eff_.getLocalFailStatis()).replaceExp(this, _mids, _oldName, _newName);
            new ChangeStringValueUtil<Statistic>(eff_.getLocalFailSwapBoostStatis()).replaceExp(this, _mids, _oldName, _newName);
        }
        if (_e instanceof EffectStatus) {
            EffectStatus eff_ = (EffectStatus) _e;
            new ChangeStringValueUtil<String>(eff_.getLocalFailStatus()).replaceExp(this, _mids, _oldName, _newName);
        }
        if (_e instanceof EffectFullHpRate) {
            EffectFullHpRate eff_ = (EffectFullHpRate) _e;
            eff_.setRestoredHp(rename(eff_.getRestoredHp(), _mids, _oldName, _newName));
        }
        if (_e instanceof EffectEndRound) {
            EffectEndRound eff_ = (EffectEndRound) _e;
            eff_.setFailEndRound(rename(eff_.getFailEndRound(), _mids, _oldName, _newName));
        }
    }

    private void renameExpItem(StringList _mids, String _oldName, String _newName, Item _o) {
        if (_o instanceof Ball) {
            Ball b_ = (Ball) _o;
            b_.setCatchingRate(rename(b_.getCatchingRate(), _mids, _oldName, _newName));
        }
        if (_o instanceof ItemForBattle) {
            ItemForBattle i_ = (ItemForBattle) _o;
            i_.setMultPower(rename(i_.getMultPower(), _mids, _oldName, _newName));
            i_.setMultDamage(rename(i_.getMultDamage(), _mids, _oldName, _newName));
            new ChangeStringValueUtil<String>(i_.getFailStatus()).replaceExp(this, _mids, _oldName, _newName);
            new ChangeStringValueUtil<Statistic>(i_.getMultStat()).replaceExp(this, _mids, _oldName, _newName);
            renameExpSend(_mids, _oldName, _newName, i_.getEffectSending());
            endRound(_mids, _oldName, _newName, i_.getEffectEndRound());
        }
    }

    private void renameExpSend(StringList _mids, String _oldName, String _newName, CustList<EffectWhileSendingWithStatistic> _ls) {
        if (!_ls.isEmpty()) {
            EffectWhileSendingWithStatistic e_ = _ls.first();
            if (!e_.isWithEffect()) {
                return;
            }
            EffectStatistic eff_ = e_.getEffect();
            eff_.setFail(rename(eff_.getFail(), _mids, _oldName, _newName));
            new ChangeStringValueUtil<Statistic>(eff_.getLocalFailStatis()).replaceExp(this, _mids, _oldName, _newName);
            new ChangeStringValueUtil<Statistic>(eff_.getLocalFailSwapBoostStatis()).replaceExp(this, _mids, _oldName, _newName);
        }
    }

    private void endRound(StringList _mids, String _oldName, String _newName, CustList<EffectEndRound> _ls) {
        if (!_ls.isEmpty()) {
            EffectEndRound e_ = _ls.first();
            e_.setFail(rename(e_.getFail(), _mids, _oldName, _newName));
            e_.setFailEndRound(rename(e_.getFailEndRound(), _mids, _oldName, _newName));
        }
    }

    private MonteCarloString patchLaw(StringList _mids, String _oldName, String _newName, MonteCarloString _law) {
        MonteCarloString newLaw_ = new MonteCarloString();
        for (EntryCust<String, LgInt> s: _law.getLaw().entryList()) {
            String ev_ = rename(s.getKey(), _mids, _oldName, _newName);
            newLaw_.addQuickEvent(ev_,s.getValue());
        }
        return PatchPkLawStringUtil.patch(newLaw_);
    }

    public String rename(String _el, StringList _mids, String _oldName, String _newName) {
        return EvolvedMathFactory.rename(_el, prefixedVar(), _mids, _oldName, _newName);
    }

    public boolean usedAbInExp(String _name) {
        return usedInExp(new StringList(),_name, KIND_AB);
    }

    public boolean usedItInExp(String _name) {
        return usedInExp(new StringList(),_name, KIND_IT);
    }

    public boolean usedPkInExp(String _name) {
        return usedInExp(new StringList(),_name, KIND_PK);
    }
    public boolean usedTypeInExp(String _name) {
        return usedInExp(typesPart(),_name, KIND_TY);
    }
    public boolean usedMoveInExp(String _name) {
        return usedInExp(movesPart(),_name, KIND_MV);
    }
    public boolean usedCategoryInExp(String _name) {
        return usedInExp(categoriesPart(),_name, KIND_CA);
    }
    public boolean usedStatusInExp(String _name) {
        return usedInExp(statusPart(),_name, KIND_ST);
    }

    public StringList typesPart() {
        return new StringList(StringUtil.concat(immuTypeAttCombattantEntrant(), SEP_BETWEEN_KEYS),
                StringUtil.concat(coeffEffBaseTypesCible(), SEP_BETWEEN_KEYS),
                StringUtil.concat(coeffEffBaseTypesCombattantEntrant(), SEP_BETWEEN_KEYS),
                StringUtil.concat(coeffEffBaseTypesFighter(), SEP_BETWEEN_KEYS),
                StringUtil.concat(coeffEffBaseTypesLanceur(), SEP_BETWEEN_KEYS),
                StringUtil.concat(immuTypeAttCible(), SEP_BETWEEN_KEYS));
    }
    public StringList categoriesPart() {
        return new StringList(StringUtil.concat(cibleDegatsRecus(),SEP_BETWEEN_KEYS),
                StringUtil.concat(cibleDegatsRecusTour(),SEP_BETWEEN_KEYS),
                StringUtil.concat(fighterDegatsRecus(),SEP_BETWEEN_KEYS),
                StringUtil.concat(fighterDegatsRecusTour(),SEP_BETWEEN_KEYS),
                StringUtil.concat(lanceurDegatsRecus(),SEP_BETWEEN_KEYS),
                StringUtil.concat(lanceurDegatsRecusTour(),SEP_BETWEEN_KEYS));
    }
    public StringList movesPart() {
        return new StringList(StringUtil.concat(cibleNbUtilisation(),SEP_BETWEEN_KEYS),
                StringUtil.concat(fighterNbUtilisation(),SEP_BETWEEN_KEYS),
                StringUtil.concat(lanceurNbUtilisation(),SEP_BETWEEN_KEYS),
                StringUtil.concat(ciblePp(),SEP_BETWEEN_KEYS),
                StringUtil.concat(fighterPp(),SEP_BETWEEN_KEYS),
                StringUtil.concat(lanceurPp(),SEP_BETWEEN_KEYS),
                StringUtil.concat(lanceurEffet(),SEP_BETWEEN_KEYS),
                StringUtil.concat(cibleEffet(),SEP_BETWEEN_KEYS),
                StringUtil.concat(nbTour(),SEP_BETWEEN_KEYS),
                StringUtil.concat(nbTourGlobal(),SEP_BETWEEN_KEYS),
                StringUtil.concat(equipeNbUtilisation(),SEP_BETWEEN_KEYS),
                StringUtil.concat(equipeAdvNbUtilisation(),SEP_BETWEEN_KEYS),
                StringUtil.concat(equipeAdvCombattantEntrantNbUtilisation(),SEP_BETWEEN_KEYS),
                StringUtil.concat(nbUtiliAttEqTour(),SEP_BETWEEN_KEYS));
    }
    public StringList statusPart() {
        return new StringList(StringUtil.concat(ciblePossedeStatutRelation(),SEP_BETWEEN_KEYS));
    }

    boolean usedInExp(StringList _mids, String _name, int _kind) {
        for (EntryCust<String, Item> o: items.entryList()) {
            if (checkContains(_name, _kind, o.getKey(), KIND_IT) && containsItem(_mids, _name, o.getValue())) {
                return true;
            }
        }
        for (EntryCust<String, AbilityData> a: abilities.entryList()) {
            if (checkContains(_name, _kind, a.getKey(), KIND_AB) && containsAbility(_mids, _name, a.getValue())) {
                return true;
            }
        }
        for (EntryCust<String, MoveData> m: moves.entryList()) {
            if (checkContains(_name, _kind, m.getKey(), KIND_MV) && containsMove(_mids, _name, m.getValue())) {
                return true;
            }
        }
        return containsEndRound(_mids, _name, _kind);
    }

    private boolean containsMove(StringList _mids, String _name, MoveData _m) {
        if (containsWord(_m.getAccuracy(), _mids, _name)) {
            return true;
        }
        for (Effect e: _m.getEffects()) {
            if (containsMoveEffect(_mids, _name, e)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkContains(String _name, int _kind, String _key, int _cst) {
        return _kind != _cst || !StringUtil.quickEq(_key, _name);
    }

    private boolean containsEndRound(StringList _mids, String _name, int _kind) {
        for (EntryCust<String, Status> s: status.entryList()) {
            for (EffectEndRoundStatus e: s.getValue().getEffectEndRound()) {
                if (checkContains(_name, _kind, s.getKey(), KIND_ST) && containsEndRound(_mids, _name, e)) {
                    return true;
                }
            }
        }
        for (EffectCombo e: combos.getEffects().values()) {
            for (EffectEndRoundFoe e2_: e.getEffectEndRound()) {
                if (containsEndRound(_mids, _name, e2_)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsItem(StringList _mids, String _name, Item _o) {
        return _o instanceof Ball && containsWord(((Ball) _o).getCatchingRate(), _mids, _name) || _o instanceof ItemForBattle && (containsWord(((ItemForBattle) _o).getMultPower(), _mids, _name) || containsWord(((ItemForBattle) _o).getMultDamage(), _mids, _name) || new ChangeStringValueUtil<Statistic>(((ItemForBattle) _o).getMultStat()).containsWord(this, _mids, _name) || !((ItemForBattle) _o).getEffectSending().isEmpty() && containsWordStat(_mids, _name, ((ItemForBattle) _o).getEffectSending().first()) || !((ItemForBattle) _o).getEffectEndRound().isEmpty() && containsEndRound(_mids, _name, ((ItemForBattle) _o).getEffectEndRound().first()));
    }

    private boolean containsAbility(StringList _mids, String _name, AbilityData _a) {
        if (containsWord(_a.getMultPower(), _mids, _name)) {
            return true;
        }
        if (containsWord(_a.getMultDamage(), _mids, _name)) {
            return true;
        }
        if (new ChangeStringValueUtil<Statistic>(_a.getMultStat()).containsWord(this, _mids, _name)) {
            return true;
        }
        if (new ChangeStringValueUtil<String>(_a.getFailStatus()).containsWord(this, _mids, _name)) {
            return true;
        }
        if (!_a.getEffectSending().isEmpty() && containsWordStat(_mids, _name, _a.getEffectSending().first())) {
            return true;
        }
        return !_a.getEffectEndRound().isEmpty() && containsEndRound(_mids, _name, _a.getEffectEndRound().first());
    }

    private boolean containsMoveEffect(StringList _mids, String _name, Effect _e) {
        if (containsWord(_e.getFail(), _mids, _name)) {
            return true;
        }
        if (_e instanceof EffectDamage) {
            EffectDamage eff_ = (EffectDamage) _e;
            if (containsWord(eff_.getPower(), _mids, _name)) {
                return true;
            }
            for (String s: eff_.getDamageLaw().events()) {
                if (containsWord(s, _mids, _name)) {
                    return true;
                }
            }
            return false;
        }
        return containsMoveEffectDef(_mids, _name, _e);
    }

    private boolean containsMoveEffectDef(StringList _mids, String _name, Effect _e) {
        return _e instanceof EffectTeamWhileSendFoe && (containsWord(((EffectTeamWhileSendFoe) _e).getDamageRateAgainstFoe(), _mids, _name) || containsWord(((EffectTeamWhileSendFoe) _e).getFailSending(), _mids, _name)) || _e instanceof EffectCommonStatistics && new ChangeStringValueUtil<Statistic>(((EffectCommonStatistics) _e).getCommonValue()).containsWord(this, _mids, _name) || _e instanceof EffectStatistic && containsWordStatSpec(_mids, _name, (EffectStatistic) _e) || _e instanceof EffectStatus && new ChangeStringValueUtil<String>(((EffectStatus) _e).getLocalFailStatus()).containsWord(this, _mids, _name) || _e instanceof EffectFullHpRate && containsWord(((EffectFullHpRate) _e).getRestoredHp(), _mids, _name) || _e instanceof EffectEndRound && containsWord(((EffectEndRound) _e).getFailEndRound(), _mids, _name);
    }

    private boolean containsWordStat(StringList _mids, String _name, EffectWhileSendingWithStatistic _eff) {
        return _eff.isWithEffect() && (containsWord(_eff.getEffect().getFail(), _mids, _name) || containsWordStatSpec(_mids, _name, _eff.getEffect()));
    }

    private boolean containsWordStatSpec(StringList _mids, String _name, EffectStatistic _eff) {
        return new ChangeStringValueUtil<Statistic>(_eff.getLocalFailStatis()).containsWord(this, _mids, _name) || new ChangeStringValueUtil<Statistic>(_eff.getLocalFailSwapBoostStatis()).containsWord(this, _mids, _name);
    }

    private boolean containsEndRound(StringList _mids, String _name, EffectEndRound _e) {
        return containsWord(_e.getFail(), _mids, _name) || containsWord(_e.getFailEndRound(), _mids, _name);
    }

    public boolean containsWord(String _el, StringList _mids, String _id) {
        return EvolvedMathFactory.usedId(_el,prefixedVar(), _mids, _id);
    }
    public boolean isUsed(String _id) {
        return moves.contains(_id) || items.contains(_id) || pokedex.contains(_id) || status.contains(_id) || abilities.contains(_id) || getTypes().containsObj(_id) || getAllCategories().containsObj(_id);
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

    public void setVarParamsMove(StringMap<StringList> _p) {
        this.varParamsMove = _p;
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

    public ImageArrayBaseSixtyFour getEndGameImage() {
        return endGameImage;
    }

    public void setEndGameImage(ImageArrayBaseSixtyFour _endGameImage) {
        endGameImage = _endGameImage;
    }

//    StringList getFilesWithSameNameDifferentCase() {
//        return filesWithSameNameDifferentCase;
//    }

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

    public StringMap<ImageArrayBaseSixtyFour> getTypesImages() {
        return typesImages;
    }

    public StringMap<ImageArrayBaseSixtyFour> getAnimStatis() {
        return animStatis;
    }

    public void setAnimStatis(StringMap<ImageArrayBaseSixtyFour> _a) {
        this.animStatis = _a;
    }

    public StringMap<ImageArrayBaseSixtyFour> getAnimStatus() {
        return animStatus;
    }

    public void setAnimStatus(StringMap<ImageArrayBaseSixtyFour> _a) {
        this.animStatus = _a;
    }

    public ImageArrayBaseSixtyFour getAnimAbsorb() {
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
