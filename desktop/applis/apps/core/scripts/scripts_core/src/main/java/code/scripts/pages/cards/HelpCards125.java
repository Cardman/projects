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
Text txt0_=tx(_doc,"Aide au jeu Raccourci F2");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu \"Aide au jeu\" permet");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"d''avoir l''ensemble des cartes jou&#233;es,");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"l''ensemble des r&#233;partitions possibles des cartes");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt4_=tx(_doc,"pour les autres joueurs,");
ad(elt1_,txt4_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt5_=tx(_doc,"et l''ensemble des cartes certainement");
ad(elt1_,txt5_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt6_=tx(_doc,"poss&#233;d&#233;es par les autres joueurs,");
ad(elt1_,txt6_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt7_=tx(_doc,"en fonction des r&#232;gles des jeux,");
ad(elt1_,txt7_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt8_=tx(_doc,"pour des jeux non solitaires.");
ad(elt1_,txt8_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt9_=tx(_doc,"Cette action ouvre une bo&#238;te de dialogue.");
ad(elt1_,txt9_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt10_=tx(_doc,"Cette bo&#238;te de dialogue pr&#233;sente");
ad(elt1_,txt10_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt11_=tx(_doc,"l''ensemble des possibilit&#233;s de possession des cartes des joueurs.");
ad(elt1_,txt11_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt12_=tx(_doc,"La lettre P, derri&#232;re un nom de carte, signifie");
ad(elt1_,txt12_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt13_=tx(_doc,"que cette carte est probablement poss&#233;d&#233;e par ce joueur.");
ad(elt1_,txt13_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt14_=tx(_doc,"La lettre C, derri&#232;re un nom de carte, signifie");
ad(elt1_,txt14_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt15_=tx(_doc,"que cette carte est certainement poss&#233;d&#233;e par ce joueur.");
ad(elt1_,txt15_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt16_=tx(_doc,"La lettre J, derri&#232;re un nom de carte, signifie");
ad(elt1_,txt16_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Text txt17_=tx(_doc,"que cette carte est d&#233;j&#224; jou&#233;e.");
ad(elt1_,txt17_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt18_=tx(_doc,"Si aucune des deux lettres n''est plac&#233;e derri&#232;re un nom de carte,");
ad(elt1_,txt18_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt19_=tx(_doc,"alors cette carte est jou&#233;e (&#233;cart&#233;e dans le chien, si preneur; ou dans un pli)");
ad(elt1_,txt19_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Text txt20_=tx(_doc,"ou encore poss&#233;d&#233;e par l''utilisateur.");
ad(elt1_,txt20_);
Element elt24_=el(_doc,"br");
ad(elt1_,elt24_);
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
