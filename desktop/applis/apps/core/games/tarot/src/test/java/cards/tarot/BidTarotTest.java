package cards.tarot;

import org.junit.Test;

import cards.tarot.enumerations.BidTarot;
import code.util.IdList;


public class BidTarotTest extends EquallableTarotUtil {

    @Test
    public void getValidBids_allValidBids1Test() {
        IdList<BidTarot> validBids_ = BidTarot.getValidBids();
        assertEq(6, validBids_.size());
    }

    @Test
    public void getAlwaysUsableBids_alwaysUsableBids1Test() {
        IdList<BidTarot> alwaysUsableBids_ = BidTarot.getAlwaysUsableBids();
        assertTrue(!alwaysUsableBids_.isEmpty());
    }

    @Test
    public void getAlwaysUsableBids_alwaysUsableZeroBids1Test() {
        IdList<BidTarot> alwaysUsableBids_ = BidTarot.getAlwaysUsableBids();
        IdList<BidTarot> zeroBids_ = BidTarot.getZeroBids();
        assertTrue(!zeroBids_.isEmpty());
        assertTrue(alwaysUsableBids_.containsAllObj(zeroBids_));
    }
    @Test
    public void getNonZeroBids_alwaysUsableNonZeroBids1Test() {
        IdList<BidTarot> alwaysUsableBids_ = BidTarot.getAlwaysUsableBids();
        IdList<BidTarot> nonZeroBids_ = BidTarot.getNonZeroBids();
        nonZeroBids_.retainAllElements(alwaysUsableBids_);
        assertTrue(!nonZeroBids_.isEmpty());
    }
}
