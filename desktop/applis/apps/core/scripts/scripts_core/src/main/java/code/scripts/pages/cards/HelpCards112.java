package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards112{
private HelpCards112(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Sauvegarder une partie (raccourci: CTRL + S)");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu Sauvegarder une partie met en place une bo&#238;te de dialogue.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"Ce sous menu permet de sauvegarder une partie de cartes en train d''&#234;tre");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"jou&#233;e d&#232;s que possible.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt4_=tx(_doc,"L''utilisateur peut alors reprendre cette partie apr&#232;s. De plus, s''il encha&#238;ne un \"TriaCartes\",");
ad(elt1_,txt4_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt5_=tx(_doc,"il peut sauvegarder la partie en train d''&#234;tre jou&#233;e.");
ad(elt1_,txt5_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt6_=tx(_doc,"La bo&#238;te de dialogue place le chemin relatif du dossier pour sauvegarder en tenant compte de la partie en train d''&#234;tre jou&#233;e ou &#233;dit&#233;e.");
ad(elt1_,txt6_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt7_=tx(_doc,"On peut trier les fichiers:");
ad(elt1_,txt7_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt8_=tx(_doc,"par ordre alphab&#233;tique du nom de fichier.");
ad(elt12_,txt8_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,"li");
Text txt9_=tx(_doc,"par ordre de date modification du fichier.");
ad(elt13_,txt9_);
ad(elt11_,elt13_);
Element elt14_=el(_doc,"li");
Text txt10_=tx(_doc,"par ordre de taille du fichier.");
ad(elt14_,txt10_);
ad(elt11_,elt14_);
ad(elt1_,elt11_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt11_=tx(_doc,"A l''installation de la bo&#238;te de dialogue, le tri est croissant pour tous les attributs de fichiers cit&#233;s.");
ad(elt1_,txt11_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt12_=tx(_doc,"L''utilisateur ne sera pas oblig&#233; de rajouter l''extension du fichier apr&#232;s son nom.");
ad(elt1_,txt12_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt13_=tx(_doc,"L''utilisateur, pour sauvegarder la partie, apr&#232;s avoir entr&#233; un nom,");
ad(elt1_,txt13_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Text txt14_=tx(_doc,"pourra soit cliquer sur le bouton \"Sauvegarder\", soit taper sur la touche \"Entree\".");
ad(elt1_,txt14_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt15_=tx(_doc,"Le bouton \"Annuler\" ou la croix rouge en haut &#224; droite permettent de fermer la bo&#238;te de dialogue.");
ad(elt1_,txt15_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Element elt24_=el(_doc,"br");
ad(elt1_,elt24_);
Text txt16_=tx(_doc,"&#160;Le symbole \"<\" plac&#233; &#224; droite de \"Nom\" ou de \"Date\" ou de \"Taille\"");
ad(elt1_,txt16_);
Element elt25_=el(_doc,"br");
ad(elt1_,elt25_);
Text txt17_=tx(_doc,"&#160;permet de dire que l''ordre de tri est croissant, pour l''attribut de fichier correspondant. (U0>=U1, pour une suite, cf Maths)");
ad(elt1_,txt17_);
Element elt26_=el(_doc,"br");
ad(elt1_,elt26_);
Text txt18_=tx(_doc,"&#160;Le symbole \">\" plac&#233; &#224; droite de \"Nom\" ou de \"Date\" ou de \"Taille\"");
ad(elt1_,txt18_);
Element elt27_=el(_doc,"br");
ad(elt1_,elt27_);
Text txt19_=tx(_doc,"&#160;permet de dire que l''ordre de tri est d&#233;croissant, pour l''attribut de fichier correspondant. (U0>=U1, pour une suite, cf Maths)");
ad(elt1_,txt19_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Text txt20_=tx(_doc,"Si l''utilisateur tente d''utiliser les caract&#232;res < > ? \" * / \\ | : ., le saut de ligne, les tabulations, alors un message d''erreur appara&#238;t.");
ad(elt1_,txt20_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Text txt21_=tx(_doc,"Si l''utilisateur tente de cr&#233;er une sauvegarde ne contenant que des espaces (Sur Windows), alors un message d''erreur appara&#238;t.");
ad(elt1_,txt21_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Text txt22_=tx(_doc,"Si l''utilisateur tente de sauvegarder dans un fichier dont le nom existe d&#233;j&#224;, alors un message d''avertissement appara&#238;t.");
ad(elt1_,txt22_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
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
