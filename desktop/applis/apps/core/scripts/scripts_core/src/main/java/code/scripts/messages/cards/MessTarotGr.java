package code.scripts.messages.cards;
import code.util.*;
public final class MessTarotGr{
private MessTarotGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/classes/en/cards/tarot/gametarot.properties",enGame());
m.addEntry("resources_cards/classes/fr/cards/tarot/gametarot.properties",frGame());
m.addEntry("resources_cards/classes/en/cards/tarot/resultstarot.properties",enRes());
m.addEntry("resources_cards/classes/fr/cards/tarot/resultstarot.properties",frRes());
return m;
}
static String enGame(){
String f="tooManyCards:You cannot discard one more card because the deck of discarded cards is full.\n";
f+="noDiscardedOudler:You cannot discard {0} because it is an oudler.\n";
f+="noDiscardedCharacter:You cannot discard {0} because it is a {1}.\n";
f+="noDiscardedTrump:You cannot discard {0} because it is a trump and it exists at least one always discardable card which will not be discarded.\n";
f+="discardedTrump:You can discard {0} only if all always discardable cards are discarded.\n";
f+="handfulTooManyTrumps:There are {0} more trumps in the handful than expected.\n";
f+="handfulNotEnoughTrumps:There are {0} less trumps in the handful than expected.\n";
f+="handfulExcuse:The Excuse can be shown in a handful only if no trump can fill the handful.\n";
f+="firstTrick:You cannot lead the called suit {0} at the first trick except for {1} or if you have no other card even by playing just after the Excuse has been led.\n";
f+="playSuit:You must follow a card of the suit {0}.\n";
f+="playStrongerCard:You must play a stronger card than {0}.\n";
f+="trump:You must trump with a trump.\n";
f+="undertrump:You must undertrump with a trump.\n";
f+="overtrump:You must overtrump with a trump.\n";
f+="underTrumpPartner:You must undertrump with a card of the suit {0} under your partner.\n";
f+="overTrumpPartner:You must overtrump with a card of the suit {0} over your partner.\n";
f+="trumpFoe:You must trump with a card of the suit {0} over your foe.\n";
f+="overTrumpFoe:You must overtrump with a card of the suit {0} over your foe.\n";
f+="underTrumpFoe:You must undertrump with a card of the suit {0} under your foe.\n";
f+="handValueNoSuit:Your hand was worth a bid {0} but you cannot overbid with the bid {0} over the bid {1}.\n";
f+="overbidDue:you have to declare a stronger bid than the previously declared one or fold.\n";
f+="maybeGoodDog:you may win a good dog.\n";
f+="foeWinTrick:A foe is going to win the trick.\n";
f+="partnerWinTrick:A partner is going to win the trick.\n";
f+="onePossibility:This is one of possibilities.\n";
f+="onlyPlayer:You score more points by playing alone with a sure slam.\n";
f+="singleDiscaredCards:These are the single cards which may be discarded.\n";
f+="noSlam:Not enough trump card or stronger suit cards.\n";
f+="slam:You may win as this.\n";
f+="winAllTricks:You cannot loose the deal, you are going to win all tricks.\n";
f+="hideWeakestTrumps:You should hide the weakest trump cards.\n";
f+="singleCard:This is the single card which can be played.\n";
f+="noWinAllTrick:You cannot win all tricks.\n";
f+="smallBound:Do not worry, you can lead your Small at the last trick.\n";
f+="smallNow:You are going to save your Small now.\n";
f+="savedSmall:You may save your Small.\n";
f+="catchChars:Catching characters or the Small is very interesting.\n";
f+="noCatch:Nothing is useful if one does not have the way.\n";
return f;
}
static String frGame(){
String f="tooManyCards:Vous ne pouvez pas &eacute;carter une carte de plus car l'&eacute;cart est plein.\n";
f+="noDiscardedOudler:Vous ne pouvez pas &eacute;carter {0} car c''est un Bout.\n";
f+="noDiscardedCharacter:Vous ne pouvez pas &eacute;carter {0} car c''est un {1}.\n";
f+="noDiscardedTrump:Vous ne pouvez pas &eacute;carter {0} car c''est un atout et il existe au moins une carte toujours &eacute;cartable qui ne sera pas &eacute;cart&eacute;e.\n";
f+="discardedTrump:Vous ne pouvez &eacute;carter {0} que si toutes les cartes toujours &eacute;cartables sont &eacute;cart&eacute;es.\n";
f+="handfulTooManyTrumps:Il y a {0} atous dans la poign&eacute;e en plus.\n";
f+="handfulNotEnoughTrumps:Il y a {0} atous dans la poign&eacute;e en moins.\n";
f+="handfulExcuse:L'Excuse ne peut &ecirc;tre montr&eacute;e dans une poign&eacute;e que si aucun atout ne peut compl&eacute;ter la poign&eacute;e.\n";
f+="firstTrick:Vous ne pouvez pas entamer le premier pli avec la couleur appel&eacute;e {0} sauf {1} ou si vous n''avez pas d''autre carte m&ecirc;me en jouant juste apr&egrave;s que l''Excuse est entam&eacute;e.\n";
f+="playSuit:Vous devez fournir une carte de la couleur {0}.\n";
f+="playStrongerCard:Vous devez jouer une carte plus forte que {0}.\n";
f+="trump:Vous devez couper avec un atout.\n";
f+="undertrump:Vous devez sous - couper avec un atout.\n";
f+="overtrump:Vous devez sur - couper avec un atout.\n";
f+="underTrumpPartner:Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre partenaire.\n";
f+="overTrumpPartner:Vous devez sur-couper avec une carte de la couleur {0} en dessous de votre partenaire.\n";
f+="trumpFoe:Vous devez couper avec une carte de la couleur {0} au dessus de votre adversaire.\n";
f+="overTrumpFoe:Vous devez sur-couper avec une carte de la couleur {0} au dessus de votre adversaire.\n";
f+="underTrumpFoe:Vous devez sous-couper avec une carte de la couleur {0} en dessous de votre adversaire.\n";
f+="handValueNoSuit:Votre main valait pour un contrat {0} mais vous ne pouvez pas surench&eacute;rir avec le contrat {0} sur le contrat {1}.\n";
f+="overbidDue:il faut annoncer un contrat plus &eacute;lev&eacute; que celui pr&eacute;c&eacute;demment annonc&eacute; ou passer.\n";
f+="maybeGoodDog:Vous aurez peut &ecirc;tre un bon chien.\n";
f+="foeWinTrick:Un adversaire va ramasser le pli.\n";
f+="partnerWinTrick:Votre partenaire va ramasser le pli.\n";
f+="onePossibility:C''est une des possibilit&eacute;s.\n";
f+="onlyPlayer:Vous allez marquer plus de points en jouant seul avec un grand chelem s&ucirc;r.\n";
f+="singleDiscaredCards:Ce sont les seules cartes &agrave; &eacute;carter.\n";
f+="noSlam:Pas assez d''atout ou de cartes ma&icirc;tresses.\n";
f+="slam:Vous aurez finalement plus de chance de gagner comme cela.\n";
f+="winAllTricks:Vous ne pouvez pas perdre la partie, vous allez faire tous les plis.\n";
f+="hideWeakestTrumps:Il faut cacher les atouts les plus petits.\n";
f+="singleCard:C''est la seule carte &agrave; jouer.\n";
f+="noWinAllTrick:Vous ne pourrez pas de toute maniere faire tous les plis.\n";
f+="smallBound:Ne pleurez pas, vous allez pouvoir mener votre Petit au bout.\n";
f+="smallNow:Vous allez sauver votre Petit maintenant.\n";
f+="savedSmall:Vous allez tr&egrave;s probablement sauver votre Petit.\n";
f+="catchChars:Capturer des figures ou le Petit est tr&egraves int&eacute;ressant.\n";
f+="noCatch:Rien ne sert d'acheter si on n'a pas les moyens.\n";
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
