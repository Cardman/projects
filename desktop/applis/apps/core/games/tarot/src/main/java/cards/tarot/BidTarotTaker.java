package cards.tarot;

import cards.tarot.enumerations.BidTarot;

public final class BidTarotTaker {
    private final BidTarot bid;
    private final int taker;
    private final int index;

    public BidTarotTaker(BidTarot _b, int _t, int _i) {
        this.bid = _b;
        this.taker = _t;
        this.index = _i;
    }

    public BidTarot getBid() {
        return bid;
    }

    public int getTaker() {
        return taker;
    }

    public int getIndex() {
        return index;
    }
}
