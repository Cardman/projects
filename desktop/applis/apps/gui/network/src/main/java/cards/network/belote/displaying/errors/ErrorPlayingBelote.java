package cards.network.belote.displaying.errors;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;


public final class ErrorPlayingBelote {

    private HandBelote cards;

    private CardBelote card;

    private String reason;

    public HandBelote getCards() {
        return cards;
    }

    public void setCards(HandBelote _cards) {
        cards = _cards;
    }

    public CardBelote getCard() {
        return card;
    }

    public void setCard(CardBelote _card) {
        card = _card;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String _reason) {
        reason = _reason;
    }
}
