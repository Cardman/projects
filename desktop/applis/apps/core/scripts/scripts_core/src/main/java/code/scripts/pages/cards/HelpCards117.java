package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards117{
private HelpCards117(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Belote raccourci MAJ + B");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu \"Belote\" permet de changer les variantes de jeu pour la belote.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"Cette action met en place une bo&#238;te de dialogue.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"La bo&#238;te de dialogue permet de mettre en place des variantes.");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"Ceci ne change pas les r&#232;gles du jeu de l''&#233;ventuelle partie jou&#233;e.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"On change les r&#232;gles du jeu de la belote &#224; partir de la prochaine partie de cartes al&#233;atoire ou d''entra&#238;nement.");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt6_=tx(_doc,"On ne change pas les r&#232;gles du jeu de la belote lors d''un tournoi.");
ad(elt1_,txt6_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt7_=tx(_doc,"Le bouton, dont le nom est \"Valider\", sert &#224; valider les modifications.");
ad(elt1_,txt7_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt8_=tx(_doc,"Si l''utilisateur a cliqu&#233; sur la croix rouge en haut &#224; droite de la fen&#234;tre,");
ad(elt1_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt9_=tx(_doc,"alors aucune modification n''est faite et la bo&#238;te de dialogue se ferme.");
ad(elt1_,txt9_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
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
