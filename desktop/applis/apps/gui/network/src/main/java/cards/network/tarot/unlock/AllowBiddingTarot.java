package cards.network.tarot.unlock;
import cards.tarot.enumerations.BidTarot;
import code.util.IdList;


public final class AllowBiddingTarot {

    private IdList<BidTarot> bids;

    public IdList<BidTarot> getBids() {
        return bids;
    }

    public void setBids(IdList<BidTarot> _bids) {
        bids = _bids;
    }
}
