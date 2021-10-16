package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataSimulationSelectitem extends PageAikiCommon{
private static final String C_P_199_0="javahtml";
private static final String C_P_199_1="selectitem";
private static final String C_P_199_2="msg_levelsimu,title_select_item";
private static final String C_P_199_3="web/css/simulation.css";
private static final String C_P_199_4="stylesheet";
private static final String C_P_199_5="text/css";
private static final String C_P_199_6="$cancel";
private static final String C_P_199_7="";
private static final String C_P_199_8="msg_levelsimu,cancel";
private static final String C_P_199_9="$cancelItem";
private static final String C_P_199_10="";
private static final String C_P_199_11="msg_simulation,cancel_item";
private static final String C_P_199_12="";
private static final String C_P_199_13="$search";
private static final String C_P_199_14="post";
private static final String C_P_199_15="searching";
private static final String C_P_199_16="msg_items,content_name";
private static final String C_P_199_17="typedName";
private static final String C_P_199_18="typedName";
private static final String C_P_199_19="text";
private static final String C_P_199_20="msg_items,price_dot";
private static final String C_P_199_21="typedPrice";
private static final String C_P_199_22="typedPrice";
private static final String C_P_199_23="text";
private static final String C_P_199_24="msg_items,content";
private static final String C_P_199_25="typedClass";
private static final String C_P_199_26="typedClass";
private static final String C_P_199_27="text";
private static final String C_P_199_28="msg_simulation,search";
private static final String C_P_199_29="msg_items,items";
private static final String C_P_199_30="msg_items,image";
private static final String C_P_199_31="msg_items,name";
private static final String C_P_199_32="msg_items,price";
private static final String C_P_199_33="msg_items,description";
private static final String C_P_199_34="aiki.beans.facade.dto.ItemLine";
private static final String C_P_199_35="items";
private static final String C_P_199_36="d";
private static final String C_P_199_37="{getMiniImage(([d]))}";
private static final String C_P_199_38="$clickLink({([d])})";
private static final String C_P_199_39="";
private static final String C_P_199_40="{d.displayName}";
private static final String C_P_199_41="{d.price}";
private static final String C_P_199_42="{d.descriptionClass}";
private PageDataSimulationSelectitem(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc104){
Element elt0_=el(_doc104,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_199_0));
attrs0_.add(at(C_BEAN,C_P_199_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc104,HEAD);
Element elt2_=el(_doc104,TITLE);
Element elt3_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_199_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc104,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_199_3));
attrs2_.add(at(REL,C_P_199_4));
attrs2_.add(at(TYPE,C_P_199_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc104,BODY);
build0(elt5_,_doc104);
build1(elt5_,_doc104);
build2(elt5_,_doc104);
build3(elt5_,_doc104);
build4(elt5_,_doc104);
build5(elt5_,_doc104);
ad(elt0_,elt5_);
_doc104.appendChild(elt0_);
}
static void build0(Element _body,Document _doc104){
Element elt0_=el(_doc104,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_199_6));
attrs0_.add(at(HREF,C_P_199_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_199_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc104,BR);
ad(_body,elt2_);
}
static void build1(Element _body,Document _doc104){
Element elt0_=el(_doc104,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_199_9));
attrs0_.add(at(HREF,C_P_199_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_199_11));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc104){
Element elt0_=el(_doc104,BR);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc104){
Element elt0_=el(_doc104,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_199_12));
attrs0_.add(at(C_COMMAND,C_P_199_13));
attrs0_.add(at(METHOD,C_P_199_14));
attrs0_.add(at(NAME,C_P_199_15));
at(elt0_,attrs0_);
Element elt1_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_199_16));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc104,INPUT);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(C_VARVALUE,C_P_199_17));
attrs2_.add(at(NAME,C_P_199_18));
attrs2_.add(at(TYPE,C_P_199_19));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc104,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_199_20));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
Element elt5_=el(_doc104,INPUT);
CustList<Attr> attrs4_=al(3);
attrs4_.add(at(C_VARVALUE,C_P_199_21));
attrs4_.add(at(NAME,C_P_199_22));
attrs4_.add(at(TYPE,C_P_199_23));
at(elt5_,attrs4_);
ad(elt0_,elt5_);
Element elt6_=el(_doc104,BR);
ad(elt0_,elt6_);
Element elt7_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_199_24));
at(elt7_,attrs5_);
ad(elt0_,elt7_);
Element elt8_=el(_doc104,INPUT);
CustList<Attr> attrs6_=al(3);
attrs6_.add(at(C_VARVALUE,C_P_199_25));
attrs6_.add(at(NAME,C_P_199_26));
attrs6_.add(at(TYPE,C_P_199_27));
at(elt8_,attrs6_);
ad(elt0_,elt8_);
Element elt9_=el(_doc104,BR);
ad(elt0_,elt9_);
Element elt10_=el(_doc104,C_SUBMIT);
CustList<Attr> attrs7_=al(1);
attrs7_.add(at(MESSAGE,C_P_199_28));
at(elt10_,attrs7_);
ad(elt0_,elt10_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc104){
Element elt0_=el(_doc104,BR);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc104){
Element elt0_=el(_doc104,TABLE);
Element elt1_=el(_doc104,CAPTION);
Element elt2_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_P_199_29));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc104,THEAD);
Element elt4_=el(_doc104,TR);
Element elt5_=el(_doc104,TH);
Element elt6_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_199_30));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc104,TH);
Element elt8_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_199_31));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
Element elt9_=el(_doc104,TH);
Element elt10_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_199_32));
at(elt10_,attrs3_);
ad(elt9_,elt10_);
ad(elt4_,elt9_);
Element elt11_=el(_doc104,TH);
Element elt12_=el(_doc104,C_MESSAGE);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_199_33));
at(elt12_,attrs4_);
ad(elt11_,elt12_);
ad(elt4_,elt11_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt13_=el(_doc104,TBODY);
Element elt14_=el(_doc104,C_FOR);
CustList<Attr> attrs5_=al(3);
attrs5_.add(at(CLASSNAME,C_P_199_34));
attrs5_.add(at(LIST,C_P_199_35));
attrs5_.add(at(VAR,C_P_199_36));
at(elt14_,attrs5_);
Element elt15_=el(_doc104,TR);
Element elt16_=el(_doc104,TD);
Element elt17_=el(_doc104,C_IMG);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(SRC,C_P_199_37));
at(elt17_,attrs6_);
ad(elt16_,elt17_);
ad(elt15_,elt16_);
Element elt18_=el(_doc104,TD);
Element elt19_=el(_doc104,A);
CustList<Attr> attrs7_=al(2);
attrs7_.add(at(C_COMMAND,C_P_199_38));
attrs7_.add(at(HREF,C_P_199_39));
at(elt19_,attrs7_);
Text txt0_=tx(_doc104,C_P_199_40);
ad(elt19_,txt0_);
ad(elt18_,elt19_);
ad(elt15_,elt18_);
Element elt20_=el(_doc104,TD);
Text txt1_=tx(_doc104,C_P_199_41);
ad(elt20_,txt1_);
ad(elt15_,elt20_);
Element elt21_=el(_doc104,TD);
Text txt2_=tx(_doc104,C_P_199_42);
ad(elt21_,txt2_);
ad(elt15_,elt21_);
ad(elt14_,elt15_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
ad(_body,elt0_);
}
}
