package aiki.gui.components.editor;

import aiki.db.*;
import code.sml.util.*;

public final class MessagesEditorSelect {
    public static final String EVO_TYPE = "_";
    public static final String EVO_LEVEL_SIMPLE = "0";
    public static final String EVO_LEVEL_GENDER = "1";
    public static final String EVO_STONE_SIMPLE = "2";
    public static final String EVO_STONE_GENDER = "3";
    public static final String EVO_HAPPINESS = "4";
    public static final String EVO_ITEM = "5";
    public static final String EVO_MOVE = "6";
    public static final String EVO_MOVE_TYPE = "7";
    public static final String EVO_TEAM = "8";
    public static final String EFF_TYPE = "_";
    public static final String EFF_ACCURACY = "0";
    public static final String EFF_ALLY = "1";
    public static final String EFF_BATON_PASS = "2";
    public static final String EFF_CLONE = "3";
    public static final String EFF_COMMON_STATISTICS = "4";
    public static final String EFF_COPY_FIGHTER = "5";
    public static final String EFF_COPY_MOVE = "6";
    public static final String EFF_COUNTER_ATTACK = "7";
    public static final String EFF_DAMAGE = "8";
    public static final String EFF_DAMAGE_RATE = "9";
    public static final String EFF_END_ROUND_FOE = "10";
    public static final String EFF_END_ROUND_GLOBAL = "11";
    public static final String EFF_END_ROUND_INDIVIDUAL = "12";
    public static final String EFF_END_ROUND_MULTI_RELATION = "13";
    public static final String EFF_END_ROUND_POSITION_RELATION = "14";
    public static final String EFF_END_ROUND_POSITION_TARGET = "15";
    public static final String EFF_END_ROUND_SINGLE_RELATION = "16";
    public static final String EFF_END_ROUND_STATUS = "17";
    public static final String EFF_END_ROUND_STATUS_RELATION = "18";
    public static final String EFF_END_ROUND_TEAM = "19";
    public static final String EFF_FULL_HP_RATE = "20";
    public static final String EFF_GLOBAL = "21";
    public static final String EFF_INVOKE = "22";
    public static final String EFF_MULT_SUFFERED_MOVE_POWER = "23";
    public static final String EFF_MULT_USED_MOVE_POWER = "24";
    public static final String EFF_ORDER = "25";
    public static final String EFF_PROTECT_FROM_TYPES = "26";
    public static final String EFF_PROTECTION = "27";
    public static final String EFF_REMAINED_HP_RATE = "28";
    public static final String EFF_RESTRICTION = "29";
    public static final String EFF_STATIS = "30";
    public static final String EFF_STATUS = "31";
    public static final String EFF_SWITCH_ABILITIES = "32";
    public static final String EFF_SWITCH_ITEMS = "33";
    public static final String EFF_SWITCH_MOVES_TYPES = "34";
    public static final String EFF_SWITCH_POINT_VIEW = "35";
    public static final String EFF_SWITCH_POSITION = "36";
    public static final String EFF_SWITCH_TYPES = "37";
    public static final String EFF_TEAM = "38";
    public static final String EFF_TEAM_WHILE_SENDING_FOE = "39";
    public static final String EFF_UNPROTECT_FROM_TYPES = "40";
    public static final String EFF_VAR_PP = "41";
    public static final String EFF_WIN_MONEY = "42";
    public static final String STAT_AUTO = "0";
    public static final String STAT_BEGIN = "1";
    public static final String STAT_SIMPLE = "2";
    public static final String PLACE_CITY = "0";
    public static final String PLACE_ROAD = "1";
    public static final String PLACE_CAVE = "2";
    public static final String PLACE_LEAGUE = "3";
    public static final String TILE_ITEMS = "0";
    public static final String TILE_TM = "1";
    public static final String TILE_HM = "2";
    public static final String TILE_LEG_PK = "3";
    public static final String TILE_DEALER = "4";
    public static final String TILE_TRAINER = "5";
    public static final String TILE_DUAL = "6";
    public static final String GYM_TILE_EXIT = "0";
    public static final String GYM_TILE_TRAINER = "1";
    public static final String GYM_TILE_LEADER = "2";
    public static final String PC_TILE_EXIT = "0";
    public static final String PC_TILE_GERANT = "1";
    public static final String PC_TILE_SELLER = "2";
    public static final String PC_TILE_STORAGE = "3";
    public static final String SELL_TYPE_TM = "0";
    public static final String SELL_TYPE_MOVE = "1";
    public static final String SELL_TYPE_ITEM = "2";
    public static final String GERANCE_TYPE_FOSSILE = "0";
    public static final String GERANCE_TYPE_HOST = "1";
    public static final String GERANCE_TYPE_HEAL = "2";
    public static final String LEAGUE_TRAINER = "0";
    public static final String LEAGUE_ACCESS = "1";
    public static final String SCREEN_WIDTH = "0";
    public static final String SCREEN_HEIGHT = "1";
    public static final String SPACE_LEFT = "2";
    public static final String SPACE_TOP = "3";
    public static final String SIDE_LENGTH = "4";
    public static final String GYM_CHECK = "0";
    public static final String DETAIL_TILE_BUILDING = "1";
    public static final String IT_KIND = "2";
    public static final String GER_KIND = "3";
    public static final String DELTA_WIDTH = "4";
    public static final String DELTA_HEIGHT = "5";
    public static final String AREAS = "6";
    public static final String CONTENT_LEVEL = "7";
    public static final String TEAMS = "8";
    public static final String AREA_WALK = "9";
    public static final String AREA_FISH = "10";
    public static final String AREA_MULT = "11";
    public static final String AREA_SINGLE = "12";
    public static final String AREA_STEP = "13";
    public static final String SOLD_IT = "14";
    public static final String ST_KIND = "15";
    public static final String UNIQ_LEFT = "16";
    public static final String UNIQ_RIGHT = "17";
    public static final String UNIQ_VIEW_LEFT = "18";
    public static final String UNIQ_VIEW_RIGHT = "19";
    public static final String BASE_ENCODE = "20";
    public static final String LOADED_IMG = "21";
    public static final String FILE_IMG = "22";
    public static final String IMG_ENT_NAME = "23";
    public static final String IMG_FREE_NAME = "24";
    public static final String IMG_HERO_SEX = "25";
    public static final String IMG_HERO_DIR = "26";
    public static final String IMG_HERO_ENV = "27";
    public static final String FILE_IMG_NAME = "28";
    public static final String IMG_RED = "29";
    public static final String IMG_GREEN = "30";
    public static final String IMG_BLUE = "31";
    public static final String SEL_LEVEL = "32";
    public static final String PK_TEAM = "33";
    public static final String KEY_TR = "34";
    public static final String PART_ONE_LIT = "35";
    public static final String PART_TWO_LIT = "36";
    public static final String ACC_COND_FORM = "37";
    public static final String JOIN_UNIQ = "38";
    public static final String JOIN_CAVE_UNIQ = "39";
    public static final String JOIN_CAVE_UNIQ_REV = "40";
    public static final String SEL_PLACE = "41";
    public static final String EDITED_CITY = "42";
    public static final String EDITED_ROAD = "43";
    public static final String EDITED_CAVE = "44";
    public static final String EDITED_LEAGUE = "45";
    public static final String ADD_PLACE = "46";
    public static final String LK_PLACE = "47";
    public static final String LV_CONT_OUT = "48";
    public static final String INTRA_LV_LK = "49";
    public static final String INTRA_LV_LK_NEXT = "50";
    public static final String UNIQ_PLACE = "51";
    public static final String CAVE = "52";
    public static final String PLACE_NAME = "53";
    public static final String PLACE_KIND = "54";
    public static final String PLACE_LIST = "55";
    public static final String MINI_MAP = "56";
    public static final String ROWS = "57";
    public static final String COLS = "58";
    public static final String TILE = "59";
    public static final String DEST_NB = "60";
    public static final String DEST_TR = "61";
    public static final String ENUM_VALUES = "62";
    public static final String IT_TYPE = "63";
    public static final String CST_DIFF_LAW = "64";
    public static final String NUM_VAR = "65";
    public static final String NUM_VAL = "66";
    public static final String TR_KEY = "67";
    public static final String BUTTON_LINK_PLACE_CAVE_ONE = "0";
    public static final String BUTTON_LINK_CAVE_PLACE_ONE = "1";
    public static final String BUTTON_LINK_PLACE_CAVE_BOTH = "2";
    public static final String BUTTON_LINK_CAVE_PLACE_BOTH = "3";
    public static final String BUTTON_UNLINK_PLACE_CAVE_ONE = "4";
    public static final String BUTTON_UNLINK_CAVE_PLACE_ONE = "5";
    public static final String BUTTON_UNLINK_PLACE_CAVE_BOTH = "6";
    public static final String BUTTON_UNLINK_CAVE_PLACE_BOTH = "7";
    public static final String BUTTON_LINK_LEVELS_LEFT = "8";
    public static final String BUTTON_LINK_LEVELS_RIGHT = "9";
    public static final String BUTTON_LINK_LEVELS_BOTH = "10";
    public static final String BUTTON_UNLINK_LEVELS_LEFT = "11";
    public static final String BUTTON_UNLINK_LEVELS_RIGHT = "12";
    public static final String BUTTON_UNLINK_LEVELS_BOTH = "13";
    public static final String BUTTON_IMG_LINK_LEFT = "14";
    public static final String BUTTON_IMG_LINK_RIGHT = "15";
    public static final String BUTTON_IMG_LINK_BOTH = "16";
    private MessagesEditorSelect() {
    }
    public static TranslationsFile enGenderRep(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_GENDER_REP_FEMALE, "100% Female");
        e_.add(DataBase.DEF_GENDER_REP_MALE, "100% Male");
        e_.add(DataBase.DEF_GENDER_REP_MIXED, "Mixed");
        e_.add(DataBase.DEF_GENDER_REP_LEGENDARY, "Legendary");
        e_.add(DataBase.DEF_GENDER_REP_NO_GENDER, "No gender");
        return e_;
    }
    public static TranslationsFile frGenderRep(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_GENDER_REP_FEMALE, "100% Femelle");
        f_.add(DataBase.DEF_GENDER_REP_MALE, "100% Mâle");
        f_.add(DataBase.DEF_GENDER_REP_MIXED, "Mixte");
        f_.add(DataBase.DEF_GENDER_REP_LEGENDARY, "Légendaire");
        f_.add(DataBase.DEF_GENDER_REP_NO_GENDER, "Pas de genre");
        return f_;
    }
    public static TranslationsFile enExpType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_EXP_E,"erratic");
        e_.add(DataBase.DEF_EXP_F,"fluctuating");
        e_.add(DataBase.DEF_EXP_L,"slow");
        e_.add(DataBase.DEF_EXP_M,"medium fast");
        e_.add(DataBase.DEF_EXP_P,"medium slow");
        e_.add(DataBase.DEF_EXP_R,"fast");
        return e_;
    }
    public static TranslationsFile frExpType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_EXP_E,"erratique");
        f_.add(DataBase.DEF_EXP_F,"fluctuante");
        f_.add(DataBase.DEF_EXP_L,"lente");
        f_.add(DataBase.DEF_EXP_M,"moyenne");
        f_.add(DataBase.DEF_EXP_P,"parabolique");
        f_.add(DataBase.DEF_EXP_R,"rapide");
        return f_;
    }
    public static TranslationsFile enEvo(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EVO_TYPE, "type of evolution");
        e_.add(EVO_LEVEL_SIMPLE, "evolution by level");
        e_.add(EVO_LEVEL_GENDER, "evolution by level and gender");
        e_.add(EVO_STONE_SIMPLE, "evolution by stone");
        e_.add(EVO_STONE_GENDER, "evolution by stone and gender");
        e_.add(EVO_HAPPINESS, "evolution by happiness");
        e_.add(EVO_ITEM, "evolution by worn item");
        e_.add(EVO_MOVE, "evolution by learnt move");
        e_.add(EVO_MOVE_TYPE, "evolution by type of learnt move");
        e_.add(EVO_TEAM, "evolution by member in team");
        return e_;
    }
    public static TranslationsFile frEvo(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EVO_TYPE, "type d'évolution");
        f_.add(EVO_LEVEL_SIMPLE, "évolution par niveau");
        f_.add(EVO_LEVEL_GENDER, "évolution par niveau et genre");
        f_.add(EVO_STONE_SIMPLE, "évolution par pierre");
        f_.add(EVO_STONE_GENDER, "évolution par pierre et genre");
        f_.add(EVO_HAPPINESS, "évolution par bonheur");
        f_.add(EVO_ITEM, "évolution par objet porté");
        f_.add(EVO_MOVE, "évolution par attaque apprise");
        f_.add(EVO_MOVE_TYPE, "évolution par type d'attaque apprise");
        f_.add(EVO_TEAM, "évolution par membre en équipe");
        return f_;
    }
    public static TranslationsFile enEff(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(EFF_TYPE,"Type of effect");
        e_.add(EFF_ACCURACY,"Achieve targets disappearing during the next round this effect.");
        e_.add(EFF_ALLY,"Lets the partner to get bonuses.");
        e_.add(EFF_BATON_PASS,"Forwards levels of statistics.");
        e_.add(EFF_CLONE,"Uses of hp maximum.");
        e_.add(EFF_COMMON_STATISTICS,"Affects a common value for a statistic.");
        e_.add(EFF_COPY_FIGHTER,"Copies the data of the target.");
        e_.add(EFF_COPY_MOVE,"Happens when a move copy");
        e_.add(EFF_COUNTER_ATTACK,"Protects its user against some moves and its user may counter attack.");
        e_.add(EFF_DAMAGE,"Inflicts damage against targets with a constant value of damage points or not.");
        e_.add(EFF_DAMAGE_RATE,"Makes vary the hp of the user in function by inflicted damage against the target.");
        e_.add(EFF_END_ROUND_FOE,"Regards the foe team of the user at the end.");
        e_.add(EFF_END_ROUND_GLOBAL,"Regards all fighters at the end.");
        e_.add(EFF_END_ROUND_INDIVIDUAL,"Regards one fighter at the end.");
        e_.add(EFF_END_ROUND_MULTI_RELATION,"Regards all possible relations between users and targets on the front of battle at the end.");
        e_.add(EFF_END_ROUND_POSITION_RELATION,"Regards the position of the user and the possible substitute at the end.");
        e_.add(EFF_END_ROUND_POSITION_TARGET,"Regards the position of the user and the position of the target at the end.");
        e_.add(EFF_END_ROUND_SINGLE_RELATION,"Regards the bonus of the worn item by the user at the end.");
        e_.add(EFF_END_ROUND_STATUS,"Regards the status of one fighter at the end.");
        e_.add(EFF_END_ROUND_STATUS_RELATION,"Regards the user and the target at the end.");
        e_.add(EFF_END_ROUND_TEAM,"Regards the team of the user at the end.");
        e_.add(EFF_FULL_HP_RATE,"Makes vary the hp in function by a rate of full hp of the fighter.");
        e_.add(EFF_GLOBAL,"Regards all fighters.");
        e_.add(EFF_INVOKE,"Invokes an other move not invoked while the round of the user.");
        e_.add(EFF_MULT_SUFFERED_MOVE_POWER,"Multiplies the power of the suffered moves of the target in function by the type.");
        e_.add(EFF_MULT_USED_MOVE_POWER,"Multiplies the power of the used moves of the user in fonction by the type.");
        e_.add(EFF_ORDER,"Changes the order of the fighters.");
        e_.add(EFF_PROTECT_FROM_TYPES,"Protects a fighter against a type of move.");
        e_.add(EFF_PROTECTION,"Protects a part of the team of the user.");
        e_.add(EFF_REMAINED_HP_RATE,"Makes vary the hp in function of a rate of remaining hp of the fighter.");
        e_.add(EFF_RESTRICTION,"Restricts some usage.");
        e_.add(EFF_STATIS,"Changes levels or values of statistics for a fighter at least.");
        e_.add(EFF_STATUS,"Adds/Deletes a status at least.");
        e_.add(EFF_SWITCH_ABILITIES,"Changes an ability at least.");
        e_.add(EFF_SWITCH_ITEMS,"Changes the owner of an item at least.");
        e_.add(EFF_SWITCH_MOVES_TYPES,"Changes the types of a move of a target not having acted yet.");
        e_.add(EFF_SWITCH_POINT_VIEW,"Changes the view point of fighters for a move.");
        e_.add(EFF_SWITCH_POSITION,"The user and the target switch their position at the front of battle.");
        e_.add(EFF_SWITCH_TYPES,"Change the types of a fighter at least.");
        e_.add(EFF_TEAM,"Regards a team.");
        e_.add(EFF_TEAM_WHILE_SENDING_FOE,"Happens while sending a foe at the front battle.");
        e_.add(EFF_UNPROTECT_FROM_TYPES,"Allows the user to use moves that the target was protected.");
        e_.add(EFF_VAR_PP,"Affects the pp of moves of a fighter.");
        e_.add(EFF_WIN_MONEY,"Lets the player to win money.");
        return e_;
    }
    public static TranslationsFile frEff(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(EFF_TYPE,"Type d'effet");
        f_.add(EFF_ACCURACY,"Permet de toucher des cibles disparaissant pendant le tour suivant cet effet.");
        f_.add(EFF_ALLY,"Permet au partenaire d'obtenir des bonus.");
        f_.add(EFF_BATON_PASS,"Transmet les boosts des statistiques.");
        f_.add(EFF_CLONE,"Utilise des pv max.");
        f_.add(EFF_COMMON_STATISTICS,"Attribue une valeur commune pour une statistique du lanceur et de la cible.");
        f_.add(EFF_COPY_FIGHTER,"Copie l'espèce de la cible.");
        f_.add(EFF_COPY_MOVE,"A lieu lors d'une copie d'attaque de combattant");
        f_.add(EFF_COUNTER_ATTACK,"Protège son lanceur contre certains attaques et son lanceur peut contre attaquer.");
        f_.add(EFF_DAMAGE,"Inflige des dégâts aux cibles avec une valeur fixe de points de dégâts ou non.");
        f_.add(EFF_DAMAGE_RATE,"Fait varier les pv du lanceur en fonction des dégâts infligés à la cible.");
        f_.add(EFF_END_ROUND_FOE,"Concerne l'équipe adverse du lanceur à la fin.");
        f_.add(EFF_END_ROUND_GLOBAL,"Concerne tous les combattants à la fin.");
        f_.add(EFF_END_ROUND_INDIVIDUAL,"Concerne un seul combattant à la fin.");
        f_.add(EFF_END_ROUND_MULTI_RELATION,"Prend en compte toutes les relations possibles entre lanceurs et cibles sur le terrain à la fin.");
        f_.add(EFF_END_ROUND_POSITION_RELATION,"Tient compte de la position du lanceur et le remplaçant éventuel à la fin.");
        f_.add(EFF_END_ROUND_POSITION_TARGET,"Tient compte de la position du lanceur et celle de la cible à la fin.");
        f_.add(EFF_END_ROUND_SINGLE_RELATION,"Concerne le bonus de l'objet porté par le lanceur à la fin.");
        f_.add(EFF_END_ROUND_STATUS,"Concerne le statut d'un combattant à la fin.");
        f_.add(EFF_END_ROUND_STATUS_RELATION,"Concerne le lanceur et la cible à la fin.");
        f_.add(EFF_END_ROUND_TEAM,"Concerne l'équipe du lanceur à la fin.");
        f_.add(EFF_FULL_HP_RATE,"Fait varier les pv en fonction d'un quota de pv totaux du combattant.");
        f_.add(EFF_GLOBAL,"Concerne tous les combattants.");
        f_.add(EFF_INVOKE,"Invoque une autre attaque non invoquée dans le tour du lanceur.");
        f_.add(EFF_MULT_SUFFERED_MOVE_POWER,"Multiplie la puissance des attaques subies de la cible en fonction du type.");
        f_.add(EFF_MULT_USED_MOVE_POWER,"Multiplie la puissance des attaques du lanceur en fonction du type.");
        f_.add(EFF_ORDER,"Change l'ordre d'attaque des combattants.");
        f_.add(EFF_PROTECT_FROM_TYPES,"Immunise un combattant à un type d'attaque.");
        f_.add(EFF_PROTECTION,"Protège une partie de l'équipe du lanceur.");
        f_.add(EFF_REMAINED_HP_RATE,"Fait varier les pv en fonction d'un quota de pv restants du combattant.");
        f_.add(EFF_RESTRICTION,"Restreint certains usages.");
        f_.add(EFF_STATIS,"Change des boosts ou des valeurs de statistiques pour au moins un combattant.");
        f_.add(EFF_STATUS,"Ajoute/supprime au moins un statut.");
        f_.add(EFF_SWITCH_ABILITIES,"Change au moins une capacité.");
        f_.add(EFF_SWITCH_ITEMS,"Change le possesseur d'au moins un objet.");
        f_.add(EFF_SWITCH_MOVES_TYPES,"Change les types d'une attaque d'une cible n'ayant pas encore joué.");
        f_.add(EFF_SWITCH_POINT_VIEW,"Change le point de vue des combattants pour une attaque.");
        f_.add(EFF_SWITCH_POSITION,"Le lanceur et la cible échangent leur position sur le terrain.");
        f_.add(EFF_SWITCH_TYPES,"Change les types d'au moins un combattant.");
        f_.add(EFF_TEAM,"Concerne une équipe.");
        f_.add(EFF_TEAM_WHILE_SENDING_FOE,"A lieu lors de l'entrée d'un adversaire sur le terrain.");
        f_.add(EFF_UNPROTECT_FROM_TYPES,"Permet au lanceur d'utiliser des attaques auxquelles la cible était immunisée.");
        f_.add(EFF_VAR_PP,"Touche aux pp d'un combattant.");
        f_.add(EFF_WIN_MONEY,"Permet au joueur de gagner de l'argent.");
        return f_;
    }
    public static TranslationsFile enStatus(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(STAT_AUTO,"Auto");
        e_.add(STAT_BEGIN,"Begin");
        e_.add(STAT_SIMPLE,"Simple");
        return e_;
    }
    public static TranslationsFile frStatus(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(STAT_AUTO,"Auto");
        f_.add(STAT_BEGIN,"Début");
        f_.add(STAT_SIMPLE,"Simple");
        return f_;
    }
    public static TranslationsFile enExchangeType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_TARGET,"The target takes from the user");
        e_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_THROWER,"The user takes from the target");
        e_.add(DataBase.DEF_EXCHANGE_TYPE_EXCHANGE,"The user and the target switch one each other");
        e_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_CONST,"The target takes an entity");
        e_.add(DataBase.DEF_EXCHANGE_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return e_;
    }
    public static TranslationsFile frExchangeType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_TARGET,"La cible prend depuis le lanceur");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_THROWER,"Le lanceur prend depuis la cible");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_EXCHANGE,"Le lanceur et la cible s'échangent");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_CONST,"La cible prend une entité");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return f_;
    }
    public static TranslationsFile enMoveItemType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_MOVE_ITEM_TYPE_GIVE_OBJECT_TARGET,"The user gives its item to the target");
        e_.add(DataBase.DEF_MOVE_ITEM_TYPE_EXCHANGE_OBJECTS,"The user and the target switch one each other the items");
        e_.add(DataBase.DEF_MOVE_ITEM_TYPE_TAKE_OBJET,"The user takes the item of the target");
        e_.add(DataBase.DEF_MOVE_ITEM_TYPE_REMOVE_TARGET_OBJECT,"The target looses its item");
        e_.add(DataBase.DEF_MOVE_ITEM_TYPE_DELETE_DEF_TARGET_BERRY,"The berry of the fighter is definitively deleted");
        e_.add(DataBase.DEF_MOVE_ITEM_TYPE_USE_OBJECT_AS_POSSIBLE,"If possible, the user uses the item of the target");
        e_.add(DataBase.DEF_MOVE_ITEM_TYPE_REUSE_LAST_OBJECT,"The user gets the last item that the user used");
        return e_;
    }
    public static TranslationsFile frMoveItemType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_MOVE_ITEM_TYPE_GIVE_OBJECT_TARGET,"Le lanceur donne son objet à la cible");
        f_.add(DataBase.DEF_MOVE_ITEM_TYPE_EXCHANGE_OBJECTS,"Le lanceur et la cible s'échangent les objets");
        f_.add(DataBase.DEF_MOVE_ITEM_TYPE_TAKE_OBJET,"Le lanceur prend l'objet de la cible");
        f_.add(DataBase.DEF_MOVE_ITEM_TYPE_REMOVE_TARGET_OBJECT,"La cible perd son objet");
        f_.add(DataBase.DEF_MOVE_ITEM_TYPE_DELETE_DEF_TARGET_BERRY,"La baie du combattant est supprimé définitivement");
        f_.add(DataBase.DEF_MOVE_ITEM_TYPE_USE_OBJECT_AS_POSSIBLE,"Si possible, le lanceur utilise l'objet de la cible");
        f_.add(DataBase.DEF_MOVE_ITEM_TYPE_REUSE_LAST_OBJECT,"Le lanceur récupère le dernier objet qu'il a utilisé");
        return f_;
    }
    public static TranslationsFile enPointViewChangementType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_THIEF_BONUSES,"The user of this move waits that its target use a \"thievable\" move");
        e_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_MIRROR_AGAINST_THROWER,"The owner of this effect counters against the initial user");
        e_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_ATTRACT_DAMAGES_MOVES,"The user of this move suffers damage of moves with single choice instead of its partners");
        e_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return e_;
    }
    public static TranslationsFile frPointViewChangementType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_THIEF_BONUSES,"Le lanceur de cette attaque attend que sa cible lance une attaque \"saisissable\"");
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_MIRROR_AGAINST_THROWER,"Le possesseur de cet effet contre l'utilisateur initial");
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_ATTRACT_DAMAGES_MOVES,"Le lanceur de cette attaque subit les dégâts des attaques à cible unique au lieu de ses partenaires");
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return f_;
    }
    public static TranslationsFile enConstValuesType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_CONST_VALUES_TYPE_LANCEUR_ATTAQUES_TYPES,"The user of this move waits that its target use a \"thievable\" move");
        e_.add(DataBase.DEF_CONST_VALUES_TYPE_TYPES_ATTAQUES_RES,"The owner of this effect counters against the initial user");
        e_.add(DataBase.DEF_CONST_VALUES_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return e_;
    }
    public static TranslationsFile frConstValuesType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_CONST_VALUES_TYPE_LANCEUR_ATTAQUES_TYPES,"Le lanceur de cette attaque attend que sa cible lance une attaque \"saisissable\"");
        f_.add(DataBase.DEF_CONST_VALUES_TYPE_TYPES_ATTAQUES_RES,"Le possesseur de cet effet contre l'utilisateur initial");
        f_.add(DataBase.DEF_CONST_VALUES_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return f_;
    }
    public static TranslationsFile enMoveChoiceRestrictionType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORCE,"The fighter must reuse the move having just been chosen during some rounds");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORBIDDEN,"The fighter cannot reuse the move having just been chosen during some rounds");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_LANCEUR_ATTAQUES,"The target cannot use the moves that the user owns");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_DER,"The fighter cannot choose consecutively the same move");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_CATEGORIE_AUTRE,"The fighter cannot use not damaging moves anymore");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return e_;
    }
    public static TranslationsFile frMoveChoiceRestrictionType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORCE,"Le combattant doit réutiliser l'attaque venant d'être choisie pendant un certain nombre de tour");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORBIDDEN,"Le combattant ne peut pas réutiliser l'attaque venant d'être choisie pendant un certain nombre de tour");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_LANCEUR_ATTAQUES,"La cible ne peut pas utiliser les attaques que le lanceur possède");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_DER,"Le combattant ne peut pas choisir consécutivement la même attaque");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_CATEGORIE_AUTRE,"Le combattant ne peut plus utiliser les attaques non offensives");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_NOTHING,DataBase.EMPTY_STRING);
        return f_;
    }
    public static TranslationsFile enHerosSex(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_SEX_GIRL,"girl");
        e_.add(DataBase.DEF_SEX_BOY,"boy");
        return e_;
    }
    public static TranslationsFile frHerosSex(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_SEX_GIRL,"fille");
        f_.add(DataBase.DEF_SEX_BOY,"garçon");
        return f_;
    }
    public static TranslationsFile enPlace(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(PLACE_CITY,"city");
        e_.add(PLACE_ROAD,"road");
        e_.add(PLACE_CAVE,"cave");
        e_.add(PLACE_LEAGUE,"league");
        return e_;
    }
    public static TranslationsFile frPlace(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(PLACE_CITY,"ville");
        f_.add(PLACE_ROAD,"route");
        f_.add(PLACE_CAVE,"cave");
        f_.add(PLACE_LEAGUE,"ligue");
        return f_;
    }
    public static TranslationsFile enTileKindWild(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TILE_ITEMS,"items");
        e_.add(TILE_TM,"technical move");
        e_.add(TILE_HM,"hidden move");
        e_.add(TILE_LEG_PK,"legendary");
        e_.add(TILE_DEALER,"dealer item");
        e_.add(TILE_TRAINER,"trainer");
        e_.add(TILE_DUAL,"fight with ally");
        return e_;
    }
    public static TranslationsFile frTileKindWild(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TILE_ITEMS,"objets");
        f_.add(TILE_TM,"capsule technique");
        f_.add(TILE_HM,"capsule secrète");
        f_.add(TILE_LEG_PK,"légendaire");
        f_.add(TILE_DEALER,"donneur d'objet");
        f_.add(TILE_TRAINER,"dresseur");
        f_.add(TILE_DUAL,"combat avec allié");
        return f_;
    }
    public static TranslationsFile enGymTileKindWild(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(GYM_TILE_EXIT,"exit");
        e_.add(GYM_TILE_TRAINER,"ordinary");
        e_.add(GYM_TILE_LEADER,"leader");
        return e_;
    }
    public static TranslationsFile frGymTileKindWild(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(GYM_TILE_EXIT,"sortie");
        f_.add(GYM_TILE_TRAINER,"ordinaire");
        f_.add(GYM_TILE_LEADER,"meneur");
        return f_;
    }
    public static TranslationsFile enPcTileKindWild(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(PC_TILE_EXIT,"exit");
        e_.add(PC_TILE_GERANT,"gerant");
        e_.add(PC_TILE_SELLER,"seller");
        e_.add(PC_TILE_STORAGE,"storage");
        return e_;
    }
    public static TranslationsFile frPcTileKindWild(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(PC_TILE_EXIT,"sortie");
        f_.add(PC_TILE_GERANT,"gérant");
        f_.add(PC_TILE_SELLER,"vendeur");
        f_.add(PC_TILE_STORAGE,"stockage");
        return f_;
    }
    public static TranslationsFile enSellType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(SELL_TYPE_TM,"technical move");
        e_.add(SELL_TYPE_MOVE,"move tutor");
        e_.add(SELL_TYPE_ITEM,"item");
        return e_;
    }
    public static TranslationsFile frSellType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(SELL_TYPE_TM,"capsule technique");
        f_.add(SELL_TYPE_MOVE,"tuteur d'attaque");
        f_.add(SELL_TYPE_ITEM,"objet");
        return f_;
    }
    public static TranslationsFile enGeranceType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(GERANCE_TYPE_FOSSILE,"fossil");
        e_.add(GERANCE_TYPE_HOST,"host");
        e_.add(GERANCE_TYPE_HEAL,"heal");
        return e_;
    }
    public static TranslationsFile frGeranceType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(GERANCE_TYPE_FOSSILE,"fossile");
        f_.add(GERANCE_TYPE_HOST,"pension");
        f_.add(GERANCE_TYPE_HEAL,"soin");
        return f_;
    }
    public static TranslationsFile enLeagueTile(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(LEAGUE_TRAINER,"trainer");
        e_.add(LEAGUE_ACCESS,"access");
        return e_;
    }
    public static TranslationsFile frLeagueTile(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(LEAGUE_TRAINER,"dresseur");
        f_.add(LEAGUE_ACCESS,"accès");
        return f_;
    }
    public static TranslationsFile enDataMapGene() {
        TranslationsFile e_ = new TranslationsFile();
        e_.add(SCREEN_WIDTH,"width of the screen");
        e_.add(SCREEN_HEIGHT,"height of the screen");
        e_.add(SPACE_LEFT,"space between hero and left");
        e_.add(SPACE_TOP,"space between hero and top");
        e_.add(SIDE_LENGTH,"number of pixels in a tile");
        return e_;
    }
    public static TranslationsFile frDataMapGene() {
        TranslationsFile f_ = new TranslationsFile();
        f_.add(SCREEN_WIDTH,"hauteur de l'écran");
        f_.add(SCREEN_HEIGHT,"largeur de l'écran");
        f_.add(SPACE_LEFT,"espace entre le héros et la gauche");
        f_.add(SPACE_TOP,"espace entre le héros et le haut");
        f_.add(SIDE_LENGTH,"nombre de pixels dans une tuile");
        return f_;
    }
    public static TranslationsFile enDataMapLev() {
        TranslationsFile e_ = new TranslationsFile();
        e_.add(GYM_CHECK,"The building is a gym");
        e_.add(DETAIL_TILE_BUILDING,"Detail of the tile in the building");
        e_.add(IT_KIND,"kind of item");
        e_.add(GER_KIND,"kind of gerant");
        e_.add(DELTA_WIDTH,"width of the rectangle");
        e_.add(DELTA_HEIGHT,"height of the rectangle");
        e_.add(AREAS,"areas of appearing");
        e_.add(CONTENT_LEVEL,"content of tiles of the level");
        e_.add(TEAMS,"teams");
        e_.add(AREA_WALK,"while walking");
        e_.add(AREA_FISH,"while fishing");
        e_.add(AREA_MULT,"multiplicity");
        e_.add(AREA_SINGLE,"single");
        e_.add(AREA_STEP,"number of steps");
        e_.add(SOLD_IT,"kind of sold items");
        e_.add(ST_KIND,"kind of status");
        e_.add(UNIQ_LEFT,"left list of single level place");
        e_.add(UNIQ_RIGHT,"right list of single level place");
        e_.add(UNIQ_VIEW_LEFT,"left single view");
        e_.add(UNIQ_VIEW_RIGHT,"right single view");
        e_.add(BASE_ENCODE,"base 64 of encoding image");
        e_.add(LOADED_IMG,"loaded image");
        e_.add(FILE_IMG,"image file searching");
        e_.add(IMG_ENT_NAME,"name of image for entity");
        e_.add(IMG_FREE_NAME,"name of free image");
        e_.add(IMG_HERO_SEX,"sex of hero");
        e_.add(IMG_HERO_DIR,"direction of hero");
        e_.add(IMG_HERO_ENV,"environment for hero");
        e_.add(FILE_IMG_NAME,"name of file");
        e_.add(IMG_RED,"red part color");
        e_.add(IMG_GREEN,"green part color");
        e_.add(IMG_BLUE,"blue part color");
        e_.add(SEL_LEVEL,"selected level");
        e_.add(PK_TEAM,"pokemon team");
        e_.add(KEY_TR,"key string for translating:");
        e_.add(PART_ONE_LIT,"variable:");
        e_.add(PART_TWO_LIT,"description:");
        e_.add(ACC_COND_FORM,"access condition form");
        e_.add(JOIN_UNIQ,"city road join form");
        e_.add(JOIN_CAVE_UNIQ,"place with alone level with cave form");
        e_.add(JOIN_CAVE_UNIQ_REV,"reversed place with alone level with cave form");
        e_.add(SEL_PLACE,"selected place");
        e_.add(EDITED_CITY,"edited city");
        e_.add(EDITED_ROAD,"edited road");
        e_.add(EDITED_CAVE,"edited cave");
        e_.add(EDITED_LEAGUE,"edited league");
        e_.add(ADD_PLACE,"place to be added");
        e_.add(LK_PLACE,"list of levels of places to be bound");
        e_.add(LV_CONT_OUT,"out dour level content");
        e_.add(INTRA_LV_LK,"intra linking levels of a cave");
        e_.add(INTRA_LV_LK_NEXT,"next level");
        e_.add(UNIQ_PLACE,"list of places with single level");
        e_.add(CAVE,"list of levels of cave");
        e_.add(PLACE_NAME,"name of place");
        e_.add(PLACE_KIND,"kind of place");
        e_.add(PLACE_LIST,"list of places");
        e_.add(MINI_MAP,"mini map");
        e_.add(ROWS,"rows");
        e_.add(COLS,"columns");
        e_.add(TILE,"information of tile");
        e_.add(DEST_NB,"identifying number target");
        e_.add(DEST_TR,"identifying string target");
        e_.add(ENUM_VALUES,"translation of enumerated values");
        e_.add(IT_TYPE,"descriptions of items");
        e_.add(CST_DIFF_LAW,"laws about difficulty damage rate");
        e_.add(NUM_VAR,"literal variable strings");
        e_.add(NUM_VAL,"numeric constants");
        e_.add(TR_KEY,"translation values by key for each language");
        return e_;
    }
    public static TranslationsFile frDataMapLev() {
        TranslationsFile f_ = new TranslationsFile();
        f_.add(GYM_CHECK,"Le bâtiment est une arène");
        f_.add(DETAIL_TILE_BUILDING,"Détail de la tuile dans le bâtiment");
        f_.add(IT_KIND,"type d'objet");
        f_.add(GER_KIND,"type de gérant");
        f_.add(DELTA_WIDTH,"largeur du rectangle");
        f_.add(DELTA_HEIGHT,"hauteur du rectangle");
        f_.add(AREAS,"zone d'apparition");
        f_.add(CONTENT_LEVEL,"contenu de tuiles du niveau");
        f_.add(TEAMS,"équipes");
        f_.add(AREA_WALK,"en marchant");
        f_.add(AREA_FISH,"en péchant");
        f_.add(AREA_MULT,"multiplicité");
        f_.add(AREA_SINGLE,"simple");
        f_.add(AREA_STEP,"number de pas");
        f_.add(SOLD_IT,"type d'objets vendus");
        f_.add(ST_KIND,"type de statut");
        f_.add(UNIQ_LEFT,"liste à gauche des lieux à niveau unique");
        f_.add(UNIQ_RIGHT,"liste à droite des lieux à niveau unique");
        f_.add(UNIQ_VIEW_LEFT,"vue du niveau unique à gauche");
        f_.add(UNIQ_VIEW_RIGHT,"vue du niveau unique à droite");
        f_.add(BASE_ENCODE,"base 64 d'encodage d'image");
        f_.add(LOADED_IMG,"image chargée");
        f_.add(FILE_IMG,"recherche de fichier d'image");
        f_.add(IMG_ENT_NAME,"nom d'image pour entité");
        f_.add(IMG_FREE_NAME,"nom d'image libre");
        f_.add(IMG_HERO_SEX,"sexe du héros");
        f_.add(IMG_HERO_DIR,"direction du héros");
        f_.add(IMG_HERO_ENV,"environnement pour le héros");
        f_.add(FILE_IMG_NAME,"nom de fichier");
        f_.add(IMG_RED,"partie rouge de couleur");
        f_.add(IMG_GREEN,"partie verte de couleur");
        f_.add(IMG_BLUE,"partie bleue de couleur");
        f_.add(SEL_LEVEL,"niveau sélectionné");
        f_.add(PK_TEAM,"équipe pokemon");
        f_.add(KEY_TR,"chaîne clé pour traduire:");
        f_.add(PART_ONE_LIT,"variable:");
        f_.add(PART_TWO_LIT,"description:");
        f_.add(ACC_COND_FORM,"formulaire de condition d'accès");
        f_.add(JOIN_UNIQ,"formulaire de jointure de ville et route");
        f_.add(JOIN_CAVE_UNIQ,"formulaire de jointure de lieu à niveau unique et cave");
        f_.add(JOIN_CAVE_UNIQ_REV,"formulaire inversé de jointure de lieu à niveau unique et cave");
        f_.add(SEL_PLACE,"lieu sélectionné");
        f_.add(EDITED_CITY,"ville éditée");
        f_.add(EDITED_ROAD,"route éditée");
        f_.add(EDITED_CAVE,"cave éditée");
        f_.add(EDITED_LEAGUE,"ligue éditée");
        f_.add(ADD_PLACE,"lieu à être ajouté");
        f_.add(LK_PLACE,"liste de niveaux de lieux à être liés");
        f_.add(LV_CONT_OUT,"contenu du niveau extérieur");
        f_.add(INTRA_LV_LK,"niveaux intra liés d'une cave");
        f_.add(INTRA_LV_LK_NEXT,"niveau suivant");
        f_.add(UNIQ_PLACE,"liste de lieux avec niveau unique");
        f_.add(CAVE,"liste de niveaux de cave");
        f_.add(PLACE_NAME,"nom de lieu");
        f_.add(PLACE_KIND,"type de lieu");
        f_.add(PLACE_LIST,"liste de lieux");
        f_.add(MINI_MAP,"mini carte");
        f_.add(ROWS,"lignes");
        f_.add(COLS,"colonnes");
        f_.add(TILE,"informations de tuile");
        f_.add(DEST_NB,"nombre identifiant cible");
        f_.add(DEST_TR,"chaîne identifiant cible");
        f_.add(ENUM_VALUES,"traduction des valeurs énumérées");
        f_.add(IT_TYPE,"descriptions d'objets");
        f_.add(CST_DIFF_LAW,"lois à propos de la difficulté du taux de dégâts");
        f_.add(NUM_VAR,"chaînes variables littérales");
        f_.add(NUM_VAL,"constantes numériques");
        f_.add(TR_KEY,"valeurs de traduction par clé pour chaque langue");
        return f_;
    }
    public static TranslationsFile enButtons() {
        TranslationsFile e_ = new TranslationsFile();
        e_.add(BUTTON_LINK_PLACE_CAVE_ONE,"Link a cave to a place with single level");
        e_.add(BUTTON_LINK_CAVE_PLACE_ONE,"Link a place with single level to a cave");
        e_.add(BUTTON_LINK_PLACE_CAVE_BOTH,"Link a cave to a place with single level both directions");
        e_.add(BUTTON_LINK_CAVE_PLACE_BOTH,"Link a place with single level to a cave both directions");
        e_.add(BUTTON_UNLINK_PLACE_CAVE_ONE,"Unlink a cave to a place with single level");
        e_.add(BUTTON_UNLINK_CAVE_PLACE_ONE,"Unlink a place with single level to a cave");
        e_.add(BUTTON_UNLINK_PLACE_CAVE_BOTH,"Unlink a cave to a place with single level both directions");
        e_.add(BUTTON_UNLINK_CAVE_PLACE_BOTH,"Unlink a place with single level to a cave both directions");
        e_.add(BUTTON_LINK_LEVELS_LEFT,"Link levels of cave from left to right");
        e_.add(BUTTON_LINK_LEVELS_RIGHT,"Link levels of cave from right to left");
        e_.add(BUTTON_LINK_LEVELS_BOTH,"Link levels of cave both directions");
        e_.add(BUTTON_UNLINK_LEVELS_LEFT,"Unlink levels of cave from left to right");
        e_.add(BUTTON_UNLINK_LEVELS_RIGHT,"Unlink levels of cave from right to left");
        e_.add(BUTTON_UNLINK_LEVELS_BOTH,"Unlink levels of cave both directions");
        e_.add(BUTTON_IMG_LINK_LEFT,"Choose a image link for left level");
        e_.add(BUTTON_IMG_LINK_RIGHT,"Choose a image link for right level");
        e_.add(BUTTON_IMG_LINK_BOTH,"Choose a image link for both levels");
        return e_;
    }
    public static TranslationsFile frButtons() {
        TranslationsFile f_ = new TranslationsFile();
        f_.add(BUTTON_LINK_PLACE_CAVE_ONE,"Lier une cave à un lieu avec niveau unique");
        f_.add(BUTTON_LINK_CAVE_PLACE_ONE,"Lier un lieu avec niveau unique à une cave");
        f_.add(BUTTON_LINK_PLACE_CAVE_BOTH,"Lier une cave à un lieu avec niveau unique deux directions");
        f_.add(BUTTON_LINK_CAVE_PLACE_BOTH,"Lier un lieu avec niveau unique à une cave deux directions");
        f_.add(BUTTON_UNLINK_PLACE_CAVE_ONE,"Délier une cave à un lieu avec niveau unique");
        f_.add(BUTTON_UNLINK_CAVE_PLACE_ONE,"Délier un lieu avec niveau unique à une cave");
        f_.add(BUTTON_UNLINK_PLACE_CAVE_BOTH,"Délier une cave à un lieu avec niveau unique deux directions");
        f_.add(BUTTON_UNLINK_CAVE_PLACE_BOTH,"Délier un lieu avec niveau unique à une cave deux directions");
        f_.add(BUTTON_LINK_LEVELS_LEFT,"Délier un lieu avec niveau unique à une cave deux directions");
        f_.add(BUTTON_LINK_LEVELS_LEFT,"Lier niveaux de cave de gauche à droite");
        f_.add(BUTTON_LINK_LEVELS_RIGHT,"Lier niveaux de cave de droite à gauche");
        f_.add(BUTTON_LINK_LEVELS_BOTH,"Lier niveaux de cave deux directions");
        f_.add(BUTTON_UNLINK_LEVELS_LEFT,"Délier niveaux de cave de gauche à droite");
        f_.add(BUTTON_UNLINK_LEVELS_RIGHT,"Délier niveaux de cave de droite à gauche");
        f_.add(BUTTON_UNLINK_LEVELS_BOTH,"Délier niveaux de cave deux directions");
        f_.add(BUTTON_IMG_LINK_LEFT,"Choisir un lien d'image pour niveau gauche");
        f_.add(BUTTON_IMG_LINK_RIGHT,"Choisir un lien d'image pour niveau droit");
        f_.add(BUTTON_IMG_LINK_BOTH,"Choisir un lien d'image pour deux niveaux");
        return f_;
    }
}
