package code.scripts.messages.cards;
import code.util.*;
public final class MessagesSymbolSymbolCards{
private MessagesSymbolSymbolCards(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/const_enum/en/symbol_cards.txt",en());
m.addEntry("resources_cards/const_enum/fr/symbol_cards.txt",fr());
return m;
}
static String en(){
String f="cards.consts.CardChar.EXCUSE:*\n";
f+="cards.consts.CardChar.KING:K\n";
f+="cards.consts.CardChar.QUEEN:Q\n";
f+="cards.consts.CardChar.KNIGHT:N\n";
f+="cards.consts.CardChar.JACK:J\n";
return f;
}
static String fr(){
String f="cards.consts.CardChar.EXCUSE:*\n";
f+="cards.consts.CardChar.KING:R\n";
f+="cards.consts.CardChar.QUEEN:D\n";
f+="cards.consts.CardChar.KNIGHT:C\n";
f+="cards.consts.CardChar.JACK:V\n";
return f;
}
}
