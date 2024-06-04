package code.gui.files;

import code.sml.util.TranslationsFile;

public final class MessagesFileSaveDialog {
    public static final String BODY_CONF = "0";
    public static final String CANCEL = "1";
    public static final String FOLDER_NAME = "2";
    public static final String FORBIDDEN = "3";
    public static final String FORBIDDEN_SPACES = "4";
    public static final String FORBIDDEN_SPECIAL_CHARS = "5";
    public static final String SAVE = "6";
    public static final String TITLE_CONF = "7";
    private MessagesFileSaveDialog() {}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(BODY_CONF,"Would you delete the file {0}?");
        e_.add(CANCEL,"Cancel");
        e_.add(FOLDER_NAME,"Name of the folder to be made.");
        e_.add(FORBIDDEN,"Error");
        e_.add(FORBIDDEN_SPACES,"The name must contain at least one visible character.");
        e_.add(FORBIDDEN_SPECIAL_CHARS,"The characters < > ? \" * / \\ | : are forbidden.");
        e_.add(SAVE,"Save");
        e_.add(TITLE_CONF,"Existing file");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(BODY_CONF,"Voulez vous écraser le fichier {0}?");
        f_.add(CANCEL,"Annuler");
        f_.add(FOLDER_NAME,"Nom du dossier à créer.");
        f_.add(FORBIDDEN,"Erreur");
        f_.add(FORBIDDEN_SPACES,"Le nom doit contenir au moins un caractère visible.");
        f_.add(FORBIDDEN_SPECIAL_CHARS,"Les caractères < > ? \" * / \\ | : sont interdits.");
        f_.add(SAVE,"Sauvegarder");
        f_.add(TITLE_CONF,"Fichier existant");
        return f_;
    }
}
