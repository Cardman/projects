package code.mock;

import code.threads.*;
import code.util.*;

public final class MockLaterFuture implements AbstractFuture {
    private final boolean shutdown;
    private final Runnable task;
    private final IntMap<AbstractFuture> tasks;
    private final int idTask;

    public MockLaterFuture(Runnable _r, boolean _s, IntMap<AbstractFuture> _t, int _id) {
        task = _r;
        shutdown = _s;
        tasks = _t;
        idTask = _id;
    }
    @Override
    public boolean cancel(boolean _b) {
        return shutdown;
    }

    @Override
    public boolean attendre() {
        if (!shutdown) {
            task.run();
            tasks.removeKey(idTask);
        }
        return shutdown;
    }
}
