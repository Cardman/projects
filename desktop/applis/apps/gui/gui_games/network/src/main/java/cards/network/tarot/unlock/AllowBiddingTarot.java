package cards.network.tarot.unlock;
import cards.tarot.enumerations.BidTarot;
import code.util.IdList;


public final class AllowBiddingTarot {

    private IdList<BidTarot> bids;
    private BidTarot maxBid = BidTarot.FOLD;

    public IdList<BidTarot> getBids() {
        return bids;
    }

    public void setBids(IdList<BidTarot> _bids) {
        bids = _bids;
    }

    public BidTarot getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(BidTarot _m) {
        this.maxBid = _m;
    }
}
