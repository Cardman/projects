package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards73{
private HelpCards73(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Les contrats d&#233;terminent la partie.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Chaque joueur parle, dans le sens contraire des aiguilles d''une montre,");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"en commen&#231;ant par le joueur plac&#233;, &#224; droite du donneur.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"Un joueur doit soit passer, soit annoncer un contrat.");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt4_=tx(_doc,"A la belote classique, on proc&#232;de en deux tours:");
ad(elt1_,txt4_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt5_=tx(_doc,"Au premier tour:");
ad(elt12_,txt5_);
Element elt13_=el(_doc,"br");
ad(elt12_,elt13_);
Text txt6_=tx(_doc,"&#160;Si un joueur annonce un contrat, alors il prend la carte du dessus et on arr&#234;te les annonces de contrat pour passer au jeu des cartes.");
ad(elt12_,txt6_);
ad(elt11_,elt12_);
Element elt14_=el(_doc,"li");
Text txt7_=tx(_doc,"Au deuxi&#232;me tour:");
ad(elt14_,txt7_);
Element elt15_=el(_doc,"br");
ad(elt14_,elt15_);
Text txt8_=tx(_doc,"&#160;Si un joueur ne passe pas, alors il annonce une couleur qui va dominer les autres durant cette partie.");
ad(elt14_,txt8_);
Element elt16_=el(_doc,"br");
ad(elt14_,elt16_);
Text txt9_=tx(_doc,"&#160;Dans ce cas, il ne peut pas annoncer la couleur de la carte du dessus.");
ad(elt14_,txt9_);
Element elt17_=el(_doc,"br");
ad(elt14_,elt17_);
Text txt10_=tx(_doc,"&#160;Il prend la carte du dessus et on arr&#234;te les annonces de contrat pour passer au jeu des cartes.");
ad(elt14_,txt10_);
ad(elt11_,elt14_);
ad(elt1_,elt11_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt11_=tx(_doc,"Si quelqu''un prend, alors le donneur distribue deux cartes au preneur et trois cartes aux autres joueurs en commen&#231;ant par l''entameur.");
ad(elt1_,txt11_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Text txt12_=tx(_doc,"Remarque: on n''entame le deuxi&#232;me tour que si tout le monde a pass&#233;.");
ad(elt1_,txt12_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt13_=tx(_doc,"A la belote coinch&#233;e, on proc&#232;de de la fa&#231;on suivante:");
ad(elt1_,txt13_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Element elt24_=el(_doc,"br");
ad(elt1_,elt24_);
Element elt25_=el(_doc,"ol");
Element elt26_=el(_doc,"li");
Text txt14_=tx(_doc,"Un joueur peut passer ou annoncer une couleur avec un nombre de points &#224; marquer.");
ad(elt26_,txt14_);
Element elt27_=el(_doc,"br");
ad(elt26_,elt27_);
Text txt15_=tx(_doc,"&#160;Les points annonc&#233;s varient de dix en dix de 80 &#224; 160.");
ad(elt26_,txt15_);
Element elt28_=el(_doc,"br");
ad(elt26_,elt28_);
Text txt16_=tx(_doc,"&#160;Annoncer 162 points signifie que le joueur doit faire tous les plis et commence &#224; jouer.");
ad(elt26_,txt16_);
ad(elt25_,elt26_);
Element elt29_=el(_doc,"li");
Text txt17_=tx(_doc,"Pour monter les ench&#232;res, il faut annoncer une couleur avec dix points de plus.");
ad(elt29_,txt17_);
ad(elt25_,elt29_);
Element elt30_=el(_doc,"li");
Text txt18_=tx(_doc,"M&#234;me en passant sur un tour, il est possible d''annoncer dans un tour suivant une couleur avec des points.");
ad(elt30_,txt18_);
ad(elt25_,elt30_);
Element elt31_=el(_doc,"li");
Text txt19_=tx(_doc,"Si trois joueurs de suite passent les ench&#232;res, alors les ench&#232;res sont finies pour passer au jeu des cartes.");
ad(elt31_,txt19_);
ad(elt25_,elt31_);
ad(elt1_,elt25_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Text txt20_=tx(_doc,"Remarque: si un joueur annonce 162 points pour une couleur, alors le tour d''ench&#232;res se finit, car les autres sont oblig&#233;s de passer.");
ad(elt1_,txt20_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Text txt33_=tx(_doc,"Le preneur joue avec le joueur d''en face contre les deux autres joueurs.");
ad(elt1_,txt33_);
Element elt50_=el(_doc,"br");
ad(elt1_,elt50_);
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
