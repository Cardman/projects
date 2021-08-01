package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMapElementsLegPk{
private PageDataMapElementsLegPk(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","leg_pk"));
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
attrs2_.add(at("value","msg_levelmap,title_leg"));
at(elt4_,attrs2_);
Element elt5_=el(_doc,"param");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","getName()"));
at(elt5_,attrs3_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt6_=el(_doc,"body");
build0(elt6_,_doc);
build1(elt6_,_doc);
build2(elt6_,_doc);
build3(elt6_,_doc);
build4(elt6_,_doc);
build5(elt6_,_doc);
build6(elt6_,_doc);
build7(elt6_,_doc);
build8(elt6_,_doc);
build9(elt6_,_doc);
build10(elt6_,_doc);
build11(elt6_,_doc);
build12(elt6_,_doc);
build13(elt6_,_doc);
build14(elt6_,_doc);
build15(elt6_,_doc);
build16(elt6_,_doc);
build17(elt6_,_doc);
build18(elt6_,_doc);
build19(elt6_,_doc);
build20(elt6_,_doc);
build21(elt6_,_doc);
build22(elt6_,_doc);
ad(elt0_,elt6_);
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
attrs1_.add(at("value","msg_pokemon_npc,index"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","web/html/map/map.html"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon_npc,map"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","web/html/map/level.html"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon_npc,level"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,"c:img");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("src","{getImage()}"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,name"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","$clickName"));
at(elt0_,attrs0_);
Text txt0_=tx(_doc,"{getName()}");
ad(elt0_,txt0_);
ad(_body,elt0_);
Element elt1_=el(_doc,"br");
ad(_body,elt1_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,gender"));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc,"{getGender()}");
ad(_body,txt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,level"));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc,"{getLevel()}");
ad(_body,txt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,ability"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","$clickAbility"));
at(elt0_,attrs0_);
Text txt0_=tx(_doc,"{getAbility()}");
ad(elt0_,txt0_);
ad(_body,elt0_);
Element elt1_=el(_doc,"br");
ad(_body,elt1_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,item"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!isEmpty(pokemon.getItem())"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"a");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("c:command","$clickItem"));
at(elt1_,attrs1_);
Text txt0_=tx(_doc,"{getItem()}");
ad(elt1_,txt0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"br");
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","isEmpty(pokemon.getItem())"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon_npc,item_no"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"br");
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,moves"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","getMovesAtLevel()"));
attrs0_.add(at("var","m"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Element elt3_=el(_doc,"a");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("c:command","$clickMove({([m])})"));
at(elt3_,attrs1_);
Text txt0_=tx(_doc,"{getMove(([m]))}");
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
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
