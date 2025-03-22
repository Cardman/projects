package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards12{
public static final String M_12_0="_12_0";
public static final String M_12_1="_12_1";
public static final String M_12_2="_12_2";
public static final String M_12_3="_12_3";
public static final String M_12_4="_12_4";
public static final String M_12_5="_12_5";
private MessagesHelpCards12(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(6);
e.add(M_12_0,"The player who deals cards is called dealer.");
e.add(M_12_1,"From a deal to an other, the dealer changes,");
e.add(M_12_2,"that is say the next dealer is the player at the right of the previous dealer,");
e.add(M_12_3,"and the cards are mixed at each time.");
e.add(M_12_4,"The cards are dealt one by one.");
e.add(M_12_5,"It is never if some players have one more card than other players.");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(6);
f.add(M_12_0,"Le joueur qui distribue les cartes est appelé le donneur.");
f.add(M_12_1,"D'une donne à l'autre, the donneur change,");
f.add(M_12_2,"c'est-à-dire que le donneur suivant est le joueur à droite du donneur précédent,");
f.add(M_12_3,"et les cartes sont battues à chaque fois.");
f.add(M_12_4,"Les cartes sont distribuées une par une.");
f.add(M_12_5,"Ce n'est pas grave si des joueurs ont une carte de plus que d'autres.");
return f;
}
}
