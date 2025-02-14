package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEvolutionsEvotype {
    public static final String M_P_81_BASE="base";
    public static final String M_P_81_TYPE="type";
    private MessagesDataEvolutionsEvotype(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_81_BASE,"{0} evolves into {1}.");
        e_.add(M_P_81_TYPE,"{0} must know at least one move with the type {1}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_81_BASE,"{0} évolue en {1}.");
        f_.add(M_P_81_TYPE,"{0} doit connaître au moins une attaque de type {1}.");
        return f_;
    }
}
