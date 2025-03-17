package cards.tarot.enumerations;

/**Prime pour le chelem au tarot*/
public enum BonusTarot {
    SLAM(400),
    SMALL_BOUND(10);
    private final long points;
    BonusTarot(int _points){
        points = _points;
    }
    public long getPoints() {
        return points;
    }

}
