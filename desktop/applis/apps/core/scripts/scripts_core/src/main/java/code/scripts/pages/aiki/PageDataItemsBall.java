package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsBall{
private PageDataItemsBall(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","ball"));
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
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_ball,rate_catching"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Text txt0_=tx(_doc,"{catchingRate}");
ad(elt0_,txt0_);
Element elt2_=el(_doc,"c:if");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("condition","!mapVars.isEmpty()"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"ul");
Element elt4_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(5);
attrs2_.add(at("key","k"));
attrs2_.add(at("map","mapVars"));
attrs2_.add(at("value","v"));
attrs2_.add(at("keyClassName","java.lang.String"));
attrs2_.add(at("varClassName","java.lang.String"));
at(elt4_,attrs2_);
Element elt5_=el(_doc,"li");
Text txt1_=tx(_doc,"{k} :");
ad(elt5_,txt1_);
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("quoted",""));
attrs3_.add(at("value","msg_ball,formula"));
at(elt6_,attrs3_);
Element elt7_=el(_doc,"param");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","v"));
at(elt7_,attrs4_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt8_=el(_doc,"br");
ad(elt2_,elt8_);
ad(elt0_,elt2_);
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
