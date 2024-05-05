package aiki.sml;

import code.sml.util.TranslationsFile;

public final class MessagesRenderPaginatorHealingItem {

    public static final String CST_NAME = "0";
    public static final String CST_DESCRIPTION = "1";
    public static final String CST_STATUS = "2";
    public static final String HP = "3";
    public static final String HP_RATE = "4";
    public static final String PP = "5";
    public static final String PRICE = "6";
    public static final String RELATIVE_HP = "7";
    public static final String RELATIVE_PP = "8";
    public static final String HEAL_MOVE = "9";
    public static final String STATISTIC = "10";
    public static final String HEAL_KO = "11";
    public static final String NUMBER = "12";
    public static final String LAB_HEAL_ONE_MOVE = "13";
    public static final String LAB_HEAL_MOVES = "14";
    public static final String LAB_HP = "15";
    public static final String LAB_HP_RATE = "16";
    public static final String LAB_PP = "17";
    public static final String LAB_STATUS = "18";
    public static final String LAB_STATISTICS = "19";
    public static final String LAB_KO = "20";
    public static final String TITLE = "0";
    public static final String HEAL_MOVE_TITLE = "1";
    private MessagesRenderPaginatorHealingItem() {
    }
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CST_NAME, "Name");
        e_.add(CST_DESCRIPTION, "Description of the healing item");
        e_.add(CST_STATUS, "Status");
        e_.add(HP, "Restored health points");
        e_.add(HP_RATE, "Restored life rate");
        e_.add(PP, "Restored power points");
        e_.add(PRICE, "Price");
        e_.add(RELATIVE_HP, "Relative health points with maximum");
        e_.add(RELATIVE_PP, "Relative power points with maximum");
        e_.add(HEAL_MOVE, "Heal at least one move");
        e_.add(STATISTIC, "Statistic");
        e_.add(HEAL_KO, "Heal from ko");
        e_.add(NUMBER, "Number");
        e_.add(LAB_HEAL_ONE_MOVE, "heal one move");
        e_.add(LAB_HEAL_MOVES, "heal all moves");
        e_.add(LAB_HP, "hp : ");
        e_.add(LAB_HP_RATE, "life rate : ");
        e_.add(LAB_PP, "pp : ");
        e_.add(LAB_STATUS, "status : ");
        e_.add(LAB_STATISTICS, "statistics : ");
        e_.add(LAB_KO, "do revive the pokemon");
        return e_;
    }
    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CST_NAME, "Nom");
        f_.add(CST_DESCRIPTION, "Description de l'objet de soin");
        f_.add(CST_STATUS, "Statuts");
        f_.add(HP, "Points de vie restaurés");
        f_.add(HP_RATE, "Taux de vie restaurée");
        f_.add(PP, "Points de pouvoir restaurés");
        f_.add(PRICE, "Prix");
        f_.add(RELATIVE_HP, "Points de vie relatifs au maximum");
        f_.add(RELATIVE_PP, "Points de pouvoir relatifs au maximum");
        f_.add(HEAL_MOVE, "Soigne au moins une attaque");
        f_.add(STATISTIC, "Statistique");
        f_.add(HEAL_KO, "Fait revivre");
        f_.add(NUMBER, "Quantité");
        f_.add(LAB_HEAL_ONE_MOVE, "soigne une attaque");
        f_.add(LAB_HEAL_MOVES, "soigne toutes les attaques");
        f_.add(LAB_HP, "pv : ");
        f_.add(LAB_HP_RATE, "taux de vie : ");
        f_.add(LAB_PP, "pp : ");
        f_.add(LAB_STATUS, "statut : ");
        f_.add(LAB_STATISTICS, "statistiques : ");
        f_.add(LAB_KO, "fait revivre le pokemon");
        return f_;
    }
    public static TranslationsFile enTitle(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(TITLE, "Healing item search");
        e_.add(HEAL_MOVE_TITLE, "Choose a move to be healed");
        return e_;
    }
    public static TranslationsFile frTitle(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(TITLE, "Recherche d'objet de soin");
        f_.add(HEAL_MOVE_TITLE, "Choisissez une attaque à soigner");
        return f_;
    }

}
