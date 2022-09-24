package cards.tarot;

import cards.tarot.enumerations.BidTarot;

public final class BidTarotTaker {
    private final BidTarot bid;
    private final byte taker;

    public BidTarotTaker(BidTarot _b, byte _t) {
        this.bid = _b;
        this.taker = _t;
    }

    public BidTarot getBid() {
        return bid;
    }

    public byte getTaker() {
        return taker;
    }
}
