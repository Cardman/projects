package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPaginatorItem {
    public static final String CST_NAME = "0";
    public static final String CST_DESCRIPTION = "1";
    public static final String PRICE = "2";
    public static final String NUMBER = "3";
    public static final String TITLE = "0";
    public static final String GIVE = "1";
    private MessagesRenderPaginatorItem() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CST_NAME, "Name");
        e_.add(CST_DESCRIPTION, "Description of the item");
        e_.add(PRICE, "Price");
        e_.add(NUMBER, "Number");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CST_NAME, "Nom");
        f_.add(CST_DESCRIPTION, "Description de l'objet");
        f_.add(PRICE, "Prix");
        f_.add(NUMBER, "Quantité");
        return f_;
    }
    public static TranslationsFile enTitle(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TITLE, "Item search");
        e_.add(GIVE, "Give to a pokemon");
        return e_;
    }
    public static TranslationsFile frTitle(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TITLE, "Recherche d'objet");
        f_.add(GIVE, "Donner à un pokemon");
        return f_;
    }
}
