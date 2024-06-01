package cards.network.tarot.actions;
import cards.network.common.PlayerActionGame;
import cards.tarot.enumerations.CardTarot;


public final class DiscardedCardTarot extends PlayerActionGame {

    private CardTarot card = CardTarot.WHITE;
    public CardTarot getCard() {
        return card;
    }

    public void setCard(CardTarot _card) {
        card = _card;
    }

}
