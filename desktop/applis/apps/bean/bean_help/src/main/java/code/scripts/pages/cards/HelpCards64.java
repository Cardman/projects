package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards64 extends HelpCardsCommon{

private HelpCards64(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_64_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt1_=tx(_doc,M_64_1);
ad(elt1_,txt1_);
Element elt4_=el(_doc,BR);
ad(elt1_,elt4_);
Element elt5_=el(_doc,BR);
ad(elt1_,elt5_);
Element txt2_=tx(_doc,M_64_2);
ad(elt1_,txt2_);
Element elt6_=el(_doc,BR);
ad(elt1_,elt6_);
Element elt7_=el(_doc,OL);
Element elt8_=el(_doc,LI);
Element txt3_=tx(_doc,M_64_3);
ad(elt8_,txt3_);
ad(elt7_,elt8_);
Element elt9_=el(_doc,LI);
Element txt4_=tx(_doc,M_64_4);
ad(elt9_,txt4_);
ad(elt7_,elt9_);
Element elt10_=el(_doc,LI);
Element txt5_=tx(_doc,M_64_5);
ad(elt10_,txt5_);
ad(elt7_,elt10_);
Element elt11_=el(_doc,LI);
Element txt6_=tx(_doc,M_64_6);
ad(elt11_,txt6_);
ad(elt7_,elt11_);
Element elt12_=el(_doc,LI);
Element txt7_=tx(_doc,M_64_7);
ad(elt12_,txt7_);
ad(elt7_,elt12_);
Element elt13_=el(_doc,LI);
Element txt8_=tx(_doc,M_64_8);
ad(elt13_,txt8_);
ad(elt7_,elt13_);
ad(elt1_,elt7_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
