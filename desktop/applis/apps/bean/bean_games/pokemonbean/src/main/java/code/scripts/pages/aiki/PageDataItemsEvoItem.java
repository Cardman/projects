package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataItemsEvoItem extends PageCardsCommon{
private static final String C_P_118_0="javahtml";
private static final String C_P_118_1="evoitem";
private static final String C_P_118_2="msg_item,title";
private static final String C_P_118_3="displayName";
private static final String C_P_118_4=PkScriptPages.REN_ADD_WEB_CSS_ITEMS_CSS;
private static final String C_P_118_5="stylesheet";
private static final String C_P_118_6="text/css";
private static final String C_P_118_7="itemBean";
private static final String C_P_118_8="aiki.beans.items";
private static final String C_P_118_9="ItemBean";
private static final String C_P_118_10="$intern.name=name";
private static final String C_P_118_11="!pokemon.isEmpty()";
private static final String C_P_118_12="msg_evo_item,item";
private static final String C_P_118_13="pokemon";
private static final String C_P_118_14="p";
private static final String C_P_118_15="$clickPokemon({([p])})";
private static final String C_P_118_16="";
private static final String C_P_118_17="{getTrPokemon(([p]))}";
private PageDataItemsEvoItem(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc23){
Element elt0_=el(_doc23,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_118_0));
attrs0_.add(at(C_BEAN,C_P_118_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc23,HEAD);
Element elt2_=el(_doc23,TITLE);
Element elt3_=el(_doc23,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_118_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc23,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_118_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc23,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_118_4));
attrs3_.add(at(REL,C_P_118_5));
attrs3_.add(at(TYPE,C_P_118_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc23,BODY);
build0(elt6_,_doc23);
build1(elt6_,_doc23);
ad(elt0_,elt6_);
_doc23.appendChild(elt0_);
}
static void build0(Element _body,Document _doc23){
Element elt0_=el(_doc23,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_118_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc23,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_118_8));
at(elt1_,attrs1_);
Element elt2_=el(_doc23,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_118_9));
at(elt2_,attrs2_);
Element elt3_=el(_doc23,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_118_10));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc23){
Element elt0_=el(_doc23,P);
Element elt1_=el(_doc23,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_118_11));
at(elt1_,attrs0_);
Element elt2_=el(_doc23,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_118_12));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc23,UL);
Element elt4_=el(_doc23,C_FOR);
CustList<Attr> attrs2_=al(2);
attrs2_.add(at(LIST,C_P_118_13));
attrs2_.add(at(VAR,C_P_118_14));
at(elt4_,attrs2_);
Element elt5_=el(_doc23,LI);
Element elt6_=el(_doc23,A);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(C_COMMAND,C_P_118_15));
attrs3_.add(at(HREF,C_P_118_16));
at(elt6_,attrs3_);
Text txt0_=tx(_doc23,C_P_118_17);
ad(elt6_,txt0_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
