package code.scripts.messages.cards;

import code.sml.util.TranslationsFile;

public final class MessagesDialogBelote {
    public static final String ALLOWED_DECLARING = "0";
    public static final String ALL_POINTS_FOR_DEFENDER_TEAM = "1";
    public static final String CST_BIDS = "2";
    public static final String DEALING = "3";
    public static final String DEALING_MODE = "4";
    public static final String DECLARING = "5";
    public static final String END_DEAL = "6";
    public static final String MIX_CARDS = "7";
    public static final String NUMBER_DEALS = "8";
    public static final String RULES_TRUMPS = "9";
    public static final String SCORING = "10";
    public static final String TRUMPING = "11";
    public static final String TRUMPING_DESCRIPTION = "12";
    public static final String UNDER_TRUMPING_FOE = "13";
    public static final String VALIDATE = "14";
    private MessagesDialogBelote() {}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(ALLOWED_DECLARING,"Allowed declaring");
        e_.add(ALL_POINTS_FOR_DEFENDER_TEAM,"The defender team score all points");
        e_.add(CST_BIDS,"Allowed bids");
        e_.add(DEALING,"Dealing");
        e_.add(DEALING_MODE,"Deal all cards before bidding");
        e_.add(DECLARING,"Declaring");
        e_.add(END_DEAL,"End deal");
        e_.add(MIX_CARDS,"Mix cards");
        e_.add(NUMBER_DEALS,"Number of deals without mixing");
        e_.add(RULES_TRUMPS,"Rules of trumping");
        e_.add(SCORING,"Scoring");
        e_.add(TRUMPING,"rules minding the trumps");
        e_.add(TRUMPING_DESCRIPTION,"rules minding the compulsory playing of trumps over/under the partner");
        e_.add(UNDER_TRUMPING_FOE,"compulsory playing a trump under a foe");
        e_.add(VALIDATE,"Validate");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(ALLOWED_DECLARING,"Annonces autorisées");
        f_.add(ALL_POINTS_FOR_DEFENDER_TEAM,"La défense marque tous les points");
        f_.add(CST_BIDS,"Enchères autorisées");
        f_.add(DEALING,"Distribution");
        f_.add(DEALING_MODE,"Distribuer toutes les cartes avant les enchères");
        f_.add(DECLARING,"Annonces");
        f_.add(END_DEAL,"Fin de partie");
        f_.add(MIX_CARDS,"Battre les cartes");
        f_.add(NUMBER_DEALS,"Nombre de parties sans battre");
        f_.add(RULES_TRUMPS,"Règles de coupes");
        f_.add(SCORING,"Calcul des scores");
        f_.add(TRUMPING,"Règles concernant les atouts");
        f_.add(TRUMPING_DESCRIPTION,"Règles concernant le jeu obligatoire des atouts au dessus/en dessous du partenaire");
        f_.add(UNDER_TRUMPING_FOE,"Obligation de sous-couper adversaire");
        f_.add(VALIDATE,"Valider");
        return f_;
    }
}
