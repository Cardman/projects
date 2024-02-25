package cards.gui.containers;

import cards.president.HandPresident;
import code.gui.AbsPanel;

public interface ContainerPlayablePresident extends ContainerPlayableGame {
    void discard();
    void noPlay();

//    void setCarteSurvoleePresident(CardPresident _c);
//
//    CardPresident getCarteSurvoleePresident();

    void discard(byte _i);

    void cancelDiscard(byte _i);

    HandPresident getReceivedCards();

    AbsPanel getPanelHand();

    AbsPanel getPanelGivenCards();

    HandPresident getVirtualHand();

    HandPresident getGivenCards();
}
