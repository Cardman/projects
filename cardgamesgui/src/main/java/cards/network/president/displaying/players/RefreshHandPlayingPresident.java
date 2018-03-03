package cards.network.president.displaying.players;
import code.util.NumberMap;
import code.util.annot.RwXml;
import cards.network.common.PlayerActionGame;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;

@RwXml
public final class RefreshHandPlayingPresident extends PlayerActionGame {

    private CardPresident playedCard;

    private byte index;

    private boolean pass;

    private HandPresident playedHand;

    private NumberMap<Byte,Playing> status;

    private byte nextPlayer;

    private boolean reversed;

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

    public NumberMap<Byte,Playing> getStatus() {
        return status;
    }

    public void setStatus(NumberMap<Byte,Playing> _status) {
        status = _status;
    }

    public byte getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(byte _nextPlayer) {
        nextPlayer = _nextPlayer;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean _reversed) {
        reversed = _reversed;
    }
}
