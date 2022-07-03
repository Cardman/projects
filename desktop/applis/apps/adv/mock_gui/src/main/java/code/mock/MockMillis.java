package code.mock;

public final class MockMillis {
    private final long[] incr;
    private int index;
    private long current;

    public MockMillis(long _initMillis, long[] _incrs) {
        incr = _incrs;
        current = _initMillis;
    }
    public long millis() {
        if (index >= incr.length) {
            return current;
        }
        long inc_ = incr[index];
        if (index + 1 < incr.length) {
            index++;
        } else {
            index = 0;
        }
        current += inc_;
        return current;
    }
}
