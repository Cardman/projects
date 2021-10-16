package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageBeloteRules extends PageCardsCommon{
private static final String C_0="rules";
private static final String C_1="javahtml";
private static final String C_2="resources_cards/css/belote.css";
private static final String C_3="stylesheet";
private static final String C_4="text/css";
private static final String C_5="msg,beat_cards";
private static final String C_6="{cartesBattues}";
private static final String C_7="msg,deal";
private static final String C_8="dealAll";
private static final String C_9="msg,yes";
private static final String C_10="!dealAll";
private static final String C_11="msg,no";
private static final String C_12="msg,decl";
private static final String C_13="b";
private static final String C_14="encheresAutorisees";
private static final String C_15="java.lang.String";
private static final String C_16="{b}";
private static final String C_17="msg,under";
private static final String C_18="sousCoupeAdv";
private static final String C_19="msg,yes";
private static final String C_20="!sousCoupeAdv";
private static final String C_21="msg,no";
private static final String C_22="msg,bids";
private static final String C_23="d";
private static final String C_24="annoncesAutorisees";
private static final String C_25="java.lang.String";
private static final String C_26="{d}";
private static final String C_27="msg,partner";
private static final String C_28="{gestionCoupePartenaire}";
private static final String C_29="msg,dealing";
private static final String C_30="{repartition}";
private static final String C_31="msg,end";
private static final String C_32="comptePointsClassique";
private static final String C_33="msg,end_def";
private static final String C_34="!comptePointsClassique";
private static final String C_35="msg,end_else";
private PageBeloteRules(){}
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
build20(elt3_,_doc);
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
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_8));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_9));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_11));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_12));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,UL);
Element elt1_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(VAR,C_13));
attrs0_.add(at(LIST,C_14));
attrs0_.add(at(CLASSNAME,C_15));
at(elt1_,attrs0_);
Element elt2_=el(_doc,LI);
Text txt0_=tx(_doc,C_16);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_17));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_18));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_19));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_20));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_21));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_22));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,UL);
Element elt1_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(VAR,C_23));
attrs0_.add(at(LIST,C_24));
attrs0_.add(at(CLASSNAME,C_25));
at(elt1_,attrs0_);
Element elt2_=el(_doc,LI);
Text txt0_=tx(_doc,C_26);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_27));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_28);
ad(_body,txt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_29));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_30);
ad(_body,txt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_31));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_32));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_33));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_34));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_35));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
}
