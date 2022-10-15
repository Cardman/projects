package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationSimulationlevel extends PageAikiCommon{
private static final String C_P_202_0="javahtml";
private static final String C_P_202_1="level_simu";
private static final String C_P_202_2="web/css/simulation.css";
private static final String C_P_202_3="stylesheet";
private static final String C_P_202_4="text/css";
private static final String C_P_202_5="possibleMultiLayer";
private static final String C_P_202_6="msg_levelsimu,title_level_place";
private static final String C_P_202_7="placeName";
private static final String C_P_202_8="levelIndex";
private static final String C_P_202_9="!possibleMultiLayer";
private static final String C_P_202_10="outside";
private static final String C_P_202_11="road";
private static final String C_P_202_12="msg_levelsimu,title_out_road";
private static final String C_P_202_13="placeName";
private static final String C_P_202_14="!road";
private static final String C_P_202_15="msg_levelsimu,title_out_city";
private static final String C_P_202_16="placeName";
private static final String C_P_202_17="!outside";
private static final String C_P_202_18="gym";
private static final String C_P_202_19="msg_levelsimu,title_gym";
private static final String C_P_202_20="placeName";
private static final String C_P_202_21="pokemonCenter";
private static final String C_P_202_22="msg_levelsimu,title_pk_center";
private static final String C_P_202_23="placeName";
private static final String C_P_202_24="";
private static final String C_P_202_25="$l()";
private static final String C_P_202_26="post";
private static final String C_P_202_27="msg_levelsimu,no_fight";
private static final String C_P_202_28="noFight";
private static final String C_P_202_29="noFight";
private static final String C_P_202_30="text";
private static final String C_P_202_31="noFight";
private static final String C_P_202_32="submit";
private static final String C_P_202_33="OK";
private static final String C_P_202_34="$cancel()";
private static final String C_P_202_35="";
private static final String C_P_202_36="msg_levelsimu,cancel";
private static final String C_P_202_37="{getMapWidth()}";
private static final String C_P_202_38="p";
private static final String C_P_202_39="tiles";
private static final String C_P_202_40="b";
private static final String C_P_202_41="java.lang.Object";
private static final String C_P_202_42="java.lang.String";
private static final String C_P_202_43="$clickTile({([p])})";
private static final String C_P_202_44="0";
private static final String C_P_202_45="{b}";
private PageDataSimulationSimulationlevel(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc107){
Element elt0_=el(_doc107,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_202_0));
attrs0_.add(at(C_BEAN,C_P_202_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc107,HEAD);
Element elt2_=el(_doc107,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_202_2));
attrs1_.add(at(REL,C_P_202_3));
attrs1_.add(at(TYPE,C_P_202_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc107,TITLE);
Element elt4_=el(_doc107,C_IF);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(CONDITION,C_P_202_5));
at(elt4_,attrs2_);
Element elt5_=el(_doc107,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_202_6));
at(elt5_,attrs3_);
Element elt6_=el(_doc107,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_202_7));
at(elt6_,attrs4_);
ad(elt5_,elt6_);
Element elt7_=el(_doc107,PARAM);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_202_8));
at(elt7_,attrs5_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt8_=el(_doc107,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_202_9));
at(elt8_,attrs6_);
Element elt9_=el(_doc107,C_IF);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(CONDITION,C_P_202_10));
at(elt9_,attrs7_);
Element elt10_=el(_doc107,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_202_11));
at(elt10_,attrs8_);
Element elt11_=el(_doc107,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_202_12));
at(elt11_,attrs9_);
Element elt12_=el(_doc107,PARAM);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_202_13));
at(elt12_,attrs10_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
Element elt13_=el(_doc107,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_202_14));
at(elt13_,attrs11_);
Element elt14_=el(_doc107,C_MESSAGE);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(VALUE,C_P_202_15));
at(elt14_,attrs12_);
Element elt15_=el(_doc107,PARAM);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_202_16));
at(elt15_,attrs13_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt9_,elt13_);
ad(elt8_,elt9_);
Element elt16_=el(_doc107,C_IF);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(CONDITION,C_P_202_17));
at(elt16_,attrs14_);
Element elt17_=el(_doc107,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_202_18));
at(elt17_,attrs15_);
Element elt18_=el(_doc107,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_202_19));
at(elt18_,attrs16_);
Element elt19_=el(_doc107,PARAM);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_202_20));
at(elt19_,attrs17_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
Element elt20_=el(_doc107,C_IF);
CustList<Attr> attrs18_=al(1);
attrs18_.add(at(CONDITION,C_P_202_21));
at(elt20_,attrs18_);
Element elt21_=el(_doc107,C_MESSAGE);
CustList<Attr> attrs19_=al(1);
attrs19_.add(at(VALUE,C_P_202_22));
at(elt21_,attrs19_);
Element elt22_=el(_doc107,PARAM);
CustList<Attr> attrs20_=al(1);
attrs20_.add(at(VALUE,C_P_202_23));
at(elt22_,attrs20_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt16_,elt20_);
ad(elt8_,elt16_);
ad(elt3_,elt8_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt23_=el(_doc107,BODY);
build0(elt23_,_doc107);
build1(elt23_,_doc107);
build2(elt23_,_doc107);
build3(elt23_,_doc107);
build4(elt23_,_doc107);
ad(elt0_,elt23_);
_doc107.appendChild(elt0_);
}
static void build0(Element _body,Document _doc107){
Element elt0_=el(_doc107,FORM);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(ACTION,C_P_202_24));
attrs0_.add(at(C_COMMAND,C_P_202_25));
attrs0_.add(at(METHOD,C_P_202_26));
at(elt0_,attrs0_);
Element elt1_=el(_doc107,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_202_27));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc107,INPUT);
CustList<Attr> attrs2_=al(4);
attrs2_.add(at(C_VARVALUE,C_P_202_28));
attrs2_.add(at(NAME,C_P_202_29));
attrs2_.add(at(TYPE,C_P_202_30));
attrs2_.add(at(VALUE,C_P_202_31));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc107,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc107,INPUT);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(TYPE,C_P_202_32));
attrs3_.add(at(VALUE,C_P_202_33));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc107){
Element elt0_=el(_doc107,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc107){
Element elt0_=el(_doc107,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_202_34));
attrs0_.add(at(HREF,C_P_202_35));
at(elt0_,attrs0_);
Element elt1_=el(_doc107,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_202_36));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc107,BR);
ad(_body,elt2_);
}
static void build3(Element _body,Document _doc107){
Element elt0_=el(_doc107,MAP);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(WIDTH,C_P_202_37));
at(elt0_,attrs0_);
Element elt1_=el(_doc107,C_FOR);
CustList<Attr> attrs1_=al(5);
attrs1_.add(at(KEY,C_P_202_38));
attrs1_.add(at(MAP,C_P_202_39));
attrs1_.add(at(VALUE,C_P_202_40));
attrs1_.add(at(KEYCLASSNAME,C_P_202_41));
attrs1_.add(at(VARCLASSNAME,C_P_202_42));
at(elt1_,attrs1_);
Element elt2_=el(_doc107,A);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(C_COMMAND,C_P_202_43));
at(elt2_,attrs2_);
Element elt3_=el(_doc107,C_IMG);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(BORDER,C_P_202_44));
attrs3_.add(at(SRC,C_P_202_45));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc107){
Element elt0_=el(_doc107,BR);
ad(_body,elt0_);
}
}
