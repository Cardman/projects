package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffswitchmovetypes extends PageAikiCommon{
private static final String C_P_170_0="javahtml";
private static final String C_P_170_1="eff_switchmovetypes";
private static final String C_P_170_2="web/css/moves.css";
private static final String C_P_170_3="stylesheet";
private static final String C_P_170_4="text/css";
private static final String C_P_170_5="msg_effswitchmovetypes,effect";
private static final String C_P_170_6="{effectBean}";
private static final String C_P_170_7="aiki.beans.moves.effects";
private static final String C_P_170_8="EffectBean";
private static final String C_P_170_9="$intern.index=index";
private static final String C_P_170_10="$intern.move=move";
private static final String C_P_170_11="!replacingTypes.isEmpty()";
private static final String C_P_170_12="msg_effswitchmovetypes,replacing_types";
private static final String C_P_170_13="replacingTypes";
private static final String C_P_170_14="s";
private static final String C_P_170_15="{getTrReplacingTypes(([s]))}";
private static final String C_P_170_16="!changeTypes.isEmpty()";
private static final String C_P_170_17="!replacingTypes.isEmpty()";
private static final String C_P_170_18="msg_effswitchmovetypes,changing_type_possible";
private static final String C_P_170_19="replacingTypes.isEmpty()";
private static final String C_P_170_20="msg_effswitchmovetypes,changing_type";
private static final String C_P_170_21="msg_effswitchmovetypes,old_type";
private static final String C_P_170_22="msg_effswitchmovetypes,new_type";
private static final String C_P_170_23="c";
private static final String C_P_170_24="changeTypes";
private static final String C_P_170_25="r";
private static final String C_P_170_26="java.lang.Object";
private static final String C_P_170_27="java.lang.String";
private static final String C_P_170_28="{getTrChangedTypes(([c]))}";
private static final String C_P_170_29="{r}";
private PageDataMovesEffectsEffswitchmovetypes(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc75){
Element elt0_=el(_doc75,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_170_0));
attrs0_.add(at(C_BEAN,C_P_170_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc75,HEAD);
Element elt2_=el(_doc75,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_170_2));
attrs1_.add(at(REL,C_P_170_3));
attrs1_.add(at(TYPE,C_P_170_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc75,BODY);
build0(elt3_,_doc75);
ad(elt0_,elt3_);
_doc75.appendChild(elt0_);
}
static void build0(Element _body,Document _doc75){
Element elt0_=el(_doc75,P);
Element elt1_=el(_doc75,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_170_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc75,C_IMPORT);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(PAGE,C_P_170_6));
at(elt2_,attrs1_);
Element elt3_=el(_doc75,C_PACKAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_170_7));
at(elt3_,attrs2_);
Element elt4_=el(_doc75,C_CLASS);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(NAME,C_P_170_8));
at(elt4_,attrs3_);
Element elt5_=el(_doc75,C_FIELD);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(PREPARE,C_P_170_9));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc75,C_FIELD);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(PREPARE,C_P_170_10));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc75,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_P_170_11));
at(elt7_,attrs6_);
Element elt8_=el(_doc75,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_170_12));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
Element elt9_=el(_doc75,UL);
Element elt10_=el(_doc75,C_FOR);
CustList<Attr> attrs8_=al(2);
attrs8_.add(at(LIST,C_P_170_13));
attrs8_.add(at(VAR,C_P_170_14));
at(elt10_,attrs8_);
Element elt11_=el(_doc75,LI);
Text txt0_=tx(_doc75,C_P_170_15);
ad(elt11_,txt0_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt7_,elt9_);
Element elt12_=el(_doc75,BR);
ad(elt7_,elt12_);
ad(elt0_,elt7_);
Element elt13_=el(_doc75,C_IF);
CustList<Attr> attrs9_=al(1);
attrs9_.add(at(CONDITION,C_P_170_16));
at(elt13_,attrs9_);
Element elt14_=el(_doc75,C_IF);
CustList<Attr> attrs10_=al(1);
attrs10_.add(at(CONDITION,C_P_170_17));
at(elt14_,attrs10_);
Element elt15_=el(_doc75,C_MESSAGE);
CustList<Attr> attrs11_=al(1);
attrs11_.add(at(VALUE,C_P_170_18));
at(elt15_,attrs11_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
Element elt16_=el(_doc75,C_IF);
CustList<Attr> attrs12_=al(1);
attrs12_.add(at(CONDITION,C_P_170_19));
at(elt16_,attrs12_);
Element elt17_=el(_doc75,C_MESSAGE);
CustList<Attr> attrs13_=al(1);
attrs13_.add(at(VALUE,C_P_170_20));
at(elt17_,attrs13_);
ad(elt16_,elt17_);
ad(elt13_,elt16_);
Element elt18_=el(_doc75,TABLE);
Element elt19_=el(_doc75,THEAD);
Element elt20_=el(_doc75,TR);
Element elt21_=el(_doc75,TH);
Element elt22_=el(_doc75,C_MESSAGE);
CustList<Attr> attrs14_=al(1);
attrs14_.add(at(VALUE,C_P_170_21));
at(elt22_,attrs14_);
ad(elt21_,elt22_);
ad(elt20_,elt21_);
Element elt23_=el(_doc75,TH);
Element elt24_=el(_doc75,C_MESSAGE);
CustList<Attr> attrs15_=al(1);
attrs15_.add(at(VALUE,C_P_170_22));
at(elt24_,attrs15_);
ad(elt23_,elt24_);
ad(elt20_,elt23_);
ad(elt19_,elt20_);
ad(elt18_,elt19_);
Element elt25_=el(_doc75,TBODY);
Element elt26_=el(_doc75,C_FOR);
CustList<Attr> attrs16_=al(5);
attrs16_.add(at(KEY,C_P_170_23));
attrs16_.add(at(MAP,C_P_170_24));
attrs16_.add(at(VALUE,C_P_170_25));
attrs16_.add(at(KEYCLASSNAME,C_P_170_26));
attrs16_.add(at(VARCLASSNAME,C_P_170_27));
at(elt26_,attrs16_);
Element elt27_=el(_doc75,TR);
Element elt28_=el(_doc75,TD);
Text txt1_=tx(_doc75,C_P_170_28);
ad(elt28_,txt1_);
ad(elt27_,elt28_);
Element elt29_=el(_doc75,TD);
Text txt2_=tx(_doc75,C_P_170_29);
ad(elt29_,txt2_);
ad(elt27_,elt29_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt18_,elt25_);
ad(elt13_,elt18_);
Element elt30_=el(_doc75,BR);
ad(elt13_,elt30_);
ad(elt0_,elt13_);
ad(_body,elt0_);
}
}
