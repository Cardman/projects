package cards.network.belote.displaying.errors;
import cards.belote.BidBeloteSuit;
import code.util.annot.RwXml;

@RwXml
public final class ErrorBiddingBelote {

    private BidBeloteSuit bid;

    public BidBeloteSuit getBid() {
        return bid;
    }

    public void setBid(BidBeloteSuit _bid) {
        bid = _bid;
    }
}
