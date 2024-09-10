package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataPokemonEvolutionsEvolevelgender extends PageCardsCommon{
private static final String C_P_185_0="javahtml";
private static final String C_P_185_1="evo_levelgender";
private static final String C_P_185_2=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_185_3="stylesheet";
private static final String C_P_185_4="text/css";
private static final String C_P_185_5="$clickEvo({index})";
private static final String C_P_185_6="";
private static final String C_P_185_7="{displayName}";
private static final String C_P_185_8="msg_evolevelgender,gender";
private static final String C_P_185_9="displayBase";
private static final String C_P_185_10="level";
private static final String C_P_185_11="gender";
private PageDataPokemonEvolutionsEvolevelgender(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc90){
Element elt0_=el(_doc90,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_185_0));
attrs0_.add(at(C_BEAN,C_P_185_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc90,HEAD);
Element elt2_=el(_doc90,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_185_2));
attrs1_.add(at(REL,C_P_185_3));
attrs1_.add(at(TYPE,C_P_185_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc90,BODY);
build0(elt3_,_doc90);
ad(elt0_,elt3_);
_doc90.appendChild(elt0_);
}
static void build0(Element _body,Document _doc90){
Element elt0_=el(_doc90,TR);
Element elt1_=el(_doc90,TD);
Element elt2_=el(_doc90,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_185_5));
attrs0_.add(at(HREF,C_P_185_6));
at(elt2_,attrs0_);
Text txt0_=tx(_doc90,C_P_185_7);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc90,TD);
Element elt4_=el(_doc90,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_185_8));
at(elt4_,attrs1_);
Element elt5_=el(_doc90,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_185_9));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc90,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_185_10));
at(elt6_,attrs3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc90,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_185_11));
at(elt7_,attrs4_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
}
