package cards.belote.enumerations;

public enum DeclaresBeloteRebelote {
    BELOTE_REBELOTE(20);
    private final int points;

    DeclaresBeloteRebelote(int _points) {
        points = _points;
    }

    public int getPoints() {
        return points;
    }

}

