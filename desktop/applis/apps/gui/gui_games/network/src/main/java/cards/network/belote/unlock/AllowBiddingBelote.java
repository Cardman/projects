package cards.network.belote.unlock;
import cards.belote.BidBeloteSuit;
import code.util.CustList;

public final class AllowBiddingBelote {

    private CustList<BidBeloteSuit> bids;
    private BidBeloteSuit bid;

    public CustList<BidBeloteSuit> getBids() {
        return bids;
    }

    public void setBids(CustList<BidBeloteSuit> _bids) {
        bids = _bids;
    }

    public BidBeloteSuit getBid() {
        return bid;
    }

    public void setBid(BidBeloteSuit _b) {
        this.bid = _b;
    }

}
