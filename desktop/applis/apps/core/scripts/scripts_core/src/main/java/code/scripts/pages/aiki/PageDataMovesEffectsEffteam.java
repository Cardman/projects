package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffteam extends PageAikiCommon{
private static final String C_P_174_0="javahtml";
private static final String C_P_174_1="eff_team";
private static final String C_P_174_2="web/css/moves.css";
private static final String C_P_174_3="stylesheet";
private static final String C_P_174_4="text/css";
private static final String C_P_174_5="msg_effteam,effect";
private static final String C_P_174_6="{effectBean}";
private static final String C_P_174_7="aiki.beans.moves.effects";
private static final String C_P_174_8="EffectBean";
private static final String C_P_174_9="$intern.index=index";
private static final String C_P_174_10="$intern.move=move";
private static final String C_P_174_11="forbiddingHealing";
private static final String C_P_174_12="msg_effteam,forbid_heal";
private static final String C_P_174_13="protectAgainstCh";
private static final String C_P_174_14="msg_effteam,protect_ag_ch";
private static final String C_P_174_15="!forbiddenBoost.isEmpty()";
private static final String C_P_174_16="msg_effteam,forbid_boost";
private static final String C_P_174_17="forbiddenBoost";
private static final String C_P_174_18="s";
private static final String C_P_174_19="java.lang.String";
private static final String C_P_174_20="{s}";
private static final String C_P_174_21="!cancelChgtStatFoeTeam.isEmpty()";
private static final String C_P_174_22="msg_effteam,cancel_chgt_stat_foe";
private static final String C_P_174_23="defaultBoost";
private static final String C_P_174_24="cancelChgtStatFoeTeam";
private static final String C_P_174_25="s";
private static final String C_P_174_26="java.lang.String";
private static final String C_P_174_27="{s}";
private static final String C_P_174_28="!cancelChgtStatTeam.isEmpty()";
private static final String C_P_174_29="msg_effteam,cancel_chgt_stat";
private static final String C_P_174_30="defaultBoost";
private static final String C_P_174_31="cancelChgtStatTeam";
private static final String C_P_174_32="s";
private static final String C_P_174_33="java.lang.String";
private static final String C_P_174_34="{s}";
private static final String C_P_174_35="!protectAgainstLowStat.isEmpty()";
private static final String C_P_174_36="msg_effteam,protect_ag_law_statis";
private static final String C_P_174_37="protectAgainstLowStat";
private static final String C_P_174_38="s";
private static final String C_P_174_39="java.lang.String";
private static final String C_P_174_40="{s}";
private static final String C_P_174_41="!protectAgainstStatus.isEmpty()";
private static final String C_P_174_42="msg_effteam,protect_ag_status";
private static final String C_P_174_43="protectAgainstStatus";
private static final String C_P_174_44="s";
private static final String C_P_174_45="$clickStatus({index},{([s])})";
private static final String C_P_174_46="";
private static final String C_P_174_47="{getTrStatus(([s]))}";
private static final String C_P_174_48="!multStatistic.isEmpty()";
private static final String C_P_174_49="msg_effteam,mult_stat";
private static final String C_P_174_50="msg_effteam,statistic";
private static final String C_P_174_51="msg_effteam,rate";
private static final String C_P_174_52="c";
private static final String C_P_174_53="multStatistic";
private static final String C_P_174_54="r";
private static final String C_P_174_55="java.lang.String";
private static final String C_P_174_56="r";
private static final String C_P_174_57="{c}";
private static final String C_P_174_58="{r}";
private static final String C_P_174_59="!multStatisticFoe.isEmpty()";
private static final String C_P_174_60="msg_effteam,mult_stat_foe";
private static final String C_P_174_61="msg_effteam,statistic";
private static final String C_P_174_62="msg_effteam,rate";
private static final String C_P_174_63="c";
private static final String C_P_174_64="multStatisticFoe";
private static final String C_P_174_65="r";
private static final String C_P_174_66="java.lang.String";
private static final String C_P_174_67="r";
private static final String C_P_174_68="{c}";
private static final String C_P_174_69="{r}";
private static final String C_P_174_70="!multDamage.isEmpty()";
private static final String C_P_174_71="msg_effteam,mult_damage";
private static final String C_P_174_72="msg_effteam,cat";
private static final String C_P_174_73="msg_effteam,mult";
private static final String C_P_174_74="msg_effteam,rate";
private static final String C_P_174_75="c";
private static final String C_P_174_76="aiki.fight.util.CategoryMult";
private static final String C_P_174_77="multDamage";
private static final String C_P_174_78="r";
private static final String C_P_174_79="r";
private static final String C_P_174_80="{c.getCategory()}";
private static final String C_P_174_81="{c.getMult()}";
private static final String C_P_174_82="{r}";
private static final String C_P_174_83="!unusableMoves.isEmpty()";
private static final String C_P_174_84="msg_effteam,forbid_move";
private static final String C_P_174_85="unusableMoves";
private static final String C_P_174_86="s";
private static final String C_P_174_87="$clickUnusableMove({index},{([s])})";
private static final String C_P_174_88="";
private static final String C_P_174_89="{getTrUnusableMove(([s]))}";
private static final String C_P_174_90="!disableFoeTeamEffects.isEmpty()";
private static final String C_P_174_91="msg_effteam,delete_effects";
private static final String C_P_174_92="disableFoeTeamEffects";
private static final String C_P_174_93="s";
private static final String C_P_174_94="$clickDisableFoeTeamEffects({index},{([s])})";
private static final String C_P_174_95="";
private static final String C_P_174_96="{getTrDisableFoeTeamEffects(([s]))}";
private static final String C_P_174_97="!disableFoeTeamStatus.isEmpty()";
private static final String C_P_174_98="msg_effteam,delete_status";
private static final String C_P_174_99="disableFoeTeamStatus";
private static final String C_P_174_100="s";
private static final String C_P_174_101="$clickDisableFoeTeamStatus({index},{([s])})";
private static final String C_P_174_102="";
private static final String C_P_174_103="{getTrDisableFoeTeamStatus(([s]))}";
private PageDataMovesEffectsEffteam(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc79){
Element elt0_=el(_doc79,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_174_0));
attrs0_.add(at(C_BEAN,C_P_174_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc79,HEAD);
Element elt2_=el(_doc79,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_174_2));
attrs1_.add(at(REL,C_P_174_3));
attrs1_.add(at(TYPE,C_P_174_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc79,BODY);
build0(elt3_,_doc79);
ad(elt0_,elt3_);
_doc79.appendChild(elt0_);
}
static void build0(Element _body,Document _doc79){
Element elt0_=el(_doc79,P);
Element elt1_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_174_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc79,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_174_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc79,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_174_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc79,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_174_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc79,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_174_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc79,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_174_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc79,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_174_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_174_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc79,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_174_13));
at(elt9_,attrs8_);
Element elt10_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_174_14));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
Element elt11_=el(_doc79,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_174_15));
at(elt11_,attrs10_);
Element elt12_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_174_16));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
Element elt13_=el(_doc79,UL);
Element elt14_=el(_doc79,C_FOR);
CustList<Attr> attrs12_=al(3);
attrs12_.add(at(LIST,C_P_174_17));
attrs12_.add(at(VAR,C_P_174_18));
attrs12_.add(at(CLASSNAME,C_P_174_19));
at(elt14_,attrs12_);
Element elt15_=el(_doc79,LI);
Text txt0_=tx(_doc79,C_P_174_20);
ad(elt15_,txt0_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt11_,elt13_);
Element elt16_=el(_doc79,BR);
ad(elt11_,elt16_);
ad(elt0_,elt11_);
Element elt17_=el(_doc79,C_IF);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(CONDITION,C_P_174_21));
at(elt17_,attrs13_);
Element elt18_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_174_22));
at(elt18_,attrs14_);
Element elt19_=el(_doc79,PARAM);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_174_23));
at(elt19_,attrs15_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
Element elt20_=el(_doc79,UL);
Element elt21_=el(_doc79,C_FOR);
CustList<Attr> attrs16_=al(3);
attrs16_.add(at(LIST,C_P_174_24));
attrs16_.add(at(VAR,C_P_174_25));
attrs16_.add(at(CLASSNAME,C_P_174_26));
at(elt21_,attrs16_);
Element elt22_=el(_doc79,LI);
Text txt1_=tx(_doc79,C_P_174_27);
ad(elt22_,txt1_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt17_,elt20_);
Element elt23_=el(_doc79,BR);
ad(elt17_,elt23_);
ad(elt0_,elt17_);
Element elt24_=el(_doc79,C_IF);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(CONDITION,C_P_174_28));
at(elt24_,attrs17_);
Element elt25_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_174_29));
at(elt25_,attrs18_);
Element elt26_=el(_doc79,PARAM);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_174_30));
at(elt26_,attrs19_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
Element elt27_=el(_doc79,UL);
Element elt28_=el(_doc79,C_FOR);
CustList<Attr> attrs20_=al(3);
attrs20_.add(at(LIST,C_P_174_31));
attrs20_.add(at(VAR,C_P_174_32));
attrs20_.add(at(CLASSNAME,C_P_174_33));
at(elt28_,attrs20_);
Element elt29_=el(_doc79,LI);
Text txt2_=tx(_doc79,C_P_174_34);
ad(elt29_,txt2_);
ad(elt28_,elt29_);
ad(elt27_,elt28_);
ad(elt24_,elt27_);
Element elt30_=el(_doc79,BR);
ad(elt24_,elt30_);
ad(elt0_,elt24_);
Element elt31_=el(_doc79,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_174_35));
at(elt31_,attrs21_);
Element elt32_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_174_36));
at(elt32_,attrs22_);
ad(elt31_,elt32_);
Element elt33_=el(_doc79,UL);
Element elt34_=el(_doc79,C_FOR);
CustList<Attr> attrs23_=al(3);
attrs23_.add(at(LIST,C_P_174_37));
attrs23_.add(at(VAR,C_P_174_38));
attrs23_.add(at(CLASSNAME,C_P_174_39));
at(elt34_,attrs23_);
Element elt35_=el(_doc79,LI);
Text txt3_=tx(_doc79,C_P_174_40);
ad(elt35_,txt3_);
ad(elt34_,elt35_);
ad(elt33_,elt34_);
ad(elt31_,elt33_);
Element elt36_=el(_doc79,BR);
ad(elt31_,elt36_);
ad(elt0_,elt31_);
Element elt37_=el(_doc79,C_IF);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(CONDITION,C_P_174_41));
at(elt37_,attrs24_);
Element elt38_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_174_42));
at(elt38_,attrs25_);
ad(elt37_,elt38_);
Element elt39_=el(_doc79,UL);
Element elt40_=el(_doc79,C_FOR);
CustList<Attr> attrs26_=al(2);
attrs26_.add(at(LIST,C_P_174_43));
attrs26_.add(at(VAR,C_P_174_44));
at(elt40_,attrs26_);
Element elt41_=el(_doc79,LI);
Element elt42_=el(_doc79,A);
CustList<Attr> attrs27_=al(2);
attrs27_.add(at(C_COMMAND,C_P_174_45));
attrs27_.add(at(HREF,C_P_174_46));
at(elt42_,attrs27_);
Text txt4_=tx(_doc79,C_P_174_47);
ad(elt42_,txt4_);
ad(elt41_,elt42_);
ad(elt40_,elt41_);
ad(elt39_,elt40_);
ad(elt37_,elt39_);
Element elt43_=el(_doc79,BR);
ad(elt37_,elt43_);
ad(elt0_,elt37_);
Element elt44_=el(_doc79,C_IF);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(CONDITION,C_P_174_48));
at(elt44_,attrs28_);
Element elt45_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_P_174_49));
at(elt45_,attrs29_);
ad(elt44_,elt45_);
Element elt46_=el(_doc79,TABLE);
Element elt47_=el(_doc79,THEAD);
Element elt48_=el(_doc79,TR);
Element elt49_=el(_doc79,TH);
Element elt50_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs30_=al(1);
attrs30_.add(at(VALUE,C_P_174_50));
at(elt50_,attrs30_);
ad(elt49_,elt50_);
ad(elt48_,elt49_);
Element elt51_=el(_doc79,TH);
Element elt52_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(VALUE,C_P_174_51));
at(elt52_,attrs31_);
ad(elt51_,elt52_);
ad(elt48_,elt51_);
ad(elt47_,elt48_);
ad(elt46_,elt47_);
Element elt53_=el(_doc79,TBODY);
Element elt54_=el(_doc79,C_FOR);
CustList<Attr> attrs32_=al(5);
attrs32_.add(at(KEY,C_P_174_52));
attrs32_.add(at(MAP,C_P_174_53));
attrs32_.add(at(VALUE,C_P_174_54));
attrs32_.add(at(KEYCLASSNAME,C_P_174_55));
attrs32_.add(at(VARCLASSNAME,C_P_174_56));
at(elt54_,attrs32_);
Element elt55_=el(_doc79,TR);
Element elt56_=el(_doc79,TD);
Text txt5_=tx(_doc79,C_P_174_57);
ad(elt56_,txt5_);
ad(elt55_,elt56_);
Element elt57_=el(_doc79,TD);
Text txt6_=tx(_doc79,C_P_174_58);
ad(elt57_,txt6_);
ad(elt55_,elt57_);
ad(elt54_,elt55_);
ad(elt53_,elt54_);
ad(elt46_,elt53_);
ad(elt44_,elt46_);
Element elt58_=el(_doc79,BR);
ad(elt44_,elt58_);
ad(elt0_,elt44_);
Element elt59_=el(_doc79,C_IF);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(CONDITION,C_P_174_59));
at(elt59_,attrs33_);
Element elt60_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs34_=al(1);
attrs34_.add(at(VALUE,C_P_174_60));
at(elt60_,attrs34_);
ad(elt59_,elt60_);
Element elt61_=el(_doc79,TABLE);
Element elt62_=el(_doc79,THEAD);
Element elt63_=el(_doc79,TR);
Element elt64_=el(_doc79,TH);
Element elt65_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs35_=al(1);
attrs35_.add(at(VALUE,C_P_174_61));
at(elt65_,attrs35_);
ad(elt64_,elt65_);
ad(elt63_,elt64_);
Element elt66_=el(_doc79,TH);
Element elt67_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs36_=al(1);
attrs36_.add(at(VALUE,C_P_174_62));
at(elt67_,attrs36_);
ad(elt66_,elt67_);
ad(elt63_,elt66_);
ad(elt62_,elt63_);
ad(elt61_,elt62_);
Element elt68_=el(_doc79,TBODY);
Element elt69_=el(_doc79,C_FOR);
CustList<Attr> attrs37_=al(5);
attrs37_.add(at(KEY,C_P_174_63));
attrs37_.add(at(MAP,C_P_174_64));
attrs37_.add(at(VALUE,C_P_174_65));
attrs37_.add(at(KEYCLASSNAME,C_P_174_66));
attrs37_.add(at(VARCLASSNAME,C_P_174_67));
at(elt69_,attrs37_);
Element elt70_=el(_doc79,TR);
Element elt71_=el(_doc79,TD);
Text txt7_=tx(_doc79,C_P_174_68);
ad(elt71_,txt7_);
ad(elt70_,elt71_);
Element elt72_=el(_doc79,TD);
Text txt8_=tx(_doc79,C_P_174_69);
ad(elt72_,txt8_);
ad(elt70_,elt72_);
ad(elt69_,elt70_);
ad(elt68_,elt69_);
ad(elt61_,elt68_);
ad(elt59_,elt61_);
Element elt73_=el(_doc79,BR);
ad(elt59_,elt73_);
ad(elt0_,elt59_);
Element elt74_=el(_doc79,C_IF);
CustList<Attr> attrs38_=al(1);
attrs38_.add(at(CONDITION,C_P_174_70));
at(elt74_,attrs38_);
Element elt75_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs39_=al(1);
attrs39_.add(at(VALUE,C_P_174_71));
at(elt75_,attrs39_);
ad(elt74_,elt75_);
Element elt76_=el(_doc79,TABLE);
Element elt77_=el(_doc79,THEAD);
Element elt78_=el(_doc79,TR);
Element elt79_=el(_doc79,TH);
Element elt80_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(VALUE,C_P_174_72));
at(elt80_,attrs40_);
ad(elt79_,elt80_);
ad(elt78_,elt79_);
Element elt81_=el(_doc79,TH);
Element elt82_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs41_=al(1);
attrs41_.add(at(VALUE,C_P_174_73));
at(elt82_,attrs41_);
ad(elt81_,elt82_);
ad(elt78_,elt81_);
Element elt83_=el(_doc79,TH);
Element elt84_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs42_=al(1);
attrs42_.add(at(VALUE,C_P_174_74));
at(elt84_,attrs42_);
ad(elt83_,elt84_);
ad(elt78_,elt83_);
ad(elt77_,elt78_);
ad(elt76_,elt77_);
Element elt85_=el(_doc79,TBODY);
Element elt86_=el(_doc79,C_FOR);
CustList<Attr> attrs43_=al(5);
attrs43_.add(at(KEY,C_P_174_75));
attrs43_.add(at(KEYCLASSNAME,C_P_174_76));
attrs43_.add(at(MAP,C_P_174_77));
attrs43_.add(at(VALUE,C_P_174_78));
attrs43_.add(at(VARCLASSNAME,C_P_174_79));
at(elt86_,attrs43_);
Element elt87_=el(_doc79,TR);
Element elt88_=el(_doc79,TD);
Text txt9_=tx(_doc79,C_P_174_80);
ad(elt88_,txt9_);
ad(elt87_,elt88_);
Element elt89_=el(_doc79,TD);
Text txt10_=tx(_doc79,C_P_174_81);
ad(elt89_,txt10_);
ad(elt87_,elt89_);
Element elt90_=el(_doc79,TD);
Text txt11_=tx(_doc79,C_P_174_82);
ad(elt90_,txt11_);
ad(elt87_,elt90_);
ad(elt86_,elt87_);
ad(elt85_,elt86_);
ad(elt76_,elt85_);
ad(elt74_,elt76_);
Element elt91_=el(_doc79,BR);
ad(elt74_,elt91_);
ad(elt0_,elt74_);
Element elt92_=el(_doc79,C_IF);
CustList<Attr> attrs44_=al(1);
attrs44_.add(at(CONDITION,C_P_174_83));
at(elt92_,attrs44_);
Element elt93_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs45_=al(1);
attrs45_.add(at(VALUE,C_P_174_84));
at(elt93_,attrs45_);
ad(elt92_,elt93_);
Element elt94_=el(_doc79,UL);
Element elt95_=el(_doc79,C_FOR);
CustList<Attr> attrs46_=al(2);
attrs46_.add(at(LIST,C_P_174_85));
attrs46_.add(at(VAR,C_P_174_86));
at(elt95_,attrs46_);
Element elt96_=el(_doc79,LI);
Element elt97_=el(_doc79,A);
CustList<Attr> attrs47_=al(2);
attrs47_.add(at(C_COMMAND,C_P_174_87));
attrs47_.add(at(HREF,C_P_174_88));
at(elt97_,attrs47_);
Text txt12_=tx(_doc79,C_P_174_89);
ad(elt97_,txt12_);
ad(elt96_,elt97_);
ad(elt95_,elt96_);
ad(elt94_,elt95_);
ad(elt92_,elt94_);
Element elt98_=el(_doc79,BR);
ad(elt92_,elt98_);
ad(elt0_,elt92_);
Element elt99_=el(_doc79,C_IF);
CustList<Attr> attrs48_=al(1);
attrs48_.add(at(CONDITION,C_P_174_90));
at(elt99_,attrs48_);
Element elt100_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs49_=al(1);
attrs49_.add(at(VALUE,C_P_174_91));
at(elt100_,attrs49_);
ad(elt99_,elt100_);
Element elt101_=el(_doc79,UL);
Element elt102_=el(_doc79,C_FOR);
CustList<Attr> attrs50_=al(2);
attrs50_.add(at(LIST,C_P_174_92));
attrs50_.add(at(VAR,C_P_174_93));
at(elt102_,attrs50_);
Element elt103_=el(_doc79,LI);
Element elt104_=el(_doc79,A);
CustList<Attr> attrs51_=al(2);
attrs51_.add(at(C_COMMAND,C_P_174_94));
attrs51_.add(at(HREF,C_P_174_95));
at(elt104_,attrs51_);
Text txt13_=tx(_doc79,C_P_174_96);
ad(elt104_,txt13_);
ad(elt103_,elt104_);
ad(elt102_,elt103_);
ad(elt101_,elt102_);
ad(elt99_,elt101_);
Element elt105_=el(_doc79,BR);
ad(elt99_,elt105_);
ad(elt0_,elt99_);
Element elt106_=el(_doc79,C_IF);
CustList<Attr> attrs52_=al(1);
attrs52_.add(at(CONDITION,C_P_174_97));
at(elt106_,attrs52_);
Element elt107_=el(_doc79,C_MESSAGE);
CustList<Attr> attrs53_=al(1);
attrs53_.add(at(VALUE,C_P_174_98));
at(elt107_,attrs53_);
ad(elt106_,elt107_);
Element elt108_=el(_doc79,UL);
Element elt109_=el(_doc79,C_FOR);
CustList<Attr> attrs54_=al(2);
attrs54_.add(at(LIST,C_P_174_99));
attrs54_.add(at(VAR,C_P_174_100));
at(elt109_,attrs54_);
Element elt110_=el(_doc79,LI);
Element elt111_=el(_doc79,A);
CustList<Attr> attrs55_=al(2);
attrs55_.add(at(C_COMMAND,C_P_174_101));
attrs55_.add(at(HREF,C_P_174_102));
at(elt111_,attrs55_);
Text txt14_=tx(_doc79,C_P_174_103);
ad(elt111_,txt14_);
ad(elt110_,elt111_);
ad(elt109_,elt110_);
ad(elt108_,elt109_);
ad(elt106_,elt108_);
Element elt112_=el(_doc79,BR);
ad(elt106_,elt112_);
ad(elt0_,elt106_);
ad(_body,elt0_);
}
}
