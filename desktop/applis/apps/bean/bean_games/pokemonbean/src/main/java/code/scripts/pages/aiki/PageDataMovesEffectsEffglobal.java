package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffglobal extends PageCardsCommon{
private static final String C_P_157_0="javahtml";
private static final String C_P_157_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_GLOBAL;
private static final String C_P_157_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_157_3="stylesheet";
private static final String C_P_157_4="text/css";
private static final String C_P_157_5="msg_effglobal,effect";
private static final String C_P_157_6="effectBean";
private static final String C_P_157_7="aiki.beans.moves.effects";
private static final String C_P_157_8="EffectBean";
private static final String C_P_157_9="$intern.index=index";
private static final String C_P_157_10="$intern.move=move";
private static final String C_P_157_11="weather";
private static final String C_P_157_12="msg_effglobal,is_weather";
private static final String C_P_157_13="!weather";
private static final String C_P_157_14="msg_effglobal,is_not_weather";
private static final String C_P_157_15="canceledIfUsed";
private static final String C_P_157_16="msg_effglobal,cancel_reuse";
private static final String C_P_157_17="reverseOrderOfSortBySpeed";
private static final String C_P_157_18="msg_effglobal,reverse_speed";
private static final String C_P_157_19="unusableItem";
private static final String C_P_157_20="msg_effglobal,unusable_item";
private static final String C_P_157_21="puttingKo";
private static final String C_P_157_22="msg_effglobal,putting_ko";
private static final String C_P_157_23="!multAccuracy.isZero()";
private static final String C_P_157_24="msg_effglobal,mult_acc";
private static final String C_P_157_25="multAccuracy";
private static final String C_P_157_26="!damageEndRound.isZero()";
private static final String C_P_157_27="msg_effglobal,damage_end_round";
private static final String C_P_157_28="damageEndRound";
private static final String C_P_157_29="!healingEndRoundGround.isZero()";
private static final String C_P_157_30="msg_effglobal,healing_end_round_ground";
private static final String C_P_157_31="healingEndRoundGround";
private static final String C_P_157_32="!healingEndRound.isZero()";
private static final String C_P_157_33="msg_effglobal,healing_end_round";
private static final String C_P_157_34="healingEndRound";
private static final String C_P_157_35="!multEffectLovingAlly.isZero()";
private static final String C_P_157_36="msg_effglobal,mult_love";
private static final String C_P_157_37="multEffectLovingAlly";
private static final String C_P_157_38="!preventStatus.isEmpty()";
private static final String C_P_157_39="msg_effglobal,forbid_status";
private static final String C_P_157_40="preventStatus";
private static final String C_P_157_41="s";
private static final String C_P_157_42="java.lang.Object";
private static final String C_P_157_43="$clickPreventedStatus({index},{([s])})";
private static final String C_P_157_44="";
private static final String C_P_157_45="{getTrPreventedStatus(([s]))}";
private static final String C_P_157_46="!immuneTypes.isEmpty()";
private static final String C_P_157_47="msg_effglobal,immune_types";
private static final String C_P_157_48="immuneTypes";
private static final String C_P_157_49="t";
private static final String C_P_157_50="java.lang.String";
private static final String C_P_157_51="{t}";
private static final String C_P_157_52="!efficiencyMoves.isEmpty()";
private static final String C_P_157_53="msg_effglobal,efficiency_table";
private static final String C_P_157_54="msg_effglobal,damage_type";
private static final String C_P_157_55="msg_effglobal,pokemon_type";
private static final String C_P_157_56="msg_effglobal,efficiency";
private static final String C_P_157_57="c";
private static final String C_P_157_58="aiki.fight.util.TypesDuo";
private static final String C_P_157_59="efficiencyMoves";
private static final String C_P_157_60="r";
private static final String C_P_157_61="r";
private static final String C_P_157_62="{c.getDamageType()}";
private static final String C_P_157_63="{c.getPokemonType()}";
private static final String C_P_157_64="{r}";
private static final String C_P_157_65="!disableImmuAgainstTypes.isEmpty()";
private static final String C_P_157_66="msg_effglobal,disable_immu_types";
private static final String C_P_157_67="disableImmuAgainstTypes";
private static final String C_P_157_68="t";
private static final String C_P_157_69="java.lang.String";
private static final String C_P_157_70="{t}";
private static final String C_P_157_71="!cancelProtectingAbilities.isEmpty()";
private static final String C_P_157_72="msg_effglobal,disable_immu_abilities";
private static final String C_P_157_73="cancelProtectingAbilities";
private static final String C_P_157_74="t";
private static final String C_P_157_75="java.lang.Object";
private static final String C_P_157_76="$clickCancelledAbility({index},{([t])})";
private static final String C_P_157_77="";
private static final String C_P_157_78="{getTrCancelledAbility(([t]))}";
private static final String C_P_157_79="!unusableMoves.isEmpty()";
private static final String C_P_157_80="msg_effglobal,unusable_moves";
private static final String C_P_157_81="unusableMoves";
private static final String C_P_157_82="t";
private static final String C_P_157_83="$clickUnusableMove({index},{([t])})";
private static final String C_P_157_84="";
private static final String C_P_157_85="{getTrUnusableMoves(([t]))}";
private static final String C_P_157_86="!cancelEffects.isEmpty()";
private static final String C_P_157_87="msg_effglobal,cancel_effects";
private static final String C_P_157_88="cancelEffects";
private static final String C_P_157_89="t";
private static final String C_P_157_90="$clickCancelledEffect({index},{([t])})";
private static final String C_P_157_91="";
private static final String C_P_157_92="{getTrCancelledEffect(([t]))}";
private static final String C_P_157_93="!multPowerMoves.isEmpty()";
private static final String C_P_157_94="msg_effglobal,mult_power_move";
private static final String C_P_157_95="msg_effglobal,move";
private static final String C_P_157_96="msg_effglobal,rate_damage";
private static final String C_P_157_97="c";
private static final String C_P_157_98="multPowerMoves";
private static final String C_P_157_99="r";
private static final String C_P_157_100="java.lang.Object";
private static final String C_P_157_101="r";
private static final String C_P_157_102="$clickMultMovePower({index},{([c])})";
private static final String C_P_157_103="";
private static final String C_P_157_104="{getTrMultMovePower(([c]))}";
private static final String C_P_157_105="{r}";
private static final String C_P_157_106="!multDamageTypesMoves.isEmpty()";
private static final String C_P_157_107="msg_effglobal,mult_power_type";
private static final String C_P_157_108="msg_effglobal,move_type";
private static final String C_P_157_109="msg_effglobal,rate_damage";
private static final String C_P_157_110="c";
private static final String C_P_157_111="multDamageTypesMoves";
private static final String C_P_157_112="r";
private static final String C_P_157_113="java.lang.String";
private static final String C_P_157_114="r";
private static final String C_P_157_115="{c}";
private static final String C_P_157_116="{r}";
private static final String C_P_157_117="!cancelChgtStat.isEmpty()";
private static final String C_P_157_118="msg_effglobal,cancel_chgt_statis";
private static final String C_P_157_119="cancelChgtStat";
private static final String C_P_157_120="t";
private static final String C_P_157_121="java.lang.String";
private static final String C_P_157_122="{t}";
private static final String C_P_157_123="!isEmpty(invokedMoveTerrain)";
private static final String C_P_157_124="msg_effglobal,invoked_move";
private static final String C_P_157_124_0="msg_effglobal,invoked_move_env";
private static final String C_P_157_125_LK="$clickInvokedMove({index})";
private static final String C_P_157_125="{getTrInvokedMoveTerrain()}";
private static final String C_P_157_126="invokingMoves";
private static final String C_P_157_127="t";
private static final String C_P_157_128="$clickInvokingMove({([t])})";
private static final String C_P_157_129="";
private static final String C_P_157_130="{getTrInvokingMove(([t]))}";
private static final String C_P_157_131="!changedTypesTerrain.isEmpty()";
private static final String C_P_157_132="msg_effglobal,changing_type_invoked";
private static final String C_P_157_133="changedTypesTerrain";
private static final String C_P_157_134="t";
private static final String C_P_157_135="java.lang.String";
private static final String C_P_157_136="{t}";
private static final String C_P_157_137="msg_effglobal,changing_type_invoking";
private static final String C_P_157_138="invokingMovesChangingTypes";
private static final String C_P_157_139="t";
private static final String C_P_157_140="java.lang.Object";
private static final String C_P_157_141="$clickInvokingMoveTypes({([t])})";
private static final String C_P_157_142="";
private static final String C_P_157_143="{getTrInvokingMoveTypes(([t]))}";
private static final String C_P_157_144="!multStatIfContainsType.isEmpty()";
private static final String C_P_157_145="msg_effglobal,mult_stat_type";
private static final String C_P_157_146="msg_effglobal,statistic";
private static final String C_P_157_147="msg_effglobal,pokemon_type_stat";
private static final String C_P_157_148="msg_effglobal,rate_pokemon_statistic";
private static final String C_P_157_149="c";
private static final String C_P_157_150="multStatIfContainsType";
private static final String C_P_157_151="r";
private static final String C_P_157_152="java.lang.Object";
private static final String C_P_157_153="r";
private static final String C_P_157_154="{getTrMultStatIfDamgeTypeFirst(([c]))}";
private static final String C_P_157_155="{getTrMultStatIfDamgeTypeSecond(([c]))}";
private static final String C_P_157_156="{r}";
private static final String C_P_157_157="!multDamagePrepaRound.isEmpty()";
private static final String C_P_157_158="msg_effglobal,mult_damage_type";
private static final String C_P_157_159="movesUsedByTargetedFighters";
private static final String C_P_157_160="t";
private static final String C_P_157_161="$clickMovesTarget({index},{([t])})";
private static final String C_P_157_162="";
private static final String C_P_157_163="{getTrMovesTarget(([t]))}";
private static final String C_P_157_164="msg_effglobal,damage_type";
private static final String C_P_157_165="msg_effglobal,rate";
private static final String C_P_157_166="c";
private static final String C_P_157_167="multDamagePrepaRound";
private static final String C_P_157_168="r";
private static final String C_P_157_169="java.lang.String";
private static final String C_P_157_170="r";
private static final String C_P_157_171="{c}";
private static final String C_P_157_172="{r}";
private PageDataMovesEffectsEffglobal(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc62){
Element elt0_=el(_doc62,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_157_0));
attrs0_.add(at(C_BEAN,C_P_157_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc62,HEAD);
Element elt2_=el(_doc62,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_157_2));
attrs1_.add(at(REL,C_P_157_3));
attrs1_.add(at(TYPE,C_P_157_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc62,BODY);
build0(elt3_,_doc62);
ad(elt0_,elt3_);
_doc62.appendChild(elt0_);
}
static void build0(Element _body,Document _doc62){
Element elt0_=el(_doc62,P);
Element elt1_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_157_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc62,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_157_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc62,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_157_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc62,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_157_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc62,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_157_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc62,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_157_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc62,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_157_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_157_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc62,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_157_13));
at(elt9_,attrs8_);
Element elt10_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_157_14));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
Element elt11_=el(_doc62,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_157_15));
at(elt11_,attrs10_);
Element elt12_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_157_16));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
Element elt13_=el(_doc62,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_157_17));
at(elt13_,attrs12_);
Element elt14_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_157_18));
at(elt14_,attrs13_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
Element elt15_=el(_doc62,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_157_19));
at(elt15_,attrs14_);
Element elt16_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_157_20));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
ad(elt0_,elt15_);
Element elt17_=el(_doc62,C_IF);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(CONDITION,C_P_157_21));
at(elt17_,attrs16_);
Element elt18_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_157_22));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt0_,elt17_);
Element elt19_=el(_doc62,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_157_23));
at(elt19_,attrs18_);
Element elt20_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_157_24));
at(elt20_,attrs19_);
Element elt21_=el(_doc62,PARAM);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_157_25));
at(elt21_,attrs20_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt0_,elt19_);
Element elt22_=el(_doc62,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_157_26));
at(elt22_,attrs21_);
Element elt23_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_157_27));
at(elt23_,attrs22_);
Element elt24_=el(_doc62,PARAM);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_157_28));
at(elt24_,attrs23_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt0_,elt22_);
Element elt25_=el(_doc62,C_IF);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(CONDITION,C_P_157_29));
at(elt25_,attrs24_);
Element elt26_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_157_30));
at(elt26_,attrs25_);
Element elt27_=el(_doc62,PARAM);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(VALUE,C_P_157_31));
at(elt27_,attrs26_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt0_,elt25_);
Element elt28_=el(_doc62,C_IF);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(CONDITION,C_P_157_32));
at(elt28_,attrs27_);
Element elt29_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(VALUE,C_P_157_33));
at(elt29_,attrs28_);
Element elt30_=el(_doc62,PARAM);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_P_157_34));
at(elt30_,attrs29_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
ad(elt0_,elt28_);
Element elt31_=el(_doc62,C_IF);
CustList<Attr> attrs30_=al(1);
attrs30_.add(at(CONDITION,C_P_157_35));
at(elt31_,attrs30_);
Element elt32_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs31_=al(1);
attrs31_.add(at(VALUE,C_P_157_36));
at(elt32_,attrs31_);
Element elt33_=el(_doc62,PARAM);
CustList<Attr> attrs32_=al(1);
attrs32_.add(at(VALUE,C_P_157_37));
at(elt33_,attrs32_);
ad(elt32_,elt33_);
ad(elt31_,elt32_);
ad(elt0_,elt31_);
Element elt34_=el(_doc62,C_IF);
CustList<Attr> attrs33_=al(1);
attrs33_.add(at(CONDITION,C_P_157_38));
at(elt34_,attrs33_);
Element elt35_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs34_=al(1);
attrs34_.add(at(VALUE,C_P_157_39));
at(elt35_,attrs34_);
ad(elt34_,elt35_);
Element elt36_=el(_doc62,UL);
Element elt37_=el(_doc62,C_FOR);
CustList<Attr> attrs35_=al(3);
attrs35_.add(at(LIST,C_P_157_40));
attrs35_.add(at(VAR,C_P_157_41));
attrs35_.add(at(CLASSNAME,C_P_157_42));
at(elt37_,attrs35_);
Element elt38_=el(_doc62,LI);
Element elt39_=el(_doc62,A);
CustList<Attr> attrs36_=al(2);
attrs36_.add(at(C_COMMAND,C_P_157_43));
attrs36_.add(at(HREF,C_P_157_44));
at(elt39_,attrs36_);
Text txt0_=tx(_doc62,C_P_157_45);
ad(elt39_,txt0_);
ad(elt38_,elt39_);
ad(elt37_,elt38_);
ad(elt36_,elt37_);
ad(elt34_,elt36_);
Element elt40_=el(_doc62,BR);
ad(elt34_,elt40_);
ad(elt0_,elt34_);
Element elt41_=el(_doc62,C_IF);
CustList<Attr> attrs37_=al(1);
attrs37_.add(at(CONDITION,C_P_157_46));
at(elt41_,attrs37_);
Element elt42_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs38_=al(1);
attrs38_.add(at(VALUE,C_P_157_47));
at(elt42_,attrs38_);
ad(elt41_,elt42_);
Element elt43_=el(_doc62,UL);
Element elt44_=el(_doc62,C_FOR);
CustList<Attr> attrs39_=al(3);
attrs39_.add(at(LIST,C_P_157_48));
attrs39_.add(at(VAR,C_P_157_49));
attrs39_.add(at(CLASSNAME,C_P_157_50));
at(elt44_,attrs39_);
Element elt45_=el(_doc62,LI);
Text txt1_=tx(_doc62,C_P_157_51);
ad(elt45_,txt1_);
ad(elt44_,elt45_);
ad(elt43_,elt44_);
ad(elt41_,elt43_);
Element elt46_=el(_doc62,BR);
ad(elt41_,elt46_);
ad(elt0_,elt41_);
Element elt47_=el(_doc62,C_IF);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(CONDITION,C_P_157_52));
at(elt47_,attrs40_);
Element elt48_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs41_=al(1);
attrs41_.add(at(VALUE,C_P_157_53));
at(elt48_,attrs41_);
ad(elt47_,elt48_);
Element elt49_=el(_doc62,TABLE);
Element elt50_=el(_doc62,THEAD);
Element elt51_=el(_doc62,TR);
Element elt52_=el(_doc62,TH);
Element elt53_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs42_=al(1);
attrs42_.add(at(VALUE,C_P_157_54));
at(elt53_,attrs42_);
ad(elt52_,elt53_);
ad(elt51_,elt52_);
Element elt54_=el(_doc62,TH);
Element elt55_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs43_=al(1);
attrs43_.add(at(VALUE,C_P_157_55));
at(elt55_,attrs43_);
ad(elt54_,elt55_);
ad(elt51_,elt54_);
Element elt56_=el(_doc62,TH);
Element elt57_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs44_=al(1);
attrs44_.add(at(VALUE,C_P_157_56));
at(elt57_,attrs44_);
ad(elt56_,elt57_);
ad(elt51_,elt56_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
Element elt58_=el(_doc62,TBODY);
Element elt59_=el(_doc62,C_FOR);
CustList<Attr> attrs45_=al(5);
attrs45_.add(at(KEY,C_P_157_57));
attrs45_.add(at(KEYCLASSNAME,C_P_157_58));
attrs45_.add(at(MAP,C_P_157_59));
attrs45_.add(at(VALUE,C_P_157_60));
attrs45_.add(at(VARCLASSNAME,C_P_157_61));
at(elt59_,attrs45_);
Element elt60_=el(_doc62,TR);
Element elt61_=el(_doc62,TD);
Text txt2_=tx(_doc62,C_P_157_62);
ad(elt61_,txt2_);
ad(elt60_,elt61_);
Element elt62_=el(_doc62,TD);
Text txt3_=tx(_doc62,C_P_157_63);
ad(elt62_,txt3_);
ad(elt60_,elt62_);
Element elt63_=el(_doc62,TD);
Text txt4_=tx(_doc62,C_P_157_64);
ad(elt63_,txt4_);
ad(elt60_,elt63_);
ad(elt59_,elt60_);
ad(elt58_,elt59_);
ad(elt49_,elt58_);
ad(elt47_,elt49_);
Element elt64_=el(_doc62,BR);
ad(elt47_,elt64_);
ad(elt0_,elt47_);
Element elt65_=el(_doc62,C_IF);
CustList<Attr> attrs46_=al(1);
attrs46_.add(at(CONDITION,C_P_157_65));
at(elt65_,attrs46_);
Element elt66_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs47_=al(1);
attrs47_.add(at(VALUE,C_P_157_66));
at(elt66_,attrs47_);
ad(elt65_,elt66_);
Element elt67_=el(_doc62,UL);
Element elt68_=el(_doc62,C_FOR);
CustList<Attr> attrs48_=al(3);
attrs48_.add(at(LIST,C_P_157_67));
attrs48_.add(at(VAR,C_P_157_68));
attrs48_.add(at(CLASSNAME,C_P_157_69));
at(elt68_,attrs48_);
Element elt69_=el(_doc62,LI);
Text txt5_=tx(_doc62,C_P_157_70);
ad(elt69_,txt5_);
ad(elt68_,elt69_);
ad(elt67_,elt68_);
ad(elt65_,elt67_);
Element elt70_=el(_doc62,BR);
ad(elt65_,elt70_);
ad(elt0_,elt65_);
Element elt71_=el(_doc62,C_IF);
CustList<Attr> attrs49_=al(1);
attrs49_.add(at(CONDITION,C_P_157_71));
at(elt71_,attrs49_);
Element elt72_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs50_=al(1);
attrs50_.add(at(VALUE,C_P_157_72));
at(elt72_,attrs50_);
ad(elt71_,elt72_);
Element elt73_=el(_doc62,UL);
Element elt74_=el(_doc62,C_FOR);
CustList<Attr> attrs51_=al(3);
attrs51_.add(at(LIST,C_P_157_73));
attrs51_.add(at(VAR,C_P_157_74));
attrs51_.add(at(CLASSNAME,C_P_157_75));
at(elt74_,attrs51_);
Element elt75_=el(_doc62,LI);
Element elt76_=el(_doc62,A);
CustList<Attr> attrs52_=al(2);
attrs52_.add(at(C_COMMAND,C_P_157_76));
attrs52_.add(at(HREF,C_P_157_77));
at(elt76_,attrs52_);
Text txt6_=tx(_doc62,C_P_157_78);
ad(elt76_,txt6_);
ad(elt75_,elt76_);
ad(elt74_,elt75_);
ad(elt73_,elt74_);
ad(elt71_,elt73_);
Element elt77_=el(_doc62,BR);
ad(elt71_,elt77_);
ad(elt0_,elt71_);
Element elt78_=el(_doc62,C_IF);
CustList<Attr> attrs53_=al(1);
attrs53_.add(at(CONDITION,C_P_157_79));
at(elt78_,attrs53_);
Element elt79_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs54_=al(1);
attrs54_.add(at(VALUE,C_P_157_80));
at(elt79_,attrs54_);
ad(elt78_,elt79_);
Element elt80_=el(_doc62,UL);
Element elt81_=el(_doc62,C_FOR);
CustList<Attr> attrs55_=al(2);
attrs55_.add(at(LIST,C_P_157_81));
attrs55_.add(at(VAR,C_P_157_82));
at(elt81_,attrs55_);
Element elt82_=el(_doc62,LI);
Element elt83_=el(_doc62,A);
CustList<Attr> attrs56_=al(2);
attrs56_.add(at(C_COMMAND,C_P_157_83));
attrs56_.add(at(HREF,C_P_157_84));
at(elt83_,attrs56_);
Text txt7_=tx(_doc62,C_P_157_85);
ad(elt83_,txt7_);
ad(elt82_,elt83_);
ad(elt81_,elt82_);
ad(elt80_,elt81_);
ad(elt78_,elt80_);
Element elt84_=el(_doc62,BR);
ad(elt78_,elt84_);
ad(elt0_,elt78_);
Element elt85_=el(_doc62,C_IF);
CustList<Attr> attrs57_=al(1);
attrs57_.add(at(CONDITION,C_P_157_86));
at(elt85_,attrs57_);
Element elt86_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs58_=al(1);
attrs58_.add(at(VALUE,C_P_157_87));
at(elt86_,attrs58_);
ad(elt85_,elt86_);
Element elt87_=el(_doc62,UL);
Element elt88_=el(_doc62,C_FOR);
CustList<Attr> attrs59_=al(2);
attrs59_.add(at(LIST,C_P_157_88));
attrs59_.add(at(VAR,C_P_157_89));
at(elt88_,attrs59_);
Element elt89_=el(_doc62,LI);
Element elt90_=el(_doc62,A);
CustList<Attr> attrs60_=al(2);
attrs60_.add(at(C_COMMAND,C_P_157_90));
attrs60_.add(at(HREF,C_P_157_91));
at(elt90_,attrs60_);
Text txt8_=tx(_doc62,C_P_157_92);
ad(elt90_,txt8_);
ad(elt89_,elt90_);
ad(elt88_,elt89_);
ad(elt87_,elt88_);
ad(elt85_,elt87_);
Element elt91_=el(_doc62,BR);
ad(elt85_,elt91_);
ad(elt0_,elt85_);
Element elt92_=el(_doc62,C_IF);
CustList<Attr> attrs61_=al(1);
attrs61_.add(at(CONDITION,C_P_157_93));
at(elt92_,attrs61_);
Element elt93_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs62_=al(1);
attrs62_.add(at(VALUE,C_P_157_94));
at(elt93_,attrs62_);
ad(elt92_,elt93_);
Element elt94_=el(_doc62,TABLE);
Element elt95_=el(_doc62,THEAD);
Element elt96_=el(_doc62,TR);
Element elt97_=el(_doc62,TH);
Element elt98_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs63_=al(1);
attrs63_.add(at(VALUE,C_P_157_95));
at(elt98_,attrs63_);
ad(elt97_,elt98_);
ad(elt96_,elt97_);
Element elt99_=el(_doc62,TH);
Element elt100_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs64_=al(1);
attrs64_.add(at(VALUE,C_P_157_96));
at(elt100_,attrs64_);
ad(elt99_,elt100_);
ad(elt96_,elt99_);
ad(elt95_,elt96_);
ad(elt94_,elt95_);
Element elt101_=el(_doc62,TBODY);
Element elt102_=el(_doc62,C_FOR);
CustList<Attr> attrs65_=al(5);
attrs65_.add(at(KEY,C_P_157_97));
attrs65_.add(at(MAP,C_P_157_98));
attrs65_.add(at(VALUE,C_P_157_99));
attrs65_.add(at(KEYCLASSNAME,C_P_157_100));
attrs65_.add(at(VARCLASSNAME,C_P_157_101));
at(elt102_,attrs65_);
Element elt103_=el(_doc62,TR);
Element elt104_=el(_doc62,TD);
Element elt105_=el(_doc62,A);
CustList<Attr> attrs66_=al(2);
attrs66_.add(at(C_COMMAND,C_P_157_102));
attrs66_.add(at(HREF,C_P_157_103));
at(elt105_,attrs66_);
Text txt9_=tx(_doc62,C_P_157_104);
ad(elt105_,txt9_);
ad(elt104_,elt105_);
ad(elt103_,elt104_);
Element elt106_=el(_doc62,TD);
Text txt10_=tx(_doc62,C_P_157_105);
ad(elt106_,txt10_);
ad(elt103_,elt106_);
ad(elt102_,elt103_);
ad(elt101_,elt102_);
ad(elt94_,elt101_);
ad(elt92_,elt94_);
Element elt107_=el(_doc62,BR);
ad(elt92_,elt107_);
ad(elt0_,elt92_);
Element elt108_=el(_doc62,C_IF);
CustList<Attr> attrs67_=al(1);
attrs67_.add(at(CONDITION,C_P_157_106));
at(elt108_,attrs67_);
Element elt109_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs68_=al(1);
attrs68_.add(at(VALUE,C_P_157_107));
at(elt109_,attrs68_);
ad(elt108_,elt109_);
Element elt110_=el(_doc62,TABLE);
Element elt111_=el(_doc62,THEAD);
Element elt112_=el(_doc62,TR);
Element elt113_=el(_doc62,TH);
Element elt114_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs69_=al(1);
attrs69_.add(at(VALUE,C_P_157_108));
at(elt114_,attrs69_);
ad(elt113_,elt114_);
ad(elt112_,elt113_);
Element elt115_=el(_doc62,TH);
Element elt116_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs70_=al(1);
attrs70_.add(at(VALUE,C_P_157_109));
at(elt116_,attrs70_);
ad(elt115_,elt116_);
ad(elt112_,elt115_);
ad(elt111_,elt112_);
ad(elt110_,elt111_);
Element elt117_=el(_doc62,TBODY);
Element elt118_=el(_doc62,C_FOR);
CustList<Attr> attrs71_=al(5);
attrs71_.add(at(KEY,C_P_157_110));
attrs71_.add(at(MAP,C_P_157_111));
attrs71_.add(at(VALUE,C_P_157_112));
attrs71_.add(at(KEYCLASSNAME,C_P_157_113));
attrs71_.add(at(VARCLASSNAME,C_P_157_114));
at(elt118_,attrs71_);
Element elt119_=el(_doc62,TR);
Element elt120_=el(_doc62,TD);
Text txt11_=tx(_doc62,C_P_157_115);
ad(elt120_,txt11_);
ad(elt119_,elt120_);
Element elt121_=el(_doc62,TD);
Text txt12_=tx(_doc62,C_P_157_116);
ad(elt121_,txt12_);
ad(elt119_,elt121_);
ad(elt118_,elt119_);
ad(elt117_,elt118_);
ad(elt110_,elt117_);
ad(elt108_,elt110_);
Element elt122_=el(_doc62,BR);
ad(elt108_,elt122_);
ad(elt0_,elt108_);
Element elt123_=el(_doc62,C_IF);
CustList<Attr> attrs72_=al(1);
attrs72_.add(at(CONDITION,C_P_157_117));
at(elt123_,attrs72_);
Element elt124_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs73_=al(1);
attrs73_.add(at(VALUE,C_P_157_118));
at(elt124_,attrs73_);
ad(elt123_,elt124_);
Element elt125_=el(_doc62,UL);
Element elt126_=el(_doc62,C_FOR);
CustList<Attr> attrs74_=al(3);
attrs74_.add(at(LIST,C_P_157_119));
attrs74_.add(at(VAR,C_P_157_120));
attrs74_.add(at(CLASSNAME,C_P_157_121));
at(elt126_,attrs74_);
Element elt127_=el(_doc62,LI);
Text txt13_=tx(_doc62,C_P_157_122);
ad(elt127_,txt13_);
ad(elt126_,elt127_);
ad(elt125_,elt126_);
ad(elt123_,elt125_);
Element elt128_=el(_doc62,BR);
ad(elt123_,elt128_);
ad(elt0_,elt123_);
Element elt129_=el(_doc62,C_IF);
CustList<Attr> attrs75_=al(1);
attrs75_.add(at(CONDITION,C_P_157_123));
at(elt129_,attrs75_);
Element elt130_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs76_=al(1);
attrs76_.add(at(VALUE,C_P_157_124));
at(elt130_,attrs76_);
ad(elt129_,elt130_);
Element elt8a_=el(_doc62,A);
CustList<Attr> attrs8a_=al(1);
attrs8a_.add(at(C_COMMAND,C_P_157_125_LK));
at(elt8a_,attrs8a_);
Text txt30_=tx(_doc62,C_P_157_125);
ad(elt8a_,txt30_);
ad(elt129_,elt8a_);
Element elt1301_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs761_=al(1);
attrs761_.add(at(VALUE,C_P_157_124_0));
at(elt1301_,attrs761_);
ad(elt129_,elt1301_);
Element elt132_=el(_doc62,UL);
Element elt133_=el(_doc62,C_FOR);
CustList<Attr> attrs78_=al(2);
attrs78_.add(at(LIST,C_P_157_126));
attrs78_.add(at(VAR,C_P_157_127));
at(elt133_,attrs78_);
Element elt134_=el(_doc62,LI);
Element elt135_=el(_doc62,A);
CustList<Attr> attrs79_=al(2);
attrs79_.add(at(C_COMMAND,C_P_157_128));
attrs79_.add(at(HREF,C_P_157_129));
at(elt135_,attrs79_);
Text txt14_=tx(_doc62,C_P_157_130);
ad(elt135_,txt14_);
ad(elt134_,elt135_);
ad(elt133_,elt134_);
ad(elt132_,elt133_);
ad(elt129_,elt132_);
Element elt136_=el(_doc62,BR);
ad(elt129_,elt136_);
ad(elt0_,elt129_);
Element elt137_=el(_doc62,C_IF);
CustList<Attr> attrs80_=al(1);
attrs80_.add(at(CONDITION,C_P_157_131));
at(elt137_,attrs80_);
Element elt138_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs81_=al(1);
attrs81_.add(at(VALUE,C_P_157_132));
at(elt138_,attrs81_);
ad(elt137_,elt138_);
Element elt139_=el(_doc62,UL);
Element elt140_=el(_doc62,C_FOR);
CustList<Attr> attrs82_=al(3);
attrs82_.add(at(LIST,C_P_157_133));
attrs82_.add(at(VAR,C_P_157_134));
attrs82_.add(at(CLASSNAME,C_P_157_135));
at(elt140_,attrs82_);
Element elt141_=el(_doc62,LI);
Text txt15_=tx(_doc62,C_P_157_136);
ad(elt141_,txt15_);
ad(elt140_,elt141_);
ad(elt139_,elt140_);
ad(elt137_,elt139_);
Element elt142_=el(_doc62,BR);
ad(elt137_,elt142_);
Element elt143_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs83_=al(1);
attrs83_.add(at(VALUE,C_P_157_137));
at(elt143_,attrs83_);
ad(elt137_,elt143_);
Element elt144_=el(_doc62,UL);
Element elt145_=el(_doc62,C_FOR);
CustList<Attr> attrs84_=al(3);
attrs84_.add(at(LIST,C_P_157_138));
attrs84_.add(at(VAR,C_P_157_139));
attrs84_.add(at(CLASSNAME,C_P_157_140));
at(elt145_,attrs84_);
Element elt146_=el(_doc62,LI);
Element elt147_=el(_doc62,A);
CustList<Attr> attrs85_=al(2);
attrs85_.add(at(C_COMMAND,C_P_157_141));
attrs85_.add(at(HREF,C_P_157_142));
at(elt147_,attrs85_);
Text txt16_=tx(_doc62,C_P_157_143);
ad(elt147_,txt16_);
ad(elt146_,elt147_);
ad(elt145_,elt146_);
ad(elt144_,elt145_);
ad(elt137_,elt144_);
Element elt148_=el(_doc62,BR);
ad(elt137_,elt148_);
ad(elt0_,elt137_);
Element elt149_=el(_doc62,C_IF);
CustList<Attr> attrs86_=al(1);
attrs86_.add(at(CONDITION,C_P_157_144));
at(elt149_,attrs86_);
Element elt150_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs87_=al(1);
attrs87_.add(at(VALUE,C_P_157_145));
at(elt150_,attrs87_);
ad(elt149_,elt150_);
Element elt151_=el(_doc62,TABLE);
Element elt152_=el(_doc62,THEAD);
Element elt153_=el(_doc62,TR);
Element elt154_=el(_doc62,TH);
Element elt155_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs88_=al(1);
attrs88_.add(at(VALUE,C_P_157_146));
at(elt155_,attrs88_);
ad(elt154_,elt155_);
ad(elt153_,elt154_);
Element elt156_=el(_doc62,TH);
Element elt157_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs89_=al(1);
attrs89_.add(at(VALUE,C_P_157_147));
at(elt157_,attrs89_);
ad(elt156_,elt157_);
ad(elt153_,elt156_);
Element elt158_=el(_doc62,TH);
Element elt159_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs90_=al(1);
attrs90_.add(at(VALUE,C_P_157_148));
at(elt159_,attrs90_);
ad(elt158_,elt159_);
ad(elt153_,elt158_);
ad(elt152_,elt153_);
ad(elt151_,elt152_);
Element elt160_=el(_doc62,TBODY);
Element elt161_=el(_doc62,C_FOR);
CustList<Attr> attrs91_=al(5);
attrs91_.add(at(KEY,C_P_157_149));
attrs91_.add(at(MAP,C_P_157_150));
attrs91_.add(at(VALUE,C_P_157_151));
attrs91_.add(at(KEYCLASSNAME,C_P_157_152));
attrs91_.add(at(VARCLASSNAME,C_P_157_153));
at(elt161_,attrs91_);
Element elt162_=el(_doc62,TR);
Element elt163_=el(_doc62,TD);
Text txt17_=tx(_doc62,C_P_157_154);
ad(elt163_,txt17_);
ad(elt162_,elt163_);
Element elt164_=el(_doc62,TD);
Text txt18_=tx(_doc62,C_P_157_155);
ad(elt164_,txt18_);
ad(elt162_,elt164_);
Element elt165_=el(_doc62,TD);
Text txt19_=tx(_doc62,C_P_157_156);
ad(elt165_,txt19_);
ad(elt162_,elt165_);
ad(elt161_,elt162_);
ad(elt160_,elt161_);
ad(elt151_,elt160_);
ad(elt149_,elt151_);
Element elt166_=el(_doc62,BR);
ad(elt149_,elt166_);
ad(elt0_,elt149_);
Element elt167_=el(_doc62,C_IF);
CustList<Attr> attrs92_=al(1);
attrs92_.add(at(CONDITION,C_P_157_157));
at(elt167_,attrs92_);
Element elt168_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs93_=al(1);
attrs93_.add(at(VALUE,C_P_157_158));
at(elt168_,attrs93_);
ad(elt167_,elt168_);
Element elt169_=el(_doc62,UL);
Element elt170_=el(_doc62,C_FOR);
CustList<Attr> attrs94_=al(2);
attrs94_.add(at(LIST,C_P_157_159));
attrs94_.add(at(VAR,C_P_157_160));
at(elt170_,attrs94_);
Element elt171_=el(_doc62,LI);
Element elt172_=el(_doc62,A);
CustList<Attr> attrs95_=al(2);
attrs95_.add(at(C_COMMAND,C_P_157_161));
attrs95_.add(at(HREF,C_P_157_162));
at(elt172_,attrs95_);
Text txt20_=tx(_doc62,C_P_157_163);
ad(elt172_,txt20_);
ad(elt171_,elt172_);
ad(elt170_,elt171_);
ad(elt169_,elt170_);
ad(elt167_,elt169_);
Element elt173_=el(_doc62,BR);
ad(elt167_,elt173_);
Element elt174_=el(_doc62,TABLE);
Element elt175_=el(_doc62,THEAD);
Element elt176_=el(_doc62,TR);
Element elt177_=el(_doc62,TH);
Element elt178_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs96_=al(1);
attrs96_.add(at(VALUE,C_P_157_164));
at(elt178_,attrs96_);
ad(elt177_,elt178_);
ad(elt176_,elt177_);
Element elt179_=el(_doc62,TH);
Element elt180_=el(_doc62,C_MESSAGE);
CustList<Attr> attrs97_=al(1);
attrs97_.add(at(VALUE,C_P_157_165));
at(elt180_,attrs97_);
ad(elt179_,elt180_);
ad(elt176_,elt179_);
ad(elt175_,elt176_);
ad(elt174_,elt175_);
Element elt181_=el(_doc62,TBODY);
Element elt182_=el(_doc62,C_FOR);
CustList<Attr> attrs98_=al(5);
attrs98_.add(at(KEY,C_P_157_166));
attrs98_.add(at(MAP,C_P_157_167));
attrs98_.add(at(VALUE,C_P_157_168));
attrs98_.add(at(KEYCLASSNAME,C_P_157_169));
attrs98_.add(at(VARCLASSNAME,C_P_157_170));
at(elt182_,attrs98_);
Element elt183_=el(_doc62,TR);
Element elt184_=el(_doc62,TD);
Text txt21_=tx(_doc62,C_P_157_171);
ad(elt184_,txt21_);
ad(elt183_,elt184_);
Element elt185_=el(_doc62,TD);
Text txt22_=tx(_doc62,C_P_157_172);
ad(elt185_,txt22_);
ad(elt183_,elt185_);
ad(elt182_,elt183_);
ad(elt181_,elt182_);
ad(elt174_,elt181_);
ad(elt167_,elt174_);
Element elt186_=el(_doc62,BR);
ad(elt167_,elt186_);
ad(elt0_,elt167_);
ad(_body,elt0_);
}
}
