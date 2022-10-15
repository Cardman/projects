package cards.tarot.beans;

import code.expressionlanguage.structs.AbNullStruct;

public final class ScoresPlayersStruct extends AbNullStruct {
    private final ScoresPlayers scoresPlayers;

    public ScoresPlayersStruct(ScoresPlayers _scoresPlayers) {
        this.scoresPlayers = _scoresPlayers;
    }

    public ScoresPlayers getScoresPlayers() {
        return scoresPlayers;
    }
}
