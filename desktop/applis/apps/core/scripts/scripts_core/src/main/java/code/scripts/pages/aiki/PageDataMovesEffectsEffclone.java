package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffclone extends PageAikiCommon{
private static final String C_P_148_0="javahtml";
private static final String C_P_148_1="eff_clone";
private static final String C_P_148_2="web/css/moves.css";
private static final String C_P_148_3="stylesheet";
private static final String C_P_148_4="text/css";
private static final String C_P_148_5="msg_effclone,effect";
private static final String C_P_148_6="hpRateClone";
private static final String C_P_148_7="{effectBean}";
private static final String C_P_148_8="aiki.beans.moves.effects";
private static final String C_P_148_9="EffectBean";
private static final String C_P_148_10="$intern.index=index";
private static final String C_P_148_11="$intern.move=move";
private static final String C_P_148_12="msg_effclone,effect_2";
private static final String C_P_148_13="!movesEndRound.isEmpty()";
private static final String C_P_148_14="msg_effclone,effect_3";
private static final String C_P_148_15="movesEndRound";
private static final String C_P_148_16="m";
private static final String C_P_148_17="$clickMoveEndRound({([m])})";
private static final String C_P_148_18="";
private static final String C_P_148_19="{getTrMovesEndRound(([m]))}";
private static final String C_P_148_20="!movesBatonPass.isEmpty()";
private static final String C_P_148_21="!movesSending.isEmpty()";
private static final String C_P_148_22="msg_effclone,effect_4";
private static final String C_P_148_23="movesBatonPass";
private static final String C_P_148_24="m";
private static final String C_P_148_25="$clickMoveBatonPass({([m])})";
private static final String C_P_148_26="";
private static final String C_P_148_27="{getTrMovesBatonPass(([m]))}";
private static final String C_P_148_28="msg_effclone,effect_5";
private static final String C_P_148_29="movesSending";
private static final String C_P_148_30="m";
private static final String C_P_148_31="$clickMoveSending({([m])})";
private static final String C_P_148_32="";
private static final String C_P_148_33="{getTrMovesSending(([m]))}";
private static final String C_P_148_34="msg_effclone,effect_6";
private static final String C_P_148_35="msg_effclone,effect_7";
private static final String C_P_148_36="msg_effclone,effect_8";
private static final String C_P_148_37="msg_effclone,effect_9";
private PageDataMovesEffectsEffclone(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_148_0));
attrs0_.add(at(C_BEAN,C_P_148_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_148_2));
attrs1_.add(at(REL,C_P_148_3));
attrs1_.add(at(TYPE,C_P_148_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,BODY);
build0(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,P);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_148_5));
at(elt1_,attrs0_);
Element elt2_=el(_doc,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_148_6));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,C_IMPORT);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(PAGE,C_P_148_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc,C_PACKAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_148_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc,C_CLASS);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(NAME,C_P_148_9));
at(elt5_,attrs4_);
Element elt6_=el(_doc,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_148_10));
at(elt6_,attrs5_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,C_FIELD);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(PREPARE,C_P_148_11));
at(elt7_,attrs6_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt8_=el(_doc,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_148_12));
at(elt8_,attrs7_);
ad(elt0_,elt8_);
Element elt9_=el(_doc,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_148_13));
at(elt9_,attrs8_);
Element elt10_=el(_doc,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_148_14));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,UL);
Element elt12_=el(_doc,C_FOR);
CustList<Attr> attrs10_=al(2);
attrs10_.add(at(LIST,C_P_148_15));
attrs10_.add(at(VAR,C_P_148_16));
at(elt12_,attrs10_);
Element elt13_=el(_doc,LI);
Element elt14_=el(_doc,A);
CustList<Attr> attrs11_=al(2);
attrs11_.add(at(C_COMMAND,C_P_148_17));
attrs11_.add(at(HREF,C_P_148_18));
at(elt14_,attrs11_);
Text txt0_=tx(_doc,C_P_148_19);
ad(elt14_,txt0_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
ad(elt9_,elt11_);
Element elt15_=el(_doc,BR);
ad(elt9_,elt15_);
ad(elt0_,elt9_);
Element elt16_=el(_doc,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_148_20));
at(elt16_,attrs12_);
Element elt17_=el(_doc,C_IF);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(CONDITION,C_P_148_21));
at(elt17_,attrs13_);
Element elt18_=el(_doc,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_148_22));
at(elt18_,attrs14_);
ad(elt17_,elt18_);
Element elt19_=el(_doc,UL);
Element elt20_=el(_doc,C_FOR);
CustList<Attr> attrs15_=al(2);
attrs15_.add(at(LIST,C_P_148_23));
attrs15_.add(at(VAR,C_P_148_24));
at(elt20_,attrs15_);
Element elt21_=el(_doc,LI);
Element elt22_=el(_doc,A);
CustList<Attr> attrs16_=al(2);
attrs16_.add(at(C_COMMAND,C_P_148_25));
attrs16_.add(at(HREF,C_P_148_26));
at(elt22_,attrs16_);
Text txt1_=tx(_doc,C_P_148_27);
ad(elt22_,txt1_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt17_,elt19_);
Element elt23_=el(_doc,BR);
ad(elt17_,elt23_);
Element elt24_=el(_doc,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_148_28));
at(elt24_,attrs17_);
ad(elt17_,elt24_);
Element elt25_=el(_doc,UL);
Element elt26_=el(_doc,C_FOR);
CustList<Attr> attrs18_=al(2);
attrs18_.add(at(LIST,C_P_148_29));
attrs18_.add(at(VAR,C_P_148_30));
at(elt26_,attrs18_);
Element elt27_=el(_doc,LI);
Element elt28_=el(_doc,A);
CustList<Attr> attrs19_=al(2);
attrs19_.add(at(C_COMMAND,C_P_148_31));
attrs19_.add(at(HREF,C_P_148_32));
at(elt28_,attrs19_);
Text txt2_=tx(_doc,C_P_148_33);
ad(elt28_,txt2_);
ad(elt27_,elt28_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt17_,elt25_);
Element elt29_=el(_doc,BR);
ad(elt17_,elt29_);
ad(elt16_,elt17_);
ad(elt0_,elt16_);
Element elt30_=el(_doc,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_148_34));
at(elt30_,attrs20_);
ad(elt0_,elt30_);
Element elt31_=el(_doc,C_MESSAGE);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_P_148_35));
at(elt31_,attrs21_);
ad(elt0_,elt31_);
Element elt32_=el(_doc,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_148_36));
at(elt32_,attrs22_);
ad(elt0_,elt32_);
Element elt33_=el(_doc,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_148_37));
at(elt33_,attrs23_);
ad(elt0_,elt33_);
ad(_body,elt0_);
}
}
