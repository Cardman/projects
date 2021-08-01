package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataGeneralGeneral{
private PageDataGeneralGeneral(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","gereral_data"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_generalhelp,title"));
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
build4(elt5_,_doc);
build5(elt5_,_doc);
build6(elt5_,_doc);
build7(elt5_,_doc);
build8(elt5_,_doc);
build9(elt5_,_doc);
build10(elt5_,_doc);
build11(elt5_,_doc);
build12(elt5_,_doc);
build13(elt5_,_doc);
build14(elt5_,_doc);
build15(elt5_,_doc);
build16(elt5_,_doc);
build17(elt5_,_doc);
build18(elt5_,_doc);
build19(elt5_,_doc);
build20(elt5_,_doc);
build21(elt5_,_doc);
build22(elt5_,_doc);
build23(elt5_,_doc);
build24(elt5_,_doc);
build25(elt5_,_doc);
build26(elt5_,_doc);
build27(elt5_,_doc);
build28(elt5_,_doc);
build29(elt5_,_doc);
build30(elt5_,_doc);
build31(elt5_,_doc);
build32(elt5_,_doc);
build33(elt5_,_doc);
build34(elt5_,_doc);
build35(elt5_,_doc);
build36(elt5_,_doc);
build37(elt5_,_doc);
build38(elt5_,_doc);
build39(elt5_,_doc);
build40(elt5_,_doc);
build41(elt5_,_doc);
build42(elt5_,_doc);
build43(elt5_,_doc);
build44(elt5_,_doc);
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
attrs1_.add(at("value","msg_generalhelp,index"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,goal_0"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"li");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,goal_0_0"));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"li");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_generalhelp,goal_0_1"));
at(elt4_,attrs1_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt5_=el(_doc,"li");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_generalhelp,goal_0_2"));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
Element elt7_=el(_doc,"li");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_generalhelp,goal_0_3"));
at(elt8_,attrs3_);
Element elt9_=el(_doc,"param");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","maxLevel"));
at(elt9_,attrs4_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt10_=el(_doc,"li");
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_generalhelp,goal_0_4"));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt0_,elt10_);
Element elt12_=el(_doc,"li");
Element elt13_=el(_doc,"c:message");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","msg_generalhelp,goal_0_5"));
at(elt13_,attrs6_);
Element elt14_=el(_doc,"param");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","maxEv"));
at(elt14_,attrs7_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt0_,elt12_);
Element elt15_=el(_doc,"li");
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","msg_generalhelp,goal_0_6"));
at(elt16_,attrs8_);
Element elt17_=el(_doc,"param");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","happinessMax"));
at(elt17_,attrs9_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt0_,elt15_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,goal_0_7"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,goal_1"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","begin"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,goal_2"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,"map");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("style","font-size: 0"));
attrs0_.add(at("width","{getMapWidth()}"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(5);
attrs1_.add(at("key","p"));
attrs1_.add(at("map","miniMap"));
attrs1_.add(at("value","i"));
attrs1_.add(at("keyClassName","java.lang.Object"));
attrs1_.add(at("varClassName","java.lang.Object"));
at(elt1_,attrs1_);
Element elt2_=el(_doc,"a");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("title","{getPlaceName(([p]))}"));
at(elt2_,attrs2_);
Element elt3_=el(_doc,"c:img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","{getMiniMapImage(([p]))}"));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,goal_3"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,"c:img");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("src","{unlockedCity}"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_0"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,"c:img");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("src","{getImage()}"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,name"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc){
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
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,gender"));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc,"{getGender()}");
ad(_body,txt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,level"));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc,"{getLevel()}");
ad(_body,txt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,ability"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc){
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
static void build23(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,item"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build24(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","firstPokemonHasItem()"));
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
static void build25(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!firstPokemonHasItem()"));
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
static void build26(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon_npc,moves"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build27(Element _body,Document _doc){
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
static void build28(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_1"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbMaxTeam"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build29(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_2"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","minLevel"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","maxLevel"));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build30(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_3"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbMaxMoves"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build31(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_4"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","maxPp"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build32(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_5"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build33(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"li");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_5_0"));
at(elt2_,attrs0_);
Element elt3_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbNecStepsIncrHappiness"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt4_=el(_doc,"li");
Element elt5_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_generalhelp,help_5_1"));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
ad(elt0_,elt4_);
Element elt6_=el(_doc,"li");
Element elt7_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_generalhelp,help_5_2"));
at(elt7_,attrs3_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt8_=el(_doc,"li");
Element elt9_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_generalhelp,help_5_3"));
at(elt9_,attrs4_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
Element elt10_=el(_doc,"li");
Element elt11_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_generalhelp,help_5_4"));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt0_,elt10_);
ad(_body,elt0_);
}
static void build34(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_6"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build35(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"li");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_6_0"));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"li");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_generalhelp,help_6_1"));
at(elt4_,attrs1_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt5_=el(_doc,"li");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_generalhelp,help_6_2"));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
Element elt7_=el(_doc,"li");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_generalhelp,help_6_3"));
at(elt8_,attrs3_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc,"li");
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("value","msg_generalhelp,help_6_4"));
at(elt10_,attrs4_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,"ul");
Element elt12_=el(_doc,"li");
Element elt13_=el(_doc,"c:message");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("value","msg_generalhelp,help_6_5"));
at(elt13_,attrs5_);
Element elt14_=el(_doc,"param");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("value","nbMaxStepsSameEvoBase"));
at(elt14_,attrs6_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
Element elt15_=el(_doc,"li");
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_generalhelp,help_6_6"));
at(elt16_,attrs7_);
Element elt17_=el(_doc,"param");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("value","nbMaxSteps"));
at(elt17_,attrs8_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt11_,elt15_);
ad(elt9_,elt11_);
ad(elt0_,elt9_);
Element elt18_=el(_doc,"li");
Element elt19_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_generalhelp,help_6_7"));
at(elt19_,attrs9_);
ad(elt18_,elt19_);
ad(elt0_,elt18_);
ad(_body,elt0_);
}
static void build36(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_6_8"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build37(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","pokemonDefaultEggGroup"));
attrs0_.add(at("var","p"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Element elt3_=el(_doc,"a");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("c:command","$clickPokemon({([p])})"));
at(elt3_,attrs1_);
Text txt0_=tx(_doc,"{getTrPokemon(([p]))}");
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build38(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_6_9"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","defaultMoney"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build39(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,help_7"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","maxIv"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build40(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!tm.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_generalhelp,help_tm"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"table");
Element elt3_=el(_doc,"thead");
Element elt4_=el(_doc,"tr");
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_generalhelp,help_tm_hm_move"));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_generalhelp,help_tm_hm_price"));
at(elt8_,attrs3_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt9_=el(_doc,"tbody");
Element elt10_=el(_doc,"c:for");
CustList<Attr> attrs4_=al(2);
attrs4_.add(at("list","tm"));
attrs4_.add(at("var","m"));
at(elt10_,attrs4_);
Element elt11_=el(_doc,"tr");
Element elt12_=el(_doc,"td");
Element elt13_=el(_doc,"a");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("c:command","$clickTm({([m])})"));
at(elt13_,attrs5_);
Text txt0_=tx(_doc,"{getTrTm(([m]))}");
ad(elt13_,txt0_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
Element elt14_=el(_doc,"td");
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","isEmpty(getTmPrice(([m])))"));
at(elt15_,attrs6_);
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_generalhelp,help_tm_hm_price_no"));
at(elt16_,attrs7_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
Element elt17_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","!isEmpty(getTmPrice(([m])))"));
at(elt17_,attrs8_);
Text txt1_=tx(_doc,"{getTmPrice(([m]))}");
ad(elt17_,txt1_);
ad(elt14_,elt17_);
ad(elt11_,elt14_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt2_,elt9_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build41(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!hm.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_generalhelp,help_hm"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"ul");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(2);
attrs2_.add(at("list","hm"));
attrs2_.add(at("var","m"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"li");
Element elt5_=el(_doc,"a");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("c:command","$clickHm({([m])})"));
at(elt5_,attrs3_);
Text txt0_=tx(_doc,"{getTrHm(([m]))}");
ad(elt5_,txt0_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build42(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,types_intro"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build43(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"th");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_generalhelp,types_name"));
at(elt4_,attrs0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_generalhelp,types_image"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt2_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_generalhelp,types_coulour"));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt2_,elt7_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt9_=el(_doc,"tbody");
Element elt10_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(2);
attrs3_.add(at("list","types"));
attrs3_.add(at("var","t"));
at(elt10_,attrs3_);
Element elt11_=el(_doc,"tr");
Element elt12_=el(_doc,"td");
Text txt0_=tx(_doc,"{getTrType(([t]))}");
ad(elt12_,txt0_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,"td");
Element elt14_=el(_doc,"c:img");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("src","{getImageType(([t]))}"));
at(elt14_,attrs4_);
ad(elt13_,elt14_);
ad(elt11_,elt13_);
Element elt15_=el(_doc,"td");
Element elt16_=el(_doc,"c:img");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("src","{getColorType(([t]))}"));
at(elt16_,attrs5_);
ad(elt15_,elt16_);
ad(elt11_,elt15_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
static void build44(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","web/html/index.html"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_generalhelp,index"));
at(elt1_,attrs1_);
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
