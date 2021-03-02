package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataEndroundMultirelation{
private PageDataEndroundMultirelation(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","end_multirelation"));
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
Element elt1_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!damageByStatus.isEmpty()"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_multirelation,effect"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,"table");
Element elt4_=el(_doc,"thead");
Element elt5_=el(_doc,"tr");
Element elt6_=el(_doc,"th");
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_multirelation,damage_status_key"));
at(elt7_,attrs2_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc,"th");
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_multirelation,damage_status_rate"));
at(elt9_,attrs3_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt10_=el(_doc,"tbody");
Element elt11_=el(_doc,"c:for");
CustList<Attr> attrs4_=al(5);
attrs4_.add(at("key","s"));
attrs4_.add(at("map","damageByStatus"));
attrs4_.add(at("value","r"));
attrs4_.add(at("keyClassName","java.lang.Object"));
attrs4_.add(at("varClassName","r"));
at(elt11_,attrs4_);
Element elt12_=el(_doc,"tr");
Element elt13_=el(_doc,"td");
Element elt14_=el(_doc,"a");
CustList<Attr> attrs5_=al(2);
attrs5_.add(at("c:command","$clickDamageStatus({index},{([s])})"));
attrs5_.add(at("href",""));
at(elt14_,attrs5_);
Text txt0_=tx(_doc,"{getTrDamageStatus(([s]))}");
ad(elt14_,txt0_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
Element elt15_=el(_doc,"td");
Text txt1_=tx(_doc,"{r}");
ad(elt15_,txt1_);
ad(elt12_,elt15_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt3_,elt10_);
ad(elt1_,elt3_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
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
