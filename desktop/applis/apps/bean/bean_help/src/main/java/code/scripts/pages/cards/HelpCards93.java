package code.scripts.pages.cards;
final class HelpCards93 extends HelpCardsCommon{
private HelpCards93(){}
static String en(){
String e="";
e+=M_22_0+"=Value of cards\n";
e+=M_22_1+"=Trump 21, Small and Excuse: 4,5 points each one (these are the 3 Oudlers)\n";
e+=M_22_2+"=Kings: 4,5 points each one\n";
e+=M_22_3+"=Queens: 3,5 points each one\n";
e+=M_22_4+"=Knights: 2,5 points each one\n";
e+=M_22_5+"=Jacks: 1,5 points each one\n";
e+=M_22_6+"=Low cards and trump cards from 2 to 20: 0,5 point each one\n";
e+=M_22_7+"=Calculation of scores\n";
e+=M_22_8+"="+SEP+"Nb means \"Number\".\n";
e+=M_22_9+"="+SEP+"+- means \"Plus or minus\".\n";
e+=M_22_10+"="+SEP+"pts means \"points\".\n";
e+=M_22_11+"="+SEP+"\"Plus or minus 25\" (+-25) means:\n";
e+=M_22_12+"="+SEP+""+SEP+"if the taker succeeds the taker's bid, it is worth \"plus 25\",\n";
e+=M_22_13+"="+SEP+""+SEP+"if the taker looses, it is worth \"minus 25\".\n";
e+=M_22_14+"=Ace to end:\n";
e+=M_22_15+"=10 pts, if the team of the taker wins the Small in the last trick.\n";
e+=M_22_16+"=-10 pts, if the defense wins the Small in the last trick.\n";
e+=M_22_17+"=0 pts, else.\n";
e+=M_22_18+"=Handfuls:\n";
e+=M_22_19+"=Simple: 20 pts, if the declarer wins the deal; -20 pts, else.\n";
e+=M_22_20+"=Double: 30 pts, if the declarer wins the deal; -30 pts, else.\n";
e+=M_22_21+"=Triple: 40 pts, if the declarer wins the deal; -40 pts, else.\n";
e+=M_22_22+"=chelem:\n";
e+=M_22_23+"=successful declared: 400 pts.\n";
e+=M_22_24+"=successful not declared: 200 pts.\n";
e+=M_22_25+"=failed declared: -200 pts.\n";
e+=M_22_26+"=failed not declared: 0 pts.\n";
e+=M_22_27+"=Rate of bid:\n";
e+=M_22_28+"=Take: 1.\n";
e+=M_22_29+"=Guard: 2.\n";
e+=M_22_30+"=Guard without: 4.\n";
e+=M_22_31+"=Guard against: 6.\n";
e+=M_22_32+"=Success of a bid:\n";
e+=M_22_33+"=56 points needed without oudler.\n";
e+=M_22_34+"=51 points needed with 1 oudler won in the tricks of the taker.\n";
e+=M_22_35+"=41 points needed with 2 oudlers won in the tricks of the taker.\n";
e+=M_22_36+"=36 points needed with 3 oudlers won in the tricks of the taker.\n";
e+=M_22_37+"="+SEP+""+SEP+"Amount of points for a deal: 91 points.\n";
e+=M_22_38+"="+SEP+""+SEP+"Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker\n";
e+=M_22_39+"="+SEP+""+SEP+"Score of the taker without declaring points = +-25pts + Difference + Ace to end\n";
e+=M_22_40+"="+SEP+""+SEP+"Declaring = Handfuls + Slam\n";
e+=M_22_41+"="+SEP+""+SEP+"Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)\n";
e+=M_22_42+"="+SEP+""+SEP+"Full score of the taker = -3 x Full score total of a defender\n";
e+=M_22_43+"=Examples:\n";
e+=M_22_44+"=If the taker scores 43 points with 2 Oudlers in the tricks of the taker, by declaring \"Take\", neither declaring handful for all players, nor slam,\n";
e+=M_22_45+"="+SEP+""+SEP+"if no player has played the Small at the last trick,\n";
e+=M_22_46+"="+SEP+""+SEP+"then the taker wins the deal with 2 points and scores ((25+(43-41)+0)x1+0+0)x3=81 points, and each defender scores ((-25-(43-41)+0)x1+0+0)X1=-27 points.\n";
e+=M_22_47+"=If the taker scores 43 points with 1 Oudler in the tricks of the taker, by declaring \"Take\", neither declaring handful for all players, nor slam,\n";
e+=M_22_48+"="+SEP+""+SEP+"if no player has played the Small at the last trick,\n";
e+=M_22_49+"="+SEP+""+SEP+"then the taker looses the deal with 8 points and scores ((-25+(43-51)+0)x1+0+0)x3=-99 points, and each defender scores ((25-(43-51)+0)x1+0+0)X1=33 points.\n";
e+=M_22_50+"=If the taker scores 60 points avec 2 Oudlers in the tricks of the taker, by declaring \"Guard\", with a double handful, without handfuls for the defense, without slam,\n";
e+=M_22_51+"="+SEP+""+SEP+"if the taker has played the Small at the last round without loosing it,\n";
e+=M_22_52+"="+SEP+""+SEP+"then the taker wins the deal with 19 points and scores ((25+(60-41)+10)x2+30+0)x3=414 points, and each defender scores ((-25-(60-41)-10)x2-30+0)X1=-138 points.\n";
return e;
}
static String fr(){
String f="";
f+=M_22_0+"=Valeur des cartes\n";
f+=M_22_1+"=21 d'atout, Petit et Excuse: 4,5 points chacun (ces sont les 3 Bouts)\n";
f+=M_22_2+"=Rois: 4,5 points chacun\n";
f+=M_22_3+"=Dames: 3,5 points chacune\n";
f+=M_22_4+"=Cavaliers: 2,5 points chacun\n";
f+=M_22_5+"=Valets: 1,5 points chacun\n";
f+=M_22_6+"=Cartes basses et atouts du 2 au 20: 0,5 point chacun\n";
f+=M_22_7+"=Calcul de scores\n";
f+=M_22_8+"="+SEP+"Nb signifie \"Nombre\".\n";
f+=M_22_9+"="+SEP+"+- signifie \"Plus ou moins\".\n";
f+=M_22_10+"="+SEP+"pts signifie \"points\".\n";
f+=M_22_11+"="+SEP+"\"Plus ou moins 25\" (+-25) signifie que:\n";
f+=M_22_12+"="+SEP+""+SEP+"si le preneur réussit son contrat, cela vaut \"plus 25\",\n";
f+=M_22_13+"="+SEP+""+SEP+"s'il chute, cela vaut \"moins 25\".\n";
f+=M_22_14+"=Petit au bout:\n";
f+=M_22_15+"=10 pts, si l'équipe du preneur possède le Petit dans le dernier pli.\n";
f+=M_22_16+"=-10 pts, si l'équipe adverse du preneur possède le Petit dans le dernier pli.\n";
f+=M_22_17+"=0 pts, sinon.\n";
f+=M_22_18+"=Poignées d'atouts:\n";
f+=M_22_19+"=Simple: 20 pts, si l'annonceur gagne; -20 pts, sinon.\n";
f+=M_22_20+"=Double: 30 pts, si l'annonceur gagne; -30 pts, sinon.\n";
f+=M_22_21+"=Triple: 40 pts, si l'annonceur gagne; -40 pts, sinon.\n";
f+=M_22_22+"=chelem:\n";
f+=M_22_23+"=demandé réussi: 400 pts.\n";
f+=M_22_24+"=non demandé réussi: 200 pts.\n";
f+=M_22_25+"=demandé chuté: -200 pts.\n";
f+=M_22_26+"=non demandé chuté: 0 pts.\n";
f+=M_22_27+"=Coefficients de contrat:\n";
f+=M_22_28+"=Petite: 1.\n";
f+=M_22_29+"=Garde: 2.\n";
f+=M_22_30+"=Garde Sans: 4.\n";
f+=M_22_31+"=Garde Contre: 6.\n";
f+=M_22_32+"=Réussite d'un contrat:\n";
f+=M_22_33+"=56 points nécessaires sans bout.\n";
f+=M_22_34+"=51 points nécessaires avec 1 bout dans les plis du preneur.\n";
f+=M_22_35+"=41 points nécessaires avec 2 bouts dans les plis du preneur.\n";
f+=M_22_36+"=36 points nécessaires avec 3 bouts dans les plis du preneur.\n";
f+=M_22_37+"="+SEP+""+SEP+"Total des points pour une partie: 91 points.\n";
f+=M_22_38+"="+SEP+""+SEP+"Différence = Nb pts réalisés par le preneur - Nb de pts nécessaires pour gagner la partie, pour le preneur\n";
f+=M_22_39+"="+SEP+""+SEP+"Score du preneur sans points d'annonces = +-25pts + Différence + Petit au bout\n";
f+=M_22_40+"="+SEP+""+SEP+"Annonces = Poignées + Chelem\n";
f+=M_22_41+"="+SEP+""+SEP+"Score total d'un défenseur = - (Score sans points d'annonces du preneur x Coefficient de contrat + Annonces preneur - Annonces défense)\n";
f+=M_22_42+"="+SEP+""+SEP+"Score total du preneur = -3 x Score total d'un défenseur\n";
f+=M_22_43+"=Exemples:\n";
f+=M_22_44+"=Si le preneur marque 43 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poignée pour les 4 joueurs, ni de chelem,\n";
f+=M_22_45+"="+SEP+""+SEP+"si aucun des 4 joueurs n'a joué le Petit au dernier tour,\n";
f+=M_22_46+"="+SEP+""+SEP+"alors il gagne son contrat de 2 points et marque ((25+(43-41)+0)x1+0+0)x3=81 points de score, et chaque défenseur marque ((-25-(43-41)+0)x1+0+0)X1=-27 points de score.\n";
f+=M_22_47+"=Si le preneur marque 43 points avec 1 Bout dans ses plis, en demandant une petite, sans annonces de poignée pour les 4 joueurs, ni de chelem,\n";
f+=M_22_48+"="+SEP+""+SEP+"si aucun des 4 joueurs n'a joué le Petit au dernier tour,\n";
f+=M_22_49+"="+SEP+""+SEP+"alors il chute son contrat de 8 points et marque ((-25+(43-51)+0)x1+0+0)x3=-99 points de score, et chaque défenseur marque ((25-(43-51)+0)x1+0+0)X1=33 points de score.\n";
f+=M_22_50+"=Si le preneur marque 60 points avec 2 Bouts dans ses plis, en demandant une garde, avec une double poignée, sans annonces de poignée pour les défenseurs, ni de chelem pour personne,\n";
f+=M_22_51+"="+SEP+""+SEP+"si le preneur a emmené le Petit au bout sans se le faire prendre,\n";
f+=M_22_52+"="+SEP+""+SEP+"alors il gagne son contrat de 19 points et marque ((25+(60-41)+10)x2+30+0)x3=414 points de score, et chaque défenseur marque ((-25-(60-41)-10)x2-30+0)X1=-138 points de score.\n";
return f;
}
}
