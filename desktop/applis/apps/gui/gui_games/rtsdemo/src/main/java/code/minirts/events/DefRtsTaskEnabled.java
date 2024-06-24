package code.minirts.events;

import code.threads.AbstractAtomicInteger;

public final class DefRtsTaskEnabled implements AbsRtsTaskEnabled {
    @Override
    public int status(AbstractAtomicInteger _current) {
        return _current.get();
    }
}
