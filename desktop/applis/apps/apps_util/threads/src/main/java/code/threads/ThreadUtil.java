package code.threads;

public final class ThreadUtil {

    private ThreadUtil() {
    }

    public static boolean start(Thread _thread) {
        try {
            _thread.start();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static boolean setPriority(Thread _thread, int _prio) {
        if (_prio > Thread.MAX_PRIORITY || _prio < Thread.MIN_PRIORITY) {
            return false;
        }
        _thread.setPriority(_prio);
        return true;
    }
    public static void join(Thread _thread) {
        while (_thread.isAlive()) {
            sleep(0);
        }
    }
    public static boolean sleep(long _time) {
        if (_time <= 0) {
            return false;
        }
        try {
            Thread.sleep(_time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
