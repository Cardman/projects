package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffcopymove extends PageCardsCommon{
private static final String C_P_151_0="javahtml";
private static final String C_P_151_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_COPY_MOVE;
private static final String C_P_151_2="web/css/moves.css";
private static final String C_P_151_3="stylesheet";
private static final String C_P_151_4="text/css";
private static final String C_P_151_5="msg_effcopymove,effect";
private static final String C_P_151_6_LK="$clickDefaultMove()";
private static final String C_P_151_6="msg_effcopymove,no_effect";
private static final String C_P_151_7="{getTrDefaultMove()}";
private static final String C_P_151_8="{effectBean}";
private static final String C_P_151_9="aiki.beans.moves.effects";
private static final String C_P_151_10="EffectBean";
private static final String C_P_151_11="$intern.index=index";
private static final String C_P_151_12="$intern.move=move";
private static final String C_P_151_13="copyMoveForUser()";
private static final String C_P_151_14="msg_effcopymove,copy_tmp_move";
private static final String C_P_151_15="displayName";
private static final String C_P_151_16="copyingMoveForUser";
private static final String C_P_151_17="copyingMoveForUserDef";
private static final String C_P_151_18="msg_effcopymove,no_effect_2";
private static final String C_P_151_19="{getTrDefaultMove()}";
private static final String C_P_151_20="!movesTransforming.isEmpty()";
private static final String C_P_151_21="msg_effcopymove,copy_def_move";
private static final String C_P_151_22="displayName";
private static final String C_P_151_23="movesTransforming";
private static final String C_P_151_24="m";
private static final String C_P_151_25="$clickMoveTrans({([m])})";
private static final String C_P_151_26="";
private static final String C_P_151_27="{getTrMoveTrans(([m]))}";
private static final String C_P_151_28="movesTransforming.isEmpty()";
private static final String C_P_151_29="msg_effcopymove,copy_def_move_without_trans";
private static final String C_P_151_30="displayName";
private static final String C_P_151_31="!movesNotToBeCopied.isEmpty()";
private static final String C_P_151_32="msg_effcopymove,moves_not_copied";
private static final String C_P_151_33="movesNotToBeCopied";
private static final String C_P_151_34="s";
private static final String C_P_151_35="$clickMove({([s])})";
private static final String C_P_151_36="";
private static final String C_P_151_37="{getTrMove(([s]))}";
private PageDataMovesEffectsEffcopymove(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc56){
Element elt0_=el(_doc56,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_151_0));
attrs0_.add(at(C_BEAN,C_P_151_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc56,HEAD);
Element elt2_=el(_doc56,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_151_2));
attrs1_.add(at(REL,C_P_151_3));
attrs1_.add(at(TYPE,C_P_151_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc56,BODY);
build0(elt3_,_doc56);
ad(elt0_,elt3_);
_doc56.appendChild(elt0_);
}
static void build0(Element _body,Document _doc56){
Element elt0_=el(_doc56,P);
Element elt1_=el(_doc56,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_151_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc56,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_151_6));
at(elt2_,attrs1_);
ad(elt0_,elt2_);
Element elt12a_=el(_doc56,A);
CustList<Attr> attrs12a_=al(1);
attrs12a_.add(at(C_COMMAND,C_P_151_6_LK));
at(elt12a_,attrs12a_);
Text txt3_=tx(_doc56,C_P_151_7);
ad(elt12a_,txt3_);
ad(elt0_,elt12a_);
br(elt0_,_doc56);
Element elt4_=el(_doc56,C_IMPORT);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PAGE,C_P_151_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc56,C_PACKAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(NAME,C_P_151_9));
at(elt5_,attrs4_);
Element elt6_=el(_doc56,C_CLASS);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(NAME,C_P_151_10));
at(elt6_,attrs5_);
Element elt7_=el(_doc56,C_FIELD);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(PREPARE,C_P_151_11));
at(elt7_,attrs6_);
ad(elt6_,elt7_);
Element elt8_=el(_doc56,C_FIELD);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(PREPARE,C_P_151_12));
at(elt8_,attrs7_);
ad(elt6_,elt8_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt0_,elt4_);
Element elt9_=el(_doc56,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_151_13));
at(elt9_,attrs8_);
Element elt10_=el(_doc56,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_151_14));
at(elt10_,attrs9_);
Element elt11_=el(_doc56,PARAM);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_151_15));
at(elt11_,attrs10_);
ad(elt10_,elt11_);
Element elt12_=el(_doc56,PARAM);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_151_16));
at(elt12_,attrs11_);
ad(elt10_,elt12_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
Element elt13_=el(_doc56,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_151_17));
at(elt13_,attrs12_);
Element elt14_=el(_doc56,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_151_18));
at(elt14_,attrs13_);
ad(elt13_,elt14_);
Element elt13a_=el(_doc56,A);
CustList<Attr> attrs13a_=al(1);
attrs13a_.add(at(C_COMMAND,C_P_151_6_LK));
at(elt13a_,attrs13a_);
Text txt4_=tx(_doc56,C_P_151_19);
ad(elt13a_,txt4_);
ad(elt13_,elt13a_);
br(elt13_,_doc56);
Element elt16_=el(_doc56,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_151_20));
at(elt16_,attrs15_);
Element elt17_=el(_doc56,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_151_21));
at(elt17_,attrs16_);
Element elt18_=el(_doc56,PARAM);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_151_22));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
Element elt19_=el(_doc56,UL);
Element elt20_=el(_doc56,C_FOR);
CustList<Attr> attrs18_=al(2);
attrs18_.add(at(LIST,C_P_151_23));
attrs18_.add(at(VAR,C_P_151_24));
at(elt20_,attrs18_);
Element elt21_=el(_doc56,LI);
Element elt22_=el(_doc56,A);
CustList<Attr> attrs19_=al(2);
attrs19_.add(at(C_COMMAND,C_P_151_25));
attrs19_.add(at(HREF,C_P_151_26));
at(elt22_,attrs19_);
Text txt0_=tx(_doc56,C_P_151_27);
ad(elt22_,txt0_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt16_,elt19_);
ad(elt13_,elt16_);
Element elt23_=el(_doc56,C_IF);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(CONDITION,C_P_151_28));
at(elt23_,attrs20_);
Element elt24_=el(_doc56,C_MESSAGE);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_P_151_29));
at(elt24_,attrs21_);
Element elt25_=el(_doc56,PARAM);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_151_30));
at(elt25_,attrs22_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt13_,elt23_);
Element elt26_=el(_doc56,BR);
ad(elt13_,elt26_);
ad(elt0_,elt13_);
Element elt27_=el(_doc56,C_IF);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(CONDITION,C_P_151_31));
at(elt27_,attrs23_);
Element elt28_=el(_doc56,C_MESSAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_P_151_32));
at(elt28_,attrs24_);
ad(elt27_,elt28_);
Element elt29_=el(_doc56,UL);
Element elt30_=el(_doc56,C_FOR);
CustList<Attr> attrs25_=al(2);
attrs25_.add(at(LIST,C_P_151_33));
attrs25_.add(at(VAR,C_P_151_34));
at(elt30_,attrs25_);
Element elt31_=el(_doc56,LI);
Element elt32_=el(_doc56,A);
CustList<Attr> attrs26_=al(2);
attrs26_.add(at(C_COMMAND,C_P_151_35));
attrs26_.add(at(HREF,C_P_151_36));
at(elt32_,attrs26_);
Text txt1_=tx(_doc56,C_P_151_37);
ad(elt32_,txt1_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
ad(elt29_,elt30_);
ad(elt27_,elt29_);
Element elt33_=el(_doc56,BR);
ad(elt27_,elt33_);
ad(elt0_,elt27_);
ad(_body,elt0_);
}
}
