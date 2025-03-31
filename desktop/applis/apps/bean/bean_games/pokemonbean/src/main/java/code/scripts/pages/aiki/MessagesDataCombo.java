package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataCombo {
    public static final String M_P_2_EFFECT="effect";
    public static final String M_P_2_ENDROUND="endRound";
    public static final String M_P_2_INDEX="index";
    public static final String M_P_2_LAW_REPEAT="law_repeat";
    public static final String M_P_2_LAW_REPEAT_KEY="law_repeat_key";
    public static final String M_P_2_LAW_REPEAT_VALUE="law_repeat_value";
    public static final String M_P_2_MULT_STAT_FOE="mult_stat_foe";
    public static final String M_P_2_RANK_INCREMENT_NB_ROUND="rank_increment_nb_round";
    public static final String M_P_2_RATE="rate";
    public static final String M_P_2_RATE_SEC_EFF="rate_sec_eff";
    public static final String M_P_2_STATISTIC="statistic";
    public static final String M_P_2_TITLE="title";
    private MessagesDataCombo(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_2_EFFECT,"The effect is only enabled if the following move are successful in the same round:");
        e_.add(M_P_2_ENDROUND,"See the end of round");
        e_.add(M_P_2_INDEX,"Return to the index");
        e_.add(M_P_2_LAW_REPEAT,"The number of rounds is calculated in the following law of averages:");
        e_.add(M_P_2_LAW_REPEAT_KEY,"Number of rounds");
        e_.add(M_P_2_LAW_REPEAT_VALUE,"Probability");
        e_.add(M_P_2_MULT_STAT_FOE,"The statistics of the members of the foe team of the user are multiplied according to the following table:");
        e_.add(M_P_2_RANK_INCREMENT_NB_ROUND,"The rank of incrementing of end of round is {0}.");
        e_.add(M_P_2_RATE,"Rate");
        e_.add(M_P_2_RATE_SEC_EFF,"The probability of second effects used by a member of the team of the user is multiplied by {0}.");
        e_.add(M_P_2_STATISTIC,"Statistic");
        e_.add(M_P_2_TITLE,"Enabled groups of moves");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_2_EFFECT,"L'effet n'est actif que si les attaques suivantes sont réussies dans le même tour:");
        f_.add(M_P_2_ENDROUND,"Voir l'effet de fin de tour");
        f_.add(M_P_2_INDEX,"Revenir à l'indexe");
        f_.add(M_P_2_LAW_REPEAT,"Le nombre de tours déterminé par les probabilités suivantes:");
        f_.add(M_P_2_LAW_REPEAT_KEY,"Nombre de tours");
        f_.add(M_P_2_LAW_REPEAT_VALUE,"Probabilité");
        f_.add(M_P_2_MULT_STAT_FOE,"Les statistiques des membres de l'équipe adverse du lanceur sont multipliées selon le tableau suivant:");
        f_.add(M_P_2_RANK_INCREMENT_NB_ROUND,"Le rand d''incrémentation de fin tour est de {0}.");
        f_.add(M_P_2_RATE,"Coefficient");
        f_.add(M_P_2_RATE_SEC_EFF,"La probabilité des effets secondaires lancées par un membre de l''équipe du lanceur est multipliée par {0}.");
        f_.add(M_P_2_STATISTIC,"Statistique");
        f_.add(M_P_2_TITLE,"Groupes d'attaques actives");
        return f_;
    }
}