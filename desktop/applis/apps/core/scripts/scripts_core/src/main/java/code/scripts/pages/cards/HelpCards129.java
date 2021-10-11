package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards129{
private HelpCards129(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Tarot");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action d''un sous menu du sous menu Editer permet de construire une partie quelconque de tarot pour que l''utilisateur teste sa fa&#231;on de jouer et que l''utilisateur signale &#224; l''auteur des erreurs de jouerie.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt4_=tx(_doc,"Cette action ouvre une bo&#238;te de dialogue.");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"La bo&#238;te de dialogue se pr&#233;sente sous deux diff&#233;rentes formes:");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"ol");
Element elt11_=el(_doc,"li");
Text txt6_=tx(_doc,"Choix du nombre de joueurs et des r&#232;gles du tarot avant ouverture.");
ad(elt11_,txt6_);
ad(elt10_,elt11_);
Element elt12_=el(_doc,"li");
Text txt7_=tx(_doc,"Choix de la distribution des cartes.");
ad(elt12_,txt7_);
ad(elt10_,elt12_);
ad(elt1_,elt10_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt8_=tx(_doc,"&#160;Le choix des r&#232;gles du tarot, pour les variantes, influent sur la distribution des cartes (nombre de mains, nombre de cartes). Les variantes sont d&#233;crites dans le descriptif du tarot.");
ad(elt1_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt10_=tx(_doc,"&#160;Apr&#232;s avoir distribu&#233; correctement les cartes, (Toutes mains compl&#232;tes au tarot) l''utilisateur peut sauvegarder la partie dans un fichier et &#233;ventuellement la jouer.");
ad(elt1_,txt10_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
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
