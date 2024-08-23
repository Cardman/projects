package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPkSoftParams {
    public static final String ANIMATION = "0";
    public static final String ANIMATION_MOVING = "1";
    public static final String CLICK_PAD = "2";
    public static final String ENABLE_KEY_PAD = "3";
    public static final String GAME_LOAD = "4";
    public static final String SAVE_EXIT = "5";
    public static final String SELECT_HOME_PATH = "6";
    public static final String SELECT_HOME_PATH_ZIP = "7";
    public static final String TITLE = "8";
    public static final String ZIP_LOAD = "9";
    public static final String OK = "10";
    private MessagesRenderPkSoftParams() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(ANIMATION,"Enable animations of battle");
        e_.add(ANIMATION_MOVING,"Enable animations of moving heros");
        e_.add(CLICK_PAD,"Click on keys for moving");
        e_.add(ENABLE_KEY_PAD,"Use keys of keypad for moving");
        e_.add(GAME_LOAD,"Load the last game state");
        e_.add(SAVE_EXIT,"Save at the exit and load the last game state");
        e_.add(SELECT_HOME_PATH,"Save files in the home folder");
        e_.add(SELECT_HOME_PATH_ZIP,"Load archives files from the home folder");
        e_.add(TITLE,"Parameters of files saving and movement");
        e_.add(ZIP_LOAD,"Load the last zipped game");
        e_.add(OK,"ok");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(ANIMATION,"Activer les animations de combat");
        f_.add(ANIMATION_MOVING,"Activer les animations de déplacement du héros");
        f_.add(CLICK_PAD,"Cliquer sur les touches pour le déplacement");
        f_.add(ENABLE_KEY_PAD,"Utiliser les flèches du clavier pour le déplacement");
        f_.add(GAME_LOAD,"Charger la dernière sauvegarde de jeu");
        f_.add(SAVE_EXIT,"Sauvegarder à la fermeture du jeu et charger la dernière sauvegarde de jeu");
        f_.add(SELECT_HOME_PATH,"Sauvegarder les fichiers dans l'espace personnel");
        f_.add(SELECT_HOME_PATH_ZIP,"Charger des fichiers d'archives depuis l'espace personnel");
        f_.add(TITLE,"Paramètres de sauvegarde de fichiers et de mouvement");
        f_.add(ZIP_LOAD,"Charger la dernière archive de jeu");
        f_.add(OK,"ok");
        return f_;
    }
}
