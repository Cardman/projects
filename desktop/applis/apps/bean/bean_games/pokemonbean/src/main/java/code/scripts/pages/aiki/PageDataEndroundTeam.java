package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataEndroundTeam extends PageCardsCommon{
private static final String C_P_112_0="javahtml";
private static final String C_P_112_1="end_team";
private static final String C_P_112_2=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_112_3="stylesheet";
private static final String C_P_112_4="text/css";
private static final String C_P_112_5="endRoundHtml";
private static final String C_P_112_6="aiki.beans.endround";
private static final String C_P_112_7="EffectEndRoundBean";
private static final String C_P_112_8="$intern.index=index";
private static final String C_P_112_9="!deleteAllStatus.isZero()";
private static final String C_P_112_10="msg_team,effect";
private static final String C_P_112_11="msg_team,owner";
private static final String C_P_112_12="deleteAllStatus";
private static final String C_P_112_13="msg_team,team";
private static final String C_P_112_14="deleteAllStatusAlly";
private PageDataEndroundTeam(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc17){
Element elt0_=el(_doc17,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_112_0));
attrs0_.add(at(C_BEAN,C_P_112_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc17,HEAD);
Element elt2_=el(_doc17,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_112_2));
attrs1_.add(at(REL,C_P_112_3));
attrs1_.add(at(TYPE,C_P_112_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc17,BODY);
build0(elt3_,_doc17);
build1(elt3_,_doc17);
ad(elt0_,elt3_);
_doc17.appendChild(elt0_);
}
static void build0(Element _body,Document _doc17){
Element elt0_=el(_doc17,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_112_5));
at(elt0_,attrs0_);
Element elt1_=el(_doc17,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_112_6));
at(elt1_,attrs1_);
Element elt2_=el(_doc17,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_112_7));
at(elt2_,attrs2_);
Element elt3_=el(_doc17,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_112_8));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc17){
Element elt0_=el(_doc17,P);
Element elt2_=el(_doc17,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_112_10));
at(elt2_,attrs1_);
ad(elt0_,elt2_);
Element elt1_=el(_doc17,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_112_9));
at(elt1_,attrs0_);
Element elt3_=el(_doc17,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_112_11));
at(elt3_,attrs2_);
Element elt4_=el(_doc17,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_112_12));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt5_=el(_doc17,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_112_13));
at(elt5_,attrs4_);
Element elt6_=el(_doc17,PARAM);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_112_14));
at(elt6_,attrs5_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
}
