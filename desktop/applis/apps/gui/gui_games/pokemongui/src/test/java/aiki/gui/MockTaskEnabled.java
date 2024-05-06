package aiki.gui;

import aiki.gui.listeners.AbsTaskEnabled;
import code.threads.AbstractAtomicInteger;
import code.util.core.NumberUtil;

public final class MockTaskEnabled implements AbsTaskEnabled {
    @Override
    public int status(AbstractAtomicInteger _current) {
        return NumberUtil.compareLg(3,_current.incrementAndGet());
    }
}
