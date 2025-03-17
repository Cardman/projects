package cards.belote.enumerations;

public enum DeclaresBeloteRebelote {
    BELOTE_REBELOTE(20);
    private final long points;

    DeclaresBeloteRebelote(int _points) {
        points = _points;
    }

    public long getPoints() {
        return points;
    }

}

