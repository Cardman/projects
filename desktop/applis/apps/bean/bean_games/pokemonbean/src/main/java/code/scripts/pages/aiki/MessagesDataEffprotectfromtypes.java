package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffprotectfromtypes {
    public static final String M_P_54_EFFECT="effect";
    public static final String M_P_54_IMMU_MOVE_TYPES="immu_move_types";
    private MessagesDataEffprotectfromtypes(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_54_EFFECT,"This effect protects a fighter against a type of move.");
        e_.add(M_P_54_IMMU_MOVE_TYPES,"The target is protected against moves of the following types:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_54_EFFECT,"Cette effet immunise un combattant à un type d'attaque.");
        f_.add(M_P_54_IMMU_MOVE_TYPES,"La cible est immunisée aux attaques de types suivants:");
        return f_;
    }
}