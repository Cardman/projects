package code.expressionlanguage.adv;

public final class DbgLaunchTask implements Runnable {
    private final AbsDebuggerGui window;

    public DbgLaunchTask(AbsDebuggerGui _w) {
        this.window = _w;
    }

    @Override
    public void run() {
        window.next();
    }
}
