package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataStatusData extends PageCardsCommon{
private static final String C_P_204_0="javahtml";
private static final String C_P_204_1="status";
private static final String C_P_204_2="msg_status,title";
private static final String C_P_204_3="displayName";
private static final String C_P_204_4="web/css/abilities.css";
private static final String C_P_204_5="stylesheet";
private static final String C_P_204_6="text/css";
private static final String C_P_204_7="$clickIndex()";
private static final String C_P_204_8="";
private static final String C_P_204_9="msg_status,status";
private static final String C_P_204_10="{displayName}";
private static final String C_P_204_11="{animStatus}";
private static final String C_P_204_12="endRound";
private static final String C_P_204_13="msg_effendround,rank";
private static final String C_P_204_14="endRoundRank";
private static final String C_P_204_15=GO_TO_ENDROUND;
private static final String C_P_204_16="";
private static final String C_P_204_17="msg_status,endRound";
private static final String C_P_204_18="!reasonsEndRound.isEmpty()";
private static final String C_P_204_19="msg_effendround,reasons";
private static final String C_P_204_20="reasonsEndRound";
private static final String C_P_204_21="r";
private static final String C_P_204_22="java.lang.String";
private static final String C_P_204_23="{r}";
private static final String C_P_204_24="c";
private static final String C_P_204_25="mapVarsFailEndRound";
private static final String C_P_204_26="r";
private static final String C_P_204_27="java.lang.String";
private static final String C_P_204_28="java.lang.String";
private static final String C_P_204_29="{c} :";
private static final String C_P_204_30="";
private static final String C_P_204_31="msg_status,formula";
private static final String C_P_204_32="r";
private static final String C_P_204_33="singleStatus";
private static final String C_P_204_34="incrementingDamageByRounds";
private static final String C_P_204_35="msg_status,damage_incremented_true";
private static final String C_P_204_36="!incrementingDamageByRounds";
private static final String C_P_204_37="msg_status,damage_incremented_false";
private static final String C_P_204_38="!catchingRate.isZero()";
private static final String C_P_204_39="msg_status,catching_rate";
private static final String C_P_204_40="catchingRate";
private static final String C_P_204_41="disabledEffIfSwitch";
private static final String C_P_204_42="msg_status,disabled_eff_if_switch";
private static final String C_P_204_43="incrementEndRoundInt()";
private static final String C_P_204_44="msg_status,increment_end_round";
private static final String C_P_204_45="incrementEndRound";
private static final String C_P_204_46="incrementingEndRound";
private static final String C_P_204_47="msg_status,incrementing_end_round_true";
private static final String C_P_204_48="!incrementingEndRound";
private static final String C_P_204_49="msg_status,incrementing_end_round_false";
private static final String C_P_204_50="isSingle()";
private static final String C_P_204_51="msg_status,single";
private static final String C_P_204_52="!isSingle()";
private static final String C_P_204_53="msg_status,relation";
private static final String C_P_204_54="!multStat.isEmpty()";
private static final String C_P_204_55="msg_status,mult_stat";
private static final String C_P_204_56="msg_status,mult_stat_key";
private static final String C_P_204_57="msg_status,mult_stat_value";
private static final String C_P_204_58="s";
private static final String C_P_204_59="multStat";
private static final String C_P_204_60="r";
private static final String C_P_204_61="java.lang.Object";
private static final String C_P_204_62="r";
private static final String C_P_204_63="{getTrMultStat(([s]))}";
private static final String C_P_204_64="{r}";
private static final String C_P_204_65="!reasons.isEmpty()";
private static final String C_P_204_66="msg_status,reasons";
private static final String C_P_204_67="reasons";
private static final String C_P_204_68="r";
private static final String C_P_204_69="java.lang.String";
private static final String C_P_204_70="{r}";
private static final String C_P_204_71="c";
private static final String C_P_204_72="mapVarsFail";
private static final String C_P_204_73="r";
private static final String C_P_204_74="java.lang.String";
private static final String C_P_204_75="java.lang.String";
private static final String C_P_204_76="{c} :";
private static final String C_P_204_77="";
private static final String C_P_204_78="msg_status,formula";
private static final String C_P_204_79="r";
private static final String C_P_204_80="!rateForUsingAMove.isZero()";
private static final String C_P_204_81="msg_status,rate_use_move";
private static final String C_P_204_82="rateForUsingAMove";
private static final String C_P_204_83="notAttack";
private static final String C_P_204_84="msg_status,not_attack";
private static final String C_P_204_85="!rateForUsingAMoveIfFoe.isZero()";
private static final String C_P_204_86="msg_status,rate_use_move_foe";
private static final String C_P_204_87="rateForUsingAMoveIfFoe";
private static final String C_P_204_88="notAttackFoe";
private static final String C_P_204_89="msg_status,not_attack_foe";
private static final String C_P_204_90="!rateForFullHealIfMove.isZero()";
private static final String C_P_204_91="msg_status,rate_heal_move";
private static final String C_P_204_92="rateForFullHealIfMove";
private static final String C_P_204_93="!lawForUsingAMoveNbRound.isEmpty()";
private static final String C_P_204_94="msg_status,rate_use_move_round";
private static final String C_P_204_95="msg_status,rate_use_move_round_key";
private static final String C_P_204_96="msg_status,rate_use_move_round_rate";
private static final String C_P_204_97="s";
private static final String C_P_204_98="lawForUsingAMoveNbRound";
private static final String C_P_204_99="r";
private static final String C_P_204_100="li";
private static final String C_P_204_101="r";
private static final String C_P_204_102="{s}";
private static final String C_P_204_103="{r}";
private static final String C_P_204_104="!power.isZero()";
private static final String C_P_204_105="msg_status,auto_damage";
private static final String C_P_204_106="power";
private static final String C_P_204_107="attack";
private static final String C_P_204_108="defense";
private static final String C_P_204_109="!effectsPartner.isEmpty()";
private static final String C_P_204_110="!getEffectPartner().getRestoredHpRateLovedAlly().isZero()";
private static final String C_P_204_111="msg_status,heal_hp";
private static final String C_P_204_112="getEffectPartner().getRestoredHpRateLovedAlly()";
private static final String C_P_204_113="getEffectPartner().getWeddingAlly()";
private static final String C_P_204_114="msg_status,wedding";
private static final String C_P_204_115="msg_status,damaged_foes";
private static final String C_P_204_116="getEffectPartner().getMultDamageAgainstFoe()";
private PageDataStatusData(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc109){
Element elt0_=el(_doc109,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_204_0));
attrs0_.add(at(C_BEAN,C_P_204_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc109,HEAD);
Element elt2_=el(_doc109,TITLE);
Element elt3_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_204_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc109,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_204_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc109,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_204_4));
attrs3_.add(at(REL,C_P_204_5));
attrs3_.add(at(TYPE,C_P_204_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc109,BODY);
build0(elt6_,_doc109);
ad(elt0_,elt6_);
_doc109.appendChild(elt0_);
}
static void build0(Element _body,Document _doc109){
Element elt0_=el(_doc109,P);
Element elt1_=el(_doc109,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_204_7));
attrs0_.add(at(HREF,C_P_204_8));
at(elt1_,attrs0_);
Element elt2_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_204_9));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc109,BR);
ad(elt0_,elt3_);
Text txt0_=tx(_doc109,C_P_204_10);
ad(elt0_,txt0_);
Element elt4_=el(_doc109,BR);
ad(elt0_,elt4_);
Element elt5_=el(_doc109,C_IMG);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(SRC,C_P_204_11));
at(elt5_,attrs2_);
ad(elt0_,elt5_);
Element elt6_=el(_doc109,BR);
ad(elt0_,elt6_);
Element elt7_=el(_doc109,C_IF);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(CONDITION,C_P_204_12));
at(elt7_,attrs3_);
Element elt8_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_204_13));
at(elt8_,attrs4_);
Element elt9_=el(_doc109,PARAM);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_204_14));
at(elt9_,attrs5_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
Element elt10_=el(_doc109,A);
CustList<Attr> attrs6_=al(2);
attrs6_.add(at(C_COMMAND,C_P_204_15));
attrs6_.add(at(HREF,C_P_204_16));
at(elt10_,attrs6_);
Element elt11_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_204_17));
at(elt11_,attrs7_);
ad(elt10_,elt11_);
ad(elt7_,elt10_);
Element elt12_=el(_doc109,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_204_18));
at(elt12_,attrs8_);
Element elt13_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_204_19));
at(elt13_,attrs9_);
ad(elt12_,elt13_);
Element elt14_=el(_doc109,UL);
Element elt15_=el(_doc109,C_FOR);
CustList<Attr> attrs10_=al(3);
attrs10_.add(at(LIST,C_P_204_20));
attrs10_.add(at(VAR,C_P_204_21));
attrs10_.add(at(CLASSNAME,C_P_204_22));
at(elt15_,attrs10_);
Element elt16_=el(_doc109,LI);
Text txt1_=tx(_doc109,C_P_204_23);
ad(elt16_,txt1_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
ad(elt12_,elt14_);
Element elt17_=el(_doc109,BR);
ad(elt12_,elt17_);
Element elt18_=el(_doc109,UL);
Element elt19_=el(_doc109,C_FOR);
CustList<Attr> attrs11_=al(5);
attrs11_.add(at(KEY,C_P_204_24));
attrs11_.add(at(MAP,C_P_204_25));
attrs11_.add(at(VALUE,C_P_204_26));
attrs11_.add(at(KEYCLASSNAME,C_P_204_27));
attrs11_.add(at(VARCLASSNAME,C_P_204_28));
at(elt19_,attrs11_);
Element elt20_=el(_doc109,LI);
Text txt2_=tx(_doc109,C_P_204_29);
ad(elt20_,txt2_);
Element elt21_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs12_=al(2);
attrs12_.add(at(QUOTED,C_P_204_30));
attrs12_.add(at(VALUE,C_P_204_31));
at(elt21_,attrs12_);
Element elt22_=el(_doc109,PARAM);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_204_32));
at(elt22_,attrs13_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt18_,elt19_);
ad(elt12_,elt18_);
Element elt23_=el(_doc109,BR);
ad(elt12_,elt23_);
ad(elt7_,elt12_);
Element elt24_=el(_doc109,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_204_33));
at(elt24_,attrs14_);
Element elt25_=el(_doc109,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_204_34));
at(elt25_,attrs15_);
Element elt26_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_204_35));
at(elt26_,attrs16_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
Element elt27_=el(_doc109,C_IF);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(CONDITION,C_P_204_36));
at(elt27_,attrs17_);
Element elt28_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_204_37));
at(elt28_,attrs18_);
ad(elt27_,elt28_);
ad(elt24_,elt27_);
ad(elt7_,elt24_);
ad(elt0_,elt7_);
Element elt29_=el(_doc109,C_IF);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(CONDITION,C_P_204_38));
at(elt29_,attrs19_);
Element elt30_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_204_39));
at(elt30_,attrs20_);
Element elt31_=el(_doc109,PARAM);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_P_204_40));
at(elt31_,attrs21_);
ad(elt30_,elt31_);
ad(elt29_,elt30_);
ad(elt0_,elt29_);
Element elt32_=el(_doc109,C_IF);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(CONDITION,C_P_204_41));
at(elt32_,attrs22_);
Element elt33_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_204_42));
at(elt33_,attrs23_);
ad(elt32_,elt33_);
ad(elt0_,elt32_);
Element elt34_=el(_doc109,C_IF);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(CONDITION,C_P_204_43));
at(elt34_,attrs24_);
Element elt35_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_204_44));
at(elt35_,attrs25_);
Element elt36_=el(_doc109,PARAM);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(VALUE,C_P_204_45));
at(elt36_,attrs26_);
ad(elt35_,elt36_);
ad(elt34_,elt35_);
ad(elt0_,elt34_);
Element elt37_=el(_doc109,C_IF);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(CONDITION,C_P_204_46));
at(elt37_,attrs27_);
Element elt38_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(VALUE,C_P_204_47));
at(elt38_,attrs28_);
ad(elt37_,elt38_);
ad(elt0_,elt37_);
Element elt39_=el(_doc109,C_IF);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(CONDITION,C_P_204_48));
at(elt39_,attrs29_);
Element elt40_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs30_=al(1);
attrs30_.add(at(VALUE,C_P_204_49));
at(elt40_,attrs30_);
ad(elt39_,elt40_);
ad(elt0_,elt39_);
Element elt41_=el(_doc109,C_IF);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(CONDITION,C_P_204_50));
at(elt41_,attrs31_);
Element elt42_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs32_=al(1);
attrs32_.add(at(VALUE,C_P_204_51));
at(elt42_,attrs32_);
ad(elt41_,elt42_);
ad(elt0_,elt41_);
Element elt43_=el(_doc109,C_IF);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(CONDITION,C_P_204_52));
at(elt43_,attrs33_);
Element elt44_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs34_=al(1);
attrs34_.add(at(VALUE,C_P_204_53));
at(elt44_,attrs34_);
ad(elt43_,elt44_);
ad(elt0_,elt43_);
Element elt45_=el(_doc109,C_IF);
CustList<Attr> attrs35_=al(1);
attrs35_.add(at(CONDITION,C_P_204_54));
at(elt45_,attrs35_);
Element elt46_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs36_=al(1);
attrs36_.add(at(VALUE,C_P_204_55));
at(elt46_,attrs36_);
ad(elt45_,elt46_);
Element elt47_=el(_doc109,TABLE);
Element elt48_=el(_doc109,THEAD);
Element elt49_=el(_doc109,TR);
Element elt50_=el(_doc109,TH);
Element elt51_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs37_=al(1);
attrs37_.add(at(VALUE,C_P_204_56));
at(elt51_,attrs37_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
Element elt52_=el(_doc109,TH);
Element elt53_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs38_=al(1);
attrs38_.add(at(VALUE,C_P_204_57));
at(elt53_,attrs38_);
ad(elt52_,elt53_);
ad(elt49_,elt52_);
ad(elt48_,elt49_);
ad(elt47_,elt48_);
Element elt54_=el(_doc109,TBODY);
Element elt55_=el(_doc109,C_FOR);
CustList<Attr> attrs39_=al(5);
attrs39_.add(at(KEY,C_P_204_58));
attrs39_.add(at(MAP,C_P_204_59));
attrs39_.add(at(VALUE,C_P_204_60));
attrs39_.add(at(KEYCLASSNAME,C_P_204_61));
attrs39_.add(at(VARCLASSNAME,C_P_204_62));
at(elt55_,attrs39_);
Element elt56_=el(_doc109,TR);
Element elt57_=el(_doc109,TD);
Text txt3_=tx(_doc109,C_P_204_63);
ad(elt57_,txt3_);
ad(elt56_,elt57_);
Element elt58_=el(_doc109,TD);
Text txt4_=tx(_doc109,C_P_204_64);
ad(elt58_,txt4_);
ad(elt56_,elt58_);
ad(elt55_,elt56_);
ad(elt54_,elt55_);
ad(elt47_,elt54_);
ad(elt45_,elt47_);
Element elt59_=el(_doc109,BR);
ad(elt45_,elt59_);
ad(elt0_,elt45_);
Element elt60_=el(_doc109,C_IF);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(CONDITION,C_P_204_65));
at(elt60_,attrs40_);
Element elt61_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs41_=al(1);
attrs41_.add(at(VALUE,C_P_204_66));
at(elt61_,attrs41_);
ad(elt60_,elt61_);
Element elt62_=el(_doc109,UL);
Element elt63_=el(_doc109,C_FOR);
CustList<Attr> attrs42_=al(3);
attrs42_.add(at(LIST,C_P_204_67));
attrs42_.add(at(VAR,C_P_204_68));
attrs42_.add(at(CLASSNAME,C_P_204_69));
at(elt63_,attrs42_);
Element elt64_=el(_doc109,LI);
Text txt5_=tx(_doc109,C_P_204_70);
ad(elt64_,txt5_);
ad(elt63_,elt64_);
ad(elt62_,elt63_);
ad(elt60_,elt62_);
Element elt65_=el(_doc109,BR);
ad(elt60_,elt65_);
Element elt66_=el(_doc109,UL);
Element elt67_=el(_doc109,C_FOR);
CustList<Attr> attrs43_=al(5);
attrs43_.add(at(KEY,C_P_204_71));
attrs43_.add(at(MAP,C_P_204_72));
attrs43_.add(at(VALUE,C_P_204_73));
attrs43_.add(at(KEYCLASSNAME,C_P_204_74));
attrs43_.add(at(VARCLASSNAME,C_P_204_75));
at(elt67_,attrs43_);
Element elt68_=el(_doc109,LI);
Text txt6_=tx(_doc109,C_P_204_76);
ad(elt68_,txt6_);
Element elt69_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs44_=al(2);
attrs44_.add(at(QUOTED,C_P_204_77));
attrs44_.add(at(VALUE,C_P_204_78));
at(elt69_,attrs44_);
Element elt70_=el(_doc109,PARAM);
CustList<Attr> attrs45_=al(1);
attrs45_.add(at(VALUE,C_P_204_79));
at(elt70_,attrs45_);
ad(elt69_,elt70_);
ad(elt68_,elt69_);
ad(elt67_,elt68_);
ad(elt66_,elt67_);
ad(elt60_,elt66_);
Element elt71_=el(_doc109,BR);
ad(elt60_,elt71_);
ad(elt0_,elt60_);
Element elt72_=el(_doc109,C_IF);
CustList<Attr> attrs46_=al(1);
attrs46_.add(at(CONDITION,C_P_204_80));
at(elt72_,attrs46_);
Element elt73_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs47_=al(1);
attrs47_.add(at(VALUE,C_P_204_81));
at(elt73_,attrs47_);
Element elt74_=el(_doc109,PARAM);
CustList<Attr> attrs48_=al(1);
attrs48_.add(at(VALUE,C_P_204_82));
at(elt74_,attrs48_);
ad(elt73_,elt74_);
ad(elt72_,elt73_);
ad(elt0_,elt72_);
Element elt75_=el(_doc109,C_IF);
CustList<Attr> attrs49_=al(1);
attrs49_.add(at(CONDITION,C_P_204_83));
at(elt75_,attrs49_);
Element elt76_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs50_=al(1);
attrs50_.add(at(VALUE,C_P_204_84));
at(elt76_,attrs50_);
ad(elt75_,elt76_);
ad(elt0_,elt75_);
Element elt77_=el(_doc109,C_IF);
CustList<Attr> attrs51_=al(1);
attrs51_.add(at(CONDITION,C_P_204_85));
at(elt77_,attrs51_);
Element elt78_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs52_=al(1);
attrs52_.add(at(VALUE,C_P_204_86));
at(elt78_,attrs52_);
Element elt79_=el(_doc109,PARAM);
CustList<Attr> attrs53_=al(1);
attrs53_.add(at(VALUE,C_P_204_87));
at(elt79_,attrs53_);
ad(elt78_,elt79_);
ad(elt77_,elt78_);
ad(elt0_,elt77_);
Element elt80_=el(_doc109,C_IF);
CustList<Attr> attrs54_=al(1);
attrs54_.add(at(CONDITION,C_P_204_88));
at(elt80_,attrs54_);
Element elt81_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs55_=al(1);
attrs55_.add(at(VALUE,C_P_204_89));
at(elt81_,attrs55_);
ad(elt80_,elt81_);
ad(elt0_,elt80_);
Element elt82_=el(_doc109,C_IF);
CustList<Attr> attrs56_=al(1);
attrs56_.add(at(CONDITION,C_P_204_90));
at(elt82_,attrs56_);
Element elt83_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs57_=al(1);
attrs57_.add(at(VALUE,C_P_204_91));
at(elt83_,attrs57_);
Element elt84_=el(_doc109,PARAM);
CustList<Attr> attrs58_=al(1);
attrs58_.add(at(VALUE,C_P_204_92));
at(elt84_,attrs58_);
ad(elt83_,elt84_);
ad(elt82_,elt83_);
ad(elt0_,elt82_);
Element elt85_=el(_doc109,C_IF);
CustList<Attr> attrs59_=al(1);
attrs59_.add(at(CONDITION,C_P_204_93));
at(elt85_,attrs59_);
Element elt86_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs60_=al(1);
attrs60_.add(at(VALUE,C_P_204_94));
at(elt86_,attrs60_);
ad(elt85_,elt86_);
Element elt87_=el(_doc109,TABLE);
Element elt88_=el(_doc109,THEAD);
Element elt89_=el(_doc109,TR);
Element elt90_=el(_doc109,TH);
Element elt91_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs61_=al(1);
attrs61_.add(at(VALUE,C_P_204_95));
at(elt91_,attrs61_);
ad(elt90_,elt91_);
ad(elt89_,elt90_);
Element elt92_=el(_doc109,TH);
Element elt93_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs62_=al(1);
attrs62_.add(at(VALUE,C_P_204_96));
at(elt93_,attrs62_);
ad(elt92_,elt93_);
ad(elt89_,elt92_);
ad(elt88_,elt89_);
ad(elt87_,elt88_);
Element elt94_=el(_doc109,TBODY);
Element elt95_=el(_doc109,C_FOR);
CustList<Attr> attrs63_=al(5);
attrs63_.add(at(KEY,C_P_204_97));
attrs63_.add(at(MAP,C_P_204_98));
attrs63_.add(at(VALUE,C_P_204_99));
attrs63_.add(at(KEYCLASSNAME,C_P_204_100));
attrs63_.add(at(VARCLASSNAME,C_P_204_101));
at(elt95_,attrs63_);
Element elt96_=el(_doc109,TR);
Element elt97_=el(_doc109,TD);
Text txt7_=tx(_doc109,C_P_204_102);
ad(elt97_,txt7_);
ad(elt96_,elt97_);
Element elt98_=el(_doc109,TD);
Text txt8_=tx(_doc109,C_P_204_103);
ad(elt98_,txt8_);
ad(elt96_,elt98_);
ad(elt95_,elt96_);
ad(elt94_,elt95_);
ad(elt87_,elt94_);
ad(elt85_,elt87_);
Element elt99_=el(_doc109,BR);
ad(elt85_,elt99_);
ad(elt0_,elt85_);
Element elt100_=el(_doc109,C_IF);
CustList<Attr> attrs64_=al(1);
attrs64_.add(at(CONDITION,C_P_204_104));
at(elt100_,attrs64_);
Element elt101_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs65_=al(1);
attrs65_.add(at(VALUE,C_P_204_105));
at(elt101_,attrs65_);
Element elt102_=el(_doc109,PARAM);
CustList<Attr> attrs66_=al(1);
attrs66_.add(at(VALUE,C_P_204_106));
at(elt102_,attrs66_);
ad(elt101_,elt102_);
Element elt103_=el(_doc109,PARAM);
CustList<Attr> attrs67_=al(1);
attrs67_.add(at(VALUE,C_P_204_107));
at(elt103_,attrs67_);
ad(elt101_,elt103_);
Element elt104_=el(_doc109,PARAM);
CustList<Attr> attrs68_=al(1);
attrs68_.add(at(VALUE,C_P_204_108));
at(elt104_,attrs68_);
ad(elt101_,elt104_);
ad(elt100_,elt101_);
ad(elt0_,elt100_);
Element elt105_=el(_doc109,C_IF);
CustList<Attr> attrs69_=al(1);
attrs69_.add(at(CONDITION,C_P_204_109));
at(elt105_,attrs69_);
Element elt106_=el(_doc109,HR);
ad(elt105_,elt106_);
Element elt107_=el(_doc109,C_IF);
CustList<Attr> attrs70_=al(1);
attrs70_.add(at(CONDITION,C_P_204_110));
at(elt107_,attrs70_);
Element elt108_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs71_=al(1);
attrs71_.add(at(VALUE,C_P_204_111));
at(elt108_,attrs71_);
Element elt109_=el(_doc109,PARAM);
CustList<Attr> attrs72_=al(1);
attrs72_.add(at(VALUE,C_P_204_112));
at(elt109_,attrs72_);
ad(elt108_,elt109_);
ad(elt107_,elt108_);
ad(elt105_,elt107_);
Element elt110_=el(_doc109,C_IF);
CustList<Attr> attrs73_=al(1);
attrs73_.add(at(CONDITION,C_P_204_113));
at(elt110_,attrs73_);
Element elt111_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs74_=al(1);
attrs74_.add(at(VALUE,C_P_204_114));
at(elt111_,attrs74_);
ad(elt110_,elt111_);
Element elt112_=el(_doc109,C_MESSAGE);
CustList<Attr> attrs75_=al(1);
attrs75_.add(at(VALUE,C_P_204_115));
at(elt112_,attrs75_);
Element elt113_=el(_doc109,PARAM);
CustList<Attr> attrs76_=al(1);
attrs76_.add(at(VALUE,C_P_204_116));
at(elt113_,attrs76_);
ad(elt112_,elt113_);
ad(elt110_,elt112_);
ad(elt105_,elt110_);
ad(elt0_,elt105_);
ad(_body,elt0_);
}
}
