package cards.network.tarot.displaying.errors;
import code.util.annot.RwXml;
import cards.tarot.enumerations.CardTarot;

@RwXml
public final class ErrorDiscarding {

    private CardTarot card;

    private String errorMessage;

    public CardTarot getCard() {
        return card;
    }

    public void setCard(CardTarot _card) {
        card = _card;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String _errorMessage) {
        errorMessage = _errorMessage;
    }
}
