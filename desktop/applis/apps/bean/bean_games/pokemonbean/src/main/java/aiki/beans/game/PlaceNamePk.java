package aiki.beans.game;

public final class PlaceNamePk {
    private final int index;
    private final String name;

    public PlaceNamePk(int _i, String _n) {
        this.index = _i;
        this.name = _n;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
