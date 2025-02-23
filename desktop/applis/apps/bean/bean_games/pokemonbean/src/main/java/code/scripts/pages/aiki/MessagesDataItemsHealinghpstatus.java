package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsHealinghpstatus {
    public static final String M_P_23_RATE="rate";
    private MessagesDataItemsHealinghpstatus(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_23_RATE,"The restored life rate of the pokemon is {0}.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_23_RATE,"Le taux de vie restaurée du pokémon est de {0}.");
        return f_;
    }
}