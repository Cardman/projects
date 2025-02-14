package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffcommonstatistics {
    public static final String M_P_41_COMMON="common";
    public static final String M_P_41_COMMON_STAT="common_stat";
    public static final String M_P_41_COMMON_VALUE="common_value";
    public static final String M_P_41_EFFECT="effect";
    public static final String M_P_41_FORMULA="formula";
    private MessagesDataEffcommonstatistics(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_41_COMMON,"The user and the target owns the following common values:");
        e_.add(M_P_41_COMMON_STAT,"Common element fot the user and the target");
        e_.add(M_P_41_COMMON_VALUE,"Formula");
        e_.add(M_P_41_EFFECT,"This effect affects a common value foe a statistic of the user and the target.");
        e_.add(M_P_41_FORMULA,"{0}");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_41_COMMON,"Le lanceur et la cible possède les valeurs communes suivantes:");
        f_.add(M_P_41_COMMON_STAT,"Element commun pour le lanceur et la cible");
        f_.add(M_P_41_COMMON_VALUE,"Formule");
        f_.add(M_P_41_EFFECT,"Cet effet attribue une valeur commune pour une statistique du lanceur et de la cible.");
        f_.add(M_P_41_FORMULA,"{0}");
        return f_;
    }
}