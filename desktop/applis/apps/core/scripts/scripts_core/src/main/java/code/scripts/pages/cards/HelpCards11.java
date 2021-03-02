package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards11{
private HelpCards11(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
Element elt1_=el(_doc,"body");
Text txt0_=tx(_doc,"The game is made up by:");
ad(elt1_,txt0_);
Element elt2_=el(_doc,"br");
ad(elt1_,elt2_);
Element elt3_=el(_doc,"br");
ad(elt1_,elt3_);
Element elt4_=el(_doc,"ol");
Element elt5_=el(_doc,"li");
Text txt1_=tx(_doc,"12 characters.");
ad(elt5_,txt1_);
Element elt6_=el(_doc,"br");
ad(elt5_,elt6_);
Element elt7_=el(_doc,"ol");
Element elt8_=el(_doc,"li");
Text txt2_=tx(_doc,"4 kings.");
ad(elt8_,txt2_);
ad(elt7_,elt8_);
Element elt9_=el(_doc,"li");
Text txt3_=tx(_doc,"4 queens.");
ad(elt9_,txt3_);
ad(elt7_,elt9_);
Element elt10_=el(_doc,"li");
Text txt4_=tx(_doc,"4 jacks.");
ad(elt10_,txt4_);
ad(elt7_,elt10_);
ad(elt5_,elt7_);
ad(elt4_,elt5_);
Element elt11_=el(_doc,"li");
Text txt5_=tx(_doc,"36 cards numbered from 2 to 10.");
ad(elt11_,txt5_);
ad(elt4_,elt11_);
Element elt12_=el(_doc,"li");
Text txt6_=tx(_doc,"4 aces.");
ad(elt12_,txt6_);
ad(elt4_,elt12_);
ad(elt1_,elt4_);
Element elt13_=el(_doc,"br");
ad(elt1_,elt13_);
Text txt7_=tx(_doc,"As many card games, there are 4 suits: heart, spade, diamond, club; for the characters and numbered cards.");
ad(elt1_,txt7_);
Element elt14_=el(_doc,"br");
ad(elt1_,elt14_);
Text txt8_=tx(_doc,"For each suit, each type of cards is single.(type of cards: king, queen...)");
ad(elt1_,txt8_);
Element elt15_=el(_doc,"br");
ad(elt1_,elt15_);
Element elt16_=el(_doc,"hr");
ad(elt1_,elt16_);
Text txt9_=tx(_doc,"Heart");
ad(elt1_,txt9_);
Element elt17_=el(_doc,"br");
ad(elt1_,elt17_);
Element elt18_=el(_doc,"table");
Element elt19_=el(_doc,"tr");
Element elt20_=el(_doc,"td");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("border","1px"));
at(elt20_,attrs0_);
Element elt21_=el(_doc,"img");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("src","resources_cards/images/en/HEART_2.txt"));
at(elt21_,attrs1_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
Element elt22_=el(_doc,"td");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("border","1px"));
at(elt22_,attrs2_);
Element elt23_=el(_doc,"img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","resources_cards/images/en/HEART_1.txt"));
at(elt23_,attrs3_);
ad(elt22_,elt23_);
ad(elt19_,elt22_);
Element elt24_=el(_doc,"td");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("border","1px"));
at(elt24_,attrs4_);
Element elt25_=el(_doc,"img");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("src","resources_cards/images/en/HEART_KING.txt"));
at(elt25_,attrs5_);
ad(elt24_,elt25_);
ad(elt19_,elt24_);
Element elt26_=el(_doc,"td");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("border","1px"));
at(elt26_,attrs6_);
Element elt27_=el(_doc,"img");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("src","resources_cards/images/en/HEART_QUEEN.txt"));
at(elt27_,attrs7_);
ad(elt26_,elt27_);
ad(elt19_,elt26_);
Element elt28_=el(_doc,"td");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("border","1px"));
at(elt28_,attrs8_);
Element elt29_=el(_doc,"img");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("src","resources_cards/images/en/HEART_JACK.txt"));
at(elt29_,attrs9_);
ad(elt28_,elt29_);
ad(elt19_,elt28_);
Element elt30_=el(_doc,"td");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("border","1px"));
at(elt30_,attrs10_);
Element elt31_=el(_doc,"img");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("src","resources_cards/images/en/HEART_10.txt"));
at(elt31_,attrs11_);
ad(elt30_,elt31_);
ad(elt19_,elt30_);
Element elt32_=el(_doc,"td");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("border","1px"));
at(elt32_,attrs12_);
Element elt33_=el(_doc,"img");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("src","resources_cards/images/en/HEART_9.txt"));
at(elt33_,attrs13_);
ad(elt32_,elt33_);
ad(elt19_,elt32_);
Element elt34_=el(_doc,"td");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("border","1px"));
at(elt34_,attrs14_);
Element elt35_=el(_doc,"img");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("src","resources_cards/images/en/HEART_8.txt"));
at(elt35_,attrs15_);
ad(elt34_,elt35_);
ad(elt19_,elt34_);
Element elt36_=el(_doc,"td");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("border","1px"));
at(elt36_,attrs16_);
Element elt37_=el(_doc,"img");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("src","resources_cards/images/en/HEART_7.txt"));
at(elt37_,attrs17_);
ad(elt36_,elt37_);
ad(elt19_,elt36_);
Element elt38_=el(_doc,"td");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("border","1px"));
at(elt38_,attrs18_);
Element elt39_=el(_doc,"img");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("src","resources_cards/images/en/HEART_6.txt"));
at(elt39_,attrs19_);
ad(elt38_,elt39_);
ad(elt19_,elt38_);
Element elt40_=el(_doc,"td");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("border","1px"));
at(elt40_,attrs20_);
Element elt41_=el(_doc,"img");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("src","resources_cards/images/en/HEART_5.txt"));
at(elt41_,attrs21_);
ad(elt40_,elt41_);
ad(elt19_,elt40_);
Element elt42_=el(_doc,"td");
CustList<Attr> attrs22_=al(1);
attrs22_.add(at("border","1px"));
at(elt42_,attrs22_);
Element elt43_=el(_doc,"img");
CustList<Attr> attrs23_=al(1);
attrs23_.add(at("src","resources_cards/images/en/HEART_4.txt"));
at(elt43_,attrs23_);
ad(elt42_,elt43_);
ad(elt19_,elt42_);
Element elt44_=el(_doc,"td");
CustList<Attr> attrs24_=al(1);
attrs24_.add(at("border","1px"));
at(elt44_,attrs24_);
Element elt45_=el(_doc,"img");
CustList<Attr> attrs25_=al(1);
attrs25_.add(at("src","resources_cards/images/en/HEART_3.txt"));
at(elt45_,attrs25_);
ad(elt44_,elt45_);
ad(elt19_,elt44_);
ad(elt18_,elt19_);
ad(elt1_,elt18_);
Element elt46_=el(_doc,"br");
ad(elt1_,elt46_);
Element elt47_=el(_doc,"hr");
ad(elt1_,elt47_);
Text txt10_=tx(_doc,"Spade");
ad(elt1_,txt10_);
Element elt48_=el(_doc,"br");
ad(elt1_,elt48_);
Element elt49_=el(_doc,"table");
Element elt50_=el(_doc,"tr");
Element elt51_=el(_doc,"td");
CustList<Attr> attrs26_=al(1);
attrs26_.add(at("border","1px"));
at(elt51_,attrs26_);
Element elt52_=el(_doc,"img");
CustList<Attr> attrs27_=al(1);
attrs27_.add(at("src","resources_cards/images/en/SPADE_2.txt"));
at(elt52_,attrs27_);
ad(elt51_,elt52_);
ad(elt50_,elt51_);
Element elt53_=el(_doc,"td");
CustList<Attr> attrs28_=al(1);
attrs28_.add(at("border","1px"));
at(elt53_,attrs28_);
Element elt54_=el(_doc,"img");
CustList<Attr> attrs29_=al(1);
attrs29_.add(at("src","resources_cards/images/en/SPADE_1.txt"));
at(elt54_,attrs29_);
ad(elt53_,elt54_);
ad(elt50_,elt53_);
Element elt55_=el(_doc,"td");
CustList<Attr> attrs30_=al(1);
attrs30_.add(at("border","1px"));
at(elt55_,attrs30_);
Element elt56_=el(_doc,"img");
CustList<Attr> attrs31_=al(1);
attrs31_.add(at("src","resources_cards/images/en/SPADE_KING.txt"));
at(elt56_,attrs31_);
ad(elt55_,elt56_);
ad(elt50_,elt55_);
Element elt57_=el(_doc,"td");
CustList<Attr> attrs32_=al(1);
attrs32_.add(at("border","1px"));
at(elt57_,attrs32_);
Element elt58_=el(_doc,"img");
CustList<Attr> attrs33_=al(1);
attrs33_.add(at("src","resources_cards/images/en/SPADE_QUEEN.txt"));
at(elt58_,attrs33_);
ad(elt57_,elt58_);
ad(elt50_,elt57_);
Element elt59_=el(_doc,"td");
CustList<Attr> attrs34_=al(1);
attrs34_.add(at("border","1px"));
at(elt59_,attrs34_);
Element elt60_=el(_doc,"img");
CustList<Attr> attrs35_=al(1);
attrs35_.add(at("src","resources_cards/images/en/SPADE_JACK.txt"));
at(elt60_,attrs35_);
ad(elt59_,elt60_);
ad(elt50_,elt59_);
Element elt61_=el(_doc,"td");
CustList<Attr> attrs36_=al(1);
attrs36_.add(at("border","1px"));
at(elt61_,attrs36_);
Element elt62_=el(_doc,"img");
CustList<Attr> attrs37_=al(1);
attrs37_.add(at("src","resources_cards/images/en/SPADE_10.txt"));
at(elt62_,attrs37_);
ad(elt61_,elt62_);
ad(elt50_,elt61_);
Element elt63_=el(_doc,"td");
CustList<Attr> attrs38_=al(1);
attrs38_.add(at("border","1px"));
at(elt63_,attrs38_);
Element elt64_=el(_doc,"img");
CustList<Attr> attrs39_=al(1);
attrs39_.add(at("src","resources_cards/images/en/SPADE_9.txt"));
at(elt64_,attrs39_);
ad(elt63_,elt64_);
ad(elt50_,elt63_);
Element elt65_=el(_doc,"td");
CustList<Attr> attrs40_=al(1);
attrs40_.add(at("border","1px"));
at(elt65_,attrs40_);
Element elt66_=el(_doc,"img");
CustList<Attr> attrs41_=al(1);
attrs41_.add(at("src","resources_cards/images/en/SPADE_8.txt"));
at(elt66_,attrs41_);
ad(elt65_,elt66_);
ad(elt50_,elt65_);
Element elt67_=el(_doc,"td");
CustList<Attr> attrs42_=al(1);
attrs42_.add(at("border","1px"));
at(elt67_,attrs42_);
Element elt68_=el(_doc,"img");
CustList<Attr> attrs43_=al(1);
attrs43_.add(at("src","resources_cards/images/en/SPADE_7.txt"));
at(elt68_,attrs43_);
ad(elt67_,elt68_);
ad(elt50_,elt67_);
Element elt69_=el(_doc,"td");
CustList<Attr> attrs44_=al(1);
attrs44_.add(at("border","1px"));
at(elt69_,attrs44_);
Element elt70_=el(_doc,"img");
CustList<Attr> attrs45_=al(1);
attrs45_.add(at("src","resources_cards/images/en/SPADE_6.txt"));
at(elt70_,attrs45_);
ad(elt69_,elt70_);
ad(elt50_,elt69_);
Element elt71_=el(_doc,"td");
CustList<Attr> attrs46_=al(1);
attrs46_.add(at("border","1px"));
at(elt71_,attrs46_);
Element elt72_=el(_doc,"img");
CustList<Attr> attrs47_=al(1);
attrs47_.add(at("src","resources_cards/images/en/SPADE_5.txt"));
at(elt72_,attrs47_);
ad(elt71_,elt72_);
ad(elt50_,elt71_);
Element elt73_=el(_doc,"td");
CustList<Attr> attrs48_=al(1);
attrs48_.add(at("border","1px"));
at(elt73_,attrs48_);
Element elt74_=el(_doc,"img");
CustList<Attr> attrs49_=al(1);
attrs49_.add(at("src","resources_cards/images/en/SPADE_4.txt"));
at(elt74_,attrs49_);
ad(elt73_,elt74_);
ad(elt50_,elt73_);
Element elt75_=el(_doc,"td");
CustList<Attr> attrs50_=al(1);
attrs50_.add(at("border","1px"));
at(elt75_,attrs50_);
Element elt76_=el(_doc,"img");
CustList<Attr> attrs51_=al(1);
attrs51_.add(at("src","resources_cards/images/en/SPADE_3.txt"));
at(elt76_,attrs51_);
ad(elt75_,elt76_);
ad(elt50_,elt75_);
ad(elt49_,elt50_);
ad(elt1_,elt49_);
Element elt77_=el(_doc,"br");
ad(elt1_,elt77_);
Element elt78_=el(_doc,"hr");
ad(elt1_,elt78_);
Text txt11_=tx(_doc,"Diamond");
ad(elt1_,txt11_);
Element elt79_=el(_doc,"br");
ad(elt1_,elt79_);
Element elt80_=el(_doc,"table");
Element elt81_=el(_doc,"tr");
Element elt82_=el(_doc,"td");
CustList<Attr> attrs52_=al(1);
attrs52_.add(at("border","1px"));
at(elt82_,attrs52_);
Element elt83_=el(_doc,"img");
CustList<Attr> attrs53_=al(1);
attrs53_.add(at("src","resources_cards/images/en/DIAMOND_2.txt"));
at(elt83_,attrs53_);
ad(elt82_,elt83_);
ad(elt81_,elt82_);
Element elt84_=el(_doc,"td");
CustList<Attr> attrs54_=al(1);
attrs54_.add(at("border","1px"));
at(elt84_,attrs54_);
Element elt85_=el(_doc,"img");
CustList<Attr> attrs55_=al(1);
attrs55_.add(at("src","resources_cards/images/en/DIAMOND_1.txt"));
at(elt85_,attrs55_);
ad(elt84_,elt85_);
ad(elt81_,elt84_);
Element elt86_=el(_doc,"td");
CustList<Attr> attrs56_=al(1);
attrs56_.add(at("border","1px"));
at(elt86_,attrs56_);
Element elt87_=el(_doc,"img");
CustList<Attr> attrs57_=al(1);
attrs57_.add(at("src","resources_cards/images/en/DIAMOND_KING.txt"));
at(elt87_,attrs57_);
ad(elt86_,elt87_);
ad(elt81_,elt86_);
Element elt88_=el(_doc,"td");
CustList<Attr> attrs58_=al(1);
attrs58_.add(at("border","1px"));
at(elt88_,attrs58_);
Element elt89_=el(_doc,"img");
CustList<Attr> attrs59_=al(1);
attrs59_.add(at("src","resources_cards/images/en/DIAMOND_QUEEN.txt"));
at(elt89_,attrs59_);
ad(elt88_,elt89_);
ad(elt81_,elt88_);
Element elt90_=el(_doc,"td");
CustList<Attr> attrs60_=al(1);
attrs60_.add(at("border","1px"));
at(elt90_,attrs60_);
Element elt91_=el(_doc,"img");
CustList<Attr> attrs61_=al(1);
attrs61_.add(at("src","resources_cards/images/en/DIAMOND_JACK.txt"));
at(elt91_,attrs61_);
ad(elt90_,elt91_);
ad(elt81_,elt90_);
Element elt92_=el(_doc,"td");
CustList<Attr> attrs62_=al(1);
attrs62_.add(at("border","1px"));
at(elt92_,attrs62_);
Element elt93_=el(_doc,"img");
CustList<Attr> attrs63_=al(1);
attrs63_.add(at("src","resources_cards/images/en/DIAMOND_10.txt"));
at(elt93_,attrs63_);
ad(elt92_,elt93_);
ad(elt81_,elt92_);
Element elt94_=el(_doc,"td");
CustList<Attr> attrs64_=al(1);
attrs64_.add(at("border","1px"));
at(elt94_,attrs64_);
Element elt95_=el(_doc,"img");
CustList<Attr> attrs65_=al(1);
attrs65_.add(at("src","resources_cards/images/en/DIAMOND_9.txt"));
at(elt95_,attrs65_);
ad(elt94_,elt95_);
ad(elt81_,elt94_);
Element elt96_=el(_doc,"td");
CustList<Attr> attrs66_=al(1);
attrs66_.add(at("border","1px"));
at(elt96_,attrs66_);
Element elt97_=el(_doc,"img");
CustList<Attr> attrs67_=al(1);
attrs67_.add(at("src","resources_cards/images/en/DIAMOND_8.txt"));
at(elt97_,attrs67_);
ad(elt96_,elt97_);
ad(elt81_,elt96_);
Element elt98_=el(_doc,"td");
CustList<Attr> attrs68_=al(1);
attrs68_.add(at("border","1px"));
at(elt98_,attrs68_);
Element elt99_=el(_doc,"img");
CustList<Attr> attrs69_=al(1);
attrs69_.add(at("src","resources_cards/images/en/DIAMOND_7.txt"));
at(elt99_,attrs69_);
ad(elt98_,elt99_);
ad(elt81_,elt98_);
Element elt100_=el(_doc,"td");
CustList<Attr> attrs70_=al(1);
attrs70_.add(at("border","1px"));
at(elt100_,attrs70_);
Element elt101_=el(_doc,"img");
CustList<Attr> attrs71_=al(1);
attrs71_.add(at("src","resources_cards/images/en/DIAMOND_6.txt"));
at(elt101_,attrs71_);
ad(elt100_,elt101_);
ad(elt81_,elt100_);
Element elt102_=el(_doc,"td");
CustList<Attr> attrs72_=al(1);
attrs72_.add(at("border","1px"));
at(elt102_,attrs72_);
Element elt103_=el(_doc,"img");
CustList<Attr> attrs73_=al(1);
attrs73_.add(at("src","resources_cards/images/en/DIAMOND_5.txt"));
at(elt103_,attrs73_);
ad(elt102_,elt103_);
ad(elt81_,elt102_);
Element elt104_=el(_doc,"td");
CustList<Attr> attrs74_=al(1);
attrs74_.add(at("border","1px"));
at(elt104_,attrs74_);
Element elt105_=el(_doc,"img");
CustList<Attr> attrs75_=al(1);
attrs75_.add(at("src","resources_cards/images/en/DIAMOND_4.txt"));
at(elt105_,attrs75_);
ad(elt104_,elt105_);
ad(elt81_,elt104_);
Element elt106_=el(_doc,"td");
CustList<Attr> attrs76_=al(1);
attrs76_.add(at("border","1px"));
at(elt106_,attrs76_);
Element elt107_=el(_doc,"img");
CustList<Attr> attrs77_=al(1);
attrs77_.add(at("src","resources_cards/images/en/DIAMOND_3.txt"));
at(elt107_,attrs77_);
ad(elt106_,elt107_);
ad(elt81_,elt106_);
ad(elt80_,elt81_);
ad(elt1_,elt80_);
Element elt108_=el(_doc,"br");
ad(elt1_,elt108_);
Element elt109_=el(_doc,"hr");
ad(elt1_,elt109_);
Text txt12_=tx(_doc,"Club");
ad(elt1_,txt12_);
Element elt110_=el(_doc,"br");
ad(elt1_,elt110_);
Element elt111_=el(_doc,"table");
Element elt112_=el(_doc,"tr");
Element elt113_=el(_doc,"td");
CustList<Attr> attrs78_=al(1);
attrs78_.add(at("border","1px"));
at(elt113_,attrs78_);
Element elt114_=el(_doc,"img");
CustList<Attr> attrs79_=al(1);
attrs79_.add(at("src","resources_cards/images/en/CLUB_2.txt"));
at(elt114_,attrs79_);
ad(elt113_,elt114_);
ad(elt112_,elt113_);
Element elt115_=el(_doc,"td");
CustList<Attr> attrs80_=al(1);
attrs80_.add(at("border","1px"));
at(elt115_,attrs80_);
Element elt116_=el(_doc,"img");
CustList<Attr> attrs81_=al(1);
attrs81_.add(at("src","resources_cards/images/en/CLUB_1.txt"));
at(elt116_,attrs81_);
ad(elt115_,elt116_);
ad(elt112_,elt115_);
Element elt117_=el(_doc,"td");
CustList<Attr> attrs82_=al(1);
attrs82_.add(at("border","1px"));
at(elt117_,attrs82_);
Element elt118_=el(_doc,"img");
CustList<Attr> attrs83_=al(1);
attrs83_.add(at("src","resources_cards/images/en/CLUB_KING.txt"));
at(elt118_,attrs83_);
ad(elt117_,elt118_);
ad(elt112_,elt117_);
Element elt119_=el(_doc,"td");
CustList<Attr> attrs84_=al(1);
attrs84_.add(at("border","1px"));
at(elt119_,attrs84_);
Element elt120_=el(_doc,"img");
CustList<Attr> attrs85_=al(1);
attrs85_.add(at("src","resources_cards/images/en/CLUB_QUEEN.txt"));
at(elt120_,attrs85_);
ad(elt119_,elt120_);
ad(elt112_,elt119_);
Element elt121_=el(_doc,"td");
CustList<Attr> attrs86_=al(1);
attrs86_.add(at("border","1px"));
at(elt121_,attrs86_);
Element elt122_=el(_doc,"img");
CustList<Attr> attrs87_=al(1);
attrs87_.add(at("src","resources_cards/images/en/CLUB_JACK.txt"));
at(elt122_,attrs87_);
ad(elt121_,elt122_);
ad(elt112_,elt121_);
Element elt123_=el(_doc,"td");
CustList<Attr> attrs88_=al(1);
attrs88_.add(at("border","1px"));
at(elt123_,attrs88_);
Element elt124_=el(_doc,"img");
CustList<Attr> attrs89_=al(1);
attrs89_.add(at("src","resources_cards/images/en/CLUB_10.txt"));
at(elt124_,attrs89_);
ad(elt123_,elt124_);
ad(elt112_,elt123_);
Element elt125_=el(_doc,"td");
CustList<Attr> attrs90_=al(1);
attrs90_.add(at("border","1px"));
at(elt125_,attrs90_);
Element elt126_=el(_doc,"img");
CustList<Attr> attrs91_=al(1);
attrs91_.add(at("src","resources_cards/images/en/CLUB_9.txt"));
at(elt126_,attrs91_);
ad(elt125_,elt126_);
ad(elt112_,elt125_);
Element elt127_=el(_doc,"td");
CustList<Attr> attrs92_=al(1);
attrs92_.add(at("border","1px"));
at(elt127_,attrs92_);
Element elt128_=el(_doc,"img");
CustList<Attr> attrs93_=al(1);
attrs93_.add(at("src","resources_cards/images/en/CLUB_8.txt"));
at(elt128_,attrs93_);
ad(elt127_,elt128_);
ad(elt112_,elt127_);
Element elt129_=el(_doc,"td");
CustList<Attr> attrs94_=al(1);
attrs94_.add(at("border","1px"));
at(elt129_,attrs94_);
Element elt130_=el(_doc,"img");
CustList<Attr> attrs95_=al(1);
attrs95_.add(at("src","resources_cards/images/en/CLUB_7.txt"));
at(elt130_,attrs95_);
ad(elt129_,elt130_);
ad(elt112_,elt129_);
Element elt131_=el(_doc,"td");
CustList<Attr> attrs96_=al(1);
attrs96_.add(at("border","1px"));
at(elt131_,attrs96_);
Element elt132_=el(_doc,"img");
CustList<Attr> attrs97_=al(1);
attrs97_.add(at("src","resources_cards/images/en/CLUB_6.txt"));
at(elt132_,attrs97_);
ad(elt131_,elt132_);
ad(elt112_,elt131_);
Element elt133_=el(_doc,"td");
CustList<Attr> attrs98_=al(1);
attrs98_.add(at("border","1px"));
at(elt133_,attrs98_);
Element elt134_=el(_doc,"img");
CustList<Attr> attrs99_=al(1);
attrs99_.add(at("src","resources_cards/images/en/CLUB_5.txt"));
at(elt134_,attrs99_);
ad(elt133_,elt134_);
ad(elt112_,elt133_);
Element elt135_=el(_doc,"td");
CustList<Attr> attrs100_=al(1);
attrs100_.add(at("border","1px"));
at(elt135_,attrs100_);
Element elt136_=el(_doc,"img");
CustList<Attr> attrs101_=al(1);
attrs101_.add(at("src","resources_cards/images/en/CLUB_4.txt"));
at(elt136_,attrs101_);
ad(elt135_,elt136_);
ad(elt112_,elt135_);
Element elt137_=el(_doc,"td");
CustList<Attr> attrs102_=al(1);
attrs102_.add(at("border","1px"));
at(elt137_,attrs102_);
Element elt138_=el(_doc,"img");
CustList<Attr> attrs103_=al(1);
attrs103_.add(at("src","resources_cards/images/en/CLUB_3.txt"));
at(elt138_,attrs103_);
ad(elt137_,elt138_);
ad(elt112_,elt137_);
ad(elt111_,elt112_);
ad(elt1_,elt111_);
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
