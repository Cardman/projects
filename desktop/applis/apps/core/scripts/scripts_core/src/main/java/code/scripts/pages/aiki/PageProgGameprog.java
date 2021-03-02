package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageProgGameprog{
private PageProgGameprog(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","progressing"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,title"));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,"link");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("href","web_prog/css/difficulty.css"));
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
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","finishedGame"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:img");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("src","{heroImage}"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Text txt0_=tx(_doc,"+");
ad(elt0_,txt0_);
Element elt2_=el(_doc,"c:img");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("src","{heroImageOppositeSex}"));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Text txt1_=tx(_doc,"=");
ad(elt0_,txt1_);
Element elt3_=el(_doc,"c:img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","{endGameImage}"));
at(elt3_,attrs3_);
ad(elt0_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"c:if");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("condition","!finishedGame"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,nickname"));
at(elt1_,attrs1_);
Element elt2_=el(_doc,"param");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","nickname"));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"c:img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","{heroImage}"));
at(elt3_,attrs3_);
ad(elt0_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","web_prog/html/gameprognotatall.html"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,titleNotAtAll"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","web_prog/html/gameprogpart.html"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,titlePart"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("c:command","web_prog/html/gameprogall.html"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,titleFull"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,"br");
ad(_body,elt2_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,unBeatTrainer"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("className","aiki.fight.pokemon.TrainerPlaceNames"));
attrs0_.add(at("list","unBeatenImportantTrainers"));
attrs0_.add(at("var","p"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Text txt0_=tx(_doc,"{p.getTrainer()} - {p.getPlace()}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,beatTrainer"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("className","aiki.fight.pokemon.TrainerPlaceNames"));
attrs0_.add(at("list","beatenImportantTrainers"));
attrs0_.add(at("var","p"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Text txt0_=tx(_doc,"{p.getTrainer()} - {p.getPlace()}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"caption");
Element elt2_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,otherTrainers"));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"thead");
Element elt4_=el(_doc,"tr");
Element elt5_=el(_doc,"th");
Element elt6_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_prog,otherTrainersPlace"));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc,"th");
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("value","msg_prog,otherTrainersNumber"));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt9_=el(_doc,"tbody");
Element elt10_=el(_doc,"c:for");
CustList<Attr> attrs3_=al(5);
attrs3_.add(at("key","p"));
attrs3_.add(at("map","remainingOtherTrainerPlaces"));
attrs3_.add(at("value","n"));
attrs3_.add(at("keyClassName","java.lang.Object"));
attrs3_.add(at("varClassName","java.lang.Integer"));
at(elt10_,attrs3_);
Element elt11_=el(_doc,"tr");
Element elt12_=el(_doc,"td");
Text txt0_=tx(_doc,"{getRemainingOtherTrainersPlaceName(([p]))}");
ad(elt12_,txt0_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,"td");
Text txt1_=tx(_doc,"{n}");
ad(elt13_,txt1_);
ad(elt11_,elt13_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,unVisitPlace"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("list","unVisitedPlaces"));
attrs0_.add(at("var","p"));
attrs0_.add(at("className","java.lang.String"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Text txt0_=tx(_doc,"{p}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,visitPlace"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,"ul");
Element elt1_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(3);
attrs0_.add(at("list","visitedPlaces"));
attrs0_.add(at("var","p"));
attrs0_.add(at("className","java.lang.String"));
at(elt1_,attrs0_);
Element elt2_=el(_doc,"li");
Text txt0_=tx(_doc,"{p}");
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,nbRemPkLevel"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbRemainingNotMaxLevel"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,nbRemPkHappiness"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbRemainingNotMaxHappiness"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build23(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,nbRemEgg"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","nbRemainingEggs"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build24(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build25(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,repel"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","remainStepsRepel"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build26(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build27(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_prog,money"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"param");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","money"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build28(Element _body,Document _doc){
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
