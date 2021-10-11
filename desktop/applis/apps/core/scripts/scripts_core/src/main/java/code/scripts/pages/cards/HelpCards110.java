package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards110{
private HelpCards110(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Charger une partie (raccourci: CTRL + O):");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Le sous menu \"Charger une partie\" ouvre une bo&#238;te de dialogue permettant de charger n''importe quelle partie depuis cette bo&#238;te.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt3_=tx(_doc,"Si une partie est en train d''&#234;tre jou&#233;e, et si la partie ne vient pas d''&#234;tre sauvegard&#233;e, alors un message d''avertissement pour la partie en cours appara&#238;t.");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt5_=tx(_doc,"Dans ce cas, l''utilisateur a le choix entre trois options:");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"ol");
Element elt11_=el(_doc,"li");
Text txt6_=tx(_doc,"Oui");
ad(elt11_,txt6_);
ad(elt10_,elt11_);
Element elt12_=el(_doc,"li");
Text txt7_=tx(_doc,"Non");
ad(elt12_,txt7_);
ad(elt10_,elt12_);
Element elt13_=el(_doc,"li");
Text txt8_=tx(_doc,"Annuler");
ad(elt13_,txt8_);
ad(elt10_,elt13_);
ad(elt1_,elt10_);
Text txt9_=tx(_doc,"Si l''utilisateur a choisi oui, alors une bo&#238;te de dialogue pour sauvegarder appara&#238;t et celui-ci doit donner un nom &#224; la partie en train d''&#234;tre jou&#233;e.");
ad(elt1_,txt9_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt11_=tx(_doc,"Si l''utilisateur a choisi non, alors il y a ouverture d''une bo&#238;te de dialogue de chargement.");
ad(elt1_,txt11_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt13_=tx(_doc,"Si l''utilisateur a choisi annuler, alors rien ne se passe.");
ad(elt1_,txt13_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Text txt14_=tx(_doc,"L''utilisateur peut alors jouer ou rejouer une partie sauvegard&#233;e dans un fichier. De plus, si la partie est &#233;dit&#233;e, il peut encha&#238;ner les donnes suivantes s''il a choisi de jouer plusieurs d''affil&#233;.");
ad(elt1_,txt14_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt18_=tx(_doc,"Les dossiers seront regroup&#233;s sur une arborescence graphique &#224; gauche de la bo&#238;te de dialogue.");
ad(elt1_,txt18_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Text txt19_=tx(_doc,"Leur contenu sera plac&#233; &#224; droite de la bo&#238;te de dialogue, comme pour Explorateur Windows.");
ad(elt1_,txt19_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Text txt20_=tx(_doc,"Le bouton \"Annuler\" ou la croix rouge en haut &#224; droite permettent de fermer la bo&#238;te de dialogue.");
ad(elt1_,txt20_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Text txt21_=tx(_doc,"A l''installation de la bo&#238;te de dialogue, le tri est croissant pour tous les attributs de fichiers cit&#233;s.");
ad(elt1_,txt21_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Text txt22_=tx(_doc,"Si l''utilisateur clique sur un fichier de la liste &#233;num&#233;r&#233;e des fichiers, alors la bo&#238;te de dialogue se ferme.");
ad(elt1_,txt22_);
Element elt35_=el(_doc,"br");
ad(elt1_,elt35_);
Text txt23_=tx(_doc,"Ensuite:");
ad(elt1_,txt23_);
Element elt36_=el(_doc,"br");
ad(elt1_,elt36_);
Element elt37_=el(_doc,"ol");
Element elt38_=el(_doc,"li");
Text txt24_=tx(_doc,"Si le fichier est accessible en lecture, alors la partie (Que ce soit une simple partie ou un ensemble, dans le cas o&#249; c''est un ensemble, la partie charg&#233;e est la derni&#232;re jou&#233;e.), contenue dans ce fichier, sera charg&#233;e; sinon un message d''erreur appara&#238;t indiquant l''&#233;chec du chargement..");
ad(elt38_,txt24_);
Element elt39_=el(_doc,"br");
ad(elt38_,elt39_);
ad(elt37_,elt38_);
ad(elt1_,elt37_);
Text txt27_=tx(_doc,"Les donnes sont v&#233;rifi&#233;es au niveau des r&#232;gles pour &#233;viter les probl&#232;mes.");
ad(elt1_,txt27_);
Element elt42_=el(_doc,"br");
ad(elt1_,elt42_);
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
