package cards.network.tarot.unlock;
import cards.tarot.HandTarot;


public final class CallableCards {

    private HandTarot cards;

    private boolean discarding;

    private int takerIndex;

    public HandTarot getCards() {
        return cards;
    }

    public void setCards(HandTarot _callableCards) {
        cards = _callableCards;
    }

    public boolean isDiscarding() {
        return discarding;
    }

    public void setDiscarding(boolean _discarding) {
        discarding = _discarding;
    }

    public int getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(int _takerIndex) {
        takerIndex = _takerIndex;
    }
}
