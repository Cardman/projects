package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards66 extends HelpCardsCommon{

private HelpCards66(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_66_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt1_=tx(_doc,M_66_1);
ad(elt1_,txt1_);
Element elt4_=el(_doc,BR);
ad(elt1_,elt4_);
Element elt5_=el(_doc,OL);
Element elt6_=el(_doc,LI);
Element txt2_=tx(_doc,M_66_2);
ad(elt6_,txt2_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,LI);
Element txt3_=tx(_doc,M_66_3);
ad(elt7_,txt3_);
ad(elt5_,elt7_);
ad(elt1_,elt5_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
