package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMapElementsLegPk extends PageCardsCommon{
private static final String C_P_136_0="javahtml";
private static final String C_P_136_1="leg_pk";
private static final String C_P_136_2=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_136_3="stylesheet";
private static final String C_P_136_4="text/css";
private static final String C_P_136_5="msg_levelmap,title_leg";
private static final String C_P_136_6="getName()";
private static final String C_P_136_7=GO_TO_IND;
private static final String C_P_136_8="";
private static final String C_P_136_9="msg_pokemon_npc,index";
private static final String C_P_136_10=GO_TO_MAP;
private static final String C_P_136_11="";
private static final String C_P_136_12="msg_pokemon_npc,map";
private static final String C_P_136_13=GO_TO_LEVEL;
private static final String C_P_136_14="";
private static final String C_P_136_15="msg_pokemon_npc,level";
private static final String C_P_136_16="getImage()";
private static final String C_P_136_17="msg_pokemon_npc,name";
private static final String C_P_136_18="$clickName()";
private static final String C_P_136_19="{getName()}";
private static final String C_P_136_20="msg_pokemon_npc,gender";
private static final String C_P_136_21="{getGender()}";
private static final String C_P_136_22="msg_pokemon_npc,level";
private static final String C_P_136_23="{getLevel()}";
private static final String C_P_136_24="msg_pokemon_npc,ability";
private static final String C_P_136_25="$clickAbility()";
private static final String C_P_136_26="{getAbility()}";
private static final String C_P_136_27="msg_pokemon_npc,item";
private static final String C_P_136_28="!isEmpty(pokemon.getItem())";
private static final String C_P_136_29="$clickItem()";
private static final String C_P_136_30="{getItem()}";
private static final String C_P_136_31="isEmpty(pokemon.getItem())";
private static final String C_P_136_32="msg_pokemon_npc,item_no";
private static final String C_P_136_33="msg_pokemon_npc,moves";
private static final String C_P_136_34="getMovesAtLevel()";
private static final String C_P_136_35="m";
private static final String C_P_136_36="$clickMove({([m])})";
private static final String C_P_136_37="{getMove(([m]))}";
private PageDataMapElementsLegPk(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc41){
Element elt0_=el(_doc41,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_136_0));
attrs0_.add(at(C_BEAN,C_P_136_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc41,HEAD);
Element elt2_=el(_doc41,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_136_2));
attrs1_.add(at(REL,C_P_136_3));
attrs1_.add(at(TYPE,C_P_136_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc41,TITLE);
Element elt4_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_136_5));
at(elt4_,attrs2_);
Element elt5_=el(_doc41,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_136_6));
at(elt5_,attrs3_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt6_=el(_doc41,BODY);
build0(elt6_,_doc41);
build1(elt6_,_doc41);
build2(elt6_,_doc41);
br(elt6_,_doc41);
build4(elt6_,_doc41);
br(elt6_,_doc41);
build6(elt6_,_doc41);
br(elt6_,_doc41);
build8(elt6_,_doc41);
build9(elt6_,_doc41);
build10(elt6_,_doc41);
br(elt6_,_doc41);
build12(elt6_,_doc41);
br(elt6_,_doc41);
build14(elt6_,_doc41);
build15(elt6_,_doc41);
build16(elt6_,_doc41);
build17(elt6_,_doc41);
build18(elt6_,_doc41);
build19(elt6_,_doc41);
build20(elt6_,_doc41);
br(elt6_,_doc41);
br(elt6_,_doc41);
ad(elt0_,elt6_);
_doc41.appendChild(elt0_);
}
static void build0(Element _body,Document _doc41){
Element elt0_=el(_doc41,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_136_7));
attrs0_.add(at(HREF,C_P_136_8));
at(elt0_,attrs0_);
Element elt1_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_136_9));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc41){
Element elt0_=el(_doc41,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc41){
Element elt0_=el(_doc41,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_136_10));
attrs0_.add(at(HREF,C_P_136_11));
at(elt0_,attrs0_);
Element elt1_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_136_12));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc41,BR);
ad(_body,elt2_);
}
static void build4(Element _body,Document _doc41){
Element elt0_=el(_doc41,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_136_13));
attrs0_.add(at(HREF,C_P_136_14));
at(elt0_,attrs0_);
Element elt1_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_136_15));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc41,BR);
ad(_body,elt2_);
}
static void build6(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_136_16));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_136_17));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc41){
Element elt0_=el(_doc41,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_136_18));
at(elt0_,attrs0_);
Text txt0_=tx(_doc41,C_P_136_19);
ad(elt0_,txt0_);
ad(_body,elt0_);
Element elt1_=el(_doc41,BR);
ad(_body,elt1_);
}
static void build10(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_136_20));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc41,C_P_136_21);
ad(_body,txt0_);
}
static void build12(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_136_22));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc41,C_P_136_23);
ad(_body,txt0_);
}
static void build14(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_136_24));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc41){
Element elt0_=el(_doc41,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_136_25));
at(elt0_,attrs0_);
Text txt0_=tx(_doc41,C_P_136_26);
ad(elt0_,txt0_);
ad(_body,elt0_);
Element elt1_=el(_doc41,BR);
ad(_body,elt1_);
}
static void build16(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_136_27));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_136_28));
at(elt0_,attrs0_);
Element elt1_=el(_doc41,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_136_29));
at(elt1_,attrs1_);
Text txt0_=tx(_doc41,C_P_136_30);
ad(elt1_,txt0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc41,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_136_31));
at(elt0_,attrs0_);
Element elt1_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_136_32));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc41,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc41){
Element elt0_=el(_doc41,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_136_33));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc41){
Element elt0_=el(_doc41,UL);
Element elt1_=el(_doc41,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_136_34));
attrs0_.add(at(VAR,C_P_136_35));
at(elt1_,attrs0_);
Element elt2_=el(_doc41,LI);
Element elt3_=el(_doc41,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_136_36));
at(elt3_,attrs1_);
Text txt0_=tx(_doc41,C_P_136_37);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
