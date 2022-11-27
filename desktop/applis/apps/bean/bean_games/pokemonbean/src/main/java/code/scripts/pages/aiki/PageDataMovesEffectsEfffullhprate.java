package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEfffullhprate extends PageCardsCommon{
private static final String C_P_156_0="javahtml";
private static final String C_P_156_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_FULLHPRATE;
private static final String C_P_156_2="web/css/moves.css";
private static final String C_P_156_3="stylesheet";
private static final String C_P_156_4="text/css";
private static final String C_P_156_5="msg_efffullhprate,effect";
private static final String C_P_156_6="effectBean";
private static final String C_P_156_7="aiki.beans.moves.effects";
private static final String C_P_156_8="EffectBean";
private static final String C_P_156_9="$intern.index=index";
private static final String C_P_156_10="$intern.move=move";
private static final String C_P_156_11="!leftUserHp.isZero()";
private static final String C_P_156_12="msg_efffullhprate,left_user_hp";
private static final String C_P_156_13="leftUserHp";
private static final String C_P_156_14="!isEmpty(restoredHp)";
private static final String C_P_156_15="msg_efffullhprate,restored";
private static final String C_P_156_16="{restoredHp}";
private static final String C_P_156_17="!mapVarsRestored.isEmpty()";
private static final String C_P_156_18="k";
private static final String C_P_156_19="mapVarsRestored";
private static final String C_P_156_20="v";
private static final String C_P_156_21="java.lang.String";
private static final String C_P_156_22="java.lang.String";
private static final String C_P_156_23="{k} :";
private static final String C_P_156_24="";
private static final String C_P_156_25="msg_efffullhprate,formula";
private static final String C_P_156_26="v";
private static final String C_P_156_27="!closestFoeDamageRateHp.isZero()";
private static final String C_P_156_28="msg_efffullhprate,closest_foe_damage_rate_hp";
private static final String C_P_156_29="closestFoeDamageRateHp";
private PageDataMovesEffectsEfffullhprate(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc61){
Element elt0_=el(_doc61,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_156_0));
attrs0_.add(at(C_BEAN,C_P_156_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc61,HEAD);
Element elt2_=el(_doc61,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_156_2));
attrs1_.add(at(REL,C_P_156_3));
attrs1_.add(at(TYPE,C_P_156_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc61,BODY);
build0(elt3_,_doc61);
ad(elt0_,elt3_);
_doc61.appendChild(elt0_);
}
static void build0(Element _body,Document _doc61){
Element elt0_=el(_doc61,P);
Element elt1_=el(_doc61,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_156_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc61,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_156_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc61,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_156_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc61,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_156_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc61,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_156_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc61,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_156_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc61,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_156_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc61,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_156_12));
at(elt8_,attrs7_);
Element elt9_=el(_doc61,PARAM);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_156_13));
at(elt9_,attrs8_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt10_=el(_doc61,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_156_14));
at(elt10_,attrs9_);
Element elt11_=el(_doc61,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_156_15));
at(elt11_,attrs10_);
ad(elt10_,elt11_);
Text txt0_=tx(_doc61,C_P_156_16);
ad(elt10_,txt0_);
Element elt12_=el(_doc61,BR);
ad(elt10_,elt12_);
Element elt13_=el(_doc61,C_IF);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(CONDITION,C_P_156_17));
at(elt13_,attrs11_);
Element elt14_=el(_doc61,UL);
Element elt15_=el(_doc61,C_FOR);
CustList<Attr> attrs12_=al(5);
attrs12_.add(at(KEY,C_P_156_18));
attrs12_.add(at(MAP,C_P_156_19));
attrs12_.add(at(VALUE,C_P_156_20));
attrs12_.add(at(KEYCLASSNAME,C_P_156_21));
attrs12_.add(at(VARCLASSNAME,C_P_156_22));
at(elt15_,attrs12_);
Element elt16_=el(_doc61,LI);
Text txt1_=tx(_doc61,C_P_156_23);
ad(elt16_,txt1_);
Element elt17_=el(_doc61,C_MESSAGE);
CustList<Attr> attrs13_=al(2);
attrs13_.add(at(QUOTED,C_P_156_24));
attrs13_.add(at(VALUE,C_P_156_25));
at(elt17_,attrs13_);
Element elt18_=el(_doc61,PARAM);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_156_26));
at(elt18_,attrs14_);
ad(elt17_,elt18_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
Element elt19_=el(_doc61,BR);
ad(elt13_,elt19_);
ad(elt10_,elt13_);
ad(elt0_,elt10_);
Element elt20_=el(_doc61,C_IF);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(CONDITION,C_P_156_27));
at(elt20_,attrs15_);
Element elt21_=el(_doc61,C_MESSAGE);
CustList<Attr> attrs16_=al(1);
attrs16_.add(at(VALUE,C_P_156_28));
at(elt21_,attrs16_);
Element elt22_=el(_doc61,PARAM);
CustList<Attr> attrs17_=al(1);
attrs17_.add(at(VALUE,C_P_156_29));
at(elt22_,attrs17_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
ad(elt0_,elt20_);
ad(_body,elt0_);
}
}
