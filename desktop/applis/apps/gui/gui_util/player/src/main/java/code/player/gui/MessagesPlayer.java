package code.player.gui;

import code.sml.util.*;

public final class MessagesPlayer {
    public static final String TITLE = "0";
    public static final String SONGS = "1";
    public static final String RANDOM = "2";
    public static final String CANNOT_READ_TITLE = "3";
    public static final String CANNOT_READ_MESSAGE = "4";
    public static final String LAST = "5";
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
        en_.add(BASE_KEY,BASE);
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
        fr_.add(BASE_KEY,BASE);
        return fr_;
    }
}
