package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards38{
private HelpCards38(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Change game (shortcut: CTRL + J)");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The action of the sub menu \"Change game\" lets change the \"state of the software\",");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"by returning to the menu with names of games.");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt3_=tx(_doc,"If a deal is being played, and if the deal has not been just saved, then");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"a warning message for the deal in progress appears.");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"In this case, the user has the choice between three options:");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"ol");
Element elt11_=el(_doc,"li");
Text txt6_=tx(_doc,"Yes");
ad(elt11_,txt6_);
ad(elt10_,elt11_);
Element elt12_=el(_doc,"li");
Text txt7_=tx(_doc,"No");
ad(elt12_,txt7_);
ad(elt10_,elt12_);
Element elt13_=el(_doc,"li");
Text txt8_=tx(_doc,"Cancel");
ad(elt13_,txt8_);
ad(elt10_,elt13_);
ad(elt1_,elt10_);
Text txt9_=tx(_doc,"If the user has chosen \"yes\", then a dialog box for saving appears and");
ad(elt1_,txt9_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt10_=tx(_doc,"the user must give a name to the deal in progress.");
ad(elt1_,txt10_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt11_=tx(_doc,"If the user has chosen \"No\", then there is change of \"state of the software\".");
ad(elt1_,txt11_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt12_=tx(_doc,"If the user has chosen \"Cancel\", then nothing happens.");
ad(elt1_,txt12_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
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
