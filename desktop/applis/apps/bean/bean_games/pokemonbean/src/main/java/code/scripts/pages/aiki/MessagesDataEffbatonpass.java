package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffbatonpass {
    public static final String M_P_39_EFFECT="effect";
    public static final String M_P_39_EFFECT_2="effect_2";
    private MessagesDataEffbatonpass(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_39_EFFECT,"The user forwards the levels of its statistics.The hp of the clone are not changed.");
        e_.add(M_P_39_EFFECT_2,"The user forwards the effects of the following moves affecting its substitute:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_39_EFFECT,"Le lanceur transmet ses boosts des statistiques.Les pv du clone sont conservés.");
        f_.add(M_P_39_EFFECT_2,"Le lanceur transmet les effets des attaques, affectant à son remplaçant, suivantes:");
        return f_;
    }
}