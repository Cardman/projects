package code.mock;

import code.threads.AbstractBaseExecutorService;

public final class MockBaseExecutorService implements AbstractBaseExecutorService {
    private boolean cancel;
    @Override
    public void execute(Runnable _run) {
        if (!cancel && _run != null) {
            _run.run();
        }
    }

    @Override
    public void shutdown() {
        cancel = true;
    }
}
