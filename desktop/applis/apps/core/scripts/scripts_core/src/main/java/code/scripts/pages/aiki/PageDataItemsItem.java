package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsItem{
private PageDataItemsItem(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","item"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/items.css"));
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
Element elt1_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","$clickItems"));
attrs0_.add(at("href",""));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_item,items"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"br");
ad(elt0_,elt3_);
Text txt0_=tx(_doc,"{displayName}");
ad(elt0_,txt0_);
Element elt4_=el(_doc,"c:img");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("src","{itemImage}"));
at(elt4_,attrs2_);
ad(elt0_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt0_,elt5_);
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("quoted",""));
attrs3_.add(at("value","msg_item,item_type"));
at(elt6_,attrs3_);
Element elt7_=el(_doc,"param");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","description"));
at(elt7_,attrs4_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt8_=el(_doc,"br");
ad(elt0_,elt8_);
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_item,price"));
at(elt9_,attrs5_);
Element elt10_=el(_doc,"param");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","displayName"));
at(elt10_,attrs6_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,"param");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","price"));
at(elt11_,attrs7_);
ad(elt9_,elt11_);
ad(elt0_,elt9_);
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
