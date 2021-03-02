package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards21{
private HelpCards21(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The player who deals cards is the dealer.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"From a deal to an other, the dealer changes,");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"that is say the next dealer is the player at the right of the previous dealer,");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"and the game is mixed and cut at each time.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"Each player owns 18 cards and 6 cards make up a deck (called dog),");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"the cards are dealt 3 by 3.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt6_=tx(_doc,"The cards, hidden side, which make up the dog,");
ad(elt1_,txt6_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt7_=tx(_doc,"are dealt card by card at any time,");
ad(elt1_,txt7_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt8_=tx(_doc,"except for the first round and for the last round.");
ad(elt1_,txt8_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt9_=tx(_doc,"The dealer deals in the counter clock wise.");
ad(elt1_,txt9_);
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
