package cards.network.tarot.actions;
import cards.network.common.PlayerActionGame;
import cards.tarot.HandTarot;


public final class CalledCards extends PlayerActionGame {

    private HandTarot calledCards;

    private boolean discarding;

    public HandTarot getCalledCards() {
        return calledCards;
    }

    public void setCalledCards(HandTarot _calledCards) {
        calledCards = _calledCards;
    }

    public boolean isDiscarding() {
        return discarding;
    }

    public void setDiscarding(boolean _discarding) {
        discarding = _discarding;
    }

}
