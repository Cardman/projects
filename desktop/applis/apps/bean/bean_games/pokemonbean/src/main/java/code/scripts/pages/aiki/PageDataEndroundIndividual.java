package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataEndroundIndividual extends PageCardsCommon{
private static final String C_P_105_0="javahtml";
private static final String C_P_105_1="end_individual";
private static final String C_P_105_2=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_105_3="stylesheet";
private static final String C_P_105_4="text/css";
private static final String C_P_105_5="endRoundHtml";
private static final String C_P_105_6="aiki.beans.endround";
private static final String C_P_105_7="EffectEndRoundBean";
private static final String C_P_105_8="$intern.index=index";
private static final String C_P_105_9="msg_individual,effect";
private static final String C_P_105_10="!deleteAllStatus.isZero()";
private static final String C_P_105_11="msg_individual,delete_all_status";
private static final String C_P_105_12="deleteAllStatus";
private static final String C_P_105_13="!recoilDamage.isZero()";
private static final String C_P_105_14="msg_individual,recoil_damage";
private static final String C_P_105_15="recoilDamage";
private static final String C_P_105_16="!healHp.isZero()";
private static final String C_P_105_17="msg_individual,heal_hp";
private static final String C_P_105_18="healHp";
private static final String C_P_105_19="!isEmpty(userStatusEndRound)";
private static final String C_P_105_20="msg_individual,user_status";
private static final String C_P_105_21="{getTrUserStatus()}";
private static final String C_P_105_22="$clickUserStatus({index})";
private static final String C_P_105_23="!multDamageStatus.isEmpty()";
private static final String C_P_105_24="msg_individual,mult_damage_status";
private static final String C_P_105_25="msg_individual,mult_damage_status_key";
private static final String C_P_105_26="msg_individual,mult_damage_status_value";
private static final String C_P_105_27="s";
private static final String C_P_105_28="multDamageStatus";
private static final String C_P_105_29="r";
private static final String C_P_105_30="java.lang.Object";
private static final String C_P_105_31="r";
private static final String C_P_105_32="$clickDamageStatus({index},{([s])})";
private static final String C_P_105_33="";
private static final String C_P_105_34="{getTrDamageStatus(([s]))}";
private static final String C_P_105_35="{r}";
private static final String C_P_105_36="!healHpByOwnerTypes.isEmpty()";
private static final String C_P_105_37="msg_individual,heal_hp_by_owner_types";
private static final String C_P_105_38="msg_individual,heal_hp_by_owner_types_key";
private static final String C_P_105_39="msg_individual,heal_hp_by_owner_types_value";
private static final String C_P_105_40="s";
private static final String C_P_105_41="healHpByOwnerTypes";
private static final String C_P_105_42="r";
private static final String C_P_105_43="java.lang.Object";
private static final String C_P_105_44="r";
private static final String C_P_105_45="isType(([s]))";
private static final String C_P_105_46="{getTrType(([s]))}";
private static final String C_P_105_47="!isType(([s]))";
private static final String C_P_105_48="msg_individual,heal_hp_by_owner_types_other";
private static final String C_P_105_49="r.isZeroOrGt()";
private static final String C_P_105_50="msg_individual,heal_hp_by_owner_types_value_w";
private static final String C_P_105_51="r.absNb()";
private static final String C_P_105_52="!r.isZeroOrGt()";
private static final String C_P_105_53="msg_individual,heal_hp_by_owner_types_value_l";
private static final String C_P_105_54="r.absNb()";
private PageDataEndroundIndividual(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc10){
Element elt0_=el(_doc10,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_105_0));
attrs0_.add(at(C_BEAN,C_P_105_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc10,HEAD);
Element elt2_=el(_doc10,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_105_2));
attrs1_.add(at(REL,C_P_105_3));
attrs1_.add(at(TYPE,C_P_105_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc10,BODY);
build0(elt3_,_doc10);
build1(elt3_,_doc10);
ad(elt0_,elt3_);
_doc10.appendChild(elt0_);
}
static void build0(Element _body,Document _doc10){
Element elt0_=el(_doc10,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_105_5));
at(elt0_,attrs0_);
Element elt1_=el(_doc10,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_105_6));
at(elt1_,attrs1_);
Element elt2_=el(_doc10,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_105_7));
at(elt2_,attrs2_);
Element elt3_=el(_doc10,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_105_8));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc10){
Element elt0_=el(_doc10,P);
Element elt1_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_105_9));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc10,C_IF);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(CONDITION,C_P_105_10));
at(elt2_,attrs1_);
Element elt3_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_105_11));
at(elt3_,attrs2_);
Element elt4_=el(_doc10,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_105_12));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt5_=el(_doc10,C_IF);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(CONDITION,C_P_105_13));
at(elt5_,attrs4_);
Element elt6_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_105_14));
at(elt6_,attrs5_);
Element elt7_=el(_doc10,PARAM);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_105_15));
at(elt7_,attrs6_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
Element elt8_=el(_doc10,C_IF);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(CONDITION,C_P_105_16));
at(elt8_,attrs7_);
Element elt9_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_105_17));
at(elt9_,attrs8_);
Element elt10_=el(_doc10,PARAM);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_105_18));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
Element elt11_=el(_doc10,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_105_19));
at(elt11_,attrs10_);
Element elt12_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_105_20));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
Element elt12a_=el(_doc10,A);
CustList<Attr> attrs12a_=al(1);
attrs12a_.add(at(C_COMMAND,C_P_105_22));
at(elt12a_,attrs12a_);
Text txt3_=tx(_doc10,C_P_105_21);
ad(elt12a_,txt3_);
ad(elt11_,elt12a_);
br(elt11_,_doc10);
ad(elt0_,elt11_);
Element elt15_=el(_doc10,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_105_23));
at(elt15_,attrs14_);
Element elt16_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_105_24));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
Element elt17_=el(_doc10,TABLE);
Element elt18_=el(_doc10,THEAD);
Element elt19_=el(_doc10,TR);
Element elt20_=el(_doc10,TH);
Element elt21_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_105_25));
at(elt21_,attrs16_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
Element elt22_=el(_doc10,TH);
Element elt23_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_105_26));
at(elt23_,attrs17_);
ad(elt22_,elt23_);
ad(elt19_,elt22_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
Element elt24_=el(_doc10,TBODY);
Element elt25_=el(_doc10,C_FOR);
CustList<Attr> attrs18_=al(5);
attrs18_.add(at(KEY,C_P_105_27));
attrs18_.add(at(MAP,C_P_105_28));
attrs18_.add(at(VALUE,C_P_105_29));
attrs18_.add(at(KEYCLASSNAME,C_P_105_30));
attrs18_.add(at(VARCLASSNAME,C_P_105_31));
at(elt25_,attrs18_);
Element elt26_=el(_doc10,TR);
Element elt27_=el(_doc10,TD);
Element elt28_=el(_doc10,A);
CustList<Attr> attrs19_=al(2);
attrs19_.add(at(C_COMMAND,C_P_105_32));
attrs19_.add(at(HREF,C_P_105_33));
at(elt28_,attrs19_);
Text txt0_=tx(_doc10,C_P_105_34);
ad(elt28_,txt0_);
ad(elt27_,elt28_);
ad(elt26_,elt27_);
Element elt29_=el(_doc10,TD);
Text txt1_=tx(_doc10,C_P_105_35);
ad(elt29_,txt1_);
ad(elt26_,elt29_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt17_,elt24_);
ad(elt15_,elt17_);
Element elt30_=el(_doc10,BR);
ad(elt15_,elt30_);
ad(elt0_,elt15_);
Element elt31_=el(_doc10,C_IF);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(CONDITION,C_P_105_36));
at(elt31_,attrs20_);
Element elt32_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_P_105_37));
at(elt32_,attrs21_);
ad(elt31_,elt32_);
Element elt33_=el(_doc10,TABLE);
Element elt34_=el(_doc10,THEAD);
Element elt35_=el(_doc10,TR);
Element elt36_=el(_doc10,TH);
Element elt37_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_105_38));
at(elt37_,attrs22_);
ad(elt36_,elt37_);
ad(elt35_,elt36_);
Element elt38_=el(_doc10,TH);
Element elt39_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_105_39));
at(elt39_,attrs23_);
ad(elt38_,elt39_);
ad(elt35_,elt38_);
ad(elt34_,elt35_);
ad(elt33_,elt34_);
Element elt40_=el(_doc10,TBODY);
Element elt41_=el(_doc10,C_FOR);
CustList<Attr> attrs24_=al(5);
attrs24_.add(at(KEY,C_P_105_40));
attrs24_.add(at(MAP,C_P_105_41));
attrs24_.add(at(VALUE,C_P_105_42));
attrs24_.add(at(KEYCLASSNAME,C_P_105_43));
attrs24_.add(at(VARCLASSNAME,C_P_105_44));
at(elt41_,attrs24_);
Element elt42_=el(_doc10,TR);
Element elt43_=el(_doc10,C_IF);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(CONDITION,C_P_105_45));
at(elt43_,attrs25_);
Element elt44_=el(_doc10,TD);
Text txt2_=tx(_doc10,C_P_105_46);
ad(elt44_,txt2_);
ad(elt43_,elt44_);
ad(elt42_,elt43_);
Element elt45_=el(_doc10,C_IF);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(CONDITION,C_P_105_47));
at(elt45_,attrs26_);
Element elt46_=el(_doc10,TD);
Element elt47_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(VALUE,C_P_105_48));
at(elt47_,attrs27_);
ad(elt46_,elt47_);
ad(elt45_,elt46_);
ad(elt42_,elt45_);
Element elt48_=el(_doc10,C_IF);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(CONDITION,C_P_105_49));
at(elt48_,attrs28_);
Element elt49_=el(_doc10,TD);
Element elt50_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_P_105_50));
at(elt50_,attrs29_);
Element elt51_=el(_doc10,PARAM);
CustList<Attr> attrs30_=al(1);
attrs30_.add(at(VALUE,C_P_105_51));
at(elt51_,attrs30_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
ad(elt48_,elt49_);
ad(elt42_,elt48_);
Element elt52_=el(_doc10,C_IF);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(CONDITION,C_P_105_52));
at(elt52_,attrs31_);
Element elt53_=el(_doc10,TD);
Element elt54_=el(_doc10,C_MESSAGE);
CustList<Attr> attrs32_=al(1);
attrs32_.add(at(VALUE,C_P_105_53));
at(elt54_,attrs32_);
Element elt55_=el(_doc10,PARAM);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(VALUE,C_P_105_54));
at(elt55_,attrs33_);
ad(elt54_,elt55_);
ad(elt53_,elt54_);
ad(elt52_,elt53_);
ad(elt42_,elt52_);
ad(elt41_,elt42_);
ad(elt40_,elt41_);
ad(elt33_,elt40_);
ad(elt31_,elt33_);
Element elt56_=el(_doc10,BR);
ad(elt31_,elt56_);
ad(elt0_,elt31_);
ad(_body,elt0_);
}
}
