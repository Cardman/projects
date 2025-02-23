package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataStatusset {
    public static final String M_P_89_CONTENT="content";
    public static final String M_P_89_INDEX="index";
    public static final String M_P_89_STATUS="status";
    public static final String M_P_89_TITLE="title";
    public static final String M_P_89_OK="ok";
    private MessagesDataStatusset(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_89_CONTENT,"Content of the name of status:");
        e_.add(M_P_89_INDEX,"Return to the index");
        e_.add(M_P_89_STATUS,"Status");
        e_.add(M_P_89_TITLE,"Searching status");
        e_.add(M_P_89_OK,"OK");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_89_CONTENT,"Contenu du nom de statut:");
        f_.add(M_P_89_INDEX,"Revenir à l''indexe");
        f_.add(M_P_89_STATUS,"Statuts");
        f_.add(M_P_89_TITLE,"Recherche de statuts");
        f_.add(M_P_89_OK,"OK");
        return f_;
    }
}