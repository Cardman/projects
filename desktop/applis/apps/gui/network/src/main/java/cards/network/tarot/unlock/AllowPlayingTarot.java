package cards.network.tarot.unlock;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.IdList;
import code.util.IdMap;


public final class AllowPlayingTarot {

    private boolean firstRoundPlaying;

    private IdList<Handfuls> allowedHandfuls;

    private IdMap<Handfuls,Integer> requiredTrumps;

    private IdList<Miseres> allowedMiseres;
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

    public IdList<Handfuls> getAllowedHandfuls() {
        return allowedHandfuls;
    }

    public void setAllowedHandfuls(IdList<Handfuls> _allowedHandfuls) {
        allowedHandfuls = _allowedHandfuls;
    }

    public IdMap<Handfuls,Integer> getRequiredTrumps() {
        return requiredTrumps;
    }

    public void setRequiredTrumps(IdMap<Handfuls,Integer> _requiredTrumps) {
        requiredTrumps = _requiredTrumps;
    }

    public IdList<Miseres> getAllowedMiseres() {
        return allowedMiseres;
    }

    public void setAllowedMiseres(IdList<Miseres> _allowedMiseres) {
        allowedMiseres = _allowedMiseres;
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
