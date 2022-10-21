package aiki.beans.facade.fight;

public final class FighterNameId {
    private final String name;
    private final int position;

    public FighterNameId(String _n, int _p) {
        this.name = _n;
        this.position = _p;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
