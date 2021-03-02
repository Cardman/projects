package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffclone{
private PageDataMovesEffectsEffclone(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_clone"));
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
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_effclone,effect"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","hpRateClone"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"c:import");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("page","{effectBean}"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"c:package");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("name","aiki.beans.moves.effects"));
at(elt4_,attrs3_);
Element elt5_=el(_doc,"c:class");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("name","EffectBean"));
at(elt5_,attrs4_);
Element elt6_=el(_doc,"c:field");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("prepare","$intern.index=index"));
at(elt6_,attrs5_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,"c:field");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("prepare","$intern.move=move"));
at(elt7_,attrs6_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_effclone,effect_2"));
at(elt8_,attrs7_);
ad(elt0_,elt8_);
Element elt9_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","!movesEndRound.isEmpty()"));
at(elt9_,attrs8_);
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_effclone,effect_3"));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,"ul");
Element elt12_=el(_doc,"c:for");
CustList<Attr> attrs10_=al(2);
attrs10_.add(at("list","movesEndRound"));
attrs10_.add(at("var","m"));
at(elt12_,attrs10_);
Element elt13_=el(_doc,"li");
Element elt14_=el(_doc,"a");
CustList<Attr> attrs11_=al(2);
attrs11_.add(at("c:command","$clickMoveEndRound({([m])})"));
attrs11_.add(at("href",""));
at(elt14_,attrs11_);
Text txt0_=tx(_doc,"{getTrMovesEndRound(([m]))}");
ad(elt14_,txt0_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
ad(elt9_,elt11_);
Element elt15_=el(_doc,"br");
ad(elt9_,elt15_);
ad(elt0_,elt9_);
Element elt16_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","!movesBatonPass.isEmpty()"));
at(elt16_,attrs12_);
Element elt17_=el(_doc,"c:if");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("condition","!movesSending.isEmpty()"));
at(elt17_,attrs13_);
Element elt18_=el(_doc,"c:message");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("value","msg_effclone,effect_4"));
at(elt18_,attrs14_);
ad(elt17_,elt18_);
Element elt19_=el(_doc,"ul");
Element elt20_=el(_doc,"c:for");
CustList<Attr> attrs15_=al(2);
attrs15_.add(at("list","movesBatonPass"));
attrs15_.add(at("var","m"));
at(elt20_,attrs15_);
Element elt21_=el(_doc,"li");
Element elt22_=el(_doc,"a");
CustList<Attr> attrs16_=al(2);
attrs16_.add(at("c:command","$clickMoveBatonPass({([m])})"));
attrs16_.add(at("href",""));
at(elt22_,attrs16_);
Text txt1_=tx(_doc,"{getTrMovesBatonPass(([m]))}");
ad(elt22_,txt1_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt17_,elt19_);
Element elt23_=el(_doc,"br");
ad(elt17_,elt23_);
Element elt24_=el(_doc,"c:message");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("value","msg_effclone,effect_5"));
at(elt24_,attrs17_);
ad(elt17_,elt24_);
Element elt25_=el(_doc,"ul");
Element elt26_=el(_doc,"c:for");
CustList<Attr> attrs18_=al(2);
attrs18_.add(at("list","movesSending"));
attrs18_.add(at("var","m"));
at(elt26_,attrs18_);
Element elt27_=el(_doc,"li");
Element elt28_=el(_doc,"a");
CustList<Attr> attrs19_=al(2);
attrs19_.add(at("c:command","$clickMoveSending({([m])})"));
attrs19_.add(at("href",""));
at(elt28_,attrs19_);
Text txt2_=tx(_doc,"{getTrMovesSending(([m]))}");
ad(elt28_,txt2_);
ad(elt27_,elt28_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt17_,elt25_);
Element elt29_=el(_doc,"br");
ad(elt17_,elt29_);
ad(elt16_,elt17_);
ad(elt0_,elt16_);
Element elt30_=el(_doc,"c:message");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("value","msg_effclone,effect_6"));
at(elt30_,attrs20_);
ad(elt0_,elt30_);
Element elt31_=el(_doc,"c:message");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("value","msg_effclone,effect_7"));
at(elt31_,attrs21_);
ad(elt0_,elt31_);
Element elt32_=el(_doc,"c:message");
CustList<Attr> attrs22_=al(1);
attrs22_.add(at("value","msg_effclone,effect_8"));
at(elt32_,attrs22_);
ad(elt0_,elt32_);
Element elt33_=el(_doc,"c:message");
CustList<Attr> attrs23_=al(1);
attrs23_.add(at("value","msg_effclone,effect_9"));
at(elt33_,attrs23_);
ad(elt0_,elt33_);
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
