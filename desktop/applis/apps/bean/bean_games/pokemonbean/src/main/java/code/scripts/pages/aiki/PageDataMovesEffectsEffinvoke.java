package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffinvoke extends PageCardsCommon{
private static final String C_P_158_0="javahtml";
private static final String C_P_158_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_INVOKE;
private static final String C_P_158_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_158_3="stylesheet";
private static final String C_P_158_4="text/css";
private static final String C_P_158_5="msg_effinvoke,effect";
//private static final String C_P_158_6="effectBean";
//private static final String C_P_158_7="aiki.beans.moves.effects";
//private static final String C_P_158_8="EffectBean";
//private static final String C_P_158_9="$intern.index=index";
//private static final String C_P_158_10="$intern.move=move";
private static final String C_P_158_11="invokingMoveButUser";
private static final String C_P_158_12="msg_effinvoke,invoke_move_but_user";
private static final String C_P_158_13="invokingTargetChosenMove";
private static final String C_P_158_14="msg_effinvoke,invoke_target_chosen_move";
private static final String C_P_158_15="invokingUserMoveWhileSleep";
private static final String C_P_158_16="msg_effinvoke,invoke_user_move_while_sleep";
private static final String C_P_158_17="invokingAllyMove";
private static final String C_P_158_18="msg_effinvoke,invoke_move_part";
private static final String C_P_158_19="invokingTargetSuccesfulMove";
private static final String C_P_158_20="msg_effinvoke,invoke_move_success_target";
private static final String C_P_158_21="invokingSufferedMove";
private static final String C_P_158_22="msg_effinvoke,invoke_suffered_move";
private static final String C_P_158_23="!rateInvokationMove.isZero()";
private static final String C_P_158_24="msg_effinvoke,rate_invoke_move";
private static final String C_P_158_25="rateInvokationMove";
private static final String C_P_158_26="!moveFctEnv.isEmpty()";
private static final String C_P_158_27="msg_effinvoke,move_fct_env";
private static final String C_P_158_28="msg_effinvoke,env_type";
private static final String C_P_158_29="msg_effinvoke,invoked_move";
private static final String C_P_158_30="c";
private static final String C_P_158_31="moveFctEnv";
private static final String C_P_158_32="r";
private static final String C_P_158_33="java.lang.Object";
private static final String C_P_158_34="java.lang.Object";
private static final String C_P_158_35="{getTrEnv(([c]))}";
private static final String C_P_158_36="$clickMoveFctEnv({([c])})";
private static final String C_P_158_37="";
private static final String C_P_158_38="{getTrMoveFctEnv(([c]))}";
private static final String C_P_158_39="!globalMoves.isEmpty()";
private static final String C_P_158_40="msg_effinvoke,move_fct_env_exc";
private static final String C_P_158_41="globalMoves";
private static final String C_P_158_42="m";
private static final String C_P_158_43="$clickGlobalMoveFctEnv({([m])})";
private static final String C_P_158_44="";
private static final String C_P_158_45="{getTrGlobalMoveFctEnv(([m]))}";
private static final String C_P_158_46="!invokingMoveByUserTypes.isEmpty()";
private static final String C_P_158_47="msg_effinvoke,invoke_move_type";
private static final String C_P_158_48="msg_effinvoke,owned_type";
private static final String C_P_158_49="msg_effinvoke,invoked_move";
private static final String C_P_158_50="c";
private static final String C_P_158_51="invokingMoveByUserTypes";
private static final String C_P_158_52="r";
private static final String C_P_158_53="java.lang.Object";
private static final String C_P_158_54="java.lang.Object";
private static final String C_P_158_55="!isType(([c]))";
private static final String C_P_158_56="msg_effinvoke,other_owned_type";
private static final String C_P_158_57="isType(([c]))";
private static final String C_P_158_58="{getTrUserTypes(([c]))}";
private static final String C_P_158_59="$clickMoveUserTypes({index},{([c])})";
private static final String C_P_158_60="";
private static final String C_P_158_61="{getTrMoveUserTypes(([c]))}";
private static final String C_P_158_62="!movesNotToBeInvoked.isEmpty()";
private static final String C_P_158_63="msg_effinvoke,moves_not_invoked";
private static final String C_P_158_64="movesNotToBeInvoked";
private static final String C_P_158_65="s";
private static final String C_P_158_66="$clickMoveNotInvok({index},{([s])})";
private static final String C_P_158_67="";
private static final String C_P_158_68="{getTrMoveNotInvok(([s]))}";
private PageDataMovesEffectsEffinvoke(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc63){
Element elt0_=el(_doc63,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_158_0));
attrs0_.add(at(C_BEAN,C_P_158_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc63,HEAD);
Element elt2_=el(_doc63,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_158_2));
attrs1_.add(at(REL,C_P_158_3));
attrs1_.add(at(TYPE,C_P_158_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc63,BODY);
build0(elt3_,_doc63);
ad(elt0_,elt3_);
_doc63.appendChild(elt0_);
}
static void build0(Element _body,Document _doc63){
Element elt0_=el(_doc63,P);
Element elt1_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_158_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
//Element elt2_=el(_doc63,C_IMPORT);
//CustList<Attr> attrs1_=al(1);
//attrs1_.add(at(PAGE,C_P_158_6));
//at(elt2_,attrs1_);
//Element elt3_=el(_doc63,C_PACKAGE);
//CustList<Attr> attrs2_=al(1);
//attrs2_.add(at(NAME,C_P_158_7));
//at(elt3_,attrs2_);
//Element elt4_=el(_doc63,C_CLASS);
//CustList<Attr> attrs3_=al(1);
//attrs3_.add(at(NAME,C_P_158_8));
//at(elt4_,attrs3_);
//Element elt5_=el(_doc63,C_FIELD);
//CustList<Attr> attrs4_=al(1);
//attrs4_.add(at(PREPARE,C_P_158_9));
//at(elt5_,attrs4_);
//ad(elt4_,elt5_);
//Element elt6_=el(_doc63,C_FIELD);
//CustList<Attr> attrs5_=al(1);
//attrs5_.add(at(PREPARE,C_P_158_10));
//at(elt6_,attrs5_);
//ad(elt4_,elt6_);
//ad(elt3_,elt4_);
//ad(elt2_,elt3_);
//ad(elt0_,elt2_);
Element elt7_=el(_doc63,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_158_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_158_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc63,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_158_13));
at(elt9_,attrs8_);
Element elt10_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_158_14));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
Element elt11_=el(_doc63,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_158_15));
at(elt11_,attrs10_);
Element elt12_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_158_16));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
Element elt13_=el(_doc63,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_158_17));
at(elt13_,attrs12_);
Element elt14_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_158_18));
at(elt14_,attrs13_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
Element elt15_=el(_doc63,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_158_19));
at(elt15_,attrs14_);
Element elt16_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_158_20));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
ad(elt0_,elt15_);
Element elt17_=el(_doc63,C_IF);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(CONDITION,C_P_158_21));
at(elt17_,attrs16_);
Element elt18_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_158_22));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt0_,elt17_);
Element elt19_=el(_doc63,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_158_23));
at(elt19_,attrs18_);
Element elt20_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_158_24));
at(elt20_,attrs19_);
Element elt21_=el(_doc63,PARAM);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_158_25));
at(elt21_,attrs20_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt0_,elt19_);
Element elt22_=el(_doc63,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_158_26));
at(elt22_,attrs21_);
Element elt23_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_158_27));
at(elt23_,attrs22_);
ad(elt22_,elt23_);
Element elt24_=el(_doc63,TABLE);
Element elt25_=el(_doc63,THEAD);
Element elt26_=el(_doc63,TR);
Element elt27_=el(_doc63,TH);
Element elt28_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_158_28));
at(elt28_,attrs23_);
ad(elt27_,elt28_);
ad(elt26_,elt27_);
Element elt29_=el(_doc63,TH);
Element elt30_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_P_158_29));
at(elt30_,attrs24_);
ad(elt29_,elt30_);
ad(elt26_,elt29_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
Element elt31_=el(_doc63,TBODY);
Element elt32_=el(_doc63,C_FOR);
CustList<Attr> attrs25_=al(5);
attrs25_.add(at(KEY,C_P_158_30));
attrs25_.add(at(MAP,C_P_158_31));
attrs25_.add(at(VALUE,C_P_158_32));
attrs25_.add(at(KEYCLASSNAME,C_P_158_33));
attrs25_.add(at(VARCLASSNAME,C_P_158_34));
at(elt32_,attrs25_);
Element elt33_=el(_doc63,TR);
Element elt34_=el(_doc63,TD);
Text txt0_=tx(_doc63,C_P_158_35);
ad(elt34_,txt0_);
ad(elt33_,elt34_);
Element elt35_=el(_doc63,TD);
Element elt36_=el(_doc63,A);
CustList<Attr> attrs26_=al(2);
attrs26_.add(at(C_COMMAND,C_P_158_36));
attrs26_.add(at(HREF,C_P_158_37));
at(elt36_,attrs26_);
Text txt1_=tx(_doc63,C_P_158_38);
ad(elt36_,txt1_);
ad(elt35_,elt36_);
ad(elt33_,elt35_);
ad(elt32_,elt33_);
ad(elt31_,elt32_);
ad(elt24_,elt31_);
ad(elt22_,elt24_);
Element elt37_=el(_doc63,BR);
ad(elt22_,elt37_);
Element elt38_=el(_doc63,C_IF);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(CONDITION,C_P_158_39));
at(elt38_,attrs27_);
Element elt39_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(VALUE,C_P_158_40));
at(elt39_,attrs28_);
ad(elt38_,elt39_);
Element elt40_=el(_doc63,UL);
Element elt41_=el(_doc63,C_FOR);
CustList<Attr> attrs29_=al(2);
attrs29_.add(at(LIST,C_P_158_41));
attrs29_.add(at(VAR,C_P_158_42));
at(elt41_,attrs29_);
Element elt42_=el(_doc63,LI);
Element elt43_=el(_doc63,A);
CustList<Attr> attrs30_=al(2);
attrs30_.add(at(C_COMMAND,C_P_158_43));
attrs30_.add(at(HREF,C_P_158_44));
at(elt43_,attrs30_);
Text txt2_=tx(_doc63,C_P_158_45);
ad(elt43_,txt2_);
ad(elt42_,elt43_);
ad(elt41_,elt42_);
ad(elt40_,elt41_);
ad(elt38_,elt40_);
Element elt44_=el(_doc63,BR);
ad(elt38_,elt44_);
ad(elt22_,elt38_);
ad(elt0_,elt22_);
Element elt45_=el(_doc63,C_IF);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(CONDITION,C_P_158_46));
at(elt45_,attrs31_);
Element elt46_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs32_=al(1);
attrs32_.add(at(VALUE,C_P_158_47));
at(elt46_,attrs32_);
ad(elt45_,elt46_);
Element elt47_=el(_doc63,TABLE);
Element elt48_=el(_doc63,THEAD);
Element elt49_=el(_doc63,TR);
Element elt50_=el(_doc63,TH);
Element elt51_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(VALUE,C_P_158_48));
at(elt51_,attrs33_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
Element elt52_=el(_doc63,TH);
Element elt53_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs34_=al(1);
attrs34_.add(at(VALUE,C_P_158_49));
at(elt53_,attrs34_);
ad(elt52_,elt53_);
ad(elt49_,elt52_);
ad(elt48_,elt49_);
ad(elt47_,elt48_);
Element elt54_=el(_doc63,TBODY);
Element elt55_=el(_doc63,C_FOR);
CustList<Attr> attrs35_=al(5);
attrs35_.add(at(KEY,C_P_158_50));
attrs35_.add(at(MAP,C_P_158_51));
attrs35_.add(at(VALUE,C_P_158_52));
attrs35_.add(at(KEYCLASSNAME,C_P_158_53));
attrs35_.add(at(VARCLASSNAME,C_P_158_54));
at(elt55_,attrs35_);
Element elt56_=el(_doc63,TR);
Element elt57_=el(_doc63,C_IF);
CustList<Attr> attrs36_=al(1);
attrs36_.add(at(CONDITION,C_P_158_55));
at(elt57_,attrs36_);
Element elt58_=el(_doc63,TD);
Element elt59_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs37_=al(1);
attrs37_.add(at(VALUE,C_P_158_56));
at(elt59_,attrs37_);
ad(elt58_,elt59_);
ad(elt57_,elt58_);
ad(elt56_,elt57_);
Element elt60_=el(_doc63,C_IF);
CustList<Attr> attrs38_=al(1);
attrs38_.add(at(CONDITION,C_P_158_57));
at(elt60_,attrs38_);
Element elt61_=el(_doc63,TD);
Text txt3_=tx(_doc63,C_P_158_58);
ad(elt61_,txt3_);
ad(elt60_,elt61_);
ad(elt56_,elt60_);
Element elt62_=el(_doc63,TD);
Element elt63_=el(_doc63,A);
CustList<Attr> attrs39_=al(2);
attrs39_.add(at(C_COMMAND,C_P_158_59));
attrs39_.add(at(HREF,C_P_158_60));
at(elt63_,attrs39_);
Text txt4_=tx(_doc63,C_P_158_61);
ad(elt63_,txt4_);
ad(elt62_,elt63_);
ad(elt56_,elt62_);
ad(elt55_,elt56_);
ad(elt54_,elt55_);
ad(elt47_,elt54_);
ad(elt45_,elt47_);
Element elt64_=el(_doc63,BR);
ad(elt45_,elt64_);
ad(elt0_,elt45_);
Element elt65_=el(_doc63,C_IF);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(CONDITION,C_P_158_62));
at(elt65_,attrs40_);
Element elt66_=el(_doc63,C_MESSAGE);
CustList<Attr> attrs41_=al(1);
attrs41_.add(at(VALUE,C_P_158_63));
at(elt66_,attrs41_);
ad(elt65_,elt66_);
Element elt67_=el(_doc63,UL);
Element elt68_=el(_doc63,C_FOR);
CustList<Attr> attrs42_=al(2);
attrs42_.add(at(LIST,C_P_158_64));
attrs42_.add(at(VAR,C_P_158_65));
at(elt68_,attrs42_);
Element elt69_=el(_doc63,LI);
Element elt70_=el(_doc63,A);
CustList<Attr> attrs43_=al(2);
attrs43_.add(at(C_COMMAND,C_P_158_66));
attrs43_.add(at(HREF,C_P_158_67));
at(elt70_,attrs43_);
Text txt5_=tx(_doc63,C_P_158_68);
ad(elt70_,txt5_);
ad(elt69_,elt70_);
ad(elt68_,elt69_);
ad(elt67_,elt68_);
ad(elt65_,elt67_);
Element elt71_=el(_doc63,BR);
ad(elt65_,elt71_);
ad(elt0_,elt65_);
ad(_body,elt0_);
}
}
