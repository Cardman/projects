package cards.tarot.beans;

import code.bean.nat.*;

public final class ScoresPlayersStruct extends NaNuSt {
    private final ScoresPlayers scoresPlayers;

    public ScoresPlayersStruct(ScoresPlayers _scoresPlayers) {
        this.scoresPlayers = _scoresPlayers;
    }

    public ScoresPlayers getScoresPlayers() {
        return scoresPlayers;
    }
}
