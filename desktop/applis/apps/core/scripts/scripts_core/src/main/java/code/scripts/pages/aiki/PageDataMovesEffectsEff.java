package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEff{
private PageDataMovesEffectsEff(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/moves.css"));
attrs1_.add(at("rel","stylesheet"));
attrs1_.add(at("type","text/css"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"body");
build0(elt3_,_doc);
build1(elt3_,_doc);
build2(elt3_,_doc);
build3(elt3_,_doc);
build4(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_eff,targets"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","isAdjAdv()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_eff,targets_ADJ_ADV"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"br");
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","isAdjMult()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_eff,targets_ADJ_MULT"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"br");
ad(elt0_,elt2_);
ad(_body,elt0_);
Element elt3_=el(_doc,"c:if");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("condition","isAdjUniq()"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_eff,targets_ADJ_UNIQ"));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt3_,elt5_);
ad(_body,elt3_);
Element elt6_=el(_doc,"c:if");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("condition","isAllie()"));
at(elt6_,attrs4_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_eff,targets_ALLIE"));
at(elt7_,attrs5_);
ad(elt6_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt6_,elt8_);
ad(_body,elt6_);
Element elt9_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","isAllies()"));
at(elt9_,attrs6_);
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_eff,targets_ALLIES"));
at(elt10_,attrs7_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
ad(_body,elt9_);
Element elt12_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","isAnyFoe()"));
at(elt12_,attrs8_);
Element elt13_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_eff,targets_ANY_FOE"));
at(elt13_,attrs9_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt12_,elt14_);
ad(_body,elt12_);
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("condition","isAutreUniq()"));
at(elt15_,attrs10_);
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","msg_eff,targets_AUTRE_UNIQ"));
at(elt16_,attrs11_);
ad(elt15_,elt16_);
Element elt17_=el(_doc,"br");
ad(elt15_,elt17_);
ad(_body,elt15_);
Element elt18_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","isGlobale()"));
at(elt18_,attrs12_);
Element elt19_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_eff,targets_GLOBALE"));
at(elt19_,attrs13_);
ad(elt18_,elt19_);
Element elt20_=el(_doc,"br");
ad(elt18_,elt20_);
ad(_body,elt18_);
Element elt21_=el(_doc,"c:if");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("condition","isLanceur()"));
at(elt21_,attrs14_);
Element elt22_=el(_doc,"c:message");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","msg_eff,targets_LANCEUR"));
at(elt22_,attrs15_);
ad(elt21_,elt22_);
Element elt23_=el(_doc,"br");
ad(elt21_,elt23_);
ad(_body,elt21_);
Element elt24_=el(_doc,"c:if");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("condition","isPseudoGlobale()"));
at(elt24_,attrs16_);
Element elt25_=el(_doc,"c:message");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("value","msg_eff,targets_PSEUDO_GLOBALE"));
at(elt25_,attrs17_);
ad(elt24_,elt25_);
Element elt26_=el(_doc,"br");
ad(elt24_,elt26_);
ad(_body,elt24_);
Element elt27_=el(_doc,"c:if");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("condition","isTousAdv()"));
at(elt27_,attrs18_);
Element elt28_=el(_doc,"c:message");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("value","msg_eff,targets_TOUS_ADV"));
at(elt28_,attrs19_);
ad(elt27_,elt28_);
Element elt29_=el(_doc,"br");
ad(elt27_,elt29_);
ad(_body,elt27_);
Element elt30_=el(_doc,"c:if");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("condition","isUniqueImporte()"));
at(elt30_,attrs20_);
Element elt31_=el(_doc,"c:message");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("value","msg_eff,targets_UNIQUE_IMPORTE"));
at(elt31_,attrs21_);
ad(elt30_,elt31_);
Element elt32_=el(_doc,"br");
ad(elt30_,elt32_);
ad(_body,elt30_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!reasons.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"p");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_eff,reasons"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,"ul");
Element elt4_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","reasons"));
attrs2_.add(at("var","r"));
attrs2_.add(at("className","java.lang.String"));
at(elt4_,attrs2_);
Element elt5_=el(_doc,"li");
Text txt0_=tx(_doc,"{r}");
ad(elt5_,txt0_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"ul");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(5);
attrs3_.add(at("key","c"));
attrs3_.add(at("map","mapVarsFail"));
attrs3_.add(at("value","r"));
attrs3_.add(at("keyClassName","java.lang.String"));
attrs3_.add(at("varClassName","java.lang.String"));
at(elt8_,attrs3_);
Element elt9_=el(_doc,"li");
Text txt1_=tx(_doc,"{c} :");
ad(elt9_,txt1_);
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(2);
attrs4_.add(at("quoted",""));
attrs4_.add(at("value","msg_eff,formula"));
at(elt10_,attrs4_);
Element elt11_=el(_doc,"param");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","r"));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt1_,elt7_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","needSuccessFirstEffect"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_eff,need_sucess"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
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
