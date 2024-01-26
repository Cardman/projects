package cards.belote;

import cards.belote.enumerations.*;
import org.junit.Test;

public final class BeloteCardsUtilTest extends EquallableBeloteUtil {
    @Test
    public void converDeclaresBelote0(){
        assertEq(DeclaresBelote.UNDEFINED,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.UNDEFINED)));
    }
    @Test
    public void converDeclaresBelote1(){
        assertEq(DeclaresBelote.FOUR_1,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_1)));
    }
    @Test
    public void converDeclaresBelote2(){
        assertEq(DeclaresBelote.FOUR_KING,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_KING)));
    }
    @Test
    public void converDeclaresBelote3(){
        assertEq(DeclaresBelote.FOUR_QUEEN,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_QUEEN)));
    }
    @Test
    public void converDeclaresBelote4(){
        assertEq(DeclaresBelote.FOUR_JACK,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_JACK)));
    }
    @Test
    public void converDeclaresBelote5(){
        assertEq(DeclaresBelote.FOUR_10,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_10)));
    }
    @Test
    public void converDeclaresBelote6(){
        assertEq(DeclaresBelote.FOUR_9,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_9)));
    }
    @Test
    public void converDeclaresBelote7(){
        assertEq(DeclaresBelote.FOUR_8,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_8)));
    }
    @Test
    public void converDeclaresBelote8(){
        assertEq(DeclaresBelote.FOUR_7,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_7)));
    }
    @Test
    public void converDeclaresBelote9(){
        assertEq(DeclaresBelote.HUNDRED,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.HUNDRED)));
    }
    @Test
    public void converDeclaresBelote10(){
        assertEq(DeclaresBelote.FIFTY,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FIFTY)));
    }
    @Test
    public void converDeclaresBelote11(){
        assertEq(DeclaresBelote.THIRTY,BeloteCardsRetrieverUtil.toDeclaresBelote(BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.THIRTY)));
    }
    @Test
    public void converBeloteTrumpPartner0(){
        assertEq(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP,BeloteCardsRetrieverUtil.toBeloteTrumpPartner(BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP)));
    }
    @Test
    public void converBeloteTrumpPartner1(){
        assertEq(BeloteTrumpPartner.OVERTRUMP_ONLY,BeloteCardsRetrieverUtil.toBeloteTrumpPartner(BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY)));
    }
    @Test
    public void converBeloteTrumpPartner2(){
        assertEq(BeloteTrumpPartner.UNDERTRUMP_ONLY,BeloteCardsRetrieverUtil.toBeloteTrumpPartner(BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY)));
    }
    @Test
    public void converBeloteTrumpPartner3(){
        assertEq(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP,BeloteCardsRetrieverUtil.toBeloteTrumpPartner(BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP)));
    }
    @Test
    public void converBeloteTrumpPartner4(){
        assertEq(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP,BeloteCardsRetrieverUtil.toBeloteTrumpPartner(""));
    }
    @Test
    public void converBidBelote0(){
        assertEq(BidBelote.FOLD,BeloteCardsRetrieverUtil.toBidBelote(BeloteCardsExporterUtil.fromBidBelote(BidBelote.FOLD)));
    }
    @Test
    public void converBidBelote1(){
        assertEq(BidBelote.SUIT,BeloteCardsRetrieverUtil.toBidBelote(BeloteCardsExporterUtil.fromBidBelote(BidBelote.SUIT)));
    }
    @Test
    public void converBidBelote2(){
        assertEq(BidBelote.OTHER_SUIT,BeloteCardsRetrieverUtil.toBidBelote(BeloteCardsExporterUtil.fromBidBelote(BidBelote.OTHER_SUIT)));
    }
    @Test
    public void converBidBelote3(){
        assertEq(BidBelote.NO_TRUMP,BeloteCardsRetrieverUtil.toBidBelote(BeloteCardsExporterUtil.fromBidBelote(BidBelote.NO_TRUMP)));
    }
    @Test
    public void converBidBelote4(){
        assertEq(BidBelote.ALL_TRUMP,BeloteCardsRetrieverUtil.toBidBelote(BeloteCardsExporterUtil.fromBidBelote(BidBelote.ALL_TRUMP)));
    }
    @Test
    public void converDealingBelote1() {
        assertEq(DealingBelote.COINCHE_2_VS_2,BeloteCardsRetrieverUtil.toDealingBelote(BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_2_VS_2)));
    }
    @Test
    public void converDealingBelote2() {
        assertEq(DealingBelote.CLASSIC_2_VS_2,BeloteCardsRetrieverUtil.toDealingBelote(BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_2_VS_2)));
    }
    @Test
    public void converDealingBelote3() {
        assertEq(DealingBelote.CLASSIC_2_VS_2,BeloteCardsRetrieverUtil.toDealingBelote("__"));
    }
    @Test
    public void i0(){
        assertEq(CardBelote.WHITE,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.WHITE)));
    }
    @Test
    public void i1(){
        assertEq(CardBelote.HEART_JACK,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_JACK)));
    }
    @Test
    public void i2(){
        assertEq(CardBelote.HEART_9,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_9)));
    }
    @Test
    public void i3(){
        assertEq(CardBelote.HEART_1,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_1)));
    }
    @Test
    public void i4(){
        assertEq(CardBelote.HEART_10,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_10)));
    }
    @Test
    public void i5(){
        assertEq(CardBelote.HEART_KING,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_KING)));
    }
    @Test
    public void i6(){
        assertEq(CardBelote.HEART_QUEEN,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_QUEEN)));
    }
    @Test
    public void i7(){
        assertEq(CardBelote.HEART_8,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_8)));
    }
    @Test
    public void i8(){
        assertEq(CardBelote.HEART_7,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.HEART_7)));
    }
    @Test
    public void i9(){
        assertEq(CardBelote.SPADE_JACK,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_JACK)));
    }
    @Test
    public void i10(){
        assertEq(CardBelote.SPADE_9,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_9)));
    }
    @Test
    public void i11(){
        assertEq(CardBelote.SPADE_1,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_1)));
    }
    @Test
    public void i12(){
        assertEq(CardBelote.SPADE_10,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_10)));
    }
    @Test
    public void i13(){
        assertEq(CardBelote.SPADE_KING,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_KING)));
    }
    @Test
    public void i14(){
        assertEq(CardBelote.SPADE_QUEEN,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_QUEEN)));
    }
    @Test
    public void i15(){
        assertEq(CardBelote.SPADE_8,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_8)));
    }
    @Test
    public void i16(){
        assertEq(CardBelote.SPADE_7,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.SPADE_7)));
    }
    @Test
    public void i17(){
        assertEq(CardBelote.DIAMOND_JACK,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_JACK)));
    }
    @Test
    public void i18(){
        assertEq(CardBelote.DIAMOND_9,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_9)));
    }
    @Test
    public void i19(){
        assertEq(CardBelote.DIAMOND_1,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_1)));
    }
    @Test
    public void i20(){
        assertEq(CardBelote.DIAMOND_10,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_10)));
    }
    @Test
    public void i21(){
        assertEq(CardBelote.DIAMOND_KING,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_KING)));
    }
    @Test
    public void i22(){
        assertEq(CardBelote.DIAMOND_QUEEN,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_QUEEN)));
    }
    @Test
    public void i23(){
        assertEq(CardBelote.DIAMOND_8,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_8)));
    }
    @Test
    public void i24(){
        assertEq(CardBelote.DIAMOND_7,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.DIAMOND_7)));
    }
    @Test
    public void i25(){
        assertEq(CardBelote.CLUB_JACK,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_JACK)));
    }
    @Test
    public void i26(){
        assertEq(CardBelote.CLUB_9,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_9)));
    }
    @Test
    public void i27(){
        assertEq(CardBelote.CLUB_1,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_1)));
    }
    @Test
    public void i28(){
        assertEq(CardBelote.CLUB_10,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_10)));
    }
    @Test
    public void i29(){
        assertEq(CardBelote.CLUB_KING,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_KING)));
    }
    @Test
    public void i30(){
        assertEq(CardBelote.CLUB_QUEEN,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_QUEEN)));
    }
    @Test
    public void i31(){
        assertEq(CardBelote.CLUB_8, BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_8)));
    }
    @Test
    public void i32(){
        assertEq(CardBelote.CLUB_7,BeloteCardsRetrieverUtil.toCardBelote(BeloteCardsExporterUtil.fromCardBelote(CardBelote.CLUB_7)));
    }}
