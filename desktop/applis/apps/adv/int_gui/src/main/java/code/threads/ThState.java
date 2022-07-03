package code.threads;

public enum ThState {
    INTERRUPTED,ENDED,ALIVE;
    public static ThState of(boolean _alive) {
        return ofNormal(_alive);
    }

    private static ThState ofNormal(boolean _alive) {
        if (_alive) {
            return ALIVE;
        }
        return ENDED;
    }

}
