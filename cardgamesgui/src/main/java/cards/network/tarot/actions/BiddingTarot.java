package cards.network.tarot.actions;
import cards.network.common.PlayerActionGame;
import cards.tarot.enumerations.BidTarot;
import code.util.annot.RwXml;

@RwXml
public final class BiddingTarot extends PlayerActionGame {

    private BidTarot bid;

    public BidTarot getBid() {
        return bid;
    }

    public void setBid(BidTarot _bid) {
        bid = _bid;
    }
}
