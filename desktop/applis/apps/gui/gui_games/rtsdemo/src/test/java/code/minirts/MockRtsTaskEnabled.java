package code.minirts;

import code.minirts.events.*;
import code.threads.*;
import code.util.core.*;

public final class MockRtsTaskEnabled implements AbsRtsTaskEnabled {
    @Override
    public int status(AbstractAtomicInteger _current) {
        return NumberUtil.compareLg(3,_current.incrementAndGet());
    }
}
