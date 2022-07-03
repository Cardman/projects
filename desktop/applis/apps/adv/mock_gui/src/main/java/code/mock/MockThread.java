package code.mock;

import code.threads.AbstractAtomicLong;
import code.threads.AbstractThread;
import code.threads.ThState;

public final class MockThread implements AbstractThread {

    private boolean alive;
    private final boolean immediate;
    private final Runnable runnable;
    private int priority;
    private boolean interrupted;
    private boolean invoked;
    private final long id;

    public MockThread(Runnable _r, boolean _i, AbstractAtomicLong _id) {
        this.immediate = _i;
        this.runnable = _r;
        this.id = _id.getAndIncrement();
    }

    @Override
    public void start() {
        setAlive(true);
        if (immediate && runnable != null) {
            runnable.run();
            invoked = true;
        }
        setAlive(false);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean setPriority(int _i) {
        priority = _i;
        return !isAlive();
    }

    @Override
    public ThState join() {
        boolean alive_ = isAlive();
        if (!invoked && runnable != null) {
            setAlive(true);
            runnable.run();
            invoked = true;
            setAlive(false);
        }
        return ThState.of(alive_);
    }

    public boolean isInvoked() {
        return invoked;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean _a) {
        this.alive = _a;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Runnable getThread() {
        return runnable;
    }

    @Override
    public boolean isInterrupted() {
        return interrupted;
    }

    @Override
    public void setInterrupted(boolean _b) {
        interrupted = _b;
    }
}
