package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMapElementsPokemonTeam{
private PageDataMapElementsPokemonTeam(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","pk_team"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"body");
build0(elt1_,_doc);
build1(elt1_,_doc);
build2(elt1_,_doc);
build3(elt1_,_doc);
build4(elt1_,_doc);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"b");
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,foe_team"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,reward"));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc,"{reward}");
ad(_body,txt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,multiplicity"));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc,"{multiplicity}");
ad(_body,txt0_);
Element elt1_=el(_doc,"br");
ad(_body,elt1_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("className","aiki.map.pokemon.PkTrainer"));
attrs0_.add(at("list","team"));
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
attrs3_.add(at("c:command","$clickName({noFight},{([p])})"));
at(elt5_,attrs3_);
Text txt0_=tx(_doc,"{getName(([p]))}");
ad(elt5_,txt0_);
ad(elt0_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt0_,elt6_);
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_pokemon_npc,level"));
at(elt7_,attrs4_);
ad(elt0_,elt7_);
Text txt1_=tx(_doc,"{p.getLevel()}");
ad(elt0_,txt1_);
Element elt8_=el(_doc,"br");
ad(elt0_,elt8_);
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_pokemon_npc,ability"));
at(elt9_,attrs5_);
ad(elt0_,elt9_);
Element elt10_=el(_doc,"a");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("c:command","$clickAbility({noFight},{([p])})"));
at(elt10_,attrs6_);
Text txt2_=tx(_doc,"{getAbility(([p]))}");
ad(elt10_,txt2_);
ad(elt0_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt0_,elt11_);
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_pokemon_npc,item"));
at(elt12_,attrs7_);
ad(elt0_,elt12_);
Element elt13_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","!p.getItem().isEmpty()"));
at(elt13_,attrs8_);
Element elt14_=el(_doc,"a");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("c:command","$clickItem({noFight},{([p])})"));
at(elt14_,attrs9_);
Text txt3_=tx(_doc,"{getItem(([p]))}");
ad(elt14_,txt3_);
ad(elt13_,elt14_);
Element elt15_=el(_doc,"br");
ad(elt13_,elt15_);
ad(elt0_,elt13_);
Element elt16_=el(_doc,"c:if");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("condition","p.getItem().isEmpty()"));
at(elt16_,attrs10_);
Element elt17_=el(_doc,"c:message");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","msg_pokemon_npc,item_no"));
at(elt17_,attrs11_);
ad(elt16_,elt17_);
Element elt18_=el(_doc,"br");
ad(elt16_,elt18_);
ad(elt0_,elt16_);
Element elt19_=el(_doc,"c:message");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("value","msg_pokemon_npc,moves"));
at(elt19_,attrs12_);
ad(elt0_,elt19_);
Element elt20_=el(_doc,"ul");
Element elt21_=el(_doc,"c:for");
CustList<Attr> attrs13_=al(2);
attrs13_.add(at("list","p.getMoves()"));
attrs13_.add(at("var","m"));
at(elt21_,attrs13_);
Element elt22_=el(_doc,"li");
Element elt23_=el(_doc,"a");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("c:command","$clickMove({noFight},{([p])},{([m])})"));
at(elt23_,attrs14_);
Text txt4_=tx(_doc,"{getMove(([p]),([m]))}");
ad(elt23_,txt4_);
ad(elt22_,elt23_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt0_,elt20_);
Element elt24_=el(_doc,"br");
ad(elt0_,elt24_);
Element elt25_=el(_doc,"br");
ad(elt0_,elt25_);
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
