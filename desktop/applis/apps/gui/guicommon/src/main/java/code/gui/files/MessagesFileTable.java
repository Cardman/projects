package code.gui.files;

import code.sml.util.TranslationsFile;

public final class MessagesFileTable {
    public static final String MODIFIED = "0";
    public static final String NAME = "1";
    public static final String PATH = "2";
    public static final String SIZE = "3";
    private MessagesFileTable() {}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(MODIFIED,"Last Modified");
        e_.add(NAME,"Name");
        e_.add(PATH,"Relative path");
        e_.add(SIZE,"Size");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(MODIFIED,"Dernière modification");
        f_.add(NAME,"Nom");
        f_.add(PATH,"Chemin relatif");
        f_.add(SIZE,"Taille");
        return f_;
    }

}
