package code.scripts.messages.cards;

import code.sml.util.TranslationsFile;

public final class MessagesDialogTarot {
    public static final String ALLOWED_MISERES = "0";
    public static final String ALLOW_PLAYING_CALLED_SUIT = "1";
    public static final String CST_BIDS = "2";
    public static final String DEALING = "3";
    public static final String DECLARING = "4";
    public static final String DISCARDING = "5";
    public static final String END_DEAL = "6";
    public static final String END_DEAL_RULE = "7";
    public static final String HANDFUL = "8";
    public static final String MIX_CARDS = "9";
    public static final String MODE_GAME = "10";
    public static final String NUMBER_DEALS = "11";
    public static final String NUMBER_PLAYERS = "12";
    public static final String NUMBER_TRUMPS = "13";
    public static final String REPARTITION = "14";
    public static final String REPARTITION_PLAYERS = "15";
    public static final String RULES = "16";
    public static final String VALIDATE = "17";
    public static final String VALIDATE_HANDFUL = "18";
    private MessagesDialogTarot() {}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(ALLOWED_MISERES,"Allowed miseres");
        e_.add(ALLOW_PLAYING_CALLED_SUIT,"Allow playing the called suit at the first round");
        e_.add(CST_BIDS,"Allowed bids");
        e_.add(DEALING,"Dealing");
        e_.add(DECLARING,"Declaring");
        e_.add(DISCARDING,"Discard after calling");
        e_.add(END_DEAL,"End deal");
        e_.add(END_DEAL_RULE,"End deal if equality between teams");
        e_.add(HANDFUL,"Handful");
        e_.add(MIX_CARDS,"Mix cards");
        e_.add(MODE_GAME,"Game mode");
        e_.add(NUMBER_DEALS,"Number of deals without mixing");
        e_.add(NUMBER_PLAYERS,"Number of players");
        e_.add(NUMBER_TRUMPS,"Number of trumps");
        e_.add(REPARTITION,"Repartition");
        e_.add(REPARTITION_PLAYERS,"Repartition of players");
        e_.add(RULES,"Rules");
        e_.add(VALIDATE_HANDFUL,"Validate handful");
        e_.add(VALIDATE,"Validate");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(ALLOWED_MISERES,"Misères autorisées");
        f_.add(ALLOW_PLAYING_CALLED_SUIT,"Autoriser le jeu de la couleur appelée au premier tour");
        f_.add(CST_BIDS,"Enchères autorisées");
        f_.add(DEALING,"Distribution");
        f_.add(DECLARING,"Annonces");
        f_.add(DISCARDING,"Ecart après appel");
        f_.add(END_DEAL,"Fin de partie");
        f_.add(END_DEAL_RULE,"Fin de partie si égalité entre les équipes");
        f_.add(HANDFUL,"Poignée");
        f_.add(MIX_CARDS,"Battre les cartes");
        f_.add(MODE_GAME,"Mode de jeu");
        f_.add(NUMBER_DEALS,"Nombre de parties sans battre");
        f_.add(NUMBER_PLAYERS,"Nombre de joueurs");
        f_.add(NUMBER_TRUMPS,"Nombre d'atouts");
        f_.add(REPARTITION,"Répartition");
        f_.add(REPARTITION_PLAYERS,"Répartition des joueurs");
        f_.add(RULES,"Règles");
        f_.add(VALIDATE_HANDFUL,"Valider poignée");
        f_.add(VALIDATE,"Valider");
        return f_;
    }
}
