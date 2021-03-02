package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards13{
private HelpCards13(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"President is played by groups of cards of same strength.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"A trick is a set of cards won by a player having played the strongest group of cards which is played last.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"If the player having won a trick has still cards, this player begins the next trick.(Example: the player having played a 2 begins the next trick.).");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"If the player having won a trick has finished, the next player who has cards begins the next trick.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"The player who starts a trick is a starter.");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"The player who has played the latest and strongest group of cards of a trick is the winner of trick, this player wins the trick.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"ol");
Element elt11_=el(_doc,"li");
Text txt6_=tx(_doc,"The first group of cards of a trick");
ad(elt11_,txt6_);
Element elt12_=el(_doc,"br");
ad(elt11_,elt12_);
Text txt7_=tx(_doc,"The player at the right of the dealer begins to play. This player can play any group of cards of same strength.");
ad(elt11_,txt7_);
ad(elt10_,elt11_);
Element elt13_=el(_doc,"li");
Text txt8_=tx(_doc,"Rules");
ad(elt13_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt13_,elt14_);
Element elt15_=el(_doc,"ol");
Element elt16_=el(_doc,"li");
Text txt9_=tx(_doc,"The player can pass or play stronger cards over the last played cards. Cards of a group must be of same strength.");
ad(elt16_,txt9_);
ad(elt15_,elt16_);
Element elt17_=el(_doc,"li");
Text txt10_=tx(_doc,"Besides, for playing, the player must use the same count of cards as the count of cards played by the stater.");
ad(elt17_,txt10_);
ad(elt15_,elt17_);
Element elt18_=el(_doc,"li");
Text txt11_=tx(_doc,"A player who has passed cannot play while the current trick.");
ad(elt18_,txt11_);
ad(elt15_,elt18_);
Element elt19_=el(_doc,"li");
Text txt12_=tx(_doc,"If, at the most, one player has not passed, the trick ends. The leader become:");
ad(elt19_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Element elt21_=el(_doc,"ol");
Element elt22_=el(_doc,"li");
Text txt13_=tx(_doc,"the player who has played the last group of cards, if this player has cards.");
ad(elt22_,txt13_);
ad(elt21_,elt22_);
Element elt23_=el(_doc,"li");
Text txt14_=tx(_doc,"else the next player, who has cards, after the player who has played the last group of cards.");
ad(elt23_,txt14_);
ad(elt21_,elt23_);
ad(elt19_,elt21_);
ad(elt15_,elt19_);
ad(elt13_,elt15_);
ad(elt10_,elt13_);
ad(elt1_,elt10_);
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
