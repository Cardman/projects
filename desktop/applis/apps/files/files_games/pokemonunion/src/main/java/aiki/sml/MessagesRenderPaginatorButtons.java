package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPaginatorButtons {
    public static final String SEARCH = "0";
    public static final String NEW_SEARCH = "1";
    public static final String END = "2";
    public static final String VALIDATE_SELECT = "0";
    public static final String CANCEL = "1";

    private MessagesRenderPaginatorButtons() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(SEARCH, "Search");
        e_.add(NEW_SEARCH, "Search again from found results");
        e_.add(END, "End");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(SEARCH, "Chercher");
        f_.add(NEW_SEARCH, "Rechercher sur les résultats trouvés");
        f_.add(END, "Fin");
        return f_;
    }
    public static TranslationsFile enSel(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(VALIDATE_SELECT, "OK");
        e_.add(CANCEL, "Cancel");
        return e_;
    }
    public static TranslationsFile frSel(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(VALIDATE_SELECT, "OK");
        f_.add(CANCEL, "Annuler");
        return f_;
    }
}
