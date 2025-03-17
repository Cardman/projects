package cards.gui.containers;

import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import code.gui.AbsPanel;

public interface ContainerPlayablePresident extends ContainerPlayableGame,ContainerSingle<CardPresident> {
    void discard();
    void noPlay();

//    void setCarteSurvoleePresident(CardPresident _c);
//
//    CardPresident getCarteSurvoleePresident();

    void discard(int _i);

    void cancelDiscard(int _i);

    HandPresident getReceivedCards();

    AbsPanel getPanelHand();

    AbsPanel getPanelGivenCards();

    HandPresident getVirtualHand();

    HandPresident getGivenCards();
}
