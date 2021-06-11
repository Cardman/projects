package code.threads;

public final class ThreadUtil {

    private ThreadUtil() {
    }

    public static boolean sleepCurrent(AbstractThreadFactory _fact, long _time) {
        AbstractThread current_ = _fact.newThread();
        long millis_ = _fact.millis();
        while (millis_ + _time > _fact.millis()) {
            if (current_.isInterrupted()) {
                return false;
            }
        }
        return true;
    }
    public static boolean sleep(AbstractThreadFactory _fact,long _time) {
        if (_time <= 0) {
            return false;
        }
        return _fact.sleep(_time);
    }

}
