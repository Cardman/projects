package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards140{
private HelpCards140(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Vous trouverez toutes les fonctionnalites du logiciel.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Le logiciel poss&#232;de une barre de menus avec cinq menus.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"Lors du premier lancement du logiciel vous avez devant");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"vous les boutons \"Mode solo\" et \"Mode multi joueurs\".");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt4_=tx(_doc,"C''est le lancement par d&#233;faut.");
ad(elt1_,txt4_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt5_=tx(_doc,"En ayant choisi un des deux modes, vous choisissez en suite le jeu auquel vous voulez jouer.");
ad(elt1_,txt5_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt6_=tx(_doc,"De plus, des bo&#238;tes de dialogues sont accessibles par certains sous menus.");
ad(elt1_,txt6_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt7_=tx(_doc,"Pour jouer en \"Mode multi joueurs\", vous devez choisir le nombre de joueurs puis cr&#233;er un serveur qui va permettre &#224; d''autres personnes de jouer avec vous.");
ad(elt1_,txt7_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt8_=tx(_doc,"Il faudra que vous donniez votre adresse IP aux autres joueurs.");
ad(elt1_,txt8_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt9_=tx(_doc,"En \"Mode multi joueurs\", chaque personne doit choisir sa place avant de commencer.");
ad(elt1_,txt9_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt10_=tx(_doc,"Il n''est possible de voir toutes les informations de la partie en r&#233;seau que si tous les joueurs sont dans la m&#234;me &#233;quipe pour &#233;viter de la tricherie.");
ad(elt1_,txt10_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt11_=tx(_doc,"D''autres personnes peuvent se joindre apr&#232;s la fin de la partie.");
ad(elt1_,txt11_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
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
