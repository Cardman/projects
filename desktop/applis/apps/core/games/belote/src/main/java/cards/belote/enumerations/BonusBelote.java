package cards.belote.enumerations;

public enum BonusBelote {
    LAST_TRICK(10);
    private final long points;

    BonusBelote(int _points) {
        points = _points;
    }

    public long getPoints() {
        return points;
    }

}
