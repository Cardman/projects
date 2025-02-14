package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEvolutionsEvohappiness {
    public static final String M_P_73_BASE="base";
    public static final String M_P_73_HAPPY="happy";
    private MessagesDataEvolutionsEvohappiness(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_73_BASE,"{0} evolves into {1}.");
        e_.add(M_P_73_HAPPY,"The happiness of {0} is greater or equals to {1} by growing a level.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_73_BASE,"{0} évolue en {1}.");
        f_.add(M_P_73_HAPPY,"Le bonheur de {0} est supérieur ou égal à {1} en montant de niveau.");
        return f_;
    }
}
