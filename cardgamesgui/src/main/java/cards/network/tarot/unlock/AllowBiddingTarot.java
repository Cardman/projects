package cards.network.tarot.unlock;
import code.util.EnumList;
import code.util.annot.RwXml;
import cards.tarot.enumerations.BidTarot;

@RwXml
public class AllowBiddingTarot {

    private EnumList<BidTarot> bids;

    public EnumList<BidTarot> getBids() {
        return bids;
    }

    public void setBids(EnumList<BidTarot> _bids) {
        bids = _bids;
    }
}
