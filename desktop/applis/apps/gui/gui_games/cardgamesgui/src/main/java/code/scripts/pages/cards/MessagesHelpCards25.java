package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards25{
public static final String M_25_0="_25_0";
public static final String M_25_10="_25_10";
public static final String M_25_11="_25_11";
public static final String M_25_12="_25_12";
public static final String M_25_13="_25_13";
public static final String M_25_14="_25_14";
public static final String M_25_15="_25_15";
public static final String M_25_16="_25_16";
public static final String M_25_17="_25_17";
public static final String M_25_18="_25_18";
public static final String M_25_19="_25_19";
public static final String M_25_1="_25_1";
public static final String M_25_20="_25_20";
public static final String M_25_21="_25_21";
public static final String M_25_22="_25_22";
public static final String M_25_23="_25_23";
public static final String M_25_24="_25_24";
public static final String M_25_25="_25_25";
public static final String M_25_26="_25_26";
public static final String M_25_27="_25_27";
public static final String M_25_28="_25_28";
public static final String M_25_29="_25_29";
public static final String M_25_2="_25_2";
public static final String M_25_30="_25_30";
public static final String M_25_31="_25_31";
public static final String M_25_32="_25_32";
public static final String M_25_33="_25_33";
public static final String M_25_34="_25_34";
public static final String M_25_35="_25_35";
public static final String M_25_36="_25_36";
public static final String M_25_37="_25_37";
public static final String M_25_38="_25_38";
public static final String M_25_39="_25_39";
public static final String M_25_3="_25_3";
public static final String M_25_40="_25_40";
public static final String M_25_41="_25_41";
public static final String M_25_42="_25_42";
public static final String M_25_43="_25_43";
public static final String M_25_44="_25_44";
public static final String M_25_45="_25_45";
public static final String M_25_46="_25_46";
public static final String M_25_47="_25_47";
public static final String M_25_48="_25_48";
public static final String M_25_4="_25_4";
public static final String M_25_5="_25_5";
public static final String M_25_6="_25_6";
public static final String M_25_7="_25_7";
public static final String M_25_8="_25_8";
public static final String M_25_9="_25_9";
private MessagesHelpCards25(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(49);
e.add(M_25_0,"Here is changes with tarot played by 4 players:");
e.add(M_25_10,"After all players having declared,");
e.add(M_25_11,"the taker calls the king of a suit,");
e.add(M_25_12,"if the taker has the 4 kings, the taker calls the queen of a suit,");
e.add(M_25_13,"if the taker has the 4 queens and the 4 kings, the taker calls the knight of a suit,");
e.add(M_25_14,"if the taker has the 4 knights, the 4 queens and the 4 kings, the taker calls the jack of a suit.");
e.add(M_25_15,"In function by rules, the call can be done before or after the dog.");
e.add(M_25_16,"Second variant:");
e.add(M_25_17,"After all players having declared,");
e.add(M_25_18,"the taker calls a character of a suit.");
e.add(M_25_19,"In function by rules, the call can be done before or after the dog.");
e.add(M_25_1,"dealing");
e.add(M_25_20,"Third variant:");
e.add(M_25_21,"If there is no call, the taker plays against the other players.");
e.add(M_25_22,"Declaring handfuls:");
e.add(M_25_23,"simple handful: 8 trump cards (The Excuse is a trump card).");
e.add(M_25_24,"double handful: 10 trump cards (The Excuse is a trump card).");
e.add(M_25_25,"triple handful: 12 trump cards (The Excuse is a trump card).");
e.add(M_25_26,"Calculation of scores at the end of deal:");
e.add(M_25_27,"Amount of points for a deal: 91 points");
e.add(M_25_28,"Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker");
e.add(M_25_29,"Score of the taker without declaring points = +-25pts + Difference + Ace to end");
e.add(M_25_2,"If the taker plays against the 4 other players:");
e.add(M_25_30,"Declaring = Handfuls + Slam + Possible Miseres");
e.add(M_25_31,"Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)");
e.add(M_25_32,"If the taker has a partner");
e.add(M_25_33,"Full score of the taker = -2 x Full score total of a defender");
e.add(M_25_34,"Else");
e.add(M_25_35,"Full score of the taker = -4 x Full score total of a defender");
e.add(M_25_36,"Full score of the possible called player = - Full score total of a defender");
e.add(M_25_37,"If the taker score a not integer points, then the difference is rounded to the greater integer.");
e.add(M_25_38,"Examples:");
e.add(M_25_39,"If the taker scores 43,5 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
e.add(M_25_3,"each player owns 14 cards and 8 cards make up the dog,");
e.add(M_25_40,"if the taker has a partner,");
e.add(M_25_41,"if none of 5 players has played the Small at the last trick,");
e.add(M_25_42,"then the taker wins the deal by 3 points and scores ((25+(44-41)+0)x1+0+0+0)x2=56 points,");
e.add(M_25_43,"the called player scores (25+(44-41)+0)x1+0+0+0=28 points,");
e.add(M_25_44,"each defender scores ((-25-(44-41)+0)x1+0+0+0)X1=-28 points.");
e.add(M_25_45,"If the taker scores 43,5 points with 1 Oudler in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
e.add(M_25_46,"if none of 5 players has played the Small at the last trick,");
e.add(M_25_47,"if the taker has no partner,");
e.add(M_25_48,"then the taker looses the deal by 8 points and scores ((-25+(43-51)+0)x1+0+0+0)x4=-132 points, and each defender scores ((25-(43-51)+0)x1+0+0+0)X1=33 points.");
e.add(M_25_4,"cards are dealt 2 by 2.");
e.add(M_25_5,"Else:");
e.add(M_25_6,"each player owns 15 cards and 3 cards make up the dog,");
e.add(M_25_7,"cards are dealt 3 by 3.");
e.add(M_25_8,"Repartition of teams");
e.add(M_25_9,"First variant, the most played one:");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(49);
f.add(M_25_0,"Voici ce qui change par rapport au jeu à 4 joueurs:");
f.add(M_25_10,"Après que tout le monde ait parlé,");
f.add(M_25_11,"le preneur appelle le roi d'une couleur,");
f.add(M_25_12,"s'il a les 4 rois, il appelle la dame d'une couleur,");
f.add(M_25_13,"s'il a les 4 dames et les 4 rois, il appelle le cavalier d'une couleur,");
f.add(M_25_14,"s'il a les 4 cavaliers, les 4 dames et les 4 rois, il appelle le valet d'une couleur.");
f.add(M_25_15,"Selon les variantes, l'appel peut être fait après ou avant le chien.");
f.add(M_25_16,"Deuxième variante:");
f.add(M_25_17,"Après que tout le monde ait parlé,");
f.add(M_25_18,"le preneur appelle une figure d'une couleur.");
f.add(M_25_19,"Selon les variantes, l'appel peut être fait après ou avant le chien.");
f.add(M_25_1,"distribution");
f.add(M_25_20,"Troisième variante:");
f.add(M_25_21,"S'il n'y a pas d'appel, le preneur joue contre les 4 autres joueurs.");
f.add(M_25_22,"Annonces de poignée:");
f.add(M_25_23,"simple poignée: 8 atouts (y compris l'Excuse).");
f.add(M_25_24,"poignée double: 10 atouts (y compris l'Excuse).");
f.add(M_25_25,"triple poignée: 12 atouts (y compris l'Excuse).");
f.add(M_25_26,"Calcul des scores à la fin de la partie:");
f.add(M_25_27,"Total des points pour une partie: 91 points");
f.add(M_25_28,"Différence = Nb pts réalisés par le preneur - Nb de pts nécessaires pour gagner la partie, pour le preneur");
f.add(M_25_29,"Score du preneur sans points d'annonces = +-25pts + Différence + Petit au bout");
f.add(M_25_2,"Si le preneur joue contre les 4 autres joueurs:");
f.add(M_25_30,"Annonces = Poignées + Chelem + Eventuelles Misères");
f.add(M_25_31,"Score total d'un défenseur = - (Score sans points d'annonces du preneur x Coefficient de contrat + Annonces preneur - Annonces défense)");
f.add(M_25_32,"Si le preneur n'est seul en attaque");
f.add(M_25_33,"Score total du preneur = -2 x Score total d'un défenseur.");
f.add(M_25_34,"Sinon");
f.add(M_25_35,"Score total du preneur = -4 x Score total d'un défenseur.");
f.add(M_25_36,"Score total de l'éventuel appelé = - Score total d'un défenseur");
f.add(M_25_37,"En cas de réussite du contrat pour le preneur, et s'il marque un nombre non entier de points, alors sa différence est arrondie à l'entier supérieur.");
f.add(M_25_38,"Exemples:");
f.add(M_25_39,"Si le preneur marque 43,5 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poignée pour les 5 joueurs, ni de chelem,");
f.add(M_25_3,"chaque joueur possède 14 cartes et 8 constituent le chien,");
f.add(M_25_40,"si le preneur n'est pas seul en attaque,");
f.add(M_25_41,"si aucun des 5 joueurs n'a joué le Petit au dernier tour,");
f.add(M_25_42,"alors il gagne son contrat de 3 points et marque ((25+(44-41)+0)x1+0+0+0)x2=56 points de score,");
f.add(M_25_43,"l'appelé marque (25+(44-41)+0)x1+0+0+0=28 points de score,");
f.add(M_25_44,"chaque défenseur marque ((-25-(43-41)+0)x1+0+0+0)X1=-28 points de score.");
f.add(M_25_45,"Si le preneur marque 43,5 points avec 1 Bout dans ses plis, en demandant une petite, sans annonces de poignée pour les 5 joueurs, ni de chelem,");
f.add(M_25_46,"si aucun des 5 joueurs n'a joué le Petit au dernier tour,");
f.add(M_25_47,"si le preneur est seul en attaque,");
f.add(M_25_48,"alors il perd son contrat de 8 points et marque ((-25+(43-51)+0)x1+0+0+0)x4=-132 points de score, et chaque défenseur marque ((25-(43-51)+0)x1+0+0+0)X1=33 points de score.");
f.add(M_25_4,"les cartes sont distribuées 2 par 2.");
f.add(M_25_5,"Sinon:");
f.add(M_25_6,"chaque joueur possède 15 cartes et 3 constituent le chien,");
f.add(M_25_7,"les cartes sont distribuées 3 par 3.");
f.add(M_25_8,"Répartition des équipes");
f.add(M_25_9,"Première variante, la plus jouée:");
return f;
}
}
