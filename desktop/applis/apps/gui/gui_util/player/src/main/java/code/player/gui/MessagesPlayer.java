package code.player.gui;

import code.sml.util.*;

public final class MessagesPlayer {
    public static final String TITLE = "0";
    public static final String SONGS = "1";
    public static final String RANDOM = "2";
    public static final String CANNOT_READ_TITLE = "3";
    public static final String CANNOT_READ_MESSAGE = "4";
    public static final String LAST = "5";
    public static final String BASE_MESSAGE = "6";
    public static final String FROM_FOLDER = "7";
    public static final String TO_FOLDER = "8";
    public static final String MIGRATE = "9";
    public static final String BASE_KEY = "6";
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private MessagesPlayer() {
    }
    public static TranslationsFile en(){
        TranslationsFile en_ = new TranslationsFile();
        en_.add(TITLE,"Player of WAV songs");
        en_.add(SONGS,"songs:");
        en_.add(RANDOM,"Random");
        en_.add(CANNOT_READ_TITLE,"Error while reading song.");
        en_.add(CANNOT_READ_MESSAGE,"No file in WPL file");
        en_.add(LAST,"Path for saving");
        en_.add(BASE_MESSAGE,"base");
        en_.add(FROM_FOLDER,"from folder");
        en_.add(TO_FOLDER,"to folder");
        en_.add(MIGRATE,"migrate");
        return en_;
    }
    public static TranslationsFile fr(){
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(TITLE,"Lecteur de chansons WAV");
        fr_.add(SONGS,"chansons:");
        fr_.add(RANDOM,"Aléatoire");
        fr_.add(CANNOT_READ_TITLE,"Erreur lors de la lecture de la chanson.");
        fr_.add(CANNOT_READ_MESSAGE,"Aucun fichier dans le fichier WPL");
        fr_.add(LAST,"Chemin de sauvegarde");
        fr_.add(BASE_MESSAGE,"base");
        fr_.add(FROM_FOLDER,"dossier d'origine");
        fr_.add(TO_FOLDER,"dossier de destination");
        fr_.add(MIGRATE,"migrer");
        return fr_;
    }
}
