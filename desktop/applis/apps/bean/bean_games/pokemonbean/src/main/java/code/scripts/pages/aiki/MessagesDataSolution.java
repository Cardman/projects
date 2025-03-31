package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataSolution {
    public static final String M_P_87_GENDER="gender";
    public static final String M_P_87_IMAGE="image";
    public static final String M_P_87_INDEX="index";
    public static final String M_P_87_NAME="name";
    public static final String M_P_87_TITLE="title";
    private MessagesDataSolution(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_87_GENDER,"Gender");
        e_.add(M_P_87_IMAGE,"Image");
        e_.add(M_P_87_INDEX,"Return to the index");
        e_.add(M_P_87_NAME,"Name");
        e_.add(M_P_87_TITLE,"Solution for playing");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_87_GENDER,"Genre");
        f_.add(M_P_87_IMAGE,"Image");
        f_.add(M_P_87_INDEX,"Retour à l'index");
        f_.add(M_P_87_NAME,"Nom");
        f_.add(M_P_87_TITLE,"Solution de jeu");
        return f_;
    }
}