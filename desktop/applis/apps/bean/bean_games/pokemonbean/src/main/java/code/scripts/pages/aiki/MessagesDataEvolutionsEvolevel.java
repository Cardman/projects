package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEvolutionsEvolevel {
    public static final String M_P_75_BASE="base";
    public static final String M_P_75_LEVEL="level";
    private MessagesDataEvolutionsEvolevel(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_75_BASE,"{0} evolves into {1}.");
        e_.add(M_P_75_LEVEL,"The level of {0} is greater or equals to {1}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_75_BASE,"{0} évolue en {1}.");
        f_.add(M_P_75_LEVEL,"Le niveau de {0} est supérieur ou égal à {1}.");
        return f_;
    }
}
