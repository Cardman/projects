package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffunprotectfromtypes{
private PageDataMovesEffectsEffunprotectfromtypes(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_unprotectfromtypes"));
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
attrs0_.add(at("value","msg_effunprotectfromtypes,effect"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:import");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("page","{effectBean}"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"c:package");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("name","aiki.beans.moves.effects"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"c:class");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("name","EffectBean"));
at(elt4_,attrs3_);
Element elt5_=el(_doc,"c:field");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("prepare","$intern.index=index"));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"c:field");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("prepare","$intern.move=move"));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","!types.isEmpty()"));
at(elt7_,attrs6_);
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_effunprotectfromtypes,types"));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
Element elt9_=el(_doc,"table");
Element elt10_=el(_doc,"thead");
Element elt11_=el(_doc,"tr");
Element elt12_=el(_doc,"th");
Element elt13_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_effunprotectfromtypes,types_damag"));
at(elt13_,attrs8_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
Element elt14_=el(_doc,"th");
Element elt15_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_effunprotectfromtypes,types_pk"));
at(elt15_,attrs9_);
ad(elt14_,elt15_);
ad(elt11_,elt14_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt16_=el(_doc,"tbody");
Element elt17_=el(_doc,"c:for");
CustList<Attr> attrs10_=al(2);
attrs10_.add(at("list","types"));
attrs10_.add(at("var","d"));
at(elt17_,attrs10_);
Element elt18_=el(_doc,"tr");
Element elt19_=el(_doc,"td");
Text txt0_=tx(_doc,"{getTrDamageType(([d]))}");
ad(elt19_,txt0_);
ad(elt18_,elt19_);
Element elt20_=el(_doc,"td");
Text txt1_=tx(_doc,"{getTrPokemonType(([d]))}");
ad(elt20_,txt1_);
ad(elt18_,elt20_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt9_,elt16_);
ad(elt7_,elt9_);
Element elt21_=el(_doc,"br");
ad(elt7_,elt21_);
ad(elt0_,elt7_);
Element elt22_=el(_doc,"c:if");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("condition","!disableImmuAgainstTypes.isEmpty()"));
at(elt22_,attrs11_);
Element elt23_=el(_doc,"c:message");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("value","msg_effunprotectfromtypes,disable_immu_types"));
at(elt23_,attrs12_);
ad(elt22_,elt23_);
Element elt24_=el(_doc,"ul");
Element elt25_=el(_doc,"c:for");
CustList<Attr> attrs13_=al(2);
attrs13_.add(at("list","disableImmuAgainstTypes"));
attrs13_.add(at("var","t"));
at(elt25_,attrs13_);
Element elt26_=el(_doc,"li");
Text txt2_=tx(_doc,"{getTrDisableImmuType(([t]))}");
ad(elt26_,txt2_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt22_,elt24_);
Element elt27_=el(_doc,"br");
ad(elt22_,elt27_);
ad(elt0_,elt22_);
Element elt28_=el(_doc,"c:if");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("condition","!disableImmuFromMoves.isEmpty()"));
at(elt28_,attrs14_);
Element elt29_=el(_doc,"c:message");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","msg_effunprotectfromtypes,disable_immu_from_moves"));
at(elt29_,attrs15_);
ad(elt28_,elt29_);
Element elt30_=el(_doc,"ul");
Element elt31_=el(_doc,"c:for");
CustList<Attr> attrs16_=al(2);
attrs16_.add(at("list","disableImmuFromMoves"));
attrs16_.add(at("var","t"));
at(elt31_,attrs16_);
Element elt32_=el(_doc,"li");
Element elt33_=el(_doc,"a");
CustList<Attr> attrs17_=al(2);
attrs17_.add(at("c:command","$clickMove({index},{([t])})"));
attrs17_.add(at("href",""));
at(elt33_,attrs17_);
Text txt3_=tx(_doc,"{getTrDisableImmuMove(([t]))}");
ad(elt33_,txt3_);
ad(elt32_,elt33_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
ad(elt28_,elt30_);
Element elt34_=el(_doc,"br");
ad(elt28_,elt34_);
ad(elt0_,elt28_);
Element elt35_=el(_doc,"c:if");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("condition","!attackTargetWithTypes.isEmpty()"));
at(elt35_,attrs18_);
Element elt36_=el(_doc,"c:message");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("value","msg_effunprotectfromtypes,attack_target_types"));
at(elt36_,attrs19_);
ad(elt35_,elt36_);
Element elt37_=el(_doc,"ul");
Element elt38_=el(_doc,"c:for");
CustList<Attr> attrs20_=al(2);
attrs20_.add(at("list","attackTargetWithTypes"));
attrs20_.add(at("var","t"));
at(elt38_,attrs20_);
Element elt39_=el(_doc,"li");
Text txt4_=tx(_doc,"{getTrAttackTargetType(([t]))}");
ad(elt39_,txt4_);
ad(elt38_,elt39_);
ad(elt37_,elt38_);
ad(elt35_,elt37_);
Element elt40_=el(_doc,"br");
ad(elt35_,elt40_);
ad(elt0_,elt35_);
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
