package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageTarotRules extends PageCardsCommon{
private static final String C_0="rules";
private static final String C_1="javahtml";
private static final String C_2="resources_cards/css/tarot.css";
private static final String C_3="stylesheet";
private static final String C_4="text/css";
private static final String C_5="msg,beat_cards";
private static final String C_6="{cartesBattues}";
private static final String C_7="msg,dealing_pl";
private static final String C_8="{repartition}";
private static final String C_9="msg,mode";
private static final String C_10="{mode}";
private static final String C_11="msg,discard";
private static final String C_12="discardAfterCall";
private static final String C_13="msg,yes";
private static final String C_14="!discardAfterCall";
private static final String C_15="msg,no";
private static final String C_16="msg,bids";
private static final String C_17="d";
private static final String C_18="contrats";
private static final String C_19="java.lang.String";
private static final String C_20="{d}";
private static final String C_21="msg,decls";
private static final String C_22="msg,hands";
private static final String C_23="msg,hand";
private static final String C_24="msg,nb";
private static final String C_25="h";
private static final String C_26="n";
private static final String C_27="poigneesAutorisees";
private static final String C_28="java.lang.String";
private static final String C_29="java.lang.Integer";
private static final String C_30="{h}";
private static final String C_31="{n}";
private static final String C_32="msg,mis";
private static final String C_33="!miseres.isEmpty()";
private static final String C_34="m";
private static final String C_35="miseres";
private static final String C_36="java.lang.String";
private static final String C_37="{m}";
private static final String C_38="miseres.isEmpty()";
private static final String C_39="msg,nothing";
private static final String C_40="msg,ending";
private static final String C_41="{finPartieTarot}";
private PageTarotRules(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_BEAN,C_0));
attrs0_.add(at(XMLNS_C,C_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_2));
attrs1_.add(at(REL,C_3));
attrs1_.add(at(TYPE,C_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,BODY);
build0(elt3_,_doc);
build1(elt3_,_doc);
build2(elt3_,_doc);
build3(elt3_,_doc);
build4(elt3_,_doc);
build5(elt3_,_doc);
build6(elt3_,_doc);
build7(elt3_,_doc);
build8(elt3_,_doc);
build9(elt3_,_doc);
build10(elt3_,_doc);
build11(elt3_,_doc);
build12(elt3_,_doc);
build13(elt3_,_doc);
build14(elt3_,_doc);
build15(elt3_,_doc);
build16(elt3_,_doc);
build17(elt3_,_doc);
build18(elt3_,_doc);
build19(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_6);
ad(_body,txt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_7));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_8);
ad(_body,txt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_9));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_10);
ad(_body,txt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_11));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_14));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_15));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_16));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,UL);
Element elt1_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(VAR,C_17));
attrs0_.add(at(LIST,C_18));
attrs0_.add(at(CLASSNAME,C_19));
at(elt1_,attrs0_);
Element elt2_=el(_doc,LI);
Text txt0_=tx(_doc,C_20);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_21));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,TABLE);
Element elt1_=el(_doc,CAPTION);
Element elt2_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_22));
at(elt2_,attrs0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,THEAD);
Element elt4_=el(_doc,TR);
Element elt5_=el(_doc,TD);
Element elt6_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_23));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc,TD);
Element elt8_=el(_doc,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_24));
at(elt8_,attrs2_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
Element elt9_=el(_doc,TBODY);
Element elt10_=el(_doc,C_FOR);
CustList<Attr> attrs3_=al(5);
attrs3_.add(at(KEY,C_25));
attrs3_.add(at(VALUE,C_26));
attrs3_.add(at(MAP,C_27));
attrs3_.add(at(KEYCLASSNAME,C_28));
attrs3_.add(at(VARCLASSNAME,C_29));
at(elt10_,attrs3_);
Element elt11_=el(_doc,TR);
Element elt12_=el(_doc,TD);
Text txt0_=tx(_doc,C_30);
ad(elt12_,txt0_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,TD);
Text txt1_=tx(_doc,C_31);
ad(elt13_,txt1_);
ad(elt11_,elt13_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_32));
at(elt0_,attrs0_);
ad(_body,elt0_);
Element elt1_=el(_doc,BR);
ad(_body,elt1_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_33));
at(elt0_,attrs0_);
Element elt1_=el(_doc,UL);
Element elt2_=el(_doc,C_FOR);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(VAR,C_34));
attrs1_.add(at(LIST,C_35));
attrs1_.add(at(CLASSNAME,C_36));
at(elt2_,attrs1_);
Element elt3_=el(_doc,LI);
Text txt0_=tx(_doc,C_37);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_38));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_39));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_40));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_41);
ad(_body,txt0_);
}
}
