package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataPokemonData extends PageAikiCommon{
private static final String C_P_181_0="javahtml";
private static final String C_P_181_1="pokemon";
private static final String C_P_181_2="msg_pkdata,title";
private static final String C_P_181_3="displayName";
private static final String C_P_181_4="web/css/pokedex.css";
private static final String C_P_181_5="stylesheet";
private static final String C_P_181_6="text/css";
private static final String C_P_181_7="msg_pkdata,general";
private static final String C_P_181_8="{displayName}";
private static final String C_P_181_9="$clickPokedex";
private static final String C_P_181_10="";
private static final String C_P_181_11="msg_pkdata,pokedex";
private static final String C_P_181_12="msg_pkdata,name";
private static final String C_P_181_13="displayName";
private static final String C_P_181_14="msg_pkdata,back";
private static final String C_P_181_15=":";
private static final String C_P_181_16="{backImage}";
private static final String C_P_181_17="msg_pkdata,front";
private static final String C_P_181_18=":";
private static final String C_P_181_19="{frontImage}";
private static final String C_P_181_20="msg_pkdata,weight";
private static final String C_P_181_21="displayName";
private static final String C_P_181_22="weight";
private static final String C_P_181_23="roundWeight()";
private static final String C_P_181_24="msg_pkdata,height";
private static final String C_P_181_25="displayName";
private static final String C_P_181_26="height";
private static final String C_P_181_27="roundHeight()";
private static final String C_P_181_28="msg_pkdata,genders";
private static final String C_P_181_29="displayName";
private static final String C_P_181_30="possibleGenders";
private static final String C_P_181_31="g";
private static final String C_P_181_32="java.lang.String";
private static final String C_P_181_33="{g}";
private static final String C_P_181_34="msg_pkdata,types";
private static final String C_P_181_35="displayName";
private static final String C_P_181_36="types";
private static final String C_P_181_37="t";
private static final String C_P_181_38="java.lang.String";
private static final String C_P_181_39="{t}";
private static final String C_P_181_40="msg_pkdata,abilities";
private static final String C_P_181_41="displayName";
private static final String C_P_181_42="abilities";
private static final String C_P_181_43="a";
private static final String C_P_181_44="$clickAbility({([a])})";
private static final String C_P_181_45="";
private static final String C_P_181_46="{getTrAbility(([a]))}";
private static final String C_P_181_47="msg_pkdata,catchingRate";
private static final String C_P_181_48="displayName";
private static final String C_P_181_49="catchingRate";
private static final String C_P_181_50="msg_pkdata,tree";
private static final String C_P_181_51="!evolutions.isEmpty()";
private static final String C_P_181_52="msg_pkdata,evolutions";
private static final String C_P_181_53="displayName";
private static final String C_P_181_54="msg_pkdata,evolutions_title";
private static final String C_P_181_55="msg_pkdata,evolutions_key";
private static final String C_P_181_56="msg_pkdata,get_evo";
private static final String C_P_181_57="java.lang.String";
private static final String C_P_181_58="evolutions";
private static final String C_P_181_59="e";
private static final String C_P_181_60="{getPage(([e]))}";
private static final String C_P_181_61="aiki.beans.pokemon.evolutions";
private static final String C_P_181_62="EvolutionBean";
private static final String C_P_181_63="$intern.index=([e])";
private static final String C_P_181_64="$intern.name=e";
private static final String C_P_181_65="$intern.base=name";
private static final String C_P_181_66="msg_pkdata,base";
private static final String C_P_181_67="displayName";
private static final String C_P_181_68="evoBase";
private static final String C_P_181_69="msg_pkdata,exp";
private static final String C_P_181_70="msg_pkdata,exp_growth";
private static final String C_P_181_71="displayName";
private static final String C_P_181_72="expEvo";
private static final String C_P_181_73="k";
private static final String C_P_181_74="mapVars";
private static final String C_P_181_75="v";
private static final String C_P_181_76="java.lang.String";
private static final String C_P_181_77="java.lang.String";
private static final String C_P_181_78="{k} : {v}";
private static final String C_P_181_79="msg_pkdata,pts_exp";
private static final String C_P_181_80="displayName";
private static final String C_P_181_81="expRate";
private static final String C_P_181_82="msg_pkdata,statistics_title";
private static final String C_P_181_83="msg_pkdata,statistics";
private static final String C_P_181_84="displayName";
private static final String C_P_181_85="msg_pkdata,statistics_title";
private static final String C_P_181_86="msg_pkdata,statistics_key";
private static final String C_P_181_87="msg_pkdata,statistics_value";
private static final String C_P_181_88="msg_pkdata,statistics_ev";
private static final String C_P_181_89="displayName";
private static final String C_P_181_90="statistics";
private static final String C_P_181_91="s";
private static final String C_P_181_92="java.lang.String";
private static final String C_P_181_93="{s}";
private static final String C_P_181_94="{getBase(([s]))}";
private static final String C_P_181_95="{getEv(([s]))}";
private static final String C_P_181_96="msg_pkdata,moves";
private static final String C_P_181_97="msg_pkdata,level_moves";
private static final String C_P_181_98="displayName";
private static final String C_P_181_99="msg_pkdata,moves_levels";
private static final String C_P_181_100="msg_pkdata,level";
private static final String C_P_181_101="msg_pkdata,move";
private static final String C_P_181_102="aiki.fight.util.LevelMove";
private static final String C_P_181_103="levMoves";
private static final String C_P_181_104="l";
private static final String C_P_181_105="{l.getLevel()}";
private static final String C_P_181_106="$clickMove({([l])})";
private static final String C_P_181_107="";
private static final String C_P_181_108="{l.getMove()}";
private static final String C_P_181_109="msg_pkdata,technical_moves";
private static final String C_P_181_110="displayName";
private static final String C_P_181_111="msg_pkdata,tm_title";
private static final String C_P_181_112="msg_pkdata,tm_number";
private static final String C_P_181_113="msg_pkdata,tm_move";
private static final String C_P_181_114="l";
private static final String C_P_181_115="technicalMoves";
private static final String C_P_181_116="m";
private static final String C_P_181_117="java.lang.Short";
private static final String C_P_181_118="java.lang.String";
private static final String C_P_181_119="{l}";
private static final String C_P_181_120="$clickTechnicalMove({([l])})";
private static final String C_P_181_121="";
private static final String C_P_181_122="{m}";
private static final String C_P_181_123="msg_pkdata,hidden_moves";
private static final String C_P_181_124="displayName";
private static final String C_P_181_125="msg_pkdata,hm_title";
private static final String C_P_181_126="msg_pkdata,hm_number";
private static final String C_P_181_127="msg_pkdata,hm_move";
private static final String C_P_181_128="l";
private static final String C_P_181_129="hiddenMoves";
private static final String C_P_181_130="m";
private static final String C_P_181_131="java.lang.Short";
private static final String C_P_181_132="java.lang.String";
private static final String C_P_181_133="{l}";
private static final String C_P_181_134="$clickHiddenMove({([l])})";
private static final String C_P_181_135="";
private static final String C_P_181_136="{m}";
private static final String C_P_181_137="msg_pkdata,move_tutors";
private static final String C_P_181_138="displayName";
private static final String C_P_181_139="moveTutors";
private static final String C_P_181_140="m";
private static final String C_P_181_141="$clickMoveTutors({([m])})";
private static final String C_P_181_142="";
private static final String C_P_181_143="{getMoveTutor(([m]))}";
private static final String C_P_181_144="msg_pkdata,egg";
private static final String C_P_181_145="msg_pkdata,egg_groups_pk";
private static final String C_P_181_146="displayName";
private static final String C_P_181_147="eggGroupsPk";
private static final String C_P_181_148="p";
private static final String C_P_181_149="$clickEggPk({([p])})";
private static final String C_P_181_150="";
private static final String C_P_181_151="{getEggPk(([p]))}";
private static final String C_P_181_152="msg_pkdata,hatching";
private static final String C_P_181_153="displayName";
private static final String C_P_181_154="hatchingSteps";
private static final String C_P_181_155="isAppearingAnyWhere()";
private static final String C_P_181_156="msg_pkdata,where_catch";
private static final String C_P_181_157="displayName";
private static final String C_P_181_158="aiki.beans.facade.map.dto.PlaceIndex";
private static final String C_P_181_159="places";
private static final String C_P_181_160="p";
private static final String C_P_181_161="isAppearingPlace(([p]))";
private static final String C_P_181_162="{p.getPlace().getName()}";
private static final String C_P_181_163="isMultiLayer(([p]))";
private static final String C_P_181_164="layers(([p]))";
private static final String C_P_181_165="l";
private static final String C_P_181_166="isAppearing(([p]),([l]))";
private static final String C_P_181_167="$clickLevel({p.index},{([l])})";
private static final String C_P_181_168="{([l])}";
private static final String C_P_181_169="!isMultiLayer(([p]))";
private static final String C_P_181_170="isAppearingZero(([p]))";
private static final String C_P_181_171="$clickLevelZero({p.index})";
private static final String C_P_181_172="msg_pkdata,goLevel";
private static final String C_P_181_173="{getMapWidth()}";
private static final String C_P_181_174="p";
private static final String C_P_181_175="images";
private static final String C_P_181_176="i";
private static final String C_P_181_177="java.lang.Object";
private static final String C_P_181_178="java.lang.Object";
private static final String C_P_181_179="{getPlaceName(([p]))}";
private static final String C_P_181_180="{getMiniMapImage(([p]))}";
private static final String C_P_181_181="$clickPokedex";
private static final String C_P_181_182="";
private static final String C_P_181_183="msg_pkdata,pokedex";
private PageDataPokemonData(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc86){
Element elt0_=el(_doc86,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_181_0));
attrs0_.add(at(C_BEAN,C_P_181_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc86,HEAD);
Element elt2_=el(_doc86,TITLE);
Element elt3_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_181_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc86,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc86,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_181_4));
attrs3_.add(at(REL,C_P_181_5));
attrs3_.add(at(TYPE,C_P_181_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc86,BODY);
build0(elt6_,_doc86);
build1(elt6_,_doc86);
build2(elt6_,_doc86);
build3(elt6_,_doc86);
build4(elt6_,_doc86);
build5(elt6_,_doc86);
build6(elt6_,_doc86);
build7(elt6_,_doc86);
build8(elt6_,_doc86);
ad(elt0_,elt6_);
_doc86.appendChild(elt0_);
}
static void build0(Element _body,Document _doc86){
Element elt0_=el(_doc86,H1);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_181_7));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Text txt0_=tx(_doc86,C_P_181_8);
ad(elt0_,txt0_);
ad(_body,elt0_);
Element elt2_=el(_doc86,BR);
ad(_body,elt2_);
Element elt3_=el(_doc86,A);
CustList<Attr> attrs1_=al(2);
attrs1_.add(at(C_COMMAND,C_P_181_9));
attrs1_.add(at(HREF,C_P_181_10));
at(elt3_,attrs1_);
Element elt4_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_11));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(_body,elt3_);
Element elt5_=el(_doc86,BR);
ad(_body,elt5_);
Element elt6_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_181_12));
at(elt6_,attrs3_);
Element elt7_=el(_doc86,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_181_13));
at(elt7_,attrs4_);
ad(elt6_,elt7_);
ad(_body,elt6_);
Element elt8_=el(_doc86,BR);
ad(_body,elt8_);
Element elt9_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_181_14));
at(elt9_,attrs5_);
ad(_body,elt9_);
Text txt1_=tx(_doc86,C_P_181_15);
ad(_body,txt1_);
Element elt10_=el(_doc86,C_IMG);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(SRC,C_P_181_16));
at(elt10_,attrs6_);
ad(_body,elt10_);
Element elt11_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_181_17));
at(elt11_,attrs7_);
ad(_body,elt11_);
Text txt2_=tx(_doc86,C_P_181_18);
ad(_body,txt2_);
Element elt12_=el(_doc86,C_IMG);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(SRC,C_P_181_19));
at(elt12_,attrs8_);
ad(_body,elt12_);
Element elt13_=el(_doc86,BR);
ad(_body,elt13_);
Element elt14_=el(_doc86,BR);
ad(_body,elt14_);
Element elt15_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_181_20));
at(elt15_,attrs9_);
Element elt16_=el(_doc86,PARAM);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_181_21));
at(elt16_,attrs10_);
ad(elt15_,elt16_);
Element elt17_=el(_doc86,PARAM);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_181_22));
at(elt17_,attrs11_);
ad(elt15_,elt17_);
Element elt18_=el(_doc86,PARAM);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_181_23));
at(elt18_,attrs12_);
ad(elt15_,elt18_);
ad(_body,elt15_);
Element elt19_=el(_doc86,BR);
ad(_body,elt19_);
Element elt20_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_181_24));
at(elt20_,attrs13_);
Element elt21_=el(_doc86,PARAM);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_181_25));
at(elt21_,attrs14_);
ad(elt20_,elt21_);
Element elt22_=el(_doc86,PARAM);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_181_26));
at(elt22_,attrs15_);
ad(elt20_,elt22_);
Element elt23_=el(_doc86,PARAM);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_181_27));
at(elt23_,attrs16_);
ad(elt20_,elt23_);
ad(_body,elt20_);
Element elt24_=el(_doc86,BR);
ad(_body,elt24_);
Element elt25_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_181_28));
at(elt25_,attrs17_);
Element elt26_=el(_doc86,PARAM);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_181_29));
at(elt26_,attrs18_);
ad(elt25_,elt26_);
ad(_body,elt25_);
Element elt27_=el(_doc86,BR);
ad(_body,elt27_);
Element elt28_=el(_doc86,UL);
Element elt29_=el(_doc86,C_FOR);
CustList<Attr> attrs19_=al(3);
attrs19_.add(at(LIST,C_P_181_30));
attrs19_.add(at(VAR,C_P_181_31));
attrs19_.add(at(CLASSNAME,C_P_181_32));
at(elt29_,attrs19_);
Element elt30_=el(_doc86,LI);
Text txt3_=tx(_doc86,C_P_181_33);
ad(elt30_,txt3_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
ad(_body,elt28_);
Element elt31_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_181_34));
at(elt31_,attrs20_);
Element elt32_=el(_doc86,PARAM);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(VALUE,C_P_181_35));
at(elt32_,attrs21_);
ad(elt31_,elt32_);
ad(_body,elt31_);
Element elt33_=el(_doc86,BR);
ad(_body,elt33_);
Element elt34_=el(_doc86,UL);
Element elt35_=el(_doc86,C_FOR);
CustList<Attr> attrs22_=al(3);
attrs22_.add(at(LIST,C_P_181_36));
attrs22_.add(at(VAR,C_P_181_37));
attrs22_.add(at(CLASSNAME,C_P_181_38));
at(elt35_,attrs22_);
Element elt36_=el(_doc86,LI);
Text txt4_=tx(_doc86,C_P_181_39);
ad(elt36_,txt4_);
ad(elt35_,elt36_);
ad(elt34_,elt35_);
ad(_body,elt34_);
Element elt37_=el(_doc86,BR);
ad(_body,elt37_);
Element elt38_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_181_40));
at(elt38_,attrs23_);
Element elt39_=el(_doc86,PARAM);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(VALUE,C_P_181_41));
at(elt39_,attrs24_);
ad(elt38_,elt39_);
ad(_body,elt38_);
Element elt40_=el(_doc86,UL);
Element elt41_=el(_doc86,C_FOR);
CustList<Attr> attrs25_=al(2);
attrs25_.add(at(LIST,C_P_181_42));
attrs25_.add(at(VAR,C_P_181_43));
at(elt41_,attrs25_);
Element elt42_=el(_doc86,LI);
Element elt43_=el(_doc86,A);
CustList<Attr> attrs26_=al(2);
attrs26_.add(at(C_COMMAND,C_P_181_44));
attrs26_.add(at(HREF,C_P_181_45));
at(elt43_,attrs26_);
Text txt5_=tx(_doc86,C_P_181_46);
ad(elt43_,txt5_);
ad(elt42_,elt43_);
ad(elt41_,elt42_);
ad(elt40_,elt41_);
ad(_body,elt40_);
Element elt44_=el(_doc86,BR);
ad(_body,elt44_);
Element elt45_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(VALUE,C_P_181_47));
at(elt45_,attrs27_);
Element elt46_=el(_doc86,PARAM);
CustList<Attr> attrs28_=al(1);
attrs28_.add(at(VALUE,C_P_181_48));
at(elt46_,attrs28_);
ad(elt45_,elt46_);
Element elt47_=el(_doc86,PARAM);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_P_181_49));
at(elt47_,attrs29_);
ad(elt45_,elt47_);
ad(_body,elt45_);
Element elt48_=el(_doc86,BR);
ad(_body,elt48_);
}
static void build1(Element _body,Document _doc86){
Element elt0_=el(_doc86,H1);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_181_50));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc86,BR);
ad(_body,elt2_);
Element elt3_=el(_doc86,C_IF);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(CONDITION,C_P_181_51));
at(elt3_,attrs1_);
Element elt4_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_52));
at(elt4_,attrs2_);
Element elt5_=el(_doc86,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_181_53));
at(elt5_,attrs3_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt6_=el(_doc86,TABLE);
Element elt7_=el(_doc86,CAPTION);
Element elt8_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_181_54));
at(elt8_,attrs4_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
Element elt9_=el(_doc86,THEAD);
Element elt10_=el(_doc86,TR);
Element elt11_=el(_doc86,TH);
Element elt12_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_181_55));
at(elt12_,attrs5_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc86,TH);
Element elt14_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_181_56));
at(elt14_,attrs6_);
ad(elt13_,elt14_);
ad(elt10_,elt13_);
ad(elt9_,elt10_);
ad(elt6_,elt9_);
Element elt15_=el(_doc86,TBODY);
Element elt16_=el(_doc86,C_FOR);
CustList<Attr> attrs7_=al(3);
attrs7_.add(at(CLASSNAME,C_P_181_57));
attrs7_.add(at(LIST,C_P_181_58));
attrs7_.add(at(VAR,C_P_181_59));
at(elt16_,attrs7_);
Element elt17_=el(_doc86,C_IMPORT);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(PAGE,C_P_181_60));
at(elt17_,attrs8_);
Element elt18_=el(_doc86,C_PACKAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(NAME,C_P_181_61));
at(elt18_,attrs9_);
Element elt19_=el(_doc86,C_CLASS);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(NAME,C_P_181_62));
at(elt19_,attrs10_);
Element elt20_=el(_doc86,C_FIELD);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(PREPARE,C_P_181_63));
at(elt20_,attrs11_);
ad(elt19_,elt20_);
Element elt21_=el(_doc86,C_FIELD);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(PREPARE,C_P_181_64));
at(elt21_,attrs12_);
ad(elt19_,elt21_);
Element elt22_=el(_doc86,C_FIELD);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(PREPARE,C_P_181_65));
at(elt22_,attrs13_);
ad(elt19_,elt22_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt6_,elt15_);
ad(elt3_,elt6_);
Element elt23_=el(_doc86,BR);
ad(elt3_,elt23_);
ad(_body,elt3_);
Element elt24_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_181_66));
at(elt24_,attrs14_);
Element elt25_=el(_doc86,PARAM);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_181_67));
at(elt25_,attrs15_);
ad(elt24_,elt25_);
Element elt26_=el(_doc86,PARAM);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_181_68));
at(elt26_,attrs16_);
ad(elt24_,elt26_);
ad(_body,elt24_);
Element elt27_=el(_doc86,BR);
ad(_body,elt27_);
Element elt28_=el(_doc86,BR);
ad(_body,elt28_);
}
static void build2(Element _body,Document _doc86){
Element elt0_=el(_doc86,H1);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_181_69));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc86,BR);
ad(_body,elt2_);
Element elt3_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_181_70));
at(elt3_,attrs1_);
Element elt4_=el(_doc86,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_71));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
Element elt5_=el(_doc86,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_181_72));
at(elt5_,attrs3_);
ad(elt3_,elt5_);
ad(_body,elt3_);
Element elt6_=el(_doc86,UL);
Element elt7_=el(_doc86,C_FOR);
CustList<Attr> attrs4_=al(5);
attrs4_.add(at(KEY,C_P_181_73));
attrs4_.add(at(MAP,C_P_181_74));
attrs4_.add(at(VALUE,C_P_181_75));
attrs4_.add(at(KEYCLASSNAME,C_P_181_76));
attrs4_.add(at(VARCLASSNAME,C_P_181_77));
at(elt7_,attrs4_);
Element elt8_=el(_doc86,LI);
Text txt0_=tx(_doc86,C_P_181_78);
ad(elt8_,txt0_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(_body,elt6_);
Element elt9_=el(_doc86,BR);
ad(_body,elt9_);
Element elt10_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_181_79));
at(elt10_,attrs5_);
Element elt11_=el(_doc86,PARAM);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_181_80));
at(elt11_,attrs6_);
ad(elt10_,elt11_);
Element elt12_=el(_doc86,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_181_81));
at(elt12_,attrs7_);
ad(elt10_,elt12_);
ad(_body,elt10_);
Element elt13_=el(_doc86,BR);
ad(_body,elt13_);
Element elt14_=el(_doc86,BR);
ad(_body,elt14_);
}
static void build3(Element _body,Document _doc86){
Element elt0_=el(_doc86,H1);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_181_82));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc86,BR);
ad(_body,elt2_);
Element elt3_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_181_83));
at(elt3_,attrs1_);
Element elt4_=el(_doc86,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_84));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(_body,elt3_);
Element elt5_=el(_doc86,TABLE);
Element elt6_=el(_doc86,CAPTION);
Element elt7_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_181_85));
at(elt7_,attrs3_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc86,THEAD);
Element elt9_=el(_doc86,TR);
Element elt10_=el(_doc86,TH);
Element elt11_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_181_86));
at(elt11_,attrs4_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt12_=el(_doc86,TH);
Element elt13_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_181_87));
at(elt13_,attrs5_);
ad(elt12_,elt13_);
ad(elt9_,elt12_);
Element elt14_=el(_doc86,TH);
Element elt15_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_181_88));
at(elt15_,attrs6_);
Element elt16_=el(_doc86,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_181_89));
at(elt16_,attrs7_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
ad(elt9_,elt14_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
Element elt17_=el(_doc86,TBODY);
Element elt18_=el(_doc86,C_FOR);
CustList<Attr> attrs8_=al(3);
attrs8_.add(at(LIST,C_P_181_90));
attrs8_.add(at(VAR,C_P_181_91));
attrs8_.add(at(CLASSNAME,C_P_181_92));
at(elt18_,attrs8_);
Element elt19_=el(_doc86,TR);
Element elt20_=el(_doc86,TD);
Text txt0_=tx(_doc86,C_P_181_93);
ad(elt20_,txt0_);
ad(elt19_,elt20_);
Element elt21_=el(_doc86,TD);
Text txt1_=tx(_doc86,C_P_181_94);
ad(elt21_,txt1_);
ad(elt19_,elt21_);
Element elt22_=el(_doc86,TD);
Text txt2_=tx(_doc86,C_P_181_95);
ad(elt22_,txt2_);
ad(elt19_,elt22_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
ad(elt5_,elt17_);
ad(_body,elt5_);
Element elt23_=el(_doc86,BR);
ad(_body,elt23_);
Element elt24_=el(_doc86,BR);
ad(_body,elt24_);
}
static void build4(Element _body,Document _doc86){
Element elt0_=el(_doc86,H1);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_181_96));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc86,BR);
ad(_body,elt2_);
Element elt3_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_181_97));
at(elt3_,attrs1_);
Element elt4_=el(_doc86,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_98));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(_body,elt3_);
Element elt5_=el(_doc86,TABLE);
Element elt6_=el(_doc86,CAPTION);
Element elt7_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_181_99));
at(elt7_,attrs3_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc86,THEAD);
Element elt9_=el(_doc86,TR);
Element elt10_=el(_doc86,TH);
Element elt11_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_181_100));
at(elt11_,attrs4_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt12_=el(_doc86,TH);
Element elt13_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_181_101));
at(elt13_,attrs5_);
ad(elt12_,elt13_);
ad(elt9_,elt12_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
Element elt14_=el(_doc86,TBODY);
Element elt15_=el(_doc86,C_FOR);
CustList<Attr> attrs6_=al(3);
attrs6_.add(at(CLASSNAME,C_P_181_102));
attrs6_.add(at(LIST,C_P_181_103));
attrs6_.add(at(VAR,C_P_181_104));
at(elt15_,attrs6_);
Element elt16_=el(_doc86,TR);
Element elt17_=el(_doc86,TD);
Text txt0_=tx(_doc86,C_P_181_105);
ad(elt17_,txt0_);
ad(elt16_,elt17_);
Element elt18_=el(_doc86,TD);
Element elt19_=el(_doc86,A);
CustList<Attr> attrs7_=al(2);
attrs7_.add(at(C_COMMAND,C_P_181_106));
attrs7_.add(at(HREF,C_P_181_107));
at(elt19_,attrs7_);
Text txt1_=tx(_doc86,C_P_181_108);
ad(elt19_,txt1_);
ad(elt18_,elt19_);
ad(elt16_,elt18_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
ad(elt5_,elt14_);
ad(_body,elt5_);
Element elt20_=el(_doc86,BR);
ad(_body,elt20_);
Element elt21_=el(_doc86,BR);
ad(_body,elt21_);
Element elt22_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_181_109));
at(elt22_,attrs8_);
Element elt23_=el(_doc86,PARAM);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_181_110));
at(elt23_,attrs9_);
ad(elt22_,elt23_);
ad(_body,elt22_);
Element elt24_=el(_doc86,TABLE);
Element elt25_=el(_doc86,CAPTION);
Element elt26_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_181_111));
at(elt26_,attrs10_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
Element elt27_=el(_doc86,THEAD);
Element elt28_=el(_doc86,TR);
Element elt29_=el(_doc86,TH);
Element elt30_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_181_112));
at(elt30_,attrs11_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
Element elt31_=el(_doc86,TH);
Element elt32_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_181_113));
at(elt32_,attrs12_);
ad(elt31_,elt32_);
ad(elt28_,elt31_);
ad(elt27_,elt28_);
ad(elt24_,elt27_);
Element elt33_=el(_doc86,TBODY);
Element elt34_=el(_doc86,C_FOR);
CustList<Attr> attrs13_=al(5);
attrs13_.add(at(KEY,C_P_181_114));
attrs13_.add(at(MAP,C_P_181_115));
attrs13_.add(at(VALUE,C_P_181_116));
attrs13_.add(at(KEYCLASSNAME,C_P_181_117));
attrs13_.add(at(VARCLASSNAME,C_P_181_118));
at(elt34_,attrs13_);
Element elt35_=el(_doc86,TR);
Element elt36_=el(_doc86,TD);
Text txt2_=tx(_doc86,C_P_181_119);
ad(elt36_,txt2_);
ad(elt35_,elt36_);
Element elt37_=el(_doc86,TD);
Element elt38_=el(_doc86,A);
CustList<Attr> attrs14_=al(2);
attrs14_.add(at(C_COMMAND,C_P_181_120));
attrs14_.add(at(HREF,C_P_181_121));
at(elt38_,attrs14_);
Text txt3_=tx(_doc86,C_P_181_122);
ad(elt38_,txt3_);
ad(elt37_,elt38_);
ad(elt35_,elt37_);
ad(elt34_,elt35_);
ad(elt33_,elt34_);
ad(elt24_,elt33_);
ad(_body,elt24_);
Element elt39_=el(_doc86,BR);
ad(_body,elt39_);
Element elt40_=el(_doc86,BR);
ad(_body,elt40_);
Element elt41_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_181_123));
at(elt41_,attrs15_);
Element elt42_=el(_doc86,PARAM);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_181_124));
at(elt42_,attrs16_);
ad(elt41_,elt42_);
ad(_body,elt41_);
Element elt43_=el(_doc86,TABLE);
Element elt44_=el(_doc86,CAPTION);
Element elt45_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_181_125));
at(elt45_,attrs17_);
ad(elt44_,elt45_);
ad(elt43_,elt44_);
Element elt46_=el(_doc86,THEAD);
Element elt47_=el(_doc86,TR);
Element elt48_=el(_doc86,TH);
Element elt49_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_181_126));
at(elt49_,attrs18_);
ad(elt48_,elt49_);
ad(elt47_,elt48_);
Element elt50_=el(_doc86,TH);
Element elt51_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_181_127));
at(elt51_,attrs19_);
ad(elt50_,elt51_);
ad(elt47_,elt50_);
ad(elt46_,elt47_);
ad(elt43_,elt46_);
Element elt52_=el(_doc86,TBODY);
Element elt53_=el(_doc86,C_FOR);
CustList<Attr> attrs20_=al(5);
attrs20_.add(at(KEY,C_P_181_128));
attrs20_.add(at(MAP,C_P_181_129));
attrs20_.add(at(VALUE,C_P_181_130));
attrs20_.add(at(KEYCLASSNAME,C_P_181_131));
attrs20_.add(at(VARCLASSNAME,C_P_181_132));
at(elt53_,attrs20_);
Element elt54_=el(_doc86,TR);
Element elt55_=el(_doc86,TD);
Text txt4_=tx(_doc86,C_P_181_133);
ad(elt55_,txt4_);
ad(elt54_,elt55_);
Element elt56_=el(_doc86,TD);
Element elt57_=el(_doc86,A);
CustList<Attr> attrs21_=al(2);
attrs21_.add(at(C_COMMAND,C_P_181_134));
attrs21_.add(at(HREF,C_P_181_135));
at(elt57_,attrs21_);
Text txt5_=tx(_doc86,C_P_181_136);
ad(elt57_,txt5_);
ad(elt56_,elt57_);
ad(elt54_,elt56_);
ad(elt53_,elt54_);
ad(elt52_,elt53_);
ad(elt43_,elt52_);
ad(_body,elt43_);
Element elt58_=el(_doc86,BR);
ad(_body,elt58_);
Element elt59_=el(_doc86,BR);
ad(_body,elt59_);
Element elt60_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_181_137));
at(elt60_,attrs22_);
Element elt61_=el(_doc86,PARAM);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_181_138));
at(elt61_,attrs23_);
ad(elt60_,elt61_);
ad(_body,elt60_);
Element elt62_=el(_doc86,UL);
Element elt63_=el(_doc86,C_FOR);
CustList<Attr> attrs24_=al(2);
attrs24_.add(at(LIST,C_P_181_139));
attrs24_.add(at(VAR,C_P_181_140));
at(elt63_,attrs24_);
Element elt64_=el(_doc86,LI);
Element elt65_=el(_doc86,A);
CustList<Attr> attrs25_=al(2);
attrs25_.add(at(C_COMMAND,C_P_181_141));
attrs25_.add(at(HREF,C_P_181_142));
at(elt65_,attrs25_);
Text txt6_=tx(_doc86,C_P_181_143);
ad(elt65_,txt6_);
ad(elt64_,elt65_);
ad(elt63_,elt64_);
ad(elt62_,elt63_);
ad(_body,elt62_);
Element elt66_=el(_doc86,BR);
ad(_body,elt66_);
}
static void build5(Element _body,Document _doc86){
Element elt0_=el(_doc86,H1);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_181_144));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc86,BR);
ad(_body,elt2_);
Element elt3_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_181_145));
at(elt3_,attrs1_);
Element elt4_=el(_doc86,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_146));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(_body,elt3_);
Element elt5_=el(_doc86,UL);
Element elt6_=el(_doc86,C_FOR);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(LIST,C_P_181_147));
attrs3_.add(at(VAR,C_P_181_148));
at(elt6_,attrs3_);
Element elt7_=el(_doc86,LI);
Element elt8_=el(_doc86,A);
CustList<Attr> attrs4_=al(2);
attrs4_.add(at(C_COMMAND,C_P_181_149));
attrs4_.add(at(HREF,C_P_181_150));
at(elt8_,attrs4_);
Text txt0_=tx(_doc86,C_P_181_151);
ad(elt8_,txt0_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(_body,elt5_);
Element elt9_=el(_doc86,BR);
ad(_body,elt9_);
Element elt10_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_181_152));
at(elt10_,attrs5_);
Element elt11_=el(_doc86,PARAM);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_181_153));
at(elt11_,attrs6_);
ad(elt10_,elt11_);
Element elt12_=el(_doc86,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_181_154));
at(elt12_,attrs7_);
ad(elt10_,elt12_);
ad(_body,elt10_);
}
static void build6(Element _body,Document _doc86){
Element elt0_=el(_doc86,BR);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc86){
Element elt0_=el(_doc86,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_181_155));
at(elt0_,attrs0_);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_181_156));
at(elt1_,attrs1_);
Element elt2_=el(_doc86,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_181_157));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc86,UL);
Element elt4_=el(_doc86,C_FOR);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(CLASSNAME,C_P_181_158));
attrs3_.add(at(LIST,C_P_181_159));
attrs3_.add(at(VAR,C_P_181_160));
at(elt4_,attrs3_);
Element elt5_=el(_doc86,C_IF);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(CONDITION,C_P_181_161));
at(elt5_,attrs4_);
Element elt6_=el(_doc86,LI);
Text txt0_=tx(_doc86,C_P_181_162);
ad(elt6_,txt0_);
Element elt7_=el(_doc86,BR);
ad(elt6_,elt7_);
Element elt8_=el(_doc86,C_IF);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(CONDITION,C_P_181_163));
at(elt8_,attrs5_);
Element elt9_=el(_doc86,UL);
Element elt10_=el(_doc86,C_FOR);
CustList<Attr> attrs6_=al(2);
attrs6_.add(at(LIST,C_P_181_164));
attrs6_.add(at(VAR,C_P_181_165));
at(elt10_,attrs6_);
Element elt11_=el(_doc86,C_IF);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(CONDITION,C_P_181_166));
at(elt11_,attrs7_);
Element elt12_=el(_doc86,LI);
Element elt13_=el(_doc86,A);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(C_COMMAND,C_P_181_167));
at(elt13_,attrs8_);
Text txt1_=tx(_doc86,C_P_181_168);
ad(elt13_,txt1_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
ad(elt6_,elt8_);
Element elt14_=el(_doc86,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_181_169));
at(elt14_,attrs9_);
Element elt15_=el(_doc86,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_181_170));
at(elt15_,attrs10_);
Element elt16_=el(_doc86,A);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(C_COMMAND,C_P_181_171));
at(elt16_,attrs11_);
Element elt17_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_181_172));
at(elt17_,attrs12_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
ad(elt6_,elt14_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt18_=el(_doc86,BR);
ad(elt0_,elt18_);
Element elt19_=el(_doc86,MAP);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(WIDTH,C_P_181_173));
at(elt19_,attrs13_);
Element elt20_=el(_doc86,C_FOR);
CustList<Attr> attrs14_=al(5);
attrs14_.add(at(KEY,C_P_181_174));
attrs14_.add(at(MAP,C_P_181_175));
attrs14_.add(at(VALUE,C_P_181_176));
attrs14_.add(at(KEYCLASSNAME,C_P_181_177));
attrs14_.add(at(VARCLASSNAME,C_P_181_178));
at(elt20_,attrs14_);
Element elt21_=el(_doc86,A);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(TITLE,C_P_181_179));
at(elt21_,attrs15_);
Element elt22_=el(_doc86,C_IMG);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(SRC,C_P_181_180));
at(elt22_,attrs16_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt0_,elt19_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc86){
Element elt0_=el(_doc86,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_181_181));
attrs0_.add(at(HREF,C_P_181_182));
at(elt0_,attrs0_);
Element elt1_=el(_doc86,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_181_183));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
