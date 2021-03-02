package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards33{
private HelpCards33(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The tarot is played with 78 cards, from 3 to 5 players.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"There are different variants of game.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"The mode of classic game, which is going to be introduced, is the game bidding.");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"The goal is to score the most points as possible.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"Several variants exist. Firstly, this game has been played with 4 players.");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"As many card games, the tarot is played card by card, players play one card per round.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
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
