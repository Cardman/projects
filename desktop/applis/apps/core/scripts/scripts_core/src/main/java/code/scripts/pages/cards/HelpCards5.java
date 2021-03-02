package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards5{
private HelpCards5(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Belote is played card by card.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"A trick is a set of cards won by a player having played the strongest card. The player having won a trick begins the next trick.(Example: the player having played the Jack of the trump suit begins the next trick.).");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"The player who starts a trick is a starter.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt3_=tx(_doc,"The player who has played the strongest card of a trick is the winner of trick, this player wins the trick.");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"The demanded suit is the suit of the first played card of a trick.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"A trick is trumped if there is at lest one trump.");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt6_=tx(_doc,"The first card of a trick");
ad(elt12_,txt6_);
Element elt13_=el(_doc,"br");
ad(elt12_,elt13_);
Text txt7_=tx(_doc,"The player at the right of the dealer begins to play. This player can play any card.");
ad(elt12_,txt7_);
ad(elt11_,elt12_);
Element elt14_=el(_doc,"li");
Text txt8_=tx(_doc,"Rules");
ad(elt14_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt14_,elt15_);
Element elt16_=el(_doc,"ol");
Element elt17_=el(_doc,"li");
Text txt9_=tx(_doc,"If the dominant suit is the trump suit,");
ad(elt17_,txt9_);
Element elt18_=el(_doc,"br");
ad(elt17_,elt18_);
Element elt19_=el(_doc,"ol");
Element elt20_=el(_doc,"li");
Text txt10_=tx(_doc,"If the player does not own any trump card,");
ad(elt20_,txt10_);
Element elt21_=el(_doc,"br");
ad(elt20_,elt21_);
Text txt11_=tx(_doc,"one can play any card.");
ad(elt20_,txt11_);
ad(elt19_,elt20_);
Element elt22_=el(_doc,"li");
Text txt12_=tx(_doc,"Else If this last one can overtrump (play a greater trump),");
ad(elt22_,txt12_);
Element elt23_=el(_doc,"br");
ad(elt22_,elt23_);
Text txt13_=tx(_doc,"then one must overtrump,");
ad(elt22_,txt13_);
Element elt24_=el(_doc,"br");
ad(elt22_,elt24_);
ad(elt19_,elt22_);
Element elt25_=el(_doc,"li");
Text txt14_=tx(_doc,"else, one must follow a trump card,");
ad(elt25_,txt14_);
Element elt26_=el(_doc,"br");
ad(elt25_,elt26_);
ad(elt19_,elt25_);
Element elt27_=el(_doc,"li");
Text txt15_=tx(_doc,"else one can play any card.");
ad(elt27_,txt15_);
Element elt28_=el(_doc,"br");
ad(elt27_,elt28_);
ad(elt19_,elt27_);
ad(elt17_,elt19_);
ad(elt16_,elt17_);
Element elt29_=el(_doc,"li");
Text txt16_=tx(_doc,"If the dominant suit is not the trump suit,");
ad(elt29_,txt16_);
Element elt30_=el(_doc,"br");
ad(elt29_,elt30_);
Element elt31_=el(_doc,"ol");
Element elt32_=el(_doc,"li");
Text txt17_=tx(_doc,"If the player owns cards of the dominant suit,");
ad(elt32_,txt17_);
Element elt33_=el(_doc,"br");
ad(elt32_,elt33_);
Text txt18_=tx(_doc,"then this one must follow the suit without playing a stronger card.");
ad(elt32_,txt18_);
Element elt34_=el(_doc,"br");
ad(elt32_,elt34_);
ad(elt31_,elt32_);
Element elt35_=el(_doc,"li");
Text txt19_=tx(_doc,"Else, if one''s partner leads the trick,");
ad(elt35_,txt19_);
Element elt36_=el(_doc,"br");
ad(elt35_,elt36_);
Text txt20_=tx(_doc,"then one can play any card.");
ad(elt35_,txt20_);
Element elt37_=el(_doc,"br");
ad(elt35_,elt37_);
ad(elt31_,elt35_);
Element elt38_=el(_doc,"li");
Text txt21_=tx(_doc,"Else if the player does not own any trump card, one can play any card.");
ad(elt38_,txt21_);
Element elt39_=el(_doc,"br");
ad(elt38_,elt39_);
ad(elt31_,elt38_);
Element elt40_=el(_doc,"li");
Text txt22_=tx(_doc,"Else If the trick is not trumped,");
ad(elt40_,txt22_);
Element elt41_=el(_doc,"br");
ad(elt40_,elt41_);
Text txt23_=tx(_doc,"then one must use a trump card of one''s choice, (the player trumps).");
ad(elt40_,txt23_);
Element elt42_=el(_doc,"br");
ad(elt40_,elt42_);
ad(elt31_,elt40_);
Element elt43_=el(_doc,"li");
Text txt24_=tx(_doc,"Else if the player can play a trump card stronger than the maximal played trump card,");
ad(elt43_,txt24_);
Element elt44_=el(_doc,"br");
ad(elt43_,elt44_);
Text txt25_=tx(_doc,"then one must use a trump card that one can play, (the player overtrumps).");
ad(elt43_,txt25_);
Element elt45_=el(_doc,"br");
ad(elt43_,elt45_);
ad(elt31_,elt43_);
Element elt46_=el(_doc,"li");
Text txt26_=tx(_doc,"Else one can play any card.");
ad(elt46_,txt26_);
Element elt47_=el(_doc,"br");
ad(elt46_,elt47_);
ad(elt31_,elt46_);
ad(elt29_,elt31_);
ad(elt16_,elt29_);
ad(elt14_,elt16_);
ad(elt11_,elt14_);
ad(elt1_,elt11_);
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
