package cards.network.tarot.actions;
import cards.tarot.HandTarot;


public final class DiscardedTrumps {

    private HandTarot trumps;

    private boolean declaringSlam;

    public HandTarot getTrumps() {
        return trumps;
    }

    public void setTrumps(HandTarot _trumps) {
        trumps = _trumps;
    }

    public boolean isDeclaringSlam() {
        return declaringSlam;
    }

    public void setDeclaringSlam(boolean _declaringSlam) {
        declaringSlam = _declaringSlam;
    }

}
