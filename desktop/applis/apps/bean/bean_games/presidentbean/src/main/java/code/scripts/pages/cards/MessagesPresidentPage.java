package code.scripts.pages.cards;

import code.sml.util.*;

public final class MessagesPresidentPage {
    public static final String APP_BEAN = "president_bean";
    public static final String M_BEAT_CARDS="beat_cards";
    public static final String M_EQUALITY="equality";
    public static final String M_EQUALTY="equalty";
    public static final String M_FAILED="failed";
    public static final String M_HAS_TO_PLAY="has_to_play";
    public static final String M_INVERT="invert";
    public static final String M_LOOSE="loose";
    public static final String M_LOOSE_COND="loose_cond";
    public static final String M_LOSSE_FIRST="losse_first";
    public static final String M_MID="mid";
    public static final String M_NB_CARDS="nb_cards";
    public static final String M_NB_CARDS_BET="nb_cards_bet";
    public static final String M_NB_PLAYERS="nb_players";
    public static final String M_NB_STACKS="nb_stacks";
    public static final String M_NO="no";
    public static final String M_NOSLAM="noSlam";
    public static final String M_RANKS="ranks";
    public static final String M_RESULTS="results";
    public static final String M_SLAM="slam";
    public static final String M_SUCCESSFUL="successful";
    public static final String M_SWITCH="switch";
    public static final String M_WIN="win";
    public static final String M_YES="yes";
    private MessagesPresidentPage(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_BEAT_CARDS,"Mix Cards");
        e_.add(M_EQUALITY,"Equality.");
        e_.add(M_EQUALTY,"Equality");
        e_.add(M_FAILED,"The bid {0} is failed of {1} points.");
        e_.add(M_HAS_TO_PLAY,"Every player has to play if possible");
        e_.add(M_INVERT,"Possible reversing strength");
        e_.add(M_LOOSE,"You loose.");
        e_.add(M_LOOSE_COND,"A player looses if this player finish by the best cards");
        e_.add(M_LOSSE_FIRST,"For next deals, the looser player starts first the first trick");
        e_.add(M_MID,"The bid {0} is neither passed nor failed.");
        e_.add(M_NB_CARDS,"Number of cards per player: {0}");
        e_.add(M_NB_CARDS_BET,"Number of cards per player: between {0} and {1}");
        e_.add(M_NB_PLAYERS,"Number of players");
        e_.add(M_NB_STACKS,"Number of stacks");
        e_.add(M_NO,"no");
        e_.add(M_NOSLAM,"The attack''s team has not won all tricks.");
        e_.add(M_RANKS,"Ranks");
        e_.add(M_RESULTS,"Results");
        e_.add(M_SLAM,"The attack''s team has achieved the grand slam.");
        e_.add(M_SUCCESSFUL,"The bid {0} is passed of {1} points.");
        e_.add(M_SWITCH,"For next deals, players can switch their cards at the beginning");
        e_.add(M_WIN,"You win.");
        e_.add(M_YES,"yes");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_BEAT_CARDS,"Battre les cartes");
        f_.add(M_EQUALITY,"Match nul.");
        f_.add(M_EQUALTY,"Égalité");
        f_.add(M_FAILED,"L''enchère {0} est chutée de {1} points.");
        f_.add(M_HAS_TO_PLAY,"Chaque joueur doit jouer si possible");
        f_.add(M_INVERT,"Révolution possible");
        f_.add(M_LOOSE,"Vous perdez.");
        f_.add(M_LOOSE_COND,"Un joueur perd si ce joueur finit par les meilleures cartes");
        f_.add(M_LOSSE_FIRST,"Pour les donnes suivantes, le perdant commence à jouer le premier pli");
        f_.add(M_MID,"L''enchère {0} n''est ni réussie ni chutée.");
        f_.add(M_NB_CARDS,"Nombre de cartes par joueur: {0}");
        f_.add(M_NB_CARDS_BET,"Nombre de cartes par joueur: entre {0} et {1}");
        f_.add(M_NB_PLAYERS,"Nombre de joueurs");
        f_.add(M_NB_STACKS,"Nombre de tas");
        f_.add(M_NO,"non");
        f_.add(M_NOSLAM,"L''attaque n''a pas réussi de capot.");
        f_.add(M_RANKS,"Rangs");
        f_.add(M_RESULTS,"Résultats");
        f_.add(M_SLAM,"L''attaque a réussi un capot.");
        f_.add(M_SUCCESSFUL,"L''enchère {0} est réussie de {1} points.");
        f_.add(M_SWITCH,"Pour les donnes suivantes, les joueurs peuvent échanger leurs cartes au début");
        f_.add(M_WIN,"Vous gagnez.");
        f_.add(M_YES,"oui");
        return f_;
    }
}