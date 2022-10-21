package aiki.util;

public final class TeamPositionsPairRatesPair {

    private final TeamPositionsPairRates foe;
    private final TeamPositionsPairRates player;

    public TeamPositionsPairRatesPair(TeamPositionsPairRates _f, TeamPositionsPairRates _p) {
        this.foe = _f;
        this.player = _p;
    }

    public TeamPositionsPairRates getFoe() {
        return foe;
    }

    public TeamPositionsPairRates getPlayer() {
        return player;
    }
}
