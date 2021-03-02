package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards86{
private HelpCards86(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Comme pour tous les jeux de cartes, il existe des variantes.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Voici quelques variantes:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"ol");
Element elt7_=el(_doc,"li");
Text txt2_=tx(_doc,"&#201;galer est intedit: tous les joueurs ne peuvent pas jouer des groupes de cartes de m&#234;me force que le dernier groupr.");
ad(elt7_,txt2_);
ad(elt6_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt6_,elt8_);
Element elt9_=el(_doc,"li");
Text txt3_=tx(_doc,"&#201;galer saute toujours le joueur suivant: si deux groupes of m&#234;me force sont jou&#233;s, le joueur suivant est saut&#233;, (ce joueur ne peut pas jouer &#224; ce moment, mais peut apr&#232;s).");
ad(elt9_,txt3_);
ad(elt6_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt6_,elt10_);
Element elt11_=el(_doc,"li");
Text txt4_=tx(_doc,"&#201;galer force le joueur suivant &#224; &#233;galer encore: si deux grouprs de m&#234;me force sont jou&#233;s, le joueur suivant doit &#233;galer ou passer, (ce joueur peut seulement jouer un groupe de m&#234;me force &#224; ce moment, mais peut jouer n''importe quel groupe apr&#232;s). Si toutes les cartes de m&#234;me force sont successivement jou&#233;es dans le pli, li pli est fini comme le jeu d''un groupe de 2.");
ad(elt11_,txt4_);
ad(elt6_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt6_,elt12_);
Element elt13_=el(_doc,"li");
Text txt5_=tx(_doc,"Tous les joueurs doivent jouer si possible. (ne peut pas passer)");
ad(elt13_,txt5_);
ad(elt6_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt6_,elt14_);
Element elt15_=el(_doc,"li");
Text txt6_=tx(_doc,"Si un joueur finit par un groupe de 2, le joueur peut perdre la donne. (Si deux joueurs finissent par un groupe de 2, le dernier joueur perd.)");
ad(elt15_,txt6_);
ad(elt6_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt6_,elt16_);
Element elt17_=el(_doc,"li");
Text txt7_=tx(_doc,"Si 4 cartes sont jou&#233;es en m&#234;me temps, l''ordre est invers&#233; (Les 2 deviennent faibles et les 3 deviennent forts.). Si 4 autres cartes sont jou&#233;es en m&#234;me temps, l''inversion est annul&#233;e.");
ad(elt17_,txt7_);
ad(elt6_,elt17_);
Element elt18_=el(_doc,"br");
ad(elt6_,elt18_);
Element elt19_=el(_doc,"li");
Text txt8_=tx(_doc,"Pour les donnes suivantes, le joueur au dernier rang &#233;change 2 cartes avec le joueur au premier rang et le joueur &#224; l''avant dernier rang &#233;change 1 carte avec le joueur au deuxi&#232;me rang.");
ad(elt19_,txt8_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Text txt9_=tx(_doc,"Les perdants donnent leur meilleures cartes. Les gagnants donnent des cartes de leur choix.");
ad(elt19_,txt9_);
ad(elt6_,elt19_);
Element elt21_=el(_doc,"br");
ad(elt6_,elt21_);
Element elt22_=el(_doc,"li");
Text txt10_=tx(_doc,"Pour les donnes suivantes, le joueur au dernier rang commence le premier pli.");
ad(elt22_,txt10_);
ad(elt6_,elt22_);
Element elt23_=el(_doc,"br");
ad(elt6_,elt23_);
Element elt24_=el(_doc,"li");
Text txt11_=tx(_doc,"D''autres jeux de cartes peuvent &#234;tre ajout&#233;s.");
ad(elt24_,txt11_);
ad(elt6_,elt24_);
Element elt25_=el(_doc,"br");
ad(elt6_,elt25_);
Element elt26_=el(_doc,"li");
Text txt12_=tx(_doc,"Les cartes peuvent &#234;tre distribu&#233;es dans le sens des aiguilles d''une montre.");
ad(elt26_,txt12_);
ad(elt6_,elt26_);
ad(elt1_,elt6_);
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
