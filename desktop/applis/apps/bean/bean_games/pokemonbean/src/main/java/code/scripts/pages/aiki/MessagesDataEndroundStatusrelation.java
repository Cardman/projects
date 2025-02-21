package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundStatusrelation {
    public static final String M_P_12_EFFECT="effect";
    public static final String M_P_12_THIEVED_HP_RATE="thieved_hp_rate";
    private MessagesDataEndroundStatusrelation(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_12_EFFECT,"This effect regards the user and the target. If the user or the target switch then the effect is stopped.");
        e_.add(M_P_12_THIEVED_HP_RATE,"The user absorbs {0} of full hp of the target.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_12_EFFECT,"Cet effet concerne le lanceur et la cible. Si le lanceur ou la cible switchent alors l''effet est arrêté.");
        f_.add(M_P_12_THIEVED_HP_RATE,"Le lanceur absorbe {0} des pv max de la cible.");
        return f_;
    }
}