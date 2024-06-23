package cards.network.common.before;

import code.network.AddingPlayer;

public final class PlayerCards extends PlayerActionBeforeGameCards implements AddingPlayer {

    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String _pseudo) {
        pseudo = _pseudo;
    }
}
