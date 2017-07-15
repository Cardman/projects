package cards.network.tarot.unlock;
import code.util.annot.RwXml;
import cards.tarot.HandTarot;

@RwXml
public class CallableCards {

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
