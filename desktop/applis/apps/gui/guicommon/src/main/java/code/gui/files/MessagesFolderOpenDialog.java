package code.gui.files;

import code.sml.util.TranslationsFile;

public final class MessagesFolderOpenDialog {
    public static final String CANCEL = "0";
    public static final String OPEN = "1";
    private MessagesFolderOpenDialog() {}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CANCEL,"Cancel");
        e_.add(OPEN,"Open");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CANCEL,"Annuler");
        f_.add(OPEN,"Ouvrir");
        return f_;
    }
}
