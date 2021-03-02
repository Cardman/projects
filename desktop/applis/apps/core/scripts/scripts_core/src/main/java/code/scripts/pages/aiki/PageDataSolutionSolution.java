package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSolutionSolution{
private PageDataSolutionSolution(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","solution"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/pokedex.css"));
attrs1_.add(at("rel","stylesheet"));
attrs1_.add(at("type","text/css"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,"title");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_solution,title"));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"body");
build0(elt5_,_doc);
build1(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","web/html/index.html"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_solution,index"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("className","aiki.beans.facade.solution.dto.StepDto"));
attrs0_.add(at("list","steps"));
attrs0_.add(at("var","s"));
at(elt0_,attrs0_);
Text txt0_=tx(_doc,"{([s])}");
ad(elt0_,txt0_);
Element elt1_=el(_doc,"br");
ad(elt0_,elt1_);
Element elt2_=el(_doc,"p");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(5);
attrs1_.add(at("key","i"));
attrs1_.add(at("map","s.getPokemon()"));
attrs1_.add(at("value","g"));
attrs1_.add(at("keyClassName","java.lang.Object"));
attrs1_.add(at("varClassName","ls"));
at(elt3_,attrs1_);
Element elt4_=el(_doc,"table");
Element elt5_=el(_doc,"caption");
Element elt6_=el(_doc,"b");
Text txt1_=tx(_doc,"{getPlace(([s]),([i]))}");
ad(elt6_,txt1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc,"thead");
Element elt8_=el(_doc,"tr");
Element elt9_=el(_doc,"th");
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_solution,image"));
at(elt10_,attrs2_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt11_=el(_doc,"th");
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_solution,name"));
at(elt12_,attrs3_);
ad(elt11_,elt12_);
ad(elt8_,elt11_);
Element elt13_=el(_doc,"th");
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_solution,gender"));
at(elt14_,attrs4_);
ad(elt13_,elt14_);
ad(elt8_,elt13_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
Element elt15_=el(_doc,"tbody");
Element elt16_=el(_doc,"c:for");
CustList<Attr> attrs5_=al(3);
attrs5_.add(at("className","aiki.beans.facade.solution.dto.WildPokemonDto"));
attrs5_.add(at("list","g"));
attrs5_.add(at("var","l"));
at(elt16_,attrs5_);
Element elt17_=el(_doc,"tr");
Element elt18_=el(_doc,"td");
Element elt19_=el(_doc,"c:img");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("src","{l.image}"));
at(elt19_,attrs6_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
Element elt20_=el(_doc,"td");
Text txt2_=tx(_doc,"{l.name}");
ad(elt20_,txt2_);
ad(elt17_,elt20_);
Element elt21_=el(_doc,"td");
Text txt3_=tx(_doc,"{l.gender}");
ad(elt21_,txt3_);
ad(elt17_,elt21_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt4_,elt15_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt22_=el(_doc,"c:if");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("condition","!s.getNames().isEmpty()"));
at(elt22_,attrs7_);
Element elt23_=el(_doc,"ul");
Element elt24_=el(_doc,"c:for");
CustList<Attr> attrs8_=al(3);
attrs8_.add(at("className","aiki.beans.facade.solution.dto.PlaceTrainerDto"));
attrs8_.add(at("list","s.getNames()"));
attrs8_.add(at("var","i"));
at(elt24_,attrs8_);
Element elt25_=el(_doc,"li");
Text txt4_=tx(_doc,"{i.trainer} - {i.place}");
ad(elt25_,txt4_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt2_,elt22_);
ad(elt0_,elt2_);
Element elt26_=el(_doc,"br");
ad(elt0_,elt26_);
Element elt27_=el(_doc,"br");
ad(elt0_,elt27_);
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
