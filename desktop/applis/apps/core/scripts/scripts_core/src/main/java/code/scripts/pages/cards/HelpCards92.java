package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards92{
private HelpCards92(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Le joueur qui distribue les cartes s''appelle le donneur.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"D''une partie &#224; l''autre, le donneur tourne,");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"c''est &#224; dire que le nouveau donneur est le joueur plac&#233; &#224; droite de l''ancien donneur,");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"et le jeu est m&#233;lang&#233; et coup&#233; &#224; chaque fois.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt4_=tx(_doc,"Chaque joueur poss&#232;de 18 cartes et 6 constituent un talon (appel&#233; chien),");
ad(elt1_,txt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"les cartes sont distribu&#233;es 3 par 3.");
ad(elt1_,txt5_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt6_=tx(_doc,"Les cartes, face cach&#233;e, qui constituent le chien,");
ad(elt1_,txt6_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt7_=tx(_doc,"sont distribu&#233;es carte par carte &#224; n''importe quel moment,");
ad(elt1_,txt7_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt8_=tx(_doc,"sauf au premier tour et au dernier.");
ad(elt1_,txt8_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt9_=tx(_doc,"Le donneur distribue dans le sens contraire des aiguilles d''une montre.");
ad(elt1_,txt9_);
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
