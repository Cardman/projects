package cards.network.belote.unlock;
import cards.belote.DeclareHandBelote;


public final class AllowPlayingBelote {

    private boolean firstRoundPlaying;

    private DeclareHandBelote declaration;

    private boolean possibleBeloteRebelote;

    private boolean allowedBeloteRebelote;

    private byte takerIndex;

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

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }
}
