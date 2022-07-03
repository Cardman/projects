package code.mock;

public final class MockRunnable implements Runnable{
    private boolean started;
    @Override
    public void run() {
        started = true;
    }

    public boolean isStarted() {
        return started;
    }
}
