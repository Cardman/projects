package cards.belote;

import cards.belote.enumerations.*;
import org.junit.Test;

public final class BeloteCardsRetrieverUtilTest extends EquallableBeloteUtil {
    @Test
    public void toDeclaresBelote1() {
        assertEq(DeclaresBelote.FOUR_1, BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_1"));
    }
    @Test
    public void toDeclaresBelote2() {
        assertEq(DeclaresBelote.FOUR_KING,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_KING"));
    }
    @Test
    public void toDeclaresBelote3() {
        assertEq(DeclaresBelote.FOUR_QUEEN,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_QUEEN"));
    }
    @Test
    public void toDeclaresBelote4() {
        assertEq(DeclaresBelote.FOUR_JACK,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_JACK"));
    }
    @Test
    public void toDeclaresBelote5() {
        assertEq(DeclaresBelote.FOUR_10,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_10"));
    }
    @Test
    public void toDeclaresBelote6() {
        assertEq(DeclaresBelote.FOUR_9,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_9"));
    }
    @Test
    public void toDeclaresBelote7() {
        assertEq(DeclaresBelote.FOUR_8,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_8"));
    }
    @Test
    public void toDeclaresBelote8() {
        assertEq(DeclaresBelote.FOUR_7,BeloteCardsRetrieverUtil.toDeclaresBelote("FOUR_7"));
    }
    @Test
    public void toDeclaresBelote9() {
        assertEq(DeclaresBelote.HUNDRED,BeloteCardsRetrieverUtil.toDeclaresBelote("HUNDRED"));
    }
    @Test
    public void toDeclaresBelote10() {
        assertEq(DeclaresBelote.FIFTY,BeloteCardsRetrieverUtil.toDeclaresBelote("FIFTY"));
    }
    @Test
    public void toDeclaresBelote11() {
        assertEq(DeclaresBelote.THIRTY,BeloteCardsRetrieverUtil.toDeclaresBelote("THIRTY"));
    }
    @Test
    public void toDeclaresBelote12() {
        assertEq(DeclaresBelote.UNDEFINED,BeloteCardsRetrieverUtil.toDeclaresBelote("UNDEFINED"));
    }
    @Test
    public void toBeloteTrumpPartner1() {
        assertEq(BeloteTrumpPartner.OVERTRUMP_ONLY,BeloteCardsRetrieverUtil.toBeloteTrumpPartner("OVERTRUMP_ONLY"));
    }
    @Test
    public void toBeloteTrumpPartner2() {
        assertEq(BeloteTrumpPartner.UNDERTRUMP_ONLY,BeloteCardsRetrieverUtil.toBeloteTrumpPartner("UNDERTRUMP_ONLY"));
    }
    @Test
    public void toBeloteTrumpPartner3() {
        assertEq(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP,BeloteCardsRetrieverUtil.toBeloteTrumpPartner("UNDERTRUMP_OVERTRUMP"));
    }
    @Test
    public void toBeloteTrumpPartner4() {
        assertEq(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP,BeloteCardsRetrieverUtil.toBeloteTrumpPartner("NO_UNDERTRUMP_NO_OVERTRUMP"));
    }
    @Test
    public void toBidBelote1() {
        assertEq(BidBelote.SUIT,BeloteCardsRetrieverUtil.toBidBelote("SUIT"));
    }
    @Test
    public void toBidBelote2() {
        assertEq(BidBelote.OTHER_SUIT,BeloteCardsRetrieverUtil.toBidBelote("OTHER_SUIT"));
    }
    @Test
    public void toBidBelote3() {
        assertEq(BidBelote.NO_TRUMP,BeloteCardsRetrieverUtil.toBidBelote("NO_TRUMP"));
    }
    @Test
    public void toBidBelote4() {
        assertEq(BidBelote.ALL_TRUMP,BeloteCardsRetrieverUtil.toBidBelote("ALL_TRUMP"));
    }
    @Test
    public void toBidBelote5() {
        assertEq(BidBelote.FOLD,BeloteCardsRetrieverUtil.toBidBelote("FOLD"));
    }
    @Test
    public void toDealingBelote1() {
        assertEq(DealingBelote.COINCHE_2_VS_2,BeloteCardsRetrieverUtil.toDealingBelote("COINCHE_2_VS_2"));
    }
    @Test
    public void toDealingBelote2() {
        assertEq(DealingBelote.CLASSIC_2_VS_2,BeloteCardsRetrieverUtil.toDealingBelote("CLASSIC_2_VS_2"));
    }
}
