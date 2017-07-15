package cards.network.tarot.actions;
import code.util.annot.RwXml;
import cards.tarot.HandTarot;

@RwXml
public class DiscardedTrumps {

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
