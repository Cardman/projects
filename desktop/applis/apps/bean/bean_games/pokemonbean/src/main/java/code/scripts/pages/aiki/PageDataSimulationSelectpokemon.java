package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataSimulationSelectpokemon extends PageCardsCommon{
private static final String C_P_200_0="javahtml";
private static final String C_P_200_1="selectpokemon";
private static final String C_P_200_2="msg_levelsimu,title_select_pk";
private static final String C_P_200_3=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_200_4="stylesheet";
private static final String C_P_200_5="text/css";
//private static final String C_P_200_6="$cancel()";
//private static final String C_P_200_7="";
//private static final String C_P_200_8="msg_levelsimu,cancel";
private static final String C_P_200_9="";
private static final String C_P_200_10="$search";
private static final String C_P_200_11="post";
private static final String C_P_200_12="searching";
private static final String C_P_200_13="msg_pokedex,content_name";
private static final String C_P_200_14="typedName";
private static final String C_P_200_15="typedName";
private static final String C_P_200_16="text";
private static final String C_P_200_17="msg_pokedex,content_type";
private static final String C_P_200_18="typedType";
private static final String C_P_200_19="typedType";
private static final String C_P_200_20="text";
private static final String C_P_200_21="msg_pokedex,content_type_whole";
private static final String C_P_200_22="wholeWord";
private static final String C_P_200_23="wholeWord";
private static final String C_P_200_24="checkbox";
private static final String C_P_200_25="msg_pokedex,has_evo";
private static final String C_P_200_26="";
private static final String C_P_200_27="booleans";
private static final String C_P_200_28="hasEvo";
private static final String C_P_200_29="";
private static final String C_P_200_30="hasEvo";
private static final String C_P_200_31="msg_pokedex,is_evo";
private static final String C_P_200_32="";
private static final String C_P_200_33="booleans";
private static final String C_P_200_34="isEvo";
private static final String C_P_200_35="";
private static final String C_P_200_36="isEvo";
private static final String C_P_200_37="msg_pokedex,leg";
private static final String C_P_200_38="";
private static final String C_P_200_39="booleans";
private static final String C_P_200_40="isLeg";
private static final String C_P_200_41="";
private static final String C_P_200_42="isLeg";
private static final String C_P_200_44="msg_pokedex,ok";
private static final String C_P_200_45="msg_pokedex,pokedex";
private static final String C_P_200_46="msg_pokedex,image";
private static final String C_P_200_47="msg_pokedex,name";
private static final String C_P_200_48="msg_pokedex,types";
private static final String C_P_200_49="msg_pokedex,evos";
private static final String C_P_200_50="aiki.beans.facade.dto.PokemonLine";
private static final String C_P_200_51="pokedex";
private static final String C_P_200_52="d";
private static final String C_P_200_53="getMiniImage(([d]))";
//private static final String C_P_200_54="$clickLink({([d])})";
//private static final String C_P_200_55="";
//private static final String C_P_200_56="{d.displayName}";
private static final String C_P_200_57="d.types";
private static final String C_P_200_58="t";
private static final String C_P_200_59="java.lang.String";
private static final String C_P_200_60="{t} -";
private static final String C_P_200_61="{d.evolutions}";
private PageDataSimulationSelectpokemon(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc105){
Element elt0_=el(_doc105,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_200_0));
attrs0_.add(at(C_BEAN,C_P_200_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc105,HEAD);
Element elt2_=el(_doc105,TITLE);
Element elt3_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_200_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc105,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_200_3));
attrs2_.add(at(REL,C_P_200_4));
attrs2_.add(at(TYPE,C_P_200_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc105,BODY);
//build0(elt5_,_doc105);
build1(elt5_,_doc105);
build2(elt5_,_doc105);
build3(elt5_,_doc105);
ad(elt0_,elt5_);
_doc105.appendChild(elt0_);
}
//static void build0(Element _body,Document _doc105){
//Element elt0_=el(_doc105,A);
//CustList<Attr> attrs0_=al(2);
//attrs0_.add(at(C_COMMAND,C_P_200_6));
//attrs0_.add(at(HREF,C_P_200_7));
//at(elt0_,attrs0_);
//Element elt1_=el(_doc105,C_MESSAGE);
//CustList<Attr> attrs1_=al(1);
//attrs1_.add(at(VALUE,C_P_200_8));
//at(elt1_,attrs1_);
//ad(elt0_,elt1_);
//ad(_body,elt0_);
//}
static void build1(Element _body,Document _doc105){
Element elt0_=el(_doc105,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_200_9));
attrs0_.add(at(C_COMMAND,C_P_200_10));
attrs0_.add(at(METHOD,C_P_200_11));
attrs0_.add(at(NAME,C_P_200_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_200_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc105,INPUT);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(C_VARVALUE,C_P_200_14));
attrs2_.add(at(NAME,C_P_200_15));
attrs2_.add(at(TYPE,C_P_200_16));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc105,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_200_17));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
Element elt5_=el(_doc105,INPUT);
CustList<Attr> attrs4_=al(3);
attrs4_.add(at(C_VARVALUE,C_P_200_18));
attrs4_.add(at(NAME,C_P_200_19));
attrs4_.add(at(TYPE,C_P_200_20));
at(elt5_,attrs4_);
ad(elt0_,elt5_);
Element elt6_=el(_doc105,BR);
ad(elt0_,elt6_);
Element elt7_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_200_21));
at(elt7_,attrs5_);
ad(elt0_,elt7_);
Element elt8_=el(_doc105,INPUT);
CustList<Attr> attrs6_=al(3);
attrs6_.add(at(C_VARVALUE,C_P_200_22));
attrs6_.add(at(NAME,C_P_200_23));
attrs6_.add(at(TYPE,C_P_200_24));
at(elt8_,attrs6_);
ad(elt0_,elt8_);
Element elt9_=el(_doc105,BR);
ad(elt0_,elt9_);
Element elt10_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_200_25));
at(elt10_,attrs7_);
ad(elt0_,elt10_);
Element elt11_=el(_doc105,C_SELECT);
CustList<Attr> attrs8_=al(5);
attrs8_.add(at(DEFAULT,C_P_200_26));
attrs8_.add(at(MAP,C_P_200_27));
attrs8_.add(at(NAME,C_P_200_28));
attrs8_.add(at(UPDATE,C_P_200_29));
attrs8_.add(at(VARVALUE,C_P_200_30));
at(elt11_,attrs8_);
ad(elt0_,elt11_);
Element elt12_=el(_doc105,BR);
ad(elt0_,elt12_);
Element elt13_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_200_31));
at(elt13_,attrs9_);
ad(elt0_,elt13_);
Element elt14_=el(_doc105,C_SELECT);
CustList<Attr> attrs10_=al(5);
attrs10_.add(at(DEFAULT,C_P_200_32));
attrs10_.add(at(MAP,C_P_200_33));
attrs10_.add(at(NAME,C_P_200_34));
attrs10_.add(at(UPDATE,C_P_200_35));
attrs10_.add(at(VARVALUE,C_P_200_36));
at(elt14_,attrs10_);
ad(elt0_,elt14_);
Element elt15_=el(_doc105,BR);
ad(elt0_,elt15_);
Element elt16_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_200_37));
at(elt16_,attrs11_);
ad(elt0_,elt16_);
Element elt17_=el(_doc105,C_SELECT);
CustList<Attr> attrs12_=al(5);
attrs12_.add(at(DEFAULT,C_P_200_38));
attrs12_.add(at(MAP,C_P_200_39));
attrs12_.add(at(NAME,C_P_200_40));
attrs12_.add(at(UPDATE,C_P_200_41));
attrs12_.add(at(VARVALUE,C_P_200_42));
at(elt17_,attrs12_);
ad(elt0_,elt17_);
Element elt18_=el(_doc105,BR);
ad(elt0_,elt18_);
Element elt19_=el(_doc105,C_SUBMIT);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(MESSAGE,C_P_200_44));
at(elt19_,attrs13_);
ad(elt0_,elt19_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc105){
Element elt0_=el(_doc105,BR);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc105){
Element elt0_=el(_doc105,TABLE);
Element elt1_=el(_doc105,CAPTION);
Element elt2_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_200_45));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc105,THEAD);
Element elt4_=el(_doc105,TR);
Element elt5_=el(_doc105,TH);
Element elt6_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_200_46));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc105,TH);
Element elt8_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_200_47));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
Element elt9_=el(_doc105,TH);
Element elt10_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_200_48));
at(elt10_,attrs3_);
ad(elt9_,elt10_);
ad(elt4_,elt9_);
Element elt11_=el(_doc105,TH);
Element elt12_=el(_doc105,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_200_49));
at(elt12_,attrs4_);
ad(elt11_,elt12_);
ad(elt4_,elt11_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt13_=el(_doc105,TBODY);
Element elt14_=el(_doc105,C_FOR);
CustList<Attr> attrs5_=al(3);
attrs5_.add(at(CLASSNAME,C_P_200_50));
attrs5_.add(at(LIST,C_P_200_51));
attrs5_.add(at(VAR,C_P_200_52));
at(elt14_,attrs5_);
Element elt15_=el(_doc105,TR);
Element elt16_=el(_doc105,TD);
Element elt17_=el(_doc105,C_IMG);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(SRC,C_P_200_53));
at(elt17_,attrs6_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
Element elt18_=el(_doc105,TD);
//Element elt19_=el(_doc105,A);
//CustList<Attr> attrs7_=al(2);
//attrs7_.add(at(C_COMMAND,C_P_200_54));
//attrs7_.add(at(HREF,C_P_200_55));
//at(elt19_,attrs7_);
//Text txt0_=tx(_doc105,C_P_200_56);
//ad(elt19_,txt0_);
//ad(elt18_,elt19_);
ad(elt15_,elt18_);
Element elt20_=el(_doc105,TD);
Element elt21_=el(_doc105,C_FOR);
CustList<Attr> attrs8_=al(3);
attrs8_.add(at(LIST,C_P_200_57));
attrs8_.add(at(VAR,C_P_200_58));
attrs8_.add(at(CLASSNAME,C_P_200_59));
at(elt21_,attrs8_);
Text txt1_=tx(_doc105,C_P_200_60);
ad(elt21_,txt1_);
ad(elt20_,elt21_);
ad(elt15_,elt20_);
Element elt22_=el(_doc105,TD);
Text txt2_=tx(_doc105,C_P_200_61);
ad(elt22_,txt2_);
ad(elt15_,elt22_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
ad(_body,elt0_);
}
}
