package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataStatusStatus extends PageCardsCommon{
private static final String C_P_205_0="javahtml";
private static final String C_P_205_1="status_set";
private static final String C_P_205_2="msg_statusset,title";
private static final String C_P_205_3=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_205_4="stylesheet";
private static final String C_P_205_5="text/css";
private static final String C_P_205_6=GO_TO_IND;
private static final String C_P_205_7="";
private static final String C_P_205_8="msg_statusset,index";
private static final String C_P_205_9="";
private static final String C_P_205_10="$search";
private static final String C_P_205_11="post";
private static final String C_P_205_12="searching";
private static final String C_P_205_13="msg_statusset,content";
private static final String C_P_205_14="typedStatus";
private static final String C_P_205_15="typedStatus";
private static final String C_P_205_16="text";
private static final String C_P_205_18="msg_statusset,ok";
private static final String C_P_205_19="sortedStatus";
private static final String C_P_205_20="a";
private static final String C_P_205_21="$clickStatus({([a])})";
private static final String C_P_205_22="";
private static final String C_P_205_23="{getTrStatus(([a]))}";
private PageDataStatusStatus(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc110){
Element elt0_=el(_doc110,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_205_0));
attrs0_.add(at(C_BEAN,C_P_205_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc110,HEAD);
Element elt2_=el(_doc110,TITLE);
Element elt3_=el(_doc110,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_205_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc110,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_205_3));
attrs2_.add(at(REL,C_P_205_4));
attrs2_.add(at(TYPE,C_P_205_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc110,BODY);
build0(elt5_,_doc110);
build1(elt5_,_doc110);
build2(elt5_,_doc110);
br(elt5_,_doc110);
build4(elt5_,_doc110);
ad(elt0_,elt5_);
_doc110.appendChild(elt0_);
}
static void build0(Element _body,Document _doc110){
Element elt0_=el(_doc110,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_205_6));
attrs0_.add(at(HREF,C_P_205_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc110,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_205_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc110){
Element elt0_=el(_doc110,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc110){
Element elt0_=el(_doc110,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_205_9));
attrs0_.add(at(C_COMMAND,C_P_205_10));
attrs0_.add(at(METHOD,C_P_205_11));
attrs0_.add(at(NAME,C_P_205_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc110,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_205_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc110,INPUT);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(C_VARVALUE,C_P_205_14));
attrs2_.add(at(NAME,C_P_205_15));
attrs2_.add(at(TYPE,C_P_205_16));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc110,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc110,C_SUBMIT);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(MESSAGE,C_P_205_18));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc110){
Element elt0_=el(_doc110,UL);
Element elt1_=el(_doc110,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_205_19));
attrs0_.add(at(VAR,C_P_205_20));
at(elt1_,attrs0_);
Element elt2_=el(_doc110,LI);
Element elt3_=el(_doc110,A);
CustList<Attr> attrs1_=al(2);
attrs1_.add(at(C_COMMAND,C_P_205_21));
attrs1_.add(at(HREF,C_P_205_22));
at(elt3_,attrs1_);
Text txt0_=tx(_doc110,C_P_205_23);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
