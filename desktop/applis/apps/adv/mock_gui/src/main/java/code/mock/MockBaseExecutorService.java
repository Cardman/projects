package code.mock;

import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;
import code.util.IntMap;

public class MockBaseExecutorService implements AbstractBaseExecutorService {
    private boolean cancel;
    private int idFuture;
    private final IntMap<AbstractFuture> tasks = new IntMap<AbstractFuture>();
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
        int i_ = incr();
        MockFuture m_ = new MockFuture(cancel, getTasks(), i_);
        getTasks().addEntry(i_,m_);
        return m_;
    }

    @Override
    public AbstractFuture submitLater(Runnable _command) {
        int i_ = incr();
        MockLaterFuture m_ = new MockLaterFuture(_command, cancel, getTasks(), i_);
        getTasks().addEntry(i_,m_);
        return m_;
    }

    public IntMap<AbstractFuture> getTasks() {
        return tasks;
    }
    public int incr() {
        int i_ = getIdFuture();
        setIdFuture(i_+1);
        return i_;
    }

    public int getIdFuture() {
        return idFuture;
    }

    public void setIdFuture(int _i) {
        this.idFuture = _i;
    }
}
