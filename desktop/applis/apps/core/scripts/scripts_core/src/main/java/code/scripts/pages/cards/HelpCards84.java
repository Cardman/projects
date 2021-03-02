package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards84{
private HelpCards84(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Le pr&#233;sident est jou&#233; par groupes de cartes de m&#234;me force.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Un pli est un ensemble de cartes gagn&#233; par un joueur ayant jou&#233; le groupe de cartes le plus fort qui est jou&#233; en dernier.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"Si le joueur ayant gagn&#233; un pli a encore des cartes, ce joueur comme le pli suivant.(Exemple: le joueur ayant jou&#233; un 2 commence le pli suivant.).");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"Si le joueur ayant gagn&#233; un pli a fini, le joueur suivant qui a des cartes commence le pli suivant.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"Le joueur qui commence un pli est l''entameur.");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"Le joueur qui a jou&#233; en dernier le groupe de cartes le plus fort d''un pli est le ramasseur du pli, ce joueur gagne le pli.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"ol");
Element elt11_=el(_doc,"li");
Text txt6_=tx(_doc,"Le premier groupe de cartes d''un pli");
ad(elt11_,txt6_);
Element elt12_=el(_doc,"br");
ad(elt11_,elt12_);
Text txt7_=tx(_doc,"Le joueur &#224; droite du donneur commence &#224; jouer. Ce joueur peut jouer n''importe quel groupe de cartes de m&#234;me force.");
ad(elt11_,txt7_);
ad(elt10_,elt11_);
Element elt13_=el(_doc,"li");
Text txt8_=tx(_doc,"R&#232;gles");
ad(elt13_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt13_,elt14_);
Element elt15_=el(_doc,"ol");
Element elt16_=el(_doc,"li");
Text txt9_=tx(_doc,"Le joueur peut passer ou jouer des cartes plus fortes que les derni&#232;res cartes jou&#233;es. Les cartes d''un m&#234;me groupe doivent &#234;tre de m&#234;me force.");
ad(elt16_,txt9_);
ad(elt15_,elt16_);
Element elt17_=el(_doc,"li");
Text txt10_=tx(_doc,"De plus, pour jouer, le joueur doit utiliser le  player must use the m&#234;me nombre de cartes que le nombre de cartes jou&#233;es par l''entameur.");
ad(elt17_,txt10_);
ad(elt15_,elt17_);
Element elt18_=el(_doc,"li");
Text txt11_=tx(_doc,"Un joueur qui a pass&#233; ne peut pas jouer pendant le pli en cours.");
ad(elt18_,txt11_);
ad(elt15_,elt18_);
Element elt19_=el(_doc,"li");
Text txt12_=tx(_doc,"Si, au plus, un joueur n''a pas pass&#233;, le pli est fini. L''entameur devient:");
ad(elt19_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Element elt21_=el(_doc,"ol");
Element elt22_=el(_doc,"li");
Text txt13_=tx(_doc,"le joueur qui a jou&#233; le dernier groupe de cartes, si ce joueur a des cartes.");
ad(elt22_,txt13_);
ad(elt21_,elt22_);
Element elt23_=el(_doc,"li");
Text txt14_=tx(_doc,"sinon le joueur suivant, qui a des cartes, apr&#232;s le joueur qui a jou&#233; le dernier groupe de cartes.");
ad(elt23_,txt14_);
ad(elt21_,elt23_);
ad(elt19_,elt21_);
ad(elt15_,elt19_);
ad(elt13_,elt15_);
ad(elt10_,elt13_);
ad(elt1_,elt10_);
ad(elt0_,elt1_);
_doc.appendChild(elt0_);
}
static Attr at(String _name,String _value){
return CoreDocument.createAttribute(_name,_value);
}
static void at(Element _elt,CustList<Attr> _ls){
_elt.setAttributes(new NamedNodeMap(_ls));
}
static CustList<Attr> al(int _len){
return new CustList<Attr>(new CollCapacity(_len));
}
static Text tx(Document _doc,String _value){
return _doc.createEscapedTextNode(_value);
}
static Element el(Document _doc,String _value){
return _doc.createElement(_value);
}
static void ad(Element _elt,Node _value){
_elt.appendChild(_value);
}
}
