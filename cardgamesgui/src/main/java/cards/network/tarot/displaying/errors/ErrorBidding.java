package cards.network.tarot.displaying.errors;
import code.util.annot.RwXml;
import cards.tarot.enumerations.BidTarot;

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
