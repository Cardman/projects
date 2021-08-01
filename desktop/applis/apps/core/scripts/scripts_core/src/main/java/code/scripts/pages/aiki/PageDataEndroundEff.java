package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataEndroundEff{
private PageDataEndroundEff(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","end"));
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
build2(elt3_,_doc);
build3(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"p");
Element elt1_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!isEmpty(move)"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"a");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("c:command","$clickMove({index})"));
attrs1_.add(at("href",""));
at(elt2_,attrs1_);
Text txt0_=tx(_doc,"{move}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"c:if");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("condition","!isEmpty(ability)"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"a");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("c:command","$clickAbility({index})"));
attrs3_.add(at("href",""));
at(elt4_,attrs3_);
Text txt1_=tx(_doc,"{ability}");
ad(elt4_,txt1_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt5_=el(_doc,"c:if");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("condition","!isEmpty(item)"));
at(elt5_,attrs4_);
Element elt6_=el(_doc,"a");
CustList<Attr> attrs5_=al(2);
attrs5_.add(at("c:command","$clickItem({index})"));
attrs5_.add(at("href",""));
at(elt6_,attrs5_);
Text txt2_=tx(_doc,"{item}");
ad(elt6_,txt2_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
Element elt7_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","!isEmpty(status)"));
at(elt7_,attrs6_);
Element elt8_=el(_doc,"a");
CustList<Attr> attrs7_=al(2);
attrs7_.add(at("c:command","$clickStatus({index})"));
attrs7_.add(at("href",""));
at(elt8_,attrs7_);
Text txt3_=tx(_doc,"{status}");
ad(elt8_,txt3_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","!moves.isEmpty()"));
at(elt9_,attrs8_);
Element elt10_=el(_doc,"ul");
Element elt11_=el(_doc,"c:for");
CustList<Attr> attrs9_=al(2);
attrs9_.add(at("list","moves"));
attrs9_.add(at("var","m"));
at(elt11_,attrs9_);
Element elt12_=el(_doc,"li");
Element elt13_=el(_doc,"a");
CustList<Attr> attrs10_=al(2);
attrs10_.add(at("c:command","$clickMoves({index},{([m])})"));
attrs10_.add(at("href",""));
at(elt13_,attrs10_);
Text txt4_=tx(_doc,"{getTrMoves(([m]))}");
ad(elt13_,txt4_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_event,rank"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","endRoundRank"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
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
