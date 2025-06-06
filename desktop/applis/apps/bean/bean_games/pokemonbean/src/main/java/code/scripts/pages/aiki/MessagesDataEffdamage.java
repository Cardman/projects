package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffdamage {
    public static final String M_P_45_POWER="power";
    public static final String M_P_45_TARGET="target";
    public static final String M_P_45_USER="user";
    public static final String M_P_45_ATTACK_INTRO ="attack_intro";
    public static final String M_P_45_ATTACK_TARGET="attack_target";
    public static final String M_P_45_ATTACK_USER="attack_user";
    public static final String M_P_45_BOOST="boost";
    public static final String M_P_45_BOOST_STATIS_ONCE_KO_FOE="boost_statis_once_ko_foe";
    public static final String M_P_45_CATEGORY="category";
    public static final String M_P_45_CH_LAW="ch_law";
    public static final String M_P_45_CH_RATE="ch_rate";
    public static final String M_P_45_CH_RATE_INTRO="ch_rate_intro";
    public static final String M_P_45_CONST_DAMAGE="const_damage";
    public static final String M_P_45_CONST_DAMAGE_INTRO="const_damage_intro";
    public static final String M_P_45_CONST_POWER="const_power";
    public static final String M_P_45_DAMAGE_MULT_COUNTER="damage_mult_counter";
    public static final String M_P_45_DAMAG_LAW="damag_law";
    public static final String M_P_45_DAMAG_LAW_CONST="damag_law_const";
    public static final String M_P_45_DEFENSE_INTRO="defense_intro";
    public static final String M_P_45_DEFENSE_TARGET="defense_target";
    public static final String M_P_45_DEFENSE_USER="defense_user";
    public static final String M_P_45_EFFECT="effect";
    public static final String M_P_45_EVENT="event";
    public static final String M_P_45_EVENT_NB_HITS="event_nb_hits";
    public static final String M_P_45_EVENT_RATE="event_rate";
    public static final String M_P_45_FORMULA="formula";
    public static final String M_P_45_HIT_LAW="hit_law";
    public static final String M_P_45_HIT_LAW_CONST="hit_law_const";
    public static final String M_P_45_IGN_NEG_STAT="ign_neg_stat";
    public static final String M_P_45_IGN_POS_STAT="ign_pos_stat";
    public static final String M_P_45_RAND_MAX="rand_max";
    public static final String M_P_45_RATE="rate";
    public static final String M_P_45_RATE_EVENT="rate_event";
    public static final String M_P_45_STATISTIC="statistic";
    public static final String M_P_45_SUMMING_TEAM="summing_team";
    public static final String M_P_45_VAR_POWER="var_power";
    private MessagesDataEffdamage(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_45_POWER,"Power formula");
        e_.add(M_P_45_TARGET,"Target for defense");
        e_.add(M_P_45_USER,"User for attack");
        e_.add(M_P_45_ATTACK_INTRO,"By being value for the attack, used statistic for calculate the damage of the move:");
        e_.add(M_P_45_ATTACK_TARGET,"By being value for the attack, the statistic {0} of the target is used for calculate the damage of the move.");
        e_.add(M_P_45_ATTACK_USER,"By being value for the attack, the statistic {0} of the user is used for calculate the damage of the move.");
        e_.add(M_P_45_BOOST,"Increasing");
        e_.add(M_P_45_BOOST_STATIS_ONCE_KO_FOE,"If the target is knocked out by this move, the increased statistics of the user are the following one:");
        e_.add(M_P_45_CATEGORY,"Category");
        e_.add(M_P_45_CH_LAW,"The law of critical hits is the following one:");
        e_.add(M_P_45_CH_RATE,"The base level of critical hit is {0}.");
        e_.add(M_P_45_CH_RATE_INTRO,"Base level of critical hit:");
        e_.add(M_P_45_CONST_DAMAGE,"Except for failing, the target loosed {0} hp.");
        e_.add(M_P_45_CONST_DAMAGE_INTRO,"Except for failing, loosed hp for the target:");
        e_.add(M_P_45_CONST_POWER,"The base power of the move is {0}.");
        e_.add(M_P_45_DAMAGE_MULT_COUNTER,"Here is the damage rates suffered by the user during the round in fonction by the category:");
        e_.add(M_P_45_DAMAG_LAW,"The inflicted damage is calculated from the following law:");
        e_.add(M_P_45_DAMAG_LAW_CONST,"The inflicted damage are always: {0}.");
        e_.add(M_P_45_DEFENSE_INTRO,"By being value for the defense, used statistic for calculate the damage of the move:");
        e_.add(M_P_45_DEFENSE_TARGET,"By being value for the defense, the statistic {0} of the target is used for calculate the damage of the move.");
        e_.add(M_P_45_DEFENSE_USER,"By being value for the defense, the statistic {0} of the user is used for calculate the damage of the move.");
        e_.add(M_P_45_EFFECT,"This effect inflicts damage against targets with a constant value of damage points or not.");
        e_.add(M_P_45_EVENT,"Event");
        e_.add(M_P_45_EVENT_NB_HITS,"Number of hits");
        e_.add(M_P_45_EVENT_RATE,"Rate");
        e_.add(M_P_45_FORMULA,"{0}");
        e_.add(M_P_45_HIT_LAW,"The number of repeated hits for the move is calculated from the following law:");
        e_.add(M_P_45_HIT_LAW_CONST,"The number of repeated hits for the move is {0}.");
        e_.add(M_P_45_IGN_NEG_STAT,"The move ignores the following negative variations of the statistics of the user:");
        e_.add(M_P_45_IGN_POS_STAT,"The move ignores the following positive variations of the statistics of the target:");
        e_.add(M_P_45_RAND_MAX,"The value for the random rate edited for the calculation of damage is always maximum.");
        e_.add(M_P_45_RATE,"Rate");
        e_.add(M_P_45_RATE_EVENT,"Rate");
        e_.add(M_P_45_STATISTIC,"Statistic");
        e_.add(M_P_45_SUMMING_TEAM,"Each not KO partner and without status uses a move against the target with the same base power.");
        e_.add(M_P_45_VAR_POWER,"The base power of the move has for litteral expression: {0}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_45_POWER,"Formule de la puissance");
        f_.add(M_P_45_TARGET,"Cible pour la défense");
        f_.add(M_P_45_USER,"Lanceur pour l'attaque");
        f_.add(M_P_45_ATTACK_INTRO,"En tant que valeur pour l'attaque, statistique utilisée pour calculer les dégâts de l'attaque:");
        f_.add(M_P_45_ATTACK_TARGET,"En tant que valeur pour l''attaque, la statistique {0} de la cible est utilisée pour calculer les dégâts de l''attaque.");
        f_.add(M_P_45_ATTACK_USER,"En tant que valeur pour l''attaque, la statistique {0} du lanceur est utilisée pour calculer les dégâts de l''attaque.");
        f_.add(M_P_45_BOOST,"Augmentation");
        f_.add(M_P_45_BOOST_STATIS_ONCE_KO_FOE,"Si la cible tombe Ko par cette attaque, les statistiques du lanceur augmentées sont les suivantes:");
        f_.add(M_P_45_CATEGORY,"Catégorie");
        f_.add(M_P_45_CH_LAW,"La loi de probabilité des coups critiques est la suivante:");
        f_.add(M_P_45_CH_RATE,"Le niveau de coup critique de base est {0}.");
        f_.add(M_P_45_CH_RATE_INTRO,"Niveau de coup critique de base:");
        f_.add(M_P_45_CONST_DAMAGE,"Sauf en cas d''échec, la cible perd {0} pv.");
        f_.add(M_P_45_CONST_DAMAGE_INTRO,"Sauf en cas d'échec, pv perdus pour la cible:");
        f_.add(M_P_45_CONST_POWER,"La puissance de base de l''attaque est {0}.");
        f_.add(M_P_45_DAMAGE_MULT_COUNTER,"Voici les coefficients des dégâts subits par le lanceur pendant le tour en fonction de la catégorie:");
        f_.add(M_P_45_DAMAG_LAW,"Les dégâts infligés suivent la loi suivante.");
        f_.add(M_P_45_DAMAG_LAW_CONST,"Les dégâts infligés valent toujours: {0}.");
        f_.add(M_P_45_DEFENSE_INTRO,"En tant que valeur pour la défense, statistique utilisée pour calculer les dégâts de l'attaque:");
        f_.add(M_P_45_DEFENSE_TARGET,"En tant que valeur pour la défense, la statistique {0} de la cible est utilisée pour calculer les dégâts de l''attaque.");
        f_.add(M_P_45_DEFENSE_USER,"En tant que valeur pour la défense, la statistique {0} du lanceur est utilisée pour calculer les dégâts de l''attaque.");
        f_.add(M_P_45_EFFECT,"Cet effet inflige des dégâts aux cibles avec une valeur fixe de points de dégâts ou non.");
        f_.add(M_P_45_EVENT,"Evénement");
        f_.add(M_P_45_EVENT_NB_HITS,"Nombre de coups");
        f_.add(M_P_45_EVENT_RATE,"Coefficient");
        f_.add(M_P_45_FORMULA,"{0}");
        f_.add(M_P_45_HIT_LAW,"Le nombre de coups répétés pour l'attaque suit la loi suivante:");
        f_.add(M_P_45_HIT_LAW_CONST,"Le nombre de coups répétés pour l''attaque est de {0}.");
        f_.add(M_P_45_IGN_NEG_STAT,"L'attaque ignore les variations négatives des statistiques du lanceur suivantes:");
        f_.add(M_P_45_IGN_POS_STAT,"L'attaque ignore les variations positives des statistiques de la cible suivantes:");
        f_.add(M_P_45_RAND_MAX,"La valeur pour le coefficient aléatoire tiré pour le calcul des dégâts est toujours maximal.");
        f_.add(M_P_45_RATE,"Coefficient");
        f_.add(M_P_45_RATE_EVENT,"Probabilité");
        f_.add(M_P_45_STATISTIC,"Statistique");
        f_.add(M_P_45_SUMMING_TEAM,"Chaque partenaire non KO et sans statut attaque la cible avec la même puissance de base.");
        f_.add(M_P_45_VAR_POWER,"La puissance de base de l''attaque a pour expression littérale: {0}.");
        return f_;
    }
}