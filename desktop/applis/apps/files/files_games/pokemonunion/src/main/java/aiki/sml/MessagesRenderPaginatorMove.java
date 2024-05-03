package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPaginatorMove {
    public static final String CST_NAME = "0";
    public static final String CST_TYPES = "1";
    public static final String CST_DAMAGING = "2";
    public static final String CST_TARGETS = "3";
    public static final String CST_PRIORITY = "4";
    public static final String CST_PP = "5";
    public static final String CST_PRICE = "6";
    public static final String TITLE = "0";
    private MessagesRenderPaginatorMove() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CST_NAME, "Name");
        e_.add(CST_TYPES, "Types of the move");
        e_.add(CST_DAMAGING, "Damaging move");
        e_.add(CST_TARGETS, "Targets");
        e_.add(CST_PRIORITY, "Priority of the move");
        e_.add(CST_PP, "Default power points");
        e_.add(CST_PRICE, "Price");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CST_NAME, "Nom");
        f_.add(CST_TYPES, "Types de l'attaque");
        f_.add(CST_DAMAGING, "Attaque offensive");
        f_.add(CST_TARGETS, "Cibles");
        f_.add(CST_PRIORITY, "Priorité de l'attaque");
        f_.add(CST_PP, "Points de pouvoir de base");
        f_.add(CST_PRICE, "Prix");
        return f_;
    }
    public static TranslationsFile enTitle(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TITLE, "Move search");
        return e_;
    }
    public static TranslationsFile frTitle(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TITLE, "Recherche d'attaque");
        return f_;
    }
}
