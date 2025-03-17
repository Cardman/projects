package cards.gui.animations;

import cards.president.*;
import code.util.*;

public final class DisplaySwitchedCardsSimuPresident implements Runnable {
    private final SimulatingPresidentImpl simulatingPresident;
    private final Ints winners;
    private final Ints loosers;
    private final CustList<HandPresident> switchedCards;
    public DisplaySwitchedCardsSimuPresident(SimulatingPresidentImpl _simulatingPresident,
                                             Ints _winners, Ints _loosers, CustList<HandPresident> _switchedCards) {
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
