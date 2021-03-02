package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataEndroundSinglerelation{
private PageDataEndroundSinglerelation(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","end_singlerelation"));
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
attrs0_.add(at("value","msg_singlerelation,effect"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_singlerelation,suffered"));
at(elt2_,attrs1_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,"table");
Element elt4_=el(_doc,"thead");
Element elt5_=el(_doc,"tr");
Element elt6_=el(_doc,"th");
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_singlerelation,suffered_rd"));
at(elt7_,attrs2_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc,"th");
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_singlerelation,suffered_rate"));
at(elt9_,attrs3_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt10_=el(_doc,"tbody");
Element elt11_=el(_doc,"c:for");
CustList<Attr> attrs4_=al(5);
attrs4_.add(at("key","s"));
attrs4_.add(at("map","rateDamageFunctionOfNbRounds"));
attrs4_.add(at("value","r"));
attrs4_.add(at("keyClassName","java.lang.Long"));
attrs4_.add(at("varClassName","r"));
at(elt11_,attrs4_);
Element elt12_=el(_doc,"tr");
Element elt13_=el(_doc,"td");
Text txt0_=tx(_doc,"{s}");
ad(elt13_,txt0_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"td");
Text txt1_=tx(_doc,"{r}");
ad(elt14_,txt1_);
ad(elt12_,elt14_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt3_,elt10_);
ad(elt0_,elt3_);
Element elt15_=el(_doc,"br");
ad(elt0_,elt15_);
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_singlerelation,law"));
at(elt16_,attrs5_);
ad(elt0_,elt16_);
Element elt17_=el(_doc,"table");
Element elt18_=el(_doc,"thead");
Element elt19_=el(_doc,"tr");
Element elt20_=el(_doc,"th");
Element elt21_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_singlerelation,law_rd"));
at(elt21_,attrs6_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
Element elt22_=el(_doc,"th");
Element elt23_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_singlerelation,law_value"));
at(elt23_,attrs7_);
ad(elt22_,elt23_);
ad(elt19_,elt22_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
Element elt24_=el(_doc,"tbody");
Element elt25_=el(_doc,"c:for");
CustList<Attr> attrs8_=al(5);
attrs8_.add(at("key","s"));
attrs8_.add(at("map","lawForEnablingEffect"));
attrs8_.add(at("value","r"));
attrs8_.add(at("keyClassName","li"));
attrs8_.add(at("varClassName","r"));
at(elt25_,attrs8_);
Element elt26_=el(_doc,"tr");
Element elt27_=el(_doc,"td");
Text txt2_=tx(_doc,"{s}");
ad(elt27_,txt2_);
ad(elt26_,elt27_);
Element elt28_=el(_doc,"td");
Text txt3_=tx(_doc,"{r}");
ad(elt28_,txt3_);
ad(elt26_,elt28_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt17_,elt24_);
ad(elt0_,elt17_);
Element elt29_=el(_doc,"br");
ad(elt0_,elt29_);
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
