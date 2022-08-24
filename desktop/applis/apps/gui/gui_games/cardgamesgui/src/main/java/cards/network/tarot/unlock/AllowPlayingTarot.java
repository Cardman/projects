package cards.network.tarot.unlock;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.EnumList;
import code.util.IdMap;


public final class AllowPlayingTarot {

    private boolean firstRoundPlaying;

    private EnumList<Handfuls> allowedHandfuls;

    private IdMap<Handfuls,Integer> requiredTrumps;

    private EnumList<Miseres> allowedMiseres;

    private byte takerIndex;

    public boolean isFirstRoundPlaying() {
        return firstRoundPlaying;
    }

    public void setFirstRoundPlaying(boolean _firstRoundPlaying) {
        firstRoundPlaying = _firstRoundPlaying;
    }

    public EnumList<Handfuls> getAllowedHandfuls() {
        return allowedHandfuls;
    }

    public void setAllowedHandfuls(EnumList<Handfuls> _allowedHandfuls) {
        allowedHandfuls = _allowedHandfuls;
    }

    public IdMap<Handfuls,Integer> getRequiredTrumps() {
        return requiredTrumps;
    }

    public void setRequiredTrumps(IdMap<Handfuls,Integer> _requiredTrumps) {
        requiredTrumps = _requiredTrumps;
    }

    public EnumList<Miseres> getAllowedMiseres() {
        return allowedMiseres;
    }

    public void setAllowedMiseres(EnumList<Miseres> _allowedMiseres) {
        allowedMiseres = _allowedMiseres;
    }

    public byte getTakerIndex() {
        return takerIndex;
    }

    public void setTakerIndex(byte _takerIndex) {
        takerIndex = _takerIndex;
    }
}
