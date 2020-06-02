package code.threads;

public final class LockFactory {
    private LockFactory(){
    }
    public static AbstractLock newLock() {
        return new CustomLock();
    }
}
