package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPaginatorSearchMode {
    public static final String WHOLE_STRING = "0";
    public static final String BEGIN = "1";
    public static final String END = "2";
    public static final String SUBSTRING = "3";
    public static final String META_CHARACTER = "4";
    public static final String MATCH_SPACE = "5";

    private MessagesRenderPaginatorSearchMode() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(WHOLE_STRING, "Whole string");
        e_.add(BEGIN, "Begin of string");
        e_.add(END, "End of string");
        e_.add(SUBSTRING, "Sub string");
        e_.add(META_CHARACTER, "Wild cards");
        e_.add(MATCH_SPACE, "Spaced wild cards");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(WHOLE_STRING, "Chaîne entière");
        f_.add(BEGIN, "Début de chaîne");
        f_.add(END, "Fin de chaîne");
        f_.add(SUBSTRING, "Sous chaîne");
        f_.add(META_CHARACTER, "Caractères de remplacement");
        f_.add(MATCH_SPACE, "Espaces de remplacement");
        return f_;
    }
}
