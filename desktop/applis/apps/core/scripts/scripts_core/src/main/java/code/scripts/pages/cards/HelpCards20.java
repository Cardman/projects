package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards20{
private HelpCards20(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Bids determine the deal.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"Each player declares a bid, in the counter clock wise, by beginning by the player at the right of the dealer.");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Text txt3_=tx(_doc,"A player must either declare \"pass\", or declare a bid greater than the previous bids. Each player has to declare once only.");
ad(elt1_,txt3_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Text txt5_=tx(_doc,"At tarot, there are 5 bids, in the order from the lowest to the greatest:");
ad(elt1_,txt5_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Element elt12_=el(_doc,"ol");
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"Pass");
ad(elt13_,txt6_);
ad(elt12_,elt13_);
Element elt14_=el(_doc,"li");
Text txt7_=tx(_doc,"Take");
ad(elt14_,txt7_);
ad(elt12_,elt14_);
Element elt15_=el(_doc,"li");
Text txt8_=tx(_doc,"Guard");
ad(elt15_,txt8_);
ad(elt12_,elt15_);
Element elt16_=el(_doc,"li");
Text txt9_=tx(_doc,"Guard without");
ad(elt16_,txt9_);
ad(elt12_,elt16_);
Element elt17_=el(_doc,"li");
Text txt10_=tx(_doc,"Guard against");
ad(elt17_,txt10_);
ad(elt12_,elt17_);
ad(elt1_,elt12_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt11_=tx(_doc,"After that all players have bid, the player having declared the strongest bid becomes the taker.");
ad(elt1_,txt11_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt12_=tx(_doc,"In function of the declared bid by the taker, about the dog, one processes by the following way:");
ad(elt1_,txt12_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"ol");
Element elt22_=el(_doc,"li");
Text txt13_=tx(_doc,"Take: the taker takes the dog and shows it to the other players.");
ad(elt22_,txt13_);
ad(elt21_,elt22_);
Element elt23_=el(_doc,"li");
Text txt14_=tx(_doc,"Guard: the taker takes the dog and shows it to the other players.");
ad(elt23_,txt14_);
ad(elt21_,elt23_);
Element elt24_=el(_doc,"li");
Text txt15_=tx(_doc,"Guard without: the taker takes the dog but the dog stays hidden.");
ad(elt24_,txt15_);
ad(elt21_,elt24_);
Element elt25_=el(_doc,"li");
Text txt16_=tx(_doc,"Guard against: the taker leaves the dog to the opponents of the player and the dog stays hidden.");
ad(elt25_,txt16_);
ad(elt21_,elt25_);
ad(elt1_,elt21_);
Element elt26_=el(_doc,"br");
ad(elt1_,elt26_);
Text txt17_=tx(_doc,"Meanings:");
ad(elt1_,txt17_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Element elt30_=el(_doc,"ol");
Element elt31_=el(_doc,"li");
Text txt18_=tx(_doc,"The opponents of the taker are the defense.");
ad(elt31_,txt18_);
ad(elt30_,elt31_);
Element elt32_=el(_doc,"li");
Text txt19_=tx(_doc,"There is only one round of bidding.(Each player declares only once.)");
ad(elt32_,txt19_);
ad(elt30_,elt32_);
ad(elt1_,elt30_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Text txt20_=tx(_doc,"Examples:");
ad(elt1_,txt20_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Element elt35_=el(_doc,"br");
ad(elt1_,elt35_);
Element elt36_=el(_doc,"ol");
Element elt37_=el(_doc,"li");
Text txt21_=tx(_doc,"If the 1st player, who must speak, declares \"pass\",");
ad(elt37_,txt21_);
Element elt38_=el(_doc,"br");
ad(elt37_,elt38_);
Text txt22_=tx(_doc,"&#160;&#160;if the 2nd declares \"Take\",");
ad(elt37_,txt22_);
Element elt39_=el(_doc,"br");
ad(elt37_,elt39_);
Text txt23_=tx(_doc,"&#160;&#160;then the 3rd cannot declare \"Take\", but the 3rd can declare \"Guard\" or greater.");
ad(elt37_,txt23_);
Element elt40_=el(_doc,"br");
ad(elt37_,elt40_);
ad(elt36_,elt37_);
Element elt41_=el(_doc,"li");
Text txt24_=tx(_doc,"If the 1st player, who must speak, declares \"pass\",");
ad(elt41_,txt24_);
Element elt42_=el(_doc,"br");
ad(elt41_,elt42_);
Text txt25_=tx(_doc,"&#160;&#160;if the 2nd declares \"Take\",");
ad(elt41_,txt25_);
Element elt43_=el(_doc,"br");
ad(elt41_,elt43_);
Text txt26_=tx(_doc,"&#160;&#160;if the 3rd declares \"pass\",");
ad(elt41_,txt26_);
Element elt44_=el(_doc,"br");
ad(elt41_,elt44_);
Text txt27_=tx(_doc,"&#160;&#160;if the last one declares \"Guard without\",");
ad(elt41_,txt27_);
Element elt45_=el(_doc,"br");
ad(elt41_,elt45_);
Text txt28_=tx(_doc,"&#160;&#160;then the taker is the last player.");
ad(elt41_,txt28_);
Element elt46_=el(_doc,"br");
ad(elt41_,elt46_);
ad(elt36_,elt41_);
Element elt47_=el(_doc,"li");
Text txt29_=tx(_doc,"If the 1st player, who must speak, declares \"pass\",");
ad(elt47_,txt29_);
Element elt48_=el(_doc,"br");
ad(elt47_,elt48_);
Text txt30_=tx(_doc,"&#160;&#160;if the 2nd declares \"Guard against\",");
ad(elt47_,txt30_);
Element elt49_=el(_doc,"br");
ad(elt47_,elt49_);
Text txt31_=tx(_doc,"&#160;&#160;then, the two other players must declares \"pass\".");
ad(elt47_,txt31_);
Element elt50_=el(_doc,"br");
ad(elt47_,elt50_);
ad(elt36_,elt47_);
ad(elt1_,elt36_);
Element elt51_=el(_doc,"br");
ad(elt1_,elt51_);
Text txt32_=tx(_doc,"The taker plays against the three other players.");
ad(elt1_,txt32_);
Element elt52_=el(_doc,"br");
ad(elt1_,elt52_);
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
