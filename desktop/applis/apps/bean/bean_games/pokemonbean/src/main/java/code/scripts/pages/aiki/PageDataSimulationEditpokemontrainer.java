package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataSimulationEditpokemontrainer extends PageCardsCommon{
private static final String C_P_197_0="javahtml";
private static final String C_P_197_1="edittrainerpokemon";
private static final String C_P_197_2="msg_levelsimu,title_edit_pokemon_tr";
private static final String C_P_197_3=PkScriptPages.REN_ADD_WEB_CSS_SIMULATION_CSS;
private static final String C_P_197_4="stylesheet";
private static final String C_P_197_5="text/css";
private static final String C_P_197_6="$cancel()";
private static final String C_P_197_7="";
private static final String C_P_197_8="msg_levelsimu,cancel";
private static final String C_P_197_9="";
private static final String C_P_197_10="$chooseName";
private static final String C_P_197_11="post";
private static final String C_P_197_12="namePk";
private static final String C_P_197_13="msg_simulation,name_pk";
private static final String C_P_197_14="";
private static final String C_P_197_15="$chooseItem";
private static final String C_P_197_16="post";
private static final String C_P_197_17="item";
private static final String C_P_197_18="msg_simulation,item_pk";
private static final String C_P_197_19="";
private static final String C_P_197_20="$chooseAbility";
private static final String C_P_197_21="post";
private static final String C_P_197_22="ability";
private static final String C_P_197_23="msg_simulation,ability_pk";
private static final String C_P_197_24="";
private static final String C_P_197_25="$addMoves";
private static final String C_P_197_26="post";
private static final String C_P_197_27="adding_moves";
private static final String C_P_197_28="msg_simulation,add";
private static final String C_P_197_29="";
private static final String C_P_197_30="$deleteMoves";
private static final String C_P_197_31="post";
private static final String C_P_197_32="delete_moves";
private static final String C_P_197_33="msg_moves,moves";
private static final String C_P_197_34="msg_moves,name_h";
private static final String C_P_197_35="msg_moves,pp_h";
private static final String C_P_197_36="msg_moves,types_h";
private static final String C_P_197_37="msg_moves,cat_h";
private static final String C_P_197_38="msg_moves,damag_h";
private static final String C_P_197_39="msg_moves,direc_h";
private static final String C_P_197_40="msg_moves,prio_h";
private static final String C_P_197_41="msg_simulation,selected";
private static final String C_P_197_42="aiki.beans.facade.simulation.dto.SelectLineMove";
private static final String C_P_197_43="moves";
private static final String C_P_197_44="d";
private static final String C_P_197_45="{d.displayName}";
private static final String C_P_197_46="{d.pp}";
private static final String C_P_197_47="d.getTypes()";
private static final String C_P_197_48="t";
private static final String C_P_197_49="java.lang.String";
private static final String C_P_197_50="{t} -";
private static final String C_P_197_51="{d.category}";
private static final String C_P_197_52="d.isDamageMove()";
private static final String C_P_197_53="msg_moves,damaging";
private static final String C_P_197_54="!d.isDamageMove()";
private static final String C_P_197_55="msg_moves,status";
private static final String C_P_197_56="!d.isDamageMove()";
private static final String C_P_197_57="msg_moves,status_indirect";
private static final String C_P_197_58="d.isDamageMove()";
private static final String C_P_197_59="d.isDirect()";
private static final String C_P_197_60="msg_moves,damaging_direct";
private static final String C_P_197_61="!d.isDirect()";
private static final String C_P_197_62="msg_moves,damaging_indirect";
private static final String C_P_197_63="{d.priority}";
private static final String C_P_197_64="d.selected";
private static final String C_P_197_65="d.selected";
private static final String C_P_197_66="checkbox";
private static final String C_P_197_67="msg_simulation,remove";
private static final String C_P_197_68="";
private static final String C_P_197_69="$validateTrainerPk";
private static final String C_P_197_70="post";
private static final String C_P_197_71="adding";
private static final String C_P_197_72="msg_simulation,name_pk";
private static final String C_P_197_73="{getTranslatedName()}";
private static final String C_P_197_74="msg_simulation,ability_pk";
private static final String C_P_197_75="{getTranslatedAbility()}";
private static final String C_P_197_76="msg_simulation,item_pk";
private static final String C_P_197_77="{getTranslatedItem()}";
private static final String C_P_197_78="msg_simulation,gender_pk";
private static final String C_P_197_79="";
private static final String C_P_197_80="genders";
private static final String C_P_197_81="gender";
private static final String C_P_197_82="";
private static final String C_P_197_83="gender";
private static final String C_P_197_84="msg_simulation,level_pk";
private static final String C_P_197_85="level";
private static final String C_P_197_86="level";
private static final String C_P_197_87="text";
private static final String C_P_197_88="add";
private static final String C_P_197_89="msg_simulation,ally_pk";
private static final String C_P_197_90="allyPk";
private static final String C_P_197_91="allyPk";
private static final String C_P_197_92="checkbox";
private static final String C_P_197_94="msg_levelsimu,ok";
private PageDataSimulationEditpokemontrainer(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc102){
Element elt0_=el(_doc102,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_197_0));
attrs0_.add(at(C_BEAN,C_P_197_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,HEAD);
Element elt2_=el(_doc102,TITLE);
Element elt3_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_197_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc102,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_197_3));
attrs2_.add(at(REL,C_P_197_4));
attrs2_.add(at(TYPE,C_P_197_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc102,BODY);
build0(elt5_,_doc102);
build1(elt5_,_doc102);
build2(elt5_,_doc102);
build3(elt5_,_doc102);
build4(elt5_,_doc102);
build5(elt5_,_doc102);
build6(elt5_,_doc102);
ad(elt0_,elt5_);
_doc102.appendChild(elt0_);
}
static void build0(Element _body,Document _doc102){
Element elt0_=el(_doc102,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_197_6));
attrs0_.add(at(HREF,C_P_197_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_197_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc102){
Element elt0_=el(_doc102,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_197_9));
attrs0_.add(at(C_COMMAND,C_P_197_10));
attrs0_.add(at(METHOD,C_P_197_11));
attrs0_.add(at(NAME,C_P_197_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,C_SUBMIT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(MESSAGE,C_P_197_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc102){
Element elt0_=el(_doc102,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_197_14));
attrs0_.add(at(C_COMMAND,C_P_197_15));
attrs0_.add(at(METHOD,C_P_197_16));
attrs0_.add(at(NAME,C_P_197_17));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,C_SUBMIT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(MESSAGE,C_P_197_18));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc102){
Element elt0_=el(_doc102,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_197_19));
attrs0_.add(at(C_COMMAND,C_P_197_20));
attrs0_.add(at(METHOD,C_P_197_21));
attrs0_.add(at(NAME,C_P_197_22));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,C_SUBMIT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(MESSAGE,C_P_197_23));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc102){
Element elt0_=el(_doc102,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_197_24));
attrs0_.add(at(C_COMMAND,C_P_197_25));
attrs0_.add(at(METHOD,C_P_197_26));
attrs0_.add(at(NAME,C_P_197_27));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,C_SUBMIT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(MESSAGE,C_P_197_28));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc102){
Element elt0_=el(_doc102,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_197_29));
attrs0_.add(at(C_COMMAND,C_P_197_30));
attrs0_.add(at(METHOD,C_P_197_31));
attrs0_.add(at(NAME,C_P_197_32));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,TABLE);
Element elt2_=el(_doc102,CAPTION);
Element elt3_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_197_33));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc102,THEAD);
Element elt5_=el(_doc102,TR);
Element elt6_=el(_doc102,TH);
Element elt7_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_197_34));
at(elt7_,attrs2_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc102,TH);
Element elt9_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_197_35));
at(elt9_,attrs3_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
Element elt10_=el(_doc102,TH);
Element elt11_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_197_36));
at(elt11_,attrs4_);
ad(elt10_,elt11_);
ad(elt5_,elt10_);
Element elt12_=el(_doc102,TH);
Element elt13_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_197_37));
at(elt13_,attrs5_);
ad(elt12_,elt13_);
ad(elt5_,elt12_);
Element elt14_=el(_doc102,TH);
Element elt15_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_197_38));
at(elt15_,attrs6_);
ad(elt14_,elt15_);
ad(elt5_,elt14_);
Element elt16_=el(_doc102,TH);
Element elt17_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_197_39));
at(elt17_,attrs7_);
ad(elt16_,elt17_);
ad(elt5_,elt16_);
Element elt18_=el(_doc102,TH);
Element elt19_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_197_40));
at(elt19_,attrs8_);
ad(elt18_,elt19_);
ad(elt5_,elt18_);
Element elt20_=el(_doc102,TH);
Element elt21_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_197_41));
at(elt21_,attrs9_);
ad(elt20_,elt21_);
ad(elt5_,elt20_);
ad(elt4_,elt5_);
ad(elt1_,elt4_);
Element elt22_=el(_doc102,TBODY);
Element elt23_=el(_doc102,C_FOR);
CustList<Attr> attrs10_=al(3);
attrs10_.add(at(CLASSNAME,C_P_197_42));
attrs10_.add(at(LIST,C_P_197_43));
attrs10_.add(at(VAR,C_P_197_44));
at(elt23_,attrs10_);
Element elt24_=el(_doc102,TR);
Element elt25_=el(_doc102,TD);
Text txt0_=tx(_doc102,C_P_197_45);
ad(elt25_,txt0_);
ad(elt24_,elt25_);
Element elt26_=el(_doc102,TD);
Text txt1_=tx(_doc102,C_P_197_46);
ad(elt26_,txt1_);
ad(elt24_,elt26_);
Element elt27_=el(_doc102,TD);
Element elt28_=el(_doc102,C_FOR);
CustList<Attr> attrs11_=al(3);
attrs11_.add(at(LIST,C_P_197_47));
attrs11_.add(at(VAR,C_P_197_48));
attrs11_.add(at(CLASSNAME,C_P_197_49));
at(elt28_,attrs11_);
Text txt2_=tx(_doc102,C_P_197_50);
ad(elt28_,txt2_);
ad(elt27_,elt28_);
ad(elt24_,elt27_);
Element elt29_=el(_doc102,TD);
Text txt3_=tx(_doc102,C_P_197_51);
ad(elt29_,txt3_);
ad(elt24_,elt29_);
Element elt30_=el(_doc102,TD);
Element elt31_=el(_doc102,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_197_52));
at(elt31_,attrs12_);
Element elt32_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_197_53));
at(elt32_,attrs13_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
Element elt33_=el(_doc102,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_197_54));
at(elt33_,attrs14_);
Element elt34_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_197_55));
at(elt34_,attrs15_);
ad(elt33_,elt34_);
ad(elt30_,elt33_);
ad(elt24_,elt30_);
Element elt35_=el(_doc102,TD);
Element elt36_=el(_doc102,C_IF);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(CONDITION,C_P_197_56));
at(elt36_,attrs16_);
Element elt37_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_197_57));
at(elt37_,attrs17_);
ad(elt36_,elt37_);
ad(elt35_,elt36_);
Element elt38_=el(_doc102,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_197_58));
at(elt38_,attrs18_);
Element elt39_=el(_doc102,C_IF);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(CONDITION,C_P_197_59));
at(elt39_,attrs19_);
Element elt40_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_197_60));
at(elt40_,attrs20_);
ad(elt39_,elt40_);
ad(elt38_,elt39_);
Element elt41_=el(_doc102,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_197_61));
at(elt41_,attrs21_);
Element elt42_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_197_62));
at(elt42_,attrs22_);
ad(elt41_,elt42_);
ad(elt38_,elt41_);
ad(elt35_,elt38_);
ad(elt24_,elt35_);
Element elt43_=el(_doc102,TD);
Text txt4_=tx(_doc102,C_P_197_63);
ad(elt43_,txt4_);
ad(elt24_,elt43_);
Element elt44_=el(_doc102,TD);
Element elt45_=el(_doc102,INPUT);
CustList<Attr> attrs23_=al(3);
attrs23_.add(at(C_VARVALUE,C_P_197_64));
attrs23_.add(at(NAME,C_P_197_65));
attrs23_.add(at(TYPE,C_P_197_66));
at(elt45_,attrs23_);
ad(elt44_,elt45_);
ad(elt24_,elt44_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt1_,elt22_);
ad(elt0_,elt1_);
Element elt46_=el(_doc102,C_SUBMIT);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(MESSAGE,C_P_197_67));
at(elt46_,attrs24_);
ad(elt0_,elt46_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc102){
Element elt0_=el(_doc102,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_197_68));
attrs0_.add(at(C_COMMAND,C_P_197_69));
attrs0_.add(at(METHOD,C_P_197_70));
attrs0_.add(at(NAME,C_P_197_71));
at(elt0_,attrs0_);
Element elt1_=el(_doc102,FIELDSET);
Element elt2_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_197_72));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Text txt0_=tx(_doc102,C_P_197_73);
ad(elt1_,txt0_);
Element elt3_=el(_doc102,BR);
ad(elt1_,elt3_);
Element elt4_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_197_74));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
Text txt1_=tx(_doc102,C_P_197_75);
ad(elt1_,txt1_);
Element elt5_=el(_doc102,BR);
ad(elt1_,elt5_);
Element elt6_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_197_76));
at(elt6_,attrs3_);
ad(elt1_,elt6_);
Text txt2_=tx(_doc102,C_P_197_77);
ad(elt1_,txt2_);
Element elt7_=el(_doc102,BR);
ad(elt1_,elt7_);
Element elt8_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_197_78));
at(elt8_,attrs4_);
ad(elt1_,elt8_);
Element elt9_=el(_doc102,C_SELECT);
CustList<Attr> attrs5_=al(5);
attrs5_.add(at(DEFAULT,C_P_197_79));
attrs5_.add(at(MAP,C_P_197_80));
attrs5_.add(at(NAME,C_P_197_81));
attrs5_.add(at(UPDATE,C_P_197_82));
attrs5_.add(at(VARVALUE,C_P_197_83));
at(elt9_,attrs5_);
ad(elt1_,elt9_);
Element elt10_=el(_doc102,BR);
ad(elt1_,elt10_);
Element elt11_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_197_84));
at(elt11_,attrs6_);
ad(elt1_,elt11_);
Element elt12_=el(_doc102,INPUT);
CustList<Attr> attrs7_=al(3);
attrs7_.add(at(C_VARVALUE,C_P_197_85));
attrs7_.add(at(NAME,C_P_197_86));
attrs7_.add(at(TYPE,C_P_197_87));
at(elt12_,attrs7_);
ad(elt1_,elt12_);
Element elt13_=el(_doc102,BR);
ad(elt1_,elt13_);
Element elt14_=el(_doc102,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_197_88));
at(elt14_,attrs8_);
Element elt15_=el(_doc102,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_197_89));
at(elt15_,attrs9_);
ad(elt14_,elt15_);
Element elt16_=el(_doc102,INPUT);
CustList<Attr> attrs10_=al(3);
attrs10_.add(at(C_VARVALUE,C_P_197_90));
attrs10_.add(at(NAME,C_P_197_91));
attrs10_.add(at(TYPE,C_P_197_92));
at(elt16_,attrs10_);
ad(elt14_,elt16_);
Element elt17_=el(_doc102,BR);
ad(elt14_,elt17_);
ad(elt1_,elt14_);
Element elt18_=el(_doc102,C_SUBMIT);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(MESSAGE,C_P_197_94));
at(elt18_,attrs11_);
ad(elt1_,elt18_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
