package cards.gui.animations;

import cards.president.HandPresident;
import code.util.ByteMap;
import code.util.Bytes;
import code.util.CustList;

public final class DisplaySwitchedCardsSimuPresident implements Runnable {
    private SimulatingPresidentImpl simulatingPresident;
    private Bytes winners;
    private Bytes loosers;
    private CustList<HandPresident> switchedCards;
    public DisplaySwitchedCardsSimuPresident(SimulatingPresidentImpl _simulatingPresident,
                                             Bytes _winners, Bytes _loosers, CustList<HandPresident> _switchedCards) {
        simulatingPresident = _simulatingPresident;
        winners = _winners;
        loosers = _loosers;
        switchedCards = _switchedCards;
    }

    @Override
    public void run() {
        simulatingPresident.displayGuiSwitchedUserHand(winners,loosers,switchedCards);
    }
}
