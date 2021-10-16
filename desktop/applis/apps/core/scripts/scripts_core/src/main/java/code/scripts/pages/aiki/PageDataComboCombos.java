package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataComboCombos extends PageAikiCommon{
private static final String C_P_100_0="javahtml";
private static final String C_P_100_1="combos";
private static final String C_P_100_2="msg_combo,title";
private static final String C_P_100_3="web/css/abilities.css";
private static final String C_P_100_4="stylesheet";
private static final String C_P_100_5="text/css";
private static final String C_P_100_6="web/html/index.html";
private static final String C_P_100_7="";
private static final String C_P_100_8="msg_combo,index";
private static final String C_P_100_9="getCombosKey()";
private static final String C_P_100_10="m";
private static final String C_P_100_11="{combo}";
private static final String C_P_100_12="aiki.beans.effects";
private static final String C_P_100_13="EffectComboBean";
private static final String C_P_100_14="$intern.combos=combos";
private static final String C_P_100_15="$intern.index=([m])";
private PageDataComboCombos(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_100_0));
attrs0_.add(at(C_BEAN,C_P_100_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,TITLE);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_100_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_100_3));
attrs2_.add(at(REL,C_P_100_4));
attrs2_.add(at(TYPE,C_P_100_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,BODY);
build0(elt5_,_doc);
build1(elt5_,_doc);
build2(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_100_6));
attrs0_.add(at(HREF,C_P_100_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_100_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_100_9));
attrs0_.add(at(VAR,C_P_100_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_100_11));
at(elt1_,attrs1_);
Element elt2_=el(_doc,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_100_12));
at(elt2_,attrs2_);
Element elt3_=el(_doc,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_100_13));
at(elt3_,attrs3_);
Element elt4_=el(_doc,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_100_14));
at(elt4_,attrs4_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_100_15));
at(elt5_,attrs5_);
ad(elt3_,elt5_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
