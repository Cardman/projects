package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards78{
private HelpCards78(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Voici les variantes de fin de partie:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"&#160;Le score temporaire total du preneur vaut le score d&#233;finitif total du preneur.");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt2_=tx(_doc,"Exemples pour une fin de partie diff&#233;rente:");
ad(elt1_,txt2_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"ol");
Element elt9_=el(_doc,"li");
Text txt3_=tx(_doc,"Si l''&#233;quipe du preneur marque 75 points dans ses plis,");
ad(elt9_,txt3_);
Element elt10_=el(_doc,"br");
ad(elt9_,elt10_);
Text txt4_=tx(_doc,"&#160;&#160;si l''&#233;quipe du preneur fait le dernier pli,");
ad(elt9_,txt4_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
Text txt5_=tx(_doc,"&#160;&#160;si la d&#233;fense poss&#232;de l''annonce belote-rebelote,");
ad(elt9_,txt5_);
Element elt12_=el(_doc,"br");
ad(elt9_,elt12_);
Text txt6_=tx(_doc,"&#160;&#160;alors le preneur a un score temporaire de 85 points, ce qui est en-dessous de 91 points.");
ad(elt9_,txt6_);
Element elt13_=el(_doc,"br");
ad(elt9_,elt13_);
Text txt7_=tx(_doc,"&#160;&#160;Donc le preneur perd la partie et marque 85 points de score ainsi que son partenaite, et chaque d&#233;fenseur marque 97 points de score.");
ad(elt9_,txt7_);
Element elt14_=el(_doc,"br");
ad(elt9_,elt14_);
ad(elt8_,elt9_);
Element elt15_=el(_doc,"li");
Text txt8_=tx(_doc,"Si l''&#233;quipe du preneur marque 75 points dans ses plis,");
ad(elt15_,txt8_);
Element elt16_=el(_doc,"br");
ad(elt15_,elt16_);
Text txt9_=tx(_doc,"&#160;&#160;si l''&#233;quipe du preneur fait le dernier pli,");
ad(elt15_,txt9_);
Element elt17_=el(_doc,"br");
ad(elt15_,elt17_);
Text txt10_=tx(_doc,"&#160;&#160;alors le preneur a un score temporaire de 85 points, ce qui est au-dessus de 81 points.");
ad(elt15_,txt10_);
Element elt18_=el(_doc,"br");
ad(elt15_,elt18_);
Text txt11_=tx(_doc,"&#160;&#160;Donc le preneur gagne la partie et marque 85 points de score ainsi que son partenaite, et chaque d&#233;fenseur marque 77 points de score.");
ad(elt15_,txt11_);
Element elt19_=el(_doc,"br");
ad(elt15_,elt19_);
ad(elt8_,elt15_);
Element elt20_=el(_doc,"li");
Text txt12_=tx(_doc,"Si l''&#233;quipe du preneur fait tous les plis,");
ad(elt20_,txt12_);
Element elt21_=el(_doc,"br");
ad(elt20_,elt21_);
Text txt13_=tx(_doc,"&#160;&#160;si l''&#233;quipe du preneur poss&#232;de l''annonce belote-rebelote,");
ad(elt20_,txt13_);
Element elt22_=el(_doc,"br");
ad(elt20_,elt22_);
Text txt14_=tx(_doc,"&#160;&#160;alors le preneur a un score temporaire de 182 points, ce qui est au-dessus de 81 points.");
ad(elt20_,txt14_);
Element elt23_=el(_doc,"br");
ad(elt20_,elt23_);
Text txt15_=tx(_doc,"&#160;&#160;Donc le preneur gagne la partie et marque 282 points de score ainsi que son partenaite, et chaque d&#233;fenseur marque z&#233;ro points de score.");
ad(elt20_,txt15_);
Element elt24_=el(_doc,"br");
ad(elt20_,elt24_);
ad(elt8_,elt20_);
ad(elt1_,elt8_);
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
