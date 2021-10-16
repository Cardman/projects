package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataPokemonEvolutionsEvostonegender extends PageAikiCommon{
private static final String C_P_188_0="javahtml";
private static final String C_P_188_1="evo_stonegender";
private static final String C_P_188_2="web/css/pokedex.css";
private static final String C_P_188_3="stylesheet";
private static final String C_P_188_4="text/css";
private static final String C_P_188_5="$clickEvo({index})";
private static final String C_P_188_6="";
private static final String C_P_188_7="{displayName}";
private static final String C_P_188_8="msg_evostonegender,stone_gender";
private static final String C_P_188_9="displayBase";
private static final String C_P_188_10="stone";
private static final String C_P_188_11="gender";
private static final String C_P_188_12="index";
private PageDataPokemonEvolutionsEvostonegender(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_188_0));
attrs0_.add(at(C_BEAN,C_P_188_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_188_2));
attrs1_.add(at(REL,C_P_188_3));
attrs1_.add(at(TYPE,C_P_188_4));
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
attrs0_.add(at(C_COMMAND,C_P_188_5));
attrs0_.add(at(HREF,C_P_188_6));
at(elt2_,attrs0_);
Text txt0_=tx(_doc,C_P_188_7);
ad(elt2_,txt0_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,TD);
Element elt4_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_188_8));
at(elt4_,attrs1_);
Element elt5_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_P_188_9));
at(elt5_,attrs2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_P_188_10));
at(elt6_,attrs3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,PARAM);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(VALUE,C_P_188_11));
at(elt7_,attrs4_);
ad(elt4_,elt7_);
Element elt8_=el(_doc,PARAM);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_P_188_12));
at(elt8_,attrs5_);
ad(elt4_,elt8_);
ad(elt3_,elt4_);
ad(elt0_,elt3_);
ad(_body,elt0_);
}
}
