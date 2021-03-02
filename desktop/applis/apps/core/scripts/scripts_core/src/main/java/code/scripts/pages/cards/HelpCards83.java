package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards83{
private HelpCards83(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Le joueur qui distribue les cartes est appel&#233; le donneur.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"D''une donne &#224; l''autre, the donneur change,");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"c''est-&#224;-dire que le donneur suivant est le joueur &#224; droite du donneur pr&#233;c&#233;dent,");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"et les cartes sont battues &#224; chaque fois.");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt4_=tx(_doc,"Les cartes sont distribu&#233;es une par une.");
ad(elt1_,txt4_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt5_=tx(_doc,"Ce n''est pas grave si des joueurs ont une carte de plus que d''autres.");
ad(elt1_,txt5_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
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
