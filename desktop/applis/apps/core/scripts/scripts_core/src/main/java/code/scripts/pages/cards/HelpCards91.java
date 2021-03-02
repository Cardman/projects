package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards91{
private HelpCards91(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Les contrats d&#233;terminent la partie.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Chaque joueur annonce un contrat, dans le sens contraire des aiguilles d''une montre,");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"en commen&#231;ant par le joueur plac&#233;, &#224; droite du donneur.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"Un joueur doit soit passer, soit annoncer un contrat sup&#233;rieur aux pr&#233;c&#233;dents.");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt4_=tx(_doc,"Au tarot, il existe 5 contrats, dans l''ordre du plus faible au plus fort:");
ad(elt1_,txt4_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt5_=tx(_doc,"Passe");
ad(elt12_,txt5_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"Petite");
ad(elt13_,txt6_);
ad(elt11_,elt13_);
Element elt14_=el(_doc,"li");
Text txt7_=tx(_doc,"Garde");
ad(elt14_,txt7_);
ad(elt11_,elt14_);
Element elt15_=el(_doc,"li");
Text txt8_=tx(_doc,"Garde Sans");
ad(elt15_,txt8_);
ad(elt11_,elt15_);
Element elt16_=el(_doc,"li");
Text txt9_=tx(_doc,"Garde Contre");
ad(elt16_,txt9_);
ad(elt11_,elt16_);
ad(elt1_,elt11_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt10_=tx(_doc,"Apr&#232;s que tous les joueurs aient parl&#233;, le joueur ayant annonc&#233; le contrat le plus fort devient le preneur.");
ad(elt1_,txt10_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt11_=tx(_doc,"En fonction du contrat annonc&#233; par le preneur, vis-&#224;-vis du chien, on proc&#232;de de la mani&#232;re suivate:");
ad(elt1_,txt11_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Element elt20_=el(_doc,"ol");
Element elt21_=el(_doc,"li");
Text txt12_=tx(_doc,"Petite: le preneur prend le chien et le montre aux autres,");
ad(elt21_,txt12_);
ad(elt20_,elt21_);
Element elt22_=el(_doc,"li");
Text txt13_=tx(_doc,"Garde: le preneur prend le chien et le montre aux autres,");
ad(elt22_,txt13_);
ad(elt20_,elt22_);
Element elt23_=el(_doc,"li");
Text txt14_=tx(_doc,"Garde Sans: le preneur prend le chien non visible &#224; personne,");
ad(elt23_,txt14_);
ad(elt20_,elt23_);
Element elt24_=el(_doc,"li");
Text txt15_=tx(_doc,"Garde Contre: le preneur laisse le chien &#224; ses adversaires non visible &#224; personne,");
ad(elt24_,txt15_);
ad(elt20_,elt24_);
ad(elt1_,elt20_);
Element elt25_=el(_doc,"br");
ad(elt1_,elt25_);
Text txt16_=tx(_doc,"Remarques:");
ad(elt1_,txt16_);
Element elt26_=el(_doc,"br");
ad(elt1_,elt26_);
Element elt27_=el(_doc,"br");
ad(elt1_,elt27_);
Element elt28_=el(_doc,"ol");
Element elt29_=el(_doc,"li");
Text txt17_=tx(_doc,"Les adversaires du preneur constituent la d&#233;fense.");
ad(elt29_,txt17_);
ad(elt28_,elt29_);
Element elt30_=el(_doc,"li");
Text txt18_=tx(_doc,"Il n''y a qu''un tour d''ench&#232;re.(Chaque joueur ne parle qu''une fois.)");
ad(elt30_,txt18_);
ad(elt28_,elt30_);
ad(elt1_,elt28_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Text txt19_=tx(_doc,"Exemples:");
ad(elt1_,txt19_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Element elt34_=el(_doc,"ol");
Element elt35_=el(_doc,"li");
Text txt20_=tx(_doc,"Si le 1er joueur, qui doit parler, passe,");
ad(elt35_,txt20_);
Element elt36_=el(_doc,"br");
ad(elt35_,elt36_);
Text txt21_=tx(_doc,"&#160;&#160;si le 2e annonce une petite,");
ad(elt35_,txt21_);
Element elt37_=el(_doc,"br");
ad(elt35_,elt37_);
Text txt22_=tx(_doc,"&#160;&#160;alors le 3e ne peut pas demander une petite, mais il peut demander une garde ou un contrat sup&#233;rieur.");
ad(elt35_,txt22_);
Element elt38_=el(_doc,"br");
ad(elt35_,elt38_);
ad(elt34_,elt35_);
Element elt39_=el(_doc,"li");
Text txt23_=tx(_doc,"Si le 1er joueur, qui doit parler, passe,");
ad(elt39_,txt23_);
Element elt40_=el(_doc,"br");
ad(elt39_,elt40_);
Text txt24_=tx(_doc,"&#160;&#160;si le 2e annonce une petite,");
ad(elt39_,txt24_);
Element elt41_=el(_doc,"br");
ad(elt39_,elt41_);
Text txt25_=tx(_doc,"&#160;&#160;si le 3e passe,");
ad(elt39_,txt25_);
Element elt42_=el(_doc,"br");
ad(elt39_,elt42_);
Text txt26_=tx(_doc,"&#160;&#160;si le dernier demande une garde sans,");
ad(elt39_,txt26_);
Element elt43_=el(_doc,"br");
ad(elt39_,elt43_);
Text txt27_=tx(_doc,"&#160;&#160;alors le preneur est le dernier joueur.");
ad(elt39_,txt27_);
Element elt44_=el(_doc,"br");
ad(elt39_,elt44_);
ad(elt34_,elt39_);
Element elt45_=el(_doc,"li");
Text txt28_=tx(_doc,"Si 1er joueur, qui doit parler, passe,");
ad(elt45_,txt28_);
Element elt46_=el(_doc,"br");
ad(elt45_,elt46_);
Text txt29_=tx(_doc,"&#160;&#160;Si le 2e annonce une garde contre,");
ad(elt45_,txt29_);
Element elt47_=el(_doc,"br");
ad(elt45_,elt47_);
Text txt30_=tx(_doc,"&#160;&#160;alors, les deux autres joueurs doivent passer.");
ad(elt45_,txt30_);
Element elt48_=el(_doc,"br");
ad(elt45_,elt48_);
ad(elt34_,elt45_);
ad(elt1_,elt34_);
Element elt49_=el(_doc,"br");
ad(elt1_,elt49_);
Text txt31_=tx(_doc,"Le preneur joue contre les trois autres joueurs.");
ad(elt1_,txt31_);
Element elt50_=el(_doc,"br");
ad(elt1_,elt50_);
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
