package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationSelectitem{
private PageDataSimulationSelectitem(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","selectitem"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelsimu,title_select_item"));
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
build5(elt5_,_doc);
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
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","$cancelItem"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_simulation,cancel_item"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"form");
CustList<Attr> attrs0_=al(4);
attrs0_.add(at("action",""));
attrs0_.add(at("c:command","$search"));
attrs0_.add(at("method","post"));
attrs0_.add(at("name","searching"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_items,content_name"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"input");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("c:varValue","typedName"));
attrs2_.add(at("name","typedName"));
attrs2_.add(at("type","text"));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt0_,elt3_);
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_items,price_dot"));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
Element elt5_=el(_doc,"input");
CustList<Attr> attrs4_=al(3);
attrs4_.add(at("c:varValue","typedPrice"));
attrs4_.add(at("name","typedPrice"));
attrs4_.add(at("type","text"));
at(elt5_,attrs4_);
ad(elt0_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt0_,elt6_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_items,content"));
at(elt7_,attrs5_);
ad(elt0_,elt7_);
Element elt8_=el(_doc,"input");
CustList<Attr> attrs6_=al(3);
attrs6_.add(at("c:varValue","typedClass"));
attrs6_.add(at("name","typedClass"));
attrs6_.add(at("type","text"));
at(elt8_,attrs6_);
ad(elt0_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt0_,elt9_);
Element elt10_=el(_doc,"c:submit");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("message","msg_simulation,search"));
at(elt10_,attrs7_);
ad(elt0_,elt10_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"caption");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_items,items"));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"thead");
Element elt4_=el(_doc,"tr");
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_items,image"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_items,name"));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
Element elt9_=el(_doc,"th");
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_items,price"));
at(elt10_,attrs3_);
ad(elt9_,elt10_);
ad(elt4_,elt9_);
Element elt11_=el(_doc,"th");
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_items,description"));
at(elt12_,attrs4_);
ad(elt11_,elt12_);
ad(elt4_,elt11_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt13_=el(_doc,"tbody");
Element elt14_=el(_doc,"c:for");
CustList<Attr> attrs5_=al(3);
attrs5_.add(at("className","aiki.beans.facade.dto.ItemLine"));
attrs5_.add(at("list","items"));
attrs5_.add(at("var","d"));
at(elt14_,attrs5_);
Element elt15_=el(_doc,"tr");
Element elt16_=el(_doc,"td");
Element elt17_=el(_doc,"c:img");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("src","{getMiniImage(([d]))}"));
at(elt17_,attrs6_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
Element elt18_=el(_doc,"td");
Element elt19_=el(_doc,"a");
CustList<Attr> attrs7_=al(2);
attrs7_.add(at("c:command","$clickLink({([d])})"));
attrs7_.add(at("href",""));
at(elt19_,attrs7_);
Text txt0_=tx(_doc,"{d.displayName}");
ad(elt19_,txt0_);
ad(elt18_,elt19_);
ad(elt15_,elt18_);
Element elt20_=el(_doc,"td");
Text txt1_=tx(_doc,"{d.price}");
ad(elt20_,txt1_);
ad(elt15_,elt20_);
Element elt21_=el(_doc,"td");
Text txt2_=tx(_doc,"{d.descriptionClass}");
ad(elt21_,txt2_);
ad(elt15_,elt21_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
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
