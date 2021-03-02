package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards14{
private HelpCards14(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt0_=tx(_doc,"The order of cards is:");
ad(elt1_,txt0_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"2-Ace-King-Queen-Jack-10-9-8-7-6-5-4-3");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"Order of cards:");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"table");
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"td");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("border","1px"));
at(elt8_,attrs0_);
Element elt9_=el(_doc,"img");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("src","resources_cards/images/en/HEART_2.txt"));
at(elt9_,attrs1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
Element elt10_=el(_doc,"td");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("border","1px"));
at(elt10_,attrs2_);
Element elt11_=el(_doc,"img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","resources_cards/images/en/HEART_1.txt"));
at(elt11_,attrs3_);
ad(elt10_,elt11_);
ad(elt7_,elt10_);
Element elt12_=el(_doc,"td");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("border","1px"));
at(elt12_,attrs4_);
Element elt13_=el(_doc,"img");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("src","resources_cards/images/en/HEART_KING.txt"));
at(elt13_,attrs5_);
ad(elt12_,elt13_);
ad(elt7_,elt12_);
Element elt14_=el(_doc,"td");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("border","1px"));
at(elt14_,attrs6_);
Element elt15_=el(_doc,"img");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("src","resources_cards/images/en/HEART_QUEEN.txt"));
at(elt15_,attrs7_);
ad(elt14_,elt15_);
ad(elt7_,elt14_);
Element elt16_=el(_doc,"td");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("border","1px"));
at(elt16_,attrs8_);
Element elt17_=el(_doc,"img");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("src","resources_cards/images/en/HEART_JACK.txt"));
at(elt17_,attrs9_);
ad(elt16_,elt17_);
ad(elt7_,elt16_);
Element elt18_=el(_doc,"td");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("border","1px"));
at(elt18_,attrs10_);
Element elt19_=el(_doc,"img");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("src","resources_cards/images/en/HEART_10.txt"));
at(elt19_,attrs11_);
ad(elt18_,elt19_);
ad(elt7_,elt18_);
Element elt20_=el(_doc,"td");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("border","1px"));
at(elt20_,attrs12_);
Element elt21_=el(_doc,"img");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("src","resources_cards/images/en/HEART_9.txt"));
at(elt21_,attrs13_);
ad(elt20_,elt21_);
ad(elt7_,elt20_);
Element elt22_=el(_doc,"td");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("border","1px"));
at(elt22_,attrs14_);
Element elt23_=el(_doc,"img");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("src","resources_cards/images/en/HEART_8.txt"));
at(elt23_,attrs15_);
ad(elt22_,elt23_);
ad(elt7_,elt22_);
Element elt24_=el(_doc,"td");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("border","1px"));
at(elt24_,attrs16_);
Element elt25_=el(_doc,"img");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("src","resources_cards/images/en/HEART_7.txt"));
at(elt25_,attrs17_);
ad(elt24_,elt25_);
ad(elt7_,elt24_);
Element elt26_=el(_doc,"td");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("border","1px"));
at(elt26_,attrs18_);
Element elt27_=el(_doc,"img");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("src","resources_cards/images/en/HEART_6.txt"));
at(elt27_,attrs19_);
ad(elt26_,elt27_);
ad(elt7_,elt26_);
Element elt28_=el(_doc,"td");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("border","1px"));
at(elt28_,attrs20_);
Element elt29_=el(_doc,"img");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("src","resources_cards/images/en/HEART_5.txt"));
at(elt29_,attrs21_);
ad(elt28_,elt29_);
ad(elt7_,elt28_);
Element elt30_=el(_doc,"td");
CustList<Attr> attrs22_=al(1);
attrs22_.add(at("border","1px"));
at(elt30_,attrs22_);
Element elt31_=el(_doc,"img");
CustList<Attr> attrs23_=al(1);
attrs23_.add(at("src","resources_cards/images/en/HEART_4.txt"));
at(elt31_,attrs23_);
ad(elt30_,elt31_);
ad(elt7_,elt30_);
Element elt32_=el(_doc,"td");
CustList<Attr> attrs24_=al(1);
attrs24_.add(at("border","1px"));
at(elt32_,attrs24_);
Element elt33_=el(_doc,"img");
CustList<Attr> attrs25_=al(1);
attrs25_.add(at("src","resources_cards/images/en/HEART_3.txt"));
at(elt33_,attrs25_);
ad(elt32_,elt33_);
ad(elt7_,elt32_);
ad(elt6_,elt7_);
ad(elt1_,elt6_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
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
