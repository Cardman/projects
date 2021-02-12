package cards.belote;

import code.util.CustList;
import org.junit.Test;

public final class BeloteAddonTest extends EquallableBeloteUtil {
    @Test
    public void test() {
        DealBelote deal_ = new DealBelote();
        deal_.setDeal(new CustList<HandBelote>());
        assertEq(0,deal_.getDeal().size());
    }
}
