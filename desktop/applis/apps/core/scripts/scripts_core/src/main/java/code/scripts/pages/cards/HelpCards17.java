package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards17{
private HelpCards17(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The base declaring are handful and slam.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"For slam, if the taker owns besides the Excuse, the taker must play it at the last trick,");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"because the taker has no partner and the Excuse, played before the last trick, does not allow to lead.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"A handful is a hand containing only trump cards (The Excuse can be a trump card).");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"Adjectives simple, double et triple qualify the number of present trump cards in the handful.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt5_=tx(_doc,"The minimal numbers of trump cards (Excuse included) for declaring a simple handful,");
ad(elt1_,txt5_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt6_=tx(_doc,"a double handful and a triple handful are respectively 10, 13 and 15.");
ad(elt1_,txt6_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt7_=tx(_doc,"The Excuse can belong to a handful only if the full number of trump cards (Excuse included) is the matching minimal number.");
ad(elt1_,txt7_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt8_=tx(_doc,"Examples:");
ad(elt1_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Element elt17_=el(_doc,"ol");
Element elt18_=el(_doc,"li");
Text txt9_=tx(_doc,"If a player owns 9 trump cards and the Excuse,");
ad(elt18_,txt9_);
Element elt19_=el(_doc,"br");
ad(elt18_,elt19_);
Text txt10_=tx(_doc,"&#160;&#160;then for declaring the simple handful, the declarer must add the Excuse in the handful.");
ad(elt18_,txt10_);
Element elt20_=el(_doc,"br");
ad(elt18_,elt20_);
ad(elt17_,elt18_);
Element elt21_=el(_doc,"li");
Text txt11_=tx(_doc,"If a player owns 10 trump cards and the Excuse,");
ad(elt21_,txt11_);
Element elt22_=el(_doc,"br");
ad(elt21_,elt22_);
Text txt12_=tx(_doc,"&#160;&#160;then for declaring the simple handful, the declarer must not add the Excuse in the handful.");
ad(elt21_,txt12_);
Element elt23_=el(_doc,"br");
ad(elt21_,elt23_);
ad(elt17_,elt21_);
Element elt24_=el(_doc,"li");
Text txt13_=tx(_doc,"If a player owns 13 trump cards and the Excuse,");
ad(elt24_,txt13_);
Element elt25_=el(_doc,"br");
ad(elt24_,elt25_);
Text txt14_=tx(_doc,"&#160;&#160;then the player has not to declare a double handful.");
ad(elt24_,txt14_);
Element elt26_=el(_doc,"br");
ad(elt24_,elt26_);
ad(elt17_,elt24_);
ad(elt1_,elt17_);
Element elt27_=el(_doc,"br");
ad(elt1_,elt27_);
Text txt15_=tx(_doc,"Declaring handfuls or miseres is done at the beginning of deal by playing the first card.");
ad(elt1_,txt15_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Text txt16_=tx(_doc,"Handfuls must be shown, while declaring handfuls or else, there may be false declaring.");
ad(elt1_,txt16_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
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
