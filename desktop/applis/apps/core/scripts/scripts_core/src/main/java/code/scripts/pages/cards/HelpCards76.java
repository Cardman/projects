package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards76{
private HelpCards76(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"La belote se joue carte par carte.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"On appelle pli, un ensemble de cartes remport&#233; par un joueur ayant jou&#233; la plus forte carte. Le joueur ayant fait un pli b&#233;n&#233;ficie de l''entame suivante.(Exemple: le joueur ayant mis le valet de la couleur d''atout, b&#233;n&#233;ficie de l''entame suivante.).");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"Le joueur qui entame est dit entameur.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt3_=tx(_doc,"Le joueur qui a jou&#233; la plus forte carte d''un pli est dit le ramasseur, il ramasse le pli.");
ad(elt1_,txt3_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"On appelle couleur demand&#233;e la couleur de la carte entam&#233;e.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"On dit qu''un pli est coup&#233; s''il existe au moins un atout.");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt6_=tx(_doc,"L''entame");
ad(elt12_,txt6_);
Element elt13_=el(_doc,"br");
ad(elt12_,elt13_);
Text txt7_=tx(_doc,"&#160;&#160;Le joueur &#224; droite du donneur commence &#224; jouer une carte de son choix.");
ad(elt12_,txt7_);
ad(elt11_,elt12_);
Element elt14_=el(_doc,"li");
Text txt8_=tx(_doc,"Les r&#232;gles");
ad(elt14_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt14_,elt15_);
Element elt16_=el(_doc,"ol");
Element elt17_=el(_doc,"li");
Text txt9_=tx(_doc,"Si la couleur demand&#233;e est la couleur d''atout,");
ad(elt17_,txt9_);
Element elt18_=el(_doc,"br");
ad(elt17_,elt18_);
Text txt10_=tx(_doc,"&#160;&#160;&#160;Si le joueur poss&#232;de de l''atout,");
ad(elt17_,txt10_);
Element elt19_=el(_doc,"br");
ad(elt17_,elt19_);
Text txt11_=tx(_doc,"&#160;&#160;&#160;&#160;Si ce dernier peut monter (mettre un atout sup&#233;rieur),");
ad(elt17_,txt11_);
Element elt20_=el(_doc,"br");
ad(elt17_,elt20_);
Text txt12_=tx(_doc,"&#160;&#160;&#160;&#160;&#160;alors il doit mettre un atout sup&#233;rieur; on dit qu''il monte.");
ad(elt17_,txt12_);
Element elt21_=el(_doc,"br");
ad(elt17_,elt21_);
Text txt13_=tx(_doc,"&#160;&#160;&#160;&#160;Sinon, il fournit de l''atout, n''importe lequel,");
ad(elt17_,txt13_);
Element elt22_=el(_doc,"br");
ad(elt17_,elt22_);
Text txt14_=tx(_doc,"&#160;&#160;&#160;sinon il joue la carte de son choix.");
ad(elt17_,txt14_);
ad(elt16_,elt17_);
Element elt23_=el(_doc,"li");
Text txt15_=tx(_doc,"Si la couleur demand&#233;e n''est pas atout,");
ad(elt23_,txt15_);
Element elt24_=el(_doc,"br");
ad(elt23_,elt24_);
Element elt25_=el(_doc,"ol");
Element elt26_=el(_doc,"li");
Text txt16_=tx(_doc,"Si le joueur poss&#232;de des cartes de m&#234;me couleur que celle de la carte entam&#233;e,");
ad(elt26_,txt16_);
Element elt27_=el(_doc,"br");
ad(elt26_,elt27_);
Text txt17_=tx(_doc,"alors celui-ci doit fournir &#224; la couleur sans &#234;tre oblig&#233; de monter.");
ad(elt26_,txt17_);
Element elt28_=el(_doc,"br");
ad(elt26_,elt28_);
ad(elt25_,elt26_);
Element elt29_=el(_doc,"li");
Text txt18_=tx(_doc,"Sinon, si son partenaire est ma&#238;tre,");
ad(elt29_,txt18_);
Element elt30_=el(_doc,"br");
ad(elt29_,elt30_);
Text txt19_=tx(_doc,"alors il joue la carte de son choix.");
ad(elt29_,txt19_);
Element elt31_=el(_doc,"br");
ad(elt29_,elt31_);
ad(elt25_,elt29_);
Element elt32_=el(_doc,"li");
Text txt20_=tx(_doc,"Sinon, si le joueur poss&#232;de de l''atout,");
ad(elt32_,txt20_);
Element elt33_=el(_doc,"br");
ad(elt32_,elt33_);
Text txt21_=tx(_doc,"&#160;&#160;&#160;&#160;&#160;&#160;Si le pli n''est pas coup&#233;,");
ad(elt32_,txt21_);
Element elt34_=el(_doc,"br");
ad(elt32_,elt34_);
Text txt22_=tx(_doc,"&#160;&#160;&#160;&#160;&#160;&#160;&#160;alors il doit utiliser un atout de son choix, on dit qu''il coupe.");
ad(elt32_,txt22_);
Element elt35_=el(_doc,"br");
ad(elt32_,elt35_);
Text txt23_=tx(_doc,"&#160;&#160;&#160;&#160;&#160;&#160;Sinon si le joueur peut monter sur le maximum des atouts jou&#233;s,");
ad(elt32_,txt23_);
Element elt36_=el(_doc,"br");
ad(elt32_,elt36_);
Text txt24_=tx(_doc,"&#160;&#160;&#160;&#160;&#160;&#160;&#160;alors il doit utiliser un atout et monter sur les atouts pr&#233;c&#233;dents, on dit qu''il sur-coupe.");
ad(elt32_,txt24_);
Element elt37_=el(_doc,"br");
ad(elt32_,elt37_);
ad(elt25_,elt32_);
Element elt38_=el(_doc,"li");
Text txt25_=tx(_doc,"Sinon il joue la carte de son choix.");
ad(elt38_,txt25_);
Element elt39_=el(_doc,"br");
ad(elt38_,elt39_);
ad(elt25_,elt38_);
ad(elt23_,elt25_);
ad(elt16_,elt23_);
ad(elt14_,elt16_);
ad(elt11_,elt14_);
ad(elt1_,elt11_);
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
