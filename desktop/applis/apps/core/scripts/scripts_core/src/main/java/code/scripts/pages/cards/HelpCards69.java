package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards69{
private HelpCards69(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"You will find all functions of the software.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"The software owns a menu bar with five menus.");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt2_=tx(_doc,"While the first launch of the software, you can access");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"the buttons \"Single player mode\" and \"Multi players mode\".");
ad(elt1_,txt3_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt4_=tx(_doc,"This the default launch.");
ad(elt1_,txt4_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Text txt5_=tx(_doc,"By having chosen one of the two modes, you then choose the game that you want to play.");
ad(elt1_,txt5_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt6_=tx(_doc,"Besides, dialog boxes are accessible by certain sub menus.");
ad(elt1_,txt6_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt7_=tx(_doc,"For playing in \"Multi players mode\", you must choose the number of players then create a server which lets other people play with you.");
ad(elt1_,txt7_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt8_=tx(_doc,"You have to give your IP address to other players.");
ad(elt1_,txt8_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt9_=tx(_doc,"In \"Multi players mode\", each person must choose a place before beginning.");
ad(elt1_,txt9_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt10_=tx(_doc,"Information about net deal is visible only all players (not robots) are in the same team for avoiding cheeting.");
ad(elt1_,txt10_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt11_=tx(_doc,"Other people can join after the end of the deal.");
ad(elt1_,txt11_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
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
