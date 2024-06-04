package code.gui.files;

import code.sml.util.TranslationsFile;

public final class MessagesConfirmDialog {
    public static final String CANCEL = "0";
    public static final String NO = "1";
    public static final String OK = "2";
    public static final String YES = "3";
    private MessagesConfirmDialog(){}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CANCEL,"Cancel");
        e_.add(NO,"No");
        e_.add(OK,"Ok");
        e_.add(YES,"Yes");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CANCEL,"Annuler");
        f_.add(NO,"Non");
        f_.add(OK,"Ok");
        f_.add(YES,"Oui");
        return f_;
    }
}
