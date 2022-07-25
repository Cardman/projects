package aiki.game.fight;

public final class FighterPosition {
    private final Fighter fighter;
    private final byte firstPosit;

    public FighterPosition(Fighter _f, byte _p) {
        this.fighter = _f;
        this.firstPosit = _p;
    }

    public byte getFirstPosit() {
        return firstPosit;
    }

    public Fighter getFighter() {
        return fighter;
    }
}
