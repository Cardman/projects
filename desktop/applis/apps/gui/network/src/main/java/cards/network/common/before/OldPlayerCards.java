package cards.network.common.before;
import code.network.AddingPlayer;


public final class OldPlayerCards extends PlayerActionBeforeGameCards implements AddingPlayer {

    private String pseudo;
    private int target;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String _pseudo) {
        pseudo = _pseudo;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int _t) {
        this.target = _t;
    }

}
