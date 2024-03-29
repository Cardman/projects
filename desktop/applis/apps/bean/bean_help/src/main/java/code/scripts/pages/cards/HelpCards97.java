package code.scripts.pages.cards;
final class HelpCards97 extends HelpCardsCommon{
private HelpCards97(){}
static String en(){
String e="";
e+=M_26_0+"=Here is the difference with playing by teams (Taker + possible called player versus Defense):\n";
e+=M_26_1+"=Possible dealing are the following one:\n";
e+=M_26_2+"=18 cards per player and 6 at the dog; cards are dealt 3 by 3; for 4 players.\n";
e+=M_26_3+"=24 cards per player and 6 at the dog; cards are dealt 3 by 3; for 3 players.\n";
e+=M_26_4+"=15 cards per player and 3 at the dog; cards are dealt 3 by 3; for 5 players.\n";
e+=M_26_5+"=14 cards per player and 8 at the dog; cards are dealt 2 by 2; for 5 players.\n";
e+=M_26_6+"=12 cards per player and 6 at the dog; cards are dealt 2 by 2; for 6 players.\n";
e+=M_26_7+"=Playing one for one can be done after all players have declared \"pass\".\n";
e+=M_26_8+"=In this case, the dog is hidden.\n";
e+=M_26_9+"=The goal is to get the most cards as possible.\n";
e+=M_26_10+"=Else the deal progresses like game by teams (Same declaring and same rules for playing cards.).\n";
e+=M_26_11+"=&#160;Limit values to be achieved for a player are the following one:\n";
e+=M_26_12+"=56 points without Oulder.\n";
e+=M_26_13+"=51 points with 1 Oulder in the tricks of the player.\n";
e+=M_26_14+"=41 points with 2 Oulders in the tricks of the player.\n";
e+=M_26_15+"=36 points with 3 Oulders in the tricks of the player.\n";
e+=M_26_16+"=&#160;At the end of deal, for sorting players, one processes in the following way:\n";
e+=M_26_17+"=Difference points of a player = number of scored points in the tricks - limit value for the player.\n";
e+=M_26_18+"=Players are sorted by their difference points, the player having the greatest difference is the best player.\n";
e+=M_26_19+"=If two players have same difference points and have won tricks:\n";
e+=M_26_20+"=&#160;Then the players are sorted by number of Oudlers in the tricks, the number of Oudlers must be the greatest as possible for winning.\n";
e+=M_26_21+"=&#160;If besides, the number of Oudlers in the tricks of the two players is the same, then:\n";
e+=M_26_22+"=If this number is 0:\n";
e+=M_26_23+"=&#160;&#160;&#160;Numbers of characters in the tricks of the two players is compared, the number of characters in the tricks of a player must be the greatest as possible for winning.\n";
e+=M_26_24+"=&#160;&#160;&#160;If besides, the number of characters in the tricks of the two players is the same, then:\n";
e+=M_26_25+"=&#160;&#160;&#160;&#160;Won characters by the two players are compared, by firstly comparing the greatest characters.\n";
e+=M_26_26+"=&#160;&#160;&#160;&#160;Comparison is stopped, if there is a difference of strength of card.\n";
e+=M_26_27+"=&#160;&#160;&#160;&#160;If the characters have the same strength two by two for the two players,\n";
e+=M_26_28+"=&#160;&#160;&#160;&#160;&#160;the first player who has won a trick is before the other player.\n";
e+=M_26_29+"=If this number is 1:\n";
e+=M_26_30+"=&#160;&#160;&#160;Sorting on Oudlers is based in the following way from the most winning to the most loosing: Trump 21 - Excuse - Small.\n";
e+=M_26_31+"=&#160;&#160;&#160;(Winning the Excuse is better than winning the Small,...)\n";
e+=M_26_32+"=If two players have same difference points but have not won any trick, there is dispute.\n";
e+=M_26_33+"=3 players\n";
e+=M_26_34+"=&#160;&#160;Rate is worth from the winner to the looser:\n";
e+=M_26_35+"=1, 0, -1; if there is no dispute.\n";
e+=M_26_36+"=2, -1, -1; if there is dispute.\n";
e+=M_26_37+"=4 players\n";
e+=M_26_39+"=2, 1, -1, -2; if there is no dispute.\n";
e+=M_26_40+"=3, 1, -2, -2; if there is dispute between two players exactly.\n";
e+=M_26_41+"=6, -2, -2, -2; if there is dispute between three players exactly.\n";
e+=M_26_42+"=5 players\n";
e+=M_26_44+"=2, 1, 0, -1, -2; if there is no dispute.\n";
e+=M_26_45+"=3, 1, 0, -2, -2; if there is dispute between two players exactly.\n";
e+=M_26_46+"=6, 0, -2, -2, -2; if there is dispute between three players exactly.\n";
e+=M_26_47+"=8, -2, -2, -2, -2; if there is dispute between four players exactly.\n";
e+=M_26_48+"=6 players\n";
e+=M_26_50+"=3, 2, 1, -1, -2, -3; if there is no dispute.\n";
e+=M_26_51+"=3, 2, 1, 0, -3, -3; if there is dispute between two players exactly.\n";
e+=M_26_52+"=4, 2, 0, -2, -2, -2; if there is dispute between three players exactly.\n";
e+=M_26_53+"=8, 0, -2, -2, -2, -2; if there is dispute between four players exactly.\n";
e+=M_26_54+"=10, -2, -2, -2, -2, -2; if there is dispute between five players exactly.\n";
e+=M_26_55+"=A successful slam is by default 200 points. (The slam is regarded as not declared.)\n";
e+=M_26_56+"=Score of a player = 4 x (Rate x (25 + Maximal difference points for players rounded to the greater integer + number of points in the dog rounded to the greater integer) + (number of players - team size) x Declaring points of player team - team size x Declaring of each one of other players teams).\n";
e+=M_26_57+"=By teams of two players, rates are the following one:\n";
e+=M_26_58+"=4 players\n";
e+=M_26_59+"=&#160;&#160;Rate is worth from the winner team to the looser team:\n";
e+=M_26_60+"=1, -1\n";
e+=M_26_61+"=6 players\n";
e+=M_26_62+"=&#160;&#160;Rate is worth from the winner team to the looser team:\n";
e+=M_26_63+"=1, 0, -1; if there is no dispute.\n";
e+=M_26_64+"=2, -1, -1; if there is dispute between two teams exactly.\n";
return e;
}
static String fr(){
String f="";
f+=M_26_0+"=Voici ce qui change par rapport au jeu d''&#233;quipes (Preneur + &#233;ventuel appel&#233; versus D&#233;fense):\n";
f+=M_26_1+"=Les distributions possibles sont les suivantes:\n";
f+=M_26_2+"=18 cartes par joueur et 6 au chien; les cartes sont distribu&#233;es 3 par 3; pour 4 joueurs.\n";
f+=M_26_3+"=24 cartes par joueur et 6 au chien; les cartes sont distribu&#233;es 3 par 3; pour 3 joueurs.\n";
f+=M_26_4+"=15 cartes par joueur et 3 au chien; les cartes sont distribu&#233;es 3 par 3; pour 5 joueurs.\n";
f+=M_26_5+"=14 cartes par joueur et 8 au chien; les cartes sont distribu&#233;es 2 par 2; pour 5 joueurs.\n";
f+=M_26_6+"=12 cartes par joueur et 6 au chien; les cartes sont distribu&#233;es 2 par 2; pour 6 joueurs.\n";
f+=M_26_7+"=On peut jouer le jeu du chacun pour soi apr&#232;s que tout le monde a pass&#233;.\n";
f+=M_26_8+"=Dans ce cas, le chien est cach&#233;.\n";
f+=M_26_9+"=Le but est de ramasser le plus de cartes possibles.\n";
f+=M_26_10+"=Sinon le jeu se d&#233;roule de la m&#234;me mani&#232;re que le jeu d''&#233;quipes (M&#234;mes annonces et m&#234;mes r&#232;gles pour le jeu de la carte.).\n";
f+=M_26_11+"=&#160;Les valeurs limites &#224; atteindre pour un joueur sont les suivantes:\n";
f+=M_26_12+"=56 points sans bout.\n";
f+=M_26_13+"=51 points avec 1 bout dans les plis du joueur.\n";
f+=M_26_14+"=41 points avec 2 bouts dans les plis du joueur.\n";
f+=M_26_15+"=36 points avec 3 bouts dans les plis du joueur.\n";
f+=M_26_16+"=&#160;A la fin de la partie, pour d&#233;signer le classement des joueurs, on proc&#232;de de la mani&#232;re suivante:\n";
f+=M_26_17+"=Diff&#233;rence d''un joueur = nombre de points marqu&#233;s dans les plis - valeur limite pour le joueur.\n";
f+=M_26_18+"=On classe les joueurs par leur diff&#233;rence, la plus grande diff&#233;rence pour le meilleur.\n";
f+=M_26_19+"=Si deux joueurs ont m&#234;me diff&#233;rence et ont fait des plis:\n";
f+=M_26_20+"=&#160;Alors on les classe par le nombre de bouts dans les plis, il doit &#234;tre le plus grand possible pour gagner.\n";
f+=M_26_21+"=&#160;Si de plus, le nombre de bouts dans les plis des deux joueurs est identique, alors:\n";
f+=M_26_22+"=Si ce nombre vaut 0:\n";
f+=M_26_23+"=&#160;&#160;&#160;On compare les nombres de figures dans les plis des deux joueurs, le nombre de figures dans les plis d''un joueur doit &#234;tre le plus grand possible pour gagner.\n";
f+=M_26_24+"=&#160;&#160;&#160;Si de plus, le nombre de figures dans les plis des deux joueurs est identique, alors:\n";
f+=M_26_25+"=&#160;&#160;&#160;&#160;On compare les figures ramass&#233;es par les deux joueurs, en commen&#231;ant par les plus hautes.\n";
f+=M_26_26+"=&#160;&#160;&#160;&#160;On s''arr&#234;te d&#232;s qu''il y a une diff&#233;rence de hauteur.\n";
f+=M_26_27+"=&#160;&#160;&#160;&#160;Si les figures sont identiques en valeur deux &#224; deux des deux joueurs, on regarde qui a fait le premier pli.\n";
f+=M_26_28+"=&#160;&#160;&#160;&#160;&#160;Celui qui a fait son premier pli en premier passe devant.\n";
f+=M_26_29+"=Si ce nombre vaut 1:\n";
f+=M_26_30+"=&#160;&#160;&#160;On classe alors de la mani&#232;re suivante du plus gagnant au plus perdant: 21 - Excuse - Petit.\n";
f+=M_26_31+"=&#160;&#160;&#160;(Il vaut mieux alors avoir l''Excuse que le Petit,...)\n";
f+=M_26_32+"=Si deux joueurs ont m&#234;me diff&#233;rence mais n''ont pas fait des plis, il y a litige.\n";
f+=M_26_33+"=3 joueurs\n";
f+=M_26_34+"=&#160;&#160;Coefficient vaut du gagnant au perdant:\n";
f+=M_26_35+"=1, 0, -1; s''il n''y a pas de litige.\n";
f+=M_26_36+"=2, -1, -1; s''il y a litige.\n";
f+=M_26_37+"=4 joueurs\n";
f+=M_26_39+"=2, 1, -1, -2; s''il n''y a pas de litige.\n";
f+=M_26_40+"=3, 1, -2, -2; s''il y a litige entre deux joueurs exactement.\n";
f+=M_26_41+"=6, -2, -2, -2; s''il y a litige entre trois joueurs exactement.\n";
f+=M_26_42+"=5 joueurs\n";
f+=M_26_44+"=2, 1, 0, -1, -2; s''il n''y a pas de litige.\n";
f+=M_26_45+"=3, 1, 0, -2, -2; s''il y a litige entre deux joueurs exactement.\n";
f+=M_26_46+"=6, 0, -2, -2, -2; s''il y a litige entre trois joueurs exactement.\n";
f+=M_26_47+"=8, -2, -2, -2, -2; s''il y a litige entre quatre joueurs exactement.\n";
f+=M_26_48+"=6 joueurs\n";
f+=M_26_50+"=3, 2, 1, -1, -2, -3; s''il n''y a pas de litige.\n";
f+=M_26_51+"=3, 2, 1, 0, -3, -3; s''il y a litige entre deux joueurs exactement.\n";
f+=M_26_52+"=4, 2, 0, -2, -2, -2; s''il y a litige entre trois joueurs exactement.\n";
f+=M_26_53+"=8, 0, -2, -2, -2, -2; s''il y a litige entre quatre joueurs exactement.\n";
f+=M_26_54+"=10, -2, -2, -2, -2, -2; s''il y a litige entre cinq joueurs exactement.\n";
f+=M_26_55+"=Un chelem r&#233;ussi vaut de base 200 points. (Il est consid&#233;r&#233; comme non demand&#233;.)\n";
f+=M_26_56+"=Score d''un joueur = 4 x (Coefficient x (25 + Diff&#233;rence max pour les joueurs arrondie &#224; l''entier sup&#233;rieur + nombre points chien arrondi &#224; l''entier sup&#233;rieur) + (nombre de joueurs - taille &eacute;quipe) x Annonces de l''&eacute;quipe joueur - taille &eacute;quipe x Annonces de chacun des autres &eacute;quipes des joueurs).\n";
f+=M_26_57+"=En &#233;quipes de deux joueurs, les coefficients sont les suivants:\n";
f+=M_26_58+"=4 joueurs\n";
f+=M_26_59+"=&#160;&#160;Coefficient vaut de l''&#233;quipe gagnante &#224; l''&#233;quipe perdante:\n";
f+=M_26_60+"=1, -1\n";
f+=M_26_61+"=6 joueurs\n";
f+=M_26_62+"=&#160;&#160;Coefficient vaut de l''&#233;quipe gagnante &#224; l''&#233;quipe perdante:\n";
f+=M_26_63+"=1, 0, -1; s''il n''y a pas de litige.\n";
f+=M_26_64+"=2, -1, -1; s''il y a litige entre deux &#233;quipes exactement.\n";
return f;
}
}
