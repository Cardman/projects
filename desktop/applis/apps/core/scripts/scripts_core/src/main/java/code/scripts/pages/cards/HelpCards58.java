package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards58{
private HelpCards58(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Tarot");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The action of a sub menu \"Edit\" lets made some deal of tarot in order that the user test its way of playing and the user returns errors of playing to the author.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt3_=tx(_doc,"This action opens a dialog box.");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"The dialog box is introduced under two different forms:");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"ol");
Element elt10_=el(_doc,"li");
Text txt5_=tx(_doc,"Choice of the number of players and rules of the tarot before opening.");
ad(elt10_,txt5_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,"li");
Text txt6_=tx(_doc,"Choice of the dealing of cards.");
ad(elt11_,txt6_);
ad(elt9_,elt11_);
ad(elt1_,elt9_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt7_=tx(_doc,"&#160;Choice of the rules of the tarot, for variants, influence on the dealing of cards (number of hands, number of cards). Variants are described in the description of the tarot.");
ad(elt1_,txt7_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt9_=tx(_doc,"&#160;After having correctly dealt cards, (All hands are full at tarot) the user can save the deal in a file and possibly play it.");
ad(elt1_,txt9_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
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
