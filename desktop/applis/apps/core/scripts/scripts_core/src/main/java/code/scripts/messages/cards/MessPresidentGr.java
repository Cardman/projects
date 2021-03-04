package code.scripts.messages.cards;
import code.util.*;
public final class MessPresidentGr{
private MessPresidentGr(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/classes/en/cards/president/gamepresident.properties",enGame());
m.addEntry("resources_cards/classes/fr/cards/president/gamepresident.properties",frGame());
m.addEntry("resources_cards/classes/en/cards/president/resultspresident.properties",enRes());
m.addEntry("resources_cards/classes/fr/cards/president/resultspresident.properties",frRes());
return m;
}
static String enGame(){
String f="skipped:You cannot play any cards at this moment because two players have consecutively played card groups of same strength.\n";
f+="havePassed:You cannot play any cards while this trick because you had passed before.\n";
f+="hasToEqualOrSkip:You have to play cards while same value as {0} or pass.\n";
f+="cannotUseLowerOrEq:If you want to play, you have to use cards whose value is greater than {0}.\n";
f+="cannotUseLower:If you want to play, you have to use cards whose value is greater than or equals to {0}.\n";
f+="hasPlayGivenNumberCards:If you want to play, you have to use exactly {0} cards.\n";
f+="cannotPass:You have to play over {0}.\n";
return f;
}
static String frGame(){
String f="skipped:Vous ne pouvez pas jouer de cartes sur ce coup car deux joueurs ont jou&eacute; cons&eacute;cutivement des groupes de cartes de m&ecirc;me force.\n";
f+="havePassed:Vous ne pouvez pas jouer de cartes pendant ce pli car vous avez pass&eacute; avant.\n";
f+="hasToEqualOrSkip:Vous devez jouez des cartes de m&ecirc;me valeur que {0} ou passer.\n";
f+="cannotUseLowerOrEq:Si vous voulez jouer, vous devez utiliser des cartes dont la valeur est sup&eacute;rieure &agrave; {0}.\n";
f+="cannotUseLower:Si vous voulez jouer, vous devez utiliser des cartes dont la valeur est sup&eacute;rieure ou &eacute;gale &agrave; {0}.\n";
f+="hasPlayGivenNumberCards:Si vous voulez jouer, vous devez utiliser exactement {0} cartes.\n";
f+="cannotPass:Vous devez jouer sur {0}.\n";
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
