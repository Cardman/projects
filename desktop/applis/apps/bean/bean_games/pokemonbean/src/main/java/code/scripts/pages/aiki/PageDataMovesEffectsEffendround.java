package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffendround extends PageCardsCommon{
private static final String C_P_155_0="javahtml";
private static final String C_P_155_1="eff_endround";
private static final String C_P_155_2="web/css/moves.css";
private static final String C_P_155_3="stylesheet";
private static final String C_P_155_4="text/css";
private static final String C_P_155_5="{effectBean}";
private static final String C_P_155_6="aiki.beans.moves.effects";
private static final String C_P_155_7="EffectBean";
private static final String C_P_155_8="$intern.index=index";
private static final String C_P_155_9="$intern.move=move";
private static final String C_P_155_10="msg_effendround,rank";
private static final String C_P_155_11="endRoundRank";
private static final String C_P_155_12=GO_TO_ENDROUND;
private static final String C_P_155_13="";
private static final String C_P_155_14="msg_effendround,endRound";
private static final String C_P_155_15="!reasonsEndRound.isEmpty()";
private static final String C_P_155_16="msg_effendround,reasons";
private static final String C_P_155_17="reasonsEndRound";
private static final String C_P_155_18="r";
private static final String C_P_155_19="java.lang.String";
private static final String C_P_155_20="{r}";
private static final String C_P_155_21="c";
private static final String C_P_155_22="mapVarsFailEndRound";
private static final String C_P_155_23="r";
private static final String C_P_155_24="java.lang.String";
private static final String C_P_155_25="java.lang.String";
private static final String C_P_155_26="{c} : {r}";
private PageDataMovesEffectsEffendround(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc60){
Element elt0_=el(_doc60,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_155_0));
attrs0_.add(at(C_BEAN,C_P_155_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc60,HEAD);
Element elt2_=el(_doc60,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_155_2));
attrs1_.add(at(REL,C_P_155_3));
attrs1_.add(at(TYPE,C_P_155_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc60,BODY);
build0(elt3_,_doc60);
build1(elt3_,_doc60);
ad(elt0_,elt3_);
_doc60.appendChild(elt0_);
}
static void build0(Element _body,Document _doc60){
Element elt0_=el(_doc60,P);
Element elt1_=el(_doc60,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_155_5));
at(elt1_,attrs0_);
Element elt2_=el(_doc60,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_155_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc60,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_155_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc60,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_155_8));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
Element elt5_=el(_doc60,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_155_9));
at(elt5_,attrs4_);
ad(elt3_,elt5_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt6_=el(_doc60,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_155_10));
at(elt6_,attrs5_);
Element elt7_=el(_doc60,PARAM);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_155_11));
at(elt7_,attrs6_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt8_=el(_doc60,A);
CustList<Attr> attrs7_=al(2);
attrs7_.add(at(C_COMMAND,C_P_155_12));
attrs7_.add(at(HREF,C_P_155_13));
at(elt8_,attrs7_);
Element elt9_=el(_doc60,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_155_14));
at(elt9_,attrs8_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc60){
Element elt0_=el(_doc60,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_155_15));
at(elt0_,attrs0_);
Element elt1_=el(_doc60,P);
Element elt2_=el(_doc60,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_155_16));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc60,UL);
Element elt4_=el(_doc60,C_FOR);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(LIST,C_P_155_17));
attrs2_.add(at(VAR,C_P_155_18));
attrs2_.add(at(CLASSNAME,C_P_155_19));
at(elt4_,attrs2_);
Element elt5_=el(_doc60,LI);
Text txt0_=tx(_doc60,C_P_155_20);
ad(elt5_,txt0_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
Element elt6_=el(_doc60,BR);
ad(elt1_,elt6_);
Element elt7_=el(_doc60,UL);
Element elt8_=el(_doc60,C_FOR);
CustList<Attr> attrs3_=al(5);
attrs3_.add(at(KEY,C_P_155_21));
attrs3_.add(at(MAP,C_P_155_22));
attrs3_.add(at(VALUE,C_P_155_23));
attrs3_.add(at(KEYCLASSNAME,C_P_155_24));
attrs3_.add(at(VARCLASSNAME,C_P_155_25));
at(elt8_,attrs3_);
Element elt9_=el(_doc60,LI);
Text txt1_=tx(_doc60,C_P_155_26);
ad(elt9_,txt1_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt1_,elt7_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
