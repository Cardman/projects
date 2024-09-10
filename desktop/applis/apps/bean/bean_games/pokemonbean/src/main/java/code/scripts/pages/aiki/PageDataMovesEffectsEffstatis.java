package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffstatis extends PageCardsCommon{
private static final String C_P_166_0="javahtml";
private static final String C_P_166_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_STATIS;
private static final String C_P_166_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_166_3="stylesheet";
private static final String C_P_166_4="text/css";
private static final String C_P_166_5="msg_effstatis,effect";
private static final String C_P_166_6="effectBean";
private static final String C_P_166_7="aiki.beans.moves.effects";
private static final String C_P_166_8="EffectBean";
private static final String C_P_166_9="$intern.index=index";
private static final String C_P_166_10="$intern.move=move";
private static final String C_P_166_11="!randomStatis()";
private static final String C_P_166_12="isAlwaysEnabled()";
private static final String C_P_166_13="msg_effstatis,always_enabled";
private static final String C_P_166_14="!isAlwaysEnabled()";
private static final String C_P_166_15="msg_effstatis,rate_enabled";
private static final String C_P_166_16="evtRate";
private static final String C_P_166_17="evtRatePerCent";
private static final String C_P_166_18="notEmptyVarBoost()";
private static final String C_P_166_19="randomStatis()";
private static final String C_P_166_20="msg_effstatis,var_statis_rank";
private static final String C_P_166_21="msg_effstatis,statistic";
private static final String C_P_166_22="msg_effstatis,boost";
private static final String C_P_166_23="msg_effstatis,fail";
private static final String C_P_166_24="msg_effstatis,rate_event";
private static final String C_P_166_25="c";
private static final String C_P_166_26="statisVarRank";
private static final String C_P_166_27="r";
private static final String C_P_166_28="java.lang.String";
private static final String C_P_166_29="java.lang.Byte";
private static final String C_P_166_30="{c}";
private static final String C_P_166_31="{r}";
private static final String C_P_166_32="{getFail(([c]))}";
private static final String C_P_166_33="{getRate(([c]))}";
private static final String C_P_166_34="!randomStatis()";
private static final String C_P_166_35="msg_effstatis,var_statis_rank";
private static final String C_P_166_36="msg_effstatis,statistic";
private static final String C_P_166_37="msg_effstatis,boost";
private static final String C_P_166_38="msg_effstatis,fail";
private static final String C_P_166_39="c";
private static final String C_P_166_40="statisVarRank";
private static final String C_P_166_41="r";
private static final String C_P_166_42="java.lang.String";
private static final String C_P_166_43="java.lang.Byte";
private static final String C_P_166_44="{c}";
private static final String C_P_166_45="{r}";
private static final String C_P_166_46="{getFail(([c]))}";
private static final String C_P_166_47="!mapVarsStatistics.isEmpty()";
private static final String C_P_166_48="k";
private static final String C_P_166_49="mapVarsStatistics";
private static final String C_P_166_50="v";
private static final String C_P_166_51="java.lang.String";
private static final String C_P_166_52="java.lang.String";
private static final String C_P_166_53="{k} :";
private static final String C_P_166_54="";
private static final String C_P_166_55="msg_effstatis,formula";
private static final String C_P_166_56="v";
private static final String C_P_166_57="!swapBoostStatis.isEmpty()";
private static final String C_P_166_58="msg_effstatis,swap_boost";
private static final String C_P_166_59="msg_effstatis,statistic";
private static final String C_P_166_60="msg_effstatis,fail";
private static final String C_P_166_61="swapBoostStatis";
private static final String C_P_166_62="c";
private static final String C_P_166_63="java.lang.String";
private static final String C_P_166_64="{c}";
private static final String C_P_166_65="{getSwapFail(([c]))}";
private static final String C_P_166_66="!mapVarsStatistics.isEmpty()";
private static final String C_P_166_67="k";
private static final String C_P_166_68="mapVarsStatistics";
private static final String C_P_166_69="v";
private static final String C_P_166_70="java.lang.String";
private static final String C_P_166_71="java.lang.String";
private static final String C_P_166_72="{k} :";
private static final String C_P_166_73="";
private static final String C_P_166_74="msg_effstatis,formula";
private static final String C_P_166_75="v";
private static final String C_P_166_76="!cancelLowStat.isEmpty()";
private static final String C_P_166_77="msg_effstatis,cancel_low_stat";
private static final String C_P_166_78="defaultBoost";
private static final String C_P_166_79="cancelLowStat";
private static final String C_P_166_80="s";
private static final String C_P_166_81="java.lang.String";
private static final String C_P_166_82="{s}";
private static final String C_P_166_83="!cancelChgtStat.isEmpty()";
private static final String C_P_166_84="msg_effstatis,cancel_chgt_stat";
private static final String C_P_166_85="defaultBoost";
private static final String C_P_166_86="cancelChgtStat";
private static final String C_P_166_87="s";
private static final String C_P_166_88="java.lang.String";
private static final String C_P_166_89="{s}";
private static final String C_P_166_90="!copyBoost.isEmpty()";
private static final String C_P_166_91="msg_effstatis,copy_boost";
private static final String C_P_166_92="defaultBoost";
private static final String C_P_166_93="copyBoost";
private static final String C_P_166_94="s";
private static final String C_P_166_95="java.lang.String";
private static final String C_P_166_96="{s}";
private PageDataMovesEffectsEffstatis(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc71){
Element elt0_=el(_doc71,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_166_0));
attrs0_.add(at(C_BEAN,C_P_166_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc71,HEAD);
Element elt2_=el(_doc71,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_166_2));
attrs1_.add(at(REL,C_P_166_3));
attrs1_.add(at(TYPE,C_P_166_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc71,BODY);
build0(elt3_,_doc71);
ad(elt0_,elt3_);
_doc71.appendChild(elt0_);
}
static void build0(Element _body,Document _doc71){
Element elt0_=el(_doc71,P);
Element elt1_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_166_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc71,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_166_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc71,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_166_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc71,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_166_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc71,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_166_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc71,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_166_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc71,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_166_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc71,C_IF);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(CONDITION,C_P_166_12));
at(elt8_,attrs7_);
Element elt9_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_166_13));
at(elt9_,attrs8_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
Element elt10_=el(_doc71,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_166_14));
at(elt10_,attrs9_);
Element elt11_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_166_15));
at(elt11_,attrs10_);
Element elt12_=el(_doc71,PARAM);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_166_16));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
Element elt13_=el(_doc71,PARAM);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_166_17));
at(elt13_,attrs12_);
ad(elt11_,elt13_);
ad(elt10_,elt11_);
ad(elt7_,elt10_);
ad(elt0_,elt7_);
Element elt14_=el(_doc71,C_IF);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(CONDITION,C_P_166_18));
at(elt14_,attrs13_);
Element elt15_=el(_doc71,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_166_19));
at(elt15_,attrs14_);
Element elt16_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_166_20));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
Element elt17_=el(_doc71,TABLE);
Element elt18_=el(_doc71,THEAD);
Element elt19_=el(_doc71,TR);
Element elt20_=el(_doc71,TH);
Element elt21_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_166_21));
at(elt21_,attrs16_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
Element elt22_=el(_doc71,TH);
Element elt23_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_166_22));
at(elt23_,attrs17_);
ad(elt22_,elt23_);
ad(elt19_,elt22_);
Element elt24_=el(_doc71,TH);
Element elt25_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_166_23));
at(elt25_,attrs18_);
ad(elt24_,elt25_);
ad(elt19_,elt24_);
Element elt26_=el(_doc71,TH);
Element elt27_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_166_24));
at(elt27_,attrs19_);
ad(elt26_,elt27_);
ad(elt19_,elt26_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
Element elt28_=el(_doc71,TBODY);
Element elt29_=el(_doc71,C_FOR);
CustList<Attr> attrs20_=al(5);
attrs20_.add(at(KEY,C_P_166_25));
attrs20_.add(at(MAP,C_P_166_26));
attrs20_.add(at(VALUE,C_P_166_27));
attrs20_.add(at(KEYCLASSNAME,C_P_166_28));
attrs20_.add(at(VARCLASSNAME,C_P_166_29));
at(elt29_,attrs20_);
Element elt30_=el(_doc71,TR);
Element elt31_=el(_doc71,TD);
Text txt0_=tx(_doc71,C_P_166_30);
ad(elt31_,txt0_);
ad(elt30_,elt31_);
Element elt32_=el(_doc71,TD);
Text txt1_=tx(_doc71,C_P_166_31);
ad(elt32_,txt1_);
ad(elt30_,elt32_);
Element elt33_=el(_doc71,TD);
Text txt2_=tx(_doc71,C_P_166_32);
ad(elt33_,txt2_);
ad(elt30_,elt33_);
Element elt34_=el(_doc71,TD);
Text txt3_=tx(_doc71,C_P_166_33);
ad(elt34_,txt3_);
ad(elt30_,elt34_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
ad(elt17_,elt28_);
ad(elt15_,elt17_);
ad(elt14_,elt15_);
Element elt35_=el(_doc71,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_166_34));
at(elt35_,attrs21_);
Element elt36_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_166_35));
at(elt36_,attrs22_);
ad(elt35_,elt36_);
Element elt37_=el(_doc71,TABLE);
Element elt38_=el(_doc71,THEAD);
Element elt39_=el(_doc71,TR);
Element elt40_=el(_doc71,TH);
Element elt41_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_166_36));
at(elt41_,attrs23_);
ad(elt40_,elt41_);
ad(elt39_,elt40_);
Element elt42_=el(_doc71,TH);
Element elt43_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_P_166_37));
at(elt43_,attrs24_);
ad(elt42_,elt43_);
ad(elt39_,elt42_);
Element elt44_=el(_doc71,TH);
Element elt45_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_166_38));
at(elt45_,attrs25_);
ad(elt44_,elt45_);
ad(elt39_,elt44_);
ad(elt38_,elt39_);
ad(elt37_,elt38_);
Element elt46_=el(_doc71,TBODY);
Element elt47_=el(_doc71,C_FOR);
CustList<Attr> attrs26_=al(5);
attrs26_.add(at(KEY,C_P_166_39));
attrs26_.add(at(MAP,C_P_166_40));
attrs26_.add(at(VALUE,C_P_166_41));
attrs26_.add(at(KEYCLASSNAME,C_P_166_42));
attrs26_.add(at(VARCLASSNAME,C_P_166_43));
at(elt47_,attrs26_);
Element elt48_=el(_doc71,TR);
Element elt49_=el(_doc71,TD);
Text txt4_=tx(_doc71,C_P_166_44);
ad(elt49_,txt4_);
ad(elt48_,elt49_);
Element elt50_=el(_doc71,TD);
Text txt5_=tx(_doc71,C_P_166_45);
ad(elt50_,txt5_);
ad(elt48_,elt50_);
Element elt51_=el(_doc71,TD);
Text txt6_=tx(_doc71,C_P_166_46);
ad(elt51_,txt6_);
ad(elt48_,elt51_);
ad(elt47_,elt48_);
ad(elt46_,elt47_);
ad(elt37_,elt46_);
ad(elt35_,elt37_);
ad(elt14_,elt35_);
Element elt52_=el(_doc71,BR);
ad(elt14_,elt52_);
Element elt53_=el(_doc71,C_IF);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(CONDITION,C_P_166_47));
at(elt53_,attrs27_);
Element elt54_=el(_doc71,UL);
Element elt55_=el(_doc71,C_FOR);
CustList<Attr> attrs28_=al(5);
attrs28_.add(at(KEY,C_P_166_48));
attrs28_.add(at(MAP,C_P_166_49));
attrs28_.add(at(VALUE,C_P_166_50));
attrs28_.add(at(KEYCLASSNAME,C_P_166_51));
attrs28_.add(at(VARCLASSNAME,C_P_166_52));
at(elt55_,attrs28_);
Element elt56_=el(_doc71,LI);
Text txt7_=tx(_doc71,C_P_166_53);
ad(elt56_,txt7_);
Element elt57_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs29_=al(2);
attrs29_.add(at(QUOTED,C_P_166_54));
attrs29_.add(at(VALUE,C_P_166_55));
at(elt57_,attrs29_);
Element elt58_=el(_doc71,PARAM);
CustList<Attr> attrs30_=al(1);
attrs30_.add(at(VALUE,C_P_166_56));
at(elt58_,attrs30_);
ad(elt57_,elt58_);
ad(elt56_,elt57_);
ad(elt55_,elt56_);
ad(elt54_,elt55_);
ad(elt53_,elt54_);
ad(elt14_,elt53_);
ad(elt0_,elt14_);
Element elt59_=el(_doc71,C_IF);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(CONDITION,C_P_166_57));
at(elt59_,attrs31_);
Element elt60_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs32_=al(1);
attrs32_.add(at(VALUE,C_P_166_58));
at(elt60_,attrs32_);
ad(elt59_,elt60_);
Element elt61_=el(_doc71,TABLE);
Element elt62_=el(_doc71,THEAD);
Element elt63_=el(_doc71,TR);
Element elt64_=el(_doc71,TH);
Element elt65_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(VALUE,C_P_166_59));
at(elt65_,attrs33_);
ad(elt64_,elt65_);
ad(elt63_,elt64_);
Element elt66_=el(_doc71,TH);
Element elt67_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs34_=al(1);
attrs34_.add(at(VALUE,C_P_166_60));
at(elt67_,attrs34_);
ad(elt66_,elt67_);
ad(elt63_,elt66_);
ad(elt62_,elt63_);
ad(elt61_,elt62_);
Element elt68_=el(_doc71,TBODY);
Element elt69_=el(_doc71,C_FOR);
CustList<Attr> attrs35_=al(3);
attrs35_.add(at(LIST,C_P_166_61));
attrs35_.add(at(VAR,C_P_166_62));
attrs35_.add(at(CLASSNAME,C_P_166_63));
at(elt69_,attrs35_);
Element elt70_=el(_doc71,TR);
Element elt71_=el(_doc71,TD);
Text txt8_=tx(_doc71,C_P_166_64);
ad(elt71_,txt8_);
ad(elt70_,elt71_);
Element elt72_=el(_doc71,TD);
Text txt9_=tx(_doc71,C_P_166_65);
ad(elt72_,txt9_);
ad(elt70_,elt72_);
ad(elt69_,elt70_);
ad(elt68_,elt69_);
ad(elt61_,elt68_);
ad(elt59_,elt61_);
Element elt73_=el(_doc71,BR);
ad(elt59_,elt73_);
Element elt74_=el(_doc71,C_IF);
CustList<Attr> attrs36_=al(1);
attrs36_.add(at(CONDITION,C_P_166_66));
at(elt74_,attrs36_);
Element elt75_=el(_doc71,UL);
Element elt76_=el(_doc71,C_FOR);
CustList<Attr> attrs37_=al(5);
attrs37_.add(at(KEY,C_P_166_67));
attrs37_.add(at(MAP,C_P_166_68));
attrs37_.add(at(VALUE,C_P_166_69));
attrs37_.add(at(KEYCLASSNAME,C_P_166_70));
attrs37_.add(at(VARCLASSNAME,C_P_166_71));
at(elt76_,attrs37_);
Element elt77_=el(_doc71,LI);
Text txt10_=tx(_doc71,C_P_166_72);
ad(elt77_,txt10_);
Element elt78_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs38_=al(2);
attrs38_.add(at(QUOTED,C_P_166_73));
attrs38_.add(at(VALUE,C_P_166_74));
at(elt78_,attrs38_);
Element elt79_=el(_doc71,PARAM);
CustList<Attr> attrs39_=al(1);
attrs39_.add(at(VALUE,C_P_166_75));
at(elt79_,attrs39_);
ad(elt78_,elt79_);
ad(elt77_,elt78_);
ad(elt76_,elt77_);
ad(elt75_,elt76_);
ad(elt74_,elt75_);
ad(elt59_,elt74_);
ad(elt0_,elt59_);
Element elt80_=el(_doc71,C_IF);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(CONDITION,C_P_166_76));
at(elt80_,attrs40_);
Element elt81_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs41_=al(1);
attrs41_.add(at(VALUE,C_P_166_77));
at(elt81_,attrs41_);
Element elt82_=el(_doc71,PARAM);
CustList<Attr> attrs42_=al(1);
attrs42_.add(at(VALUE,C_P_166_78));
at(elt82_,attrs42_);
ad(elt81_,elt82_);
ad(elt80_,elt81_);
Element elt83_=el(_doc71,UL);
Element elt84_=el(_doc71,C_FOR);
CustList<Attr> attrs43_=al(3);
attrs43_.add(at(LIST,C_P_166_79));
attrs43_.add(at(VAR,C_P_166_80));
attrs43_.add(at(CLASSNAME,C_P_166_81));
at(elt84_,attrs43_);
Element elt85_=el(_doc71,LI);
Text txt11_=tx(_doc71,C_P_166_82);
ad(elt85_,txt11_);
ad(elt84_,elt85_);
ad(elt83_,elt84_);
ad(elt80_,elt83_);
Element elt86_=el(_doc71,BR);
ad(elt80_,elt86_);
ad(elt0_,elt80_);
Element elt87_=el(_doc71,C_IF);
CustList<Attr> attrs44_=al(1);
attrs44_.add(at(CONDITION,C_P_166_83));
at(elt87_,attrs44_);
Element elt88_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs45_=al(1);
attrs45_.add(at(VALUE,C_P_166_84));
at(elt88_,attrs45_);
Element elt89_=el(_doc71,PARAM);
CustList<Attr> attrs46_=al(1);
attrs46_.add(at(VALUE,C_P_166_85));
at(elt89_,attrs46_);
ad(elt88_,elt89_);
ad(elt87_,elt88_);
Element elt90_=el(_doc71,UL);
Element elt91_=el(_doc71,C_FOR);
CustList<Attr> attrs47_=al(3);
attrs47_.add(at(LIST,C_P_166_86));
attrs47_.add(at(VAR,C_P_166_87));
attrs47_.add(at(CLASSNAME,C_P_166_88));
at(elt91_,attrs47_);
Element elt92_=el(_doc71,LI);
Text txt12_=tx(_doc71,C_P_166_89);
ad(elt92_,txt12_);
ad(elt91_,elt92_);
ad(elt90_,elt91_);
ad(elt87_,elt90_);
Element elt93_=el(_doc71,BR);
ad(elt87_,elt93_);
ad(elt0_,elt87_);
Element elt94_=el(_doc71,C_IF);
CustList<Attr> attrs48_=al(1);
attrs48_.add(at(CONDITION,C_P_166_90));
at(elt94_,attrs48_);
Element elt95_=el(_doc71,C_MESSAGE);
CustList<Attr> attrs49_=al(1);
attrs49_.add(at(VALUE,C_P_166_91));
at(elt95_,attrs49_);
Element elt96_=el(_doc71,PARAM);
CustList<Attr> attrs50_=al(1);
attrs50_.add(at(VALUE,C_P_166_92));
at(elt96_,attrs50_);
ad(elt95_,elt96_);
ad(elt94_,elt95_);
Element elt97_=el(_doc71,UL);
Element elt98_=el(_doc71,C_FOR);
CustList<Attr> attrs51_=al(3);
attrs51_.add(at(LIST,C_P_166_93));
attrs51_.add(at(VAR,C_P_166_94));
attrs51_.add(at(CLASSNAME,C_P_166_95));
at(elt98_,attrs51_);
Element elt99_=el(_doc71,LI);
Text txt13_=tx(_doc71,C_P_166_96);
ad(elt99_,txt13_);
ad(elt98_,elt99_);
ad(elt97_,elt98_);
ad(elt94_,elt97_);
Element elt100_=el(_doc71,BR);
ad(elt94_,elt100_);
ad(elt0_,elt94_);
ad(_body,elt0_);
}
}
