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
    private MessagesGuiCards() {}
    public static TranslationsFile enNickname(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_NICK_CST_NICKNAME,"Your nickname");
        e_.add(DIAL_NICK_NICKNAME_PLAYER,"Player {0}''s nickname");
        e_.add(DIAL_NICK_VALIDATE,"Validate");
        return e_;
    }

    public static TranslationsFile frNickname(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_NICK_CST_NICKNAME,"Votre pseudo");
        f_.add(DIAL_NICK_NICKNAME_PLAYER,"Pseudo du joueur {0}");
        f_.add(DIAL_NICK_VALIDATE,"Valider");
        return f_;
    }
    public static TranslationsFile enSoft(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_SOFT_CLICK_FOR_PLAYING_CARD,"Click for playing the card");
        e_.add(DIAL_SOFT_CLICK_FOR_PLAYING_TRICK,"Click for playing the next trick");
        e_.add(DIAL_SOFT_LAUNCHING,"Software launching");
        e_.add(DIAL_SOFT_SELECT_HOME_PATH,"Save files in the home folder");
        e_.add(DIAL_SOFT_VALIDATE,"Validate");
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
        f_.add(DIAL_SOFT_VALIDATE,"Valider");
        f_.add(DIAL_SOFT_WAITING_BIDDING,"Temps écoulé entre chaque annonce d'enchère");
        f_.add(DIAL_SOFT_WAITING_PLAYED_CARD,"Temps écoulé entre chaque carte jouée");
        f_.add(DIAL_SOFT_WAITING_SENTENCE,"{0} {1}");
        f_.add(DIAL_SOFT_WAITING_TRICK,"Temps écoulé entre chaque pli");
        f_.add(DIAL_SOFT_WAITING_VALUES,"(min={0} ms,max={1}):{2} ms");
        return f_;
    }
}
