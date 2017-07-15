package cards.network.tarot.displaying.players;
import code.util.annot.RwXml;
import cards.network.common.PlayerActionGame;

@RwXml
public class SeenDiscardedTrumps extends PlayerActionGame {

    private boolean declaringSlam;

    public boolean isDeclaringSlam() {
        return declaringSlam;
    }

    public void setDeclaringSlam(boolean _declaringSlam) {
        declaringSlam = _declaringSlam;
    }
}
