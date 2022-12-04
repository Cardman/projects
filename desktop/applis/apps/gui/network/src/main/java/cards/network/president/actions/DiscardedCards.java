package cards.network.president.actions;
import cards.network.common.PlayerActionGame;
import cards.president.HandPresident;


public final class DiscardedCards extends PlayerActionGame {

    private HandPresident discarded;

    public HandPresident getDiscarded() {
        return discarded;
    }

    public void setDiscarded(HandPresident _discarded) {
        discarded = _discarded;
    }
}
