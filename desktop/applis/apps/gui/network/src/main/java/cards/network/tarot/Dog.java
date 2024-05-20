package cards.network.tarot;
import cards.tarot.HandTarot;


public final class Dog {

    public static final int TAKER_NO = 0;
    public static final int TAKER_BOT = 1;
    public static final int TAKER_HUM_WRITE = 2;
    public static final int TAKER_HUM_READ = 3;
    private HandTarot dog;

    private int taker;

    private byte takerIndex;

    private boolean callAfter;

    public HandTarot getDog() {
        return dog;
    }

    public void setDog(HandTarot _dog) {
        dog = _dog;
    }

    public int getTaker() {
        return taker;
    }

    public void setTaker(int _taker) {
        taker = _taker;
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
