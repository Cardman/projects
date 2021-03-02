package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards9{
private HelpCards9(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"As for all card games, there are variants.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Here is some variants:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"ol");
Element elt7_=el(_doc,"li");
Text txt2_=tx(_doc,"The rules about playing trump cards can be changed:");
ad(elt7_,txt2_);
Element elt8_=el(_doc,"br");
ad(elt7_,elt8_);
Element elt9_=el(_doc,"ol");
Element elt10_=el(_doc,"li");
Text txt3_=tx(_doc,"The player must undertrump (Play a trump card lower) on a player of the opposite team (current winner of the trick) if possible, if the dominant suit of a trick is not the trump suit.");
ad(elt10_,txt3_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,"li");
Text txt4_=tx(_doc,"The player must undertrump and overtrump (Play a trump card greater) on the partner (current winner of the trick) if possible, if the dominant suit of a trick is not the trump suit.");
ad(elt11_,txt4_);
ad(elt9_,elt11_);
Element elt12_=el(_doc,"li");
Text txt5_=tx(_doc,"The player must overtrump on the partner (current winner of the trick) if possible, but not undertrump, if the dominant suit of a trick is not the trump suit.");
ad(elt12_,txt5_);
ad(elt9_,elt12_);
ad(elt7_,elt9_);
ad(elt6_,elt7_);
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"Play with bids \"no trump\" and \"all trump\".");
ad(elt13_,txt6_);
ad(elt6_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt6_,elt14_);
Element elt15_=el(_doc,"li");
Text txt7_=tx(_doc,"Different end of deal.");
ad(elt15_,txt7_);
ad(elt6_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt6_,elt16_);
Element elt17_=el(_doc,"li");
Text txt8_=tx(_doc,"Cards can be dealt in the clock wise.");
ad(elt17_,txt8_);
ad(elt6_,elt17_);
Element elt18_=el(_doc,"br");
ad(elt6_,elt18_);
Element elt19_=el(_doc,"li");
Text txt9_=tx(_doc,"Cards may not be mixed.");
ad(elt19_,txt9_);
ad(elt6_,elt19_);
Element elt20_=el(_doc,"br");
ad(elt6_,elt20_);
Element elt21_=el(_doc,"li");
Text txt10_=tx(_doc,"Cards may be mixed once only.");
ad(elt21_,txt10_);
ad(elt6_,elt21_);
ad(elt1_,elt6_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt11_=tx(_doc,"The variants numbered 2 and 3 are explained.");
ad(elt1_,txt11_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
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
