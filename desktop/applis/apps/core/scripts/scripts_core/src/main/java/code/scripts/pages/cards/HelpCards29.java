package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards29{
private HelpCards29(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Here is changes with tarot played by 4 players from the classic way:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt1_=tx(_doc,"Repartition of teams");
ad(elt5_,txt1_);
Element elt6_=el(_doc,"br");
ad(elt5_,elt6_);
Element elt7_=el(_doc,"ol");
Element elt8_=el(_doc,"li");
Text txt2_=tx(_doc,"First variant, the most played one:");
ad(elt8_,txt2_);
Element elt9_=el(_doc,"br");
ad(elt8_,elt9_);
Text txt3_=tx(_doc,"&#160;&#160;&#160;After all players having declared,");
ad(elt8_,txt3_);
Element elt10_=el(_doc,"br");
ad(elt8_,elt10_);
Text txt4_=tx(_doc,"&#160;&#160;&#160;&#160;the taker calls the king of a suit,");
ad(elt8_,txt4_);
Element elt11_=el(_doc,"br");
ad(elt8_,elt11_);
Text txt5_=tx(_doc,"&#160;&#160;&#160;&#160;if the taker has the 4 kings, the taker calls the queen of a suit,");
ad(elt8_,txt5_);
Element elt12_=el(_doc,"br");
ad(elt8_,elt12_);
Text txt6_=tx(_doc,"&#160;&#160;&#160;&#160;if the taker has the 4 queens and the 4 kings, the taker calls the knight of a suit,");
ad(elt8_,txt6_);
Element elt13_=el(_doc,"br");
ad(elt8_,elt13_);
Text txt7_=tx(_doc,"&#160;&#160;&#160;&#160;if the taker has the 4 knights, the 4 queens and the 4 kings, the taker calls the jack of a suit,");
ad(elt8_,txt7_);
Element elt14_=el(_doc,"br");
ad(elt8_,elt14_);
Text txt8_=tx(_doc,"&#160;&#160;&#160;&#160;if the taker has the 16 characters, the taker calls an Oudler.");
ad(elt8_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt8_,elt15_);
Text txt9_=tx(_doc,"&#160;&#160;&#160;&#160;In function by rules, the call can be done before or after the dog.");
ad(elt8_,txt9_);
Element elt16_=el(_doc,"br");
ad(elt8_,elt16_);
ad(elt7_,elt8_);
Element elt17_=el(_doc,"li");
Text txt10_=tx(_doc,"Second variant:");
ad(elt17_,txt10_);
Element elt18_=el(_doc,"br");
ad(elt17_,elt18_);
Text txt11_=tx(_doc,"&#160;&#160;&#160;After all players having declared,");
ad(elt17_,txt11_);
Element elt19_=el(_doc,"br");
ad(elt17_,elt19_);
Text txt12_=tx(_doc,"&#160;&#160;&#160;&#160;the taker calls a character of a suit or an Oudler.");
ad(elt17_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt17_,elt20_);
Text txt13_=tx(_doc,"&#160;&#160;&#160;&#160;In function by rules, the call can be done before or after the dog.");
ad(elt17_,txt13_);
Element elt21_=el(_doc,"br");
ad(elt17_,elt21_);
ad(elt7_,elt17_);
Element elt22_=el(_doc,"li");
Text txt14_=tx(_doc,"Third variant:");
ad(elt22_,txt14_);
Element elt23_=el(_doc,"br");
ad(elt22_,elt23_);
Text txt15_=tx(_doc,"&#160;&#160;&#160;After all players having declared,");
ad(elt22_,txt15_);
Element elt24_=el(_doc,"br");
ad(elt22_,elt24_);
Text txt16_=tx(_doc,"&#160;&#160;&#160;&#160;the taker is the partner of the player in the opposite side.");
ad(elt22_,txt16_);
Element elt25_=el(_doc,"br");
ad(elt22_,elt25_);
ad(elt7_,elt22_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
Element elt26_=el(_doc,"li");
Text txt17_=tx(_doc,"Calculation of scores at the end of deal:");
ad(elt26_,txt17_);
Element elt27_=el(_doc,"br");
ad(elt26_,elt27_);
Text txt18_=tx(_doc,"&#160;&#160;Amount of points for a deal: 91 points");
ad(elt26_,txt18_);
Element elt28_=el(_doc,"br");
ad(elt26_,elt28_);
Text txt19_=tx(_doc,"&#160;&#160;Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker");
ad(elt26_,txt19_);
Element elt29_=el(_doc,"br");
ad(elt26_,elt29_);
Text txt20_=tx(_doc,"&#160;&#160;Score of the taker without declaring points = +-25pts + Difference + Ace to end");
ad(elt26_,txt20_);
Element elt30_=el(_doc,"br");
ad(elt26_,elt30_);
Text txt21_=tx(_doc,"&#160;&#160;Declaring = Handfuls + Slam + Possible Miseres");
ad(elt26_,txt21_);
Element elt31_=el(_doc,"br");
ad(elt26_,elt31_);
Text txt22_=tx(_doc,"&#160;&#160;Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)");
ad(elt26_,txt22_);
Element elt32_=el(_doc,"br");
ad(elt26_,elt32_);
Text txt23_=tx(_doc,"&#160;&#160;If the taker has a partner");
ad(elt26_,txt23_);
Element elt33_=el(_doc,"br");
ad(elt26_,elt33_);
Text txt24_=tx(_doc,"&#160;&#160;&#160;Full score of the taker = -1,5 x Full score total of a defender");
ad(elt26_,txt24_);
Element elt34_=el(_doc,"br");
ad(elt26_,elt34_);
Text txt25_=tx(_doc,"&#160;&#160;Else");
ad(elt26_,txt25_);
Element elt35_=el(_doc,"br");
ad(elt26_,elt35_);
Text txt26_=tx(_doc,"&#160;&#160;&#160;Full score of the taker = -3 x Full score total of a defender");
ad(elt26_,txt26_);
Element elt36_=el(_doc,"br");
ad(elt26_,elt36_);
Text txt27_=tx(_doc,"&#160;&#160;Full score of the possible called player = -0,5 Full score total of a defender");
ad(elt26_,txt27_);
Element elt37_=el(_doc,"br");
ad(elt26_,elt37_);
ad(elt4_,elt26_);
ad(elt1_,elt4_);
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
