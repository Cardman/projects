package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundFoe {
    public static final String M_P_5_EFFECT="effect";
    public static final String M_P_5_FOE="foe";
    private MessagesDataEndroundFoe(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_5_EFFECT,"This effect regards the foe team of the user.");
        e_.add(M_P_5_FOE,"The target looses {0} of its full hp.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_5_EFFECT,"Cet effet concerne l''équipe adverse du lanceur.");
        f_.add(M_P_5_FOE,"La cible perd {0} de ses pv max.");
        return f_;
    }
}