package cards.tarot;

import org.junit.Test;

import cards.tarot.enumerations.BidTarot;
import code.util.EnumList;


public class BidTarotTest extends EquallableTarotUtil {

    @Test
    public void getValidBids_allValidBids1Test() {
        EnumList<BidTarot> validBids_ = BidTarot.getValidBids();
        assertEq(6, validBids_.size());
        assertTrue(!validBids_.isEmpty());
        boolean existSlam_ = existSlam(validBids_);
        assertTrue(existSlam_);
    }

    private boolean existSlam(EnumList<BidTarot> _validBids) {
        boolean existSlam_ = false;
        for (BidTarot b: _validBids) {
            if (b.isFaireTousPlis()) {
                existSlam_ = true;
            }
        }
        return existSlam_;
    }

    @Test
    public void getAlwaysUsableBids_alwaysUsableBids1Test() {
        EnumList<BidTarot> alwaysUsableBids_ = BidTarot.getAlwaysUsableBids();
        assertTrue(!alwaysUsableBids_.isEmpty());
    }

    @Test
    public void getAlwaysUsableBids_alwaysUsableZeroBids1Test() {
        EnumList<BidTarot> alwaysUsableBids_ = BidTarot.getAlwaysUsableBids();
        EnumList<BidTarot> zeroBids_ = BidTarot.getZeroBids();
        assertTrue(!zeroBids_.isEmpty());
        assertTrue(alwaysUsableBids_.containsAllObj(zeroBids_));
    }
    @Test
    public void getNonZeroBids_alwaysUsableNonZeroBids1Test() {
        EnumList<BidTarot> alwaysUsableBids_ = BidTarot.getAlwaysUsableBids();
        EnumList<BidTarot> nonZeroBids_ = BidTarot.getNonZeroBids();
        nonZeroBids_.retainAllElements(alwaysUsableBids_);
        assertTrue(!nonZeroBids_.isEmpty());
    }
}
