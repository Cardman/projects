package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesMoveline{
private PageDataMovesMoveline(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","move_line"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/moves.css"));
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
attrs0_.add(at("c:command","$clickMove({index})"));
attrs0_.add(at("href",""));
at(elt2_,attrs0_);
Text txt0_=tx(_doc,"{displayName}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"td");
Text txt1_=tx(_doc,"{pp}");
ad(elt3_,txt1_);
ad(elt0_,elt3_);
Element elt4_=el(_doc,"td");
Element elt5_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("list","types"));
attrs1_.add(at("var","t"));
attrs1_.add(at("className","java.lang.String"));
at(elt5_,attrs1_);
Text txt2_=tx(_doc,"{t} -");
ad(elt5_,txt2_);
ad(elt4_,elt5_);
ad(elt0_,elt4_);
Element elt6_=el(_doc,"td");
Text txt3_=tx(_doc,"{category}");
ad(elt6_,txt3_);
ad(elt0_,elt6_);
Element elt7_=el(_doc,"td");
Element elt8_=el(_doc,"c:if");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("condition","moveLine.isDamageMove()"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_moves,damaging"));
at(elt9_,attrs3_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
Element elt10_=el(_doc,"c:else");
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_moves,status"));
at(elt11_,attrs4_);
ad(elt10_,elt11_);
ad(elt7_,elt10_);
ad(elt0_,elt7_);
Element elt12_=el(_doc,"td");
Element elt13_=el(_doc,"c:if");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("condition","!moveLine.isDamageMove()"));
at(elt13_,attrs5_);
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_moves,status_indirect"));
at(elt14_,attrs6_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
Element elt15_=el(_doc,"c:elseif");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("condition","moveLine.isDirect()"));
at(elt15_,attrs7_);
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_moves,damaging_direct"));
at(elt16_,attrs8_);
ad(elt15_,elt16_);
ad(elt12_,elt15_);
Element elt17_=el(_doc,"c:else");
Element elt18_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_moves,damaging_indirect"));
at(elt18_,attrs9_);
ad(elt17_,elt18_);
ad(elt12_,elt17_);
ad(elt0_,elt12_);
Element elt19_=el(_doc,"td");
Text txt4_=tx(_doc,"{priority}");
ad(elt19_,txt4_);
ad(elt0_,elt19_);
Element elt20_=el(_doc,"td");
Text txt5_=tx(_doc,"{accuracy}");
ad(elt20_,txt5_);
ad(elt0_,elt20_);
Element elt21_=el(_doc,"td");
Text txt6_=tx(_doc,"{power}");
ad(elt21_,txt6_);
ad(elt0_,elt21_);
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
