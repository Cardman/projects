package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsBerry {
    public static final String M_P_17_CATEGORY="category";
    public static final String M_P_17_CATEGORY_BOOST="category_boost";
    public static final String M_P_17_CATEGORY_STAT="category_stat";
    public static final String M_P_17_HEAL_HP="heal_hp";
    public static final String M_P_17_HEAL_HP_TIME="heal_hp_time";
    public static final String M_P_17_HEAL_HP_ONLY_ROUND_HEAL="heal_hp_only_round_heal";
    public static final String M_P_17_HEAL_HP_RATE="heal_hp_rate";
    public static final String M_P_17_HEAL_HP_RATE_TIME="heal_hp_rate_time";
    public static final String M_P_17_HEAL_HP_RATE_ONLY_ROUND_HEAL="heal_hp_rate_only_round_heal";
    public static final String M_P_17_HEAL_PP="heal_pp";
    public static final String M_P_17_HEAL_STATUS="heal_status";
    public static final String M_P_17_MULT_DAMAGE_FOE="mult_damage_foe";
    public static final String M_P_17_MULT_DAMAGE_FOE_EFF="mult_damage_foe_eff";
    public static final String M_P_17_MULT_DAMAGE_FOE_RATE="mult_damage_foe_rate";
    public static final String M_P_17_MULT_DAMAGE_FOE_TYPE="mult_damage_foe_type";
    public static final String M_P_17_MULT_STAT="mult_stat";
    public static final String M_P_17_MULT_STAT_BOOST="mult_stat_boost";
    public static final String M_P_17_MULT_STAT_HP="mult_stat_hp";
    public static final String M_P_17_MULT_STAT_KEY="mult_stat_key";
    public static final String M_P_17_RECOIL="recoil";
    public static final String M_P_17_RECOIL_CAT="recoil_cat";
    public static final String M_P_17_RECOIL_HP="recoil_hp";
    public static final String M_P_17_SORTING_USERS="sorting_users";
    public static final String M_P_17_WIN_SUPER_EFF="win_super_eff";
    public static final String M_P_17_WITHOUT_FAIL="without_fail";
    private MessagesDataItemsBerry(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_17_CATEGORY,"The berry is enabled while the owner suffers damage from a move directly used by a fighter.\nIf the owner suffers from a move with the category {0}, then its statistics vary by the following kind:");
        e_.add(M_P_17_CATEGORY_BOOST,"Boost");
        e_.add(M_P_17_CATEGORY_STAT,"Statistic");
        e_.add(M_P_17_HEAL_HP,"If the rate of full life of the owner of the berry is at most {0}, then {1} hp are restored.The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} health points.");
        e_.add(M_P_17_HEAL_HP_TIME,"The berry is enabled at the end of the round of the owner.");
        e_.add(M_P_17_HEAL_HP_ONLY_ROUND_HEAL,"The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} health points.");
        e_.add(M_P_17_HEAL_HP_RATE,"If the current rate of full life of the owner of the berry is lower or equals to {0}, then the rate of full restored life is {1}.The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} of its full life.");
        e_.add(M_P_17_HEAL_HP_RATE_TIME,"The berry is enabled at the end of the round of the owner.");
        e_.add(M_P_17_HEAL_HP_RATE_ONLY_ROUND_HEAL,"The player can use a such berry never mind the remaining health points strictly lower at the maximum, in this case the pokemon is healed by {1} of its full life.");
        e_.add(M_P_17_HEAL_PP,"The berry is enabled at the end of the round of the owner.\nIf the owner of the berry has one of its moves without PP, then {0} PP are restored fot this move.");
        e_.add(M_P_17_HEAL_STATUS,"The berry is enabled while the owner is targetted by an adding of status.\nThe following status of the owner are deleted:");
        e_.add(M_P_17_MULT_DAMAGE_FOE,"The berry is enabled while the owner suffers damage from a move directly used by a fighter.\nWhile the owner is targetted by a super effective move or not effective by default, the damage rate inflicted varies in function by the type of the suffered move:");
        e_.add(M_P_17_MULT_DAMAGE_FOE_EFF,"Efficiency of the move");
        e_.add(M_P_17_MULT_DAMAGE_FOE_RATE,"Rate");
        e_.add(M_P_17_MULT_DAMAGE_FOE_TYPE,"Type of the move");
        e_.add(M_P_17_MULT_STAT,"The berry is enabled at the end of the round of the owner.\nIn function of the current rate of full rate of the owner, les levels of statistics of the owner vary by the following kind:");
        e_.add(M_P_17_MULT_STAT_BOOST,"Variation of level");
        e_.add(M_P_17_MULT_STAT_HP,"Rate of full life");
        e_.add(M_P_17_MULT_STAT_KEY,"Statistic");
        e_.add(M_P_17_RECOIL,"The berry is enabled while the owner suffers damage from a move directly used by a fighter.\nWhile the owner suffers damage from a move, the recoil damage inflicted against the user vary by the category of the move:");
        e_.add(M_P_17_RECOIL_CAT,"Category of the suffered move");
        e_.add(M_P_17_RECOIL_HP,"Rate of lost life by the user");
        e_.add(M_P_17_SORTING_USERS,"The berry is enabled at the moment of sorting the users by order of using.\nIf the owner of the berry has to use a move after a pokemon not having a berry, then the owner of the berry uses first a move.");
        e_.add(M_P_17_WIN_SUPER_EFF,"The berry is enabled while the owner suffers damage from a move directly used by a fighter.\nIf the owner of the berry is targetted by a super effective move and can use a berry, then the rate of full life restoring the owner is {0}, the berry is not used if the owner had all its hp.");
        e_.add(M_P_17_WITHOUT_FAIL,"The berry is enabled while the owner uses a move.\nIf the owner of the berry is going to fail using a move, then the owner successes the move.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_17_CATEGORY,"La baie est activée lorsque le possesseur subit des dégâts d''une attaque utilisée directement par un combattant.\nSi le possesseur subit une attaque de catégorie {0}, alors ses statistiques suivantes varient de la façon suivante:");
        f_.add(M_P_17_CATEGORY_BOOST,"Boost");
        f_.add(M_P_17_CATEGORY_STAT,"Statistique");
        f_.add(M_P_17_HEAL_HP,"Si le taux de vie totale du porteur de la baie est de au plus {0}, alors {1} pv sont restaurés.Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inférieurs au maximum, dans ce cas le pokemon est soigné de {1} points de vie.");
        f_.add(M_P_17_HEAL_HP_TIME,"La baie est activée en fin de tour du possesseur.");
        f_.add(M_P_17_HEAL_HP_ONLY_ROUND_HEAL,"Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inférieurs au maximum, dans ce cas le pokemon est soigné de {1} points de vie.");
        f_.add(M_P_17_HEAL_HP_RATE,"Si le taux de vie totale actuelle du porteur de la baie est de au plus {0}, alors le taux de vie totale récupérée vaut {1}.Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inférieurs au maximum, dans ce cas le pokemon est soigné de {1} de sa vie totale.");
        f_.add(M_P_17_HEAL_HP_RATE_TIME,"La baie est activée en fin de tour du possesseur.");
        f_.add(M_P_17_HEAL_HP_RATE_ONLY_ROUND_HEAL,"Le joueur peut utiliser une telle baie peu importe les points de vie restants strictement inférieurs au maximum, dans ce cas le pokemon est soigné de {1} de sa vie totale.");
        f_.add(M_P_17_HEAL_PP,"La baie est activée en fin de tour du possesseur.\nSi le porteur de la baie a une de ses attaques sans PP, alors {0} PP sont restaurés pour cette attaque.");
        f_.add(M_P_17_HEAL_STATUS,"La baie est activée lorsque le possesseur est visé par une altération de statut.\nLes statuts suivants du porteur sont guéris:");
        f_.add(M_P_17_MULT_DAMAGE_FOE,"La baie est activée lorsque le possesseur subit des dégâts d''une attaque utilisée directement par un combattant.\nLorsque le porteur est touché par une attaque de type super efficace sur lui ou de type non efficace de base, le coefficient des dégâts infligés varie en fonction du type de l''attaque subie:");
        f_.add(M_P_17_MULT_DAMAGE_FOE_EFF,"Efficacité de l''attaque");
        f_.add(M_P_17_MULT_DAMAGE_FOE_RATE,"Coefficient");
        f_.add(M_P_17_MULT_DAMAGE_FOE_TYPE,"Type de l''attaque");
        f_.add(M_P_17_MULT_STAT,"La baie est activée en fin de tour du possesseur.\nEn fonction du taux de vie totale actuelle du porteur, les crans des statistiques du porteur varient de la façon suivante:");
        f_.add(M_P_17_MULT_STAT_BOOST,"Variation du cran");
        f_.add(M_P_17_MULT_STAT_HP,"Taux de vie maximal");
        f_.add(M_P_17_MULT_STAT_KEY,"Statistique");
        f_.add(M_P_17_RECOIL,"La baie est activée lorsque le possesseur subit des dégâts d''une attaque utilisée directement par un combattant.\nLorsque le porteur subit les dégâts d''une attaque, les dégâts de recul infligés au lanceur varient en fonction de la catégorie de l''attaque:");
        f_.add(M_P_17_RECOIL_CAT,"Catégorie de l''attaque subie");
        f_.add(M_P_17_RECOIL_HP,"Taux de vie perdu par le lanceur");
        f_.add(M_P_17_SORTING_USERS,"La baie est activée au moment de classer les lanceurs par ordre de lancer.\nSi le porteur de la baie est censé attaquer après un pokémon ne portant la baie, alors le porteur de la baie attaque d''abord.");
        f_.add(M_P_17_WIN_SUPER_EFF,"La baie est activée lorsque le possesseur subit des dégâts d''une attaque utilisée directement par un combattant.\nSi le porteur de la baie est touché par une attaque super efficace et peut utiliser une baie, alors le taux de vie totale récupérée du porteur vaut {0}, la baie n''est pas utilisée si le porteur avait tous ses pv.");
        f_.add(M_P_17_WITHOUT_FAIL,"La baie est activée lorsque le possesseur attaque.\nSi le porteur de la baie vient à échouer une attaque, alors il la réussit.");
        return f_;
    }
}