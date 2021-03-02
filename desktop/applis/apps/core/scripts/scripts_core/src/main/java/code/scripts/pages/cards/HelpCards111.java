package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards111{
private HelpCards111(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Quitter (raccourci: CTRL + W)");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action de quitter permet d''arr&#234;ter le programme.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"Si une partie est en train d''&#234;tre jou&#233;e, et si la partie ne vient pas d''&#234;tre sauvegard&#233;e, alors");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"un message d''avertissement pour la partie en cours appara&#238;t.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"Dans ce cas, l''utilisateur a le choix entre trois options:");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"ol");
Element elt9_=el(_doc,"li");
Text txt5_=tx(_doc,"Oui");
ad(elt9_,txt5_);
ad(elt8_,elt9_);
Element elt10_=el(_doc,"li");
Text txt6_=tx(_doc,"Non");
ad(elt10_,txt6_);
ad(elt8_,elt10_);
Element elt11_=el(_doc,"li");
Text txt7_=tx(_doc,"Annuler");
ad(elt11_,txt7_);
ad(elt8_,elt11_);
ad(elt1_,elt8_);
Text txt8_=tx(_doc,"Si l''utilisateur a choisi oui, alors une bo&#238;te de dialogue pour sauvegarder appara&#238;t et");
ad(elt1_,txt8_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt9_=tx(_doc,"Celui-ci doit donner un nom &#224; la partie en train d''&#234;tre jou&#233;e.");
ad(elt1_,txt9_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt10_=tx(_doc,"Si l''utilisateur a choisi non, alors il y a fermeture du programme.");
ad(elt1_,txt10_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt11_=tx(_doc,"Si l''utilisateur a choisi annuler, alors rien ne se passe.");
ad(elt1_,txt11_);
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
