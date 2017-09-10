package cards.belote;
import static cards.belote.EquallableBeloteUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import code.util.EnumList;

@SuppressWarnings("static-method")
public class BidBeloteTest {

    @Test
    public void getValidBids_allValidBids1Test() {
        EnumList<BidBelote> validBids_ = BidBelote.getValidBids();
        assertEq(BidBelote.values().length, validBids_.size());
        assertTrue(!validBids_.isEmpty());
    }

    @Test
    public void getAlwaysUsableBids_alwaysUsableBids1Test() {
        EnumList<BidBelote> alwaysUsableBids_ = BidBelote.getAlwaysUsableBids();
        assertTrue(!alwaysUsableBids_.isEmpty());
    }

    @Test
    public void getAlwaysUsableBids_alwaysUsableZeroBids2Test() {
        EnumList<BidBelote> alwaysUsableBids_ = BidBelote.getAlwaysUsableBids();
        EnumList<BidBelote> zeroBids_ = BidBelote.getZeroBids();
        assertTrue(!zeroBids_.isEmpty());
        assertTrue(alwaysUsableBids_.containsAllObj(zeroBids_));
    }
    @Test
    public void getNonZeroBids_alwaysUsableNonZeroBids1Test() {
        EnumList<BidBelote> alwaysUsableBids_ = BidBelote.getAlwaysUsableBids();
        EnumList<BidBelote> nonZeroBids_ = BidBelote.getNonZeroBids();
        nonZeroBids_.retainAllElements(alwaysUsableBids_);
        assertTrue(!nonZeroBids_.isEmpty());
    }
    @Test
    public void allOrderedBids1Test() {
        assertTrue(BidBelote.allOrderedBids());
    }
}
