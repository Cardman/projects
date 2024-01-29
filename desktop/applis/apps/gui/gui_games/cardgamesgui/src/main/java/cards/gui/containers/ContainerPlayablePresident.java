package cards.gui.containers;

import cards.president.enumerations.CardPresident;

public interface ContainerPlayablePresident extends ContainerPlayableGame {
    void discard();
    void noPlay();

    void setCarteSurvoleePresident(CardPresident _c);

    void setIndexCard(byte _i);

    CardPresident getCarteSurvoleePresident();

    byte getIndexCard();

    void discard(byte _i);

    boolean isCanDiscard();

    void cancelDiscard(byte _i);
}
