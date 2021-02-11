package cards.tarot;

import cards.tarot.enumerations.DealingTarot;
import code.util.CustList;
import org.junit.Test;

public final class TarotAddonTest extends EquallableTarotUtil {
    @Test
    public void test() {
        DealTarot d_ = new DealTarot();
        d_.setDeal(new CustList<HandTarot>());
        assertEq(0,d_.getDeal().size());
        assertTrue(DealingTarot.DEAL_1_VS_2.getNbAppeles()>=0);
    }
}
