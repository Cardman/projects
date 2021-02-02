package cards.belote;

import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import code.util.EnumList;


public class BidBeloteTest extends EquallableBeloteUtil {

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
}
