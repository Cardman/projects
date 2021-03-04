package code.scripts.messages.cards;
import code.util.*;
public final class MessagesGamesGames{
private MessagesGamesGames(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/const_enum/en/games.txt",en());
m.addEntry("resources_cards/const_enum/fr/games.txt",fr());
return m;
}
static String en(){
String f="cards.facade.enumerations.GameEnum.BELOTE:Belote\n";
f+="cards.facade.enumerations.GameEnum.TAROT:Tarot\n";
f+="cards.facade.enumerations.GameEnum.PRESIDENT:President\n";
return f;
}
static String fr(){
String f="cards.facade.enumerations.GameEnum.BELOTE:Belote\n";
f+="cards.facade.enumerations.GameEnum.TAROT:Tarot\n";
f+="cards.facade.enumerations.GameEnum.PRESIDENT:Pr&eacute;sident\n";
return f;
}
}
