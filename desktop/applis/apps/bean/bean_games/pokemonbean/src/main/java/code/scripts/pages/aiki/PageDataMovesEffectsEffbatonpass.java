package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffbatonpass extends PageCardsCommon{
private static final String C_P_147_0="javahtml";
private static final String C_P_147_1="eff_batonpass";
private static final String C_P_147_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_147_3="stylesheet";
private static final String C_P_147_4="text/css";
private static final String C_P_147_5="msg_effbatonpass,effect";
private static final String C_P_147_6="effectBean";
private static final String C_P_147_7="aiki.beans.moves.effects";
private static final String C_P_147_8="EffectBean";
private static final String C_P_147_9="$intern.index=index";
private static final String C_P_147_10="$intern.move=move";
private static final String C_P_147_11="!moves.isEmpty()";
private static final String C_P_147_12="msg_effbatonpass,effect_2";
private static final String C_P_147_13="moves";
private static final String C_P_147_14="m";
private static final String C_P_147_15="$clickMove({([m])})";
private static final String C_P_147_16="";
private static final String C_P_147_17="{getTrMove(([m]))}";
private PageDataMovesEffectsEffbatonpass(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc52){
Element elt0_=el(_doc52,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_147_0));
attrs0_.add(at(C_BEAN,C_P_147_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc52,HEAD);
Element elt2_=el(_doc52,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_147_2));
attrs1_.add(at(REL,C_P_147_3));
attrs1_.add(at(TYPE,C_P_147_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc52,BODY);
build0(elt3_,_doc52);
ad(elt0_,elt3_);
_doc52.appendChild(elt0_);
}
static void build0(Element _body,Document _doc52){
Element elt0_=el(_doc52,P);
Element elt1_=el(_doc52,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_147_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc52,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_147_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc52,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_147_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc52,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_147_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc52,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_147_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc52,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_147_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc52,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_147_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc52,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_147_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
Element elt9_=el(_doc52,UL);
Element elt10_=el(_doc52,C_FOR);
CustList<Attr> attrs8_=al(2);
attrs8_.add(at(LIST,C_P_147_13));
attrs8_.add(at(VAR,C_P_147_14));
at(elt10_,attrs8_);
Element elt11_=el(_doc52,LI);
Element elt12_=el(_doc52,A);
CustList<Attr> attrs9_=al(2);
attrs9_.add(at(C_COMMAND,C_P_147_15));
attrs9_.add(at(HREF,C_P_147_16));
at(elt12_,attrs9_);
Text txt0_=tx(_doc52,C_P_147_17);
ad(elt12_,txt0_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt7_,elt9_);
ad(elt0_,elt7_);
ad(_body,elt0_);
}
}
