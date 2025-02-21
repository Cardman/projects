package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataEndroundMultirelation {
    public static final String M_P_7_DAMAGE_STATUS="damage_status";
    public static final String M_P_7_DAMAGE_STATUS_KEY="damage_status_key";
    public static final String M_P_7_DAMAGE_STATUS_RATE="damage_status_rate";
    public static final String M_P_7_EFFECT="effect";
    private MessagesDataEndroundMultirelation(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_7_DAMAGE_STATUS,"Here is the rate of lost hp in function by the combinaisons of the status of the foe of the figther (the hidden combinaisons of status have not effect.):");
        e_.add(M_P_7_DAMAGE_STATUS_KEY,"Owned status");
        e_.add(M_P_7_DAMAGE_STATUS_RATE,"Rate of lost life by the foe");
        e_.add(M_P_7_EFFECT,"This effect regards all possible relations between users and targets on the front of battle.");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_7_DAMAGE_STATUS,"Voici le taux de pv perdus en fonction des combinaisons des statuts de l''advsersaire du combattant (les combinaisons de statut non affichées n''on aucun effet.):");
        f_.add(M_P_7_DAMAGE_STATUS_KEY,"Statut possédé");
        f_.add(M_P_7_DAMAGE_STATUS_RATE,"Taux de vie perdue par l''advesaire");
        f_.add(M_P_7_EFFECT,"Cet effect prend en compte toutes les relations possibles entre lanceurs et cibles sur le terrains.");
        return f_;
    }
}