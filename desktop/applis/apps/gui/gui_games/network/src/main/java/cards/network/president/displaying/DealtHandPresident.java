package cards.network.president.displaying;
import cards.president.HandPresident;
import cards.president.enumerations.Playing;
import code.util.*;


public final class DealtHandPresident {

    private HandPresident cards;

    private int maxCards;

    private IntMap<Playing> status;

    private int dealer;

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

    public IntMap<Playing> getStatus() {
        return status;
    }

    public void setStatus(IntMap<Playing> _status) {
        status = _status;
    }

    public int getDealer() {
        return dealer;
    }

    public void setDealer(int _dealer) {
        dealer = _dealer;
    }

}
