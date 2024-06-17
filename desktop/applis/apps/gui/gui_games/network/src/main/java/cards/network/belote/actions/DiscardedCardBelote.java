package cards.network.belote.actions;
import cards.belote.enumerations.CardBelote;
import cards.network.common.PlayerActionGame;


public final class DiscardedCardBelote extends PlayerActionGame {

    private CardBelote card = CardBelote.WHITE;

    public CardBelote getCard() {
        return card;
    }

    public void setCard(CardBelote _card) {
        card = _card;
    }

}
