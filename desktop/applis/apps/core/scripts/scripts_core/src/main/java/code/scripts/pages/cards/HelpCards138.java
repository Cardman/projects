package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards138{
private HelpCards138(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Le menu Partie poss&#232;de neuf sous menus qui sont:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"ol");
Element elt4_=el(_doc,"li");
Text txt1_=tx(_doc,"Conseil (Raccourci F1), accessible en cours de partie lorsque l''utilisateur doit interagir dans une partie non solitaire (avec une intelligence artificielle)");
ad(elt4_,txt1_);
ad(elt3_,elt4_);
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"Pause (Raccourci PAUSE), accessible en cours de partie pour des parties non solitaires (avec une intelligence artificielle)");
ad(elt5_,txt2_);
ad(elt3_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"Aide au jeu (Raccourci F2), accessible en cours de partie lorsque l''utilisateur doit interagir dans une partie non solitaire (avec une intelligence artificielle)");
ad(elt6_,txt3_);
ad(elt3_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"Mains et plis, accessible en cours de partie lorsque l''utilisateur doit interagir dans une partie non solitaire (avec une intelligence artificielle)");
ad(elt7_,txt4_);
ad(elt3_,elt7_);
Element elt8_=el(_doc,"li");
Text txt5_=tx(_doc,"Equipes, accessible en cours de partie lorsque l''utilisateur doit interagir dans une partie non solitaire (avec une intelligence artificielle)");
ad(elt8_,txt5_);
ad(elt3_,elt8_);
Element elt9_=el(_doc,"li");
Text txt6_=tx(_doc,"Editer (Raccourci CTRL + E), accessible n''importe quand");
ad(elt9_,txt6_);
ad(elt3_,elt9_);
Element elt10_=el(_doc,"li");
Text txt7_=tx(_doc,"Demo, accessible n''importe quand");
ad(elt10_,txt7_);
ad(elt3_,elt10_);
Element elt11_=el(_doc,"li");
Text txt8_=tx(_doc,"Entrainement contenant des sous menus");
ad(elt11_,txt8_);
ad(elt3_,elt11_);
Element elt12_=el(_doc,"li");
Text txt9_=tx(_doc,"Arr&#234;ter de jouer en r&#233;seau");
ad(elt12_,txt9_);
ad(elt3_,elt12_);
ad(elt1_,elt3_);
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
