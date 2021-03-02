package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards80{
private HelpCards80(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Comme dans tous les jeux de cartes, il existe des variantes.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"En voici quelques unes:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"ol");
Element elt7_=el(_doc,"li");
Text txt2_=tx(_doc,"On peut changer les r&#232;gles de coupe:");
ad(elt7_,txt2_);
Element elt8_=el(_doc,"br");
ad(elt7_,elt8_);
Element elt9_=el(_doc,"ol");
Element elt10_=el(_doc,"li");
Text txt3_=tx(_doc,"Le joueur est oblig&#233; de sous-couper (Mettre un atout en-dessous) sur un adversaire si possible, lorsque la couleur demand&#233;e d''un pli n''est pas la couleur de l''atout.");
ad(elt10_,txt3_);
ad(elt9_,elt10_);
Element elt11_=el(_doc,"li");
Text txt4_=tx(_doc,"Le joueur est oblig&#233; de sous-couper et surcouper (Mettre un atout au-dessus) sur le partenaire si possible, lorsque la couleur demand&#233;e d''un pli n''est pas la couleur de l''atout.");
ad(elt11_,txt4_);
ad(elt9_,elt11_);
Element elt12_=el(_doc,"li");
Text txt5_=tx(_doc,"Le joueur est oblig&#233; de surcouper sur le partenaire si possible, mais pas de sous-couper, lorsque la couleur demand&#233;e d''un pli n''est pas la couleur de l''atout.");
ad(elt12_,txt5_);
ad(elt9_,elt12_);
ad(elt7_,elt9_);
ad(elt6_,elt7_);
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"Jeu avec les surcontrats sans atout et tout atout.");
ad(elt13_,txt6_);
ad(elt6_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt6_,elt14_);
Element elt15_=el(_doc,"li");
Text txt7_=tx(_doc,"Fin de partie diff&#233;rente.");
ad(elt15_,txt7_);
ad(elt6_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt6_,elt16_);
Element elt17_=el(_doc,"li");
Text txt8_=tx(_doc,"Les cartes peuvent &#234;tre distribu&#233;es dans le sens des aiguilles d''une montre.");
ad(elt17_,txt8_);
ad(elt6_,elt17_);
Element elt18_=el(_doc,"br");
ad(elt6_,elt18_);
Element elt19_=el(_doc,"li");
Text txt9_=tx(_doc,"Les cartes peuvent ne pas &#234;tre battues du tout.");
ad(elt19_,txt9_);
ad(elt6_,elt19_);
Element elt20_=el(_doc,"br");
ad(elt6_,elt20_);
Element elt21_=el(_doc,"li");
Text txt10_=tx(_doc,"Les cartes peuvent n''&#234;tre battues qu''une seule fois.");
ad(elt21_,txt10_);
ad(elt6_,elt21_);
ad(elt1_,elt6_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt11_=tx(_doc,"On verra en d&#233;tail les variantes num&#233;ro 2 et 3.");
ad(elt1_,txt11_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
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
