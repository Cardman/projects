package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEffwinmoney {
    public static final String M_P_70_EFFECT="effect";
    public static final String M_P_70_WIN_MONEY="win_money";
    private MessagesDataEffwinmoney(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_70_EFFECT,"This effect lets the player to win money.");
        e_.add(M_P_70_WIN_MONEY,"The sum of won money is {0} muliplied by the sum of the level of the user and the level of target.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_70_EFFECT,"Cet effet permet au joueur de gagner de l'argent.");
        f_.add(M_P_70_WIN_MONEY,"La somme d''argent gagnée vaut {0} fois la somme des niveaux du lanceur et de la cible.");
        return f_;
    }
}