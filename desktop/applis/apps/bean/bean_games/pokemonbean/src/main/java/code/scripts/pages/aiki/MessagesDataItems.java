package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItems {
    public static final String M_P_29_CONTENT="content";
    public static final String M_P_29_CONTENT_NAME="content_name";
    public static final String M_P_29_DESCRIPTION="description";
    public static final String M_P_29_IMAGE="image";
    public static final String M_P_29_INDEX="index";
    public static final String M_P_29_ITEMS="items";
    public static final String M_P_29_NAME="name";
    public static final String M_P_29_PRICE="price";
    public static final String M_P_29_PRICE_DOT="price_dot";
    public static final String M_P_29_TITLE="title";
    public static final String M_P_29_OK="ok";
    private MessagesDataItems(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_29_CONTENT,"Content of description:");
        e_.add(M_P_29_CONTENT_NAME,"Name of the item:");
        e_.add(M_P_29_DESCRIPTION,"Description");
        e_.add(M_P_29_IMAGE,"Image");
        e_.add(M_P_29_INDEX,"Return to the index");
        e_.add(M_P_29_ITEMS,"Items");
        e_.add(M_P_29_NAME,"Name");
        e_.add(M_P_29_PRICE,"Price");
        e_.add(M_P_29_PRICE_DOT,"Price:");
        e_.add(M_P_29_TITLE,"Searching items");
        e_.add(M_P_29_OK,"OK");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_29_CONTENT,"Contenu de description:");
        f_.add(M_P_29_CONTENT_NAME,"Nom de l'objet:");
        f_.add(M_P_29_DESCRIPTION,"Description");
        f_.add(M_P_29_IMAGE,"Image");
        f_.add(M_P_29_INDEX,"Revenir à l'indexe");
        f_.add(M_P_29_ITEMS,"Objets");
        f_.add(M_P_29_NAME,"Nom");
        f_.add(M_P_29_PRICE,"Prix");
        f_.add(M_P_29_PRICE_DOT,"Prix:");
        f_.add(M_P_29_TITLE,"Rercherche d'objets");
        f_.add(M_P_29_OK,"OK");
        return f_;
    }
}