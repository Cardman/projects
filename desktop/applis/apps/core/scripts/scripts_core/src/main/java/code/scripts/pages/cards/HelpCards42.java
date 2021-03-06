package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards42{
private HelpCards42(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The menu \"File\" owns four sub menus which are:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"ol");
Element elt4_=el(_doc,"li");
Text txt1_=tx(_doc,"Load a deal (shortcut: CTRL + C), accessible");
ad(elt4_,txt1_);
Element elt5_=el(_doc,"br");
ad(elt4_,elt5_);
Element elt6_=el(_doc,"ol");
Element elt7_=el(_doc,"li");
Text txt2_=tx(_doc,"while no deal is played");
ad(elt7_,txt2_);
ad(elt6_,elt7_);
Element elt8_=el(_doc,"li");
Text txt3_=tx(_doc,"while a deal is played and put in pause");
ad(elt8_,txt3_);
ad(elt6_,elt8_);
Element elt9_=el(_doc,"li");
Text txt4_=tx(_doc,"while a deal is played and the user must choose at least one card");
ad(elt9_,txt4_);
ad(elt6_,elt9_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
Element elt10_=el(_doc,"li");
Text txt5_=tx(_doc,"Save a deal (shortcut: CTRL + S), accessible");
ad(elt10_,txt5_);
Element elt11_=el(_doc,"br");
ad(elt10_,elt11_);
Element elt12_=el(_doc,"ol");
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"while a deal is played and put in pause");
ad(elt13_,txt6_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"li");
Text txt7_=tx(_doc,"while a deal is played and the user must choose at least one card");
ad(elt14_,txt7_);
ad(elt12_,elt14_);
ad(elt10_,elt12_);
ad(elt3_,elt10_);
Element elt15_=el(_doc,"li");
Text txt8_=tx(_doc,"Change game (shortcut: CTRL + J), accessible");
ad(elt15_,txt8_);
Element elt16_=el(_doc,"br");
ad(elt15_,elt16_);
Element elt17_=el(_doc,"ol");
Element elt18_=el(_doc,"li");
Text txt9_=tx(_doc,"while a deal is played and put in pause and the deal has not just been saved");
ad(elt18_,txt9_);
ad(elt17_,elt18_);
Element elt19_=el(_doc,"li");
Text txt10_=tx(_doc,"while a deal is played and the user must choose at least one card");
ad(elt19_,txt10_);
ad(elt17_,elt19_);
Element elt20_=el(_doc,"li");
Text txt11_=tx(_doc,"while no deal is played and the buttons of the window have not a name of a card game");
ad(elt20_,txt11_);
ad(elt17_,elt20_);
ad(elt15_,elt17_);
ad(elt3_,elt15_);
Element elt21_=el(_doc,"li");
Text txt12_=tx(_doc,"Quit (shortcut: CTRL + W), always accessible");
ad(elt21_,txt12_);
ad(elt3_,elt21_);
ad(elt1_,elt3_);
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
