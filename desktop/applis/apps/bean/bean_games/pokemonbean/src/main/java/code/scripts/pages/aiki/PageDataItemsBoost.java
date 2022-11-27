package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataItemsBoost extends PageCardsCommon{
private static final String C_P_117_0="javahtml";
private static final String C_P_117_1="boost";
private static final String C_P_117_2="msg_item,title";
private static final String C_P_117_3="displayName";
private static final String C_P_117_4="web/css/items.css";
private static final String C_P_117_5="stylesheet";
private static final String C_P_117_6="text/css";
private static final String C_P_117_7="itemBean";
private static final String C_P_117_8="aiki.beans.items";
private static final String C_P_117_9="ItemBean";
private static final String C_P_117_10="$intern.name=name";
private static final String C_P_117_11="!winPp.isZero()";
private static final String C_P_117_12="msg_boost,win_pp";
private static final String C_P_117_13="winPp";
private static final String C_P_117_14="!happiness.isEmpty()";
private static final String C_P_117_15="msg_boost,happiness";
private static final String C_P_117_16="msg_boost,happiness_ball";
private static final String C_P_117_17="msg_boost,happiness_boost";
private static final String C_P_117_18="b";
private static final String C_P_117_19="happiness";
private static final String C_P_117_20="w";
private static final String C_P_117_21="java.lang.Object";
private static final String C_P_117_22="java.lang.Short";
private static final String C_P_117_23="!isBall(([b]))";
private static final String C_P_117_24="msg_boost,happiness_other_ball";
private static final String C_P_117_25="isBall(([b]))";
private static final String C_P_117_26="$clickHappiness({([b])})";
private static final String C_P_117_27="";
private static final String C_P_117_28="{getTrHappiness(([b]))}";
private static final String C_P_117_29="{w}";
private static final String C_P_117_30="!evs.isEmpty()";
private static final String C_P_117_31="msg_boost,evs";
private static final String C_P_117_32="maxEv";
private static final String C_P_117_33="msg_boost,evs_stat";
private static final String C_P_117_34="msg_boost,evs_boost";
private static final String C_P_117_35="s";
private static final String C_P_117_36="evs";
private static final String C_P_117_37="w";
private static final String C_P_117_38="java.lang.Object";
private static final String C_P_117_39="java.lang.Short";
private static final String C_P_117_40="{getTrEv(([s]))}";
private static final String C_P_117_41="{w}";
private PageDataItemsBoost(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc22){
Element elt0_=el(_doc22,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_117_0));
attrs0_.add(at(C_BEAN,C_P_117_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc22,HEAD);
Element elt2_=el(_doc22,TITLE);
Element elt3_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_117_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc22,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_117_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc22,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_117_4));
attrs3_.add(at(REL,C_P_117_5));
attrs3_.add(at(TYPE,C_P_117_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc22,BODY);
build0(elt6_,_doc22);
build1(elt6_,_doc22);
ad(elt0_,elt6_);
_doc22.appendChild(elt0_);
}
static void build0(Element _body,Document _doc22){
Element elt0_=el(_doc22,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_117_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc22,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_117_8));
at(elt1_,attrs1_);
Element elt2_=el(_doc22,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_117_9));
at(elt2_,attrs2_);
Element elt3_=el(_doc22,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_117_10));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc22){
Element elt0_=el(_doc22,P);
Element elt1_=el(_doc22,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_117_11));
at(elt1_,attrs0_);
Element elt2_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_117_12));
at(elt2_,attrs1_);
Element elt3_=el(_doc22,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_117_13));
at(elt3_,attrs2_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt4_=el(_doc22,C_IF);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(CONDITION,C_P_117_14));
at(elt4_,attrs3_);
Element elt5_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_117_15));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc22,TABLE);
Element elt7_=el(_doc22,THEAD);
Element elt8_=el(_doc22,TR);
Element elt9_=el(_doc22,TH);
Element elt10_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_117_16));
at(elt10_,attrs5_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt11_=el(_doc22,TH);
Element elt12_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_117_17));
at(elt12_,attrs6_);
ad(elt11_,elt12_);
ad(elt8_,elt11_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
Element elt13_=el(_doc22,TBODY);
Element elt14_=el(_doc22,C_FOR);
CustList<Attr> attrs7_=al(5);
attrs7_.add(at(KEY,C_P_117_18));
attrs7_.add(at(MAP,C_P_117_19));
attrs7_.add(at(VALUE,C_P_117_20));
attrs7_.add(at(KEYCLASSNAME,C_P_117_21));
attrs7_.add(at(VARCLASSNAME,C_P_117_22));
at(elt14_,attrs7_);
Element elt15_=el(_doc22,TR);
Element elt16_=el(_doc22,C_IF);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(CONDITION,C_P_117_23));
at(elt16_,attrs8_);
Element elt17_=el(_doc22,TD);
Element elt18_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(VALUE,C_P_117_24));
at(elt18_,attrs9_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
Element elt19_=el(_doc22,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_117_25));
at(elt19_,attrs10_);
Element elt20_=el(_doc22,TD);
Element elt21_=el(_doc22,A);
CustList<Attr> attrs11_=al(2);
attrs11_.add(at(C_COMMAND,C_P_117_26));
attrs11_.add(at(HREF,C_P_117_27));
at(elt21_,attrs11_);
Text txt0_=tx(_doc22,C_P_117_28);
ad(elt21_,txt0_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt15_,elt19_);
Element elt22_=el(_doc22,TD);
Text txt1_=tx(_doc22,C_P_117_29);
ad(elt22_,txt1_);
ad(elt15_,elt22_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt6_,elt13_);
ad(elt4_,elt6_);
Element elt23_=el(_doc22,BR);
ad(elt4_,elt23_);
ad(elt0_,elt4_);
Element elt24_=el(_doc22,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_117_30));
at(elt24_,attrs12_);
Element elt25_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_117_31));
at(elt25_,attrs13_);
Element elt26_=el(_doc22,PARAM);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_117_32));
at(elt26_,attrs14_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
Element elt27_=el(_doc22,TABLE);
Element elt28_=el(_doc22,THEAD);
Element elt29_=el(_doc22,TR);
Element elt30_=el(_doc22,TH);
Element elt31_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_117_33));
at(elt31_,attrs15_);
ad(elt30_,elt31_);
ad(elt29_,elt30_);
Element elt32_=el(_doc22,TH);
Element elt33_=el(_doc22,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_117_34));
at(elt33_,attrs16_);
ad(elt32_,elt33_);
ad(elt29_,elt32_);
ad(elt28_,elt29_);
ad(elt27_,elt28_);
Element elt34_=el(_doc22,TBODY);
Element elt35_=el(_doc22,C_FOR);
CustList<Attr> attrs17_=al(5);
attrs17_.add(at(KEY,C_P_117_35));
attrs17_.add(at(MAP,C_P_117_36));
attrs17_.add(at(VALUE,C_P_117_37));
attrs17_.add(at(KEYCLASSNAME,C_P_117_38));
attrs17_.add(at(VARCLASSNAME,C_P_117_39));
at(elt35_,attrs17_);
Element elt36_=el(_doc22,TR);
Element elt37_=el(_doc22,TD);
Text txt2_=tx(_doc22,C_P_117_40);
ad(elt37_,txt2_);
ad(elt36_,elt37_);
Element elt38_=el(_doc22,TD);
Text txt3_=tx(_doc22,C_P_117_41);
ad(elt38_,txt3_);
ad(elt36_,elt38_);
ad(elt35_,elt36_);
ad(elt34_,elt35_);
ad(elt27_,elt34_);
ad(elt24_,elt27_);
Element elt39_=el(_doc22,BR);
ad(elt24_,elt39_);
ad(elt0_,elt24_);
ad(_body,elt0_);
}
}
