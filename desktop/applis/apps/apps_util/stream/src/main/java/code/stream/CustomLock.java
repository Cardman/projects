package code.stream;

import java.util.concurrent.atomic.AtomicBoolean;

public final class CustomLock implements AbstractLock {
    private AtomicBoolean lock = new AtomicBoolean();
    private long heldCount;
    private Thread exclusiveThread;
    private long sleep = 1;
    @Override
    public int lock(Locking _context) {
        Thread currentThread_ = _context.getCurrentThread();
        if (currentThread_ == exclusiveThread) {
            heldCount++;
            return -1;
        }
        while (!lock.compareAndSet(false,true)) {
            ThreadUtil.sleep(sleep);
            if (_context.isCurrentThreadEnded()) {
                return 1;
            }
        }
        exclusiveThread = currentThread_;
        return 0;
    }

    @Override
    public boolean heldLock(Locking _context) {
        return exclusiveThread == _context.getCurrentThread();
    }

    @Override
    public int unlock(Locking _context) {
        Thread currentThread_ = _context.getCurrentThread();
        if (currentThread_ != exclusiveThread) {
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

    public void setSleep(long sleep) {
        this.sleep = Math.max(1,sleep);
    }
}
