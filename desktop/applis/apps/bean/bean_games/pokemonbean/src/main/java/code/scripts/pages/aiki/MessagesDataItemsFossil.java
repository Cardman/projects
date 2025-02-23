package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsFossil {
    public static final String M_P_21_FOSSIL="fossil";
    private MessagesDataItemsFossil(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_21_FOSSIL,"This fossil can rescucitate at level {0}:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_21_FOSSIL,"Ce fossile permet de faire revivre au niveau {0}:");
        return f_;
    }
}