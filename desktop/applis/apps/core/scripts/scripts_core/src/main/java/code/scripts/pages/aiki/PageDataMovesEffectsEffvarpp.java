package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffvarpp extends PageAikiCommon{
private static final String C_P_177_0="javahtml";
private static final String C_P_177_1="eff_varpp";
private static final String C_P_177_2="web/css/moves.css";
private static final String C_P_177_3="stylesheet";
private static final String C_P_177_4="text/css";
private static final String C_P_177_5="msg_effvarpp,effect";
private static final String C_P_177_6="{effectBean}";
private static final String C_P_177_7="aiki.beans.moves.effects";
private static final String C_P_177_8="EffectBean";
private static final String C_P_177_9="$intern.index=index";
private static final String C_P_177_10="$intern.move=move";
private static final String C_P_177_11="msg_effvarpp,delete_pp";
private static final String C_P_177_12="deletePp";
private PageDataMovesEffectsEffvarpp(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc82){
Element elt0_=el(_doc82,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_177_0));
attrs0_.add(at(C_BEAN,C_P_177_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc82,HEAD);
Element elt2_=el(_doc82,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_177_2));
attrs1_.add(at(REL,C_P_177_3));
attrs1_.add(at(TYPE,C_P_177_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc82,BODY);
build0(elt3_,_doc82);
ad(elt0_,elt3_);
_doc82.appendChild(elt0_);
}
static void build0(Element _body,Document _doc82){
Element elt0_=el(_doc82,P);
Element elt1_=el(_doc82,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_177_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc82,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_177_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc82,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_177_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc82,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_177_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc82,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_177_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc82,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_177_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc82,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_177_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc82,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_177_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
ad(_body,elt0_);
}
}
