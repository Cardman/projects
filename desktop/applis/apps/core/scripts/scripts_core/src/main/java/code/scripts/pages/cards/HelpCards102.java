package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards102{
private HelpCards102(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Voici ce qui change par rapport au jeu &#224; 4 joueurs:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt1_=tx(_doc,"distribution");
ad(elt5_,txt1_);
Element elt6_=el(_doc,"br");
ad(elt5_,elt6_);
Text txt2_=tx(_doc,"&#160;&#160;chaque joueur poss&#232;de 24 cartes et 6 constituent le chien,");
ad(elt5_,txt2_);
Element elt7_=el(_doc,"br");
ad(elt5_,elt7_);
Text txt3_=tx(_doc,"&#160;&#160;les cartes sont distribu&#233;es 3 par 3.");
ad(elt5_,txt3_);
Element elt8_=el(_doc,"br");
ad(elt5_,elt8_);
ad(elt4_,elt5_);
Element elt9_=el(_doc,"li");
Text txt4_=tx(_doc,"R&#233;partition des &#233;quipes");
ad(elt9_,txt4_);
Element elt10_=el(_doc,"br");
ad(elt9_,elt10_);
Text txt5_=tx(_doc,"&#160;&#160;Le preneur joue contre les deux autres joueurs.");
ad(elt9_,txt5_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
ad(elt4_,elt9_);
Element elt12_=el(_doc,"li");
Text txt6_=tx(_doc,"Annonces de poign&#233;e:");
ad(elt12_,txt6_);
Element elt13_=el(_doc,"br");
ad(elt12_,elt13_);
Element elt14_=el(_doc,"ol");
Element elt15_=el(_doc,"li");
Text txt7_=tx(_doc,"simple poign&#233;e: 12 atouts (y compris l''Excuse).");
ad(elt15_,txt7_);
ad(elt14_,elt15_);
Element elt16_=el(_doc,"li");
Text txt8_=tx(_doc,"poign&#233;e double: 15 atouts (y compris l''Excuse).");
ad(elt16_,txt8_);
ad(elt14_,elt16_);
Element elt17_=el(_doc,"li");
Text txt9_=tx(_doc,"triple poign&#233;e: 18 atouts (y compris l''Excuse).");
ad(elt17_,txt9_);
ad(elt14_,elt17_);
ad(elt12_,elt14_);
ad(elt4_,elt12_);
Element elt18_=el(_doc,"li");
Text txt10_=tx(_doc,"Calcul des scores &#224; la fin de la partie:");
ad(elt18_,txt10_);
Element elt19_=el(_doc,"br");
ad(elt18_,elt19_);
Text txt11_=tx(_doc,"&#160;&#160;Total des points pour une partie: 91 points");
ad(elt18_,txt11_);
Element elt20_=el(_doc,"br");
ad(elt18_,elt20_);
Text txt12_=tx(_doc,"&#160;&#160;Diff&#233;rence = Nb pts r&#233;alis&#233;s par le preneur - Nb de pts n&#233;cessaires pour gagner la partie, pour le preneur");
ad(elt18_,txt12_);
Element elt21_=el(_doc,"br");
ad(elt18_,elt21_);
Text txt13_=tx(_doc,"&#160;&#160;Score du preneur sans points d''annonces = +-25pts + Diff&#233;rence + Petit au bout");
ad(elt18_,txt13_);
Element elt22_=el(_doc,"br");
ad(elt18_,elt22_);
Text txt14_=tx(_doc,"&#160;&#160;Annonces = Poign&#233;es + Chelem + Eventuelles Mis&#232;res");
ad(elt18_,txt14_);
Element elt23_=el(_doc,"br");
ad(elt18_,elt23_);
Text txt15_=tx(_doc,"&#160;&#160;Score total d''un d&#233;fenseur = - (Score sans points d''annonces du preneur x Coefficient de contrat + Annonces preneur - Annonces d&#233;fense)");
ad(elt18_,txt15_);
Element elt24_=el(_doc,"br");
ad(elt18_,elt24_);
Text txt16_=tx(_doc,"&#160;&#160;Score total du preneur = -2 x Score total d''un d&#233;fenseur");
ad(elt18_,txt16_);
Element elt25_=el(_doc,"br");
ad(elt18_,elt25_);
ad(elt4_,elt18_);
ad(elt1_,elt4_);
Element elt26_=el(_doc,"br");
ad(elt1_,elt26_);
Text txt17_=tx(_doc,"&#160;Si le preneur marque un nombre non entier de points, alors sa diff&#233;rence est arrondie &#224; l''entier sup&#233;rieur.");
ad(elt1_,txt17_);
Element elt27_=el(_doc,"br");
ad(elt1_,elt27_);
Text txt21_=tx(_doc,"&#160;Examples:");
ad(elt1_,txt21_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Element elt35_=el(_doc,"ol");
Element elt36_=el(_doc,"li");
Text txt22_=tx(_doc,"Si le preneur marque 43,5 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 3 joueurs, ni de chelem,");
ad(elt36_,txt22_);
Element elt37_=el(_doc,"br");
ad(elt36_,elt37_);
Text txt23_=tx(_doc,"&#160;&#160;si aucun des 3 joueurs n''a jou&#233; le Petit au dernier tour,");
ad(elt36_,txt23_);
Element elt38_=el(_doc,"br");
ad(elt36_,elt38_);
Text txt24_=tx(_doc,"&#160;&#160;alors il gagne son contrat de 3 points et marque ((25+(44-41)+0)x1+0+0+0)x2=56 points de score, et chaque d&#233;fenseur marque ((-25-(43-41)+0)x1+0+0+0)X1=-28 points de score.");
ad(elt36_,txt24_);
Element elt39_=el(_doc,"br");
ad(elt36_,elt39_);
ad(elt35_,elt36_);
Element elt40_=el(_doc,"li");
Text txt25_=tx(_doc,"Si le preneur marque 43,5 points avec 1 Bout dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 3 joueurs, ni de chelem,");
ad(elt40_,txt25_);
Element elt41_=el(_doc,"br");
ad(elt40_,elt41_);
Text txt26_=tx(_doc,"&#160;&#160;si aucun des 3 joueurs n''a jou&#233; le Petit au dernier tour,");
ad(elt40_,txt26_);
Element elt42_=el(_doc,"br");
ad(elt40_,elt42_);
Text txt27_=tx(_doc,"&#160;&#160;alors il perd son contrat de 8 points et marque ((-25+(43-51)+0)x1+0+0+0)x2=-66 points de score, et chaque d&#233;fenseur marque ((25-(43-51)+0)x1+0+0+0)X1=33 points de score.");
ad(elt40_,txt27_);
Element elt43_=el(_doc,"br");
ad(elt40_,elt43_);
ad(elt35_,elt40_);
ad(elt1_,elt35_);
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
