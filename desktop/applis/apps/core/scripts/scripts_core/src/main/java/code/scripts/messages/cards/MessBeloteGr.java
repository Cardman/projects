package code.scripts.messages.cards;
import code.util.*;
public final class MessBeloteGr{
private static final String C_P_BELOTE_0_0="You must follow a card of the suit {0}.\n";
private static final String C_P_BELOTE_0_10="For declaring {0}.\n";
private static final String C_P_BELOTE_0_11="This is the single card which can be played.\n";
private static final String C_P_BELOTE_0_12="You are going to win all tricks by playing first the strongest cards.\n";
private static final String C_P_BELOTE_0_13="You are going to win all trump cards of the other players, then you will play with a suit card.\n";
private static final String C_P_BELOTE_0_14="A foe is going to win the trick.\n";
private static final String C_P_BELOTE_0_15="Your partner is going to win the trick.\n";
private static final String C_P_BELOTE_0_16="Vous devez fournir une carte de la couleur {0}.\n";
private static final String C_P_BELOTE_0_17="Vous devez jouer une carte plus forte que {0}.\n";
private static final String C_P_BELOTE_0_18="Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre partenaire.\n";
private static final String C_P_BELOTE_0_19="Vous devez sur-couper avec une carte de la couleur {0} en dessous de votre partenaire.\n";
private static final String C_P_BELOTE_0_1="You must play a stronger card than {0}.\n";
private static final String C_P_BELOTE_0_20="Vous devez couper avec une carte de la couleur {0} au dessus de votre adversaire.\n";
private static final String C_P_BELOTE_0_21="Vous devez sur-couper avec une carte de la couleur {0} au dessus de votre adversaire.\n";
private static final String C_P_BELOTE_0_22="Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre adversaire.\n";
private static final String C_P_BELOTE_0_23="Votre main valait pour un contrat &agrave; la couleur {0} mais vous ne pouvez pas surench&eacute;rir avec le contrat &agrave; la couleur {0} sur le contrat {1}.\n";
private static final String C_P_BELOTE_0_24="Votre main valait pour un contrat {0} mais vous ne pouvez pas surench&eacute;rir avec le contrat {0} sur le contrat {1}.\n";
private static final String C_P_BELOTE_0_25="il faut annoncer un contrat plus &eacute;lev&eacute; que celui pr&eacute;c&eacute;demment annonc&eacute; ou passer.\n";
private static final String C_P_BELOTE_0_26="Pour annoncer {0}.\n";
private static final String C_P_BELOTE_0_27="C''est la seule carte &agrave; jouer.\n";
private static final String C_P_BELOTE_0_28="Vous allez maintenant ramasser les plis de toutes les couleurs en commen&ccedil;ant par les cartes ma&icirc;tresses.\n";
private static final String C_P_BELOTE_0_29="Vous allez ramasser d''abord tous les atouts des autres joueurs, puis vous jouerez dans les couleurs.\n";
private static final String C_P_BELOTE_0_2="You must undertrump with a card of the suit {0} under your partner.\n";
private static final String C_P_BELOTE_0_30="Un adversaire va ramasser le pli.\n";
private static final String C_P_BELOTE_0_31="Votre partenaire va ramasser le pli.\n";
private static final String C_P_BELOTE_0_32="Global results\n";
private static final String C_P_BELOTE_0_33="Detail results\n";
private static final String C_P_BELOTE_0_34="R&eacute;sultats globaux\n";
private static final String C_P_BELOTE_0_35="D&eacute;tail des r&eacute;sultats\n";
private static final String C_P_BELOTE_0_3="You must overtrump with a card of the suit {0} over your partner.\n";
private static final String C_P_BELOTE_0_4="You must trump with a card of the suit {0} over your foe.\n";
private static final String C_P_BELOTE_0_5="You must overtrump with a card of the suit {0} over your foe.\n";
private static final String C_P_BELOTE_0_6="You must undertrump with a card of the suit {0} under your foe.\n";
private static final String C_P_BELOTE_0_7="Your hand was worth a suit bid {0} but you cannot overbid with the suit bid {0} over the bid {1}.\n";
private static final String C_P_BELOTE_0_8="Your hand was worth a bid {0} but you cannot overbid with the bid {0} over the bid {1}.\n";
private static final String C_P_BELOTE_0_9="you have to declare a stronger bid than the previously declared one or fold.\n";
private static final String DETAIL_RESULTS_PAGE="detailResultsPage";
private static final String FOE_WIN_TRICK="foeWinTrick";
private static final String GET_CARDS_BY_SUIT_CARDS="getCardsBySuitCards";
private static final String GET_CARDS_BY_TRUMP_CARDS="getCardsByTrumpCards";
private static final String HAND_VALUE_NO_SUIT="handValueNoSuit";
private static final String HAND_VALUE_SUIT="handValueSuit";
private static final String OVERBID_DUE="overbidDue";
private static final String OVER_TRUMP_FOE="overTrumpFoe";
private static final String OVER_TRUMP_PARTNER="overTrumpPartner";
private static final String PARTNER_WIN_TRICK="partnerWinTrick";
private static final String PLAY_BELOTE_REBELOTE="playBeloteRebelote";
private static final String PLAY_STRONGER_CARD="playStrongerCard";
private static final String PLAY_SUIT="playSuit";
private static final String RESULTS_PAGE="resultsPage";
private static final String SINGLE_CARD="singleCard";
private static final String TRUMP_FOE="trumpFoe";
private static final String UNDER_TRUMP_FOE="underTrumpFoe";
private static final String UNDER_TRUMP_PARTNER="underTrumpPartner";
private static final char EQ_SEP='=';
private static final char SEP=':';
private MessBeloteGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/classes/en/cards/belote/gamebelote.properties",enGame());
m.addEntry("resources_cards/classes/fr/cards/belote/gamebelote.properties",frGame());
m.addEntry("resources_cards/classes/en/cards/belote/resultsbelote.properties",enRes());
m.addEntry("resources_cards/classes/fr/cards/belote/resultsbelote.properties",frRes());
return m;
}
static String enGame(){
String f=PLAY_SUIT+SEP+C_P_BELOTE_0_0;
f+=PLAY_STRONGER_CARD+SEP+C_P_BELOTE_0_1;
f+=UNDER_TRUMP_PARTNER+SEP+C_P_BELOTE_0_2;
f+=OVER_TRUMP_PARTNER+SEP+C_P_BELOTE_0_3;
f+=TRUMP_FOE+SEP+C_P_BELOTE_0_4;
f+=OVER_TRUMP_FOE+SEP+C_P_BELOTE_0_5;
f+=UNDER_TRUMP_FOE+SEP+C_P_BELOTE_0_6;
f+=HAND_VALUE_SUIT+SEP+C_P_BELOTE_0_7;
f+=HAND_VALUE_NO_SUIT+SEP+C_P_BELOTE_0_8;
f+=OVERBID_DUE+SEP+C_P_BELOTE_0_9;
f+=PLAY_BELOTE_REBELOTE+SEP+C_P_BELOTE_0_10;
f+=SINGLE_CARD+SEP+C_P_BELOTE_0_11;
f+=GET_CARDS_BY_SUIT_CARDS+SEP+C_P_BELOTE_0_12;
f+=GET_CARDS_BY_TRUMP_CARDS+SEP+C_P_BELOTE_0_13;
f+=FOE_WIN_TRICK+SEP+C_P_BELOTE_0_14;
f+=PARTNER_WIN_TRICK+SEP+C_P_BELOTE_0_15;
return f;
}
static String frGame(){
String f=PLAY_SUIT+SEP+C_P_BELOTE_0_16;
f+=PLAY_STRONGER_CARD+SEP+C_P_BELOTE_0_17;
f+=UNDER_TRUMP_PARTNER+SEP+C_P_BELOTE_0_18;
f+=OVER_TRUMP_PARTNER+SEP+C_P_BELOTE_0_19;
f+=TRUMP_FOE+SEP+C_P_BELOTE_0_20;
f+=OVER_TRUMP_FOE+SEP+C_P_BELOTE_0_21;
f+=UNDER_TRUMP_FOE+SEP+C_P_BELOTE_0_22;
f+=HAND_VALUE_SUIT+SEP+C_P_BELOTE_0_23;
f+=HAND_VALUE_NO_SUIT+SEP+C_P_BELOTE_0_24;
f+=OVERBID_DUE+SEP+C_P_BELOTE_0_25;
f+=PLAY_BELOTE_REBELOTE+SEP+C_P_BELOTE_0_26;
f+=SINGLE_CARD+SEP+C_P_BELOTE_0_27;
f+=GET_CARDS_BY_SUIT_CARDS+SEP+C_P_BELOTE_0_28;
f+=GET_CARDS_BY_TRUMP_CARDS+SEP+C_P_BELOTE_0_29;
f+=FOE_WIN_TRICK+SEP+C_P_BELOTE_0_30;
f+=PARTNER_WIN_TRICK+SEP+C_P_BELOTE_0_31;
return f;
}
static String enRes(){
String f=RESULTS_PAGE+EQ_SEP+C_P_BELOTE_0_32;
f+=DETAIL_RESULTS_PAGE+EQ_SEP+C_P_BELOTE_0_33;
return f;
}
static String frRes(){
String f=RESULTS_PAGE+EQ_SEP+C_P_BELOTE_0_34;
f+=DETAIL_RESULTS_PAGE+EQ_SEP+C_P_BELOTE_0_35;
return f;
}
}
