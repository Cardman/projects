package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationSelectpokemon{
private PageDataSimulationSelectpokemon(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","selectpokemon"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelsimu,title_select_pk"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,"link");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("href","web/css/pokedex.css"));
attrs2_.add(at("rel","stylesheet"));
attrs2_.add(at("type","text/css"));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"body");
build0(elt5_,_doc);
build1(elt5_,_doc);
build2(elt5_,_doc);
build3(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","$cancel"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_levelsimu,cancel"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"form");
CustList<Attr> attrs0_=al(4);
attrs0_.add(at("action",""));
attrs0_.add(at("c:command","$search"));
attrs0_.add(at("method","post"));
attrs0_.add(at("name","searching"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokedex,content_name"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"input");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("c:varValue","typedName"));
attrs2_.add(at("name","typedName"));
attrs2_.add(at("type","text"));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt0_,elt3_);
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_pokedex,content_type"));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
Element elt5_=el(_doc,"input");
CustList<Attr> attrs4_=al(3);
attrs4_.add(at("c:varValue","typedType"));
attrs4_.add(at("name","typedType"));
attrs4_.add(at("type","text"));
at(elt5_,attrs4_);
ad(elt0_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt0_,elt6_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_pokedex,content_type_whole"));
at(elt7_,attrs5_);
ad(elt0_,elt7_);
Element elt8_=el(_doc,"input");
CustList<Attr> attrs6_=al(3);
attrs6_.add(at("c:varValue","wholeWord"));
attrs6_.add(at("name","wholeWord"));
attrs6_.add(at("type","checkbox"));
at(elt8_,attrs6_);
ad(elt0_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt0_,elt9_);
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_pokedex,has_evo"));
at(elt10_,attrs7_);
ad(elt0_,elt10_);
Element elt11_=el(_doc,"c:select");
CustList<Attr> attrs8_=al(5);
attrs8_.add(at("default",""));
attrs8_.add(at("map","booleans"));
attrs8_.add(at("name","hasEvo"));
attrs8_.add(at("update",""));
attrs8_.add(at("varValue","hasEvo"));
at(elt11_,attrs8_);
ad(elt0_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt0_,elt12_);
Element elt13_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_pokedex,is_evo"));
at(elt13_,attrs9_);
ad(elt0_,elt13_);
Element elt14_=el(_doc,"c:select");
CustList<Attr> attrs10_=al(5);
attrs10_.add(at("default",""));
attrs10_.add(at("map","booleans"));
attrs10_.add(at("name","isEvo"));
attrs10_.add(at("update",""));
attrs10_.add(at("varValue","isEvo"));
at(elt14_,attrs10_);
ad(elt0_,elt14_);
Element elt15_=el(_doc,"br");
ad(elt0_,elt15_);
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","msg_pokedex,leg"));
at(elt16_,attrs11_);
ad(elt0_,elt16_);
Element elt17_=el(_doc,"c:select");
CustList<Attr> attrs12_=al(5);
attrs12_.add(at("default",""));
attrs12_.add(at("map","booleans"));
attrs12_.add(at("name","isLeg"));
attrs12_.add(at("update",""));
attrs12_.add(at("varValue","isLeg"));
at(elt17_,attrs12_);
ad(elt0_,elt17_);
Element elt18_=el(_doc,"br");
ad(elt0_,elt18_);
Element elt19_=el(_doc,"input");
CustList<Attr> attrs13_=al(2);
attrs13_.add(at("type","submit"));
attrs13_.add(at("value","OK"));
at(elt19_,attrs13_);
ad(elt0_,elt19_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"caption");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokedex,pokedex"));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"thead");
Element elt4_=el(_doc,"tr");
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokedex,image"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_pokedex,name"));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
Element elt9_=el(_doc,"th");
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_pokedex,types"));
at(elt10_,attrs3_);
ad(elt9_,elt10_);
ad(elt4_,elt9_);
Element elt11_=el(_doc,"th");
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_pokedex,evos"));
at(elt12_,attrs4_);
ad(elt11_,elt12_);
ad(elt4_,elt11_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt13_=el(_doc,"tbody");
Element elt14_=el(_doc,"c:for");
CustList<Attr> attrs5_=al(3);
attrs5_.add(at("className","aiki.beans.facade.dto.PokemonLine"));
attrs5_.add(at("list","pokedex"));
attrs5_.add(at("var","d"));
at(elt14_,attrs5_);
Element elt15_=el(_doc,"tr");
Element elt16_=el(_doc,"td");
Element elt17_=el(_doc,"c:img");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("src","{getMiniImage(([d]))}"));
at(elt17_,attrs6_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
Element elt18_=el(_doc,"td");
Element elt19_=el(_doc,"a");
CustList<Attr> attrs7_=al(2);
attrs7_.add(at("c:command","$clickLink({([d])})"));
attrs7_.add(at("href",""));
at(elt19_,attrs7_);
Text txt0_=tx(_doc,"{d.displayName}");
ad(elt19_,txt0_);
ad(elt18_,elt19_);
ad(elt15_,elt18_);
Element elt20_=el(_doc,"td");
Element elt21_=el(_doc,"c:for");
CustList<Attr> attrs8_=al(3);
attrs8_.add(at("list","d.types"));
attrs8_.add(at("var","t"));
attrs8_.add(at("className","java.lang.String"));
at(elt21_,attrs8_);
Text txt1_=tx(_doc,"{t} -");
ad(elt21_,txt1_);
ad(elt20_,elt21_);
ad(elt15_,elt20_);
Element elt22_=el(_doc,"td");
Text txt2_=tx(_doc,"{d.evolutions}");
ad(elt22_,txt2_);
ad(elt15_,elt22_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
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
