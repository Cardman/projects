package code.scripts.messages.cards;
import code.util.*;
public final class MessagesCardsAll{
private MessagesCardsAll(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addAllEntries(MessagesCommonCommon.ms());
m.addAllEntries(MessagesSymbolSymbolCards.ms());
m.addAllEntries(MessagesBeloteBelote.ms());
m.addAllEntries(MessagesPresidentPresident.ms());
m.addAllEntries(MessagesTarotTarot.ms());
m.addAllEntries(MessagesGamesGames.ms());
m.addAllEntries(MessagesGuiGui.ms());
return m;
}
}
