package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards57 extends HelpCardsCommon{

private HelpCards57(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_57_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element txt1_=tx(_doc,M_57_1);
ad(elt1_,txt1_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt3_=tx(_doc,M_57_2);
ad(elt1_,txt3_);
Element elt6_=el(_doc,BR);
ad(elt1_,elt6_);
Element elt7_=el(_doc,BR);
ad(elt1_,elt7_);
Element txt4_=tx(_doc,M_57_3);
ad(elt1_,txt4_);
Element elt8_=el(_doc,BR);
ad(elt1_,elt8_);
Element elt9_=el(_doc,OL);
Element elt10_=el(_doc,LI);
Element txt5_=tx(_doc,M_57_4);
ad(elt10_,txt5_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,LI);
Element txt6_=tx(_doc,M_57_5);
ad(elt11_,txt6_);
ad(elt9_,elt11_);
ad(elt1_,elt9_);
Element elt12_=el(_doc,BR);
ad(elt1_,elt12_);
Element txt7_=tx(_doc,M_57_6);
ad(elt1_,txt7_);
Element elt13_=el(_doc,BR);
ad(elt1_,elt13_);
Element txt8_=tx(_doc,M_57_7);
ad(elt1_,txt8_);
Element elt14_=el(_doc,BR);
ad(elt1_,elt14_);
Element elt15_=el(_doc,BR);
ad(elt1_,elt15_);
Element txt9_=tx(_doc,M_57_8);
ad(elt1_,txt9_);
Element elt16_=el(_doc,BR);
ad(elt1_,elt16_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
