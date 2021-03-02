package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PagePkPokemon{
private PagePkPokemon(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","pokemon"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,title"));
at(elt3_,attrs1_);
Element elt4_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","name"));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc,"link");
CustList<Attr> attrs3_=al(3);
attrs3_.add(at("href","web_pk/css/pokemon.css"));
attrs3_.add(at("rel","stylesheet"));
attrs3_.add(at("type","text/css"));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
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
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,name"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","name"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:img");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("src","{image}"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!evolutions.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,evolutions"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"ul");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(5);
attrs2_.add(at("key","p"));
attrs2_.add(at("map","evolutions"));
attrs2_.add(at("value","e"));
attrs2_.add(at("keyClassName","java.lang.Object"));
attrs2_.add(at("varClassName","java.lang.String"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"li");
Text txt0_=tx(_doc,"{getEvo(([p]))}");
ad(elt4_,txt0_);
Element elt5_=el(_doc,"c:img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","{e}"));
at(elt5_,attrs3_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,level"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","level"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,gender"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","gender"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,ability"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","ability"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!usedBallCatching.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,used_ball_catching"));
at(elt1_,attrs1_);
Element elt2_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","usedBallCatching"));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!item.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,item"));
at(elt1_,attrs1_);
Element elt2_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","item"));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","item.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,item_no"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,hp"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","remainingHp"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","remainingHpPerCent"));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,"param");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","fullHp"));
at(elt3_,attrs3_);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,nickname"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nickname"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,won_exp_last_level"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","wonExpSinceLastLevel"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,necessary_pts"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","necessaryPointsNextLevel"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,happiness"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","happiness"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,nb_steps"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbStepsTeamLead"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,types"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("list","types"));
attrs0_.add(at("var","t"));
attrs0_.add(at("className","java.lang.String"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Text txt0_=tx(_doc,"{t}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!status.isEmpty()"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,status"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"ul");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","status"));
attrs2_.add(at("var","t"));
attrs2_.add(at("className","java.lang.String"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"li");
Text txt0_=tx(_doc,"{t}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,moves"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"th");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,moves_key"));
at(elt4_,attrs0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,moves_cur_pp"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt2_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_pokemon,moves_max_pp"));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt2_,elt7_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt9_=el(_doc,"tbody");
Element elt10_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(5);
attrs3_.add(at("key","m"));
attrs3_.add(at("map","moves"));
attrs3_.add(at("value","a"));
attrs3_.add(at("keyClassName","java.lang.String"));
attrs3_.add(at("varClassName","aiki.game.UsesOfMove"));
at(elt10_,attrs3_);
Element elt11_=el(_doc,"tr");
Element elt12_=el(_doc,"td");
Text txt0_=tx(_doc,"{m}");
ad(elt12_,txt0_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,"td");
Text txt1_=tx(_doc,"{a.getCurrent()}");
ad(elt13_,txt1_);
ad(elt11_,elt13_);
Element elt14_=el(_doc,"td");
Text txt2_=tx(_doc,"{a.getMax()}");
ad(elt14_,txt2_);
ad(elt11_,elt14_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,statistics"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"th");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_pokemon,statistics_key"));
at(elt4_,attrs0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_pokemon,statistics_ev"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt2_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_pokemon,statistics_iv"));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt2_,elt7_);
Element elt9_=el(_doc,"th");
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_pokemon,statistics_rate"));
at(elt10_,attrs3_);
ad(elt9_,elt10_);
ad(elt2_,elt9_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt11_=el(_doc,"tbody");
Element elt12_=el(_doc,"c:for");
CustList<Attr> attrs4_=al(3);
attrs4_.add(at("className","aiki.beans.facade.game.dto.StatisticInfoPkPlayer"));
attrs4_.add(at("list","statistics"));
attrs4_.add(at("var","s"));
at(elt12_,attrs4_);
Element elt13_=el(_doc,"tr");
Element elt14_=el(_doc,"td");
Text txt0_=tx(_doc,"{s.getName()}");
ad(elt14_,txt0_);
ad(elt13_,elt14_);
Element elt15_=el(_doc,"td");
Text txt1_=tx(_doc,"{s.getEv()}");
ad(elt15_,txt1_);
ad(elt13_,elt15_);
Element elt16_=el(_doc,"td");
Text txt2_=tx(_doc,"{s.getIv()}");
ad(elt16_,txt2_);
ad(elt13_,elt16_);
Element elt17_=el(_doc,"td");
Text txt3_=tx(_doc,"{s.getRate()}");
ad(elt17_,txt3_);
ad(elt13_,elt17_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
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
