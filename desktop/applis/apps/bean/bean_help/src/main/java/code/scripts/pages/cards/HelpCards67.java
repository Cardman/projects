package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards67 extends HelpCardsCommon{

private HelpCards67(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_67_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element elt3_=el(_doc,OL);
Element elt4_=el(_doc,LI);
Element txt1_=tx(_doc,M_67_1);
ad(elt4_,txt1_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,LI);
Element txt2_=tx(_doc,M_67_2);
ad(elt5_,txt2_);
ad(elt3_,elt5_);
Element elt6_=el(_doc,LI);
Element txt3_=tx(_doc,M_67_3);
ad(elt6_,txt3_);
ad(elt3_,elt6_);
Element elt7_=el(_doc,LI);
Element txt4_=tx(_doc,M_67_4);
ad(elt7_,txt4_);
ad(elt3_,elt7_);
Element elt8_=el(_doc,LI);
Element txt5_=tx(_doc,M_67_5);
ad(elt8_,txt5_);
ad(elt3_,elt8_);
Element elt9_=el(_doc,LI);
Element txt6_=tx(_doc,M_67_6);
ad(elt9_,txt6_);
ad(elt3_,elt9_);
Element elt10_=el(_doc,LI);
Element txt7_=tx(_doc,M_67_7);
ad(elt10_,txt7_);
ad(elt3_,elt10_);
Element elt11_=el(_doc,LI);
Element txt8_=tx(_doc,M_67_8);
ad(elt11_,txt8_);
ad(elt3_,elt11_);
Element elt12_=el(_doc,LI);
Element txt9_=tx(_doc,M_67_9);
ad(elt12_,txt9_);
ad(elt3_,elt12_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
