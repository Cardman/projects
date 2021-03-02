package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards51{
private HelpCards51(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Tarot shortcut MAJ + T");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The action of the sub menu \"Tarot\" lets change variants of playing tarot.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"This action builds a dialog box.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"The dialog box lets choose variants.");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"This does not change game rules of the possible played deal.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"Game rules is changed only from the next deal played randomly or by a training.");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt6_=tx(_doc,"Game rules is not changed while a competition.");
ad(elt1_,txt6_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt7_=tx(_doc,"The button, whose name is \"Validate\", is useful for validate modifications.");
ad(elt1_,txt7_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt8_=tx(_doc,"If the user has clicked the red cross at the top right of the window,");
ad(elt1_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt9_=tx(_doc,"then no modification is done and the dialog box is disposed.");
ad(elt1_,txt9_);
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
