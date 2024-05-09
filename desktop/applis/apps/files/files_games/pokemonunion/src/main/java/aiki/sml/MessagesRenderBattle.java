package aiki.sml;

import code.sml.util.*;

public final class MessagesRenderBattle {
    public static final String BACK_TEAM = "0";
    public static final String BACK_TEAM_SUB = "1";
    public static final String BALLS = "2";
    public static final String CATCH_PK = "3";
    public static final String CST_ACTIONS = "4";
    public static final String CST_FLEE = "5";
    public static final String ERRORS = "6";
    public static final String EVOS = "7";
    public static final String FIGHT_DATA_MESSAGE = "8";
    public static final String FRONT_TEAM = "9";
    public static final String GO_BACK = "10";
    public static final String GO_TO_ROUND = "11";
    public static final String NO_EVO = "12";
    public static final String NO_ITEM = "13";
    public static final String ROUND = "14";
    public static final String SELECTED_ITEM = "15";
    public static final String SELECT_ABILITY = "16";
    public static final String SELECT_ACTION = "17";
    public static final String SELECT_ITEM = "18";
    public static final String SELECT_MOVE_HEAL = "19";
    public static final String SELECT_MOVE_ROUND = "20";
    public static final String SELECT_MT = "21";
    public static final String SELECT_TARGET = "22";
    public static final String TEAM_CST = "23";
    public static final String TITLE = "24";
    public static final String VALIDATE_EVOS = "25";
    public static final String VALIDATE_SWITCH = "26";
    private MessagesRenderBattle() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(BACK_TEAM,"Your pokemon at the back of the battle");
        e_.add(BACK_TEAM_SUB,"Choose a pokemon for substituting a front");
        e_.add(BALLS,"Balls for catching");
        e_.add(CATCH_PK,"Catch the wild pokemon&");
        e_.add(CST_ACTIONS,"Your choice of actions");
        e_.add(CST_FLEE,"Flee (success rate: (min {0} about {1} %, max {2} about {3} %, avg {4} about {5} %, var {6} about {7} %))");
        e_.add(ERRORS,"Errors of choices");
        e_.add(EVOS,"Evolutions of selected pokemon");
        e_.add(FIGHT_DATA_MESSAGE,"You can type F2 for displaying data about this fight.");
        e_.add(FRONT_TEAM,"Your pokemon on the battle");
        e_.add(GO_BACK,"Withdraw the selected pokemon");
        e_.add(GO_TO_ROUND,"Go to round");
        e_.add(NO_EVO,"No evolution");
        e_.add(NO_ITEM,"No item is selected");
        e_.add(ROUND,"Round events");
        e_.add(SELECTED_ITEM,"The item {0} is selected");
        e_.add(SELECT_ABILITY,"Select an ability for the evolution");
        e_.add(SELECT_ACTION,"Choose a kind of action");
        e_.add(SELECT_ITEM,"Select an item");
        e_.add(SELECT_MOVE_HEAL,"Choose a move to be healed for this fighter");
        e_.add(SELECT_MOVE_ROUND,"Choose a move for this user");
        e_.add(SELECT_MT,"Select moves to be learnt");
        e_.add(SELECT_TARGET,"Choose a target for the move");
        e_.add(TEAM_CST,"Your pokemon team");
        e_.add(TITLE,"Data about the current fight");
        e_.add(VALIDATE_EVOS,"Validate evolutions and moves");
        e_.add(VALIDATE_SWITCH,"Validate switches");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(BACK_TEAM,"Vos pokemon à l'arrière");
        f_.add(BACK_TEAM_SUB,"Choisissez un pokemon pour remplacer un avant");
        f_.add(BALLS,"Balles pour capturer");
        f_.add(CATCH_PK,"Capturer le pokemon sauvage");
        f_.add(CST_ACTIONS,"Votre choix d'actions");
        f_.add(CST_FLEE,"Fuir (probabilité de réussite: (min {0} environ {1} %, max {2} environ {3} %, moy {4} environ {5} %, var {6} environ {7} %))");
        f_.add(ERRORS,"Erreurs de choix");
        f_.add(EVOS,"Évolutions du pokemon sélectionné");
        f_.add(FIGHT_DATA_MESSAGE,"Vous pouvez taper F2 pour afficher les données sur le combat.");
        f_.add(FRONT_TEAM,"Vos pokemon sur le terrain");
        f_.add(GO_BACK,"Retirer le pokemon sélectionné");
        f_.add(GO_TO_ROUND,"Jouer le tour");
        f_.add(NO_EVO,"Pas d'évolution");
        f_.add(NO_ITEM,"Aucun objet sélectionné");
        f_.add(ROUND,"Événements de tour");
        f_.add(SELECTED_ITEM,"L''objet {0} est sélectionné");
        f_.add(SELECT_ABILITY,"Sélectionnez une capacité pour l'évolution");
        f_.add(SELECT_ACTION,"Choisissez un type d'action");
        f_.add(SELECT_ITEM,"Sélectionnez un objet");
        f_.add(SELECT_MOVE_HEAL,"Choisissez une attaque à soigner pour ce combattant");
        f_.add(SELECT_MOVE_ROUND,"Choisissez une attaque pour ce lanceur");
        f_.add(SELECT_MT,"Sélectionnez des attaques à apprendre");
        f_.add(SELECT_TARGET,"Choisissez une cible pour l'attaque");
        f_.add(TEAM_CST,"Votre équipe pokemon");
        f_.add(TITLE,"Données sur le combat courant");
        f_.add(VALIDATE_EVOS,"Valider les évolutions et attaques");
        f_.add(VALIDATE_SWITCH,"Valider les remplacements");
        return f_;
    }
}
