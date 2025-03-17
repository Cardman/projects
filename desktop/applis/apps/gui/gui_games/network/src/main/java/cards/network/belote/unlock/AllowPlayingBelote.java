package cards.network.belote.unlock;
import cards.belote.*;


public final class AllowPlayingBelote {

    private boolean firstRoundPlaying;

    private DeclareHandBelote declaration;

    private boolean possibleBeloteRebelote;

    private boolean allowedBeloteRebelote;

    private int takerIndex;

    private BidBeloteSuit currentBid;

    private HandBelote belReb;
    private HandBelote cards;

    public boolean isFirstRoundPlaying() {
        return firstRoundPlaying;
    }

    public void setFirstRoundPlaying(boolean _firstRoundPlaying) {
        firstRoundPlaying = _firstRoundPlaying;
    }

    public DeclareHandBelote getDeclaration() {
        return declaration;
    }

    public void setDeclaration(DeclareHandBelote _declaration) {
        declaration = _declaration;
    }

    public boolean isPossibleBeloteRebelote() {
        return possibleBeloteRebelote;
    }

    public void setPossibleBeloteRebelote(boolean _possibleBeloteRebelote) {
        possibleBeloteRebelote = _possibleBeloteRebelote;
    }

    public boolean isAllowedBeloteRebelote() {
        return allowedBeloteRebelote;
    }

    public void setAllowedBeloteRebelote(boolean _allowedBeloteRebelote) {
        allowedBeloteRebelote = _allowedBeloteRebelote;
    }

    public int getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(int _takerIndex) {
        takerIndex = _takerIndex;
    }

    public BidBeloteSuit getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(BidBeloteSuit _c) {
        this.currentBid = _c;
    }

    public HandBelote getBelReb() {
        return belReb;
    }

    public void setBelReb(HandBelote _b) {
        this.belReb = _b;
    }

    public HandBelote getCards() {
        return cards;
    }

    public void setCards(HandBelote _c) {
        this.cards = _c;
    }
}
