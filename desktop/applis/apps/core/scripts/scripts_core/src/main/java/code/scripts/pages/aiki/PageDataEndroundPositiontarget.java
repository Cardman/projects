package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataEndroundPositiontarget{
private PageDataEndroundPositiontarget(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","end_positiontarget"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/abilities.css"));
attrs1_.add(at("rel","stylesheet"));
attrs1_.add(at("type","text/css"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"body");
build0(elt3_,_doc);
build1(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"c:import");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("page","{endRoundHtml}"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:package");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("name","aiki.beans.endround"));
at(elt1_,attrs1_);
Element elt2_=el(_doc,"c:class");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("name","EffectEndRoundBean"));
at(elt2_,attrs2_);
Element elt3_=el(_doc,"c:field");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("prepare","$intern.index=index"));
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
attrs0_.add(at("value","msg_positiontarget,effect"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:if");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("condition","!getMovesSameCategory().isEmpty()"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_positiontarget,anticipe"));
at(elt3_,attrs2_);
ad(elt2_,elt3_);
Element elt4_=el(_doc,"ul");
Element elt5_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("list","getMovesSameCategory()"));
attrs3_.add(at("var","m"));
at(elt5_,attrs3_);
Element elt6_=el(_doc,"li");
Element elt7_=el(_doc,"a");
CustList<Attr> attrs4_=al(2);
attrs4_.add(at("c:command","$clickTargetRelationMove({([m])})"));
attrs4_.add(at("href",""));
at(elt7_,attrs4_);
Text txt0_=tx(_doc,"{getTrTargetRelationMove(([m]))}");
ad(elt7_,txt0_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt2_,elt4_);
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
