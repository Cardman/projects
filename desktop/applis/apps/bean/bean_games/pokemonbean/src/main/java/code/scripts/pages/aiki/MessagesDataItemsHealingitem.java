package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsHealingitem {
    public static final String M_P_24_HAPPINESS="happiness";
    public static final String M_P_24_HAPPINESS_BALL="happiness_ball";
    public static final String M_P_24_HAPPINESS_BOOST="happiness_boost";
    public static final String M_P_24_HAPPINESS_OTHER_BALL="happiness_other_ball";
    public static final String M_P_24_HEAL_TEAM="heal_team";
    private MessagesDataItemsHealingitem(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_24_HAPPINESS,"Here is the table of happiness points depending on the BALL having caught the pokemon to be healed:");
        e_.add(M_P_24_HAPPINESS_BALL,"BALL having caught");
        e_.add(M_P_24_HAPPINESS_BOOST,"Won happiness points");
        e_.add(M_P_24_HAPPINESS_OTHER_BALL,"Other used BALL or none");
        e_.add(M_P_24_HEAL_TEAM,"This object can restore all team while a fight or not.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_24_HAPPINESS,"Voici le tableau des points de bonheur gagnés en fonction de la BALL de capture du pokémon à booster:");
        f_.add(M_P_24_HAPPINESS_BALL,"BALL de capture");
        f_.add(M_P_24_HAPPINESS_BOOST,"Points de bonheur gagnés");
        f_.add(M_P_24_HAPPINESS_OTHER_BALL,"Autre BALL de capture ou aucune");
        f_.add(M_P_24_HEAL_TEAM,"Cet objet permet de soigner toute l'équipe pendant un combat ou non.");
        return f_;
    }
}