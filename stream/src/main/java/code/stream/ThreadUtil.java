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
    public static void sleep(long _time) {
        long millis_ = System.currentTimeMillis();
        while (millis_ + _time > System.currentTimeMillis()) {
            continue;
        }
    }

}
