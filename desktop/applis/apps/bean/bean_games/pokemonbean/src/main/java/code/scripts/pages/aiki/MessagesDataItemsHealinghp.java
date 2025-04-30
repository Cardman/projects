package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsHealinghp {
    public static final String M_P_22_HEAL_HP="heal_hp";
    public static final String M_P_22_HEAL_HP_INTRO="heal_hp_intro";
    private MessagesDataItemsHealinghp(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_22_HEAL_HP,"This item can restores {0} hp.");
        e_.add(M_P_22_HEAL_HP_INTRO,"This item can restores the following amount of hp:");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_22_HEAL_HP,"Cet objet permet de soigner {0} pv.");
        f_.add(M_P_22_HEAL_HP_INTRO,"Cet objet permet de soigner le montant suivant de pv:");
        return f_;
    }
}