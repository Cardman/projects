package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffcommonstatistics extends PageCardsCommon{
private static final String C_P_149_0="javahtml";
private static final String C_P_149_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_COMMONSTATISTICS;
private static final String C_P_149_2="web/css/moves.css";
private static final String C_P_149_3="stylesheet";
private static final String C_P_149_4="text/css";
private static final String C_P_149_5="msg_effcommonstatistics,effect";
private static final String C_P_149_6="effectBean";
private static final String C_P_149_7="aiki.beans.moves.effects";
private static final String C_P_149_8="EffectBean";
private static final String C_P_149_9="$intern.index=index";
private static final String C_P_149_10="$intern.move=move";
private static final String C_P_149_11="msg_effcommonstatistics,common";
private static final String C_P_149_12="msg_effcommonstatistics,common_stat";
private static final String C_P_149_13="msg_effcommonstatistics,common_value";
private static final String C_P_149_14="s";
private static final String C_P_149_15="commonValue";
private static final String C_P_149_16="f";
private static final String C_P_149_17="java.lang.Object";
private static final String C_P_149_18="java.lang.String";
private static final String C_P_149_19="{getTrStatistic(([s]))}";
private static final String C_P_149_20="";
private static final String C_P_149_21="msg_effcommonstatistics,formula";
private static final String C_P_149_22="f";
private static final String C_P_149_23="!mapVarsCommonStatistics.isEmpty()";
private static final String C_P_149_24="c";
private static final String C_P_149_25="mapVarsCommonStatistics";
private static final String C_P_149_26="r";
private static final String C_P_149_27="java.lang.String";
private static final String C_P_149_28="java.lang.String";
private static final String C_P_149_29="{c} :";
private static final String C_P_149_30="";
private static final String C_P_149_31="msg_effcommonstatistics,formula";
private static final String C_P_149_32="r";
private PageDataMovesEffectsEffcommonstatistics(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc54){
Element elt0_=el(_doc54,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_149_0));
attrs0_.add(at(C_BEAN,C_P_149_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc54,HEAD);
Element elt2_=el(_doc54,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_149_2));
attrs1_.add(at(REL,C_P_149_3));
attrs1_.add(at(TYPE,C_P_149_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc54,BODY);
build0(elt3_,_doc54);
ad(elt0_,elt3_);
_doc54.appendChild(elt0_);
}
static void build0(Element _body,Document _doc54){
Element elt0_=el(_doc54,P);
Element elt1_=el(_doc54,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_149_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc54,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_149_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc54,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_149_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc54,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_149_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc54,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_149_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc54,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_149_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc54,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_149_11));
at(elt7_,attrs6_);
ad(elt0_,elt7_);
Element elt8_=el(_doc54,TABLE);
Element elt9_=el(_doc54,THEAD);
Element elt10_=el(_doc54,TR);
Element elt11_=el(_doc54,TH);
Element elt12_=el(_doc54,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_149_12));
at(elt12_,attrs7_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc54,TH);
Element elt14_=el(_doc54,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_149_13));
at(elt14_,attrs8_);
ad(elt13_,elt14_);
ad(elt10_,elt13_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt15_=el(_doc54,TBODY);
Element elt16_=el(_doc54,C_FOR);
CustList<Attr> attrs9_=al(5);
attrs9_.add(at(KEY,C_P_149_14));
attrs9_.add(at(MAP,C_P_149_15));
attrs9_.add(at(VALUE,C_P_149_16));
attrs9_.add(at(KEYCLASSNAME,C_P_149_17));
attrs9_.add(at(VARCLASSNAME,C_P_149_18));
at(elt16_,attrs9_);
Element elt17_=el(_doc54,TR);
Element elt18_=el(_doc54,TD);
Text txt0_=tx(_doc54,C_P_149_19);
ad(elt18_,txt0_);
ad(elt17_,elt18_);
Element elt19_=el(_doc54,TD);
Element elt20_=el(_doc54,C_MESSAGE);
CustList<Attr> attrs10_=al(2);
attrs10_.add(at(QUOTED,C_P_149_20));
attrs10_.add(at(VALUE,C_P_149_21));
at(elt20_,attrs10_);
Element elt21_=el(_doc54,PARAM);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_149_22));
at(elt21_,attrs11_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt17_,elt19_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt8_,elt15_);
ad(elt0_,elt8_);
Element elt22_=el(_doc54,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_149_23));
at(elt22_,attrs12_);
Element elt23_=el(_doc54,UL);
Element elt24_=el(_doc54,C_FOR);
CustList<Attr> attrs13_=al(5);
attrs13_.add(at(KEY,C_P_149_24));
attrs13_.add(at(MAP,C_P_149_25));
attrs13_.add(at(VALUE,C_P_149_26));
attrs13_.add(at(KEYCLASSNAME,C_P_149_27));
attrs13_.add(at(VARCLASSNAME,C_P_149_28));
at(elt24_,attrs13_);
Element elt25_=el(_doc54,LI);
Text txt1_=tx(_doc54,C_P_149_29);
ad(elt25_,txt1_);
Element elt26_=el(_doc54,C_MESSAGE);
CustList<Attr> attrs14_=al(2);
attrs14_.add(at(QUOTED,C_P_149_30));
attrs14_.add(at(VALUE,C_P_149_31));
at(elt26_,attrs14_);
Element elt27_=el(_doc54,PARAM);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_149_32));
at(elt27_,attrs15_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt0_,elt22_);
ad(_body,elt0_);
}
}
