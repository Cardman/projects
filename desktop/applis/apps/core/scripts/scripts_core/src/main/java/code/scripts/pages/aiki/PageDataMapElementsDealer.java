package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMapElementsDealer extends PageAikiCommon{
private static final String C_P_134_0="javahtml";
private static final String C_P_134_1="dealer";
private static final String C_P_134_2="web/css/pokedex.css";
private static final String C_P_134_3="stylesheet";
private static final String C_P_134_4="text/css";
private static final String C_P_134_5="msg_levelmap,title_dealer";
private static final String C_P_134_6="web/html/index.html";
private static final String C_P_134_7="";
private static final String C_P_134_8="msg_levelmap,index";
private static final String C_P_134_9="web/html/map/map.html";
private static final String C_P_134_10="";
private static final String C_P_134_11="msg_levelmap,map";
private static final String C_P_134_12="web/html/map/level.html";
private static final String C_P_134_13="";
private static final String C_P_134_14="msg_levelmap,level";
private static final String C_P_134_15="!getItems().isEmpty()";
private static final String C_P_134_16="getItems()";
private static final String C_P_134_17="i";
private static final String C_P_134_18="$clickItem({([i])})";
private static final String C_P_134_19="{getItem(([i]))}";
private static final String C_P_134_20="!getAllTm().isEmpty()";
private static final String C_P_134_21="getAllTm()";
private static final String C_P_134_22="i";
private static final String C_P_134_23="$clickTm({([i])})";
private static final String C_P_134_24="{getTm(([i]))}";
private PageDataMapElementsDealer(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_134_0));
attrs0_.add(at(C_BEAN,C_P_134_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_134_2));
attrs1_.add(at(REL,C_P_134_3));
attrs1_.add(at(TYPE,C_P_134_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc,TITLE);
Element elt4_=el(_doc,C_MESSAGE);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_134_5));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,BODY);
build0(elt5_,_doc);
build1(elt5_,_doc);
build2(elt5_,_doc);
build3(elt5_,_doc);
build4(elt5_,_doc);
build5(elt5_,_doc);
build6(elt5_,_doc);
build7(elt5_,_doc);
build8(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_134_6));
attrs0_.add(at(HREF,C_P_134_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_134_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build2(Element _body,Document _doc){
Element elt0_=el(_doc,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_134_9));
attrs0_.add(at(HREF,C_P_134_10));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_134_11));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,BR);
ad(_body,elt2_);
}
static void build3(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build4(Element _body,Document _doc){
Element elt0_=el(_doc,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_134_12));
attrs0_.add(at(HREF,C_P_134_13));
at(elt0_,attrs0_);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_134_14));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc,BR);
ad(_body,elt2_);
}
static void build5(Element _body,Document _doc){
Element elt0_=el(_doc,BR);
ad(_body,elt0_);
}
static void build6(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_134_15));
at(elt0_,attrs0_);
Element elt1_=el(_doc,UL);
Element elt2_=el(_doc,C_FOR);
CustList<Attr> attrs1_=al(2);
attrs1_.add(at(LIST,C_P_134_16));
attrs1_.add(at(VAR,C_P_134_17));
at(elt2_,attrs1_);
Element elt3_=el(_doc,LI);
Element elt4_=el(_doc,A);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(C_COMMAND,C_P_134_18));
at(elt4_,attrs2_);
Text txt0_=tx(_doc,C_P_134_19);
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build7(Element _body,Document _doc){
Element elt0_=el(_doc,HR);
ad(_body,elt0_);
}
static void build8(Element _body,Document _doc){
Element elt0_=el(_doc,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_134_20));
at(elt0_,attrs0_);
Element elt1_=el(_doc,UL);
Element elt2_=el(_doc,C_FOR);
CustList<Attr> attrs1_=al(2);
attrs1_.add(at(LIST,C_P_134_21));
attrs1_.add(at(VAR,C_P_134_22));
at(elt2_,attrs1_);
Element elt3_=el(_doc,LI);
Element elt4_=el(_doc,A);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(C_COMMAND,C_P_134_23));
at(elt4_,attrs2_);
Text txt0_=tx(_doc,C_P_134_24);
ad(elt4_,txt0_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
