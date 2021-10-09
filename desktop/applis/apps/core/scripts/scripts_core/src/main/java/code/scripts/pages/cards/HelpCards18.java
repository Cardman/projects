package code.scripts.pages.cards;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class HelpCards18{
private HelpCards18(){}
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
Text txt1_=tx(_doc,"21 trumps numbered from 1 to 21.");
ad(elt5_,txt1_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"li");
Text txt2_=tx(_doc,"56 cards of non trump suit.");
ad(elt6_,txt2_);
Element elt7_=el(_doc,"br");
ad(elt6_,elt7_);
Element elt8_=el(_doc,"ol");
Element elt9_=el(_doc,"li");
Text txt3_=tx(_doc,"16 characters.");
ad(elt9_,txt3_);
Element elt10_=el(_doc,"br");
ad(elt9_,elt10_);
Element elt11_=el(_doc,"ol");
Element elt12_=el(_doc,"li");
Text txt4_=tx(_doc,"4 kings.");
ad(elt12_,txt4_);
ad(elt11_,elt12_);
Element elt13_=el(_doc,"li");
Text txt5_=tx(_doc,"4 queens.");
ad(elt13_,txt5_);
ad(elt11_,elt13_);
Element elt14_=el(_doc,"li");
Text txt6_=tx(_doc,"4 knights.");
ad(elt14_,txt6_);
ad(elt11_,elt14_);
Element elt15_=el(_doc,"li");
Text txt7_=tx(_doc,"4 jacks.");
ad(elt15_,txt7_);
ad(elt11_,elt15_);
ad(elt9_,elt11_);
ad(elt8_,elt9_);
Element elt16_=el(_doc,"li");
Text txt8_=tx(_doc,"40 low cards numbered from 1 to 10.");
ad(elt16_,txt8_);
ad(elt8_,elt16_);
ad(elt6_,elt8_);
ad(elt4_,elt6_);
Element elt17_=el(_doc,"li");
Text txt9_=tx(_doc,"1 card called \"Excuse\".Sometimes, this card is regarded as a trump card, an other time no.");
ad(elt17_,txt9_);
ad(elt4_,elt17_);
ad(elt1_,elt4_);
Element elt18_=el(_doc,"br");
ad(elt1_,elt18_);
Text txt10_=tx(_doc,"As many card games, there are 4 suits: heart, spade, diamond, club; for kings, queens, knights, jacks and low cards.");
ad(elt1_,txt10_);
Element elt19_=el(_doc,"br");
ad(elt1_,elt19_);
Text txt11_=tx(_doc,"For each suit, each type of cards is single.(type of cards: king, queen...)");
ad(elt1_,txt11_);
Element elt20_=el(_doc,"br");
ad(elt1_,elt20_);
Element elt21_=el(_doc,"br");
ad(elt1_,elt21_);
Element elt22_=el(_doc,"hr");
ad(elt1_,elt22_);
Text txt12_=tx(_doc,"Excuse");
ad(elt1_,txt12_);
Element elt23_=el(_doc,"br");
ad(elt1_,elt23_);
Element elt24_=el(_doc,"table");
Element elt25_=el(_doc,"tr");
Element elt26_=el(_doc,"td");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("border","1px"));
at(elt26_,attrs0_);
Element elt27_=el(_doc,"img");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("src","resources_cards/images/EXCUSE.txt"));
at(elt27_,attrs1_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt24_,elt25_);
ad(elt1_,elt24_);
Element elt28_=el(_doc,"br");
ad(elt1_,elt28_);
Element elt29_=el(_doc,"hr");
ad(elt1_,elt29_);
Text txt13_=tx(_doc,"Trumps");
ad(elt1_,txt13_);
Element elt30_=el(_doc,"br");
ad(elt1_,elt30_);
Element elt31_=el(_doc,"table");
Element elt32_=el(_doc,"tr");
Element elt33_=el(_doc,"td");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("border","1px"));
at(elt33_,attrs2_);
Element elt34_=el(_doc,"img");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("src","resources_cards/images/TRUMP_21.txt"));
at(elt34_,attrs3_);
ad(elt33_,elt34_);
ad(elt32_,elt33_);
Element elt35_=el(_doc,"td");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("border","1px"));
at(elt35_,attrs4_);
Element elt36_=el(_doc,"img");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("src","resources_cards/images/TRUMP_20.txt"));
at(elt36_,attrs5_);
ad(elt35_,elt36_);
ad(elt32_,elt35_);
Element elt37_=el(_doc,"td");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("border","1px"));
at(elt37_,attrs6_);
Element elt38_=el(_doc,"img");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("src","resources_cards/images/TRUMP_19.txt"));
at(elt38_,attrs7_);
ad(elt37_,elt38_);
ad(elt32_,elt37_);
Element elt39_=el(_doc,"td");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("border","1px"));
at(elt39_,attrs8_);
Element elt40_=el(_doc,"img");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("src","resources_cards/images/TRUMP_18.txt"));
at(elt40_,attrs9_);
ad(elt39_,elt40_);
ad(elt32_,elt39_);
Element elt41_=el(_doc,"td");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("border","1px"));
at(elt41_,attrs10_);
Element elt42_=el(_doc,"img");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("src","resources_cards/images/TRUMP_17.txt"));
at(elt42_,attrs11_);
ad(elt41_,elt42_);
ad(elt32_,elt41_);
Element elt43_=el(_doc,"td");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("border","1px"));
at(elt43_,attrs12_);
Element elt44_=el(_doc,"img");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("src","resources_cards/images/TRUMP_16.txt"));
at(elt44_,attrs13_);
ad(elt43_,elt44_);
ad(elt32_,elt43_);
Element elt45_=el(_doc,"td");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("border","1px"));
at(elt45_,attrs14_);
Element elt46_=el(_doc,"img");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("src","resources_cards/images/TRUMP_15.txt"));
at(elt46_,attrs15_);
ad(elt45_,elt46_);
ad(elt32_,elt45_);
Element elt47_=el(_doc,"td");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("border","1px"));
at(elt47_,attrs16_);
Element elt48_=el(_doc,"img");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("src","resources_cards/images/TRUMP_14.txt"));
at(elt48_,attrs17_);
ad(elt47_,elt48_);
ad(elt32_,elt47_);
Element elt49_=el(_doc,"td");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("border","1px"));
at(elt49_,attrs18_);
Element elt50_=el(_doc,"img");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("src","resources_cards/images/TRUMP_13.txt"));
at(elt50_,attrs19_);
ad(elt49_,elt50_);
ad(elt32_,elt49_);
Element elt51_=el(_doc,"td");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("border","1px"));
at(elt51_,attrs20_);
Element elt52_=el(_doc,"img");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("src","resources_cards/images/TRUMP_12.txt"));
at(elt52_,attrs21_);
ad(elt51_,elt52_);
ad(elt32_,elt51_);
Element elt53_=el(_doc,"td");
CustList<Attr> attrs22_=al(1);
attrs22_.add(at("border","1px"));
at(elt53_,attrs22_);
Element elt54_=el(_doc,"img");
CustList<Attr> attrs23_=al(1);
attrs23_.add(at("src","resources_cards/images/TRUMP_11.txt"));
at(elt54_,attrs23_);
ad(elt53_,elt54_);
ad(elt32_,elt53_);
ad(elt31_,elt32_);
Element elt55_=el(_doc,"tr");
Element elt56_=el(_doc,"td");
CustList<Attr> attrs24_=al(1);
attrs24_.add(at("border","1px"));
at(elt56_,attrs24_);
Element elt57_=el(_doc,"img");
CustList<Attr> attrs25_=al(1);
attrs25_.add(at("src","resources_cards/images/TRUMP_10.txt"));
at(elt57_,attrs25_);
ad(elt56_,elt57_);
ad(elt55_,elt56_);
Element elt58_=el(_doc,"td");
CustList<Attr> attrs26_=al(1);
attrs26_.add(at("border","1px"));
at(elt58_,attrs26_);
Element elt59_=el(_doc,"img");
CustList<Attr> attrs27_=al(1);
attrs27_.add(at("src","resources_cards/images/TRUMP_9.txt"));
at(elt59_,attrs27_);
ad(elt58_,elt59_);
ad(elt55_,elt58_);
Element elt60_=el(_doc,"td");
CustList<Attr> attrs28_=al(1);
attrs28_.add(at("border","1px"));
at(elt60_,attrs28_);
Element elt61_=el(_doc,"img");
CustList<Attr> attrs29_=al(1);
attrs29_.add(at("src","resources_cards/images/TRUMP_8.txt"));
at(elt61_,attrs29_);
ad(elt60_,elt61_);
ad(elt55_,elt60_);
Element elt62_=el(_doc,"td");
CustList<Attr> attrs30_=al(1);
attrs30_.add(at("border","1px"));
at(elt62_,attrs30_);
Element elt63_=el(_doc,"img");
CustList<Attr> attrs31_=al(1);
attrs31_.add(at("src","resources_cards/images/TRUMP_7.txt"));
at(elt63_,attrs31_);
ad(elt62_,elt63_);
ad(elt55_,elt62_);
Element elt64_=el(_doc,"td");
CustList<Attr> attrs32_=al(1);
attrs32_.add(at("border","1px"));
at(elt64_,attrs32_);
Element elt65_=el(_doc,"img");
CustList<Attr> attrs33_=al(1);
attrs33_.add(at("src","resources_cards/images/TRUMP_6.txt"));
at(elt65_,attrs33_);
ad(elt64_,elt65_);
ad(elt55_,elt64_);
Element elt66_=el(_doc,"td");
CustList<Attr> attrs34_=al(1);
attrs34_.add(at("border","1px"));
at(elt66_,attrs34_);
Element elt67_=el(_doc,"img");
CustList<Attr> attrs35_=al(1);
attrs35_.add(at("src","resources_cards/images/TRUMP_5.txt"));
at(elt67_,attrs35_);
ad(elt66_,elt67_);
ad(elt55_,elt66_);
Element elt68_=el(_doc,"td");
CustList<Attr> attrs36_=al(1);
attrs36_.add(at("border","1px"));
at(elt68_,attrs36_);
Element elt69_=el(_doc,"img");
CustList<Attr> attrs37_=al(1);
attrs37_.add(at("src","resources_cards/images/TRUMP_4.txt"));
at(elt69_,attrs37_);
ad(elt68_,elt69_);
ad(elt55_,elt68_);
Element elt70_=el(_doc,"td");
CustList<Attr> attrs38_=al(1);
attrs38_.add(at("border","1px"));
at(elt70_,attrs38_);
Element elt71_=el(_doc,"img");
CustList<Attr> attrs39_=al(1);
attrs39_.add(at("src","resources_cards/images/TRUMP_3.txt"));
at(elt71_,attrs39_);
ad(elt70_,elt71_);
ad(elt55_,elt70_);
Element elt72_=el(_doc,"td");
CustList<Attr> attrs40_=al(1);
attrs40_.add(at("border","1px"));
at(elt72_,attrs40_);
Element elt73_=el(_doc,"img");
CustList<Attr> attrs41_=al(1);
attrs41_.add(at("src","resources_cards/images/TRUMP_2.txt"));
at(elt73_,attrs41_);
ad(elt72_,elt73_);
ad(elt55_,elt72_);
Element elt74_=el(_doc,"td");
CustList<Attr> attrs42_=al(1);
attrs42_.add(at("border","1px"));
at(elt74_,attrs42_);
Element elt75_=el(_doc,"img");
CustList<Attr> attrs43_=al(1);
attrs43_.add(at("src","resources_cards/images/TRUMP_1.txt"));
at(elt75_,attrs43_);
ad(elt74_,elt75_);
ad(elt55_,elt74_);
Element elt76_=el(_doc,"td");
ad(elt55_,elt76_);
ad(elt31_,elt55_);
ad(elt1_,elt31_);
Element elt77_=el(_doc,"br");
ad(elt1_,elt77_);
Element elt78_=el(_doc,"hr");
ad(elt1_,elt78_);
Text txt14_=tx(_doc,"Heart");
ad(elt1_,txt14_);
Element elt79_=el(_doc,"br");
ad(elt1_,elt79_);
Element elt80_=el(_doc,"table");
Element elt81_=el(_doc,"tr");
Element elt82_=el(_doc,"td");
CustList<Attr> attrs44_=al(1);
attrs44_.add(at("border","1px"));
at(elt82_,attrs44_);
Element elt83_=el(_doc,"img");
CustList<Attr> attrs45_=al(1);
attrs45_.add(at("src","resources_cards/images/HEART_KING.txt"));
at(elt83_,attrs45_);
ad(elt82_,elt83_);
ad(elt81_,elt82_);
Element elt84_=el(_doc,"td");
CustList<Attr> attrs46_=al(1);
attrs46_.add(at("border","1px"));
at(elt84_,attrs46_);
Element elt85_=el(_doc,"img");
CustList<Attr> attrs47_=al(1);
attrs47_.add(at("src","resources_cards/images/HEART_QUEEN.txt"));
at(elt85_,attrs47_);
ad(elt84_,elt85_);
ad(elt81_,elt84_);
Element elt86_=el(_doc,"td");
CustList<Attr> attrs48_=al(1);
attrs48_.add(at("border","1px"));
at(elt86_,attrs48_);
Element elt87_=el(_doc,"img");
CustList<Attr> attrs49_=al(1);
attrs49_.add(at("src","resources_cards/images/HEART_KNIGHT.txt"));
at(elt87_,attrs49_);
ad(elt86_,elt87_);
ad(elt81_,elt86_);
Element elt88_=el(_doc,"td");
CustList<Attr> attrs50_=al(1);
attrs50_.add(at("border","1px"));
at(elt88_,attrs50_);
Element elt89_=el(_doc,"img");
CustList<Attr> attrs51_=al(1);
attrs51_.add(at("src","resources_cards/images/HEART_JACK.txt"));
at(elt89_,attrs51_);
ad(elt88_,elt89_);
ad(elt81_,elt88_);
Element elt90_=el(_doc,"td");
CustList<Attr> attrs52_=al(1);
attrs52_.add(at("border","1px"));
at(elt90_,attrs52_);
Element elt91_=el(_doc,"img");
CustList<Attr> attrs53_=al(1);
attrs53_.add(at("src","resources_cards/images/HEART_10.txt"));
at(elt91_,attrs53_);
ad(elt90_,elt91_);
ad(elt81_,elt90_);
Element elt92_=el(_doc,"td");
CustList<Attr> attrs54_=al(1);
attrs54_.add(at("border","1px"));
at(elt92_,attrs54_);
Element elt93_=el(_doc,"img");
CustList<Attr> attrs55_=al(1);
attrs55_.add(at("src","resources_cards/images/HEART_9.txt"));
at(elt93_,attrs55_);
ad(elt92_,elt93_);
ad(elt81_,elt92_);
Element elt94_=el(_doc,"td");
CustList<Attr> attrs56_=al(1);
attrs56_.add(at("border","1px"));
at(elt94_,attrs56_);
Element elt95_=el(_doc,"img");
CustList<Attr> attrs57_=al(1);
attrs57_.add(at("src","resources_cards/images/HEART_8.txt"));
at(elt95_,attrs57_);
ad(elt94_,elt95_);
ad(elt81_,elt94_);
Element elt96_=el(_doc,"td");
CustList<Attr> attrs58_=al(1);
attrs58_.add(at("border","1px"));
at(elt96_,attrs58_);
Element elt97_=el(_doc,"img");
CustList<Attr> attrs59_=al(1);
attrs59_.add(at("src","resources_cards/images/HEART_7.txt"));
at(elt97_,attrs59_);
ad(elt96_,elt97_);
ad(elt81_,elt96_);
Element elt98_=el(_doc,"td");
CustList<Attr> attrs60_=al(1);
attrs60_.add(at("border","1px"));
at(elt98_,attrs60_);
Element elt99_=el(_doc,"img");
CustList<Attr> attrs61_=al(1);
attrs61_.add(at("src","resources_cards/images/HEART_6.txt"));
at(elt99_,attrs61_);
ad(elt98_,elt99_);
ad(elt81_,elt98_);
Element elt100_=el(_doc,"td");
CustList<Attr> attrs62_=al(1);
attrs62_.add(at("border","1px"));
at(elt100_,attrs62_);
Element elt101_=el(_doc,"img");
CustList<Attr> attrs63_=al(1);
attrs63_.add(at("src","resources_cards/images/HEART_5.txt"));
at(elt101_,attrs63_);
ad(elt100_,elt101_);
ad(elt81_,elt100_);
Element elt102_=el(_doc,"td");
CustList<Attr> attrs64_=al(1);
attrs64_.add(at("border","1px"));
at(elt102_,attrs64_);
Element elt103_=el(_doc,"img");
CustList<Attr> attrs65_=al(1);
attrs65_.add(at("src","resources_cards/images/HEART_4.txt"));
at(elt103_,attrs65_);
ad(elt102_,elt103_);
ad(elt81_,elt102_);
Element elt104_=el(_doc,"td");
CustList<Attr> attrs66_=al(1);
attrs66_.add(at("border","1px"));
at(elt104_,attrs66_);
Element elt105_=el(_doc,"img");
CustList<Attr> attrs67_=al(1);
attrs67_.add(at("src","resources_cards/images/HEART_3.txt"));
at(elt105_,attrs67_);
ad(elt104_,elt105_);
ad(elt81_,elt104_);
Element elt106_=el(_doc,"td");
CustList<Attr> attrs68_=al(1);
attrs68_.add(at("border","1px"));
at(elt106_,attrs68_);
Element elt107_=el(_doc,"img");
CustList<Attr> attrs69_=al(1);
attrs69_.add(at("src","resources_cards/images/HEART_2.txt"));
at(elt107_,attrs69_);
ad(elt106_,elt107_);
ad(elt81_,elt106_);
Element elt108_=el(_doc,"td");
CustList<Attr> attrs70_=al(1);
attrs70_.add(at("border","1px"));
at(elt108_,attrs70_);
Element elt109_=el(_doc,"img");
CustList<Attr> attrs71_=al(1);
attrs71_.add(at("src","resources_cards/images/HEART_1.txt"));
at(elt109_,attrs71_);
ad(elt108_,elt109_);
ad(elt81_,elt108_);
ad(elt80_,elt81_);
ad(elt1_,elt80_);
Element elt110_=el(_doc,"br");
ad(elt1_,elt110_);
Element elt111_=el(_doc,"hr");
ad(elt1_,elt111_);
Text txt15_=tx(_doc,"Spade");
ad(elt1_,txt15_);
Element elt112_=el(_doc,"br");
ad(elt1_,elt112_);
Element elt113_=el(_doc,"table");
Element elt114_=el(_doc,"tr");
Element elt115_=el(_doc,"td");
CustList<Attr> attrs72_=al(1);
attrs72_.add(at("border","1px"));
at(elt115_,attrs72_);
Element elt116_=el(_doc,"img");
CustList<Attr> attrs73_=al(1);
attrs73_.add(at("src","resources_cards/images/SPADE_KING.txt"));
at(elt116_,attrs73_);
ad(elt115_,elt116_);
ad(elt114_,elt115_);
Element elt117_=el(_doc,"td");
CustList<Attr> attrs74_=al(1);
attrs74_.add(at("border","1px"));
at(elt117_,attrs74_);
Element elt118_=el(_doc,"img");
CustList<Attr> attrs75_=al(1);
attrs75_.add(at("src","resources_cards/images/SPADE_QUEEN.txt"));
at(elt118_,attrs75_);
ad(elt117_,elt118_);
ad(elt114_,elt117_);
Element elt119_=el(_doc,"td");
CustList<Attr> attrs76_=al(1);
attrs76_.add(at("border","1px"));
at(elt119_,attrs76_);
Element elt120_=el(_doc,"img");
CustList<Attr> attrs77_=al(1);
attrs77_.add(at("src","resources_cards/images/SPADE_KNIGHT.txt"));
at(elt120_,attrs77_);
ad(elt119_,elt120_);
ad(elt114_,elt119_);
Element elt121_=el(_doc,"td");
CustList<Attr> attrs78_=al(1);
attrs78_.add(at("border","1px"));
at(elt121_,attrs78_);
Element elt122_=el(_doc,"img");
CustList<Attr> attrs79_=al(1);
attrs79_.add(at("src","resources_cards/images/SPADE_JACK.txt"));
at(elt122_,attrs79_);
ad(elt121_,elt122_);
ad(elt114_,elt121_);
Element elt123_=el(_doc,"td");
CustList<Attr> attrs80_=al(1);
attrs80_.add(at("border","1px"));
at(elt123_,attrs80_);
Element elt124_=el(_doc,"img");
CustList<Attr> attrs81_=al(1);
attrs81_.add(at("src","resources_cards/images/SPADE_10.txt"));
at(elt124_,attrs81_);
ad(elt123_,elt124_);
ad(elt114_,elt123_);
Element elt125_=el(_doc,"td");
CustList<Attr> attrs82_=al(1);
attrs82_.add(at("border","1px"));
at(elt125_,attrs82_);
Element elt126_=el(_doc,"img");
CustList<Attr> attrs83_=al(1);
attrs83_.add(at("src","resources_cards/images/SPADE_9.txt"));
at(elt126_,attrs83_);
ad(elt125_,elt126_);
ad(elt114_,elt125_);
Element elt127_=el(_doc,"td");
CustList<Attr> attrs84_=al(1);
attrs84_.add(at("border","1px"));
at(elt127_,attrs84_);
Element elt128_=el(_doc,"img");
CustList<Attr> attrs85_=al(1);
attrs85_.add(at("src","resources_cards/images/SPADE_8.txt"));
at(elt128_,attrs85_);
ad(elt127_,elt128_);
ad(elt114_,elt127_);
Element elt129_=el(_doc,"td");
CustList<Attr> attrs86_=al(1);
attrs86_.add(at("border","1px"));
at(elt129_,attrs86_);
Element elt130_=el(_doc,"img");
CustList<Attr> attrs87_=al(1);
attrs87_.add(at("src","resources_cards/images/SPADE_7.txt"));
at(elt130_,attrs87_);
ad(elt129_,elt130_);
ad(elt114_,elt129_);
Element elt131_=el(_doc,"td");
CustList<Attr> attrs88_=al(1);
attrs88_.add(at("border","1px"));
at(elt131_,attrs88_);
Element elt132_=el(_doc,"img");
CustList<Attr> attrs89_=al(1);
attrs89_.add(at("src","resources_cards/images/SPADE_6.txt"));
at(elt132_,attrs89_);
ad(elt131_,elt132_);
ad(elt114_,elt131_);
Element elt133_=el(_doc,"td");
CustList<Attr> attrs90_=al(1);
attrs90_.add(at("border","1px"));
at(elt133_,attrs90_);
Element elt134_=el(_doc,"img");
CustList<Attr> attrs91_=al(1);
attrs91_.add(at("src","resources_cards/images/SPADE_5.txt"));
at(elt134_,attrs91_);
ad(elt133_,elt134_);
ad(elt114_,elt133_);
Element elt135_=el(_doc,"td");
CustList<Attr> attrs92_=al(1);
attrs92_.add(at("border","1px"));
at(elt135_,attrs92_);
Element elt136_=el(_doc,"img");
CustList<Attr> attrs93_=al(1);
attrs93_.add(at("src","resources_cards/images/SPADE_4.txt"));
at(elt136_,attrs93_);
ad(elt135_,elt136_);
ad(elt114_,elt135_);
Element elt137_=el(_doc,"td");
CustList<Attr> attrs94_=al(1);
attrs94_.add(at("border","1px"));
at(elt137_,attrs94_);
Element elt138_=el(_doc,"img");
CustList<Attr> attrs95_=al(1);
attrs95_.add(at("src","resources_cards/images/SPADE_3.txt"));
at(elt138_,attrs95_);
ad(elt137_,elt138_);
ad(elt114_,elt137_);
Element elt139_=el(_doc,"td");
CustList<Attr> attrs96_=al(1);
attrs96_.add(at("border","1px"));
at(elt139_,attrs96_);
Element elt140_=el(_doc,"img");
CustList<Attr> attrs97_=al(1);
attrs97_.add(at("src","resources_cards/images/SPADE_2.txt"));
at(elt140_,attrs97_);
ad(elt139_,elt140_);
ad(elt114_,elt139_);
Element elt141_=el(_doc,"td");
CustList<Attr> attrs98_=al(1);
attrs98_.add(at("border","1px"));
at(elt141_,attrs98_);
Element elt142_=el(_doc,"img");
CustList<Attr> attrs99_=al(1);
attrs99_.add(at("src","resources_cards/images/SPADE_1.txt"));
at(elt142_,attrs99_);
ad(elt141_,elt142_);
ad(elt114_,elt141_);
ad(elt113_,elt114_);
ad(elt1_,elt113_);
Element elt143_=el(_doc,"br");
ad(elt1_,elt143_);
Element elt144_=el(_doc,"hr");
ad(elt1_,elt144_);
Text txt16_=tx(_doc,"Diamond");
ad(elt1_,txt16_);
Element elt145_=el(_doc,"br");
ad(elt1_,elt145_);
Element elt146_=el(_doc,"table");
Element elt147_=el(_doc,"tr");
Element elt148_=el(_doc,"td");
CustList<Attr> attrs100_=al(1);
attrs100_.add(at("border","1px"));
at(elt148_,attrs100_);
Element elt149_=el(_doc,"img");
CustList<Attr> attrs101_=al(1);
attrs101_.add(at("src","resources_cards/images/DIAMOND_KING.txt"));
at(elt149_,attrs101_);
ad(elt148_,elt149_);
ad(elt147_,elt148_);
Element elt150_=el(_doc,"td");
CustList<Attr> attrs102_=al(1);
attrs102_.add(at("border","1px"));
at(elt150_,attrs102_);
Element elt151_=el(_doc,"img");
CustList<Attr> attrs103_=al(1);
attrs103_.add(at("src","resources_cards/images/DIAMOND_QUEEN.txt"));
at(elt151_,attrs103_);
ad(elt150_,elt151_);
ad(elt147_,elt150_);
Element elt152_=el(_doc,"td");
CustList<Attr> attrs104_=al(1);
attrs104_.add(at("border","1px"));
at(elt152_,attrs104_);
Element elt153_=el(_doc,"img");
CustList<Attr> attrs105_=al(1);
attrs105_.add(at("src","resources_cards/images/DIAMOND_KNIGHT.txt"));
at(elt153_,attrs105_);
ad(elt152_,elt153_);
ad(elt147_,elt152_);
Element elt154_=el(_doc,"td");
CustList<Attr> attrs106_=al(1);
attrs106_.add(at("border","1px"));
at(elt154_,attrs106_);
Element elt155_=el(_doc,"img");
CustList<Attr> attrs107_=al(1);
attrs107_.add(at("src","resources_cards/images/DIAMOND_JACK.txt"));
at(elt155_,attrs107_);
ad(elt154_,elt155_);
ad(elt147_,elt154_);
Element elt156_=el(_doc,"td");
CustList<Attr> attrs108_=al(1);
attrs108_.add(at("border","1px"));
at(elt156_,attrs108_);
Element elt157_=el(_doc,"img");
CustList<Attr> attrs109_=al(1);
attrs109_.add(at("src","resources_cards/images/DIAMOND_10.txt"));
at(elt157_,attrs109_);
ad(elt156_,elt157_);
ad(elt147_,elt156_);
Element elt158_=el(_doc,"td");
CustList<Attr> attrs110_=al(1);
attrs110_.add(at("border","1px"));
at(elt158_,attrs110_);
Element elt159_=el(_doc,"img");
CustList<Attr> attrs111_=al(1);
attrs111_.add(at("src","resources_cards/images/DIAMOND_9.txt"));
at(elt159_,attrs111_);
ad(elt158_,elt159_);
ad(elt147_,elt158_);
Element elt160_=el(_doc,"td");
CustList<Attr> attrs112_=al(1);
attrs112_.add(at("border","1px"));
at(elt160_,attrs112_);
Element elt161_=el(_doc,"img");
CustList<Attr> attrs113_=al(1);
attrs113_.add(at("src","resources_cards/images/DIAMOND_8.txt"));
at(elt161_,attrs113_);
ad(elt160_,elt161_);
ad(elt147_,elt160_);
Element elt162_=el(_doc,"td");
CustList<Attr> attrs114_=al(1);
attrs114_.add(at("border","1px"));
at(elt162_,attrs114_);
Element elt163_=el(_doc,"img");
CustList<Attr> attrs115_=al(1);
attrs115_.add(at("src","resources_cards/images/DIAMOND_7.txt"));
at(elt163_,attrs115_);
ad(elt162_,elt163_);
ad(elt147_,elt162_);
Element elt164_=el(_doc,"td");
CustList<Attr> attrs116_=al(1);
attrs116_.add(at("border","1px"));
at(elt164_,attrs116_);
Element elt165_=el(_doc,"img");
CustList<Attr> attrs117_=al(1);
attrs117_.add(at("src","resources_cards/images/DIAMOND_6.txt"));
at(elt165_,attrs117_);
ad(elt164_,elt165_);
ad(elt147_,elt164_);
Element elt166_=el(_doc,"td");
CustList<Attr> attrs118_=al(1);
attrs118_.add(at("border","1px"));
at(elt166_,attrs118_);
Element elt167_=el(_doc,"img");
CustList<Attr> attrs119_=al(1);
attrs119_.add(at("src","resources_cards/images/DIAMOND_5.txt"));
at(elt167_,attrs119_);
ad(elt166_,elt167_);
ad(elt147_,elt166_);
Element elt168_=el(_doc,"td");
CustList<Attr> attrs120_=al(1);
attrs120_.add(at("border","1px"));
at(elt168_,attrs120_);
Element elt169_=el(_doc,"img");
CustList<Attr> attrs121_=al(1);
attrs121_.add(at("src","resources_cards/images/DIAMOND_4.txt"));
at(elt169_,attrs121_);
ad(elt168_,elt169_);
ad(elt147_,elt168_);
Element elt170_=el(_doc,"td");
CustList<Attr> attrs122_=al(1);
attrs122_.add(at("border","1px"));
at(elt170_,attrs122_);
Element elt171_=el(_doc,"img");
CustList<Attr> attrs123_=al(1);
attrs123_.add(at("src","resources_cards/images/DIAMOND_3.txt"));
at(elt171_,attrs123_);
ad(elt170_,elt171_);
ad(elt147_,elt170_);
Element elt172_=el(_doc,"td");
CustList<Attr> attrs124_=al(1);
attrs124_.add(at("border","1px"));
at(elt172_,attrs124_);
Element elt173_=el(_doc,"img");
CustList<Attr> attrs125_=al(1);
attrs125_.add(at("src","resources_cards/images/DIAMOND_2.txt"));
at(elt173_,attrs125_);
ad(elt172_,elt173_);
ad(elt147_,elt172_);
Element elt174_=el(_doc,"td");
CustList<Attr> attrs126_=al(1);
attrs126_.add(at("border","1px"));
at(elt174_,attrs126_);
Element elt175_=el(_doc,"img");
CustList<Attr> attrs127_=al(1);
attrs127_.add(at("src","resources_cards/images/DIAMOND_1.txt"));
at(elt175_,attrs127_);
ad(elt174_,elt175_);
ad(elt147_,elt174_);
ad(elt146_,elt147_);
ad(elt1_,elt146_);
Element elt176_=el(_doc,"br");
ad(elt1_,elt176_);
Element elt177_=el(_doc,"hr");
ad(elt1_,elt177_);
Text txt17_=tx(_doc,"Club");
ad(elt1_,txt17_);
Element elt178_=el(_doc,"br");
ad(elt1_,elt178_);
Element elt179_=el(_doc,"table");
Element elt180_=el(_doc,"tr");
Element elt181_=el(_doc,"td");
CustList<Attr> attrs128_=al(1);
attrs128_.add(at("border","1px"));
at(elt181_,attrs128_);
Element elt182_=el(_doc,"img");
CustList<Attr> attrs129_=al(1);
attrs129_.add(at("src","resources_cards/images/CLUB_KING.txt"));
at(elt182_,attrs129_);
ad(elt181_,elt182_);
ad(elt180_,elt181_);
Element elt183_=el(_doc,"td");
CustList<Attr> attrs130_=al(1);
attrs130_.add(at("border","1px"));
at(elt183_,attrs130_);
Element elt184_=el(_doc,"img");
CustList<Attr> attrs131_=al(1);
attrs131_.add(at("src","resources_cards/images/CLUB_QUEEN.txt"));
at(elt184_,attrs131_);
ad(elt183_,elt184_);
ad(elt180_,elt183_);
Element elt185_=el(_doc,"td");
CustList<Attr> attrs132_=al(1);
attrs132_.add(at("border","1px"));
at(elt185_,attrs132_);
Element elt186_=el(_doc,"img");
CustList<Attr> attrs133_=al(1);
attrs133_.add(at("src","resources_cards/images/CLUB_KNIGHT.txt"));
at(elt186_,attrs133_);
ad(elt185_,elt186_);
ad(elt180_,elt185_);
Element elt187_=el(_doc,"td");
CustList<Attr> attrs134_=al(1);
attrs134_.add(at("border","1px"));
at(elt187_,attrs134_);
Element elt188_=el(_doc,"img");
CustList<Attr> attrs135_=al(1);
attrs135_.add(at("src","resources_cards/images/CLUB_JACK.txt"));
at(elt188_,attrs135_);
ad(elt187_,elt188_);
ad(elt180_,elt187_);
Element elt189_=el(_doc,"td");
CustList<Attr> attrs136_=al(1);
attrs136_.add(at("border","1px"));
at(elt189_,attrs136_);
Element elt190_=el(_doc,"img");
CustList<Attr> attrs137_=al(1);
attrs137_.add(at("src","resources_cards/images/CLUB_10.txt"));
at(elt190_,attrs137_);
ad(elt189_,elt190_);
ad(elt180_,elt189_);
Element elt191_=el(_doc,"td");
CustList<Attr> attrs138_=al(1);
attrs138_.add(at("border","1px"));
at(elt191_,attrs138_);
Element elt192_=el(_doc,"img");
CustList<Attr> attrs139_=al(1);
attrs139_.add(at("src","resources_cards/images/CLUB_9.txt"));
at(elt192_,attrs139_);
ad(elt191_,elt192_);
ad(elt180_,elt191_);
Element elt193_=el(_doc,"td");
CustList<Attr> attrs140_=al(1);
attrs140_.add(at("border","1px"));
at(elt193_,attrs140_);
Element elt194_=el(_doc,"img");
CustList<Attr> attrs141_=al(1);
attrs141_.add(at("src","resources_cards/images/CLUB_8.txt"));
at(elt194_,attrs141_);
ad(elt193_,elt194_);
ad(elt180_,elt193_);
Element elt195_=el(_doc,"td");
CustList<Attr> attrs142_=al(1);
attrs142_.add(at("border","1px"));
at(elt195_,attrs142_);
Element elt196_=el(_doc,"img");
CustList<Attr> attrs143_=al(1);
attrs143_.add(at("src","resources_cards/images/CLUB_7.txt"));
at(elt196_,attrs143_);
ad(elt195_,elt196_);
ad(elt180_,elt195_);
Element elt197_=el(_doc,"td");
CustList<Attr> attrs144_=al(1);
attrs144_.add(at("border","1px"));
at(elt197_,attrs144_);
Element elt198_=el(_doc,"img");
CustList<Attr> attrs145_=al(1);
attrs145_.add(at("src","resources_cards/images/CLUB_6.txt"));
at(elt198_,attrs145_);
ad(elt197_,elt198_);
ad(elt180_,elt197_);
Element elt199_=el(_doc,"td");
CustList<Attr> attrs146_=al(1);
attrs146_.add(at("border","1px"));
at(elt199_,attrs146_);
Element elt200_=el(_doc,"img");
CustList<Attr> attrs147_=al(1);
attrs147_.add(at("src","resources_cards/images/CLUB_5.txt"));
at(elt200_,attrs147_);
ad(elt199_,elt200_);
ad(elt180_,elt199_);
Element elt201_=el(_doc,"td");
CustList<Attr> attrs148_=al(1);
attrs148_.add(at("border","1px"));
at(elt201_,attrs148_);
Element elt202_=el(_doc,"img");
CustList<Attr> attrs149_=al(1);
attrs149_.add(at("src","resources_cards/images/CLUB_4.txt"));
at(elt202_,attrs149_);
ad(elt201_,elt202_);
ad(elt180_,elt201_);
Element elt203_=el(_doc,"td");
CustList<Attr> attrs150_=al(1);
attrs150_.add(at("border","1px"));
at(elt203_,attrs150_);
Element elt204_=el(_doc,"img");
CustList<Attr> attrs151_=al(1);
attrs151_.add(at("src","resources_cards/images/CLUB_3.txt"));
at(elt204_,attrs151_);
ad(elt203_,elt204_);
ad(elt180_,elt203_);
Element elt205_=el(_doc,"td");
CustList<Attr> attrs152_=al(1);
attrs152_.add(at("border","1px"));
at(elt205_,attrs152_);
Element elt206_=el(_doc,"img");
CustList<Attr> attrs153_=al(1);
attrs153_.add(at("src","resources_cards/images/CLUB_2.txt"));
at(elt206_,attrs153_);
ad(elt205_,elt206_);
ad(elt180_,elt205_);
Element elt207_=el(_doc,"td");
CustList<Attr> attrs154_=al(1);
attrs154_.add(at("border","1px"));
at(elt207_,attrs154_);
Element elt208_=el(_doc,"img");
CustList<Attr> attrs155_=al(1);
attrs155_.add(at("src","resources_cards/images/CLUB_1.txt"));
at(elt208_,attrs155_);
ad(elt207_,elt208_);
ad(elt180_,elt207_);
ad(elt179_,elt180_);
ad(elt1_,elt179_);
Element elt209_=el(_doc,"br");
ad(elt1_,elt209_);
Element elt210_=el(_doc,"hr");
ad(elt1_,elt210_);
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
