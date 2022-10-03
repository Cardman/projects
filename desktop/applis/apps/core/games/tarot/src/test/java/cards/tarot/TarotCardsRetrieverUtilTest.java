package cards.tarot;

import cards.tarot.enumerations.*;
import org.junit.Test;

public final class TarotCardsRetrieverUtilTest extends EquallableTarotUtil {
    @Test
    public void toBidTarot1() {
        assertEq(BidTarot.TAKE,TarotCardsRetrieverUtil.toBidTarot("TAKE"));
    }
    @Test
    public void toBidTarot2() {
        assertEq(BidTarot.GUARD,TarotCardsRetrieverUtil.toBidTarot("GUARD"));
    }
    @Test
    public void toBidTarot3() {
        assertEq(BidTarot.GUARD_WITHOUT,TarotCardsRetrieverUtil.toBidTarot("GUARD_WITHOUT"));
    }
    @Test
    public void toBidTarot4() {
        assertEq(BidTarot.GUARD_AGAINST,TarotCardsRetrieverUtil.toBidTarot("GUARD_AGAINST"));
    }
    @Test
    public void toBidTarot5() {
        assertEq(BidTarot.SLAM,TarotCardsRetrieverUtil.toBidTarot("SLAM"));
    }
    @Test
    public void toBidTarot6() {
        assertEq(BidTarot.FOLD,TarotCardsRetrieverUtil.toBidTarot("FOLD"));
    }
    @Test
    public void toModeTarot1() {
        assertEq(ModeTarot.NORMAL_WITH_MISERE,TarotCardsRetrieverUtil.toModeTarot("NORMAL_WITH_MISERE"));
    }
    @Test
    public void toModeTarot2() {
        assertEq(ModeTarot.NORMAL_WITH_ONE_FOR_ONE,TarotCardsRetrieverUtil.toModeTarot("NORMAL_WITH_ONE_FOR_ONE"));
    }
    @Test
    public void toModeTarot3() {
        assertEq(ModeTarot.MISERE,TarotCardsRetrieverUtil.toModeTarot("MISERE"));
    }
    @Test
    public void toModeTarot4() {
        assertEq(ModeTarot.ONE_FOR_ONE,TarotCardsRetrieverUtil.toModeTarot("ONE_FOR_ONE"));
    }
    @Test
    public void toModeTarot5() {
        assertEq(ModeTarot.NORMAL,TarotCardsRetrieverUtil.toModeTarot("NORMAL"));
    }
    @Test
    public void toDealingTarot1() {
        assertEq(DealingTarot.DEAL_1_VS_2,TarotCardsRetrieverUtil.toDealingTarot("DEAL_1_VS_2"));
    }
    @Test
    public void toDealingTarot2() {
        assertEq(DealingTarot.DEAL_1_VS_3,TarotCardsRetrieverUtil.toDealingTarot("DEAL_1_VS_3"));
    }
    @Test
    public void toDealingTarot3() {
        assertEq(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_2_WITHOUT_CALL"));
    }
    @Test
    public void toDealingTarot4() {
        assertEq(DealingTarot.DEAL_2_VS_2_CALL_KING,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_2_CALL_KING"));
    }
    @Test
    public void toDealingTarot5() {
        assertEq(DealingTarot.DEAL_2_VS_2_CALL_CHAR,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_2_CALL_CHAR"));
    }
    @Test
    public void toDealingTarot6() {
        assertEq(DealingTarot.DEAL_1_VS_4,TarotCardsRetrieverUtil.toDealingTarot("DEAL_1_VS_4"));
    }
    @Test
    public void toDealingTarot7() {
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_KING,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_3_CALL_KING"));
    }
    @Test
    public void toDealingTarot8() {
        assertEq(DealingTarot.DEAL_2_VS_3_CALL_CHAR,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_3_CALL_CHAR"));
    }
    @Test
    public void toDealingTarot9() {
        assertEq(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_4_WITHOUT_CALL"));
    }
    @Test
    public void toDealingTarot10() {
        assertEq(DealingTarot.DEAL_2_VS_4_CALL_KING,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_4_CALL_KING"));
    }
    @Test
    public void toDealingTarot11() {
        assertEq(DealingTarot.DEAL_2_VS_4_CALL_CHAR,TarotCardsRetrieverUtil.toDealingTarot("DEAL_2_VS_4_CALL_CHAR"));
    }
}
