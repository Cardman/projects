package code.scripts.pages.aiki;

import code.sml.util.*;

public final class MessagesDataIndex {
    public static final String M_P_15_ABILITIES="abilities";
    public static final String M_P_15_COMBOS="combos";
    public static final String M_P_15_ENDROUND="endRound";
    public static final String M_P_15_GENERAL="general";
    public static final String M_P_15_ITEMS="items";
    public static final String M_P_15_LANGS="langs";
    public static final String M_P_15_LEARNTMOVES="learntMoves";
    public static final String M_P_15_MAP="map";
    public static final String M_P_15_MOVES="moves";
    public static final String M_P_15_NOTLEARNTMOVES="notLearntMoves";
    public static final String M_P_15_POKEDEX="pokedex";
    public static final String M_P_15_ROUND="round";
    public static final String M_P_15_SIMULATION="simulation";
    public static final String M_P_15_SOLUTION="solution";
    public static final String M_P_15_STATUS="status";
    public static final String M_P_15_TITLE="title";
    private MessagesDataIndex(){
    }
    public static TranslationsFile en(){
        TranslationsFile e_=new TranslationsFile();
        e_.add(M_P_15_ABILITIES,"Abilities");
        e_.add(M_P_15_COMBOS,"Moves combos");
        e_.add(M_P_15_ENDROUND,"End of round");
        e_.add(M_P_15_GENERAL,"Generalities");
        e_.add(M_P_15_ITEMS,"Items");
        e_.add(M_P_15_LANGS,"Translations");
        e_.add(M_P_15_LEARNTMOVES,"Learnt moves");
        e_.add(M_P_15_MAP,"Map");
        e_.add(M_P_15_MOVES,"Moves");
        e_.add(M_P_15_NOTLEARNTMOVES,"Not learnt moves");
        e_.add(M_P_15_POKEDEX,"Pokedex");
        e_.add(M_P_15_ROUND,"Round happening");
        e_.add(M_P_15_SIMULATION,"Simulation of fights");
        e_.add(M_P_15_SOLUTION,"Solution");
        e_.add(M_P_15_STATUS,"Status");
        e_.add(M_P_15_TITLE,"Data about the current loaded game");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_=new TranslationsFile();
        f_.add(M_P_15_ABILITIES,"Capacités");
        f_.add(M_P_15_COMBOS,"Combinaisons d''attaques");
        f_.add(M_P_15_ENDROUND,"Fin de tour");
        f_.add(M_P_15_GENERAL,"Généralités");
        f_.add(M_P_15_ITEMS,"Objets");
        f_.add(M_P_15_LANGS,"Traductions");
        f_.add(M_P_15_LEARNTMOVES,"Attaques apprises");
        f_.add(M_P_15_MAP,"Cartographie");
        f_.add(M_P_15_MOVES,"Attaques");
        f_.add(M_P_15_NOTLEARNTMOVES,"Attaques non apprises");
        f_.add(M_P_15_POKEDEX,"Pokedex");
        f_.add(M_P_15_ROUND,"Déroulement d''un tour");
        f_.add(M_P_15_SIMULATION,"Simulation de combats");
        f_.add(M_P_15_SOLUTION,"Solution");
        f_.add(M_P_15_STATUS,"Statuts");
        f_.add(M_P_15_TITLE,"Données sur le jeu venant d''être chargé");
        return f_;
    }
}

