package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataItemsBerry extends PageCardsCommon{
private static final String C_P_116_0="javahtml";
private static final String C_P_116_1="berry";
private static final String C_P_116_2="msg_item,title";
private static final String C_P_116_3="displayName";
private static final String C_P_116_4=PkScriptPages.REN_ADD_WEB_CSS_ITEMS_CSS;
private static final String C_P_116_5="stylesheet";
private static final String C_P_116_6="text/css";
private static final String C_P_116_7="itemBean";
private static final String C_P_116_8="aiki.beans.items";
private static final String C_P_116_9="ItemBean";
private static final String C_P_116_10="$intern.name=name";
private static final String C_P_116_11="!healHpBySuperEffMove.isZero()";
private static final String C_P_116_12="msg_berry,win_super_eff_time";
private static final String C_P_116_13="msg_berry,win_super_eff";
private static final String C_P_116_14="healHpBySuperEffMove";
private static final String C_P_116_15="lawForAttackFirst";
private static final String C_P_116_16="msg_berry,sorting_users_time";
private static final String C_P_116_17="msg_berry,sorting_users";
private static final String C_P_116_18="withoutFail";
private static final String C_P_116_19="msg_berry,without_fail_time";
private static final String C_P_116_20="msg_berry,without_fail";
private static final String C_P_116_21="isHealingPp()";
private static final String C_P_116_22="msg_berry,heal_pp_time";
private static final String C_P_116_23="msg_berry,heal_pp";
private static final String C_P_116_24="healPp";
private static final String C_P_116_25="!healHp.isZero()";
private static final String C_P_116_26="msg_berry,heal_hp_time";
private static final String C_P_116_27="maxHpHealingHp.isZero()";
private static final String C_P_116_28="msg_berry,heal_hp_only_round_heal";
private static final String C_P_116_29="healHp";
private static final String C_P_116_30="!maxHpHealingHp.isZero()";
private static final String C_P_116_31="msg_berry,heal_hp";
private static final String C_P_116_32="maxHpHealingHp";
private static final String C_P_116_33="healHp";
private static final String C_P_116_34="!healHpRate.isZero()";
private static final String C_P_116_35="msg_berry,heal_hp_rate_time";
private static final String C_P_116_36="maxHpHealingHpRate.isZero()";
private static final String C_P_116_37="msg_berry,heal_hp_rate_only_round_heal";
private static final String C_P_116_38="healHpRate";
private static final String C_P_116_39="!maxHpHealingHpRate.isZero()";
private static final String C_P_116_40="msg_berry,heal_hp_rate";
private static final String C_P_116_41="maxHpHealingHpRate";
private static final String C_P_116_42="healHpRate";
private static final String C_P_116_43="!multFoesDamage.isEmpty()";
private static final String C_P_116_44="msg_berry,mult_damage_foe_time";
private static final String C_P_116_45="msg_berry,mult_damage_foe";
private static final String C_P_116_46="msg_berry,mult_damage_foe_type";
private static final String C_P_116_47="msg_berry,mult_damage_foe_eff";
private static final String C_P_116_48="msg_berry,mult_damage_foe_rate";
private static final String C_P_116_49="s";
private static final String C_P_116_50="multFoesDamage";
private static final String C_P_116_51="b";
private static final String C_P_116_52="java.lang.Object";
private static final String C_P_116_53="aiki.fight.util.EfficiencyRate";
private static final String C_P_116_54="{getTrMultFoesDamage(([s]))}";
private static final String C_P_116_55="{b.getEff()}";
private static final String C_P_116_56="{b.getHpRate()}";
private static final String C_P_116_57="!multStat.isEmpty()";
private static final String C_P_116_58="msg_berry,mult_stat_time";
private static final String C_P_116_59="msg_berry,mult_stat";
private static final String C_P_116_60="msg_berry,mult_stat_key";
private static final String C_P_116_61="msg_berry,mult_stat_hp";
private static final String C_P_116_62="msg_berry,mult_stat_boost";
private static final String C_P_116_63="s";
private static final String C_P_116_64="multStat";
private static final String C_P_116_65="b";
private static final String C_P_116_66="java.lang.Object";
private static final String C_P_116_67="aiki.fight.util.BoostHpRate";
private static final String C_P_116_68="{getTrMultStat(([s]))}";
private static final String C_P_116_69="{b.getHpRate()}";
private static final String C_P_116_70="{b.getBoost()}";
private static final String C_P_116_71="!healStatus.isEmpty()";
private static final String C_P_116_72="msg_berry,heal_status_time";
private static final String C_P_116_73="msg_berry,heal_status";
private static final String C_P_116_74="healStatus";
private static final String C_P_116_75="s";
private static final String C_P_116_76="$clickStatus({([s])})";
private static final String C_P_116_77="";
private static final String C_P_116_78="{getTrStatus(([s]))}";
private static final String C_P_116_79="!damageRateRecoilFoe.isEmpty()";
private static final String C_P_116_80="msg_berry,recoil_time";
private static final String C_P_116_81="msg_berry,recoil";
private static final String C_P_116_82="msg_berry,recoil_cat";
private static final String C_P_116_83="msg_berry,recoil_hp";
private static final String C_P_116_84="s";
private static final String C_P_116_85="damageRateRecoilFoe";
private static final String C_P_116_86="b";
private static final String C_P_116_87="java.lang.Object";
private static final String C_P_116_88="r";
private static final String C_P_116_89="{getTrCategRecoil(([s]))}";
private static final String C_P_116_90="{b}";
private static final String C_P_116_91="!boostStatis.isEmpty()";
private static final String C_P_116_92="msg_berry,category_time";
private static final String C_P_116_93="msg_berry,category";
private static final String C_P_116_94="categoryBoosting";
private static final String C_P_116_95="msg_berry,category_stat";
private static final String C_P_116_96="msg_berry,category_boost";
private static final String C_P_116_97="s";
private static final String C_P_116_98="boostStatis";
private static final String C_P_116_99="b";
private static final String C_P_116_100="java.lang.Object";
private static final String C_P_116_101="java.lang.Byte";
private static final String C_P_116_102="{getTrBoostStat(([s]))}";
private static final String C_P_116_103="{b}";
private PageDataItemsBerry(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc21){
Element elt0_=el(_doc21,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_116_0));
attrs0_.add(at(C_BEAN,C_P_116_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc21,HEAD);
Element elt2_=el(_doc21,TITLE);
Element elt3_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_116_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc21,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_116_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc21,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_116_4));
attrs3_.add(at(REL,C_P_116_5));
attrs3_.add(at(TYPE,C_P_116_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc21,BODY);
build0(elt6_,_doc21);
ad(elt0_,elt6_);
_doc21.appendChild(elt0_);
}
static void build0(Element _body,Document _doc21){
Element elt0_=el(_doc21,P);
Element elt1_=el(_doc21,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_116_7));
at(elt1_,attrs0_);
Element elt2_=el(_doc21,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_116_8));
at(elt2_,attrs1_);
Element elt3_=el(_doc21,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_116_9));
at(elt3_,attrs2_);
Element elt4_=el(_doc21,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_116_10));
at(elt4_,attrs3_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt5_=el(_doc21,C_IF);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(CONDITION,C_P_116_11));
at(elt5_,attrs4_);
Element elt6_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_116_12));
at(elt6_,attrs5_);
ad(elt5_,elt6_);
Element elt7_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_116_13));
at(elt7_,attrs6_);
Element elt8_=el(_doc21,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_116_14));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
ad(elt5_,elt7_);
ad(elt0_,elt5_);
Element elt9_=el(_doc21,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_116_15));
at(elt9_,attrs8_);
Element elt10_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_116_16));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
Element elt11_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_116_17));
at(elt11_,attrs10_);
ad(elt9_,elt11_);
ad(elt0_,elt9_);
Element elt12_=el(_doc21,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_116_18));
at(elt12_,attrs11_);
Element elt13_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_116_19));
at(elt13_,attrs12_);
ad(elt12_,elt13_);
Element elt14_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_116_20));
at(elt14_,attrs13_);
ad(elt12_,elt14_);
ad(elt0_,elt12_);
Element elt15_=el(_doc21,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_116_21));
at(elt15_,attrs14_);
Element elt16_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_116_22));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
Element elt17_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_116_23));
at(elt17_,attrs16_);
Element elt18_=el(_doc21,PARAM);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_116_24));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt15_,elt17_);
ad(elt0_,elt15_);
Element elt19_=el(_doc21,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_116_25));
at(elt19_,attrs18_);
Element elt20_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_116_26));
at(elt20_,attrs19_);
ad(elt19_,elt20_);
Element elt21_=el(_doc21,C_IF);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(CONDITION,C_P_116_27));
at(elt21_,attrs20_);
Element elt22_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_P_116_28));
at(elt22_,attrs21_);
Element elt23_=el(_doc21,PARAM);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_116_29));
at(elt23_,attrs22_);
ad(elt22_,elt23_);
ad(elt21_,elt22_);
ad(elt19_,elt21_);
Element elt24_=el(_doc21,C_IF);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(CONDITION,C_P_116_30));
at(elt24_,attrs23_);
Element elt25_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_P_116_31));
at(elt25_,attrs24_);
Element elt26_=el(_doc21,PARAM);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_116_32));
at(elt26_,attrs25_);
ad(elt25_,elt26_);
Element elt27_=el(_doc21,PARAM);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(VALUE,C_P_116_33));
at(elt27_,attrs26_);
ad(elt25_,elt27_);
ad(elt24_,elt25_);
ad(elt19_,elt24_);
ad(elt0_,elt19_);
Element elt28_=el(_doc21,C_IF);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(CONDITION,C_P_116_34));
at(elt28_,attrs27_);
Element elt29_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(VALUE,C_P_116_35));
at(elt29_,attrs28_);
ad(elt28_,elt29_);
Element elt30_=el(_doc21,C_IF);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(CONDITION,C_P_116_36));
at(elt30_,attrs29_);
Element elt31_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs30_=al(1);
attrs30_.add(at(VALUE,C_P_116_37));
at(elt31_,attrs30_);
Element elt32_=el(_doc21,PARAM);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(VALUE,C_P_116_38));
at(elt32_,attrs31_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
ad(elt28_,elt30_);
Element elt33_=el(_doc21,C_IF);
CustList<Attr> attrs32_=al(1);
attrs32_.add(at(CONDITION,C_P_116_39));
at(elt33_,attrs32_);
Element elt34_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(VALUE,C_P_116_40));
at(elt34_,attrs33_);
Element elt35_=el(_doc21,PARAM);
CustList<Attr> attrs34_=al(1);
attrs34_.add(at(VALUE,C_P_116_41));
at(elt35_,attrs34_);
ad(elt34_,elt35_);
Element elt36_=el(_doc21,PARAM);
CustList<Attr> attrs35_=al(1);
attrs35_.add(at(VALUE,C_P_116_42));
at(elt36_,attrs35_);
ad(elt34_,elt36_);
ad(elt33_,elt34_);
ad(elt28_,elt33_);
ad(elt0_,elt28_);
Element elt37_=el(_doc21,C_IF);
CustList<Attr> attrs36_=al(1);
attrs36_.add(at(CONDITION,C_P_116_43));
at(elt37_,attrs36_);
Element elt38_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs37_=al(1);
attrs37_.add(at(VALUE,C_P_116_44));
at(elt38_,attrs37_);
ad(elt37_,elt38_);
Element elt39_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs38_=al(1);
attrs38_.add(at(VALUE,C_P_116_45));
at(elt39_,attrs38_);
ad(elt37_,elt39_);
Element elt40_=el(_doc21,TABLE);
Element elt41_=el(_doc21,THEAD);
Element elt42_=el(_doc21,TR);
Element elt43_=el(_doc21,TH);
Element elt44_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs39_=al(1);
attrs39_.add(at(VALUE,C_P_116_46));
at(elt44_,attrs39_);
ad(elt43_,elt44_);
ad(elt42_,elt43_);
Element elt45_=el(_doc21,TH);
Element elt46_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(VALUE,C_P_116_47));
at(elt46_,attrs40_);
ad(elt45_,elt46_);
ad(elt42_,elt45_);
Element elt47_=el(_doc21,TH);
Element elt48_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs41_=al(1);
attrs41_.add(at(VALUE,C_P_116_48));
at(elt48_,attrs41_);
ad(elt47_,elt48_);
ad(elt42_,elt47_);
ad(elt41_,elt42_);
ad(elt40_,elt41_);
Element elt49_=el(_doc21,TBODY);
Element elt50_=el(_doc21,C_FOR);
CustList<Attr> attrs42_=al(5);
attrs42_.add(at(KEY,C_P_116_49));
attrs42_.add(at(MAP,C_P_116_50));
attrs42_.add(at(VALUE,C_P_116_51));
attrs42_.add(at(KEYCLASSNAME,C_P_116_52));
attrs42_.add(at(VARCLASSNAME,C_P_116_53));
at(elt50_,attrs42_);
Element elt51_=el(_doc21,TR);
Element elt52_=el(_doc21,TD);
Text txt0_=tx(_doc21,C_P_116_54);
ad(elt52_,txt0_);
ad(elt51_,elt52_);
Element elt53_=el(_doc21,TD);
Text txt1_=tx(_doc21,C_P_116_55);
ad(elt53_,txt1_);
ad(elt51_,elt53_);
Element elt54_=el(_doc21,TD);
Text txt2_=tx(_doc21,C_P_116_56);
ad(elt54_,txt2_);
ad(elt51_,elt54_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
ad(elt40_,elt49_);
ad(elt37_,elt40_);
Element elt55_=el(_doc21,BR);
ad(elt37_,elt55_);
ad(elt0_,elt37_);
Element elt56_=el(_doc21,C_IF);
CustList<Attr> attrs43_=al(1);
attrs43_.add(at(CONDITION,C_P_116_57));
at(elt56_,attrs43_);
Element elt57_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs44_=al(1);
attrs44_.add(at(VALUE,C_P_116_58));
at(elt57_,attrs44_);
ad(elt56_,elt57_);
Element elt58_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs45_=al(1);
attrs45_.add(at(VALUE,C_P_116_59));
at(elt58_,attrs45_);
ad(elt56_,elt58_);
Element elt59_=el(_doc21,TABLE);
Element elt60_=el(_doc21,THEAD);
Element elt61_=el(_doc21,TR);
Element elt62_=el(_doc21,TH);
Element elt63_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs46_=al(1);
attrs46_.add(at(VALUE,C_P_116_60));
at(elt63_,attrs46_);
ad(elt62_,elt63_);
ad(elt61_,elt62_);
Element elt64_=el(_doc21,TH);
Element elt65_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs47_=al(1);
attrs47_.add(at(VALUE,C_P_116_61));
at(elt65_,attrs47_);
ad(elt64_,elt65_);
ad(elt61_,elt64_);
Element elt66_=el(_doc21,TH);
Element elt67_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs48_=al(1);
attrs48_.add(at(VALUE,C_P_116_62));
at(elt67_,attrs48_);
ad(elt66_,elt67_);
ad(elt61_,elt66_);
ad(elt60_,elt61_);
ad(elt59_,elt60_);
Element elt68_=el(_doc21,TBODY);
Element elt69_=el(_doc21,C_FOR);
CustList<Attr> attrs49_=al(5);
attrs49_.add(at(KEY,C_P_116_63));
attrs49_.add(at(MAP,C_P_116_64));
attrs49_.add(at(VALUE,C_P_116_65));
attrs49_.add(at(KEYCLASSNAME,C_P_116_66));
attrs49_.add(at(VARCLASSNAME,C_P_116_67));
at(elt69_,attrs49_);
Element elt70_=el(_doc21,TR);
Element elt71_=el(_doc21,TD);
Text txt3_=tx(_doc21,C_P_116_68);
ad(elt71_,txt3_);
ad(elt70_,elt71_);
Element elt72_=el(_doc21,TD);
Text txt4_=tx(_doc21,C_P_116_69);
ad(elt72_,txt4_);
ad(elt70_,elt72_);
Element elt73_=el(_doc21,TD);
Text txt5_=tx(_doc21,C_P_116_70);
ad(elt73_,txt5_);
ad(elt70_,elt73_);
ad(elt69_,elt70_);
ad(elt68_,elt69_);
ad(elt59_,elt68_);
ad(elt56_,elt59_);
Element elt74_=el(_doc21,BR);
ad(elt56_,elt74_);
ad(elt0_,elt56_);
Element elt75_=el(_doc21,C_IF);
CustList<Attr> attrs50_=al(1);
attrs50_.add(at(CONDITION,C_P_116_71));
at(elt75_,attrs50_);
Element elt76_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs51_=al(1);
attrs51_.add(at(VALUE,C_P_116_72));
at(elt76_,attrs51_);
ad(elt75_,elt76_);
Element elt77_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs52_=al(1);
attrs52_.add(at(VALUE,C_P_116_73));
at(elt77_,attrs52_);
ad(elt75_,elt77_);
Element elt78_=el(_doc21,UL);
Element elt79_=el(_doc21,C_FOR);
CustList<Attr> attrs53_=al(2);
attrs53_.add(at(LIST,C_P_116_74));
attrs53_.add(at(VAR,C_P_116_75));
at(elt79_,attrs53_);
Element elt80_=el(_doc21,LI);
Element elt81_=el(_doc21,A);
CustList<Attr> attrs54_=al(2);
attrs54_.add(at(C_COMMAND,C_P_116_76));
attrs54_.add(at(HREF,C_P_116_77));
at(elt81_,attrs54_);
Text txt6_=tx(_doc21,C_P_116_78);
ad(elt81_,txt6_);
ad(elt80_,elt81_);
ad(elt79_,elt80_);
ad(elt78_,elt79_);
ad(elt75_,elt78_);
Element elt82_=el(_doc21,BR);
ad(elt75_,elt82_);
ad(elt0_,elt75_);
Element elt83_=el(_doc21,C_IF);
CustList<Attr> attrs55_=al(1);
attrs55_.add(at(CONDITION,C_P_116_79));
at(elt83_,attrs55_);
Element elt84_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs56_=al(1);
attrs56_.add(at(VALUE,C_P_116_80));
at(elt84_,attrs56_);
ad(elt83_,elt84_);
Element elt85_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs57_=al(1);
attrs57_.add(at(VALUE,C_P_116_81));
at(elt85_,attrs57_);
ad(elt83_,elt85_);
Element elt86_=el(_doc21,TABLE);
Element elt87_=el(_doc21,THEAD);
Element elt88_=el(_doc21,TR);
Element elt89_=el(_doc21,TH);
Element elt90_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs58_=al(1);
attrs58_.add(at(VALUE,C_P_116_82));
at(elt90_,attrs58_);
ad(elt89_,elt90_);
ad(elt88_,elt89_);
Element elt91_=el(_doc21,TH);
Element elt92_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs59_=al(1);
attrs59_.add(at(VALUE,C_P_116_83));
at(elt92_,attrs59_);
ad(elt91_,elt92_);
ad(elt88_,elt91_);
ad(elt87_,elt88_);
ad(elt86_,elt87_);
Element elt93_=el(_doc21,TBODY);
Element elt94_=el(_doc21,C_FOR);
CustList<Attr> attrs60_=al(5);
attrs60_.add(at(KEY,C_P_116_84));
attrs60_.add(at(MAP,C_P_116_85));
attrs60_.add(at(VALUE,C_P_116_86));
attrs60_.add(at(KEYCLASSNAME,C_P_116_87));
attrs60_.add(at(VARCLASSNAME,C_P_116_88));
at(elt94_,attrs60_);
Element elt95_=el(_doc21,TR);
Element elt96_=el(_doc21,TD);
Text txt7_=tx(_doc21,C_P_116_89);
ad(elt96_,txt7_);
ad(elt95_,elt96_);
Element elt97_=el(_doc21,TD);
Text txt8_=tx(_doc21,C_P_116_90);
ad(elt97_,txt8_);
ad(elt95_,elt97_);
ad(elt94_,elt95_);
ad(elt93_,elt94_);
ad(elt86_,elt93_);
ad(elt83_,elt86_);
Element elt98_=el(_doc21,BR);
ad(elt83_,elt98_);
ad(elt0_,elt83_);
Element elt99_=el(_doc21,C_IF);
CustList<Attr> attrs61_=al(1);
attrs61_.add(at(CONDITION,C_P_116_91));
at(elt99_,attrs61_);
Element elt100_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs62_=al(1);
attrs62_.add(at(VALUE,C_P_116_92));
at(elt100_,attrs62_);
ad(elt99_,elt100_);
Element elt101_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs63_=al(1);
attrs63_.add(at(VALUE,C_P_116_93));
at(elt101_,attrs63_);
Element elt102_=el(_doc21,PARAM);
CustList<Attr> attrs64_=al(1);
attrs64_.add(at(VALUE,C_P_116_94));
at(elt102_,attrs64_);
ad(elt101_,elt102_);
ad(elt99_,elt101_);
Element elt103_=el(_doc21,TABLE);
Element elt104_=el(_doc21,THEAD);
Element elt105_=el(_doc21,TR);
Element elt106_=el(_doc21,TH);
Element elt107_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs65_=al(1);
attrs65_.add(at(VALUE,C_P_116_95));
at(elt107_,attrs65_);
ad(elt106_,elt107_);
ad(elt105_,elt106_);
Element elt108_=el(_doc21,TH);
Element elt109_=el(_doc21,C_MESSAGE);
CustList<Attr> attrs66_=al(1);
attrs66_.add(at(VALUE,C_P_116_96));
at(elt109_,attrs66_);
ad(elt108_,elt109_);
ad(elt105_,elt108_);
ad(elt104_,elt105_);
ad(elt103_,elt104_);
Element elt110_=el(_doc21,TBODY);
Element elt111_=el(_doc21,C_FOR);
CustList<Attr> attrs67_=al(5);
attrs67_.add(at(KEY,C_P_116_97));
attrs67_.add(at(MAP,C_P_116_98));
attrs67_.add(at(VALUE,C_P_116_99));
attrs67_.add(at(KEYCLASSNAME,C_P_116_100));
attrs67_.add(at(VARCLASSNAME,C_P_116_101));
at(elt111_,attrs67_);
Element elt112_=el(_doc21,TR);
Element elt113_=el(_doc21,TD);
Text txt9_=tx(_doc21,C_P_116_102);
ad(elt113_,txt9_);
ad(elt112_,elt113_);
Element elt114_=el(_doc21,TD);
Text txt10_=tx(_doc21,C_P_116_103);
ad(elt114_,txt10_);
ad(elt112_,elt114_);
ad(elt111_,elt112_);
ad(elt110_,elt111_);
ad(elt103_,elt110_);
ad(elt99_,elt103_);
Element elt115_=el(_doc21,BR);
ad(elt99_,elt115_);
ad(elt0_,elt99_);
ad(_body,elt0_);
}
}
