package cards.network.tarot.actions;
import code.util.annot.RwXml;
import cards.network.common.PlayerActionGame;
import cards.tarot.enumerations.CardTarot;

@RwXml
public final class DiscardedCard extends PlayerActionGame {

    private CardTarot card;

    private boolean inHand;

    public CardTarot getCard() {
        return card;
    }

    public void setCard(CardTarot _card) {
        card = _card;
    }

    public boolean isInHand() {
        return inHand;
    }

    public void setInHand(boolean _inHand) {
        inHand = _inHand;
    }
}
