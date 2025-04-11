package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffstatis {
    public static final String M_P_58_ALWAYS_ENABLED="always_enabled";
    public static final String M_P_58_BOOST="boost";
    public static final String M_P_58_CANCEL_CHGT_STAT_INTRO="cancel_chgt_stat_intro";
    public static final String M_P_58_CANCEL_LOW_STAT_INTRO="cancel_low_stat_intro";
    public static final String M_P_58_CANCEL_CHGT_STAT="cancel_chgt_stat";
    public static final String M_P_58_CANCEL_LOW_STAT="cancel_low_stat";
    public static final String M_P_58_COPY_BOOST="copy_boost";
    public static final String M_P_58_EFFECT="effect";
    public static final String M_P_58_FAIL="fail";
    public static final String M_P_58_FAIL_VAR="fail_var";
    public static final String M_P_58_FAIL_SWAP="fail_swap";
    public static final String M_P_58_FORMULA="formula";
    public static final String M_P_58_RATE="rate";
    public static final String M_P_58_RATE_ENABLED="rate_enabled";
    public static final String M_P_58_RATE_EVENT="rate_event";
    public static final String M_P_58_STATISTIC="statistic";
    public static final String M_P_58_SWAP_BOOST="swap_boost";
    public static final String M_P_58_VAR_STATIS_RANK="var_statis_rank";
    public static final String M_P_58_LAW="law";
    private MessagesDataEffstatis(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_58_ALWAYS_ENABLED,"The effect always happens.");
        e_.add(M_P_58_BOOST,"Variation of the level of the statistic");
        e_.add(M_P_58_CANCEL_CHGT_STAT_INTRO,"The following statistics of the fighter are:");
        e_.add(M_P_58_CANCEL_LOW_STAT_INTRO,"The following lowered statistics of the fighter are:");
        e_.add(M_P_58_CANCEL_CHGT_STAT,"The following statistics of the fighter are now {0}:");
        e_.add(M_P_58_CANCEL_LOW_STAT,"The following lowered statistics of the fighter are now {0}:");
        e_.add(M_P_58_COPY_BOOST,"The user copies the levels of the following statistics of the target:");
        e_.add(M_P_58_EFFECT,"This effect changes levels or values of statistics for a fighter at least.");
        e_.add(M_P_58_FAIL,"Sufficient conditions of fail");
        e_.add(M_P_58_FAIL_VAR,"Sufficient conditions of fail for variation of level");
        e_.add(M_P_58_FAIL_SWAP,"Sufficient conditions of fail for swapping statistic values");
        e_.add(M_P_58_FORMULA,"{0}");
        e_.add(M_P_58_RATE,"The effect happens with a rate:");
        e_.add(M_P_58_RATE_ENABLED,"The effect happens with a rate of {0} being about {1} %.");
        e_.add(M_P_58_RATE_EVENT,"Rate");
        e_.add(M_P_58_STATISTIC,"Statistic");
        e_.add(M_P_58_SWAP_BOOST,"The user and the target switch the levels of the following statistics:");
        e_.add(M_P_58_VAR_STATIS_RANK,"Here is the table of variations of statistics of the target:");
        e_.add(M_P_58_LAW,"Law of statistic variation");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_58_ALWAYS_ENABLED,"L'effet a toujours lieu.");
        f_.add(M_P_58_BOOST,"Variation du boost de la statistique");
        f_.add(M_P_58_CANCEL_CHGT_STAT_INTRO,"Les statistiques du combattant suivantes sont:");
        f_.add(M_P_58_CANCEL_LOW_STAT_INTRO,"Les statistiques baissées du combattant suivantes sont:");
        f_.add(M_P_58_CANCEL_CHGT_STAT,"Les statistiques du combattant suivantes sont remises à {0}:");
        f_.add(M_P_58_CANCEL_LOW_STAT,"Les statistiques baissées du combattant suivantes sont remises à {0}:");
        f_.add(M_P_58_COPY_BOOST,"Le lanceur copie les boosts des statistiques de la cible suivantes:");
        f_.add(M_P_58_EFFECT,"Cet effet change des boosts ou des valeurs de statistiques pour au moins un combattant.");
        f_.add(M_P_58_FAIL,"Conditions suffisantes d'échec");
        f_.add(M_P_58_FAIL_VAR,"Conditions suffisantes d'échec pour les variations de niveau");
        f_.add(M_P_58_FAIL_SWAP,"Conditions suffisantes d'échec pour l'échange de valeurs de statistique");
        f_.add(M_P_58_FORMULA,"{0}");
        f_.add(M_P_58_RATE,"L''effet a lieu avec une probabilité de:");
        f_.add(M_P_58_RATE_ENABLED,"L''effet a lieu avec une probabilité de {0} soit environ de {1} %.");
        f_.add(M_P_58_RATE_EVENT,"Probabilité");
        f_.add(M_P_58_STATISTIC,"Statistique");
        f_.add(M_P_58_SWAP_BOOST,"Le lanceur et la cible échangent les boosts des statistiques suivantes:");
        f_.add(M_P_58_VAR_STATIS_RANK,"Voici le tableau de variations des statistiques de la cible:");
        f_.add(M_P_58_LAW,"Loi de la variation de statistique");
        return f_;
    }
}