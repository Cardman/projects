package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataAbilityAbilities extends PageAikiCommon{
private static final String C_P_96_0="javahtml";
private static final String C_P_96_1="abilities";
private static final String C_P_96_2="msg_abilities,title";
private static final String C_P_96_3="web/css/abilities.css";
private static final String C_P_96_4="stylesheet";
private static final String C_P_96_5="text/css";
private static final String C_P_96_6="web/html/index.html";
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
private static final String C_P_96_17="submit";
private static final String C_P_96_18="OK";
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
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_96_0));
attrs0_.add(at(C_BEAN,C_P_96_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,TITLE);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_96_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_96_3));
attrs2_.add(at(REL,C_P_96_4));
attrs2_.add(at(TYPE,C_P_96_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,BODY);
build0(elt5_,_doc);
build1(elt5_,_doc);
build2(elt5_,_doc);
build3(elt5_,_doc);
build4(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_96_6));
attrs0_.add(at(HREF,C_P_96_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_96_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_96_9));
attrs0_.add(at(C_COMMAND,C_P_96_10));
attrs0_.add(at(METHOD,C_P_96_11));
attrs0_.add(at(NAME,C_P_96_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_96_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,INPUT);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(C_VARVALUE,C_P_96_14));
attrs2_.add(at(NAME,C_P_96_15));
attrs2_.add(at(TYPE,C_P_96_16));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc,INPUT);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(TYPE,C_P_96_17));
attrs3_.add(at(VALUE,C_P_96_18));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,UL);
Element elt1_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_96_19));
attrs0_.add(at(VAR,C_P_96_20));
at(elt1_,attrs0_);
Element elt2_=el(_doc,LI);
Element elt3_=el(_doc,A);
CustList<Attr> attrs1_=al(2);
attrs1_.add(at(C_COMMAND,C_P_96_21));
attrs1_.add(at(HREF,C_P_96_22));
at(elt3_,attrs1_);
Text txt0_=tx(_doc,C_P_96_23);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
