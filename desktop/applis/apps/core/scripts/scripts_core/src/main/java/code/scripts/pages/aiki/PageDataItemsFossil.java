package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsFossil extends PageAikiCommon{
private static final String C_P_120_0="javahtml";
private static final String C_P_120_1="fossil";
private static final String C_P_120_2="msg_item,title";
private static final String C_P_120_3="displayName";
private static final String C_P_120_4="web/css/items.css";
private static final String C_P_120_5="stylesheet";
private static final String C_P_120_6="text/css";
private static final String C_P_120_7="{itemBean}";
private static final String C_P_120_8="aiki.beans.items";
private static final String C_P_120_9="ItemBean";
private static final String C_P_120_10="$intern.name=name";
private static final String C_P_120_11="msg_fossil,fossil";
private static final String C_P_120_12="getTrPokemon()";
private static final String C_P_120_13="level";
private PageDataItemsFossil(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_120_0));
attrs0_.add(at(C_BEAN,C_P_120_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,TITLE);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_120_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_120_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_120_4));
attrs3_.add(at(REL,C_P_120_5));
attrs3_.add(at(TYPE,C_P_120_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc,BODY);
build0(elt6_,_doc);
build1(elt6_,_doc);
ad(elt0_,elt6_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_120_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_120_8));
at(elt1_,attrs1_);
Element elt2_=el(_doc,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_120_9));
at(elt2_,attrs2_);
Element elt3_=el(_doc,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_120_10));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,P);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_120_11));
at(elt1_,attrs0_);
Element elt2_=el(_doc,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_120_12));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_120_13));
at(elt3_,attrs2_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
