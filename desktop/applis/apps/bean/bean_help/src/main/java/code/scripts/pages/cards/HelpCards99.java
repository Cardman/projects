package code.scripts.pages.cards;
final class HelpCards99 extends HelpCardsCommon{
private HelpCards99(){}
static String en(){
String e="";
e+=M_28_0+"=The declaring miseres are:\n";
e+=M_28_1+"=Misere of trump: no trump card(The Excuse is a trump card here).\n";
e+=M_28_2+"=Misere of points: neither Oudler nor characters.\n";
e+=M_28_3+"=Misere of low cards: no low card from ace to ten.\n";
e+=M_28_4+"=Misere of suit: only trump cards and possibly the Excuse.\n";
e+=M_28_5+"=Misere of characters: no characters card.\n";
e+=M_28_6+"=Declaring misere is done as the same time than declaring handfuls, before playing the first card.\n";
e+=M_28_7+"=Their respective values are: 10, 10, 20, 30, 5.\n";
e+=M_28_8+"=At the end of deal,\n";
e+=M_28_9+"=If the taker declares miseres, then the declaring miseres points are added to declaring points of the taker.\n";
e+=M_28_10+"=If the possible partner of the taker declares miseres, then the declaring miseres points are added to declaring points of the taker.\n";
e+=M_28_11+"=If a defender declares miseres, then the declaring miseres points are remove from declaring points of the taker.\n";
e+=M_28_12+"=Example: (at 4 players classic)\n";
e+=M_28_13+"=&#160;If the taker scores 43 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for the 4 players, without slam, by with a misere of trump declared by a defender,\n";
e+=M_28_14+"=&#160;&#160;if none of 4 players has played the Small at the last trick,\n";
e+=M_28_15+"=&#160;&#160;then the taker wins the deal by 2 points and scores ((25+(43-41)+0)x1+0+0-10)x3=51 points, and each defender scores ((-25-(43-41)+0)x1+0+0+10)X1=-17 points.\n";
return e;
}
static String fr(){
String f="";
f+=M_28_0+"=Les annonces de mis&#232;res sont:\n";
f+=M_28_1+"=Mis&#232;re d''atout: aucun atout dans la main (M&#234;me pas l''Excuse).\n";
f+=M_28_2+"=Mis&#232;re de t&#234;te: aucun Bout ni figure dans la main.\n";
f+=M_28_3+"=Mis&#232;re de cartes basses: auncune carte &#224; la couleur allant de l''as au dix dans la main.\n";
f+=M_28_4+"=Mis&#232;re de couleur: que des atouts et &#233;ventuellement l''Excuse dans la main.\n";
f+=M_28_5+"=Mis&#232;re de figures: pas de figures dans la main.\n";
f+=M_28_6+"=On les annonce au m&#234;me moment que les poign&#233;es, avant de jouer la premi&#232;re carte.\n";
f+=M_28_7+"=Leurs valeurs respectives sont: 10, 10, 20, 30, 5.\n";
f+=M_28_8+"=A la fin de la partie,\n";
f+=M_28_9+"=Si le preneur annonce des mis&#232;res, alors ses annonces de mis&#232;res lui sont compt&#233;es positivement, dans les annonces du preneur.\n";
f+=M_28_10+"=Si l''&#233;ventuel partenaire du preneur annonce des mis&#232;res, alors ses annonces de mis&#232;res sont compt&#233;es positivement au preneur, dans les annonces du preneur.\n";
f+=M_28_11+"=Si un d&#233;fenseur annonce des mis&#232;res, alors ses annonces de mis&#232;res sont compt&#233;es n&#233;gativement au preneur, dans les annonces du preneur.\n";
f+=M_28_12+"=Exemple: (&#224; 4 joueurs classique)\n";
f+=M_28_13+"=&#160;Si le preneur marque 43 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 4 joueurs, ni de chelem, mais avec une mis&#232;re d''atout en d&#233;fense,\n";
f+=M_28_14+"=&#160;&#160;si aucun des 4 joueurs n''a jou&#233; le Petit au dernier tour,\n";
f+=M_28_15+"=&#160;&#160;alors il gagne son contrat de 2 points et marque ((25+(43-41)+0)x1+0+0-10)x3=51 points de score, et chaque d&#233;fenseur marque ((-25-(43-41)+0)x1+0+0+10)X1=-17 points de score.\n";
return f;
}
}
