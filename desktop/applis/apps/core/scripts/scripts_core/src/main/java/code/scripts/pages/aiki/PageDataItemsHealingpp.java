package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsHealingpp extends PageAikiCommon{
private static final String C_P_124_0="javahtml";
private static final String C_P_124_1="healingpp";
private static final String C_P_124_2="msg_item,title";
private static final String C_P_124_3="displayName";
private static final String C_P_124_4="web/css/items.css";
private static final String C_P_124_5="stylesheet";
private static final String C_P_124_6="text/css";
private static final String C_P_124_7="{healingItemBean}";
private static final String C_P_124_8="aiki.beans.items";
private static final String C_P_124_9="ItemBean";
private static final String C_P_124_10="$intern.name=name";
private static final String C_P_124_11="healingAllMovesPp";
private static final String C_P_124_12="msg_healingpp,full_heal_moves";
private static final String C_P_124_13="healingMoveFullpp";
private static final String C_P_124_14="msg_healingpp,full_heal_move";
private static final String C_P_124_15="limitedPpMove()";
private static final String C_P_124_16="msg_healingpp,heal_move";
private static final String C_P_124_17="healedMovePp";
private static final String C_P_124_18="limitedPpMoves()";
private static final String C_P_124_19="msg_healingpp,heal_moves";
private static final String C_P_124_20="healingAllMovesFullpp";
private PageDataItemsHealingpp(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc29){
Element elt0_=el(_doc29,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_124_0));
attrs0_.add(at(C_BEAN,C_P_124_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc29,HEAD);
Element elt2_=el(_doc29,TITLE);
Element elt3_=el(_doc29,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_124_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc29,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_124_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc29,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_124_4));
attrs3_.add(at(REL,C_P_124_5));
attrs3_.add(at(TYPE,C_P_124_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc29,BODY);
build0(elt6_,_doc29);
build1(elt6_,_doc29);
ad(elt0_,elt6_);
_doc29.appendChild(elt0_);
}
static void build0(Element _body,Document _doc29){
Element elt0_=el(_doc29,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_124_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc29,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_124_8));
at(elt1_,attrs1_);
Element elt2_=el(_doc29,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_124_9));
at(elt2_,attrs2_);
Element elt3_=el(_doc29,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_124_10));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc29){
Element elt0_=el(_doc29,P);
Element elt1_=el(_doc29,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_124_11));
at(elt1_,attrs0_);
Element elt2_=el(_doc29,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_124_12));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc29,C_IF);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(CONDITION,C_P_124_13));
at(elt3_,attrs2_);
Element elt4_=el(_doc29,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_124_14));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt5_=el(_doc29,C_IF);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(CONDITION,C_P_124_15));
at(elt5_,attrs4_);
Element elt6_=el(_doc29,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_124_16));
at(elt6_,attrs5_);
Element elt7_=el(_doc29,PARAM);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_124_17));
at(elt7_,attrs6_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
Element elt8_=el(_doc29,C_IF);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(CONDITION,C_P_124_18));
at(elt8_,attrs7_);
Element elt9_=el(_doc29,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_124_19));
at(elt9_,attrs8_);
Element elt10_=el(_doc29,PARAM);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_124_20));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
ad(_body,elt0_);
}
}
