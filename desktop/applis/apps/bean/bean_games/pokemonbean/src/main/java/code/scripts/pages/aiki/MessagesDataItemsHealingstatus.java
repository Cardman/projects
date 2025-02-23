package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsHealingstatus {
    public static final String M_P_26_HEAL_KO="heal_ko";
    public static final String M_P_26_STATUS="status";
    private MessagesDataItemsHealingstatus(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_26_HEAL_KO,"This item can resuscitate a KO pokemon.");
        e_.add(M_P_26_STATUS,"This item can removes the following status of one pokemon:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_26_HEAL_KO,"Cet objet permet de réanimer un pokémon KO.");
        f_.add(M_P_26_STATUS,"Cet objet permet d''enlever les statuts suivants d''un pokémon:");
        return f_;
    }
}