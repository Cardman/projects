package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsBall extends PageAikiCommon{
private static final String C_P_115_0="javahtml";
private static final String C_P_115_1="ball";
private static final String C_P_115_2="msg_item,title";
private static final String C_P_115_3="displayName";
private static final String C_P_115_4="web/css/items.css";
private static final String C_P_115_5="stylesheet";
private static final String C_P_115_6="text/css";
private static final String C_P_115_7="{itemBean}";
private static final String C_P_115_8="aiki.beans.items";
private static final String C_P_115_9="ItemBean";
private static final String C_P_115_10="$intern.name=name";
private static final String C_P_115_11="msg_ball,rate_catching";
private static final String C_P_115_12="{catchingRate}";
private static final String C_P_115_13="!mapVars.isEmpty()";
private static final String C_P_115_14="k";
private static final String C_P_115_15="mapVars";
private static final String C_P_115_16="v";
private static final String C_P_115_17="java.lang.String";
private static final String C_P_115_18="java.lang.String";
private static final String C_P_115_19="{k} :";
private static final String C_P_115_20="";
private static final String C_P_115_21="msg_ball,formula";
private static final String C_P_115_22="v";
private PageDataItemsBall(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc20){
Element elt0_=el(_doc20,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_115_0));
attrs0_.add(at(C_BEAN,C_P_115_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc20,HEAD);
Element elt2_=el(_doc20,TITLE);
Element elt3_=el(_doc20,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_115_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc20,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_115_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc20,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_115_4));
attrs3_.add(at(REL,C_P_115_5));
attrs3_.add(at(TYPE,C_P_115_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc20,BODY);
build0(elt6_,_doc20);
build1(elt6_,_doc20);
ad(elt0_,elt6_);
_doc20.appendChild(elt0_);
}
static void build0(Element _body,Document _doc20){
Element elt0_=el(_doc20,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_115_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc20,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_115_8));
at(elt1_,attrs1_);
Element elt2_=el(_doc20,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_115_9));
at(elt2_,attrs2_);
Element elt3_=el(_doc20,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_115_10));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc20){
Element elt0_=el(_doc20,P);
Element elt1_=el(_doc20,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_115_11));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Text txt0_=tx(_doc20,C_P_115_12);
ad(elt0_,txt0_);
Element elt2_=el(_doc20,C_IF);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(CONDITION,C_P_115_13));
at(elt2_,attrs1_);
Element elt3_=el(_doc20,UL);
Element elt4_=el(_doc20,C_FOR);
CustList<Attr> attrs2_=al(5);
attrs2_.add(at(KEY,C_P_115_14));
attrs2_.add(at(MAP,C_P_115_15));
attrs2_.add(at(VALUE,C_P_115_16));
attrs2_.add(at(KEYCLASSNAME,C_P_115_17));
attrs2_.add(at(VARCLASSNAME,C_P_115_18));
at(elt4_,attrs2_);
Element elt5_=el(_doc20,LI);
Text txt1_=tx(_doc20,C_P_115_19);
ad(elt5_,txt1_);
Element elt6_=el(_doc20,C_MESSAGE);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(QUOTED,C_P_115_20));
attrs3_.add(at(VALUE,C_P_115_21));
at(elt6_,attrs3_);
Element elt7_=el(_doc20,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_115_22));
at(elt7_,attrs4_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt8_=el(_doc20,BR);
ad(elt2_,elt8_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
}
