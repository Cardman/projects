package code.mock;

import code.threads.AbstractFuture;

public final class MockFuture implements AbstractFuture {
    private final boolean shutdown;
    public MockFuture(boolean _c) {
        shutdown = _c;
    }
    @Override
    public boolean cancel(boolean _b) {
        return shutdown||_b;
    }
}
