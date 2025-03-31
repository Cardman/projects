package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffvarpp {
    public static final String M_P_69_DELETE_PP="delete_pp";
    public static final String M_P_69_EFFECT="effect";
    private MessagesDataEffvarpp(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_69_DELETE_PP,"The target looses {0} pp of full pp of its last successful move.If the last successful move by the target owns less than {0} pp of the full pp, then the number of pp becomes zero.");
        e_.add(M_P_69_EFFECT,"This effect affects the pp of moves of a fighter.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_69_DELETE_PP,"La cible perd {0} pp des pp max de sa dernière attaque réussie.Si la dernière attaque réussie de la cible possède moins de {0} pp des pp max, alors le nombre de pp devient nul.");
        f_.add(M_P_69_EFFECT,"Cet effet touche aux pp d'un combattant.");
        return f_;
    }
}