package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards125{
private HelpCards125(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Aide au jeu Raccourci F2:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu \"Aide au jeu\" permet d''avoir l''ensemble des cartes jou&#233;es, l''ensemble des r&#233;partitions possibles des cartes pour les autres joueurs, et l''ensemble des cartes certainement poss&#233;d&#233;es par les autres joueurs, en fonction des r&#232;gles des jeux, pour des jeux non solitaires.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt9_=tx(_doc,"Cette action ouvre une bo&#238;te de dialogue.");
ad(elt1_,txt9_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt10_=tx(_doc,"Cette bo&#238;te de dialogue pr&#233;sente l''ensemble des possibilit&#233;s de possession des cartes des joueurs.");
ad(elt1_,txt10_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt12_=tx(_doc,"La lettre P, derri&#232;re un nom de carte, signifie que cette carte est probablement poss&#233;d&#233;e par ce joueur.");
ad(elt1_,txt12_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt14_=tx(_doc,"La lettre C, derri&#232;re un nom de carte, signifie que cette carte est certainement poss&#233;d&#233;e par ce joueur.");
ad(elt1_,txt14_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt16_=tx(_doc,"La lettre J, derri&#232;re un nom de carte, signifie que cette carte est d&#233;j&#224; jou&#233;e.");
ad(elt1_,txt16_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Text txt18_=tx(_doc,"Si aucune des deux lettres n''est plac&#233;e derri&#232;re un nom de carte, alors cette carte est jou&#233;e (&#233;cart&#233;e dans le chien, si preneur; ou dans un pli) ou encore poss&#233;d&#233;e par l''utilisateur.");
ad(elt1_,txt18_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
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
