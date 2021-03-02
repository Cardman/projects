package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataPokemonEvolutionsEvohappy{
private PageDataPokemonEvolutionsEvohappy(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","evo_happy"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/pokedex.css"));
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
Element elt0_=el(_doc,"tr");
Element elt1_=el(_doc,"td");
Element elt2_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","$clickEvo({index})"));
attrs0_.add(at("href",""));
at(elt2_,attrs0_);
Text txt0_=tx(_doc,"{displayName}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"td");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_evohappiness,happy"));
at(elt4_,attrs1_);
Element elt5_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","displayBase"));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"param");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","min"));
at(elt6_,attrs3_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
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
