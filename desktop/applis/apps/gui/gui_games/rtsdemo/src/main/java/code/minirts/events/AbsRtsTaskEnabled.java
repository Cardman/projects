package code.minirts.events;

import code.threads.AbstractAtomicInteger;

public interface AbsRtsTaskEnabled {
    int status(AbstractAtomicInteger _current);
}
