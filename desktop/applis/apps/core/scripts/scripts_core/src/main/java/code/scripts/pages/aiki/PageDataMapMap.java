package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMapMap{
private PageDataMapMap(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","game_map"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelmap,title_map"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,"link");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("href","web/css/pokedex.css"));
attrs2_.add(at("rel","stylesheet"));
attrs2_.add(at("type","text/css"));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"body");
build0(elt5_,_doc);
build1(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","web/html/index.html"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelmap,index"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("className","aiki.beans.facade.map.dto.PlaceIndex"));
attrs0_.add(at("list","places"));
attrs0_.add(at("var","p"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Text txt0_=tx(_doc,"{p.getPlace().getName()}");
ad(elt2_,txt0_);
Element elt3_=el(_doc,"br");
ad(elt2_,elt3_);
Element elt4_=el(_doc,"c:if");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("condition","isMultiLayer(([p]))"));
at(elt4_,attrs1_);
Element elt5_=el(_doc,"ul");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(2);
attrs2_.add(at("list","layers(([p]))"));
attrs2_.add(at("var","l"));
at(elt6_,attrs2_);
Element elt7_=el(_doc,"li");
Element elt8_=el(_doc,"a");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("c:command","$clickLevel({p.index},{([l])})"));
at(elt8_,attrs3_);
Text txt1_=tx(_doc,"{([l])}");
ad(elt8_,txt1_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt2_,elt4_);
Element elt9_=el(_doc,"c:if");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("condition","!isMultiLayer(([p]))"));
at(elt9_,attrs4_);
Element elt10_=el(_doc,"a");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("c:command","$clickLevel({p.index},0)"));
at(elt10_,attrs5_);
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_levelmap,goLevel"));
at(elt11_,attrs6_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt2_,elt9_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
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
