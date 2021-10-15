package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards59 extends HelpCardsCommon{

private HelpCards59(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_59_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element txt1_=tx(_doc,M_59_1);
ad(elt1_,txt1_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element elt4_=el(_doc,OL);
Element elt5_=el(_doc,LI);
Element txt2_=tx(_doc,M_59_2);
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,LI);
Element txt3_=tx(_doc,M_59_3);
ad(elt6_,txt3_);
ad(elt4_,elt6_);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
