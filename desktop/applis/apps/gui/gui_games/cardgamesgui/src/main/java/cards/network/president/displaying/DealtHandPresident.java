package cards.network.president.displaying;
import cards.president.HandPresident;
import cards.president.enumerations.Playing;
import code.util.*;


public final class DealtHandPresident {

    private HandPresident cards;

    private int maxCards;

    private ByteMap<Playing> status;

    private byte dealer;

    public HandPresident getCards() {
        return cards;
    }

    public void setCards(HandPresident _cards) {
        cards = _cards;
    }

    public int getMaxCards() {
        return maxCards;
    }

    public void setMaxCards(int _maxCards) {
        maxCards = _maxCards;
    }

    public ByteMap<Playing> getStatus() {
        return status;
    }

    public void setStatus(ByteMap<Playing> _status) {
        status = _status;
    }

    public byte getDealer() {
        return dealer;
    }

    public void setDealer(byte _dealer) {
        dealer = _dealer;
    }

}
