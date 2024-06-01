package cards.gui.containers;

import cards.belote.enumerations.CardBelote;

public interface ContainerPlayableBelote extends ContainerPlayableSlam,ContainerSingle<CardBelote>,ContainerSingleWithDiscard<CardBelote> {
    void prendreCartesChien();
    void validateDiscard();
    void bid();
    void fold();

//    void setCarteSurvoleeBelote(CardBelote _c);
//
//    CardBelote getCarteSurvoleeBelote();
}
