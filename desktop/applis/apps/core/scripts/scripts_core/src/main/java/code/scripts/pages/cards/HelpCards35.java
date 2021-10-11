package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards35{
private HelpCards35(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Displaying end of deal is done on the window, by a tabbed pane, so no dialog box.");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Text txt2_=tx(_doc,"Tabs are the following one:");
ad(elt1_,txt2_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"ol");
Element elt6_=el(_doc,"li");
Text txt3_=tx(_doc,"Results of this deal (previous scores and scores of this deal).");
ad(elt6_,txt3_);
ad(elt5_,elt6_);
Element elt7_=el(_doc,"li");
Text txt4_=tx(_doc,"Detail results.");
ad(elt7_,txt4_);
ad(elt5_,elt7_);
Element elt8_=el(_doc,"li");
Text txt5_=tx(_doc,"Temporal graph.");
ad(elt8_,txt5_);
ad(elt5_,elt8_);
Element elt9_=el(_doc,"li");
Text txt6_=tx(_doc,"Hands and tricks.");
ad(elt9_,txt6_);
ad(elt5_,elt9_);
ad(elt1_,elt5_);
Text txt7_=tx(_doc,"The user can see previous scores, scores of this deal and declaring of this deal.");
ad(elt1_,txt7_);
Element elt10_=el(_doc,"br");
ad(elt1_,elt10_);
Element elt11_=el(_doc,"br");
ad(elt1_,elt11_);
Text txt8_=tx(_doc,"If the deal is finished at least for the second time, the deal is not counted.");
ad(elt1_,txt8_);
Element elt12_=el(_doc,"br");
ad(elt1_,elt12_);
Text txt9_=tx(_doc,"Scores of random deals are separated from scores of edited deals.");
ad(elt1_,txt9_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt10_=tx(_doc,"The user can see hands and tricks of this deal.");
ad(elt1_,txt10_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"br");
ad(elt1_,elt16_);
Text txt11_=tx(_doc,"Stack of all cards is saved in a file.");
ad(elt1_,txt11_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Text txt12_=tx(_doc,"The number of deals played since the launch of the software is incremented (increased by one),");
ad(elt1_,txt12_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt13_=tx(_doc,"&#160;then this number is saved in a text file.");
ad(elt1_,txt13_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt14_=tx(_doc,"The number of times, this deal is finished, is incremented.");
ad(elt1_,txt14_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Text txt15_=tx(_doc,"Options of this state are the following one:");
ad(elt1_,txt15_);
Element elt22_=el(_doc,"br");
ad(elt1_,elt22_);
Element elt23_=el(_doc,"ol");
Element elt24_=el(_doc,"li");
Text txt16_=tx(_doc,"\"Keep playing the edited deals\", if the finished deal is edited and not final.");
ad(elt24_,txt16_);
ad(elt23_,elt24_);
Element elt25_=el(_doc,"li");
Text txt17_=tx(_doc,"\"Keep playing the deals\", if the finished deal is edited and final and previously,");
ad(elt25_,txt17_);
Element elt26_=el(_doc,"br");
ad(elt25_,elt26_);
Text txt18_=tx(_doc,"at least one random deal has been played; or if the previous deal is random.");
ad(elt25_,txt18_);
ad(elt23_,elt25_);
Element elt27_=el(_doc,"li");
Text txt19_=tx(_doc,"\"Replay the deal\".");
ad(elt27_,txt19_);
ad(elt23_,elt27_);
Element elt28_=el(_doc,"li");
Text txt20_=tx(_doc,"\"Stop\".");
ad(elt28_,txt20_);
ad(elt23_,elt28_);
ad(elt1_,elt23_);
Element elt29_=el(_doc,"br");
ad(elt1_,elt29_);
Text txt21_=tx(_doc,"If the user has chosen the option \"Keep playing the edited deals\", then cards are not mixed and the next deal is played.");
ad(elt1_,txt21_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Element elt31_=el(_doc,"br");
ad(elt1_,elt31_);
Text txt22_=tx(_doc,"If the user has chosen the option \"Keep playing the deals\", then the next deal is played.");
ad(elt1_,txt22_);
Element elt32_=el(_doc,"br");
ad(elt1_,elt32_);
Element elt33_=el(_doc,"br");
ad(elt1_,elt33_);
Text txt23_=tx(_doc,"If the user has chosen the option \"Replay the deal\", then the deal is redealt.");
ad(elt1_,txt23_);
Element elt34_=el(_doc,"br");
ad(elt1_,elt34_);
Element elt35_=el(_doc,"br");
ad(elt1_,elt35_);
Text txt24_=tx(_doc,"If the user has chosen the option \"Stop\", then the \"single mode\" menu with games is shown.");
ad(elt1_,txt24_);
Element elt36_=el(_doc,"br");
ad(elt1_,elt36_);
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
