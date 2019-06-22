package cards.network.president.actions;
import cards.network.common.PlayerActionGame;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.util.*;


public final class PlayingCardPresident extends PlayerActionGame {

    private CardPresident playedCard;

    private byte index;

    private boolean pass;

    private HandPresident playedHand;

    private ByteMap<Playing> status;

    private byte nextPlayer;

    public CardPresident getPlayedCard() {
        return playedCard;
    }

    public void setPlayedCard(CardPresident _playedCard) {
        playedCard = _playedCard;
    }

    public byte getIndex() {
        return index;
    }

    public void setIndex(byte _index) {
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

    public ByteMap<Playing> getStatus() {
        return status;
    }

    public void setStatus(ByteMap<Playing> _status) {
        status = _status;
    }

    public byte getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(byte _nextPlayer) {
        nextPlayer = _nextPlayer;
    }
}
