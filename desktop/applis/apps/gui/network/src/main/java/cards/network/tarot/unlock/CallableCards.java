package cards.network.tarot.unlock;
import cards.tarot.HandTarot;


public final class CallableCards {

    private HandTarot callableCards;

    private boolean discarding;

    private byte takerIndex;

    public HandTarot getCallableCards() {
        return callableCards;
    }

    public void setCallableCards(HandTarot _callableCards) {
        callableCards = _callableCards;
    }

    public boolean isDiscarding() {
        return discarding;
    }

    public void setDiscarding(boolean _discarding) {
        discarding = _discarding;
    }

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }
}
