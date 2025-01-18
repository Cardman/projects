package aiki.game.fight;

public final class FighterPosition {
    private final Fighter fighter;
    private final int firstPosit;

    public FighterPosition(Fighter _f, int _p) {
        this.fighter = _f;
        this.firstPosit = _p;
    }

    public int getFirstPosit() {
        return firstPosit;
    }

    public Fighter getFighter() {
        return fighter;
    }
}
