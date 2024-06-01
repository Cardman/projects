package cards.network.tarot;
import cards.network.common.DiscardPhase;
import cards.tarot.HandTarot;


public final class Dog {

    public static final int TAKER_NO = 0;
    public static final int TAKER_BOT = 1;
    public static final int TAKER_HUM_WRITE = 2;
    public static final int TAKER_HUM_READ = 3;
    private final DiscardPhase discardPhase = new DiscardPhase();
    private HandTarot discardCard;
    private HandTarot callableCards;
    private boolean callAfter;

    public DiscardPhase getDiscardPhase() {
        return discardPhase;
    }

    public HandTarot getDiscardCard() {
        return discardCard;
    }

    public void setDiscardCard(HandTarot _dog) {
        discardCard = _dog;
    }

    public HandTarot getCallableCards() {
        return callableCards;
    }

    public void setCallableCards(HandTarot _callableCards) {
        callableCards = _callableCards;
    }

    public boolean isCallAfter() {
        return callAfter;
    }

    public void setCallAfter(boolean _callAfter) {
        callAfter = _callAfter;
    }
}
