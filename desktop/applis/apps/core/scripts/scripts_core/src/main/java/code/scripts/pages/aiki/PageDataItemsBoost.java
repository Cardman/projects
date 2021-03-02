package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsBoost{
private PageDataItemsBoost(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","boost"));
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
attrs0_.add(at("condition","!winPp.isZero()"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_boost,win_pp"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","winPp"));
at(elt3_,attrs2_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt4_=el(_doc,"c:if");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("condition","!happiness.isEmpty()"));
at(elt4_,attrs3_);
Element elt5_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_boost,happiness"));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"table");
Element elt7_=el(_doc,"thead");
Element elt8_=el(_doc,"tr");
Element elt9_=el(_doc,"th");
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_boost,happiness_ball"));
at(elt10_,attrs5_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt11_=el(_doc,"th");
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_boost,happiness_boost"));
at(elt12_,attrs6_);
ad(elt11_,elt12_);
ad(elt8_,elt11_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
Element elt13_=el(_doc,"tbody");
Element elt14_=el(_doc,"c:for");
CustList<Attr> attrs7_=al(5);
attrs7_.add(at("key","b"));
attrs7_.add(at("map","happiness"));
attrs7_.add(at("value","w"));
attrs7_.add(at("keyClassName","java.lang.Object"));
attrs7_.add(at("varClassName","java.lang.Short"));
at(elt14_,attrs7_);
Element elt15_=el(_doc,"tr");
Element elt16_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","!isBall(([b]))"));
at(elt16_,attrs8_);
Element elt17_=el(_doc,"td");
Element elt18_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_boost,happiness_other_ball"));
at(elt18_,attrs9_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
Element elt19_=el(_doc,"c:if");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("condition","isBall(([b]))"));
at(elt19_,attrs10_);
Element elt20_=el(_doc,"td");
Element elt21_=el(_doc,"a");
CustList<Attr> attrs11_=al(2);
attrs11_.add(at("c:command","$clickHappiness({([b])})"));
attrs11_.add(at("href",""));
at(elt21_,attrs11_);
Text txt0_=tx(_doc,"{getTrHappiness(([b]))}");
ad(elt21_,txt0_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt15_,elt19_);
Element elt22_=el(_doc,"td");
Text txt1_=tx(_doc,"{w}");
ad(elt22_,txt1_);
ad(elt15_,elt22_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt6_,elt13_);
ad(elt4_,elt6_);
Element elt23_=el(_doc,"br");
ad(elt4_,elt23_);
ad(elt0_,elt4_);
Element elt24_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","!evs.isEmpty()"));
at(elt24_,attrs12_);
Element elt25_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_boost,evs"));
at(elt25_,attrs13_);
Element elt26_=el(_doc,"param");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("value","maxEv"));
at(elt26_,attrs14_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
Element elt27_=el(_doc,"table");
Element elt28_=el(_doc,"thead");
Element elt29_=el(_doc,"tr");
Element elt30_=el(_doc,"th");
Element elt31_=el(_doc,"c:message");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","msg_boost,evs_stat"));
at(elt31_,attrs15_);
ad(elt30_,elt31_);
ad(elt29_,elt30_);
Element elt32_=el(_doc,"th");
Element elt33_=el(_doc,"c:message");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("value","msg_boost,evs_boost"));
at(elt33_,attrs16_);
ad(elt32_,elt33_);
ad(elt29_,elt32_);
ad(elt28_,elt29_);
ad(elt27_,elt28_);
Element elt34_=el(_doc,"tbody");
Element elt35_=el(_doc,"c:for");
CustList<Attr> attrs17_=al(5);
attrs17_.add(at("key","s"));
attrs17_.add(at("map","evs"));
attrs17_.add(at("value","w"));
attrs17_.add(at("keyClassName","java.lang.Object"));
attrs17_.add(at("varClassName","java.lang.Short"));
at(elt35_,attrs17_);
Element elt36_=el(_doc,"tr");
Element elt37_=el(_doc,"td");
Text txt2_=tx(_doc,"{getTrEv(([s]))}");
ad(elt37_,txt2_);
ad(elt36_,elt37_);
Element elt38_=el(_doc,"td");
Text txt3_=tx(_doc,"{w}");
ad(elt38_,txt3_);
ad(elt36_,elt38_);
ad(elt35_,elt36_);
ad(elt34_,elt35_);
ad(elt27_,elt34_);
ad(elt24_,elt27_);
Element elt39_=el(_doc,"br");
ad(elt24_,elt39_);
ad(elt0_,elt24_);
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
