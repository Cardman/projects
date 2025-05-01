package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsFossil {
    public static final String M_P_21_FOSSIL="fossil";
    public static final String M_P_21_LEVEL="level";
    public static final String M_P_21_PK="pokemon";
    private MessagesDataItemsFossil(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_21_FOSSIL,"This fossil can rescucitate at level {0}:");
        e_.add(M_P_21_LEVEL,"Level:");
        e_.add(M_P_21_PK,"Pokemon:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_21_FOSSIL,"Ce fossile permet de faire revivre au niveau {0}:");
        f_.add(M_P_21_LEVEL,"Niveau:");
        f_.add(M_P_21_PK,"Pokemon:");
        return f_;
    }
}