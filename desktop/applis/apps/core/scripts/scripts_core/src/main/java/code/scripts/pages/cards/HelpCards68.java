package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards68{
private HelpCards68(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The software owns a menu bar with five menus.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"These menus are:");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"File, which lets gear");
ad(elt5_,txt2_);
Element elt6_=el(_doc,"br");
ad(elt5_,elt6_);
Element elt7_=el(_doc,"ol");
Element elt8_=el(_doc,"li");
Text txt3_=tx(_doc,"files");
ad(elt8_,txt3_);
ad(elt7_,elt8_);
Element elt9_=el(_doc,"li");
Text txt4_=tx(_doc,"state changements of the software");
ad(elt9_,txt4_);
ad(elt7_,elt9_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
Element elt10_=el(_doc,"li");
Text txt5_=tx(_doc,"Deal, which lets");
ad(elt10_,txt5_);
Element elt11_=el(_doc,"br");
ad(elt10_,elt11_);
Element elt12_=el(_doc,"ol");
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"gear a deal");
ad(elt13_,txt6_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"li");
Text txt7_=tx(_doc,"get help about way of playing for this deal");
ad(elt14_,txt7_);
ad(elt12_,elt14_);
Element elt15_=el(_doc,"li");
Text txt8_=tx(_doc,"train with certain situations of game");
ad(elt15_,txt8_);
ad(elt12_,elt15_);
Element elt16_=el(_doc,"li");
Text txt9_=tx(_doc,"see the possibilities of owning cards of the other players");
ad(elt16_,txt9_);
ad(elt12_,elt16_);
Element elt17_=el(_doc,"li");
Text txt10_=tx(_doc,"edit a deal");
ad(elt17_,txt10_);
ad(elt12_,elt17_);
Element elt18_=el(_doc,"li");
Text txt11_=tx(_doc,"learn playing by a demo of a deal");
ad(elt18_,txt11_);
ad(elt12_,elt18_);
ad(elt10_,elt12_);
ad(elt4_,elt10_);
Element elt19_=el(_doc,"li");
Text txt12_=tx(_doc,"Parameters, which lets gear");
ad(elt19_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Element elt21_=el(_doc,"ol");
Element elt22_=el(_doc,"li");
Text txt13_=tx(_doc,"rules of card games");
ad(elt22_,txt13_);
ad(elt21_,elt22_);
Element elt23_=el(_doc,"li");
Text txt14_=tx(_doc,"launching of the software");
ad(elt23_,txt14_);
ad(elt21_,elt23_);
Element elt24_=el(_doc,"li");
Text txt15_=tx(_doc,"nicknames of the players");
ad(elt24_,txt15_);
ad(elt21_,elt24_);
Element elt25_=el(_doc,"li");
Text txt16_=tx(_doc,"the timing of playing");
ad(elt25_,txt16_);
ad(elt21_,elt25_);
Element elt26_=el(_doc,"li");
Text txt17_=tx(_doc,"interaction between arrow of the mouse and cards");
ad(elt26_,txt17_);
ad(elt21_,elt26_);
ad(elt19_,elt21_);
ad(elt4_,elt19_);
Element elt27_=el(_doc,"li");
Text txt18_=tx(_doc,"Help, which gives help");
ad(elt27_,txt18_);
ad(elt4_,elt27_);
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
