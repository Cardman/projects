package code.threads;

public interface AbstractLock {
    int lock(Locking _context);
    boolean heldLock(Locking _context);
    int unlock(Locking _context);
    long getSleep();
    void setSleep(long _sleep);

}
