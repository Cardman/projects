package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards38 extends HelpCardsCommon{

private HelpCards38(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_38_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element txt1_=tx(_doc,M_38_1);
ad(elt1_,txt1_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt3_=tx(_doc,M_38_2);
ad(elt1_,txt3_);
Element elt6_=el(_doc,BR);
ad(elt1_,elt6_);
Element txt5_=tx(_doc,M_38_3);
ad(elt1_,txt5_);
Element elt9_=el(_doc,BR);
ad(elt1_,elt9_);
Element elt10_=el(_doc,OL);
Element elt11_=el(_doc,LI);
Element txt6_=tx(_doc,M_38_4);
ad(elt11_,txt6_);
ad(elt10_,elt11_);
Element elt12_=el(_doc,LI);
Element txt7_=tx(_doc,M_38_5);
ad(elt12_,txt7_);
ad(elt10_,elt12_);
Element elt13_=el(_doc,LI);
Element txt8_=tx(_doc,M_38_6);
ad(elt13_,txt8_);
ad(elt10_,elt13_);
ad(elt1_,elt10_);
Element txt9_=tx(_doc,M_38_7);
ad(elt1_,txt9_);
Element elt14_=el(_doc,BR);
ad(elt1_,elt14_);
Element txt11_=tx(_doc,M_38_8);
ad(elt1_,txt11_);
Element elt17_=el(_doc,BR);
ad(elt1_,elt17_);
Element elt18_=el(_doc,BR);
ad(elt1_,elt18_);
Element txt12_=tx(_doc,M_38_9);
ad(elt1_,txt12_);
Element elt19_=el(_doc,BR);
ad(elt1_,elt19_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
