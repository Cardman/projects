package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffprotectfromtypes extends PageCardsCommon{
private static final String C_P_162_0="javahtml";
private static final String C_P_162_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_PROTECTFROMTYPES;
private static final String C_P_162_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_162_3="stylesheet";
private static final String C_P_162_4="text/css";
private static final String C_P_162_5="msg_effprotectfromtypes,effect";
private static final String C_P_162_6="effectBean";
private static final String C_P_162_7="aiki.beans.moves.effects";
private static final String C_P_162_8="EffectBean";
private static final String C_P_162_9="$intern.index=index";
private static final String C_P_162_10="$intern.move=move";
private static final String C_P_162_11="msg_effprotectfromtypes,immu_move_types";
private static final String C_P_162_12="immuAgainstTypes";
private static final String C_P_162_13="t";
private static final String C_P_162_14="{getTrType(([t]))}";
private PageDataMovesEffectsEffprotectfromtypes(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc67){
Element elt0_=el(_doc67,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_162_0));
attrs0_.add(at(C_BEAN,C_P_162_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc67,HEAD);
Element elt2_=el(_doc67,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_162_2));
attrs1_.add(at(REL,C_P_162_3));
attrs1_.add(at(TYPE,C_P_162_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc67,BODY);
build0(elt3_,_doc67);
ad(elt0_,elt3_);
_doc67.appendChild(elt0_);
}
static void build0(Element _body,Document _doc67){
Element elt0_=el(_doc67,P);
Element elt1_=el(_doc67,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_162_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc67,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_162_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc67,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_162_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc67,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_162_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc67,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_162_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc67,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_162_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc67,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_162_11));
at(elt7_,attrs6_);
ad(elt0_,elt7_);
Element elt8_=el(_doc67,UL);
Element elt9_=el(_doc67,C_FOR);
CustList<Attr> attrs7_=al(2);
attrs7_.add(at(LIST,C_P_162_12));
attrs7_.add(at(VAR,C_P_162_13));
at(elt9_,attrs7_);
Element elt10_=el(_doc67,LI);
Text txt0_=tx(_doc67,C_P_162_14);
ad(elt10_,txt0_);
ad(elt9_,elt10_);
ad(elt8_,elt9_);
ad(elt0_,elt8_);
ad(_body,elt0_);
}
}
