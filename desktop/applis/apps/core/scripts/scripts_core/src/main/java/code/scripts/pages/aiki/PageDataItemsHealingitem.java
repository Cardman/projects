package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsHealingitem{
private PageDataItemsHealingitem(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","healingitem"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_item,title"));
at(elt3_,attrs1_);
Element elt4_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","displayName"));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc,"link");
CustList<Attr> attrs3_=al(3);
attrs3_.add(at("href","web/css/items.css"));
attrs3_.add(at("rel","stylesheet"));
attrs3_.add(at("type","text/css"));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc,"body");
build0(elt6_,_doc);
build1(elt6_,_doc);
ad(elt0_,elt6_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"c:import");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("page","{itemBean}"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:package");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("name","aiki.beans.items"));
at(elt1_,attrs1_);
Element elt2_=el(_doc,"c:class");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("name","ItemBean"));
at(elt2_,attrs2_);
Element elt3_=el(_doc,"c:field");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("prepare","$intern.name=name"));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"p");
Element elt1_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","healingTeam"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_healingitem,heal_team"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"c:if");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("condition","!happiness.isEmpty()"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_healingitem,happiness"));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,"table");
Element elt6_=el(_doc,"thead");
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"th");
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_healingitem,happiness_ball"));
at(elt9_,attrs4_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
Element elt10_=el(_doc,"th");
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_healingitem,happiness_boost"));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt7_,elt10_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt12_=el(_doc,"tbody");
Element elt13_=el(_doc,"c:for");
CustList<Attr> attrs6_=al(5);
attrs6_.add(at("key","b"));
attrs6_.add(at("map","happiness"));
attrs6_.add(at("value","w"));
attrs6_.add(at("keyClassName","java.lang.Object"));
attrs6_.add(at("varClassName","java.lang.Short"));
at(elt13_,attrs6_);
Element elt14_=el(_doc,"tr");
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("condition","!isBall(([b]))"));
at(elt15_,attrs7_);
Element elt16_=el(_doc,"td");
Element elt17_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_healingitem,happiness_other_ball"));
at(elt17_,attrs8_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
Element elt18_=el(_doc,"c:if");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("condition","isBall(([b]))"));
at(elt18_,attrs9_);
Element elt19_=el(_doc,"td");
Element elt20_=el(_doc,"a");
CustList<Attr> attrs10_=al(2);
attrs10_.add(at("c:command","$clickHappiness({([b])})"));
attrs10_.add(at("href",""));
at(elt20_,attrs10_);
Text txt0_=tx(_doc,"{getTrHappiness(([b]))}");
ad(elt20_,txt0_);
ad(elt19_,elt20_);
ad(elt18_,elt19_);
ad(elt14_,elt18_);
Element elt21_=el(_doc,"td");
Text txt1_=tx(_doc,"{w}");
ad(elt21_,txt1_);
ad(elt14_,elt21_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt5_,elt12_);
ad(elt3_,elt5_);
Element elt22_=el(_doc,"br");
ad(elt3_,elt22_);
ad(elt0_,elt3_);
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
