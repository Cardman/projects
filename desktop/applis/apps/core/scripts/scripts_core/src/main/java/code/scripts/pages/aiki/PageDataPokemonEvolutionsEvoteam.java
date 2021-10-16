package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataPokemonEvolutionsEvoteam extends PageAikiCommon{
private static final String C_P_189_0="javahtml";
private static final String C_P_189_1="evo_team";
private static final String C_P_189_2="web/css/pokedex.css";
private static final String C_P_189_3="stylesheet";
private static final String C_P_189_4="text/css";
private static final String C_P_189_5="$clickEvo({index})";
private static final String C_P_189_6="";
private static final String C_P_189_7="{displayName}";
private static final String C_P_189_8="msg_evoteam,team";
private static final String C_P_189_9="displayBase";
private static final String C_P_189_10="other";
private static final String C_P_189_11="index";
private PageDataPokemonEvolutionsEvoteam(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_189_0));
attrs0_.add(at(C_BEAN,C_P_189_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_189_2));
attrs1_.add(at(REL,C_P_189_3));
attrs1_.add(at(TYPE,C_P_189_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,BODY);
build0(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,TR);
Element elt1_=el(_doc,TD);
Element elt2_=el(_doc,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_189_5));
attrs0_.add(at(HREF,C_P_189_6));
at(elt2_,attrs0_);
Text txt0_=tx(_doc,C_P_189_7);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,TD);
Element elt4_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_189_8));
at(elt4_,attrs1_);
Element elt5_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_189_9));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_189_10));
at(elt6_,attrs3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_189_11));
at(elt7_,attrs4_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
}
