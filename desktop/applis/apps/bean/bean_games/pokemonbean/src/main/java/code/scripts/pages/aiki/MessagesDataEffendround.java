package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffendround {
    public static final String M_P_47_ENDROUND="endRound";
    public static final String M_P_47_RANK="rank";
    public static final String M_P_47_RANK_INTRO="rank_intro";
    public static final String M_P_47_REASONS="reasons";
    private MessagesDataEffendround(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_47_ENDROUND,"See the effect of end of round");
        e_.add(M_P_47_RANK,"The rank of the effect is {0}.");
        e_.add(M_P_47_RANK_INTRO,"The rank of the effect is:");
        e_.add(M_P_47_REASONS,"The effect is not enabled at the end of round if and only if one of the conditions is checked:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_47_ENDROUND,"Voir l'effet de fin de tour");
        f_.add(M_P_47_RANK,"Le rang de l''effet est de {0}.");
        f_.add(M_P_47_RANK_INTRO,"Le rang de l''effet est de:");
        f_.add(M_P_47_REASONS,"L'effet n'est pas actif en fin de tour si et seulement une des conditions est vérifiée:");
        return f_;
    }
}