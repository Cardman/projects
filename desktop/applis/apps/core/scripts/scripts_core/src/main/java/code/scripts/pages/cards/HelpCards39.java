package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards39{
private HelpCards39(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Load a deal (shortcut: CTRL + O):");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The sub menu \"Load a deal\" opens a dialog box which lets load any deal from this dialog box.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt3_=tx(_doc,"If a deal is being played, and if the deal has not been just saved, then a warning message for the deal in progress appears.");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
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
Text txt9_=tx(_doc,"If the user has chosen \"yes\", then a dialog box for saving appears and the user must give a name to the deal in progress.");
ad(elt1_,txt9_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt11_=tx(_doc,"If the user has chosen \"no\", then a dialog box of loading is opened.");
ad(elt1_,txt11_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt12_=tx(_doc,"If the user has chosen \"cancel\", then nothing happens.");
ad(elt1_,txt12_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt13_=tx(_doc,"The user can play or replay a saved deal from a file. Besides, if the deal is edited, the user can play the next deals if the user has chosen to play several consecutive deals.");
ad(elt1_,txt13_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt16_=tx(_doc,"Folders are grouped on a tree at the left of the dialog box.");
ad(elt1_,txt16_);
Element elt25_=el(_doc,"br");
ad(elt1_,elt25_);
Text txt17_=tx(_doc,"Their content will be placed at the right of the dialog box, like Windows Explorer.");
ad(elt1_,txt17_);
Element elt26_=el(_doc,"br");
ad(elt1_,elt26_);
Element elt27_=el(_doc,"br");
ad(elt1_,elt27_);
Text txt18_=tx(_doc,"The button \"Cancel\" lets close the dialog box.");
ad(elt1_,txt18_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Text txt19_=tx(_doc,"The user can search a file in the current folder with a text field above the content of folder.");
ad(elt1_,txt19_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Text txt20_=tx(_doc,"If the user clicks a file of the enumerated list of files, then the dialog box is disposed.");
ad(elt1_,txt20_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Text txt21_=tx(_doc,"Then:");
ad(elt1_,txt21_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Element elt34_=el(_doc,"ol");
Element elt35_=el(_doc,"li");
Text txt22_=tx(_doc,"If the file is readable, then the deal (Either a simple deal or a set of deals, in the case when it is a set of deals, the loaded deal is the last played deal.), contained in this file, will be loaded; else an error message appears displaying the loading fail.");
ad(elt35_,txt22_);
Element elt36_=el(_doc,"br");
ad(elt35_,elt36_);
Text txt25_=tx(_doc,"Deals are checked about rules for avoiding bugs.");
ad(elt1_,txt25_);
Element elt39_=el(_doc,"br");
ad(elt1_,elt39_);
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
