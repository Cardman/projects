package cards.network.tarot.displaying;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.IdList;


public final class DealtHandTarot {

    private HandTarot cards;

    private HandTarot dog;

    private IdList<BidTarot> allowedBids;

    private byte dealer;

    private DealingTarot rep;

    public HandTarot getCards() {
        return cards;
    }

    public void setCards(HandTarot _cards) {
        cards = _cards;
    }

    public HandTarot getDog() {
        return dog;
    }

    public void setDog(HandTarot _dog) {
        dog = _dog;
    }

    public IdList<BidTarot> getAllowedBids() {
        return allowedBids;
    }

    public void setAllowedBids(IdList<BidTarot> _allowedBids) {
        allowedBids = _allowedBids;
    }

    public byte getDealer() {
        return dealer;
    }

    public void setDealer(byte _dealer) {
        dealer = _dealer;
    }

    public DealingTarot getRep() {
        return rep;
    }

    public void setRep(DealingTarot _rep) {
        rep = _rep;
    }
}
