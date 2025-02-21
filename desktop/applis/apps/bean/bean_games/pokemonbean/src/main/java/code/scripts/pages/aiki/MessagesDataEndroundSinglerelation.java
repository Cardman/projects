package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundSinglerelation {
    public static final String M_P_10_EFFECT="effect";
    public static final String M_P_10_LAW="law";
    public static final String M_P_10_LAW_RD="law_rd";
    public static final String M_P_10_LAW_VALUE="law_value";
    public static final String M_P_10_SUFFERED="suffered";
    public static final String M_P_10_SUFFERED_RATE="suffered_rate";
    public static final String M_P_10_SUFFERED_RD="suffered_rd";
    private MessagesDataEndroundSinglerelation(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_10_EFFECT,"This effect regards the user and the target. If the user or the target switch then the effect is stopped.");
        e_.add(M_P_10_LAW,"Table of law of number of rounds:");
        e_.add(M_P_10_LAW_RD,"Number of rounds since the beginning of the effect");
        e_.add(M_P_10_LAW_VALUE,"Rate");
        e_.add(M_P_10_SUFFERED,"Table of the rate of lost hp of the target in function by the number of rounds:");
        e_.add(M_P_10_SUFFERED_RATE,"Rate of lost life by the target");
        e_.add(M_P_10_SUFFERED_RD,"Number of rounds since the beginning of the effect");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_10_EFFECT,"Cet effet concerne le lanceur et la cible. Si le lanceur ou la cible switchent alors l''effet est arrêté.");
        f_.add(M_P_10_LAW,"Tableau des probabilités en fonction du nombre de tours:");
        f_.add(M_P_10_LAW_RD,"Nombre de tours depuis le début de l''effet");
        f_.add(M_P_10_LAW_VALUE,"Probabilité");
        f_.add(M_P_10_SUFFERED,"Tableau du taux de pv perdus de la cible en fonction du nombre de tours:");
        f_.add(M_P_10_SUFFERED_RATE,"Taux de vie perdue par la cible");
        f_.add(M_P_10_SUFFERED_RD,"Nombre de tours depuis le début de l''effet");
        return f_;
    }
}