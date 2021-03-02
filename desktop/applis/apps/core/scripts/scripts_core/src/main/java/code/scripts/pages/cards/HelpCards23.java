package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards23{
private HelpCards23(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The tarot is played card by card.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"A trick is a set of cards won by a player having played the strongest card. The player having won a trick can start the next trick.(Example: the player having played the trump 21 can start the next trick.).");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"The player who starts the trick is called as starter.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt3_=tx(_doc,"The player who has played the strongest card of a trick is called as the winner of trick, the player wins the trick.");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"The dominant suit is the suit of the first played card in the trick if the card is not the Excuse; the suit of the second card else.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"A trick is cut if there is at least one trump card (Excuse excluded).");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt6_=tx(_doc,"Playing the first card");
ad(elt12_,txt6_);
Element elt13_=el(_doc,"br");
ad(elt12_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt12_,elt14_);
Text txt7_=tx(_doc,"The player at the right of dealer begins playing a card of the choice of the player, once the taker has made up the dog.");
ad(elt12_,txt7_);
Element elt15_=el(_doc,"br");
ad(elt12_,elt15_);
Text txt8_=tx(_doc,"In case of slam, this is the taker who begins playing.");
ad(elt12_,txt8_);
Element elt16_=el(_doc,"br");
ad(elt12_,elt16_);
ad(elt11_,elt12_);
Element elt17_=el(_doc,"li");
Text txt9_=tx(_doc,"Rules");
ad(elt17_,txt9_);
Element elt18_=el(_doc,"br");
ad(elt17_,elt18_);
Element elt19_=el(_doc,"br");
ad(elt17_,elt19_);
Text txt10_=tx(_doc,"The Excuse can be played at any trick.");
ad(elt17_,txt10_);
Element elt20_=el(_doc,"br");
ad(elt17_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt17_,elt21_);
Element elt22_=el(_doc,"ol");
Element elt23_=el(_doc,"li");
Text txt11_=tx(_doc,"If the first card is the Excuse,");
ad(elt23_,txt11_);
Element elt24_=el(_doc,"br");
ad(elt23_,elt24_);
Text txt12_=tx(_doc,"then the next player can play any card.");
ad(elt23_,txt12_);
Element elt25_=el(_doc,"br");
ad(elt23_,elt25_);
ad(elt22_,elt23_);
Element elt26_=el(_doc,"li");
Text txt13_=tx(_doc,"If the dominant suit is the trump suit (The Excuse, here, is not a trump card.),");
ad(elt26_,txt13_);
Element elt27_=el(_doc,"br");
ad(elt26_,elt27_);
Element elt28_=el(_doc,"ol");
Element elt29_=el(_doc,"li");
Text txt14_=tx(_doc,"If the player does not own any trump card");
ad(elt29_,txt14_);
Element elt30_=el(_doc,"br");
ad(elt29_,elt30_);
Text txt15_=tx(_doc,"the player can play any card.");
ad(elt29_,txt15_);
ad(elt28_,elt29_);
Element elt31_=el(_doc,"li");
Text txt16_=tx(_doc,"If the player can play a greater trump card,");
ad(elt31_,txt16_);
Element elt32_=el(_doc,"br");
ad(elt31_,elt32_);
Text txt17_=tx(_doc,"then the player must play a greater trump card.");
ad(elt31_,txt17_);
Element elt33_=el(_doc,"br");
ad(elt31_,elt33_);
ad(elt28_,elt31_);
Element elt34_=el(_doc,"li");
Text txt18_=tx(_doc,"else, the player must play any trump card,");
ad(elt34_,txt18_);
Element elt35_=el(_doc,"br");
ad(elt34_,elt35_);
ad(elt28_,elt34_);
ad(elt26_,elt28_);
Element elt36_=el(_doc,"br");
ad(elt26_,elt36_);
ad(elt22_,elt26_);
Element elt37_=el(_doc,"li");
Text txt19_=tx(_doc,"If the dominant suit is not the trump suit,");
ad(elt37_,txt19_);
Element elt38_=el(_doc,"br");
ad(elt37_,elt38_);
Element elt39_=el(_doc,"ol");
Element elt40_=el(_doc,"li");
Text txt20_=tx(_doc,"If the player owns cards of the suit of the first card,");
ad(elt40_,txt20_);
Element elt41_=el(_doc,"br");
ad(elt40_,elt41_);
Text txt21_=tx(_doc,"then the player must follow any card of the dominant suit.");
ad(elt40_,txt21_);
Element elt42_=el(_doc,"br");
ad(elt40_,elt42_);
ad(elt39_,elt40_);
Element elt43_=el(_doc,"li");
Text txt22_=tx(_doc,"Else if the player does not own any trump card,");
ad(elt43_,txt22_);
Element elt44_=el(_doc,"br");
ad(elt43_,elt44_);
Text txt23_=tx(_doc,"the player can play any card.");
ad(elt43_,txt23_);
ad(elt39_,elt43_);
Element elt45_=el(_doc,"li");
Text txt24_=tx(_doc,"Else if no trump card is played,");
ad(elt45_,txt24_);
Element elt46_=el(_doc,"br");
ad(elt45_,elt46_);
Text txt25_=tx(_doc,"the player must play any trump card, the player trumps.");
ad(elt45_,txt25_);
ad(elt39_,elt45_);
Element elt47_=el(_doc,"li");
Text txt26_=tx(_doc,"Else if the player can play a greater card than the current strongest played trump card,");
ad(elt47_,txt26_);
Element elt48_=el(_doc,"br");
ad(elt47_,elt48_);
Text txt27_=tx(_doc,"then the player must play a greater trump card than the previous trump cards, the player owertrumps.");
ad(elt47_,txt27_);
Element elt49_=el(_doc,"br");
ad(elt47_,elt49_);
ad(elt39_,elt47_);
Element elt50_=el(_doc,"li");
Text txt28_=tx(_doc,"else the player must play any trump card, the player undertrumps.");
ad(elt50_,txt28_);
Element elt51_=el(_doc,"br");
ad(elt50_,elt51_);
ad(elt39_,elt50_);
ad(elt37_,elt39_);
ad(elt22_,elt37_);
ad(elt17_,elt22_);
ad(elt11_,elt17_);
ad(elt1_,elt11_);
Element elt52_=el(_doc,"br");
ad(elt1_,elt52_);
Element elt53_=el(_doc,"br");
ad(elt1_,elt53_);
Text txt29_=tx(_doc,"Before the last trick, the Excuse does not let win a trick. The Excuse is untakable.");
ad(elt1_,txt29_);
Element elt54_=el(_doc,"br");
ad(elt1_,elt54_);
Text txt30_=tx(_doc,"The Excuse is substituted by a low card or a trump card numbered from 2 to 20.");
ad(elt1_,txt30_);
Element elt55_=el(_doc,"br");
ad(elt1_,elt55_);
Element elt56_=el(_doc,"br");
ad(elt1_,elt56_);
Text txt31_=tx(_doc,"At the last trick, if all previous tricks are not won by the team of the player owing the Excuse (or by the taker),");
ad(elt1_,txt31_);
Element elt57_=el(_doc,"br");
ad(elt1_,elt57_);
Text txt32_=tx(_doc,"then the Excuse is takable and belongs to the team of the player who wins the last trick.");
ad(elt1_,txt32_);
Element elt58_=el(_doc,"br");
ad(elt1_,elt58_);
Element elt59_=el(_doc,"br");
ad(elt1_,elt59_);
Text txt33_=tx(_doc,"If a player with possibly partners wins tricks from the 1st to the before last trick and played the Excuse at the last trick,");
ad(elt1_,txt33_);
Element elt60_=el(_doc,"br");
ad(elt1_,elt60_);
Text txt34_=tx(_doc,"then the player achieves a slam even not declared.");
ad(elt1_,txt34_);
Element elt61_=el(_doc,"br");
ad(elt1_,elt61_);
Text txt35_=tx(_doc,"The possible partner(s) of a player, declaring a slam, have/has not to play the Excuse at the last trick.");
ad(elt1_,txt35_);
Element elt62_=el(_doc,"br");
ad(elt1_,elt62_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
static Attr at(String _name,String _value){
return CoreDocument.createAttribute(_name,_value);
}
static void at(Element _elt,CustList<Attr> _ls){
_elt.setAttributes(new NamedNodeMap(_ls));
}
static CustList<Attr> al(int _len){
return new CustList<Attr>(new CollCapacity(_len));
}
static Text tx(Document _doc,String _value){
return _doc.createEscapedTextNode(_value);
}
static Element el(Document _doc,String _value){
return _doc.createElement(_value);
}
static void ad(Element _elt,Node _value){
_elt.appendChild(_value);
}
}
