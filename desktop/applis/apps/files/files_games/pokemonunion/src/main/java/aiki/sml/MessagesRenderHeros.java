package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderHeros {
    public static final String NICKNAME = "0";
    public static final String VALIDATE_NICKNAME = "1";
    private MessagesRenderHeros() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(NICKNAME, "Nickname");
        e_.add(VALIDATE_NICKNAME, "OK");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(NICKNAME, "Surnom");
        f_.add(VALIDATE_NICKNAME, "OK");
        return f_;
    }
}
