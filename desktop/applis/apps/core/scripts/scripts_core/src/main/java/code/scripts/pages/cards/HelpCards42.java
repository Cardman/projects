package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards42 extends HelpCardsCommon{

private HelpCards42(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_42_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element elt3_=el(_doc,OL);
Element elt4_=el(_doc,LI);
Element txt1_=tx(_doc,M_42_1);
ad(elt4_,txt1_);
Element elt5_=el(_doc,BR);
ad(elt4_,elt5_);
Element elt6_=el(_doc,OL);
Element elt7_=el(_doc,LI);
Element txt2_=tx(_doc,M_42_2);
ad(elt7_,txt2_);
ad(elt6_,elt7_);
Element elt8_=el(_doc,LI);
Element txt3_=tx(_doc,M_42_3);
ad(elt8_,txt3_);
ad(elt6_,elt8_);
Element elt9_=el(_doc,LI);
Element txt4_=tx(_doc,M_42_4);
ad(elt9_,txt4_);
ad(elt6_,elt9_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
Element elt10_=el(_doc,LI);
Element txt5_=tx(_doc,M_42_5);
ad(elt10_,txt5_);
Element elt11_=el(_doc,BR);
ad(elt10_,elt11_);
Element elt12_=el(_doc,OL);
Element elt13_=el(_doc,LI);
Element txt6_=tx(_doc,M_42_6);
ad(elt13_,txt6_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,LI);
Element txt7_=tx(_doc,M_42_7);
ad(elt14_,txt7_);
ad(elt12_,elt14_);
ad(elt10_,elt12_);
ad(elt3_,elt10_);
Element elt15_=el(_doc,LI);
Element txt8_=tx(_doc,M_42_8);
ad(elt15_,txt8_);
Element elt16_=el(_doc,BR);
ad(elt15_,elt16_);
Element elt17_=el(_doc,OL);
Element elt18_=el(_doc,LI);
Element txt9_=tx(_doc,M_42_9);
ad(elt18_,txt9_);
ad(elt17_,elt18_);
Element elt19_=el(_doc,LI);
Element txt10_=tx(_doc,M_42_10);
ad(elt19_,txt10_);
ad(elt17_,elt19_);
Element elt20_=el(_doc,LI);
Element txt11_=tx(_doc,M_42_11);
ad(elt20_,txt11_);
ad(elt17_,elt20_);
ad(elt15_,elt17_);
ad(elt3_,elt15_);
Element elt21_=el(_doc,LI);
Element txt12_=tx(_doc,M_42_12);
ad(elt21_,txt12_);
ad(elt3_,elt21_);
ad(elt1_,elt3_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
