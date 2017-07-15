package cards.network.president.unlock;
import code.util.annot.RwXml;
import cards.president.HandPresident;

@RwXml
public class AllowDiscarding {

    private HandPresident receivedCards;

    public HandPresident getReceivedCards() {
        return receivedCards;
    }

    public void setReceivedCards(HandPresident _receivedCards) {
        receivedCards = _receivedCards;
    }
}
