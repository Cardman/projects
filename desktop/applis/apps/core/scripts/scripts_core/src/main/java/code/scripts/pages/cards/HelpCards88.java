package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards88{
private HelpCards88(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Les annonces de base sont la poign&#233;e et le chelem.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Pour le chelem, s''il poss&#232;de de plus l''Excuse, il doit la jouer au dernier tour,");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt2_=tx(_doc,"car le preneur est seul et l''Excuse, jou&#233;e avant le dernier tour, ne permet pas de prendre la main.");
ad(elt1_,txt2_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt3_=tx(_doc,"Une poign&#233;e est une main ne contenant que des atouts (L''Excuse peut en faire partie).");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"Les adjectifs simple, double et triple qualifient le nombre d''atouts pr&#233;sents dans la poign&#233;e.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt5_=tx(_doc,"Les nombres minimaux d''atouts (Excuse comprise) pour annoncer une simple poign&#233;e,");
ad(elt1_,txt5_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt6_=tx(_doc,"une double poign&#233;e et une triple poign&#233;e sont respectivement 10, 13 et 15.");
ad(elt1_,txt6_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt7_=tx(_doc,"L''Excuse ne peut faire partie d''une poign&#233;e, que si le nombre total d''atouts (Excuse comprise) vaut le nombre minimal correspondant.");
ad(elt1_,txt7_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt8_=tx(_doc,"Exemples:");
ad(elt1_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Element elt17_=el(_doc,"ol");
Element elt18_=el(_doc,"li");
Text txt9_=tx(_doc,"Si un joueur poss&#232;de 9 atouts et l''Excuse,");
ad(elt18_,txt9_);
Element elt19_=el(_doc,"br");
ad(elt18_,elt19_);
Text txt10_=tx(_doc,"&#160;&#160;alors pour l''annonce de la simple poign&#233;e, l''annonceur doit ajouter l''Excuse dans la poign&#233;e.");
ad(elt18_,txt10_);
Element elt20_=el(_doc,"br");
ad(elt18_,elt20_);
ad(elt17_,elt18_);
Element elt21_=el(_doc,"li");
Text txt11_=tx(_doc,"Si un joueur poss&#232;de 10 atouts et l''Excuse,");
ad(elt21_,txt11_);
Element elt22_=el(_doc,"br");
ad(elt21_,elt22_);
Text txt12_=tx(_doc,"&#160;&#160;alors pour l''annonce de la simple poign&#233;e, l''annonceur n''est pas autoris&#233; &#224; ajouter l''Excuse dans la poign&#233;e.");
ad(elt21_,txt12_);
Element elt23_=el(_doc,"br");
ad(elt21_,elt23_);
ad(elt17_,elt21_);
Element elt24_=el(_doc,"li");
Text txt13_=tx(_doc,"Si un joueur poss&#232;de 13 atouts et l''Excuse,");
ad(elt24_,txt13_);
Element elt25_=el(_doc,"br");
ad(elt24_,elt25_);
Text txt14_=tx(_doc,"&#160;&#160;alors le joueur n''est pas oblig&#233; d''annoncer une double poign&#233;e.");
ad(elt24_,txt14_);
Element elt26_=el(_doc,"br");
ad(elt24_,elt26_);
ad(elt17_,elt24_);
ad(elt1_,elt17_);
Element elt27_=el(_doc,"br");
ad(elt1_,elt27_);
Text txt15_=tx(_doc,"Les annonces de poign&#233;es ou de mis&#232;res se fait au d&#233;but de la partie en posant la 1&#232;re carte.");
ad(elt1_,txt15_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Text txt16_=tx(_doc,"On doit montrer son jeu, lors des annonces de poign&#233;es sinon, il peut y avoir des fausses annonces.");
ad(elt1_,txt16_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
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
