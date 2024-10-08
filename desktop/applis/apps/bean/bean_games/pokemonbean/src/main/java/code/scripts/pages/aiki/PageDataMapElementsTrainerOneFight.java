package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMapElementsTrainerOneFight extends PageCardsCommon{
private static final String C_P_140_0="javahtml";
private static final String C_P_140_1="trainer_fight";
private static final String C_P_140_2=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_140_3="stylesheet";
private static final String C_P_140_4="text/css";
private static final String C_P_140_5="!isEmpty(getName())";
private static final String C_P_140_6="msg_levelmap,title_fight";
private static final String C_P_140_7="getName()";
private static final String C_P_140_8="isEmpty(getName())";
private static final String C_P_140_9="msg_levelmap,title_fight_stand";
private static final String C_P_140_10=GO_TO_IND;
private static final String C_P_140_11="";
private static final String C_P_140_12="msg_levelmap,index";
private static final String C_P_140_13=GO_TO_MAP;
private static final String C_P_140_14="";
private static final String C_P_140_15="msg_levelmap,map";
private static final String C_P_140_16=GO_TO_LEVEL;
private static final String C_P_140_17="";
private static final String C_P_140_18="msg_levelmap,level";
private static final String C_P_140_19="image";
private static final String C_P_140_20="imageMini";
private static final String C_P_140_21="pageTeam";
private static final String C_P_140_22="aiki.beans.map.pokemon";
private static final String C_P_140_23="PokemonTeamBean";
private static final String C_P_140_24="$intern.trainer=trainer";
private static final String C_P_140_25="!isEmpty(move)";
private static final String C_P_140_26="$clickMove()";
private static final String C_P_140_27="{getTrMove()}";
private PageDataMapElementsTrainerOneFight(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc45){
Element elt0_=el(_doc45,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_140_0));
attrs0_.add(at(C_BEAN,C_P_140_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc45,HEAD);
Element elt2_=el(_doc45,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_140_2));
attrs1_.add(at(REL,C_P_140_3));
attrs1_.add(at(TYPE,C_P_140_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc45,TITLE);
Element elt4_=el(_doc45,C_IF);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(CONDITION,C_P_140_5));
at(elt4_,attrs2_);
Element elt5_=el(_doc45,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_140_6));
at(elt5_,attrs3_);
Element elt6_=el(_doc45,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_140_7));
at(elt6_,attrs4_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt7_=el(_doc45,C_IF);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(CONDITION,C_P_140_8));
at(elt7_,attrs5_);
Element elt8_=el(_doc45,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_140_9));
at(elt8_,attrs6_);
ad(elt7_,elt8_);
ad(elt3_,elt7_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt9_=el(_doc45,BODY);
build0(elt9_,_doc45);
build1(elt9_,_doc45);
build2(elt9_,_doc45);
br(elt9_,_doc45);
build4(elt9_,_doc45);
br(elt9_,_doc45);
build6(elt9_,_doc45);
br(elt9_,_doc45);
build8(elt9_,_doc45);
br(elt9_,_doc45);
build10(elt9_,_doc45);
build11(elt9_,_doc45);
ad(elt0_,elt9_);
_doc45.appendChild(elt0_);
}
static void build0(Element _body,Document _doc45){
Element elt0_=el(_doc45,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_140_10));
attrs0_.add(at(HREF,C_P_140_11));
at(elt0_,attrs0_);
Element elt1_=el(_doc45,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_140_12));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc45){
Element elt0_=el(_doc45,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc45){
Element elt0_=el(_doc45,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_140_13));
attrs0_.add(at(HREF,C_P_140_14));
at(elt0_,attrs0_);
Element elt1_=el(_doc45,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_140_15));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc45,BR);
ad(_body,elt2_);
}
static void build4(Element _body,Document _doc45){
Element elt0_=el(_doc45,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_140_16));
attrs0_.add(at(HREF,C_P_140_17));
at(elt0_,attrs0_);
Element elt1_=el(_doc45,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_140_18));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc45,BR);
ad(_body,elt2_);
}
static void build6(Element _body,Document _doc45){
Element elt0_=el(_doc45,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_140_19));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc45){
Element elt0_=el(_doc45,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_140_20));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc45){
Element elt0_=el(_doc45,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_140_21));
at(elt0_,attrs0_);
Element elt1_=el(_doc45,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_140_22));
at(elt1_,attrs1_);
Element elt2_=el(_doc45,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_140_23));
at(elt2_,attrs2_);
Element elt3_=el(_doc45,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_140_24));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc45){
Element elt0_=el(_doc45,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_140_25));
at(elt0_,attrs0_);
Element elt1_=el(_doc45,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_140_26));
at(elt1_,attrs1_);
Text txt0_=tx(_doc45,C_P_140_27);
ad(elt1_,txt0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
