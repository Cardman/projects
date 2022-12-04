package cards.network.belote.unlock;
import cards.belote.BidBeloteSuit;
import code.util.CustList;

public final class AllowBiddingBelote {

    private CustList<BidBeloteSuit> bids;

    private int points;

    public CustList<BidBeloteSuit> getBids() {
        return bids;
    }

    public void setBids(CustList<BidBeloteSuit> _bids) {
        bids = _bids;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int _points) {
        points = _points;
    }
}
