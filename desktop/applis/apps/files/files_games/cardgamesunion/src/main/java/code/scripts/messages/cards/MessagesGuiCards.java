package code.scripts.messages.cards;

import code.sml.util.*;

public final class MessagesGuiCards {
    public static final String DIAL_NICK_CST_NICKNAME = "0";
    public static final String DIAL_NICK_NICKNAME_PLAYER = "1";
    public static final String DIAL_NICK_VALIDATE = "2";
    public static final String CLICK_FOR_PLAYING_CARD = "0";
    public static final String CLICK_FOR_PLAYING_TRICK = "1";
    public static final String LAUNCHING = "2";
    public static final String SELECT_HOME_PATH = "3";
    public static final String VALIDATE = "4";
    public static final String WAITING_BIDDING = "5";
    public static final String WAITING_PLAYED_CARD = "6";
    public static final String WAITING_SENTENCE = "7";
    public static final String WAITING_TRICK = "8";
    public static final String WAITING_VALUES = "9";
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
        e_.add(CLICK_FOR_PLAYING_CARD,"Click for playing the card");
        e_.add(CLICK_FOR_PLAYING_TRICK,"Click for playing the next trick");
        e_.add(LAUNCHING,"Software launching");
        e_.add(SELECT_HOME_PATH,"Save files in the home folder");
        e_.add(VALIDATE,"Validate");
        e_.add(WAITING_BIDDING,"Time elapsed between each declaring a bid");
        e_.add(WAITING_PLAYED_CARD,"Time elapsed between each played card");
        e_.add(WAITING_SENTENCE,"{0} {1}");
        e_.add(WAITING_TRICK,"Time elapsed between each trick");
        e_.add(WAITING_VALUES,"(min={0} ms,max={1}):{2} ms");
        return e_;
    }

    public static TranslationsFile frSoft(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CLICK_FOR_PLAYING_CARD,"Cliquer pour jouer la carte");
        f_.add(CLICK_FOR_PLAYING_TRICK,"Cliquer pour jouer le pli suivant");
        f_.add(LAUNCHING,"Lancement du logiciel");
        f_.add(SELECT_HOME_PATH,"Sauvegarder les fichiers dans l'espace personnel");
        f_.add(VALIDATE,"Valider");
        f_.add(WAITING_BIDDING,"Temps écoulé entre chaque annonce d'enchère");
        f_.add(WAITING_PLAYED_CARD,"Temps écoulé entre chaque carte jouée");
        f_.add(WAITING_SENTENCE,"{0} {1}");
        f_.add(WAITING_TRICK,"Temps écoulé entre chaque pli");
        f_.add(WAITING_VALUES,"(min={0} ms,max={1}):{2} ms");
        return f_;
    }
}
