package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards9 extends HelpCardsCommon{

private HelpCards9(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_9_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt1_=tx(_doc,M_9_1);
ad(elt1_,txt1_);
Element elt4_=el(_doc,BR);
ad(elt1_,elt4_);
Element elt5_=el(_doc,BR);
ad(elt1_,elt5_);
Element elt6_=el(_doc,OL);
Element elt7_=el(_doc,LI);
Element txt2_=tx(_doc,M_9_2);
ad(elt7_,txt2_);
Element elt8_=el(_doc,BR);
ad(elt7_,elt8_);
Element elt9_=el(_doc,OL);
Element elt10_=el(_doc,LI);
Element txt3_=tx(_doc,M_9_3);
ad(elt10_,txt3_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,LI);
Element txt4_=tx(_doc,M_9_4);
ad(elt11_,txt4_);
ad(elt9_,elt11_);
Element elt12_=el(_doc,LI);
Element txt5_=tx(_doc,M_9_5);
ad(elt12_,txt5_);
ad(elt9_,elt12_);
ad(elt7_,elt9_);
ad(elt6_,elt7_);
Element elt13_=el(_doc,LI);
Element txt6_=tx(_doc,M_9_6);
ad(elt13_,txt6_);
ad(elt6_,elt13_);
Element elt14_=el(_doc,BR);
ad(elt6_,elt14_);
Element elt15_=el(_doc,LI);
Element txt7_=tx(_doc,M_9_7);
ad(elt15_,txt7_);
ad(elt6_,elt15_);
Element elt16_=el(_doc,BR);
ad(elt6_,elt16_);
Element elt17_=el(_doc,LI);
Element txt8_=tx(_doc,M_9_8);
ad(elt17_,txt8_);
ad(elt6_,elt17_);
Element elt18_=el(_doc,BR);
ad(elt6_,elt18_);
Element elt19_=el(_doc,LI);
Element txt9_=tx(_doc,M_9_9);
ad(elt19_,txt9_);
ad(elt6_,elt19_);
Element elt20_=el(_doc,BR);
ad(elt6_,elt20_);
Element elt21_=el(_doc,LI);
Element txt10_=tx(_doc,M_9_10);
ad(elt21_,txt10_);
ad(elt6_,elt21_);
ad(elt1_,elt6_);
Element elt22_=el(_doc,BR);
ad(elt1_,elt22_);
Element txt11_=tx(_doc,M_9_11);
ad(elt1_,txt11_);
Element elt23_=el(_doc,BR);
ad(elt1_,elt23_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
