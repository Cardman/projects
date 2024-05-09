package aiki.sml;

import code.sml.util.*;

public final class MessagesRenderWindowPk {
    public static final String AVAILAIBLE_HELPS = "0";
    public static final String CST_DIFFICULTY = "1";
    public static final String CST_FILE = "2";
    public static final String CST_LANGUAGE = "3";
    public static final String CST_PARAMS = "4";
    public static final String CST_QUIT = "5";
    public static final String DATA_GAME = "6";
    public static final String DATA_WEB = "7";
    public static final String ERROR_LOADING = "8";
    public static final String FOLDER_LOAD = "9";
    public static final String GAME_LOAD = "10";
    public static final String GAME_PROGRESS = "11";
    public static final String GAME_SAVE = "12";
    public static final String HELP_INFO = "13";
    public static final String LAST_SAVED_GAME = "14";
    public static final String NEW_GAME = "15";
    public static final String OPEN_HTML = "16";
    public static final String SUCCESSFUL_LOADING = "17";
    public static final String TITLE = "18";
    public static final String TITLE_BATTLE = "19";
    public static final String TITLE_DIFFICULTY = "20";
    public static final String TITLE_WEB = "21";
    public static final String ZIP_LOAD = "22";
    private MessagesRenderWindowPk() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(AVAILAIBLE_HELPS,"The files about help are available.");
        e_.add(CST_DIFFICULTY,"Difficulty");
        e_.add(CST_FILE,"File");
        e_.add(CST_LANGUAGE,"Language");
        e_.add(CST_PARAMS,"Parameters");
        e_.add(CST_QUIT,"Quit");
        e_.add(DATA_GAME,"Game");
        e_.add(DATA_WEB,"See data game");
        e_.add(ERROR_LOADING,"Error while loading the file.");
        e_.add(FOLDER_LOAD,"Load folder");
        e_.add(GAME_LOAD,"Load game state");
        e_.add(GAME_PROGRESS,"Game progressing");
        e_.add(GAME_SAVE,"Save game state");
        e_.add(HELP_INFO,"For displaying information about data, type F1.");
        e_.add(LAST_SAVED_GAME,"Last saved at {0}.");
        e_.add(NEW_GAME,"New game");
        e_.add(OPEN_HTML,"A html frame is opened.");
        e_.add(SUCCESSFUL_LOADING,"Successful loading the ZIP archive.");
        e_.add(TITLE,"Pokemon Aiki");
        e_.add(TITLE_BATTLE,"Data about the current fight");
        e_.add(TITLE_DIFFICULTY,"Difficulty");
        e_.add(TITLE_WEB,"Data for the current loaded archive");
        e_.add(ZIP_LOAD,"Load zipped game");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(AVAILAIBLE_HELPS,"Les fichiers d'aide sont disponibles.");
        f_.add(CST_DIFFICULTY,"Difficulté");
        f_.add(CST_FILE,"Fichier");
        f_.add(CST_LANGUAGE,"Langue");
        f_.add(CST_PARAMS,"Paramètres");
        f_.add(CST_QUIT,"Quitter");
        f_.add(DATA_GAME,"Jeu");
        f_.add(DATA_WEB,"Voir les données du jeu");
        f_.add(ERROR_LOADING,"Erreur de chargement du fichier.");
        f_.add(FOLDER_LOAD,"Charger un dossier");
        f_.add(GAME_LOAD,"Charger une sauvegarde de jeu");
        f_.add(GAME_PROGRESS,"Avancement du jeu");
        f_.add(GAME_SAVE,"Sauvegarder l'état d'un jeu");
        f_.add(HELP_INFO,"Pour afficher les informations sur les données, tapez F1.");
        f_.add(LAST_SAVED_GAME,"Dernière sauvegarde à {0}.");
        f_.add(NEW_GAME,"Nouveau jeu");
        f_.add(OPEN_HTML,"Une fenêtre html est ouverte.");
        f_.add(SUCCESSFUL_LOADING,"Chargement réussi de l'archive ZIP.");
        f_.add(TITLE,"Pokemon Aiki");
        f_.add(TITLE_BATTLE,"Données sur le combat courant");
        f_.add(TITLE_DIFFICULTY,"Difficulté");
        f_.add(TITLE_WEB,"Données pour l'archive chargée courante");
        f_.add(ZIP_LOAD,"Charger un jeu archivé");
        return f_;
    }
}
