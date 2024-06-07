package cards.network.tarot.unlock;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.BidTarot;


public final class AllowPlayingTarot {

    private boolean firstRoundPlaying;

    private HandTarot discardedTrumps;

    private HandTarot calledCards;

    private byte takerIndex;
    private BidTarot currentBid;
    private HandTarot cards;

    public boolean isFirstRoundPlaying() {
        return firstRoundPlaying;
    }

    public void setFirstRoundPlaying(boolean _firstRoundPlaying) {
        firstRoundPlaying = _firstRoundPlaying;
    }

    public HandTarot getDiscardedTrumps() {
        return discardedTrumps;
    }

    public void setDiscardedTrumps(HandTarot _d) {
        this.discardedTrumps = _d;
    }

    public HandTarot getCalledCards() {
        return calledCards;
    }

    public void setCalledCards(HandTarot _c) {
        this.calledCards = _c;
    }

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }

    public BidTarot getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(BidTarot _c) {
        this.currentBid = _c;
    }

    public HandTarot getCards() {
        return cards;
    }

    public void setCards(HandTarot _c) {
        this.cards = _c;
    }
}
