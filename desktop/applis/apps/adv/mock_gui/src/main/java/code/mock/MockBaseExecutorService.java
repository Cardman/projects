package code.mock;

import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;

public class MockBaseExecutorService implements AbstractBaseExecutorService {
    private boolean cancel;
    @Override
    public void execute(Runnable _run) {
        submit(_run);
    }

    @Override
    public void shutdown() {
        cancel = true;
    }

    public boolean isCancel() {
        return cancel;
    }

    @Override
    public AbstractFuture submit(Runnable _run) {
        if (!cancel && _run != null) {
            _run.run();
        }
        return new MockFuture(cancel);
    }

    @Override
    public AbstractFuture submitLater(Runnable _command) {
        return new MockLaterFuture(_command,cancel);
    }
}
