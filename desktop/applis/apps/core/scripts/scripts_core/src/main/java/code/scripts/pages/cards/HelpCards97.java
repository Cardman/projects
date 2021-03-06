package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards97{
private HelpCards97(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Voici ce qui change par rapport au jeu d''&#233;quipes (Preneur + &#233;ventuel appel&#233; versus D&#233;fense):");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Les distributions possibles sont les suivantes:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"ul");
Element elt6_=el(_doc,"li");
Text txt2_=tx(_doc,"18 cartes par joueur et 6 au chien; les cartes sont distribu&#233;es 3 par 3; pour 4 joueurs.");
ad(elt6_,txt2_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,"li");
Text txt3_=tx(_doc,"24 cartes par joueur et 6 au chien; les cartes sont distribu&#233;es 3 par 3; pour 3 joueurs.");
ad(elt7_,txt3_);
ad(elt5_,elt7_);
Element elt8_=el(_doc,"li");
Text txt4_=tx(_doc,"15 cartes par joueur et 3 au chien; les cartes sont distribu&#233;es 3 par 3; pour 5 joueurs.");
ad(elt8_,txt4_);
ad(elt5_,elt8_);
Element elt9_=el(_doc,"li");
Text txt5_=tx(_doc,"14 cartes par joueur et 8 au chien; les cartes sont distribu&#233;es 2 par 2; pour 5 joueurs.");
ad(elt9_,txt5_);
ad(elt5_,elt9_);
Element elt10_=el(_doc,"li");
Text txt6_=tx(_doc,"12 cartes par joueur et 6 au chien; les cartes sont distribu&#233;es 2 par 2; pour 6 joueurs.");
ad(elt10_,txt6_);
ad(elt5_,elt10_);
ad(elt1_,elt5_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt7_=tx(_doc,"On peut jouer le jeu du chacun pour soi apr&#232;s que tout le monde a pass&#233;.");
ad(elt1_,txt7_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt8_=tx(_doc,"Dans ce cas, le chien est cach&#233;.");
ad(elt1_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt9_=tx(_doc,"Le but est de ramasser le plus de cartes possibles.");
ad(elt1_,txt9_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt10_=tx(_doc,"Sinon le jeu se d&#233;roule de la m&#234;me mani&#232;re que le jeu d''&#233;quipes (M&#234;mes annonces et m&#234;mes r&#232;gles pour le jeu de la carte.).");
ad(elt1_,txt10_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt11_=tx(_doc,"&#160;Les valeurs limites &#224; atteindre pour un joueur sont les suivantes:");
ad(elt1_,txt11_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Element elt22_=el(_doc,"ul");
Element elt23_=el(_doc,"li");
Text txt12_=tx(_doc,"56 points sans bout.");
ad(elt23_,txt12_);
ad(elt22_,elt23_);
Element elt24_=el(_doc,"li");
Text txt13_=tx(_doc,"51 points avec 1 bout dans les plis du joueur.");
ad(elt24_,txt13_);
ad(elt22_,elt24_);
Element elt25_=el(_doc,"li");
Text txt14_=tx(_doc,"41 points avec 2 bouts dans les plis du joueur.");
ad(elt25_,txt14_);
ad(elt22_,elt25_);
Element elt26_=el(_doc,"li");
Text txt15_=tx(_doc,"36 points avec 3 bouts dans les plis du joueur.");
ad(elt26_,txt15_);
ad(elt22_,elt26_);
ad(elt1_,elt22_);
Element elt27_=el(_doc,"br");
ad(elt1_,elt27_);
Text txt16_=tx(_doc,"&#160;A la fin de la partie, pour d&#233;signer le classement des joueurs, on proc&#232;de de la mani&#232;re suivante:");
ad(elt1_,txt16_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Text txt17_=tx(_doc,"Diff&#233;rence d''un joueur = nombre de points marqu&#233;s dans les plis - valeur limite pour le joueur.");
ad(elt1_,txt17_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Text txt18_=tx(_doc,"On classe les joueurs par leur diff&#233;rence, la plus grande diff&#233;rence pour le meilleur.");
ad(elt1_,txt18_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Text txt19_=tx(_doc,"Si deux joueurs ont m&#234;me diff&#233;rence et ont fait des plis:");
ad(elt1_,txt19_);
Element elt35_=el(_doc,"br");
ad(elt1_,elt35_);
Text txt20_=tx(_doc,"&#160;Alors on les classe par le nombre de bouts dans les plis, il doit &#234;tre le plus grand possible pour gagner.");
ad(elt1_,txt20_);
Element elt36_=el(_doc,"br");
ad(elt1_,elt36_);
Element elt37_=el(_doc,"br");
ad(elt1_,elt37_);
Text txt21_=tx(_doc,"&#160;Si de plus, le nombre de bouts dans les plis des deux joueurs est identique, alors:");
ad(elt1_,txt21_);
Element elt38_=el(_doc,"br");
ad(elt1_,elt38_);
Element elt39_=el(_doc,"ol");
Element elt40_=el(_doc,"li");
Text txt22_=tx(_doc,"Si ce nombre vaut 0:");
ad(elt40_,txt22_);
Element elt41_=el(_doc,"br");
ad(elt40_,elt41_);
Text txt23_=tx(_doc,"&#160;&#160;&#160;On compare les nombres de figures dans les plis des deux joueurs, le nombre de figures dans les plis d''un joueur doit &#234;tre le plus grand possible pour gagner.");
ad(elt40_,txt23_);
Element elt42_=el(_doc,"br");
ad(elt40_,elt42_);
Text txt24_=tx(_doc,"&#160;&#160;&#160;Si de plus, le nombre de figures dans les plis des deux joueurs est identique, alors:");
ad(elt40_,txt24_);
Element elt43_=el(_doc,"br");
ad(elt40_,elt43_);
Text txt25_=tx(_doc,"&#160;&#160;&#160;&#160;On compare les figures ramass&#233;es par les deux joueurs, en commen&#231;ant par les plus hautes.");
ad(elt40_,txt25_);
Element elt44_=el(_doc,"br");
ad(elt40_,elt44_);
Text txt26_=tx(_doc,"&#160;&#160;&#160;&#160;On s''arr&#234;te d&#232;s qu''il y a une diff&#233;rence de hauteur.");
ad(elt40_,txt26_);
Element elt45_=el(_doc,"br");
ad(elt40_,elt45_);
Text txt27_=tx(_doc,"&#160;&#160;&#160;&#160;Si les figures sont identiques en valeur deux &#224; deux des deux joueurs, on regarde qui a fait le premier pli.");
ad(elt40_,txt27_);
Element elt46_=el(_doc,"br");
ad(elt40_,elt46_);
Text txt28_=tx(_doc,"&#160;&#160;&#160;&#160;&#160;Celui qui a fait son premier pli en premier passe devant.");
ad(elt40_,txt28_);
Element elt47_=el(_doc,"br");
ad(elt40_,elt47_);
ad(elt39_,elt40_);
Element elt48_=el(_doc,"li");
Text txt29_=tx(_doc,"Si ce nombre vaut 1:");
ad(elt48_,txt29_);
Element elt49_=el(_doc,"br");
ad(elt48_,elt49_);
Text txt30_=tx(_doc,"&#160;&#160;&#160;On classe alors de la mani&#232;re suivante du plus gagnant au plus perdant: 21 - Excuse - Petit.");
ad(elt48_,txt30_);
Element elt50_=el(_doc,"br");
ad(elt48_,elt50_);
Text txt31_=tx(_doc,"&#160;&#160;&#160;(Il vaut mieux alors avoir l''Excuse que le Petit,...)");
ad(elt48_,txt31_);
Element elt51_=el(_doc,"br");
ad(elt48_,elt51_);
ad(elt39_,elt48_);
ad(elt1_,elt39_);
Element elt52_=el(_doc,"br");
ad(elt1_,elt52_);
Text txt32_=tx(_doc,"Si deux joueurs ont m&#234;me diff&#233;rence mais n''ont pas fait des plis, il y a litige.");
ad(elt1_,txt32_);
Element elt53_=el(_doc,"br");
ad(elt1_,elt53_);
Element elt54_=el(_doc,"br");
ad(elt1_,elt54_);
Element elt55_=el(_doc,"ul");
Element elt56_=el(_doc,"li");
Text txt33_=tx(_doc,"3 joueurs");
ad(elt56_,txt33_);
Element elt57_=el(_doc,"br");
ad(elt56_,elt57_);
Text txt34_=tx(_doc,"&#160;&#160;Coefficient vaut du gagnant au perdant:");
ad(elt56_,txt34_);
Element elt58_=el(_doc,"br");
ad(elt56_,elt58_);
Element elt59_=el(_doc,"ul");
Element elt60_=el(_doc,"li");
Text txt35_=tx(_doc,"1, 0, -1; s''il n''y a pas de litige.");
ad(elt60_,txt35_);
ad(elt59_,elt60_);
Element elt61_=el(_doc,"li");
Text txt36_=tx(_doc,"2, -1, -1; s''il y a litige.");
ad(elt61_,txt36_);
ad(elt59_,elt61_);
ad(elt56_,elt59_);
ad(elt55_,elt56_);
Element elt62_=el(_doc,"li");
Text txt37_=tx(_doc,"4 joueurs");
ad(elt62_,txt37_);
Element elt63_=el(_doc,"br");
ad(elt62_,elt63_);
Text txt38_=tx(_doc,"&#160;&#160;Coefficient vaut du gagnant au perdant:");
ad(elt62_,txt38_);
Element elt64_=el(_doc,"br");
ad(elt62_,elt64_);
Element elt65_=el(_doc,"ul");
Element elt66_=el(_doc,"li");
Text txt39_=tx(_doc,"2, 1, -1, -2; s''il n''y a pas de litige.");
ad(elt66_,txt39_);
ad(elt65_,elt66_);
Element elt67_=el(_doc,"li");
Text txt40_=tx(_doc,"3, 1, -2, -2; s''il y a litige entre deux joueurs exactement.");
ad(elt67_,txt40_);
ad(elt65_,elt67_);
Element elt68_=el(_doc,"li");
Text txt41_=tx(_doc,"6, -2, -2, -2; s''il y a litige entre trois joueurs exactement.");
ad(elt68_,txt41_);
ad(elt65_,elt68_);
ad(elt62_,elt65_);
ad(elt55_,elt62_);
Element elt69_=el(_doc,"li");
Text txt42_=tx(_doc,"5 joueurs");
ad(elt69_,txt42_);
Element elt70_=el(_doc,"br");
ad(elt69_,elt70_);
Text txt43_=tx(_doc,"&#160;&#160;Coefficient vaut du gagnant au perdant:");
ad(elt69_,txt43_);
Element elt71_=el(_doc,"br");
ad(elt69_,elt71_);
Element elt72_=el(_doc,"ul");
Element elt73_=el(_doc,"li");
Text txt44_=tx(_doc,"2, 1, 0, -1, -2; s''il n''y a pas de litige.");
ad(elt73_,txt44_);
ad(elt72_,elt73_);
Element elt74_=el(_doc,"li");
Text txt45_=tx(_doc,"3, 1, 0, -2, -2; s''il y a litige entre deux joueurs exactement.");
ad(elt74_,txt45_);
ad(elt72_,elt74_);
Element elt75_=el(_doc,"li");
Text txt46_=tx(_doc,"6, 0, -2, -2, -2; s''il y a litige entre trois joueurs exactement.");
ad(elt75_,txt46_);
ad(elt72_,elt75_);
Element elt76_=el(_doc,"li");
Text txt47_=tx(_doc,"8, -2, -2, -2, -2; s''il y a litige entre quatre joueurs exactement.");
ad(elt76_,txt47_);
ad(elt72_,elt76_);
ad(elt69_,elt72_);
ad(elt55_,elt69_);
Element elt77_=el(_doc,"li");
Text txt48_=tx(_doc,"6 joueurs");
ad(elt77_,txt48_);
Element elt78_=el(_doc,"br");
ad(elt77_,elt78_);
Text txt49_=tx(_doc,"&#160;&#160;Coefficient vaut du gagnant au perdant:");
ad(elt77_,txt49_);
Element elt79_=el(_doc,"br");
ad(elt77_,elt79_);
Element elt80_=el(_doc,"ul");
Element elt81_=el(_doc,"li");
Text txt50_=tx(_doc,"3, 2, 1, -1, -2, -3; s''il n''y a pas de litige.");
ad(elt81_,txt50_);
ad(elt80_,elt81_);
Element elt82_=el(_doc,"li");
Text txt51_=tx(_doc,"3, 2, 1, 0, -3, -3; s''il y a litige entre deux joueurs exactement.");
ad(elt82_,txt51_);
ad(elt80_,elt82_);
Element elt83_=el(_doc,"li");
Text txt52_=tx(_doc,"4, 2, 0, -2, -2, -2; s''il y a litige entre trois joueurs exactement.");
ad(elt83_,txt52_);
ad(elt80_,elt83_);
Element elt84_=el(_doc,"li");
Text txt53_=tx(_doc,"8, 0, -2, -2, -2, -2; s''il y a litige entre quatre joueurs exactement.");
ad(elt84_,txt53_);
ad(elt80_,elt84_);
Element elt85_=el(_doc,"li");
Text txt54_=tx(_doc,"10, -2, -2, -2, -2, -2; s''il y a litige entre cinq joueurs exactement.");
ad(elt85_,txt54_);
ad(elt80_,elt85_);
ad(elt77_,elt80_);
ad(elt55_,elt77_);
ad(elt1_,elt55_);
Element elt86_=el(_doc,"br");
ad(elt1_,elt86_);
Text txt55_=tx(_doc,"Un chelem r&#233;ussi vaut de base 200 points. (Il est consid&#233;r&#233; comme non demand&#233;.)");
ad(elt1_,txt55_);
Element elt87_=el(_doc,"br");
ad(elt1_,elt87_);
Element elt88_=el(_doc,"br");
ad(elt1_,elt88_);
Text txt56_=tx(_doc,"Score d''un joueur = 4 x (Coefficient x (25 + Diff&#233;rence max pour les joueurs arrondie &#224; l''entier sup&#233;rieur + nombre points chien arrondi &#224; l''entier sup&#233;rieur) + (nombre de joueurs - 1) x Annonces du joueur - Annonces de chacun des autres joueurs).");
ad(elt1_,txt56_);
Element elt89_=el(_doc,"br");
ad(elt1_,elt89_);
Element elt90_=el(_doc,"br");
ad(elt1_,elt90_);
Text txt57_=tx(_doc,"En &#233;quipes de deux joueurs, les coefficients sont les suivants:");
ad(elt1_,txt57_);
Element elt91_=el(_doc,"br");
ad(elt1_,elt91_);
Element elt92_=el(_doc,"br");
ad(elt1_,elt92_);
Element elt93_=el(_doc,"ul");
Element elt94_=el(_doc,"li");
Text txt58_=tx(_doc,"4 joueurs");
ad(elt94_,txt58_);
Element elt95_=el(_doc,"br");
ad(elt94_,elt95_);
Text txt59_=tx(_doc,"&#160;&#160;Coefficient vaut de l''&#233;quipe gagnante &#224; l''&#233;quipe perdante:");
ad(elt94_,txt59_);
Element elt96_=el(_doc,"br");
ad(elt94_,elt96_);
Element elt97_=el(_doc,"ul");
Element elt98_=el(_doc,"li");
Text txt60_=tx(_doc,"1, -1");
ad(elt98_,txt60_);
ad(elt97_,elt98_);
ad(elt94_,elt97_);
ad(elt93_,elt94_);
Element elt99_=el(_doc,"li");
Text txt61_=tx(_doc,"6 joueurs");
ad(elt99_,txt61_);
Element elt100_=el(_doc,"br");
ad(elt99_,elt100_);
Text txt62_=tx(_doc,"&#160;&#160;Coefficient vaut de l''&#233;quipe gagnante &#224; l''&#233;quipe perdante:");
ad(elt99_,txt62_);
Element elt101_=el(_doc,"br");
ad(elt99_,elt101_);
Element elt102_=el(_doc,"ul");
Element elt103_=el(_doc,"li");
Text txt63_=tx(_doc,"1, 0, -1; s''il n''y a pas de litige.");
ad(elt103_,txt63_);
ad(elt102_,elt103_);
Element elt104_=el(_doc,"li");
Text txt64_=tx(_doc,"2, -1, -1; s''il y a litige entre deux &#233;quipes exactement.");
ad(elt104_,txt64_);
ad(elt102_,elt104_);
ad(elt99_,elt102_);
ad(elt93_,elt99_);
ad(elt1_,elt93_);
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
