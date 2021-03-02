package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards47{
private HelpCards47(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Interacting with cards shortcut F5");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The action of the sub menu \"Interacting with cards\" opens a dialog box which lets choose between");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"Click a card for playing it.");
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"Overfly a card and exit the arrow to the carpet or out of the window.");
ad(elt6_,txt3_);
ad(elt4_,elt6_);
ad(elt1_,elt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"The case of belote and the case of tarot are separated.");
ad(elt1_,txt4_);
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
