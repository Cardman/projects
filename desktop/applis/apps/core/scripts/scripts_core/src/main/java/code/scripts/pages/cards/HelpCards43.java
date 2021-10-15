package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards43 extends HelpCardsCommon{

private HelpCards43(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,HTML);
Element elt1_=el(_doc,BODY);
Element txt0_=tx(_doc,M_43_0);
ad(elt1_,txt0_);
Element elt2_=el(_doc,BR);
ad(elt1_,elt2_);
Element elt3_=el(_doc,BR);
ad(elt1_,elt3_);
Element txt1_=tx(_doc,M_43_1);
ad(elt1_,txt1_);
Element elt4_=el(_doc,BR);
ad(elt1_,elt4_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
}
