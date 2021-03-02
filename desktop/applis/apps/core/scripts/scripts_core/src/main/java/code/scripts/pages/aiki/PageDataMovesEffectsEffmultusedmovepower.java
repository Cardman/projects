package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffmultusedmovepower{
private PageDataMovesEffectsEffmultusedmovepower(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_multusedmovepower"));
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
Element elt0_=el(_doc,"p");
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_effmultusedmovepower,effect"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:import");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("page","{effectBean}"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"c:package");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("name","aiki.beans.moves.effects"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"c:class");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("name","EffectBean"));
at(elt4_,attrs3_);
Element elt5_=el(_doc,"c:field");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("prepare","$intern.index=index"));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"c:field");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("prepare","$intern.move=move"));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_effmultusedmovepower,mult_power"));
at(elt7_,attrs6_);
ad(elt0_,elt7_);
Element elt8_=el(_doc,"table");
Element elt9_=el(_doc,"thead");
Element elt10_=el(_doc,"tr");
Element elt11_=el(_doc,"th");
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_effmultusedmovepower,mult_power_type"));
at(elt12_,attrs7_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc,"th");
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_effmultusedmovepower,mult_power_rate"));
at(elt14_,attrs8_);
ad(elt13_,elt14_);
ad(elt10_,elt13_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt15_=el(_doc,"tbody");
Element elt16_=el(_doc,"c:for");
CustList<Attr> attrs9_=al(5);
attrs9_.add(at("key","t"));
attrs9_.add(at("map","multMovePowerFctType"));
attrs9_.add(at("value","r"));
attrs9_.add(at("keyClassName","java.lang.Object"));
attrs9_.add(at("varClassName","r"));
at(elt16_,attrs9_);
Element elt17_=el(_doc,"tr");
Element elt18_=el(_doc,"td");
Text txt0_=tx(_doc,"{getTrType(([t]))}");
ad(elt18_,txt0_);
ad(elt17_,elt18_);
Element elt19_=el(_doc,"td");
Text txt1_=tx(_doc,"{r}");
ad(elt19_,txt1_);
ad(elt17_,elt19_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt8_,elt15_);
ad(elt0_,elt8_);
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
