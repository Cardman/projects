package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards7{
private HelpCards7(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"Here is the variants at end of deal:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Text txt1_=tx(_doc,"&#160;The temporary full score of the taker is the definitive full score of the taker.");
ad(elt1_,txt1_);
Element elt4_=el(_doc,"br");
ad(elt1_,elt4_);
Element elt5_=el(_doc,"br");
ad(elt1_,elt5_);
Text txt2_=tx(_doc,"Examples for a different end of deal:");
ad(elt1_,txt2_);
Element elt6_=el(_doc,"br");
ad(elt1_,elt6_);
Element elt7_=el(_doc,"br");
ad(elt1_,elt7_);
Element elt8_=el(_doc,"ol");
Element elt9_=el(_doc,"li");
Text txt3_=tx(_doc,"If the taker''s team scores 75 points in the team''s tricks,");
ad(elt9_,txt3_);
Element elt10_=el(_doc,"br");
ad(elt9_,elt10_);
Text txt4_=tx(_doc,"&#160;&#160;if the taker''s team wins the last trick,");
ad(elt9_,txt4_);
Element elt11_=el(_doc,"br");
ad(elt9_,elt11_);
Text txt5_=tx(_doc,"&#160;&#160;if the defence owns the declaring belote rebelote,");
ad(elt9_,txt5_);
Element elt12_=el(_doc,"br");
ad(elt9_,elt12_);
Text txt6_=tx(_doc,"&#160;&#160;then the taker has a temporary score of 85 points, that is under 91 points.");
ad(elt9_,txt6_);
Element elt13_=el(_doc,"br");
ad(elt9_,elt13_);
Text txt7_=tx(_doc,"&#160;&#160;So the taker looses the deal and scores 85 points and the taker''s partner also, and each defender scores 97 points.");
ad(elt9_,txt7_);
Element elt14_=el(_doc,"br");
ad(elt9_,elt14_);
ad(elt8_,elt9_);
Element elt15_=el(_doc,"li");
Text txt8_=tx(_doc,"If the taker''s team scores 75 points in the team''s tricks,");
ad(elt15_,txt8_);
Element elt16_=el(_doc,"br");
ad(elt15_,elt16_);
Text txt9_=tx(_doc,"&#160;&#160;if the taker''s team wins the last trick,");
ad(elt15_,txt9_);
Element elt17_=el(_doc,"br");
ad(elt15_,elt17_);
Text txt10_=tx(_doc,"&#160;&#160;then the taker has a temporary score of 85 points, that is over 81 points.");
ad(elt15_,txt10_);
Element elt18_=el(_doc,"br");
ad(elt15_,elt18_);
Text txt11_=tx(_doc,"&#160;&#160;So the taker wins the deal and scores 85 points and the taker''s partner also, and each defender scores 77 points.");
ad(elt15_,txt11_);
Element elt19_=el(_doc,"br");
ad(elt15_,elt19_);
ad(elt8_,elt15_);
Element elt20_=el(_doc,"li");
Text txt12_=tx(_doc,"If the taker''s team wins all tricks,");
ad(elt20_,txt12_);
Element elt21_=el(_doc,"br");
ad(elt20_,elt21_);
Text txt13_=tx(_doc,"&#160;&#160;if the taker''s team owns the declaring belote-rebelote,");
ad(elt20_,txt13_);
Element elt22_=el(_doc,"br");
ad(elt20_,elt22_);
Text txt14_=tx(_doc,"&#160;&#160;then the taker has a temporary score of 182 points, that is over 81 points.");
ad(elt20_,txt14_);
Element elt23_=el(_doc,"br");
ad(elt20_,elt23_);
Text txt15_=tx(_doc,"&#160;&#160;So the taker wins the deal and scores 282 points and the taker''s partner also, and each defender scores zero points.");
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
