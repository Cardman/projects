package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationEditpokemon extends PageAikiCommon{
private static final String C_P_195_0="javahtml";
private static final String C_P_195_1="editpokemon";
private static final String C_P_195_2="msg_levelsimu,title_edit_pokemon_pl";
private static final String C_P_195_3="web/css/simulation.css";
private static final String C_P_195_4="stylesheet";
private static final String C_P_195_5="text/css";
private static final String C_P_195_6="$cancel()";
private static final String C_P_195_7="";
private static final String C_P_195_8="msg_levelsimu,cancel";
private static final String C_P_195_9="msg_simulation,name_pk";
private static final String C_P_195_10="{translateName()}";
private static final String C_P_195_11="msg_simulation,level_pk";
private static final String C_P_195_12="{level}";
private static final String C_P_195_13="msg_simulation,item_pk";
private static final String C_P_195_14=": {translateItem()}";
private static final String C_P_195_15="";
private static final String C_P_195_16="$chooseItem";
private static final String C_P_195_17="post";
private static final String C_P_195_18="item";
private static final String C_P_195_19="msg_simulation,item_pk";
private static final String C_P_195_20="!isEmpty(namePk)";
private static final String C_P_195_21="";
private static final String C_P_195_22="$addMoves";
private static final String C_P_195_23="post";
private static final String C_P_195_24="adding_moves";
private static final String C_P_195_25="msg_simulation,add";
private static final String C_P_195_26="";
private static final String C_P_195_27="$deleteMoves";
private static final String C_P_195_28="post";
private static final String C_P_195_29="delete_moves";
private static final String C_P_195_30="msg_moves,moves";
private static final String C_P_195_31="msg_moves,name_h";
private static final String C_P_195_32="msg_moves,pp_h";
private static final String C_P_195_33="msg_moves,types_h";
private static final String C_P_195_34="msg_moves,cat_h";
private static final String C_P_195_35="msg_moves,damag_h";
private static final String C_P_195_36="msg_moves,direc_h";
private static final String C_P_195_37="msg_moves,prio_h";
private static final String C_P_195_38="msg_simulation,selected";
private static final String C_P_195_39="aiki.beans.facade.simulation.dto.SelectLineMove";
private static final String C_P_195_40="moves";
private static final String C_P_195_41="d";
private static final String C_P_195_42="{d.displayName}";
private static final String C_P_195_43="{d.pp}";
private static final String C_P_195_44="d.getTypes()";
private static final String C_P_195_45="t";
private static final String C_P_195_46="java.lang.String";
private static final String C_P_195_47="{t} -";
private static final String C_P_195_48="{d.category}";
private static final String C_P_195_49="d.isDamageMove()";
private static final String C_P_195_50="msg_moves,damaging";
private static final String C_P_195_51="!d.isDamageMove()";
private static final String C_P_195_52="msg_moves,status";
private static final String C_P_195_53="!d.isDamageMove()";
private static final String C_P_195_54="msg_moves,status_indirect";
private static final String C_P_195_55="d.isDamageMove()";
private static final String C_P_195_56="d.isDirect()";
private static final String C_P_195_57="msg_moves,damaging_direct";
private static final String C_P_195_58="!d.isDirect()";
private static final String C_P_195_59="msg_moves,damaging_indirect";
private static final String C_P_195_60="{d.priority}";
private static final String C_P_195_61="d.selected";
private static final String C_P_195_62="d.selected";
private static final String C_P_195_63="checkbox";
private static final String C_P_195_64="msg_simulation,remove";
private static final String C_P_195_65="";
private static final String C_P_195_66="$edit";
private static final String C_P_195_67="post";
private static final String C_P_195_68="adding";
private static final String C_P_195_69="msg_simulation,exp_pk";
private static final String C_P_195_70="positive_rate_validator";
private static final String C_P_195_71="experience";
private static final String C_P_195_72="experienceId";
private static final String C_P_195_73="experience";
private static final String C_P_195_74="text";
private static final String C_P_195_75="experienceId";
private static final String C_P_195_76="errormessage";
private static final String C_P_195_77="msg_simulation,rate_issue";
private static final String C_P_195_78="msg_simulation,catching_ball";
private static final String C_P_195_79="";
private static final String C_P_195_80="balls";
private static final String C_P_195_81="ball";
private static final String C_P_195_82="";
private static final String C_P_195_83="ball";
private static final String C_P_195_84="msg_simulation,happiness_pk";
private static final String C_P_195_85="short_validator";
private static final String C_P_195_86="happiness";
private static final String C_P_195_87="happinessId";
private static final String C_P_195_88="happiness";
private static final String C_P_195_89="text";
private static final String C_P_195_90="happinessId";
private static final String C_P_195_91="errormessage";
private static final String C_P_195_92="msg_simulation,short_issue";
private static final String C_P_195_93="msg_simulation,remaining_hp";
private static final String C_P_195_94="positive_rate_validator";
private static final String C_P_195_95="remainingHp";
private static final String C_P_195_96="remainingHpId";
private static final String C_P_195_97="remainingHp";
private static final String C_P_195_98="text";
private static final String C_P_195_99="remainingHpId";
private static final String C_P_195_100="errormessage";
private static final String C_P_195_101="msg_simulation,rate_issue";
private static final String C_P_195_102="msg_simulation,heal_hp";
private static final String C_P_195_103="heal";
private static final String C_P_195_104="heal";
private static final String C_P_195_105="checkbox";
private static final String C_P_195_106="msg_simulation,ev";
private static final String C_P_195_107="s";
private static final String C_P_195_108="ev";
private static final String C_P_195_109="e";
private static final String C_P_195_110="java.lang.Object";
private static final String C_P_195_111="aiki.beans.facade.simulation.dto.EvLine";
private static final String C_P_195_112="{getTranslatedStatistic(([s]))}:";
private static final String C_P_195_113="short_validator";
private static final String C_P_195_114="e.ev";
private static final String C_P_195_115="id{([s])}";
private static final String C_P_195_116="e.ev";
private static final String C_P_195_117="text";
private static final String C_P_195_118="id{([s])}";
private static final String C_P_195_119="errormessage";
private static final String C_P_195_120="msg_simulation,short_issue";
private static final String C_P_195_122="msg_levelsimu,ok";
private PageDataSimulationEditpokemon(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc100){
Element elt0_=el(_doc100,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_195_0));
attrs0_.add(at(C_BEAN,C_P_195_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc100,HEAD);
Element elt2_=el(_doc100,TITLE);
Element elt3_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_195_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc100,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_195_3));
attrs2_.add(at(REL,C_P_195_4));
attrs2_.add(at(TYPE,C_P_195_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc100,BODY);
build0(elt5_,_doc100);
build1(elt5_,_doc100);
build2(elt5_,_doc100);
build3(elt5_,_doc100);
build4(elt5_,_doc100);
build5(elt5_,_doc100);
ad(elt0_,elt5_);
_doc100.appendChild(elt0_);
}
static void build0(Element _body,Document _doc100){
Element elt0_=el(_doc100,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_195_6));
attrs0_.add(at(HREF,C_P_195_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_195_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc100){
Element elt0_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_195_9));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc100,C_P_195_10);
ad(_body,txt0_);
Element elt1_=el(_doc100,BR);
ad(_body,elt1_);
}
static void build2(Element _body,Document _doc100){
Element elt0_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_195_11));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc100,C_P_195_12);
ad(_body,txt0_);
Element elt1_=el(_doc100,BR);
ad(_body,elt1_);
}
static void build3(Element _body,Document _doc100){
Element elt0_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_195_13));
at(elt0_,attrs0_);
ad(_body,elt0_);
Text txt0_=tx(_doc100,C_P_195_14);
ad(_body,txt0_);
Element elt1_=el(_doc100,BR);
ad(_body,elt1_);
}
static void build4(Element _body,Document _doc100){
Element elt0_=el(_doc100,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_195_15));
attrs0_.add(at(C_COMMAND,C_P_195_16));
attrs0_.add(at(METHOD,C_P_195_17));
attrs0_.add(at(NAME,C_P_195_18));
at(elt0_,attrs0_);
Element elt1_=el(_doc100,C_SUBMIT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(MESSAGE,C_P_195_19));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc100){
Element elt0_=el(_doc100,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_195_20));
at(elt0_,attrs0_);
Element elt1_=el(_doc100,BR);
ad(elt0_,elt1_);
Element elt2_=el(_doc100,FORM);
CustList<Attr> attrs1_=al(4);
attrs1_.add(at(ACTION,C_P_195_21));
attrs1_.add(at(C_COMMAND,C_P_195_22));
attrs1_.add(at(METHOD,C_P_195_23));
attrs1_.add(at(NAME,C_P_195_24));
at(elt2_,attrs1_);
Element elt3_=el(_doc100,C_SUBMIT);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(MESSAGE,C_P_195_25));
at(elt3_,attrs2_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt4_=el(_doc100,FORM);
CustList<Attr> attrs3_=al(4);
attrs3_.add(at(ACTION,C_P_195_26));
attrs3_.add(at(C_COMMAND,C_P_195_27));
attrs3_.add(at(METHOD,C_P_195_28));
attrs3_.add(at(NAME,C_P_195_29));
at(elt4_,attrs3_);
Element elt5_=el(_doc100,TABLE);
Element elt6_=el(_doc100,CAPTION);
Element elt7_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_195_30));
at(elt7_,attrs4_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc100,THEAD);
Element elt9_=el(_doc100,TR);
Element elt10_=el(_doc100,TH);
Element elt11_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_195_31));
at(elt11_,attrs5_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt12_=el(_doc100,TH);
Element elt13_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_195_32));
at(elt13_,attrs6_);
ad(elt12_,elt13_);
ad(elt9_,elt12_);
Element elt14_=el(_doc100,TH);
Element elt15_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_195_33));
at(elt15_,attrs7_);
ad(elt14_,elt15_);
ad(elt9_,elt14_);
Element elt16_=el(_doc100,TH);
Element elt17_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_195_34));
at(elt17_,attrs8_);
ad(elt16_,elt17_);
ad(elt9_,elt16_);
Element elt18_=el(_doc100,TH);
Element elt19_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_195_35));
at(elt19_,attrs9_);
ad(elt18_,elt19_);
ad(elt9_,elt18_);
Element elt20_=el(_doc100,TH);
Element elt21_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_195_36));
at(elt21_,attrs10_);
ad(elt20_,elt21_);
ad(elt9_,elt20_);
Element elt22_=el(_doc100,TH);
Element elt23_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_195_37));
at(elt23_,attrs11_);
ad(elt22_,elt23_);
ad(elt9_,elt22_);
Element elt24_=el(_doc100,TH);
Element elt25_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_195_38));
at(elt25_,attrs12_);
ad(elt24_,elt25_);
ad(elt9_,elt24_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
Element elt26_=el(_doc100,TBODY);
Element elt27_=el(_doc100,C_FOR);
CustList<Attr> attrs13_=al(3);
attrs13_.add(at(CLASSNAME,C_P_195_39));
attrs13_.add(at(LIST,C_P_195_40));
attrs13_.add(at(VAR,C_P_195_41));
at(elt27_,attrs13_);
Element elt28_=el(_doc100,TR);
Element elt29_=el(_doc100,TD);
Text txt0_=tx(_doc100,C_P_195_42);
ad(elt29_,txt0_);
ad(elt28_,elt29_);
Element elt30_=el(_doc100,TD);
Text txt1_=tx(_doc100,C_P_195_43);
ad(elt30_,txt1_);
ad(elt28_,elt30_);
Element elt31_=el(_doc100,TD);
Element elt32_=el(_doc100,C_FOR);
CustList<Attr> attrs14_=al(3);
attrs14_.add(at(LIST,C_P_195_44));
attrs14_.add(at(VAR,C_P_195_45));
attrs14_.add(at(CLASSNAME,C_P_195_46));
at(elt32_,attrs14_);
Text txt2_=tx(_doc100,C_P_195_47);
ad(elt32_,txt2_);
ad(elt31_,elt32_);
ad(elt28_,elt31_);
Element elt33_=el(_doc100,TD);
Text txt3_=tx(_doc100,C_P_195_48);
ad(elt33_,txt3_);
ad(elt28_,elt33_);
Element elt34_=el(_doc100,TD);
Element elt35_=el(_doc100,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_195_49));
at(elt35_,attrs15_);
Element elt36_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_195_50));
at(elt36_,attrs16_);
ad(elt35_,elt36_);
ad(elt34_,elt35_);
Element elt37_=el(_doc100,C_IF);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(CONDITION,C_P_195_51));
at(elt37_,attrs17_);
Element elt38_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(VALUE,C_P_195_52));
at(elt38_,attrs18_);
ad(elt37_,elt38_);
ad(elt34_,elt37_);
ad(elt28_,elt34_);
Element elt39_=el(_doc100,TD);
Element elt40_=el(_doc100,C_IF);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(CONDITION,C_P_195_53));
at(elt40_,attrs19_);
Element elt41_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_195_54));
at(elt41_,attrs20_);
ad(elt40_,elt41_);
ad(elt39_,elt40_);
Element elt42_=el(_doc100,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_195_55));
at(elt42_,attrs21_);
Element elt43_=el(_doc100,C_IF);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(CONDITION,C_P_195_56));
at(elt43_,attrs22_);
Element elt44_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs23_=al(1);
attrs23_.add(at(VALUE,C_P_195_57));
at(elt44_,attrs23_);
ad(elt43_,elt44_);
ad(elt42_,elt43_);
Element elt45_=el(_doc100,C_IF);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(CONDITION,C_P_195_58));
at(elt45_,attrs24_);
Element elt46_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs25_=al(1);
attrs25_.add(at(VALUE,C_P_195_59));
at(elt46_,attrs25_);
ad(elt45_,elt46_);
ad(elt42_,elt45_);
ad(elt39_,elt42_);
ad(elt28_,elt39_);
Element elt47_=el(_doc100,TD);
Text txt4_=tx(_doc100,C_P_195_60);
ad(elt47_,txt4_);
ad(elt28_,elt47_);
Element elt48_=el(_doc100,TD);
Element elt49_=el(_doc100,INPUT);
CustList<Attr> attrs26_=al(3);
attrs26_.add(at(C_VARVALUE,C_P_195_61));
attrs26_.add(at(NAME,C_P_195_62));
attrs26_.add(at(TYPE,C_P_195_63));
at(elt49_,attrs26_);
ad(elt48_,elt49_);
ad(elt28_,elt48_);
ad(elt27_,elt28_);
ad(elt26_,elt27_);
ad(elt5_,elt26_);
ad(elt4_,elt5_);
Element elt50_=el(_doc100,C_SUBMIT);
CustList<Attr> attrs27_=al(1);
attrs27_.add(at(MESSAGE,C_P_195_64));
at(elt50_,attrs27_);
ad(elt4_,elt50_);
ad(elt0_,elt4_);
Element elt51_=el(_doc100,FORM);
CustList<Attr> attrs28_=al(4);
attrs28_.add(at(ACTION,C_P_195_65));
attrs28_.add(at(C_COMMAND,C_P_195_66));
attrs28_.add(at(METHOD,C_P_195_67));
attrs28_.add(at(NAME,C_P_195_68));
at(elt51_,attrs28_);
Element elt52_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs29_=al(1);
attrs29_.add(at(VALUE,C_P_195_69));
at(elt52_,attrs29_);
ad(elt51_,elt52_);
Element elt53_=el(_doc100,INPUT);
CustList<Attr> attrs30_=al(5);
attrs30_.add(at(C_VALIDATOR,C_P_195_70));
attrs30_.add(at(C_VARVALUE,C_P_195_71));
attrs30_.add(at(ID,C_P_195_72));
attrs30_.add(at(NAME,C_P_195_73));
attrs30_.add(at(TYPE,C_P_195_74));
at(elt53_,attrs30_);
ad(elt51_,elt53_);
Element elt54_=el(_doc100,SPAN);
CustList<Attr> attrs31_=al(3);
attrs31_.add(at(C_FOR,C_P_195_75));
attrs31_.add(at(CLASS,C_P_195_76));
attrs31_.add(at(C_VALUEMESSAGE,C_P_195_77));
at(elt54_,attrs31_);
ad(elt51_,elt54_);
Element elt55_=el(_doc100,BR);
ad(elt51_,elt55_);
Element elt56_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs32_=al(1);
attrs32_.add(at(VALUE,C_P_195_78));
at(elt56_,attrs32_);
ad(elt51_,elt56_);
Element elt57_=el(_doc100,C_SELECT);
CustList<Attr> attrs33_=al(5);
attrs33_.add(at(DEFAULT,C_P_195_79));
attrs33_.add(at(MAP,C_P_195_80));
attrs33_.add(at(NAME,C_P_195_81));
attrs33_.add(at(UPDATE,C_P_195_82));
attrs33_.add(at(VARVALUE,C_P_195_83));
at(elt57_,attrs33_);
ad(elt51_,elt57_);
Element elt58_=el(_doc100,BR);
ad(elt51_,elt58_);
Element elt59_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs34_=al(1);
attrs34_.add(at(VALUE,C_P_195_84));
at(elt59_,attrs34_);
ad(elt51_,elt59_);
Element elt60_=el(_doc100,INPUT);
CustList<Attr> attrs35_=al(5);
attrs35_.add(at(C_VALIDATOR,C_P_195_85));
attrs35_.add(at(C_VARVALUE,C_P_195_86));
attrs35_.add(at(ID,C_P_195_87));
attrs35_.add(at(NAME,C_P_195_88));
attrs35_.add(at(TYPE,C_P_195_89));
at(elt60_,attrs35_);
ad(elt51_,elt60_);
Element elt61_=el(_doc100,SPAN);
CustList<Attr> attrs36_=al(3);
attrs36_.add(at(C_FOR,C_P_195_90));
attrs36_.add(at(CLASS,C_P_195_91));
attrs36_.add(at(C_VALUEMESSAGE,C_P_195_92));
at(elt61_,attrs36_);
ad(elt51_,elt61_);
Element elt62_=el(_doc100,BR);
ad(elt51_,elt62_);
Element elt63_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs37_=al(1);
attrs37_.add(at(VALUE,C_P_195_93));
at(elt63_,attrs37_);
ad(elt51_,elt63_);
Element elt64_=el(_doc100,INPUT);
CustList<Attr> attrs38_=al(5);
attrs38_.add(at(C_VALIDATOR,C_P_195_94));
attrs38_.add(at(C_VARVALUE,C_P_195_95));
attrs38_.add(at(ID,C_P_195_96));
attrs38_.add(at(NAME,C_P_195_97));
attrs38_.add(at(TYPE,C_P_195_98));
at(elt64_,attrs38_);
ad(elt51_,elt64_);
Element elt65_=el(_doc100,SPAN);
CustList<Attr> attrs39_=al(3);
attrs39_.add(at(C_FOR,C_P_195_99));
attrs39_.add(at(CLASS,C_P_195_100));
attrs39_.add(at(C_VALUEMESSAGE,C_P_195_101));
at(elt65_,attrs39_);
ad(elt51_,elt65_);
Element elt66_=el(_doc100,BR);
ad(elt51_,elt66_);
Element elt67_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs40_=al(1);
attrs40_.add(at(VALUE,C_P_195_102));
at(elt67_,attrs40_);
ad(elt51_,elt67_);
Element elt68_=el(_doc100,INPUT);
CustList<Attr> attrs41_=al(3);
attrs41_.add(at(C_VARVALUE,C_P_195_103));
attrs41_.add(at(NAME,C_P_195_104));
attrs41_.add(at(TYPE,C_P_195_105));
at(elt68_,attrs41_);
ad(elt51_,elt68_);
Element elt69_=el(_doc100,BR);
ad(elt51_,elt69_);
Element elt70_=el(_doc100,C_MESSAGE);
CustList<Attr> attrs42_=al(1);
attrs42_.add(at(VALUE,C_P_195_106));
at(elt70_,attrs42_);
ad(elt51_,elt70_);
Element elt71_=el(_doc100,BR);
ad(elt51_,elt71_);
Element elt72_=el(_doc100,C_FOR);
CustList<Attr> attrs43_=al(5);
attrs43_.add(at(KEY,C_P_195_107));
attrs43_.add(at(MAP,C_P_195_108));
attrs43_.add(at(VALUE,C_P_195_109));
attrs43_.add(at(KEYCLASSNAME,C_P_195_110));
attrs43_.add(at(VARCLASSNAME,C_P_195_111));
at(elt72_,attrs43_);
Text txt5_=tx(_doc100,C_P_195_112);
ad(elt72_,txt5_);
Element elt73_=el(_doc100,INPUT);
CustList<Attr> attrs44_=al(5);
attrs44_.add(at(C_VALIDATOR,C_P_195_113));
attrs44_.add(at(C_VARVALUE,C_P_195_114));
attrs44_.add(at(ID,C_P_195_115));
attrs44_.add(at(NAME,C_P_195_116));
attrs44_.add(at(TYPE,C_P_195_117));
at(elt73_,attrs44_);
ad(elt72_,elt73_);
Element elt74_=el(_doc100,SPAN);
CustList<Attr> attrs45_=al(3);
attrs45_.add(at(C_FOR,C_P_195_118));
attrs45_.add(at(CLASS,C_P_195_119));
attrs45_.add(at(C_VALUEMESSAGE,C_P_195_120));
at(elt74_,attrs45_);
ad(elt72_,elt74_);
Element elt75_=el(_doc100,BR);
ad(elt72_,elt75_);
ad(elt51_,elt72_);
Element elt76_=el(_doc100,BR);
ad(elt51_,elt76_);
Element elt77_=el(_doc100,C_SUBMIT);
CustList<Attr> attrs46_=al(1);
attrs46_.add(at(MESSAGE,C_P_195_122));
at(elt77_,attrs46_);
ad(elt51_,elt77_);
ad(elt0_,elt51_);
ad(_body,elt0_);
}
}
