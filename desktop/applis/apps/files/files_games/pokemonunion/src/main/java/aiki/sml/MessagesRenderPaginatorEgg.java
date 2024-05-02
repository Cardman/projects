package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPaginatorEgg {
    public static final String CST_NAME = "0";
    public static final String STEPS = "1";
    public static final String REMAIN_STEPS = "2";
    public static final String TITLE = "0";
    private MessagesRenderPaginatorEgg() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CST_NAME, "Name");
        e_.add(STEPS, "Done steps");
        e_.add(REMAIN_STEPS, "Remaining Steps");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CST_NAME, "Nom");
        f_.add(STEPS, "Pas effectués");
        f_.add(REMAIN_STEPS, "Pas restants");
        return f_;
    }
    public static TranslationsFile enTitle(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TITLE, "Egg search");
        return e_;
    }
    public static TranslationsFile frTitle(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TITLE, "Recherche d'oeuf");
        return f_;
    }
}
