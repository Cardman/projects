package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMapMap extends PageCardsCommon{
private static final String C_P_142_0="javahtml";
private static final String C_P_142_1="game_map";
private static final String C_P_142_2="msg_levelmap,title_map";
private static final String C_P_142_3="web/css/pokedex.css";
private static final String C_P_142_4="stylesheet";
private static final String C_P_142_5="text/css";
private static final String C_P_142_6=GO_TO_IND;
private static final String C_P_142_7="";
private static final String C_P_142_8="msg_levelmap,index";
private static final String C_P_142_9="aiki.beans.facade.map.dto.PlaceIndex";
private static final String C_P_142_10="places";
private static final String C_P_142_11="p";
private static final String C_P_142_12="{p.getPlace().getName()}";
private static final String C_P_142_13="isMultiLayer(([p]))";
private static final String C_P_142_14="layers(([p]))";
private static final String C_P_142_15="l";
private static final String C_P_142_16="$clickLevel({p.index},{([l])})";
private static final String C_P_142_17="{([l])}";
private static final String C_P_142_18="!isMultiLayer(([p]))";
private static final String C_P_142_19="$clickLevelZero({p.index})";
private static final String C_P_142_20="msg_levelmap,goLevel";
private PageDataMapMap(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc47){
Element elt0_=el(_doc47,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_142_0));
attrs0_.add(at(C_BEAN,C_P_142_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc47,HEAD);
Element elt2_=el(_doc47,TITLE);
Element elt3_=el(_doc47,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_142_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc47,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_142_3));
attrs2_.add(at(REL,C_P_142_4));
attrs2_.add(at(TYPE,C_P_142_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc47,BODY);
build0(elt5_,_doc47);
build1(elt5_,_doc47);
ad(elt0_,elt5_);
_doc47.appendChild(elt0_);
}
static void build0(Element _body,Document _doc47){
Element elt0_=el(_doc47,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_142_6));
attrs0_.add(at(HREF,C_P_142_7));
at(elt0_,attrs0_);
Element elt1_=el(_doc47,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_142_8));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc47){
Element elt0_=el(_doc47,UL);
Element elt1_=el(_doc47,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(CLASSNAME,C_P_142_9));
attrs0_.add(at(LIST,C_P_142_10));
attrs0_.add(at(VAR,C_P_142_11));
at(elt1_,attrs0_);
Element elt2_=el(_doc47,LI);
Text txt0_=tx(_doc47,C_P_142_12);
ad(elt2_,txt0_);
Element elt3_=el(_doc47,BR);
ad(elt2_,elt3_);
Element elt4_=el(_doc47,C_IF);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(CONDITION,C_P_142_13));
at(elt4_,attrs1_);
Element elt5_=el(_doc47,UL);
Element elt6_=el(_doc47,C_FOR);
CustList<Attr> attrs2_=al(2);
attrs2_.add(at(LIST,C_P_142_14));
attrs2_.add(at(VAR,C_P_142_15));
at(elt6_,attrs2_);
Element elt7_=el(_doc47,LI);
Element elt8_=el(_doc47,A);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(C_COMMAND,C_P_142_16));
at(elt8_,attrs3_);
Text txt1_=tx(_doc47,C_P_142_17);
ad(elt8_,txt1_);
ad(elt7_,elt8_);
ad(elt6_,elt7_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
ad(elt2_,elt4_);
Element elt9_=el(_doc47,C_IF);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(CONDITION,C_P_142_18));
at(elt9_,attrs4_);
Element elt10_=el(_doc47,A);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(C_COMMAND,C_P_142_19));
at(elt10_,attrs5_);
Element elt11_=el(_doc47,C_MESSAGE);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(VALUE,C_P_142_20));
at(elt11_,attrs6_);
ad(elt10_,elt11_);
ad(elt9_,elt10_);
ad(elt2_,elt9_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
