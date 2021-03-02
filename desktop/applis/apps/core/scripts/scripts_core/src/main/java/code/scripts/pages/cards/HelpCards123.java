package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards123{
private HelpCards123(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Temporisation du jeu raccourci F4");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"L''action du sous menu \"Temporisation du jeu\" permet de changer");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt2_=tx(_doc,"les d&#233;lais entre deux cartes cons&#233;cutivement jou&#233;es par les \"robots\".");
ad(elt5_,txt2_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"les d&#233;lais entre deux plis ou tours cons&#233;cutivement jou&#233;s.");
ad(elt6_,txt3_);
ad(elt4_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"les d&#233;lais entre deux contrats cons&#233;cutivement jou&#233;s par les \"robots\".");
ad(elt7_,txt4_);
ad(elt4_,elt7_);
ad(elt1_,elt4_);
Element elt8_=el(_doc,"br");
ad(elt1_,elt8_);
Text txt5_=tx(_doc,"Cette action met en place une bo&#238;te de dialogue.");
ad(elt1_,txt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Text txt6_=tx(_doc,"Cette bo&#238;te de dialogue permet de fixer les d&#233;lais entre:");
ad(elt1_,txt6_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"ol");
Element elt13_=el(_doc,"li");
Text txt7_=tx(_doc,"Deux cartes cons&#233;cutives.");
ad(elt13_,txt7_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"li");
Text txt8_=tx(_doc,"Deux contrats cons&#233;cutifs.");
ad(elt14_,txt8_);
ad(elt12_,elt14_);
Element elt15_=el(_doc,"li");
Text txt9_=tx(_doc,"Deux plis cons&#233;cutifs.");
ad(elt15_,txt9_);
ad(elt12_,elt15_);
ad(elt1_,elt12_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt10_=tx(_doc,"De plus, cette bo&#238;te de dialogue permet");
ad(elt1_,txt10_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt11_=tx(_doc,"d''activer ou de d&#233;sactiver les d&#233;lais entre deux plis cons&#233;cutifs,");
ad(elt1_,txt11_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt12_=tx(_doc,"sans cliquer sur les boutons de la fen&#234;tre plac&#233;s en bas &#224; droite de la fen&#234;tre.");
ad(elt1_,txt12_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
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
