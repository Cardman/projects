package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataItemsItem extends PageCardsCommon{
private static final String C_P_126_0="javahtml";
private static final String C_P_126_1="item";
private static final String C_P_126_2=PkScriptPages.REN_ADD_WEB_CSS_ITEMS_CSS;
private static final String C_P_126_3="stylesheet";
private static final String C_P_126_4="text/css";
private static final String C_P_126_5="$clickItems()";
private static final String C_P_126_6="";
private static final String C_P_126_7="msg_item,items";
private static final String C_P_126_8="{displayName}";
private static final String C_P_126_9="itemImage";
private static final String C_P_126_10="";
private static final String C_P_126_11="msg_item,item_type";
private static final String C_P_126_12="description";
private static final String C_P_126_13="msg_item,price";
private static final String C_P_126_14="displayName";
private static final String C_P_126_15="price";
private PageDataItemsItem(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc31){
Element elt0_=el(_doc31,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_126_0));
attrs0_.add(at(C_BEAN,C_P_126_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc31,HEAD);
Element elt2_=el(_doc31,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_126_2));
attrs1_.add(at(REL,C_P_126_3));
attrs1_.add(at(TYPE,C_P_126_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc31,BODY);
build0(elt3_,_doc31);
ad(elt0_,elt3_);
_doc31.appendChild(elt0_);
}
static void build0(Element _body,Document _doc31){
Element elt0_=el(_doc31,P);
Element elt1_=el(_doc31,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_126_5));
attrs0_.add(at(HREF,C_P_126_6));
at(elt1_,attrs0_);
Element elt2_=el(_doc31,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_126_7));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc31,BR);
ad(elt0_,elt3_);
Text txt0_=tx(_doc31,C_P_126_8);
ad(elt0_,txt0_);
Element elt4_=el(_doc31,C_IMG);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(SRC,C_P_126_9));
at(elt4_,attrs2_);
ad(elt0_,elt4_);
Element elt5_=el(_doc31,BR);
ad(elt0_,elt5_);
Element elt6_=el(_doc31,C_MESSAGE);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(QUOTED,C_P_126_10));
attrs3_.add(at(VALUE,C_P_126_11));
at(elt6_,attrs3_);
Element elt7_=el(_doc31,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_126_12));
at(elt7_,attrs4_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt8_=el(_doc31,BR);
ad(elt0_,elt8_);
Element elt9_=el(_doc31,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_126_13));
at(elt9_,attrs5_);
Element elt10_=el(_doc31,PARAM);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_126_14));
at(elt10_,attrs6_);
ad(elt9_,elt10_);
Element elt11_=el(_doc31,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_126_15));
at(elt11_,attrs7_);
ad(elt9_,elt11_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
}
