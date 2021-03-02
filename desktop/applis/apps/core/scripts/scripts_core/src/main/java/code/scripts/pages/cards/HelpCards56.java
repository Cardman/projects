package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards56{
private HelpCards56(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Consulting Shortcut F1");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Acting the sub menu \"Consulting\" lets get help for");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"Declaring a bid or \"Pass\"");
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"Calling a card");
ad(elt6_,txt3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"Discarding one or several cards");
ad(elt7_,txt4_);
ad(elt4_,elt7_);
Element elt8_=el(_doc,"li");
Text txt5_=tx(_doc,"Playing one or several cards");
ad(elt8_,txt5_);
ad(elt4_,elt8_);
ad(elt1_,elt4_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt6_=tx(_doc,"This dialog box lets advise the user, without obligation that the user follows advise.");
ad(elt1_,txt6_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
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
