package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageGameDifficulty extends PageCardsCommon{
private static final String C_P_210_0="javahtml";
private static final String C_P_210_1="difficulty";
private static final String C_P_210_2="msg_difficulty,title";
private static final String C_P_210_3=PkScriptPages.REN_ADD_WEB_GAME_CSS_DIFFICULTY_CSS;
private static final String C_P_210_4="stylesheet";
private static final String C_P_210_5="text/css";
private static final String C_P_210_6="$change";
private static final String C_P_210_7="changingDiff";
//private static final String C_P_210_8="msg_difficulty,win_pts";
//private static final String C_P_210_9="";
//private static final String C_P_210_10="winPointsFight";
//private static final String C_P_210_11="diffWinningExpPtsFight";
//private static final String C_P_210_12="";
//private static final String C_P_210_13="diffWinningExpPtsFight";
//private static final String C_P_210_14="msg_difficulty,allow_catching_ko";
//private static final String C_P_210_15="allowCatchingKo";
//private static final String C_P_210_16="allowCatchingKo";
//private static final String C_P_210_17="checkbox";
//private static final String C_P_210_18="msg_difficulty,allow_switch_places";
//private static final String C_P_210_19="allowedSwitchPlacesEndRound";
//private static final String C_P_210_20="allowedSwitchPlacesEndRound";
//private static final String C_P_210_21="checkbox";
//private static final String C_P_210_22="msg_difficulty,win_trainer_exp";
//private static final String C_P_210_23="rate_validator";
//private static final String C_P_210_24="winTrainerExp";
//private static final String C_P_210_25="winTrainerExpRate";
//private static final String C_P_210_26="winTrainerExp";
//private static final String C_P_210_27="text";
//private static final String C_P_210_28="winTrainerExpRate";
//private static final String C_P_210_29="errormessage";
//private static final String C_P_210_30="msg_difficulty,rate_issue";
//private static final String C_P_210_31="msg_difficulty,winning_exp_pts_fight";
//private static final String C_P_210_32="rate_validator";
//private static final String C_P_210_33="rateWinningExpPtsFight";
//private static final String C_P_210_34="winExpRateFight";
//private static final String C_P_210_35="rateWinningExpPtsFight";
//private static final String C_P_210_36="text";
//private static final String C_P_210_37="winExpRateFight";
//private static final String C_P_210_38="errormessage";
//private static final String C_P_210_39="msg_difficulty,rate_issue";
//private static final String C_P_210_40="msg_difficulty,end_fight";
//private static final String C_P_210_41="endFightIfOneTeamKo";
//private static final String C_P_210_42="endFightIfOneTeamKo";
//private static final String C_P_210_43="checkbox";
//private static final String C_P_210_44="msg_difficulty,iv_player";
//private static final String C_P_210_45="short_validator";
//private static final String C_P_210_46="ivPlayer";
//private static final String C_P_210_47="ivPlayerId";
//private static final String C_P_210_48="ivPlayer";
//private static final String C_P_210_49="text";
//private static final String C_P_210_50="ivPlayerId";
//private static final String C_P_210_51="errormessage";
//private static final String C_P_210_52="msg_difficulty,short_issue";
//private static final String C_P_210_53="msg_difficulty,iv_foe";
//private static final String C_P_210_54="short_validator";
//private static final String C_P_210_55="ivFoe";
//private static final String C_P_210_56="ivFoeId";
//private static final String C_P_210_57="ivFoe";
//private static final String C_P_210_58="text";
//private static final String C_P_210_59="ivFoeId";
//private static final String C_P_210_60="errormessage";
//private static final String C_P_210_61="msg_difficulty,short_issue";
//private static final String C_P_210_62="msg_difficulty,rate_win_money_base";
//private static final String C_P_210_63="rate_validator";
//private static final String C_P_210_64="rateWinMoneyBase";
//private static final String C_P_210_65="rateWinMoneyBaseId";
//private static final String C_P_210_66="rateWinMoneyBase";
//private static final String C_P_210_67="text";
//private static final String C_P_210_68="rateWinMoneyBaseId";
//private static final String C_P_210_69="errormessage";
//private static final String C_P_210_70="msg_difficulty,rate_issue";
//private static final String C_P_210_71="msg_difficulty,rate_win_money_loose";
//private static final String C_P_210_72="rate_validator";
//private static final String C_P_210_73="rateLooseMoneyWin";
//private static final String C_P_210_74="rateLooseMoneyWinId";
//private static final String C_P_210_75="rateLooseMoneyWin";
//private static final String C_P_210_76="text";
//private static final String C_P_210_77="rateLooseMoneyWinId";
//private static final String C_P_210_78="errormessage";
//private static final String C_P_210_79="msg_difficulty,rate_issue";
//private static final String C_P_210_80="msg_difficulty,restored_moves";
//private static final String C_P_210_81="restoredMovesEndFight";
//private static final String C_P_210_82="restoredMovesEndFight";
//private static final String C_P_210_83="checkbox";
//private static final String C_P_210_84="msg_difficulty,closing";
//private static final String C_P_210_85="enabledClosing";
//private static final String C_P_210_86="enabledClosing";
//private static final String C_P_210_87="checkbox";
//private static final String C_P_210_88="msg_difficulty,random_wild";
//private static final String C_P_210_89="randomWildFight";
//private static final String C_P_210_90="randomWildFight";
//private static final String C_P_210_91="checkbox";
//private static final String C_P_210_92="msg_difficulty,flee";
//private static final String C_P_210_93="stillPossibleFlee";
//private static final String C_P_210_94="stillPossibleFlee";
//private static final String C_P_210_95="checkbox";
//private static final String C_P_210_96="msg_difficulty,skip_learn";
//private static final String C_P_210_97="skipLearningMovesWhileNotGrowingLevel";
//private static final String C_P_210_98="skipLearningMovesWhileNotGrowingLevel";
//private static final String C_P_210_99="checkbox";
//private static final String C_P_210_100="msg_difficulty,law_choice_player";
//private static final String C_P_210_101="";
//private static final String C_P_210_102="damageRates";
//private static final String C_P_210_103="damageRatePlayer";
//private static final String C_P_210_104="";
//private static final String C_P_210_105="damageRatePlayer";
//private static final String C_P_210_106="msg_difficulty,rate_damage_ev";
//private static final String C_P_210_107="msg_difficulty,rate_damage";
//private static final String C_P_210_108="e";
//private static final String C_P_210_109="damageRatePlayerTable";
//private static final String C_P_210_110="r";
//private static final String C_P_210_111="r";
//private static final String C_P_210_112="r";
//private static final String C_P_210_113="{e}";
//private static final String C_P_210_114="{r}";
//private static final String C_P_210_115="msg_difficulty,law_choice_foe";
//private static final String C_P_210_116="";
//private static final String C_P_210_117="damageRates";
//private static final String C_P_210_118="damageRateLawFoe";
//private static final String C_P_210_119="";
//private static final String C_P_210_120="damageRateLawFoe";
//private static final String C_P_210_121="msg_difficulty,rate_damage_ev";
//private static final String C_P_210_122="msg_difficulty,rate_damage";
//private static final String C_P_210_123="e";
//private static final String C_P_210_124="damageRateFoeTable";
//private static final String C_P_210_125="r";
//private static final String C_P_210_126="r";
//private static final String C_P_210_127="r";
//private static final String C_P_210_128="{e}";
//private static final String C_P_210_129="{r}";
private static final String C_P_210_131="msg_difficulty,ok";
private static final String C_P_143_277="aiki.beans";
private static final String C_P_143_278="DiCo";
private static final String C_P_143_279="$intern.c=c";
private PageGameDifficulty(){}
static Document build(){
FullDocument docDiff_ = DocumentBuilder.newXmlDocument(4);
build(docDiff_);
return docDiff_;
}
static void build(Document _doc115){
Element elt0_=el(_doc115,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_210_0));
attrs0_.add(at(C_BEAN,C_P_210_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc115,HEAD);
Element elt2_=el(_doc115,TITLE);
Element elt3_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_210_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc115,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_210_3));
attrs2_.add(at(REL,C_P_210_4));
attrs2_.add(at(TYPE,C_P_210_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc115,BODY);
build0(elt5_,_doc115);
ad(elt0_,elt5_);
_doc115.appendChild(elt0_);
}
static void build0(Element _body,Document _doc115){
Element elt0_=el(_doc115,FORM);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_210_6));
attrs0_.add(at(NAME,C_P_210_7));
at(elt0_,attrs0_);
/*Element elt1_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_210_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc115,C_SELECT);
CustList<Attr> attrs2_=al(5);
attrs2_.add(at(DEFAULT,C_P_210_9));
attrs2_.add(at(MAP,C_P_210_10));
attrs2_.add(at(NAME,C_P_210_11));
attrs2_.add(at(UPDATE,C_P_210_12));
attrs2_.add(at(VARVALUE,C_P_210_13));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc115,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_210_14));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
Element elt5_=el(_doc115,INPUT);
CustList<Attr> attrs4_=al(3);
attrs4_.add(at(C_VARVALUE,C_P_210_15));
attrs4_.add(at(NAME,C_P_210_16));
attrs4_.add(at(TYPE,C_P_210_17));
at(elt5_,attrs4_);
ad(elt0_,elt5_);
Element elt6_=el(_doc115,BR);
ad(elt0_,elt6_);
Element elt7_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_210_18));
at(elt7_,attrs5_);
ad(elt0_,elt7_);
Element elt8_=el(_doc115,INPUT);
CustList<Attr> attrs6_=al(3);
attrs6_.add(at(C_VARVALUE,C_P_210_19));
attrs6_.add(at(NAME,C_P_210_20));
attrs6_.add(at(TYPE,C_P_210_21));
at(elt8_,attrs6_);
ad(elt0_,elt8_);
Element elt9_=el(_doc115,BR);
ad(elt0_,elt9_);
Element elt10_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_210_22));
at(elt10_,attrs7_);
ad(elt0_,elt10_);
Element elt11_=el(_doc115,INPUT);
CustList<Attr> attrs8_=al(5);
attrs8_.add(at(C_VALIDATOR,C_P_210_23));
attrs8_.add(at(C_VARVALUE,C_P_210_24));
attrs8_.add(at(ID,C_P_210_25));
attrs8_.add(at(NAME,C_P_210_26));
attrs8_.add(at(TYPE,C_P_210_27));
at(elt11_,attrs8_);
ad(elt0_,elt11_);
Element elt12_=el(_doc115,SPAN);
CustList<Attr> attrs9_=al(3);
attrs9_.add(at(C_FOR,C_P_210_28));
attrs9_.add(at(CLASS,C_P_210_29));
attrs9_.add(at(C_VALUEMESSAGE,C_P_210_30));
at(elt12_,attrs9_);
ad(elt0_,elt12_);
Element elt13_=el(_doc115,BR);
ad(elt0_,elt13_);
Element elt14_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_210_31));
at(elt14_,attrs10_);
ad(elt0_,elt14_);
Element elt15_=el(_doc115,INPUT);
CustList<Attr> attrs11_=al(5);
attrs11_.add(at(C_VALIDATOR,C_P_210_32));
attrs11_.add(at(C_VARVALUE,C_P_210_33));
attrs11_.add(at(ID,C_P_210_34));
attrs11_.add(at(NAME,C_P_210_35));
attrs11_.add(at(TYPE,C_P_210_36));
at(elt15_,attrs11_);
ad(elt0_,elt15_);
Element elt16_=el(_doc115,SPAN);
CustList<Attr> attrs12_=al(3);
attrs12_.add(at(C_FOR,C_P_210_37));
attrs12_.add(at(CLASS,C_P_210_38));
attrs12_.add(at(C_VALUEMESSAGE,C_P_210_39));
at(elt16_,attrs12_);
ad(elt0_,elt16_);
Element elt17_=el(_doc115,BR);
ad(elt0_,elt17_);
Element elt18_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_210_40));
at(elt18_,attrs13_);
ad(elt0_,elt18_);
Element elt19_=el(_doc115,INPUT);
CustList<Attr> attrs14_=al(3);
attrs14_.add(at(C_VARVALUE,C_P_210_41));
attrs14_.add(at(NAME,C_P_210_42));
attrs14_.add(at(TYPE,C_P_210_43));
at(elt19_,attrs14_);
ad(elt0_,elt19_);
Element elt20_=el(_doc115,BR);
ad(elt0_,elt20_);
Element elt21_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_210_44));
at(elt21_,attrs15_);
ad(elt0_,elt21_);
Element elt22_=el(_doc115,INPUT);
CustList<Attr> attrs16_=al(5);
attrs16_.add(at(C_VALIDATOR,C_P_210_45));
attrs16_.add(at(C_VARVALUE,C_P_210_46));
attrs16_.add(at(ID,C_P_210_47));
attrs16_.add(at(NAME,C_P_210_48));
attrs16_.add(at(TYPE,C_P_210_49));
at(elt22_,attrs16_);
ad(elt0_,elt22_);
Element elt23_=el(_doc115,SPAN);
CustList<Attr> attrs17_=al(3);
attrs17_.add(at(C_FOR,C_P_210_50));
attrs17_.add(at(CLASS,C_P_210_51));
attrs17_.add(at(C_VALUEMESSAGE,C_P_210_52));
at(elt23_,attrs17_);
ad(elt0_,elt23_);
Element elt24_=el(_doc115,BR);
ad(elt0_,elt24_);
Element elt25_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_210_53));
at(elt25_,attrs18_);
ad(elt0_,elt25_);
Element elt26_=el(_doc115,INPUT);
CustList<Attr> attrs19_=al(5);
attrs19_.add(at(C_VALIDATOR,C_P_210_54));
attrs19_.add(at(C_VARVALUE,C_P_210_55));
attrs19_.add(at(ID,C_P_210_56));
attrs19_.add(at(NAME,C_P_210_57));
attrs19_.add(at(TYPE,C_P_210_58));
at(elt26_,attrs19_);
ad(elt0_,elt26_);
Element elt27_=el(_doc115,SPAN);
CustList<Attr> attrs20_=al(3);
attrs20_.add(at(C_FOR,C_P_210_59));
attrs20_.add(at(CLASS,C_P_210_60));
attrs20_.add(at(C_VALUEMESSAGE,C_P_210_61));
at(elt27_,attrs20_);
ad(elt0_,elt27_);
Element elt28_=el(_doc115,BR);
ad(elt0_,elt28_);
Element elt29_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_P_210_62));
at(elt29_,attrs21_);
ad(elt0_,elt29_);
Element elt30_=el(_doc115,INPUT);
CustList<Attr> attrs22_=al(5);
attrs22_.add(at(C_VALIDATOR,C_P_210_63));
attrs22_.add(at(C_VARVALUE,C_P_210_64));
attrs22_.add(at(ID,C_P_210_65));
attrs22_.add(at(NAME,C_P_210_66));
attrs22_.add(at(TYPE,C_P_210_67));
at(elt30_,attrs22_);
ad(elt0_,elt30_);
Element elt31_=el(_doc115,SPAN);
CustList<Attr> attrs23_=al(3);
attrs23_.add(at(C_FOR,C_P_210_68));
attrs23_.add(at(CLASS,C_P_210_69));
attrs23_.add(at(C_VALUEMESSAGE,C_P_210_70));
at(elt31_,attrs23_);
ad(elt0_,elt31_);
Element elt32_=el(_doc115,BR);
ad(elt0_,elt32_);
Element elt33_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_P_210_71));
at(elt33_,attrs24_);
ad(elt0_,elt33_);
Element elt34_=el(_doc115,INPUT);
CustList<Attr> attrs25_=al(5);
attrs25_.add(at(C_VALIDATOR,C_P_210_72));
attrs25_.add(at(C_VARVALUE,C_P_210_73));
attrs25_.add(at(ID,C_P_210_74));
attrs25_.add(at(NAME,C_P_210_75));
attrs25_.add(at(TYPE,C_P_210_76));
at(elt34_,attrs25_);
ad(elt0_,elt34_);
Element elt35_=el(_doc115,SPAN);
CustList<Attr> attrs26_=al(3);
attrs26_.add(at(C_FOR,C_P_210_77));
attrs26_.add(at(CLASS,C_P_210_78));
attrs26_.add(at(C_VALUEMESSAGE,C_P_210_79));
at(elt35_,attrs26_);
ad(elt0_,elt35_);
Element elt36_=el(_doc115,BR);
ad(elt0_,elt36_);
Element elt37_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(VALUE,C_P_210_80));
at(elt37_,attrs27_);
ad(elt0_,elt37_);
Element elt38_=el(_doc115,INPUT);
CustList<Attr> attrs28_=al(3);
attrs28_.add(at(C_VARVALUE,C_P_210_81));
attrs28_.add(at(NAME,C_P_210_82));
attrs28_.add(at(TYPE,C_P_210_83));
at(elt38_,attrs28_);
ad(elt0_,elt38_);
Element elt39_=el(_doc115,BR);
ad(elt0_,elt39_);
Element elt40_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_P_210_84));
at(elt40_,attrs29_);
ad(elt0_,elt40_);
Element elt41_=el(_doc115,INPUT);
CustList<Attr> attrs30_=al(3);
attrs30_.add(at(C_VARVALUE,C_P_210_85));
attrs30_.add(at(NAME,C_P_210_86));
attrs30_.add(at(TYPE,C_P_210_87));
at(elt41_,attrs30_);
ad(elt0_,elt41_);
Element elt42_=el(_doc115,BR);
ad(elt0_,elt42_);
Element elt43_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(VALUE,C_P_210_88));
at(elt43_,attrs31_);
ad(elt0_,elt43_);
Element elt44_=el(_doc115,INPUT);
CustList<Attr> attrs32_=al(3);
attrs32_.add(at(C_VARVALUE,C_P_210_89));
attrs32_.add(at(NAME,C_P_210_90));
attrs32_.add(at(TYPE,C_P_210_91));
at(elt44_,attrs32_);
ad(elt0_,elt44_);
Element elt45_=el(_doc115,BR);
ad(elt0_,elt45_);
Element elt46_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(VALUE,C_P_210_92));
at(elt46_,attrs33_);
ad(elt0_,elt46_);
Element elt47_=el(_doc115,INPUT);
CustList<Attr> attrs34_=al(3);
attrs34_.add(at(C_VARVALUE,C_P_210_93));
attrs34_.add(at(NAME,C_P_210_94));
attrs34_.add(at(TYPE,C_P_210_95));
at(elt47_,attrs34_);
ad(elt0_,elt47_);
Element elt48_=el(_doc115,BR);
ad(elt0_,elt48_);
Element elt49_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs35_=al(1);
attrs35_.add(at(VALUE,C_P_210_96));
at(elt49_,attrs35_);
ad(elt0_,elt49_);
Element elt50_=el(_doc115,INPUT);
CustList<Attr> attrs36_=al(3);
attrs36_.add(at(C_VARVALUE,C_P_210_97));
attrs36_.add(at(NAME,C_P_210_98));
attrs36_.add(at(TYPE,C_P_210_99));
at(elt50_,attrs36_);
ad(elt0_,elt50_);
Element elt51_=el(_doc115,BR);
ad(elt0_,elt51_);
Element elt52_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs37_=al(1);
attrs37_.add(at(VALUE,C_P_210_100));
at(elt52_,attrs37_);
ad(elt0_,elt52_);
Element elt53_=el(_doc115,C_SELECT);
CustList<Attr> attrs38_=al(5);
attrs38_.add(at(DEFAULT,C_P_210_101));
attrs38_.add(at(MAP,C_P_210_102));
attrs38_.add(at(NAME,C_P_210_103));
attrs38_.add(at(UPDATE,C_P_210_104));
attrs38_.add(at(VARVALUE,C_P_210_105));
at(elt53_,attrs38_);
ad(elt0_,elt53_);
Element elt54_=el(_doc115,BR);
ad(elt0_,elt54_);
Element elt55_=el(_doc115,TABLE);
Element elt56_=el(_doc115,THEAD);
Element elt57_=el(_doc115,TR);
Element elt58_=el(_doc115,TH);
Element elt59_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs39_=al(1);
attrs39_.add(at(VALUE,C_P_210_106));
at(elt59_,attrs39_);
ad(elt58_,elt59_);
ad(elt57_,elt58_);
Element elt60_=el(_doc115,TH);
Element elt61_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(VALUE,C_P_210_107));
at(elt61_,attrs40_);
ad(elt60_,elt61_);
ad(elt57_,elt60_);
ad(elt56_,elt57_);
ad(elt55_,elt56_);
Element elt62_=el(_doc115,TBODY);
Element elt63_=el(_doc115,C_FOR);
CustList<Attr> attrs41_=al(5);
attrs41_.add(at(KEY,C_P_210_108));
attrs41_.add(at(MAP,C_P_210_109));
attrs41_.add(at(VALUE,C_P_210_110));
attrs41_.add(at(KEYCLASSNAME,C_P_210_111));
attrs41_.add(at(VARCLASSNAME,C_P_210_112));
at(elt63_,attrs41_);
Element elt64_=el(_doc115,TR);
Element elt65_=el(_doc115,TD);
Text txt0_=tx(_doc115,C_P_210_113);
ad(elt65_,txt0_);
ad(elt64_,elt65_);
Element elt66_=el(_doc115,TD);
Text txt1_=tx(_doc115,C_P_210_114);
ad(elt66_,txt1_);
ad(elt64_,elt66_);
ad(elt63_,elt64_);
ad(elt62_,elt63_);
ad(elt55_,elt62_);
ad(elt0_,elt55_);
Element elt67_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs42_=al(1);
attrs42_.add(at(VALUE,C_P_210_115));
at(elt67_,attrs42_);
ad(elt0_,elt67_);
Element elt68_=el(_doc115,C_SELECT);
CustList<Attr> attrs43_=al(5);
attrs43_.add(at(DEFAULT,C_P_210_116));
attrs43_.add(at(MAP,C_P_210_117));
attrs43_.add(at(NAME,C_P_210_118));
attrs43_.add(at(UPDATE,C_P_210_119));
attrs43_.add(at(VARVALUE,C_P_210_120));
at(elt68_,attrs43_);
ad(elt0_,elt68_);
Element elt69_=el(_doc115,BR);
ad(elt0_,elt69_);
Element elt70_=el(_doc115,TABLE);
Element elt71_=el(_doc115,THEAD);
Element elt72_=el(_doc115,TR);
Element elt73_=el(_doc115,TH);
Element elt74_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs44_=al(1);
attrs44_.add(at(VALUE,C_P_210_121));
at(elt74_,attrs44_);
ad(elt73_,elt74_);
ad(elt72_,elt73_);
Element elt75_=el(_doc115,TH);
Element elt76_=el(_doc115,C_MESSAGE);
CustList<Attr> attrs45_=al(1);
attrs45_.add(at(VALUE,C_P_210_122));
at(elt76_,attrs45_);
ad(elt75_,elt76_);
ad(elt72_,elt75_);
ad(elt71_,elt72_);
ad(elt70_,elt71_);
Element elt77_=el(_doc115,TBODY);
Element elt78_=el(_doc115,C_FOR);
CustList<Attr> attrs46_=al(5);
attrs46_.add(at(KEY,C_P_210_123));
attrs46_.add(at(MAP,C_P_210_124));
attrs46_.add(at(VALUE,C_P_210_125));
attrs46_.add(at(KEYCLASSNAME,C_P_210_126));
attrs46_.add(at(VARCLASSNAME,C_P_210_127));
at(elt78_,attrs46_);
Element elt79_=el(_doc115,TR);
Element elt80_=el(_doc115,TD);
Text txt2_=tx(_doc115,C_P_210_128);
ad(elt80_,txt2_);
ad(elt79_,elt80_);
Element elt81_=el(_doc115,TD);
Text txt3_=tx(_doc115,C_P_210_129);
ad(elt81_,txt3_);
ad(elt79_,elt81_);
ad(elt78_,elt79_);
ad(elt77_,elt78_);
ad(elt70_,elt77_);
ad(elt0_,elt70_);*/
Element elt28_=el(_doc115,C_IMPORT);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(PAGE,DIFF));
at(elt28_,attrs23_);
Element elt29_=el(_doc115,C_PACKAGE);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(NAME,C_P_143_277));
at(elt29_,attrs24_);
Element elt30_=el(_doc115,C_CLASS);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(NAME,C_P_143_278));
at(elt30_,attrs25_);
Element elt31_=el(_doc115,C_FIELD);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(PREPARE,C_P_143_279));
at(elt31_,attrs26_);
ad(elt30_,elt31_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
ad(elt0_,elt28_);
Element elt82_=el(_doc115,C_SUBMIT);
CustList<Attr> attrs47_=al(1);
attrs47_.add(at(MESSAGE,C_P_210_131));
at(elt82_,attrs47_);
ad(elt0_,elt82_);
ad(_body,elt0_);
}
}
