package cards.network.tarot.displaying.errors;
import cards.tarot.enumerations.CardTarot;


public final class ErrorPlaying {

    private CardTarot card;

    private String reason;

    public CardTarot getCard() {
        return card;
    }

    public void setCard(CardTarot _card) {
        card = _card;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String _reason) {
        reason = _reason;
    }
}
