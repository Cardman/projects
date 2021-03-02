package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards118{
private HelpCards118(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Interactions avec les cartes raccourci F5");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu \"Interactions avec les cartes\" permet de choisir entre");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"Cliquer sur une carte pour la jouer.");
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"Survoler une carte et sortir la fl&#232;che vers le tapis ou en dehors de la fen&#234;tre.");
ad(elt6_,txt3_);
ad(elt4_,elt6_);
ad(elt1_,elt4_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt4_=tx(_doc,"Le cas de la belote et le cas du tarot sont s&#233;par&#233;s.");
ad(elt1_,txt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"Cette action met en place une bo&#238;te de dialogue.");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt6_=tx(_doc,"Cette bo&#238;te de dialogue permet de choisir entre:");
ad(elt1_,txt6_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"ol");
Element elt13_=el(_doc,"li");
Text txt7_=tx(_doc,"Cliquer sur une carte pour la jouer.");
ad(elt13_,txt7_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"li");
Text txt8_=tx(_doc,"Survoler une carte et sortir la fl&#232;che vers le tapis ou en dehors de la fen&#234;tre.");
ad(elt14_,txt8_);
ad(elt12_,elt14_);
ad(elt1_,elt12_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Text txt9_=tx(_doc,"Le cas de la belote et le cas du tarot sont s&#233;par&#233;s.");
ad(elt1_,txt9_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
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
