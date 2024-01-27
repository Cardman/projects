package code.scripts.messages.cards;

import code.sml.util.*;

public final class MessagesGuiCards {
    public static final String DIAL_NICK_CST_NICKNAME = "0";
    public static final String DIAL_NICK_NICKNAME_PLAYER = "1";
    public static final String DIAL_NICK_VALIDATE = "2";
    public static final String DIAL_SOFT_CLICK_FOR_PLAYING_CARD = "0";
    public static final String DIAL_SOFT_CLICK_FOR_PLAYING_TRICK = "1";
    public static final String DIAL_SOFT_LAUNCHING = "2";
    public static final String DIAL_SOFT_SELECT_HOME_PATH = "3";
    public static final String DIAL_SOFT_VALIDATE = "4";
    public static final String DIAL_SOFT_WAITING_BIDDING = "5";
    public static final String DIAL_SOFT_WAITING_PLAYED_CARD = "6";
    public static final String DIAL_SOFT_WAITING_SENTENCE = "7";
    public static final String DIAL_SOFT_WAITING_TRICK = "8";
    public static final String DIAL_SOFT_WAITING_VALUES = "9";
    public static final String DIAL_DISPLAY_ADD_SUIT = "0";
    public static final String DIAL_DISPLAY_CLOCK_WISE = "1";
    public static final String DIAL_DISPLAY_DEALING = "2";
    public static final String DIAL_DISPLAY_NB_DEALS_DEMO = "3";
    public static final String DIAL_DISPLAY_REMOVE_SUIT = "4";
    public static final String DIAL_DISPLAY_SORTING = "5";
    public static final String DIAL_DISPLAY_SORTING_BEFORE_PLAYING_CARDS = "6";
    public static final String DIAL_DISPLAY_SORTING_TRUMP = "7";
    public static final String DIAL_DISPLAY_SORT_DECREASING = "8";
    public static final String DIAL_DISPLAY_VALIDATE = "9";
    public static final String DIAL_DISPLAY_WISE = "10";
    public static final String CST_VALIDATE_EN = "Validate";
    public static final String CST_VALIDATE_FR = "Valider";

    private MessagesGuiCards() {}
    public static TranslationsFile enNickname(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_NICK_CST_NICKNAME,"Your nickname");
        e_.add(DIAL_NICK_NICKNAME_PLAYER,"Player {0}''s nickname");
        e_.add(DIAL_NICK_VALIDATE, CST_VALIDATE_EN);
        return e_;
    }

    public static TranslationsFile frNickname(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_NICK_CST_NICKNAME,"Votre pseudo");
        f_.add(DIAL_NICK_NICKNAME_PLAYER,"Pseudo du joueur {0}");
        f_.add(DIAL_NICK_VALIDATE, CST_VALIDATE_FR);
        return f_;
    }
    public static TranslationsFile enSoft(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_SOFT_CLICK_FOR_PLAYING_CARD,"Click for playing the card");
        e_.add(DIAL_SOFT_CLICK_FOR_PLAYING_TRICK,"Click for playing the next trick");
        e_.add(DIAL_SOFT_LAUNCHING,"Software launching");
        e_.add(DIAL_SOFT_SELECT_HOME_PATH,"Save files in the home folder");
        e_.add(DIAL_SOFT_VALIDATE, CST_VALIDATE_EN);
        e_.add(DIAL_SOFT_WAITING_BIDDING,"Time elapsed between each declaring a bid");
        e_.add(DIAL_SOFT_WAITING_PLAYED_CARD,"Time elapsed between each played card");
        e_.add(DIAL_SOFT_WAITING_SENTENCE,"{0} {1}");
        e_.add(DIAL_SOFT_WAITING_TRICK,"Time elapsed between each trick");
        e_.add(DIAL_SOFT_WAITING_VALUES,"(min={0} ms,max={1}):{2} ms");
        return e_;
    }

    public static TranslationsFile frSoft(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_SOFT_CLICK_FOR_PLAYING_CARD,"Cliquer pour jouer la carte");
        f_.add(DIAL_SOFT_CLICK_FOR_PLAYING_TRICK,"Cliquer pour jouer le pli suivant");
        f_.add(DIAL_SOFT_LAUNCHING,"Lancement du logiciel");
        f_.add(DIAL_SOFT_SELECT_HOME_PATH,"Sauvegarder les fichiers dans l'espace personnel");
        f_.add(DIAL_SOFT_VALIDATE, CST_VALIDATE_FR);
        f_.add(DIAL_SOFT_WAITING_BIDDING,"Temps écoulé entre chaque annonce d'enchère");
        f_.add(DIAL_SOFT_WAITING_PLAYED_CARD,"Temps écoulé entre chaque carte jouée");
        f_.add(DIAL_SOFT_WAITING_SENTENCE,"{0} {1}");
        f_.add(DIAL_SOFT_WAITING_TRICK,"Temps écoulé entre chaque pli");
        f_.add(DIAL_SOFT_WAITING_VALUES,"(min={0} ms,max={1}):{2} ms");
        return f_;
    }
    public static TranslationsFile enDisplay(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_DISPLAY_ADD_SUIT,"Add selected suit in list =>");
        e_.add(DIAL_DISPLAY_CLOCK_WISE,"Play clockwise");
        e_.add(DIAL_DISPLAY_DEALING,"Dealing");
        e_.add(DIAL_DISPLAY_NB_DEALS_DEMO,"Number of simulated deals");
        e_.add(DIAL_DISPLAY_REMOVE_SUIT,"<= Remove selected suits in list");
        e_.add(DIAL_DISPLAY_SORTING,"Sorting");
        e_.add(DIAL_DISPLAY_SORTING_BEFORE_PLAYING_CARDS,"Sort suits before playing cards");
        e_.add(DIAL_DISPLAY_SORTING_TRUMP,"Sort suits by order trump");
        e_.add(DIAL_DISPLAY_SORT_DECREASING,"Sort suits by decreasing order");
        e_.add(DIAL_DISPLAY_VALIDATE, CST_VALIDATE_EN);
        e_.add(DIAL_DISPLAY_WISE,"Wise");
        return e_;
    }

    public static TranslationsFile frDisplay(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_DISPLAY_ADD_SUIT,"Ajouter la couleur sélectionnée dans la liste pour le tri =>");
        f_.add(DIAL_DISPLAY_CLOCK_WISE,"Jouer dans le sens horaire");
        f_.add(DIAL_DISPLAY_DEALING,"Distribution");
        f_.add(DIAL_DISPLAY_NB_DEALS_DEMO,"Nombre de donnes simuléees.");
        f_.add(DIAL_DISPLAY_REMOVE_SUIT,"<= Supprimer les couleurs sélectionnées de la liste");
        f_.add(DIAL_DISPLAY_SORTING,"Tri");
        f_.add(DIAL_DISPLAY_SORTING_BEFORE_PLAYING_CARDS,"Tri des couleurs avant de jouer les cartes");
        f_.add(DIAL_DISPLAY_SORTING_TRUMP,"Trier les couleurs par ordre d'atout");
        f_.add(DIAL_DISPLAY_SORT_DECREASING,"Trier les couleurs par ordre décroissant");
        f_.add(DIAL_DISPLAY_VALIDATE, CST_VALIDATE_FR);
        f_.add(DIAL_DISPLAY_WISE,"Sens");
        return f_;
    }

}
