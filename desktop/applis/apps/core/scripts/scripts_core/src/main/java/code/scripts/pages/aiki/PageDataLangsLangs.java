package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataLangsLangs{
private PageDataLangsLangs(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","langs"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"title");
Element elt3_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_lang,title"));
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
attrs1_.add(at("value","msg_lang,index"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("name","begin"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_lang,translations"));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,"br");
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_genders"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysGenders()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowGender(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_booleans"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysBooleans()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowBoolean(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_envs"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysEnvironments()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowEnvironment(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_statistics"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysStatistics()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowStatistic(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_targets"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysTargets()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowTarget(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_categories"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysCategories()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowCategory(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_types"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysTypes()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowType(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_pk"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysPokemon()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowPokemon(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_mv"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysMoves()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowMove(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_it"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysItems()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowItem(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build23(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_ab"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build24(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysAbilities()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowAbility(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build25(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_status"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build26(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysStatus()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowStatus(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build27(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_math"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build28(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysMath()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowMath(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build29(Element _body,Document _doc){
Element elt0_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_lang,translations_classes"));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build30(Element _body,Document _doc){
Element elt0_=el(_doc,"table");
Element elt1_=el(_doc,"thead");
Element elt2_=el(_doc,"tr");
Element elt3_=el(_doc,"c:for");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("list","languages"));
attrs0_.add(at("var","l"));
at(elt3_,attrs0_);
Element elt4_=el(_doc,"th");
Text txt0_=tx(_doc,"{getTrLang(([l]))}");
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,"tbody");
Element elt6_=el(_doc,"c:for");
CustList<Attr> attrs1_=al(2);
attrs1_.add(at("list","getKeysDesc()"));
attrs1_.add(at("var","k"));
at(elt6_,attrs1_);
Element elt7_=el(_doc,"tr");
Element elt8_=el(_doc,"c:for");
CustList<Attr> attrs2_=al(3);
attrs2_.add(at("list","getRowDesc(([k]))"));
attrs2_.add(at("var","s"));
attrs2_.add(at("className","java.lang.String"));
at(elt8_,attrs2_);
Element elt9_=el(_doc,"td");
Text txt1_=tx(_doc,"{s}");
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
static void build31(Element _body,Document _doc){
Element elt0_=el(_doc,"a");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("c:command","web/html/langs/langs.html#begin"));
attrs0_.add(at("href",""));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("value","msg_lang,return_begin"));
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
