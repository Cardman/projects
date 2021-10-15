package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards21 extends HelpCardsCommon{

private HelpCards21(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_21_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element txt1_=tx(_doc,M_21_1);
ad(elt1_,txt1_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt2_=tx(_doc,M_21_2);
ad(elt1_,txt2_);
Element elt4_=el(_doc,BR);
ad(elt1_,elt4_);
Element txt3_=tx(_doc,M_21_3);
ad(elt1_,txt3_);
Element elt5_=el(_doc,BR);
ad(elt1_,elt5_);
Element elt6_=el(_doc,BR);
ad(elt1_,elt6_);
Element txt4_=tx(_doc,M_21_4);
ad(elt1_,txt4_);
Element elt7_=el(_doc,BR);
ad(elt1_,elt7_);
Element txt5_=tx(_doc,M_21_5);
ad(elt1_,txt5_);
Element elt8_=el(_doc,BR);
ad(elt1_,elt8_);
Element elt9_=el(_doc,BR);
ad(elt1_,elt9_);
Element txt6_=tx(_doc,M_21_6);
ad(elt1_,txt6_);
Element elt10_=el(_doc,BR);
ad(elt1_,elt10_);
Element txt7_=tx(_doc,M_21_7);
ad(elt1_,txt7_);
Element elt11_=el(_doc,BR);
ad(elt1_,elt11_);
Element txt8_=tx(_doc,M_21_8);
ad(elt1_,txt8_);
Element elt12_=el(_doc,BR);
ad(elt1_,elt12_);
Element txt9_=tx(_doc,M_21_9);
ad(elt1_,txt9_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
