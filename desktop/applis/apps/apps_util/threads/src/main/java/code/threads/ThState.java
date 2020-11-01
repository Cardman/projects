package code.threads;

public enum ThState {
    INTERRUPTED,ENDED,ALIVE;
    static ThState ofNormal(boolean _alive) {
        if (_alive) {
            return ALIVE;
        }
        return ENDED;
    }

}
