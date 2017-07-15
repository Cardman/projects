package cards.belote;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.AbEqList;
import code.util.EnumList;
import cards.belote.enumerations.BidBelote;

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
        nonZeroBids_.retainAllElements((AbEqList<? super BidBelote>)alwaysUsableBids_);
        assertTrue(!nonZeroBids_.isEmpty());
    }
    @Test
    public void allOrderedBids1Test() {
        assertTrue(BidBelote.allOrderedBids());
    }
}
