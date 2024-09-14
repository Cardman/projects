package code.scripts.pages.cards;
final class HelpCards102 extends HelpCardsCommon{
private HelpCards102(){}
static String en(){
String e="";
e+=M_31_0+"=Here is changes with tarot played by 4 players:\n";
e+=M_31_1+"=dealing\n";
e+=M_31_2+"="+SEP+""+SEP+"each player owns 24 cards and 6 cards make up the dog,\n";
e+=M_31_3+"="+SEP+""+SEP+""+SEP+"cards are dealt 3 by 3.\n";
e+=M_31_4+"=Repartition of teams\n";
e+=M_31_5+"="+SEP+""+SEP+"The taker plays against the other players.\n";
e+=M_31_6+"=Declaring handfuls:\n";
e+=M_31_7+"=simple handful: 12 trump cards (The Excuse is a trump card).\n";
e+=M_31_8+"=double handful: 15 trump cards (The Excuse is a trump card).\n";
e+=M_31_9+"=triple handful: 18 trump cards (The Excuse is a trump card).\n";
e+=M_31_10+"=Calculation of scores at the end of deal:\n";
e+=M_31_11+"="+SEP+""+SEP+"Amount of points for a deal: 91 points\n";
e+=M_31_12+"="+SEP+""+SEP+"Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker\n";
e+=M_31_13+"="+SEP+""+SEP+"Score of the taker without declaring points = +-25pts + Difference + Ace to end\n";
e+=M_31_14+"="+SEP+""+SEP+"Declaring = Handfuls + Slam + Possible Miseres\n";
e+=M_31_15+"="+SEP+""+SEP+"Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)\n";
e+=M_31_16+"="+SEP+""+SEP+"Full score of the taker = -2 x Full score total of a defender\n";
e+=M_31_17+"="+SEP+"If the taker score a not integer points, then the difference is rounded to the greater integer.\n";
e+=M_31_18+"="+SEP+"Examples:\n";
e+=M_31_19+"=If the taker scores 43,5 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,\n";
e+=M_31_20+"="+SEP+""+SEP+"if none of 3 players has played the Small at the last trick,\n";
e+=M_31_21+"="+SEP+""+SEP+"then the taker wins the deal by 3 points and scores ((25+(44-41)+0)x1+0+0+0)x2=56 points, each defender scores ((-25-(44-41)+0)x1+0+0+0)X1=-28 points.\n";
e+=M_31_22+"=If the taker scores 43,5 points with 1 Oudler in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,\n";
e+=M_31_23+"="+SEP+""+SEP+"if none of 3 players has played the Small at the last trick,\n";
e+=M_31_24+"="+SEP+""+SEP+"then the taker looses the deal by 8 points and scores ((-25+(43-51)+0)x1+0+0+0)x2=-66 points, and each defender scores ((25-(43-51)+0)x1+0+0+0)X1=33 points.\n";
return e;
}
static String fr(){
String f="";
f+=M_31_0+"=Voici ce qui change par rapport au jeu à 4 joueurs:\n";
f+=M_31_1+"=distribution\n";
f+=M_31_2+"="+SEP+""+SEP+"chaque joueur possède 24 cartes et 6 constituent le chien,\n";
f+=M_31_3+"="+SEP+""+SEP+"les cartes sont distribuées 3 par 3.\n";
f+=M_31_4+"=Répartition des équipes\n";
f+=M_31_5+"="+SEP+""+SEP+"Le preneur joue contre les deux autres joueurs.\n";
f+=M_31_6+"=Annonces de poignée:\n";
f+=M_31_7+"=simple poignée: 12 atouts (y compris l'Excuse).\n";
f+=M_31_8+"=poignée double: 15 atouts (y compris l'Excuse).\n";
f+=M_31_9+"=triple poignée: 18 atouts (y compris l'Excuse).\n";
f+=M_31_10+"=Calcul des scores à la fin de la partie:\n";
f+=M_31_11+"="+SEP+""+SEP+"Total des points pour une partie: 91 points\n";
f+=M_31_12+"="+SEP+""+SEP+"Différence = Nb pts réalisés par le preneur - Nb de pts nécessaires pour gagner la partie, pour le preneur\n";
f+=M_31_13+"="+SEP+""+SEP+"Score du preneur sans points d'annonces = +-25pts + Différence + Petit au bout\n";
f+=M_31_14+"="+SEP+""+SEP+"Annonces = Poignées + Chelem + Eventuelles Misères\n";
f+=M_31_15+"="+SEP+""+SEP+"Score total d'un défenseur = - (Score sans points d'annonces du preneur x Coefficient de contrat + Annonces preneur - Annonces défense)\n";
f+=M_31_16+"="+SEP+""+SEP+"Score total du preneur = -2 x Score total d'un défenseur\n";
f+=M_31_17+"="+SEP+"Si le preneur marque un nombre non entier de points, alors sa différence est arrondie à l'entier supérieur.\n";
f+=M_31_18+"="+SEP+"Examples:\n";
f+=M_31_19+"=Si le preneur marque 43,5 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poignée pour les 3 joueurs, ni de chelem,\n";
f+=M_31_20+"="+SEP+""+SEP+"si aucun des 3 joueurs n'a joué le Petit au dernier tour,\n";
f+=M_31_21+"="+SEP+""+SEP+"alors il gagne son contrat de 3 points et marque ((25+(44-41)+0)x1+0+0+0)x2=56 points de score, et chaque défenseur marque ((-25-(43-41)+0)x1+0+0+0)X1=-28 points de score.\n";
f+=M_31_22+"=Si le preneur marque 43,5 points avec 1 Bout dans ses plis, en demandant une petite, sans annonces de poignée pour les 3 joueurs, ni de chelem,\n";
f+=M_31_23+"="+SEP+""+SEP+"si aucun des 3 joueurs n'a joué le Petit au dernier tour,\n";
f+=M_31_24+"="+SEP+""+SEP+"alors il perd son contrat de 8 points et marque ((-25+(43-51)+0)x1+0+0+0)x2=-66 points de score, et chaque défenseur marque ((25-(43-51)+0)x1+0+0+0)X1=33 points de score.\n";
return f;
}
}
