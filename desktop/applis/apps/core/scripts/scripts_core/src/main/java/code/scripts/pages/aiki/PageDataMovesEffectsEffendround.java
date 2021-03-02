package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffendround{
private PageDataMovesEffectsEffendround(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_endround"));
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
build1(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"p");
Element elt1_=el(_doc,"c:import");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("page","{effectBean}"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"c:package");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("name","aiki.beans.moves.effects"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"c:class");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("name","EffectBean"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"c:field");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("prepare","$intern.index=index"));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,"c:field");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("prepare","$intern.move=move"));
at(elt5_,attrs4_);
ad(elt3_,elt5_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_effendround,rank"));
at(elt6_,attrs5_);
Element elt7_=el(_doc,"param");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","endRoundRank"));
at(elt7_,attrs6_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt8_=el(_doc,"a");
CustList<Attr> attrs7_=al(2);
attrs7_.add(at("c:command","web/html/endround/endround.html"));
attrs7_.add(at("href",""));
at(elt8_,attrs7_);
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_effendround,endRound"));
at(elt9_,attrs8_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!reasonsEndRound.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"p");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_effendround,reasons"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,"ul");
Element elt4_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","reasonsEndRound"));
attrs2_.add(at("var","r"));
attrs2_.add(at("className","java.lang.String"));
at(elt4_,attrs2_);
Element elt5_=el(_doc,"li");
Text txt0_=tx(_doc,"{r}");
ad(elt5_,txt0_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"ul");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(5);
attrs3_.add(at("key","c"));
attrs3_.add(at("map","mapVarsFailEndRound"));
attrs3_.add(at("value","r"));
attrs3_.add(at("keyClassName","java.lang.String"));
attrs3_.add(at("varClassName","java.lang.String"));
at(elt8_,attrs3_);
Element elt9_=el(_doc,"li");
Text txt1_=tx(_doc,"{c} : {r}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt1_,elt7_);
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
