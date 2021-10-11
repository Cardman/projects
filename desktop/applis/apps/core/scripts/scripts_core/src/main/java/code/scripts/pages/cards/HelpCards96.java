package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards96{
private HelpCards96(){}
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
Element elt7_=el(_doc,"br");
ad(elt5_,elt7_);
Element elt8_=el(_doc,"ol");
Element elt9_=el(_doc,"li");
Text txt2_=tx(_doc,"Si le preneur joue contre les 4 autres joueurs:");
ad(elt9_,txt2_);
Element elt10_=el(_doc,"br");
ad(elt9_,elt10_);
Text txt3_=tx(_doc,"&#160;&#160;&#160;chaque joueur poss&#232;de 14 cartes et 8 constituent le chien,");
ad(elt9_,txt3_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
Text txt4_=tx(_doc,"&#160;&#160;&#160;les cartes sont distribu&#233;es 2 par 2.");
ad(elt9_,txt4_);
Element elt12_=el(_doc,"br");
ad(elt9_,elt12_);
ad(elt8_,elt9_);
Element elt13_=el(_doc,"li");
Text txt5_=tx(_doc,"Sinon:");
ad(elt13_,txt5_);
Element elt14_=el(_doc,"br");
ad(elt13_,elt14_);
Text txt6_=tx(_doc,"&#160;&#160;&#160;chaque joueur poss&#232;de 15 cartes et 3 constituent le chien,");
ad(elt13_,txt6_);
Element elt15_=el(_doc,"br");
ad(elt13_,elt15_);
Text txt7_=tx(_doc,"&#160;&#160;&#160;les cartes sont distribu&#233;es 3 par 3.");
ad(elt13_,txt7_);
Element elt16_=el(_doc,"br");
ad(elt13_,elt16_);
ad(elt8_,elt13_);
ad(elt5_,elt8_);
ad(elt4_,elt5_);
Element elt17_=el(_doc,"li");
Text txt8_=tx(_doc,"R&#233;partition des &#233;quipes");
ad(elt17_,txt8_);
Element elt18_=el(_doc,"br");
ad(elt17_,elt18_);
Element elt19_=el(_doc,"br");
ad(elt17_,elt19_);
Element elt20_=el(_doc,"ol");
Element elt21_=el(_doc,"li");
Text txt9_=tx(_doc,"Premi&#232;re variante, la plus jou&#233;e:");
ad(elt21_,txt9_);
Element elt22_=el(_doc,"br");
ad(elt21_,elt22_);
Text txt10_=tx(_doc,"&#160;&#160;&#160;Apr&#232;s que tout le monde ait parl&#233;,");
ad(elt21_,txt10_);
Element elt23_=el(_doc,"br");
ad(elt21_,elt23_);
Text txt11_=tx(_doc,"&#160;&#160;&#160;&#160;le preneur appelle le roi d''une couleur,");
ad(elt21_,txt11_);
Element elt24_=el(_doc,"br");
ad(elt21_,elt24_);
Text txt12_=tx(_doc,"&#160;&#160;&#160;&#160;s''il a les 4 rois, il appelle la dame d''une couleur,");
ad(elt21_,txt12_);
Element elt25_=el(_doc,"br");
ad(elt21_,elt25_);
Text txt13_=tx(_doc,"&#160;&#160;&#160;&#160;s''il a les 4 dames et les 4 rois, il appelle le cavalier d''une couleur,");
ad(elt21_,txt13_);
Element elt26_=el(_doc,"br");
ad(elt21_,elt26_);
Text txt14_=tx(_doc,"&#160;&#160;&#160;&#160;s''il a les 4 cavaliers, les 4 dames et les 4 rois, il appelle le valet d''une couleur.");
ad(elt21_,txt14_);
Element elt27_=el(_doc,"br");
ad(elt21_,elt27_);
Text txt15_=tx(_doc,"&#160;&#160;&#160;&#160;Selon les variantes, l''appel peut &#234;tre fait apr&#232;s ou avant le chien.");
ad(elt21_,txt15_);
Element elt28_=el(_doc,"br");
ad(elt21_,elt28_);
ad(elt20_,elt21_);
Element elt29_=el(_doc,"li");
Text txt16_=tx(_doc,"Deuxi&#232;me variante:");
ad(elt29_,txt16_);
Element elt30_=el(_doc,"br");
ad(elt29_,elt30_);
Text txt17_=tx(_doc,"&#160;&#160;&#160;Apr&#232;s que tout le monde ait parl&#233;,");
ad(elt29_,txt17_);
Element elt31_=el(_doc,"br");
ad(elt29_,elt31_);
Text txt18_=tx(_doc,"&#160;&#160;&#160;&#160;le preneur appelle une figure d''une couleur.");
ad(elt29_,txt18_);
Element elt32_=el(_doc,"br");
ad(elt29_,elt32_);
Text txt19_=tx(_doc,"&#160;&#160;&#160;&#160;Selon les variantes, l''appel peut &#234;tre fait apr&#232;s ou avant le chien.");
ad(elt29_,txt19_);
Element elt33_=el(_doc,"br");
ad(elt29_,elt33_);
ad(elt20_,elt29_);
Element elt34_=el(_doc,"li");
Text txt20_=tx(_doc,"Troisi&#232;me variante:");
ad(elt34_,txt20_);
Element elt35_=el(_doc,"br");
ad(elt34_,elt35_);
Text txt21_=tx(_doc,"&#160;&#160;&#160;S''il n''y a pas d''appel, le preneur joue contre les 4 autres joueurs.");
ad(elt34_,txt21_);
ad(elt20_,elt34_);
ad(elt17_,elt20_);
ad(elt4_,elt17_);
Element elt36_=el(_doc,"li");
Text txt22_=tx(_doc,"Annonces de poign&#233;e:");
ad(elt36_,txt22_);
Element elt37_=el(_doc,"br");
ad(elt36_,elt37_);
Element elt38_=el(_doc,"br");
ad(elt36_,elt38_);
Element elt39_=el(_doc,"ol");
Element elt40_=el(_doc,"li");
Text txt23_=tx(_doc,"simple poign&#233;e: 8 atouts (y compris l''Excuse).");
ad(elt40_,txt23_);
ad(elt39_,elt40_);
Element elt41_=el(_doc,"li");
Text txt24_=tx(_doc,"poign&#233;e double: 10 atouts (y compris l''Excuse).");
ad(elt41_,txt24_);
ad(elt39_,elt41_);
Element elt42_=el(_doc,"li");
Text txt25_=tx(_doc,"triple poign&#233;e: 12 atouts (y compris l''Excuse).");
ad(elt42_,txt25_);
ad(elt39_,elt42_);
ad(elt36_,elt39_);
ad(elt4_,elt36_);
Element elt43_=el(_doc,"li");
Text txt26_=tx(_doc,"Calcul des scores &#224; la fin de la partie:");
ad(elt43_,txt26_);
Element elt44_=el(_doc,"br");
ad(elt43_,elt44_);
Element elt45_=el(_doc,"br");
ad(elt43_,elt45_);
Text txt27_=tx(_doc,"&#160;&#160;Total des points pour une partie: 91 points");
ad(elt43_,txt27_);
Element elt46_=el(_doc,"br");
ad(elt43_,elt46_);
Text txt28_=tx(_doc,"&#160;&#160;Diff&#233;rence = Nb pts r&#233;alis&#233;s par le preneur - Nb de pts n&#233;cessaires pour gagner la partie, pour le preneur");
ad(elt43_,txt28_);
Element elt47_=el(_doc,"br");
ad(elt43_,elt47_);
Text txt29_=tx(_doc,"&#160;&#160;Score du preneur sans points d''annonces = +-25pts + Diff&#233;rence + Petit au bout");
ad(elt43_,txt29_);
Element elt48_=el(_doc,"br");
ad(elt43_,elt48_);
Text txt30_=tx(_doc,"&#160;&#160;Annonces = Poign&#233;es + Chelem + Eventuelles Mis&#232;res");
ad(elt43_,txt30_);
Element elt49_=el(_doc,"br");
ad(elt43_,elt49_);
Text txt31_=tx(_doc,"&#160;&#160;Score total d''un d&#233;fenseur = - (Score sans points d''annonces du preneur x Coefficient de contrat + Annonces preneur - Annonces d&#233;fense)");
ad(elt43_,txt31_);
Element elt50_=el(_doc,"br");
ad(elt43_,elt50_);
Text txt32_=tx(_doc,"&#160;&#160;Si le preneur n''est seul en attaque");
ad(elt43_,txt32_);
Element elt51_=el(_doc,"br");
ad(elt43_,elt51_);
Text txt33_=tx(_doc,"&#160;&#160;&#160;Score total du preneur = -2 x Score total d''un d&#233;fenseur.");
ad(elt43_,txt33_);
Element elt52_=el(_doc,"br");
ad(elt43_,elt52_);
Text txt34_=tx(_doc,"&#160;&#160;Sinon");
ad(elt43_,txt34_);
Element elt53_=el(_doc,"br");
ad(elt43_,elt53_);
Text txt35_=tx(_doc,"&#160;&#160;&#160;Score total du preneur = -4 x Score total d''un d&#233;fenseur.");
ad(elt43_,txt35_);
Element elt54_=el(_doc,"br");
ad(elt43_,elt54_);
Text txt36_=tx(_doc,"&#160;&#160;Score total de l''&#233;ventuel appel&#233; = - Score total d''un d&#233;fenseur");
ad(elt43_,txt36_);
Element elt55_=el(_doc,"br");
ad(elt43_,elt55_);
Element elt56_=el(_doc,"br");
ad(elt43_,elt56_);
Text txt37_=tx(_doc,"&#160;En cas de r&#233;ussite du contrat pour le preneur, et s''il marque un nombre non entier de points, alors sa diff&#233;rence est arrondie &#224; l''entier sup&#233;rieur.");
ad(elt43_,txt37_);
Element elt57_=el(_doc,"br");
ad(elt43_,elt57_);
ad(elt4_,elt43_);
ad(elt1_,elt4_);
Element elt62_=el(_doc,"br");
ad(elt1_,elt62_);
Text txt41_=tx(_doc,"&#160;Exemples:");
ad(elt1_,txt41_);
Element elt63_=el(_doc,"br");
ad(elt1_,elt63_);
Element elt64_=el(_doc,"br");
ad(elt1_,elt64_);
Element elt65_=el(_doc,"ol");
Element elt66_=el(_doc,"li");
Text txt42_=tx(_doc,"Si le preneur marque 43,5 points avec 2 Bouts dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 5 joueurs, ni de chelem,");
ad(elt66_,txt42_);
Element elt67_=el(_doc,"br");
ad(elt66_,elt67_);
Text txt43_=tx(_doc,"&#160;&#160;si le preneur n''est pas seul en attaque,");
ad(elt66_,txt43_);
Element elt68_=el(_doc,"br");
ad(elt66_,elt68_);
Text txt44_=tx(_doc,"&#160;&#160;si aucun des 5 joueurs n''a jou&#233; le Petit au dernier tour,");
ad(elt66_,txt44_);
Element elt69_=el(_doc,"br");
ad(elt66_,elt69_);
Text txt45_=tx(_doc,"&#160;&#160;alors il gagne son contrat de 3 points et marque ((25+(44-41)+0)x1+0+0+0)x2=56 points de score,");
ad(elt66_,txt45_);
Element elt70_=el(_doc,"br");
ad(elt66_,elt70_);
Text txt46_=tx(_doc,"&#160;&#160;&#160;l''appel&#233; marque (25+(44-41)+0)x1+0+0+0=28 points de score,");
ad(elt66_,txt46_);
Element elt71_=el(_doc,"br");
ad(elt66_,elt71_);
Text txt47_=tx(_doc,"&#160;&#160;&#160;chaque d&#233;fenseur marque ((-25-(43-41)+0)x1+0+0+0)X1=-28 points de score.");
ad(elt66_,txt47_);
Element elt72_=el(_doc,"br");
ad(elt66_,elt72_);
ad(elt65_,elt66_);
Element elt73_=el(_doc,"li");
Text txt48_=tx(_doc,"Si le preneur marque 43,5 points avec 1 Bout dans ses plis, en demandant une petite, sans annonces de poign&#233;e pour les 5 joueurs, ni de chelem,");
ad(elt73_,txt48_);
Element elt74_=el(_doc,"br");
ad(elt73_,elt74_);
Text txt49_=tx(_doc,"&#160;&#160;si aucun des 5 joueurs n''a jou&#233; le Petit au dernier tour,");
ad(elt73_,txt49_);
Element elt75_=el(_doc,"br");
ad(elt73_,elt75_);
Text txt50_=tx(_doc,"&#160;&#160;si le preneur est seul en attaque,");
ad(elt73_,txt50_);
Element elt76_=el(_doc,"br");
ad(elt73_,elt76_);
Text txt51_=tx(_doc,"&#160;&#160;alors il perd son contrat de 8 points et marque ((-25+(43-51)+0)x1+0+0+0)x4=-132 points de score, et chaque d&#233;fenseur marque ((25-(43-51)+0)x1+0+0+0)X1=33 points de score.");
ad(elt73_,txt51_);
Element elt77_=el(_doc,"br");
ad(elt73_,elt77_);
ad(elt65_,elt73_);
ad(elt1_,elt65_);
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
