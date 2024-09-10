package code.scripts.pages.cards;
import code.scripts.confs.*;
import code.scripts.pages.aiki.PageCardsCommon;
import code.sml.*;
import code.util.*;
final class PageBeloteDetailsresults extends PageCardsCommon{
private static final String C_1_2_0="details";
private static final String C_1_2_1="javahtml";
private static final String C_1_2_2="msg,results";
private static final String C_1_2_3=BeloteScriptPages.CSS;
private static final String C_1_2_4="stylesheet";
private static final String C_1_2_5="text/css";
private static final String C_1_2_6="msg,detail";
private static final String C_1_2_7="p";
private static final String C_1_2_8="declaring";
private static final String C_1_2_9="cards.belote.beans.SumDeclaringPlayer";
private static final String C_1_2_10="msg,decl_player";
private static final String C_1_2_11="p.nickname";
private static final String C_1_2_12="p.statut";
private static final String C_1_2_13="p.declaring.isEmpty()";
private static final String C_1_2_14="msg,nothing";
private static final String C_1_2_15="!p.declaring.isEmpty()";
private static final String C_1_2_16="d";
private static final String C_1_2_17="p.declaring";
private static final String C_1_2_18="cards.belote.beans.DeclaringPlayerValue";
private static final String C_1_2_19="{d.declaring} : {d.value}";
private static final String C_1_2_20="msg,sum";
private static final String C_1_2_21="{p.sum}";
private PageBeloteDetailsresults(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_BEAN,C_1_2_0));
attrs0_.add(at(XMLNS_C,C_1_2_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc,HEAD);
Element elt2_=el(_doc,TITLE);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_2_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_1_2_3));
attrs2_.add(at(REL,C_1_2_4));
attrs2_.add(at(TYPE,C_1_2_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc,BODY);
build0(elt5_,_doc);
build1(elt5_,_doc);
ad(elt0_,elt5_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,H1);
Element elt1_=el(_doc,C_MESSAGE);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(VALUE,C_1_2_6));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
static void build1(Element _body,Document _doc){
Element elt0_=el(_doc,UL);
Element elt1_=el(_doc,C_FOR);
CustList<Attr> attrs0_=al(3);
attrs0_.add(at(VAR,C_1_2_7));
attrs0_.add(at(LIST,C_1_2_8));
attrs0_.add(at(CLASSNAME,C_1_2_9));
at(elt1_,attrs0_);
Element elt2_=el(_doc,LI);
Element elt3_=el(_doc,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_1_2_10));
at(elt3_,attrs1_);
Element elt4_=el(_doc,PARAM);
CustList<Attr> attrs2_=al(1);
attrs2_.add(at(VALUE,C_1_2_11));
at(elt4_,attrs2_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,PARAM);
CustList<Attr> attrs3_=al(1);
attrs3_.add(at(VALUE,C_1_2_12));
at(elt5_,attrs3_);
ad(elt3_,elt5_);
ad(elt2_,elt3_);
Element elt6_=el(_doc,C_IF);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(CONDITION,C_1_2_13));
at(elt6_,attrs4_);
Element elt7_=el(_doc,C_MESSAGE);
CustList<Attr> attrs5_=al(1);
attrs5_.add(at(VALUE,C_1_2_14));
at(elt7_,attrs5_);
ad(elt6_,elt7_);
ad(elt2_,elt6_);
Element elt8_=el(_doc,C_IF);
CustList<Attr> attrs6_=al(1);
attrs6_.add(at(CONDITION,C_1_2_15));
at(elt8_,attrs6_);
Element elt9_=el(_doc,BR);
ad(elt8_,elt9_);
Element elt10_=el(_doc,UL);
Element elt11_=el(_doc,C_FOR);
CustList<Attr> attrs7_=al(3);
attrs7_.add(at(VAR,C_1_2_16));
attrs7_.add(at(LIST,C_1_2_17));
attrs7_.add(at(CLASSNAME,C_1_2_18));
at(elt11_,attrs7_);
Element elt12_=el(_doc,LI);
Text txt0_=tx(_doc,C_1_2_19);
ad(elt12_,txt0_);
ad(elt11_,elt12_);
ad(elt10_,elt11_);
Element elt13_=el(_doc,LI);
Element elt14_=el(_doc,C_MESSAGE);
CustList<Attr> attrs8_=al(1);
attrs8_.add(at(VALUE,C_1_2_20));
at(elt14_,attrs8_);
ad(elt13_,elt14_);
Text txt1_=tx(_doc,C_1_2_21);
ad(elt13_,txt1_);
ad(elt10_,elt13_);
ad(elt8_,elt10_);
ad(elt2_,elt8_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
ad(_body,elt0_);
}
}
