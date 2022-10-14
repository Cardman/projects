package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataGeneralGeneral extends PageAikiCommon{
private static final String C_P_113_0="javahtml";
private static final String C_P_113_1="gereral_data";
private static final String C_P_113_2="msg_generalhelp,title";
private static final String C_P_113_3="web/css/pokedex.css";
private static final String C_P_113_4="stylesheet";
private static final String C_P_113_5="text/css";
private static final String C_P_113_6=GO_TO_IND;
private static final String C_P_113_7="";
private static final String C_P_113_8="msg_generalhelp,index";
private static final String C_P_113_9="msg_generalhelp,goal_0";
private static final String C_P_113_10="msg_generalhelp,goal_0_0";
private static final String C_P_113_11="msg_generalhelp,goal_0_1";
private static final String C_P_113_12="msg_generalhelp,goal_0_2";
private static final String C_P_113_13="msg_generalhelp,goal_0_3";
private static final String C_P_113_14="maxLevel";
private static final String C_P_113_15="msg_generalhelp,goal_0_4";
private static final String C_P_113_16="msg_generalhelp,goal_0_5";
private static final String C_P_113_17="maxEv";
private static final String C_P_113_18="msg_generalhelp,goal_0_6";
private static final String C_P_113_19="happinessMax";
private static final String C_P_113_20="msg_generalhelp,goal_0_7";
private static final String C_P_113_21="msg_generalhelp,goal_1";
private static final String C_P_113_22="begin";
private static final String C_P_113_23="msg_generalhelp,goal_2";
private static final String C_P_113_24="font-size: 0";
private static final String C_P_113_25="{getMapWidth()}";
private static final String C_P_113_26="p";
private static final String C_P_113_27="miniMap";
private static final String C_P_113_28="i";
private static final String C_P_113_29="java.lang.Object";
private static final String C_P_113_30="java.lang.Object";
private static final String C_P_113_31="{getPlaceName(([p]))}";
private static final String C_P_113_32="{getMiniMapImage(([p]))}";
private static final String C_P_113_33="msg_generalhelp,goal_3";
private static final String C_P_113_34="{unlockedCity}";
private static final String C_P_113_35="msg_generalhelp,help_0";
private static final String C_P_113_36="{getImage()}";
private static final String C_P_113_37="msg_pokemon_npc,name";
private static final String C_P_113_38="$clickName";
private static final String C_P_113_39="{getName()}";
private static final String C_P_113_40="msg_pokemon_npc,gender";
private static final String C_P_113_41="{getGender()}";
private static final String C_P_113_42="msg_pokemon_npc,level";
private static final String C_P_113_43="{getLevel()}";
private static final String C_P_113_44="msg_pokemon_npc,ability";
private static final String C_P_113_45="$clickAbility";
private static final String C_P_113_46="{getAbility()}";
private static final String C_P_113_47="msg_pokemon_npc,item";
private static final String C_P_113_48="firstPokemonHasItem()";
private static final String C_P_113_49="$clickItem";
private static final String C_P_113_50="{getItem()}";
private static final String C_P_113_51="!firstPokemonHasItem()";
private static final String C_P_113_52="msg_pokemon_npc,item_no";
private static final String C_P_113_53="msg_pokemon_npc,moves";
private static final String C_P_113_54="getMovesAtLevel()";
private static final String C_P_113_55="m";
private static final String C_P_113_56="$clickMove({([m])})";
private static final String C_P_113_57="{getMove(([m]))}";
private static final String C_P_113_58="msg_generalhelp,help_1";
private static final String C_P_113_59="nbMaxTeam";
private static final String C_P_113_60="msg_generalhelp,help_2";
private static final String C_P_113_61="minLevel";
private static final String C_P_113_62="maxLevel";
private static final String C_P_113_63="msg_generalhelp,help_3";
private static final String C_P_113_64="nbMaxMoves";
private static final String C_P_113_65="msg_generalhelp,help_4";
private static final String C_P_113_66="maxPp";
private static final String C_P_113_67="msg_generalhelp,help_5";
private static final String C_P_113_68="msg_generalhelp,help_5_0";
private static final String C_P_113_69="nbNecStepsIncrHappiness";
private static final String C_P_113_70="msg_generalhelp,help_5_1";
private static final String C_P_113_71="msg_generalhelp,help_5_2";
private static final String C_P_113_72="msg_generalhelp,help_5_3";
private static final String C_P_113_73="msg_generalhelp,help_5_4";
private static final String C_P_113_74="msg_generalhelp,help_6";
private static final String C_P_113_75="msg_generalhelp,help_6_0";
private static final String C_P_113_76="msg_generalhelp,help_6_1";
private static final String C_P_113_77="msg_generalhelp,help_6_2";
private static final String C_P_113_78="msg_generalhelp,help_6_3";
private static final String C_P_113_79="msg_generalhelp,help_6_4";
private static final String C_P_113_80="msg_generalhelp,help_6_5";
private static final String C_P_113_81="nbMaxStepsSameEvoBase";
private static final String C_P_113_82="msg_generalhelp,help_6_6";
private static final String C_P_113_83="nbMaxSteps";
private static final String C_P_113_84="msg_generalhelp,help_6_7";
private static final String C_P_113_85="msg_generalhelp,help_6_8";
private static final String C_P_113_86="pokemonDefaultEggGroup";
private static final String C_P_113_87="p";
private static final String C_P_113_88="$clickPokemon({([p])})";
private static final String C_P_113_89="{getTrPokemon(([p]))}";
private static final String C_P_113_90="msg_generalhelp,help_6_9";
private static final String C_P_113_91="defaultMoney";
private static final String C_P_113_92="msg_generalhelp,help_7";
private static final String C_P_113_93="maxIv";
private static final String C_P_113_94="!tm.isEmpty()";
private static final String C_P_113_95="msg_generalhelp,help_tm";
private static final String C_P_113_96="msg_generalhelp,help_tm_hm_move";
private static final String C_P_113_97="msg_generalhelp,help_tm_hm_price";
private static final String C_P_113_98="tm";
private static final String C_P_113_99="m";
private static final String C_P_113_100="$clickTm({([m])})";
private static final String C_P_113_101="{getTrTm(([m]))}";
private static final String C_P_113_102="isEmpty(getTmPrice(([m])))";
private static final String C_P_113_103="msg_generalhelp,help_tm_hm_price_no";
private static final String C_P_113_104="!isEmpty(getTmPrice(([m])))";
private static final String C_P_113_105="{getTmPrice(([m]))}";
private static final String C_P_113_106="!hm.isEmpty()";
private static final String C_P_113_107="msg_generalhelp,help_hm";
private static final String C_P_113_108="hm";
private static final String C_P_113_109="m";
private static final String C_P_113_110="$clickHm({([m])})";
private static final String C_P_113_111="{getTrHm(([m]))}";
private static final String C_P_113_112="msg_generalhelp,types_intro";
private static final String C_P_113_113="msg_generalhelp,types_name";
private static final String C_P_113_114="msg_generalhelp,types_image";
private static final String C_P_113_115="msg_generalhelp,types_coulour";
private static final String C_P_113_116="types";
private static final String C_P_113_117="t";
private static final String C_P_113_118="{getTrType(([t]))}";
private static final String C_P_113_119="{getImageType(([t]))}";
private static final String C_P_113_120="{getColorType(([t]))}";
private static final String C_P_113_121=GO_TO_IND;
private static final String C_P_113_122="";
private static final String C_P_113_123="msg_generalhelp,index";
private PageDataGeneralGeneral(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc18){
Element elt0_=el(_doc18,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_113_0));
attrs0_.add(at(C_BEAN,C_P_113_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,HEAD);
Element elt2_=el(_doc18,TITLE);
Element elt3_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc18,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_113_3));
attrs2_.add(at(REL,C_P_113_4));
attrs2_.add(at(TYPE,C_P_113_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc18,BODY);
build0(elt5_,_doc18);
build1(elt5_,_doc18);
build2(elt5_,_doc18);
build3(elt5_,_doc18);
build4(elt5_,_doc18);
build5(elt5_,_doc18);
build6(elt5_,_doc18);
build7(elt5_,_doc18);
build8(elt5_,_doc18);
build9(elt5_,_doc18);
build10(elt5_,_doc18);
build11(elt5_,_doc18);
build12(elt5_,_doc18);
build13(elt5_,_doc18);
build14(elt5_,_doc18);
build15(elt5_,_doc18);
build16(elt5_,_doc18);
build17(elt5_,_doc18);
build18(elt5_,_doc18);
build19(elt5_,_doc18);
build20(elt5_,_doc18);
build21(elt5_,_doc18);
build22(elt5_,_doc18);
build23(elt5_,_doc18);
build24(elt5_,_doc18);
build25(elt5_,_doc18);
build26(elt5_,_doc18);
build27(elt5_,_doc18);
build28(elt5_,_doc18);
build29(elt5_,_doc18);
build30(elt5_,_doc18);
build31(elt5_,_doc18);
build32(elt5_,_doc18);
build33(elt5_,_doc18);
build34(elt5_,_doc18);
build35(elt5_,_doc18);
build36(elt5_,_doc18);
build37(elt5_,_doc18);
build38(elt5_,_doc18);
build39(elt5_,_doc18);
build40(elt5_,_doc18);
build41(elt5_,_doc18);
build42(elt5_,_doc18);
build43(elt5_,_doc18);
build44(elt5_,_doc18);
ad(elt0_,elt5_);
_doc18.appendChild(elt0_);
}
static void build0(Element _body,Document _doc18){
Element elt0_=el(_doc18,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_113_6));
attrs0_.add(at(HREF,C_P_113_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc18){
Element elt0_=el(_doc18,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_9));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc18){
Element elt0_=el(_doc18,UL);
Element elt1_=el(_doc18,LI);
Element elt2_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_10));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc18,LI);
Element elt4_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_11));
at(elt4_,attrs1_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt5_=el(_doc18,LI);
Element elt6_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_113_12));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
Element elt7_=el(_doc18,LI);
Element elt8_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_113_13));
at(elt8_,attrs3_);
Element elt9_=el(_doc18,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_113_14));
at(elt9_,attrs4_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt10_=el(_doc18,LI);
Element elt11_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_113_15));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt0_,elt10_);
Element elt12_=el(_doc18,LI);
Element elt13_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_113_16));
at(elt13_,attrs6_);
Element elt14_=el(_doc18,PARAM);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_113_17));
at(elt14_,attrs7_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt0_,elt12_);
Element elt15_=el(_doc18,LI);
Element elt16_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_113_18));
at(elt16_,attrs8_);
Element elt17_=el(_doc18,PARAM);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_113_19));
at(elt17_,attrs9_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt0_,elt15_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_20));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_21));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_22));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_23));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc18){
Element elt0_=el(_doc18,BR);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc18){
Element elt0_=el(_doc18,MAP);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(STYLE,C_P_113_24));
attrs0_.add(at(WIDTH,C_P_113_25));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,C_FOR);
CustList<Attr> attrs1_=al(5);
attrs1_.add(at(KEY,C_P_113_26));
attrs1_.add(at(MAP,C_P_113_27));
attrs1_.add(at(VALUE,C_P_113_28));
attrs1_.add(at(KEYCLASSNAME,C_P_113_29));
attrs1_.add(at(VARCLASSNAME,C_P_113_30));
at(elt1_,attrs1_);
Element elt2_=el(_doc18,A);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(TITLE,C_P_113_31));
at(elt2_,attrs2_);
Element elt3_=el(_doc18,C_IMG);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(SRC,C_P_113_32));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_33));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_113_34));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc18){
Element elt0_=el(_doc18,BR);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_35));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_IMG);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(SRC,C_P_113_36));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc18){
Element elt0_=el(_doc18,BR);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_37));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc18){
Element elt0_=el(_doc18,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_113_38));
at(elt0_,attrs0_);
Text txt0_=tx(_doc18,C_P_113_39);
ad(elt0_,txt0_);
ad(_body,elt0_);
Element elt1_=el(_doc18,BR);
ad(_body,elt1_);
}
static void build17(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_40));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc18,C_P_113_41);
ad(_body,txt0_);
}
static void build18(Element _body,Document _doc18){
Element elt0_=el(_doc18,BR);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_42));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc18,C_P_113_43);
ad(_body,txt0_);
}
static void build20(Element _body,Document _doc18){
Element elt0_=el(_doc18,BR);
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_44));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc18){
Element elt0_=el(_doc18,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_113_45));
at(elt0_,attrs0_);
Text txt0_=tx(_doc18,C_P_113_46);
ad(elt0_,txt0_);
ad(_body,elt0_);
Element elt1_=el(_doc18,BR);
ad(_body,elt1_);
}
static void build23(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_47));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build24(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_113_48));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_113_49));
at(elt1_,attrs1_);
Text txt0_=tx(_doc18,C_P_113_50);
ad(elt1_,txt0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc18,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build25(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_113_51));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_52));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc18,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build26(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_53));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build27(Element _body,Document _doc18){
Element elt0_=el(_doc18,UL);
Element elt1_=el(_doc18,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_113_54));
attrs0_.add(at(VAR,C_P_113_55));
at(elt1_,attrs0_);
Element elt2_=el(_doc18,LI);
Element elt3_=el(_doc18,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_113_56));
at(elt3_,attrs1_);
Text txt0_=tx(_doc18,C_P_113_57);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build28(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_58));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_59));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build29(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_60));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_61));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc18,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_113_62));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build30(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_63));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_64));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build31(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_65));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_66));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build32(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_67));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build33(Element _body,Document _doc18){
Element elt0_=el(_doc18,UL);
Element elt1_=el(_doc18,LI);
Element elt2_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_68));
at(elt2_,attrs0_);
Element elt3_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_69));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt4_=el(_doc18,LI);
Element elt5_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_113_70));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
ad(elt0_,elt4_);
Element elt6_=el(_doc18,LI);
Element elt7_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_113_71));
at(elt7_,attrs3_);
ad(elt6_,elt7_);
ad(elt0_,elt6_);
Element elt8_=el(_doc18,LI);
Element elt9_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_113_72));
at(elt9_,attrs4_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
Element elt10_=el(_doc18,LI);
Element elt11_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_113_73));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt0_,elt10_);
ad(_body,elt0_);
}
static void build34(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_74));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build35(Element _body,Document _doc18){
Element elt0_=el(_doc18,UL);
Element elt1_=el(_doc18,LI);
Element elt2_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_75));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc18,LI);
Element elt4_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_76));
at(elt4_,attrs1_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt5_=el(_doc18,LI);
Element elt6_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_113_77));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt0_,elt5_);
Element elt7_=el(_doc18,LI);
Element elt8_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_113_78));
at(elt8_,attrs3_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc18,LI);
Element elt10_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_113_79));
at(elt10_,attrs4_);
ad(elt9_,elt10_);
Element elt11_=el(_doc18,UL);
Element elt12_=el(_doc18,LI);
Element elt13_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_113_80));
at(elt13_,attrs5_);
Element elt14_=el(_doc18,PARAM);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_113_81));
at(elt14_,attrs6_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
Element elt15_=el(_doc18,LI);
Element elt16_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_113_82));
at(elt16_,attrs7_);
Element elt17_=el(_doc18,PARAM);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_113_83));
at(elt17_,attrs8_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt11_,elt15_);
ad(elt9_,elt11_);
ad(elt0_,elt9_);
Element elt18_=el(_doc18,LI);
Element elt19_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_113_84));
at(elt19_,attrs9_);
ad(elt18_,elt19_);
ad(elt0_,elt18_);
ad(_body,elt0_);
}
static void build36(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_85));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build37(Element _body,Document _doc18){
Element elt0_=el(_doc18,UL);
Element elt1_=el(_doc18,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_113_86));
attrs0_.add(at(VAR,C_P_113_87));
at(elt1_,attrs0_);
Element elt2_=el(_doc18,LI);
Element elt3_=el(_doc18,A);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(C_COMMAND,C_P_113_88));
at(elt3_,attrs1_);
Text txt0_=tx(_doc18,C_P_113_89);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build38(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_90));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_91));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build39(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_92));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,PARAM);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_93));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build40(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_113_94));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_95));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc18,TABLE);
Element elt3_=el(_doc18,THEAD);
Element elt4_=el(_doc18,TR);
Element elt5_=el(_doc18,TH);
Element elt6_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_113_96));
at(elt6_,attrs2_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc18,TH);
Element elt8_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_113_97));
at(elt8_,attrs3_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt9_=el(_doc18,TBODY);
Element elt10_=el(_doc18,C_FOR);
CustList<Attr> attrs4_=al(2);
attrs4_.add(at(LIST,C_P_113_98));
attrs4_.add(at(VAR,C_P_113_99));
at(elt10_,attrs4_);
Element elt11_=el(_doc18,TR);
Element elt12_=el(_doc18,TD);
Element elt13_=el(_doc18,A);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(C_COMMAND,C_P_113_100));
at(elt13_,attrs5_);
Text txt0_=tx(_doc18,C_P_113_101);
ad(elt13_,txt0_);
ad(elt12_,elt13_);
ad(elt11_,elt12_);
Element elt14_=el(_doc18,TD);
Element elt15_=el(_doc18,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_113_102));
at(elt15_,attrs6_);
Element elt16_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_113_103));
at(elt16_,attrs7_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
Element elt17_=el(_doc18,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_113_104));
at(elt17_,attrs8_);
Text txt1_=tx(_doc18,C_P_113_105);
ad(elt17_,txt1_);
ad(elt14_,elt17_);
ad(elt11_,elt14_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt2_,elt9_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build41(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_113_106));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_107));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc18,UL);
Element elt3_=el(_doc18,C_FOR);
CustList<Attr> attrs2_=al(2);
attrs2_.add(at(LIST,C_P_113_108));
attrs2_.add(at(VAR,C_P_113_109));
at(elt3_,attrs2_);
Element elt4_=el(_doc18,LI);
Element elt5_=el(_doc18,A);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(C_COMMAND,C_P_113_110));
at(elt5_,attrs3_);
Text txt0_=tx(_doc18,C_P_113_111);
ad(elt5_,txt0_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build42(Element _body,Document _doc18){
Element elt0_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_112));
at(elt0_,attrs0_);
ad(_body,elt0_);
}
static void build43(Element _body,Document _doc18){
Element elt0_=el(_doc18,TABLE);
Element elt1_=el(_doc18,THEAD);
Element elt2_=el(_doc18,TR);
Element elt3_=el(_doc18,TH);
Element elt4_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_113_113));
at(elt4_,attrs0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
Element elt5_=el(_doc18,TH);
Element elt6_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_114));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt2_,elt5_);
Element elt7_=el(_doc18,TH);
Element elt8_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_113_115));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt2_,elt7_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt9_=el(_doc18,TBODY);
Element elt10_=el(_doc18,C_FOR);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(LIST,C_P_113_116));
attrs3_.add(at(VAR,C_P_113_117));
at(elt10_,attrs3_);
Element elt11_=el(_doc18,TR);
Element elt12_=el(_doc18,TD);
Text txt0_=tx(_doc18,C_P_113_118);
ad(elt12_,txt0_);
ad(elt11_,elt12_);
Element elt13_=el(_doc18,TD);
Element elt14_=el(_doc18,C_IMG);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(SRC,C_P_113_119));
at(elt14_,attrs4_);
ad(elt13_,elt14_);
ad(elt11_,elt13_);
Element elt15_=el(_doc18,TD);
Element elt16_=el(_doc18,C_IMG);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(SRC,C_P_113_120));
at(elt16_,attrs5_);
ad(elt15_,elt16_);
ad(elt11_,elt15_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
static void build44(Element _body,Document _doc18){
Element elt0_=el(_doc18,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_113_121));
attrs0_.add(at(HREF,C_P_113_122));
at(elt0_,attrs0_);
Element elt1_=el(_doc18,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_113_123));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
