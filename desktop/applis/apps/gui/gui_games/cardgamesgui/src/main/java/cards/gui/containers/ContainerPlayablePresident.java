package cards.gui.containers;

import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import code.gui.AbsPanel;

public interface ContainerPlayablePresident extends ContainerPlayableGame {
    void discard();
    void noPlay();

    void setCarteSurvoleePresident(CardPresident _c);

    void setIndexCard(byte _i);

    CardPresident getCarteSurvoleePresident();

    byte getIndexCard();

    void discard(byte _i);

    void cancelDiscard(byte _i);

    HandPresident getReceivedCards();

    AbsPanel getPanelHand();

    AbsPanel getPanelGivenCards();

    HandPresident getVirtualHand();

    HandPresident getGivenCards();
}
