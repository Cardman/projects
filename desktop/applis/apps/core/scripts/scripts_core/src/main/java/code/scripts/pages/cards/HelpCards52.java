package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards52{
private HelpCards52(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Timing for playing shortcut F4");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The action of the sub menu \"Timing for playing\" lets change");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"delay between two cards consecutively played by the \"robots\".");
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"delay between two tricks or rounds consecutively played.");
ad(elt6_,txt3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"delay between two bids consecutively played by the \"robots\".");
ad(elt7_,txt4_);
ad(elt4_,elt7_);
ad(elt1_,elt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"This action shows a dialog box.");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt6_=tx(_doc,"This dialog box lets fix delay between:");
ad(elt1_,txt6_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"ol");
Element elt13_=el(_doc,"li");
Text txt7_=tx(_doc,"Two consecutive cards.");
ad(elt13_,txt7_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"li");
Text txt8_=tx(_doc,"Two consecutive bids.");
ad(elt14_,txt8_);
ad(elt12_,elt14_);
Element elt15_=el(_doc,"li");
Text txt9_=tx(_doc,"Two consecutive tricks.");
ad(elt15_,txt9_);
ad(elt12_,elt15_);
ad(elt1_,elt12_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt10_=tx(_doc,"Besides, this dialog box lets");
ad(elt1_,txt10_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt11_=tx(_doc,"enable or disabled delay between two consecutive tricks,");
ad(elt1_,txt11_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt12_=tx(_doc,"without clicking buttons at bottom right of the window.");
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
