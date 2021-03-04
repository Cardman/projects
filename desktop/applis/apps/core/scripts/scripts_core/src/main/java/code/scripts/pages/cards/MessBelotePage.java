package code.scripts.pages.cards;
import code.util.*;
public final class MessBelotePage{
private MessBelotePage(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/css/belote.css",css());
m.addEntry("resources_cards/messages/en/messages_belote.properties",en());
m.addEntry("resources_cards/messages/fr/messages_belote.properties",fr());
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
f+="results=Results\n";
f+="detail=Details of declaring\n";
f+="decl_player={0}''s declaring ({1}):\n";
f+="nothing=No thing\n";
f+="sum=Sum :\n";
f+="calc_title=1 Calculation of attack team''s points\n";
f+="need=Number of necessary points in order that the taker wins:\n";
f+="won_trick=Number of points won in the attack team''s tricks:\n";
f+="taker_title=2 Attack team\n";
f+="taker=Taker:\n";
f+="taker_team=Taker''s partners:\n";
f+="none=Noone\n";
f+="bid=Bid:\n";
f+="results_title=3 Results\n";
f+="without_decl_att=Scored points by attack''s team without bonuses:\n";
f+="without_decl_def=Scored points by defense''s team without bonuses:\n";
f+="with_decl_att=Scored points by attack''s team with bonuses:\n";
f+="with_decl_def=Scored points by defense''s team with bonuses:\n";
f+="fin_att=Final scored points by attack''s team:\n";
f+="fin_def=Final scored points by defense''s team:\n";
f+="scores=Scores\n";
f+="beat_cards=Mix Cards\n";
f+="deal=Deal all cards\n";
f+="decl=Allowed declaring\n";
f+="under=Undertrumping a foe\n";
f+="bids=Allowed Bids at the beginning of the deal\n";
f+="partner=Rules of playing trumps when a partner is leading the current trick\n";
f+="dealing=Players'' repartition\n";
f+="end=End of game\n";
f+="end_def=162 - 0, if the attack''s team looses.\n";
f+="end_else=If the attack''s team looses, then this team keeps its own scored points.\n";
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
f+="results=R&eacute;sultats\n";
f+="detail=D&eacute;tails des annonces\n";
f+="decl_player=Annonces de {0} ({1}):\n";
f+="nothing=Rien\n";
f+="sum=Somme :\n";
f+="calc_title=1 Calcul des points en attaque\n";
f+="need=Nombre de points n&eacute;cessaires pour que le preneur gagne:\n";
f+="won_trick=Nombre de points gagn&eacute;s dans les plis de l''attaque:\n";
f+="taker_title=2 Attaque\n";
f+="taker=Preneur:\n";
f+="taker_team=Partenaires du preneur:\n";
f+="none=Aucun\n";
f+="bid=Ench&egrave;re:\n";
f+="results_title=3 R&eacute;sultats\n";
f+="without_decl_att=Points marqu&eacute;s par l''attaque sans les primes:\n";
f+="without_decl_def=Points marqu&eacute;s par la d&eacute;fense sans les primes:\n";
f+="with_decl_att=Points marqu&eacute;s par l''attaque avec les primes:\n";
f+="with_decl_def=Points marqu&eacute;s par la d&eacute;fense avec les primes:\n";
f+="fin_att=Score d&eacute;finitif de l''attaque:\n";
f+="fin_def=Score d&eacute;finitif de la d&eacute;fense:\n";
f+="scores=Scores\n";
f+="beat_cards=Battre les cartes\n";
f+="deal=Distribuer toutes les cartes\n";
f+="decl=Annonces autoris&eacute;es\n";
f+="under=Sous-couper un adversaire\n";
f+="bids=Ench&egrave;res autoris&eacute;es au d&eacute;but de la partie\n";
f+="partner=R&egrave;gles du jeu des atouts lorsqu''un partenaire est ma&icirc;tre du pli courant\n";
f+="dealing=R&eacute;partition des joueurs\n";
f+="end=Fin de partie\n";
f+="end_def=162 - 0, si l''attaque perd.\n";
f+="end_else=Si l''attaque gagne, alors cette &eacute;quipe garde son propre score.\n";
f+="yes=oui\n";
f+="no=non\n";
return f;
}
}
