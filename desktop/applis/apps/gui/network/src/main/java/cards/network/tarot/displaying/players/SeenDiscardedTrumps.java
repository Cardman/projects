package cards.network.tarot.displaying.players;
import cards.network.common.PlayerActionGame;


public final class SeenDiscardedTrumps extends PlayerActionGame {

    private boolean declaringSlam;

    public boolean isDeclaringSlam() {
        return declaringSlam;
    }

    public void setDeclaringSlam(boolean _declaringSlam) {
        declaringSlam = _declaringSlam;
    }
}
