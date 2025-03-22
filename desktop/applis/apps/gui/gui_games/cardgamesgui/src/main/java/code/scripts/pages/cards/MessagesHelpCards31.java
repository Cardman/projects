package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards31{
public static final String M_31_0="_31_0";
public static final String M_31_10="_31_10";
public static final String M_31_11="_31_11";
public static final String M_31_12="_31_12";
public static final String M_31_13="_31_13";
public static final String M_31_14="_31_14";
public static final String M_31_15="_31_15";
public static final String M_31_16="_31_16";
public static final String M_31_17="_31_17";
public static final String M_31_18="_31_18";
public static final String M_31_19="_31_19";
public static final String M_31_1="_31_1";
public static final String M_31_20="_31_20";
public static final String M_31_21="_31_21";
public static final String M_31_22="_31_22";
public static final String M_31_23="_31_23";
public static final String M_31_24="_31_24";
public static final String M_31_2="_31_2";
public static final String M_31_3="_31_3";
public static final String M_31_4="_31_4";
public static final String M_31_5="_31_5";
public static final String M_31_6="_31_6";
public static final String M_31_7="_31_7";
public static final String M_31_8="_31_8";
public static final String M_31_9="_31_9";
private MessagesHelpCards31(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(25);
e.add(M_31_0,"Here is changes with tarot played by 4 players:");
e.add(M_31_10,"Calculation of scores at the end of deal:");
e.add(M_31_11,"\\u00A0\\u00A0Amount of points for a deal: 91 points");
e.add(M_31_12,"\\u00A0\\u00A0Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker");
e.add(M_31_13,"\\u00A0\\u00A0Score of the taker without declaring points = +-25pts + Difference + Ace to end");
e.add(M_31_14,"\\u00A0\\u00A0Declaring = Handfuls + Slam + Possible Miseres");
e.add(M_31_15,"\\u00A0\\u00A0Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)");
e.add(M_31_16,"\\u00A0\\u00A0Full score of the taker = -2 x Full score total of a defender");
e.add(M_31_17,"\\u00A0If the taker score a not integer points, then the difference is rounded to the greater integer.");
e.add(M_31_18,"\\u00A0Examples:");
e.add(M_31_19,"If the taker scores 43,5 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
e.add(M_31_1,"dealing");
e.add(M_31_20,"\\u00A0\\u00A0if none of 3 players has played the Small at the last trick,");
e.add(M_31_21,"\\u00A0\\u00A0then the taker wins the deal by 3 points and scores ((25+(44-41)+0)x1+0+0+0)x2=56 points, each defender scores ((-25-(44-41)+0)x1+0+0+0)X1=-28 points.");
e.add(M_31_22,"If the taker scores 43,5 points with 1 Oudler in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
e.add(M_31_23,"\\u00A0\\u00A0if none of 3 players has played the Small at the last trick,");
e.add(M_31_24,"\\u00A0\\u00A0then the taker looses the deal by 8 points and scores ((-25+(43-51)+0)x1+0+0+0)x2=-66 points, and each defender scores ((25-(43-51)+0)x1+0+0+0)X1=33 points.");
e.add(M_31_2,"\\u00A0\\u00A0each player owns 24 cards and 6 cards make up the dog,");
e.add(M_31_3,"\\u00A0\\u00A0\\u00A0cards are dealt 3 by 3.");
e.add(M_31_4,"Repartition of teams");
e.add(M_31_5,"\\u00A0\\u00A0The taker plays against the other players.");
e.add(M_31_6,"Declaring handfuls:");
e.add(M_31_7,"simple handful: 12 trump cards (The Excuse is a trump card).");
e.add(M_31_8,"double handful: 15 trump cards (The Excuse is a trump card).");
e.add(M_31_9,"triple handful: 18 trump cards (The Excuse is a trump card).");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(25);
f.add(M_31_0,"Voici ce qui change par rapport au jeu à 4 joueurs:");
f.add(M_31_10,"Calcul des scores à la fin de la partie:");
f.add(M_31_11,"\\u00A0\\u00A0Total des points pour une partie: 91 points");
f.add(M_31_12,"\\u00A0\\u00A0Différence = Nb pts réalisés par le preneur - Nb de pts nécessaires pour gagner la partie, pour le preneur");
f.add(M_31_13,"\\u00A0\\u00A0Score du preneur sans points d'annonces = +-25pts + Différence + Petit au bout");
f.add(M_31_14,"\\u00A0\\u00A0Annonces = Poignées + Chelem + Eventuelles Misères");
f.add(M_31_15,"\\u00A0\\u00A0Score total d'un défenseur = - (Score sans points d'annonces du preneur x Coefficient de contrat + Annonces preneur - Annonces défense)");
f.add(M_31_16,"\\u00A0\\u00A0Score total du preneur = -2 x Score total d'un défenseur");
f.add(M_31_17,"\\u00A0Si le preneur marque un nombre non entier de points, alors sa différence est arrondie à l'entier supérieur.");
f.add(M_31_18,"\\u00A0Examples:");
f.add(M_31_19,"Si le preneur marque 43,5 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poignée pour les 3 joueurs, ni de chelem,");
f.add(M_31_1,"distribution");
f.add(M_31_20,"\\u00A0\\u00A0si aucun des 3 joueurs n'a joué le Petit au dernier tour,");
f.add(M_31_21,"\\u00A0\\u00A0alors il gagne son contrat de 3 points et marque ((25+(44-41)+0)x1+0+0+0)x2=56 points de score, et chaque défenseur marque ((-25-(43-41)+0)x1+0+0+0)X1=-28 points de score.");
f.add(M_31_22,"Si le preneur marque 43,5 points avec 1 Bout dans ses plis, en demandant une petite, sans annonces de poignée pour les 3 joueurs, ni de chelem,");
f.add(M_31_23,"\\u00A0\\u00A0si aucun des 3 joueurs n'a joué le Petit au dernier tour,");
f.add(M_31_24,"\\u00A0\\u00A0alors il perd son contrat de 8 points et marque ((-25+(43-51)+0)x1+0+0+0)x2=-66 points de score, et chaque défenseur marque ((25-(43-51)+0)x1+0+0+0)X1=33 points de score.");
f.add(M_31_2,"\\u00A0\\u00A0chaque joueur possède 24 cartes et 6 constituent le chien,");
f.add(M_31_3,"\\u00A0\\u00A0les cartes sont distribuées 3 par 3.");
f.add(M_31_4,"Répartition des équipes");
f.add(M_31_5,"\\u00A0\\u00A0Le preneur joue contre les deux autres joueurs.");
f.add(M_31_6,"Annonces de poignée:");
f.add(M_31_7,"simple poignée: 12 atouts (y compris l'Excuse).");
f.add(M_31_8,"poignée double: 15 atouts (y compris l'Excuse).");
f.add(M_31_9,"triple poignée: 18 atouts (y compris l'Excuse).");
return f;
}
}
