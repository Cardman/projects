package cards.gui.containers;

import cards.belote.enumerations.CardBelote;

public interface ContainerPlayableBelote extends ContainerPlayableGame,ContainerSingle<CardBelote> {
    void bid();
    void fold();

//    void setCarteSurvoleeBelote(CardBelote _c);
//
//    CardBelote getCarteSurvoleeBelote();
}
