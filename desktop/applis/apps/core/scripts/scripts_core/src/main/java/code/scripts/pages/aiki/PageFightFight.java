package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageFightFight{
private PageFightFight(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","fight"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_fight,title_fight"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,"link");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("href","web_fight/css/fight.css"));
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
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","web_fight/html/fight.html"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_fight,refresh"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","web_fight/html/fightdetail.html"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_fight,go_detail"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","$clickPlayer"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_fight,yours"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","$clickFoe"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_fight,foes"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_fight,mult"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","mult"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_fight,nb_rounds"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbRounds"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_fight,nb_flee_attempts"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbFleeAttempt"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_fight,win_money"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","winningMoney"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_fight,enbaled_moves"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"th");
Element elt4_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_fight,enbaled_moves_key"));
at(elt4_,attrs0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_fight,enbaled_moves_still"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt2_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_fight,enbaled_moves_enabled"));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt2_,elt7_);
Element elt9_=el(_doc,"th");
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("value","msg_fight,enbaled_moves_nb_round"));
at(elt10_,attrs3_);
ad(elt9_,elt10_);
ad(elt2_,elt9_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt11_=el(_doc,"tbody");
Element elt12_=el(_doc,"c:for");
CustList<Attr> attrs4_=al(5);
attrs4_.add(at("key","m"));
attrs4_.add(at("map","enabledMoves"));
attrs4_.add(at("value","a"));
attrs4_.add(at("keyClassName","java.lang.String"));
attrs4_.add(at("varClassName","aiki.game.fight.ActivityOfMove"));
at(elt12_,attrs4_);
Element elt13_=el(_doc,"tr");
Element elt14_=el(_doc,"td");
Text txt0_=tx(_doc,"{m}");
ad(elt14_,txt0_);
ad(elt13_,elt14_);
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("condition","isStillEnabled(([m]))"));
at(elt15_,attrs5_);
Element elt16_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","a.isEnabled()"));
at(elt16_,attrs6_);
Element elt17_=el(_doc,"td");
Element elt18_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_fight,enbaled_moves_enabled_y"));
at(elt18_,attrs7_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
Element elt19_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","!a.isEnabled()"));
at(elt19_,attrs8_);
Element elt20_=el(_doc,"td");
Element elt21_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_fight,enbaled_moves_enabled_n"));
at(elt21_,attrs9_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt15_,elt19_);
ad(elt13_,elt15_);
Element elt22_=el(_doc,"c:if");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("condition","!isStillEnabled(([m]))"));
at(elt22_,attrs10_);
Element elt23_=el(_doc,"td");
Element elt24_=el(_doc,"c:message");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","msg_fight,enbaled_moves_no"));
at(elt24_,attrs11_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt13_,elt22_);
Element elt25_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","a.isEnabled()"));
at(elt25_,attrs12_);
Element elt26_=el(_doc,"td");
Element elt27_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_fight,enbaled_moves_enabled_y"));
at(elt27_,attrs13_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt13_,elt25_);
Element elt28_=el(_doc,"c:if");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("condition","!a.isEnabled()"));
at(elt28_,attrs14_);
Element elt29_=el(_doc,"td");
Element elt30_=el(_doc,"c:message");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","msg_fight,enbaled_moves_enabled_n"));
at(elt30_,attrs15_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
ad(elt13_,elt28_);
Element elt31_=el(_doc,"c:if");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("condition","a.isIncrementCount()"));
at(elt31_,attrs16_);
Element elt32_=el(_doc,"td");
Text txt1_=tx(_doc,"{a.getNbTurn()}");
ad(elt32_,txt1_);
ad(elt31_,elt32_);
ad(elt13_,elt31_);
Element elt33_=el(_doc,"c:if");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("condition","!a.isIncrementCount()"));
at(elt33_,attrs17_);
Element elt34_=el(_doc,"td");
Element elt35_=el(_doc,"c:message");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("value","msg_fight,enbaled_moves_no"));
at(elt35_,attrs18_);
ad(elt34_,elt35_);
ad(elt33_,elt34_);
ad(elt13_,elt33_);
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
