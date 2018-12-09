package cards.network.tarot.unlock;
import cards.tarot.enumerations.BidTarot;
import code.util.EnumList;


public final class AllowBiddingTarot {

    private EnumList<BidTarot> bids;

    public EnumList<BidTarot> getBids() {
        return bids;
    }

    public void setBids(EnumList<BidTarot> _bids) {
        bids = _bids;
    }
}
