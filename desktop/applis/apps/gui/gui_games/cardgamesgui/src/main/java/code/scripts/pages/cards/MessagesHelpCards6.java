package code.scripts.pages.cards;
import code.sml.util.*;
public final class MessagesHelpCards6{
public static final String M_6_0="_6_0";
public static final String M_6_1="_6_1";
public static final String M_6_2="_6_2";
public static final String M_6_3="_6_3";
public static final String M_6_4="_6_4";
public static final String M_6_5="_6_5";
public static final String M_6_6="_6_6";
public static final String M_6_7="_6_7";
private MessagesHelpCards6(){
}
public static TranslationsFile en(){
TranslationsFile e=new TranslationsFile(8);
e.add(M_6_0,"There are two types of suit: trump and suit.");
e.add(M_6_1,"The order of cards at suit is:");
e.add(M_6_2,"Ace-10-King-Queen-Jack-9-8-7");
e.add(M_6_3,"The order of cards at trump is:");
e.add(M_6_4,"Jack-9-Ace-10-King-Queen-8-7");
e.add(M_6_5,"In this game, trumps are stronger than suit cards.");
e.add(M_6_6,"Order of cards at trump:");
e.add(M_6_7,"Order of cards at suit:");
return e;
}
public static TranslationsFile fr(){
TranslationsFile f=new TranslationsFile(8);
f.add(M_6_0,"Il existe deux types de couleur atout et couleur.");
f.add(M_6_1,"L'ordre des cartes à la couleur est:");
f.add(M_6_2,"As-10-Roi-Dame-Valet-9-8-7");
f.add(M_6_3,"L'ordre des cartes à l'atout est:");
f.add(M_6_4,"Valet-9-As-10-Roi-Dame-8-7");
f.add(M_6_5,"Dans ce jeu, les atouts sont plus fort que les cartes de couleur.");
f.add(M_6_6,"Ordre à l'atout:");
f.add(M_6_7,"Ordre à la couleur:");
return f;
}
}
