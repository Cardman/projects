package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffunprotectfromtypes extends PageCardsCommon{
private static final String C_P_176_0="javahtml";
private static final String C_P_176_1="eff_unprotectfromtypes";
private static final String C_P_176_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_176_3="stylesheet";
private static final String C_P_176_4="text/css";
private static final String C_P_176_5="msg_effunprotectfromtypes,effect";
private static final String C_P_176_6="effectBean";
private static final String C_P_176_7="aiki.beans.moves.effects";
private static final String C_P_176_8="EffectBean";
private static final String C_P_176_9="$intern.index=index";
private static final String C_P_176_10="$intern.move=move";
private static final String C_P_176_11="!types.isEmpty()";
private static final String C_P_176_12="msg_effunprotectfromtypes,types";
private static final String C_P_176_13="msg_effunprotectfromtypes,types_damag";
private static final String C_P_176_14="msg_effunprotectfromtypes,types_pk";
private static final String C_P_176_15="types";
private static final String C_P_176_16="d";
private static final String C_P_176_17="{getTrDamageType(([d]))}";
private static final String C_P_176_18="{getTrPokemonType(([d]))}";
private static final String C_P_176_19="!disableImmuAgainstTypes.isEmpty()";
private static final String C_P_176_20="msg_effunprotectfromtypes,disable_immu_types";
private static final String C_P_176_21="disableImmuAgainstTypes";
private static final String C_P_176_22="t";
private static final String C_P_176_23="{getTrDisableImmuType(([t]))}";
private static final String C_P_176_24="!disableImmuFromMoves.isEmpty()";
private static final String C_P_176_25="msg_effunprotectfromtypes,disable_immu_from_moves";
private static final String C_P_176_26="disableImmuFromMoves";
private static final String C_P_176_27="t";
private static final String C_P_176_28="$clickMove({index},{([t])})";
private static final String C_P_176_29="";
private static final String C_P_176_30="{getTrDisableImmuMove(([t]))}";
private static final String C_P_176_31="!attackTargetWithTypes.isEmpty()";
private static final String C_P_176_32="msg_effunprotectfromtypes,attack_target_types";
private static final String C_P_176_33="attackTargetWithTypes";
private static final String C_P_176_34="t";
private static final String C_P_176_35="{getTrAttackTargetType(([t]))}";
private PageDataMovesEffectsEffunprotectfromtypes(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc81){
Element elt0_=el(_doc81,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_176_0));
attrs0_.add(at(C_BEAN,C_P_176_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc81,HEAD);
Element elt2_=el(_doc81,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_176_2));
attrs1_.add(at(REL,C_P_176_3));
attrs1_.add(at(TYPE,C_P_176_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc81,BODY);
build0(elt3_,_doc81);
ad(elt0_,elt3_);
_doc81.appendChild(elt0_);
}
static void build0(Element _body,Document _doc81){
Element elt0_=el(_doc81,P);
Element elt1_=el(_doc81,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_176_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc81,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_176_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc81,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_176_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc81,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_176_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc81,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_176_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc81,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_176_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc81,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_176_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc81,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_176_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
Element elt9_=el(_doc81,TABLE);
Element elt10_=el(_doc81,THEAD);
Element elt11_=el(_doc81,TR);
Element elt12_=el(_doc81,TH);
Element elt13_=el(_doc81,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_176_13));
at(elt13_,attrs8_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
Element elt14_=el(_doc81,TH);
Element elt15_=el(_doc81,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_176_14));
at(elt15_,attrs9_);
ad(elt14_,elt15_);
ad(elt11_,elt14_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt16_=el(_doc81,TBODY);
Element elt17_=el(_doc81,C_FOR);
CustList<Attr> attrs10_=al(2);
attrs10_.add(at(LIST,C_P_176_15));
attrs10_.add(at(VAR,C_P_176_16));
at(elt17_,attrs10_);
Element elt18_=el(_doc81,TR);
Element elt19_=el(_doc81,TD);
Text txt0_=tx(_doc81,C_P_176_17);
ad(elt19_,txt0_);
ad(elt18_,elt19_);
Element elt20_=el(_doc81,TD);
Text txt1_=tx(_doc81,C_P_176_18);
ad(elt20_,txt1_);
ad(elt18_,elt20_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt9_,elt16_);
ad(elt7_,elt9_);
Element elt21_=el(_doc81,BR);
ad(elt7_,elt21_);
ad(elt0_,elt7_);
Element elt22_=el(_doc81,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_176_19));
at(elt22_,attrs11_);
Element elt23_=el(_doc81,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_176_20));
at(elt23_,attrs12_);
ad(elt22_,elt23_);
Element elt24_=el(_doc81,UL);
Element elt25_=el(_doc81,C_FOR);
CustList<Attr> attrs13_=al(2);
attrs13_.add(at(LIST,C_P_176_21));
attrs13_.add(at(VAR,C_P_176_22));
at(elt25_,attrs13_);
Element elt26_=el(_doc81,LI);
Text txt2_=tx(_doc81,C_P_176_23);
ad(elt26_,txt2_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt22_,elt24_);
Element elt27_=el(_doc81,BR);
ad(elt22_,elt27_);
ad(elt0_,elt22_);
Element elt28_=el(_doc81,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_176_24));
at(elt28_,attrs14_);
Element elt29_=el(_doc81,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_176_25));
at(elt29_,attrs15_);
ad(elt28_,elt29_);
Element elt30_=el(_doc81,UL);
Element elt31_=el(_doc81,C_FOR);
CustList<Attr> attrs16_=al(2);
attrs16_.add(at(LIST,C_P_176_26));
attrs16_.add(at(VAR,C_P_176_27));
at(elt31_,attrs16_);
Element elt32_=el(_doc81,LI);
Element elt33_=el(_doc81,A);
CustList<Attr> attrs17_=al(2);
attrs17_.add(at(C_COMMAND,C_P_176_28));
attrs17_.add(at(HREF,C_P_176_29));
at(elt33_,attrs17_);
Text txt3_=tx(_doc81,C_P_176_30);
ad(elt33_,txt3_);
ad(elt32_,elt33_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
ad(elt28_,elt30_);
Element elt34_=el(_doc81,BR);
ad(elt28_,elt34_);
ad(elt0_,elt28_);
Element elt35_=el(_doc81,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_176_31));
at(elt35_,attrs18_);
Element elt36_=el(_doc81,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_176_32));
at(elt36_,attrs19_);
ad(elt35_,elt36_);
Element elt37_=el(_doc81,UL);
Element elt38_=el(_doc81,C_FOR);
CustList<Attr> attrs20_=al(2);
attrs20_.add(at(LIST,C_P_176_33));
attrs20_.add(at(VAR,C_P_176_34));
at(elt38_,attrs20_);
Element elt39_=el(_doc81,LI);
Text txt4_=tx(_doc81,C_P_176_35);
ad(elt39_,txt4_);
ad(elt38_,elt39_);
ad(elt37_,elt38_);
ad(elt35_,elt37_);
Element elt40_=el(_doc81,BR);
ad(elt35_,elt40_);
ad(elt0_,elt35_);
ad(_body,elt0_);
}
}
