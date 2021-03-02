package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards41{
private HelpCards41(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Save a deal (raccourci: CTRL + S)");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The action of the sub menu \"Save a deal\" shows a dialog box.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"This sub menu lets save a deal in progress as soon as possible.");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"The user can play this deal later. Besides, if the user plays a \"TriaCartes\",");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt4_=tx(_doc,"the user can save the deal in progress.");
ad(elt1_,txt4_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"The dialog box set the relative path of the folder for saving regarding the deal in progress.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt6_=tx(_doc,"Files can be sorted:");
ad(elt1_,txt6_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"ol");
Element elt11_=el(_doc,"li");
Text txt7_=tx(_doc,"by alphabetic order of name of file.");
ad(elt11_,txt7_);
ad(elt10_,elt11_);
Element elt12_=el(_doc,"li");
Text txt8_=tx(_doc,"by order of modification date of file.");
ad(elt12_,txt8_);
ad(elt10_,elt12_);
Element elt13_=el(_doc,"li");
Text txt9_=tx(_doc,"by order of size of file.");
ad(elt13_,txt9_);
ad(elt10_,elt13_);
ad(elt1_,elt10_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt10_=tx(_doc,"The user will not have to add the extension of the file after its name.");
ad(elt1_,txt10_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt11_=tx(_doc,"The user, for saving the deal, after typing a name,");
ad(elt1_,txt11_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt12_=tx(_doc,"will be able to click either the button \"Save\", or press the key \"Enter\".");
ad(elt1_,txt12_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt13_=tx(_doc,"The button \"Cancel\" lets close the dialog box.");
ad(elt1_,txt13_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt14_=tx(_doc,"If the user tries to use the characters < > ? \" * / \\ | : ., the line return, the tabulations, then an error message appears.");
ad(elt1_,txt14_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Text txt15_=tx(_doc,"If the user tries to create a save file containing only spaces (With Windows), then an error message appears.");
ad(elt1_,txt15_);
Element elt24_=el(_doc,"br");
ad(elt1_,elt24_);
Element elt25_=el(_doc,"br");
ad(elt1_,elt25_);
Text txt16_=tx(_doc,"If the user tries to save a deal in a deal whose name already exists, then a warning message appears.");
ad(elt1_,txt16_);
Element elt26_=el(_doc,"br");
ad(elt1_,elt26_);
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
