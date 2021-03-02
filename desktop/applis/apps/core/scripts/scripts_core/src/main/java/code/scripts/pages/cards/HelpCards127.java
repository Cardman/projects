package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards127{
private HelpCards127(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Conseil Raccourci F1");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu \"Conseil\" permet d''obtenir de l''aide pour");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"Annoncer un contrat");
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"Annoncer une ench&#232;re");
ad(elt6_,txt3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"Annoncer une carte");
ad(elt7_,txt4_);
ad(elt4_,elt7_);
Element elt8_=el(_doc,"li");
Text txt5_=tx(_doc,"Ecarter une ou plusieurs cartes");
ad(elt8_,txt5_);
ad(elt4_,elt8_);
Element elt9_=el(_doc,"li");
Text txt6_=tx(_doc,"Jouer une ou plusieurs cartes");
ad(elt9_,txt6_);
ad(elt4_,elt9_);
ad(elt1_,elt4_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt7_=tx(_doc,"Cependant, il n''y aura pas d''aide pour les jeux solitaires.");
ad(elt1_,txt7_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt8_=tx(_doc,"Cette bo&#238;te de dialogue permet de conseiller l''utilisateur, sans l''obliger,");
ad(elt1_,txt8_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt9_=tx(_doc,"d''annoncer un contrat ou une ench&#232;re, d''appeler une carte,");
ad(elt1_,txt9_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt10_=tx(_doc,"de jouer une carte dans un pli, d''&#233;carter des cartes dans un talon");
ad(elt1_,txt10_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt11_=tx(_doc,"ou d''&#233;changer des cartes.");
ad(elt1_,txt11_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt12_=tx(_doc,"On donne la raison de l''action conseill&#233;e.");
ad(elt1_,txt12_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
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
