package cards.tarot;

import cards.tarot.enumerations.BidTarot;

public final class BidTarotTaker {
    private final BidTarot bid;
    private final byte taker;
    private final byte index;

    public BidTarotTaker(BidTarot _b, byte _t, byte _i) {
        this.bid = _b;
        this.taker = _t;
        this.index = _i;
    }

    public BidTarot getBid() {
        return bid;
    }

    public byte getTaker() {
        return taker;
    }

    public byte getIndex() {
        return index;
    }
}
