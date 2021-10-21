package code.scripts.messages.cards;
import code.util.*;
public final class MessagesSymbolSymbolCards{
private static final String CARD_CHAR_M_C_EXCUSE="cards.consts.CardChar.EXCUSE";
private static final String CARD_CHAR_M_C_JACK="cards.consts.CardChar.JACK";
private static final String CARD_CHAR_M_C_KING="cards.consts.CardChar.KING";
private static final String CARD_CHAR_M_C_KNIGHT="cards.consts.CardChar.KNIGHT";
private static final String CARD_CHAR_M_C_QUEEN="cards.consts.CardChar.QUEEN";
private static final String C_P_9_0="*\n";
private static final String C_P_9_1="K\n";
private static final String C_P_9_2="Q\n";
private static final String C_P_9_3="N\n";
private static final String C_P_9_4="J\n";
private static final String C_P_9_5="*\n";
private static final String C_P_9_6="R\n";
private static final String C_P_9_7="D\n";
private static final String C_P_9_8="C\n";
private static final String C_P_9_9="V\n";
private static final char SEP=':';
private MessagesSymbolSymbolCards(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/const_enum/en/symbol_cards.txt",en());
m.addEntry("resources_cards/const_enum/fr/symbol_cards.txt",fr());
return m;
}
static String en(){
String f=CARD_CHAR_M_C_EXCUSE+SEP+C_P_9_0;
f+=CARD_CHAR_M_C_KING+SEP+C_P_9_1;
f+=CARD_CHAR_M_C_QUEEN+SEP+C_P_9_2;
f+=CARD_CHAR_M_C_KNIGHT+SEP+C_P_9_3;
f+=CARD_CHAR_M_C_JACK+SEP+C_P_9_4;
return f;
}
static String fr(){
String f=CARD_CHAR_M_C_EXCUSE+SEP+C_P_9_5;
f+=CARD_CHAR_M_C_KING+SEP+C_P_9_6;
f+=CARD_CHAR_M_C_QUEEN+SEP+C_P_9_7;
f+=CARD_CHAR_M_C_KNIGHT+SEP+C_P_9_8;
f+=CARD_CHAR_M_C_JACK+SEP+C_P_9_9;
return f;
}
}
