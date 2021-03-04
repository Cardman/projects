package code.scripts.messages.cards;
import code.util.*;
public final class MessagesGuiGui{
private MessagesGuiGui(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/const_enum/en/gui.txt",en());
m.addEntry("resources_cards/const_enum/fr/gui.txt",fr());
return m;
}
static String en(){
String f="cards.enumerations.Launching.WELCOME:Welcome at card games\n";
return f;
}
static String fr(){
String f="cards.enumerations.Launching.WELCOME:Bienvenue dans les jeux de cartes\n";
return f;
}
}
