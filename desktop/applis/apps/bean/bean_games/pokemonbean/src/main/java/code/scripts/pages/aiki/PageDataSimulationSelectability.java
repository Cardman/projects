package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataSimulationSelectability extends PageCardsCommon{
private static final String C_P_198_0="javahtml";
private static final String C_P_198_1="selectability";
private static final String C_P_198_2="msg_levelsimu,title_select_ability";
private static final String C_P_198_3=PkScriptPages.REN_ADD_WEB_CSS_SIMULATION_CSS;
private static final String C_P_198_4="stylesheet";
private static final String C_P_198_5="text/css";
private static final String C_P_198_6="$cancel()";
private static final String C_P_198_7="";
private static final String C_P_198_8="msg_levelsimu,cancel";
private static final String C_P_198_9="";
private static final String C_P_198_10="$search";
private static final String C_P_198_11="post";
private static final String C_P_198_12="searching";
private static final String C_P_198_13="msg_abilities,content";
private static final String C_P_198_14="typedAbility";
private static final String C_P_198_15="typedAbility";
private static final String C_P_198_16="text";
private static final String C_P_198_18="msg_abilities,ok";
private static final String C_P_198_19="sortedAbilities";
private static final String C_P_198_20="a";
private static final String C_P_198_21="$clickAbility({([a])})";
private static final String C_P_198_22="";
private static final String C_P_198_23="{getTrAbility(([a]))}";
private PageDataSimulationSelectability(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc103){
Element elt0_=el(_doc103,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_198_0));
attrs0_.add(at(C_BEAN,C_P_198_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc103,HEAD);
Element elt2_=el(_doc103,TITLE);
Element elt3_=el(_doc103,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_198_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc103,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_198_3));
attrs2_.add(at(REL,C_P_198_4));
attrs2_.add(at(TYPE,C_P_198_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc103,BODY);
build0(elt5_,_doc103);
build1(elt5_,_doc103);
build2(elt5_,_doc103);
br(elt5_,_doc103);
build4(elt5_,_doc103);
ad(elt0_,elt5_);
_doc103.appendChild(elt0_);
}
static void build0(Element _body,Document _doc103){
Element elt0_=el(_doc103,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_198_6));
attrs0_.add(at(HREF,C_P_198_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc103,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_198_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc103){
Element elt0_=el(_doc103,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc103){
Element elt0_=el(_doc103,FORM);
CustList<Attr> attrs0_=al(4);
attrs0_.add(at(ACTION,C_P_198_9));
attrs0_.add(at(C_COMMAND,C_P_198_10));
attrs0_.add(at(METHOD,C_P_198_11));
attrs0_.add(at(NAME,C_P_198_12));
at(elt0_,attrs0_);
Element elt1_=el(_doc103,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_198_13));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
Element elt2_=el(_doc103,INPUT);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(C_VARVALUE,C_P_198_14));
attrs2_.add(at(NAME,C_P_198_15));
attrs2_.add(at(TYPE,C_P_198_16));
at(elt2_,attrs2_);
ad(elt0_,elt2_);
Element elt3_=el(_doc103,BR);
ad(elt0_,elt3_);
Element elt4_=el(_doc103,C_SUBMIT);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(MESSAGE,C_P_198_18));
at(elt4_,attrs3_);
ad(elt0_,elt4_);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc103){
Element elt0_=el(_doc103,UL);
Element elt1_=el(_doc103,C_FOR);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(LIST,C_P_198_19));
attrs0_.add(at(VAR,C_P_198_20));
at(elt1_,attrs0_);
Element elt2_=el(_doc103,LI);
Element elt3_=el(_doc103,A);
CustList<Attr> attrs1_=al(2);
attrs1_.add(at(C_COMMAND,C_P_198_21));
attrs1_.add(at(HREF,C_P_198_22));
at(elt3_,attrs1_);
Text txt0_=tx(_doc103,C_P_198_23);
ad(elt3_,txt0_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
