package cards.network.belote.displaying.errors;
import code.util.annot.RwXml;
import cards.belote.BidBeloteSuit;

@RwXml
public class ErrorBiddingBelote {

    private BidBeloteSuit bid;

    public BidBeloteSuit getBid() {
        return bid;
    }

    public void setBid(BidBeloteSuit _bid) {
        bid = _bid;
    }
}
