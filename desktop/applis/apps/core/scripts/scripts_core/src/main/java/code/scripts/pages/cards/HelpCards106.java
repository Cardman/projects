package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards106{
private HelpCards106(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"La pr&#233;sentation de la fin de partie s''effectue directement sur la fen&#234;tre, par un conteneur &#224; onglets, donc pas de bo&#238;te de dialogue.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt2_=tx(_doc,"Les onglets sont les suivants:");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"ol");
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"R&#233;sultats de cette partie (scores pr&#233;c&#233;dents et scores de cette partie).");
ad(elt6_,txt3_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"Explication annonces.");
ad(elt7_,txt4_);
ad(elt5_,elt7_);
Element elt8_=el(_doc,"li");
Text txt5_=tx(_doc,"Courbes temporelles des scores centr&#233;s par rapport &#224; la moyenne, si la partie est al&#233;atoire.");
ad(elt8_,txt5_);
ad(elt5_,elt8_);
Element elt9_=el(_doc,"li");
Text txt6_=tx(_doc,"Mains et plis.");
ad(elt9_,txt6_);
ad(elt5_,elt9_);
ad(elt1_,elt5_);
Text txt7_=tx(_doc,"L''utilisateur peut regarder les scores pr&#233;c&#233;dents, ceux de cette partie et les annonces de cette partie.");
ad(elt1_,txt7_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt8_=tx(_doc,"Si la partie est finie au moins pour la deuxi&#232;me fois, on ne la comptabilise pas.");
ad(elt1_,txt8_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt9_=tx(_doc,"On s&#233;pare les scores des parties al&#233;atoires de ceux des parties &#233;dit&#233;es.");
ad(elt1_,txt9_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt10_=tx(_doc,"L''utilisateur peut regarder les mains et les plis de cette partie.");
ad(elt1_,txt10_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt11_=tx(_doc,"On sauvegarde la pile de toutes les cartes dans un fichier.");
ad(elt1_,txt11_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt12_=tx(_doc,"On incr&#233;mente (augmente de un) le nombre de parties jou&#233;es depuis le lancement du logiciel,");
ad(elt1_,txt12_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt13_=tx(_doc,"&#160;puis on le sauvegarde, dans un fichier texte, au bon endroit.");
ad(elt1_,txt13_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt14_=tx(_doc,"On incr&#233;mente le nombre de fois que cette partie est finie.");
ad(elt1_,txt14_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt15_=tx(_doc,"Les options de cet &#233;tat sont les suivantes:");
ad(elt1_,txt15_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Element elt23_=el(_doc,"ol");
Element elt24_=el(_doc,"li");
Text txt16_=tx(_doc,"\"Continuer de jouer les parties &#233;dit&#233;es\", si la partie finie est &#233;dit&#233;e et non finale.");
ad(elt24_,txt16_);
ad(elt23_,elt24_);
Element elt25_=el(_doc,"li");
Text txt17_=tx(_doc,"\"Continuer sur le jeu actuel\", si la partie finie est &#233;dit&#233;e et finale et qu''auparavant,");
ad(elt25_,txt17_);
Element elt26_=el(_doc,"br");
ad(elt25_,elt26_);
Text txt18_=tx(_doc,"au moins une partie al&#233;atoire a &#233;t&#233; jou&#233;e; ou si la partie pr&#233;c&#233;dente est al&#233;atoire.");
ad(elt25_,txt18_);
ad(elt23_,elt25_);
Element elt27_=el(_doc,"li");
Text txt19_=tx(_doc,"\"Rejouer la donne\".");
ad(elt27_,txt19_);
ad(elt23_,elt27_);
Element elt28_=el(_doc,"li");
Text txt20_=tx(_doc,"\"Arr&#234;ter\".");
ad(elt28_,txt20_);
ad(elt23_,elt28_);
ad(elt1_,elt23_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Text txt21_=tx(_doc,"Si l''utilisateur a choisi l''option \"Continuer de jouer les parties &#233;dit&#233;es\", alors les cartes ne sont pas battues et la donne suivante est jou&#233;e.");
ad(elt1_,txt21_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Text txt22_=tx(_doc,"Si l''utilisateur a choisi l''option \"Continuer sur le jeu actuel\", alors la partie suivante est jou&#233;e.");
ad(elt1_,txt22_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Text txt23_=tx(_doc,"Si l''utilisateur a choisi l''option \"Rejouer la donne\", alors la partie est redistribu&#233;e.");
ad(elt1_,txt23_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Element elt35_=el(_doc,"br");
ad(elt1_,elt35_);
Text txt24_=tx(_doc,"Si l''utilisateur a choisi l''option \"Arr&#234;ter\", alors le menu \"menu solo\" avec les jeux est affich&eacute; is shown.");
ad(elt1_,txt24_);
Element elt36_=el(_doc,"br");
ad(elt1_,elt36_);
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
