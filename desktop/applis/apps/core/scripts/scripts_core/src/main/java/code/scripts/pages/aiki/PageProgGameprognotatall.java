package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageProgGameprognotatall extends PageAikiCommon{
private static final String C_P_214_0="javahtml";
private static final String C_P_214_1="progressing";
private static final String C_P_214_2="msg_prog,titleNotAtAll";
private static final String C_P_214_3="web_prog/css/difficulty.css";
private static final String C_P_214_4="stylesheet";
private static final String C_P_214_5="text/css";
private static final String C_P_214_6="web_prog/html/gameprog.html";
private static final String C_P_214_7="msg_prog,returnMain";
private static final String C_P_214_8="k";
private static final String C_P_214_9="notAtAllFamiliesBase";
private static final String C_P_214_10="m";
private static final String C_P_214_11="java.lang.String";
private static final String C_P_214_12="ls";
private static final String C_P_214_13="{k}";
private static final String C_P_214_14="msg_prog,notCaughtPkCaughtNotPart";
private static final String C_P_214_15="ls";
private static final String C_P_214_16="m";
private static final String C_P_214_17="l";
private static final String C_P_214_18="l";
private static final String C_P_214_19="e";
private static final String C_P_214_20="{getImagePokemonNotAll(([k]),([l]),([e]))}";
private static final String C_P_214_21="{getTrPokemonNotAll(([k]),([l]),([e]))}";
private PageProgGameprognotatall(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc119){
Element elt0_=el(_doc119,HTML);
CustList<Attr> attrs0_=al(2);
attrs0_.add(at(XMLNS_C,C_P_214_0));
attrs0_.add(at(C_BEAN,C_P_214_1));
at(elt0_,attrs0_);
Element elt1_=el(_doc119,HEAD);
Element elt2_=el(_doc119,TITLE);
Element elt3_=el(_doc119,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_214_2));
at(elt3_,attrs1_);
ad(elt2_,elt3_);
ad(elt1_,elt2_);
Element elt4_=el(_doc119,LINK);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(HREF,C_P_214_3));
attrs2_.add(at(REL,C_P_214_4));
attrs2_.add(at(TYPE,C_P_214_5));
at(elt4_,attrs2_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
Element elt5_=el(_doc119,BODY);
build0(elt5_,_doc119);
build1(elt5_,_doc119);
ad(elt0_,elt5_);
_doc119.appendChild(elt0_);
}
static void build0(Element _body,Document _doc119){
Element elt0_=el(_doc119,A);
CustList<Attr> attrs0_=al(1);
attrs0_.add(at(C_COMMAND,C_P_214_6));
at(elt0_,attrs0_);
Element elt1_=el(_doc119,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_214_7));
at(elt1_,attrs1_);
ad(elt0_,elt1_);
ad(_body,elt0_);
Element elt2_=el(_doc119,BR);
ad(_body,elt2_);
}
static void build1(Element _body,Document _doc119){
Element elt0_=el(_doc119,C_FOR);
CustList<Attr> attrs0_=al(5);
attrs0_.add(at(KEY,C_P_214_8));
attrs0_.add(at(MAP,C_P_214_9));
attrs0_.add(at(VALUE,C_P_214_10));
attrs0_.add(at(KEYCLASSNAME,C_P_214_11));
attrs0_.add(at(VARCLASSNAME,C_P_214_12));
at(elt0_,attrs0_);
Text txt0_=tx(_doc119,C_P_214_13);
ad(elt0_,txt0_);
Element elt1_=el(_doc119,BR);
ad(elt0_,elt1_);
Element elt2_=el(_doc119,TABLE);
Element elt3_=el(_doc119,TBODY);
Element elt4_=el(_doc119,TR);
Element elt5_=el(_doc119,TD);
Element elt6_=el(_doc119,C_MESSAGE);
CustList<Attr> attrs1_=al(1);
attrs1_.add(at(VALUE,C_P_214_14));
at(elt6_,attrs1_);
ad(elt5_,elt6_);
ad(elt4_,elt5_);
Element elt7_=el(_doc119,C_FOR);
CustList<Attr> attrs2_=al(3);
attrs2_.add(at(CLASSNAME,C_P_214_15));
attrs2_.add(at(LIST,C_P_214_16));
attrs2_.add(at(VAR,C_P_214_17));
at(elt7_,attrs2_);
Element elt8_=el(_doc119,TD);
Element elt9_=el(_doc119,C_FOR);
CustList<Attr> attrs3_=al(2);
attrs3_.add(at(LIST,C_P_214_18));
attrs3_.add(at(VAR,C_P_214_19));
at(elt9_,attrs3_);
Element elt10_=el(_doc119,C_IMG);
CustList<Attr> attrs4_=al(1);
attrs4_.add(at(SRC,C_P_214_20));
at(elt10_,attrs4_);
ad(elt9_,elt10_);
Text txt1_=tx(_doc119,C_P_214_21);
ad(elt9_,txt1_);
Element elt11_=el(_doc119,BR);
ad(elt9_,elt11_);
ad(elt8_,elt9_);
ad(elt7_,elt8_);
ad(elt4_,elt7_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt12_=el(_doc119,BR);
ad(elt0_,elt12_);
ad(_body,elt0_);
}
}
