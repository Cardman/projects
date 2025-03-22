package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards21{
public static final String M_21_0="_21_0";
public static final String M_21_1="_21_1";
public static final String M_21_2="_21_2";
public static final String M_21_3="_21_3";
public static final String M_21_4="_21_4";
public static final String M_21_5="_21_5";
public static final String M_21_6="_21_6";
public static final String M_21_7="_21_7";
public static final String M_21_8="_21_8";
public static final String M_21_9="_21_9";
private MessagesHelpCards21(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(10);
e.add(M_21_0,"The player who deals cards is the dealer.");
e.add(M_21_1,"From a deal to an other, the dealer changes,");
e.add(M_21_2,"that is say the next dealer is the player at the right of the previous dealer,");
e.add(M_21_3,"and the game is mixed and cut at each time.");
e.add(M_21_4,"Each player owns 18 cards and 6 cards make up a deck (called dog),");
e.add(M_21_5,"the cards are dealt 3 by 3.");
e.add(M_21_6,"The cards, hidden side, which make up the dog,");
e.add(M_21_7,"are dealt card by card at any time,");
e.add(M_21_8,"except for the first round and for the last round.");
e.add(M_21_9,"The dealer deals in the counter clock wise.");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(10);
f.add(M_21_0,"Le joueur qui distribue les cartes s'appelle le donneur.");
f.add(M_21_1,"D'une partie à l'autre, le donneur tourne,");
f.add(M_21_2,"c'est à dire que le nouveau donneur est le joueur placé à droite de l'ancien donneur,");
f.add(M_21_3,"et le jeu est mélangé et coupé à chaque fois.");
f.add(M_21_4,"Chaque joueur possède 18 cartes et 6 constituent un talon (appelé chien),");
f.add(M_21_5,"les cartes sont distribuées 3 par 3.");
f.add(M_21_6,"Les cartes, face cachée, qui constituent le chien,");
f.add(M_21_7,"sont distribuées carte par carte à n'importe quel moment,");
f.add(M_21_8,"sauf au premier tour et au dernier.");
f.add(M_21_9,"Le donneur distribue dans le sens contraire des aiguilles d'une montre.");
return f;
}
}
