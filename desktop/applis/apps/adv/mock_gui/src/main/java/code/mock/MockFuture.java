package code.mock;

import code.threads.*;
import code.util.*;

public class MockFuture implements AbstractFuture {
    private final boolean shutdown;
    private final IntMap<AbstractFuture> tasks;
    private final int idTask;
    public MockFuture(boolean _c, IntMap<AbstractFuture> _t, int _id) {
        shutdown = _c;
        tasks = _t;
        idTask = _id;
    }
    @Override
    public boolean cancel(boolean _b) {
        return attendre()||_b;
    }

    @Override
    public boolean attendre() {
        tasks.removeKey(idTask);
        return shutdown;
    }

    public IntMap<AbstractFuture> getTasks() {
        return tasks;
    }

    public int getIdTask() {
        return idTask;
    }
}
