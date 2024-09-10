package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataItemsSellingitem extends PageCardsCommon{
private static final String C_P_130_0="javahtml";
private static final String C_P_130_1="sellingitem";
private static final String C_P_130_2="msg_item,title";
private static final String C_P_130_3="displayName";
private static final String C_P_130_4=PkScriptPages.REN_ADD_WEB_CSS_ITEMS_CSS;
private static final String C_P_130_5="stylesheet";
private static final String C_P_130_6="text/css";
private static final String C_P_130_7="itemBean";
private static final String C_P_130_8="aiki.beans.items";
private static final String C_P_130_9="ItemBean";
private static final String C_P_130_10="$intern.name=name";
private PageDataItemsSellingitem(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc35){
Element elt0_=el(_doc35,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_130_0));
attrs0_.add(at(C_BEAN,C_P_130_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc35,HEAD);
Element elt2_=el(_doc35,TITLE);
Element elt3_=el(_doc35,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_130_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc35,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_130_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc35,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_130_4));
attrs3_.add(at(REL,C_P_130_5));
attrs3_.add(at(TYPE,C_P_130_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc35,BODY);
build0(elt6_,_doc35);
ad(elt0_,elt6_);
_doc35.appendChild(elt0_);
}
static void build0(Element _body,Document _doc35){
Element elt0_=el(_doc35,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_130_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc35,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_130_8));
at(elt1_,attrs1_);
Element elt2_=el(_doc35,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_130_9));
at(elt2_,attrs2_);
Element elt3_=el(_doc35,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_130_10));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
