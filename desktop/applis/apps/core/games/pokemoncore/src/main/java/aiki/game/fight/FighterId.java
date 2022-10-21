package aiki.game.fight;

public final class FighterId {
    private final Fighter fighter;
    private final TeamPosition id;

    public FighterId(Fighter _f, TeamPosition _p) {
        this.fighter = _f;
        this.id = _p;
    }

    public TeamPosition getId() {
        return id;
    }

    public Fighter getFighter() {
        return fighter;
    }
}
