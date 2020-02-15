package code.stream;

public final class ThreadUtil {

    private ThreadUtil() {
    }

    public static boolean start(Thread _thread) {
        try {
            _thread.start();
            return true;
        } catch (Exception _e) {
            return false;
        }
    }

    public static boolean setPriority(Thread _thread, int _prio) {
        try {
            _thread.setPriority(_prio);
            return true;
        } catch (Exception _e) {
            return false;
        }
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
