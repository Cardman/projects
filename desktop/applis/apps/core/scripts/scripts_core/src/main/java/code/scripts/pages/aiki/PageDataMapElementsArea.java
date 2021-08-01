package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMapElementsArea{
private PageDataMapElementsArea(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","area"));
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
attrs2_.add(at("value","msg_levelmap,title_area"));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"body");
build0(elt5_,_doc);
build1(elt5_,_doc);
build2(elt5_,_doc);
build3(elt5_,_doc);
build4(elt5_,_doc);
build5(elt5_,_doc);
build6(elt5_,_doc);
build7(elt5_,_doc);
build8(elt5_,_doc);
build9(elt5_,_doc);
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
attrs1_.add(at("value","msg_levelmap,index"));
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
attrs1_.add(at("value","msg_levelmap,map"));
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
attrs1_.add(at("value","msg_levelmap,level"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
Text txt0_=tx(_doc,"{area.getAvgNbSteps()}");
ad(_body,txt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("className","aiki.map.pokemon.WildPk"));
attrs0_.add(at("list","area.getWildPokemon()"));
attrs0_.add(at("var","p"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"br");
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:img");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("src","{getImage(([p]))}"));
at(elt2_,attrs1_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt0_,elt3_);
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_pokemon_npc,name"));
at(elt4_,attrs2_);
ad(elt0_,elt4_);
Element elt5_=el(_doc,"a");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("c:command","$clickName({([p])})"));
at(elt5_,attrs3_);
Text txt0_=tx(_doc,"{getName(([p]))}");
ad(elt5_,txt0_);
ad(elt0_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt0_,elt6_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_pokemon_npc,gender"));
at(elt7_,attrs4_);
ad(elt0_,elt7_);
Text txt1_=tx(_doc,"{getGender(([p]))}");
ad(elt0_,txt1_);
Element elt8_=el(_doc,"br");
ad(elt0_,elt8_);
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_pokemon_npc,level"));
at(elt9_,attrs5_);
ad(elt0_,elt9_);
Text txt2_=tx(_doc,"{p.getLevel()}");
ad(elt0_,txt2_);
Element elt10_=el(_doc,"br");
ad(elt0_,elt10_);
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_pokemon_npc,ability"));
at(elt11_,attrs6_);
ad(elt0_,elt11_);
Element elt12_=el(_doc,"a");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("c:command","$clickAbility({([p])})"));
at(elt12_,attrs7_);
Text txt3_=tx(_doc,"{getAbility(([p]))}");
ad(elt12_,txt3_);
ad(elt0_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt0_,elt13_);
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_pokemon_npc,item"));
at(elt14_,attrs8_);
ad(elt0_,elt14_);
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("condition","!isEmpty(p.getItem())"));
at(elt15_,attrs9_);
Element elt16_=el(_doc,"a");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("c:command","$clickItem({([p])})"));
at(elt16_,attrs10_);
Text txt4_=tx(_doc,"{getItem(([p]))}");
ad(elt16_,txt4_);
ad(elt15_,elt16_);
Element elt17_=el(_doc,"br");
ad(elt15_,elt17_);
ad(elt0_,elt15_);
Element elt18_=el(_doc,"c:if");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("condition","isEmpty(p.getItem())"));
at(elt18_,attrs11_);
Element elt19_=el(_doc,"c:message");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("value","msg_pokemon_npc,item_no"));
at(elt19_,attrs12_);
ad(elt18_,elt19_);
Element elt20_=el(_doc,"br");
ad(elt18_,elt20_);
ad(elt0_,elt18_);
Element elt21_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_pokemon_npc,moves"));
at(elt21_,attrs13_);
ad(elt0_,elt21_);
Element elt22_=el(_doc,"ul");
Element elt23_=el(_doc,"c:for");
CustList<Attr> attrs14_=al(2);
attrs14_.add(at("list","getMovesAtLevel(([p]))"));
attrs14_.add(at("var","m"));
at(elt23_,attrs14_);
Element elt24_=el(_doc,"li");
Element elt25_=el(_doc,"a");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("c:command","$clickMove({([p])},{([m])})"));
at(elt25_,attrs15_);
Text txt5_=tx(_doc,"{getMove(([p]),([m]))}");
ad(elt25_,txt5_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt0_,elt22_);
Element elt26_=el(_doc,"br");
ad(elt0_,elt26_);
Element elt27_=el(_doc,"br");
ad(elt0_,elt27_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,"hr");
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("className","aiki.map.pokemon.WildPk"));
attrs0_.add(at("list","area.getWildPokemonFishing()"));
attrs0_.add(at("var","p"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"br");
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:img");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("src","{getImageFishing(([p]))}"));
at(elt2_,attrs1_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt0_,elt3_);
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_pokemon_npc,name"));
at(elt4_,attrs2_);
ad(elt0_,elt4_);
Element elt5_=el(_doc,"a");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("c:command","$clickNameFishing({([p])})"));
at(elt5_,attrs3_);
Text txt0_=tx(_doc,"{getNameFishing(([p]))}");
ad(elt5_,txt0_);
ad(elt0_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt0_,elt6_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_pokemon_npc,gender"));
at(elt7_,attrs4_);
ad(elt0_,elt7_);
Text txt1_=tx(_doc,"{getGender(([p]))}");
ad(elt0_,txt1_);
Element elt8_=el(_doc,"br");
ad(elt0_,elt8_);
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_pokemon_npc,level"));
at(elt9_,attrs5_);
ad(elt0_,elt9_);
Text txt2_=tx(_doc,"{p.getLevel()}");
ad(elt0_,txt2_);
Element elt10_=el(_doc,"br");
ad(elt0_,elt10_);
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_pokemon_npc,ability"));
at(elt11_,attrs6_);
ad(elt0_,elt11_);
Element elt12_=el(_doc,"a");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("c:command","$clickAbilityFishing({([p])})"));
at(elt12_,attrs7_);
Text txt3_=tx(_doc,"{getAbilityFishing(([p]))}");
ad(elt12_,txt3_);
ad(elt0_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt0_,elt13_);
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_pokemon_npc,item"));
at(elt14_,attrs8_);
ad(elt0_,elt14_);
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("condition","!isEmpty(p.getItem())"));
at(elt15_,attrs9_);
Element elt16_=el(_doc,"a");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("c:command","$clickItemFishing({([p])})"));
at(elt16_,attrs10_);
Text txt4_=tx(_doc,"{getItemFishing(([p]))}");
ad(elt16_,txt4_);
ad(elt15_,elt16_);
Element elt17_=el(_doc,"br");
ad(elt15_,elt17_);
ad(elt0_,elt15_);
Element elt18_=el(_doc,"c:if");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("condition","isEmpty(p.getItem())"));
at(elt18_,attrs11_);
Element elt19_=el(_doc,"c:message");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("value","msg_pokemon_npc,item_no"));
at(elt19_,attrs12_);
ad(elt18_,elt19_);
Element elt20_=el(_doc,"br");
ad(elt18_,elt20_);
ad(elt0_,elt18_);
Element elt21_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_pokemon_npc,moves"));
at(elt21_,attrs13_);
ad(elt0_,elt21_);
Element elt22_=el(_doc,"ul");
Element elt23_=el(_doc,"c:for");
CustList<Attr> attrs14_=al(2);
attrs14_.add(at("list","getMovesAtLevelFishing(([p]))"));
attrs14_.add(at("var","m"));
at(elt23_,attrs14_);
Element elt24_=el(_doc,"li");
Element elt25_=el(_doc,"a");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("c:command","$clickMoveFishing({([p])},{([m])})"));
at(elt25_,attrs15_);
Text txt5_=tx(_doc,"{getMoveFishing(([p]),([m]))}");
ad(elt25_,txt5_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt0_,elt22_);
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
