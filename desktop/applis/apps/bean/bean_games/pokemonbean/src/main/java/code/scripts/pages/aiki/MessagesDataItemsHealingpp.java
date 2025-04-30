package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsHealingpp {
    public static final String M_P_25_FULL_HEAL_MOVE="full_heal_move";
    public static final String M_P_25_FULL_HEAL_MOVES="full_heal_moves";
    public static final String M_P_25_HEAL_MOVE="heal_move";
    public static final String M_P_25_HEAL_MOVE_INTRO="heal_move_intro";
    public static final String M_P_25_HEAL_MOVES="heal_moves";
    public static final String M_P_25_HEAL_MOVES_INTRO="heal_moves_intro";
    private MessagesDataItemsHealingpp(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_25_FULL_HEAL_MOVE,"This item can restore all pp of one attack.");
        e_.add(M_P_25_FULL_HEAL_MOVES,"This item can restore all pp of all attacks.");
        e_.add(M_P_25_HEAL_MOVE,"This item can restore {0} pp of one attack.");
        e_.add(M_P_25_HEAL_MOVE_INTRO,"This item can restore the following amount of pp of one attack:");
        e_.add(M_P_25_HEAL_MOVES,"This item can restore {0} pp of all attacks.");
        e_.add(M_P_25_HEAL_MOVES_INTRO,"This item can restore the following amount of pp of all attacks.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_25_FULL_HEAL_MOVE,"Cet objet permet de soigner tous les pp d'une attaque.");
        f_.add(M_P_25_FULL_HEAL_MOVES,"Cet objet permet de soigner tous les pp de toutes les attaques.");
        f_.add(M_P_25_HEAL_MOVE,"Cet objet permet de soigner {0} pp d''une attaque.");
        f_.add(M_P_25_HEAL_MOVE_INTRO,"Cet objet permet de soigner le montant suivant de pp d'une attaque:");
        f_.add(M_P_25_HEAL_MOVES,"Cet objet permet de soigner {0} pp de toutes les attaques.");
        f_.add(M_P_25_HEAL_MOVES_INTRO,"Cet objet permet de soigner le montant suivant de pp de toutes les attaques:");
        return f_;
    }
}