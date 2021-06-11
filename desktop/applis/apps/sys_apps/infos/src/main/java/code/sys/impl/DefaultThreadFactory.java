package code.sys.impl;

import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;

public final class DefaultThreadFactory implements AbstractThreadFactory {

    @Override
    public long nanos() {
        return System.nanoTime();
    }

    @Override
    public long millis() {
        return System.currentTimeMillis();
    }

    @Override
    public boolean sleep(long _time) {
        return DefaultThread.simpleSleep(_time);
    }

    @Override
    public void newStartedThread(Runnable _runnable) {
        newThread(_runnable).start();
    }

    @Override
    public AbstractThread newThread(Runnable _runnable) {
        return new DefaultThread(_runnable);
    }

    @Override
    public AbstractThread newThread() {
        return new DefaultThread();
    }
}
