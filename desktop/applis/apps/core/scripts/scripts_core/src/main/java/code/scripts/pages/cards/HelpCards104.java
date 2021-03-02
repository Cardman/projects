package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards104{
private HelpCards104(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Le tarot se joue avec 78 cartes, de 3 &#224; 5 joueurs.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Il existe diff&#233;rents modes de jeu.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"Nous allons pr&#233;senter le mode de jeu classique qui est le jeu de contrat.");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"Le but, c''est de marquer le plus de points possible.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"Plusieurs variantes (ou modes) existent. A l''origine, ce jeu se jouait &#224; 4 joueurs.");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"Comme beaucoup de jeux de cartes, le tarot se joue carte par carte, les joueurs jouent une carte par tour.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
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
