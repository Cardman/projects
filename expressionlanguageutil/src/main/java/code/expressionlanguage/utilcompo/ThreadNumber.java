package code.expressionlanguage.utilcompo;

public final class ThreadNumber {
    private Thread thread;
    private long number;
    public ThreadNumber(Thread _th, long _number) {
        thread = _th;
        number = _number;
    }
    public boolean eq(ThreadNumber _th) {
        if (thread != _th.thread) {
            return false;
        }
        return number == _th.number;
    }

    public Thread getThread() {
        return thread;
    }

    public long getNumber() {
        return number;
    }
}
