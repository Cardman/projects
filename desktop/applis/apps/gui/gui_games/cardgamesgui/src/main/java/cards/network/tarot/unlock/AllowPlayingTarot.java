package cards.network.tarot.unlock;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.IdList;
import code.util.IdMap;


public final class AllowPlayingTarot {

    private boolean firstRoundPlaying;

    private IdList<Handfuls> allowedHandfuls;

    private IdMap<Handfuls,Integer> requiredTrumps;

    private IdList<Miseres> allowedMiseres;

    private byte takerIndex;

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

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }
}
