package code.scripts.messages.cards;

import code.sml.util.TranslationsFile;

public final class MessagesEditorCards {
    public static final String BACK = "0";
    public static final String CST_REMAINING = "1";
    public static final String DEALER = "2";
    public static final String DEALING_CARDS = "3";
    public static final String DEALING_STACK = "4";
    public static final String ERROR_MOVE = "5";
    public static final String ERROR_MOVE_TITLE = "6";
    public static final String ERROR_REPARTITION = "7";
    public static final String ERROR_REPARTITION_TITLE = "8";
    public static final String ERROR_SAVE_FILE = "9";
    public static final String ERROR_SAVE_FILE_TITLE = "10";
    public static final String MOVE_CARDS = "11";
    public static final String NEXT = "12";
    public static final String PLAYER_HAND = "13";
    public static final String PLAY_WITHOUT_SAVING = "14";
    public static final String RANDOM = "15";
    public static final String REMAINING = "16";
    public static final String SAVE_THEN_CLOSE = "17";
    public static final String SAVE_THEN_PLAY = "18";
    public static final String SAVE_WITHOUT_CLOSING = "19";
    public static final String SELECTED_CARDS = "20";
    public static final String USER_HAND = "21";
    private MessagesEditorCards() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(BACK,"<= Back");
        e_.add(CST_REMAINING,"Remaining cards");
        e_.add(DEALER,"Dealer");
        e_.add(DEALING_CARDS,"Deal cards");
        e_.add(DEALING_STACK,"Stack of dealing");
        e_.add(ERROR_MOVE,"{0} cards are selected, the number of remaining places is {1} for the following hand: {2}.");
        e_.add(ERROR_MOVE_TITLE,"Placement error");
        e_.add(ERROR_REPARTITION,"There are {0} left cards to place.");
        e_.add(ERROR_REPARTITION_TITLE,"Repartition error");
        e_.add(ERROR_SAVE_FILE,"Already saved deal");
        e_.add(ERROR_SAVE_FILE_TITLE,"Saving error");
        e_.add(MOVE_CARDS,"Move the cards to the hand");
        e_.add(NEXT,"Next =>");
        e_.add(PLAYER_HAND,"{0}''s hand");
        e_.add(PLAY_WITHOUT_SAVING,"Play without saving");
        e_.add(RANDOM,"Random");
        e_.add(REMAINING,"Dog");
        e_.add(SAVE_THEN_CLOSE,"Save then close");
        e_.add(SAVE_THEN_PLAY,"Save then play");
        e_.add(SAVE_WITHOUT_CLOSING,"Save without closing");
        e_.add(SELECTED_CARDS,"Number of selected cards: {0}");
        e_.add(USER_HAND,"Your hand");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(BACK,"<= Précédent");
        f_.add(CST_REMAINING,"Talon");
        f_.add(DEALER,"Donneur");
        f_.add(DEALING_CARDS,"Distribuer les cartes");
        f_.add(DEALING_STACK,"Pile de distribution");
        f_.add(ERROR_MOVE,"{0} cartes sont sélectionnées, le nombre de places restantes est {1} pour la main suivante: {2}.");
        f_.add(ERROR_MOVE_TITLE,"Erreur de placement");
        f_.add(ERROR_REPARTITION,"Il y a {0} cartes restantes à placer.");
        f_.add(ERROR_REPARTITION_TITLE,"Erreur de répartition");
        f_.add(ERROR_SAVE_FILE,"Partie déjà sauvegardée");
        f_.add(ERROR_SAVE_FILE_TITLE,"Erreur de sauvegarde");
        f_.add(MOVE_CARDS,"Déplacer les cartes vers la main");
        f_.add(NEXT,"Suivant =>");
        f_.add(PLAYER_HAND,"Main de {0}");
        f_.add(PLAY_WITHOUT_SAVING,"Jouer sans sauvegarder");
        f_.add(RANDOM,"Aléatoire");
        f_.add(REMAINING,"Chien");
        f_.add(SAVE_THEN_CLOSE,"Sauvegarder puis fermer");
        f_.add(SAVE_THEN_PLAY,"Sauvegarder puis jouer");
        f_.add(SAVE_WITHOUT_CLOSING,"Sauvegarder sans fermer");
        f_.add(SELECTED_CARDS,"Nombre de cartes sélectionnées: {0}");
        f_.add(USER_HAND,"Votre main");
        return f_;
    }
}
