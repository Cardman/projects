package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards60{
private HelpCards60(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The subs menu \"Training\" contains the sub menus about games for training.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"For each different number of players (three, four, five or six), there are four training modes at tarot:");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"ol");
Element elt6_=el(_doc,"li");
Text txt2_=tx(_doc,"Haunt the Small");
ad(elt6_,txt2_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,"li");
Text txt3_=tx(_doc,"Save the Small");
ad(elt7_,txt3_);
ad(elt5_,elt7_);
Element elt8_=el(_doc,"li");
Text txt4_=tx(_doc,"Lead the Small at the last trick");
ad(elt8_,txt4_);
ad(elt5_,elt8_);
ad(elt1_,elt5_);
Element elt9_=el(_doc,"br");
ad(elt1_,elt9_);
Text txt5_=tx(_doc,"This is developped by editing random numbers.");
ad(elt1_,txt5_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt6_=tx(_doc,"A random number of trump cards is edited following a binomial conditionnal law; for keeping the events with the same probability.");
ad(elt1_,txt6_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt8_=tx(_doc,"Intervals of numbers of trump cards to be edited are the following one (The Small does not belong to the randomly edited trump cards.):");
ad(elt1_,txt8_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Element elt16_=el(_doc,"ol");
Element elt17_=el(_doc,"li");
Text txt9_=tx(_doc,"At three players:");
ad(elt17_,txt9_);
Element elt18_=el(_doc,"br");
ad(elt17_,elt18_);
Element elt19_=el(_doc,"ol");
Element elt20_=el(_doc,"li");
Text txt10_=tx(_doc,"Haunt the Small:");
ad(elt20_,txt10_);
Element elt21_=el(_doc,"br");
ad(elt20_,elt21_);
Text txt11_=tx(_doc,"&#160;The minimum is worth fifteen and the maximum is worth twenty one.");
ad(elt20_,txt11_);
ad(elt19_,elt20_);
Element elt22_=el(_doc,"li");
Text txt12_=tx(_doc,"Save the Small:");
ad(elt22_,txt12_);
Element elt23_=el(_doc,"br");
ad(elt22_,elt23_);
Text txt13_=tx(_doc,"&#160;The minimum is worth one and the maximum is worth five.");
ad(elt22_,txt13_);
ad(elt19_,elt22_);
Element elt24_=el(_doc,"li");
Text txt14_=tx(_doc,"Lead the Small at the last trick:");
ad(elt24_,txt14_);
Element elt25_=el(_doc,"br");
ad(elt24_,elt25_);
Text txt15_=tx(_doc,"&#160;The minimum is worth fourteen and the maximum is worth twenty one.");
ad(elt24_,txt15_);
ad(elt19_,elt24_);
ad(elt17_,elt19_);
ad(elt16_,elt17_);
Element elt26_=el(_doc,"li");
Text txt16_=tx(_doc,"At four players:");
ad(elt26_,txt16_);
Element elt27_=el(_doc,"br");
ad(elt26_,elt27_);
Element elt28_=el(_doc,"ol");
Element elt29_=el(_doc,"li");
Text txt17_=tx(_doc,"Haunt the Small:");
ad(elt29_,txt17_);
Element elt30_=el(_doc,"br");
ad(elt29_,elt30_);
Text txt18_=tx(_doc,"&#160;The minimum is worth thirteen and the maximum is worth eighteen.");
ad(elt29_,txt18_);
ad(elt28_,elt29_);
Element elt31_=el(_doc,"li");
Text txt19_=tx(_doc,"Save the Small:");
ad(elt31_,txt19_);
Element elt32_=el(_doc,"br");
ad(elt31_,elt32_);
Text txt20_=tx(_doc,"&#160;The minimum is worth one and the maximum is worth four.");
ad(elt31_,txt20_);
ad(elt28_,elt31_);
Element elt33_=el(_doc,"li");
Text txt21_=tx(_doc,"Lead the Small at the last trick:");
ad(elt33_,txt21_);
Element elt34_=el(_doc,"br");
ad(elt33_,elt34_);
Text txt22_=tx(_doc,"&#160;The minimum is worth twelve and the maximum is worth seventeen.");
ad(elt33_,txt22_);
ad(elt28_,elt33_);
ad(elt26_,elt28_);
ad(elt16_,elt26_);
Element elt35_=el(_doc,"li");
Text txt23_=tx(_doc,"At five players, (with call):");
ad(elt35_,txt23_);
Element elt36_=el(_doc,"br");
ad(elt35_,elt36_);
Element elt37_=el(_doc,"ol");
Element elt38_=el(_doc,"li");
Text txt24_=tx(_doc,"Haunt the Small:");
ad(elt38_,txt24_);
Element elt39_=el(_doc,"br");
ad(elt38_,elt39_);
Text txt25_=tx(_doc,"&#160;The minimum is worth ten and the maximum is worth fifteen.");
ad(elt38_,txt25_);
ad(elt37_,elt38_);
Element elt40_=el(_doc,"li");
Text txt26_=tx(_doc,"Save the Small:");
ad(elt40_,txt26_);
Element elt41_=el(_doc,"br");
ad(elt40_,elt41_);
Text txt27_=tx(_doc,"&#160;The minimum is worth one and the maximum is worth three.");
ad(elt40_,txt27_);
ad(elt37_,elt40_);
Element elt42_=el(_doc,"li");
Text txt28_=tx(_doc,"Lead the Small at the last trick:");
ad(elt42_,txt28_);
Element elt43_=el(_doc,"br");
ad(elt42_,elt43_);
Text txt29_=tx(_doc,"&#160;The minimum is worth nine and the maximum is worth fourteen.");
ad(elt42_,txt29_);
ad(elt37_,elt42_);
ad(elt35_,elt37_);
ad(elt16_,elt35_);
Element elt44_=el(_doc,"li");
Text txt30_=tx(_doc,"At five players, (without call):");
ad(elt44_,txt30_);
Element elt45_=el(_doc,"br");
ad(elt44_,elt45_);
Element elt46_=el(_doc,"ol");
Element elt47_=el(_doc,"li");
Text txt31_=tx(_doc,"Haunt the Small:");
ad(elt47_,txt31_);
Element elt48_=el(_doc,"br");
ad(elt47_,elt48_);
Text txt32_=tx(_doc,"&#160;The minimum is worth ten and the maximum is worth fourteen.");
ad(elt47_,txt32_);
ad(elt46_,elt47_);
Element elt49_=el(_doc,"li");
Text txt33_=tx(_doc,"Save the Small:");
ad(elt49_,txt33_);
Element elt50_=el(_doc,"br");
ad(elt49_,elt50_);
Text txt34_=tx(_doc,"&#160;The minimum is worth one and the maximum is worth two.");
ad(elt49_,txt34_);
ad(elt46_,elt49_);
Element elt51_=el(_doc,"li");
Text txt35_=tx(_doc,"Lead the Small at the last trick:");
ad(elt51_,txt35_);
Element elt52_=el(_doc,"br");
ad(elt51_,elt52_);
Text txt36_=tx(_doc,"&#160;The minimum is worth nine and the maximum is worth thirteen.");
ad(elt51_,txt36_);
ad(elt46_,elt51_);
ad(elt44_,elt46_);
ad(elt16_,elt44_);
Element elt53_=el(_doc,"li");
Text txt37_=tx(_doc,"At six players:");
ad(elt53_,txt37_);
Element elt54_=el(_doc,"br");
ad(elt53_,elt54_);
Element elt55_=el(_doc,"ol");
Element elt56_=el(_doc,"li");
Text txt38_=tx(_doc,"Haunt the Small:");
ad(elt56_,txt38_);
Element elt57_=el(_doc,"br");
ad(elt56_,elt57_);
Text txt39_=tx(_doc,"&#160;The minimum is worth nine and the maximum is worth twelve.");
ad(elt56_,txt39_);
ad(elt55_,elt56_);
Element elt58_=el(_doc,"li");
Text txt40_=tx(_doc,"Save the Small:");
ad(elt58_,txt40_);
Element elt59_=el(_doc,"br");
ad(elt58_,elt59_);
Text txt41_=tx(_doc,"&#160;The minimum is worth one and the maximum is worth three.");
ad(elt58_,txt41_);
ad(elt55_,elt58_);
Element elt60_=el(_doc,"li");
Text txt42_=tx(_doc,"Lead the Small at the last trick:");
ad(elt60_,txt42_);
Element elt61_=el(_doc,"br");
ad(elt60_,elt61_);
Text txt43_=tx(_doc,"&#160;The minimum is worth eight and the maximum is worth eleven.");
ad(elt60_,txt43_);
ad(elt55_,elt60_);
ad(elt53_,elt55_);
ad(elt16_,elt53_);
ad(elt1_,elt16_);
Text txt44_=tx(_doc,"In all cases, a deal checking the above conditions, is played.");
ad(elt1_,txt44_);
Element elt63_=el(_doc,"br");
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
