package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
final class PageTarotDetailsresults extends PageCardsCommon{
private static final String C_3_2_0="details";
private static final String C_3_2_1="javahtml";
private static final String C_3_2_2="msg,results";
private static final String C_3_2_3="resources_cards/css/tarot.css";
private static final String C_3_2_4="stylesheet";
private static final String C_3_2_5="text/css";
private static final String C_3_2_6="playClassicGame()";
private static final String C_3_2_7="msg,classic_bid";
private static final String C_3_2_8="msg,classic_base";
private static final String C_3_2_9="{basePoints}";
private static final String C_3_2_10="msg,classic_small";
private static final String C_3_2_11="{playerSmall}";
private static final String C_3_2_12="msg,classic_diff";
private static final String C_3_2_13="{differenceScoreTaker}";
private static final String C_3_2_14="msg,classic_rate";
private static final String C_3_2_15="{rate}";
private static final String C_3_2_16="msg,classic_score_taker";
private static final String C_3_2_17="basePoints";
private static final String C_3_2_18="small";
private static final String C_3_2_19="differenceScoreTaker";
private static final String C_3_2_20="rate";
private static final String C_3_2_21="multipliedTmp";
private static final String C_3_2_22="msg,classic_decl";
private static final String C_3_2_23="l";
private static final String C_3_2_24="linesDeclaring";
private static final String C_3_2_25="cards.tarot.beans.SumDeclaringPlayer";
private static final String C_3_2_26="msg,classic_decl_player";
private static final String C_3_2_27="l.nickname";
private static final String C_3_2_28="l.status";
private static final String C_3_2_29="h";
private static final String C_3_2_30="p";
private static final String C_3_2_31="l.handfuls";
private static final String C_3_2_32="java.lang.String";
private static final String C_3_2_33="java.lang.Short";
private static final String C_3_2_34="{h} : {p}";
private static final String C_3_2_35="m";
private static final String C_3_2_36="p";
private static final String C_3_2_37="l.miseres";
private static final String C_3_2_38="java.lang.String";
private static final String C_3_2_39="java.lang.Short";
private static final String C_3_2_40="{m} : {p}";
private static final String C_3_2_41="msg,sum";
private static final String C_3_2_42="{l.sum}";
private static final String C_3_2_43="msg,classic_sum_player";
private static final String C_3_2_44="{sumPlayers}";
private static final String C_3_2_45="msg,classic_addon";
private static final String C_3_2_46="msg,classic_addon_att";
private static final String C_3_2_47="{additionnalBonusesAttack}";
private static final String C_3_2_48="msg,classic_addon_def";
private static final String C_3_2_49="{additionnalBonusesDefense}";
private static final String C_3_2_50="msg,classic_addon_sum";
private static final String C_3_2_51="{diffAttackDefenseBonuses}";
private static final String C_3_2_52="1";
private static final String C_3_2_53="msg,classic_rate_pl";
private static final String C_3_2_54="msg,player";
private static final String C_3_2_55="msg,rate";
private static final String C_3_2_56="msg,score";
private static final String C_3_2_57="l";
private static final String C_3_2_58="playersScores";
private static final String C_3_2_59="cards.tarot.beans.ScoresPlayers";
private static final String C_3_2_60="{l.nickname}";
private static final String C_3_2_61="{l.rate}";
private static final String C_3_2_62="{l.score}";
private static final String C_3_2_63="playVariantModeGame()";
private static final String C_3_2_64="1";
private static final String C_3_2_65="msg,variant_table_1";
private static final String C_3_2_66="msg,variant_table_1_1";
private static final String C_3_2_67="msg,variant_table_1_2";
private static final String C_3_2_68="msg,variant_table_1_3";
private static final String C_3_2_69="msg,variant_table_1_4";
private static final String C_3_2_70="msg,variant_table_1_5";
private static final String C_3_2_71="msg,variant_table_1_6";
private static final String C_3_2_72="l";
private static final String C_3_2_73="orderedPlayers";
private static final String C_3_2_74="cards.tarot.beans.RankingPlayerVariantGame";
private static final String C_3_2_75="{l.nickname}";
private static final String C_3_2_76="{l.positionDiff}";
private static final String C_3_2_77="{l.positionOudlers}";
private static final String C_3_2_78="{l.positionCharacters}";
private static final String C_3_2_79="{l.positionStrengthCharacters}";
private static final String C_3_2_80="{l.finalPosition}";
private static final String C_3_2_81="1";
private static final String C_3_2_82="msg,variant_table_2";
private static final String C_3_2_83="msg,variant_table_2_1";
private static final String C_3_2_84="msg,variant_table_2_2";
private static final String C_3_2_85="msg,variant_table_2_3";
private static final String C_3_2_86="msg,variant_table_2_4";
private static final String C_3_2_87="msg,variant_table_2_5";
private static final String C_3_2_88="msg,variant_table_2_6";
private static final String C_3_2_89="l";
private static final String C_3_2_90="pointsPlayers";
private static final String C_3_2_91="cards.tarot.beans.PointsPlayerVariantGame";
private static final String C_3_2_92="{l.nickname}";
private static final String C_3_2_93="{l.pointsTricks}";
private static final String C_3_2_94="{l.minimumPoints}";
private static final String C_3_2_95="{l.differenceScore}";
private static final String C_3_2_96="{l.rate}";
private static final String C_3_2_97="{l.score}";
private static final String C_3_2_98="msg,variant_decl";
private static final String C_3_2_99="l";
private static final String C_3_2_100="linesDeclaring";
private static final String C_3_2_101="cards.tarot.beans.SumDeclaringPlayer";
private static final String C_3_2_102="msg,variant_decl_pl";
private static final String C_3_2_103="l.nickname";
private static final String C_3_2_104="h";
private static final String C_3_2_105="p";
private static final String C_3_2_106="l.handfuls";
private static final String C_3_2_107="java.lang.String";
private static final String C_3_2_108="java.lang.Short";
private static final String C_3_2_109="{h}";
private static final String C_3_2_110="m";
private static final String C_3_2_111="p";
private static final String C_3_2_112="l.miseres";
private static final String C_3_2_113="java.lang.String";
private static final String C_3_2_114="java.lang.Short";
private static final String C_3_2_115="{m}";
private static final String C_3_2_116="msg,variant_add";
private static final String C_3_2_117="1";
private static final String C_3_2_118="msg,variant_add_pl";
private static final String C_3_2_119="msg,variant_add_pl_1";
private static final String C_3_2_120="msg,variant_add_pl_2";
private static final String C_3_2_121="l";
private static final String C_3_2_122="bonuses";
private static final String C_3_2_123="cards.tarot.beans.BonusesPlayers";
private static final String C_3_2_124="{l.nickname}";
private static final String C_3_2_125="{l.bonus}";
private PageTarotDetailsresults(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_BEAN,C_3_2_0));
attrs0_.add(at(XMLNS_C,C_3_2_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,TITLE);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_3_2_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_3_2_3));
attrs2_.add(at(REL,C_3_2_4));
attrs2_.add(at(TYPE,C_3_2_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,BODY);
build0(elt5_,_doc);
build1(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_3_2_6));
at(elt0_,attrs0_);
Element elt1_=el(_doc,H1);
Element elt2_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_3_2_7));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,P);
Element elt4_=el(_doc,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_3_2_8));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
Text txt0_=tx(_doc,C_3_2_9);
ad(elt3_,txt0_);
ad(elt0_,elt3_);
Element elt5_=el(_doc,P);
Element elt6_=el(_doc,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_3_2_10));
at(elt6_,attrs3_);
ad(elt5_,elt6_);
Text txt1_=tx(_doc,C_3_2_11);
ad(elt5_,txt1_);
ad(elt0_,elt5_);
Element elt7_=el(_doc,P);
Element elt8_=el(_doc,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_3_2_12));
at(elt8_,attrs4_);
ad(elt7_,elt8_);
Text txt2_=tx(_doc,C_3_2_13);
ad(elt7_,txt2_);
ad(elt0_,elt7_);
Element elt9_=el(_doc,P);
Element elt10_=el(_doc,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_3_2_14));
at(elt10_,attrs5_);
ad(elt9_,elt10_);
Text txt3_=tx(_doc,C_3_2_15);
ad(elt9_,txt3_);
ad(elt0_,elt9_);
Element elt11_=el(_doc,P);
Element elt12_=el(_doc,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_3_2_16));
at(elt12_,attrs6_);
Element elt13_=el(_doc,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_3_2_17));
at(elt13_,attrs7_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,PARAM);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_3_2_18));
at(elt14_,attrs8_);
ad(elt12_,elt14_);
Element elt15_=el(_doc,PARAM);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_3_2_19));
at(elt15_,attrs9_);
ad(elt12_,elt15_);
Element elt16_=el(_doc,PARAM);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_3_2_20));
at(elt16_,attrs10_);
ad(elt12_,elt16_);
Element elt17_=el(_doc,PARAM);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_3_2_21));
at(elt17_,attrs11_);
ad(elt12_,elt17_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
Element elt18_=el(_doc,H1);
Element elt19_=el(_doc,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_3_2_22));
at(elt19_,attrs12_);
ad(elt18_,elt19_);
ad(elt0_,elt18_);
Element elt20_=el(_doc,UL);
Element elt21_=el(_doc,C_FOR);
CustList<Attr> attrs13_=al(3);
attrs13_.add(at(VAR,C_3_2_23));
attrs13_.add(at(LIST,C_3_2_24));
attrs13_.add(at(CLASSNAME,C_3_2_25));
at(elt21_,attrs13_);
Element elt22_=el(_doc,LI);
Element elt23_=el(_doc,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_3_2_26));
at(elt23_,attrs14_);
Element elt24_=el(_doc,PARAM);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_3_2_27));
at(elt24_,attrs15_);
ad(elt23_,elt24_);
Element elt25_=el(_doc,PARAM);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_3_2_28));
at(elt25_,attrs16_);
ad(elt23_,elt25_);
ad(elt22_,elt23_);
Element elt26_=el(_doc,BR);
ad(elt22_,elt26_);
Element elt27_=el(_doc,UL);
Element elt28_=el(_doc,C_FOR);
CustList<Attr> attrs17_=al(5);
attrs17_.add(at(KEY,C_3_2_29));
attrs17_.add(at(VALUE,C_3_2_30));
attrs17_.add(at(MAP,C_3_2_31));
attrs17_.add(at(KEYCLASSNAME,C_3_2_32));
attrs17_.add(at(VARCLASSNAME,C_3_2_33));
at(elt28_,attrs17_);
Element elt29_=el(_doc,LI);
Text txt4_=tx(_doc,C_3_2_34);
ad(elt29_,txt4_);
ad(elt28_,elt29_);
ad(elt27_,elt28_);
Element elt30_=el(_doc,C_FOR);
CustList<Attr> attrs18_=al(5);
attrs18_.add(at(KEY,C_3_2_35));
attrs18_.add(at(VALUE,C_3_2_36));
attrs18_.add(at(MAP,C_3_2_37));
attrs18_.add(at(KEYCLASSNAME,C_3_2_38));
attrs18_.add(at(VARCLASSNAME,C_3_2_39));
at(elt30_,attrs18_);
Element elt31_=el(_doc,LI);
Text txt5_=tx(_doc,C_3_2_40);
ad(elt31_,txt5_);
ad(elt30_,elt31_);
ad(elt27_,elt30_);
Element elt32_=el(_doc,LI);
Element elt33_=el(_doc,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_3_2_41));
at(elt33_,attrs19_);
ad(elt32_,elt33_);
Text txt6_=tx(_doc,C_3_2_42);
ad(elt32_,txt6_);
ad(elt27_,elt32_);
ad(elt22_,elt27_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
Element elt34_=el(_doc,LI);
Element elt35_=el(_doc,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_3_2_43));
at(elt35_,attrs20_);
ad(elt34_,elt35_);
Text txt7_=tx(_doc,C_3_2_44);
ad(elt34_,txt7_);
ad(elt20_,elt34_);
ad(elt0_,elt20_);
Element elt36_=el(_doc,BR);
ad(elt0_,elt36_);
Element elt37_=el(_doc,H1);
Element elt38_=el(_doc,C_MESSAGE);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_3_2_45));
at(elt38_,attrs21_);
ad(elt37_,elt38_);
ad(elt0_,elt37_);
Element elt39_=el(_doc,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_3_2_46));
at(elt39_,attrs22_);
ad(elt0_,elt39_);
Text txt8_=tx(_doc,C_3_2_47);
ad(elt0_,txt8_);
Element elt40_=el(_doc,BR);
ad(elt0_,elt40_);
Element elt41_=el(_doc,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_3_2_48));
at(elt41_,attrs23_);
ad(elt0_,elt41_);
Text txt9_=tx(_doc,C_3_2_49);
ad(elt0_,txt9_);
Element elt42_=el(_doc,BR);
ad(elt0_,elt42_);
Element elt43_=el(_doc,C_MESSAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_3_2_50));
at(elt43_,attrs24_);
ad(elt0_,elt43_);
Text txt10_=tx(_doc,C_3_2_51);
ad(elt0_,txt10_);
Element elt44_=el(_doc,BR);
ad(elt0_,elt44_);
Element elt45_=el(_doc,TABLE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(BORDER,C_3_2_52));
at(elt45_,attrs25_);
Element elt46_=el(_doc,CAPTION);
Element elt47_=el(_doc,C_MESSAGE);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(VALUE,C_3_2_53));
at(elt47_,attrs26_);
ad(elt46_,elt47_);
ad(elt45_,elt46_);
Element elt48_=el(_doc,THEAD);
Element elt49_=el(_doc,TR);
Element elt50_=el(_doc,TD);
Element elt51_=el(_doc,C_MESSAGE);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(VALUE,C_3_2_54));
at(elt51_,attrs27_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
Element elt52_=el(_doc,TD);
Element elt53_=el(_doc,C_MESSAGE);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(VALUE,C_3_2_55));
at(elt53_,attrs28_);
ad(elt52_,elt53_);
ad(elt49_,elt52_);
Element elt54_=el(_doc,TD);
Element elt55_=el(_doc,C_MESSAGE);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_3_2_56));
at(elt55_,attrs29_);
ad(elt54_,elt55_);
ad(elt49_,elt54_);
ad(elt48_,elt49_);
ad(elt45_,elt48_);
Element elt56_=el(_doc,TBODY);
Element elt57_=el(_doc,C_FOR);
CustList<Attr> attrs30_=al(3);
attrs30_.add(at(VAR,C_3_2_57));
attrs30_.add(at(LIST,C_3_2_58));
attrs30_.add(at(CLASSNAME,C_3_2_59));
at(elt57_,attrs30_);
Element elt58_=el(_doc,TR);
Element elt59_=el(_doc,TD);
Text txt11_=tx(_doc,C_3_2_60);
ad(elt59_,txt11_);
ad(elt58_,elt59_);
Element elt60_=el(_doc,TD);
Text txt12_=tx(_doc,C_3_2_61);
ad(elt60_,txt12_);
ad(elt58_,elt60_);
Element elt61_=el(_doc,TD);
Text txt13_=tx(_doc,C_3_2_62);
ad(elt61_,txt13_);
ad(elt58_,elt61_);
ad(elt57_,elt58_);
ad(elt56_,elt57_);
ad(elt45_,elt56_);
ad(elt0_,elt45_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_3_2_63));
at(elt0_,attrs0_);
Element elt1_=el(_doc,TABLE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(BORDER,C_3_2_64));
at(elt1_,attrs1_);
Element elt2_=el(_doc,CAPTION);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_3_2_65));
at(elt3_,attrs2_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,THEAD);
Element elt5_=el(_doc,TR);
Element elt6_=el(_doc,TD);
Element elt7_=el(_doc,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_3_2_66));
at(elt7_,attrs3_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc,TD);
Element elt9_=el(_doc,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_3_2_67));
at(elt9_,attrs4_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
Element elt10_=el(_doc,TD);
Element elt11_=el(_doc,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_3_2_68));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt5_,elt10_);
Element elt12_=el(_doc,TD);
Element elt13_=el(_doc,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_3_2_69));
at(elt13_,attrs6_);
ad(elt12_,elt13_);
ad(elt5_,elt12_);
Element elt14_=el(_doc,TD);
Element elt15_=el(_doc,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_3_2_70));
at(elt15_,attrs7_);
ad(elt14_,elt15_);
ad(elt5_,elt14_);
Element elt16_=el(_doc,TD);
Element elt17_=el(_doc,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_3_2_71));
at(elt17_,attrs8_);
ad(elt16_,elt17_);
ad(elt5_,elt16_);
ad(elt4_,elt5_);
ad(elt1_,elt4_);
Element elt18_=el(_doc,TBODY);
Element elt19_=el(_doc,C_FOR);
CustList<Attr> attrs9_=al(3);
attrs9_.add(at(VAR,C_3_2_72));
attrs9_.add(at(LIST,C_3_2_73));
attrs9_.add(at(CLASSNAME,C_3_2_74));
at(elt19_,attrs9_);
Element elt20_=el(_doc,TR);
Element elt21_=el(_doc,TD);
Text txt0_=tx(_doc,C_3_2_75);
ad(elt21_,txt0_);
ad(elt20_,elt21_);
Element elt22_=el(_doc,TD);
Text txt1_=tx(_doc,C_3_2_76);
ad(elt22_,txt1_);
ad(elt20_,elt22_);
Element elt23_=el(_doc,TD);
Text txt2_=tx(_doc,C_3_2_77);
ad(elt23_,txt2_);
ad(elt20_,elt23_);
Element elt24_=el(_doc,TD);
Text txt3_=tx(_doc,C_3_2_78);
ad(elt24_,txt3_);
ad(elt20_,elt24_);
Element elt25_=el(_doc,TD);
Text txt4_=tx(_doc,C_3_2_79);
ad(elt25_,txt4_);
ad(elt20_,elt25_);
Element elt26_=el(_doc,TD);
Text txt5_=tx(_doc,C_3_2_80);
ad(elt26_,txt5_);
ad(elt20_,elt26_);
ad(elt19_,elt20_);
ad(elt18_,elt19_);
ad(elt1_,elt18_);
ad(elt0_,elt1_);
Element elt27_=el(_doc,TABLE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(BORDER,C_3_2_81));
at(elt27_,attrs10_);
Element elt28_=el(_doc,CAPTION);
Element elt29_=el(_doc,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_3_2_82));
at(elt29_,attrs11_);
ad(elt28_,elt29_);
ad(elt27_,elt28_);
Element elt30_=el(_doc,THEAD);
Element elt31_=el(_doc,TR);
Element elt32_=el(_doc,TD);
Element elt33_=el(_doc,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_3_2_83));
at(elt33_,attrs12_);
ad(elt32_,elt33_);
ad(elt31_,elt32_);
Element elt34_=el(_doc,TD);
Element elt35_=el(_doc,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_3_2_84));
at(elt35_,attrs13_);
ad(elt34_,elt35_);
ad(elt31_,elt34_);
Element elt36_=el(_doc,TD);
Element elt37_=el(_doc,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_3_2_85));
at(elt37_,attrs14_);
ad(elt36_,elt37_);
ad(elt31_,elt36_);
Element elt38_=el(_doc,TD);
Element elt39_=el(_doc,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_3_2_86));
at(elt39_,attrs15_);
ad(elt38_,elt39_);
ad(elt31_,elt38_);
Element elt40_=el(_doc,TD);
Element elt41_=el(_doc,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_3_2_87));
at(elt41_,attrs16_);
ad(elt40_,elt41_);
ad(elt31_,elt40_);
Element elt42_=el(_doc,TD);
Element elt43_=el(_doc,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_3_2_88));
at(elt43_,attrs17_);
ad(elt42_,elt43_);
ad(elt31_,elt42_);
ad(elt30_,elt31_);
ad(elt27_,elt30_);
Element elt44_=el(_doc,TBODY);
Element elt45_=el(_doc,C_FOR);
CustList<Attr> attrs18_=al(3);
attrs18_.add(at(VAR,C_3_2_89));
attrs18_.add(at(LIST,C_3_2_90));
attrs18_.add(at(CLASSNAME,C_3_2_91));
at(elt45_,attrs18_);
Element elt46_=el(_doc,TR);
Element elt47_=el(_doc,TD);
Text txt6_=tx(_doc,C_3_2_92);
ad(elt47_,txt6_);
ad(elt46_,elt47_);
Element elt48_=el(_doc,TD);
Text txt7_=tx(_doc,C_3_2_93);
ad(elt48_,txt7_);
ad(elt46_,elt48_);
Element elt49_=el(_doc,TD);
Text txt8_=tx(_doc,C_3_2_94);
ad(elt49_,txt8_);
ad(elt46_,elt49_);
Element elt50_=el(_doc,TD);
Text txt9_=tx(_doc,C_3_2_95);
ad(elt50_,txt9_);
ad(elt46_,elt50_);
Element elt51_=el(_doc,TD);
Text txt10_=tx(_doc,C_3_2_96);
ad(elt51_,txt10_);
ad(elt46_,elt51_);
Element elt52_=el(_doc,TD);
Text txt11_=tx(_doc,C_3_2_97);
ad(elt52_,txt11_);
ad(elt46_,elt52_);
ad(elt45_,elt46_);
ad(elt44_,elt45_);
ad(elt27_,elt44_);
ad(elt0_,elt27_);
Element elt53_=el(_doc,H1);
Element elt54_=el(_doc,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_3_2_98));
at(elt54_,attrs19_);
ad(elt53_,elt54_);
ad(elt0_,elt53_);
Element elt55_=el(_doc,UL);
Element elt56_=el(_doc,C_FOR);
CustList<Attr> attrs20_=al(3);
attrs20_.add(at(VAR,C_3_2_99));
attrs20_.add(at(LIST,C_3_2_100));
attrs20_.add(at(CLASSNAME,C_3_2_101));
at(elt56_,attrs20_);
Element elt57_=el(_doc,LI);
Element elt58_=el(_doc,C_MESSAGE);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_3_2_102));
at(elt58_,attrs21_);
Element elt59_=el(_doc,PARAM);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_3_2_103));
at(elt59_,attrs22_);
ad(elt58_,elt59_);
ad(elt57_,elt58_);
Element elt60_=el(_doc,BR);
ad(elt57_,elt60_);
Element elt61_=el(_doc,UL);
Element elt62_=el(_doc,C_FOR);
CustList<Attr> attrs23_=al(5);
attrs23_.add(at(KEY,C_3_2_104));
attrs23_.add(at(VALUE,C_3_2_105));
attrs23_.add(at(MAP,C_3_2_106));
attrs23_.add(at(KEYCLASSNAME,C_3_2_107));
attrs23_.add(at(VARCLASSNAME,C_3_2_108));
at(elt62_,attrs23_);
Element elt63_=el(_doc,LI);
Text txt12_=tx(_doc,C_3_2_109);
ad(elt63_,txt12_);
ad(elt62_,elt63_);
ad(elt61_,elt62_);
Element elt64_=el(_doc,C_FOR);
CustList<Attr> attrs24_=al(5);
attrs24_.add(at(KEY,C_3_2_110));
attrs24_.add(at(VALUE,C_3_2_111));
attrs24_.add(at(MAP,C_3_2_112));
attrs24_.add(at(KEYCLASSNAME,C_3_2_113));
attrs24_.add(at(VARCLASSNAME,C_3_2_114));
at(elt64_,attrs24_);
Element elt65_=el(_doc,LI);
Text txt13_=tx(_doc,C_3_2_115);
ad(elt65_,txt13_);
ad(elt64_,elt65_);
ad(elt61_,elt64_);
ad(elt57_,elt61_);
ad(elt56_,elt57_);
ad(elt55_,elt56_);
ad(elt0_,elt55_);
Element elt66_=el(_doc,H1);
Element elt67_=el(_doc,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_3_2_116));
at(elt67_,attrs25_);
ad(elt66_,elt67_);
ad(elt0_,elt66_);
Element elt68_=el(_doc,TABLE);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(BORDER,C_3_2_117));
at(elt68_,attrs26_);
Element elt69_=el(_doc,CAPTION);
Element elt70_=el(_doc,C_MESSAGE);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(VALUE,C_3_2_118));
at(elt70_,attrs27_);
ad(elt69_,elt70_);
ad(elt68_,elt69_);
Element elt71_=el(_doc,THEAD);
Element elt72_=el(_doc,TR);
Element elt73_=el(_doc,TD);
Element elt74_=el(_doc,C_MESSAGE);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(VALUE,C_3_2_119));
at(elt74_,attrs28_);
ad(elt73_,elt74_);
ad(elt72_,elt73_);
Element elt75_=el(_doc,TD);
Element elt76_=el(_doc,C_MESSAGE);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_3_2_120));
at(elt76_,attrs29_);
ad(elt75_,elt76_);
ad(elt72_,elt75_);
ad(elt71_,elt72_);
ad(elt68_,elt71_);
Element elt77_=el(_doc,TBODY);
Element elt78_=el(_doc,C_FOR);
CustList<Attr> attrs30_=al(3);
attrs30_.add(at(VAR,C_3_2_121));
attrs30_.add(at(LIST,C_3_2_122));
attrs30_.add(at(CLASSNAME,C_3_2_123));
at(elt78_,attrs30_);
Element elt79_=el(_doc,TR);
Element elt80_=el(_doc,TD);
Text txt14_=tx(_doc,C_3_2_124);
ad(elt80_,txt14_);
ad(elt79_,elt80_);
Element elt81_=el(_doc,TD);
Text txt15_=tx(_doc,C_3_2_125);
ad(elt81_,txt15_);
ad(elt79_,elt81_);
ad(elt78_,elt79_);
ad(elt77_,elt78_);
ad(elt68_,elt77_);
ad(elt0_,elt68_);
ad(_body,elt0_);
}
}
