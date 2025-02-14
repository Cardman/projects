package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEvolutionsEvoitem {
    public static final String M_P_74_ITEM="item";
    private MessagesDataEvolutionsEvoitem(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_74_ITEM,"The following item must be hold by {0}, which has to grow a level:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_74_ITEM,"L''objet suivant doit être porté par {0}, qui doit monter d''un niveau:");
        return f_;
    }
}
