package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPkGameDetail {
    public static final String SEARCH_LABEL = "0";
    public static final String HIDE = "1";
    public static final String TITLE = "2";
    private MessagesRenderPkGameDetail() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(SEARCH_LABEL, "Search the typed text.");
        e_.add(HIDE, "Hide");
        e_.add(TITLE, "Pokemon detail");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(SEARCH_LABEL, "Chercher le texte saisi.");
        f_.add(HIDE, "Cacher");
        f_.add(TITLE, "Détail du pokemon");
        return f_;
    }
}
