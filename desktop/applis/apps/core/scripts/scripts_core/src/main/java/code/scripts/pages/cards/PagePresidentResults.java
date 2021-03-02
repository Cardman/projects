package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PagePresidentResults{
private PagePresidentResults(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:bean","results"));
attrs0_.add(at("xmlns:c","javahtml"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg,results"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,"link");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("href","resources_cards/css/president.css"));
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
Element elt0_=el(_doc,"table");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("border","1"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"caption");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg,ranks"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"thead");
Element elt4_=el(_doc,"tr");
Element elt5_=el(_doc,"td");
ad(elt4_,elt5_);
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("var","p"));
attrs2_.add(at("list","nicknames"));
attrs2_.add(at("className","java.lang.String"));
at(elt6_,attrs2_);
Element elt7_=el(_doc,"td");
Text txt0_=tx(_doc,"{p}");
ad(elt7_,txt0_);
ad(elt6_,elt7_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt8_=el(_doc,"tbody");
Element elt9_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(3);
attrs3_.add(at("var","l"));
attrs3_.add(at("list","linesDeal"));
attrs3_.add(at("className","cards.president.beans.LineDeal"));
at(elt9_,attrs3_);
Element elt10_=el(_doc,"tr");
Element elt11_=el(_doc,"td");
Text txt1_=tx(_doc,"{l.number}");
ad(elt11_,txt1_);
ad(elt10_,elt11_);
Element elt12_=el(_doc,"c:for");
CustList<Attr> attrs4_=al(3);
attrs4_.add(at("var","s"));
attrs4_.add(at("list","l.scores"));
attrs4_.add(at("className","java.lang.Long"));
at(elt12_,attrs4_);
Element elt13_=el(_doc,"td");
Text txt2_=tx(_doc,"{s}");
ad(elt13_,txt2_);
ad(elt12_,elt13_);
ad(elt10_,elt12_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
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
