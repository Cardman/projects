package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards19{
private HelpCards19(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"A slam is a declaring when, the declarer (the player who has declared it) must win all tricks, that is says win the cards of the opponents of the player.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt1_=tx(_doc,"For declaring it, the player must declare a bid other than \"Pass\".");
ad(elt1_,txt1_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"ol");
Element elt6_=el(_doc,"li");
Text txt2_=tx(_doc,"If the taker has declared \"Take\" or \"Guard\":");
ad(elt6_,txt2_);
Element elt7_=el(_doc,"br");
ad(elt6_,elt7_);
Text txt3_=tx(_doc,"&#160;&#160;The dog is shown to all players.");
ad(elt6_,txt3_);
Element elt8_=el(_doc,"br");
ad(elt6_,elt8_);
Text txt4_=tx(_doc,"&#160;&#160;The taker takes the \"dog\" cards.");
ad(elt6_,txt4_);
Element elt9_=el(_doc,"br");
ad(elt6_,elt9_);
Text txt5_=tx(_doc,"&#160;&#160;For having the same number of cards than the other players, the taker discards from the hand the same number of cards as the dog contained.");
ad(elt6_,txt5_);
Element elt10_=el(_doc,"br");
ad(elt6_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt6_,elt11_);
Text txt6_=tx(_doc,"&#160;&#160;The taker cannot discard trump cards(The Excuse is, here, a trump card.) or kings.");
ad(elt6_,txt6_);
Element elt12_=el(_doc,"br");
ad(elt6_,elt12_);
Text txt7_=tx(_doc,"&#160;&#160;For trump cards, if the taker has a sum of number of trump cards (The Excuse is, here, a trump card.) and number of kings(trump cards + kings)greater than the number of cards per player,");
ad(elt6_,txt7_);
Element elt13_=el(_doc,"br");
ad(elt6_,elt13_);
Text txt8_=tx(_doc,"&#160;&#160;then the taker shows the discarded trump cards to the defense,");
ad(elt6_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt6_,elt14_);
Text txt9_=tx(_doc,"&#160;&#160;in this case, the taker can now discard them from the hand.");
ad(elt6_,txt9_);
Element elt15_=el(_doc,"br");
ad(elt6_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt6_,elt16_);
Text txt10_=tx(_doc,"&#160;&#160;The discarded cards from the hand are hidden for the defense.");
ad(elt6_,txt10_);
Element elt17_=el(_doc,"br");
ad(elt6_,elt17_);
Text txt11_=tx(_doc,"&#160;&#160;After discarding cards to the dog, the taker can declare a slam.");
ad(elt6_,txt11_);
Element elt18_=el(_doc,"br");
ad(elt6_,elt18_);
ad(elt5_,elt6_);
Element elt19_=el(_doc,"li");
Text txt12_=tx(_doc,"If the taker has declared \"Guard without\" or \"Guard against\":");
ad(elt19_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt19_,elt20_);
Text txt13_=tx(_doc,"&#160;&#160;The dog stays hidden.");
ad(elt19_,txt13_);
Element elt21_=el(_doc,"br");
ad(elt19_,elt21_);
Text txt14_=tx(_doc,"&#160;&#160;If the taker has declared \"Guard without\", then the dog belongs to the taker.");
ad(elt19_,txt14_);
Element elt22_=el(_doc,"br");
ad(elt19_,elt22_);
Text txt15_=tx(_doc,"&#160;&#160;If the taker has declared \"Guard against\", then the dog belongs to the defense.");
ad(elt19_,txt15_);
Element elt23_=el(_doc,"br");
ad(elt19_,elt23_);
Text txt16_=tx(_doc,"&#160;&#160;The taker can declare a slam.");
ad(elt19_,txt16_);
Element elt24_=el(_doc,"br");
ad(elt19_,elt24_);
ad(elt5_,elt19_);
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
