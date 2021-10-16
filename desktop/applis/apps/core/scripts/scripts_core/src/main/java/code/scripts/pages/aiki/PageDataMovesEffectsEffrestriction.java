package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffrestriction extends PageAikiCommon{
private static final String C_P_165_0="javahtml";
private static final String C_P_165_1="eff_restriction";
private static final String C_P_165_2="web/css/moves.css";
private static final String C_P_165_3="stylesheet";
private static final String C_P_165_4="text/css";
private static final String C_P_165_5="forbidTargetUsingItem";
private static final String C_P_165_6="msg_effrestriction,effect_item";
private static final String C_P_165_7="msg_effrestriction,effect_item_2";
private static final String C_P_165_8="forbid()";
private static final String C_P_165_9="msg_effrestriction,effect_move";
private static final String C_P_165_10="{effectBean}";
private static final String C_P_165_11="aiki.beans.moves.effects";
private static final String C_P_165_12="EffectBean";
private static final String C_P_165_13="$intern.index=index";
private static final String C_P_165_14="$intern.move=move";
private static final String C_P_165_15="forbidStatusMove()";
private static final String C_P_165_16="msg_effrestriction,forbid_status_move";
private static final String C_P_165_17="forbidLastMove()";
private static final String C_P_165_18="msg_effrestriction,forbid_last_move";
private static final String C_P_165_19="forbidUserMoves()";
private static final String C_P_165_20="msg_effrestriction,forbid_user_moves";
private static final String C_P_165_21="forbidUseMove()";
private static final String C_P_165_22="msg_effrestriction,forbid_use_last_move";
private static final String C_P_165_23="forceUseMove()";
private static final String C_P_165_24="msg_effrestriction,force_use_last_move";
private PageDataMovesEffectsEffrestriction(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc70){
Element elt0_=el(_doc70,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_165_0));
attrs0_.add(at(C_BEAN,C_P_165_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc70,HEAD);
Element elt2_=el(_doc70,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_165_2));
attrs1_.add(at(REL,C_P_165_3));
attrs1_.add(at(TYPE,C_P_165_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc70,BODY);
build0(elt3_,_doc70);
ad(elt0_,elt3_);
_doc70.appendChild(elt0_);
}
static void build0(Element _body,Document _doc70){
Element elt0_=el(_doc70,P);
Element elt1_=el(_doc70,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_165_5));
at(elt1_,attrs0_);
Element elt2_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_165_6));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_165_7));
at(elt3_,attrs2_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt4_=el(_doc70,C_IF);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(CONDITION,C_P_165_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_165_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
ad(elt0_,elt4_);
Element elt6_=el(_doc70,C_IMPORT);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PAGE,C_P_165_10));
at(elt6_,attrs5_);
Element elt7_=el(_doc70,C_PACKAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(NAME,C_P_165_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc70,C_CLASS);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(NAME,C_P_165_12));
at(elt8_,attrs7_);
Element elt9_=el(_doc70,C_FIELD);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(PREPARE,C_P_165_13));
at(elt9_,attrs8_);
ad(elt8_,elt9_);
Element elt10_=el(_doc70,C_FIELD);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(PREPARE,C_P_165_14));
at(elt10_,attrs9_);
ad(elt8_,elt10_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt11_=el(_doc70,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_165_15));
at(elt11_,attrs10_);
Element elt12_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_165_16));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
Element elt13_=el(_doc70,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_165_17));
at(elt13_,attrs12_);
Element elt14_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_165_18));
at(elt14_,attrs13_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
Element elt15_=el(_doc70,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_165_19));
at(elt15_,attrs14_);
Element elt16_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_165_20));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
ad(elt0_,elt15_);
Element elt17_=el(_doc70,C_IF);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(CONDITION,C_P_165_21));
at(elt17_,attrs16_);
Element elt18_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_165_22));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt0_,elt17_);
Element elt19_=el(_doc70,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_165_23));
at(elt19_,attrs18_);
Element elt20_=el(_doc70,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_165_24));
at(elt20_,attrs19_);
ad(elt19_,elt20_);
ad(elt0_,elt19_);
ad(_body,elt0_);
}
}
