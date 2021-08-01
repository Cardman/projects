package code.scripts.pages.aiki;
import code.sml.*;
import code.util.*;
import code.util.ints.*;
final class PageDataMovesEffectsEffglobal{
private PageDataMovesEffectsEffglobal(){}
static Document build(){
FullDocument doc_ = DocumentBuilder.newXmlDocument(4);
build(doc_);
return doc_;
}
static void build(Document _doc){
Element elt0_=el(_doc,"html");
CustList<Attr> attrs0_=al(2);
attrs0_.add(at("xmlns:c","javahtml"));
attrs0_.add(at("c:bean","eff_global"));
at(elt0_,attrs0_);
Element elt1_=el(_doc,"head");
Element elt2_=el(_doc,"link");
CustList<Attr> attrs1_=al(3);
attrs1_.add(at("href","web/css/moves.css"));
attrs1_.add(at("rel","stylesheet"));
attrs1_.add(at("type","text/css"));
at(elt2_,attrs1_);
ad(elt1_,elt2_);
ad(elt0_,elt1_);
Element elt3_=el(_doc,"body");
build0(elt3_,_doc);
ad(elt0_,elt3_);
_doc.appendChild(elt0_);
}
static void build0(Element _body,Document _doc){
Element elt0_=el(_doc,"p");
Element elt1_=el(_doc,"c:message");
CustList<Attr> attrs0_=al(1);
attrs0_.add(at("value","msg_effglobal,effect"));
at(elt1_,attrs0_);
ad(elt0_,elt1_);
Element elt2_=el(_doc,"c:import");
CustList<Attr> attrs1_=al(1);
attrs1_.add(at("page","{effectBean}"));
at(elt2_,attrs1_);
Element elt3_=el(_doc,"c:package");
CustList<Attr> attrs2_=al(1);
attrs2_.add(at("name","aiki.beans.moves.effects"));
at(elt3_,attrs2_);
Element elt4_=el(_doc,"c:class");
CustList<Attr> attrs3_=al(1);
attrs3_.add(at("name","EffectBean"));
at(elt4_,attrs3_);
Element elt5_=el(_doc,"c:field");
CustList<Attr> attrs4_=al(1);
attrs4_.add(at("prepare","$intern.index=index"));
at(elt5_,attrs4_);
ad(elt4_,elt5_);
Element elt6_=el(_doc,"c:field");
CustList<Attr> attrs5_=al(1);
attrs5_.add(at("prepare","$intern.move=move"));
at(elt6_,attrs5_);
ad(elt4_,elt6_);
ad(elt3_,elt4_);
ad(elt2_,elt3_);
ad(elt0_,elt2_);
Element elt7_=el(_doc,"c:if");
CustList<Attr> attrs6_=al(1);
attrs6_.add(at("condition","weather"));
at(elt7_,attrs6_);
Element elt8_=el(_doc,"c:message");
CustList<Attr> attrs7_=al(1);
attrs7_.add(at("value","msg_effglobal,is_weather"));
at(elt8_,attrs7_);
ad(elt7_,elt8_);
ad(elt0_,elt7_);
Element elt9_=el(_doc,"c:if");
CustList<Attr> attrs8_=al(1);
attrs8_.add(at("condition","!weather"));
at(elt9_,attrs8_);
Element elt10_=el(_doc,"c:message");
CustList<Attr> attrs9_=al(1);
attrs9_.add(at("value","msg_effglobal,is_not_weather"));
at(elt10_,attrs9_);
ad(elt9_,elt10_);
ad(elt0_,elt9_);
Element elt11_=el(_doc,"c:if");
CustList<Attr> attrs10_=al(1);
attrs10_.add(at("condition","canceledIfUsed"));
at(elt11_,attrs10_);
Element elt12_=el(_doc,"c:message");
CustList<Attr> attrs11_=al(1);
attrs11_.add(at("value","msg_effglobal,cancel_reuse"));
at(elt12_,attrs11_);
ad(elt11_,elt12_);
ad(elt0_,elt11_);
Element elt13_=el(_doc,"c:if");
CustList<Attr> attrs12_=al(1);
attrs12_.add(at("condition","reverseOrderOfSortBySpeed"));
at(elt13_,attrs12_);
Element elt14_=el(_doc,"c:message");
CustList<Attr> attrs13_=al(1);
attrs13_.add(at("value","msg_effglobal,reverse_speed"));
at(elt14_,attrs13_);
ad(elt13_,elt14_);
ad(elt0_,elt13_);
Element elt15_=el(_doc,"c:if");
CustList<Attr> attrs14_=al(1);
attrs14_.add(at("condition","unusableItem"));
at(elt15_,attrs14_);
Element elt16_=el(_doc,"c:message");
CustList<Attr> attrs15_=al(1);
attrs15_.add(at("value","msg_effglobal,unusable_item"));
at(elt16_,attrs15_);
ad(elt15_,elt16_);
ad(elt0_,elt15_);
Element elt17_=el(_doc,"c:if");
CustList<Attr> attrs16_=al(1);
attrs16_.add(at("condition","puttingKo"));
at(elt17_,attrs16_);
Element elt18_=el(_doc,"c:message");
CustList<Attr> attrs17_=al(1);
attrs17_.add(at("value","msg_effglobal,putting_ko"));
at(elt18_,attrs17_);
ad(elt17_,elt18_);
ad(elt0_,elt17_);
Element elt19_=el(_doc,"c:if");
CustList<Attr> attrs18_=al(1);
attrs18_.add(at("condition","!multAccuracy.isZero()"));
at(elt19_,attrs18_);
Element elt20_=el(_doc,"c:message");
CustList<Attr> attrs19_=al(1);
attrs19_.add(at("value","msg_effglobal,mult_acc"));
at(elt20_,attrs19_);
Element elt21_=el(_doc,"param");
CustList<Attr> attrs20_=al(1);
attrs20_.add(at("value","multAccuracy"));
at(elt21_,attrs20_);
ad(elt20_,elt21_);
ad(elt19_,elt20_);
ad(elt0_,elt19_);
Element elt22_=el(_doc,"c:if");
CustList<Attr> attrs21_=al(1);
attrs21_.add(at("condition","!damageEndRound.isZero()"));
at(elt22_,attrs21_);
Element elt23_=el(_doc,"c:message");
CustList<Attr> attrs22_=al(1);
attrs22_.add(at("value","msg_effglobal,damage_end_round"));
at(elt23_,attrs22_);
Element elt24_=el(_doc,"param");
CustList<Attr> attrs23_=al(1);
attrs23_.add(at("value","damageEndRound"));
at(elt24_,attrs23_);
ad(elt23_,elt24_);
ad(elt22_,elt23_);
ad(elt0_,elt22_);
Element elt25_=el(_doc,"c:if");
CustList<Attr> attrs24_=al(1);
attrs24_.add(at("condition","!healingEndRoundGround.isZero()"));
at(elt25_,attrs24_);
Element elt26_=el(_doc,"c:message");
CustList<Attr> attrs25_=al(1);
attrs25_.add(at("value","msg_effglobal,healing_end_round_ground"));
at(elt26_,attrs25_);
Element elt27_=el(_doc,"param");
CustList<Attr> attrs26_=al(1);
attrs26_.add(at("value","healingEndRoundGround"));
at(elt27_,attrs26_);
ad(elt26_,elt27_);
ad(elt25_,elt26_);
ad(elt0_,elt25_);
Element elt28_=el(_doc,"c:if");
CustList<Attr> attrs27_=al(1);
attrs27_.add(at("condition","!healingEndRound.isZero()"));
at(elt28_,attrs27_);
Element elt29_=el(_doc,"c:message");
CustList<Attr> attrs28_=al(1);
attrs28_.add(at("value","msg_effglobal,healing_end_round"));
at(elt29_,attrs28_);
Element elt30_=el(_doc,"param");
CustList<Attr> attrs29_=al(1);
attrs29_.add(at("value","healingEndRound"));
at(elt30_,attrs29_);
ad(elt29_,elt30_);
ad(elt28_,elt29_);
ad(elt0_,elt28_);
Element elt31_=el(_doc,"c:if");
CustList<Attr> attrs30_=al(1);
attrs30_.add(at("condition","!multEffectLovingAlly.isZero()"));
at(elt31_,attrs30_);
Element elt32_=el(_doc,"c:message");
CustList<Attr> attrs31_=al(1);
attrs31_.add(at("value","msg_effglobal,mult_love"));
at(elt32_,attrs31_);
Element elt33_=el(_doc,"param");
CustList<Attr> attrs32_=al(1);
attrs32_.add(at("value","multEffectLovingAlly"));
at(elt33_,attrs32_);
ad(elt32_,elt33_);
ad(elt31_,elt32_);
ad(elt0_,elt31_);
Element elt34_=el(_doc,"c:if");
CustList<Attr> attrs33_=al(1);
attrs33_.add(at("condition","!preventStatus.isEmpty()"));
at(elt34_,attrs33_);
Element elt35_=el(_doc,"c:message");
CustList<Attr> attrs34_=al(1);
attrs34_.add(at("value","msg_effglobal,forbid_status"));
at(elt35_,attrs34_);
ad(elt34_,elt35_);
Element elt36_=el(_doc,"ul");
Element elt37_=el(_doc,"c:for");
CustList<Attr> attrs35_=al(3);
attrs35_.add(at("list","preventStatus"));
attrs35_.add(at("var","s"));
attrs35_.add(at("className","java.lang.Object"));
at(elt37_,attrs35_);
Element elt38_=el(_doc,"li");
Element elt39_=el(_doc,"a");
CustList<Attr> attrs36_=al(2);
attrs36_.add(at("c:command","$clickPreventedStatus({([s])})"));
attrs36_.add(at("href",""));
at(elt39_,attrs36_);
Text txt0_=tx(_doc,"{getTrPreventedStatus(([s]))}");
ad(elt39_,txt0_);
ad(elt38_,elt39_);
ad(elt37_,elt38_);
ad(elt36_,elt37_);
ad(elt34_,elt36_);
Element elt40_=el(_doc,"br");
ad(elt34_,elt40_);
ad(elt0_,elt34_);
Element elt41_=el(_doc,"c:if");
CustList<Attr> attrs37_=al(1);
attrs37_.add(at("condition","!immuneTypes.isEmpty()"));
at(elt41_,attrs37_);
Element elt42_=el(_doc,"c:message");
CustList<Attr> attrs38_=al(1);
attrs38_.add(at("value","msg_effglobal,immune_types"));
at(elt42_,attrs38_);
ad(elt41_,elt42_);
Element elt43_=el(_doc,"ul");
Element elt44_=el(_doc,"c:for");
CustList<Attr> attrs39_=al(3);
attrs39_.add(at("list","immuneTypes"));
attrs39_.add(at("var","t"));
attrs39_.add(at("className","java.lang.String"));
at(elt44_,attrs39_);
Element elt45_=el(_doc,"li");
Text txt1_=tx(_doc,"{t}");
ad(elt45_,txt1_);
ad(elt44_,elt45_);
ad(elt43_,elt44_);
ad(elt41_,elt43_);
Element elt46_=el(_doc,"br");
ad(elt41_,elt46_);
ad(elt0_,elt41_);
Element elt47_=el(_doc,"c:if");
CustList<Attr> attrs40_=al(1);
attrs40_.add(at("condition","!efficiencyMoves.isEmpty()"));
at(elt47_,attrs40_);
Element elt48_=el(_doc,"c:message");
CustList<Attr> attrs41_=al(1);
attrs41_.add(at("value","msg_effglobal,efficiency_table"));
at(elt48_,attrs41_);
ad(elt47_,elt48_);
Element elt49_=el(_doc,"table");
Element elt50_=el(_doc,"thead");
Element elt51_=el(_doc,"tr");
Element elt52_=el(_doc,"th");
Element elt53_=el(_doc,"c:message");
CustList<Attr> attrs42_=al(1);
attrs42_.add(at("value","msg_effglobal,damage_type"));
at(elt53_,attrs42_);
ad(elt52_,elt53_);
ad(elt51_,elt52_);
Element elt54_=el(_doc,"th");
Element elt55_=el(_doc,"c:message");
CustList<Attr> attrs43_=al(1);
attrs43_.add(at("value","msg_effglobal,pokemon_type"));
at(elt55_,attrs43_);
ad(elt54_,elt55_);
ad(elt51_,elt54_);
Element elt56_=el(_doc,"th");
Element elt57_=el(_doc,"c:message");
CustList<Attr> attrs44_=al(1);
attrs44_.add(at("value","msg_effglobal,efficiency"));
at(elt57_,attrs44_);
ad(elt56_,elt57_);
ad(elt51_,elt56_);
ad(elt50_,elt51_);
ad(elt49_,elt50_);
Element elt58_=el(_doc,"tbody");
Element elt59_=el(_doc,"c:for");
CustList<Attr> attrs45_=al(5);
attrs45_.add(at("key","c"));
attrs45_.add(at("keyClassName","aiki.fight.util.TypesDuo"));
attrs45_.add(at("map","efficiencyMoves"));
attrs45_.add(at("value","r"));
attrs45_.add(at("varClassName","r"));
at(elt59_,attrs45_);
Element elt60_=el(_doc,"tr");
Element elt61_=el(_doc,"td");
Text txt2_=tx(_doc,"{c.getDamageType()}");
ad(elt61_,txt2_);
ad(elt60_,elt61_);
Element elt62_=el(_doc,"td");
Text txt3_=tx(_doc,"{c.getPokemonType()}");
ad(elt62_,txt3_);
ad(elt60_,elt62_);
Element elt63_=el(_doc,"td");
Text txt4_=tx(_doc,"{r}");
ad(elt63_,txt4_);
ad(elt60_,elt63_);
ad(elt59_,elt60_);
ad(elt58_,elt59_);
ad(elt49_,elt58_);
ad(elt47_,elt49_);
Element elt64_=el(_doc,"br");
ad(elt47_,elt64_);
ad(elt0_,elt47_);
Element elt65_=el(_doc,"c:if");
CustList<Attr> attrs46_=al(1);
attrs46_.add(at("condition","!disableImmuAgainstTypes.isEmpty()"));
at(elt65_,attrs46_);
Element elt66_=el(_doc,"c:message");
CustList<Attr> attrs47_=al(1);
attrs47_.add(at("value","msg_effglobal,disable_immu_types"));
at(elt66_,attrs47_);
ad(elt65_,elt66_);
Element elt67_=el(_doc,"ul");
Element elt68_=el(_doc,"c:for");
CustList<Attr> attrs48_=al(3);
attrs48_.add(at("list","disableImmuAgainstTypes"));
attrs48_.add(at("var","t"));
attrs48_.add(at("className","java.lang.String"));
at(elt68_,attrs48_);
Element elt69_=el(_doc,"li");
Text txt5_=tx(_doc,"{t}");
ad(elt69_,txt5_);
ad(elt68_,elt69_);
ad(elt67_,elt68_);
ad(elt65_,elt67_);
Element elt70_=el(_doc,"br");
ad(elt65_,elt70_);
ad(elt0_,elt65_);
Element elt71_=el(_doc,"c:if");
CustList<Attr> attrs49_=al(1);
attrs49_.add(at("condition","!cancelProtectingAbilities.isEmpty()"));
at(elt71_,attrs49_);
Element elt72_=el(_doc,"c:message");
CustList<Attr> attrs50_=al(1);
attrs50_.add(at("value","msg_effglobal,disable_immu_abilities"));
at(elt72_,attrs50_);
ad(elt71_,elt72_);
Element elt73_=el(_doc,"ul");
Element elt74_=el(_doc,"c:for");
CustList<Attr> attrs51_=al(3);
attrs51_.add(at("list","cancelProtectingAbilities"));
attrs51_.add(at("var","t"));
attrs51_.add(at("className","java.lang.Object"));
at(elt74_,attrs51_);
Element elt75_=el(_doc,"li");
Element elt76_=el(_doc,"a");
CustList<Attr> attrs52_=al(2);
attrs52_.add(at("c:command","$clickCancelledAbility({([t])})"));
attrs52_.add(at("href",""));
at(elt76_,attrs52_);
Text txt6_=tx(_doc,"{getTrCancelledAbility(([t]))}");
ad(elt76_,txt6_);
ad(elt75_,elt76_);
ad(elt74_,elt75_);
ad(elt73_,elt74_);
ad(elt71_,elt73_);
Element elt77_=el(_doc,"br");
ad(elt71_,elt77_);
ad(elt0_,elt71_);
Element elt78_=el(_doc,"c:if");
CustList<Attr> attrs53_=al(1);
attrs53_.add(at("condition","!unusableMoves.isEmpty()"));
at(elt78_,attrs53_);
Element elt79_=el(_doc,"c:message");
CustList<Attr> attrs54_=al(1);
attrs54_.add(at("value","msg_effglobal,unusable_moves"));
at(elt79_,attrs54_);
ad(elt78_,elt79_);
Element elt80_=el(_doc,"ul");
Element elt81_=el(_doc,"c:for");
CustList<Attr> attrs55_=al(2);
attrs55_.add(at("list","unusableMoves"));
attrs55_.add(at("var","t"));
at(elt81_,attrs55_);
Element elt82_=el(_doc,"li");
Element elt83_=el(_doc,"a");
CustList<Attr> attrs56_=al(2);
attrs56_.add(at("c:command","$clickUnusableMove({([t])})"));
attrs56_.add(at("href",""));
at(elt83_,attrs56_);
Text txt7_=tx(_doc,"{getTrUnusableMoves(([t]))}");
ad(elt83_,txt7_);
ad(elt82_,elt83_);
ad(elt81_,elt82_);
ad(elt80_,elt81_);
ad(elt78_,elt80_);
Element elt84_=el(_doc,"br");
ad(elt78_,elt84_);
ad(elt0_,elt78_);
Element elt85_=el(_doc,"c:if");
CustList<Attr> attrs57_=al(1);
attrs57_.add(at("condition","!cancelEffects.isEmpty()"));
at(elt85_,attrs57_);
Element elt86_=el(_doc,"c:message");
CustList<Attr> attrs58_=al(1);
attrs58_.add(at("value","msg_effglobal,cancel_effects"));
at(elt86_,attrs58_);
ad(elt85_,elt86_);
Element elt87_=el(_doc,"ul");
Element elt88_=el(_doc,"c:for");
CustList<Attr> attrs59_=al(2);
attrs59_.add(at("list","cancelEffects"));
attrs59_.add(at("var","t"));
at(elt88_,attrs59_);
Element elt89_=el(_doc,"li");
Element elt90_=el(_doc,"a");
CustList<Attr> attrs60_=al(2);
attrs60_.add(at("c:command","$clickCancelledEffect({([t])})"));
attrs60_.add(at("href",""));
at(elt90_,attrs60_);
Text txt8_=tx(_doc,"{getTrCancelledEffect(([t]))}");
ad(elt90_,txt8_);
ad(elt89_,elt90_);
ad(elt88_,elt89_);
ad(elt87_,elt88_);
ad(elt85_,elt87_);
Element elt91_=el(_doc,"br");
ad(elt85_,elt91_);
ad(elt0_,elt85_);
Element elt92_=el(_doc,"c:if");
CustList<Attr> attrs61_=al(1);
attrs61_.add(at("condition","!multPowerMoves.isEmpty()"));
at(elt92_,attrs61_);
Element elt93_=el(_doc,"c:message");
CustList<Attr> attrs62_=al(1);
attrs62_.add(at("value","msg_effglobal,mult_power_move"));
at(elt93_,attrs62_);
ad(elt92_,elt93_);
Element elt94_=el(_doc,"table");
Element elt95_=el(_doc,"thead");
Element elt96_=el(_doc,"tr");
Element elt97_=el(_doc,"th");
Element elt98_=el(_doc,"c:message");
CustList<Attr> attrs63_=al(1);
attrs63_.add(at("value","msg_effglobal,move"));
at(elt98_,attrs63_);
ad(elt97_,elt98_);
ad(elt96_,elt97_);
Element elt99_=el(_doc,"th");
Element elt100_=el(_doc,"c:message");
CustList<Attr> attrs64_=al(1);
attrs64_.add(at("value","msg_effglobal,rate_damage"));
at(elt100_,attrs64_);
ad(elt99_,elt100_);
ad(elt96_,elt99_);
ad(elt95_,elt96_);
ad(elt94_,elt95_);
Element elt101_=el(_doc,"tbody");
Element elt102_=el(_doc,"c:for");
CustList<Attr> attrs65_=al(5);
attrs65_.add(at("key","c"));
attrs65_.add(at("map","multPowerMoves"));
attrs65_.add(at("value","r"));
attrs65_.add(at("keyClassName","java.lang.Object"));
attrs65_.add(at("varClassName","r"));
at(elt102_,attrs65_);
Element elt103_=el(_doc,"tr");
Element elt104_=el(_doc,"td");
Element elt105_=el(_doc,"a");
CustList<Attr> attrs66_=al(2);
attrs66_.add(at("c:command","$clickMultMovePower({([c])})"));
attrs66_.add(at("href",""));
at(elt105_,attrs66_);
Text txt9_=tx(_doc,"{getTrMultMovePower(([c]))}");
ad(elt105_,txt9_);
ad(elt104_,elt105_);
ad(elt103_,elt104_);
Element elt106_=el(_doc,"td");
Text txt10_=tx(_doc,"{r}");
ad(elt106_,txt10_);
ad(elt103_,elt106_);
ad(elt102_,elt103_);
ad(elt101_,elt102_);
ad(elt94_,elt101_);
ad(elt92_,elt94_);
Element elt107_=el(_doc,"br");
ad(elt92_,elt107_);
ad(elt0_,elt92_);
Element elt108_=el(_doc,"c:if");
CustList<Attr> attrs67_=al(1);
attrs67_.add(at("condition","!multDamageTypesMoves.isEmpty()"));
at(elt108_,attrs67_);
Element elt109_=el(_doc,"c:message");
CustList<Attr> attrs68_=al(1);
attrs68_.add(at("value","msg_effglobal,mult_power_type"));
at(elt109_,attrs68_);
ad(elt108_,elt109_);
Element elt110_=el(_doc,"table");
Element elt111_=el(_doc,"thead");
Element elt112_=el(_doc,"tr");
Element elt113_=el(_doc,"th");
Element elt114_=el(_doc,"c:message");
CustList<Attr> attrs69_=al(1);
attrs69_.add(at("value","msg_effglobal,move_type"));
at(elt114_,attrs69_);
ad(elt113_,elt114_);
ad(elt112_,elt113_);
Element elt115_=el(_doc,"th");
Element elt116_=el(_doc,"c:message");
CustList<Attr> attrs70_=al(1);
attrs70_.add(at("value","msg_effglobal,rate_damage"));
at(elt116_,attrs70_);
ad(elt115_,elt116_);
ad(elt112_,elt115_);
ad(elt111_,elt112_);
ad(elt110_,elt111_);
Element elt117_=el(_doc,"tbody");
Element elt118_=el(_doc,"c:for");
CustList<Attr> attrs71_=al(5);
attrs71_.add(at("key","c"));
attrs71_.add(at("map","multDamageTypesMoves"));
attrs71_.add(at("value","r"));
attrs71_.add(at("keyClassName","java.lang.String"));
attrs71_.add(at("varClassName","r"));
at(elt118_,attrs71_);
Element elt119_=el(_doc,"tr");
Element elt120_=el(_doc,"td");
Text txt11_=tx(_doc,"{c}");
ad(elt120_,txt11_);
ad(elt119_,elt120_);
Element elt121_=el(_doc,"td");
Text txt12_=tx(_doc,"{r}");
ad(elt121_,txt12_);
ad(elt119_,elt121_);
ad(elt118_,elt119_);
ad(elt117_,elt118_);
ad(elt110_,elt117_);
ad(elt108_,elt110_);
Element elt122_=el(_doc,"br");
ad(elt108_,elt122_);
ad(elt0_,elt108_);
Element elt123_=el(_doc,"c:if");
CustList<Attr> attrs72_=al(1);
attrs72_.add(at("condition","!cancelChgtStat.isEmpty()"));
at(elt123_,attrs72_);
Element elt124_=el(_doc,"c:message");
CustList<Attr> attrs73_=al(1);
attrs73_.add(at("value","msg_effglobal,cancel_chgt_statis"));
at(elt124_,attrs73_);
ad(elt123_,elt124_);
Element elt125_=el(_doc,"ul");
Element elt126_=el(_doc,"c:for");
CustList<Attr> attrs74_=al(3);
attrs74_.add(at("list","cancelChgtStat"));
attrs74_.add(at("var","t"));
attrs74_.add(at("className","java.lang.String"));
at(elt126_,attrs74_);
Element elt127_=el(_doc,"li");
Text txt13_=tx(_doc,"{t}");
ad(elt127_,txt13_);
ad(elt126_,elt127_);
ad(elt125_,elt126_);
ad(elt123_,elt125_);
Element elt128_=el(_doc,"br");
ad(elt123_,elt128_);
ad(elt0_,elt123_);
Element elt129_=el(_doc,"c:if");
CustList<Attr> attrs75_=al(1);
attrs75_.add(at("condition","!isEmpty(invokedMoveTerrain)"));
at(elt129_,attrs75_);
Element elt130_=el(_doc,"c:message");
CustList<Attr> attrs76_=al(1);
attrs76_.add(at("value","msg_effglobal,invoked_move"));
at(elt130_,attrs76_);
Element elt131_=el(_doc,"param");
CustList<Attr> attrs77_=al(1);
attrs77_.add(at("value","getTrInvokedMoveTerrain()"));
at(elt131_,attrs77_);
ad(elt130_,elt131_);
ad(elt129_,elt130_);
Element elt132_=el(_doc,"ul");
Element elt133_=el(_doc,"c:for");
CustList<Attr> attrs78_=al(2);
attrs78_.add(at("list","invokingMoves"));
attrs78_.add(at("var","t"));
at(elt133_,attrs78_);
Element elt134_=el(_doc,"li");
Element elt135_=el(_doc,"a");
CustList<Attr> attrs79_=al(2);
attrs79_.add(at("c:command","$clickInvokingMove({([t])})"));
attrs79_.add(at("href",""));
at(elt135_,attrs79_);
Text txt14_=tx(_doc,"{getTrInvokingMove(([t]))}");
ad(elt135_,txt14_);
ad(elt134_,elt135_);
ad(elt133_,elt134_);
ad(elt132_,elt133_);
ad(elt129_,elt132_);
Element elt136_=el(_doc,"br");
ad(elt129_,elt136_);
ad(elt0_,elt129_);
Element elt137_=el(_doc,"c:if");
CustList<Attr> attrs80_=al(1);
attrs80_.add(at("condition","!changedTypesTerrain.isEmpty()"));
at(elt137_,attrs80_);
Element elt138_=el(_doc,"c:message");
CustList<Attr> attrs81_=al(1);
attrs81_.add(at("value","msg_effglobal,changing_type_invoked"));
at(elt138_,attrs81_);
ad(elt137_,elt138_);
Element elt139_=el(_doc,"ul");
Element elt140_=el(_doc,"c:for");
CustList<Attr> attrs82_=al(3);
attrs82_.add(at("list","changedTypesTerrain"));
attrs82_.add(at("var","t"));
attrs82_.add(at("className","java.lang.String"));
at(elt140_,attrs82_);
Element elt141_=el(_doc,"li");
Text txt15_=tx(_doc,"{t}");
ad(elt141_,txt15_);
ad(elt140_,elt141_);
ad(elt139_,elt140_);
ad(elt137_,elt139_);
Element elt142_=el(_doc,"br");
ad(elt137_,elt142_);
Element elt143_=el(_doc,"c:message");
CustList<Attr> attrs83_=al(1);
attrs83_.add(at("value","msg_effglobal,changing_type_invoking"));
at(elt143_,attrs83_);
ad(elt137_,elt143_);
Element elt144_=el(_doc,"ul");
Element elt145_=el(_doc,"c:for");
CustList<Attr> attrs84_=al(3);
attrs84_.add(at("list","invokingMovesChangingTypes"));
attrs84_.add(at("var","t"));
attrs84_.add(at("className","java.lang.Object"));
at(elt145_,attrs84_);
Element elt146_=el(_doc,"li");
Element elt147_=el(_doc,"a");
CustList<Attr> attrs85_=al(2);
attrs85_.add(at("c:command","$clickInvokingMoveTypes({([t])})"));
attrs85_.add(at("href",""));
at(elt147_,attrs85_);
Text txt16_=tx(_doc,"{getTrInvokingMoveTypes(([t]))}");
ad(elt147_,txt16_);
ad(elt146_,elt147_);
ad(elt145_,elt146_);
ad(elt144_,elt145_);
ad(elt137_,elt144_);
Element elt148_=el(_doc,"br");
ad(elt137_,elt148_);
ad(elt0_,elt137_);
Element elt149_=el(_doc,"c:if");
CustList<Attr> attrs86_=al(1);
attrs86_.add(at("condition","!multStatIfContainsType.isEmpty()"));
at(elt149_,attrs86_);
Element elt150_=el(_doc,"c:message");
CustList<Attr> attrs87_=al(1);
attrs87_.add(at("value","msg_effglobal,mult_stat_type"));
at(elt150_,attrs87_);
ad(elt149_,elt150_);
Element elt151_=el(_doc,"table");
Element elt152_=el(_doc,"thead");
Element elt153_=el(_doc,"tr");
Element elt154_=el(_doc,"th");
Element elt155_=el(_doc,"c:message");
CustList<Attr> attrs88_=al(1);
attrs88_.add(at("value","msg_effglobal,statistic"));
at(elt155_,attrs88_);
ad(elt154_,elt155_);
ad(elt153_,elt154_);
Element elt156_=el(_doc,"th");
Element elt157_=el(_doc,"c:message");
CustList<Attr> attrs89_=al(1);
attrs89_.add(at("value","msg_effglobal,pokemon_type_stat"));
at(elt157_,attrs89_);
ad(elt156_,elt157_);
ad(elt153_,elt156_);
Element elt158_=el(_doc,"th");
Element elt159_=el(_doc,"c:message");
CustList<Attr> attrs90_=al(1);
attrs90_.add(at("value","msg_effglobal,rate_pokemon_statistic"));
at(elt159_,attrs90_);
ad(elt158_,elt159_);
ad(elt153_,elt158_);
ad(elt152_,elt153_);
ad(elt151_,elt152_);
Element elt160_=el(_doc,"tbody");
Element elt161_=el(_doc,"c:for");
CustList<Attr> attrs91_=al(5);
attrs91_.add(at("key","c"));
attrs91_.add(at("map","multStatIfContainsType"));
attrs91_.add(at("value","r"));
attrs91_.add(at("keyClassName","java.lang.Object"));
attrs91_.add(at("varClassName","r"));
at(elt161_,attrs91_);
Element elt162_=el(_doc,"tr");
Element elt163_=el(_doc,"td");
Text txt17_=tx(_doc,"{getTrMultStatIfDamgeTypeFirst(([c]))}");
ad(elt163_,txt17_);
ad(elt162_,elt163_);
Element elt164_=el(_doc,"td");
Text txt18_=tx(_doc,"{getTrMultStatIfDamgeTypeSecond(([c]))}");
ad(elt164_,txt18_);
ad(elt162_,elt164_);
Element elt165_=el(_doc,"td");
Text txt19_=tx(_doc,"{r}");
ad(elt165_,txt19_);
ad(elt162_,elt165_);
ad(elt161_,elt162_);
ad(elt160_,elt161_);
ad(elt151_,elt160_);
ad(elt149_,elt151_);
Element elt166_=el(_doc,"br");
ad(elt149_,elt166_);
ad(elt0_,elt149_);
Element elt167_=el(_doc,"c:if");
CustList<Attr> attrs92_=al(1);
attrs92_.add(at("condition","!multDamagePrepaRound.isEmpty()"));
at(elt167_,attrs92_);
Element elt168_=el(_doc,"c:message");
CustList<Attr> attrs93_=al(1);
attrs93_.add(at("value","msg_effglobal,mult_damage_type"));
at(elt168_,attrs93_);
ad(elt167_,elt168_);
Element elt169_=el(_doc,"ul");
Element elt170_=el(_doc,"c:for");
CustList<Attr> attrs94_=al(2);
attrs94_.add(at("list","movesUsedByTargetedFighters"));
attrs94_.add(at("var","t"));
at(elt170_,attrs94_);
Element elt171_=el(_doc,"li");
Element elt172_=el(_doc,"a");
CustList<Attr> attrs95_=al(2);
attrs95_.add(at("c:command","$clickMovesTarget({([t])})"));
attrs95_.add(at("href",""));
at(elt172_,attrs95_);
Text txt20_=tx(_doc,"{getTrMovesTarget(([t]))}");
ad(elt172_,txt20_);
ad(elt171_,elt172_);
ad(elt170_,elt171_);
ad(elt169_,elt170_);
ad(elt167_,elt169_);
Element elt173_=el(_doc,"br");
ad(elt167_,elt173_);
Element elt174_=el(_doc,"table");
Element elt175_=el(_doc,"thead");
Element elt176_=el(_doc,"tr");
Element elt177_=el(_doc,"th");
Element elt178_=el(_doc,"c:message");
CustList<Attr> attrs96_=al(1);
attrs96_.add(at("value","msg_effglobal,damage_type"));
at(elt178_,attrs96_);
ad(elt177_,elt178_);
ad(elt176_,elt177_);
Element elt179_=el(_doc,"th");
Element elt180_=el(_doc,"c:message");
CustList<Attr> attrs97_=al(1);
attrs97_.add(at("value","msg_effglobal,rate"));
at(elt180_,attrs97_);
ad(elt179_,elt180_);
ad(elt176_,elt179_);
ad(elt175_,elt176_);
ad(elt174_,elt175_);
Element elt181_=el(_doc,"tbody");
Element elt182_=el(_doc,"c:for");
CustList<Attr> attrs98_=al(5);
attrs98_.add(at("key","c"));
attrs98_.add(at("map","multDamagePrepaRound"));
attrs98_.add(at("value","r"));
attrs98_.add(at("keyClassName","java.lang.String"));
attrs98_.add(at("varClassName","r"));
at(elt182_,attrs98_);
Element elt183_=el(_doc,"tr");
Element elt184_=el(_doc,"td");
Text txt21_=tx(_doc,"{c}");
ad(elt184_,txt21_);
ad(elt183_,elt184_);
Element elt185_=el(_doc,"td");
Text txt22_=tx(_doc,"{r}");
ad(elt185_,txt22_);
ad(elt183_,elt185_);
ad(elt182_,elt183_);
ad(elt181_,elt182_);
ad(elt174_,elt181_);
ad(elt167_,elt174_);
Element elt186_=el(_doc,"br");
ad(elt167_,elt186_);
ad(elt0_,elt167_);
ad(_body,elt0_);
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
