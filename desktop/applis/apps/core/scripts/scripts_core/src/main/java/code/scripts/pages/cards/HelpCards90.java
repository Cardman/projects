package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards90{
private HelpCards90(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Un chelem est une annonce o&#249;, l''annonceur (le joueur qui l''a annonc&#233;) doit faire tous les plis, c''est-&#224;-dire ramasser les cartes de ses adversaires.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"Pour pouvoir l''annoncer, il faut demander un contrat sup&#233;rieur ou &#233;gal &#224; la Petite.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"ol");
Element elt6_=el(_doc,"li");
Text txt2_=tx(_doc,"Si le preneur a demand&#233; une petite ou une garde:");
ad(elt6_,txt2_);
Element elt7_=el(_doc,"br");
ad(elt6_,elt7_);
Text txt3_=tx(_doc,"&#160;&#160;Le chien est d&#233;voil&#233; &#224; tous les joueurs.");
ad(elt6_,txt3_);
Element elt8_=el(_doc,"br");
ad(elt6_,elt8_);
Text txt4_=tx(_doc,"&#160;&#160;Le preneur ramasse, ensuite, le chien.");
ad(elt6_,txt4_);
Element elt9_=el(_doc,"br");
ad(elt6_,elt9_);
Text txt5_=tx(_doc,"&#160;&#160;Il retire alors de son jeu le m&#234;me nombre de cartes que le chien contient.");
ad(elt6_,txt5_);
Element elt10_=el(_doc,"br");
ad(elt6_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt6_,elt11_);
Text txt6_=tx(_doc,"&#160;&#160;Le preneur ne peut pas retirer d''atouts(L''Excuse est, ici, un atout.) ou de rois.");
ad(elt6_,txt6_);
Element elt12_=el(_doc,"br");
ad(elt6_,elt12_);
Text txt7_=tx(_doc,"&#160;&#160;Pour les atouts, si le preneur a un total d''atouts(L''Excuse est, ici, un atout.) et de rois(atouts + rois)sup&#233;rieur au nombre de cartes par joueur,");
ad(elt6_,txt7_);
Element elt13_=el(_doc,"br");
ad(elt6_,elt13_);
Text txt8_=tx(_doc,"&#160;&#160;alors il montre &#224; la d&#233;fense les atouts qu''il veut &#233;carter,");
ad(elt6_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt6_,elt14_);
Text txt9_=tx(_doc,"&#160;&#160;dans ce cas, il peut enfin les retirer de son jeu.");
ad(elt6_,txt9_);
Element elt15_=el(_doc,"br");
ad(elt6_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt6_,elt16_);
Text txt10_=tx(_doc,"&#160;&#160;Les cartes retir&#233;es de son jeu sont face cach&#233;e pour la d&#233;fense.");
ad(elt6_,txt10_);
Element elt17_=el(_doc,"br");
ad(elt6_,elt17_);
Text txt11_=tx(_doc,"&#160;&#160;Apr&#232;s &#233;cart de cartes dans le chien, le preneur peut annoncer un chelem.");
ad(elt6_,txt11_);
Element elt18_=el(_doc,"br");
ad(elt6_,elt18_);
ad(elt5_,elt6_);
Element elt19_=el(_doc,"li");
Text txt12_=tx(_doc,"Si le preneur a demand&#233; une garde sans ou une garde contre:");
ad(elt19_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Text txt13_=tx(_doc,"&#160;&#160;Le chien n''est pas d&#233;voil&#233;.");
ad(elt19_,txt13_);
Element elt21_=el(_doc,"br");
ad(elt19_,elt21_);
Text txt14_=tx(_doc,"&#160;&#160;Si le preneur a demand&#233; une garde sans, alors le chien appartient au preneur.");
ad(elt19_,txt14_);
Element elt22_=el(_doc,"br");
ad(elt19_,elt22_);
Text txt15_=tx(_doc,"&#160;&#160;Si le preneur a demand&#233; une garde contre, alors le chien appartient &#224; la d&#233;fense.");
ad(elt19_,txt15_);
Element elt23_=el(_doc,"br");
ad(elt19_,elt23_);
Text txt16_=tx(_doc,"&#160;&#160;Le preneur peut annoncer un chelem.");
ad(elt19_,txt16_);
Element elt24_=el(_doc,"br");
ad(elt19_,elt24_);
ad(elt5_,elt19_);
ad(elt1_,elt5_);
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
