package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationSimulationlevel{
private PageDataSimulationSimulationlevel(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","level_simu"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/simulation.css"));
attrs1_.add(at("rel","stylesheet"));
attrs1_.add(at("type","text/css"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,"title");
Element elt4_=el(_doc,"c:if");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("condition","possibleMultiLayer"));
at(elt4_,attrs2_);
Element elt5_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_levelsimu,title_level_place"));
at(elt5_,attrs3_);
Element elt6_=el(_doc,"param");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","placeName"));
at(elt6_,attrs4_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,"param");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","levelIndex"));
at(elt7_,attrs5_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt8_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","!possibleMultiLayer"));
at(elt8_,attrs6_);
Element elt9_=el(_doc,"c:if");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("condition","outside"));
at(elt9_,attrs7_);
Element elt10_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","road"));
at(elt10_,attrs8_);
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_levelsimu,title_out_road"));
at(elt11_,attrs9_);
Element elt12_=el(_doc,"param");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("value","placeName"));
at(elt12_,attrs10_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt13_=el(_doc,"c:if");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("condition","!road"));
at(elt13_,attrs11_);
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("value","msg_levelsimu,title_out_city"));
at(elt14_,attrs12_);
Element elt15_=el(_doc,"param");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","placeName"));
at(elt15_,attrs13_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt9_,elt13_);
ad(elt8_,elt9_);
Element elt16_=el(_doc,"c:if");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("condition","!outside"));
at(elt16_,attrs14_);
Element elt17_=el(_doc,"c:if");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("condition","gym"));
at(elt17_,attrs15_);
Element elt18_=el(_doc,"c:message");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("value","msg_levelsimu,title_gym"));
at(elt18_,attrs16_);
Element elt19_=el(_doc,"param");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("value","placeName"));
at(elt19_,attrs17_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
Element elt20_=el(_doc,"c:if");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("condition","pokemonCenter"));
at(elt20_,attrs18_);
Element elt21_=el(_doc,"c:message");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("value","msg_levelsimu,title_pk_center"));
at(elt21_,attrs19_);
Element elt22_=el(_doc,"param");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("value","placeName"));
at(elt22_,attrs20_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt16_,elt20_);
ad(elt8_,elt16_);
ad(elt3_,elt8_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt23_=el(_doc,"body");
build0(elt23_,_doc);
build1(elt23_,_doc);
build2(elt23_,_doc);
build3(elt23_,_doc);
build4(elt23_,_doc);
ad(elt0_,elt23_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"form");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("action",""));
attrs0_.add(at("c:command","web/html/simulation/simulationlevel.html"));
attrs0_.add(at("method","post"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelsimu,no_fight"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"input");
CustList<Attr> attrs2_=al(4);
attrs2_.add(at("c:varValue","noFight"));
attrs2_.add(at("name","noFight"));
attrs2_.add(at("type","text"));
attrs2_.add(at("value","noFight"));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt0_,elt3_);
Element elt4_=el(_doc,"input");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("type","submit"));
attrs3_.add(at("value","OK"));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","$cancel"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelsimu,cancel"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"map");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("width","{getMapWidth()}"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(5);
attrs1_.add(at("key","p"));
attrs1_.add(at("map","tiles"));
attrs1_.add(at("value","b"));
attrs1_.add(at("keyClassName","java.lang.Object"));
attrs1_.add(at("varClassName","java.lang.String"));
at(elt1_,attrs1_);
Element elt2_=el(_doc,"a");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("c:command","$clickTile({([p])})"));
at(elt2_,attrs2_);
Element elt3_=el(_doc,"c:img");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("border","0"));
attrs3_.add(at("src","{b}"));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
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
