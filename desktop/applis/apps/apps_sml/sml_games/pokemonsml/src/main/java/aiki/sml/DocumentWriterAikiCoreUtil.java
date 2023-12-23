package aiki.sml;
import aiki.db.*;
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
import aiki.fight.status.effects.*;
import aiki.fight.util.*;
import aiki.game.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.enums.*;
import aiki.game.fight.util.*;
import aiki.game.params.*;
import aiki.game.params.enums.*;
import aiki.game.player.*;
import aiki.game.player.enums.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.util.*;
import code.images.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.sml.maths.*;
import code.sml.*;
import code.sml.core.*;
import code.util.*;
import aiki.facade.enums.*;
import code.util.core.*;

public final class DocumentWriterAikiCoreUtil {

    public static final String EMPTY_STRING = DataBase.EMPTY_STRING;

    public static final String POKEDEX_FOLDER = "pokedex";
    public static final String MOVES_FOLDER = "moves";
    public static final String ABILITIES_FOLDER = "abilities";
    public static final String STATUS_FOLDER = "status";

    public static final String ITEMS_FOLDER = "items";
    public static final String SEPARATOR_KEY_HEROS = ";";

    public static final String TAB = "\t";

    public static final String RETURN_LINE = "\n";
    public static final String CS = "CS";
    public static final String CT = "CT";
    public static final String CT_CS_FILE = "ct_cs.txt";
    public static final String CONST_NUM = "const_num.txt";
    public static final String CONST_NOT_NUM = "constantes_non_num.txt";
    public static final String TABLE_TYPES = "table_types.txt";
    public static final String LOIS_RANDOM = "lois_random.txt";
    public static final String COURBE_PTS_EXP = "courbe_pts_exp.txt";
    public static final String RATE_WON_POINTS = "rate_won_points.txt";
    public static final String COMBOS = "combos.xml";
    public static final String MAP_FILE = "map.xml";
    public static final String TRANSLATION_FOLDER = "translations";

    public static final String FIELD_ABILITIES = "0";
    public static final String FIELD_ABILITY = "1";
    public static final String FIELD_ACCESS_CONDITION = "2";
    public static final String FIELD_ACCESS_COORDS = "3";
    public static final String FIELD_ACCESS_POINT = "4";
    public static final String FIELD_ACCURACY = "5";
    public static final String FIELD_ACHIEVED_DISAPPEARED_PK = "6";
    public static final String FIELD_ACHIEVE_DISAPPEARED_PK_USING_MOVE = "7";
    public static final String FIELD_ACTED = "8";
    public static final String FIELD_ACTION = "9";
    public static final String FIELD_ADDED_TYPES = "10";
    public static final String FIELD_AGAINST_EVO = "11";
    public static final String FIELD_ALLOWED_SWITCH_PLACES_END_ROUND = "12";
    public static final String FIELD_ALLOW_CATCHING_KO = "13";
    public static final String FIELD_ALLY = "14";
    public static final String FIELD_ALLY_CHOICE = "15";
    public static final String FIELD_ALREADY_INVOKED_MOVES_ROUND = "16";
    public static final String FIELD_ATTACK = "17";
    public static final String FIELD_ATTACKS_SOON = "18";
    public static final String FIELD_ATTACK_LAST = "19";
    public static final String FIELD_ATTACK_TARGET_WITH_TYPES = "20";
    public static final String FIELD_AVG_NB_STEPS = "21";
    public static final String FIELD_BASE_EVO = "22";
    public static final String FIELD_BEAT_GYM_LEADER = "23";
    public static final String FIELD_BEAT_GYM_TRAINER = "24";
    public static final String FIELD_BEAT_TRAINER = "25";
    public static final String FIELD_BEGIN = "26";
    public static final String FIELD_BEGIN_ROUND = "27";
    public static final String FIELD_BELONGING_TO_PLAYER = "28";
    public static final String FIELD_BLOCKS = "29";
    public static final String FIELD_BONUS_STAT_RANK = "30";
    public static final String FIELD_BOOSTED_TYPES = "31";
    public static final String FIELD_BOOST_EXP = "32";
    public static final String FIELD_BOOST_STATIS = "33";
    public static final String FIELD_BOOST_STATIS_ONCE_KO_FOE = "34";
    public static final String FIELD_BOOST_STATIS_SUPER_EFF = "35";
    public static final String FIELD_BOOST_STATIS_TYPES = "36";
    public static final String FIELD_BOOST_STAT_RANK_END_ROUND = "37";
    public static final String FIELD_BOOST_STAT_RANK_PROTECTED = "38";
    public static final String FIELD_BOX = "39";
    public static final String FIELD_BREAK_FOE_IMMUNE = "40";
    public static final String FIELD_BREAK_PROTECTION = "41";
    public static final String FIELD_BUILDINGS = "42";
    public static final String FIELD_CANCELED_IF_USED = "43";
    public static final String FIELD_CANCEL_CHGT_STAT = "44";
    public static final String FIELD_CANCEL_CHGT_STAT_FOE_TEAM = "45";
    public static final String FIELD_CANCEL_CHGT_STAT_TEAM = "46";
    public static final String FIELD_CANCEL_EFFECTS = "47";
    public static final String FIELD_CANCEL_IMMU_TYPE = "48";
    public static final String FIELD_CANCEL_LOW_STAT = "49";
    public static final String FIELD_CANCEL_PROTECTING_ABILITIES = "50";
    public static final String FIELD_CANCEL_SEC_EFFECT_OTHER = "51";
    public static final String FIELD_CANCEL_SEC_EFFECT_OWNER = "52";
    public static final String FIELD_CANNOT_KO = "53";
    public static final String FIELD_CATCHING_BALL = "54";
    public static final String FIELD_CATCHING_RATE = "55";
    public static final String FIELD_CATEGORY = "56";
    public static final String FIELD_CATEGORY_BOOSTING = "57";
    public static final String FIELD_CAUGHT_EVOLUTIONS = "58";
    public static final String FIELD_CAUGHT_PK = "59";
    public static final String FIELD_CHANGED = "60";
    public static final String FIELD_CHANGED_TYPES_TERRAIN = "61";
    public static final String FIELD_CHANGE_TYPES = "62";
    public static final String FIELD_CHANGING_BOOST_TYPES = "63";
    public static final String FIELD_CHARACTERS = "64";
    public static final String FIELD_CHGT_TYPE_BY_DAMAGE = "65";
    public static final String FIELD_CHGT_TYPE_BY_ENV = "66";
    public static final String FIELD_CHGT_TYPE_BY_WEATHER = "67";
    public static final String FIELD_CHOICES = "68";
    public static final String FIELD_CHOICE_RESTRICTION = "69";
    public static final String FIELD_CHOSEN_HEALING_ITEM = "70";
    public static final String FIELD_CHOSEN_TARGETS = "71";
    public static final String FIELD_CH_LAW = "72";
    public static final String FIELD_CH_RATE = "73";
    public static final String FIELD_CLICK_BUTTONS_PAD = "74";
    public static final String FIELD_CLONE = "75";
    public static final String FIELD_CLOSEST_FOE_DAMAGE_RATE_HP = "76";
    public static final String FIELD_COMMON_VALUE = "77";
    public static final String FIELD_CONST_ABILITY = "78";
    public static final String FIELD_CONST_DAMAGE = "79";
    public static final String FIELD_CONST_TYPES = "80";
    public static final String FIELD_CONST_USER_CHOICE = "81";
    public static final String FIELD_CONST_VALUES_TYPE = "82";
    public static final String FIELD_COPIED_MOVES = "83";
    public static final String FIELD_COPYING_ABILITY = "84";
    public static final String FIELD_COPYING_MOVE_FOR_USER = "85";
    public static final String FIELD_COPYING_MOVE_FOR_USER_DEF = "86";
    public static final String FIELD_COPY_BOOST = "87";
    public static final String FIELD_COPY_MOVES_TYPES = "88";
    public static final String FIELD_COUNTERABLE_MOVE = "89";
    public static final String FIELD_COUNTER_FAIL = "90";
    public static final String FIELD_CURRENT_ABILITY = "91";
    public static final String FIELD_CURRENT_GENDER = "92";
    public static final String FIELD_CURRENT_MOVES = "93";
    public static final String FIELD_CURRENT_NAME = "94";
    public static final String FIELD_CURRENT_USER = "95";
    public static final String FIELD_DAMAGE_BY_STATUS = "96";
    public static final String FIELD_DAMAGE_END_ROUND = "97";
    public static final String FIELD_DAMAGE_LAW = "98";
    public static final String FIELD_DAMAGE_RATE_AGAINST_FOE = "99";
    public static final String FIELD_DAMAGE_RATE_INFLICTED_BY_TYPE = "100";
    public static final String FIELD_DAMAGE_RATE_LAW_FOE = "101";
    public static final String FIELD_DAMAGE_RATE_PLAYER = "102";
    public static final String FIELD_DAMAGE_RATE_RECOIL_FOE = "103";
    public static final String FIELD_DAMAGE_RATE_SUFFERED_BY_TYPE = "104";
    public static final String FIELD_DAMAGE_RECOIL = "105";
    public static final String FIELD_DAMAGE_SUFFERED_CATEG = "106";
    public static final String FIELD_DAMAGE_SUFFERED_CATEG_ROUND = "107";
    public static final String FIELD_DECREASE_NEC_STEPS_HATCH = "108";
    public static final String FIELD_DEFENSE = "109";
    public static final String FIELD_DELETED_BY_FOE_TYPES = "110";
    public static final String FIELD_DELETED_STATUS = "111";
    public static final String FIELD_DELETE_ALL_STATUS = "112";
    public static final String FIELD_DELETE_ALL_STATUS_ALLY = "113";
    public static final String FIELD_DELETE_PP = "114";
    public static final String FIELD_DIFFICULTY = "115";
    public static final String FIELD_DIFF_WINNING_EXP_PTS_FIGHT = "116";
    public static final String FIELD_DIRECT = "117";
    public static final String FIELD_DISABLED_EFF_IF_SWITCH = "118";
    public static final String FIELD_DISABLE_FOE_TEAM_EFFECTS = "119";
    public static final String FIELD_DISABLE_FOE_TEAM_STATUS = "120";
    public static final String FIELD_DISABLE_IMMU_AGAINST_TYPES = "121";
    public static final String FIELD_DISABLE_IMMU_FROM_MOVES = "122";
    public static final String FIELD_DISABLE_WEATHER = "123";
    public static final String FIELD_DISAPPEARED = "124";
    public static final String FIELD_DISAPPEAR_BEFORE_USE = "125";
    public static final String FIELD_DIVIDE_STATUS_ROUND = "126";
    public static final String FIELD_DRAINED_HP_BY_DAMAGE_RATE = "127";
    public static final String FIELD_DROPPED_STAT_DIRECT_MOVE = "128";
    public static final String FIELD_DUAL_FIGHTS = "129";
    public static final String FIELD_EFFECT = "130";
    public static final String FIELD_EFFECTS = "131";
    public static final String FIELD_EFFECTS_PARTNER = "132";
    public static final String FIELD_EFFECT_END_ROUND = "133";
    public static final String FIELD_EFFECT_SENDING = "134";
    public static final String FIELD_EFFICIENCY_MOVES = "135";
    public static final String FIELD_EGG_GROUPS = "136";
    public static final String FIELD_ENABLED_CHANGING_TYPES_MOVES = "137";
    public static final String FIELD_ENABLED_CLOSING = "138";
    public static final String FIELD_ENABLED_COUNTERING_MOVES = "139";
    public static final String FIELD_ENABLED_KEY_PAD = "140";
    public static final String FIELD_ENABLED_MOVES = "141";
    public static final String FIELD_ENABLED_MOVES_BY_GROUP = "142";
    public static final String FIELD_ENABLED_MOVES_CONST_CHOICES = "143";
    public static final String FIELD_ENABLED_MOVES_END_ROUND = "144";
    public static final String FIELD_ENABLED_MOVES_FOR_ALLY = "145";
    public static final String FIELD_ENABLED_MOVES_PROT = "146";
    public static final String FIELD_ENABLED_MOVES_UNPROT = "147";
    public static final String FIELD_ENABLED_MOVES_WHILE_SENDING_FOE = "148";
    public static final String FIELD_ENABLED_MOVES_WHILE_SENDING_FOE_USES = "149";
    public static final String FIELD_ENABLED_WEATHER = "150";
    public static final String FIELD_ENABLE_ANIMATION = "151";
    public static final String FIELD_ENABLE_MOVING_HEROS_ANIMATION = "152";
    public static final String FIELD_END_FIGHT_IF_ONE_TEAM_KO = "153";
    public static final String FIELD_END_ROUND_RANK = "154";
    public static final String FIELD_ENV_TYPE = "155";
    public static final String FIELD_EV = "156";
    public static final String FIELD_EVOLUTIONS = "157";
    public static final String FIELD_EVS = "158";
    public static final String FIELD_EVT_RATE = "159";
    public static final String FIELD_EXCHANGE_ABILITY = "160";
    public static final String FIELD_EXCHANGE_TYPES = "161";
    public static final String FIELD_EXIT_CITY = "162";
    public static final String FIELD_EXPORT = "163";
    public static final String FIELD_EXP_EVO = "164";
    public static final String FIELD_EXP_ITEM = "165";
    public static final String FIELD_EXP_RATE = "166";
    public static final String FIELD_FAIL = "167";
    public static final String FIELD_FAIL_END_ROUND = "168";
    public static final String FIELD_FAIL_SENDING = "169";
    public static final String FIELD_FAIL_STATUS = "170";
    public static final String FIELD_FIGHT = "171";
    public static final String FIELD_FIGHT_TYPE = "172";
    public static final String FIELD_FILE = "173";
    public static final String FIELD_FILE_NAME = "174";
    public static final String FIELD_FINAL_CHOSEN_MOVE = "175";
    public static final String FIELD_FIRST_CHOSEN_MOVE = "176";
    public static final String FIELD_FIRST_POKEMON = "177";
    public static final String FIELD_FIRST_POSIT_FOE_FIGHTERS = "178";
    public static final String FIELD_FIRST_POSIT_PLAYER_FIGHTERS = "179";
    public static final String FIELD_FOE_TRAINER = "180";
    public static final String FIELD_FORBIDDEN_BOOST = "181";
    public static final String FIELD_FORBIDDING_HEALING = "182";
    public static final String FIELD_FORBID_TARGET_USING_ITEM = "183";
    public static final String FIELD_FORBID_USE_BERRY_AGAINST_FOES = "184";
    public static final String FIELD_FORWARD_STATUS = "185";
    public static final String FIELD_GENDER = "186";
    public static final String FIELD_GENDER_REP = "187";
    public static final String FIELD_GERANCE = "188";
    public static final String FIELD_GERANTS = "189";
    public static final String FIELD_GIVE_ITEM_TO_ALLY_HAVING_USED = "190";
    public static final String FIELD_GROUND_PLACE = "191";
    public static final String FIELD_GROUND_PLACE_SUBST = "192";
    public static final String FIELD_GYM_LEADER = "193";
    public static final String FIELD_GYM_LEADER_COORDS = "194";
    public static final String FIELD_GYM_TRAINERS = "195";
    public static final String FIELD_HAPPINESS = "196";
    public static final String FIELD_HAPPINESS_HATCH = "197";
    public static final String FIELD_HATCHING = "198";
    public static final String FIELD_HATCHING_STEPS = "199";
    public static final String FIELD_HEALED_HP_RATE = "200";
    public static final String FIELD_HEALED_HP_RATE_BY_SWITCH = "201";
    public static final String FIELD_HEALED_MOVE_PP = "202";
    public static final String FIELD_HEALED_STATUS_BY_SWITCH = "203";
    public static final String FIELD_HEALING_ALL_MOVES_FULLPP = "204";
    public static final String FIELD_HEALING_ALL_MOVES_PP = "205";
    public static final String FIELD_HEALING_END_ROUND = "206";
    public static final String FIELD_HEALING_END_ROUND_GROUND = "207";
    public static final String FIELD_HEALING_KO = "208";
    public static final String FIELD_HEALING_MOVE_FULLPP = "209";
    public static final String FIELD_HEALING_TEAM = "210";
    public static final String FIELD_HEAL_AFTER = "211";
    public static final String FIELD_HEAL_HP = "212";
    public static final String FIELD_HEAL_HP_BY_OWNER_TYPES = "213";
    public static final String FIELD_HEAL_HP_BY_SUPER_EFF_MOVE = "214";
    public static final String FIELD_HEAL_HP_BY_TYPE_IF_WEATHER = "215";
    public static final String FIELD_HEAL_HP_BY_WEATHER = "216";
    public static final String FIELD_HEAL_HP_RATE = "217";
    public static final String FIELD_HEAL_HP_WHILE_USING_BERRY = "218";
    public static final String FIELD_HEAL_PP = "219";
    public static final String FIELD_HEAL_STATUS = "220";
    public static final String FIELD_HEIGHT = "221";
    public static final String FIELD_HEROS = "222";
    public static final String FIELD_HIDDEN_MOVES = "223";
    public static final String FIELD_HITS_LAW = "224";
    public static final String FIELD_HM = "225";
    public static final String FIELD_HOSTED_PK = "226";
    public static final String FIELD_HP = "227";
    public static final String FIELD_HP_RATE_CLONE = "228";
    public static final String FIELD_IGN_ABILITY = "229";
    public static final String FIELD_IGN_FOE_STATIS_BOOST = "230";
    public static final String FIELD_IGN_FOE_TEAM_MOVE = "231";
    public static final String FIELD_IGN_VAR_ACCUR_USER_NEG = "232";
    public static final String FIELD_IGN_VAR_EVAS_TARGET_POS = "233";
    public static final String FIELD_IGN_VAR_STAT_TARGET_POS = "234";
    public static final String FIELD_IGN_VAR_STAT_USER_NEG = "235";
    public static final String FIELD_IMAGE_FILE_NAME = "236";
    public static final String FIELD_IMAGE_MAXI_FILE_NAME = "237";
    public static final String FIELD_IMAGE_MINI_FILE_NAME = "238";
    public static final String FIELD_IMAGE_MINI_SECOND_TRAINER_FILE_NAME = "239";
    public static final String FIELD_IMMUNE_TYPES = "240";
    public static final String FIELD_IMMU_ABILITY = "241";
    public static final String FIELD_IMMU_AGAINST_TYPES = "242";
    public static final String FIELD_IMMU_ALLY_FROM_MOVES = "243";
    public static final String FIELD_IMMU_CH = "244";
    public static final String FIELD_IMMU_DAMAGE_ALLY_MOVES = "245";
    public static final String FIELD_IMMU_DAMAGE_RECOIL = "246";
    public static final String FIELD_IMMU_DAMAGE_TRAPPING_MOVES = "247";
    public static final String FIELD_IMMU_LOW_STAT = "248";
    public static final String FIELD_IMMU_LOW_STATIS = "249";
    public static final String FIELD_IMMU_LOW_STATIS_TYPES = "250";
    public static final String FIELD_IMMU_LOW_STAT_IF_STATUS = "251";
    public static final String FIELD_IMMU_MOVE = "252";
    public static final String FIELD_IMMU_MOVES = "253";
    public static final String FIELD_IMMU_MOVE_TYPES_BY_WEATHER = "254";
    public static final String FIELD_IMMU_RECHARGE_ROUND = "255";
    public static final String FIELD_IMMU_STATUS = "256";
    public static final String FIELD_IMMU_STATUS_BEGIN_ROUND = "257";
    public static final String FIELD_IMMU_STATUS_TYPES = "258";
    public static final String FIELD_IMMU_SUFFERED_DAMAGE_LOW_EFF = "259";
    public static final String FIELD_IMMU_TYPES = "260";
    public static final String FIELD_IMMU_WEATHER = "261";
    public static final String FIELD_INCREASED_PRIO = "262";
    public static final String FIELD_INCREASED_PRIO_TYPES = "263";
    public static final String FIELD_INCREASING_MAX_NB_ROUND_GLOBAL_MOVE = "264";
    public static final String FIELD_INCREASING_MAX_NB_ROUND_TEAM_MOVE = "265";
    public static final String FIELD_INCREASING_MAX_NB_ROUND_TRAP = "266";
    public static final String FIELD_INCREMENTING_DAMAGE_BY_ROUNDS = "267";
    public static final String FIELD_INCREMENTING_END_ROUND = "268";
    public static final String FIELD_INCREMENT_END_ROUND = "269";
    public static final String FIELD_INCR_USER_ACCURACY = "270";
    public static final String FIELD_INDEX_APPARITION = "271";
    public static final String FIELD_INDEX_PERIOD = "272";
    public static final String FIELD_INDEX_PERIOD_FISHING = "273";
    public static final String FIELD_INDEX_STEP = "274";
    public static final String FIELD_INFLICTED_RATE_HP_TARGET = "275";
    public static final String FIELD_INFLICTING_DAMAGE_INSTEAD_OF_SUFFERING = "276";
    public static final String FIELD_INVENTORY = "277";
    public static final String FIELD_INVOKED_MOVE_TERRAIN = "278";
    public static final String FIELD_INVOKING_ALLY_MOVE = "279";
    public static final String FIELD_INVOKING_MOVE_BUT_USER = "280";
    public static final String FIELD_INVOKING_MOVE_BY_USER_TYPES = "281";
    public static final String FIELD_INVOKING_SUFFERED_MOVE = "282";
    public static final String FIELD_INVOKING_TARGET_CHOSEN_MOVE = "283";
    public static final String FIELD_INVOKING_TARGET_SUCCESFUL_MOVE = "284";
    public static final String FIELD_INVOKING_USER_MOVE_WHILE_SLEEP = "285";
    public static final String FIELD_ITEM = "286";
    public static final String FIELD_ITEMS = "287";
    public static final String FIELD_IV_FOE = "288";
    public static final String FIELD_IV_PLAYER = "289";
    public static final String FIELD_KEPT_MOVES = "290";
    public static final String FIELD_KO_USER_HEAL_SUBST = "291";
    public static final String FIELD_LAST_ROM = "292";
    public static final String FIELD_LAST_SAVED_GAME = "293";
    public static final String FIELD_LAST_SUCCESSFUL_MOVE = "294";
    public static final String FIELD_LAST_SUFFERED_MOVE = "295";
    public static final String FIELD_LAST_SUFFERED_MOVE_TYPES = "296";
    public static final String FIELD_LAST_USED_ITEM = "297";
    public static final String FIELD_LAST_USED_MOVE = "298";
    public static final String FIELD_LAW = "299";
    public static final String FIELD_LAW_BOOST = "300";
    public static final String FIELD_LAW_FOR_ATTACK_FIRST = "301";
    public static final String FIELD_LAW_FOR_ENABLING_EFFECT = "302";
    public static final String FIELD_LAW_FOR_FULL_HEAL_IF_MOVE = "303";
    public static final String FIELD_LAW_FOR_USING_A_MOVE = "304";
    public static final String FIELD_LAW_FOR_USING_A_MOVE_IF_FOE = "305";
    public static final String FIELD_LAW_FOR_USING_A_MOVE_NB_ROUND = "306";
    public static final String FIELD_LAW_STATUS = "307";
    public static final String FIELD_LEFT_USER_HP = "308";
    public static final String FIELD_LEGENDARY_PKS = "309";
    public static final String FIELD_LEVEL = "310";
    public static final String FIELD_LEVELS = "311";
    public static final String FIELD_LEV_MOVES = "312";
    public static final String FIELD_LINKS_OTHER_LEVELS = "313";
    public static final String FIELD_LINKS_WITH_CAVES = "314";
    public static final String FIELD_LINKS_WITH_OTHER_PLACES = "315";
    public static final String FIELD_LOAD_HOME_FOLDER = "316";
    public static final String FIELD_LOAD_LAST_GAME = "317";
    public static final String FIELD_LOAD_LAST_ROM = "318";
    public static final String FIELD_LOCAL_FAIL_STATIS = "319";
    public static final String FIELD_LOCAL_FAIL_STATUS = "320";
    public static final String FIELD_LOCAL_FAIL_SWAP_BOOST_STATIS = "321";
    public static final String FIELD_LOST_OBJECTS = "322";
    public static final String FIELD_LOW_STAT_FOE_HIT = "323";
    public static final String FIELD_MAX_HP_FOR_USING_BERRY = "324";
    public static final String FIELD_MAX_HP_HEALING_HP = "325";
    public static final String FIELD_MAX_HP_HEALING_HP_RATE = "326";
    public static final String FIELD_MAX_STATISTICS_IF_CH = "327";
    public static final String FIELD_MEMBERS = "328";
    public static final String FIELD_MINI_MAP = "329";
    public static final String FIELD_MONEY = "330";
    public static final String FIELD_MOVE = "331";
    public static final String FIELD_MOVES = "332";
    public static final String FIELD_MOVES_ABILITIES_EVOS = "333";
    public static final String FIELD_MOVES_ANTICIPATION = "334";
    public static final String FIELD_MOVES_NOT_TO_BE_COPIED = "335";
    public static final String FIELD_MOVES_NOT_TO_BE_INVOKED = "336";
    public static final String FIELD_MOVES_TO_BE_LEARNT = "337";
    public static final String FIELD_MOVES_USED_BY_TARGETED_FIGHTERS = "338";
    public static final String FIELD_MOVE_FCT_ENV = "339";
    public static final String FIELD_MOVE_OBJECT = "340";
    public static final String FIELD_MOVE_TUTORS = "341";
    public static final String FIELD_MULT = "342";
    public static final String FIELD_MULTIPLICITY_FIGHT = "343";
    public static final String FIELD_MULT_ACCURACY = "344";
    public static final String FIELD_MULT_ALLY_DAMAGE = "345";
    public static final String FIELD_MULT_DAMAGE = "346";
    public static final String FIELD_MULT_DAMAGE_AGAINST = "347";
    public static final String FIELD_MULT_DAMAGE_AGAINST_FOE = "348";
    public static final String FIELD_MULT_DAMAGE_CH = "349";
    public static final String FIELD_MULT_DAMAGE_FOE = "350";
    public static final String FIELD_MULT_DAMAGE_PREPA_ROUND = "351";
    public static final String FIELD_MULT_DAMAGE_STATUS = "352";
    public static final String FIELD_MULT_DAMAGE_TYPES_MOVES = "353";
    public static final String FIELD_MULT_DRAINED_HP = "354";
    public static final String FIELD_MULT_EFFECT_LOVING_ALLY = "355";
    public static final String FIELD_MULT_EVT_RATE_CH = "356";
    public static final String FIELD_MULT_EVT_RATE_SEC_EFF = "357";
    public static final String FIELD_MULT_EVT_RATE_SEC_EFFECT_OWNER = "358";
    public static final String FIELD_MULT_FIGHT = "359";
    public static final String FIELD_MULT_FOES_DAMAGE = "360";
    public static final String FIELD_MULT_MOVE_POWER_FCT_TYPE = "361";
    public static final String FIELD_MULT_POWER = "362";
    public static final String FIELD_MULT_POWER_MOVES = "363";
    public static final String FIELD_MULT_POWER_MOVES_TYPES_GLOBAL = "364";
    public static final String FIELD_MULT_STAB = "365";
    public static final String FIELD_MULT_STAT = "366";
    public static final String FIELD_MULT_STATISTIC = "367";
    public static final String FIELD_MULT_STATISTIC_FOE = "368";
    public static final String FIELD_MULT_STAT_ALLY = "369";
    public static final String FIELD_MULT_STAT_IF_CAT = "370";
    public static final String FIELD_MULT_STAT_IF_CONTAINS_TYPE = "371";
    public static final String FIELD_MULT_STAT_IF_DAMAGE_CAT = "372";
    public static final String FIELD_MULT_STAT_IF_DAMGE_TYPE = "373";
    public static final String FIELD_MULT_STAT_IF_KO_FOE = "374";
    public static final String FIELD_MULT_STAT_IF_LOW_STAT = "375";
    public static final String FIELD_MULT_STAT_IF_STATUT_RANK = "376";
    public static final String FIELD_MULT_STAT_POKEMON_RANK = "377";
    public static final String FIELD_MULT_STAT_RANK = "378";
    public static final String FIELD_MULT_SUFFERED_DAMAGE_SUPER_EFF = "379";
    public static final String FIELD_MULT_TRAPPING_DAMAGE = "380";
    public static final String FIELD_MULT_VAR_BOOST = "381";
    public static final String FIELD_MULT_WEIGHT = "382";
    public static final String FIELD_MULT_WINNING_EV = "383";
    public static final String FIELD_MULT_WINNING_EXP = "384";
    public static final String FIELD_MULT_WINNING_HAPPINESS = "385";
    public static final String FIELD_MUMY = "386";
    public static final String FIELD_NAME = "387";
    public static final String FIELD_NAMES = "388";
    public static final String FIELD_NB_FLEE_ATTEMPT = "389";
    public static final String FIELD_NB_HITS = "390";
    public static final String FIELD_NB_KO_PREVIOUS_ROUND = "391";
    public static final String FIELD_NB_KO_ROUND = "392";
    public static final String FIELD_NB_PREPA_ROUND = "393";
    public static final String FIELD_NB_REPEATING_SUCCESSFUL_MOVES = "394";
    public static final String FIELD_NB_ROUNDS = "395";
    public static final String FIELD_NB_STEPS = "396";
    public static final String FIELD_NB_STEPS_TEAM_LEAD = "397";
    public static final String FIELD_NB_USED_PP = "398";
    public static final String FIELD_NB_USES_MOVES = "399";
    public static final String FIELD_NB_USES_MOVES_ROUND = "400";
    public static final String FIELD_NEEDING_TO_RECHARGE = "401";
    public static final String FIELD_NEXT_LEVEL_TARGET = "402";
    public static final String FIELD_NICKNAME = "403";
    public static final String FIELD_PLACE = "404";
    public static final String FIELD_PLACES = "405";
    public static final String FIELD_PLATE = "406";
    public static final String FIELD_PLAYER = "407";
    public static final String FIELD_PLAYER_COORDS = "408";
    public static final String FIELD_PLAYER_FIGHTERS_AGAINST_FOE = "409";
    public static final String FIELD_PLAYER_MAX_NUMBER_FRONT_FIGHTERS = "410";
    public static final String FIELD_PLAYER_ORIENTATION = "411";
    public static final String FIELD_POINT_VIEW_CHANGEMENT = "412";
    public static final String FIELD_POKEMON = "413";
    public static final String FIELD_POWER = "414";
    public static final String FIELD_PP = "415";
    public static final String FIELD_PP_FOR_MOVES = "416";
    public static final String FIELD_PREVENT_STATUS = "417";
    public static final String FIELD_PRICE = "418";
    public static final String FIELD_PRIORITY = "419";
    public static final String FIELD_PRIVATE_MOVES = "420";
    public static final String FIELD_PROTECTED_AGAINST_MOVE_TYPES = "421";
    public static final String FIELD_PROTECT_AGAINST_CH = "422";
    public static final String FIELD_PROTECT_AGAINST_KO = "423";
    public static final String FIELD_PROTECT_AGAINST_KO_IF_FULL_HP = "424";
    public static final String FIELD_PROTECT_AGAINST_LOW_STAT = "425";
    public static final String FIELD_PROTECT_AGAINST_STATUS = "426";
    public static final String FIELD_PROTECT_FAIL = "427";
    public static final String FIELD_PROT_SINGLE = "428";
    public static final String FIELD_PROT_SINGLE_AGAINST_KO = "429";
    public static final String FIELD_PROT_TEAM_AGAINST_DAMAGE_MOVES = "430";
    public static final String FIELD_PROT_TEAM_AGAINST_MULT_TARGETS = "431";
    public static final String FIELD_PROT_TEAM_AGAINST_PRIO = "432";
    public static final String FIELD_PROT_TEAM_AGAINST_STATUS_MOVES = "433";
    public static final String FIELD_PT = "434";
    public static final String FIELD_PUTTING_KO = "435";
    public static final String FIELD_RANDOM_WILD_FIGHT = "436";
    public static final String FIELD_RAND_MAX = "437";
    public static final String FIELD_RANK_INCREMENT_NB_ROUND = "438";
    public static final String FIELD_RANK_LEAGUE = "439";
    public static final String FIELD_RATE_DAMAGE = "440";
    public static final String FIELD_RATE_DAMAGE_FUNCTION_OF_NB_ROUNDS = "441";
    public static final String FIELD_RATE_HP = "442";
    public static final String FIELD_RATE_INVOKATION_MOVE = "443";
    public static final String FIELD_RATE_LOOSE_MONEY_WIN = "444";
    public static final String FIELD_RATE_WINNING_EXP_PTS_FIGHT = "445";
    public static final String FIELD_RATE_WIN_MONEY_BASE = "446";
    public static final String FIELD_RECHARGE_ROUND = "447";
    public static final String FIELD_RECOIL_DAMAGE = "448";
    public static final String FIELD_RECOIL_DAMAGE_FOE = "449";
    public static final String FIELD_REMAINING_HP = "450";
    public static final String FIELD_REMAINING_REPEL_STEPS = "451";
    public static final String FIELD_REPEATED_ROUNDS_LAW = "452";
    public static final String FIELD_REPEAT_ROUND_LAW = "453";
    public static final String FIELD_REPLACING_TYPES = "454";
    public static final String FIELD_REQUIRED_STATUS = "455";
    public static final String FIELD_REQUIRED_SUCCESSFUL_EFFECTS = "456";
    public static final String FIELD_RESTORED_HP = "457";
    public static final String FIELD_RESTORED_HP_RATE_LOVED_ALLY = "458";
    public static final String FIELD_RESTORED_MOVES_END_FIGHT = "459";
    public static final String FIELD_REVERSE_EFFECTS_POWER_MOVES_TYPES_GLOBAL = "460";
    public static final String FIELD_REVERSE_ORDER_OF_SORT_BY_SPEED = "461";
    public static final String FIELD_REWARD = "462";
    public static final String FIELD_ROOMS = "463";
    public static final String FIELD_SAVEDLINKS = "464";
    public static final String FIELD_SAVE_GAME_AT_EXIT = "465";
    public static final String FIELD_SAVE_HOME_FOLDER = "466";
    public static final String FIELD_SCREEN_HEIGHT = "467";
    public static final String FIELD_SCREEN_WIDTH = "468";
    public static final String FIELD_SECOND_POKEMON = "469";
    public static final String FIELD_SEC_EFFECTS_BY_ITEM = "470";
    public static final String FIELD_SEC_EFFECT_IF_NO_DAMAGE = "471";
    public static final String FIELD_SELL = "472";
    public static final String FIELD_SEX = "473";
    public static final String FIELD_SIDE_LENGTH = "474";
    public static final String FIELD_SINGLE_STATUS = "475";
    public static final String FIELD_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL = "476";
    public static final String FIELD_SLOWING = "477";
    public static final String FIELD_SPACE_BETWEEN_LEFT_AND_HEROS = "478";
    public static final String FIELD_SPACE_BETWEEN_TOP_AND_HEROS = "479";
    public static final String FIELD_STATE = "480";
    public static final String FIELD_STATISTICS = "481";
    public static final String FIELD_STATIS_ATT = "482";
    public static final String FIELD_STATIS_BASE = "483";
    public static final String FIELD_STATIS_BOOST = "484";
    public static final String FIELD_STATIS_DEF = "485";
    public static final String FIELD_STATIS_VAR_RANK = "486";
    public static final String FIELD_STATUS = "487";
    public static final String FIELD_STATUS_BY_NB_USES = "488";
    public static final String FIELD_STATUS_FROM_USER = "489";
    public static final String FIELD_STATUS_RELAT = "490";
    public static final String FIELD_STATUS_TYPE = "491";
    public static final String FIELD_STEPS = "492";
    public static final String FIELD_STILL_ENABLED_MOVES = "493";
    public static final String FIELD_STILL_POSSIBLE_FLEE = "494";
    public static final String FIELD_STONE = "495";
    public static final String FIELD_STOPPABLE_MOVE_KO_SINGLE = "496";
    public static final String FIELD_STOPPABLE_MOVE_MULTI = "497";
    public static final String FIELD_STOPPABLE_MOVE_PRIO = "498";
    public static final String FIELD_STOPPABLE_MOVE_SOLO = "499";
    public static final String FIELD_STORAGE_COORDS = "500";
    public static final String FIELD_SUBSTITUTE = "501";
    public static final String FIELD_SUCCESSFUL_MOVE = "502";
    public static final String FIELD_SUCCESSFUL_MOVES_ROUND = "503";
    public static final String FIELD_SUFFERING_DAMAGE_DIRECT_MOVE = "504";
    public static final String FIELD_SUFFERING_DAMAGE_TYPES = "505";
    public static final String FIELD_SUMMING_USER_TEAM_OK_FIGHTER = "506";
    public static final String FIELD_SWAP_BOOST_STATIS = "507";
    public static final String FIELD_SWITCH_TYPE = "508";
    public static final String FIELD_SYNCHRO_STATUS = "509";
    public static final String FIELD_TAKEN_OBJECTS = "510";
    public static final String FIELD_TAKEN_POKEMON = "511";
    public static final String FIELD_TAKE_ITEM_BY_DAMAGING_MOVE = "512";
    public static final String FIELD_TARGET_ATTACKS_LAST = "513";
    public static final String FIELD_TARGET_CHOICE = "514";
    public static final String FIELD_TARGET_DEFENSE = "515";
    public static final String FIELD_TEAM = "516";
    public static final String FIELD_TEAMS = "517";
    public static final String FIELD_TEAMS_REWARDS = "518";
    public static final String FIELD_TEAM_MOVE = "519";
    public static final String FIELD_TECHNICAL_MOVES = "520";
    public static final String FIELD_THIEVABLE_MOVE = "521";
    public static final String FIELD_THIEVED_HP_RATE_TARGET_TO_USER = "522";
    public static final String FIELD_TILE_FILE_NAME = "523";
    public static final String FIELD_TM = "524";
    public static final String FIELD_TRACKING_MOVES = "525";
    public static final String FIELD_TRAINER = "526";
    public static final String FIELD_TRAINER_COORDS = "527";
    public static final String FIELD_TRAPPING_MOVES = "528";
    public static final String FIELD_TYPE = "529";
    public static final String FIELD_TYPES = "530";
    public static final String FIELD_TYPES_BY_OWNED_ITEM = "531";
    public static final String FIELD_TYPES_BY_WEATHER = "532";
    public static final String FIELD_TYPES_PK = "533";
    public static final String FIELD_TYPE_FOR_MOVES = "534";
    public static final String FIELD_UNLOCKED_CITY = "535";
    public static final String FIELD_UNUSABLE_ITEM = "536";
    public static final String FIELD_UNUSABLE_MOVES = "537";
    public static final String FIELD_USED_BALL_CATCHING = "538";
    public static final String FIELD_USED_ITEMS_WHILE_ROUND = "539";
    public static final String FIELD_USED_MOVE_LAST_ROUND = "540";
    public static final String FIELD_USER_ATTACK = "541";
    public static final String FIELD_USER_STATUS_END_ROUND = "542";
    public static final String FIELD_USING_ITEM = "543";
    public static final String FIELD_VISITED_PLACES = "544";
    public static final String FIELD_WEATHER = "545";
    public static final String FIELD_WEDDING_ALLY = "546";
    public static final String FIELD_WEIGHT = "547";
    public static final String FIELD_WIDTH = "548";
    public static final String FIELD_WILD_POKEMON = "549";
    public static final String FIELD_WILD_POKEMON_AREAS = "550";
    public static final String FIELD_WILD_POKEMON_FISHING = "551";
    public static final String FIELD_WINNING_MONEY = "552";
    public static final String FIELD_WINNING_RATE_BY_SUM_TARGET_USER = "553";
    public static final String FIELD_WIN_EV_FIGHT = "554";
    public static final String FIELD_WIN_PP = "555";
    public static final String FIELD_WIN_TRAINER_EXP = "556";
    public static final String FIELD_WITHOUT_FAIL = "557";
    public static final String FIELD_WON_EXP = "558";
    public static final String FIELD_WON_EXP_SINCE_LAST_LEVEL = "559";
    public static final String FIELD_ZIPPED_ROM = "560";
    public static final String TYPE_BALL = "0";
    public static final String TYPE_BERRY = "1";
    public static final String TYPE_BOOST = "2";
    public static final String TYPE_EVOLVING_ITEM = "3";
    public static final String TYPE_EVOLVING_STONE = "4";
    public static final String TYPE_FOSSIL = "5";
    public static final String TYPE_HEALING_HP = "6";
    public static final String TYPE_HEALING_HP_STATUS = "7";
    public static final String TYPE_HEALING_PP = "8";
    public static final String TYPE_HEALING_SIMPLE_ITEM = "9";
    public static final String TYPE_HEALING_SIMPLE_STATUS = "10";
    public static final String TYPE_ITEM_FOR_BATTLE = "11";
    public static final String TYPE_REPEL = "12";
    public static final String TYPE_SELLING_ITEM = "13";
    public static final String TYPE_DAMAGING_MOVE_DATA = "0";
    public static final String TYPE_STATUS_MOVE_DATA = "1";
    public static final String TYPE_EFFECT_ACCURACY = "0";
    public static final String TYPE_EFFECT_ALLY = "1";
    public static final String TYPE_EFFECT_BATON_PASS = "2";
    public static final String TYPE_EFFECT_CLONE = "3";
    public static final String TYPE_EFFECT_WHILE_SENDING = "4";
    public static final String TYPE_EFFECT_COMMON_STATISTICS = "5";
    public static final String TYPE_EFFECT_COPY_FIGHTER = "6";
    public static final String TYPE_EFFECT_COPY_MOVE = "7";
    public static final String TYPE_EFFECT_COUNTER_ATTACK = "8";
    public static final String TYPE_EFFECT_DAMAGE = "9";
    public static final String TYPE_EFFECT_DAMAGE_RATE = "10";
    public static final String TYPE_EFFECT_END_ROUND_FOE = "11";
    public static final String TYPE_EFFECT_END_ROUND_GLOBAL = "12";
    public static final String TYPE_EFFECT_END_ROUND_INDIVIDUAL = "13";
    public static final String TYPE_EFFECT_END_ROUND_MULTI_RELATION = "14";
    public static final String TYPE_EFFECT_END_ROUND_POSITION_RELATION = "15";
    public static final String TYPE_EFFECT_END_ROUND_POSITION_TARGET_RELATION = "16";
    public static final String TYPE_EFFECT_END_ROUND_SINGLE_RELATION = "17";
    public static final String TYPE_EFFECT_END_ROUND_SINGLE_STATUS = "18";
    public static final String TYPE_EFFECT_END_ROUND_STATUS_RELATION = "19";
    public static final String TYPE_EFFECT_END_ROUND_TEAM = "20";
    public static final String TYPE_EFFECT_FULL_HP_RATE = "21";
    public static final String TYPE_EFFECT_GLOBAL = "22";
    public static final String TYPE_EFFECT_INVOKE = "23";
    public static final String TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER = "24";
    public static final String TYPE_EFFECT_MULT_USED_MOVE_POWER = "25";
    public static final String TYPE_EFFECT_ORDER = "26";
    public static final String TYPE_EFFECT_PROTECT_FROM_TYPES = "27";
    public static final String TYPE_EFFECT_PROTECTION = "28";
    public static final String TYPE_EFFECT_REMAINED_HP_RATE = "29";
    public static final String TYPE_EFFECT_RESTRICTION = "30";
    public static final String TYPE_EFFECT_STATISTIC = "31";
    public static final String TYPE_EFFECT_STATUS = "32";
    public static final String TYPE_EFFECT_SWITCH_ABILITIES = "33";
    public static final String TYPE_EFFECT_SWITCH_ITEMS = "34";
    public static final String TYPE_EFFECT_SWITCH_MOVE_TYPES = "35";
    public static final String TYPE_EFFECT_SWITCH_POINT_VIEW = "36";
    public static final String TYPE_EFFECT_SWITCH_POSITION = "37";
    public static final String TYPE_EFFECT_SWITCH_TYPES = "38";
    public static final String TYPE_EFFECT_TEAM = "39";
    public static final String TYPE_EFFECT_TEAM_WHILE_SEND_FOE = "40";
    public static final String TYPE_EFFECT_UNPROTECT_FROM_TYPES = "41";
    public static final String TYPE_EFFECT_VAR_P_P = "42";
    public static final String TYPE_EFFECT_WIN_MONEY = "43";
    public static final String TYPE_EFFECT_WHILE_SENDING_WITH_STATISTIC = "44";
    public static final String TYPE_EVOLUTION_HAPPINESS = "0";
    public static final String TYPE_EVOLUTION_ITEM = "1";
    public static final String TYPE_EVOLUTION_LEVEL_GENDER = "2";
    public static final String TYPE_EVOLUTION_LEVEL_SIMPLE = "3";
    public static final String TYPE_EVOLUTION_MOVE = "4";
    public static final String TYPE_EVOLUTION_MOVE_TYPE = "5";
    public static final String TYPE_EVOLUTION_STONE_GENDER = "6";
    public static final String TYPE_EVOLUTION_STONE_SIMPLE = "7";
    public static final String TYPE_EVOLUTION_TEAM = "8";
    public static final String TYPE_STATUS_BEGIN_ROUND_AUTO_DAMAGE = "2";
    public static final String TYPE_STATUS_BEGIN_ROUND_SIMPLE = "1";
    public static final String TYPE_STATUS_SIMPLE = "0";
    public static final String TYPE_ACTION = "0";
    public static final String TYPE_ACTION_HEAL_MOVE = "1";
    public static final String TYPE_ACTION_MOVE = "2";
    public static final String TYPE_ACTION_SIMPLE_HEAL = "3";
    public static final String TYPE_ACTION_SWITCH = "4";
    public static final String TYPE_GYM = "0";
    public static final String TYPE_POKEMON_CENTER = "1";
    public static final String TYPE_DEALER_ITEM = "0";
    public static final String TYPE_GERANT_POKEMON = "1";
    public static final String TYPE_GYM_LEADER = "2";
    public static final String TYPE_GYM_TRAINER = "3";
    public static final String TYPE_SELLER = "4";
    public static final String TYPE_TEMP_TRAINER = "5";
    public static final String TYPE_TRAINER_LEAGUE = "6";
    public static final String TYPE_TRAINER_MULTI_FIGHTS = "7";
    public static final String TYPE_CAVE = "0";
    public static final String TYPE_CITY = "1";
    public static final String TYPE_LEAGUE = "2";
    public static final String TYPE_ROAD = "3";
    public static final String TYPE_EGG = "1";
    public static final String TYPE_POKEMON_PLAYER = "PokemonPlayer";
    public static final String TYPE_POKEMON_PLAYER_INNER = "0";


    private DocumentWriterAikiCoreUtil() {
    }

    public static StringMap<String> getTextFiles(DataBase _d) {
        StringMap<String> files_ = new StringMap<String>();
        for (String n : _d.getPokedex().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setPokemonData(_d.getPokedex()
                    .getVal(n));
            files_.put(StringUtil.concat(POKEDEX_FOLDER, DataBase.SEPARATOR_FILES, n,
                    DataBase.FILES_RES_EXT), file_);
        }
        for (String n : _d.getMoves().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setMoveData(_d.getMoves()
                    .getVal(n));
            files_.put(StringUtil.concat(MOVES_FOLDER, DataBase.SEPARATOR_FILES, n,
                    DataBase.FILES_RES_EXT), file_);
        }
        for (String n : _d.getItems().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setItem(_d.getItems().getVal(n));
            files_.put(StringUtil.concat(ITEMS_FOLDER, DataBase.SEPARATOR_FILES, n,
                    DataBase.FILES_RES_EXT), file_);
        }
        for (String n : _d.getAbilities().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setAbilityData(_d.getAbilities()
                    .getVal(n));
            files_.put(StringUtil.concat(ABILITIES_FOLDER, DataBase.SEPARATOR_FILES, n,
                    DataBase.FILES_RES_EXT), file_);
        }
        for (String n : _d.getStatus().getKeys()) {
            String file_ = DocumentWriterAikiCoreUtil.setStatus(_d.getStatus()
                    .getVal(n));
            files_.put(StringUtil.concat(STATUS_FOLDER, DataBase.SEPARATOR_FILES, n,
                    DataBase.FILES_RES_EXT), file_);
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
        files_.put(StringUtil.concat(DataBase.TYPES_COLOR_CODE, DataBase.IMG_FILES_RES_EXT_TXT),
                StringUtil.join(lines_, RETURN_LINE));
        lines_ = new StringList();
        lines_.add(StringUtil.concat(DataBase.DEF_MOVE, TAB, _d.getDefMove()));
        lines_.add(StringUtil.concat(DataBase.RATE_BOOST, TAB, _d.getRateBoost()));
        lines_.add(StringUtil.concat(DataBase.RATE_BOOST_CRITICAL_HIT, TAB,
                _d.getRateBoostCriticalHit()));
        lines_.add(StringUtil.concat(DataBase.RATE_FLEEING, TAB, _d.getRateFleeing()));
        lines_.add(StringUtil.concat(DataBase.RATE_CATCHING, TAB, _d.getRateCatching()));
        lines_.add(StringUtil.concat(DataBase.BALL_DEF, TAB, _d.getBallDef()));
        lines_.add(StringUtil.concat(DataBase.DEFAULT_EGG_GROUP, TAB, _d.getDefaultEggGroup()));
        lines_.add(StringUtil.concat(DataBase.DAMAGE_FORMULA, TAB, _d.getDamageFormula()));
        lines_.add(StringUtil.concat(DataBase.DEF_CAT, TAB, _d.getDefCategory()));

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
            linesCourbes_.add(StringUtil.concat(c.getExpName(), TAB,
                    _d.getExpGrowth().getVal(c)));
        }
        files_.put(COURBE_PTS_EXP, StringUtil.join(linesCourbes_, RETURN_LINE));
        StringList rates_ = new StringList();
        for (DifficultyWinPointsFight c : _d.getRates().getKeys()) {
            rates_.add(StringUtil.concat(c.getWinName(), TAB, _d.getRates().getVal(c)));
        }
        files_.put(RATE_WON_POINTS, StringUtil.join(rates_, RETURN_LINE));
        StringList linesLaws_ = new StringList();
        for (DifficultyModelLaw k : _d.getLawsDamageRate().getKeys()) {
            LawNumber value_ = _d.getLawsDamageRate().getVal(k);
            StringList lawValues_ = new StringList();
            for (Rate event_ : value_.getLaw().events()) {
                lawValues_.add(StringUtil.concat(event_.toNumberString(),
                        DataBase.SEPARATOR_RAND_EVENTS, value_.getLaw().rate(event_)
                                .toNumberString()));
            }
            linesLaws_.add(StringUtil.concat(k.getModelName(), TAB,
                    StringUtil.join(lawValues_, DataBase.SEPARATOR_RAND)));
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_CATEGORIES);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedGenders().getKeys()) {
            StringList linesGenders_ = new StringList();
            AbsMap<Gender, String> genders_ = _d.getTranslatedGenders().getVal(l);
            for (Gender g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.getGenderName());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_GENDERS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedBooleans().getKeys()) {
            StringList linesGenders_ = new StringList();
            AbsMap<SelectedBoolean, String> genders_ = _d.getTranslatedBooleans()
                    .getVal(l);
            for (SelectedBoolean g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.getBoolName());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_BOOLEANS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedDiffWinPts().getKeys()) {
            StringList linesGenders_ = new StringList();
            AbsMap<DifficultyWinPointsFight, String> genders_ = _d.getTranslatedDiffWinPts()
                    .getVal(l);
            for (DifficultyWinPointsFight g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.getWinName());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_DIFF_WIN_PTS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedDiffModelLaw().getKeys()) {
            StringList linesGenders_ = new StringList();
            AbsMap<DifficultyModelLaw, String> genders_ = _d.getTranslatedDiffModelLaw()
                    .getVal(l);
            for (DifficultyModelLaw g : genders_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.getModelName());
                words_.add(DocumentBuilder.encodeToHtml(genders_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil
                    .concat(fileName_, DataBase.TRANSLATION_DIFF_MODEL_LAW);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedEnvironment().getKeys()) {
            StringList linesGenders_ = new StringList();
            AbsMap<EnvironmentType, String> statistics_ = _d.getTranslatedEnvironment()
                    .getVal(l);
            for (EnvironmentType g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.getEnvName());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_ENVIRONMENTS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedStatistics().getKeys()) {
            StringList linesGenders_ = new StringList();
            AbsMap<Statistic, String> statistics_ = _d.getTranslatedStatistics()
                    .getVal(l);
            for (Statistic g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.getStatName());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_STATISTICS);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String l : _d.getTranslatedTargets().getKeys()) {
            StringList linesGenders_ = new StringList();
            AbsMap<TargetChoice, String> statistics_ = _d.getTranslatedTargets()
                    .getVal(l);
            for (TargetChoice g : statistics_.getKeys()) {
                StringList words_;
                words_ = new StringList();
                words_.add(g.getTargetName());
                words_.add(DocumentBuilder.encodeToHtml(statistics_.getVal(g)));
                linesGenders_.add(StringUtil.join(words_, TAB));
            }
            String fileName_ = StringUtil.concat(TRANSLATION_FOLDER,
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_TARGETS);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_TYPES);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_POKEMON);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_MOVES);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_ITEMS);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_ABILITIES);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_STATUS);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_MATH);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_CLASSES);
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
                    DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, l, DataBase.SEPARATOR_FILES);
            fileName_ = StringUtil.concat(fileName_, DataBase.TRANSLATION_LITTERAL);
            files_.put(fileName_, StringUtil.join(linesGenders_, RETURN_LINE));
        }
        for (String n : _d.getAnimStatis().getKeys()) {
            files_.put(StringUtil.concat(DataBase.ANIM_STATIS, DataBase.SEPARATOR_FILES, n,
                    DataBase.IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                    .getStringByImage(_d.getAnimStatis().getVal(n)));
        }
        for (String n : _d.getAnimStatus().getKeys()) {
            files_.put(StringUtil.concat(DataBase.ANIM_STATUS, DataBase.SEPARATOR_FILES, n,
                    DataBase.IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                    .getStringByImage(_d.getAnimStatus().getVal(n)));
        }
        files_.put(DataBase.ANIM_ABSORB,
                BaseSixtyFourUtil.getStringByImage(_d.getAnimAbsorb()));
        for (String n : _d.getImages().getKeys()) {
            files_.put(
                    StringUtil.concat(DataBase.IMAGES_FOLDER, DataBase.SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getImages().getVal(n)));
        }
        for (String n : _d.getMiniMap().getKeys()) {
            files_.put(
                    StringUtil.concat(DataBase.MINI_MAP_FOLDER, DataBase.SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getMiniMap().getVal(n)));
        }
        for (String n : _d.getLinks().getKeys()) {
            files_.put(StringUtil.concat(DataBase.LINKS_FOLDER, DataBase.SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getLinks().getVal(n)));
        }
        for (String n : _d.getPeople().getKeys()) {
            files_.put(
                    StringUtil.concat(DataBase.PEOPLE_FOLDER, DataBase.SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getPeople().getVal(n)));
        }
        StringList linesHeros_;
        linesHeros_ = new StringList();
        for (ImageHeroKey k : _d.getFrontHeros().getKeys()) {
            String image_ = BaseSixtyFourUtil.getStringByImage(_d.getFrontHeros()
                    .getVal(k));
            StringBuilder str_ = new StringBuilder();
            str_.append(k.getType().getEnvName());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getSex().getSexName());
            str_.append(TAB);
            str_.append(image_);
            linesHeros_.add(str_.toString());
        }
        files_.put(
                StringUtil.concat(DataBase.HERO_FOLDER, DataBase.SEPARATOR_FILES, DataBase.HERO_FRONT),
                StringUtil.join(linesHeros_, RETURN_LINE));
        linesHeros_.clear();
        for (ImageHeroKey k : _d.getBackHeros().getKeys()) {
            String image_ = BaseSixtyFourUtil.getStringByImage(_d.getBackHeros()
                    .getVal(k));
            StringBuilder str_ = new StringBuilder();
            str_.append(k.getType().getEnvName());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getSex().getSexName());
            str_.append(TAB);
            str_.append(image_);
            linesHeros_.add(str_.toString());
        }
        files_.put(
                StringUtil.concat(DataBase.HERO_FOLDER, DataBase.SEPARATOR_FILES, DataBase.HERO_BACK),
                StringUtil.join(linesHeros_, RETURN_LINE));
        linesHeros_.clear();
        for (ImageHeroKey k : _d.getOverWorldHeros().getKeys()) {
            String image_ = BaseSixtyFourUtil
                    .getStringByImage(_d.getOverWorldHeros().getVal(k));
            StringBuilder str_ = new StringBuilder();
            str_.append(k.getType().getEnvName());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getDirection().getDirName());
            str_.append(SEPARATOR_KEY_HEROS);
            str_.append(k.getSex().getSexName());
            str_.append(TAB);
            str_.append(image_);
            linesHeros_.add(str_.toString());
        }
        files_.put(
                StringUtil.concat(DataBase.HERO_FOLDER, DataBase.SEPARATOR_FILES, DataBase.HERO_MINI),
                StringUtil.join(linesHeros_, RETURN_LINE));
        for (String n : _d.getTrainers().getKeys()) {
            files_.put(
                    StringUtil.concat(DataBase.TRAINERS_FOLDER, DataBase.SEPARATOR_FILES, n),
                    BaseSixtyFourUtil.getStringByImage(_d.getTrainers().getVal(n)));
        }
        for (String n : _d.getMaxiPkFront().getKeys()) {
            files_.put(StringUtil.concat(DataBase.FRONT_IMAGES_FOLDER,
                            DataBase.SEPARATOR_FILES, n, DataBase.IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMaxiPkFront()
                            .getVal(n)));
        }
        for (String n : _d.getMaxiPkBack().getKeys()) {
            files_.put(
                    StringUtil.concat(DataBase.BACK_IMAGES_FOLDER, DataBase.SEPARATOR_FILES,
                            n, DataBase.IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMaxiPkBack().getVal(n)));
        }
        for (String n : _d.getMiniPk().getKeys()) {
            files_.put(StringUtil.concat(DataBase.MINI_IMAGES_FOLDER,
                            DataBase.SEPARATOR_FILES, n, DataBase.IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMiniPk().getVal(n)));
        }
        for (String n : _d.getMiniItems().getKeys()) {
            files_.put(StringUtil.concat(DataBase.OBJECTS_IMAGES_FOLDER,
                            DataBase.SEPARATOR_FILES, n, DataBase.IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getMiniItems().getVal(n)));
        }
        for (String n : _d.getTypesImages().getKeys()) {
            files_.put(StringUtil.concat(DataBase.TYPES_IMAGES_FOLDER,
                            DataBase.SEPARATOR_FILES, n, DataBase.IMG_FILES_RES_EXT_TXT),
                    BaseSixtyFourUtil.getStringByImage(_d.getTypesImages()
                            .getVal(n)));
        }
        files_.put(
                StringUtil.concat(DataBase.IMAGE_TM_HM_FILES, DataBase.IMG_FILES_RES_EXT_TXT),
                BaseSixtyFourUtil.getStringByImage(_d.getImageTmHm()));
        files_.put(StringUtil.concat(DataBase.IMAGE_STORAGE_FILES,
                DataBase.IMG_FILES_RES_EXT_TXT), BaseSixtyFourUtil
                .getStringByImage(_d.getStorage()));
        files_.put(
                StringUtil.concat(DataBase.END_GAME_IMAGE, DataBase.IMG_FILES_RES_EXT_TXT),
                BaseSixtyFourUtil.getStringByImage(_d.getEndGameImage()));
        return files_;
    }

    public static String setCombos(Combos _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setCombos(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setCombos(Combos _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setCombos(_object,element_,_document);
        return element_;
    }

    private static void setCombos(Combos _object, Element _element, Document _document) {
        _element.appendChild(setMapStringListEffectCombo(_object.getEffects(),FIELD_EFFECTS,_document));
    }

    private static Element setAbilityData(AbilityData _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        if (_object.getEffect() != null) {
            Element element_ = _document.createElement(TYPE_EFFECT_WHILE_SENDING_WITH_STATISTIC);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
            setEffectWhileSendingWithStatistic(_object,element_,_document);
            return element_;
        }
        Element elt_ = _document.createElement(TYPE_EFFECT_WHILE_SENDING);
        setEffectWhileSending(_object, elt_, _document);
        return elt_;
    }

    private static void setEffectWhileSending(EffectWhileSendingWithStatistic _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getDisableWeather(),FIELD_DISABLE_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getEnabledWeather(),FIELD_ENABLED_WEATHER,_document));
        _element.appendChild(DocumentWriterCoreUtil.setBoolean(_object.getCopyingAbility(),FIELD_COPYING_ABILITY,_document));
        _element.appendChild(DocumentWriterMathUtil.setRate(_object.getMultWeight(),FIELD_MULT_WEIGHT,_document));
    }

    private static void setEffectWhileSendingWithStatistic(EffectWhileSendingWithStatistic _object, Element _element, Document _document) {
        _element.appendChild(setEffectStatistic(_object.getEffect(), FIELD_EFFECT, _document));
        setEffectWhileSending(_object, _element, _document);
    }

    private static Element setStatistic(Statistic _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getStatName());
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
        Element element_ = _document.createElement(TYPE_SELLING_ITEM);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setItem(nullToIt(_object),element_,_document);
        return element_;
    }

    static Item nullToIt(Item _pk) {
        if (_pk == null) {
            return Instances.newSellingItem();
        }
        return _pk;
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
        Element element_ = _document.createElement(TYPE_STATUS_MOVE_DATA);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setStatusMoveData(nullToMove(_object),element_,_document);
        return element_;
    }

    static StatusMoveData nullToMove(MoveData _pk) {
        if (!(_pk instanceof StatusMoveData)) {
            return Instances.newStatusMoveData();
        }
        return (StatusMoveData) _pk;
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
        Element element_ = _document.createElement(TYPE_EFFECT_WIN_MONEY);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectWinMoney(nullToEff(_object),element_,_document);
        return element_;
    }

    static EffectWinMoney nullToEff(Effect _pk) {
        if (!(_pk instanceof EffectWinMoney)) {
            return Instances.newEffectWinMoney();
        }
        return (EffectWinMoney) _pk;
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_TEAM);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectEndRoundTeam(nullToEffEnd(_object),element_,_document);
        return element_;
    }

    static EffectEndRoundTeam nullToEffEnd(EffectEndRound _pk) {
        if (!(_pk instanceof EffectEndRoundTeam)) {
            return Instances.newEffectEndRoundTeam();
        }
        return (EffectEndRoundTeam) _pk;
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
        Element element_ = _document.createElement(TYPE_EFFECT_END_ROUND_STATUS_RELATION);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEffectEndRoundStatusRelation(nullToEffEndSt(_object),element_,_document);
        return element_;
    }

    static EffectEndRoundStatusRelation nullToEffEndSt(EffectEndRoundStatus _pk) {
        if (!(_pk instanceof EffectEndRoundStatusRelation)) {
            return Instances.newEffectEndRoundStatusRelation();
        }
        return (EffectEndRoundStatusRelation) _pk;
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getCstValType());
        return elt_;
    }

    private static Element setExchangeType(ExchangeType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getExcType());
        return elt_;
    }

    private static Element setMoveChoiceRestrictionType(MoveChoiceRestrictionType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getResType());
        return elt_;
    }

    private static Element setMoveItemType(MoveItemType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getItType());
        return elt_;
    }

    private static Element setPointViewChangementType(PointViewChangementType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getPtView());
        return elt_;
    }

    private static Element setSwitchType(SwitchType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getSwType());
        return elt_;
    }

    private static Element setTargetChoice(TargetChoice _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getTargetName());
        return elt_;
    }

    public static String setPokemonData(PokemonData _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setPokemonData(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setPokemonData(PokemonData _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getExpName());
        return elt_;
    }

    public static Element setGenderRepartition(GenderRepartition _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getGenderRep());
        return elt_;
    }

    private static Element setEvolution(Evolution _object, String _fieldName, Document _document) {
        if (_object instanceof EvolutionHappiness) {
            Element element_ = _document.createElement(TYPE_EVOLUTION_HAPPINESS);
            DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
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
        Element element_ = _document.createElement(TYPE_EVOLUTION_TEAM);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setEvolutionTeam(nullToEvo(_object),element_,_document);
        return element_;
    }

    static EvolutionTeam nullToEvo(Evolution _pk) {
        if (!(_pk instanceof EvolutionTeam)) {
            return Instances.newEvolutionTeam();
        }
        return (EvolutionTeam) _pk;
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
        Element element_ = _document.createElement(TYPE_STATUS_SIMPLE);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setStatus(nullToSt(_object),element_,_document);
        return element_;
    }

    static Status nullToSt(Status _pk) {
        if (_pk == null) {
            return Instances.newStatusSimple();
        }
        return _pk;
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getStaType());
        return elt_;
    }

    private static Element setEffectPartnerStatus(EffectPartnerStatus _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setCategoryMult(CategoryMult _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setEfficiencyRate(EfficiencyRate _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setLevelMove(LevelMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setStatBaseEv(StatBaseEv _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticCategory(StatisticCategory _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticPokemon(StatisticPokemon _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticStatus(StatisticStatus _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setStatisticType(StatisticType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setTypeDamageBoost(TypeDamageBoost _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setTypesDuo(TypesDuo _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setWeatherType(WeatherType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    public static String setGame(Game _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setGame(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setGame(Game _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setUsesOfMove(UsesOfMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setActivityOfMove(ActivityOfMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setAnticipation(Anticipation _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setChoiceOfEvolutionAndMoves(ChoiceOfEvolutionAndMoves _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setStacksOfUses(StacksOfUses _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setTargetCoords(TargetCoords _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setTeam(Team _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setAbstractAction(AbstractAction _object, String _fieldName, Document _document) {
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
        Element element_ = _document.createElement(TYPE_ACTION);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        return element_;
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getFsType());
        return elt_;
    }

    private static Element setFightType(FightType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getFt());
        return elt_;
    }

    private static Element setAffectedMove(AffectedMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setCopiedMove(CopiedMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setMoveTarget(MoveTarget _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setMovesAbilities(MovesAbilities _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setMovesAbilities(_object,element_,_document);
        return element_;
    }

    private static void setMovesAbilities(MovesAbilities _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getMoves(),FIELD_MOVES,_document));
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getAbilities(),FIELD_ABILITIES,_document));
    }

    private static Element setDifficulty(Difficulty _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        _element.appendChild(DocumentWriterCoreUtil.setString(_object.getExport(),FIELD_EXPORT,_document));
    }

    private static Element setDifficultyModelLaw(DifficultyModelLaw _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getModelName());
        return elt_;
    }

    private static Element setDifficultyWinPointsFight(DifficultyWinPointsFight _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getWinName());
        return elt_;
    }

    private static Element setInventory(Inventory _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getSexName());
        return elt_;
    }

    public static String setDataMap(DataMap _object) {
        Document doc_ = DocumentBuilder.newXmlDocument();
        doc_.appendChild(setDataMap(_object, EMPTY_STRING, doc_));
        return doc_.export();
    }

    private static Element setDataMap(DataMap _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(TYPE_POKEMON_CENTER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPokemonCenter(nullToCent(_object),element_,_document);
        return element_;
    }

    static PokemonCenter nullToCent(Building _pk) {
        if (!(_pk instanceof PokemonCenter)) {
            return Instances.newPokemonCenter();
        }
        return (PokemonCenter) _pk;
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(TYPE_TRAINER_MULTI_FIGHTS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrainerMultiFights(nullToTrBis(_object),element_,_document);
        return element_;
    }

    static TrainerMultiFights nullToTrBis(CharacterInRoadCave _pk) {
        if (!(_pk instanceof TrainerMultiFights)) {
            return Instances.newTrainerMultiFights();
        }
        return (TrainerMultiFights) _pk;
    }

    private static void setDealerItem(DealerItem _object, Element _element, Document _document) {
        _element.appendChild(DocumentWriterCoreUtil.setStringList(_object.getItems(),FIELD_ITEMS,_document));
        _element.appendChild(DocumentWriterCoreUtil.setListShort(_object.getTechnicalMoves(),FIELD_TECHNICAL_MOVES,_document));
        setPerson(_object, _element, _document);
    }

    private static Element setDualFight(DualFight _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(TYPE_TRAINER_MULTI_FIGHTS);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setTrainerMultiFights(nullToTr(_object),element_,_document);
        return element_;
    }

    static TrainerMultiFights nullToTr(Person _pk) {
        if (!(_pk instanceof TrainerMultiFights)) {
            return Instances.newTrainerMultiFights();
        }
        return (TrainerMultiFights) _pk;
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getGer());
        return elt_;
    }

    private static Element setSellType(SellType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getSel());
        return elt_;
    }

    private static Element setDirection(Direction _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getDirName());
        return elt_;
    }

    private static Element setAreaApparition(AreaApparition _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelCave(_object,element_,_document);
        return element_;
    }

    private static void setLevelCave(LevelCave _object, Element _element, Document _document) {
        _element.appendChild(setMapPointLink(_object.getLinksOtherLevels(),FIELD_LINKS_OTHER_LEVELS,_document));
        setLevelWithWildPokemon(_object, _element, _document);
    }

    private static Element setLevelIndoorGym(LevelIndoorGym _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setLevelOutdoor(_object,element_,_document);
        return element_;
    }

    private static void setLevelOutdoor(LevelOutdoor _object, Element _element, Document _document) {
        setLevel(_object, _element, _document);
    }

    private static Element setLevelRoad(LevelRoad _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setEnvironmentType(EnvironmentType _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getEnvName());
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
        Element element_ = _document.createElement(TYPE_ROAD);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setRoad(nullToPl(_object),element_,_document);
        return element_;
    }

    static Road nullToPl(Place _pk) {
        if (!(_pk instanceof Road)) {
            return Instances.newRoad();
        }
        return (Road) _pk;
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
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setPkTrainer(PkTrainer _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
    }

    public static Element setPokemonPlayer(PokemonPlayer _object, String _fieldName, Document _document) {
        return setPokemonPlayer(_object,_fieldName,_document,TYPE_POKEMON_PLAYER_INNER);
    }
    public static Element setPokemonPlayer(PokemonPlayer _object, String _fieldName, Document _document, String _tag) {
        Element element_ = _document.createElement(_tag);
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
    }

    private static Element setPokemonTeam(PokemonTeam _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element element_ = _document.createElement(TYPE_POKEMON_PLAYER_INNER);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        setPokemonPlayer(nullToPk(_object),element_,_document);
        return element_;
    }
    static PokemonPlayer nullToPk(UsablePokemon _pk) {
        if (!(_pk instanceof PokemonPlayer)) {
            return Instances.newPokemonPlayer();
        }
        return (PokemonPlayer) _pk;
    }

    private static Element setWildPk(WildPk _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
    }

    private static Element setGender(Gender _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE,_object.getGenderName());
        return elt_;
    }

    private static Element setMiniMapCoords(MiniMapCoords _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setPlaceInterConnect(PlaceInterConnect _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setTileMiniMap(TileMiniMap _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setLevelPoint(LevelPoint _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setPoint(Point _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        elt_.setAttribute(DocumentWriterCoreUtil.VALUE, _object.display());
        return elt_;
    }

    private static Element setMonteCarloEnumStatistic(MonteCarloEnum<Statistic> _object, String _fieldName, Document _document) {
        Element element_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(element_, _fieldName);
        element_.appendChild(setMapStatisticLgInt(_object.getLaw(),FIELD_LAW,_document));
        return element_;
    }

    private static Element setMapStatisticLgInt(AbsMap<Statistic,LgInt> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectWhileSendingWithStatistic s: _object) {
            elt_.appendChild(setEffectWhileSending(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffect(CustList<Effect> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Effect s: _object) {
            elt_.appendChild(setEffect(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectEndRound(CustList<EffectEndRound> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectEndRound s: _object) {
            elt_.appendChild(setEffectEndRound(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectEndRoundFoe(CustList<EffectEndRoundFoe> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectEndRoundFoe s: _object) {
            elt_.appendChild(setEffectEndRoundFoe(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectEndRoundStatus(CustList<EffectEndRoundStatus> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectEndRoundStatus s: _object) {
            elt_.appendChild(setEffectEndRoundStatus(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectTeam(CustList<EffectTeam> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectTeam s: _object) {
            elt_.appendChild(setEffectTeam(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListEffectPartnerStatus(CustList<EffectPartnerStatus> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EffectPartnerStatus s: _object) {
            elt_.appendChild(setEffectPartnerStatus(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListAreaApparition(CustList<AreaApparition> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (AreaApparition s: _object) {
            elt_.appendChild(setAreaApparition(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListLevelLeague(CustList<LevelLeague> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LevelLeague s: _object) {
            elt_.appendChild(setLevelLeague(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListPkTrainer(CustList<PkTrainer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (PkTrainer s: _object) {
            elt_.appendChild(setPkTrainer(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListPokemonTeam(CustList<PokemonTeam> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (PokemonTeam s: _object) {
            elt_.appendChild(setPokemonTeam(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    public static Element setListUsablePokemon(CustList<UsablePokemon> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (UsablePokemon s: _object) {
            elt_.appendChild(setUsablePokemon(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListStatistic(IdList<Statistic> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Statistic s: _object) {
            elt_.appendChild(setStatistic(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setMapStatisticBoostHpRate(AbsMap<Statistic,BoostHpRate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setMapStatisticStatBaseEv(AbsMap<Statistic,StatBaseEv> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setMapStatisticRate(AbsMap<Statistic,Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setMapStatisticByte(AbsMap<Statistic,Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setMapStatisticShort(AbsMap<Statistic,Short> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setMapStatisticString(AbsMap<Statistic,String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setMapEnvironmentTypeString(AbsMap<EnvironmentType,String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LevelMove s: _object) {
            elt_.appendChild(setLevelMove(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListStatisticStatus(CustList<StatisticStatus> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticStatus s: _object) {
            elt_.appendChild(setStatisticStatus(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListTypesDuo(CustList<TypesDuo> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TypesDuo s: _object) {
            elt_.appendChild(setTypesDuo(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListTargetCoords(TargetCoordsList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TargetCoords s: _object) {
            elt_.appendChild(setTargetCoords(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListWildPk(CustList<WildPk> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (WildPk s: _object) {
            elt_.appendChild(setWildPk(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListCoords(CustList<Coords> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Coords s: _object) {
            elt_.appendChild(setCoords(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setListPoint(PointEqList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Point s: _object) {
            elt_.appendChild(setPoint(s,EMPTY_STRING,_document));
        }
        return elt_;
    }

    private static Element setMapByteAnticipation(ByteMap<Anticipation> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LevelCave s: _object) {
            Element sub_ = setLevelCave(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapShortPlace(CustList<Place> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (Place s: _object) {
            Element sub_ = setPlace(s, EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapShortListPoint(ShortMap<PointEqList> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<Short, PointEqList> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setShort(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListPoint(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCategoryMultRate(CategoryMults _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CategoryMultRate s: _object.entryList()) {
            Element sub_ = setCategoryMult(s.getCategory(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getRate(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticCategoryRate(StatisticCategoryList<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticCategoryParam<Rate> s: _object.entryList()) {
            Element sub_ = setStatisticCategory(s.getStatistic(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticCategoryByte(StatisticCategoryList<Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticCategoryParam<Byte> s: _object.entryList()) {
            Element sub_ = setStatisticCategory(s.getStatistic(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticPokemonByte(StatisticPokemons _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticPokemonByte s: _object.entryList()) {
            Element sub_ = setStatisticPokemon(s.getStat(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticStatusByte(StatisticStatusList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticStatusByte s: _object.entryList()) {
            Element sub_ = setStatisticStatus(s.getStat(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticTypeRate(StatisticTypeList<Rate> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticTypeParam<Rate> s: _object.entryList()) {
            Element sub_ = setStatisticType(s.getStatistic(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStatisticTypeByte(StatisticTypeList<Byte> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (StatisticTypeParam<Byte> s: _object.entryList()) {
            Element sub_ = setStatisticType(s.getStatistic(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapTypesDuoRate(TypesDuos _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (TypesDuoRate s: _object.entryList()) {
            Element sub_ = setTypesDuo(s.getStat(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapWeatherTypeRate(WeatherTypes _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (WeatherTypeRate s: _object.entryList()) {
            Element sub_ = setWeatherType(s.getStat(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterMathUtil.setRate(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapNbFightCoordsBoolean(NbFightCoordss _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<NbFightCoords, BoolVal> s: _object.entryList()) {
            Element sub_ = setNbFightCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionActivityOfMove(MoveTeamPositionsActivityOfMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<MoveTeamPosition, ActivityOfMove> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setActivityOfMove(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionAffectedMove(MoveTeamPositionsAffectedMove _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<MoveTeamPosition, AffectedMove> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setAffectedMove(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionStringList(MoveTeamPositionsStringList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<MoveTeamPosition, StringList> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setStringList(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionBoolean(MoveTeamPositionsBoolVal _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<MoveTeamPosition, BoolVal> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTeamPositionShort(MoveTeamPositionsShort _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<MoveTeamPosition, Short> s: _object.entryList()) {
            Element sub_ = setMoveTeamPosition(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setShort(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMoveTargetMoveTarget(MoveTargets _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<MoveTarget, MoveTarget> s: _object.entryList()) {
            Element sub_ = setMoveTarget(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setMoveTarget(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapMiniMapCoordsTileMiniMap(MiniMapCoordsList _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (MiniMapCoordsTile s: _object.entryList()) {
            Element sub_ = setMiniMapCoords(s.getMiniMapCoords(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setTileMiniMap(s.getTileMap(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPlaceInterConnectCoords(PlaceInterConnects _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (PlaceInterConnectCoords s: _object.entryList()) {
            Element sub_ = setPlaceInterConnect(s.getPlaceInterConnect(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setCoords(s.getCoords(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCoordsHostPokemonDuo(CoordssHostPokemonDuo _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Coords, HostPokemonDuo> s: _object.entryList()) {
            Element sub_ = setCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setHostPokemonDuo(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCoordsListCoords(CoordsLists _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CoordsListCoords s: _object.entryList()) {
            Element sub_ = setCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListCoords(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapCoordsBoolean(CoordssBoolVal _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Coords, BoolVal> s: _object.entryList()) {
            Element sub_ = setCoords(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setBoolean(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapLevelPointLink(LevelPoints _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (LevelPointLink s: _object.entryList()) {
            Element sub_ = setLevelPoint(s.getLevelPoint(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLink(s.getLink(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointBuilding(Points<Building> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,Building> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBuilding(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointCharacterInRoadCave(Points<CharacterInRoadCave> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,CharacterInRoadCave> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setCharacterInRoadCave(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointDualFight(Points<DualFight> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,DualFight> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setDualFight(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointGymTrainer(Points<GymTrainer> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,GymTrainer> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setGymTrainer(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointPerson(Points<Person> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,Person> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setPerson(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointBlock(Points<Block> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,Block> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setBlock(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointLink(Points<Link> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,Link> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setLink(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointWildPk(Points<WildPk> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,WildPk> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setWildPk(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointShort(Points<Short> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,Short> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setShort(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapPointString(Points<String> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (CommonParam<Point,String> s: _object.entryList()) {
            Element sub_ = setPoint(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = DocumentWriterCoreUtil.setString(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setMapStringListEffectCombo(CustList<ListEffectCombo> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setStringMapEvolution(StringMap<Evolution> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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

    private static Element setStringMapListStatistic(StringMap<IdList<Statistic>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, IdList<Statistic>> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setListStatistic(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapMapStatisticByte(StringMap<IdMap<Statistic,Byte>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
        DocumentWriterCoreUtil.setFieldName(elt_, _fieldName);
        for (EntryCust<String, IdMap<Statistic,Byte>> s: _object.entryList()) {
            Element sub_ = DocumentWriterCoreUtil.setString(s.getKey(), EMPTY_STRING, _document);
            DocumentWriterCoreUtil.setKey(sub_);
            elt_.appendChild(sub_);
            sub_ = setMapStatisticByte(s.getValue(), EMPTY_STRING, _document);
            elt_.appendChild(sub_);
        }
        return elt_;
    }

    private static Element setStringMapMapByteAnticipation(StringMap<ByteMap<Anticipation>> _object, String _fieldName, Document _document) {
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
        Element elt_ = _document.createElement(DocumentWriterCoreUtil.ANON_TAG);
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
