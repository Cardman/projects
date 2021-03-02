package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffcommonstatistics{
private PageDataMovesEffectsEffcommonstatistics(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_commonstatistics"));
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
attrs0_.add(at("value","msg_effcommonstatistics,effect"));
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
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_effcommonstatistics,common"));
at(elt7_,attrs6_);
ad(elt0_,elt7_);
Element elt8_=el(_doc,"table");
Element elt9_=el(_doc,"thead");
Element elt10_=el(_doc,"tr");
Element elt11_=el(_doc,"th");
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_effcommonstatistics,common_stat"));
at(elt12_,attrs7_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc,"th");
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_effcommonstatistics,common_value"));
at(elt14_,attrs8_);
ad(elt13_,elt14_);
ad(elt10_,elt13_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt15_=el(_doc,"tbody");
Element elt16_=el(_doc,"c:for");
CustList<Attr> attrs9_=al(5);
attrs9_.add(at("key","s"));
attrs9_.add(at("map","commonValue"));
attrs9_.add(at("value","f"));
attrs9_.add(at("keyClassName","java.lang.Object"));
attrs9_.add(at("varClassName","java.lang.String"));
at(elt16_,attrs9_);
Element elt17_=el(_doc,"tr");
Element elt18_=el(_doc,"td");
Text txt0_=tx(_doc,"{getTrStatistic(([s]))}");
ad(elt18_,txt0_);
ad(elt17_,elt18_);
Element elt19_=el(_doc,"td");
Element elt20_=el(_doc,"c:message");
CustList<Attr> attrs10_=al(2);
attrs10_.add(at("quoted",""));
attrs10_.add(at("value","msg_effcommonstatistics,formula"));
at(elt20_,attrs10_);
Element elt21_=el(_doc,"param");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","f"));
at(elt21_,attrs11_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt17_,elt19_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt8_,elt15_);
ad(elt0_,elt8_);
Element elt22_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","!mapVarsCommonStatistics.isEmpty()"));
at(elt22_,attrs12_);
Element elt23_=el(_doc,"ul");
Element elt24_=el(_doc,"c:for");
CustList<Attr> attrs13_=al(5);
attrs13_.add(at("key","c"));
attrs13_.add(at("map","mapVarsCommonStatistics"));
attrs13_.add(at("value","r"));
attrs13_.add(at("keyClassName","java.lang.String"));
attrs13_.add(at("varClassName","java.lang.String"));
at(elt24_,attrs13_);
Element elt25_=el(_doc,"li");
Text txt1_=tx(_doc,"{c} :");
ad(elt25_,txt1_);
Element elt26_=el(_doc,"c:message");
CustList<Attr> attrs14_=al(2);
attrs14_.add(at("quoted",""));
attrs14_.add(at("value","msg_effcommonstatistics,formula"));
at(elt26_,attrs14_);
Element elt27_=el(_doc,"param");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","r"));
at(elt27_,attrs15_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt0_,elt22_);
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
