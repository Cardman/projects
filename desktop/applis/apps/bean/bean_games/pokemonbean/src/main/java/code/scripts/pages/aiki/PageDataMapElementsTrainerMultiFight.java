package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMapElementsTrainerMultiFight extends PageCardsCommon{
private static final String C_P_139_0="javahtml";
private static final String C_P_139_1="trainer_fight";
private static final String C_P_139_2=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_139_3="stylesheet";
private static final String C_P_139_4="text/css";
private static final String C_P_139_5="msg_levelmap,title_fight_stand";
private static final String C_P_139_6=GO_TO_IND;
private static final String C_P_139_7="";
private static final String C_P_139_8="msg_levelmap,index";
private static final String C_P_139_9=GO_TO_MAP;
private static final String C_P_139_10="";
private static final String C_P_139_11="msg_levelmap,map";
private static final String C_P_139_12=GO_TO_LEVEL;
private static final String C_P_139_13="";
private static final String C_P_139_14="msg_levelmap,level";
private static final String C_P_139_15="image";
private static final String C_P_139_16="imageMini";
private static final String C_P_139_17="getTeamsRewards()";
private static final String C_P_139_18="t";
private static final String C_P_139_19="$int";
private static final String C_P_139_20="{([t])}";
private static final String C_P_139_21="pageTeam";
private static final String C_P_139_22="aiki.beans.map.pokemon";
private static final String C_P_139_23="PokemonTeamBean";
private static final String C_P_139_24="$intern.trainer=trainer";
private static final String C_P_139_25="$intern.noFight=([t])";
private PageDataMapElementsTrainerMultiFight(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc44){
Element elt0_=el(_doc44,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_139_0));
attrs0_.add(at(C_BEAN,C_P_139_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc44,HEAD);
Element elt2_=el(_doc44,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_139_2));
attrs1_.add(at(REL,C_P_139_3));
attrs1_.add(at(TYPE,C_P_139_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc44,TITLE);
Element elt4_=el(_doc44,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_139_5));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt5_=el(_doc44,BODY);
build0(elt5_,_doc44);
build1(elt5_,_doc44);
build2(elt5_,_doc44);
br(elt5_,_doc44);
build4(elt5_,_doc44);
br(elt5_,_doc44);
build6(elt5_,_doc44);
br(elt5_,_doc44);
build8(elt5_,_doc44);
br(elt5_,_doc44);
build10(elt5_,_doc44);
ad(elt0_,elt5_);
_doc44.appendChild(elt0_);
}
static void build0(Element _body,Document _doc44){
Element elt0_=el(_doc44,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_139_6));
attrs0_.add(at(HREF,C_P_139_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc44,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_139_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc44){
Element elt0_=el(_doc44,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc44){
Element elt0_=el(_doc44,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_139_9));
attrs0_.add(at(HREF,C_P_139_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc44,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_139_11));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc44,BR);
ad(_body,elt2_);
}
static void build4(Element _body,Document _doc44){
Element elt0_=el(_doc44,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_139_12));
attrs0_.add(at(HREF,C_P_139_13));
at(elt0_,attrs0_);
Element elt1_=el(_doc44,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_139_14));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc44,BR);
ad(_body,elt2_);
}
static void build6(Element _body,Document _doc44){
Element elt0_=el(_doc44,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_139_15));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc44){
Element elt0_=el(_doc44,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_139_16));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc44){
Element elt0_=el(_doc44,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(LIST,C_P_139_17));
attrs0_.add(at(VAR,C_P_139_18));
attrs0_.add(at(INDEXCLASSNAME,C_P_139_19));
at(elt0_,attrs0_);
Text txt0_=tx(_doc44,C_P_139_20);
ad(elt0_,txt0_);
Element elt1_=el(_doc44,BR);
ad(elt0_,elt1_);
Element elt2_=el(_doc44,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_139_21));
at(elt2_,attrs1_);
Element elt3_=el(_doc44,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_139_22));
at(elt3_,attrs2_);
Element elt4_=el(_doc44,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_139_23));
at(elt4_,attrs3_);
Element elt5_=el(_doc44,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_139_24));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc44,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_139_25));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc44,HR);
ad(elt0_,elt7_);
ad(_body,elt0_);
}
}
