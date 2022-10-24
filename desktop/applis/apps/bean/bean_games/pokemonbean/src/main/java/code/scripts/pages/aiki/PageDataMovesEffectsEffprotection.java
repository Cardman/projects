package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffprotection extends PageCardsCommon{
private static final String C_P_163_0="javahtml";
private static final String C_P_163_1="eff_protection";
private static final String C_P_163_2="web/css/moves.css";
private static final String C_P_163_3="stylesheet";
private static final String C_P_163_4="text/css";
private static final String C_P_163_5="msg_effprotection,effect";
private static final String C_P_163_6="{effectBean}";
private static final String C_P_163_7="aiki.beans.moves.effects";
private static final String C_P_163_8="EffectBean";
private static final String C_P_163_9="$intern.index=index";
private static final String C_P_163_10="$intern.move=move";
private static final String C_P_163_11="protSingle";
private static final String C_P_163_12="msg_effprotection,prot_single";
private static final String C_P_163_13="!protSingleAgainstKo.isZero()";
private static final String C_P_163_14="msg_effprotection,prot_single_ko";
private static final String C_P_163_15="protSingleAgainstKo";
private static final String C_P_163_16="protTeamAgainstMultTargets";
private static final String C_P_163_17="msg_effprotection,prot_multi_targets";
private static final String C_P_163_18="protTeamAgainstPrio";
private static final String C_P_163_19="msg_effprotection,prot_prio";
private static final String C_P_163_20="protTeamAgainstStatusMoves";
private static final String C_P_163_21="msg_effprotection,prot_single_status";
private static final String C_P_163_22="protTeamAgainstDamageMoves";
private static final String C_P_163_23="msg_effprotection,prot_single_damage";
private PageDataMovesEffectsEffprotection(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc68){
Element elt0_=el(_doc68,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_163_0));
attrs0_.add(at(C_BEAN,C_P_163_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc68,HEAD);
Element elt2_=el(_doc68,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_163_2));
attrs1_.add(at(REL,C_P_163_3));
attrs1_.add(at(TYPE,C_P_163_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc68,BODY);
build0(elt3_,_doc68);
ad(elt0_,elt3_);
_doc68.appendChild(elt0_);
}
static void build0(Element _body,Document _doc68){
Element elt0_=el(_doc68,P);
Element elt1_=el(_doc68,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_163_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc68,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_163_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc68,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_163_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc68,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_163_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc68,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_163_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc68,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_163_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc68,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_163_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc68,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_163_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc68,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_163_13));
at(elt9_,attrs8_);
Element elt10_=el(_doc68,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_163_14));
at(elt10_,attrs9_);
Element elt11_=el(_doc68,PARAM);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_163_15));
at(elt11_,attrs10_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
Element elt12_=el(_doc68,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_163_16));
at(elt12_,attrs11_);
Element elt13_=el(_doc68,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_163_17));
at(elt13_,attrs12_);
ad(elt12_,elt13_);
ad(elt0_,elt12_);
Element elt14_=el(_doc68,C_IF);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(CONDITION,C_P_163_18));
at(elt14_,attrs13_);
Element elt15_=el(_doc68,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_163_19));
at(elt15_,attrs14_);
ad(elt14_,elt15_);
ad(elt0_,elt14_);
Element elt16_=el(_doc68,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_163_20));
at(elt16_,attrs15_);
Element elt17_=el(_doc68,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_163_21));
at(elt17_,attrs16_);
ad(elt16_,elt17_);
ad(elt0_,elt16_);
Element elt18_=el(_doc68,C_IF);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(CONDITION,C_P_163_22));
at(elt18_,attrs17_);
Element elt19_=el(_doc68,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_163_23));
at(elt19_,attrs18_);
ad(elt18_,elt19_);
ad(elt0_,elt18_);
ad(_body,elt0_);
}
}
