package code.stream;

public interface AbstractLock {
    int lock(Locking _context);
    boolean heldLock(Locking _context);
    int unlock(Locking _context);
    long getSleep();
    void setSleep(long sleep);

}
