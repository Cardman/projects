package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEvolutionsEvolevelgender {
    public static final String M_P_76_BASE="base";
    public static final String M_P_76_GENDER="gender";
    private MessagesDataEvolutionsEvolevelgender(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_76_BASE,"{0} evolves into {1}.");
        e_.add(M_P_76_GENDER,"The level of {0} is greater or equals to {1} and the gender of {0} is {2}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_76_BASE,"{0} évolue en {1}.");
        f_.add(M_P_76_GENDER,"Le niveau de {0} est supérieur ou égal à {1} et le genre de {0} est {2}.");
        return f_;
    }
}
