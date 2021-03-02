package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards2{
private HelpCards2(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Bids fix the deal.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Each player speaks, in counter clock wise,");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"by beginning by the player at the right of the dealer.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"A player has to either pass or bid.");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt4_=tx(_doc,"At the classic belote, one processes by two rounds:");
ad(elt1_,txt4_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt5_=tx(_doc,"At the first round:");
ad(elt12_,txt5_);
Element elt13_=el(_doc,"br");
ad(elt12_,elt13_);
Text txt6_=tx(_doc,"&#160;If a player bids, then one''s takes the \"top\" card and bidding is stopped in order to go to play cards.");
ad(elt12_,txt6_);
ad(elt11_,elt12_);
Element elt14_=el(_doc,"li");
Text txt7_=tx(_doc,"At the second round:");
ad(elt14_,txt7_);
Element elt15_=el(_doc,"br");
ad(elt14_,elt15_);
Text txt8_=tx(_doc,"&#160;If a player bids, then one''s declares a suit which is going to lead the other one during this deal.");
ad(elt14_,txt8_);
Element elt16_=el(_doc,"br");
ad(elt14_,elt16_);
Text txt9_=tx(_doc,"&#160;In this case, one''s cannot declare the suit of the \"top\" card.");
ad(elt14_,txt9_);
Element elt17_=el(_doc,"br");
ad(elt14_,elt17_);
Text txt10_=tx(_doc,"&#160;One''s takes the \"top\" card and bidding is stopped in order to go to play cards.");
ad(elt14_,txt10_);
Element elt18_=el(_doc,"br");
ad(elt14_,elt18_);
ad(elt11_,elt14_);
ad(elt1_,elt11_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt11_=tx(_doc,"If someone takes, then the dealer deals two cards to the taker and three cards to the other players by beginning by the starter (the player who begins).");
ad(elt1_,txt11_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt12_=tx(_doc,"Meaning: the second round is played only if everybody has pass.");
ad(elt1_,txt12_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Text txt13_=tx(_doc,"At the coinche, one processes by the following way:");
ad(elt1_,txt13_);
Element elt24_=el(_doc,"br");
ad(elt1_,elt24_);
Element elt25_=el(_doc,"br");
ad(elt1_,elt25_);
Element elt26_=el(_doc,"ol");
Element elt27_=el(_doc,"li");
Text txt14_=tx(_doc,"A player can pass or declare a suit with a number of points to score.");
ad(elt27_,txt14_);
Element elt28_=el(_doc,"br");
ad(elt27_,elt28_);
Text txt15_=tx(_doc,"&#160;The declared points vary by ten to ten from 80 to 160.");
ad(elt27_,txt15_);
Element elt29_=el(_doc,"br");
ad(elt27_,elt29_);
Text txt16_=tx(_doc,"&#160;Declaring 162 points means the player must make all tricks and begins playing");
ad(elt27_,txt16_);
ad(elt26_,elt27_);
Element elt30_=el(_doc,"li");
Text txt17_=tx(_doc,"For over bidding, il faut annoncer une couleur avec dix points de plus.");
ad(elt30_,txt17_);
ad(elt26_,elt30_);
Element elt31_=el(_doc,"li");
Text txt18_=tx(_doc,"Even by passing while a round, bidding a suit with points is possible while a latter round.");
ad(elt31_,txt18_);
ad(elt26_,elt31_);
Element elt32_=el(_doc,"li");
Text txt19_=tx(_doc,"If three consecutive players pass, then bidding is stopped in order to go to play cards.");
ad(elt32_,txt19_);
ad(elt26_,elt32_);
ad(elt1_,elt26_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Text txt20_=tx(_doc,"Meaning: if a player declares 162 points for a suit, then bidding is stopped, as other players must pass.");
ad(elt1_,txt20_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Element elt35_=el(_doc,"br");
ad(elt1_,elt35_);
Text txt21_=tx(_doc,"The taker plays with the player on the opposite side against the two other players.");
ad(elt1_,txt21_);
Element elt36_=el(_doc,"br");
ad(elt1_,elt36_);
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
