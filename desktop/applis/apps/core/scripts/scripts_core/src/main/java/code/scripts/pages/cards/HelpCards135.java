package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards135{
private HelpCards135(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"La donne de belote est al&#233;atoire et param&#233;tr&#233;e par les r&#232;gles.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Il est possible de mettre en pause la partie pour comprendre le fonctionnement de la partie.");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt2_=tx(_doc,"Les diff&#233;rences entre une partie que l''utilisateur joue et une d&#233;monstration sont:");
ad(elt1_,txt2_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"ol");
Element elt8_=el(_doc,"li");
Text txt3_=tx(_doc,"Pas de boutons d''interaction avec la partie.");
ad(elt8_,txt3_);
ad(elt7_,elt8_);
Element elt9_=el(_doc,"li");
Text txt4_=tx(_doc,"Dans la zone de texte, on affiche toutes les informations sur la partie d&#233;mont&#233;e.");
ad(elt9_,txt4_);
ad(elt7_,elt9_);
Element elt10_=el(_doc,"li");
Text txt5_=tx(_doc,"Il n''y a pas de bo&#238;tes de dialogue pour une partie d&#233;monstr&#233;e.");
ad(elt10_,txt5_);
ad(elt7_,elt10_);
Element elt11_=el(_doc,"li");
Text txt6_=tx(_doc,"L''option conseil est toujours d&#233;sactiv&#233;e.");
ad(elt11_,txt6_);
ad(elt7_,elt11_);
Element elt12_=el(_doc,"li");
Text txt7_=tx(_doc,"Il est impossible de sauvegarder la partie de cartes d&#233;montr&#233;e.");
ad(elt12_,txt7_);
ad(elt7_,elt12_);
Element elt13_=el(_doc,"li");
Text txt8_=tx(_doc,"La partie de cartes d&#233;montr&#233;e peut &#234;tre interrompue brusquement.");
ad(elt13_,txt8_);
ad(elt7_,elt13_);
ad(elt1_,elt7_);
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
