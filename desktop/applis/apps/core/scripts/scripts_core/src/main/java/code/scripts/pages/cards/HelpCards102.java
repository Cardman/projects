package code.scripts.pages.cards;
final class HelpCards102 extends HelpCardsCommon{
private HelpCards102(){}
static String en(){
String e="";
e+=M_31_0+"=Here is changes with tarot played by 4 players:\n";
e+=M_31_1+"=dealing\n";
e+=M_31_2+"=&#160;&#160;each player owns 24 cards and 6 cards make up the dog,\n";
e+=M_31_3+"=&#160;&#160;&#160;cards are dealt 3 by 3.\n";
e+=M_31_4+"=Repartition of teams\n";
e+=M_31_5+"=&#160;&#160;The taker plays against the other players.\n";
e+=M_31_6+"=Declaring handfuls:\n";
e+=M_31_7+"=simple handful: 12 trump cards (The Excuse is a trump card).\n";
e+=M_31_8+"=double handful: 15 trump cards (The Excuse is a trump card).\n";
e+=M_31_9+"=triple handful: 18 trump cards (The Excuse is a trump card).\n";
e+=M_31_10+"=Calculation of scores at the end of deal:\n";
e+=M_31_11+"=&#160;&#160;Amount of points for a deal: 91 points\n";
e+=M_31_12+"=&#160;&#160;Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker\n";
e+=M_31_13+"=&#160;&#160;Score of the taker without declaring points = +-25pts + Difference + Ace to end\n";
e+=M_31_14+"=&#160;&#160;Declaring = Handfuls + Slam + Possible Miseres\n";
e+=M_31_15+"=&#160;&#160;Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)\n";
e+=M_31_16+"=&#160;&#160;Full score of the taker = -2 x Full score total of a defender\n";
e+=M_31_17+"=&#160;If the taker score a not integer points, then the difference is rounded to the greater integer.\n";
e+=M_31_18+"=&#160;Examples:\n";
e+=M_31_19+"=If the taker scores 43,5 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,\n";
e+=M_31_20+"=&#160;&#160;if none of 3 players has played the Small at the last trick,\n";
e+=M_31_21+"=&#160;&#160;then the taker wins the deal by 3 points and scores ((25+(44-41)+0)x1+0+0+0)x2=56 points, each defender scores ((-25-(44-41)+0)x1+0+0+0)X1=-28 points.\n";
e+=M_31_22+"=If the taker scores 43,5 points with 1 Oudler in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,\n";
e+=M_31_23+"=&#160;&#160;if none of 3 players has played the Small at the last trick,\n";
e+=M_31_24+"=&#160;&#160;then the taker looses the deal by 8 points and scores ((-25+(43-51)+0)x1+0+0+0)x2=-66 points, and each defender scores ((25-(43-51)+0)x1+0+0+0)X1=33 points.\n";
return e;
}
static String fr(){
String f="";
f+=M_31_0+"=Voici ce qui change par rapport au jeu &#224; 4 joueurs:\n";
f+=M_31_1+"=distribution\n";
f+=M_31_2+"=&#160;&#160;chaque joueur poss&#232;de 24 cartes et 6 constituent le chien,\n";
f+=M_31_3+"=&#160;&#160;les cartes sont distribu&#233;es 3 par 3.\n";
f+=M_31_4+"=R&#233;partition des &#233;quipes\n";
f+=M_31_5+"=&#160;&#160;Le preneur joue contre les deux autres joueurs.\n";
f+=M_31_6+"=Annonces de poign&#233;e:\n";
f+=M_31_7+"=simple poign&#233;e: 12 atouts (y compris l''Excuse).\n";
f+=M_31_8+"=poign&#233;e double: 15 atouts (y compris l''Excuse).\n";
f+=M_31_9+"=triple poign&#233;e: 18 atouts (y compris l''Excuse).\n";
f+=M_31_10+"=Calcul des scores &#224; la fin de la partie:\n";
f+=M_31_11+"=&#160;&#160;Total des points pour une partie: 91 points\n";
f+=M_31_12+"=&#160;&#160;Diff&#233;rence = Nb pts r&#233;alis&#233;s par le preneur - Nb de pts n&#233;cessaires pour gagner la partie, pour le preneur\n";
f+=M_31_13+"=&#160;&#160;Score du preneur sans points d''annonces = +-25pts + Diff&#233;rence + Petit au bout\n";
f+=M_31_14+"=&#160;&#160;Annonces = Poign&#233;es + Chelem + Eventuelles Mis&#232;res\n";
f+=M_31_15+"=&#160;&#160;Score total d''un d&#233;fenseur = - (Score sans points d''annonces du preneur x Coefficient de contrat + Annonces preneur - Annonces d&#233;fense)\n";
f+=M_31_16+"=&#160;&#160;Score total du preneur = -2 x Score total d''un d&#233;fenseur\n";
f+=M_31_17+"=&#160;Si le preneur marque un nombre non entier de points, alors sa diff&#233;rence est arrondie &#224; l''entier sup&#233;rieur.\n";
f+=M_31_18+"=&#160;Examples:\n";
f+=M_31_19+"=Si le preneur marque 43,5 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 3 joueurs, ni de chelem,\n";
f+=M_31_20+"=&#160;&#160;si aucun des 3 joueurs n''a jou&#233; le Petit au dernier tour,\n";
f+=M_31_21+"=&#160;&#160;alors il gagne son contrat de 3 points et marque ((25+(44-41)+0)x1+0+0+0)x2=56 points de score, et chaque d&#233;fenseur marque ((-25-(43-41)+0)x1+0+0+0)X1=-28 points de score.\n";
f+=M_31_22+"=Si le preneur marque 43,5 points avec 1 Bout dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 3 joueurs, ni de chelem,\n";
f+=M_31_23+"=&#160;&#160;si aucun des 3 joueurs n''a jou&#233; le Petit au dernier tour,\n";
f+=M_31_24+"=&#160;&#160;alors il perd son contrat de 8 points et marque ((-25+(43-51)+0)x1+0+0+0)x2=-66 points de score, et chaque d&#233;fenseur marque ((25-(43-51)+0)x1+0+0+0)X1=33 points de score.\n";
return f;
}
}
