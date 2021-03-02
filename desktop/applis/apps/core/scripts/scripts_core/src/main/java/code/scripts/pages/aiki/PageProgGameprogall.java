package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageProgGameprogall{
private PageProgGameprogall(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","progressing"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,titleFull"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,"link");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("href","web_prog/css/difficulty.css"));
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
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","web_prog/html/gameprog.html"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,returnMain"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(5);
attrs0_.add(at("key","k"));
attrs0_.add(at("map","fullFamiliesBase"));
attrs0_.add(at("value","m"));
attrs0_.add(at("keyClassName","java.lang.String"));
attrs0_.add(at("varClassName","ls"));
at(elt0_,attrs0_);
Text txt0_=tx(_doc,"{k}");
ad(elt0_,txt0_);
Element elt1_=el(_doc,"br");
ad(elt0_,elt1_);
Element elt2_=el(_doc,"table");
Element elt3_=el(_doc,"tbody");
Element elt4_=el(_doc,"tr");
Element elt5_=el(_doc,"td");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,notCaughtPkCaughtPart"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("className","ls"));
attrs2_.add(at("list","m"));
attrs2_.add(at("var","l"));
at(elt7_,attrs2_);
Element elt8_=el(_doc,"td");
Element elt9_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("list","l"));
attrs3_.add(at("var","e"));
at(elt9_,attrs3_);
Element elt10_=el(_doc,"c:img");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("src","{getImagePokemonFull(([k]),([l]),([e]))}"));
at(elt10_,attrs4_);
ad(elt9_,elt10_);
Text txt1_=tx(_doc,"{getTrPokemonFull(([k]),([l]),([e]))}");
ad(elt9_,txt1_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt12_=el(_doc,"br");
ad(elt0_,elt12_);
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
