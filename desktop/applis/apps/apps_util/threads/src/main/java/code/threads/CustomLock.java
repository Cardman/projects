package code.threads;

import java.util.concurrent.atomic.AtomicBoolean;

public final class CustomLock implements AbstractLock {
    private final AtomicBoolean lock = new AtomicBoolean();
    private long heldCount;
    private AbstractThread exclusiveThread;
    private long sleep = 1;
    @Override
    public int lock(Locking _context) {
        AbstractThread currentThread_ = _context.getCurrentThread();
        if (currentThread_.getThread() == exclusiveThread.getThread()) {
            heldCount++;
            return -1;
        }
        while (!lock.compareAndSet(false,true)) {
            ThreadUtil.sleep(_context.getCurrentThreadFactory(),sleep);
            if (_context.isCurrentThreadEnded()) {
                return 1;
            }
        }
        exclusiveThread = currentThread_;
        return 0;
    }

    @Override
    public boolean heldLock(Locking _context) {
        return exclusiveThread.getThread() == _context.getCurrentThread().getThread();
    }

    @Override
    public int unlock(Locking _context) {
        AbstractThread currentThread_ = _context.getCurrentThread();
        if (currentThread_.getThread() != exclusiveThread.getThread()) {
            return -1;
        }
        if (heldCount == 0) {
            exclusiveThread = null;
            lock.set(false);
            return 0;
        }
        heldCount--;
        return 1;
    }

    public long getSleep() {
        return sleep;
    }

    public void setSleep(long _sleep) {
        this.sleep = Math.max(1, _sleep);
    }
}
