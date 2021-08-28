package code.sys.impl;

import code.threads.AbstractThread;
import code.threads.ThState;

final class DefaultThread implements AbstractThread {
    private final Thread thread;
    private boolean interrupted;

    DefaultThread(Runnable _runnable) {
        thread = new Thread(_runnable);
    }

    DefaultThread() {
        thread = Thread.currentThread();
    }

    static boolean simpleSleep(long _time) {
        try {
            Thread.sleep(_time);
            return true;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private static ThState join(Thread _thread) {
        boolean alive_ = _thread.isAlive();
        try {
            _thread.join();
            return ThState.of(alive_);
        } catch (Exception e) {
            _thread.interrupt();
            return ThState.INTERRUPTED;
        }
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public int getPriority() {
        return thread.getPriority();
    }

    @Override
    public boolean setPriority(int _prio) {
        try {
            thread.setPriority(_prio);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ThState join() {
        return join(thread);
    }

    @Override
    public boolean isAlive() {
        return thread.isAlive();
    }

    @Override
    public long getId() {
        return thread.getId();
    }

    @Override
    public Runnable getThread() {
        return thread;
    }

    @Override
    public boolean isInterrupted() {
        return interrupted;
    }

    @Override
    public void setInterrupted(boolean _interrupted) {
        this.interrupted = _interrupted;
    }
}
