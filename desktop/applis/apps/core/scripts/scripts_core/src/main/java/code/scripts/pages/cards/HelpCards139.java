package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards139{
private HelpCards139(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Le logiciel poss&#232;de une barre de menus avec cinq menus.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Ces menus sont:");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"Fichier, qui permet de g&#233;rer");
ad(elt5_,txt2_);
Element elt6_=el(_doc,"br");
ad(elt5_,elt6_);
Element elt7_=el(_doc,"ol");
Element elt8_=el(_doc,"li");
Text txt3_=tx(_doc,"les fichiers");
ad(elt8_,txt3_);
ad(elt7_,elt8_);
Element elt9_=el(_doc,"li");
Text txt4_=tx(_doc,"les changements d''&#233;tat du logiciel");
ad(elt9_,txt4_);
ad(elt7_,elt9_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
Element elt10_=el(_doc,"li");
Text txt5_=tx(_doc,"Partie, qui permet de");
ad(elt10_,txt5_);
Element elt11_=el(_doc,"br");
ad(elt10_,elt11_);
Element elt12_=el(_doc,"ol");
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"g&#233;rer une partie");
ad(elt13_,txt6_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"li");
Text txt7_=tx(_doc,"obtenir de l''aide sur la fa&#231;on de jouer pour cette partie");
ad(elt14_,txt7_);
ad(elt12_,elt14_);
Element elt15_=el(_doc,"li");
Text txt8_=tx(_doc,"s''entra&#238;ner &#224; certaines situations de jeu");
ad(elt15_,txt8_);
ad(elt12_,elt15_);
Element elt16_=el(_doc,"li");
Text txt9_=tx(_doc,"regarder les possiblit&#233;s de possession de cartes des autres joueurs");
ad(elt16_,txt9_);
ad(elt12_,elt16_);
Element elt17_=el(_doc,"li");
Text txt10_=tx(_doc,"&#233;diter une partie");
ad(elt17_,txt10_);
ad(elt12_,elt17_);
Element elt18_=el(_doc,"li");
Text txt11_=tx(_doc,"apprendre &#224; jouer via une d&#233;monstration d''une partie");
ad(elt18_,txt11_);
ad(elt12_,elt18_);
ad(elt10_,elt12_);
ad(elt4_,elt10_);
Element elt19_=el(_doc,"li");
Text txt12_=tx(_doc,"Param&#232;tres, qui permet de g&#233;rer");
ad(elt19_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Element elt21_=el(_doc,"ol");
Element elt22_=el(_doc,"li");
Text txt13_=tx(_doc,"les r&#232;gles de jeux de cartes");
ad(elt22_,txt13_);
ad(elt21_,elt22_);
Element elt23_=el(_doc,"li");
Text txt14_=tx(_doc,"le lancement du logiciel");
ad(elt23_,txt14_);
ad(elt21_,elt23_);
Element elt24_=el(_doc,"li");
Text txt15_=tx(_doc,"les pseudonymes des joueurs");
ad(elt24_,txt15_);
ad(elt21_,elt24_);
Element elt25_=el(_doc,"li");
Text txt16_=tx(_doc,"la temporisation des jeux");
ad(elt25_,txt16_);
ad(elt21_,elt25_);
Element elt26_=el(_doc,"li");
Text txt17_=tx(_doc,"l''interaction entre la fl&#232;che de la souris et les cartes");
ad(elt26_,txt17_);
ad(elt21_,elt26_);
ad(elt19_,elt21_);
ad(elt4_,elt19_);
Element elt27_=el(_doc,"li");
Text txt18_=tx(_doc,"Aide, qui donne de l''aide");
ad(elt27_,txt18_);
ad(elt4_,elt27_);
ad(elt1_,elt4_);
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
