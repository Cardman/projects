package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataEndroundPositiontarget extends PageCardsCommon{
private static final String C_P_108_0="javahtml";
private static final String C_P_108_1="end_positiontarget";
private static final String C_P_108_2=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_108_3="stylesheet";
private static final String C_P_108_4="text/css";
private static final String C_P_108_5="endRoundHtml";
private static final String C_P_108_6="aiki.beans.endround";
private static final String C_P_108_7="EffectEndRoundBean";
private static final String C_P_108_8="$intern.index=index";
private static final String C_P_108_9="msg_positiontarget,effect";
private static final String C_P_108_10="!getMovesSameCategory().isEmpty()";
private static final String C_P_108_11="msg_positiontarget,anticipe";
private static final String C_P_108_12="getMovesSameCategory()";
private static final String C_P_108_13="m";
private static final String C_P_108_14="$clickTargetRelationMove({([m])})";
private static final String C_P_108_15="";
private static final String C_P_108_16="{getTrTargetRelationMove(([m]))}";
private PageDataEndroundPositiontarget(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc13){
Element elt0_=el(_doc13,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_108_0));
attrs0_.add(at(C_BEAN,C_P_108_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc13,HEAD);
Element elt2_=el(_doc13,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_108_2));
attrs1_.add(at(REL,C_P_108_3));
attrs1_.add(at(TYPE,C_P_108_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc13,BODY);
build0(elt3_,_doc13);
build1(elt3_,_doc13);
ad(elt0_,elt3_);
_doc13.appendChild(elt0_);
}
static void build0(Element _body,Document _doc13){
Element elt0_=el(_doc13,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_108_5));
at(elt0_,attrs0_);
Element elt1_=el(_doc13,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_108_6));
at(elt1_,attrs1_);
Element elt2_=el(_doc13,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_108_7));
at(elt2_,attrs2_);
Element elt3_=el(_doc13,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_108_8));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc13){
Element elt0_=el(_doc13,P);
Element elt1_=el(_doc13,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_108_9));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc13,C_IF);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(CONDITION,C_P_108_10));
at(elt2_,attrs1_);
Element elt3_=el(_doc13,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_108_11));
at(elt3_,attrs2_);
ad(elt2_,elt3_);
Element elt4_=el(_doc13,UL);
Element elt5_=el(_doc13,C_FOR);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(LIST,C_P_108_12));
attrs3_.add(at(VAR,C_P_108_13));
at(elt5_,attrs3_);
Element elt6_=el(_doc13,LI);
Element elt7_=el(_doc13,A);
CustList<Attr> attrs4_=al(2);
attrs4_.add(at(C_COMMAND,C_P_108_14));
attrs4_.add(at(HREF,C_P_108_15));
at(elt7_,attrs4_);
Text txt0_=tx(_doc13,C_P_108_16);
ad(elt7_,txt0_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt2_,elt4_);
Element elt8_=el(_doc13,BR);
ad(elt2_,elt8_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
}
