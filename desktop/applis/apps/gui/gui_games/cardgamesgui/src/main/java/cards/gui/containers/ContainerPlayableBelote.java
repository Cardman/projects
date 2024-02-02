package cards.gui.containers;

import cards.belote.enumerations.CardBelote;

public interface ContainerPlayableBelote extends ContainerPlayableGame {
    void bid();
    void fold();

    void setCarteSurvoleeBelote(CardBelote _c);

    CardBelote getCarteSurvoleeBelote();
}
