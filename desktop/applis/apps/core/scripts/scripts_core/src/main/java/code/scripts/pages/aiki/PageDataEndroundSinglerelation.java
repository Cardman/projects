package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataEndroundSinglerelation extends PageAikiCommon{
private static final String C_P_109_0="javahtml";
private static final String C_P_109_1="end_singlerelation";
private static final String C_P_109_2="web/css/abilities.css";
private static final String C_P_109_3="stylesheet";
private static final String C_P_109_4="text/css";
private static final String C_P_109_5="{endRoundHtml}";
private static final String C_P_109_6="aiki.beans.endround";
private static final String C_P_109_7="EffectEndRoundBean";
private static final String C_P_109_8="$intern.index=index";
private static final String C_P_109_9="msg_singlerelation,effect";
private static final String C_P_109_10="msg_singlerelation,suffered";
private static final String C_P_109_11="msg_singlerelation,suffered_rd";
private static final String C_P_109_12="msg_singlerelation,suffered_rate";
private static final String C_P_109_13="s";
private static final String C_P_109_14="rateDamageFunctionOfNbRounds";
private static final String C_P_109_15="r";
private static final String C_P_109_16="java.lang.Long";
private static final String C_P_109_17="r";
private static final String C_P_109_18="{s}";
private static final String C_P_109_19="{r}";
private static final String C_P_109_20="msg_singlerelation,law";
private static final String C_P_109_21="msg_singlerelation,law_rd";
private static final String C_P_109_22="msg_singlerelation,law_value";
private static final String C_P_109_23="s";
private static final String C_P_109_24="lawForEnablingEffect";
private static final String C_P_109_25="r";
private static final String C_P_109_26="li";
private static final String C_P_109_27="r";
private static final String C_P_109_28="{s}";
private static final String C_P_109_29="{r}";
private PageDataEndroundSinglerelation(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc14){
Element elt0_=el(_doc14,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_109_0));
attrs0_.add(at(C_BEAN,C_P_109_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc14,HEAD);
Element elt2_=el(_doc14,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_109_2));
attrs1_.add(at(REL,C_P_109_3));
attrs1_.add(at(TYPE,C_P_109_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc14,BODY);
build0(elt3_,_doc14);
build1(elt3_,_doc14);
ad(elt0_,elt3_);
_doc14.appendChild(elt0_);
}
static void build0(Element _body,Document _doc14){
Element elt0_=el(_doc14,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_109_5));
at(elt0_,attrs0_);
Element elt1_=el(_doc14,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_109_6));
at(elt1_,attrs1_);
Element elt2_=el(_doc14,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_109_7));
at(elt2_,attrs2_);
Element elt3_=el(_doc14,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_109_8));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc14){
Element elt0_=el(_doc14,P);
Element elt1_=el(_doc14,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_109_9));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc14,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_109_10));
at(elt2_,attrs1_);
ad(elt0_,elt2_);
Element elt3_=el(_doc14,TABLE);
Element elt4_=el(_doc14,THEAD);
Element elt5_=el(_doc14,TR);
Element elt6_=el(_doc14,TH);
Element elt7_=el(_doc14,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_109_11));
at(elt7_,attrs2_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
Element elt8_=el(_doc14,TH);
Element elt9_=el(_doc14,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_109_12));
at(elt9_,attrs3_);
ad(elt8_,elt9_);
ad(elt5_,elt8_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt10_=el(_doc14,TBODY);
Element elt11_=el(_doc14,C_FOR);
CustList<Attr> attrs4_=al(5);
attrs4_.add(at(KEY,C_P_109_13));
attrs4_.add(at(MAP,C_P_109_14));
attrs4_.add(at(VALUE,C_P_109_15));
attrs4_.add(at(KEYCLASSNAME,C_P_109_16));
attrs4_.add(at(VARCLASSNAME,C_P_109_17));
at(elt11_,attrs4_);
Element elt12_=el(_doc14,TR);
Element elt13_=el(_doc14,TD);
Text txt0_=tx(_doc14,C_P_109_18);
ad(elt13_,txt0_);
ad(elt12_,elt13_);
Element elt14_=el(_doc14,TD);
Text txt1_=tx(_doc14,C_P_109_19);
ad(elt14_,txt1_);
ad(elt12_,elt14_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
ad(elt3_,elt10_);
ad(elt0_,elt3_);
Element elt15_=el(_doc14,BR);
ad(elt0_,elt15_);
Element elt16_=el(_doc14,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_109_20));
at(elt16_,attrs5_);
ad(elt0_,elt16_);
Element elt17_=el(_doc14,TABLE);
Element elt18_=el(_doc14,THEAD);
Element elt19_=el(_doc14,TR);
Element elt20_=el(_doc14,TH);
Element elt21_=el(_doc14,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_109_21));
at(elt21_,attrs6_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
Element elt22_=el(_doc14,TH);
Element elt23_=el(_doc14,C_MESSAGE);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(VALUE,C_P_109_22));
at(elt23_,attrs7_);
ad(elt22_,elt23_);
ad(elt19_,elt22_);
ad(elt18_,elt19_);
ad(elt17_,elt18_);
Element elt24_=el(_doc14,TBODY);
Element elt25_=el(_doc14,C_FOR);
CustList<Attr> attrs8_=al(5);
attrs8_.add(at(KEY,C_P_109_23));
attrs8_.add(at(MAP,C_P_109_24));
attrs8_.add(at(VALUE,C_P_109_25));
attrs8_.add(at(KEYCLASSNAME,C_P_109_26));
attrs8_.add(at(VARCLASSNAME,C_P_109_27));
at(elt25_,attrs8_);
Element elt26_=el(_doc14,TR);
Element elt27_=el(_doc14,TD);
Text txt2_=tx(_doc14,C_P_109_28);
ad(elt27_,txt2_);
ad(elt26_,elt27_);
Element elt28_=el(_doc14,TD);
Text txt3_=tx(_doc14,C_P_109_29);
ad(elt28_,txt3_);
ad(elt26_,elt28_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt17_,elt24_);
ad(elt0_,elt17_);
Element elt29_=el(_doc14,BR);
ad(elt0_,elt29_);
ad(_body,elt0_);
}
}
