package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards28{
private HelpCards28(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The declaring miseres are:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"ul");
Element elt4_=el(_doc,"li");
Text txt1_=tx(_doc,"Misere of trump: no trump card(The Excuse is a trump card here).");
ad(elt4_,txt1_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"Misere of points: neither Oudler nor characters.");
ad(elt5_,txt2_);
ad(elt3_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"Misere of low cards: no low card from ace to ten.");
ad(elt6_,txt3_);
ad(elt3_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"Misere of suit: only trump cards and possibly the Excuse.");
ad(elt7_,txt4_);
ad(elt3_,elt7_);
Element elt8_=el(_doc,"li");
Text txt5_=tx(_doc,"Misere of characters: no characters card.");
ad(elt8_,txt5_);
ad(elt3_,elt8_);
ad(elt1_,elt3_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt6_=tx(_doc,"Declaring misere is done as the same time than declaring handfuls, before playing the first card.");
ad(elt1_,txt6_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt7_=tx(_doc,"Their respective values are: 10, 10, 20, 30, 5.");
ad(elt1_,txt7_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt8_=tx(_doc,"At the end of deal,");
ad(elt1_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Element elt15_=el(_doc,"ul");
Element elt16_=el(_doc,"li");
Text txt9_=tx(_doc,"If the taker declares miseres, then the declaring miseres points are added to declaring points of the taker.");
ad(elt16_,txt9_);
ad(elt15_,elt16_);
Element elt17_=el(_doc,"li");
Text txt10_=tx(_doc,"If the possible partner of the taker declares miseres, then the declaring miseres points are added to declaring points of the taker.");
ad(elt17_,txt10_);
ad(elt15_,elt17_);
Element elt18_=el(_doc,"li");
Text txt11_=tx(_doc,"If a defender declares miseres, then the declaring miseres points are remove from declaring points of the taker.");
ad(elt18_,txt11_);
ad(elt15_,elt18_);
ad(elt1_,elt15_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt12_=tx(_doc,"Example: (at 4 players classic)");
ad(elt1_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt13_=tx(_doc,"&#160;If the taker scores 43 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for the 4 players, without slam,");
ad(elt1_,txt13_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt14_=tx(_doc,"&#160;&#160;by with a misere of trump declared by a defender,");
ad(elt1_,txt14_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Text txt15_=tx(_doc,"&#160;&#160;if none of 4 players has played the Small at the last trick,");
ad(elt1_,txt15_);
Element elt24_=el(_doc,"br");
ad(elt1_,elt24_);
Text txt16_=tx(_doc,"&#160;&#160;then the taker wins the deal by 2 points and scores ((25+(43-41)+0)x1+0+0-10)x3=51 points, and each defender scores ((-25-(43-41)+0)x1+0+0+10)X1=-17 points.");
ad(elt1_,txt16_);
Element elt25_=el(_doc,"br");
ad(elt1_,elt25_);
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
