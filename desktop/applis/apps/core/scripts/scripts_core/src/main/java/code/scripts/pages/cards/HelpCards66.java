package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards66{
private HelpCards66(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Demonstrastions of deals of cards, with belote and tarot are possible.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"This sub menu makes up two sub menus:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"ol");
Element elt6_=el(_doc,"li");
Text txt2_=tx(_doc,"Belote, see how a deal of belote is played");
ad(elt6_,txt2_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,"li");
Text txt3_=tx(_doc,"Tarot, see how a deal of tarot is played");
ad(elt7_,txt3_);
ad(elt5_,elt7_);
ad(elt1_,elt5_);
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
