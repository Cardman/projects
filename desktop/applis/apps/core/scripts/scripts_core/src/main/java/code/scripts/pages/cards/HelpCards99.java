package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards99{
private HelpCards99(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Les annonces de mis&#232;res sont:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt4_=el(_doc,"ul");
Element elt5_=el(_doc,"li");
Text txt1_=tx(_doc,"Mis&#232;re d''atout: aucun atout dans la main (M&#234;me pas l''Excuse).");
ad(elt5_,txt1_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt2_=tx(_doc,"Mis&#232;re de t&#234;te: aucun Bout ni figure dans la main.");
ad(elt6_,txt2_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,"li");
Text txt3_=tx(_doc,"Mis&#232;re de cartes basses: auncune carte &#224; la couleur allant de l''as au dix dans la main.");
ad(elt7_,txt3_);
ad(elt4_,elt7_);
Element elt8_=el(_doc,"li");
Text txt4_=tx(_doc,"Mis&#232;re de couleur: que des atouts et &#233;ventuellement l''Excuse dans la main.");
ad(elt8_,txt4_);
ad(elt4_,elt8_);
Element elt9_=el(_doc,"li");
Text txt5_=tx(_doc,"Mis&#232;re de figures: pas de figures dans la main.");
ad(elt9_,txt5_);
ad(elt4_,elt9_);
ad(elt1_,elt4_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt6_=tx(_doc,"On les annonce au m&#234;me moment que les poign&#233;es, avant de jouer la premi&#232;re carte.");
ad(elt1_,txt6_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt7_=tx(_doc,"Leurs valeurs respectives sont: 10, 10, 20, 30, 5.");
ad(elt1_,txt7_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt8_=tx(_doc,"A la fin de la partie,");
ad(elt1_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"ul");
Element elt17_=el(_doc,"li");
Text txt9_=tx(_doc,"Si le preneur annonce des mis&#232;res, alors ses annonces de mis&#232;res lui sont compt&#233;es positivement, dans les annonces du preneur.");
ad(elt17_,txt9_);
ad(elt16_,elt17_);
Element elt18_=el(_doc,"li");
Text txt10_=tx(_doc,"Si l''&#233;ventuel partenaire du preneur annonce des mis&#232;res, alors ses annonces de mis&#232;res sont compt&#233;es positivement au preneur, dans les annonces du preneur.");
ad(elt18_,txt10_);
ad(elt16_,elt18_);
Element elt19_=el(_doc,"li");
Text txt11_=tx(_doc,"Si un d&#233;fenseur annonce des mis&#232;res, alors ses annonces de mis&#232;res sont compt&#233;es n&#233;gativement au preneur, dans les annonces du preneur.");
ad(elt19_,txt11_);
ad(elt16_,elt19_);
ad(elt1_,elt16_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Text txt12_=tx(_doc,"Exemple: (&#224; 4 joueurs classique)");
ad(elt1_,txt12_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Text txt13_=tx(_doc,"&#160;Si le preneur marque 43 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 4 joueurs, ni de chelem, mais avec une mis&#232;re d''atout en d&#233;fense,");
ad(elt1_,txt13_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Text txt15_=tx(_doc,"&#160;&#160;si aucun des 4 joueurs n''a jou&#233; le Petit au dernier tour,");
ad(elt1_,txt15_);
Element elt25_=el(_doc,"br");
ad(elt1_,elt25_);
Text txt16_=tx(_doc,"&#160;&#160;alors il gagne son contrat de 2 points et marque ((25+(43-41)+0)x1+0+0-10)x3=51 points de score, et chaque d&#233;fenseur marque ((-25-(43-41)+0)x1+0+0+10)X1=-17 points de score.");
ad(elt1_,txt16_);
Element elt26_=el(_doc,"br");
ad(elt1_,elt26_);
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
