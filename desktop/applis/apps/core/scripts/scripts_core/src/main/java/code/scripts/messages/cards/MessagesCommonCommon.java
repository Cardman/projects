package code.scripts.messages.cards;
import code.util.*;
public final class MessagesCommonCommon{
private MessagesCommonCommon(){}
public static StringMap<String> ms(){
StringMap<String> m = new StringMap<String>();
m.addEntry("resources_cards/const_enum/en/common.txt",en());
m.addEntry("resources_cards/const_enum/fr/common.txt",fr());
return m;
}
static String en(){
String f="cards.consts.MixCardsChoice.EACH_DEAL:at each deal\n";
f+="cards.consts.MixCardsChoice.EACH_LAUNCHING:at each launching\n";
f+="cards.consts.MixCardsChoice.ONCE_ONLY:once\n";
f+="cards.consts.MixCardsChoice.NEVER:never\n";
f+="cards.consts.Suit.TRUMP:Trump\n";
f+="cards.consts.Suit.HEART:Heart\n";
f+="cards.consts.Suit.SPADE:Spade\n";
f+="cards.consts.Suit.DIAMOND:Diamond\n";
f+="cards.consts.Suit.CLUB:Club\n";
f+="cards.consts.CardChar.EXCUSE:Excuse\n";
f+="cards.consts.CardChar.KING:King\n";
f+="cards.consts.CardChar.QUEEN:Queen\n";
f+="cards.consts.CardChar.KNIGHT:Knight\n";
f+="cards.consts.CardChar.JACK:Jack\n";
f+="cards.consts.Status.TAKER:Taker\n";
f+="cards.consts.Status.CALLED_PLAYER:Called player\n";
f+="cards.consts.Status.DEFENDER:Defender\n";
return f;
}
static String fr(){
String f="cards.consts.MixCardsChoice.EACH_DEAL:&agrave; chaque partie\n";
f+="cards.consts.MixCardsChoice.EACH_LAUNCHING:&agrave; chaque lancement\n";
f+="cards.consts.MixCardsChoice.ONCE_ONLY:une seule fois\n";
f+="cards.consts.MixCardsChoice.NEVER:jamais\n";
f+="cards.consts.Suit.TRUMP:Atout\n";
f+="cards.consts.Suit.HEART:Coeur\n";
f+="cards.consts.Suit.SPADE:Pique\n";
f+="cards.consts.Suit.DIAMOND:Carreau\n";
f+="cards.consts.Suit.CLUB:Tr&egrave;fle\n";
f+="cards.consts.CardChar.EXCUSE:Excuse\n";
f+="cards.consts.CardChar.KING:Roi\n";
f+="cards.consts.CardChar.QUEEN:Dame\n";
f+="cards.consts.CardChar.KNIGHT:Cavalier\n";
f+="cards.consts.CardChar.JACK:Valet\n";
f+="cards.consts.Status.TAKER:Preneur\n";
f+="cards.consts.Status.CALLED_PLAYER:Appel&eacute;\n";
f+="cards.consts.Status.DEFENDER:D&eacute;fenseur\n";
return f;
}
}
