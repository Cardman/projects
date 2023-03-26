package code.vi.prot.impl;

import code.threads.AbstractThread;
import code.threads.ThState;

final class DefaultThread implements AbstractThread {
    private final Thread thread;
    private final Runnable runnable;
    private boolean interrupted;

    DefaultThread(Runnable _runnable) {
        thread = newThread(_runnable);
        runnable = _runnable;
    }

    DefaultThread() {
        thread = Thread.currentThread();
        runnable = null;
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
        try {
            boolean alive_ = _thread.isAlive();
            _thread.join();
            return ThState.of(alive_);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return ThState.INTERRUPTED;
        }
    }

    static Thread newThread(Runnable _runnable) {
        return new Thread(_runnable);
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
    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public boolean isInterrupted() {
        return interrupted;
    }

    @Override
    public void setInterrupted(boolean _interrupted) {
        this.interrupted = _interrupted;
    }

    @Override
    public void stopJoinSleep() {
        thread.interrupt();
    }
}
