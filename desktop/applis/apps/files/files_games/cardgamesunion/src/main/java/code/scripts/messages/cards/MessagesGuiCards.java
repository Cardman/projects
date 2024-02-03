package code.scripts.messages.cards;

import code.sml.util.*;

public final class MessagesGuiCards {
    public static final String DIAL_NICK_CST_NICKNAME = "0";
    public static final String DIAL_NICK_NICKNAME_PLAYER = "1";
    public static final String DIAL_NICK_VALIDATE = "2";
    public static final String DIAL_SOFT_CLICK_FOR_PLAYING_CARD = "0";
    public static final String DIAL_SOFT_CLICK_FOR_PLAYING_TRICK = "1";
    public static final String DIAL_SOFT_LAUNCHING = "2";
    public static final String DIAL_SOFT_SELECT_HOME_PATH = "3";
    public static final String DIAL_SOFT_VALIDATE = "4";
    public static final String DIAL_SOFT_WAITING_BIDDING = "5";
    public static final String DIAL_SOFT_WAITING_PLAYED_CARD = "6";
    public static final String DIAL_SOFT_WAITING_SENTENCE = "7";
    public static final String DIAL_SOFT_WAITING_TRICK = "8";
    public static final String DIAL_SOFT_WAITING_VALUES = "9";
    public static final String DIAL_DISPLAY_ADD_SUIT = "0";
    public static final String DIAL_DISPLAY_CLOCK_WISE = "1";
    public static final String DIAL_DISPLAY_DEALING = "2";
    public static final String DIAL_DISPLAY_NB_DEALS_DEMO = "3";
    public static final String DIAL_DISPLAY_REMOVE_SUIT = "4";
    public static final String DIAL_DISPLAY_SORTING = "5";
    public static final String DIAL_DISPLAY_SORTING_BEFORE_PLAYING_CARDS = "6";
    public static final String DIAL_DISPLAY_SORTING_TRUMP = "7";
    public static final String DIAL_DISPLAY_SORT_DECREASING = "8";
    public static final String DIAL_DISPLAY_SUITS = "9";
    public static final String DIAL_DISPLAY_VALIDATE = "10";
    public static final String DIAL_DISPLAY_WISE = "11";
    public static final String DIAL_HELP_SEARCH_LABEL = "0";
    public static final String CST_VALIDATE_EN = "Validate";
    public static final String CST_VALIDATE_FR = "Valider";

    public static final String MAIN_CANT_BID = "0";
    public static final String MAIN_CANT_DECLARE_DETAIL = "1";
    public static final String MAIN_CANT_DISCARD = "2";
    public static final String MAIN_CANT_PLAY = "3";
    public static final String MAIN_CANT_PLAY_CARD = "4";
    public static final String MAIN_DECLARING_SLAM = "5";
    public static final String MAIN_DETAIL_RESULTS_PAGE = "6";
    public static final String MAIN_END_DEAL = "7";
    public static final String MAIN_GIVEN_CARDS = "8";
    public static final String MAIN_GO_CARD_GAME = "9";
    public static final String MAIN_HANDS_TRICKS = "10";
    public static final String MAIN_HANDS_TRICKS_BELOTE = "11";
    public static final String MAIN_HANDS_TRICKS_PRESIDENT = "12";
    public static final String MAIN_HANDS_TRICKS_TAROT = "13";
    public static final String MAIN_HAS_TO_DISCARD = "14";
    public static final String MAIN_HAVE_TO_PLAY = "15";
    public static final String MAIN_HELP_GAME = "16";
    public static final String MAIN_HELP_GO_MENU = "17";
    public static final String MAIN_KEEP_PLAYING_DEAL = "18";
    public static final String MAIN_KEEP_PLAYING_EDITED_DEAL = "19";
    public static final String MAIN_NEXT_TRICK = "20";
    public static final String MAIN_NO_PLAY_NOW = "21";
    public static final String MAIN_PASS_TRICK = "22";
    public static final String MAIN_RECEIVED_CARDS = "23";
    public static final String MAIN_REMOVE_TRUMPS_HANDFUL = "24";
    public static final String MAIN_REPLAY_DEAL = "25";
    public static final String MAIN_RESULTS_PAGE = "26";
    public static final String MAIN_SCORES_EVOLUTION = "27";
    public static final String MAIN_SCORES_EVOLUTION_DETAIL = "28";
    public static final String MAIN_SEE_DOG = "29";
    public static final String MAIN_STOP = "30";
    public static final String MAIN_TAKE_CARDS = "31";
    public static final String MAIN_DELTA = "32";
    public static final String MAIN_CARD = "33";
    public static final String MAIN_TRICK = "34";
    public static final String MAIN_OK = "35";
    public static final String MAIN_LEVEL = "36";
    public static final String MAIN_NB_PLAYED = "37";
    public static final String MAIN_NB_REM = "38";
    public static final String MAIN_NO = "39";
    public static final String MAIN_REVERSED = "40";
    public static final String MAIN_YES = "41";
    public static final String MAIN_POSSIBLE ="42";
    public static final String MAIN_OWNED ="43";
    public static final String MAIN_PLAYED ="44";
    public static final String MAIN_TEAM = "45";
    public static final String MAIN_TITLE = "46";
    public static final String MAIN_CONSULT_BELOTE_BID = "47";
    public static final String MAIN_CONSULT_BELOTE_BID_POINTS = "48";
    public static final String MAIN_CONSULT_BELOTE_BID_SUIT = "49";
    public static final String MAIN_CONSULT_BELOTE_BID_SUIT_POINTS = "50";
    public static final String MAIN_CONSULT_PLAYER = "51";
    public static final String MAIN_CONSULT_PRESIDENT_GIVE = "52";
    public static final String MAIN_CONSULT_PRESIDENT_PASS = "53";
    public static final String MAIN_CONSULT_TAROT_BID = "54";
    public static final String MAIN_CONSULT_TAROT_CALL = "55";
    public static final String MAIN_CONSULT_TAROT_DISCARD = "56";

    private MessagesGuiCards() {}
    public static TranslationsFile enNickname(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_NICK_CST_NICKNAME,"Your nickname");
        e_.add(DIAL_NICK_NICKNAME_PLAYER,"Player {0}''s nickname");
        e_.add(DIAL_NICK_VALIDATE, CST_VALIDATE_EN);
        return e_;
    }

    public static TranslationsFile frNickname(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_NICK_CST_NICKNAME,"Votre pseudo");
        f_.add(DIAL_NICK_NICKNAME_PLAYER,"Pseudo du joueur {0}");
        f_.add(DIAL_NICK_VALIDATE, CST_VALIDATE_FR);
        return f_;
    }
    public static TranslationsFile enSoft(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_SOFT_CLICK_FOR_PLAYING_CARD,"Click for playing the card");
        e_.add(DIAL_SOFT_CLICK_FOR_PLAYING_TRICK,"Click for playing the next trick");
        e_.add(DIAL_SOFT_LAUNCHING,"Software launching");
        e_.add(DIAL_SOFT_SELECT_HOME_PATH,"Save files in the home folder");
        e_.add(DIAL_SOFT_VALIDATE, CST_VALIDATE_EN);
        e_.add(DIAL_SOFT_WAITING_BIDDING,"Time elapsed between each declaring a bid");
        e_.add(DIAL_SOFT_WAITING_PLAYED_CARD,"Time elapsed between each played card");
        e_.add(DIAL_SOFT_WAITING_SENTENCE,"{0} {1}");
        e_.add(DIAL_SOFT_WAITING_TRICK,"Time elapsed between each trick");
        e_.add(DIAL_SOFT_WAITING_VALUES,"(min={0} ms,max={1}):{2} ms");
        return e_;
    }

    public static TranslationsFile frSoft(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_SOFT_CLICK_FOR_PLAYING_CARD,"Cliquer pour jouer la carte");
        f_.add(DIAL_SOFT_CLICK_FOR_PLAYING_TRICK,"Cliquer pour jouer le pli suivant");
        f_.add(DIAL_SOFT_LAUNCHING,"Lancement du logiciel");
        f_.add(DIAL_SOFT_SELECT_HOME_PATH,"Sauvegarder les fichiers dans l'espace personnel");
        f_.add(DIAL_SOFT_VALIDATE, CST_VALIDATE_FR);
        f_.add(DIAL_SOFT_WAITING_BIDDING,"Temps écoulé entre chaque annonce d'enchère");
        f_.add(DIAL_SOFT_WAITING_PLAYED_CARD,"Temps écoulé entre chaque carte jouée");
        f_.add(DIAL_SOFT_WAITING_SENTENCE,"{0} {1}");
        f_.add(DIAL_SOFT_WAITING_TRICK,"Temps écoulé entre chaque pli");
        f_.add(DIAL_SOFT_WAITING_VALUES,"(min={0} ms,max={1}):{2} ms");
        return f_;
    }
    public static TranslationsFile enDisplay(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_DISPLAY_ADD_SUIT,"Add selected suit in list =>");
        e_.add(DIAL_DISPLAY_CLOCK_WISE,"Play clockwise");
        e_.add(DIAL_DISPLAY_DEALING,"Dealing");
        e_.add(DIAL_DISPLAY_NB_DEALS_DEMO,"Number of simulated deals");
        e_.add(DIAL_DISPLAY_REMOVE_SUIT,"<= Remove selected suits in list");
        e_.add(DIAL_DISPLAY_SORTING,"Sorting");
        e_.add(DIAL_DISPLAY_SORTING_BEFORE_PLAYING_CARDS,"Sort suits before playing cards");
        e_.add(DIAL_DISPLAY_SORTING_TRUMP,"Sort suits by order trump");
        e_.add(DIAL_DISPLAY_SORT_DECREASING,"Sort suits by decreasing order");
        e_.add(DIAL_DISPLAY_SUITS,"Suits");
        e_.add(DIAL_DISPLAY_VALIDATE, CST_VALIDATE_EN);
        e_.add(DIAL_DISPLAY_WISE,"Wise");
        return e_;
    }

    public static TranslationsFile frDisplay(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_DISPLAY_ADD_SUIT,"Ajouter la couleur sélectionnée dans la liste pour le tri =>");
        f_.add(DIAL_DISPLAY_CLOCK_WISE,"Jouer dans le sens horaire");
        f_.add(DIAL_DISPLAY_DEALING,"Distribution");
        f_.add(DIAL_DISPLAY_NB_DEALS_DEMO,"Nombre de donnes simuléees.");
        f_.add(DIAL_DISPLAY_REMOVE_SUIT,"<= Supprimer les couleurs sélectionnées de la liste");
        f_.add(DIAL_DISPLAY_SORTING,"Tri");
        f_.add(DIAL_DISPLAY_SORTING_BEFORE_PLAYING_CARDS,"Tri des couleurs avant de jouer les cartes");
        f_.add(DIAL_DISPLAY_SORTING_TRUMP,"Trier les couleurs par ordre d'atout");
        f_.add(DIAL_DISPLAY_SORT_DECREASING,"Trier les couleurs par ordre décroissant");
        f_.add(DIAL_DISPLAY_SUITS,"Couleurs");
        f_.add(DIAL_DISPLAY_VALIDATE, CST_VALIDATE_FR);
        f_.add(DIAL_DISPLAY_WISE,"Sens");
        return f_;
    }
    public static TranslationsFile enHelp(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(DIAL_HELP_SEARCH_LABEL,"Search the typed text.");
        return e_;
    }

    public static TranslationsFile frHelp(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(DIAL_HELP_SEARCH_LABEL,"Chercher le texte saisi.");
        return f_;
    }
    public static TranslationsFile enGame(){
        TranslationsFile e_ = new TranslationsFile();
        e_.add(MAIN_CANT_BID,"You must choose a bid lower than {0}.");
        e_.add(MAIN_CANT_DECLARE_DETAIL,"You cannot declare the following handful: {0}.");
        e_.add(MAIN_CANT_DISCARD,"You cannot discard the following card: {0}");
        e_.add(MAIN_CANT_PLAY,"You cannot play:");
        e_.add(MAIN_CANT_PLAY_CARD,"You cannot play the following card:{0}.");
        e_.add(MAIN_DECLARING_SLAM,"{0}: Slam");
        e_.add(MAIN_DETAIL_RESULTS_PAGE,"Detail results");
        e_.add(MAIN_END_DEAL,"End of deal");
        e_.add(MAIN_GIVEN_CARDS,"Cards to be given");
        e_.add(MAIN_GO_CARD_GAME,"Go to the playing card");
        e_.add(MAIN_HANDS_TRICKS,"Hands and tricks");
        e_.add(MAIN_HANDS_TRICKS_BELOTE,"Hands and tricks at belote");
        e_.add(MAIN_HANDS_TRICKS_PRESIDENT,"Hands and tricks at president");
        e_.add(MAIN_HANDS_TRICKS_TAROT,"Hands and tricks at tarot");
        e_.add(MAIN_HAS_TO_DISCARD,"Discard {0} cards.");
        e_.add(MAIN_HAVE_TO_PLAY,"You must play the king or the queen of the trump suit.");
        e_.add(MAIN_HELP_GAME,"Help for deal");
        e_.add(MAIN_HELP_GO_MENU,"For using help, go to the menu help or type key F3");
        e_.add(MAIN_KEEP_PLAYING_DEAL,"Keep playing the deals");
        e_.add(MAIN_KEEP_PLAYING_EDITED_DEAL,"Keep playing the edited deals");
        e_.add(MAIN_NEXT_TRICK,"Next trick");
        e_.add(MAIN_NO_PLAY_NOW,"Do not play now");
        e_.add(MAIN_PASS_TRICK,"Pass the trick");
        e_.add(MAIN_RECEIVED_CARDS,"Received cards");
        e_.add(MAIN_REMOVE_TRUMPS_HANDFUL,"Remove {0} trumps");
        e_.add(MAIN_REPLAY_DEAL,"Replay the deal");
        e_.add(MAIN_RESULTS_PAGE,"Global results");
        e_.add(MAIN_SCORES_EVOLUTION,"Temporal graph");
        e_.add(MAIN_SCORES_EVOLUTION_DETAIL,"Temporal evolution of scores in relationship with average and distance type multiplied by plus or minus three");
        e_.add(MAIN_SEE_DOG,"See the dog");
        e_.add(MAIN_STOP,"Stop");
        e_.add(MAIN_TAKE_CARDS,"Take the cards of the dog");
        e_.add(MAIN_DELTA,"+3*sigma and -3*sigma (Maximal difference from average)");
        e_.add(MAIN_CARD,"Card");
        e_.add(MAIN_TRICK,"Trick");
        e_.add(MAIN_OK,"ok");
        e_.add(MAIN_LEVEL,"Level of cards");
        e_.add(MAIN_NB_PLAYED,"Number of played cards by level");
        e_.add(MAIN_NB_REM,"Number of remaining cards by level");
        e_.add(MAIN_NO,"No");
        e_.add(MAIN_REVERSED,"Strength reversed: {0}");
        e_.add(MAIN_YES,"Yes");
        e_.add(MAIN_POSSIBLE,"Possible");
        e_.add(MAIN_OWNED,"Owned");
        e_.add(MAIN_PLAYED,"Played");
        e_.add(MAIN_TEAM,"team number {0}");
        e_.add(MAIN_TITLE,"Teams");
        e_.add(MAIN_CONSULT_BELOTE_BID,"I advise you the bid {0}.");
        e_.add(MAIN_CONSULT_BELOTE_BID_POINTS,"I advise you the bid {0} with {1} points.");
        e_.add(MAIN_CONSULT_BELOTE_BID_SUIT,"I advise you to demand the suit {0}.");
        e_.add(MAIN_CONSULT_BELOTE_BID_SUIT_POINTS,"I advise you to demand the suit {0} with {1} points.");
        e_.add(MAIN_CONSULT_PLAYER,"I advise you to play {0}.");
        e_.add(MAIN_CONSULT_PRESIDENT_GIVE,"I advise you to give {0}.");
        e_.add(MAIN_CONSULT_PRESIDENT_PASS,"I advise you to pass.");
        e_.add(MAIN_CONSULT_TAROT_BID,"I advise you the bid {0}.");
        e_.add(MAIN_CONSULT_TAROT_CALL,"I advise you to call {0}.");
        e_.add(MAIN_CONSULT_TAROT_DISCARD,"I advise you to discard {0}.");
        return e_;
    }
    public static TranslationsFile frGame(){
        TranslationsFile f_ = new TranslationsFile();
        f_.add(MAIN_CANT_BID,"Vous devez choisir une enchère plus petite que {0}.");
        f_.add(MAIN_CANT_DECLARE_DETAIL,"Vous ne pouvez pas annoncer la poignée suivante: {0}.");
        f_.add(MAIN_CANT_DISCARD,"Vous ne pouvez pas écarter la carte suivante: {0}");
        f_.add(MAIN_CANT_PLAY,"Vous ne pouvez pas jouer:");
        f_.add(MAIN_CANT_PLAY_CARD,"Vous ne pouvez pas jouer la carte suivante:{0}.");
        f_.add(MAIN_DECLARING_SLAM,"{0}: Chelem");
        f_.add(MAIN_DETAIL_RESULTS_PAGE,"Détail des résultats");
        f_.add(MAIN_END_DEAL,"Fin de partie");
        f_.add(MAIN_GIVEN_CARDS,"Cartes à donner");
        f_.add(MAIN_GO_CARD_GAME,"Passer au jeu de la carte");
        f_.add(MAIN_HANDS_TRICKS,"Mains et plis");
        f_.add(MAIN_HANDS_TRICKS_BELOTE,"Mains et plis à la belote");
        f_.add(MAIN_HANDS_TRICKS_PRESIDENT,"Mains et plis au président");
        f_.add(MAIN_HANDS_TRICKS_TAROT,"Mains et plis au tarot");
        f_.add(MAIN_HAS_TO_DISCARD,"Ecarter {0} cartes.");
        f_.add(MAIN_HAVE_TO_PLAY,"Vous devez jouer le roi ou la dame de la couleur d'atout.");
        f_.add(MAIN_HELP_GAME,"Aide pour la partie");
        f_.add(MAIN_HELP_GO_MENU,"Pour utiliser l'aide allez dans le menu aide ou appuyer sur F3");
        f_.add(MAIN_KEEP_PLAYING_DEAL,"Continuer sur le jeu actuel");
        f_.add(MAIN_KEEP_PLAYING_EDITED_DEAL,"Continuer de jouer les parties éditées");
        f_.add(MAIN_NEXT_TRICK,"Pli suivant");
        f_.add(MAIN_NO_PLAY_NOW,"Ne pas jouer maintenant");
        f_.add(MAIN_PASS_TRICK,"Passer le pli");
        f_.add(MAIN_RECEIVED_CARDS,"Cartes reçues");
        f_.add(MAIN_REMOVE_TRUMPS_HANDFUL,"Retirez {0} atouts");
        f_.add(MAIN_REPLAY_DEAL,"Rejouer la donne");
        f_.add(MAIN_RESULTS_PAGE,"Résultats globaux");
        f_.add(MAIN_SCORES_EVOLUTION,"Courbes temporelles");
        f_.add(MAIN_SCORES_EVOLUTION_DETAIL,"Evolution temporelle des scores centres par rapport a la moyenne et des ecarts types multiplies par plus ou moins trois");
        f_.add(MAIN_SEE_DOG,"Voir le chien");
        f_.add(MAIN_STOP,"Arrêter");
        f_.add(MAIN_TAKE_CARDS,"Prendre les cartes du chien");
        f_.add(MAIN_DELTA,"+3*sigma et -3*sigma (Ecarts maximaux de la moyenne)");
        f_.add(MAIN_CARD,"Carte");
        f_.add(MAIN_TRICK,"Pli");
        f_.add(MAIN_OK,"ok");
        f_.add(MAIN_LEVEL,"Hauteur des cartes");
        f_.add(MAIN_NB_PLAYED,"Nombre de cartes jouées par hauteur");
        f_.add(MAIN_NB_REM,"Nombre de cartes restantes par hauteur");
        f_.add(MAIN_NO,"Non");
        f_.add(MAIN_REVERSED,"Révolution en cours: {0}");
        f_.add(MAIN_YES,"Oui");
        f_.add(MAIN_POSSIBLE,"Possible");
        f_.add(MAIN_OWNED,"Possédé");
        f_.add(MAIN_PLAYED,"Joué");
        f_.add(MAIN_TEAM,"équipe numéro {0}");
        f_.add(MAIN_TITLE,"Equipes");
        f_.add(MAIN_CONSULT_BELOTE_BID,"Je vous conseille le contrat {0}.");
        f_.add(MAIN_CONSULT_BELOTE_BID_POINTS,"Je vous conseille le contrat {0} à {1} points.");
        f_.add(MAIN_CONSULT_BELOTE_BID_SUIT,"Je vous conseille de prendre à la couleur {0}.");
        f_.add(MAIN_CONSULT_BELOTE_BID_SUIT_POINTS,"Je vous conseille de prendre à la couleur {0} à {1} points.");
        f_.add(MAIN_CONSULT_PLAYER,"Je vous conseille de jouer {0}.");
        f_.add(MAIN_CONSULT_PRESIDENT_GIVE,"Je vous conseille de donner {0}.");
        f_.add(MAIN_CONSULT_PRESIDENT_PASS,"Je vous conseille de passer.");
        f_.add(MAIN_CONSULT_TAROT_BID,"Je vous conseille le contrat {0}.");
        f_.add(MAIN_CONSULT_TAROT_CALL,"Je vous conseille d''appeler {0}.");
        f_.add(MAIN_CONSULT_TAROT_DISCARD,"Je vous conseille l''écart {0}.");
        return f_;
    }
}
