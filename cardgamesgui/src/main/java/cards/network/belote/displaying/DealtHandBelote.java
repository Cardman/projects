package cards.network.belote.displaying;
import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.DealingBelote;
import code.util.EqList;
import code.util.annot.RwXml;

@RwXml
public final class DealtHandBelote {

    private HandBelote cards;

    private HandBelote deck;

    private EqList<BidBeloteSuit> allowedBids;

    private byte dealer;

    private DealingBelote rep;

    private int points;

    public HandBelote getCards() {
        return cards;
    }

    public void setCards(HandBelote _cards) {
        cards = _cards;
    }

    public HandBelote getDeck() {
        return deck;
    }

    public void setDeck(HandBelote _deck) {
        deck = _deck;
    }

    public EqList<BidBeloteSuit> getAllowedBids() {
        return allowedBids;
    }

    public void setAllowedBids(EqList<BidBeloteSuit> _allowedBids) {
        allowedBids = _allowedBids;
    }

    public byte getDealer() {
        return dealer;
    }

    public void setDealer(byte _dealer) {
        dealer = _dealer;
    }

    public DealingBelote getRep() {
        return rep;
    }

    public void setRep(DealingBelote _rep) {
        rep = _rep;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int _points) {
        points = _points;
    }
}
