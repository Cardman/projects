package code.scripts.pages.aiki;
import aiki.beans.moves.effects.AikiBeansMovesEffectsStd;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataMovesEffectsEffswitchposition extends PageCardsCommon{
private static final String C_P_172_0="javahtml";
private static final String C_P_172_1=AikiBeansMovesEffectsStd.BEAN_EFFECT_SWITCHPOSITION;
private static final String C_P_172_2=PkScriptPages.REN_ADD_WEB_CSS_MOVES_CSS;
private static final String C_P_172_3="stylesheet";
private static final String C_P_172_4="text/css";
private static final String C_P_172_5="msg_effswitchposition,effect";
private static final String C_P_172_6="effectBean";
private static final String C_P_172_7="aiki.beans.moves.effects";
private static final String C_P_172_8="EffectBean";
private static final String C_P_172_9="$intern.index=index";
private static final String C_P_172_10="$intern.move=move";
private PageDataMovesEffectsEffswitchposition(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc77){
Element elt0_=el(_doc77,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_172_0));
attrs0_.add(at(C_BEAN,C_P_172_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc77,HEAD);
Element elt2_=el(_doc77,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_172_2));
attrs1_.add(at(REL,C_P_172_3));
attrs1_.add(at(TYPE,C_P_172_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc77,BODY);
build0(elt3_,_doc77);
ad(elt0_,elt3_);
_doc77.appendChild(elt0_);
}
static void build0(Element _body,Document _doc77){
Element elt0_=el(_doc77,P);
Element elt1_=el(_doc77,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_172_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc77,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_172_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc77,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_172_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc77,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_172_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc77,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_172_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc77,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_172_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
}
