package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PagePresidentRules extends PageCardsCommon{
private static final String C_0="rules";
private static final String C_1="javahtml";
private static final String C_2="resources_cards/css/president.css";
private static final String C_3="stylesheet";
private static final String C_4="text/css";
private static final String C_5="msg,beat_cards";
private static final String C_6="{cartesBattues}";
private static final String C_7="msg,nb_players";
private static final String C_8="{nbPlayers}";
private static final String C_9="msg,nb_stacks";
private static final String C_10="{nbStacks}";
private static final String C_11="sameAmount()";
private static final String C_12="msg,nb_cards";
private static final String C_13="nbCardsPerPlayerMin";
private static final String C_14="!sameAmount()";
private static final String C_15="msg,nb_cards_bet";
private static final String C_16="nbCardsPerPlayerMin";
private static final String C_17="nbCardsPerPlayerMax";
private static final String C_18="msg,equalty";
private static final String C_19="{equalty}";
private static final String C_20="msg,invert";
private static final String C_21="possibleReversing";
private static final String C_22="msg,yes";
private static final String C_23="!possibleReversing";
private static final String C_24="msg,no";
private static final String C_25="msg,has_to_play";
private static final String C_26="hasToPlay";
private static final String C_27="msg,yes";
private static final String C_28="!hasToPlay";
private static final String C_29="msg,no";
private static final String C_30="msg,loose_cond";
private static final String C_31="loosingIfFinishByBestCards";
private static final String C_32="msg,yes";
private static final String C_33="!loosingIfFinishByBestCards";
private static final String C_34="msg,no";
private static final String C_35="msg,switch";
private static final String C_36="switchCards";
private static final String C_37="msg,yes";
private static final String C_38="!switchCards";
private static final String C_39="msg,no";
private static final String C_40="msg,losse_first";
private static final String C_41="looserStartsFirst";
private static final String C_42="msg,yes";
private static final String C_43="!looserStartsFirst";
private static final String C_44="msg,no";
private PagePresidentRules(){}
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
build21(elt3_,_doc);
build22(elt3_,_doc);
build23(elt3_,_doc);
build24(elt3_,_doc);
build25(elt3_,_doc);
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
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_11));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_12));
at(elt1_,attrs1_);
Element elt2_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_13));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,BR);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_14));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_15));
at(elt1_,attrs1_);
Element elt2_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_16));
at(elt2_,attrs2_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_17));
at(elt3_,attrs3_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt4_=el(_doc,BR);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build9(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_18));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Text txt0_=tx(_doc,C_19);
ad(_body,txt0_);
}
static void build10(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build11(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_20));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build12(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_21));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_22));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build13(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_23));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_24));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build14(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_25));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build15(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_26));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_27));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build16(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_28));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_29));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build17(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_30));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build18(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_31));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_32));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build19(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_33));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_34));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build20(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_35));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build21(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_36));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_37));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build22(Element _body,Document _doc){
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
static void build23(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_40));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build24(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_41));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_42));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
static void build25(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_43));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_44));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,BR);
ad(elt0_,elt2_);
ad(_body,elt0_);
}
}
