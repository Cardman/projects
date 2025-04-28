package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundStatus {
    public static final String M_P_11_EFFECT="effect";
    public static final String M_P_11_INFLICTED_RATE_HP_TARGET="inflicted_rate_hp_target";
    public static final String M_P_11_INFLICTED_RATE_HP_TARGET_INTRO="inflicted_rate_hp_target_intro";
    private MessagesDataEndroundStatus(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_11_EFFECT,"This effect regards one fighter.");
        e_.add(M_P_11_INFLICTED_RATE_HP_TARGET,"The target looses {0} of its full hp.");
        e_.add(M_P_11_INFLICTED_RATE_HP_TARGET_INTRO,"Loss of full hp of the target:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_11_EFFECT,"Cet effet concerne un seul combattant.");
        f_.add(M_P_11_INFLICTED_RATE_HP_TARGET,"La cible perd {0} de ses pv max.");
        f_.add(M_P_11_INFLICTED_RATE_HP_TARGET_INTRO,"Perte de pv max de la cible:");
        return f_;
    }
}