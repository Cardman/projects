package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffswitchmovetypes{
private PageDataMovesEffectsEffswitchmovetypes(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_switchmovetypes"));
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
attrs0_.add(at("value","msg_effswitchmovetypes,effect"));
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
attrs6_.add(at("condition","!replacingTypes.isEmpty()"));
at(elt7_,attrs6_);
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_effswitchmovetypes,replacing_types"));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
Element elt9_=el(_doc,"ul");
Element elt10_=el(_doc,"c:for");
CustList<Attr> attrs8_=al(2);
attrs8_.add(at("list","replacingTypes"));
attrs8_.add(at("var","s"));
at(elt10_,attrs8_);
Element elt11_=el(_doc,"li");
Text txt0_=tx(_doc,"{getTrReplacingTypes(([s]))}");
ad(elt11_,txt0_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt7_,elt9_);
Element elt12_=el(_doc,"br");
ad(elt7_,elt12_);
ad(elt0_,elt7_);
Element elt13_=el(_doc,"c:if");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("condition","!changeTypes.isEmpty()"));
at(elt13_,attrs9_);
Element elt14_=el(_doc,"c:if");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("condition","!replacingTypes.isEmpty()"));
at(elt14_,attrs10_);
Element elt15_=el(_doc,"c:message");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","msg_effswitchmovetypes,changing_type_possible"));
at(elt15_,attrs11_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
Element elt16_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","replacingTypes.isEmpty()"));
at(elt16_,attrs12_);
Element elt17_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_effswitchmovetypes,changing_type"));
at(elt17_,attrs13_);
ad(elt16_,elt17_);
ad(elt13_,elt16_);
Element elt18_=el(_doc,"table");
Element elt19_=el(_doc,"thead");
Element elt20_=el(_doc,"tr");
Element elt21_=el(_doc,"th");
Element elt22_=el(_doc,"c:message");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("value","msg_effswitchmovetypes,old_type"));
at(elt22_,attrs14_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
Element elt23_=el(_doc,"th");
Element elt24_=el(_doc,"c:message");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","msg_effswitchmovetypes,new_type"));
at(elt24_,attrs15_);
ad(elt23_,elt24_);
ad(elt20_,elt23_);
ad(elt19_,elt20_);
ad(elt18_,elt19_);
Element elt25_=el(_doc,"tbody");
Element elt26_=el(_doc,"c:for");
CustList<Attr> attrs16_=al(5);
attrs16_.add(at("key","c"));
attrs16_.add(at("map","changeTypes"));
attrs16_.add(at("value","r"));
attrs16_.add(at("keyClassName","java.lang.Object"));
attrs16_.add(at("varClassName","java.lang.String"));
at(elt26_,attrs16_);
Element elt27_=el(_doc,"tr");
Element elt28_=el(_doc,"td");
Text txt1_=tx(_doc,"{getTrChangedTypes(([c]))}");
ad(elt28_,txt1_);
ad(elt27_,elt28_);
Element elt29_=el(_doc,"td");
Text txt2_=tx(_doc,"{r}");
ad(elt29_,txt2_);
ad(elt27_,elt29_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt18_,elt25_);
ad(elt13_,elt18_);
Element elt30_=el(_doc,"br");
ad(elt13_,elt30_);
ad(elt0_,elt13_);
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
