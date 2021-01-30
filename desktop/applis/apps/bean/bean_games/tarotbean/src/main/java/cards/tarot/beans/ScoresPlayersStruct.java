package cards.tarot.beans;

import code.bean.nat.CommNatStruct;

public final class ScoresPlayersStruct extends CommNatStruct {
    private final ScoresPlayers scoresPlayers;

    public ScoresPlayersStruct(ScoresPlayers _scoresPlayers, String _className) {
        super(_className);
        this.scoresPlayers = _scoresPlayers;
    }

    public ScoresPlayers getScoresPlayers() {
        return scoresPlayers;
    }
}
