package code.scripts.messages.cards;
import code.util.*;
public final class MessagesGuiGui{
private static final String C_P_7_0="Welcome at card games\n";
private static final String C_P_7_1="Bienvenue dans les jeux de cartes\n";
private static final String LAUNCHING_M_C_WELCOME="cards.enumerations.Launching.WELCOME";
private static final char SEP=':';
private MessagesGuiGui(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/const_enum/en/gui.txt",en());
m.addEntry("resources_cards/const_enum/fr/gui.txt",fr());
return m;
}
static String en(){
String f=LAUNCHING_M_C_WELCOME+SEP+C_P_7_0;
return f;
}
static String fr(){
String f=LAUNCHING_M_C_WELCOME+SEP+C_P_7_1;
return f;
}
}
