package cards.belote.enumerations;

public enum BonusBelote {
    LAST_TRICK(10);
    private final int points;

    BonusBelote(int _points) {
        points = _points;
    }

    public int getPoints() {
        return points;
    }

}
