package aiki.gui.listeners;

import code.threads.AbstractAtomicInteger;

public final class DefTaskEnabled implements AbsTaskEnabled {
    @Override
    public int status(AbstractAtomicInteger _current) {
        return _current.get();
    }
}
