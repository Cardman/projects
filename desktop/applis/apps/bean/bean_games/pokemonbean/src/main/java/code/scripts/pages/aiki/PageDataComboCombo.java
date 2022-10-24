package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataComboCombo extends PageCardsCommon{
private static final String C_P_99_0="javahtml";
private static final String C_P_99_1="eff_combo";
private static final String C_P_99_2="web/css/abilities.css";
private static final String C_P_99_3="stylesheet";
private static final String C_P_99_4="text/css";
private static final String C_P_99_5="msg_combo,effect";
private static final String C_P_99_6="moves";
private static final String C_P_99_7="m";
private static final String C_P_99_8="java.lang.String";
private static final String C_P_99_9="$clickMove({index},{([m])})";
private static final String C_P_99_10="";
private static final String C_P_99_11="{m}";
private static final String C_P_99_12="endRound";
private static final String C_P_99_13="msg_effendround,rank";
private static final String C_P_99_14="endRoundRank";
private static final String C_P_99_15=GO_TO_ENDROUND;
private static final String C_P_99_16="";
private static final String C_P_99_17="msg_combo,endRound";
private static final String C_P_99_18="!reasonsEndRound.isEmpty()";
private static final String C_P_99_19="msg_effendround,reasons";
private static final String C_P_99_20="reasonsEndRound";
private static final String C_P_99_21="r";
private static final String C_P_99_22="java.lang.String";
private static final String C_P_99_23="{r}";
private static final String C_P_99_24="c";
private static final String C_P_99_25="mapVarsFailEndRound";
private static final String C_P_99_26="r";
private static final String C_P_99_27="java.lang.String";
private static final String C_P_99_28="java.lang.String";
private static final String C_P_99_29="{c} :";
private static final String C_P_99_30="";
private static final String C_P_99_31="msg_status,formula";
private static final String C_P_99_32="r";
private static final String C_P_99_33="!multEvtRateSecEff.isZero()";
private static final String C_P_99_34="msg_combo,rate_sec_eff";
private static final String C_P_99_35="multEvtRateSecEff";
private static final String C_P_99_36="!multStatisticFoe.isEmpty()";
private static final String C_P_99_37="msg_combo,mult_stat_foe";
private static final String C_P_99_38="msg_combo,statistic";
private static final String C_P_99_39="msg_combo,rate";
private static final String C_P_99_40="s";
private static final String C_P_99_41="multStatisticFoe";
private static final String C_P_99_42="b";
private static final String C_P_99_43="java.lang.Object";
private static final String C_P_99_44="r";
private static final String C_P_99_45="{getTrStatistic(([s]))}";
private static final String C_P_99_46="{b}";
private static final String C_P_99_47="msg_combo,rank_increment_nb_round";
private static final String C_P_99_48="rankIncrementNbRound";
private static final String C_P_99_49="msg_combo,law_repeat";
private static final String C_P_99_50="msg_combo,law_repeat_key";
private static final String C_P_99_51="msg_combo,law_repeat_value";
private static final String C_P_99_52="s";
private static final String C_P_99_53="repeatedRoundsLaw";
private static final String C_P_99_54="b";
private static final String C_P_99_55="li";
private static final String C_P_99_56="r";
private static final String C_P_99_57="{s}";
private static final String C_P_99_58="{b}";
private PageDataComboCombo(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc4){
Element elt0_=el(_doc4,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_99_0));
attrs0_.add(at(C_BEAN,C_P_99_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc4,HEAD);
Element elt2_=el(_doc4,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_99_2));
attrs1_.add(at(REL,C_P_99_3));
attrs1_.add(at(TYPE,C_P_99_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc4,BODY);
build0(elt3_,_doc4);
ad(elt0_,elt3_);
_doc4.appendChild(elt0_);
}
static void build0(Element _body,Document _doc4){
Element elt0_=el(_doc4,P);
Element elt1_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_99_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc4,UL);
Element elt3_=el(_doc4,C_FOR);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(LIST,C_P_99_6));
attrs1_.add(at(VAR,C_P_99_7));
attrs1_.add(at(CLASSNAME,C_P_99_8));
at(elt3_,attrs1_);
Element elt4_=el(_doc4,LI);
Element elt5_=el(_doc4,A);
CustList<Attr> attrs2_=al(2);
attrs2_.add(at(C_COMMAND,C_P_99_9));
attrs2_.add(at(HREF,C_P_99_10));
at(elt5_,attrs2_);
Text txt0_=tx(_doc4,C_P_99_11);
ad(elt5_,txt0_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt6_=el(_doc4,C_IF);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(CONDITION,C_P_99_12));
at(elt6_,attrs3_);
Element elt7_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_99_13));
at(elt7_,attrs4_);
Element elt8_=el(_doc4,PARAM);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_99_14));
at(elt8_,attrs5_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
Element elt9_=el(_doc4,A);
CustList<Attr> attrs6_=al(2);
attrs6_.add(at(C_COMMAND,C_P_99_15));
attrs6_.add(at(HREF,C_P_99_16));
at(elt9_,attrs6_);
Element elt10_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_99_17));
at(elt10_,attrs7_);
ad(elt9_,elt10_);
ad(elt6_,elt9_);
Element elt11_=el(_doc4,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_99_18));
at(elt11_,attrs8_);
Element elt12_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_99_19));
at(elt12_,attrs9_);
ad(elt11_,elt12_);
Element elt13_=el(_doc4,UL);
Element elt14_=el(_doc4,C_FOR);
CustList<Attr> attrs10_=al(3);
attrs10_.add(at(LIST,C_P_99_20));
attrs10_.add(at(VAR,C_P_99_21));
attrs10_.add(at(CLASSNAME,C_P_99_22));
at(elt14_,attrs10_);
Element elt15_=el(_doc4,LI);
Text txt1_=tx(_doc4,C_P_99_23);
ad(elt15_,txt1_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt11_,elt13_);
Element elt16_=el(_doc4,BR);
ad(elt11_,elt16_);
Element elt17_=el(_doc4,UL);
Element elt18_=el(_doc4,C_FOR);
CustList<Attr> attrs11_=al(5);
attrs11_.add(at(KEY,C_P_99_24));
attrs11_.add(at(MAP,C_P_99_25));
attrs11_.add(at(VALUE,C_P_99_26));
attrs11_.add(at(KEYCLASSNAME,C_P_99_27));
attrs11_.add(at(VARCLASSNAME,C_P_99_28));
at(elt18_,attrs11_);
Element elt19_=el(_doc4,LI);
Text txt2_=tx(_doc4,C_P_99_29);
ad(elt19_,txt2_);
Element elt20_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs12_=al(2);
attrs12_.add(at(QUOTED,C_P_99_30));
attrs12_.add(at(VALUE,C_P_99_31));
at(elt20_,attrs12_);
Element elt21_=el(_doc4,PARAM);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_99_32));
at(elt21_,attrs13_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
ad(elt11_,elt17_);
Element elt22_=el(_doc4,BR);
ad(elt11_,elt22_);
ad(elt6_,elt11_);
ad(elt0_,elt6_);
Element elt23_=el(_doc4,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_99_33));
at(elt23_,attrs14_);
Element elt24_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_99_34));
at(elt24_,attrs15_);
Element elt25_=el(_doc4,PARAM);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_99_35));
at(elt25_,attrs16_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt0_,elt23_);
Element elt26_=el(_doc4,C_IF);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(CONDITION,C_P_99_36));
at(elt26_,attrs17_);
Element elt27_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_99_37));
at(elt27_,attrs18_);
ad(elt26_,elt27_);
Element elt28_=el(_doc4,TABLE);
Element elt29_=el(_doc4,THEAD);
Element elt30_=el(_doc4,TR);
Element elt31_=el(_doc4,TH);
Element elt32_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_99_38));
at(elt32_,attrs19_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
Element elt33_=el(_doc4,TH);
Element elt34_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_99_39));
at(elt34_,attrs20_);
ad(elt33_,elt34_);
ad(elt30_,elt33_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
Element elt35_=el(_doc4,TBODY);
Element elt36_=el(_doc4,C_FOR);
CustList<Attr> attrs21_=al(5);
attrs21_.add(at(KEY,C_P_99_40));
attrs21_.add(at(MAP,C_P_99_41));
attrs21_.add(at(VALUE,C_P_99_42));
attrs21_.add(at(KEYCLASSNAME,C_P_99_43));
attrs21_.add(at(VARCLASSNAME,C_P_99_44));
at(elt36_,attrs21_);
Element elt37_=el(_doc4,TR);
Element elt38_=el(_doc4,TD);
Text txt3_=tx(_doc4,C_P_99_45);
ad(elt38_,txt3_);
ad(elt37_,elt38_);
Element elt39_=el(_doc4,TD);
Text txt4_=tx(_doc4,C_P_99_46);
ad(elt39_,txt4_);
ad(elt37_,elt39_);
ad(elt36_,elt37_);
ad(elt35_,elt36_);
ad(elt28_,elt35_);
ad(elt26_,elt28_);
Element elt40_=el(_doc4,BR);
ad(elt26_,elt40_);
ad(elt0_,elt26_);
Element elt41_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_99_47));
at(elt41_,attrs22_);
Element elt42_=el(_doc4,PARAM);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_99_48));
at(elt42_,attrs23_);
ad(elt41_,elt42_);
ad(elt0_,elt41_);
Element elt43_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_P_99_49));
at(elt43_,attrs24_);
ad(elt0_,elt43_);
Element elt44_=el(_doc4,TABLE);
Element elt45_=el(_doc4,THEAD);
Element elt46_=el(_doc4,TR);
Element elt47_=el(_doc4,TH);
Element elt48_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_99_50));
at(elt48_,attrs25_);
ad(elt47_,elt48_);
ad(elt46_,elt47_);
Element elt49_=el(_doc4,TH);
Element elt50_=el(_doc4,C_MESSAGE);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(VALUE,C_P_99_51));
at(elt50_,attrs26_);
ad(elt49_,elt50_);
ad(elt46_,elt49_);
ad(elt45_,elt46_);
ad(elt44_,elt45_);
Element elt51_=el(_doc4,TBODY);
Element elt52_=el(_doc4,C_FOR);
CustList<Attr> attrs27_=al(5);
attrs27_.add(at(KEY,C_P_99_52));
attrs27_.add(at(MAP,C_P_99_53));
attrs27_.add(at(VALUE,C_P_99_54));
attrs27_.add(at(KEYCLASSNAME,C_P_99_55));
attrs27_.add(at(VARCLASSNAME,C_P_99_56));
at(elt52_,attrs27_);
Element elt53_=el(_doc4,TR);
Element elt54_=el(_doc4,TD);
Text txt5_=tx(_doc4,C_P_99_57);
ad(elt54_,txt5_);
ad(elt53_,elt54_);
Element elt55_=el(_doc4,TD);
Text txt6_=tx(_doc4,C_P_99_58);
ad(elt55_,txt6_);
ad(elt53_,elt55_);
ad(elt52_,elt53_);
ad(elt51_,elt52_);
ad(elt44_,elt51_);
ad(elt0_,elt44_);
Element elt56_=el(_doc4,BR);
ad(elt0_,elt56_);
ad(_body,elt0_);
}
}
