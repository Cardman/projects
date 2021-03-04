package code.scripts.pages.cards;
import code.util.*;
public final class MessPresidentPage{
private MessPresidentPage(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/css/president.css",css());
m.addEntry("resources_cards/messages/en/messages_president.properties",en());
m.addEntry("resources_cards/messages/fr/messages_president.properties",fr());
return m;
}
static String css(){
String f="h1 {\n";
f+="\tcolor: #0000FF;\n";
f+="}\n";
return f;
}
static String en(){
String f="win=You win.\n";
f+="equality=Equality.\n";
f+="loose=You loose.\n";
f+="successful=The bid {0} is passed of {1} points.\n";
f+="failed=The bid {0} is failed of {1} points.\n";
f+="mid=The bid {0} is neither passed nor failed.\n";
f+="slam=The attack''s team has achieved the grand slam.\n";
f+="noSlam=The attack''s team has not won all tricks.\n";
f+="ranks=Ranks\n";
f+="results=Results\n";
f+="beat_cards=Mix Cards\n";
f+="nb_players=Number of players\n";
f+="nb_stacks=Number of stacks\n";
f+="nb_cards=Number of cards per player: {0}\n";
f+="nb_cards_bet=Number of cards per player: between {0} and {1}\n";
f+="equalty=Equality\n";
f+="invert=Possible reversing strength\n";
f+="has_to_play=Every player has to play if possible\n";
f+="loose_cond=A player looses if this player finish by the best cards\n";
f+="switch=For next deals, players can switch their cards at the beginning\n";
f+="losse_first=For next deals, the looser player starts first the first trick\n";
f+="yes=yes\n";
f+="no=no\n";
return f;
}
static String fr(){
String f="win=Vous gagnez.\n";
f+="equality=Match nul.\n";
f+="loose=Vous perdez.\n";
f+="successful=L''ench&egrave;re {0} est r&eacute;ussie de {1} points.\n";
f+="failed=L''ench&egrave;re {0} est chut&eacute;e de {1} points.\n";
f+="mid=L''ench&egrave;re {0} n''est ni r&eacute;ussie ni chut&eacute;e.\n";
f+="slam=L''attaque a r&eacute;ussi un capot.\n";
f+="noSlam=L''attaque n''a pas r&eacute;ussi de capot.\n";
f+="ranks=Rangs\n";
f+="results=R&eacute;sultats\n";
f+="beat_cards=Battre les cartes\n";
f+="nb_players=Nombre de joueurs\n";
f+="nb_stacks=Nombre de tas\n";
f+="nb_cards=Nombre de cartes par joueur: {0}\n";
f+="nb_cards_bet=Nombre de cartes par joueur: entre {0} et {1}\n";
f+="equalty=&Eacute;galit&eacute;\n";
f+="invert=R&eacute;volution possible\n";
f+="has_to_play=Chaque joueur doit jouer si possible\n";
f+="loose_cond=Un joueur perd si ce joueur finit par les meilleures cartes\n";
f+="switch=Pour les donnes suivantes, les joueurs peuvent &eacute;changer leurs cartes au d&eacute;but\n";
f+="losse_first=Pour les donnes suivantes, le perdant commence &agrave; jouer le premier pli\n";
f+="yes=oui\n";
f+="no=non\n";
return f;
}
}
