package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMapElementsDualFight extends PageCardsCommon{
private static final String C_P_135_0="javahtml";
private static final String C_P_135_1="dual";
private static final String C_P_135_2=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_135_3="stylesheet";
private static final String C_P_135_4="text/css";
private static final String C_P_135_5="msg_levelmap,title_dual";
private static final String C_P_135_6=GO_TO_IND;
private static final String C_P_135_7="";
private static final String C_P_135_8="msg_levelmap,index";
private static final String C_P_135_9=GO_TO_MAP;
private static final String C_P_135_10="";
private static final String C_P_135_11="msg_levelmap,map";
private static final String C_P_135_12=GO_TO_LEVEL;
private static final String C_P_135_13="";
private static final String C_P_135_14="msg_levelmap,level";
private static final String C_P_135_15="image";
private static final String C_P_135_16="imageMini";
private static final String C_P_135_17="imageMiniSecond";
private static final String C_P_135_18="pageAlly";
private static final String C_P_135_19="aiki.beans.map.characters";
private static final String C_P_135_20="AllyBean";
private static final String C_P_135_21="$intern.ally=ally";
private static final String C_P_135_22="pageTeam";
private static final String C_P_135_23="aiki.beans.map.pokemon";
private static final String C_P_135_24="PokemonTeamBean";
private static final String C_P_135_25="$intern.trainer=trainer";
private PageDataMapElementsDualFight(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc40){
Element elt0_=el(_doc40,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_135_0));
attrs0_.add(at(C_BEAN,C_P_135_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc40,HEAD);
Element elt2_=el(_doc40,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_135_2));
attrs1_.add(at(REL,C_P_135_3));
attrs1_.add(at(TYPE,C_P_135_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc40,TITLE);
Element elt4_=el(_doc40,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_135_5));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt5_=el(_doc40,BODY);
build0(elt5_,_doc40);
build1(elt5_,_doc40);
build2(elt5_,_doc40);
br(elt5_,_doc40);
build4(elt5_,_doc40);
br(elt5_,_doc40);
build6(elt5_,_doc40);
br(elt5_,_doc40);
build8(elt5_,_doc40);
br(elt5_,_doc40);
build10(elt5_,_doc40);
br(elt5_,_doc40);
build12(elt5_,_doc40);
br(elt5_,_doc40);
build14(elt5_,_doc40);
br(elt5_,_doc40);
build16(elt5_,_doc40);
ad(elt0_,elt5_);
_doc40.appendChild(elt0_);
}
static void build0(Element _body,Document _doc40){
Element elt0_=el(_doc40,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_135_6));
attrs0_.add(at(HREF,C_P_135_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc40,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_135_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc40){
Element elt0_=el(_doc40,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc40){
Element elt0_=el(_doc40,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_135_9));
attrs0_.add(at(HREF,C_P_135_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc40,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_135_11));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc40,BR);
ad(_body,elt2_);
}
static void build4(Element _body,Document _doc40){
Element elt0_=el(_doc40,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_135_12));
attrs0_.add(at(HREF,C_P_135_13));
at(elt0_,attrs0_);
Element elt1_=el(_doc40,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_135_14));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc40,BR);
ad(_body,elt2_);
}
static void build6(Element _body,Document _doc40){
Element elt0_=el(_doc40,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_135_15));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc40){
Element elt0_=el(_doc40,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_135_16));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc40){
Element elt0_=el(_doc40,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_135_17));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc40){
Element elt0_=el(_doc40,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_135_18));
at(elt0_,attrs0_);
Element elt1_=el(_doc40,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_135_19));
at(elt1_,attrs1_);
Element elt2_=el(_doc40,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_135_20));
at(elt2_,attrs2_);
Element elt3_=el(_doc40,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_135_21));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc40){
Element elt0_=el(_doc40,HR);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc40){
Element elt0_=el(_doc40,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_135_22));
at(elt0_,attrs0_);
Element elt1_=el(_doc40,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_135_23));
at(elt1_,attrs1_);
Element elt2_=el(_doc40,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_135_24));
at(elt2_,attrs2_);
Element elt3_=el(_doc40,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_135_25));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
