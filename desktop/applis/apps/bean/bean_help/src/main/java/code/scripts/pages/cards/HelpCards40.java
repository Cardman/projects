package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards40 extends HelpCardsCommon{

private HelpCards40(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_40_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element txt1_=tx(_doc,M_40_1);
ad(elt1_,txt1_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt2_=tx(_doc,M_40_2);
ad(elt1_,txt2_);
Element elt4_=el(_doc,BR);
ad(elt1_,elt4_);
Element txt3_=tx(_doc,M_40_3);
ad(elt1_,txt3_);
Element elt5_=el(_doc,BR);
ad(elt1_,elt5_);
Element elt6_=el(_doc,BR);
ad(elt1_,elt6_);
Element txt4_=tx(_doc,M_40_4);
ad(elt1_,txt4_);
Element elt7_=el(_doc,BR);
ad(elt1_,elt7_);
Element elt8_=el(_doc,OL);
Element elt9_=el(_doc,LI);
Element txt5_=tx(_doc,M_40_5);
ad(elt9_,txt5_);
ad(elt8_,elt9_);
Element elt10_=el(_doc,LI);
Element txt6_=tx(_doc,M_40_6);
ad(elt10_,txt6_);
ad(elt8_,elt10_);
Element elt11_=el(_doc,LI);
Element txt7_=tx(_doc,M_40_7);
ad(elt11_,txt7_);
ad(elt8_,elt11_);
ad(elt1_,elt8_);
Element txt8_=tx(_doc,M_40_8);
ad(elt1_,txt8_);
Element elt12_=el(_doc,BR);
ad(elt1_,elt12_);
Element txt9_=tx(_doc,M_40_9);
ad(elt1_,txt9_);
Element elt13_=el(_doc,BR);
ad(elt1_,elt13_);
Element elt14_=el(_doc,BR);
ad(elt1_,elt14_);
Element txt10_=tx(_doc,M_40_10);
ad(elt1_,txt10_);
Element elt15_=el(_doc,BR);
ad(elt1_,elt15_);
Element elt16_=el(_doc,BR);
ad(elt1_,elt16_);
Element txt11_=tx(_doc,M_40_11);
ad(elt1_,txt11_);
Element elt17_=el(_doc,BR);
ad(elt1_,elt17_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
