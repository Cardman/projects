package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards77{
private HelpCards77(){}
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
Text txt0_=tx(_doc,"Il existe deux types de couleur atout et couleur.");
ad(elt1_,txt0_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"L''ordre des cartes &#224; la couleur est:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"As-10-Roi-Dame-Valet-9-8-7");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt3_=tx(_doc,"L''ordre des cartes &#224; l''atout est:");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"Valet-9-As-10-Roi-Dame-8-7");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"Dans ce jeu, les atouts sont plus fort que les cartes de couleur.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt6_=tx(_doc,"Ordre &#224; l''atout:");
ad(elt1_,txt6_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"table");
Element elt11_=el(_doc,"tr");
Element elt12_=el(_doc,"td");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("border","1px"));
at(elt12_,attrs0_);
Element elt13_=el(_doc,"img");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("src","resources_cards/images/fr/HEART_JACK.txt"));
at(elt13_,attrs1_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
Element elt14_=el(_doc,"td");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("border","1px"));
at(elt14_,attrs2_);
Element elt15_=el(_doc,"img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","resources_cards/images/fr/HEART_9.txt"));
at(elt15_,attrs3_);
ad(elt14_,elt15_);
ad(elt11_,elt14_);
Element elt16_=el(_doc,"td");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("border","1px"));
at(elt16_,attrs4_);
Element elt17_=el(_doc,"img");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("src","resources_cards/images/fr/HEART_1.txt"));
at(elt17_,attrs5_);
ad(elt16_,elt17_);
ad(elt11_,elt16_);
Element elt18_=el(_doc,"td");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("border","1px"));
at(elt18_,attrs6_);
Element elt19_=el(_doc,"img");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("src","resources_cards/images/fr/HEART_10.txt"));
at(elt19_,attrs7_);
ad(elt18_,elt19_);
ad(elt11_,elt18_);
Element elt20_=el(_doc,"td");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("border","1px"));
at(elt20_,attrs8_);
Element elt21_=el(_doc,"img");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("src","resources_cards/images/fr/HEART_KING.txt"));
at(elt21_,attrs9_);
ad(elt20_,elt21_);
ad(elt11_,elt20_);
Element elt22_=el(_doc,"td");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("border","1px"));
at(elt22_,attrs10_);
Element elt23_=el(_doc,"img");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("src","resources_cards/images/fr/HEART_QUEEN.txt"));
at(elt23_,attrs11_);
ad(elt22_,elt23_);
ad(elt11_,elt22_);
Element elt24_=el(_doc,"td");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("border","1px"));
at(elt24_,attrs12_);
Element elt25_=el(_doc,"img");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("src","resources_cards/images/fr/HEART_8.txt"));
at(elt25_,attrs13_);
ad(elt24_,elt25_);
ad(elt11_,elt24_);
Element elt26_=el(_doc,"td");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("border","1px"));
at(elt26_,attrs14_);
Element elt27_=el(_doc,"img");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("src","resources_cards/images/fr/HEART_7.txt"));
at(elt27_,attrs15_);
ad(elt26_,elt27_);
ad(elt11_,elt26_);
ad(elt10_,elt11_);
ad(elt1_,elt10_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Text txt7_=tx(_doc,"Ordre &#224; la couleur:");
ad(elt1_,txt7_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Element elt30_=el(_doc,"table");
Element elt31_=el(_doc,"tr");
Element elt32_=el(_doc,"td");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("border","1px"));
at(elt32_,attrs16_);
Element elt33_=el(_doc,"img");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("src","resources_cards/images/fr/SPADE_1.txt"));
at(elt33_,attrs17_);
ad(elt32_,elt33_);
ad(elt31_,elt32_);
Element elt34_=el(_doc,"td");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("border","1px"));
at(elt34_,attrs18_);
Element elt35_=el(_doc,"img");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("src","resources_cards/images/fr/SPADE_10.txt"));
at(elt35_,attrs19_);
ad(elt34_,elt35_);
ad(elt31_,elt34_);
Element elt36_=el(_doc,"td");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("border","1px"));
at(elt36_,attrs20_);
Element elt37_=el(_doc,"img");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("src","resources_cards/images/fr/SPADE_KING.txt"));
at(elt37_,attrs21_);
ad(elt36_,elt37_);
ad(elt31_,elt36_);
Element elt38_=el(_doc,"td");
CustList<Attr> attrs22_=al(1);
attrs22_.add(at("border","1px"));
at(elt38_,attrs22_);
Element elt39_=el(_doc,"img");
CustList<Attr> attrs23_=al(1);
attrs23_.add(at("src","resources_cards/images/fr/SPADE_QUEEN.txt"));
at(elt39_,attrs23_);
ad(elt38_,elt39_);
ad(elt31_,elt38_);
Element elt40_=el(_doc,"td");
CustList<Attr> attrs24_=al(1);
attrs24_.add(at("border","1px"));
at(elt40_,attrs24_);
Element elt41_=el(_doc,"img");
CustList<Attr> attrs25_=al(1);
attrs25_.add(at("src","resources_cards/images/fr/SPADE_JACK.txt"));
at(elt41_,attrs25_);
ad(elt40_,elt41_);
ad(elt31_,elt40_);
Element elt42_=el(_doc,"td");
CustList<Attr> attrs26_=al(1);
attrs26_.add(at("border","1px"));
at(elt42_,attrs26_);
Element elt43_=el(_doc,"img");
CustList<Attr> attrs27_=al(1);
attrs27_.add(at("src","resources_cards/images/fr/SPADE_9.txt"));
at(elt43_,attrs27_);
ad(elt42_,elt43_);
ad(elt31_,elt42_);
Element elt44_=el(_doc,"td");
CustList<Attr> attrs28_=al(1);
attrs28_.add(at("border","1px"));
at(elt44_,attrs28_);
Element elt45_=el(_doc,"img");
CustList<Attr> attrs29_=al(1);
attrs29_.add(at("src","resources_cards/images/fr/SPADE_8.txt"));
at(elt45_,attrs29_);
ad(elt44_,elt45_);
ad(elt31_,elt44_);
Element elt46_=el(_doc,"td");
CustList<Attr> attrs30_=al(1);
attrs30_.add(at("border","1px"));
at(elt46_,attrs30_);
Element elt47_=el(_doc,"img");
CustList<Attr> attrs31_=al(1);
attrs31_.add(at("src","resources_cards/images/fr/SPADE_7.txt"));
at(elt47_,attrs31_);
ad(elt46_,elt47_);
ad(elt31_,elt46_);
ad(elt30_,elt31_);
ad(elt1_,elt30_);
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
