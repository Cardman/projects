package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards3{
public static final String M_3_0="_3_0";
public static final String M_3_10="_3_10";
public static final String M_3_11="_3_11";
public static final String M_3_1="_3_1";
public static final String M_3_2="_3_2";
public static final String M_3_3="_3_3";
public static final String M_3_4="_3_4";
public static final String M_3_5="_3_5";
public static final String M_3_6="_3_6";
public static final String M_3_7="_3_7";
public static final String M_3_8="_3_8";
public static final String M_3_9="_3_9";
private MessagesHelpCards3(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(12);
e.add(M_3_0,"The player who deals cards is called dealer.");
e.add(M_3_10,"the cards are dealt 3 by 3, then 2 by 2 for the last one.");
e.add(M_3_11,"The dealer deals in the counter clock wise.");
e.add(M_3_1,"From a deal to an other, the dealer changes,");
e.add(M_3_2,"that is say the next dealer is the player at the right of the previous dealer,");
e.add(M_3_3,"and the cards are mixed and cut at each time.");
e.add(M_3_4,"At the classic belote,");
e.add(M_3_5,"each player owns, at the beginning, 5 cards. The remaining cards (equals to 12) make up a deck,");
e.add(M_3_6,"the cards are dealt 3 by 3, then 2 by 2.");
e.add(M_3_7,"Once all players have five cards, the \"top\" card of the deck must be shown.");
e.add(M_3_8,"At the coinche,");
e.add(M_3_9,"each player owns 8 cards,");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(12);
f.add(M_3_0,"Le joueur qui distribue les cartes s'appelle le donneur.");
f.add(M_3_10,"les cartes sont distribuées 3 par 3, puis 2 par 2 pour les dernières.");
f.add(M_3_11,"Le donneur distribue dans le sens contraire des aiguilles d'une montre.");
f.add(M_3_1,"D'une partie à l'autre, le donneur tourne,");
f.add(M_3_2,"c'est à dire que le nouveau donneur est le joueur placé à droite de l'ancien donneur,");
f.add(M_3_3,"et le jeu est mélangé et coupé à chaque fois.");
f.add(M_3_4,"A la belote classique,");
f.add(M_3_5,"chaque joueur possède, au départ, 5 cartes. Le reste des cartes (en l'occurence 12) constituent un talon,");
f.add(M_3_6,"les cartes sont distribuées 3 par 3, puis 2 par 2.");
f.add(M_3_7,"Une fois que tous les joueurs ont cinq cartes, la carte au-dessus du talon doit être retournée.");
f.add(M_3_8,"A la belote coinchée,");
f.add(M_3_9,"chaque joueur possède 8 cartes,");
return f;
}
}
