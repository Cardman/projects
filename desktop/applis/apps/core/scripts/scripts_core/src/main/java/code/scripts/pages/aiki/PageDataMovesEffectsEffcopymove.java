package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffcopymove{
private PageDataMovesEffectsEffcopymove(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_copymove"));
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
attrs0_.add(at("value","msg_effcopymove,effect"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_effcopymove,no_effect"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","getTrDefaultMove()"));
at(elt3_,attrs2_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt4_=el(_doc,"c:import");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("page","{effectBean}"));
at(elt4_,attrs3_);
Element elt5_=el(_doc,"c:package");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("name","aiki.beans.moves.effects"));
at(elt5_,attrs4_);
Element elt6_=el(_doc,"c:class");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("name","EffectBean"));
at(elt6_,attrs5_);
Element elt7_=el(_doc,"c:field");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("prepare","$intern.index=index"));
at(elt7_,attrs6_);
ad(elt6_,elt7_);
Element elt8_=el(_doc,"c:field");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("prepare","$intern.move=move"));
at(elt8_,attrs7_);
ad(elt6_,elt8_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt0_,elt4_);
Element elt9_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","copyMoveForUser()"));
at(elt9_,attrs8_);
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_effcopymove,copy_tmp_move"));
at(elt10_,attrs9_);
Element elt11_=el(_doc,"param");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("value","displayName"));
at(elt11_,attrs10_);
ad(elt10_,elt11_);
Element elt12_=el(_doc,"param");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","copyingMoveForUser"));
at(elt12_,attrs11_);
ad(elt10_,elt12_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
Element elt13_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","copyingMoveForUserDef"));
at(elt13_,attrs12_);
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_effcopymove,no_effect_2"));
at(elt14_,attrs13_);
Element elt15_=el(_doc,"param");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("value","getTrDefaultMove()"));
at(elt15_,attrs14_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
Element elt16_=el(_doc,"c:if");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("condition","!movesTransforming.isEmpty()"));
at(elt16_,attrs15_);
Element elt17_=el(_doc,"c:message");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("value","msg_effcopymove,copy_def_move"));
at(elt17_,attrs16_);
Element elt18_=el(_doc,"param");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("value","displayName"));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
Element elt19_=el(_doc,"ul");
Element elt20_=el(_doc,"c:for");
CustList<Attr> attrs18_=al(2);
attrs18_.add(at("list","movesTransforming"));
attrs18_.add(at("var","m"));
at(elt20_,attrs18_);
Element elt21_=el(_doc,"li");
Element elt22_=el(_doc,"a");
CustList<Attr> attrs19_=al(2);
attrs19_.add(at("c:command","$clickMoveTrans({([m])})"));
attrs19_.add(at("href",""));
at(elt22_,attrs19_);
Text txt0_=tx(_doc,"{getTrMoveTrans(([m]))}");
ad(elt22_,txt0_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt16_,elt19_);
ad(elt13_,elt16_);
Element elt23_=el(_doc,"c:if");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("condition","movesTransforming.isEmpty()"));
at(elt23_,attrs20_);
Element elt24_=el(_doc,"c:message");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("value","msg_effcopymove,copy_def_move_without_trans"));
at(elt24_,attrs21_);
Element elt25_=el(_doc,"param");
CustList<Attr> attrs22_=al(1);
attrs22_.add(at("value","displayName"));
at(elt25_,attrs22_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt13_,elt23_);
Element elt26_=el(_doc,"br");
ad(elt13_,elt26_);
ad(elt0_,elt13_);
Element elt27_=el(_doc,"c:if");
CustList<Attr> attrs23_=al(1);
attrs23_.add(at("condition","!movesNotToBeCopied.isEmpty()"));
at(elt27_,attrs23_);
Element elt28_=el(_doc,"c:message");
CustList<Attr> attrs24_=al(1);
attrs24_.add(at("value","msg_effcopymove,moves_not_copied"));
at(elt28_,attrs24_);
ad(elt27_,elt28_);
Element elt29_=el(_doc,"ul");
Element elt30_=el(_doc,"c:for");
CustList<Attr> attrs25_=al(2);
attrs25_.add(at("list","movesNotToBeCopied"));
attrs25_.add(at("var","s"));
at(elt30_,attrs25_);
Element elt31_=el(_doc,"li");
Element elt32_=el(_doc,"a");
CustList<Attr> attrs26_=al(2);
attrs26_.add(at("c:command","$clickMove({([s])})"));
attrs26_.add(at("href",""));
at(elt32_,attrs26_);
Text txt1_=tx(_doc,"{getTrMove(([s]))}");
ad(elt32_,txt1_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
ad(elt29_,elt30_);
ad(elt27_,elt29_);
Element elt33_=el(_doc,"br");
ad(elt27_,elt33_);
ad(elt0_,elt27_);
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
