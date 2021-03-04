package code.scripts.messages.cards;
import code.util.*;
public final class MessBeloteGr{
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
String f="playSuit:You must follow a card of the suit {0}.\n";
f+="playStrongerCard:You must play a stronger card than {0}.\n";
f+="underTrumpPartner:You must undertrump with a card of the suit {0} under your partner.\n";
f+="overTrumpPartner:You must overtrump with a card of the suit {0} over your partner.\n";
f+="trumpFoe:You must trump with a card of the suit {0} over your foe.\n";
f+="overTrumpFoe:You must overtrump with a card of the suit {0} over your foe.\n";
f+="underTrumpFoe:You must undertrump with a card of the suit {0} under your foe.\n";
f+="handValueSuit:Your hand was worth a suit bid {0} but you cannot overbid with the suit bid {0} over the bid {1}.\n";
f+="handValueNoSuit:Your hand was worth a bid {0} but you cannot overbid with the bid {0} over the bid {1}.\n";
f+="overbidDue:you have to declare a stronger bid than the previously declared one or fold.\n";
f+="playBeloteRebelote:For declaring {0}.\n";
f+="singleCard:This is the single card which can be played.\n";
f+="getCardsBySuitCards:You are going to win all tricks by playing first the strongest cards.\n";
f+="getCardsByTrumpCards:You are going to win all trump cards of the other players, then you will play with a suit card.\n";
f+="foeWinTrick:A foe is going to win the trick.\n";
f+="partnerWinTrick:Your partner is going to win the trick.\n";
return f;
}
static String frGame(){
String f="playSuit:Vous devez fournir une carte de la couleur {0}.\n";
f+="playStrongerCard:Vous devez jouer une carte plus forte que {0}.\n";
f+="underTrumpPartner:Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre partenaire.\n";
f+="overTrumpPartner:Vous devez sur-couper avec une carte de la couleur {0} en dessous de votre partenaire.\n";
f+="trumpFoe:Vous devez couper avec une carte de la couleur {0} au dessus de votre adversaire.\n";
f+="overTrumpFoe:Vous devez sur-couper avec une carte de la couleur {0} au dessus de votre adversaire.\n";
f+="underTrumpFoe:Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre adversaire.\n";
f+="handValueSuit:Votre main valait pour un contrat &agrave; la couleur {0} mais vous ne pouvez pas surench&eacute;rir avec le contrat &agrave; la couleur {0} sur le contrat {1}.\n";
f+="handValueNoSuit:Votre main valait pour un contrat {0} mais vous ne pouvez pas surench&eacute;rir avec le contrat {0} sur le contrat {1}.\n";
f+="overbidDue:il faut annoncer un contrat plus &eacute;lev&eacute; que celui pr&eacute;c&eacute;demment annonc&eacute; ou passer.\n";
f+="playBeloteRebelote:Pour annoncer {0}.\n";
f+="singleCard:C''est la seule carte &agrave; jouer.\n";
f+="getCardsBySuitCards:Vous allez maintenant ramasser les plis de toutes les couleurs en commen&ccedil;ant par les cartes ma&icirc;tresses.\n";
f+="getCardsByTrumpCards:Vous allez ramasser d''abord tous les atouts des autres joueurs, puis vous jouerez dans les couleurs.\n";
f+="foeWinTrick:Un adversaire va ramasser le pli.\n";
f+="partnerWinTrick:Votre partenaire va ramasser le pli.\n";
return f;
}
static String enRes(){
String f="resultsPage=Global results\n";
f+="detailResultsPage=Detail results\n";
return f;
}
static String frRes(){
String f="resultsPage=R&eacute;sultats globaux\n";
f+="detailResultsPage=D&eacute;tail des r&eacute;sultats\n";
return f;
}
}
