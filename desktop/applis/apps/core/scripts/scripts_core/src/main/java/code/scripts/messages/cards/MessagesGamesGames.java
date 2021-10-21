package code.scripts.messages.cards;
import code.util.*;
public final class MessagesGamesGames{
private static final String C_P_6_0="Belote\n";
private static final String C_P_6_1="Tarot\n";
private static final String C_P_6_2="President\n";
private static final String C_P_6_3="Belote\n";
private static final String C_P_6_4="Tarot\n";
private static final String C_P_6_5="Pr&eacute;sident\n";
private static final String GAME_ENUM_M_C_BELOTE="cards.facade.enumerations.GameEnum.BELOTE";
private static final String GAME_ENUM_M_C_PRESIDENT="cards.facade.enumerations.GameEnum.PRESIDENT";
private static final String GAME_ENUM_M_C_TAROT="cards.facade.enumerations.GameEnum.TAROT";
private static final char SEP=':';
private MessagesGamesGames(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/const_enum/en/games.txt",en());
m.addEntry("resources_cards/const_enum/fr/games.txt",fr());
return m;
}
static String en(){
String f=GAME_ENUM_M_C_BELOTE+SEP+C_P_6_0;
f+=GAME_ENUM_M_C_TAROT+SEP+C_P_6_1;
f+=GAME_ENUM_M_C_PRESIDENT+SEP+C_P_6_2;
return f;
}
static String fr(){
String f=GAME_ENUM_M_C_BELOTE+SEP+C_P_6_3;
f+=GAME_ENUM_M_C_TAROT+SEP+C_P_6_4;
f+=GAME_ENUM_M_C_PRESIDENT+SEP+C_P_6_5;
return f;
}
}
