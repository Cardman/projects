package cards.network.president.actions;
import cards.network.common.PlayerActionGame;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.util.*;


public final class PlayingCardPresident extends PlayerActionGame {

    private CardPresident playedCard;

    private int index;

    private boolean pass;

    private HandPresident playedHand;

    private IntMap<Playing> status;

    private int nextPlayer;

    private boolean refreshing;

    private boolean reversed;
    public CardPresident getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(CardPresident _playedCard) {
        playedCard = _playedCard;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean _pass) {
        pass = _pass;
    }

    public HandPresident getPlayedHand() {
        return playedHand;
    }

    public void setPlayedHand(HandPresident _playedHand) {
        playedHand = _playedHand;
    }

    public IntMap<Playing> getStatus() {
        return status;
    }

    public void setStatus(IntMap<Playing> _status) {
        status = _status;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(int _nextPlayer) {
        nextPlayer = _nextPlayer;
    }

    public boolean isRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean _r) {
        this.refreshing = _r;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean _reversed) {
        reversed = _reversed;
    }
}
