package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffdamagerate extends PageCardsCommon{
private static final String C_P_154_0="javahtml";
private static final String C_P_154_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_DAMAGE_RATE;
private static final String C_P_154_2="web/css/moves.css";
private static final String C_P_154_3="stylesheet";
private static final String C_P_154_4="text/css";
private static final String C_P_154_5="msg_effdamagerate,effect";
private static final String C_P_154_6="effectBean";
private static final String C_P_154_7="aiki.beans.moves.effects";
private static final String C_P_154_8="EffectBean";
private static final String C_P_154_9="$intern.index=index";
private static final String C_P_154_10="$intern.move=move";
private static final String C_P_154_11="winHp";
private static final String C_P_154_12="msg_effdamagerate,pos_rate";
private static final String C_P_154_13="rateDamage";
private static final String C_P_154_14="!winHp";
private static final String C_P_154_15="msg_effdamagerate,neg_rate";
private static final String C_P_154_16="rateDamage";
private PageDataMovesEffectsEffdamagerate(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc59){
Element elt0_=el(_doc59,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_154_0));
attrs0_.add(at(C_BEAN,C_P_154_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc59,HEAD);
Element elt2_=el(_doc59,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_154_2));
attrs1_.add(at(REL,C_P_154_3));
attrs1_.add(at(TYPE,C_P_154_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc59,BODY);
build0(elt3_,_doc59);
ad(elt0_,elt3_);
_doc59.appendChild(elt0_);
}
static void build0(Element _body,Document _doc59){
Element elt0_=el(_doc59,P);
Element elt1_=el(_doc59,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_154_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc59,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_154_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc59,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_154_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc59,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_154_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc59,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_154_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc59,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_154_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc59,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_154_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc59,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_154_12));
at(elt8_,attrs7_);
Element elt9_=el(_doc59,PARAM);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_P_154_13));
at(elt9_,attrs8_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt10_=el(_doc59,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_154_14));
at(elt10_,attrs9_);
Element elt11_=el(_doc59,C_MESSAGE);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(VALUE,C_P_154_15));
at(elt11_,attrs10_);
Element elt12_=el(_doc59,PARAM);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_154_16));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt0_,elt10_);
ad(_body,elt0_);
}
}
