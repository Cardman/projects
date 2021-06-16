package code.threads;

public interface AbstractAtomicInteger {
    int get();

    void set(int _value);

    boolean compareAndSet(int _one, int _two);

    int getAndSet(int _value);

    int getAndAdd(int _value);

    int addAndGet(int _value);

    int getAndIncrement();

    int incrementAndGet();

    int getAndDecrement();

    int decrementAndGet();

    void lazySet(int _value);
}
