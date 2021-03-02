package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards48{
private HelpCards48(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Players shortcut CTRL + ALT + J");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The action of the sub menu \"Players\" lets change the nickname of the user and the nickname of the other players.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"This action shows a dialog box.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"The dialog box lets change:");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"ol");
Element elt9_=el(_doc,"li");
Text txt4_=tx(_doc,"The nickname of the user.");
ad(elt9_,txt4_);
ad(elt8_,elt9_);
Element elt10_=el(_doc,"li");
Text txt5_=tx(_doc,"The nickname of the possible other players.");
ad(elt10_,txt5_);
ad(elt8_,elt10_);
ad(elt1_,elt8_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt6_=tx(_doc,"Warning, only the first nicknames, for the other players, will be used.");
ad(elt1_,txt6_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt7_=tx(_doc,"Example: if the game is played by 4 players, then only the 3 first nicknames");
ad(elt1_,txt7_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt8_=tx(_doc,"will be used.");
ad(elt1_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
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
