package cards.tarot;

import cards.tarot.enumerations.*;
import org.junit.Test;

public final class TarotCardsExporterUtilTest extends EquallableTarotUtil {
    @Test
    public void fromBidTarot1() {
        assertEq("TAKE",TarotCardsExporterUtil.fromBidTarot(BidTarot.TAKE));
    }
    @Test
    public void fromBidTarot2() {
        assertEq("GUARD",TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD));
    }
    @Test
    public void fromBidTarot3() {
        assertEq("GUARD_WITHOUT",TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_WITHOUT));
    }
    @Test
    public void fromBidTarot4() {
        assertEq("GUARD_AGAINST",TarotCardsExporterUtil.fromBidTarot(BidTarot.GUARD_AGAINST));
    }
    @Test
    public void fromBidTarot5() {
        assertEq("SLAM",TarotCardsExporterUtil.fromBidTarot(BidTarot.SLAM));
    }
    @Test
    public void fromBidTarot6() {
        assertEq("FOLD",TarotCardsExporterUtil.fromBidTarot(BidTarot.FOLD));
    }
    @Test
    public void fromModeTarot1() {
        assertEq("NORMAL",TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL));
    }
    @Test
    public void fromModeTarot2() {
        assertEq("NORMAL_WITH_MISERE",TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_MISERE));
    }
    @Test
    public void fromModeTarot3() {
        assertEq("NORMAL_WITH_ONE_FOR_ONE",TarotCardsExporterUtil.fromModeTarot(ModeTarot.NORMAL_WITH_ONE_FOR_ONE));
    }
    @Test
    public void fromModeTarot4() {
        assertEq("MISERE",TarotCardsExporterUtil.fromModeTarot(ModeTarot.MISERE));
    }
    @Test
    public void fromModeTarot5() {
        assertEq("ONE_FOR_ONE",TarotCardsExporterUtil.fromModeTarot(ModeTarot.ONE_FOR_ONE));
    }
    @Test
    public void fromDealingTarot1() {
        assertEq("DEAL_1_VS_2",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_2));
    }
    @Test
    public void fromDealingTarot2() {
        assertEq("DEAL_1_VS_3",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_3));
    }
    @Test
    public void fromDealingTarot3() {
        assertEq("DEAL_2_VS_2_WITHOUT_CALL",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL));
    }
    @Test
    public void fromDealingTarot4() {
        assertEq("DEAL_2_VS_2_CALL_KING",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_KING));
    }
    @Test
    public void fromDealingTarot5() {
        assertEq("DEAL_2_VS_2_CALL_CHAR",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR));
    }
    @Test
    public void fromDealingTarot6() {
        assertEq("DEAL_1_VS_4",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_1_VS_4));
    }
    @Test
    public void fromDealingTarot7() {
        assertEq("DEAL_2_VS_3_CALL_KING",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_KING));
    }
    @Test
    public void fromDealingTarot8() {
        assertEq("DEAL_2_VS_3_CALL_CHAR",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_3_CALL_CHAR));
    }
    @Test
    public void fromDealingTarot9() {
        assertEq("DEAL_2_VS_4_WITHOUT_CALL",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL));
    }
    @Test
    public void fromDealingTarot10() {
        assertEq("DEAL_2_VS_4_CALL_KING",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_KING));
    }
    @Test
    public void fromDealingTarot11() {
        assertEq("DEAL_2_VS_4_CALL_CHAR",TarotCardsExporterUtil.fromDealingTarot(DealingTarot.DEAL_2_VS_4_CALL_CHAR));
    }
    @Test
    public void fromHandfuls1() {
        assertEq("NO",TarotCardsExporterUtil.fromHandfuls(Handfuls.NO));
    }
    @Test
    public void fromHandfuls2() {
        assertEq("ONE",TarotCardsExporterUtil.fromHandfuls(Handfuls.ONE));
    }
    @Test
    public void fromHandfuls3() {
        assertEq("TWO",TarotCardsExporterUtil.fromHandfuls(Handfuls.TWO));
    }
    @Test
    public void fromHandfuls4() {
        assertEq("THREE",TarotCardsExporterUtil.fromHandfuls(Handfuls.THREE));
    }
    @Test
    public void fromHandfuls5() {
        assertEq("FOUR",TarotCardsExporterUtil.fromHandfuls(Handfuls.FOUR));
    }
    @Test
    public void fromMiseres1() {
        assertEq("TRUMP",TarotCardsExporterUtil.fromMiseres(Miseres.TRUMP));
    }
    @Test
    public void fromMiseres2() {
        assertEq("POINT",TarotCardsExporterUtil.fromMiseres(Miseres.POINT));
    }
    @Test
    public void fromMiseres3() {
        assertEq("CHARACTER",TarotCardsExporterUtil.fromMiseres(Miseres.CHARACTER));
    }
    @Test
    public void fromMiseres4() {
        assertEq("SUIT",TarotCardsExporterUtil.fromMiseres(Miseres.SUIT));
    }
    @Test
    public void fromMiseres5() {
        assertEq("LOW_CARDS",TarotCardsExporterUtil.fromMiseres(Miseres.LOW_CARDS));
    }
    @Test
    public void fromEndDealTarot1() {
        assertEq("ATTACK_LOOSE",TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_LOOSE));
    }
    @Test
    public void fromEndDealTarot2() {
        assertEq("ATTACK_WIN",TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ATTACK_WIN));
    }
    @Test
    public void fromEndDealTarot3() {
        assertEq("ZERO",TarotCardsExporterUtil.fromEndDealTarot(EndDealTarot.ZERO));
    }
    @Test
    public void fromCardTarot4() {
        assertEq("WHITE",TarotCardsExporterUtil.fromCardTarot(CardTarot.WHITE));
    }
    @Test
    public void key1() {
        assertEq("cards.tarot.enumerations.BidTarot.FOLD",TarotResoucesAccess.key(BidTarot.FOLD));
    }
    @Test
    public void key2() {
        assertEq("cards.tarot.enumerations.ModeTarot.NORMAL",TarotResoucesAccess.key(ModeTarot.NORMAL));
    }
    @Test
    public void key3() {
        assertEq("cards.tarot.enumerations.DealingTarot.DEAL_1_VS_2",TarotResoucesAccess.key(DealingTarot.DEAL_1_VS_2));
    }
    @Test
    public void key4() {
        assertEq("cards.tarot.enumerations.Handfuls.FOUR",TarotResoucesAccess.key(Handfuls.FOUR));
    }
    @Test
    public void key5() {
        assertEq("cards.tarot.enumerations.Miseres.LOW_CARDS",TarotResoucesAccess.key(Miseres.LOW_CARDS));
    }
    @Test
    public void key6() {
        assertEq("cards.tarot.enumerations.EndDealTarot.ATTACK_WIN",TarotResoucesAccess.key(EndDealTarot.ATTACK_WIN));
    }
    @Test
    public void key7() {
        assertEq("cards.tarot.enumerations.CardTarot.EXCUSE",TarotResoucesAccess.key(CardTarot.EXCUSE));
    }
}
