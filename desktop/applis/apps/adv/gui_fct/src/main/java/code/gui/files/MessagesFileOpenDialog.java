package code.gui.files;

import code.sml.util.TranslationsFile;

public final class MessagesFileOpenDialog {
    public static final String CANCEL = "0";
    public static final String CANCEL_SEARCHING = "1";
    public static final String ERROR_MESSAGE = "2";
    public static final String ERROR_TITLE = "3";
    public static final String FILE_COUNT = "4";
    public static final String OPEN = "5";
    public static final String RESULT_COUNT = "6";
    public static final String SEARCH = "7";
    public static final String STOP_SEARCHING = "8";
    public static final String TYPE_TEXT = "9";
    private MessagesFileOpenDialog() {}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CANCEL,"Cancel");
        e_.add(CANCEL_SEARCHING,"Cancel searching");
        e_.add(ERROR_MESSAGE,"The file {0} does not exist.");
        e_.add(ERROR_TITLE,"Inexistant file");
        e_.add(FILE_COUNT,"Number of searched files: {0}");
        e_.add(OPEN,"Open");
        e_.add(RESULT_COUNT,"Number of found files: {0}");
        e_.add(SEARCH,"Search");
        e_.add(STOP_SEARCHING,"Stop searching");
        e_.add(TYPE_TEXT,"Type text");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CANCEL,"Annuler");
        f_.add(CANCEL_SEARCHING,"Annuler la recherche");
        f_.add(ERROR_MESSAGE,"Le fichier {0} n''existe pas.");
        f_.add(ERROR_TITLE,"Fichier inexistant");
        f_.add(FILE_COUNT,"Nombre de fichiers cherchés: {0}");
        f_.add(OPEN,"Ouvrir");
        f_.add(RESULT_COUNT,"Nombre de fichiers trouvés: {0}");
        f_.add(SEARCH,"Rechercher");
        f_.add(STOP_SEARCHING,"Arrêter la recherche");
        f_.add(TYPE_TEXT,"Saissisez le texte");
        return f_;
    }
}
