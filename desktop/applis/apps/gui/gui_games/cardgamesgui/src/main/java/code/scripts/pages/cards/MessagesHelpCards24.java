package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards24{
public static final String M_24_0="_24_0";
public static final String M_24_1="_24_1";
public static final String M_24_2="_24_2";
public static final String M_24_3="_24_3";
public static final String M_24_4="_24_4";
public static final String M_24_5="_24_5";
public static final String M_24_6="_24_6";
public static final String M_24_7="_24_7";
private MessagesHelpCards24(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(8);
e.add(M_24_0,"There are two types of suit: trump and suit.");
e.add(M_24_1,"The order of cards at suit is:");
e.add(M_24_2,"King-Queen-Knight-Jack-10-9-8-7-6-5-4-3-2-1");
e.add(M_24_3,"The strongest trump card is the trump 21, the lowest trump card is the trump 1. L'Excuse is used as appoint(by excusing oneself). In this game, trump cards (The excuse is not regarded as a trump card here.) are stronger than the suit cards.");
e.add(M_24_4,"Trump cards are sorted by their number. (example: the trump 20 is stronger than the trump 12.)");
e.add(M_24_5,"The trump 1 is called the Small. The Small(takable) , the trump 21 (untakable, except for particular cases, see farther) and the Excuse(untakable from the first trick to the before last trick,see farther) are the 3 Oudlers.");
e.add(M_24_6,"Order of cards at trump:");
e.add(M_24_7,"Order of cards at suit:");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(8);
f.add(M_24_0,"Il existe deux types de couleur atout et couleur.");
f.add(M_24_1,"L'ordre des cartes à la couleur est:");
f.add(M_24_2,"Roi-Dame-Cavalier-Valet-10-9-8-7-6-5-4-3-2-1");
f.add(M_24_3,"L'atout le plus fort est le 21, le plus faible étant le 1. L'Excuse sert d'appoint(en s'excusant). Dans ce jeu, les atouts(l'excuse n'est pas considérée comme un atout,ici.) sont plus fort que les cartes de couleur.");
f.add(M_24_4,"Les atouts sont classés par leur numéro. (exemple: le 20 est plus fort que le 12.)");
f.add(M_24_5,"Le 1 d'atout est appelé le Petit. Le Petit(prenable) , le 21 d'atout(imprenable, sauf cas particuliers, voir plus loin) et l'Excuse(imprenable du 1er à l'avant dernier tour,voir plus loin) constituent les 3 Bouts.");
f.add(M_24_6,"Ordre à l'atout:");
f.add(M_24_7,"Ordre à la couleur:");
return f;
}
}
