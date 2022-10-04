package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
final class PageBeloteRules extends PageCardsCommon{
private static final String C_1_0_0="rules";
private static final String C_1_0_1="javahtml";
private static final String C_1_0_2="resources_cards/css/belote.css";
private static final String C_1_0_3="stylesheet";
private static final String C_1_0_4="text/css";
private static final String C_1_0_5="msg,beat_cards";
private static final String C_1_0_6="{cartesBattues}";
private static final String C_1_0_7="msg,deal";
private static final String C_1_0_8="dealAll";
private static final String C_1_0_9="msg,yes";
private static final String C_1_0_10="!dealAll";
private static final String C_1_0_11="msg,no";
private static final String C_1_0_12="msg,decl";
private static final String C_1_0_13="b";
private static final String C_1_0_14="encheresAutorisees";
private static final String C_1_0_15="java.lang.String";
private static final String C_1_0_16="{b}";
private static final String C_1_0_17="msg,under";
private static final String C_1_0_18="sousCoupeAdv";
private static final String C_1_0_19="msg,yes";
private static final String C_1_0_20="!sousCoupeAdv";
private static final String C_1_0_21="msg,no";
private static final String C_1_0_22="msg,bids";
private static final String C_1_0_23="d";
private static final String C_1_0_24="annoncesAutorisees";
private static final String C_1_0_25="java.lang.String";
private static final String C_1_0_26="{d}";
private static final String C_1_0_27="msg,partner";
private static final String C_1_0_28="{gestionCoupePartenaire}";
private static final String C_1_0_29="msg,dealing";
private static final String C_1_0_30="{repartition}";
private static final String C_1_0_31="msg,end";
private static final String C_1_0_32="comptePointsClassique";
private static final String C_1_0_33="msg,end_def";
private static final String C_1_0_34="!comptePointsClassique";
private static final String C_1_0_35="msg,end_else";
private PageBeloteRules(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc0){
Element elt0_=el(_doc0,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_BEAN,C_1_0_0));
attrs0_.add(at(XMLNS_C,C_1_0_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc0,HEAD);
Element elt2_=el(_doc0,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_1_0_2));
attrs1_.add(at(REL,C_1_0_3));
attrs1_.add(at(TYPE,C_1_0_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc0,BODY);
build0(elt3_,_doc0);
build1(elt3_,_doc0);
build2(elt3_,_doc0);
build3(elt3_,_doc0);
build4(elt3_,_doc0);
build5(elt3_,_doc0);
build6(elt3_,_doc0);
build7(elt3_,_doc0);
build8(elt3_,_doc0);
build9(elt3_,_doc0);
build10(elt3_,_doc0);
build11(elt3_,_doc0);
build12(elt3_,_doc0);
build13(elt3_,_doc0);
build14(elt3_,_doc0);
build15(elt3_,_doc0);
build16(elt3_,_doc0);
build17(elt3_,_doc0);
build18(elt3_,_doc0);
build19(elt3_,_doc0);
build20(elt3_,_doc0);
ad(elt0_,elt3_);
_doc0.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_1_0_6);
ad(_body,txt0_);
}
static void build1(Element _body,Document _doc){
br(_doc,_body);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_7));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_1_0_8));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_0_9));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_1_0_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_0_11));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_12));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,UL);
Element elt1_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(VAR,C_1_0_13));
attrs0_.add(at(LIST,C_1_0_14));
attrs0_.add(at(CLASSNAME,C_1_0_15));
at(elt1_,attrs0_);
Element elt2_=el(_doc,LI);
Text txt0_=tx(_doc,C_1_0_16);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
br(_doc,_body);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_17));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_1_0_18));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_0_19));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_1_0_20));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_0_21));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
br(_doc,_body);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_22));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,UL);
Element elt1_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(VAR,C_1_0_23));
attrs0_.add(at(LIST,C_1_0_24));
attrs0_.add(at(CLASSNAME,C_1_0_25));
at(elt1_,attrs0_);
Element elt2_=el(_doc,LI);
Text txt0_=tx(_doc,C_1_0_26);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_27));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_1_0_28);
ad(_body,txt0_);
}
static void build15(Element _body,Document _doc){
br(_doc,_body);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_29));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_1_0_30);
ad(_body,txt0_);
}
static void build17(Element _body,Document _doc){
br(_doc,_body);
}
private static void br(Document _doc,Element _body) {
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_0_31));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_1_0_32));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_0_33));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_1_0_34));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_0_35));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
}
