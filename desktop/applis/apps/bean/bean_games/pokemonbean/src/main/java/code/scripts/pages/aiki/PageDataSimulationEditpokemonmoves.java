package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationEditpokemonmoves extends PageCardsCommon{
private static final String C_P_196_0="javahtml";
private static final String C_P_196_1="editpokemonmoves";
private static final String C_P_196_2="msg_levelsimu,title_search_moves";
private static final String C_P_196_3="web/css/simulation.css";
private static final String C_P_196_4="stylesheet";
private static final String C_P_196_5="text/css";
private static final String C_P_196_6="$cancel()";
private static final String C_P_196_7="";
private static final String C_P_196_8="msg_levelsimu,cancel";
private static final String C_P_196_9="";
private static final String C_P_196_10="$search";
private static final String C_P_196_11="post";
private static final String C_P_196_12="searching";
private static final String C_P_196_13="msg_moves,content_name";
private static final String C_P_196_14="typedName";
private static final String C_P_196_15="typedName";
private static final String C_P_196_16="text";
private static final String C_P_196_17="msg_moves,cat";
private static final String C_P_196_18="";
private static final String C_P_196_19="categories";
private static final String C_P_196_20="category";
private static final String C_P_196_21="";
private static final String C_P_196_22="category";
private static final String C_P_196_23="msg_moves,content_type";
private static final String C_P_196_24="typedType";
private static final String C_P_196_25="typedType";
private static final String C_P_196_26="text";
private static final String C_P_196_27="msg_moves,content_type_whole";
private static final String C_P_196_28="wholeWord";
private static final String C_P_196_29="wholeWord";
private static final String C_P_196_30="checkbox";
private static final String C_P_196_31="player";
private static final String C_P_196_32="msg_simulation,available_moves";
private static final String C_P_196_33="availableMovesOnly";
private static final String C_P_196_34="availableMovesOnly";
private static final String C_P_196_35="checkbox";
private static final String C_P_196_36="msg_simulation,search";
private static final String C_P_196_37="";
private static final String C_P_196_38="$addMoves";
private static final String C_P_196_39="post";
private static final String C_P_196_40="add";
private static final String C_P_196_41="msg_moves,moves";
private static final String C_P_196_42="msg_moves,name_h";
private static final String C_P_196_43="msg_moves,pp_h";
private static final String C_P_196_44="msg_moves,types_h";
private static final String C_P_196_45="msg_moves,cat_h";
private static final String C_P_196_46="msg_moves,damag_h";
private static final String C_P_196_47="msg_moves,direc_h";
private static final String C_P_196_48="msg_moves,prio_h";
private static final String C_P_196_49="msg_simulation,selected";
private static final String C_P_196_50="aiki.beans.facade.simulation.dto.SelectLineMove";
private static final String C_P_196_51="moves";
private static final String C_P_196_52="d";
private static final String C_P_196_53="{d.displayName}";
private static final String C_P_196_54="{d.pp}";
private static final String C_P_196_55="d.getTypes()";
private static final String C_P_196_56="t";
private static final String C_P_196_57="java.lang.String";
private static final String C_P_196_58="{t} -";
private static final String C_P_196_59="{d.category}";
private static final String C_P_196_60="d.isDamageMove()";
private static final String C_P_196_61="msg_moves,damaging";
private static final String C_P_196_62="!d.isDamageMove()";
private static final String C_P_196_63="msg_moves,status";
private static final String C_P_196_64="!d.isDamageMove()";
private static final String C_P_196_65="msg_moves,status_indirect";
private static final String C_P_196_66="d.isDamageMove()";
private static final String C_P_196_67="d.isDirect()";
private static final String C_P_196_68="msg_moves,damaging_direct";
private static final String C_P_196_69="!d.isDirect()";
private static final String C_P_196_70="msg_moves,damaging_indirect";
private static final String C_P_196_71="{d.priority}";
private static final String C_P_196_72="d.selected";
private static final String C_P_196_73="d.selected";
private static final String C_P_196_74="checkbox";
private static final String C_P_196_75="msg_simulation,add";
private PageDataSimulationEditpokemonmoves(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc101){
Element elt0_=el(_doc101,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_196_0));
attrs0_.add(at(C_BEAN,C_P_196_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc101,HEAD);
Element elt2_=el(_doc101,TITLE);
Element elt3_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_196_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc101,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_196_3));
attrs2_.add(at(REL,C_P_196_4));
attrs2_.add(at(TYPE,C_P_196_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc101,BODY);
build0(elt5_,_doc101);
build1(elt5_,_doc101);
build2(elt5_,_doc101);
ad(elt0_,elt5_);
_doc101.appendChild(elt0_);
}
static void build0(Element _body,Document _doc101){
Element elt0_=el(_doc101,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_196_6));
attrs0_.add(at(HREF,C_P_196_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_196_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc101){
Element elt0_=el(_doc101,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_196_9));
attrs0_.add(at(C_COMMAND,C_P_196_10));
attrs0_.add(at(METHOD,C_P_196_11));
attrs0_.add(at(NAME,C_P_196_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_196_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc101,INPUT);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(C_VARVALUE,C_P_196_14));
attrs2_.add(at(NAME,C_P_196_15));
attrs2_.add(at(TYPE,C_P_196_16));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc101,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_196_17));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
Element elt5_=el(_doc101,C_SELECT);
CustList<Attr> attrs4_=al(5);
attrs4_.add(at(DEFAULT,C_P_196_18));
attrs4_.add(at(MAP,C_P_196_19));
attrs4_.add(at(NAME,C_P_196_20));
attrs4_.add(at(UPDATE,C_P_196_21));
attrs4_.add(at(VARVALUE,C_P_196_22));
at(elt5_,attrs4_);
ad(elt0_,elt5_);
Element elt6_=el(_doc101,BR);
ad(elt0_,elt6_);
Element elt7_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_196_23));
at(elt7_,attrs5_);
ad(elt0_,elt7_);
Element elt8_=el(_doc101,INPUT);
CustList<Attr> attrs6_=al(3);
attrs6_.add(at(C_VARVALUE,C_P_196_24));
attrs6_.add(at(NAME,C_P_196_25));
attrs6_.add(at(TYPE,C_P_196_26));
at(elt8_,attrs6_);
ad(elt0_,elt8_);
Element elt9_=el(_doc101,BR);
ad(elt0_,elt9_);
Element elt10_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_196_27));
at(elt10_,attrs7_);
ad(elt0_,elt10_);
Element elt11_=el(_doc101,INPUT);
CustList<Attr> attrs8_=al(3);
attrs8_.add(at(C_VARVALUE,C_P_196_28));
attrs8_.add(at(NAME,C_P_196_29));
attrs8_.add(at(TYPE,C_P_196_30));
at(elt11_,attrs8_);
ad(elt0_,elt11_);
Element elt12_=el(_doc101,BR);
ad(elt0_,elt12_);
Element elt13_=el(_doc101,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_196_31));
at(elt13_,attrs9_);
Element elt14_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_196_32));
at(elt14_,attrs10_);
ad(elt13_,elt14_);
Element elt15_=el(_doc101,INPUT);
CustList<Attr> attrs11_=al(3);
attrs11_.add(at(C_VARVALUE,C_P_196_33));
attrs11_.add(at(NAME,C_P_196_34));
attrs11_.add(at(TYPE,C_P_196_35));
at(elt15_,attrs11_);
ad(elt13_,elt15_);
Element elt16_=el(_doc101,BR);
ad(elt13_,elt16_);
ad(elt0_,elt13_);
Element elt17_=el(_doc101,C_SUBMIT);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(MESSAGE,C_P_196_36));
at(elt17_,attrs12_);
ad(elt0_,elt17_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc101){
Element elt0_=el(_doc101,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_196_37));
attrs0_.add(at(C_COMMAND,C_P_196_38));
attrs0_.add(at(METHOD,C_P_196_39));
attrs0_.add(at(NAME,C_P_196_40));
at(elt0_,attrs0_);
Element elt1_=el(_doc101,TABLE);
Element elt2_=el(_doc101,CAPTION);
Element elt3_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_196_41));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc101,THEAD);
Element elt5_=el(_doc101,TR);
Element elt6_=el(_doc101,TH);
Element elt7_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_196_42));
at(elt7_,attrs2_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc101,TH);
Element elt9_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_196_43));
at(elt9_,attrs3_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
Element elt10_=el(_doc101,TH);
Element elt11_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_196_44));
at(elt11_,attrs4_);
ad(elt10_,elt11_);
ad(elt5_,elt10_);
Element elt12_=el(_doc101,TH);
Element elt13_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_196_45));
at(elt13_,attrs5_);
ad(elt12_,elt13_);
ad(elt5_,elt12_);
Element elt14_=el(_doc101,TH);
Element elt15_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_196_46));
at(elt15_,attrs6_);
ad(elt14_,elt15_);
ad(elt5_,elt14_);
Element elt16_=el(_doc101,TH);
Element elt17_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_196_47));
at(elt17_,attrs7_);
ad(elt16_,elt17_);
ad(elt5_,elt16_);
Element elt18_=el(_doc101,TH);
Element elt19_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_196_48));
at(elt19_,attrs8_);
ad(elt18_,elt19_);
ad(elt5_,elt18_);
Element elt20_=el(_doc101,TH);
Element elt21_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_196_49));
at(elt21_,attrs9_);
ad(elt20_,elt21_);
ad(elt5_,elt20_);
ad(elt4_,elt5_);
ad(elt1_,elt4_);
Element elt22_=el(_doc101,TBODY);
Element elt23_=el(_doc101,C_FOR);
CustList<Attr> attrs10_=al(3);
attrs10_.add(at(CLASSNAME,C_P_196_50));
attrs10_.add(at(LIST,C_P_196_51));
attrs10_.add(at(VAR,C_P_196_52));
at(elt23_,attrs10_);
Element elt24_=el(_doc101,TR);
Element elt25_=el(_doc101,TD);
Text txt0_=tx(_doc101,C_P_196_53);
ad(elt25_,txt0_);
ad(elt24_,elt25_);
Element elt26_=el(_doc101,TD);
Text txt1_=tx(_doc101,C_P_196_54);
ad(elt26_,txt1_);
ad(elt24_,elt26_);
Element elt27_=el(_doc101,TD);
Element elt28_=el(_doc101,C_FOR);
CustList<Attr> attrs11_=al(3);
attrs11_.add(at(LIST,C_P_196_55));
attrs11_.add(at(VAR,C_P_196_56));
attrs11_.add(at(CLASSNAME,C_P_196_57));
at(elt28_,attrs11_);
Text txt2_=tx(_doc101,C_P_196_58);
ad(elt28_,txt2_);
ad(elt27_,elt28_);
ad(elt24_,elt27_);
Element elt29_=el(_doc101,TD);
Text txt3_=tx(_doc101,C_P_196_59);
ad(elt29_,txt3_);
ad(elt24_,elt29_);
Element elt30_=el(_doc101,TD);
Element elt31_=el(_doc101,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_196_60));
at(elt31_,attrs12_);
Element elt32_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_196_61));
at(elt32_,attrs13_);
ad(elt31_,elt32_);
ad(elt30_,elt31_);
Element elt33_=el(_doc101,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_196_62));
at(elt33_,attrs14_);
Element elt34_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_196_63));
at(elt34_,attrs15_);
ad(elt33_,elt34_);
ad(elt30_,elt33_);
ad(elt24_,elt30_);
Element elt35_=el(_doc101,TD);
Element elt36_=el(_doc101,C_IF);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(CONDITION,C_P_196_64));
at(elt36_,attrs16_);
Element elt37_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_196_65));
at(elt37_,attrs17_);
ad(elt36_,elt37_);
ad(elt35_,elt36_);
Element elt38_=el(_doc101,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_196_66));
at(elt38_,attrs18_);
Element elt39_=el(_doc101,C_IF);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(CONDITION,C_P_196_67));
at(elt39_,attrs19_);
Element elt40_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_196_68));
at(elt40_,attrs20_);
ad(elt39_,elt40_);
ad(elt38_,elt39_);
Element elt41_=el(_doc101,C_IF);
CustList<Attr> attrs21_=al(1);
attrs21_.add(at(CONDITION,C_P_196_69));
at(elt41_,attrs21_);
Element elt42_=el(_doc101,C_MESSAGE);
CustList<Attr> attrs22_=al(1);
attrs22_.add(at(VALUE,C_P_196_70));
at(elt42_,attrs22_);
ad(elt41_,elt42_);
ad(elt38_,elt41_);
ad(elt35_,elt38_);
ad(elt24_,elt35_);
Element elt43_=el(_doc101,TD);
Text txt4_=tx(_doc101,C_P_196_71);
ad(elt43_,txt4_);
ad(elt24_,elt43_);
Element elt44_=el(_doc101,TD);
Element elt45_=el(_doc101,INPUT);
CustList<Attr> attrs23_=al(3);
attrs23_.add(at(C_VARVALUE,C_P_196_72));
attrs23_.add(at(NAME,C_P_196_73));
attrs23_.add(at(TYPE,C_P_196_74));
at(elt45_,attrs23_);
ad(elt44_,elt45_);
ad(elt24_,elt44_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt1_,elt22_);
ad(elt0_,elt1_);
Element elt46_=el(_doc101,C_SUBMIT);
CustList<Attr> attrs24_=al(1);
attrs24_.add(at(MESSAGE,C_P_196_75));
at(elt46_,attrs24_);
ad(elt0_,elt46_);
ad(_body,elt0_);
}
}
