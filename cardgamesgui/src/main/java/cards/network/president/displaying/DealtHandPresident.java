package cards.network.president.displaying;
import code.util.NumberMap;
import code.util.annot.RwXml;
import cards.president.HandPresident;
import cards.president.enumerations.Playing;

@RwXml
public final class DealtHandPresident {

    private HandPresident cards;

    private int maxCards;

    private NumberMap<Byte,Playing> status;

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

    public NumberMap<Byte,Playing> getStatus() {
        return status;
    }

    public void setStatus(NumberMap<Byte,Playing> _status) {
        status = _status;
    }

    public byte getDealer() {
        return dealer;
    }

    public void setDealer(byte _dealer) {
        dealer = _dealer;
    }

}
