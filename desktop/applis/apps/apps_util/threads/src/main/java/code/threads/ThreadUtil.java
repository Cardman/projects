package code.threads;

public final class ThreadUtil {

    private ThreadUtil() {
    }

    public static boolean start(Thread _thread) {
        if (_thread.isAlive()) {
            return false;
        }
        _thread.start();
        return true;
    }

    public static boolean setPriority(Thread _thread, int _prio) {
        if (_prio > Thread.MAX_PRIORITY || _prio < Thread.MIN_PRIORITY) {
            return false;
        }
        _thread.setPriority(_prio);
        return true;
    }
    public static ThState join(Thread _thread) {
        boolean alive_ = _thread.isAlive();
        try {
            _thread.join();
            return ThState.ofNormal(alive_);
        } catch (Exception e) {
            return ThState.INTERRUPTED;
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
