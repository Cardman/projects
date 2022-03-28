package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards52 extends HelpCardsCommon{

private HelpCards52(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_52_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element txt1_=tx(_doc,M_52_1);
ad(elt1_,txt1_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element elt4_=el(_doc,OL);
Element elt5_=el(_doc,LI);
Element txt2_=tx(_doc,M_52_2);
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,LI);
Element txt3_=tx(_doc,M_52_3);
ad(elt6_,txt3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,LI);
Element txt4_=tx(_doc,M_52_4);
ad(elt7_,txt4_);
ad(elt4_,elt7_);
ad(elt1_,elt4_);
Element elt8_=el(_doc,BR);
ad(elt1_,elt8_);
Element txt5_=tx(_doc,M_52_5);
ad(elt1_,txt5_);
Element elt9_=el(_doc,BR);
ad(elt1_,elt9_);
Element elt10_=el(_doc,BR);
ad(elt1_,elt10_);
Element txt6_=tx(_doc,M_52_6);
ad(elt1_,txt6_);
Element elt11_=el(_doc,BR);
ad(elt1_,elt11_);
Element elt12_=el(_doc,OL);
Element elt13_=el(_doc,LI);
Element txt7_=tx(_doc,M_52_7);
ad(elt13_,txt7_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,LI);
Element txt8_=tx(_doc,M_52_8);
ad(elt14_,txt8_);
ad(elt12_,elt14_);
Element elt15_=el(_doc,LI);
Element txt9_=tx(_doc,M_52_9);
ad(elt15_,txt9_);
ad(elt12_,elt15_);
ad(elt1_,elt12_);
Element elt16_=el(_doc,BR);
ad(elt1_,elt16_);
Element txt10_=tx(_doc,M_52_10);
ad(elt1_,txt10_);
Element elt17_=el(_doc,BR);
ad(elt1_,elt17_);
Element txt11_=tx(_doc,M_52_11);
ad(elt1_,txt11_);
Element elt18_=el(_doc,BR);
ad(elt1_,elt18_);
Element txt12_=tx(_doc,M_52_12);
ad(elt1_,txt12_);
Element elt19_=el(_doc,BR);
ad(elt1_,elt19_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
