package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffmultsufferedmovepower extends PageCardsCommon{
private static final String C_P_159_0="javahtml";
private static final String C_P_159_1="eff_multsufferedmovepower";
private static final String C_P_159_2="web/css/moves.css";
private static final String C_P_159_3="stylesheet";
private static final String C_P_159_4="text/css";
private static final String C_P_159_5="msg_effmultsufferedmovepower,effect";
private static final String C_P_159_6="{effectBean}";
private static final String C_P_159_7="aiki.beans.moves.effects";
private static final String C_P_159_8="EffectBean";
private static final String C_P_159_9="$intern.index=index";
private static final String C_P_159_10="$intern.move=move";
private static final String C_P_159_11="msg_effmultsufferedmovepower,mult_power";
private static final String C_P_159_12="msg_effmultsufferedmovepower,mult_power_type";
private static final String C_P_159_13="msg_effmultsufferedmovepower,mult_power_rate";
private static final String C_P_159_14="t";
private static final String C_P_159_15="multMovePowerFctType";
private static final String C_P_159_16="r";
private static final String C_P_159_17="java.lang.Object";
private static final String C_P_159_18="r";
private static final String C_P_159_19="{getTrType(([t]))}";
private static final String C_P_159_20="{r}";
private PageDataMovesEffectsEffmultsufferedmovepower(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc64){
Element elt0_=el(_doc64,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_159_0));
attrs0_.add(at(C_BEAN,C_P_159_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc64,HEAD);
Element elt2_=el(_doc64,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_159_2));
attrs1_.add(at(REL,C_P_159_3));
attrs1_.add(at(TYPE,C_P_159_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc64,BODY);
build0(elt3_,_doc64);
ad(elt0_,elt3_);
_doc64.appendChild(elt0_);
}
static void build0(Element _body,Document _doc64){
Element elt0_=el(_doc64,P);
Element elt1_=el(_doc64,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_159_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc64,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_159_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc64,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_159_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc64,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_159_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc64,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_159_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc64,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_159_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc64,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_159_11));
at(elt7_,attrs6_);
ad(elt0_,elt7_);
Element elt8_=el(_doc64,TABLE);
Element elt9_=el(_doc64,THEAD);
Element elt10_=el(_doc64,TR);
Element elt11_=el(_doc64,TH);
Element elt12_=el(_doc64,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_159_12));
at(elt12_,attrs7_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc64,TH);
Element elt14_=el(_doc64,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_159_13));
at(elt14_,attrs8_);
ad(elt13_,elt14_);
ad(elt10_,elt13_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
Element elt15_=el(_doc64,TBODY);
Element elt16_=el(_doc64,C_FOR);
CustList<Attr> attrs9_=al(5);
attrs9_.add(at(KEY,C_P_159_14));
attrs9_.add(at(MAP,C_P_159_15));
attrs9_.add(at(VALUE,C_P_159_16));
attrs9_.add(at(KEYCLASSNAME,C_P_159_17));
attrs9_.add(at(VARCLASSNAME,C_P_159_18));
at(elt16_,attrs9_);
Element elt17_=el(_doc64,TR);
Element elt18_=el(_doc64,TD);
Text txt0_=tx(_doc64,C_P_159_19);
ad(elt18_,txt0_);
ad(elt17_,elt18_);
Element elt19_=el(_doc64,TD);
Text txt1_=tx(_doc64,C_P_159_20);
ad(elt19_,txt1_);
ad(elt17_,elt19_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt8_,elt15_);
ad(elt0_,elt8_);
ad(_body,elt0_);
}
}
