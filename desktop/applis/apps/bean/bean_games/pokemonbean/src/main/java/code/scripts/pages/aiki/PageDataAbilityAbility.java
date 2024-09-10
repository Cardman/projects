package code.scripts.pages.aiki;
import code.scripts.confs.*;
import code.sml.*;
import code.util.*;
final class PageDataAbilityAbility extends PageCardsCommon{
private static final String C_P_97_0="javahtml";
private static final String C_P_97_1="ability";
private static final String C_P_97_2=PkScriptPages.REN_ADD_WEB_CSS_ABILITIES_CSS;
private static final String C_P_97_3="stylesheet";
private static final String C_P_97_4="text/css";
private static final String C_P_97_5="$a()";
private static final String C_P_97_6="";
private static final String C_P_97_7="index";
private static final String C_P_97_8="{displayName}";
private PageDataAbilityAbility(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc2){
Element elt0_=el(_doc2,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_97_0));
attrs0_.add(at(C_BEAN,C_P_97_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc2,HEAD);
Element elt2_=el(_doc2,LINK);
CustList<Attr> attrs1_=al(3);
attrs1_.add(at(HREF,C_P_97_2));
attrs1_.add(at(REL,C_P_97_3));
attrs1_.add(at(TYPE,C_P_97_4));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc2,BODY);
build0(elt3_,_doc2);
build1(elt3_,_doc2);
ad(elt0_,elt3_);
_doc2.appendChild(elt0_);
}
static void build0(Element _body,Document _doc2){
Element elt0_=el(_doc2,A);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(C_COMMAND,C_P_97_5));
attrs0_.add(at(HREF,C_P_97_6));
at(elt0_,attrs0_);
Text txt0_=tx(_doc2,C_P_97_7);
ad(elt0_,txt0_);
ad(_body,elt0_);
Text txt1_=tx(_doc2,C_P_97_8);
ad(_body,txt1_);
}
static void build1(Element _body,Document _doc2){
Element elt0_=el(_doc2,BR);
ad(_body,elt0_);
}
}
