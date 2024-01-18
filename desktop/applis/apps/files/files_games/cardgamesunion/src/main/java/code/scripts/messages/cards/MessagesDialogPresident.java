package code.scripts.messages.cards;

import code.sml.util.TranslationsFile;

public final class MessagesDialogPresident {
    public static final String CAN_PASS = "0";
    public static final String CST_EQUALITY = "1";
    public static final String DEALING = "2";
    public static final String END_DEAL = "3";
    public static final String LOOSER_STARTS_FIRST = "4";
    public static final String LOOSE_FINISH_BEST_CARDS = "5";
    public static final String MIX_CARDS = "6";
    public static final String NUMBER_DEALS = "7";
    public static final String NUMBER_PLAYERS = "8";
    public static final String NUMBER_STACKS = "9";
    public static final String POSSIBLE_REVERSING = "10";
    public static final String REPARTITION = "11";
    public static final String RULES = "12";
    public static final String STOP_ALL_PLAYED_CARDS = "13";
    public static final String SWITCH_CARDS = "14";
    public static final String VALIDATE = "15";
    private MessagesDialogPresident() {}
    public static TranslationsFile en(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(CAN_PASS,"Players can always pass");
        e_.add(CST_EQUALITY,"Rules about equaling");
        e_.add(DEALING,"Dealing");
        e_.add(END_DEAL,"End deal");
        e_.add(LOOSER_STARTS_FIRST,"The loser starts the first trick of the next deal");
        e_.add(LOOSE_FINISH_BEST_CARDS,"A player can loose if this player finish by the best cards of the game");
        e_.add(MIX_CARDS,"Mix cards");
        e_.add(NUMBER_DEALS,"Number of deals without mixing");
        e_.add(NUMBER_PLAYERS,"Number of players");
        e_.add(NUMBER_STACKS,"Number of stacks");
        e_.add(POSSIBLE_REVERSING,"Reverse order of cards if a hand of {0} cards is played");
        e_.add(REPARTITION,"Repartition");
        e_.add(RULES,"Rules");
        e_.add(STOP_ALL_PLAYED_CARDS,"Ended trick if all cards of same strength are consecutively played at the same trick");
        e_.add(SWITCH_CARDS,"Possible switches of cards for the next deals");
        e_.add(VALIDATE,"Validate");
        return e_;
    }

    public static TranslationsFile fr(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(CAN_PASS,"Les joueurs peuvent toujours passer");
        f_.add(CST_EQUALITY,"Règles sur le jeu d'égalité");
        f_.add(DEALING,"Distribution");
        f_.add(END_DEAL,"Fin de partie");
        f_.add(LOOSER_STARTS_FIRST,"Le perdant commence le premier pli de la partie suivante");
        f_.add(LOOSE_FINISH_BEST_CARDS,"Un joueur peut perdre si ce joueur finit par les meilleurs cartes du jeu");
        f_.add(MIX_CARDS,"Battre les cartes");
        f_.add(NUMBER_DEALS,"Nombre de parties sans battre");
        f_.add(NUMBER_PLAYERS,"Nombre de joueurs");
        f_.add(NUMBER_STACKS,"Nombre de paquets");
        f_.add(POSSIBLE_REVERSING,"Inverser l''ordre des cartes si une main de {0} cartes est jouée");
        f_.add(REPARTITION,"Répartition");
        f_.add(RULES,"Règles");
        f_.add(STOP_ALL_PLAYED_CARDS,"Fin de pli si toutes cartes de même hauteur sont jouées consécutivement dans le même pli");
        f_.add(SWITCH_CARDS,"Échanges possible de cartes pour les donnes suivantes");
        f_.add(VALIDATE,"Valider");
        return f_;
    }


}
