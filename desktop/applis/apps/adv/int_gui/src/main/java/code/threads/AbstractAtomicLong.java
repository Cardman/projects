package code.threads;

public interface AbstractAtomicLong {
    long get();

    void set(long _value);

    boolean compareAndSet(long _one, long _two);

    long getAndSet(long _value);

    long getAndAdd(long _value);

    long addAndGet(long _value);

    long getAndIncrement();

    long incrementAndGet();

    long getAndDecrement();

    long decrementAndGet();

    void lazySet(long _value);
}
