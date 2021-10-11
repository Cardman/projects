package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards54{
private HelpCards54(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"\"Help for deal\" Shortcut F2");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Acting the sub menu \"Help for deal\" lets have the set of played cards, the set of possible repartition of cards for the other players, and the set of cards surely owned by the other players, in function by rules of games, for games with different players.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt8_=tx(_doc,"This action opens a dialog box.");
ad(elt1_,txt8_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt9_=tx(_doc,"This dialog box introduces the set of the possibilitie of owning cards by players.");
ad(elt1_,txt9_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt11_=tx(_doc,"The letter P, behind a name of card, means this card is possibly owned by this player.");
ad(elt1_,txt11_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt13_=tx(_doc,"The letter C, behind a name of card, means this card is surely owned by this player.");
ad(elt1_,txt13_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt15_=tx(_doc,"The letter J, behind a name of card, means this card is already played.");
ad(elt1_,txt15_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt17_=tx(_doc,"If none of the two letters is behind a name of card, then this card is played (discarded in the dog, if taker at tarot; or in a trick) or owned by the user.");
ad(elt1_,txt17_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
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
