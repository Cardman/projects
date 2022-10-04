package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
final class PagePresidentRules extends PageCardsCommon{
private static final String C_2_0_0="rules";
private static final String C_2_0_1="javahtml";
private static final String C_2_0_2="resources_cards/css/president.css";
private static final String C_2_0_3="stylesheet";
private static final String C_2_0_4="text/css";
private static final String C_2_0_5="msg,beat_cards";
private static final String C_2_0_6="{cartesBattues}";
private static final String C_2_0_7="msg,nb_players";
private static final String C_2_0_8="{nbPlayers}";
private static final String C_2_0_9="msg,nb_stacks";
private static final String C_2_0_10="{nbStacks}";
private static final String C_2_0_11="sameAmount()";
private static final String C_2_0_12="msg,nb_cards";
private static final String C_2_0_13="nbCardsPerPlayerMin";
private static final String C_2_0_14="!sameAmount()";
private static final String C_2_0_15="msg,nb_cards_bet";
private static final String C_2_0_16="nbCardsPerPlayerMin";
private static final String C_2_0_17="nbCardsPerPlayerMax";
private static final String C_2_0_18="msg,equalty";
private static final String C_2_0_19="{equalty}";
private static final String C_2_0_20="msg,invert";
private static final String C_2_0_21="possibleReversing";
private static final String C_2_0_22="msg,yes";
private static final String C_2_0_23="!possibleReversing";
private static final String C_2_0_24="msg,no";
private static final String C_2_0_25="msg,has_to_play";
private static final String C_2_0_26="hasToPlay";
private static final String C_2_0_27="msg,yes";
private static final String C_2_0_28="!hasToPlay";
private static final String C_2_0_29="msg,no";
private static final String C_2_0_30="msg,loose_cond";
private static final String C_2_0_31="loosingIfFinishByBestCards";
private static final String C_2_0_32="msg,yes";
private static final String C_2_0_33="!loosingIfFinishByBestCards";
private static final String C_2_0_34="msg,no";
private static final String C_2_0_35="msg,switch";
private static final String C_2_0_36="switchCards";
private static final String C_2_0_37="msg,yes";
private static final String C_2_0_38="!switchCards";
private static final String C_2_0_39="msg,no";
private static final String C_2_0_40="msg,losse_first";
private static final String C_2_0_41="looserStartsFirst";
private static final String C_2_0_42="msg,yes";
private static final String C_2_0_43="!looserStartsFirst";
private static final String C_2_0_44="msg,no";
private PagePresidentRules(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc1){
Element elt0_=el(_doc1,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_BEAN,C_2_0_0));
attrs0_.add(at(XMLNS_C,C_2_0_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc1,HEAD);
Element elt2_=el(_doc1,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_2_0_2));
attrs1_.add(at(REL,C_2_0_3));
attrs1_.add(at(TYPE,C_2_0_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc1,BODY);
build0(elt3_,_doc1);
build1(elt3_,_doc1);
build2(elt3_,_doc1);
build3(elt3_,_doc1);
build4(elt3_,_doc1);
build5(elt3_,_doc1);
build6(elt3_,_doc1);
build7(elt3_,_doc1);
build8(elt3_,_doc1);
build9(elt3_,_doc1);
build10(elt3_,_doc1);
build11(elt3_,_doc1);
build12(elt3_,_doc1);
build13(elt3_,_doc1);
build14(elt3_,_doc1);
build15(elt3_,_doc1);
build16(elt3_,_doc1);
build17(elt3_,_doc1);
build18(elt3_,_doc1);
build19(elt3_,_doc1);
build20(elt3_,_doc1);
build21(elt3_,_doc1);
build22(elt3_,_doc1);
build23(elt3_,_doc1);
build24(elt3_,_doc1);
build25(elt3_,_doc1);
ad(elt0_,elt3_);
_doc1.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_5));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_2_0_6);
ad(_body,txt0_);
}
static void build1(Element _body,Document _doc){
br(_doc,_body);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_7));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_2_0_8);
ad(_body,txt0_);
}
static void build3(Element _body,Document _doc){
br(_doc,_body);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_9));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_2_0_10);
ad(_body,txt0_);
}
static void build5(Element _body,Document _doc){
br(_doc,_body);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_11));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_12));
at(elt1_,attrs1_);
Element elt2_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_2_0_13));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_14));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_15));
at(elt1_,attrs1_);
Element elt2_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_2_0_16));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_2_0_17));
at(elt3_,attrs3_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
br(_doc,_body);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_18));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_2_0_19);
ad(_body,txt0_);
}
static void build10(Element _body,Document _doc){
br(_doc,_body);
}
private static void br(Document _doc,Element _body) {
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_20));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_21));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_22));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_23));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_24));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_25));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_26));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_27));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_28));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_29));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_30));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_31));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_32));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_33));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_34));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_35));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_36));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_37));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_38));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_39));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build23(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_2_0_40));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build24(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_41));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_42));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
static void build25(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_2_0_43));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_2_0_44));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
br(_doc,elt0_);
ad(_body,elt0_);
}
}
