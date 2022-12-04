package cards.network.tarot;
import cards.tarot.HandTarot;


public final class Dog {

    private HandTarot dog;

    private boolean taker;

    private boolean humanTaker;

    private byte takerIndex;

    private boolean callAfter;

    public HandTarot getDog() {
        return dog;
    }

    public void setDog(HandTarot _dog) {
        dog = _dog;
    }

    public boolean isTaker() {
        return taker;
    }

    public void setTaker(boolean _taker) {
        taker = _taker;
    }

    public boolean isHumanTaker() {
        return humanTaker;
    }

    public void setHumanTaker(boolean _humanTaker) {
        humanTaker = _humanTaker;
    }

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }

    public boolean isCallAfter() {
        return callAfter;
    }

    public void setCallAfter(boolean _callAfter) {
        callAfter = _callAfter;
    }
}
