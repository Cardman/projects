package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards15{
private HelpCards15(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"As for all card games, there are variants.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Here is some variants:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"ol");
Element elt7_=el(_doc,"li");
Text txt2_=tx(_doc,"Equaling is forbidden: all player cannot play group of cards of same strength as the last group.");
ad(elt7_,txt2_);
ad(elt6_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt6_,elt8_);
Element elt9_=el(_doc,"li");
Text txt3_=tx(_doc,"Equaling skips always the next player: if two groups of same strength are played, the next player is skipped, (this player cannot play at this moment, but can after).");
ad(elt9_,txt3_);
ad(elt6_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt6_,elt10_);
Element elt11_=el(_doc,"li");
Text txt4_=tx(_doc,"Equaling urge the next player to equal again: if two groups of same strength are played, the next player has to equal or pass, (this player can play only group of same strength at this moment, but can play any group after). If all cards of same strength are consecutively played in the trick, the trick ends like playing group of 2.");
ad(elt11_,txt4_);
ad(elt6_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt6_,elt12_);
Element elt13_=el(_doc,"li");
Text txt5_=tx(_doc,"All player have to play if possible. (cannot pass)");
ad(elt13_,txt5_);
ad(elt6_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt6_,elt14_);
Element elt15_=el(_doc,"li");
Text txt6_=tx(_doc,"If a player ends with a group of 2, the player can loose the deal. (If two players end with a group of 2, the last player looses.)");
ad(elt15_,txt6_);
ad(elt6_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt6_,elt16_);
Element elt17_=el(_doc,"li");
Text txt7_=tx(_doc,"If 4 cards are played at once, the order is reversed (The 2 become weak and the 3 become strong.). If 4 other cards are played at once, reversing is cancelled.");
ad(elt17_,txt7_);
ad(elt6_,elt17_);
Element elt18_=el(_doc,"br");
ad(elt6_,elt18_);
Element elt19_=el(_doc,"li");
Text txt8_=tx(_doc,"For next deals, the player at the last rank switches 2 cards with the player at the first rank and the player at the before last rank switches 1 card with the player at the second rank.");
ad(elt19_,txt8_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Text txt9_=tx(_doc,"The losers give their best cards. The winners give cards of their choice.");
ad(elt19_,txt9_);
ad(elt6_,elt19_);
Element elt21_=el(_doc,"br");
ad(elt6_,elt21_);
Element elt22_=el(_doc,"li");
Text txt10_=tx(_doc,"For next deals, the player at the last rank starts the first trick.");
ad(elt22_,txt10_);
ad(elt6_,elt22_);
Element elt23_=el(_doc,"br");
ad(elt6_,elt23_);
Element elt24_=el(_doc,"li");
Text txt11_=tx(_doc,"Other card games can be added.");
ad(elt24_,txt11_);
ad(elt6_,elt24_);
Element elt25_=el(_doc,"br");
ad(elt6_,elt25_);
Element elt26_=el(_doc,"li");
Text txt12_=tx(_doc,"Cards can be dealt in the clock wise.");
ad(elt26_,txt12_);
ad(elt6_,elt26_);
ad(elt1_,elt6_);
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
