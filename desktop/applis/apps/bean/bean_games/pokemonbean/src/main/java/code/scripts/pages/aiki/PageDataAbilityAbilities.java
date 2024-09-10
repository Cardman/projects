package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataAbilityAbilities extends PageCardsCommon{
private static final String C_P_96_0="javahtml";
private static final String C_P_96_1="abilities";
private static final String C_P_96_2="msg_abilities,title";
private static final String C_P_96_3=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_96_4="stylesheet";
private static final String C_P_96_5="text/css";
private static final String C_P_96_6=GO_TO_IND;
private static final String C_P_96_7="";
private static final String C_P_96_8="msg_abilities,index";
private static final String C_P_96_9="";
private static final String C_P_96_10="$search";
private static final String C_P_96_11="post";
private static final String C_P_96_12="searching";
private static final String C_P_96_13="msg_abilities,content";
private static final String C_P_96_14="typedAbility";
private static final String C_P_96_15="typedAbility";
private static final String C_P_96_16="text";
private static final String C_P_96_18="msg_abilities,ok";
private static final String C_P_96_19="sortedAbilities";
private static final String C_P_96_20="a";
private static final String C_P_96_21="$clickAbility({([a])})";
private static final String C_P_96_22="";
private static final String C_P_96_23="{getTrAbility(([a]))}";
private PageDataAbilityAbilities(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc1){
Element elt0_=el(_doc1,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_96_0));
attrs0_.add(at(C_BEAN,C_P_96_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc1,HEAD);
Element elt2_=el(_doc1,TITLE);
Element elt3_=el(_doc1,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_96_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc1,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_96_3));
attrs2_.add(at(REL,C_P_96_4));
attrs2_.add(at(TYPE,C_P_96_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc1,BODY);
build0(elt5_,_doc1);
build1(elt5_,_doc1);
build2(elt5_,_doc1);
br(elt5_,_doc1);
build4(elt5_,_doc1);
ad(elt0_,elt5_);
_doc1.appendChild(elt0_);
}
static void build0(Element _body,Document _doc1){
Element elt0_=el(_doc1,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_96_6));
attrs0_.add(at(HREF,C_P_96_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc1,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_96_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc1){
Element elt0_=el(_doc1,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc1){
Element elt0_=el(_doc1,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_96_9));
attrs0_.add(at(C_COMMAND,C_P_96_10));
attrs0_.add(at(METHOD,C_P_96_11));
attrs0_.add(at(NAME,C_P_96_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc1,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_96_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc1,INPUT);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(C_VARVALUE,C_P_96_14));
attrs2_.add(at(NAME,C_P_96_15));
attrs2_.add(at(TYPE,C_P_96_16));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc1,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc1,C_SUBMIT);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(MESSAGE,C_P_96_18));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc1){
Element elt0_=el(_doc1,UL);
Element elt1_=el(_doc1,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_96_19));
attrs0_.add(at(VAR,C_P_96_20));
at(elt1_,attrs0_);
Element elt2_=el(_doc1,LI);
Element elt3_=el(_doc1,A);
CustList<Attr> attrs1_=al(2);
attrs1_.add(at(C_COMMAND,C_P_96_21));
attrs1_.add(at(HREF,C_P_96_22));
at(elt3_,attrs1_);
Text txt0_=tx(_doc1,C_P_96_23);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
