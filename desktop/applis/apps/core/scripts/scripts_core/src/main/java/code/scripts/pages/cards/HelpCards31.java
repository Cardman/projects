package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards31{
private HelpCards31(){}
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
Text txt2_=tx(_doc,"&#160;&#160;each player owns 24 cards and 6 cards make up the dog,");
ad(elt5_,txt2_);
Element elt7_=el(_doc,"br");
ad(elt5_,elt7_);
Text txt3_=tx(_doc,"&#160;&#160;&#160;cards are dealt 3 by 3.");
ad(elt5_,txt3_);
Element elt8_=el(_doc,"br");
ad(elt5_,elt8_);
ad(elt4_,elt5_);
Element elt9_=el(_doc,"li");
Text txt4_=tx(_doc,"Repartition of teams");
ad(elt9_,txt4_);
Element elt10_=el(_doc,"br");
ad(elt9_,elt10_);
Text txt5_=tx(_doc,"&#160;&#160;The taker plays against the other players.");
ad(elt9_,txt5_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
Element elt12_=el(_doc,"br");
ad(elt9_,elt12_);
ad(elt4_,elt9_);
Element elt13_=el(_doc,"li");
Text txt6_=tx(_doc,"Declaring handfuls:");
ad(elt13_,txt6_);
Element elt14_=el(_doc,"br");
ad(elt13_,elt14_);
Element elt15_=el(_doc,"ol");
Element elt16_=el(_doc,"li");
Text txt7_=tx(_doc,"simple handful: 12 trump cards (The Excuse is a trump card).");
ad(elt16_,txt7_);
ad(elt15_,elt16_);
Element elt17_=el(_doc,"li");
Text txt8_=tx(_doc,"double handful: 15 trump cards (The Excuse is a trump card).");
ad(elt17_,txt8_);
ad(elt15_,elt17_);
Element elt18_=el(_doc,"li");
Text txt9_=tx(_doc,"triple handful: 18 trump cards (The Excuse is a trump card).");
ad(elt18_,txt9_);
ad(elt15_,elt18_);
ad(elt13_,elt15_);
Element elt19_=el(_doc,"br");
ad(elt13_,elt19_);
ad(elt4_,elt13_);
Element elt20_=el(_doc,"li");
Text txt10_=tx(_doc,"Calculation of scores at the end of deal:");
ad(elt20_,txt10_);
Element elt21_=el(_doc,"br");
ad(elt20_,elt21_);
Text txt11_=tx(_doc,"&#160;&#160;Amount of points for a deal: 91 points");
ad(elt20_,txt11_);
Element elt22_=el(_doc,"br");
ad(elt20_,elt22_);
Text txt12_=tx(_doc,"&#160;&#160;Difference = Nb pts scored by the taker - Nb pts needed for winning the deal, for the taker");
ad(elt20_,txt12_);
Element elt23_=el(_doc,"br");
ad(elt20_,elt23_);
Text txt13_=tx(_doc,"&#160;&#160;Score of the taker without declaring points = +-25pts + Difference + Ace to end");
ad(elt20_,txt13_);
Element elt24_=el(_doc,"br");
ad(elt20_,elt24_);
Text txt14_=tx(_doc,"&#160;&#160;Declaring = Handfuls + Slam + Possible Miseres");
ad(elt20_,txt14_);
Element elt25_=el(_doc,"br");
ad(elt20_,elt25_);
Text txt15_=tx(_doc,"&#160;&#160;Full score of a defender = - (Score of the taker without declaring points x Rate of bid + Declaring taker - Declaring defense)");
ad(elt20_,txt15_);
Element elt26_=el(_doc,"br");
ad(elt20_,elt26_);
Text txt16_=tx(_doc,"&#160;&#160;Full score of the taker = -2 x Full score total of a defender");
ad(elt20_,txt16_);
Element elt27_=el(_doc,"br");
ad(elt20_,elt27_);
ad(elt4_,elt20_);
ad(elt1_,elt4_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Text txt17_=tx(_doc,"&#160;If the taker score a not integer points,");
ad(elt1_,txt17_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Text txt18_=tx(_doc,"&#160;&#160;then the difference is rounded to the greater integer.");
ad(elt1_,txt18_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Text txt19_=tx(_doc,"&#160;Examples:");
ad(elt1_,txt19_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Element elt34_=el(_doc,"ol");
Element elt35_=el(_doc,"li");
Text txt20_=tx(_doc,"If the taker scores 43,5 points with 2 Oudlers in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
ad(elt35_,txt20_);
Element elt36_=el(_doc,"br");
ad(elt35_,elt36_);
Text txt21_=tx(_doc,"&#160;&#160;if none of 3 players has played the Small at the last trick,");
ad(elt35_,txt21_);
Element elt37_=el(_doc,"br");
ad(elt35_,elt37_);
Text txt22_=tx(_doc,"&#160;&#160;then the taker wins the deal by 3 points and scores ((25+(44-41)+0)x1+0+0+0)x2=56 points,");
ad(elt35_,txt22_);
Element elt38_=el(_doc,"br");
ad(elt35_,elt38_);
Text txt23_=tx(_doc,"&#160;&#160;each defender scores ((-25-(44-41)+0)x1+0+0+0)X1=-28 points.");
ad(elt35_,txt23_);
Element elt39_=el(_doc,"br");
ad(elt35_,elt39_);
ad(elt34_,elt35_);
Element elt40_=el(_doc,"li");
Text txt24_=tx(_doc,"If the taker scores 43,5 points with 1 Oudler in the tricks, by declaring \"Take\", without declaring handfuls for all players, without slam,");
ad(elt40_,txt24_);
Element elt41_=el(_doc,"br");
ad(elt40_,elt41_);
Text txt25_=tx(_doc,"&#160;&#160;if none of 3 players has played the Small at the last trick,");
ad(elt40_,txt25_);
Element elt42_=el(_doc,"br");
ad(elt40_,elt42_);
Text txt26_=tx(_doc,"&#160;&#160;if the taker has no partner,");
ad(elt40_,txt26_);
Element elt43_=el(_doc,"br");
ad(elt40_,elt43_);
Text txt27_=tx(_doc,"&#160;&#160;then the taker looses the deal by 8 points and scores ((-25+(43-51)+0)x1+0+0+0)x2=-66 points, and each defender scores ((25-(43-51)+0)x1+0+0+0)X1=33 points.");
ad(elt40_,txt27_);
Element elt44_=el(_doc,"br");
ad(elt40_,elt44_);
ad(elt34_,elt40_);
ad(elt1_,elt34_);
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
