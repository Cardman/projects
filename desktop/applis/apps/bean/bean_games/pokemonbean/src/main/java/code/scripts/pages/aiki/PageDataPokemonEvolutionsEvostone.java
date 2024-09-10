package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataPokemonEvolutionsEvostone extends PageCardsCommon{
private static final String C_P_187_0="javahtml";
private static final String C_P_187_1="evo_stone";
private static final String C_P_187_2=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_187_3="stylesheet";
private static final String C_P_187_4="text/css";
private static final String C_P_187_5="$clickEvo({index})";
private static final String C_P_187_6="";
private static final String C_P_187_7="{displayName}";
private static final String C_P_187_8="msg_evostone,stone";
private static final String C_P_187_9="displayBase";
private static final String C_P_187_10="{stone}";
private static final String C_P_187_11="$clickStone({index})";
private PageDataPokemonEvolutionsEvostone(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc92){
Element elt0_=el(_doc92,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_187_0));
attrs0_.add(at(C_BEAN,C_P_187_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc92,HEAD);
Element elt2_=el(_doc92,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_187_2));
attrs1_.add(at(REL,C_P_187_3));
attrs1_.add(at(TYPE,C_P_187_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc92,BODY);
build0(elt3_,_doc92);
ad(elt0_,elt3_);
_doc92.appendChild(elt0_);
}
static void build0(Element _body,Document _doc92){
Element elt0_=el(_doc92,TR);
Element elt1_=el(_doc92,TD);
Element elt2_=el(_doc92,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_187_5));
attrs0_.add(at(HREF,C_P_187_6));
at(elt2_,attrs0_);
Text txt0_=tx(_doc92,C_P_187_7);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc92,TD);
Element elt4_=el(_doc92,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_187_8));
at(elt4_,attrs1_);
Element elt5_=el(_doc92,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_187_9));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt12a_=el(_doc92,A);
CustList<Attr> attrs12a_=al(1);
attrs12a_.add(at(C_COMMAND,C_P_187_11));
at(elt12a_,attrs12a_);
Text txt3_=tx(_doc92,C_P_187_10);
ad(elt12a_,txt3_);
ad(elt3_,elt12a_);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
}
