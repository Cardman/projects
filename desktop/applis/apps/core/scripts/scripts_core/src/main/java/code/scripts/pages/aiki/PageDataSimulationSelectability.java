package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationSelectability{
private PageDataSimulationSelectability(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","selectability"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelsimu,title_select_ability"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,"link");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("href","web/css/simulation.css"));
attrs2_.add(at("rel","stylesheet"));
attrs2_.add(at("type","text/css"));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"body");
build0(elt5_,_doc);
build1(elt5_,_doc);
build2(elt5_,_doc);
build3(elt5_,_doc);
build4(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
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
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"form");
CustList<Attr> attrs0_=al(4);
attrs0_.add(at("action",""));
attrs0_.add(at("c:command","$search"));
attrs0_.add(at("method","post"));
attrs0_.add(at("name","searching"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_abilities,content"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"input");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("c:varValue","typedAbility"));
attrs2_.add(at("name","typedAbility"));
attrs2_.add(at("type","text"));
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
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","sortedAbilities"));
attrs0_.add(at("var","a"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Element elt3_=el(_doc,"a");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("c:command","$clickAbility({([a])})"));
attrs1_.add(at("href",""));
at(elt3_,attrs1_);
Text txt0_=tx(_doc,"{getTrAbility(([a]))}");
ad(elt3_,txt0_);
ad(elt2_,elt3_);
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
