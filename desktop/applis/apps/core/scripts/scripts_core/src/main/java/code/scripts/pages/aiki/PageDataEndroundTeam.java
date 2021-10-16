package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataEndroundTeam extends PageAikiCommon{
private static final String C_P_112_0="javahtml";
private static final String C_P_112_1="end_team";
private static final String C_P_112_2="web/css/abilities.css";
private static final String C_P_112_3="stylesheet";
private static final String C_P_112_4="text/css";
private static final String C_P_112_5="{endRoundHtml}";
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
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_112_0));
attrs0_.add(at(C_BEAN,C_P_112_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_112_2));
attrs1_.add(at(REL,C_P_112_3));
attrs1_.add(at(TYPE,C_P_112_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,BODY);
build0(elt3_,_doc);
build1(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_112_5));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_112_6));
at(elt1_,attrs1_);
Element elt2_=el(_doc,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_112_7));
at(elt2_,attrs2_);
Element elt3_=el(_doc,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_112_8));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,P);
Element elt1_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_112_9));
at(elt1_,attrs0_);
Element elt2_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_112_10));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_112_11));
at(elt3_,attrs2_);
Element elt4_=el(_doc,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_112_12));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_112_13));
at(elt5_,attrs4_);
Element elt6_=el(_doc,PARAM);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_112_14));
at(elt6_,attrs5_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
ad(_body,elt0_);
}
}
