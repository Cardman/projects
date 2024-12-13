package aiki.gui.components.editor;

import aiki.db.*;
import code.sml.util.*;

public final class MessagesEditorSelect {
    public static final String EVO_LEVEL_SIMPLE = "0";
    public static final String EVO_LEVEL_GENDER = "1";
    public static final String EVO_STONE_SIMPLE = "2";
    public static final String EVO_STONE_GENDER = "3";
    public static final String EVO_HAPPINESS = "4";
    public static final String EVO_ITEM = "5";
    public static final String EVO_MOVE = "6";
    public static final String EVO_MOVE_TYPE = "7";
    public static final String EVO_TEAM = "8";
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
        e_.add(DataBase.DEF_EXCHANGE_TYPE_NOTHING,"");
        return e_;
    }
    public static TranslationsFile frExchangeType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_TARGET,"La cible prend depuis le lanceur");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_TO_THROWER,"Le lanceur prend depuis la cible");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_EXCHANGE,"Le lanceur et la cible s'échangent");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_GIVE_CONST,"La cible prend une entité");
        f_.add(DataBase.DEF_EXCHANGE_TYPE_NOTHING,"");
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
        e_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_NOTHING,"");
        return e_;
    }
    public static TranslationsFile frPointViewChangementType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_THIEF_BONUSES,"Le lanceur de cette attaque attend que sa cible lance une attaque \"saisissable\"");
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_MIRROR_AGAINST_THROWER,"Le possesseur de cet effet contre l'utilisateur initial");
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_ATTRACT_DAMAGES_MOVES,"Le lanceur de cette attaque subit les dégâts des attaques à cible unique au lieu de ses partenaires");
        f_.add(DataBase.DEF_POINT_VIEW_CHANGEMENT_TYPE_NOTHING,"");
        return f_;
    }
    public static TranslationsFile enConstValuesType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_CONST_VALUES_TYPE_LANCEUR_ATTAQUES_TYPES,"The user of this move waits that its target use a \"thievable\" move");
        e_.add(DataBase.DEF_CONST_VALUES_TYPE_TYPES_ATTAQUES_RES,"The owner of this effect counters against the initial user");
        e_.add(DataBase.DEF_CONST_VALUES_TYPE_NOTHING,"");
        return e_;
    }
    public static TranslationsFile frConstValuesType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_CONST_VALUES_TYPE_LANCEUR_ATTAQUES_TYPES,"Le lanceur de cette attaque attend que sa cible lance une attaque \"saisissable\"");
        f_.add(DataBase.DEF_CONST_VALUES_TYPE_TYPES_ATTAQUES_RES,"Le possesseur de cet effet contre l'utilisateur initial");
        f_.add(DataBase.DEF_CONST_VALUES_TYPE_NOTHING,"");
        return f_;
    }
    public static TranslationsFile enMoveChoiceRestrictionType(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORCE,"The fighter must reuse the move having just been chosen during some rounds");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORBIDDEN,"The fighter cannot reuse the move having just been chosen during some rounds");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_LANCEUR_ATTAQUES,"The target cannot use the moves that the user owns");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_DER,"The fighter cannot choose consecutively the same move");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_CATEGORIE_AUTRE,"The fighter cannot use not damaging moves anymore");
        e_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_NOTHING,"");
        return e_;
    }
    public static TranslationsFile frMoveChoiceRestrictionType(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORCE,"Le combattant doit réutiliser l'attaque venant d'être choisie pendant un certain nombre de tour");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_FORBIDDEN,"Le combattant ne peut pas réutiliser l'attaque venant d'être choisie pendant un certain nombre de tour");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_LANCEUR_ATTAQUES,"La cible ne peut pas utiliser les attaques que le lanceur possède");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_DER,"Le combattant ne peut pas choisir consécutivement la même attaque");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_CATEGORIE_AUTRE,"Le combattant ne peut plus utiliser les attaques non offensives");
        f_.add(DataBase.DEF_MOVE_CHOICE_RESTRICTION_TYPE_NOTHING,"");
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
}
