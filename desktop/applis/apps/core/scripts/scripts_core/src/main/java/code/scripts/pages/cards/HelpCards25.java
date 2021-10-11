package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards25{
private HelpCards25(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Here is changes with tarot played by 4 players:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt1_=tx(_doc,"dealing");
ad(elt5_,txt1_);
Element elt6_=el(_doc,"br");
ad(elt5_,elt6_);
Element elt7_=el(_doc,"br");
ad(elt5_,elt7_);
Element elt8_=el(_doc,"ol");
Element elt9_=el(_doc,"li");
Text txt2_=tx(_doc,"If the taker plays against the 4 other players:");
ad(elt9_,txt2_);
Element elt10_=el(_doc,"br");
ad(elt9_,elt10_);
Text txt3_=tx(_doc,"&#160;&#160;&#160;each player owns 14 cards and 8 cards make up the dog,");
ad(elt9_,txt3_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
Text txt4_=tx(_doc,"&#160;&#160;&#160;cards are dealt 2 by 2.");
ad(elt9_,txt4_);
Element elt12_=el(_doc,"br");
ad(elt9_,elt12_);
ad(elt8_,elt9_);
Element elt13_=el(_doc,"li");
Text txt5_=tx(_doc,"Else:");
ad(elt13_,txt5_);
Element elt14_=el(_doc,"br");
ad(elt13_,elt14_);
Text txt6_=tx(_doc,"&#160;&#160;&#160;each player owns 15 cards and 3 cards make up the dog,");
ad(elt13_,txt6_);
Element elt15_=el(_doc,"br");
ad(elt13_,elt15_);
Text txt7_=tx(_doc,"&#160;&#160;&#160;cards are dealt 3 by 3.");
ad(elt13_,txt7_);
Element elt16_=el(_doc,"br");
ad(elt13_,elt16_);
ad(elt8_,elt13_);
ad(elt5_,elt8_);
ad(elt4_,elt5_);
Element elt17_=el(_doc,"li");
Text txt8_=tx(_doc,"Repartition of teams");
ad(elt17_,txt8_);
Element elt18_=el(_doc,"br");
ad(elt17_,elt18_);
Element elt19_=el(_doc,"br");
ad(elt17_,elt19_);
Element elt20_=el(_doc,"ol");
Element elt21_=el(_doc,"li");
Text txt9_=tx(_doc,"First variant, the most played one:");
ad(elt21_,txt9_);
Element elt22_=el(_doc,"br");
ad(elt21_,elt22_);
Text txt10_=tx(_doc,"&#160;&#160;&#160;After all players having declared,");
ad(elt21_,txt10_);
Element elt23_=el(_doc,"br");
ad(elt21_,elt23_);
Text txt11_=tx(_doc,"&#160;&#160;&#160;&#160;the taker calls the king of a suit,");
ad(elt21_,txt11_);
Element elt24_=el(_doc,"br");
ad(elt21_,elt24_);
Text txt12_=tx(_doc,"&#160;&#160;&#160;&#160;if the taker has the 4 kings, the taker calls the queen of a suit,");
ad(elt21_,txt12_);
Element elt25_=el(_doc,"br");
ad(elt21_,elt25_);
Text txt13_=tx(_doc,"&#160;&#160;&#160;&#160;if the taker has the 4 queens and the 4 kings, the taker calls the knight of a suit,");
ad(elt21_,txt13_);
Element elt26_=el(_doc,"br");
ad(elt21_,elt26_);
Text txt14_=tx(_doc,"&#160;&#160;&#160;&#160;if the taker has the 4 knights, the 4 queens and the 4 kings, the taker calls the jack of a suit.");
ad(elt21_,txt14_);
Element elt27_=el(_doc,"br");
ad(elt21_,elt27_);
Text txt15_=tx(_doc,"&#160;&#160;&#160;&#160;In function by rules, the call can be done before or after the dog.");
ad(elt21_,txt15_);
Element elt28_=el(_doc,"br");
ad(elt21_,elt28_);
ad(elt20_,elt21_);
Element elt29_=el(_doc,"li");
Text txt16_=tx(_doc,"Second variant:");
ad(elt29_,txt16_);
Element elt30_=el(_doc,"br");
ad(elt29_,elt30_);
Text txt17_=tx(_doc,"&#160;&#160;&#160;After all players having declared,");
ad(elt29_,txt17_);
Element elt31_=el(_doc,"br");
ad(elt29_,elt31_);
Text txt18_=tx(_doc,"&#160;&#160;&#160;&#160;the taker calls a character of a suit.");
ad(elt29_,txt18_);
Element elt32_=el(_doc,"br");
ad(elt29_,elt32_);
Text txt19_=tx(_doc,"&#160;&#160;&#160;&#160;In function by rules, the call can be done before or after the dog.");
ad(elt29_,txt19_);
Element elt33_=el(_doc,"br");
ad(elt29_,elt33_);
ad(elt20_,elt29_);
Element elt34_=el(_doc,"li");
Text txt20_=tx(_doc,"Third variant:");
ad(elt34_,txt20_);
Element elt35_=el(_doc,"br");
ad(elt34_,elt35_);
Text txt21_=tx(_doc,"&#160;&#160;&#160;If there is no call, the taker plays against the other players.");
ad(elt34_,txt21_);
ad(elt20_,elt34_);
ad(elt17_,elt20_);
ad(elt4_,elt17_);
Element elt37_=el(_doc,"li");
Text txt22_=tx(_doc,"Declaring handfuls:");
ad(elt37_,txt22_);
Element elt38_=el(_doc,"br");
ad(elt37_,elt38_);
Element elt39_=el(_doc,"br");
ad(elt37_,elt39_);
Element elt40_=el(_doc,"ol");
Element elt41_=el(_doc,"li");
Text txt23_=tx(_doc,"simple handful: 8 trump cards (The Excuse is a trump card).");
ad(elt41_,txt23_);
ad(elt40_,elt41_);
Element elt42_=el(_doc,"li");
Text txt24_=tx(_doc,"double handful: 10 trump cards (The Excuse is a trump card).");
ad(elt42_,txt24_);
ad(elt40_,elt42_);
Element elt43_=el(_doc,"li");
Text txt25_=tx(_doc,"triple handful: 12 trump cards (The Excuse is a trump card).");
ad(elt43_,txt25_);
ad(elt40_,elt43_);
ad(elt37_,elt40_);
ad(elt4_,elt37_);
Element elt44_=el(_doc,"li");
Text txt26_=tx(_doc,"Calculation of scores at the end of deal:");
ad(elt44_,txt26_);
Element elt45_=el(_doc,"br");
ad(elt44_,elt45_);
Element elt46_=el(_doc,"br");
ad(elt44_,elt46_);
Text txt27_=tx(_doc,"&#160;&#160;Amount of points for a deal: 91 points");
ad(elt44_,txt27_);
Element elt47_=el(_doc,"br");
ad(elt44_,elt47_);
Text txt28_=tx(_doc,"&#160;&#160;Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker");
ad(elt44_,txt28_);
Element elt48_=el(_doc,"br");
ad(elt44_,elt48_);
Text txt29_=tx(_doc,"&#160;&#160;Score of the taker without declaring points = +-25pts + Difference + Ace to end");
ad(elt44_,txt29_);
Element elt49_=el(_doc,"br");
ad(elt44_,elt49_);
Text txt30_=tx(_doc,"&#160;&#160;Declaring = Handfuls + Slam + Possible Miseres");
ad(elt44_,txt30_);
Element elt50_=el(_doc,"br");
ad(elt44_,elt50_);
Text txt31_=tx(_doc,"&#160;&#160;Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)");
ad(elt44_,txt31_);
Element elt51_=el(_doc,"br");
ad(elt44_,elt51_);
Text txt32_=tx(_doc,"&#160;&#160;If the taker has a partner");
ad(elt44_,txt32_);
Element elt52_=el(_doc,"br");
ad(elt44_,elt52_);
Text txt33_=tx(_doc,"&#160;&#160;&#160;Full score of the taker = -2 x Full score total of a defender");
ad(elt44_,txt33_);
Element elt53_=el(_doc,"br");
ad(elt44_,elt53_);
Text txt34_=tx(_doc,"&#160;&#160;Else");
ad(elt44_,txt34_);
Element elt54_=el(_doc,"br");
ad(elt44_,elt54_);
Text txt35_=tx(_doc,"&#160;&#160;&#160;Full score of the taker = -4 x Full score total of a defender");
ad(elt44_,txt35_);
Element elt55_=el(_doc,"br");
ad(elt44_,elt55_);
Text txt36_=tx(_doc,"&#160;&#160;Full score of the possible called player = - Full score total of a defender");
ad(elt44_,txt36_);
Element elt56_=el(_doc,"br");
ad(elt44_,elt56_);
Element elt57_=el(_doc,"br");
ad(elt44_,elt57_);
Text txt37_=tx(_doc,"&#160;If the taker score a not integer points, then the difference is rounded to the greater integer.");
ad(elt44_,txt37_);
Element elt58_=el(_doc,"br");
ad(elt44_,elt58_);
ad(elt4_,elt44_);
ad(elt1_,elt4_);
Element elt60_=el(_doc,"br");
ad(elt1_,elt60_);
Text txt39_=tx(_doc,"&#160;Examples:");
ad(elt1_,txt39_);
Element elt61_=el(_doc,"br");
ad(elt1_,elt61_);
Element elt62_=el(_doc,"br");
ad(elt1_,elt62_);
Element elt63_=el(_doc,"ol");
Element elt64_=el(_doc,"li");
Text txt40_=tx(_doc,"If the taker scores 43,5 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
ad(elt64_,txt40_);
Element elt65_=el(_doc,"br");
ad(elt64_,elt65_);
Text txt41_=tx(_doc,"&#160;&#160;if the taker has a partner,");
ad(elt64_,txt41_);
Element elt66_=el(_doc,"br");
ad(elt64_,elt66_);
Text txt42_=tx(_doc,"&#160;&#160;if none of 5 players has played the Small at the last trick,");
ad(elt64_,txt42_);
Element elt67_=el(_doc,"br");
ad(elt64_,elt67_);
Text txt43_=tx(_doc,"&#160;&#160;then the taker wins the deal by 3 points and scores ((25+(44-41)+0)x1+0+0+0)x2=56 points,");
ad(elt64_,txt43_);
Element elt68_=el(_doc,"br");
ad(elt64_,elt68_);
Text txt44_=tx(_doc,"&#160;&#160;&#160;the called player scores (25+(44-41)+0)x1+0+0+0=28 points,");
ad(elt64_,txt44_);
Element elt69_=el(_doc,"br");
ad(elt64_,elt69_);
Text txt45_=tx(_doc,"&#160;&#160;&#160;each defender scores ((-25-(44-41)+0)x1+0+0+0)X1=-28 points.");
ad(elt64_,txt45_);
Element elt70_=el(_doc,"br");
ad(elt64_,elt70_);
ad(elt63_,elt64_);
Element elt71_=el(_doc,"li");
Text txt46_=tx(_doc,"If the taker scores 43,5 points with 1 Oudler in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
ad(elt71_,txt46_);
Element elt72_=el(_doc,"br");
ad(elt71_,elt72_);
Text txt47_=tx(_doc,"&#160;&#160;if none of 5 players has played the Small at the last trick,");
ad(elt71_,txt47_);
Element elt73_=el(_doc,"br");
ad(elt71_,elt73_);
Text txt48_=tx(_doc,"&#160;&#160;if the taker has no partner,");
ad(elt71_,txt48_);
Element elt74_=el(_doc,"br");
ad(elt71_,elt74_);
Text txt49_=tx(_doc,"&#160;&#160;then the taker looses the deal by 8 points and scores ((-25+(43-51)+0)x1+0+0+0)x4=-132 points, and each defender scores ((25-(43-51)+0)x1+0+0+0)X1=33 points.");
ad(elt71_,txt49_);
Element elt75_=el(_doc,"br");
ad(elt71_,elt75_);
ad(elt63_,elt71_);
ad(elt1_,elt63_);
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
