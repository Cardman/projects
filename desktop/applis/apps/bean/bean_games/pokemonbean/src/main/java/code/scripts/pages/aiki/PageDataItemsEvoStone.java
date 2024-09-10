package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataItemsEvoStone extends PageCardsCommon{
private static final String C_P_119_0="javahtml";
private static final String C_P_119_1="evostone";
private static final String C_P_119_2="msg_item,title";
private static final String C_P_119_3="displayName";
private static final String C_P_119_4=PkScriptPages.REN_ADD_WEB_CSS_ITEMS_CSS;
private static final String C_P_119_5="stylesheet";
private static final String C_P_119_6="text/css";
private static final String C_P_119_7="itemBean";
private static final String C_P_119_8="aiki.beans.items";
private static final String C_P_119_9="ItemBean";
private static final String C_P_119_10="$intern.name=name";
private static final String C_P_119_11="!pokemon.isEmpty()";
private static final String C_P_119_12="msg_evo_stone,item";
private static final String C_P_119_13="pokemon";
private static final String C_P_119_14="p";
private static final String C_P_119_15="$clickPokemon({([p])})";
private static final String C_P_119_16="";
private static final String C_P_119_17="{getTrPokemon(([p]))}";
private PageDataItemsEvoStone(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc24){
Element elt0_=el(_doc24,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_119_0));
attrs0_.add(at(C_BEAN,C_P_119_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc24,HEAD);
Element elt2_=el(_doc24,TITLE);
Element elt3_=el(_doc24,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_119_2));
at(elt3_,attrs1_);
Element elt4_=el(_doc24,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_119_3));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt5_=el(_doc24,LINK);
CustList<Attr> attrs3_=al(3);
attrs3_.add(at(HREF,C_P_119_4));
attrs3_.add(at(REL,C_P_119_5));
attrs3_.add(at(TYPE,C_P_119_6));
at(elt5_,attrs3_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
Element elt6_=el(_doc24,BODY);
build0(elt6_,_doc24);
build1(elt6_,_doc24);
ad(elt0_,elt6_);
_doc24.appendChild(elt0_);
}
static void build0(Element _body,Document _doc24){
Element elt0_=el(_doc24,C_IMPORT);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(PAGE,C_P_119_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc24,C_PACKAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(NAME,C_P_119_8));
at(elt1_,attrs1_);
Element elt2_=el(_doc24,C_CLASS);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(NAME,C_P_119_9));
at(elt2_,attrs2_);
Element elt3_=el(_doc24,C_FIELD);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(PREPARE,C_P_119_10));
at(elt3_,attrs3_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc24){
Element elt0_=el(_doc24,P);
Element elt1_=el(_doc24,C_IF);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(CONDITION,C_P_119_11));
at(elt1_,attrs0_);
Element elt2_=el(_doc24,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_119_12));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
Element elt3_=el(_doc24,UL);
Element elt4_=el(_doc24,C_FOR);
CustList<Attr> attrs2_=al(2);
attrs2_.add(at(LIST,C_P_119_13));
attrs2_.add(at(VAR,C_P_119_14));
at(elt4_,attrs2_);
Element elt5_=el(_doc24,LI);
Element elt6_=el(_doc24,A);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(C_COMMAND,C_P_119_15));
attrs3_.add(at(HREF,C_P_119_16));
at(elt6_,attrs3_);
Text txt0_=tx(_doc24,C_P_119_17);
ad(elt6_,txt0_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
