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
}
