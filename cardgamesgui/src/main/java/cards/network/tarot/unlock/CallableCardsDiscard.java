package cards.network.tarot.unlock;
import cards.tarot.HandTarot;


public final class CallableCardsDiscard {

    private HandTarot callableCards;

    private HandTarot discarded;

    private byte takerIndex;

    public HandTarot getCallableCards() {
        return callableCards;
    }

    public void setCallableCards(HandTarot _callableCards) {
        callableCards = _callableCards;
    }

    public HandTarot getDiscarded() {
        return discarded;
    }

    public void setDiscarded(HandTarot _discarded) {
        discarded = _discarded;
    }

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }

}
