package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards28{
public static final String M_28_0="_28_0";
public static final String M_28_10="_28_10";
public static final String M_28_11="_28_11";
public static final String M_28_12="_28_12";
public static final String M_28_13="_28_13";
public static final String M_28_14="_28_14";
public static final String M_28_15="_28_15";
public static final String M_28_1="_28_1";
public static final String M_28_2="_28_2";
public static final String M_28_3="_28_3";
public static final String M_28_4="_28_4";
public static final String M_28_5="_28_5";
public static final String M_28_6="_28_6";
public static final String M_28_7="_28_7";
public static final String M_28_8="_28_8";
public static final String M_28_9="_28_9";
private MessagesHelpCards28(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(16);
e.add(M_28_0,"The declaring miseres are:");
e.add(M_28_10,"If the possible partner of the taker declares miseres, then the declaring miseres points are added to declaring points of the taker.");
e.add(M_28_11,"If a defender declares miseres, then the declaring miseres points are remove from declaring points of the taker.");
e.add(M_28_12,"Example: (at 4 players classic)");
e.add(M_28_13,"If the taker scores 43 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for the 4 players, without slam, by with a misere of trump declared by a defender,");
e.add(M_28_14,"if none of 4 players has played the Small at the last trick,");
e.add(M_28_15,"then the taker wins the deal by 2 points and scores ((25+(43-41)+0)x1+0+0-10)x3=51 points, and each defender scores ((-25-(43-41)+0)x1+0+0+10)X1=-17 points.");
e.add(M_28_1,"Misere of trump: no trump card(The Excuse is a trump card here).");
e.add(M_28_2,"Misere of points: neither Oudler nor characters.");
e.add(M_28_3,"Misere of low cards: no low card from ace to ten.");
e.add(M_28_4,"Misere of suit: only trump cards and possibly the Excuse.");
e.add(M_28_5,"Misere of characters: no characters card.");
e.add(M_28_6,"Declaring misere is done as the same time than declaring handfuls, before playing the first card.");
e.add(M_28_7,"Their respective values are: 10, 10, 20, 30, 5.");
e.add(M_28_8,"At the end of deal,");
e.add(M_28_9,"If the taker declares miseres, then the declaring miseres points are added to declaring points of the taker.");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(16);
f.add(M_28_0,"Les annonces de misères sont:");
f.add(M_28_10,"Si l'éventuel partenaire du preneur annonce des misères, alors ses annonces de misères sont comptées positivement au preneur, dans les annonces du preneur.");
f.add(M_28_11,"Si un défenseur annonce des misères, alors ses annonces de misères sont comptées négativement au preneur, dans les annonces du preneur.");
f.add(M_28_12,"Exemple: (à 4 joueurs classique)");
f.add(M_28_13,"Si le preneur marque 43 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poignée pour les 4 joueurs, ni de chelem, mais avec une misère d'atout en défense,");
f.add(M_28_14,"si aucun des 4 joueurs n'a joué le Petit au dernier tour,");
f.add(M_28_15,"alors il gagne son contrat de 2 points et marque ((25+(43-41)+0)x1+0+0-10)x3=51 points de score, et chaque défenseur marque ((-25-(43-41)+0)x1+0+0+10)X1=-17 points de score.");
f.add(M_28_1,"Misère d'atout: aucun atout dans la main (Même pas l'Excuse).");
f.add(M_28_2,"Misère de tête: aucun Bout ni figure dans la main.");
f.add(M_28_3,"Misère de cartes basses: auncune carte à la couleur allant de l'as au dix dans la main.");
f.add(M_28_4,"Misère de couleur: que des atouts et éventuellement l'Excuse dans la main.");
f.add(M_28_5,"Misère de figures: pas de figures dans la main.");
f.add(M_28_6,"On les annonce au même moment que les poignées, avant de jouer la première carte.");
f.add(M_28_7,"Leurs valeurs respectives sont: 10, 10, 20, 30, 5.");
f.add(M_28_8,"A la fin de la partie,");
f.add(M_28_9,"Si le preneur annonce des misères, alors ses annonces de misères lui sont comptées positivement, dans les annonces du preneur.");
return f;
}
}
