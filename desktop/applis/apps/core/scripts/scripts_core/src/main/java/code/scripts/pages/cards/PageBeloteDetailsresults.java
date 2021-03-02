package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageBeloteDetailsresults{
private PageBeloteDetailsresults(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:bean","details"));
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
attrs2_.add(at("href","resources_cards/css/belote.css"));
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
Element elt0_=el(_doc,"h1");
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg,detail"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("var","p"));
attrs0_.add(at("list","declaring"));
attrs0_.add(at("className","cards.belote.beans.SumDeclaringPlayer"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg,decl_player"));
at(elt3_,attrs1_);
Element elt4_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","p.nickname"));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,"param");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","p.statut"));
at(elt5_,attrs3_);
ad(elt3_,elt5_);
ad(elt2_,elt3_);
Element elt6_=el(_doc,"c:if");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("condition","p.declaring.isEmpty()"));
at(elt6_,attrs4_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg,nothing"));
at(elt7_,attrs5_);
ad(elt6_,elt7_);
ad(elt2_,elt6_);
Element elt8_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","!p.declaring.isEmpty()"));
at(elt8_,attrs6_);
Element elt9_=el(_doc,"br");
ad(elt8_,elt9_);
Element elt10_=el(_doc,"ul");
Element elt11_=el(_doc,"c:for");
CustList<Attr> attrs7_=al(3);
attrs7_.add(at("var","d"));
attrs7_.add(at("list","p.declaring"));
attrs7_.add(at("className","cards.belote.beans.DeclaringPlayerValue"));
at(elt11_,attrs7_);
Element elt12_=el(_doc,"li");
Text txt0_=tx(_doc,"{d.declaring} : {d.value}");
ad(elt12_,txt0_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc,"li");
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg,sum"));
at(elt14_,attrs8_);
ad(elt13_,elt14_);
Text txt1_=tx(_doc,"{p.sum}");
ad(elt13_,txt1_);
ad(elt10_,elt13_);
ad(elt8_,elt10_);
ad(elt2_,elt8_);
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
