package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataEndroundMultirelation extends PageCardsCommon{
private static final String C_P_106_0="javahtml";
private static final String C_P_106_1="end_multirelation";
private static final String C_P_106_2=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_106_3="stylesheet";
private static final String C_P_106_4="text/css";
private static final String C_P_106_5="endRoundHtml";
private static final String C_P_106_6="aiki.beans.endround";
private static final String C_P_106_7="EffectEndRoundBean";
private static final String C_P_106_8="$intern.index=index";
private static final String C_P_106_9="!damageByStatus.isEmpty()";
private static final String C_P_106_10="msg_multirelation,effect";
private static final String C_P_106_11="msg_multirelation,damage_status_key";
private static final String C_P_106_12="msg_multirelation,damage_status_rate";
private static final String C_P_106_13="s";
private static final String C_P_106_14="damageByStatus";
private static final String C_P_106_15="r";
private static final String C_P_106_16="java.lang.Object";
private static final String C_P_106_17="r";
private static final String C_P_106_18="$clickDamageStatus({index},{([s])})";
private static final String C_P_106_19="";
private static final String C_P_106_20="{getTrDamageStatus(([s]))}";
private static final String C_P_106_21="{r}";
private PageDataEndroundMultirelation(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc11){
Element elt0_=el(_doc11,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_106_0));
attrs0_.add(at(C_BEAN,C_P_106_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc11,HEAD);
Element elt2_=el(_doc11,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_106_2));
attrs1_.add(at(REL,C_P_106_3));
attrs1_.add(at(TYPE,C_P_106_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc11,BODY);
build0(elt3_,_doc11);
build1(elt3_,_doc11);
ad(elt0_,elt3_);
_doc11.appendChild(elt0_);
}
static void build0(Element _body,Document _doc11){
Element elt0_=el(_doc11,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_106_5));
at(elt0_,attrs0_);
Element elt1_=el(_doc11,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_106_6));
at(elt1_,attrs1_);
Element elt2_=el(_doc11,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_106_7));
at(elt2_,attrs2_);
Element elt3_=el(_doc11,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_106_8));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc11){
Element elt0_=el(_doc11,P);
Element elt1_=el(_doc11,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_106_9));
at(elt1_,attrs0_);
Element elt2_=el(_doc11,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_106_10));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc11,TABLE);
Element elt4_=el(_doc11,THEAD);
Element elt5_=el(_doc11,TR);
Element elt6_=el(_doc11,TH);
Element elt7_=el(_doc11,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_106_11));
at(elt7_,attrs2_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc11,TH);
Element elt9_=el(_doc11,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_106_12));
at(elt9_,attrs3_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt10_=el(_doc11,TBODY);
Element elt11_=el(_doc11,C_FOR);
CustList<Attr> attrs4_=al(5);
attrs4_.add(at(KEY,C_P_106_13));
attrs4_.add(at(MAP,C_P_106_14));
attrs4_.add(at(VALUE,C_P_106_15));
attrs4_.add(at(KEYCLASSNAME,C_P_106_16));
attrs4_.add(at(VARCLASSNAME,C_P_106_17));
at(elt11_,attrs4_);
Element elt12_=el(_doc11,TR);
Element elt13_=el(_doc11,TD);
Element elt14_=el(_doc11,A);
CustList<Attr> attrs5_=al(2);
attrs5_.add(at(C_COMMAND,C_P_106_18));
attrs5_.add(at(HREF,C_P_106_19));
at(elt14_,attrs5_);
Text txt0_=tx(_doc11,C_P_106_20);
ad(elt14_,txt0_);
ad(elt13_,elt14_);
ad(elt12_,elt13_);
Element elt15_=el(_doc11,TD);
Text txt1_=tx(_doc11,C_P_106_21);
ad(elt15_,txt1_);
ad(elt12_,elt15_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt3_,elt10_);
ad(elt1_,elt3_);
Element elt16_=el(_doc11,BR);
ad(elt1_,elt16_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
