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
    public void key1() {
        assertEq("cards.tarot.enumerations.BidTarot.FOLD",TarotResoucesAccess.key(BidTarot.FOLD));
    }
}
