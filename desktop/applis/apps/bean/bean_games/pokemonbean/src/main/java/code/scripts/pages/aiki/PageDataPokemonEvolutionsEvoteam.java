package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataPokemonEvolutionsEvoteam extends PageCardsCommon{
private static final String C_P_189_0="javahtml";
private static final String C_P_189_1="evo_team";
private static final String C_P_189_2=PkScriptPages.REN_ADD_WEB_CSS_POKEDEX_CSS;
private static final String C_P_189_3="stylesheet";
private static final String C_P_189_4="text/css";
private static final String C_P_189_5="$clickEvo({index})";
private static final String C_P_189_6="";
private static final String C_P_189_7="{displayName}";
private static final String C_P_189_8="msg_evoteam,team";
private static final String C_P_189_9="displayBase";
private static final String C_P_189_10="{other}";
private static final String C_P_189_11="$clickTeam({index})";
private PageDataPokemonEvolutionsEvoteam(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc94){
Element elt0_=el(_doc94,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_189_0));
attrs0_.add(at(C_BEAN,C_P_189_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc94,HEAD);
Element elt2_=el(_doc94,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_189_2));
attrs1_.add(at(REL,C_P_189_3));
attrs1_.add(at(TYPE,C_P_189_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc94,BODY);
build0(elt3_,_doc94);
ad(elt0_,elt3_);
_doc94.appendChild(elt0_);
}
static void build0(Element _body,Document _doc94){
Element elt0_=el(_doc94,TR);
Element elt1_=el(_doc94,TD);
Element elt2_=el(_doc94,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_189_5));
attrs0_.add(at(HREF,C_P_189_6));
at(elt2_,attrs0_);
Text txt0_=tx(_doc94,C_P_189_7);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc94,TD);
Element elt4_=el(_doc94,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_189_8));
at(elt4_,attrs1_);
Element elt5_=el(_doc94,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_189_9));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
ad(elt3_,elt4_);
Element elt12a_=el(_doc94,A);
CustList<Attr> attrs12a_=al(1);
attrs12a_.add(at(C_COMMAND,C_P_189_11));
at(elt12a_,attrs12a_);
Text txt3_=tx(_doc94,C_P_189_10);
ad(elt12a_,txt3_);
ad(elt3_,elt12a_);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
}
