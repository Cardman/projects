package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsItem {
    public static final String M_P_27_ITEMS="items";
    public static final String M_P_27_ITEM_TYPE="item_type";
    public static final String M_P_27_PRICE="price";
    public static final String M_P_27_PRICE_INTRO="price_intro";
    public static final String M_P_27_TITLE="title";
    private MessagesDataItemsItem(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_27_ITEMS,"Items");
        e_.add(M_P_27_ITEM_TYPE,"{0}");
        e_.add(M_P_27_PRICE,"The item {0} is worth {1}.");
        e_.add(M_P_27_PRICE_INTRO,"Price");
        e_.add(M_P_27_TITLE,"Data about the item {0}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_27_ITEMS,"Objets");
        f_.add(M_P_27_ITEM_TYPE,"{0}");
        f_.add(M_P_27_PRICE,"Le prix d''un objet {0} est de {1}.");
        f_.add(M_P_27_PRICE_INTRO,"Prix");
        f_.add(M_P_27_TITLE,"Donnés sur l''objet {0}.");
        return f_;
    }
}