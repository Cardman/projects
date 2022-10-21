package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageFightFightdetail extends PageAikiCommon{
private static final String C_P_207_0="javahtml";
private static final String C_P_207_1="fight_detail";
private static final String C_P_207_2="msg_fight,title_detail_fight";
private static final String C_P_207_3="web_fight/css/fight.css";
private static final String C_P_207_4="stylesheet";
private static final String C_P_207_5="text/css";
private static final String C_P_207_6="$f()";
private static final String C_P_207_7="msg_team,fight";
private static final String C_P_207_8="$d()";
private static final String C_P_207_9="msg_fight,refresh";
private static final String C_P_207_10="!sortedFighters.isEmpty()";
private static final String C_P_207_11="msg_fight,sorted_fighters_fct_choices";
private static final String C_P_207_12="sortedFighters";
private static final String C_P_207_13="f";
private static final String C_P_207_14="{getFighter(([f]))}";
private static final String C_P_207_15="!sortedFightersWildFight.isEmpty()";
private static final String C_P_207_16="msg_fight,sorted_fighters_fct_choices_wild";
private static final String C_P_207_17="m";
private static final String C_P_207_18="sortedFightersWildFight";
private static final String C_P_207_19="f";
private static final String C_P_207_20="java.lang.String";
private static final String C_P_207_21="ls";
private static final String C_P_207_22="{m}";
private static final String C_P_207_23="f";
private static final String C_P_207_24="p";
private static final String C_P_207_25="{getFighterWildFight(([m]),([p]))}";
private static final String C_P_207_26="msg_fight,damage_fct_choices";
private static final String C_P_207_27="msg_fight,damage_fct_choices_key_one";
private static final String C_P_207_28="msg_fight,damage_fct_choices_key_two";
private static final String C_P_207_29="msg_fight,damage_fct_choices_key_three";
private static final String C_P_207_30="msg_fight,damage_fct_choices_damage";
private static final String C_P_207_31="aiki.beans.facade.fight.KeyHypothesis";
private static final String C_P_207_32="damage";
private static final String C_P_207_33="d";
private static final String C_P_207_34="{d.getPlayerPokemon()}";
private static final String C_P_207_35="{d.getMove()}";
private static final String C_P_207_36="&nbsp;{d.getTargetPokemon()}";
private static final String C_P_207_37="d.isBelongsToUser()";
private static final String C_P_207_38="msg_fight,damage_fct_choices_player";
private static final String C_P_207_39="!d.isBelongsToUser()";
private static final String C_P_207_40="msg_fight,damage_fct_choices_foe";
//private static final String C_P_207_41="{d.getNumberTarget()}";
private static final String C_P_207_42="{d.getDamage()} - {d.getDamageSecond()}";
private static final String C_P_207_43="!allyChoice.isEmpty()";
private static final String C_P_207_44="msg_fight,ally_choices";
private static final String C_P_207_45="msg_fight,ally_choices_key";
private static final String C_P_207_46="msg_fight,ally_choices_key_team";
private static final String C_P_207_47="msg_fight,ally_choices_key_place";
private static final String C_P_207_48="msg_fight,ally_choices_key_name";
private static final String C_P_207_49="msg_fight,ally_choices_value";
private static final String C_P_207_50="msg_fight,ally_choices_value_team";
private static final String C_P_207_51="msg_fight,ally_choices_value_place";
private static final String C_P_207_52="msg_fight,ally_choices_value_name";
private static final String C_P_207_53="m";
private static final String C_P_207_54="aiki.game.fight.util.MoveTarget";
private static final String C_P_207_55="allyChoice";
private static final String C_P_207_56="a";
private static final String C_P_207_57="aiki.game.fight.util.MoveTarget";
private static final String C_P_207_58="{m.getMove()}";
private static final String C_P_207_59="isFoeTargetChoiceTeam(([m]))";
private static final String C_P_207_60="msg_fight,ally_choices_foe";
private static final String C_P_207_61="!isFoeTargetChoiceTeam(([m]))";
private static final String C_P_207_62="msg_fight,ally_choices_player";
private static final String C_P_207_63="!isBackTargetChoiceTeam(([m]))";
private static final String C_P_207_64="{m.getTarget().getPosition()}";
private static final String C_P_207_65="{getTargetNameAllyChoiceCondition(([m]))}";
private static final String C_P_207_66="isBackTargetChoiceTeam(([m]))";
private static final String C_P_207_67="msg_fight,ally_choices_no";
private static final String C_P_207_68="msg_fight,ally_choices_no";
private static final String C_P_207_69="{a.getMove()}";
private static final String C_P_207_70="isFoeTargetTeam(([m]))";
private static final String C_P_207_71="msg_fight,ally_choices_foe";
private static final String C_P_207_72="!isFoeTargetTeam(([m]))";
private static final String C_P_207_73="msg_fight,ally_choices_player";
private static final String C_P_207_74="!isBackTargetTeam(([m]))";
private static final String C_P_207_75="{a.getTarget().getPosition()}";
private static final String C_P_207_76="{getTargetNameAllyChoice(([m]))}";
private static final String C_P_207_77="isBackTargetTeam(([m]))";
private static final String C_P_207_78="msg_fight,ally_choices_no";
private static final String C_P_207_79="msg_fight,ally_choices_no";
private static final String C_P_207_80="!foeChoices.isEmpty()";
private static final String C_P_207_81="msg_fight,foe_choices";
private static final String C_P_207_82="msg_fight,foe_choices_key";
private static final String C_P_207_83="msg_fight,foe_choices_key_name";
private static final String C_P_207_84="msg_fight,foe_choices_value";
private static final String C_P_207_85="msg_fight,foe_choices_value_team";
private static final String C_P_207_86="msg_fight,foe_choices_value_place";
private static final String C_P_207_87="msg_fight,foe_choices_value_name";
private static final String C_P_207_88="p";
private static final String C_P_207_89="foeChoices";
private static final String C_P_207_90="a";
private static final String C_P_207_91="java.lang.Byte";
private static final String C_P_207_92="aiki.game.fight.util.MoveTarget";
private static final String C_P_207_93="{p}";
private static final String C_P_207_94="{getFoeFighterName(([p]))}";
private static final String C_P_207_95="{a.getMove()}";
private static final String C_P_207_96="isChosenTarget(([p]))";
private static final String C_P_207_97="isFoeTargetChTeam(([p]))";
private static final String C_P_207_98="msg_fight,foe_choices_foe";
private static final String C_P_207_99="!isFoeTargetChTeam(([p]))";
private static final String C_P_207_100="msg_fight,foe_choices_player";
private static final String C_P_207_101="{a.getTarget().getPosition()}";
private static final String C_P_207_102="{getTargetNameFoeChoice(([p]))}";
private static final String C_P_207_103="!isChosenTarget(([p]))";
private static final String C_P_207_104="msg_fight,foe_choices_no";
private static final String C_P_207_105="msg_fight,foe_choices_no";
private static final String C_P_207_106="msg_fight,foe_choices_no";
private PageFightFightdetail(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc112){
Element elt0_=el(_doc112,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_207_0));
attrs0_.add(at(C_BEAN,C_P_207_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc112,HEAD);
Element elt2_=el(_doc112,TITLE);
Element elt3_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc112,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_207_3));
attrs2_.add(at(REL,C_P_207_4));
attrs2_.add(at(TYPE,C_P_207_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc112,BODY);
build0(elt5_,_doc112);
build1(elt5_,_doc112);
build2(elt5_,_doc112);
build3(elt5_,_doc112);
build4(elt5_,_doc112);
build5(elt5_,_doc112);
build6(elt5_,_doc112);
build7(elt5_,_doc112);
ad(elt0_,elt5_);
_doc112.appendChild(elt0_);
}
static void build0(Element _body,Document _doc112){
Element elt0_=el(_doc112,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_207_6));
at(elt0_,attrs0_);
Element elt1_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_7));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc112,BR);
ad(_body,elt2_);
}
static void build1(Element _body,Document _doc112){
Element elt0_=el(_doc112,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_207_8));
at(elt0_,attrs0_);
Element elt1_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_9));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc112,BR);
ad(_body,elt2_);
}
static void build2(Element _body,Document _doc112){
Element elt0_=el(_doc112,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_207_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_11));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc112,UL);
Element elt3_=el(_doc112,C_FOR);
CustList<Attr> attrs2_=al(2);
attrs2_.add(at(LIST,C_P_207_12));
attrs2_.add(at(VAR,C_P_207_13));
at(elt3_,attrs2_);
Element elt4_=el(_doc112,LI);
Text txt0_=tx(_doc112,C_P_207_14);
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc112){
Element elt0_=el(_doc112,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_207_15));
at(elt0_,attrs0_);
Element elt1_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_16));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc112,C_FOR);
CustList<Attr> attrs2_=al(5);
attrs2_.add(at(KEY,C_P_207_17));
attrs2_.add(at(MAP,C_P_207_18));
attrs2_.add(at(VALUE,C_P_207_19));
attrs2_.add(at(KEYCLASSNAME,C_P_207_20));
attrs2_.add(at(VARCLASSNAME,C_P_207_21));
at(elt2_,attrs2_);
Text txt0_=tx(_doc112,C_P_207_22);
ad(elt2_,txt0_);
Element elt3_=el(_doc112,BR);
ad(elt2_,elt3_);
Element elt4_=el(_doc112,UL);
Element elt5_=el(_doc112,C_FOR);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(LIST,C_P_207_23));
attrs3_.add(at(VAR,C_P_207_24));
at(elt5_,attrs3_);
Element elt6_=el(_doc112,LI);
Text txt1_=tx(_doc112,C_P_207_25);
ad(elt6_,txt1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt2_,elt4_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc112){
Element elt0_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_207_26));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc112){
Element elt0_=el(_doc112,TABLE);
Element elt1_=el(_doc112,THEAD);
Element elt2_=el(_doc112,TR);
Element elt3_=el(_doc112,TH);
Element elt4_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_207_27));
at(elt4_,attrs0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt5_=el(_doc112,TH);
Element elt6_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_28));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt2_,elt5_);
Element elt7_=el(_doc112,TH);
Element elt8_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_207_29));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt2_,elt7_);
Element elt9_=el(_doc112,TH);
Element elt10_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_207_30));
at(elt10_,attrs3_);
ad(elt9_,elt10_);
ad(elt2_,elt9_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt11_=el(_doc112,TBODY);
Element elt12_=el(_doc112,C_FOR);
CustList<Attr> attrs4_=al(3);
attrs4_.add(at(CLASSNAME,C_P_207_31));
attrs4_.add(at(LIST,C_P_207_32));
attrs4_.add(at(VAR,C_P_207_33));
at(elt12_,attrs4_);
Element elt13_=el(_doc112,TR);
Element elt14_=el(_doc112,TD);
Text txt0_=tx(_doc112,C_P_207_34);
ad(elt14_,txt0_);
ad(elt13_,elt14_);
Element elt15_=el(_doc112,TD);
Text txt1_=tx(_doc112,C_P_207_35);
ad(elt15_,txt1_);
ad(elt13_,elt15_);
Element elt16_=el(_doc112,TD);
Element elt17_=el(_doc112,C_IF);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(CONDITION,C_P_207_37));
at(elt17_,attrs5_);
Element elt18_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_207_38));
at(elt18_,attrs6_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
Text txt2_=tx(_doc112,C_P_207_36);
ad(elt16_,txt2_);
Element elt19_=el(_doc112,C_IF);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(CONDITION,C_P_207_39));
at(elt19_,attrs7_);
Element elt20_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_207_40));
at(elt20_,attrs8_);
ad(elt19_,elt20_);
ad(elt16_,elt19_);
//Text txt3_=tx(_doc112,C_P_207_41);
//ad(elt16_,txt3_);
ad(elt13_,elt16_);
Element elt21_=el(_doc112,TD);
Text txt4_=tx(_doc112,C_P_207_42);
ad(elt21_,txt4_);
ad(elt13_,elt21_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc112){
Element elt0_=el(_doc112,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_207_43));
at(elt0_,attrs0_);
Element elt1_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_44));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc112,TABLE);
Element elt3_=el(_doc112,THEAD);
Element elt4_=el(_doc112,TR);
Element elt5_=el(_doc112,TH);
Element elt6_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_207_45));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc112,TH);
Element elt8_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_207_46));
at(elt8_,attrs3_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
Element elt9_=el(_doc112,TH);
Element elt10_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_207_47));
at(elt10_,attrs4_);
ad(elt9_,elt10_);
ad(elt4_,elt9_);
Element elt11_=el(_doc112,TH);
Element elt12_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_207_48));
at(elt12_,attrs5_);
ad(elt11_,elt12_);
ad(elt4_,elt11_);
Element elt13_=el(_doc112,TH);
Element elt14_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_207_49));
at(elt14_,attrs6_);
ad(elt13_,elt14_);
ad(elt4_,elt13_);
Element elt15_=el(_doc112,TH);
Element elt16_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_207_50));
at(elt16_,attrs7_);
ad(elt15_,elt16_);
ad(elt4_,elt15_);
Element elt17_=el(_doc112,TH);
Element elt18_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_207_51));
at(elt18_,attrs8_);
ad(elt17_,elt18_);
ad(elt4_,elt17_);
Element elt19_=el(_doc112,TH);
Element elt20_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_207_52));
at(elt20_,attrs9_);
ad(elt19_,elt20_);
ad(elt4_,elt19_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt21_=el(_doc112,TBODY);
Element elt22_=el(_doc112,C_FOR);
CustList<Attr> attrs10_=al(5);
attrs10_.add(at(KEY,C_P_207_53));
attrs10_.add(at(KEYCLASSNAME,C_P_207_54));
attrs10_.add(at(MAP,C_P_207_55));
attrs10_.add(at(VALUE,C_P_207_56));
attrs10_.add(at(VARCLASSNAME,C_P_207_57));
at(elt22_,attrs10_);
Element elt23_=el(_doc112,TR);
Element elt24_=el(_doc112,TD);
Text txt0_=tx(_doc112,C_P_207_58);
ad(elt24_,txt0_);
ad(elt23_,elt24_);
Element elt25_=el(_doc112,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_207_59));
at(elt25_,attrs11_);
Element elt26_=el(_doc112,TD);
Element elt27_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_207_60));
at(elt27_,attrs12_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt23_,elt25_);
Element elt28_=el(_doc112,C_IF);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(CONDITION,C_P_207_61));
at(elt28_,attrs13_);
Element elt29_=el(_doc112,TD);
Element elt30_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_207_62));
at(elt30_,attrs14_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
ad(elt23_,elt28_);
Element elt31_=el(_doc112,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_207_63));
at(elt31_,attrs15_);
Element elt32_=el(_doc112,TD);
Text txt1_=tx(_doc112,C_P_207_64);
ad(elt32_,txt1_);
ad(elt31_,elt32_);
Element elt33_=el(_doc112,TD);
Text txt2_=tx(_doc112,C_P_207_65);
ad(elt33_,txt2_);
ad(elt31_,elt33_);
ad(elt23_,elt31_);
Element elt34_=el(_doc112,C_IF);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(CONDITION,C_P_207_66));
at(elt34_,attrs16_);
Element elt35_=el(_doc112,TD);
Element elt36_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_207_67));
at(elt36_,attrs17_);
ad(elt35_,elt36_);
ad(elt34_,elt35_);
Element elt37_=el(_doc112,TD);
Element elt38_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_207_68));
at(elt38_,attrs18_);
ad(elt37_,elt38_);
ad(elt34_,elt37_);
ad(elt23_,elt34_);
Element elt39_=el(_doc112,TD);
Text txt3_=tx(_doc112,C_P_207_69);
ad(elt39_,txt3_);
ad(elt23_,elt39_);
Element elt40_=el(_doc112,C_IF);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(CONDITION,C_P_207_70));
at(elt40_,attrs19_);
Element elt41_=el(_doc112,TD);
Element elt42_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_207_71));
at(elt42_,attrs20_);
ad(elt41_,elt42_);
ad(elt40_,elt41_);
ad(elt23_,elt40_);
Element elt43_=el(_doc112,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_207_72));
at(elt43_,attrs21_);
Element elt44_=el(_doc112,TD);
Element elt45_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_207_73));
at(elt45_,attrs22_);
ad(elt44_,elt45_);
ad(elt43_,elt44_);
ad(elt23_,elt43_);
Element elt46_=el(_doc112,C_IF);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(CONDITION,C_P_207_74));
at(elt46_,attrs23_);
Element elt47_=el(_doc112,TD);
Text txt4_=tx(_doc112,C_P_207_75);
ad(elt47_,txt4_);
ad(elt46_,elt47_);
Element elt48_=el(_doc112,TD);
Text txt5_=tx(_doc112,C_P_207_76);
ad(elt48_,txt5_);
ad(elt46_,elt48_);
ad(elt23_,elt46_);
Element elt49_=el(_doc112,C_IF);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(CONDITION,C_P_207_77));
at(elt49_,attrs24_);
Element elt50_=el(_doc112,TD);
Element elt51_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_207_78));
at(elt51_,attrs25_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
Element elt52_=el(_doc112,TD);
Element elt53_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs26_=al(1);
attrs26_.add(at(VALUE,C_P_207_79));
at(elt53_,attrs26_);
ad(elt52_,elt53_);
ad(elt49_,elt52_);
ad(elt23_,elt49_);
ad(elt22_,elt23_);
ad(elt21_,elt22_);
ad(elt2_,elt21_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc112){
Element elt0_=el(_doc112,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_207_80));
at(elt0_,attrs0_);
Element elt1_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_207_81));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc112,TABLE);
Element elt3_=el(_doc112,THEAD);
Element elt4_=el(_doc112,TR);
Element elt5_=el(_doc112,TH);
Element elt6_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_207_82));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc112,TH);
Element elt8_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_207_83));
at(elt8_,attrs3_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
Element elt9_=el(_doc112,TH);
Element elt10_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_207_84));
at(elt10_,attrs4_);
ad(elt9_,elt10_);
ad(elt4_,elt9_);
Element elt11_=el(_doc112,TH);
Element elt12_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_207_85));
at(elt12_,attrs5_);
ad(elt11_,elt12_);
ad(elt4_,elt11_);
Element elt13_=el(_doc112,TH);
Element elt14_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_207_86));
at(elt14_,attrs6_);
ad(elt13_,elt14_);
ad(elt4_,elt13_);
Element elt15_=el(_doc112,TH);
Element elt16_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_207_87));
at(elt16_,attrs7_);
ad(elt15_,elt16_);
ad(elt4_,elt15_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt17_=el(_doc112,TBODY);
Element elt18_=el(_doc112,C_FOR);
CustList<Attr> attrs8_=al(5);
attrs8_.add(at(KEY,C_P_207_88));
attrs8_.add(at(MAP,C_P_207_89));
attrs8_.add(at(VALUE,C_P_207_90));
attrs8_.add(at(KEYCLASSNAME,C_P_207_91));
attrs8_.add(at(VARCLASSNAME,C_P_207_92));
at(elt18_,attrs8_);
Element elt19_=el(_doc112,TR);
Element elt20_=el(_doc112,TD);
Text txt0_=tx(_doc112,C_P_207_93);
ad(elt20_,txt0_);
ad(elt19_,elt20_);
Element elt21_=el(_doc112,TD);
Text txt1_=tx(_doc112,C_P_207_94);
ad(elt21_,txt1_);
ad(elt19_,elt21_);
Element elt22_=el(_doc112,TD);
Text txt2_=tx(_doc112,C_P_207_95);
ad(elt22_,txt2_);
ad(elt19_,elt22_);
Element elt23_=el(_doc112,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_207_96));
at(elt23_,attrs9_);
Element elt24_=el(_doc112,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_207_97));
at(elt24_,attrs10_);
Element elt25_=el(_doc112,TD);
Element elt26_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_207_98));
at(elt26_,attrs11_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
Element elt27_=el(_doc112,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_207_99));
at(elt27_,attrs12_);
Element elt28_=el(_doc112,TD);
Element elt29_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_207_100));
at(elt29_,attrs13_);
ad(elt28_,elt29_);
ad(elt27_,elt28_);
ad(elt23_,elt27_);
Element elt30_=el(_doc112,TD);
Text txt3_=tx(_doc112,C_P_207_101);
ad(elt30_,txt3_);
ad(elt23_,elt30_);
Element elt31_=el(_doc112,TD);
Text txt4_=tx(_doc112,C_P_207_102);
ad(elt31_,txt4_);
ad(elt23_,elt31_);
ad(elt19_,elt23_);
Element elt32_=el(_doc112,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_207_103));
at(elt32_,attrs14_);
Element elt33_=el(_doc112,TD);
Element elt34_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_207_104));
at(elt34_,attrs15_);
ad(elt33_,elt34_);
ad(elt32_,elt33_);
Element elt35_=el(_doc112,TD);
Element elt36_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_207_105));
at(elt36_,attrs16_);
ad(elt35_,elt36_);
ad(elt32_,elt35_);
Element elt37_=el(_doc112,TD);
Element elt38_=el(_doc112,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_207_106));
at(elt38_,attrs17_);
ad(elt37_,elt38_);
ad(elt32_,elt37_);
ad(elt19_,elt32_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
ad(elt2_,elt17_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
}
