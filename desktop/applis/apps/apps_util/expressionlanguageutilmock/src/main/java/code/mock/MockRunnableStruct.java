package code.mock;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;

public final class MockRunnableStruct extends WithoutParentIdStruct implements Runnable {
    private final String type;
    private boolean started;

    public MockRunnableStruct(String _t) {
        type = _t;
    }

    @Override
    public void run() {
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return type;
    }
}
