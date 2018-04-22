package cards.network.tarot.displaying.errors;
import cards.tarot.enumerations.BidTarot;
import code.util.annot.RwXml;

@RwXml
public final class ErrorBidding {

    private BidTarot bid;

    public BidTarot getBid() {
        return bid;
    }

    public void setBid(BidTarot _bid) {
        bid = _bid;
    }

}
