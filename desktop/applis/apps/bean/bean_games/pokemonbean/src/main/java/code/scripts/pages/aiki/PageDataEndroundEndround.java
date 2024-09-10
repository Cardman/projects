package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataEndroundEndround extends PageCardsCommon{
private static final String C_P_102_0="javahtml";
private static final String C_P_102_1="endround";
private static final String C_P_102_2="msg_endround,title";
private static final String C_P_102_3=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_102_4="stylesheet";
private static final String C_P_102_5="text/css";
private static final String C_P_102_6=GO_TO_IND;
private static final String C_P_102_7="";
private static final String C_P_102_8="msg_moves,index";
private static final String C_P_102_9="getEvts()";
private static final String C_P_102_10="e";
private static final String C_P_102_11="getPage(([e]))";
private static final String C_P_102_12="aiki.beans.endround";
private static final String C_P_102_13="EffectEndRoundBean";
private static final String C_P_102_14="$intern.index=([e])";
private PageDataEndroundEndround(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc7){
Element elt0_=el(_doc7,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_102_0));
attrs0_.add(at(C_BEAN,C_P_102_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc7,HEAD);
Element elt2_=el(_doc7,TITLE);
Element elt3_=el(_doc7,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_102_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc7,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_102_3));
attrs2_.add(at(REL,C_P_102_4));
attrs2_.add(at(TYPE,C_P_102_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc7,BODY);
build0(elt5_,_doc7);
build1(elt5_,_doc7);
build2(elt5_,_doc7);
ad(elt0_,elt5_);
_doc7.appendChild(elt0_);
}
static void build0(Element _body,Document _doc7){
Element elt0_=el(_doc7,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_102_6));
attrs0_.add(at(HREF,C_P_102_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc7,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_102_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc7){
Element elt0_=el(_doc7,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc7){
Element elt0_=el(_doc7,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_102_9));
attrs0_.add(at(VAR,C_P_102_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc7,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_102_11));
at(elt1_,attrs1_);
Element elt2_=el(_doc7,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_102_12));
at(elt2_,attrs2_);
Element elt3_=el(_doc7,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_102_13));
at(elt3_,attrs3_);
Element elt4_=el(_doc7,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_102_14));
at(elt4_,attrs4_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
