package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards109{
private HelpCards109(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Changer de jeu (raccourci: CTRL + J):");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu Changer de jeu permet de changer d''\"&#233;tat du logiciel\", en revenant au menu de lancement du logiciel par d&#233;faut, c''est &#224; dire celui qui contient les noms des jeux.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt4_=tx(_doc,"Si une partie est en train d''&#234;tre jou&#233;e, et si la partie ne vient pas d''&#234;tre sauvegard&#233;e, alors un message d''avertissement pour la partie en cours appara&#238;t.");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt6_=tx(_doc,"Dans ce cas, l''utilisateur a le choix entre trois options:");
ad(elt1_,txt6_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt7_=tx(_doc,"Oui");
ad(elt12_,txt7_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,"li");
Text txt8_=tx(_doc,"Non");
ad(elt13_,txt8_);
ad(elt11_,elt13_);
Element elt14_=el(_doc,"li");
Text txt9_=tx(_doc,"Annuler");
ad(elt14_,txt9_);
ad(elt11_,elt14_);
ad(elt1_,elt11_);
Text txt10_=tx(_doc,"Si l''utilisateur a choisi oui, alors une bo&#238;te de dialogue pour sauvegarder appara&#238;t et celui-ci doit donner un nom &#224; la partie en train d''&#234;tre jou&#233;e.");
ad(elt1_,txt10_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt12_=tx(_doc,"Si l''utilisateur a choisi non, alors il y a changement d''\"&#233;tat du logiciel\".");
ad(elt1_,txt12_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt13_=tx(_doc,"Si l''utilisateur a choisi annuler, alors rien ne se passe.");
ad(elt1_,txt13_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
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
