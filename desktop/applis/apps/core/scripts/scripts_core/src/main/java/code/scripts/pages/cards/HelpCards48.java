package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards48 extends HelpCardsCommon{

private HelpCards48(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_48_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element txt1_=tx(_doc,M_48_1);
ad(elt1_,txt1_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element elt4_=el(_doc,BR);
ad(elt1_,elt4_);
Element txt2_=tx(_doc,M_48_2);
ad(elt1_,txt2_);
Element elt5_=el(_doc,BR);
ad(elt1_,elt5_);
Element elt6_=el(_doc,BR);
ad(elt1_,elt6_);
Element txt3_=tx(_doc,M_48_3);
ad(elt1_,txt3_);
Element elt7_=el(_doc,BR);
ad(elt1_,elt7_);
Element elt8_=el(_doc,OL);
Element elt9_=el(_doc,LI);
Element txt4_=tx(_doc,M_48_4);
ad(elt9_,txt4_);
ad(elt8_,elt9_);
Element elt10_=el(_doc,LI);
Element txt5_=tx(_doc,M_48_5);
ad(elt10_,txt5_);
ad(elt8_,elt10_);
ad(elt1_,elt8_);
Element elt11_=el(_doc,BR);
ad(elt1_,elt11_);
Element txt6_=tx(_doc,M_48_6);
ad(elt1_,txt6_);
Element elt12_=el(_doc,BR);
ad(elt1_,elt12_);
Element elt13_=el(_doc,BR);
ad(elt1_,elt13_);
Element txt7_=tx(_doc,M_48_7);
ad(elt1_,txt7_);
Element elt14_=el(_doc,BR);
ad(elt1_,elt14_);
Element txt8_=tx(_doc,M_48_8);
ad(elt1_,txt8_);
Element elt15_=el(_doc,BR);
ad(elt1_,elt15_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
