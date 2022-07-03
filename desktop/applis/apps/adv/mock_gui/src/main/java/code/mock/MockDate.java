package code.mock;

import code.threads.AbstractDate;
import code.threads.AbstractDateFactory;

public final class MockDate implements AbstractDate {
    private final long millis;

    public MockDate(long _m) {
        this.millis = _m;
    }

    @Override
    public String format(AbstractDateFactory _fact, String _format) {
        return Long.toString(millis);
    }

    @Override
    public String format(long _offset, String _format) {
        return Long.toString(millis);
    }


}
