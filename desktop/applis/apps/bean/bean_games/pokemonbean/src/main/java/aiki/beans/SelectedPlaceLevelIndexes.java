package aiki.beans;

public final class SelectedPlaceLevelIndexes {
    private final int place;
    private final int level;

    public SelectedPlaceLevelIndexes(int _pl, int _lev) {
        this.place = _pl;
        this.level = _lev;
    }

    public int getLevel() {
        return level;
    }

    public int getPlace() {
        return place;
    }
}
