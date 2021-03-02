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
Text txt0_=tx(_doc,"\"Help for deal\" Raccourci F2");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Acting the sub menu \"Help for deal\" lets");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"have the set of played cards,");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"the set of possible repartition of cards");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt4_=tx(_doc,"for the other players,");
ad(elt1_,txt4_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt5_=tx(_doc,"and the set of cards surely owned by the other players,");
ad(elt1_,txt5_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt6_=tx(_doc,"in function by rules of games,");
ad(elt1_,txt6_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt7_=tx(_doc,"for games with different players.");
ad(elt1_,txt7_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt8_=tx(_doc,"This action opens a dialog box.");
ad(elt1_,txt8_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt9_=tx(_doc,"This dialog box introduces");
ad(elt1_,txt9_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt10_=tx(_doc,"the set of the possibilitie of owning cards by players.");
ad(elt1_,txt10_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt11_=tx(_doc,"The letter P, behind a name of card, means");
ad(elt1_,txt11_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt12_=tx(_doc,"this card is possibly owned by this player.");
ad(elt1_,txt12_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt13_=tx(_doc,"The letter C, behind a name of card, means");
ad(elt1_,txt13_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt14_=tx(_doc,"this card is surely owned by this player.");
ad(elt1_,txt14_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt15_=tx(_doc,"The letter J, behind a name of card, means");
ad(elt1_,txt15_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt16_=tx(_doc,"this card is already played.");
ad(elt1_,txt16_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Text txt17_=tx(_doc,"If none of the two letters is behind a name of card,");
ad(elt1_,txt17_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt18_=tx(_doc,"then this card is played (discarded in the dog, if taker at tarot; or in a trick)");
ad(elt1_,txt18_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt19_=tx(_doc,"or owned by the user.");
ad(elt1_,txt19_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
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
