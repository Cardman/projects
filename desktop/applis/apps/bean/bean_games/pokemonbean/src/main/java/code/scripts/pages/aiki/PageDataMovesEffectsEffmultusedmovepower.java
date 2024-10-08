package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffmultusedmovepower extends PageCardsCommon{
private static final String C_P_160_0="javahtml";
private static final String C_P_160_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_MULTUSEDMOVEPOWER;
private static final String C_P_160_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_160_3="stylesheet";
private static final String C_P_160_4="text/css";
private static final String C_P_160_5="msg_effmultusedmovepower,effect";
private static final String C_P_160_6="effectBean";
private static final String C_P_160_7="aiki.beans.moves.effects";
private static final String C_P_160_8="EffectBean";
private static final String C_P_160_9="$intern.index=index";
private static final String C_P_160_10="$intern.move=move";
private static final String C_P_160_11="msg_effmultusedmovepower,mult_power";
private static final String C_P_160_12="msg_effmultusedmovepower,mult_power_type";
private static final String C_P_160_13="msg_effmultusedmovepower,mult_power_rate";
private static final String C_P_160_14="t";
private static final String C_P_160_15="multMovePowerFctType";
private static final String C_P_160_16="r";
private static final String C_P_160_17="java.lang.Object";
private static final String C_P_160_18="r";
private static final String C_P_160_19="{getTrType(([t]))}";
private static final String C_P_160_20="{r}";
private PageDataMovesEffectsEffmultusedmovepower(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc65){
Element elt0_=el(_doc65,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_160_0));
attrs0_.add(at(C_BEAN,C_P_160_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc65,HEAD);
Element elt2_=el(_doc65,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_160_2));
attrs1_.add(at(REL,C_P_160_3));
attrs1_.add(at(TYPE,C_P_160_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc65,BODY);
build0(elt3_,_doc65);
ad(elt0_,elt3_);
_doc65.appendChild(elt0_);
}
static void build0(Element _body,Document _doc65){
Element elt0_=el(_doc65,P);
Element elt1_=el(_doc65,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_160_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc65,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_160_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc65,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_160_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc65,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_160_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc65,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_160_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc65,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_160_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc65,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_160_11));
at(elt7_,attrs6_);
ad(elt0_,elt7_);
Element elt8_=el(_doc65,TABLE);
Element elt9_=el(_doc65,THEAD);
Element elt10_=el(_doc65,TR);
Element elt11_=el(_doc65,TH);
Element elt12_=el(_doc65,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_160_12));
at(elt12_,attrs7_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc65,TH);
Element elt14_=el(_doc65,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_160_13));
at(elt14_,attrs8_);
ad(elt13_,elt14_);
ad(elt10_,elt13_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt15_=el(_doc65,TBODY);
Element elt16_=el(_doc65,C_FOR);
CustList<Attr> attrs9_=al(5);
attrs9_.add(at(KEY,C_P_160_14));
attrs9_.add(at(MAP,C_P_160_15));
attrs9_.add(at(VALUE,C_P_160_16));
attrs9_.add(at(KEYCLASSNAME,C_P_160_17));
attrs9_.add(at(VARCLASSNAME,C_P_160_18));
at(elt16_,attrs9_);
Element elt17_=el(_doc65,TR);
Element elt18_=el(_doc65,TD);
Text txt0_=tx(_doc65,C_P_160_19);
ad(elt18_,txt0_);
ad(elt17_,elt18_);
Element elt19_=el(_doc65,TD);
Text txt1_=tx(_doc65,C_P_160_20);
ad(elt19_,txt1_);
ad(elt17_,elt19_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt8_,elt15_);
ad(elt0_,elt8_);
ad(_body,elt0_);
}
}
