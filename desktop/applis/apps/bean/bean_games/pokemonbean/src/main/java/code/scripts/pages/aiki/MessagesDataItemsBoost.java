package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataItemsBoost {
    public static final String M_P_18_EVS="evs";
    public static final String M_P_18_EVS_BOOST="evs_boost";
    public static final String M_P_18_EVS_STAT="evs_stat";
    public static final String M_P_18_HAPPINESS="happiness";
    public static final String M_P_18_HAPPINESS_BALL="happiness_ball";
    public static final String M_P_18_HAPPINESS_BOOST="happiness_boost";
    public static final String M_P_18_HAPPINESS_OTHER_BALL="happiness_other_ball";
    public static final String M_P_18_WIN_PP="win_pp";
    private MessagesDataItemsBoost(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_18_EVS,"Here is the table of won evs by statistic if evs of the statistic to be increased are lower or equal to {0}:");
        e_.add(M_P_18_EVS_BOOST,"Won evs");
        e_.add(M_P_18_EVS_STAT,"Statistic");
        e_.add(M_P_18_HAPPINESS,"Here is the table of happiness points depending on the BALL having caught the pokemon to be boosted:");
        e_.add(M_P_18_HAPPINESS_BALL,"BALL having caught");
        e_.add(M_P_18_HAPPINESS_BOOST,"Won happiness points");
        e_.add(M_P_18_HAPPINESS_OTHER_BALL,"Other used BALL or none");
        e_.add(M_P_18_WIN_PP,"Used on a move, this move wins {0} pp.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_18_EVS,"Voici le tableau des evs gagnés par statistique si les evs de la statistique à augmenter sont inférieurs ou égaux à {0}:");
        f_.add(M_P_18_EVS_BOOST,"Evs gagnés");
        f_.add(M_P_18_EVS_STAT,"Statistique");
        f_.add(M_P_18_HAPPINESS,"Voici le tableau des points de bonheur gagnés en fonction de la BALL de capture du pokémon à booster:");
        f_.add(M_P_18_HAPPINESS_BALL,"BALL de capture");
        f_.add(M_P_18_HAPPINESS_BOOST,"Points de bonheur gagnés");
        f_.add(M_P_18_HAPPINESS_OTHER_BALL,"Autre BALL de capture ou aucune");
        f_.add(M_P_18_WIN_PP,"Utilisé sur une attaque, celle-ci gagne {0} pp.");
        return f_;
    }
}