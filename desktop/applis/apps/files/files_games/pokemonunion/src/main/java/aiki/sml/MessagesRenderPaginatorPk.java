package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPaginatorPk {
    public static final String CST_NAME = "0";
    public static final String CST_LEVEL = "1";
    public static final String CST_ITEM = "2";
    public static final String CST_WITH_ITEM = "3";
    public static final String CST_ABILITY = "4";
    public static final String CST_GENDER = "5";
    public static final String CST_MOVES = "6";
    public static final String CST_EVOLUTIONS = "7";
    public static final String TITLE = "0";
    public static final String DETAIL = "1";
    private MessagesRenderPaginatorPk() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CST_NAME, "Name");
        e_.add(CST_LEVEL, "Level");
        e_.add(CST_ITEM, "Item");
        e_.add(CST_WITH_ITEM, "With item");
        e_.add(CST_ABILITY, "Ability");
        e_.add(CST_GENDER, "Gender");
        e_.add(CST_MOVES, "Moves");
        e_.add(CST_EVOLUTIONS, "Direct possible evolutions");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CST_NAME, "Nom");
        f_.add(CST_LEVEL, "Niveau");
        f_.add(CST_ITEM, "Objet");
        f_.add(CST_WITH_ITEM, "Avec objet");
        f_.add(CST_ABILITY, "Capacité");
        f_.add(CST_GENDER, "Genre");
        f_.add(CST_MOVES, "Attaques");
        f_.add(CST_EVOLUTIONS, "Évolutions directes possibles");
        return f_;
    }
    public static TranslationsFile enTitle(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TITLE, "Pokemon search");
        e_.add(DETAIL, "Detail of the selected pokemon");
        return e_;
    }
    public static TranslationsFile frTitle(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TITLE, "Recherche de pokemon");
        f_.add(DETAIL, "Détail du pokemon sélectionné");
        return f_;
    }
}
