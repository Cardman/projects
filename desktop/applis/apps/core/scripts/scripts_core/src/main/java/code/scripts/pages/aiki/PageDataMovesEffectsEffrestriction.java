package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffrestriction{
private PageDataMovesEffectsEffrestriction(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_restriction"));
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
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"p");
Element elt1_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","forbidTargetUsingItem"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_effrestriction,effect_item"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_effrestriction,effect_item_2"));
at(elt3_,attrs2_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt4_=el(_doc,"c:if");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("condition","forbid()"));
at(elt4_,attrs3_);
Element elt5_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_effrestriction,effect_move"));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
ad(elt0_,elt4_);
Element elt6_=el(_doc,"c:import");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("page","{effectBean}"));
at(elt6_,attrs5_);
Element elt7_=el(_doc,"c:package");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("name","aiki.beans.moves.effects"));
at(elt7_,attrs6_);
Element elt8_=el(_doc,"c:class");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("name","EffectBean"));
at(elt8_,attrs7_);
Element elt9_=el(_doc,"c:field");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("prepare","$intern.index=index"));
at(elt9_,attrs8_);
ad(elt8_,elt9_);
Element elt10_=el(_doc,"c:field");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("prepare","$intern.move=move"));
at(elt10_,attrs9_);
ad(elt8_,elt10_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt11_=el(_doc,"c:if");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("condition","forbidStatusMove()"));
at(elt11_,attrs10_);
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","msg_effrestriction,forbid_status_move"));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
Element elt13_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","forbidLastMove()"));
at(elt13_,attrs12_);
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_effrestriction,forbid_last_move"));
at(elt14_,attrs13_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("condition","forbidUserMoves()"));
at(elt15_,attrs14_);
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","msg_effrestriction,forbid_user_moves"));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
ad(elt0_,elt15_);
Element elt17_=el(_doc,"c:if");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("condition","forbidUseMove()"));
at(elt17_,attrs16_);
Element elt18_=el(_doc,"c:message");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("value","msg_effrestriction,forbid_use_last_move"));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt0_,elt17_);
Element elt19_=el(_doc,"c:if");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("condition","forceUseMove()"));
at(elt19_,attrs18_);
Element elt20_=el(_doc,"c:message");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("value","msg_effrestriction,force_use_last_move"));
at(elt20_,attrs19_);
ad(elt19_,elt20_);
ad(elt0_,elt19_);
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
