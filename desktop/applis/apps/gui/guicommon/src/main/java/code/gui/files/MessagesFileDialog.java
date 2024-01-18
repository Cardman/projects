package code.gui.files;

import code.sml.util.TranslationsFile;

public final class MessagesFileDialog {
    public static final String FILES_PARAM = "0";
    public static final String NAME = "1";
    public static final String FILES = "2";
    private MessagesFileDialog(){}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(FILES_PARAM,"Files {0}");
        e_.add(NAME,"Name");
        e_.add(FILES,"Files");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(FILES_PARAM,"Fichiers {0}");
        f_.add(NAME,"Nom");
        f_.add(FILES,"Fichiers");
        return f_;
    }
}
