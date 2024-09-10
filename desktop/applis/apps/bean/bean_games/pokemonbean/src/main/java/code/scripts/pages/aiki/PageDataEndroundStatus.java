package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataEndroundStatus extends PageCardsCommon{
private static final String C_P_110_0="javahtml";
private static final String C_P_110_1="end_status";
private static final String C_P_110_2=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_110_3="stylesheet";
private static final String C_P_110_4="text/css";
private static final String C_P_110_5="endRoundHtml";
private static final String C_P_110_6="aiki.beans.endround";
private static final String C_P_110_7="EffectEndRoundBean";
private static final String C_P_110_8="$intern.index=index";
private static final String C_P_110_9="msg_statusend,effect";
private static final String C_P_110_10="!inflictedRateHpTarget.isZero()";
private static final String C_P_110_11="msg_statusend,inflicted_rate_hp_target";
private static final String C_P_110_12="inflictedRateHpTarget";
private PageDataEndroundStatus(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc15){
Element elt0_=el(_doc15,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_110_0));
attrs0_.add(at(C_BEAN,C_P_110_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc15,HEAD);
Element elt2_=el(_doc15,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_110_2));
attrs1_.add(at(REL,C_P_110_3));
attrs1_.add(at(TYPE,C_P_110_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc15,BODY);
build0(elt3_,_doc15);
build1(elt3_,_doc15);
ad(elt0_,elt3_);
_doc15.appendChild(elt0_);
}
static void build0(Element _body,Document _doc15){
Element elt0_=el(_doc15,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_110_5));
at(elt0_,attrs0_);
Element elt1_=el(_doc15,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_110_6));
at(elt1_,attrs1_);
Element elt2_=el(_doc15,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_110_7));
at(elt2_,attrs2_);
Element elt3_=el(_doc15,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_110_8));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc15){
Element elt0_=el(_doc15,P);
Element elt1_=el(_doc15,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_110_9));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc15,C_IF);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(CONDITION,C_P_110_10));
at(elt2_,attrs1_);
Element elt3_=el(_doc15,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_110_11));
at(elt3_,attrs2_);
Element elt4_=el(_doc15,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_110_12));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
}
