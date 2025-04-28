package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffremainedhprate {
    public static final String M_P_56_EFFECT="effect";
    public static final String M_P_56_RATE="rate";
    public static final String M_P_56_RATE_LOOSE="rate_loose";
    public static final String M_P_56_RATE_WIN="rate_win";
    private MessagesDataEffremainedhprate(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_56_EFFECT,"This effect makes vary the hp in function of a rate of remaining hp of the fighter.");
        e_.add(M_P_56_RATE,"Rate of remaining hp of the target:");
        e_.add(M_P_56_RATE_LOOSE,"The target looses {0} of its remaining hp.");
        e_.add(M_P_56_RATE_WIN,"The target wins {0} of its remaining hp.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_56_EFFECT,"Cet effet fait varier les pv en fonction d'un quota de pv restants du combattant.");
        f_.add(M_P_56_RATE,"Taux de pv restants de la cible:");
        f_.add(M_P_56_RATE_LOOSE,"La cible perd {0} de ses pv restants.");
        f_.add(M_P_56_RATE_WIN,"La cible gagne {0} de ses pv restants.");
        return f_;
    }
}