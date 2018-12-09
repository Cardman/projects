package cards.network.belote.unlock;
import cards.belote.BidBeloteSuit;
import code.util.EqList;


public final class AllowBiddingBelote {

    private EqList<BidBeloteSuit> bids;

    private int points;

    public EqList<BidBeloteSuit> getBids() {
        return bids;
    }

    public void setBids(EqList<BidBeloteSuit> _bids) {
        bids = _bids;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int _points) {
        points = _points;
    }
}
