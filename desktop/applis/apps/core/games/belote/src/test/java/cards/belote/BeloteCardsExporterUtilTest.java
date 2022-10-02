package cards.belote;

import cards.belote.enumerations.*;
import org.junit.Test;

public final class BeloteCardsExporterUtilTest extends EquallableBeloteUtil {
    @Test
    public void fromDeclaresBelote1() {
        assertEq("FOUR_1", BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_1));
    }
    @Test
    public void fromDeclaresBelote2() {
        assertEq("FOUR_KING",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_KING));
    }
    @Test
    public void fromDeclaresBelote3() {
        assertEq("FOUR_QUEEN",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_QUEEN));
    }
    @Test
    public void fromDeclaresBelote4() {
        assertEq("FOUR_JACK",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_JACK));
    }
    @Test
    public void fromDeclaresBelote5() {
        assertEq("FOUR_10",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_10));
    }
    @Test
    public void fromDeclaresBelote6() {
        assertEq("FOUR_9",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_9));
    }
    @Test
    public void fromDeclaresBelote7() {
        assertEq("FOUR_8",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_8));
    }
    @Test
    public void fromDeclaresBelote8() {
        assertEq("FOUR_7",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FOUR_7));
    }
    @Test
    public void fromDeclaresBelote9() {
        assertEq("HUNDRED",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.HUNDRED));
    }
    @Test
    public void fromDeclaresBelote10() {
        assertEq("FIFTY",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.FIFTY));
    }
    @Test
    public void fromDeclaresBelote11() {
        assertEq("THIRTY",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.THIRTY));
    }
    @Test
    public void fromDeclaresBelote12() {
        assertEq("UNDEFINED",BeloteCardsExporterUtil.fromDeclaresBelote(DeclaresBelote.UNDEFINED));
    }
    @Test
    public void fromBeloteTrumpPartner1() {
        assertEq("OVERTRUMP_ONLY",BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.OVERTRUMP_ONLY));
    }
    @Test
    public void fromBeloteTrumpPartner2() {
        assertEq("UNDERTRUMP_ONLY",BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_ONLY));
    }
    @Test
    public void fromBeloteTrumpPartner3() {
        assertEq("UNDERTRUMP_OVERTRUMP",BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP));
    }
    @Test
    public void fromBeloteTrumpPartner4() {
        assertEq("NO_UNDERTRUMP_NO_OVERTRUMP",BeloteCardsExporterUtil.fromBeloteTrumpPartner(BeloteTrumpPartner.NO_UNDERTRUMP_NO_OVERTRUMP));
    }
    @Test
    public void fromBidBelote1() {
        assertEq("SUIT",BeloteCardsExporterUtil.fromBidBelote(BidBelote.SUIT));
    }
    @Test
    public void fromBidBelote2() {
        assertEq("OTHER_SUIT",BeloteCardsExporterUtil.fromBidBelote(BidBelote.OTHER_SUIT));
    }
    @Test
    public void fromBidBelote3() {
        assertEq("NO_TRUMP",BeloteCardsExporterUtil.fromBidBelote(BidBelote.NO_TRUMP));
    }
    @Test
    public void fromBidBelote4() {
        assertEq("ALL_TRUMP",BeloteCardsExporterUtil.fromBidBelote(BidBelote.ALL_TRUMP));
    }
    @Test
    public void fromBidBelote5() {
        assertEq("FOLD",BeloteCardsExporterUtil.fromBidBelote(BidBelote.FOLD));
    }
    @Test
    public void fromDealingBelote1() {
        assertEq("COINCHE_2_VS_2",BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.COINCHE_2_VS_2));
    }
    @Test
    public void fromDealingBelote2() {
        assertEq("CLASSIC_2_VS_2",BeloteCardsExporterUtil.fromDealingBelote(DealingBelote.CLASSIC_2_VS_2));
    }

}
