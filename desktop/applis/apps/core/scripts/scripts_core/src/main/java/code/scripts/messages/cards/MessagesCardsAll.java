package code.scripts.messages.cards;
import code.util.*;
public final class MessagesCardsAll{
private MessagesCardsAll(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addAllEntries(MessagesGuiGui.ms());
return m;
}
}
